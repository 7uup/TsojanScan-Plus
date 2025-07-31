/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.ws.MessageDeflater;
import okhttp3.internal.ws.WebSocketProtocol;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u0018\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!J\u0018\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020!H\u0002J\u0016\u0010%\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020!J\u000e\u0010(\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020!J\u000e\u0010)\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020!R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2={"Lokhttp3/internal/ws/WebSocketWriter;", "Ljava/io/Closeable;", "isClient", "", "sink", "Lokio/BufferedSink;", "random", "Ljava/util/Random;", "perMessageDeflate", "noContextTakeover", "minimumDeflateSize", "", "(ZLokio/BufferedSink;Ljava/util/Random;ZZJ)V", "maskCursor", "Lokio/Buffer$UnsafeCursor;", "maskKey", "", "messageBuffer", "Lokio/Buffer;", "messageDeflater", "Lokhttp3/internal/ws/MessageDeflater;", "getRandom", "()Ljava/util/Random;", "getSink", "()Lokio/BufferedSink;", "sinkBuffer", "writerClosed", "close", "", "writeClose", "code", "", "reason", "Lokio/ByteString;", "writeControlFrame", "opcode", "payload", "writeMessageFrame", "formatOpcode", "data", "writePing", "writePong", "okhttp"})
public final class WebSocketWriter
implements Closeable {
    private final Buffer messageBuffer;
    private final Buffer sinkBuffer;
    private boolean writerClosed;
    private MessageDeflater messageDeflater;
    private final byte[] maskKey;
    private final Buffer.UnsafeCursor maskCursor;
    private final boolean isClient;
    @NotNull
    private final BufferedSink sink;
    @NotNull
    private final Random random;
    private final boolean perMessageDeflate;
    private final boolean noContextTakeover;
    private final long minimumDeflateSize;

    public final void writePing(@NotNull ByteString payload) throws IOException {
        Intrinsics.checkNotNullParameter(payload, "payload");
        this.writeControlFrame(9, payload);
    }

    public final void writePong(@NotNull ByteString payload) throws IOException {
        Intrinsics.checkNotNullParameter(payload, "payload");
        this.writeControlFrame(10, payload);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void writeClose(int code, @Nullable ByteString reason) throws IOException {
        ByteString payload = ByteString.EMPTY;
        if (code != 0 || reason != null) {
            if (code != 0) {
                WebSocketProtocol.INSTANCE.validateCloseCode(code);
            }
            Buffer buffer = new Buffer();
            boolean bl = false;
            boolean bl2 = false;
            Buffer $this$run = buffer;
            boolean bl3 = false;
            $this$run.writeShort(code);
            if (reason != null) {
                $this$run.write(reason);
            }
            payload = $this$run.readByteString();
        }
        try {
            this.writeControlFrame(8, payload);
        } finally {
            this.writerClosed = true;
        }
    }

    private final void writeControlFrame(int opcode, ByteString payload) throws IOException {
        if (this.writerClosed) {
            throw (Throwable)new IOException("closed");
        }
        int length = payload.size();
        boolean bl = (long)length <= 125L;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "Payload size must be less than or equal to 125";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        int b0 = 0x80 | opcode;
        this.sinkBuffer.writeByte(b0);
        int b1 = length;
        if (this.isClient) {
            this.sinkBuffer.writeByte(b1 |= 0x80);
            Intrinsics.checkNotNull(this.maskKey);
            this.random.nextBytes(this.maskKey);
            this.sinkBuffer.write(this.maskKey);
            if (length > 0) {
                long payloadStart = this.sinkBuffer.size();
                this.sinkBuffer.write(payload);
                Buffer.UnsafeCursor unsafeCursor = this.maskCursor;
                Intrinsics.checkNotNull(unsafeCursor);
                this.sinkBuffer.readAndWriteUnsafe(unsafeCursor);
                this.maskCursor.seek(payloadStart);
                WebSocketProtocol.INSTANCE.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        } else {
            this.sinkBuffer.writeByte(b1);
            this.sinkBuffer.write(payload);
        }
        this.sink.flush();
    }

    public final void writeMessageFrame(int formatOpcode, @NotNull ByteString data) throws IOException {
        Intrinsics.checkNotNullParameter(data, "data");
        if (this.writerClosed) {
            throw (Throwable)new IOException("closed");
        }
        this.messageBuffer.write(data);
        int b0 = formatOpcode | 0x80;
        if (this.perMessageDeflate && (long)data.size() >= this.minimumDeflateSize) {
            MessageDeflater messageDeflater = this.messageDeflater;
            if (messageDeflater == null) {
                MessageDeflater messageDeflater2 = new MessageDeflater(this.noContextTakeover);
                boolean bl = false;
                boolean bl2 = false;
                MessageDeflater it = messageDeflater2;
                boolean bl3 = false;
                this.messageDeflater = it;
                messageDeflater = messageDeflater2;
            }
            MessageDeflater messageDeflater3 = messageDeflater;
            messageDeflater3.deflate(this.messageBuffer);
            b0 |= 0x40;
        }
        long dataSize = this.messageBuffer.size();
        this.sinkBuffer.writeByte(b0);
        int b1 = 0;
        if (this.isClient) {
            b1 |= 0x80;
        }
        if (dataSize <= 125L) {
            this.sinkBuffer.writeByte(b1 |= (int)dataSize);
        } else if (dataSize <= 65535L) {
            this.sinkBuffer.writeByte(b1 |= 0x7E);
            this.sinkBuffer.writeShort((int)dataSize);
        } else {
            this.sinkBuffer.writeByte(b1 |= 0x7F);
            this.sinkBuffer.writeLong(dataSize);
        }
        if (this.isClient) {
            Intrinsics.checkNotNull(this.maskKey);
            this.random.nextBytes(this.maskKey);
            this.sinkBuffer.write(this.maskKey);
            if (dataSize > 0L) {
                Buffer.UnsafeCursor unsafeCursor = this.maskCursor;
                Intrinsics.checkNotNull(unsafeCursor);
                this.messageBuffer.readAndWriteUnsafe(unsafeCursor);
                this.maskCursor.seek(0L);
                WebSocketProtocol.INSTANCE.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        }
        this.sinkBuffer.write(this.messageBuffer, dataSize);
        this.sink.emit();
    }

    @Override
    public void close() {
        block0: {
            MessageDeflater messageDeflater = this.messageDeflater;
            if (messageDeflater == null) break block0;
            messageDeflater.close();
        }
    }

    @NotNull
    public final BufferedSink getSink() {
        return this.sink;
    }

    @NotNull
    public final Random getRandom() {
        return this.random;
    }

    public WebSocketWriter(boolean isClient, @NotNull BufferedSink sink2, @NotNull Random random, boolean perMessageDeflate, boolean noContextTakeover, long minimumDeflateSize) {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        Intrinsics.checkNotNullParameter(random, "random");
        this.isClient = isClient;
        this.sink = sink2;
        this.random = random;
        this.perMessageDeflate = perMessageDeflate;
        this.noContextTakeover = noContextTakeover;
        this.minimumDeflateSize = minimumDeflateSize;
        this.messageBuffer = new Buffer();
        this.sinkBuffer = this.sink.getBuffer();
        this.maskKey = this.isClient ? new byte[4] : null;
        this.maskCursor = this.isClient ? new Buffer.UnsafeCursor() : null;
    }
}

