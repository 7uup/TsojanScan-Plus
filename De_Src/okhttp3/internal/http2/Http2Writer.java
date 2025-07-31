/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Header;
import okhttp3.internal.http2.Hpack;
import okhttp3.internal.http2.Http2;
import okhttp3.internal.http2.Settings;
import okio.Buffer;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u0000 :2\u00020\u0001:\u0001:B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0014\u001a\u00020\u0011H\u0016J\u0006\u0010\u0015\u001a\u00020\u0011J(\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u000f2\b\u0010\u0019\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\u000fJ(\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u000f2\b\u0010\u001d\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\u000fJ\u0006\u0010\u001e\u001a\u00020\u0011J&\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u000fJ\u001e\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'J$\u0010(\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u000f2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*J\u0006\u0010,\u001a\u00020\u000fJ\u001e\u0010-\u001a\u00020\u00112\u0006\u0010.\u001a\u00020\u00052\u0006\u0010/\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u000fJ$\u00101\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u00102\u001a\u00020\u000f2\f\u00103\u001a\b\u0012\u0004\u0012\u00020+0*J\u0016\u00104\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020%J\u000e\u00105\u001a\u00020\u00112\u0006\u00105\u001a\u00020\u0013J\u0016\u00106\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u00107\u001a\u000208J\u0018\u00109\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u000208H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006;"}, d2={"Lokhttp3/internal/http2/Http2Writer;", "Ljava/io/Closeable;", "sink", "Lokio/BufferedSink;", "client", "", "(Lokio/BufferedSink;Z)V", "closed", "hpackBuffer", "Lokio/Buffer;", "hpackWriter", "Lokhttp3/internal/http2/Hpack$Writer;", "getHpackWriter", "()Lokhttp3/internal/http2/Hpack$Writer;", "maxFrameSize", "", "applyAndAckSettings", "", "peerSettings", "Lokhttp3/internal/http2/Settings;", "close", "connectionPreface", "data", "outFinished", "streamId", "source", "byteCount", "dataFrame", "flags", "buffer", "flush", "frameHeader", "length", "type", "goAway", "lastGoodStreamId", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "debugData", "", "headers", "headerBlock", "", "Lokhttp3/internal/http2/Header;", "maxDataLength", "ping", "ack", "payload1", "payload2", "pushPromise", "promisedStreamId", "requestHeaders", "rstStream", "settings", "windowUpdate", "windowSizeIncrement", "", "writeContinuationFrames", "Companion", "okhttp"})
public final class Http2Writer
implements Closeable {
    private final Buffer hpackBuffer;
    private int maxFrameSize;
    private boolean closed;
    @NotNull
    private final Hpack.Writer hpackWriter;
    private final BufferedSink sink;
    private final boolean client;
    private static final Logger logger;
    public static final Companion Companion;

    @NotNull
    public final Hpack.Writer getHpackWriter() {
        return this.hpackWriter;
    }

    public final synchronized void connectionPreface() throws IOException {
        if (this.closed) {
            throw (Throwable)new IOException("closed");
        }
        if (!this.client) {
            return;
        }
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(Util.format(">> CONNECTION " + Http2.CONNECTION_PREFACE.hex(), new Object[0]));
        }
        this.sink.write(Http2.CONNECTION_PREFACE);
        this.sink.flush();
    }

    public final synchronized void applyAndAckSettings(@NotNull Settings peerSettings) throws IOException {
        Intrinsics.checkNotNullParameter(peerSettings, "peerSettings");
        if (this.closed) {
            throw (Throwable)new IOException("closed");
        }
        this.maxFrameSize = peerSettings.getMaxFrameSize(this.maxFrameSize);
        if (peerSettings.getHeaderTableSize() != -1) {
            this.hpackWriter.resizeHeaderTable(peerSettings.getHeaderTableSize());
        }
        this.frameHeader(0, 0, 4, 1);
        this.sink.flush();
    }

    public final synchronized void pushPromise(int streamId, int promisedStreamId, @NotNull List<Header> requestHeaders) throws IOException {
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        if (this.closed) {
            throw (Throwable)new IOException("closed");
        }
        this.hpackWriter.writeHeaders(requestHeaders);
        long byteCount = this.hpackBuffer.size();
        long l = (long)this.maxFrameSize - 4L;
        boolean bl = false;
        int length = (int)Math.min(l, byteCount);
        this.frameHeader(streamId, length + 4, 5, byteCount == (long)length ? 4 : 0);
        this.sink.writeInt(promisedStreamId & Integer.MAX_VALUE);
        this.sink.write(this.hpackBuffer, (long)length);
        if (byteCount > (long)length) {
            this.writeContinuationFrames(streamId, byteCount - (long)length);
        }
    }

    public final synchronized void flush() throws IOException {
        if (this.closed) {
            throw (Throwable)new IOException("closed");
        }
        this.sink.flush();
    }

    public final synchronized void rstStream(int streamId, @NotNull ErrorCode errorCode) throws IOException {
        Intrinsics.checkNotNullParameter((Object)errorCode, "errorCode");
        if (this.closed) {
            throw (Throwable)new IOException("closed");
        }
        boolean bl = errorCode.getHttpCode() != -1;
        boolean bl2 = false;
        boolean bl3 = false;
        bl3 = false;
        boolean bl4 = false;
        if (!bl) {
            boolean bl5 = false;
            String string = "Failed requirement.";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        this.frameHeader(streamId, 4, 3, 0);
        this.sink.writeInt(errorCode.getHttpCode());
        this.sink.flush();
    }

    public final int maxDataLength() {
        return this.maxFrameSize;
    }

    public final synchronized void data(boolean outFinished, int streamId, @Nullable Buffer source2, int byteCount) throws IOException {
        if (this.closed) {
            throw (Throwable)new IOException("closed");
        }
        int flags = 0;
        if (outFinished) {
            flags |= 1;
        }
        this.dataFrame(streamId, flags, source2, byteCount);
    }

    public final void dataFrame(int streamId, int flags, @Nullable Buffer buffer, int byteCount) throws IOException {
        this.frameHeader(streamId, byteCount, 0, flags);
        if (byteCount > 0) {
            Buffer buffer2 = buffer;
            Intrinsics.checkNotNull(buffer2);
            this.sink.write(buffer2, (long)byteCount);
        }
    }

    /*
     * WARNING - void declaration
     */
    public final synchronized void settings(@NotNull Settings settings) throws IOException {
        Intrinsics.checkNotNullParameter(settings, "settings");
        if (this.closed) {
            throw (Throwable)new IOException("closed");
        }
        this.frameHeader(0, settings.size() * 6, 4, 0);
        int n = 0;
        int n2 = 10;
        while (n < n2) {
            void i;
            if (settings.isSet((int)i)) {
                int n3;
                switch (i) {
                    case 4: {
                        n3 = 3;
                        break;
                    }
                    case 7: {
                        n3 = 4;
                        break;
                    }
                    default: {
                        n3 = i;
                    }
                }
                int id = n3;
                this.sink.writeShort(id);
                this.sink.writeInt(settings.get((int)i));
            }
            ++i;
        }
        this.sink.flush();
    }

    public final synchronized void ping(boolean ack, int payload1, int payload2) throws IOException {
        if (this.closed) {
            throw (Throwable)new IOException("closed");
        }
        this.frameHeader(0, 8, 6, ack ? 1 : 0);
        this.sink.writeInt(payload1);
        this.sink.writeInt(payload2);
        this.sink.flush();
    }

    public final synchronized void goAway(int lastGoodStreamId, @NotNull ErrorCode errorCode, @NotNull byte[] debugData) throws IOException {
        Intrinsics.checkNotNullParameter((Object)errorCode, "errorCode");
        Intrinsics.checkNotNullParameter(debugData, "debugData");
        if (this.closed) {
            throw (Throwable)new IOException("closed");
        }
        boolean bl = errorCode.getHttpCode() != -1;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "errorCode.httpCode == -1";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        this.frameHeader(0, 8 + debugData.length, 7, 0);
        this.sink.writeInt(lastGoodStreamId);
        this.sink.writeInt(errorCode.getHttpCode());
        byte[] byArray = debugData;
        bl2 = false;
        byte[] byArray2 = byArray;
        boolean bl5 = false;
        if (!(byArray2.length == 0)) {
            this.sink.write(debugData);
        }
        this.sink.flush();
    }

    public final synchronized void windowUpdate(int streamId, long windowSizeIncrement) throws IOException {
        if (this.closed) {
            throw (Throwable)new IOException("closed");
        }
        boolean bl = windowSizeIncrement != 0L && windowSizeIncrement <= Integer.MAX_VALUE;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: " + windowSizeIncrement;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        this.frameHeader(streamId, 4, 8, 0);
        this.sink.writeInt((int)windowSizeIncrement);
        this.sink.flush();
    }

    public final void frameHeader(int streamId, int length, int type, int flags) throws IOException {
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(Http2.INSTANCE.frameLog(false, streamId, length, type, flags));
        }
        boolean bl = length <= this.maxFrameSize;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "FRAME_SIZE_ERROR length > " + this.maxFrameSize + ": " + length;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        bl = (streamId & (int)0x80000000L) == 0;
        bl2 = false;
        bl3 = false;
        if (!bl) {
            boolean bl5 = false;
            String string = "reserved bit set: " + streamId;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        Util.writeMedium(this.sink, length);
        this.sink.writeByte(type & 0xFF);
        this.sink.writeByte(flags & 0xFF);
        this.sink.writeInt(streamId & Integer.MAX_VALUE);
    }

    @Override
    public synchronized void close() throws IOException {
        this.closed = true;
        this.sink.close();
    }

    private final void writeContinuationFrames(int streamId, long byteCount) throws IOException {
        long byteCount2 = byteCount;
        while (byteCount2 > 0L) {
            long l = this.maxFrameSize;
            boolean bl = false;
            long length = Math.min(l, byteCount2);
            this.frameHeader(streamId, (int)length, 9, (byteCount2 -= length) == 0L ? 4 : 0);
            this.sink.write(this.hpackBuffer, length);
        }
    }

    public final synchronized void headers(boolean outFinished, int streamId, @NotNull List<Header> headerBlock) throws IOException {
        int flags;
        Intrinsics.checkNotNullParameter(headerBlock, "headerBlock");
        if (this.closed) {
            throw (Throwable)new IOException("closed");
        }
        this.hpackWriter.writeHeaders(headerBlock);
        long byteCount = this.hpackBuffer.size();
        long l = this.maxFrameSize;
        boolean bl = false;
        long length = Math.min(l, byteCount);
        int n = flags = byteCount == length ? 4 : 0;
        if (outFinished) {
            flags |= 1;
        }
        this.frameHeader(streamId, (int)length, 1, flags);
        this.sink.write(this.hpackBuffer, length);
        if (byteCount > length) {
            this.writeContinuationFrames(streamId, byteCount - length);
        }
    }

    public Http2Writer(@NotNull BufferedSink sink2, boolean client) {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        this.sink = sink2;
        this.client = client;
        this.hpackBuffer = new Buffer();
        this.maxFrameSize = 16384;
        this.hpackWriter = new Hpack.Writer(0, false, this.hpackBuffer, 3, null);
    }

    static {
        Companion = new Companion(null);
        logger = Logger.getLogger(Http2.class.getName());
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2={"Lokhttp3/internal/http2/Http2Writer$Companion;", "", "()V", "logger", "Ljava/util/logging/Logger;", "kotlin.jvm.PlatformType", "okhttp"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

