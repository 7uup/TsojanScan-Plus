/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.ReadPendingException;
import java.nio.channels.WritePendingException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class SerializingBufferWriter
implements CompletionHandler<Long, Void> {
    private static int WRITES_AT_ONCE = 200;
    protected AsynchronousSocketChannel channel;
    private Queue<ByteBufferWrapper> pendingWrites = new LinkedList<ByteBufferWrapper>();

    public SerializingBufferWriter(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }

    private void initiateWrite() {
        try {
            ByteBuffer[] bufs = (ByteBuffer[])this.pendingWrites.stream().limit(WRITES_AT_ONCE).map(ByteBufferWrapper::getBuffer).toArray(ByteBuffer[]::new);
            this.channel.write(bufs, 0, bufs.length, 0L, TimeUnit.MILLISECONDS, null, this);
        } catch (ReadPendingException | WritePendingException t) {
            return;
        } catch (Throwable t) {
            this.failed(t, null);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void queueBuffer(ByteBuffer buf, CompletionHandler<Long, Void> callback) {
        Queue<ByteBufferWrapper> queue = this.pendingWrites;
        synchronized (queue) {
            this.pendingWrites.add(new ByteBufferWrapper(buf, callback));
            if (this.pendingWrites.size() == 1) {
                this.initiateWrite();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void completed(Long bytesWritten, Void v) {
        LinkedList<CompletionHandler<Long, Void>> completedWrites = new LinkedList<CompletionHandler<Long, Void>>();
        Queue<ByteBufferWrapper> queue = this.pendingWrites;
        synchronized (queue) {
            while (this.pendingWrites.peek() != null && !this.pendingWrites.peek().getBuffer().hasRemaining() && completedWrites.size() < WRITES_AT_ONCE) {
                completedWrites.add(this.pendingWrites.remove().getHandler());
            }
            completedWrites.stream().filter(Objects::nonNull).forEach(l -> {
                try {
                    l.completed(0L, null);
                } catch (Throwable ex) {
                    try {
                        l.failed(ex, null);
                    } catch (Throwable ex2) {
                        ex2.printStackTrace();
                    }
                }
            });
            if (this.pendingWrites.size() > 0) {
                this.initiateWrite();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void failed(Throwable t, Void v) {
        try {
            this.channel.close();
        } catch (Exception exception) {
            // empty catch block
        }
        LinkedList<CompletionHandler<Long, Void>> failedWrites = new LinkedList<CompletionHandler<Long, Void>>();
        Queue<ByteBufferWrapper> queue = this.pendingWrites;
        synchronized (queue) {
            while (this.pendingWrites.peek() != null) {
                ByteBufferWrapper bw = this.pendingWrites.remove();
                if (bw.getHandler() == null) continue;
                failedWrites.add(bw.getHandler());
            }
        }
        failedWrites.forEach(l -> {
            try {
                l.failed(t, null);
            } catch (Exception exception) {
                // empty catch block
            }
        });
        failedWrites.clear();
    }

    public void setChannel(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }

    private static class ByteBufferWrapper {
        private ByteBuffer buffer;
        private CompletionHandler<Long, Void> handler = null;

        ByteBufferWrapper(ByteBuffer buffer, CompletionHandler<Long, Void> completionHandler) {
            this.buffer = buffer;
            this.handler = completionHandler;
        }

        public ByteBuffer getBuffer() {
            return this.buffer;
        }

        public CompletionHandler<Long, Void> getHandler() {
            return this.handler;
        }
    }
}

