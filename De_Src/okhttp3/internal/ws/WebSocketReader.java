/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okhttp3.internal.ws.MessageInflater;
import okhttp3.internal.ws.WebSocketProtocol;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001&B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\nJ\b\u0010\u001e\u001a\u00020\u001fH\u0016J\u0006\u0010 \u001a\u00020\u001fJ\b\u0010!\u001a\u00020\u001fH\u0002J\b\u0010\"\u001a\u00020\u001fH\u0002J\b\u0010#\u001a\u00020\u001fH\u0002J\b\u0010$\u001a\u00020\u001fH\u0002J\b\u0010%\u001a\u00020\u001fH\u0002R\u000e\u0010\u000b\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d\u00a8\u0006'"}, d2={"Lokhttp3/internal/ws/WebSocketReader;", "Ljava/io/Closeable;", "isClient", "", "source", "Lokio/BufferedSource;", "frameCallback", "Lokhttp3/internal/ws/WebSocketReader$FrameCallback;", "perMessageDeflate", "noContextTakeover", "(ZLokio/BufferedSource;Lokhttp3/internal/ws/WebSocketReader$FrameCallback;ZZ)V", "closed", "controlFrameBuffer", "Lokio/Buffer;", "frameLength", "", "isControlFrame", "isFinalFrame", "maskCursor", "Lokio/Buffer$UnsafeCursor;", "maskKey", "", "messageFrameBuffer", "messageInflater", "Lokhttp3/internal/ws/MessageInflater;", "opcode", "", "readingCompressedMessage", "getSource", "()Lokio/BufferedSource;", "close", "", "processNextFrame", "readControlFrame", "readHeader", "readMessage", "readMessageFrame", "readUntilNonControlFrame", "FrameCallback", "okhttp"})
public final class WebSocketReader
implements Closeable {
    private boolean closed;
    private int opcode;
    private long frameLength;
    private boolean isFinalFrame;
    private boolean isControlFrame;
    private boolean readingCompressedMessage;
    private final Buffer controlFrameBuffer;
    private final Buffer messageFrameBuffer;
    private MessageInflater messageInflater;
    private final byte[] maskKey;
    private final Buffer.UnsafeCursor maskCursor;
    private final boolean isClient;
    @NotNull
    private final BufferedSource source;
    private final FrameCallback frameCallback;
    private final boolean perMessageDeflate;
    private final boolean noContextTakeover;

    public final void processNextFrame() throws IOException {
        this.readHeader();
        if (this.isControlFrame) {
            this.readControlFrame();
        } else {
            this.readMessageFrame();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void readHeader() throws IOException, ProtocolException {
        boolean isMasked;
        boolean reservedFlag3;
        boolean reservedFlag2;
        if (this.closed) {
            throw (Throwable)new IOException("closed");
        }
        int b0 = 0;
        long timeoutBefore = this.source.timeout().timeoutNanos();
        this.source.timeout().clearTimeout();
        try {
            b0 = Util.and(this.source.readByte(), 255);
        } finally {
            this.source.timeout().timeout(timeoutBefore, TimeUnit.NANOSECONDS);
        }
        this.opcode = b0 & 0xF;
        this.isFinalFrame = (b0 & 0x80) != 0;
        boolean bl = this.isControlFrame = (b0 & 8) != 0;
        if (this.isControlFrame && !this.isFinalFrame) {
            throw (Throwable)new ProtocolException("Control frames must be final.");
        }
        boolean reservedFlag1 = (b0 & 0x40) != 0;
        switch (this.opcode) {
            case 1: 
            case 2: {
                boolean bl2;
                if (reservedFlag1) {
                    if (!this.perMessageDeflate) {
                        throw (Throwable)new ProtocolException("Unexpected rsv1 flag");
                    }
                    bl2 = true;
                } else {
                    bl2 = false;
                }
                this.readingCompressedMessage = bl2;
                break;
            }
            default: {
                if (!reservedFlag1) break;
                throw (Throwable)new ProtocolException("Unexpected rsv1 flag");
            }
        }
        boolean bl3 = reservedFlag2 = (b0 & 0x20) != 0;
        if (reservedFlag2) {
            throw (Throwable)new ProtocolException("Unexpected rsv2 flag");
        }
        boolean bl4 = reservedFlag3 = (b0 & 0x10) != 0;
        if (reservedFlag3) {
            throw (Throwable)new ProtocolException("Unexpected rsv3 flag");
        }
        int b1 = Util.and(this.source.readByte(), 255);
        boolean bl5 = isMasked = (b1 & 0x80) != 0;
        if (isMasked == this.isClient) {
            throw (Throwable)new ProtocolException(this.isClient ? "Server-sent frames must not be masked." : "Client-sent frames must be masked.");
        }
        this.frameLength = b1 & 0x7F;
        if (this.frameLength == (long)126) {
            this.frameLength = Util.and(this.source.readShort(), 65535);
        } else if (this.frameLength == (long)127) {
            this.frameLength = this.source.readLong();
            if (this.frameLength < 0L) {
                throw (Throwable)new ProtocolException("Frame length 0x" + Util.toHexString(this.frameLength) + " > 0x7FFFFFFFFFFFFFFF");
            }
        }
        if (this.isControlFrame && this.frameLength > 125L) {
            throw (Throwable)new ProtocolException("Control frame must be less than 125B.");
        }
        if (isMasked) {
            Intrinsics.checkNotNull(this.maskKey);
            this.source.readFully(this.maskKey);
        }
    }

    private final void readControlFrame() throws IOException {
        if (this.frameLength > 0L) {
            this.source.readFully(this.controlFrameBuffer, this.frameLength);
            if (!this.isClient) {
                Buffer.UnsafeCursor unsafeCursor = this.maskCursor;
                Intrinsics.checkNotNull(unsafeCursor);
                this.controlFrameBuffer.readAndWriteUnsafe(unsafeCursor);
                this.maskCursor.seek(0L);
                Intrinsics.checkNotNull(this.maskKey);
                WebSocketProtocol.INSTANCE.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        }
        switch (this.opcode) {
            case 9: {
                this.frameCallback.onReadPing(this.controlFrameBuffer.readByteString());
                break;
            }
            case 10: {
                this.frameCallback.onReadPong(this.controlFrameBuffer.readByteString());
                break;
            }
            case 8: {
                int code = 1005;
                String reason = "";
                long bufferSize = this.controlFrameBuffer.size();
                if (bufferSize == 1L) {
                    throw (Throwable)new ProtocolException("Malformed close payload length of 1.");
                }
                if (bufferSize != 0L) {
                    code = this.controlFrameBuffer.readShort();
                    reason = this.controlFrameBuffer.readUtf8();
                    String codeExceptionMessage = WebSocketProtocol.INSTANCE.closeCodeExceptionMessage(code);
                    if (codeExceptionMessage != null) {
                        throw (Throwable)new ProtocolException(codeExceptionMessage);
                    }
                }
                this.frameCallback.onReadClose(code, reason);
                this.closed = true;
                break;
            }
            default: {
                throw (Throwable)new ProtocolException("Unknown control opcode: " + Util.toHexString(this.opcode));
            }
        }
    }

    private final void readMessageFrame() throws IOException {
        int opcode = this.opcode;
        if (opcode != 1 && opcode != 2) {
            throw (Throwable)new ProtocolException("Unknown opcode: " + Util.toHexString(opcode));
        }
        this.readMessage();
        if (this.readingCompressedMessage) {
            MessageInflater messageInflater = this.messageInflater;
            if (messageInflater == null) {
                MessageInflater messageInflater2 = new MessageInflater(this.noContextTakeover);
                boolean bl = false;
                boolean bl2 = false;
                MessageInflater it = messageInflater2;
                boolean bl3 = false;
                this.messageInflater = it;
                messageInflater = messageInflater2;
            }
            MessageInflater messageInflater3 = messageInflater;
            messageInflater3.inflate(this.messageFrameBuffer);
        }
        if (opcode == 1) {
            this.frameCallback.onReadMessage(this.messageFrameBuffer.readUtf8());
        } else {
            this.frameCallback.onReadMessage(this.messageFrameBuffer.readByteString());
        }
    }

    private final void readUntilNonControlFrame() throws IOException {
        while (!this.closed) {
            this.readHeader();
            if (!this.isControlFrame) break;
            this.readControlFrame();
        }
    }

    private final void readMessage() throws IOException {
        block4: {
            do {
                if (this.closed) {
                    throw (Throwable)new IOException("closed");
                }
                if (this.frameLength > 0L) {
                    this.source.readFully(this.messageFrameBuffer, this.frameLength);
                    if (!this.isClient) {
                        Buffer.UnsafeCursor unsafeCursor = this.maskCursor;
                        Intrinsics.checkNotNull(unsafeCursor);
                        this.messageFrameBuffer.readAndWriteUnsafe(unsafeCursor);
                        this.maskCursor.seek(this.messageFrameBuffer.size() - this.frameLength);
                        Intrinsics.checkNotNull(this.maskKey);
                        WebSocketProtocol.INSTANCE.toggleMask(this.maskCursor, this.maskKey);
                        this.maskCursor.close();
                    }
                }
                if (this.isFinalFrame) break block4;
                this.readUntilNonControlFrame();
            } while (this.opcode == 0);
            throw (Throwable)new ProtocolException("Expected continuation opcode. Got: " + Util.toHexString(this.opcode));
        }
    }

    @Override
    public void close() throws IOException {
        block0: {
            MessageInflater messageInflater = this.messageInflater;
            if (messageInflater == null) break block0;
            messageInflater.close();
        }
    }

    @NotNull
    public final BufferedSource getSource() {
        return this.source;
    }

    public WebSocketReader(boolean isClient, @NotNull BufferedSource source2, @NotNull FrameCallback frameCallback, boolean perMessageDeflate, boolean noContextTakeover) {
        Intrinsics.checkNotNullParameter(source2, "source");
        Intrinsics.checkNotNullParameter(frameCallback, "frameCallback");
        this.isClient = isClient;
        this.source = source2;
        this.frameCallback = frameCallback;
        this.perMessageDeflate = perMessageDeflate;
        this.noContextTakeover = noContextTakeover;
        this.controlFrameBuffer = new Buffer();
        this.messageFrameBuffer = new Buffer();
        this.maskKey = this.isClient ? null : new byte[4];
        this.maskCursor = this.isClient ? null : new Buffer.UnsafeCursor();
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000bH&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000bH&\u00a8\u0006\u000f"}, d2={"Lokhttp3/internal/ws/WebSocketReader$FrameCallback;", "", "onReadClose", "", "code", "", "reason", "", "onReadMessage", "text", "bytes", "Lokio/ByteString;", "onReadPing", "payload", "onReadPong", "okhttp"})
    public static interface FrameCallback {
        public void onReadMessage(@NotNull String var1) throws IOException;

        public void onReadMessage(@NotNull ByteString var1) throws IOException;

        public void onReadPing(@NotNull ByteString var1);

        public void onReadPong(@NotNull ByteString var1);

        public void onReadClose(int var1, @NotNull String var2);
    }
}

