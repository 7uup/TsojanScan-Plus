/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.ws.WebSocketExtensions;
import okhttp3.internal.ws.WebSocketProtocol;
import okhttp3.internal.ws.WebSocketReader;
import okhttp3.internal.ws.WebSocketWriter;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u00b6\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001c\u0018\u0000 `2\u00020\u00012\u00020\u0002:\u0005_`abcB?\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\u0006\u0010\u000f\u001a\u00020\f\u00a2\u0006\u0002\u0010\u0010J\u0016\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\f2\u0006\u00105\u001a\u000206J\b\u00107\u001a\u000203H\u0016J\u001f\u00108\u001a\u0002032\u0006\u00109\u001a\u00020:2\b\u0010;\u001a\u0004\u0018\u00010<H\u0000\u00a2\u0006\u0002\b=J\u001a\u0010>\u001a\u00020\u00122\u0006\u0010?\u001a\u00020%2\b\u0010@\u001a\u0004\u0018\u00010\u0018H\u0016J \u0010>\u001a\u00020\u00122\u0006\u0010?\u001a\u00020%2\b\u0010@\u001a\u0004\u0018\u00010\u00182\u0006\u0010A\u001a\u00020\fJ\u000e\u0010B\u001a\u0002032\u0006\u0010C\u001a\u00020DJ\u001c\u0010E\u001a\u0002032\n\u0010F\u001a\u00060Gj\u0002`H2\b\u00109\u001a\u0004\u0018\u00010:J\u0016\u0010I\u001a\u0002032\u0006\u0010\u001e\u001a\u00020\u00182\u0006\u0010*\u001a\u00020+J\u0006\u0010J\u001a\u000203J\u0018\u0010K\u001a\u0002032\u0006\u0010?\u001a\u00020%2\u0006\u0010@\u001a\u00020\u0018H\u0016J\u0010\u0010L\u001a\u0002032\u0006\u0010M\u001a\u00020\u0018H\u0016J\u0010\u0010L\u001a\u0002032\u0006\u0010N\u001a\u00020 H\u0016J\u0010\u0010O\u001a\u0002032\u0006\u0010P\u001a\u00020 H\u0016J\u0010\u0010Q\u001a\u0002032\u0006\u0010P\u001a\u00020 H\u0016J\u000e\u0010R\u001a\u00020\u00122\u0006\u0010P\u001a\u00020 J\u0006\u0010S\u001a\u00020\u0012J\b\u0010!\u001a\u00020\fH\u0016J\u0006\u0010'\u001a\u00020%J\u0006\u0010(\u001a\u00020%J\b\u0010T\u001a\u00020\u0006H\u0016J\b\u0010U\u001a\u000203H\u0002J\u0010\u0010V\u001a\u00020\u00122\u0006\u0010M\u001a\u00020\u0018H\u0016J\u0010\u0010V\u001a\u00020\u00122\u0006\u0010N\u001a\u00020 H\u0016J\u0018\u0010V\u001a\u00020\u00122\u0006\u0010W\u001a\u00020 2\u0006\u0010X\u001a\u00020%H\u0002J\u0006\u0010)\u001a\u00020%J\u0006\u0010Y\u001a\u000203J\r\u0010Z\u001a\u00020\u0012H\u0000\u00a2\u0006\u0002\b[J\r\u0010\\\u001a\u000203H\u0000\u00a2\u0006\u0002\b]J\f\u0010^\u001a\u00020\u0012*\u00020\u000eH\u0002R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020%X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020%X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020%X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006d"}, d2={"Lokhttp3/internal/ws/RealWebSocket;", "Lokhttp3/WebSocket;", "Lokhttp3/internal/ws/WebSocketReader$FrameCallback;", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "originalRequest", "Lokhttp3/Request;", "listener", "Lokhttp3/WebSocketListener;", "random", "Ljava/util/Random;", "pingIntervalMillis", "", "extensions", "Lokhttp3/internal/ws/WebSocketExtensions;", "minimumDeflateSize", "(Lokhttp3/internal/concurrent/TaskRunner;Lokhttp3/Request;Lokhttp3/WebSocketListener;Ljava/util/Random;JLokhttp3/internal/ws/WebSocketExtensions;J)V", "awaitingPong", "", "call", "Lokhttp3/Call;", "enqueuedClose", "failed", "key", "", "getListener$okhttp", "()Lokhttp3/WebSocketListener;", "messageAndCloseQueue", "Ljava/util/ArrayDeque;", "", "name", "pongQueue", "Lokio/ByteString;", "queueSize", "reader", "Lokhttp3/internal/ws/WebSocketReader;", "receivedCloseCode", "", "receivedCloseReason", "receivedPingCount", "receivedPongCount", "sentPingCount", "streams", "Lokhttp3/internal/ws/RealWebSocket$Streams;", "taskQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "writer", "Lokhttp3/internal/ws/WebSocketWriter;", "writerTask", "Lokhttp3/internal/concurrent/Task;", "awaitTermination", "", "timeout", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "cancel", "checkUpgradeSuccess", "response", "Lokhttp3/Response;", "exchange", "Lokhttp3/internal/connection/Exchange;", "checkUpgradeSuccess$okhttp", "close", "code", "reason", "cancelAfterCloseMillis", "connect", "client", "Lokhttp3/OkHttpClient;", "failWebSocket", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "initReaderAndWriter", "loopReader", "onReadClose", "onReadMessage", "text", "bytes", "onReadPing", "payload", "onReadPong", "pong", "processNextFrame", "request", "runWriter", "send", "data", "formatOpcode", "tearDown", "writeOneFrame", "writeOneFrame$okhttp", "writePingFrame", "writePingFrame$okhttp", "isValid", "Close", "Companion", "Message", "Streams", "WriterTask", "okhttp"})
public final class RealWebSocket
implements WebSocket,
WebSocketReader.FrameCallback {
    private final String key;
    private Call call;
    private Task writerTask;
    private WebSocketReader reader;
    private WebSocketWriter writer;
    private TaskQueue taskQueue;
    private String name;
    private Streams streams;
    private final ArrayDeque<ByteString> pongQueue;
    private final ArrayDeque<Object> messageAndCloseQueue;
    private long queueSize;
    private boolean enqueuedClose;
    private int receivedCloseCode;
    private String receivedCloseReason;
    private boolean failed;
    private int sentPingCount;
    private int receivedPingCount;
    private int receivedPongCount;
    private boolean awaitingPong;
    private final Request originalRequest;
    @NotNull
    private final WebSocketListener listener;
    private final Random random;
    private final long pingIntervalMillis;
    private WebSocketExtensions extensions;
    private long minimumDeflateSize;
    private static final List<Protocol> ONLY_HTTP1;
    private static final long MAX_QUEUE_SIZE = 0x1000000L;
    private static final long CANCEL_AFTER_CLOSE_MILLIS = 60000L;
    public static final long DEFAULT_MINIMUM_DEFLATE_SIZE = 1024L;
    public static final Companion Companion;

    @Override
    @NotNull
    public Request request() {
        return this.originalRequest;
    }

    @Override
    public synchronized long queueSize() {
        return this.queueSize;
    }

    @Override
    public void cancel() {
        Call call = this.call;
        Intrinsics.checkNotNull(call);
        call.cancel();
    }

    public final void connect(@NotNull OkHttpClient client) {
        Intrinsics.checkNotNullParameter(client, "client");
        if (this.originalRequest.header("Sec-WebSocket-Extensions") != null) {
            this.failWebSocket(new ProtocolException("Request header not permitted: 'Sec-WebSocket-Extensions'"), null);
            return;
        }
        OkHttpClient webSocketClient = client.newBuilder().eventListener(EventListener.NONE).protocols(ONLY_HTTP1).build();
        Request request = this.originalRequest.newBuilder().header("Upgrade", "websocket").header("Connection", "Upgrade").header("Sec-WebSocket-Key", this.key).header("Sec-WebSocket-Version", "13").header("Sec-WebSocket-Extensions", "permessage-deflate").build();
        Call call = this.call = (Call)new RealCall(webSocketClient, request, true);
        Intrinsics.checkNotNull(call);
        call.enqueue(new Callback(this, request){
            final /* synthetic */ RealWebSocket this$0;
            final /* synthetic */ Request $request;

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                Exchange exchange = response.exchange();
                Streams streams = null;
                try {
                    this.this$0.checkUpgradeSuccess$okhttp(response, exchange);
                    Exchange exchange2 = exchange;
                    Intrinsics.checkNotNull(exchange2);
                    streams = exchange2.newWebSocketStreams();
                } catch (IOException e) {
                    Exchange exchange3 = exchange;
                    if (exchange3 != null) {
                        exchange3.webSocketUpgradeFailed();
                    }
                    this.this$0.failWebSocket(e, response);
                    Util.closeQuietly(response);
                    return;
                }
                WebSocketExtensions extensions = WebSocketExtensions.Companion.parse(response.headers());
                RealWebSocket.access$setExtensions$p(this.this$0, extensions);
                if (!RealWebSocket.access$isValid(this.this$0, extensions)) {
                    RealWebSocket realWebSocket = this.this$0;
                    boolean bl = false;
                    boolean bl2 = false;
                    synchronized (realWebSocket) {
                        boolean bl3 = false;
                        RealWebSocket.access$getMessageAndCloseQueue$p(this.this$0).clear();
                        bl2 = this.this$0.close(1010, "unexpected Sec-WebSocket-Extensions in response header");
                    }
                }
                try {
                    String name = Util.okHttpName + " WebSocket " + this.$request.url().redact();
                    this.this$0.initReaderAndWriter(name, streams);
                    this.this$0.getListener$okhttp().onOpen(this.this$0, response);
                    this.this$0.loopReader();
                } catch (Exception e) {
                    this.this$0.failWebSocket(e, null);
                }
            }

            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
                this.this$0.failWebSocket(e, null);
            }
            {
                this.this$0 = this$0;
                this.$request = $captured_local_variable$1;
            }
        });
    }

    private final boolean isValid(WebSocketExtensions $this$isValid) {
        int n;
        if ($this$isValid.unknownValues) {
            return false;
        }
        if ($this$isValid.clientMaxWindowBits != null) {
            return false;
        }
        return $this$isValid.serverMaxWindowBits == null || 8 <= (n = $this$isValid.serverMaxWindowBits.intValue()) && 15 >= n;
        {
        }
    }

    public final void checkUpgradeSuccess$okhttp(@NotNull Response response, @Nullable Exchange exchange) throws IOException {
        Intrinsics.checkNotNullParameter(response, "response");
        if (response.code() != 101) {
            throw (Throwable)new ProtocolException("Expected HTTP 101 response but was '" + response.code() + ' ' + response.message() + '\'');
        }
        String headerConnection = Response.header$default(response, "Connection", null, 2, null);
        if (!StringsKt.equals("Upgrade", headerConnection, true)) {
            throw (Throwable)new ProtocolException("Expected 'Connection' header value 'Upgrade' but was '" + headerConnection + '\'');
        }
        String headerUpgrade = Response.header$default(response, "Upgrade", null, 2, null);
        if (!StringsKt.equals("websocket", headerUpgrade, true)) {
            throw (Throwable)new ProtocolException("Expected 'Upgrade' header value 'websocket' but was '" + headerUpgrade + '\'');
        }
        String headerAccept = Response.header$default(response, "Sec-WebSocket-Accept", null, 2, null);
        String acceptExpected = ByteString.Companion.encodeUtf8(this.key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").sha1().base64();
        if (Intrinsics.areEqual(acceptExpected, headerAccept) ^ true) {
            throw (Throwable)new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + acceptExpected + "' but was '" + headerAccept + '\'');
        }
        if (exchange == null) {
            throw (Throwable)new ProtocolException("Web Socket exchange missing: bad interceptor?");
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * WARNING - void declaration
     */
    public final void initReaderAndWriter(@NotNull String name, @NotNull Streams streams) throws IOException {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(streams, "streams");
        WebSocketExtensions webSocketExtensions = this.extensions;
        Intrinsics.checkNotNull(webSocketExtensions);
        WebSocketExtensions extensions = webSocketExtensions;
        RealWebSocket realWebSocket = this;
        boolean bl = false;
        boolean bl2 = false;
        synchronized (realWebSocket) {
            boolean bl3 = false;
            this.name = name;
            this.streams = streams;
            this.writer = new WebSocketWriter(streams.getClient(), streams.getSink(), this.random, extensions.perMessageDeflate, extensions.noContextTakeover(streams.getClient()), this.minimumDeflateSize);
            this.writerTask = new WriterTask();
            if (this.pingIntervalMillis != 0L) {
                void this_$iv;
                long pingIntervalNanos = TimeUnit.MILLISECONDS.toNanos(this.pingIntervalMillis);
                TaskQueue taskQueue = this.taskQueue;
                String name$iv = name + " ping";
                boolean $i$f$schedule = false;
                this_$iv.schedule(new Task(name$iv, name$iv, pingIntervalNanos, this, name, streams, extensions){
                    final /* synthetic */ String $name;
                    final /* synthetic */ long $pingIntervalNanos$inlined;
                    final /* synthetic */ RealWebSocket this$0;
                    final /* synthetic */ String $name$inlined;
                    final /* synthetic */ Streams $streams$inlined;
                    final /* synthetic */ WebSocketExtensions $extensions$inlined;
                    {
                        this.$name = $captured_local_variable$1;
                        this.$pingIntervalNanos$inlined = l;
                        this.this$0 = realWebSocket;
                        this.$name$inlined = string;
                        this.$streams$inlined = streams;
                        this.$extensions$inlined = webSocketExtensions;
                        super($super_call_param$2, false, 2, null);
                    }

                    public long runOnce() {
                        boolean bl = false;
                        this.this$0.writePingFrame$okhttp();
                        return this.$pingIntervalNanos$inlined;
                    }
                }, pingIntervalNanos);
            }
            Collection collection = this.messageAndCloseQueue;
            boolean bl4 = false;
            if (!collection.isEmpty()) {
                this.runWriter();
            }
            Unit unit = Unit.INSTANCE;
        }
        this.reader = new WebSocketReader(streams.getClient(), streams.getSource(), this, extensions.perMessageDeflate, extensions.noContextTakeover(!streams.getClient()));
    }

    public final void loopReader() throws IOException {
        while (this.receivedCloseCode == -1) {
            WebSocketReader webSocketReader = this.reader;
            Intrinsics.checkNotNull(webSocketReader);
            webSocketReader.processNextFrame();
        }
    }

    public final boolean processNextFrame() throws IOException {
        boolean bl;
        try {
            WebSocketReader webSocketReader = this.reader;
            Intrinsics.checkNotNull(webSocketReader);
            webSocketReader.processNextFrame();
            bl = this.receivedCloseCode == -1;
        } catch (Exception e) {
            this.failWebSocket(e, null);
            bl = false;
        }
        return bl;
    }

    public final void awaitTermination(long timeout2, @NotNull TimeUnit timeUnit) throws InterruptedException {
        Intrinsics.checkNotNullParameter((Object)timeUnit, "timeUnit");
        this.taskQueue.idleLatch().await(timeout2, timeUnit);
    }

    public final void tearDown() throws InterruptedException {
        this.taskQueue.shutdown();
        this.taskQueue.idleLatch().await(10L, TimeUnit.SECONDS);
    }

    public final synchronized int sentPingCount() {
        return this.sentPingCount;
    }

    public final synchronized int receivedPingCount() {
        return this.receivedPingCount;
    }

    public final synchronized int receivedPongCount() {
        return this.receivedPongCount;
    }

    @Override
    public void onReadMessage(@NotNull String text) throws IOException {
        Intrinsics.checkNotNullParameter(text, "text");
        this.listener.onMessage((WebSocket)this, text);
    }

    @Override
    public void onReadMessage(@NotNull ByteString bytes) throws IOException {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        this.listener.onMessage((WebSocket)this, bytes);
    }

    @Override
    public synchronized void onReadPing(@NotNull ByteString payload) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (this.failed || this.enqueuedClose && this.messageAndCloseQueue.isEmpty()) {
            return;
        }
        this.pongQueue.add(payload);
        this.runWriter();
        int n = this.receivedPingCount;
        this.receivedPingCount = n + 1;
    }

    @Override
    public synchronized void onReadPong(@NotNull ByteString payload) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        int n = this.receivedPongCount;
        this.receivedPongCount = n + 1;
        this.awaitingPong = false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void onReadClose(int code, @NotNull String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        boolean bl = code != -1;
        boolean bl2 = false;
        boolean bl3 = false;
        bl3 = false;
        boolean bl4 = false;
        if (!bl) {
            boolean bl5 = false;
            String string = "Failed requirement.";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        Streams toClose = null;
        WebSocketReader readerToClose = null;
        WebSocketWriter writerToClose = null;
        RealWebSocket realWebSocket = this;
        boolean bl6 = false;
        boolean bl7 = false;
        synchronized (realWebSocket) {
            boolean bl8 = false;
            boolean bl9 = this.receivedCloseCode == -1;
            boolean bl10 = false;
            boolean bl11 = false;
            if (!bl9) {
                boolean bl12 = false;
                String string = "already closed";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            this.receivedCloseCode = code;
            this.receivedCloseReason = reason;
            if (this.enqueuedClose && this.messageAndCloseQueue.isEmpty()) {
                toClose = this.streams;
                this.streams = null;
                readerToClose = this.reader;
                this.reader = null;
                writerToClose = this.writer;
                this.writer = null;
                this.taskQueue.shutdown();
            }
            Unit unit = Unit.INSTANCE;
        }
        try {
            this.listener.onClosing(this, code, reason);
            if (toClose != null) {
                this.listener.onClosed(this, code, reason);
            }
        } finally {
            Streams streams = toClose;
            if (streams != null) {
                Util.closeQuietly(streams);
            }
            WebSocketReader webSocketReader = readerToClose;
            if (webSocketReader != null) {
                Util.closeQuietly(webSocketReader);
            }
            WebSocketWriter webSocketWriter = writerToClose;
            if (webSocketWriter != null) {
                Util.closeQuietly(webSocketWriter);
            }
        }
    }

    @Override
    public boolean send(@NotNull String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        return this.send(ByteString.Companion.encodeUtf8(text), 1);
    }

    @Override
    public boolean send(@NotNull ByteString bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return this.send(bytes, 2);
    }

    private final synchronized boolean send(ByteString data, int formatOpcode) {
        if (this.failed || this.enqueuedClose) {
            return false;
        }
        if (this.queueSize + (long)data.size() > 0x1000000L) {
            this.close(1001, null);
            return false;
        }
        this.queueSize += (long)data.size();
        this.messageAndCloseQueue.add(new Message(formatOpcode, data));
        this.runWriter();
        return true;
    }

    public final synchronized boolean pong(@NotNull ByteString payload) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (this.failed || this.enqueuedClose && this.messageAndCloseQueue.isEmpty()) {
            return false;
        }
        this.pongQueue.add(payload);
        this.runWriter();
        return true;
    }

    @Override
    public boolean close(int code, @Nullable String reason) {
        return this.close(code, reason, 60000L);
    }

    public final synchronized boolean close(int code, @Nullable String reason, long cancelAfterCloseMillis) {
        WebSocketProtocol.INSTANCE.validateCloseCode(code);
        ByteString reasonBytes = null;
        if (reason != null) {
            reasonBytes = ByteString.Companion.encodeUtf8(reason);
            boolean bl = (long)reasonBytes.size() <= 123L;
            boolean bl2 = false;
            boolean bl3 = false;
            if (!bl) {
                boolean bl4 = false;
                String string = "reason.size() > 123: " + reason;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
        }
        if (this.failed || this.enqueuedClose) {
            return false;
        }
        this.enqueuedClose = true;
        this.messageAndCloseQueue.add(new Close(code, reasonBytes, cancelAfterCloseMillis));
        this.runWriter();
        return true;
    }

    private final void runWriter() {
        RealWebSocket $this$assertThreadHoldsLock$iv = this;
        boolean $i$f$assertThreadHoldsLock = false;
        if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) {
            StringBuilder stringBuilder = new StringBuilder().append("Thread ");
            Thread thread2 = Thread.currentThread();
            Intrinsics.checkNotNullExpressionValue(thread2, "Thread.currentThread()");
            throw (Throwable)((Object)new AssertionError((Object)stringBuilder.append(thread2.getName()).append(" MUST hold lock on ").append($this$assertThreadHoldsLock$iv).toString()));
        }
        Task writerTask = this.writerTask;
        if (writerTask != null) {
            TaskQueue.schedule$default(this.taskQueue, writerTask, 0L, 2, null);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * WARNING - void declaration
     */
    public final boolean writeOneFrame$okhttp() throws IOException {
        void writerToClose;
        void readerToClose;
        void streamsToClose;
        void receivedCloseReason;
        void receivedCloseCode;
        void messageOrClose;
        WebSocketWriter writer = null;
        ByteString pong = null;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = null;
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = -1;
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = null;
        Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        objectRef3.element = null;
        Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
        objectRef4.element = null;
        Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
        objectRef5.element = null;
        RealWebSocket realWebSocket = this;
        boolean bl = false;
        boolean bl2 = false;
        synchronized (realWebSocket) {
            block27: {
                block26: {
                    boolean bl3 = false;
                    if (!this.failed) break block26;
                    boolean bl4 = false;
                    return bl4;
                }
                writer = this.writer;
                pong = this.pongQueue.poll();
                if (pong != null) break block27;
                messageOrClose.element = this.messageAndCloseQueue.poll();
                if (messageOrClose.element instanceof Close) {
                    receivedCloseCode.element = this.receivedCloseCode;
                    receivedCloseReason.element = this.receivedCloseReason;
                    if (receivedCloseCode.element != -1) {
                        streamsToClose.element = this.streams;
                        this.streams = null;
                        readerToClose.element = this.reader;
                        this.reader = null;
                        writerToClose.element = this.writer;
                        this.writer = null;
                        this.taskQueue.shutdown();
                    } else {
                        void name$iv;
                        void this_$iv;
                        Object t = messageOrClose.element;
                        if (t == null) {
                            throw new NullPointerException("null cannot be cast to non-null type okhttp3.internal.ws.RealWebSocket.Close");
                        }
                        long cancelAfterCloseMillis = ((Close)t).getCancelAfterCloseMillis();
                        TaskQueue taskQueue = this.taskQueue;
                        String string = this.name + " cancel";
                        long delayNanos$iv = TimeUnit.MILLISECONDS.toNanos(cancelAfterCloseMillis);
                        boolean cancelable$iv = true;
                        boolean $i$f$execute = false;
                        this_$iv.schedule(new Task((String)name$iv, cancelable$iv, (String)name$iv, cancelable$iv, this, writer, pong, (Ref.ObjectRef)messageOrClose, (Ref.IntRef)receivedCloseCode, (Ref.ObjectRef)receivedCloseReason, (Ref.ObjectRef)streamsToClose, (Ref.ObjectRef)readerToClose, (Ref.ObjectRef)writerToClose){
                            final /* synthetic */ String $name;
                            final /* synthetic */ boolean $cancelable;
                            final /* synthetic */ RealWebSocket this$0;
                            final /* synthetic */ WebSocketWriter $writer$inlined;
                            final /* synthetic */ ByteString $pong$inlined;
                            final /* synthetic */ Ref.ObjectRef $messageOrClose$inlined;
                            final /* synthetic */ Ref.IntRef $receivedCloseCode$inlined;
                            final /* synthetic */ Ref.ObjectRef $receivedCloseReason$inlined;
                            final /* synthetic */ Ref.ObjectRef $streamsToClose$inlined;
                            final /* synthetic */ Ref.ObjectRef $readerToClose$inlined;
                            final /* synthetic */ Ref.ObjectRef $writerToClose$inlined;
                            {
                                this.$name = $captured_local_variable$1;
                                this.$cancelable = $captured_local_variable$2;
                                this.this$0 = realWebSocket;
                                this.$writer$inlined = webSocketWriter;
                                this.$pong$inlined = byteString;
                                this.$messageOrClose$inlined = objectRef;
                                this.$receivedCloseCode$inlined = intRef;
                                this.$receivedCloseReason$inlined = objectRef2;
                                this.$streamsToClose$inlined = objectRef3;
                                this.$readerToClose$inlined = objectRef4;
                                this.$writerToClose$inlined = objectRef5;
                                super($super_call_param$3, $super_call_param$4);
                            }

                            public long runOnce() {
                                boolean bl = false;
                                this.this$0.cancel();
                                return -1L;
                            }
                        }, delayNanos$iv);
                    }
                    break block27;
                }
                if (messageOrClose.element != null) break block27;
                boolean bl5 = false;
                return bl5;
            }
            Unit unit = Unit.INSTANCE;
        }
        try {
            if (pong != null) {
                WebSocketWriter webSocketWriter = writer;
                Intrinsics.checkNotNull(webSocketWriter);
                webSocketWriter.writePong(pong);
            } else if (messageOrClose.element instanceof Message) {
                Object t = messageOrClose.element;
                if (t == null) {
                    throw new NullPointerException("null cannot be cast to non-null type okhttp3.internal.ws.RealWebSocket.Message");
                }
                Message message = (Message)t;
                WebSocketWriter webSocketWriter = writer;
                Intrinsics.checkNotNull(webSocketWriter);
                webSocketWriter.writeMessageFrame(message.getFormatOpcode(), message.getData());
                RealWebSocket realWebSocket2 = this;
                boolean bl6 = false;
                boolean bl7 = false;
                synchronized (realWebSocket2) {
                    boolean bl8 = false;
                    this.queueSize -= (long)message.getData().size();
                    Unit unit = Unit.INSTANCE;
                }
            } else if (messageOrClose.element instanceof Close) {
                Object t = messageOrClose.element;
                if (t == null) {
                    throw new NullPointerException("null cannot be cast to non-null type okhttp3.internal.ws.RealWebSocket.Close");
                }
                Close close = (Close)t;
                WebSocketWriter webSocketWriter = writer;
                Intrinsics.checkNotNull(webSocketWriter);
                webSocketWriter.writeClose(close.getCode(), close.getReason());
                if ((Streams)streamsToClose.element != null) {
                    WebSocket webSocket = this;
                    int n = receivedCloseCode.element;
                    String string = (String)receivedCloseReason.element;
                    Intrinsics.checkNotNull(string);
                    this.listener.onClosed(webSocket, n, string);
                }
            } else {
                throw (Throwable)((Object)new AssertionError());
            }
            boolean bl9 = true;
            return bl9;
        } finally {
            block28: {
                Streams streams = (Streams)streamsToClose.element;
                if (streams != null) {
                    Util.closeQuietly(streams);
                }
                WebSocketReader webSocketReader = (WebSocketReader)readerToClose.element;
                if (webSocketReader != null) {
                    Util.closeQuietly(webSocketReader);
                }
                WebSocketWriter webSocketWriter = (WebSocketWriter)writerToClose.element;
                if (webSocketWriter == null) break block28;
                Util.closeQuietly(webSocketWriter);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void writePingFrame$okhttp() {
        WebSocketWriter writer = null;
        int failedPing = 0;
        RealWebSocket realWebSocket = this;
        boolean bl = false;
        boolean bl2 = false;
        synchronized (realWebSocket) {
            boolean bl3 = false;
            if (this.failed) {
                return;
            }
            WebSocketWriter webSocketWriter = this.writer;
            if (webSocketWriter == null) {
                return;
            }
            writer = webSocketWriter;
            failedPing = this.awaitingPong ? this.sentPingCount : -1;
            int n = this.sentPingCount;
            this.sentPingCount = n + 1;
            this.awaitingPong = true;
            Unit unit = Unit.INSTANCE;
        }
        if (failedPing != -1) {
            this.failWebSocket(new SocketTimeoutException("sent ping but didn't receive pong within " + this.pingIntervalMillis + "ms (after " + (failedPing - 1) + " successful ping/pongs)"), null);
            return;
        }
        try {
            writer.writePing(ByteString.EMPTY);
        } catch (IOException e) {
            this.failWebSocket(e, null);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void failWebSocket(@NotNull Exception e, @Nullable Response response) {
        Intrinsics.checkNotNullParameter(e, "e");
        Streams streamsToClose = null;
        WebSocketReader readerToClose = null;
        WebSocketWriter writerToClose = null;
        RealWebSocket realWebSocket = this;
        boolean bl = false;
        boolean bl2 = false;
        synchronized (realWebSocket) {
            boolean bl3 = false;
            if (this.failed) {
                return;
            }
            this.failed = true;
            streamsToClose = this.streams;
            this.streams = null;
            readerToClose = this.reader;
            this.reader = null;
            writerToClose = this.writer;
            this.writer = null;
            this.taskQueue.shutdown();
            Unit unit = Unit.INSTANCE;
        }
        try {
            this.listener.onFailure(this, e, response);
        } finally {
            Streams streams = streamsToClose;
            if (streams != null) {
                Util.closeQuietly(streams);
            }
            WebSocketReader webSocketReader = readerToClose;
            if (webSocketReader != null) {
                Util.closeQuietly(webSocketReader);
            }
            WebSocketWriter webSocketWriter = writerToClose;
            if (webSocketWriter != null) {
                Util.closeQuietly(webSocketWriter);
            }
        }
    }

    @NotNull
    public final WebSocketListener getListener$okhttp() {
        return this.listener;
    }

    /*
     * WARNING - void declaration
     */
    public RealWebSocket(@NotNull TaskRunner taskRunner, @NotNull Request originalRequest, @NotNull WebSocketListener listener, @NotNull Random random, long pingIntervalMillis, @Nullable WebSocketExtensions extensions, long minimumDeflateSize) {
        void $this$apply;
        Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        Intrinsics.checkNotNullParameter(originalRequest, "originalRequest");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(random, "random");
        this.originalRequest = originalRequest;
        this.listener = listener;
        this.random = random;
        this.pingIntervalMillis = pingIntervalMillis;
        this.extensions = extensions;
        this.minimumDeflateSize = minimumDeflateSize;
        this.taskQueue = taskRunner.newQueue();
        this.pongQueue = new ArrayDeque();
        this.messageAndCloseQueue = new ArrayDeque();
        this.receivedCloseCode = -1;
        boolean bl = Intrinsics.areEqual("GET", this.originalRequest.method());
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "Request must be GET: " + this.originalRequest.method();
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        byte[] byArray = new byte[16];
        bl2 = false;
        bl3 = false;
        byte[] bl4 = byArray;
        ByteString.Companion companion = ByteString.Companion;
        RealWebSocket realWebSocket = this;
        boolean bl5 = false;
        this.random.nextBytes((byte[])$this$apply);
        Unit unit = Unit.INSTANCE;
        realWebSocket.key = ByteString.Companion.of$default(companion, byArray, 0, 0, 3, null).base64();
    }

    static {
        Companion = new Companion(null);
        ONLY_HTTP1 = CollectionsKt.listOf(Protocol.HTTP_1_1);
    }

    public static final /* synthetic */ void access$setName$p(RealWebSocket $this, String string) {
        $this.name = string;
    }

    public static final /* synthetic */ WebSocketExtensions access$getExtensions$p(RealWebSocket $this) {
        return $this.extensions;
    }

    public static final /* synthetic */ void access$setExtensions$p(RealWebSocket $this, WebSocketExtensions webSocketExtensions) {
        $this.extensions = webSocketExtensions;
    }

    public static final /* synthetic */ boolean access$isValid(RealWebSocket $this, WebSocketExtensions $this$access_u24isValid) {
        return $this.isValid($this$access_u24isValid);
    }

    public static final /* synthetic */ ArrayDeque access$getMessageAndCloseQueue$p(RealWebSocket $this) {
        return $this.messageAndCloseQueue;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2={"Lokhttp3/internal/ws/RealWebSocket$Message;", "", "formatOpcode", "", "data", "Lokio/ByteString;", "(ILokio/ByteString;)V", "getData", "()Lokio/ByteString;", "getFormatOpcode", "()I", "okhttp"})
    public static final class Message {
        private final int formatOpcode;
        @NotNull
        private final ByteString data;

        public final int getFormatOpcode() {
            return this.formatOpcode;
        }

        @NotNull
        public final ByteString getData() {
            return this.data;
        }

        public Message(int formatOpcode, @NotNull ByteString data) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.formatOpcode = formatOpcode;
            this.data = data;
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2={"Lokhttp3/internal/ws/RealWebSocket$Close;", "", "code", "", "reason", "Lokio/ByteString;", "cancelAfterCloseMillis", "", "(ILokio/ByteString;J)V", "getCancelAfterCloseMillis", "()J", "getCode", "()I", "getReason", "()Lokio/ByteString;", "okhttp"})
    public static final class Close {
        private final int code;
        @Nullable
        private final ByteString reason;
        private final long cancelAfterCloseMillis;

        public final int getCode() {
            return this.code;
        }

        @Nullable
        public final ByteString getReason() {
            return this.reason;
        }

        public final long getCancelAfterCloseMillis() {
            return this.cancelAfterCloseMillis;
        }

        public Close(int code, @Nullable ByteString reason, long cancelAfterCloseMillis) {
            this.code = code;
            this.reason = reason;
            this.cancelAfterCloseMillis = cancelAfterCloseMillis;
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2={"Lokhttp3/internal/ws/RealWebSocket$Streams;", "Ljava/io/Closeable;", "client", "", "source", "Lokio/BufferedSource;", "sink", "Lokio/BufferedSink;", "(ZLokio/BufferedSource;Lokio/BufferedSink;)V", "getClient", "()Z", "getSink", "()Lokio/BufferedSink;", "getSource", "()Lokio/BufferedSource;", "okhttp"})
    public static abstract class Streams
    implements Closeable {
        private final boolean client;
        @NotNull
        private final BufferedSource source;
        @NotNull
        private final BufferedSink sink;

        public final boolean getClient() {
            return this.client;
        }

        @NotNull
        public final BufferedSource getSource() {
            return this.source;
        }

        @NotNull
        public final BufferedSink getSink() {
            return this.sink;
        }

        public Streams(boolean client, @NotNull BufferedSource source2, @NotNull BufferedSink sink2) {
            Intrinsics.checkNotNullParameter(source2, "source");
            Intrinsics.checkNotNullParameter(sink2, "sink");
            this.client = client;
            this.source = source2;
            this.sink = sink2;
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016\u00a8\u0006\u0005"}, d2={"Lokhttp3/internal/ws/RealWebSocket$WriterTask;", "Lokhttp3/internal/concurrent/Task;", "(Lokhttp3/internal/ws/RealWebSocket;)V", "runOnce", "", "okhttp"})
    private final class WriterTask
    extends Task {
        @Override
        public long runOnce() {
            try {
                if (RealWebSocket.this.writeOneFrame$okhttp()) {
                    return 0L;
                }
            } catch (IOException e) {
                RealWebSocket.this.failWebSocket(e, null);
            }
            return -1L;
        }

        public WriterTask() {
            super(RealWebSocket.this.name + " writer", false, 2, null);
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2={"Lokhttp3/internal/ws/RealWebSocket$Companion;", "", "()V", "CANCEL_AFTER_CLOSE_MILLIS", "", "DEFAULT_MINIMUM_DEFLATE_SIZE", "MAX_QUEUE_SIZE", "ONLY_HTTP1", "", "Lokhttp3/Protocol;", "okhttp"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

