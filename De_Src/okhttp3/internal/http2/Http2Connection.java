/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import okhttp3.Headers;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Header;
import okhttp3.internal.http2.Http2Reader;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.http2.Http2Writer;
import okhttp3.internal.http2.PushObserver;
import okhttp3.internal.http2.Settings;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u00b4\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\u0014\u0018\u0000 \u0099\u00012\u00020\u0001:\b\u0098\u0001\u0099\u0001\u009a\u0001\u009b\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010P\u001a\u00020QJ\b\u0010R\u001a\u00020QH\u0016J'\u0010R\u001a\u00020Q2\u0006\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020T2\b\u0010V\u001a\u0004\u0018\u00010WH\u0000\u00a2\u0006\u0002\bXJ\u0012\u0010Y\u001a\u00020Q2\b\u0010Z\u001a\u0004\u0018\u00010WH\u0002J\u0006\u0010[\u001a\u00020QJ\u0010\u0010\\\u001a\u0004\u0018\u00010B2\u0006\u0010]\u001a\u00020\u0012J\u000e\u0010^\u001a\u00020\t2\u0006\u0010_\u001a\u00020\u0006J&\u0010`\u001a\u00020B2\u0006\u0010a\u001a\u00020\u00122\f\u0010b\u001a\b\u0012\u0004\u0012\u00020d0c2\u0006\u0010e\u001a\u00020\tH\u0002J\u001c\u0010`\u001a\u00020B2\f\u0010b\u001a\b\u0012\u0004\u0012\u00020d0c2\u0006\u0010e\u001a\u00020\tJ\u0006\u0010f\u001a\u00020\u0012J-\u0010g\u001a\u00020Q2\u0006\u0010h\u001a\u00020\u00122\u0006\u0010i\u001a\u00020j2\u0006\u0010k\u001a\u00020\u00122\u0006\u0010l\u001a\u00020\tH\u0000\u00a2\u0006\u0002\bmJ+\u0010n\u001a\u00020Q2\u0006\u0010h\u001a\u00020\u00122\f\u0010b\u001a\b\u0012\u0004\u0012\u00020d0c2\u0006\u0010l\u001a\u00020\tH\u0000\u00a2\u0006\u0002\boJ#\u0010p\u001a\u00020Q2\u0006\u0010h\u001a\u00020\u00122\f\u0010b\u001a\b\u0012\u0004\u0012\u00020d0cH\u0000\u00a2\u0006\u0002\bqJ\u001d\u0010r\u001a\u00020Q2\u0006\u0010h\u001a\u00020\u00122\u0006\u0010s\u001a\u00020TH\u0000\u00a2\u0006\u0002\btJ$\u0010u\u001a\u00020B2\u0006\u0010a\u001a\u00020\u00122\f\u0010b\u001a\b\u0012\u0004\u0012\u00020d0c2\u0006\u0010e\u001a\u00020\tJ\u0015\u0010v\u001a\u00020\t2\u0006\u0010h\u001a\u00020\u0012H\u0000\u00a2\u0006\u0002\bwJ\u0017\u0010x\u001a\u0004\u0018\u00010B2\u0006\u0010h\u001a\u00020\u0012H\u0000\u00a2\u0006\u0002\byJ\r\u0010z\u001a\u00020QH\u0000\u00a2\u0006\u0002\b{J\u000e\u0010|\u001a\u00020Q2\u0006\u0010}\u001a\u00020&J\u000e\u0010~\u001a\u00020Q2\u0006\u0010\u007f\u001a\u00020TJ\u001e\u0010\u0080\u0001\u001a\u00020Q2\t\b\u0002\u0010\u0081\u0001\u001a\u00020\t2\b\b\u0002\u0010E\u001a\u00020FH\u0007J\u0018\u0010\u0082\u0001\u001a\u00020Q2\u0007\u0010\u0083\u0001\u001a\u00020\u0006H\u0000\u00a2\u0006\u0003\b\u0084\u0001J,\u0010\u0085\u0001\u001a\u00020Q2\u0006\u0010h\u001a\u00020\u00122\u0007\u0010\u0086\u0001\u001a\u00020\t2\n\u0010\u0087\u0001\u001a\u0005\u0018\u00010\u0088\u00012\u0006\u0010k\u001a\u00020\u0006J/\u0010\u0089\u0001\u001a\u00020Q2\u0006\u0010h\u001a\u00020\u00122\u0007\u0010\u0086\u0001\u001a\u00020\t2\r\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020d0cH\u0000\u00a2\u0006\u0003\b\u008b\u0001J\u0007\u0010\u008c\u0001\u001a\u00020QJ\"\u0010\u008c\u0001\u001a\u00020Q2\u0007\u0010\u008d\u0001\u001a\u00020\t2\u0007\u0010\u008e\u0001\u001a\u00020\u00122\u0007\u0010\u008f\u0001\u001a\u00020\u0012J\u0007\u0010\u0090\u0001\u001a\u00020QJ\u001f\u0010\u0091\u0001\u001a\u00020Q2\u0006\u0010h\u001a\u00020\u00122\u0006\u0010\u007f\u001a\u00020TH\u0000\u00a2\u0006\u0003\b\u0092\u0001J\u001f\u0010\u0093\u0001\u001a\u00020Q2\u0006\u0010h\u001a\u00020\u00122\u0006\u0010s\u001a\u00020TH\u0000\u00a2\u0006\u0003\b\u0094\u0001J \u0010\u0095\u0001\u001a\u00020Q2\u0006\u0010h\u001a\u00020\u00122\u0007\u0010\u0096\u0001\u001a\u00020\u0006H\u0000\u00a2\u0006\u0003\b\u0097\u0001R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u00020\u0012X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u001fX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020\u0012X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001b\"\u0004\b$\u0010\u001dR\u0011\u0010%\u001a\u00020&\u00a2\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u001a\u0010)\u001a\u00020&X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010(\"\u0004\b+\u0010,R\u000e\u0010-\u001a\u00020.X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u00102\u001a\u00020\u00062\u0006\u00101\u001a\u00020\u0006@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u001e\u00105\u001a\u00020\u00062\u0006\u00101\u001a\u00020\u0006@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u00104R\u0015\u00107\u001a\u000608R\u00020\u0000\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u000e\u0010;\u001a\u000200X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010<\u001a\u00020=X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010?R \u0010@\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020B0AX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bC\u0010DR\u000e\u0010E\u001a\u00020FX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010G\u001a\u00020\u00062\u0006\u00101\u001a\u00020\u0006@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\bH\u00104R\u001e\u0010I\u001a\u00020\u00062\u0006\u00101\u001a\u00020\u0006@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\bJ\u00104R\u0011\u0010K\u001a\u00020L\u00a2\u0006\b\n\u0000\u001a\u0004\bM\u0010NR\u000e\u0010O\u001a\u000200X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u009c\u0001"}, d2={"Lokhttp3/internal/http2/Http2Connection;", "Ljava/io/Closeable;", "builder", "Lokhttp3/internal/http2/Http2Connection$Builder;", "(Lokhttp3/internal/http2/Http2Connection$Builder;)V", "awaitPingsSent", "", "awaitPongsReceived", "client", "", "getClient$okhttp", "()Z", "connectionName", "", "getConnectionName$okhttp", "()Ljava/lang/String;", "currentPushRequests", "", "", "degradedPingsSent", "degradedPongDeadlineNs", "degradedPongsReceived", "intervalPingsSent", "intervalPongsReceived", "isShutdown", "lastGoodStreamId", "getLastGoodStreamId$okhttp", "()I", "setLastGoodStreamId$okhttp", "(I)V", "listener", "Lokhttp3/internal/http2/Http2Connection$Listener;", "getListener$okhttp", "()Lokhttp3/internal/http2/Http2Connection$Listener;", "nextStreamId", "getNextStreamId$okhttp", "setNextStreamId$okhttp", "okHttpSettings", "Lokhttp3/internal/http2/Settings;", "getOkHttpSettings", "()Lokhttp3/internal/http2/Settings;", "peerSettings", "getPeerSettings", "setPeerSettings", "(Lokhttp3/internal/http2/Settings;)V", "pushObserver", "Lokhttp3/internal/http2/PushObserver;", "pushQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "<set-?>", "readBytesAcknowledged", "getReadBytesAcknowledged", "()J", "readBytesTotal", "getReadBytesTotal", "readerRunnable", "Lokhttp3/internal/http2/Http2Connection$ReaderRunnable;", "getReaderRunnable", "()Lokhttp3/internal/http2/Http2Connection$ReaderRunnable;", "settingsListenerQueue", "socket", "Ljava/net/Socket;", "getSocket$okhttp", "()Ljava/net/Socket;", "streams", "", "Lokhttp3/internal/http2/Http2Stream;", "getStreams$okhttp", "()Ljava/util/Map;", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "writeBytesMaximum", "getWriteBytesMaximum", "writeBytesTotal", "getWriteBytesTotal", "writer", "Lokhttp3/internal/http2/Http2Writer;", "getWriter", "()Lokhttp3/internal/http2/Http2Writer;", "writerQueue", "awaitPong", "", "close", "connectionCode", "Lokhttp3/internal/http2/ErrorCode;", "streamCode", "cause", "Ljava/io/IOException;", "close$okhttp", "failConnection", "e", "flush", "getStream", "id", "isHealthy", "nowNs", "newStream", "associatedStreamId", "requestHeaders", "", "Lokhttp3/internal/http2/Header;", "out", "openStreamCount", "pushDataLater", "streamId", "source", "Lokio/BufferedSource;", "byteCount", "inFinished", "pushDataLater$okhttp", "pushHeadersLater", "pushHeadersLater$okhttp", "pushRequestLater", "pushRequestLater$okhttp", "pushResetLater", "errorCode", "pushResetLater$okhttp", "pushStream", "pushedStream", "pushedStream$okhttp", "removeStream", "removeStream$okhttp", "sendDegradedPingLater", "sendDegradedPingLater$okhttp", "setSettings", "settings", "shutdown", "statusCode", "start", "sendConnectionPreface", "updateConnectionFlowControl", "read", "updateConnectionFlowControl$okhttp", "writeData", "outFinished", "buffer", "Lokio/Buffer;", "writeHeaders", "alternating", "writeHeaders$okhttp", "writePing", "reply", "payload1", "payload2", "writePingAndAwaitPong", "writeSynReset", "writeSynReset$okhttp", "writeSynResetLater", "writeSynResetLater$okhttp", "writeWindowUpdateLater", "unacknowledgedBytesRead", "writeWindowUpdateLater$okhttp", "Builder", "Companion", "Listener", "ReaderRunnable", "okhttp"})
public final class Http2Connection
implements Closeable {
    private final boolean client;
    @NotNull
    private final Listener listener;
    @NotNull
    private final Map<Integer, Http2Stream> streams;
    @NotNull
    private final String connectionName;
    private int lastGoodStreamId;
    private int nextStreamId;
    private boolean isShutdown;
    private final TaskRunner taskRunner;
    private final TaskQueue writerQueue;
    private final TaskQueue pushQueue;
    private final TaskQueue settingsListenerQueue;
    private final PushObserver pushObserver;
    private long intervalPingsSent;
    private long intervalPongsReceived;
    private long degradedPingsSent;
    private long degradedPongsReceived;
    private long awaitPingsSent;
    private long awaitPongsReceived;
    private long degradedPongDeadlineNs;
    @NotNull
    private final Settings okHttpSettings;
    @NotNull
    private Settings peerSettings;
    private long readBytesTotal;
    private long readBytesAcknowledged;
    private long writeBytesTotal;
    private long writeBytesMaximum;
    @NotNull
    private final Socket socket;
    @NotNull
    private final Http2Writer writer;
    @NotNull
    private final ReaderRunnable readerRunnable;
    private final Set<Integer> currentPushRequests;
    public static final int OKHTTP_CLIENT_WINDOW_SIZE = 0x1000000;
    @NotNull
    private static final Settings DEFAULT_SETTINGS;
    public static final int INTERVAL_PING = 1;
    public static final int DEGRADED_PING = 2;
    public static final int AWAIT_PING = 3;
    public static final int DEGRADED_PONG_TIMEOUT_NS = 1000000000;
    public static final Companion Companion;

    public final boolean getClient$okhttp() {
        return this.client;
    }

    @NotNull
    public final Listener getListener$okhttp() {
        return this.listener;
    }

    @NotNull
    public final Map<Integer, Http2Stream> getStreams$okhttp() {
        return this.streams;
    }

    @NotNull
    public final String getConnectionName$okhttp() {
        return this.connectionName;
    }

    public final int getLastGoodStreamId$okhttp() {
        return this.lastGoodStreamId;
    }

    public final void setLastGoodStreamId$okhttp(int n) {
        this.lastGoodStreamId = n;
    }

    public final int getNextStreamId$okhttp() {
        return this.nextStreamId;
    }

    public final void setNextStreamId$okhttp(int n) {
        this.nextStreamId = n;
    }

    @NotNull
    public final Settings getOkHttpSettings() {
        return this.okHttpSettings;
    }

    @NotNull
    public final Settings getPeerSettings() {
        return this.peerSettings;
    }

    public final void setPeerSettings(@NotNull Settings settings) {
        Intrinsics.checkNotNullParameter(settings, "<set-?>");
        this.peerSettings = settings;
    }

    public final long getReadBytesTotal() {
        return this.readBytesTotal;
    }

    public final long getReadBytesAcknowledged() {
        return this.readBytesAcknowledged;
    }

    public final long getWriteBytesTotal() {
        return this.writeBytesTotal;
    }

    public final long getWriteBytesMaximum() {
        return this.writeBytesMaximum;
    }

    @NotNull
    public final Socket getSocket$okhttp() {
        return this.socket;
    }

    @NotNull
    public final Http2Writer getWriter() {
        return this.writer;
    }

    @NotNull
    public final ReaderRunnable getReaderRunnable() {
        return this.readerRunnable;
    }

    public final synchronized int openStreamCount() {
        return this.streams.size();
    }

    @Nullable
    public final synchronized Http2Stream getStream(int id) {
        return this.streams.get(id);
    }

    @Nullable
    public final synchronized Http2Stream removeStream$okhttp(int streamId) {
        Http2Stream stream = this.streams.remove(streamId);
        Http2Connection $this$notifyAll$iv = this;
        boolean $i$f$notifyAll = false;
        Http2Connection http2Connection = $this$notifyAll$iv;
        if (http2Connection == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
        }
        ((Object)http2Connection).notifyAll();
        return stream;
    }

    public final synchronized void updateConnectionFlowControl$okhttp(long read) {
        this.readBytesTotal += read;
        long readBytesToAcknowledge = this.readBytesTotal - this.readBytesAcknowledged;
        if (readBytesToAcknowledge >= (long)(this.okHttpSettings.getInitialWindowSize() / 2)) {
            this.writeWindowUpdateLater$okhttp(0, readBytesToAcknowledge);
            this.readBytesAcknowledged += readBytesToAcknowledge;
        }
    }

    @NotNull
    public final Http2Stream pushStream(int associatedStreamId, @NotNull List<Header> requestHeaders, boolean out) throws IOException {
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        boolean bl = !this.client;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "Client cannot push requests.";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        return this.newStream(associatedStreamId, requestHeaders, out);
    }

    @NotNull
    public final Http2Stream newStream(@NotNull List<Header> requestHeaders, boolean out) throws IOException {
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        return this.newStream(0, requestHeaders, out);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final Http2Stream newStream(int associatedStreamId, List<Header> requestHeaders, boolean out) throws IOException {
        boolean outFinished = !out;
        boolean inFinished = false;
        boolean flushHeaders = false;
        Http2Stream stream = null;
        int streamId = 0;
        Http2Writer http2Writer = this.writer;
        boolean bl = false;
        boolean bl2 = false;
        synchronized (http2Writer) {
            boolean bl3 = false;
            Http2Connection http2Connection = this;
            boolean bl4 = false;
            boolean bl5 = false;
            synchronized (http2Connection) {
                boolean bl6 = false;
                if (this.nextStreamId > 0x3FFFFFFF) {
                    this.shutdown(ErrorCode.REFUSED_STREAM);
                }
                if (this.isShutdown) {
                    throw (Throwable)new ConnectionShutdownException();
                }
                streamId = this.nextStreamId;
                this.nextStreamId += 2;
                stream = new Http2Stream(streamId, this, outFinished, inFinished, null);
                boolean bl7 = flushHeaders = !out || this.writeBytesTotal >= this.writeBytesMaximum || stream.getWriteBytesTotal() >= stream.getWriteBytesMaximum();
                if (stream.isOpen()) {
                    this.streams.put(streamId, stream);
                }
                Unit unit = Unit.INSTANCE;
            }
            if (associatedStreamId == 0) {
                this.writer.headers(outFinished, streamId, requestHeaders);
            } else {
                boolean bl8 = !this.client;
                bl4 = false;
                boolean bl9 = false;
                if (!bl8) {
                    boolean bl10 = false;
                    String string = "client streams shouldn't have associated stream IDs";
                    throw (Throwable)new IllegalArgumentException(string.toString());
                }
                this.writer.pushPromise(associatedStreamId, streamId, requestHeaders);
            }
            Unit unit = Unit.INSTANCE;
        }
        if (flushHeaders) {
            this.writer.flush();
        }
        return stream;
    }

    public final void writeHeaders$okhttp(int streamId, boolean outFinished, @NotNull List<Header> alternating) throws IOException {
        Intrinsics.checkNotNullParameter(alternating, "alternating");
        this.writer.headers(outFinished, streamId, alternating);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void writeData(int streamId, boolean outFinished, @Nullable Buffer buffer, long byteCount) throws IOException {
        if (byteCount == 0L) {
            this.writer.data(outFinished, streamId, buffer, 0);
            return;
        }
        long byteCount2 = byteCount;
        while (byteCount2 > 0L) {
            int toWrite = 0;
            Http2Connection http2Connection = this;
            boolean bl = false;
            boolean bl2 = false;
            synchronized (http2Connection) {
                boolean bl3 = false;
                try {
                    while (this.writeBytesTotal >= this.writeBytesMaximum) {
                        if (!this.streams.containsKey(streamId)) {
                            throw (Throwable)new IOException("stream closed");
                        }
                        Http2Connection $this$wait$iv = this;
                        boolean $i$f$wait = false;
                        Http2Connection http2Connection2 = $this$wait$iv;
                        if (http2Connection2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                        }
                        ((Object)http2Connection2).wait();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw (Throwable)new InterruptedIOException();
                }
                long l = byteCount2;
                long l2 = this.writeBytesMaximum - this.writeBytesTotal;
                boolean bl4 = false;
                int n = toWrite = (int)Math.min(l, l2);
                int n2 = this.writer.maxDataLength();
                boolean bl5 = false;
                toWrite = Math.min(n, n2);
                this.writeBytesTotal += (long)toWrite;
                Unit unit = Unit.INSTANCE;
            }
            this.writer.data(outFinished && (byteCount2 -= (long)toWrite) == 0L, streamId, buffer, toWrite);
        }
    }

    /*
     * WARNING - void declaration
     */
    public final void writeSynResetLater$okhttp(int streamId, @NotNull ErrorCode errorCode) {
        void this_$iv;
        Intrinsics.checkNotNullParameter((Object)errorCode, "errorCode");
        TaskQueue taskQueue = this.writerQueue;
        String name$iv = this.connectionName + '[' + streamId + "] writeSynReset";
        long delayNanos$iv = 0L;
        boolean cancelable$iv = true;
        boolean $i$f$execute = false;
        this_$iv.schedule(new Task(name$iv, cancelable$iv, name$iv, cancelable$iv, this, streamId, errorCode){
            final /* synthetic */ String $name;
            final /* synthetic */ boolean $cancelable;
            final /* synthetic */ Http2Connection this$0;
            final /* synthetic */ int $streamId$inlined;
            final /* synthetic */ ErrorCode $errorCode$inlined;
            {
                this.$name = $captured_local_variable$1;
                this.$cancelable = $captured_local_variable$2;
                this.this$0 = http2Connection;
                this.$streamId$inlined = n;
                this.$errorCode$inlined = errorCode;
                super($super_call_param$3, $super_call_param$4);
            }

            public long runOnce() {
                boolean bl = false;
                try {
                    this.this$0.writeSynReset$okhttp(this.$streamId$inlined, this.$errorCode$inlined);
                } catch (IOException e) {
                    Http2Connection.access$failConnection(this.this$0, e);
                }
                return -1L;
            }
        }, delayNanos$iv);
    }

    public final void writeSynReset$okhttp(int streamId, @NotNull ErrorCode statusCode) throws IOException {
        Intrinsics.checkNotNullParameter((Object)statusCode, "statusCode");
        this.writer.rstStream(streamId, statusCode);
    }

    /*
     * WARNING - void declaration
     */
    public final void writeWindowUpdateLater$okhttp(int streamId, long unacknowledgedBytesRead) {
        void this_$iv;
        TaskQueue taskQueue = this.writerQueue;
        String name$iv = this.connectionName + '[' + streamId + "] windowUpdate";
        long delayNanos$iv = 0L;
        boolean cancelable$iv = true;
        boolean $i$f$execute = false;
        this_$iv.schedule(new Task(name$iv, cancelable$iv, name$iv, cancelable$iv, this, streamId, unacknowledgedBytesRead){
            final /* synthetic */ String $name;
            final /* synthetic */ boolean $cancelable;
            final /* synthetic */ Http2Connection this$0;
            final /* synthetic */ int $streamId$inlined;
            final /* synthetic */ long $unacknowledgedBytesRead$inlined;
            {
                this.$name = $captured_local_variable$1;
                this.$cancelable = $captured_local_variable$2;
                this.this$0 = http2Connection;
                this.$streamId$inlined = n;
                this.$unacknowledgedBytesRead$inlined = l;
                super($super_call_param$3, $super_call_param$4);
            }

            public long runOnce() {
                boolean bl = false;
                try {
                    this.this$0.getWriter().windowUpdate(this.$streamId$inlined, this.$unacknowledgedBytesRead$inlined);
                } catch (IOException e) {
                    Http2Connection.access$failConnection(this.this$0, e);
                }
                return -1L;
            }
        }, delayNanos$iv);
    }

    public final void writePing(boolean reply, int payload1, int payload2) {
        try {
            this.writer.ping(reply, payload1, payload2);
        } catch (IOException e) {
            this.failConnection(e);
        }
    }

    public final void writePingAndAwaitPong() throws InterruptedException {
        this.writePing();
        this.awaitPong();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void writePing() throws InterruptedException {
        Http2Connection http2Connection = this;
        boolean bl = false;
        boolean bl2 = false;
        synchronized (http2Connection) {
            boolean bl3 = false;
            long l = this.awaitPingsSent;
            this.awaitPingsSent = l + 1L;
            long l2 = l;
        }
        this.writePing(false, 3, 1330343787);
    }

    public final synchronized void awaitPong() throws InterruptedException {
        while (this.awaitPongsReceived < this.awaitPingsSent) {
            Http2Connection $this$wait$iv = this;
            boolean $i$f$wait = false;
            Http2Connection http2Connection = $this$wait$iv;
            if (http2Connection == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
            }
            ((Object)http2Connection).wait();
        }
    }

    public final void flush() throws IOException {
        this.writer.flush();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void shutdown(@NotNull ErrorCode statusCode) throws IOException {
        Intrinsics.checkNotNullParameter((Object)statusCode, "statusCode");
        Http2Writer http2Writer = this.writer;
        boolean bl = false;
        boolean bl2 = false;
        synchronized (http2Writer) {
            boolean bl3 = false;
            int lastGoodStreamId = 0;
            Http2Connection http2Connection = this;
            boolean bl4 = false;
            boolean bl5 = false;
            synchronized (http2Connection) {
                boolean bl6 = false;
                if (this.isShutdown) {
                    return;
                }
                this.isShutdown = true;
                lastGoodStreamId = this.lastGoodStreamId;
                Unit unit = Unit.INSTANCE;
            }
            this.writer.goAway(lastGoodStreamId, statusCode, Util.EMPTY_BYTE_ARRAY);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override
    public void close() {
        this.close$okhttp(ErrorCode.NO_ERROR, ErrorCode.CANCEL, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void close$okhttp(@NotNull ErrorCode connectionCode, @NotNull ErrorCode streamCode, @Nullable IOException cause) {
        Http2Stream[] http2StreamArray;
        Intrinsics.checkNotNullParameter((Object)connectionCode, "connectionCode");
        Intrinsics.checkNotNullParameter((Object)streamCode, "streamCode");
        Http2Connection $this$assertThreadDoesntHoldLock$iv = this;
        boolean $i$f$assertThreadDoesntHoldLock = false;
        if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv)) {
            StringBuilder stringBuilder = new StringBuilder().append("Thread ");
            Thread thread2 = Thread.currentThread();
            Intrinsics.checkNotNullExpressionValue(thread2, "Thread.currentThread()");
            throw (Throwable)((Object)new AssertionError((Object)stringBuilder.append(thread2.getName()).append(" MUST NOT hold lock on ").append($this$assertThreadDoesntHoldLock$iv).toString()));
        }
        boolean $i$f$ignoreIoExceptions = false;
        try {
            boolean bl = false;
            this.shutdown(connectionCode);
        } catch (IOException iOException) {
        }
        Http2Stream[] streamsToClose = null;
        Http2Connection bl = this;
        boolean bl2 = false;
        boolean bl3 = false;
        synchronized (bl) {
            boolean bl4 = false;
            Map<Integer, Http2Stream> map = this.streams;
            boolean bl5 = false;
            if (!map.isEmpty()) {
                Collection<Http2Stream> $this$toTypedArray$iv = this.streams.values();
                boolean $i$f$toTypedArray = false;
                Collection<Http2Stream> thisCollection$iv = $this$toTypedArray$iv;
                Http2Stream[] http2StreamArray2 = thisCollection$iv.toArray(new Http2Stream[0]);
                if (http2StreamArray2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
                streamsToClose = http2StreamArray2;
                this.streams.clear();
            }
            http2StreamArray = Unit.INSTANCE;
        }
        if (streamsToClose != null) {
            Http2Stream[] $this$forEach$iv = streamsToClose;
            boolean $i$f$forEach = false;
            http2StreamArray = $this$forEach$iv;
            int n = http2StreamArray.length;
            for (int i = 0; i < n; ++i) {
                Http2Stream element$iv;
                Http2Stream stream = element$iv = http2StreamArray[i];
                boolean bl6 = false;
                boolean $i$f$ignoreIoExceptions2 = false;
                try {
                    boolean bl7 = false;
                    stream.close(streamCode, cause);
                    continue;
                } catch (IOException iOException) {
                }
            }
        }
        boolean $i$f$ignoreIoExceptions3 = false;
        try {
            boolean bl8 = false;
            this.writer.close();
        } catch (IOException iOException) {
        }
        $i$f$ignoreIoExceptions3 = false;
        try {
            boolean bl9 = false;
            this.socket.close();
        } catch (IOException iOException) {
        }
        this.writerQueue.shutdown();
        this.pushQueue.shutdown();
        this.settingsListenerQueue.shutdown();
    }

    private final void failConnection(IOException e) {
        this.close$okhttp(ErrorCode.PROTOCOL_ERROR, ErrorCode.PROTOCOL_ERROR, e);
    }

    /*
     * WARNING - void declaration
     */
    @JvmOverloads
    public final void start(boolean sendConnectionPreface, @NotNull TaskRunner taskRunner) throws IOException {
        void name$iv;
        void this_$iv;
        Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        if (sendConnectionPreface) {
            this.writer.connectionPreface();
            this.writer.settings(this.okHttpSettings);
            int windowSize = this.okHttpSettings.getInitialWindowSize();
            if (windowSize != 65535) {
                this.writer.windowUpdate(0, windowSize - 65535);
            }
        }
        TaskQueue windowSize = taskRunner.newQueue();
        String string = this.connectionName;
        Function0 block$iv = this.readerRunnable;
        long delayNanos$iv = 0L;
        boolean cancelable$iv = true;
        boolean $i$f$execute = false;
        this_$iv.schedule(new Task(block$iv, (String)name$iv, cancelable$iv, (String)name$iv, cancelable$iv){
            final /* synthetic */ Function0 $block;
            final /* synthetic */ String $name;
            final /* synthetic */ boolean $cancelable;

            public long runOnce() {
                this.$block.invoke();
                return -1L;
            }
            {
                this.$block = $captured_local_variable$0;
                this.$name = $captured_local_variable$1;
                this.$cancelable = $captured_local_variable$2;
                super($super_call_param$3, $super_call_param$4);
            }
        }, delayNanos$iv);
    }

    public static /* synthetic */ void start$default(Http2Connection http2Connection, boolean bl, TaskRunner taskRunner, int n, Object object) throws IOException {
        if ((n & 1) != 0) {
            bl = true;
        }
        if ((n & 2) != 0) {
            taskRunner = TaskRunner.INSTANCE;
        }
        http2Connection.start(bl, taskRunner);
    }

    @JvmOverloads
    public final void start(boolean sendConnectionPreface) throws IOException {
        Http2Connection.start$default(this, sendConnectionPreface, null, 2, null);
    }

    @JvmOverloads
    public final void start() throws IOException {
        Http2Connection.start$default(this, false, null, 3, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void setSettings(@NotNull Settings settings) throws IOException {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Http2Writer http2Writer = this.writer;
        boolean bl = false;
        boolean bl2 = false;
        synchronized (http2Writer) {
            boolean bl3 = false;
            Http2Connection http2Connection = this;
            boolean bl4 = false;
            boolean bl5 = false;
            synchronized (http2Connection) {
                boolean bl6 = false;
                if (this.isShutdown) {
                    throw (Throwable)new ConnectionShutdownException();
                }
                this.okHttpSettings.merge(settings);
                Unit unit = Unit.INSTANCE;
            }
            this.writer.settings(settings);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final synchronized boolean isHealthy(long nowNs) {
        if (this.isShutdown) {
            return false;
        }
        return this.degradedPongsReceived >= this.degradedPingsSent || nowNs < this.degradedPongDeadlineNs;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * WARNING - void declaration
     */
    public final void sendDegradedPingLater$okhttp() {
        void this_$iv;
        Object object = this;
        boolean bl = false;
        boolean bl2 = false;
        synchronized (object) {
            boolean bl3 = false;
            if (this.degradedPongsReceived < this.degradedPingsSent) {
                return;
            }
            long l = this.degradedPingsSent;
            this.degradedPingsSent = l + 1L;
            this.degradedPongDeadlineNs = System.nanoTime() + (long)1000000000;
            Unit unit = Unit.INSTANCE;
        }
        object = this.writerQueue;
        String name$iv = this.connectionName + " ping";
        long delayNanos$iv = 0L;
        boolean cancelable$iv = true;
        boolean $i$f$execute = false;
        this_$iv.schedule(new Task(name$iv, cancelable$iv, name$iv, cancelable$iv, this){
            final /* synthetic */ String $name;
            final /* synthetic */ boolean $cancelable;
            final /* synthetic */ Http2Connection this$0;
            {
                this.$name = $captured_local_variable$1;
                this.$cancelable = $captured_local_variable$2;
                this.this$0 = http2Connection;
                super($super_call_param$3, $super_call_param$4);
            }

            public long runOnce() {
                boolean bl = false;
                this.this$0.writePing(false, 2, 0);
                return -1L;
            }
        }, delayNanos$iv);
    }

    public final boolean pushedStream$okhttp(int streamId) {
        return streamId != 0 && (streamId & 1) == 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * WARNING - void declaration
     */
    public final void pushRequestLater$okhttp(int streamId, @NotNull List<Header> requestHeaders) {
        void this_$iv;
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        Object object = this;
        boolean bl = false;
        boolean bl2 = false;
        synchronized (object) {
            boolean bl3 = false;
            if (this.currentPushRequests.contains(streamId)) {
                this.writeSynResetLater$okhttp(streamId, ErrorCode.PROTOCOL_ERROR);
                return;
            }
            bl2 = this.currentPushRequests.add(streamId);
        }
        object = this.pushQueue;
        String name$iv = this.connectionName + '[' + streamId + "] onRequest";
        long delayNanos$iv = 0L;
        boolean cancelable$iv = true;
        boolean $i$f$execute = false;
        this_$iv.schedule(new Task(name$iv, cancelable$iv, name$iv, cancelable$iv, this, streamId, requestHeaders){
            final /* synthetic */ String $name;
            final /* synthetic */ boolean $cancelable;
            final /* synthetic */ Http2Connection this$0;
            final /* synthetic */ int $streamId$inlined;
            final /* synthetic */ List $requestHeaders$inlined;
            {
                this.$name = $captured_local_variable$1;
                this.$cancelable = $captured_local_variable$2;
                this.this$0 = http2Connection;
                this.$streamId$inlined = n;
                this.$requestHeaders$inlined = list;
                super($super_call_param$3, $super_call_param$4);
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            public long runOnce() {
                block5: {
                    boolean bl = false;
                    boolean cancel = Http2Connection.access$getPushObserver$p(this.this$0).onRequest(this.$streamId$inlined, this.$requestHeaders$inlined);
                    boolean $i$f$ignoreIoExceptions = false;
                    try {
                        boolean bl2 = false;
                        if (!cancel) break block5;
                        this.this$0.getWriter().rstStream(this.$streamId$inlined, ErrorCode.CANCEL);
                        Http2Connection http2Connection = this.this$0;
                        boolean bl3 = false;
                        boolean bl4 = false;
                        synchronized (http2Connection) {
                            boolean bl5 = false;
                            bl4 = Http2Connection.access$getCurrentPushRequests$p(this.this$0).remove(this.$streamId$inlined);
                        }
                    } catch (IOException iOException) {
                    }
                }
                return -1L;
            }
        }, delayNanos$iv);
    }

    /*
     * WARNING - void declaration
     */
    public final void pushHeadersLater$okhttp(int streamId, @NotNull List<Header> requestHeaders, boolean inFinished) {
        void this_$iv;
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        TaskQueue taskQueue = this.pushQueue;
        String name$iv = this.connectionName + '[' + streamId + "] onHeaders";
        long delayNanos$iv = 0L;
        boolean cancelable$iv = true;
        boolean $i$f$execute = false;
        this_$iv.schedule(new Task(name$iv, cancelable$iv, name$iv, cancelable$iv, this, streamId, requestHeaders, inFinished){
            final /* synthetic */ String $name;
            final /* synthetic */ boolean $cancelable;
            final /* synthetic */ Http2Connection this$0;
            final /* synthetic */ int $streamId$inlined;
            final /* synthetic */ List $requestHeaders$inlined;
            final /* synthetic */ boolean $inFinished$inlined;
            {
                this.$name = $captured_local_variable$1;
                this.$cancelable = $captured_local_variable$2;
                this.this$0 = http2Connection;
                this.$streamId$inlined = n;
                this.$requestHeaders$inlined = list;
                this.$inFinished$inlined = bl;
                super($super_call_param$3, $super_call_param$4);
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            public long runOnce() {
                block6: {
                    boolean bl = false;
                    boolean cancel = Http2Connection.access$getPushObserver$p(this.this$0).onHeaders(this.$streamId$inlined, this.$requestHeaders$inlined, this.$inFinished$inlined);
                    boolean $i$f$ignoreIoExceptions = false;
                    try {
                        boolean bl2 = false;
                        if (cancel) {
                            this.this$0.getWriter().rstStream(this.$streamId$inlined, ErrorCode.CANCEL);
                        }
                        if (!cancel && !this.$inFinished$inlined) break block6;
                        Http2Connection http2Connection = this.this$0;
                        boolean bl3 = false;
                        boolean bl4 = false;
                        synchronized (http2Connection) {
                            boolean bl5 = false;
                            bl4 = Http2Connection.access$getCurrentPushRequests$p(this.this$0).remove(this.$streamId$inlined);
                        }
                    } catch (IOException iOException) {
                    }
                }
                return -1L;
            }
        }, delayNanos$iv);
    }

    /*
     * WARNING - void declaration
     */
    public final void pushDataLater$okhttp(int streamId, @NotNull BufferedSource source2, int byteCount, boolean inFinished) throws IOException {
        void this_$iv;
        Intrinsics.checkNotNullParameter(source2, "source");
        Buffer buffer = new Buffer();
        source2.require(byteCount);
        source2.read(buffer, byteCount);
        TaskQueue taskQueue = this.pushQueue;
        String name$iv = this.connectionName + '[' + streamId + "] onData";
        long delayNanos$iv = 0L;
        boolean cancelable$iv = true;
        boolean $i$f$execute = false;
        this_$iv.schedule(new Task(name$iv, cancelable$iv, name$iv, cancelable$iv, this, streamId, buffer, byteCount, inFinished){
            final /* synthetic */ String $name;
            final /* synthetic */ boolean $cancelable;
            final /* synthetic */ Http2Connection this$0;
            final /* synthetic */ int $streamId$inlined;
            final /* synthetic */ Buffer $buffer$inlined;
            final /* synthetic */ int $byteCount$inlined;
            final /* synthetic */ boolean $inFinished$inlined;
            {
                this.$name = $captured_local_variable$1;
                this.$cancelable = $captured_local_variable$2;
                this.this$0 = http2Connection;
                this.$streamId$inlined = n;
                this.$buffer$inlined = buffer;
                this.$byteCount$inlined = n2;
                this.$inFinished$inlined = bl;
                super($super_call_param$3, $super_call_param$4);
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            public long runOnce() {
                block6: {
                    boolean bl = false;
                    boolean $i$f$ignoreIoExceptions = false;
                    try {
                        boolean bl2 = false;
                        boolean cancel = Http2Connection.access$getPushObserver$p(this.this$0).onData(this.$streamId$inlined, this.$buffer$inlined, this.$byteCount$inlined, this.$inFinished$inlined);
                        if (cancel) {
                            this.this$0.getWriter().rstStream(this.$streamId$inlined, ErrorCode.CANCEL);
                        }
                        if (!cancel && !this.$inFinished$inlined) break block6;
                        Http2Connection http2Connection = this.this$0;
                        boolean bl3 = false;
                        boolean bl4 = false;
                        synchronized (http2Connection) {
                            boolean bl5 = false;
                            bl4 = Http2Connection.access$getCurrentPushRequests$p(this.this$0).remove(this.$streamId$inlined);
                        }
                    } catch (IOException iOException) {
                    }
                }
                return -1L;
            }
        }, delayNanos$iv);
    }

    /*
     * WARNING - void declaration
     */
    public final void pushResetLater$okhttp(int streamId, @NotNull ErrorCode errorCode) {
        void this_$iv;
        Intrinsics.checkNotNullParameter((Object)errorCode, "errorCode");
        TaskQueue taskQueue = this.pushQueue;
        String name$iv = this.connectionName + '[' + streamId + "] onReset";
        long delayNanos$iv = 0L;
        boolean cancelable$iv = true;
        boolean $i$f$execute = false;
        this_$iv.schedule(new Task(name$iv, cancelable$iv, name$iv, cancelable$iv, this, streamId, errorCode){
            final /* synthetic */ String $name;
            final /* synthetic */ boolean $cancelable;
            final /* synthetic */ Http2Connection this$0;
            final /* synthetic */ int $streamId$inlined;
            final /* synthetic */ ErrorCode $errorCode$inlined;
            {
                this.$name = $captured_local_variable$1;
                this.$cancelable = $captured_local_variable$2;
                this.this$0 = http2Connection;
                this.$streamId$inlined = n;
                this.$errorCode$inlined = errorCode;
                super($super_call_param$3, $super_call_param$4);
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            public long runOnce() {
                boolean bl = false;
                Http2Connection.access$getPushObserver$p(this.this$0).onReset(this.$streamId$inlined, this.$errorCode$inlined);
                Http2Connection http2Connection = this.this$0;
                boolean bl2 = false;
                boolean bl3 = false;
                synchronized (http2Connection) {
                    boolean bl4 = false;
                    Http2Connection.access$getCurrentPushRequests$p(this.this$0).remove(this.$streamId$inlined);
                    Unit unit = Unit.INSTANCE;
                }
                return -1L;
            }
        }, delayNanos$iv);
    }

    /*
     * WARNING - void declaration
     */
    public Http2Connection(@NotNull Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.client = builder.getClient$okhttp();
        this.listener = builder.getListener$okhttp();
        boolean bl = false;
        this.streams = new LinkedHashMap();
        this.connectionName = builder.getConnectionName$okhttp();
        this.nextStreamId = builder.getClient$okhttp() ? 3 : 2;
        this.taskRunner = builder.getTaskRunner$okhttp();
        this.writerQueue = this.taskRunner.newQueue();
        this.pushQueue = this.taskRunner.newQueue();
        this.settingsListenerQueue = this.taskRunner.newQueue();
        this.pushObserver = builder.getPushObserver$okhttp();
        Settings settings = new Settings();
        boolean bl2 = false;
        boolean bl3 = false;
        Settings settings2 = settings;
        Http2Connection http2Connection = this;
        boolean bl4 = false;
        if (builder.getClient$okhttp()) {
            void $this$apply;
            $this$apply.set(7, 0x1000000);
        }
        Unit unit = Unit.INSTANCE;
        http2Connection.okHttpSettings = settings;
        this.peerSettings = DEFAULT_SETTINGS;
        this.writeBytesMaximum = this.peerSettings.getInitialWindowSize();
        this.socket = builder.getSocket$okhttp();
        this.writer = new Http2Writer(builder.getSink$okhttp(), this.client);
        this.readerRunnable = new ReaderRunnable(new Http2Reader(builder.getSource$okhttp(), this.client));
        boolean bl5 = false;
        this.currentPushRequests = new LinkedHashSet();
        if (builder.getPingIntervalMillis$okhttp() != 0) {
            void this_$iv;
            long pingIntervalNanos = TimeUnit.MILLISECONDS.toNanos(builder.getPingIntervalMillis$okhttp());
            TaskQueue taskQueue = this.writerQueue;
            String name$iv = this.connectionName + " ping";
            boolean $i$f$schedule = false;
            this_$iv.schedule(new Task(name$iv, name$iv, this, pingIntervalNanos){
                final /* synthetic */ String $name;
                final /* synthetic */ Http2Connection this$0;
                final /* synthetic */ long $pingIntervalNanos$inlined;
                {
                    this.$name = $captured_local_variable$1;
                    this.this$0 = http2Connection;
                    this.$pingIntervalNanos$inlined = l;
                    super($super_call_param$2, false, 2, null);
                }

                /*
                 * WARNING - Removed try catching itself - possible behaviour change.
                 */
                public long runOnce() {
                    long l;
                    boolean bl = false;
                    Http2Connection http2Connection = this.this$0;
                    boolean bl2 = false;
                    boolean bl3 = false;
                    synchronized (http2Connection) {
                        boolean bl4;
                        boolean bl5 = false;
                        if (Http2Connection.access$getIntervalPongsReceived$p(this.this$0) < Http2Connection.access$getIntervalPingsSent$p(this.this$0)) {
                            bl4 = true;
                        } else {
                            Http2Connection http2Connection2 = this.this$0;
                            long l2 = Http2Connection.access$getIntervalPingsSent$p(http2Connection2);
                            Http2Connection.access$setIntervalPingsSent$p(http2Connection2, l2 + 1L);
                            bl4 = false;
                        }
                        bl3 = bl4;
                    }
                    boolean failDueToMissingPong = bl3;
                    if (failDueToMissingPong) {
                        Http2Connection.access$failConnection(this.this$0, null);
                        l = -1L;
                    } else {
                        this.this$0.writePing(false, 1, 0);
                        l = this.$pingIntervalNanos$inlined;
                    }
                    return l;
                }
            }, pingIntervalNanos);
        }
    }

    static {
        Companion = new Companion(null);
        Settings settings = new Settings();
        boolean bl = false;
        boolean bl2 = false;
        Settings $this$apply = settings;
        boolean bl3 = false;
        $this$apply.set(7, 65535);
        $this$apply.set(5, 16384);
        DEFAULT_SETTINGS = settings;
    }

    public static final /* synthetic */ long access$getWriteBytesMaximum$p(Http2Connection $this) {
        return $this.writeBytesMaximum;
    }

    public static final /* synthetic */ PushObserver access$getPushObserver$p(Http2Connection $this) {
        return $this.pushObserver;
    }

    public static final /* synthetic */ Set access$getCurrentPushRequests$p(Http2Connection $this) {
        return $this.currentPushRequests;
    }

    public static final /* synthetic */ long access$getIntervalPingsSent$p(Http2Connection $this) {
        return $this.intervalPingsSent;
    }

    public static final /* synthetic */ void access$setIntervalPingsSent$p(Http2Connection $this, long l) {
        $this.intervalPingsSent = l;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u00107\u001a\u000208J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001eJ.\u0010)\u001a\u00020\u00002\u0006\u0010)\u001a\u00020*2\b\b\u0002\u00109\u001a\u00020\f2\b\b\u0002\u0010/\u001a\u0002002\b\b\u0002\u0010#\u001a\u00020$H\u0007R\u001a\u0010\u0002\u001a\u00020\u0003X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0080.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u001eX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020$X\u0080.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010)\u001a\u00020*X\u0080.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001a\u0010/\u001a\u000200X\u0080.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u00106\u00a8\u0006:"}, d2={"Lokhttp3/internal/http2/Http2Connection$Builder;", "", "client", "", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "(ZLokhttp3/internal/concurrent/TaskRunner;)V", "getClient$okhttp", "()Z", "setClient$okhttp", "(Z)V", "connectionName", "", "getConnectionName$okhttp", "()Ljava/lang/String;", "setConnectionName$okhttp", "(Ljava/lang/String;)V", "listener", "Lokhttp3/internal/http2/Http2Connection$Listener;", "getListener$okhttp", "()Lokhttp3/internal/http2/Http2Connection$Listener;", "setListener$okhttp", "(Lokhttp3/internal/http2/Http2Connection$Listener;)V", "pingIntervalMillis", "", "getPingIntervalMillis$okhttp", "()I", "setPingIntervalMillis$okhttp", "(I)V", "pushObserver", "Lokhttp3/internal/http2/PushObserver;", "getPushObserver$okhttp", "()Lokhttp3/internal/http2/PushObserver;", "setPushObserver$okhttp", "(Lokhttp3/internal/http2/PushObserver;)V", "sink", "Lokio/BufferedSink;", "getSink$okhttp", "()Lokio/BufferedSink;", "setSink$okhttp", "(Lokio/BufferedSink;)V", "socket", "Ljava/net/Socket;", "getSocket$okhttp", "()Ljava/net/Socket;", "setSocket$okhttp", "(Ljava/net/Socket;)V", "source", "Lokio/BufferedSource;", "getSource$okhttp", "()Lokio/BufferedSource;", "setSource$okhttp", "(Lokio/BufferedSource;)V", "getTaskRunner$okhttp", "()Lokhttp3/internal/concurrent/TaskRunner;", "build", "Lokhttp3/internal/http2/Http2Connection;", "peerName", "okhttp"})
    public static final class Builder {
        @NotNull
        public Socket socket;
        @NotNull
        public String connectionName;
        @NotNull
        public BufferedSource source;
        @NotNull
        public BufferedSink sink;
        @NotNull
        private Listener listener;
        @NotNull
        private PushObserver pushObserver;
        private int pingIntervalMillis;
        private boolean client;
        @NotNull
        private final TaskRunner taskRunner;

        @NotNull
        public final Socket getSocket$okhttp() {
            Socket socket = this.socket;
            if (socket == null) {
                Intrinsics.throwUninitializedPropertyAccessException("socket");
            }
            return socket;
        }

        public final void setSocket$okhttp(@NotNull Socket socket) {
            Intrinsics.checkNotNullParameter(socket, "<set-?>");
            this.socket = socket;
        }

        @NotNull
        public final String getConnectionName$okhttp() {
            String string = this.connectionName;
            if (string == null) {
                Intrinsics.throwUninitializedPropertyAccessException("connectionName");
            }
            return string;
        }

        public final void setConnectionName$okhttp(@NotNull String string) {
            Intrinsics.checkNotNullParameter(string, "<set-?>");
            this.connectionName = string;
        }

        @NotNull
        public final BufferedSource getSource$okhttp() {
            BufferedSource bufferedSource = this.source;
            if (bufferedSource == null) {
                Intrinsics.throwUninitializedPropertyAccessException("source");
            }
            return bufferedSource;
        }

        public final void setSource$okhttp(@NotNull BufferedSource bufferedSource) {
            Intrinsics.checkNotNullParameter(bufferedSource, "<set-?>");
            this.source = bufferedSource;
        }

        @NotNull
        public final BufferedSink getSink$okhttp() {
            BufferedSink bufferedSink = this.sink;
            if (bufferedSink == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sink");
            }
            return bufferedSink;
        }

        public final void setSink$okhttp(@NotNull BufferedSink bufferedSink) {
            Intrinsics.checkNotNullParameter(bufferedSink, "<set-?>");
            this.sink = bufferedSink;
        }

        @NotNull
        public final Listener getListener$okhttp() {
            return this.listener;
        }

        public final void setListener$okhttp(@NotNull Listener listener) {
            Intrinsics.checkNotNullParameter(listener, "<set-?>");
            this.listener = listener;
        }

        @NotNull
        public final PushObserver getPushObserver$okhttp() {
            return this.pushObserver;
        }

        public final void setPushObserver$okhttp(@NotNull PushObserver pushObserver) {
            Intrinsics.checkNotNullParameter(pushObserver, "<set-?>");
            this.pushObserver = pushObserver;
        }

        public final int getPingIntervalMillis$okhttp() {
            return this.pingIntervalMillis;
        }

        public final void setPingIntervalMillis$okhttp(int n) {
            this.pingIntervalMillis = n;
        }

        @JvmOverloads
        @NotNull
        public final Builder socket(@NotNull Socket socket, @NotNull String peerName, @NotNull BufferedSource source2, @NotNull BufferedSink sink2) throws IOException {
            Intrinsics.checkNotNullParameter(socket, "socket");
            Intrinsics.checkNotNullParameter(peerName, "peerName");
            Intrinsics.checkNotNullParameter(source2, "source");
            Intrinsics.checkNotNullParameter(sink2, "sink");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.socket = socket;
            $this$apply.connectionName = $this$apply.client ? Util.okHttpName + ' ' + peerName : "MockWebServer " + peerName;
            $this$apply.source = source2;
            $this$apply.sink = sink2;
            return builder;
        }

        public static /* synthetic */ Builder socket$default(Builder builder, Socket socket, String string, BufferedSource bufferedSource, BufferedSink bufferedSink, int n, Object object) throws IOException {
            if ((n & 2) != 0) {
                string = Util.peerName(socket);
            }
            if ((n & 4) != 0) {
                bufferedSource = Okio.buffer(Okio.source(socket));
            }
            if ((n & 8) != 0) {
                bufferedSink = Okio.buffer(Okio.sink(socket));
            }
            return builder.socket(socket, string, bufferedSource, bufferedSink);
        }

        @JvmOverloads
        @NotNull
        public final Builder socket(@NotNull Socket socket, @NotNull String peerName, @NotNull BufferedSource source2) throws IOException {
            return Builder.socket$default(this, socket, peerName, source2, null, 8, null);
        }

        @JvmOverloads
        @NotNull
        public final Builder socket(@NotNull Socket socket, @NotNull String peerName) throws IOException {
            return Builder.socket$default(this, socket, peerName, null, null, 12, null);
        }

        @JvmOverloads
        @NotNull
        public final Builder socket(@NotNull Socket socket) throws IOException {
            return Builder.socket$default(this, socket, null, null, null, 14, null);
        }

        @NotNull
        public final Builder listener(@NotNull Listener listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.listener = listener;
            return builder;
        }

        @NotNull
        public final Builder pushObserver(@NotNull PushObserver pushObserver) {
            Intrinsics.checkNotNullParameter(pushObserver, "pushObserver");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.pushObserver = pushObserver;
            return builder;
        }

        @NotNull
        public final Builder pingIntervalMillis(int pingIntervalMillis) {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.pingIntervalMillis = pingIntervalMillis;
            return builder;
        }

        @NotNull
        public final Http2Connection build() {
            return new Http2Connection(this);
        }

        public final boolean getClient$okhttp() {
            return this.client;
        }

        public final void setClient$okhttp(boolean bl) {
            this.client = bl;
        }

        @NotNull
        public final TaskRunner getTaskRunner$okhttp() {
            return this.taskRunner;
        }

        public Builder(boolean client, @NotNull TaskRunner taskRunner) {
            Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
            this.client = client;
            this.taskRunner = taskRunner;
            this.listener = Listener.REFUSE_INCOMING_STREAMS;
            this.pushObserver = PushObserver.CANCEL;
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0086\u0004\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u000f\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\u0003H\u0016J8\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0016\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J(\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\fH\u0016J \u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0010H\u0016J.\u0010$\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010%\u001a\u00020\f2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'H\u0016J\t\u0010)\u001a\u00020\u0003H\u0096\u0002J \u0010*\u001a\u00020\u00032\u0006\u0010+\u001a\u00020\u00172\u0006\u0010,\u001a\u00020\f2\u0006\u0010-\u001a\u00020\fH\u0016J(\u0010.\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010/\u001a\u00020\f2\u0006\u00100\u001a\u00020\f2\u0006\u00101\u001a\u00020\u0017H\u0016J&\u00102\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u00103\u001a\u00020\f2\f\u00104\u001a\b\u0012\u0004\u0012\u00020(0'H\u0016J\u0018\u00105\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010!\u001a\u00020\"H\u0016J\u0018\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0018\u00106\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u00107\u001a\u00020\u0014H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u00068"}, d2={"Lokhttp3/internal/http2/Http2Connection$ReaderRunnable;", "Lokhttp3/internal/http2/Http2Reader$Handler;", "Lkotlin/Function0;", "", "reader", "Lokhttp3/internal/http2/Http2Reader;", "(Lokhttp3/internal/http2/Http2Connection;Lokhttp3/internal/http2/Http2Reader;)V", "getReader$okhttp", "()Lokhttp3/internal/http2/Http2Reader;", "ackSettings", "alternateService", "streamId", "", "origin", "", "protocol", "Lokio/ByteString;", "host", "port", "maxAge", "", "applyAndAckSettings", "clearPrevious", "", "settings", "Lokhttp3/internal/http2/Settings;", "data", "inFinished", "source", "Lokio/BufferedSource;", "length", "goAway", "lastGoodStreamId", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "debugData", "headers", "associatedStreamId", "headerBlock", "", "Lokhttp3/internal/http2/Header;", "invoke", "ping", "ack", "payload1", "payload2", "priority", "streamDependency", "weight", "exclusive", "pushPromise", "promisedStreamId", "requestHeaders", "rstStream", "windowUpdate", "windowSizeIncrement", "okhttp"})
    public final class ReaderRunnable
    implements Http2Reader.Handler,
    Function0<Unit> {
        @NotNull
        private final Http2Reader reader;

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void invoke() {
            ErrorCode connectionErrorCode = ErrorCode.INTERNAL_ERROR;
            ErrorCode streamErrorCode = ErrorCode.INTERNAL_ERROR;
            IOException errorException = null;
            try {
                this.reader.readConnectionPreface(this);
                while (this.reader.nextFrame(false, this)) {
                }
                connectionErrorCode = ErrorCode.NO_ERROR;
                streamErrorCode = ErrorCode.CANCEL;
            } catch (IOException e) {
                errorException = e;
                connectionErrorCode = ErrorCode.PROTOCOL_ERROR;
                streamErrorCode = ErrorCode.PROTOCOL_ERROR;
            } finally {
                Http2Connection.this.close$okhttp(connectionErrorCode, streamErrorCode, errorException);
                Util.closeQuietly(this.reader);
            }
        }

        @Override
        public void data(boolean inFinished, int streamId, @NotNull BufferedSource source2, int length) throws IOException {
            Intrinsics.checkNotNullParameter(source2, "source");
            if (Http2Connection.this.pushedStream$okhttp(streamId)) {
                Http2Connection.this.pushDataLater$okhttp(streamId, source2, length, inFinished);
                return;
            }
            Http2Stream dataStream = Http2Connection.this.getStream(streamId);
            if (dataStream == null) {
                Http2Connection.this.writeSynResetLater$okhttp(streamId, ErrorCode.PROTOCOL_ERROR);
                Http2Connection.this.updateConnectionFlowControl$okhttp(length);
                source2.skip(length);
                return;
            }
            dataStream.receiveData(source2, length);
            if (inFinished) {
                dataStream.receiveHeaders(Util.EMPTY_HEADERS, true);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         * WARNING - void declaration
         */
        @Override
        public void headers(boolean inFinished, int streamId, int associatedStreamId, @NotNull List<Header> headerBlock) {
            Intrinsics.checkNotNullParameter(headerBlock, "headerBlock");
            if (Http2Connection.this.pushedStream$okhttp(streamId)) {
                Http2Connection.this.pushHeadersLater$okhttp(streamId, headerBlock, inFinished);
                return;
            }
            Http2Stream stream = null;
            Http2Connection http2Connection = Http2Connection.this;
            boolean bl = false;
            boolean bl2 = false;
            synchronized (http2Connection) {
                boolean bl3 = false;
                stream = Http2Connection.this.getStream(streamId);
                if (stream == null) {
                    void this_$iv;
                    if (Http2Connection.this.isShutdown) {
                        return;
                    }
                    if (streamId <= Http2Connection.this.getLastGoodStreamId$okhttp()) {
                        return;
                    }
                    if (streamId % 2 == Http2Connection.this.getNextStreamId$okhttp() % 2) {
                        return;
                    }
                    Headers headers = Util.toHeaders(headerBlock);
                    Http2Stream newStream = new Http2Stream(streamId, Http2Connection.this, false, inFinished, headers);
                    Http2Connection.this.setLastGoodStreamId$okhttp(streamId);
                    Http2Connection.this.getStreams$okhttp().put(streamId, newStream);
                    TaskQueue taskQueue = Http2Connection.this.taskRunner.newQueue();
                    String name$iv = Http2Connection.this.getConnectionName$okhttp() + '[' + streamId + "] onStream";
                    long delayNanos$iv = 0L;
                    boolean cancelable$iv = true;
                    boolean $i$f$execute = false;
                    this_$iv.schedule(new Task(name$iv, cancelable$iv, name$iv, cancelable$iv, newStream, this, stream, streamId, headerBlock, inFinished){
                        final /* synthetic */ String $name;
                        final /* synthetic */ boolean $cancelable;
                        final /* synthetic */ Http2Stream $newStream$inlined;
                        final /* synthetic */ ReaderRunnable this$0;
                        final /* synthetic */ Http2Stream $stream$inlined;
                        final /* synthetic */ int $streamId$inlined;
                        final /* synthetic */ List $headerBlock$inlined;
                        final /* synthetic */ boolean $inFinished$inlined;
                        {
                            this.$name = $captured_local_variable$1;
                            this.$cancelable = $captured_local_variable$2;
                            this.$newStream$inlined = http2Stream;
                            this.this$0 = readerRunnable;
                            this.$stream$inlined = http2Stream2;
                            this.$streamId$inlined = n;
                            this.$headerBlock$inlined = list;
                            this.$inFinished$inlined = bl;
                            super($super_call_param$3, $super_call_param$4);
                        }

                        public long runOnce() {
                            boolean bl = false;
                            try {
                                this.this$0.Http2Connection.this.getListener$okhttp().onStream(this.$newStream$inlined);
                            } catch (IOException e) {
                                Platform.Companion.get().log("Http2Connection.Listener failure for " + this.this$0.Http2Connection.this.getConnectionName$okhttp(), 4, e);
                                boolean $i$f$ignoreIoExceptions = false;
                                try {
                                    boolean bl2 = false;
                                    this.$newStream$inlined.close(ErrorCode.PROTOCOL_ERROR, e);
                                } catch (IOException iOException) {
                                }
                            }
                            return -1L;
                        }
                    }, delayNanos$iv);
                    return;
                }
                Unit unit = Unit.INSTANCE;
            }
            stream.receiveHeaders(Util.toHeaders(headerBlock), inFinished);
        }

        @Override
        public void rstStream(int streamId, @NotNull ErrorCode errorCode) {
            block1: {
                Http2Stream rstStream;
                Intrinsics.checkNotNullParameter((Object)errorCode, "errorCode");
                if (Http2Connection.this.pushedStream$okhttp(streamId)) {
                    Http2Connection.this.pushResetLater$okhttp(streamId, errorCode);
                    return;
                }
                Http2Stream http2Stream = rstStream = Http2Connection.this.removeStream$okhttp(streamId);
                if (http2Stream == null) break block1;
                http2Stream.receiveRstStream(errorCode);
            }
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public void settings(boolean clearPrevious, @NotNull Settings settings) {
            void this_$iv;
            Intrinsics.checkNotNullParameter(settings, "settings");
            TaskQueue taskQueue = Http2Connection.this.writerQueue;
            String name$iv = Http2Connection.this.getConnectionName$okhttp() + " applyAndAckSettings";
            long delayNanos$iv = 0L;
            boolean cancelable$iv = true;
            boolean $i$f$execute = false;
            this_$iv.schedule(new Task(name$iv, cancelable$iv, name$iv, cancelable$iv, this, clearPrevious, settings){
                final /* synthetic */ String $name;
                final /* synthetic */ boolean $cancelable;
                final /* synthetic */ ReaderRunnable this$0;
                final /* synthetic */ boolean $clearPrevious$inlined;
                final /* synthetic */ Settings $settings$inlined;
                {
                    this.$name = $captured_local_variable$1;
                    this.$cancelable = $captured_local_variable$2;
                    this.this$0 = readerRunnable;
                    this.$clearPrevious$inlined = bl;
                    this.$settings$inlined = settings;
                    super($super_call_param$3, $super_call_param$4);
                }

                public long runOnce() {
                    boolean bl = false;
                    this.this$0.applyAndAckSettings(this.$clearPrevious$inlined, this.$settings$inlined);
                    return -1L;
                }
            }, delayNanos$iv);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         * WARNING - void declaration
         */
        public final void applyAndAckSettings(boolean clearPrevious, @NotNull Settings settings) {
            Intrinsics.checkNotNullParameter(settings, "settings");
            Ref.LongRef delta = new Ref.LongRef();
            Ref.ObjectRef streamsToNotify = new Ref.ObjectRef();
            Ref.ObjectRef newPeerSettings = new Ref.ObjectRef();
            Http2Writer http2Writer = Http2Connection.this.getWriter();
            int n = 0;
            boolean bl = false;
            synchronized (http2Writer) {
                boolean bl2 = false;
                Http2Connection http2Connection = Http2Connection.this;
                boolean bl3 = false;
                boolean bl4 = false;
                synchronized (http2Connection) {
                    void this_$iv;
                    Http2Stream[] http2StreamArray;
                    Settings settings2;
                    boolean bl5 = false;
                    Settings previousPeerSettings = Http2Connection.this.getPeerSettings();
                    Ref.ObjectRef objectRef = newPeerSettings;
                    if (clearPrevious) {
                        settings2 = settings;
                    } else {
                        void $this$apply;
                        Settings settings3 = new Settings();
                        boolean bl6 = false;
                        boolean bl7 = false;
                        Settings settings4 = settings3;
                        Ref.ObjectRef objectRef2 = objectRef;
                        boolean bl8 = false;
                        $this$apply.merge(previousPeerSettings);
                        $this$apply.merge(settings);
                        Unit unit = Unit.INSTANCE;
                        objectRef = objectRef2;
                        settings2 = settings3;
                    }
                    objectRef.element = settings2;
                    long peerInitialWindowSize = ((Settings)newPeerSettings.element).getInitialWindowSize();
                    delta.element = peerInitialWindowSize - (long)previousPeerSettings.getInitialWindowSize();
                    if (delta.element == 0L || Http2Connection.this.getStreams$okhttp().isEmpty()) {
                        http2StreamArray = null;
                    } else {
                        Collection<Http2Stream> $this$toTypedArray$iv = Http2Connection.this.getStreams$okhttp().values();
                        boolean $i$f$toTypedArray = false;
                        Collection<Http2Stream> thisCollection$iv = $this$toTypedArray$iv;
                        Http2Stream[] http2StreamArray2 = thisCollection$iv.toArray(new Http2Stream[0]);
                        if (http2StreamArray2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                        }
                        http2StreamArray = http2StreamArray2;
                    }
                    streamsToNotify.element = http2StreamArray;
                    Http2Connection.this.setPeerSettings((Settings)newPeerSettings.element);
                    TaskQueue $this$toTypedArray$iv = Http2Connection.this.settingsListenerQueue;
                    String name$iv = Http2Connection.this.getConnectionName$okhttp() + " onSettings";
                    long delayNanos$iv = 0L;
                    boolean cancelable$iv = true;
                    boolean $i$f$execute = false;
                    this_$iv.schedule(new Task(name$iv, cancelable$iv, name$iv, cancelable$iv, this, newPeerSettings, clearPrevious, settings, delta, streamsToNotify){
                        final /* synthetic */ String $name;
                        final /* synthetic */ boolean $cancelable;
                        final /* synthetic */ ReaderRunnable this$0;
                        final /* synthetic */ Ref.ObjectRef $newPeerSettings$inlined;
                        final /* synthetic */ boolean $clearPrevious$inlined;
                        final /* synthetic */ Settings $settings$inlined;
                        final /* synthetic */ Ref.LongRef $delta$inlined;
                        final /* synthetic */ Ref.ObjectRef $streamsToNotify$inlined;
                        {
                            this.$name = $captured_local_variable$1;
                            this.$cancelable = $captured_local_variable$2;
                            this.this$0 = readerRunnable;
                            this.$newPeerSettings$inlined = objectRef;
                            this.$clearPrevious$inlined = bl;
                            this.$settings$inlined = settings;
                            this.$delta$inlined = longRef;
                            this.$streamsToNotify$inlined = objectRef2;
                            super($super_call_param$3, $super_call_param$4);
                        }

                        public long runOnce() {
                            boolean bl = false;
                            this.this$0.Http2Connection.this.getListener$okhttp().onSettings(this.this$0.Http2Connection.this, (Settings)this.$newPeerSettings$inlined.element);
                            return -1L;
                        }
                    }, delayNanos$iv);
                    Unit unit = Unit.INSTANCE;
                }
                try {
                    Http2Connection.this.getWriter().applyAndAckSettings((Settings)newPeerSettings.element);
                } catch (IOException e) {
                    Http2Connection.this.failConnection(e);
                }
                Http2Stream[] http2StreamArray = Unit.INSTANCE;
            }
            if ((Http2Stream[])streamsToNotify.element != null) {
                Http2Stream[] http2StreamArray = (Http2Stream[])streamsToNotify.element;
                Intrinsics.checkNotNull(http2StreamArray);
                for (Http2Stream stream : http2StreamArray) {
                    boolean bl9 = false;
                    boolean bl10 = false;
                    synchronized (stream) {
                        boolean bl11 = false;
                        stream.addBytesToWriteWindow(delta.element);
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
        }

        @Override
        public void ackSettings() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         * WARNING - void declaration
         */
        @Override
        public void ping(boolean ack, int payload1, int payload2) {
            if (ack) {
                Http2Connection http2Connection = Http2Connection.this;
                boolean bl = false;
                boolean bl2 = false;
                synchronized (http2Connection) {
                    Object object;
                    boolean bl3 = false;
                    switch (payload1) {
                        case 1: {
                            Http2Connection http2Connection2 = Http2Connection.this;
                            long l = http2Connection2.intervalPongsReceived;
                            http2Connection2.intervalPongsReceived = l + 1L;
                            object = l;
                            break;
                        }
                        case 2: {
                            Http2Connection http2Connection3 = Http2Connection.this;
                            long l = http2Connection3.degradedPongsReceived;
                            http2Connection3.degradedPongsReceived = l + 1L;
                            object = l;
                            break;
                        }
                        case 3: {
                            Http2Connection http2Connection4 = Http2Connection.this;
                            long l = http2Connection4.awaitPongsReceived;
                            http2Connection4.awaitPongsReceived = l + 1L;
                            Http2Connection $this$notifyAll$iv = Http2Connection.this;
                            boolean $i$f$notifyAll = false;
                            Http2Connection http2Connection5 = $this$notifyAll$iv;
                            if (http2Connection5 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                            }
                            ((Object)http2Connection5).notifyAll();
                            object = Unit.INSTANCE;
                            break;
                        }
                        default: {
                            object = Unit.INSTANCE;
                        }
                    }
                    Unit unit = object;
                }
            } else {
                void this_$iv;
                TaskQueue taskQueue = Http2Connection.this.writerQueue;
                String name$iv = Http2Connection.this.getConnectionName$okhttp() + " ping";
                long delayNanos$iv = 0L;
                boolean cancelable$iv = true;
                boolean $i$f$execute = false;
                this_$iv.schedule(new Task(name$iv, cancelable$iv, name$iv, cancelable$iv, this, payload1, payload2){
                    final /* synthetic */ String $name;
                    final /* synthetic */ boolean $cancelable;
                    final /* synthetic */ ReaderRunnable this$0;
                    final /* synthetic */ int $payload1$inlined;
                    final /* synthetic */ int $payload2$inlined;
                    {
                        this.$name = $captured_local_variable$1;
                        this.$cancelable = $captured_local_variable$2;
                        this.this$0 = readerRunnable;
                        this.$payload1$inlined = n;
                        this.$payload2$inlined = n2;
                        super($super_call_param$3, $super_call_param$4);
                    }

                    public long runOnce() {
                        boolean bl = false;
                        this.this$0.Http2Connection.this.writePing(true, this.$payload1$inlined, this.$payload2$inlined);
                        return -1L;
                    }
                }, delayNanos$iv);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void goAway(int lastGoodStreamId, @NotNull ErrorCode errorCode, @NotNull ByteString debugData) {
            Intrinsics.checkNotNullParameter((Object)errorCode, "errorCode");
            Intrinsics.checkNotNullParameter(debugData, "debugData");
            if (debugData.size() > 0) {
                // empty if block
            }
            Http2Stream[] streamsCopy = null;
            Http2Connection http2Connection = Http2Connection.this;
            int n = 0;
            boolean bl = false;
            synchronized (http2Connection) {
                boolean bl2 = false;
                Collection<Http2Stream> $this$toTypedArray$iv = Http2Connection.this.getStreams$okhttp().values();
                boolean $i$f$toTypedArray = false;
                Collection<Http2Stream> thisCollection$iv = $this$toTypedArray$iv;
                Http2Stream[] http2StreamArray = thisCollection$iv.toArray(new Http2Stream[0]);
                if (http2StreamArray == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
                streamsCopy = http2StreamArray;
                Http2Connection.this.isShutdown = true;
                Http2Stream[] http2StreamArray2 = Unit.INSTANCE;
            }
            for (Http2Stream http2Stream : streamsCopy) {
                if (http2Stream.getId() <= lastGoodStreamId || !http2Stream.isLocallyInitiated()) continue;
                http2Stream.receiveRstStream(ErrorCode.REFUSED_STREAM);
                Http2Connection.this.removeStream$okhttp(http2Stream.getId());
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void windowUpdate(int streamId, long windowSizeIncrement) {
            if (streamId == 0) {
                Http2Connection http2Connection = Http2Connection.this;
                boolean bl = false;
                boolean bl2 = false;
                synchronized (http2Connection) {
                    boolean bl3 = false;
                    Http2Connection http2Connection2 = Http2Connection.this;
                    http2Connection2.writeBytesMaximum = http2Connection2.getWriteBytesMaximum() + windowSizeIncrement;
                    Http2Connection $this$notifyAll$iv = Http2Connection.this;
                    boolean $i$f$notifyAll = false;
                    Http2Connection http2Connection3 = $this$notifyAll$iv;
                    if (http2Connection3 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                    }
                    ((Object)http2Connection3).notifyAll();
                    Unit unit = Unit.INSTANCE;
                }
            }
            Http2Stream stream = Http2Connection.this.getStream(streamId);
            if (stream != null) {
                boolean bl = false;
                boolean bl4 = false;
                synchronized (stream) {
                    boolean bl5 = false;
                    stream.addBytesToWriteWindow(windowSizeIncrement);
                    Unit unit = Unit.INSTANCE;
                }
            }
        }

        @Override
        public void priority(int streamId, int streamDependency, int weight, boolean exclusive) {
        }

        @Override
        public void pushPromise(int streamId, int promisedStreamId, @NotNull List<Header> requestHeaders) {
            Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
            Http2Connection.this.pushRequestLater$okhttp(promisedStreamId, requestHeaders);
        }

        @Override
        public void alternateService(int streamId, @NotNull String origin, @NotNull ByteString protocol, @NotNull String host, int port, long maxAge) {
            Intrinsics.checkNotNullParameter(origin, "origin");
            Intrinsics.checkNotNullParameter(protocol, "protocol");
            Intrinsics.checkNotNullParameter(host, "host");
        }

        @NotNull
        public final Http2Reader getReader$okhttp() {
            return this.reader;
        }

        public ReaderRunnable(Http2Reader reader) {
            Intrinsics.checkNotNullParameter(reader, "reader");
            this.reader = reader;
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH&\u00a8\u0006\r"}, d2={"Lokhttp3/internal/http2/Http2Connection$Listener;", "", "()V", "onSettings", "", "connection", "Lokhttp3/internal/http2/Http2Connection;", "settings", "Lokhttp3/internal/http2/Settings;", "onStream", "stream", "Lokhttp3/internal/http2/Http2Stream;", "Companion", "okhttp"})
    public static abstract class Listener {
        @JvmField
        @NotNull
        public static final Listener REFUSE_INCOMING_STREAMS;
        public static final Companion Companion;

        public abstract void onStream(@NotNull Http2Stream var1) throws IOException;

        public void onSettings(@NotNull Http2Connection connection, @NotNull Settings settings) {
            Intrinsics.checkNotNullParameter(connection, "connection");
            Intrinsics.checkNotNullParameter(settings, "settings");
        }

        static {
            Companion = new Companion(null);
            REFUSE_INCOMING_STREAMS = new Listener(){

                public void onStream(@NotNull Http2Stream stream) throws IOException {
                    Intrinsics.checkNotNullParameter(stream, "stream");
                    stream.close(ErrorCode.REFUSED_STREAM, null);
                }
            };
        }

        @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2={"Lokhttp3/internal/http2/Http2Connection$Listener$Companion;", "", "()V", "REFUSE_INCOMING_STREAMS", "Lokhttp3/internal/http2/Http2Connection$Listener;", "okhttp"})
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2={"Lokhttp3/internal/http2/Http2Connection$Companion;", "", "()V", "AWAIT_PING", "", "DEFAULT_SETTINGS", "Lokhttp3/internal/http2/Settings;", "getDEFAULT_SETTINGS", "()Lokhttp3/internal/http2/Settings;", "DEGRADED_PING", "DEGRADED_PONG_TIMEOUT_NS", "INTERVAL_PING", "OKHTTP_CLIENT_WINDOW_SIZE", "okhttp"})
    public static final class Companion {
        @NotNull
        public final Settings getDEFAULT_SETTINGS() {
            return DEFAULT_SETTINGS;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

