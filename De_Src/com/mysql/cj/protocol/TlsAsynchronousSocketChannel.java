/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.exceptions.AssertionFailedException;
import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.protocol.SerializingBufferWriter;
import java.io.IOException;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;

public class TlsAsynchronousSocketChannel
extends AsynchronousSocketChannel
implements CompletionHandler<Integer, Void> {
    private static final ByteBuffer emptyBuffer = ByteBuffer.allocate(0);
    private AsynchronousSocketChannel channel;
    private SSLEngine sslEngine;
    private ByteBuffer cipherTextBuffer;
    private ByteBuffer clearTextBuffer;
    private CompletionHandler<Integer, ?> handler;
    private ByteBuffer dst;
    private SerializingBufferWriter bufferWriter;
    private LinkedBlockingQueue<ByteBuffer> cipherTextBuffers = new LinkedBlockingQueue();

    public TlsAsynchronousSocketChannel(AsynchronousSocketChannel in, SSLEngine sslEngine) {
        super(null);
        this.sslEngine = sslEngine;
        this.channel = in;
        this.sslEngine = sslEngine;
        this.cipherTextBuffer = ByteBuffer.allocate(sslEngine.getSession().getPacketBufferSize());
        this.cipherTextBuffer.flip();
        this.clearTextBuffer = ByteBuffer.allocate(sslEngine.getSession().getApplicationBufferSize());
        this.clearTextBuffer.flip();
        this.bufferWriter = new SerializingBufferWriter(this.channel);
    }

    @Override
    public void completed(Integer result, Void attachment) {
        if (result < 0) {
            CompletionHandler<Integer, ?> h2 = this.handler;
            this.handler = null;
            h2.completed(result, null);
            return;
        }
        this.cipherTextBuffer.flip();
        this.decryptAndDispatch();
    }

    @Override
    public void failed(Throwable exc, Void attachment) {
        CompletionHandler<Integer, ?> h2 = this.handler;
        this.handler = null;
        h2.failed(exc, null);
    }

    private synchronized void decryptAndDispatch() {
        try {
            this.clearTextBuffer.clear();
            SSLEngineResult res = this.sslEngine.unwrap(this.cipherTextBuffer, this.clearTextBuffer);
            switch (res.getStatus()) {
                case BUFFER_UNDERFLOW: {
                    int newPeerNetDataSize = this.sslEngine.getSession().getPacketBufferSize();
                    if (newPeerNetDataSize > this.cipherTextBuffer.capacity()) {
                        ByteBuffer newPeerNetData = ByteBuffer.allocate(newPeerNetDataSize);
                        newPeerNetData.put(this.cipherTextBuffer);
                        newPeerNetData.flip();
                        this.cipherTextBuffer = newPeerNetData;
                    } else {
                        this.cipherTextBuffer.compact();
                    }
                    this.channel.read(this.cipherTextBuffer, null, this);
                    return;
                }
                case BUFFER_OVERFLOW: {
                    throw new BufferOverflowException();
                }
                case OK: {
                    this.clearTextBuffer.flip();
                    this.dispatchData();
                    break;
                }
                case CLOSED: {
                    this.handler.completed(-1, null);
                }
            }
        } catch (Throwable ex) {
            this.failed(ex, null);
        }
    }

    @Override
    public <A> void read(ByteBuffer dest, long timeout2, TimeUnit unit, A attachment, CompletionHandler<Integer, ? super A> hdlr) {
        try {
            if (this.handler != null) {
                hdlr.completed(0, null);
            }
            this.handler = hdlr;
            this.dst = dest;
            if (this.clearTextBuffer.hasRemaining()) {
                this.dispatchData();
            } else if (this.cipherTextBuffer.hasRemaining()) {
                this.decryptAndDispatch();
            } else {
                this.cipherTextBuffer.clear();
                this.channel.read(this.cipherTextBuffer, null, this);
            }
        } catch (Throwable ex) {
            hdlr.failed(ex, null);
        }
    }

    @Override
    public <A> void read(ByteBuffer[] dsts, int offset, int length, long timeout2, TimeUnit unit, A attachment, CompletionHandler<Long, ? super A> hdlr) {
        hdlr.failed(new UnsupportedOperationException(), null);
    }

    private synchronized void dispatchData() {
        final int transferred = Math.min(this.dst.remaining(), this.clearTextBuffer.remaining());
        if (this.clearTextBuffer.remaining() > this.dst.remaining()) {
            int newLimit = this.clearTextBuffer.position() + transferred;
            ByteBuffer src = this.clearTextBuffer.duplicate();
            src.limit(newLimit);
            this.dst.put(src);
            this.clearTextBuffer.position(this.clearTextBuffer.position() + transferred);
        } else {
            this.dst.put(this.clearTextBuffer);
        }
        final CompletionHandler<Integer, ?> h2 = this.handler;
        this.handler = null;
        if (this.channel.isOpen()) {
            this.channel.read(emptyBuffer, null, new CompletionHandler<Integer, Void>(){

                @Override
                public void completed(Integer result, Void attachment) {
                    h2.completed(transferred, null);
                }

                @Override
                public void failed(Throwable t, Void attachment) {
                    t.printStackTrace();
                    h2.failed(AssertionFailedException.shouldNotHappen(new Exception(t)), null);
                }
            });
        } else {
            h2.completed(transferred, null);
        }
    }

    @Override
    public void close() throws IOException {
        this.channel.close();
    }

    @Override
    public boolean isOpen() {
        return this.channel.isOpen();
    }

    @Override
    public Future<Integer> read(ByteBuffer dest) {
        throw new UnsupportedOperationException("This channel does not support direct reads");
    }

    @Override
    public Future<Integer> write(ByteBuffer src) {
        throw new UnsupportedOperationException("This channel does not support writes");
    }

    private boolean isDrained(ByteBuffer[] buffers) {
        for (ByteBuffer b : buffers) {
            if (!b.hasRemaining()) continue;
            return false;
        }
        return true;
    }

    @Override
    public <A> void write(ByteBuffer[] srcs, int offset, int length, long timeout2, TimeUnit unit, A attachment, CompletionHandler<Long, ? super A> hdlr) {
        try {
            long totalWriteSize = 0L;
            while (true) {
                ByteBuffer cipherText;
                SSLEngineResult res;
                if ((res = this.sslEngine.wrap(srcs, offset, length, cipherText = this.getCipherTextBuffer())).getStatus() != SSLEngineResult.Status.OK) {
                    hdlr.failed(new CJCommunicationsException("Unacceptable SSLEngine result: " + res), null);
                }
                totalWriteSize += (long)res.bytesConsumed();
                cipherText.flip();
                if (this.isDrained(srcs)) {
                    long finalTotal = totalWriteSize;
                    Runnable successHandler = () -> {
                        hdlr.completed(finalTotal, null);
                        this.putCipherTextBuffer(cipherText);
                    };
                    this.bufferWriter.queueBuffer(cipherText, new ErrorPropagatingCompletionHandler<Long>(hdlr, successHandler));
                    break;
                }
                this.bufferWriter.queueBuffer(cipherText, new ErrorPropagatingCompletionHandler<Long>(hdlr, () -> this.putCipherTextBuffer(cipherText)));
            }
        } catch (SSLException ex) {
            hdlr.failed(new CJCommunicationsException(ex), null);
        } catch (Throwable ex) {
            hdlr.failed(ex, null);
        }
    }

    @Override
    public <A> void write(ByteBuffer src, long timeout2, TimeUnit unit, A attachment, CompletionHandler<Integer, ? super A> hdlr) {
        hdlr.failed(new UnsupportedOperationException(), null);
    }

    private ByteBuffer getCipherTextBuffer() {
        ByteBuffer buf = this.cipherTextBuffers.poll();
        if (buf == null) {
            return ByteBuffer.allocate(this.sslEngine.getSession().getPacketBufferSize());
        }
        buf.clear();
        return buf;
    }

    private void putCipherTextBuffer(ByteBuffer buf) {
        if (this.cipherTextBuffers.size() < 10) {
            this.cipherTextBuffers.offer(buf);
        }
    }

    @Override
    public <T> T getOption(SocketOption<T> name) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<SocketOption<?>> supportedOptions() {
        throw new UnsupportedOperationException();
    }

    @Override
    public AsynchronousSocketChannel bind(SocketAddress local) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> AsynchronousSocketChannel setOption(SocketOption<T> name, T value) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public AsynchronousSocketChannel shutdownInput() throws IOException {
        return this.channel.shutdownInput();
    }

    @Override
    public AsynchronousSocketChannel shutdownOutput() throws IOException {
        return this.channel.shutdownOutput();
    }

    @Override
    public SocketAddress getRemoteAddress() throws IOException {
        return this.channel.getRemoteAddress();
    }

    @Override
    public <A> void connect(SocketAddress remote, A attachment, CompletionHandler<Void, ? super A> hdlr) {
        hdlr.failed(new UnsupportedOperationException(), null);
    }

    @Override
    public Future<Void> connect(SocketAddress remote) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SocketAddress getLocalAddress() throws IOException {
        return this.channel.getLocalAddress();
    }

    private static class ErrorPropagatingCompletionHandler<V>
    implements CompletionHandler<V, Void> {
        private CompletionHandler<Long, ?> target;
        private Runnable success;

        public ErrorPropagatingCompletionHandler(CompletionHandler<Long, ?> target, Runnable success) {
            this.target = target;
            this.success = success;
        }

        @Override
        public void completed(V result, Void attachment) {
            this.success.run();
        }

        @Override
        public void failed(Throwable ex, Void attachment) {
            this.target.failed(ex, null);
        }
    }
}

