/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import okhttp3.internal.Util;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Header;
import okhttp3.internal.http2.Hpack;
import okhttp3.internal.http2.Http2;
import okhttp3.internal.http2.Settings;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 #2\u00020\u0001:\u0003#$%B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0016\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010J(\u0010\u0012\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J(\u0010\u0017\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J.\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J(\u0010\u001c\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J(\u0010\u001d\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J\u0018\u0010\u001e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J(\u0010\u001e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J(\u0010\u001f\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J(\u0010 \u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J(\u0010!\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J(\u0010\"\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2={"Lokhttp3/internal/http2/Http2Reader;", "Ljava/io/Closeable;", "source", "Lokio/BufferedSource;", "client", "", "(Lokio/BufferedSource;Z)V", "continuation", "Lokhttp3/internal/http2/Http2Reader$ContinuationSource;", "hpackReader", "Lokhttp3/internal/http2/Hpack$Reader;", "close", "", "nextFrame", "requireSettings", "handler", "Lokhttp3/internal/http2/Http2Reader$Handler;", "readConnectionPreface", "readData", "length", "", "flags", "streamId", "readGoAway", "readHeaderBlock", "", "Lokhttp3/internal/http2/Header;", "padding", "readHeaders", "readPing", "readPriority", "readPushPromise", "readRstStream", "readSettings", "readWindowUpdate", "Companion", "ContinuationSource", "Handler", "okhttp"})
public final class Http2Reader
implements Closeable {
    private final ContinuationSource continuation;
    private final Hpack.Reader hpackReader;
    private final BufferedSource source;
    private final boolean client;
    @NotNull
    private static final Logger logger;
    public static final Companion Companion;

    public final void readConnectionPreface(@NotNull Handler handler) throws IOException {
        Intrinsics.checkNotNullParameter(handler, "handler");
        if (this.client) {
            if (!this.nextFrame(true, handler)) {
                throw (Throwable)new IOException("Required SETTINGS preface not received");
            }
        } else {
            ByteString connectionPreface = this.source.readByteString(Http2.CONNECTION_PREFACE.size());
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(Util.format("<< CONNECTION " + connectionPreface.hex(), new Object[0]));
            }
            if (Intrinsics.areEqual(Http2.CONNECTION_PREFACE, connectionPreface) ^ true) {
                throw (Throwable)new IOException("Expected a connection header but was " + connectionPreface.utf8());
            }
        }
    }

    public final boolean nextFrame(boolean requireSettings, @NotNull Handler handler) throws IOException {
        Intrinsics.checkNotNullParameter(handler, "handler");
        try {
            this.source.require(9L);
        } catch (EOFException e) {
            return false;
        }
        int length = Util.readMedium(this.source);
        if (length > 16384) {
            throw (Throwable)new IOException("FRAME_SIZE_ERROR: " + length);
        }
        int type = Util.and(this.source.readByte(), 255);
        int flags = Util.and(this.source.readByte(), 255);
        int streamId = this.source.readInt() & Integer.MAX_VALUE;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(Http2.INSTANCE.frameLog(true, streamId, length, type, flags));
        }
        if (requireSettings && type != 4) {
            throw (Throwable)new IOException("Expected a SETTINGS frame but was " + Http2.INSTANCE.formattedType$okhttp(type));
        }
        switch (type) {
            case 0: {
                this.readData(handler, length, flags, streamId);
                break;
            }
            case 1: {
                this.readHeaders(handler, length, flags, streamId);
                break;
            }
            case 2: {
                this.readPriority(handler, length, flags, streamId);
                break;
            }
            case 3: {
                this.readRstStream(handler, length, flags, streamId);
                break;
            }
            case 4: {
                this.readSettings(handler, length, flags, streamId);
                break;
            }
            case 5: {
                this.readPushPromise(handler, length, flags, streamId);
                break;
            }
            case 6: {
                this.readPing(handler, length, flags, streamId);
                break;
            }
            case 7: {
                this.readGoAway(handler, length, flags, streamId);
                break;
            }
            case 8: {
                this.readWindowUpdate(handler, length, flags, streamId);
                break;
            }
            default: {
                this.source.skip(length);
            }
        }
        return true;
    }

    private final void readHeaders(Handler handler, int length, int flags, int streamId) throws IOException {
        if (streamId == 0) {
            throw (Throwable)new IOException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0");
        }
        boolean endStream = (flags & 1) != 0;
        int padding = (flags & 8) != 0 ? Util.and(this.source.readByte(), 255) : 0;
        int headerBlockLength = length;
        if ((flags & 0x20) != 0) {
            this.readPriority(handler, streamId);
            headerBlockLength -= 5;
        }
        headerBlockLength = Companion.lengthWithoutPadding(headerBlockLength, flags, padding);
        List<Header> headerBlock = this.readHeaderBlock(headerBlockLength, padding, flags, streamId);
        handler.headers(endStream, streamId, -1, headerBlock);
    }

    private final List<Header> readHeaderBlock(int length, int padding, int flags, int streamId) throws IOException {
        this.continuation.setLeft(length);
        this.continuation.setLength(this.continuation.getLeft());
        this.continuation.setPadding(padding);
        this.continuation.setFlags(flags);
        this.continuation.setStreamId(streamId);
        this.hpackReader.readHeaders();
        return this.hpackReader.getAndResetHeaderList();
    }

    private final void readData(Handler handler, int length, int flags, int streamId) throws IOException {
        boolean gzipped;
        if (streamId == 0) {
            throw (Throwable)new IOException("PROTOCOL_ERROR: TYPE_DATA streamId == 0");
        }
        boolean inFinished = (flags & 1) != 0;
        boolean bl = gzipped = (flags & 0x20) != 0;
        if (gzipped) {
            throw (Throwable)new IOException("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA");
        }
        int padding = (flags & 8) != 0 ? Util.and(this.source.readByte(), 255) : 0;
        int dataLength = Companion.lengthWithoutPadding(length, flags, padding);
        handler.data(inFinished, streamId, this.source, dataLength);
        this.source.skip(padding);
    }

    private final void readPriority(Handler handler, int length, int flags, int streamId) throws IOException {
        if (length != 5) {
            throw (Throwable)new IOException("TYPE_PRIORITY length: " + length + " != 5");
        }
        if (streamId == 0) {
            throw (Throwable)new IOException("TYPE_PRIORITY streamId == 0");
        }
        this.readPriority(handler, streamId);
    }

    private final void readPriority(Handler handler, int streamId) throws IOException {
        int w1 = this.source.readInt();
        boolean exclusive = (w1 & (int)0x80000000L) != 0;
        int streamDependency = w1 & Integer.MAX_VALUE;
        int weight = Util.and(this.source.readByte(), 255) + 1;
        handler.priority(streamId, streamDependency, weight, exclusive);
    }

    private final void readRstStream(Handler handler, int length, int flags, int streamId) throws IOException {
        if (length != 4) {
            throw (Throwable)new IOException("TYPE_RST_STREAM length: " + length + " != 4");
        }
        if (streamId == 0) {
            throw (Throwable)new IOException("TYPE_RST_STREAM streamId == 0");
        }
        int errorCodeInt = this.source.readInt();
        ErrorCode errorCode = ErrorCode.Companion.fromHttp2(errorCodeInt);
        if (errorCode == null) {
            throw (Throwable)new IOException("TYPE_RST_STREAM unexpected error code: " + errorCodeInt);
        }
        ErrorCode errorCode2 = errorCode;
        handler.rstStream(streamId, errorCode2);
    }

    /*
     * WARNING - void declaration
     */
    private final void readSettings(Handler handler, int length, int flags, int streamId) throws IOException {
        if (streamId != 0) {
            throw (Throwable)new IOException("TYPE_SETTINGS streamId != 0");
        }
        if ((flags & 1) != 0) {
            if (length != 0) {
                throw (Throwable)new IOException("FRAME_SIZE_ERROR ack frame should be empty!");
            }
            handler.ackSettings();
            return;
        }
        if (length % 6 != 0) {
            throw (Throwable)new IOException("TYPE_SETTINGS length % 6 != 0: " + length);
        }
        Settings settings = new Settings();
        IntProgression intProgression = RangesKt.step(RangesKt.until(0, length), 6);
        int n = intProgression.getFirst();
        int n2 = intProgression.getLast();
        int n3 = intProgression.getStep();
        int n4 = n;
        int n5 = n2;
        if (n3 >= 0 ? n4 <= n5 : n4 >= n5) {
            while (true) {
                void i;
                int id = Util.and(this.source.readShort(), 65535);
                int value = this.source.readInt();
                switch (id) {
                    case 1: {
                        break;
                    }
                    case 2: {
                        if (value == 0 || value == 1) break;
                        throw (Throwable)new IOException("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1");
                    }
                    case 3: {
                        id = 4;
                        break;
                    }
                    case 4: {
                        id = 7;
                        if (value >= 0) break;
                        throw (Throwable)new IOException("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1");
                    }
                    case 5: {
                        if (value >= 16384 && value <= 0xFFFFFF) break;
                        throw (Throwable)new IOException("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: " + value);
                    }
                    case 6: {
                        break;
                    }
                }
                settings.set(id, value);
                if (i == n2) break;
                i += n3;
            }
        }
        handler.settings(false, settings);
    }

    private final void readPushPromise(Handler handler, int length, int flags, int streamId) throws IOException {
        if (streamId == 0) {
            throw (Throwable)new IOException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0");
        }
        int padding = (flags & 8) != 0 ? Util.and(this.source.readByte(), 255) : 0;
        int promisedStreamId = this.source.readInt() & Integer.MAX_VALUE;
        int headerBlockLength = Companion.lengthWithoutPadding(length - 4, flags, padding);
        List<Header> headerBlock = this.readHeaderBlock(headerBlockLength, padding, flags, streamId);
        handler.pushPromise(streamId, promisedStreamId, headerBlock);
    }

    private final void readPing(Handler handler, int length, int flags, int streamId) throws IOException {
        if (length != 8) {
            throw (Throwable)new IOException("TYPE_PING length != 8: " + length);
        }
        if (streamId != 0) {
            throw (Throwable)new IOException("TYPE_PING streamId != 0");
        }
        int payload1 = this.source.readInt();
        int payload2 = this.source.readInt();
        boolean ack = (flags & 1) != 0;
        handler.ping(ack, payload1, payload2);
    }

    private final void readGoAway(Handler handler, int length, int flags, int streamId) throws IOException {
        if (length < 8) {
            throw (Throwable)new IOException("TYPE_GOAWAY length < 8: " + length);
        }
        if (streamId != 0) {
            throw (Throwable)new IOException("TYPE_GOAWAY streamId != 0");
        }
        int lastStreamId = this.source.readInt();
        int errorCodeInt = this.source.readInt();
        int opaqueDataLength = length - 8;
        ErrorCode errorCode = ErrorCode.Companion.fromHttp2(errorCodeInt);
        if (errorCode == null) {
            throw (Throwable)new IOException("TYPE_GOAWAY unexpected error code: " + errorCodeInt);
        }
        ErrorCode errorCode2 = errorCode;
        ByteString debugData = ByteString.EMPTY;
        if (opaqueDataLength > 0) {
            debugData = this.source.readByteString(opaqueDataLength);
        }
        handler.goAway(lastStreamId, errorCode2, debugData);
    }

    private final void readWindowUpdate(Handler handler, int length, int flags, int streamId) throws IOException {
        if (length != 4) {
            throw (Throwable)new IOException("TYPE_WINDOW_UPDATE length !=4: " + length);
        }
        long increment = Util.and(this.source.readInt(), Integer.MAX_VALUE);
        if (increment == 0L) {
            throw (Throwable)new IOException("windowSizeIncrement was 0");
        }
        handler.windowUpdate(streamId, increment);
    }

    @Override
    public void close() throws IOException {
        this.source.close();
    }

    public Http2Reader(@NotNull BufferedSource source2, boolean client) {
        Intrinsics.checkNotNullParameter(source2, "source");
        this.source = source2;
        this.client = client;
        this.continuation = new ContinuationSource(this.source);
        this.hpackReader = new Hpack.Reader(this.continuation, 4096, 0, 4, null);
    }

    static {
        Companion = new Companion(null);
        Logger logger = Logger.getLogger(Http2.class.getName());
        Intrinsics.checkNotNullExpressionValue(logger, "Logger.getLogger(Http2::class.java.name)");
        Http2Reader.logger = logger;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001aH\u0016J\b\u0010\u001e\u001a\u00020\u0018H\u0002J\b\u0010\u001f\u001a\u00020 H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001a\u0010\u000e\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR\u001a\u0010\u0011\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\b\"\u0004\b\u0013\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\b\"\u0004\b\u0016\u0010\n\u00a8\u0006!"}, d2={"Lokhttp3/internal/http2/Http2Reader$ContinuationSource;", "Lokio/Source;", "source", "Lokio/BufferedSource;", "(Lokio/BufferedSource;)V", "flags", "", "getFlags", "()I", "setFlags", "(I)V", "left", "getLeft", "setLeft", "length", "getLength", "setLength", "padding", "getPadding", "setPadding", "streamId", "getStreamId", "setStreamId", "close", "", "read", "", "sink", "Lokio/Buffer;", "byteCount", "readContinuationHeader", "timeout", "Lokio/Timeout;", "okhttp"})
    public static final class ContinuationSource
    implements Source {
        private int length;
        private int flags;
        private int streamId;
        private int left;
        private int padding;
        private final BufferedSource source;

        public final int getLength() {
            return this.length;
        }

        public final void setLength(int n) {
            this.length = n;
        }

        public final int getFlags() {
            return this.flags;
        }

        public final void setFlags(int n) {
            this.flags = n;
        }

        public final int getStreamId() {
            return this.streamId;
        }

        public final void setStreamId(int n) {
            this.streamId = n;
        }

        public final int getLeft() {
            return this.left;
        }

        public final void setLeft(int n) {
            this.left = n;
        }

        public final int getPadding() {
            return this.padding;
        }

        public final void setPadding(int n) {
            this.padding = n;
        }

        @Override
        public long read(@NotNull Buffer sink2, long byteCount) throws IOException {
            Intrinsics.checkNotNullParameter(sink2, "sink");
            while (this.left == 0) {
                this.source.skip(this.padding);
                this.padding = 0;
                if ((this.flags & 4) != 0) {
                    return -1L;
                }
                this.readContinuationHeader();
            }
            long l = this.left;
            boolean bl = false;
            long read = this.source.read(sink2, Math.min(byteCount, l));
            if (read == -1L) {
                return -1L;
            }
            this.left -= (int)read;
            return read;
        }

        @Override
        @NotNull
        public Timeout timeout() {
            return this.source.timeout();
        }

        @Override
        public void close() throws IOException {
        }

        private final void readContinuationHeader() throws IOException {
            int previousStreamId = this.streamId;
            this.length = this.left = Util.readMedium(this.source);
            int type = Util.and(this.source.readByte(), 255);
            this.flags = Util.and(this.source.readByte(), 255);
            if (Companion.getLogger().isLoggable(Level.FINE)) {
                Companion.getLogger().fine(Http2.INSTANCE.frameLog(true, this.streamId, this.length, type, this.flags));
            }
            this.streamId = this.source.readInt() & Integer.MAX_VALUE;
            if (type != 9) {
                throw (Throwable)new IOException(type + " != TYPE_CONTINUATION");
            }
            if (this.streamId != previousStreamId) {
                throw (Throwable)new IOException("TYPE_CONTINUATION streamId changed");
            }
        }

        public ContinuationSource(@NotNull BufferedSource source2) {
            Intrinsics.checkNotNullParameter(source2, "source");
            this.source = source2;
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J8\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eH&J(\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0006H&J \u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\nH&J.\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH&J \u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u0006H&J(\u0010#\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u0011H&J&\u0010'\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00062\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH&J\u0018\u0010*\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0018H&J\u0018\u0010+\u001a\u00020\u00032\u0006\u0010,\u001a\u00020\u00112\u0006\u0010+\u001a\u00020-H&J\u0018\u0010.\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u000eH&\u00a8\u00060"}, d2={"Lokhttp3/internal/http2/Http2Reader$Handler;", "", "ackSettings", "", "alternateService", "streamId", "", "origin", "", "protocol", "Lokio/ByteString;", "host", "port", "maxAge", "", "data", "inFinished", "", "source", "Lokio/BufferedSource;", "length", "goAway", "lastGoodStreamId", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "debugData", "headers", "associatedStreamId", "headerBlock", "", "Lokhttp3/internal/http2/Header;", "ping", "ack", "payload1", "payload2", "priority", "streamDependency", "weight", "exclusive", "pushPromise", "promisedStreamId", "requestHeaders", "rstStream", "settings", "clearPrevious", "Lokhttp3/internal/http2/Settings;", "windowUpdate", "windowSizeIncrement", "okhttp"})
    public static interface Handler {
        public void data(boolean var1, int var2, @NotNull BufferedSource var3, int var4) throws IOException;

        public void headers(boolean var1, int var2, int var3, @NotNull List<Header> var4);

        public void rstStream(int var1, @NotNull ErrorCode var2);

        public void settings(boolean var1, @NotNull Settings var2);

        public void ackSettings();

        public void ping(boolean var1, int var2, int var3);

        public void goAway(int var1, @NotNull ErrorCode var2, @NotNull ByteString var3);

        public void windowUpdate(int var1, long var2);

        public void priority(int var1, int var2, int var3, boolean var4);

        public void pushPromise(int var1, int var2, @NotNull List<Header> var3) throws IOException;

        public void alternateService(int var1, @NotNull String var2, @NotNull ByteString var3, @NotNull String var4, int var5, long var6);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\f"}, d2={"Lokhttp3/internal/http2/Http2Reader$Companion;", "", "()V", "logger", "Ljava/util/logging/Logger;", "getLogger", "()Ljava/util/logging/Logger;", "lengthWithoutPadding", "", "length", "flags", "padding", "okhttp"})
    public static final class Companion {
        @NotNull
        public final Logger getLogger() {
            return logger;
        }

        public final int lengthWithoutPadding(int length, int flags, int padding) throws IOException {
            int result = length;
            if ((flags & 8) != 0) {
                --result;
            }
            if (padding > result) {
                throw (Throwable)new IOException("PROTOCOL_ERROR padding " + padding + " > remaining length " + result);
            }
            return result -= padding;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

