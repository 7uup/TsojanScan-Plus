/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownServiceException;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.ConnectionSpec;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.ConnectionSpecSelector;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.connection.RealConnection$WhenMappings;
import okhttp3.internal.connection.RealConnectionPool;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http1.Http1ExchangeCodec;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2ExchangeCodec;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.http2.Settings;
import okhttp3.internal.http2.StreamResetException;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u00ec\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 {2\u00020\u00012\u00020\u0002:\u0001{B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0006\u00105\u001a\u000206J\u0018\u00107\u001a\u00020\u001d2\u0006\u00108\u001a\u0002092\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J>\u0010:\u001a\u0002062\u0006\u0010;\u001a\u00020\t2\u0006\u0010<\u001a\u00020\t2\u0006\u0010=\u001a\u00020\t2\u0006\u0010>\u001a\u00020\t2\u0006\u0010?\u001a\u00020\u001d2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020CJ%\u0010D\u001a\u0002062\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020\u00062\u0006\u0010H\u001a\u00020IH\u0000\u00a2\u0006\u0002\bJJ(\u0010K\u001a\u0002062\u0006\u0010;\u001a\u00020\t2\u0006\u0010<\u001a\u00020\t2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020CH\u0002J\u0010\u0010L\u001a\u0002062\u0006\u0010M\u001a\u00020NH\u0002J0\u0010O\u001a\u0002062\u0006\u0010;\u001a\u00020\t2\u0006\u0010<\u001a\u00020\t2\u0006\u0010=\u001a\u00020\t2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020CH\u0002J*\u0010P\u001a\u0004\u0018\u00010Q2\u0006\u0010<\u001a\u00020\t2\u0006\u0010=\u001a\u00020\t2\u0006\u0010R\u001a\u00020Q2\u0006\u00108\u001a\u000209H\u0002J\b\u0010S\u001a\u00020QH\u0002J(\u0010T\u001a\u0002062\u0006\u0010M\u001a\u00020N2\u0006\u0010>\u001a\u00020\t2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020CH\u0002J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\r\u0010U\u001a\u000206H\u0000\u00a2\u0006\u0002\bVJ%\u0010W\u001a\u00020\u001d2\u0006\u0010X\u001a\u00020Y2\u000e\u0010Z\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010[H\u0000\u00a2\u0006\u0002\b\\J\u000e\u0010]\u001a\u00020\u001d2\u0006\u0010^\u001a\u00020\u001dJ\u001d\u0010_\u001a\u00020`2\u0006\u0010E\u001a\u00020F2\u0006\u0010a\u001a\u00020bH\u0000\u00a2\u0006\u0002\bcJ\u0015\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u00020gH\u0000\u00a2\u0006\u0002\bhJ\r\u0010 \u001a\u000206H\u0000\u00a2\u0006\u0002\biJ\r\u0010!\u001a\u000206H\u0000\u00a2\u0006\u0002\bjJ\u0018\u0010k\u001a\u0002062\u0006\u0010l\u001a\u00020\u00152\u0006\u0010m\u001a\u00020nH\u0016J\u0010\u0010o\u001a\u0002062\u0006\u0010p\u001a\u00020qH\u0016J\b\u0010%\u001a\u00020&H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0016\u0010r\u001a\u00020\u001d2\f\u0010s\u001a\b\u0012\u0004\u0012\u00020\u00060[H\u0002J\b\u00101\u001a\u00020(H\u0016J\u0010\u0010t\u001a\u0002062\u0006\u0010>\u001a\u00020\tH\u0002J\u0010\u0010u\u001a\u00020\u001d2\u0006\u00108\u001a\u000209H\u0002J\b\u0010v\u001a\u00020wH\u0016J\u001f\u0010x\u001a\u0002062\u0006\u0010@\u001a\u00020\r2\b\u0010y\u001a\u0004\u0018\u00010IH\u0000\u00a2\u0006\u0002\bzR\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\u0017X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u001d8@X\u0080\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u00020\u001dX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001f\"\u0004\b#\u0010$R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010*\u001a\u00020\tX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u0010\u0010/\u001a\u0004\u0018\u000100X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u00101\u001a\u0004\u0018\u00010(X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u00102\u001a\u0004\u0018\u000103X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006|"}, d2={"Lokhttp3/internal/connection/RealConnection;", "Lokhttp3/internal/http2/Http2Connection$Listener;", "Lokhttp3/Connection;", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "route", "Lokhttp3/Route;", "(Lokhttp3/internal/connection/RealConnectionPool;Lokhttp3/Route;)V", "allocationLimit", "", "calls", "", "Ljava/lang/ref/Reference;", "Lokhttp3/internal/connection/RealCall;", "getCalls", "()Ljava/util/List;", "getConnectionPool", "()Lokhttp3/internal/connection/RealConnectionPool;", "handshake", "Lokhttp3/Handshake;", "http2Connection", "Lokhttp3/internal/http2/Http2Connection;", "idleAtNs", "", "getIdleAtNs$okhttp", "()J", "setIdleAtNs$okhttp", "(J)V", "isMultiplexed", "", "isMultiplexed$okhttp", "()Z", "noCoalescedConnections", "noNewExchanges", "getNoNewExchanges", "setNoNewExchanges", "(Z)V", "protocol", "Lokhttp3/Protocol;", "rawSocket", "Ljava/net/Socket;", "refusedStreamCount", "routeFailureCount", "getRouteFailureCount$okhttp", "()I", "setRouteFailureCount$okhttp", "(I)V", "sink", "Lokio/BufferedSink;", "socket", "source", "Lokio/BufferedSource;", "successCount", "cancel", "", "certificateSupportHost", "url", "Lokhttp3/HttpUrl;", "connect", "connectTimeout", "readTimeout", "writeTimeout", "pingIntervalMillis", "connectionRetryEnabled", "call", "Lokhttp3/Call;", "eventListener", "Lokhttp3/EventListener;", "connectFailed", "client", "Lokhttp3/OkHttpClient;", "failedRoute", "failure", "Ljava/io/IOException;", "connectFailed$okhttp", "connectSocket", "connectTls", "connectionSpecSelector", "Lokhttp3/internal/connection/ConnectionSpecSelector;", "connectTunnel", "createTunnel", "Lokhttp3/Request;", "tunnelRequest", "createTunnelRequest", "establishProtocol", "incrementSuccessCount", "incrementSuccessCount$okhttp", "isEligible", "address", "Lokhttp3/Address;", "routes", "", "isEligible$okhttp", "isHealthy", "doExtensiveChecks", "newCodec", "Lokhttp3/internal/http/ExchangeCodec;", "chain", "Lokhttp3/internal/http/RealInterceptorChain;", "newCodec$okhttp", "newWebSocketStreams", "Lokhttp3/internal/ws/RealWebSocket$Streams;", "exchange", "Lokhttp3/internal/connection/Exchange;", "newWebSocketStreams$okhttp", "noCoalescedConnections$okhttp", "noNewExchanges$okhttp", "onSettings", "connection", "settings", "Lokhttp3/internal/http2/Settings;", "onStream", "stream", "Lokhttp3/internal/http2/Http2Stream;", "routeMatchesAny", "candidates", "startHttp2", "supportsUrl", "toString", "", "trackFailure", "e", "trackFailure$okhttp", "Companion", "okhttp"})
public final class RealConnection
extends Http2Connection.Listener
implements Connection {
    private Socket rawSocket;
    private Socket socket;
    private Handshake handshake;
    private Protocol protocol;
    private Http2Connection http2Connection;
    private BufferedSource source;
    private BufferedSink sink;
    private boolean noNewExchanges;
    private boolean noCoalescedConnections;
    private int routeFailureCount;
    private int successCount;
    private int refusedStreamCount;
    private int allocationLimit;
    @NotNull
    private final List<Reference<RealCall>> calls;
    private long idleAtNs;
    @NotNull
    private final RealConnectionPool connectionPool;
    private final Route route;
    private static final String NPE_THROW_WITH_NULL = "throw with null exception";
    private static final int MAX_TUNNEL_ATTEMPTS = 21;
    public static final long IDLE_CONNECTION_HEALTHY_NS = 10000000000L;
    public static final Companion Companion = new Companion(null);

    public final boolean getNoNewExchanges() {
        return this.noNewExchanges;
    }

    public final void setNoNewExchanges(boolean bl) {
        this.noNewExchanges = bl;
    }

    public final int getRouteFailureCount$okhttp() {
        return this.routeFailureCount;
    }

    public final void setRouteFailureCount$okhttp(int n) {
        this.routeFailureCount = n;
    }

    @NotNull
    public final List<Reference<RealCall>> getCalls() {
        return this.calls;
    }

    public final long getIdleAtNs$okhttp() {
        return this.idleAtNs;
    }

    public final void setIdleAtNs$okhttp(long l) {
        this.idleAtNs = l;
    }

    public final boolean isMultiplexed$okhttp() {
        return this.http2Connection != null;
    }

    public final synchronized void noNewExchanges$okhttp() {
        this.noNewExchanges = true;
    }

    public final synchronized void noCoalescedConnections$okhttp() {
        this.noCoalescedConnections = true;
    }

    public final synchronized void incrementSuccessCount$okhttp() {
        int n = this.successCount;
        this.successCount = n + 1;
    }

    public final void connect(int connectTimeout, int readTimeout, int writeTimeout, int pingIntervalMillis, boolean connectionRetryEnabled, @NotNull Call call, @NotNull EventListener eventListener) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(eventListener, "eventListener");
        boolean bl = this.protocol == null;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "already connected";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RouteException routeException = null;
        List<ConnectionSpec> connectionSpecs = this.route.address().connectionSpecs();
        ConnectionSpecSelector connectionSpecSelector = new ConnectionSpecSelector(connectionSpecs);
        if (this.route.address().sslSocketFactory() == null) {
            if (!connectionSpecs.contains(ConnectionSpec.CLEARTEXT)) {
                throw (Throwable)new RouteException(new UnknownServiceException("CLEARTEXT communication not enabled for client"));
            }
            String host = this.route.address().url().host();
            if (!Platform.Companion.get().isCleartextTrafficPermitted(host)) {
                throw (Throwable)new RouteException(new UnknownServiceException("CLEARTEXT communication to " + host + " not permitted by network security policy"));
            }
        } else if (this.route.address().protocols().contains((Object)Protocol.H2_PRIOR_KNOWLEDGE)) {
            throw (Throwable)new RouteException(new UnknownServiceException("H2_PRIOR_KNOWLEDGE cannot be used with HTTPS"));
        }
        while (true) {
            try {
                if (this.route.requiresTunnel()) {
                    this.connectTunnel(connectTimeout, readTimeout, writeTimeout, call, eventListener);
                    if (this.rawSocket == null) {
                        break;
                    }
                } else {
                    this.connectSocket(connectTimeout, readTimeout, call, eventListener);
                }
                this.establishProtocol(connectionSpecSelector, pingIntervalMillis, call, eventListener);
                eventListener.connectEnd(call, this.route.socketAddress(), this.route.proxy(), this.protocol);
            } catch (IOException e) {
                Socket socket = this.socket;
                if (socket != null) {
                    Util.closeQuietly(socket);
                }
                Socket socket2 = this.rawSocket;
                if (socket2 != null) {
                    Util.closeQuietly(socket2);
                }
                this.socket = null;
                this.rawSocket = null;
                this.source = null;
                this.sink = null;
                this.handshake = null;
                this.protocol = null;
                this.http2Connection = null;
                this.allocationLimit = 1;
                eventListener.connectFailed(call, this.route.socketAddress(), this.route.proxy(), null, e);
                if (routeException == null) {
                    routeException = new RouteException(e);
                    continue;
                }
                routeException.addConnectException(e);
                if (connectionRetryEnabled && connectionSpecSelector.connectionFailed(e)) continue;
                throw (Throwable)routeException;
            }
            break;
        }
        if (this.route.requiresTunnel() && this.rawSocket == null) {
            throw (Throwable)new RouteException(new ProtocolException("Too many tunnel connections attempted: 21"));
        }
        this.idleAtNs = System.nanoTime();
    }

    /*
     * WARNING - void declaration
     */
    private final void connectTunnel(int connectTimeout, int readTimeout, int writeTimeout, Call call, EventListener eventListener) throws IOException {
        Request tunnelRequest = this.createTunnelRequest();
        HttpUrl url = tunnelRequest.url();
        int n = 0;
        int n2 = 21;
        while (n < n2) {
            void i;
            this.connectSocket(connectTimeout, readTimeout, call, eventListener);
            if (this.createTunnel(readTimeout, writeTimeout, tunnelRequest, url) == null) {
                break;
            }
            Socket socket = this.rawSocket;
            if (socket != null) {
                Util.closeQuietly(socket);
            }
            this.rawSocket = null;
            this.sink = null;
            this.source = null;
            eventListener.connectEnd(call, this.route.socketAddress(), this.route.proxy(), null);
            ++i;
        }
    }

    /*
     * Unable to fully structure code
     */
    private final void connectSocket(int connectTimeout, int readTimeout, Call call, EventListener eventListener) throws IOException {
        block7: {
            proxy = this.route.proxy();
            address = this.route.address();
            v0 = proxy.type();
            if (v0 == null) ** GOTO lbl-1000
            switch (RealConnection$WhenMappings.$EnumSwitchMapping$0[v0.ordinal()]) {
                case 1: 
                case 2: {
                    v1 = address.socketFactory().createSocket();
                    v2 = v1;
                    Intrinsics.checkNotNull(v1);
                    break;
                }
                default: lbl-1000:
                // 2 sources

                {
                    v2 = new Socket(proxy);
                }
            }
            this.rawSocket = rawSocket = v2;
            eventListener.connectStart(call, this.route.socketAddress(), proxy);
            rawSocket.setSoTimeout(readTimeout);
            try {
                Platform.Companion.get().connectSocket(rawSocket, this.route.socketAddress(), connectTimeout);
            } catch (ConnectException e) {
                var9_10 = new ConnectException("Failed to connect to " + this.route.socketAddress());
                var10_11 = false;
                var11_12 = false;
                $this$apply = var9_10;
                $i$a$-apply-RealConnection$connectSocket$1 = false;
                $this$apply.initCause(e);
                throw (Throwable)var9_10;
            }
            try {
                this.source = Okio.buffer(Okio.source(rawSocket));
                this.sink = Okio.buffer(Okio.sink(rawSocket));
            } catch (NullPointerException npe) {
                if (!Intrinsics.areEqual(npe.getMessage(), "throw with null exception")) break block7;
                throw (Throwable)new IOException(npe);
            }
        }
    }

    private final void establishProtocol(ConnectionSpecSelector connectionSpecSelector, int pingIntervalMillis, Call call, EventListener eventListener) throws IOException {
        if (this.route.address().sslSocketFactory() == null) {
            if (this.route.address().protocols().contains((Object)Protocol.H2_PRIOR_KNOWLEDGE)) {
                this.socket = this.rawSocket;
                this.protocol = Protocol.H2_PRIOR_KNOWLEDGE;
                this.startHttp2(pingIntervalMillis);
                return;
            }
            this.socket = this.rawSocket;
            this.protocol = Protocol.HTTP_1_1;
            return;
        }
        eventListener.secureConnectStart(call);
        this.connectTls(connectionSpecSelector);
        eventListener.secureConnectEnd(call, this.handshake);
        if (this.protocol == Protocol.HTTP_2) {
            this.startHttp2(pingIntervalMillis);
        }
    }

    private final void startHttp2(int pingIntervalMillis) throws IOException {
        Http2Connection http2Connection;
        Socket socket = this.socket;
        Intrinsics.checkNotNull(socket);
        Socket socket2 = socket;
        BufferedSource bufferedSource = this.source;
        Intrinsics.checkNotNull(bufferedSource);
        BufferedSource source2 = bufferedSource;
        BufferedSink bufferedSink = this.sink;
        Intrinsics.checkNotNull(bufferedSink);
        BufferedSink sink2 = bufferedSink;
        socket2.setSoTimeout(0);
        this.http2Connection = http2Connection = new Http2Connection.Builder(true, TaskRunner.INSTANCE).socket(socket2, this.route.address().url().host(), source2, sink2).listener(this).pingIntervalMillis(pingIntervalMillis).build();
        this.allocationLimit = Http2Connection.Companion.getDEFAULT_SETTINGS().getMaxConcurrentStreams();
        Http2Connection.start$default(http2Connection, false, null, 3, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void connectTls(ConnectionSpecSelector connectionSpecSelector) throws IOException {
        block9: {
            Address address = this.route.address();
            SSLSocketFactory sslSocketFactory = address.sslSocketFactory();
            boolean success = false;
            SSLSocket sslSocket = null;
            try {
                SSLSession sslSocketSession;
                SSLSocketFactory sSLSocketFactory = sslSocketFactory;
                Intrinsics.checkNotNull(sSLSocketFactory);
                Socket socket = sSLSocketFactory.createSocket(this.rawSocket, address.url().host(), address.url().port(), true);
                if (socket == null) {
                    throw new NullPointerException("null cannot be cast to non-null type javax.net.ssl.SSLSocket");
                }
                sslSocket = (SSLSocket)socket;
                ConnectionSpec connectionSpec = connectionSpecSelector.configureSecureSocket(sslSocket);
                if (connectionSpec.supportsTlsExtensions()) {
                    Platform.Companion.get().configureTlsExtensions(sslSocket, address.url().host(), address.protocols());
                }
                sslSocket.startHandshake();
                SSLSession sSLSession = sslSocketSession = sslSocket.getSession();
                Intrinsics.checkNotNullExpressionValue(sSLSession, "sslSocketSession");
                Handshake unverifiedHandshake = Handshake.Companion.get(sSLSession);
                HostnameVerifier hostnameVerifier = address.hostnameVerifier();
                Intrinsics.checkNotNull(hostnameVerifier);
                if (!hostnameVerifier.verify(address.url().host(), sslSocketSession)) {
                    List<Certificate> peerCertificates2 = unverifiedHandshake.peerCertificates();
                    Collection collection = peerCertificates2;
                    boolean bl = false;
                    if (!collection.isEmpty()) {
                        Certificate certificate = peerCertificates2.get(0);
                        if (certificate == null) {
                            throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");
                        }
                        X509Certificate cert = (X509Certificate)certificate;
                        StringBuilder stringBuilder = new StringBuilder().append("\n              |Hostname ").append(address.url().host()).append(" not verified:\n              |    certificate: ").append(CertificatePinner.Companion.pin(cert)).append("\n              |    DN: ");
                        Principal principal = cert.getSubjectDN();
                        Intrinsics.checkNotNullExpressionValue(principal, "cert.subjectDN");
                        throw (Throwable)new SSLPeerUnverifiedException(StringsKt.trimMargin$default(stringBuilder.append(principal.getName()).append("\n              |    subjectAltNames: ").append(OkHostnameVerifier.INSTANCE.allSubjectAltNames(cert)).append("\n              ").toString(), null, 1, null));
                    }
                    throw (Throwable)new SSLPeerUnverifiedException("Hostname " + address.url().host() + " not verified (no certificates)");
                }
                CertificatePinner certificatePinner = address.certificatePinner();
                Intrinsics.checkNotNull(certificatePinner);
                CertificatePinner certificatePinner2 = certificatePinner;
                this.handshake = new Handshake(unverifiedHandshake.tlsVersion(), unverifiedHandshake.cipherSuite(), unverifiedHandshake.localCertificates(), (Function0<? extends List<? extends Certificate>>)new Function0<List<? extends Certificate>>(certificatePinner2, unverifiedHandshake, address){
                    final /* synthetic */ CertificatePinner $certificatePinner;
                    final /* synthetic */ Handshake $unverifiedHandshake;
                    final /* synthetic */ Address $address;

                    @NotNull
                    public final List<Certificate> invoke() {
                        CertificateChainCleaner certificateChainCleaner = this.$certificatePinner.getCertificateChainCleaner$okhttp();
                        Intrinsics.checkNotNull(certificateChainCleaner);
                        return certificateChainCleaner.clean(this.$unverifiedHandshake.peerCertificates(), this.$address.url().host());
                    }
                    {
                        this.$certificatePinner = certificatePinner;
                        this.$unverifiedHandshake = handshake2;
                        this.$address = address;
                        super(0);
                    }
                });
                certificatePinner2.check$okhttp(address.url().host(), (Function0<? extends List<? extends X509Certificate>>)new Function0<List<? extends X509Certificate>>(this){
                    final /* synthetic */ RealConnection this$0;

                    /*
                     * WARNING - void declaration
                     */
                    @NotNull
                    public final List<X509Certificate> invoke() {
                        void $this$mapTo$iv$iv;
                        Handshake handshake2 = RealConnection.access$getHandshake$p(this.this$0);
                        Intrinsics.checkNotNull(handshake2);
                        Iterable $this$map$iv = handshake2.peerCertificates();
                        boolean $i$f$map = false;
                        Iterable iterable = $this$map$iv;
                        Collection destination$iv$iv = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                        boolean $i$f$mapTo = false;
                        for (T item$iv$iv : $this$mapTo$iv$iv) {
                            void it;
                            Certificate certificate = (Certificate)item$iv$iv;
                            Collection collection = destination$iv$iv;
                            boolean bl = false;
                            void v1 = it;
                            if (v1 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");
                            }
                            X509Certificate x509Certificate = (X509Certificate)v1;
                            collection.add(x509Certificate);
                        }
                        return (List)destination$iv$iv;
                    }
                    {
                        this.this$0 = realConnection;
                        super(0);
                    }
                });
                String maybeProtocol = connectionSpec.supportsTlsExtensions() ? Platform.Companion.get().getSelectedProtocol(sslSocket) : null;
                this.socket = sslSocket;
                this.source = Okio.buffer(Okio.source(sslSocket));
                this.sink = Okio.buffer(Okio.sink(sslSocket));
                this.protocol = maybeProtocol != null ? Protocol.Companion.get(maybeProtocol) : Protocol.HTTP_1_1;
                success = true;
                if (sslSocket == null) break block9;
                Platform.Companion.get().afterHandshake(sslSocket);
            } catch (Throwable throwable) {
                if (sslSocket != null) {
                    Platform.Companion.get().afterHandshake(sslSocket);
                }
                SSLSocket sSLSocket = sslSocket;
                if (sSLSocket != null) {
                    Util.closeQuietly(sSLSocket);
                }
                throw throwable;
            }
        }
    }

    private final Request createTunnel(int readTimeout, int writeTimeout, Request tunnelRequest, HttpUrl url) throws IOException {
        Response response;
        Request nextRequest = tunnelRequest;
        String requestLine = "CONNECT " + Util.toHostHeader(url, true) + " HTTP/1.1";
        block4: while (true) {
            BufferedSink sink2;
            BufferedSource source2;
            Intrinsics.checkNotNull(this.source);
            Intrinsics.checkNotNull(this.sink);
            Http1ExchangeCodec tunnelCodec = new Http1ExchangeCodec(null, this, source2, sink2);
            source2.timeout().timeout(readTimeout, TimeUnit.MILLISECONDS);
            sink2.timeout().timeout(writeTimeout, TimeUnit.MILLISECONDS);
            tunnelCodec.writeRequest(nextRequest.headers(), requestLine);
            tunnelCodec.finishRequest();
            Response.Builder builder = tunnelCodec.readResponseHeaders(false);
            Intrinsics.checkNotNull(builder);
            response = builder.request(nextRequest).build();
            tunnelCodec.skipConnectBody(response);
            switch (response.code()) {
                case 200: {
                    if (!source2.getBuffer().exhausted() || !sink2.getBuffer().exhausted()) {
                        throw (Throwable)new IOException("TLS tunnel buffered too many bytes!");
                    }
                    return null;
                }
                case 407: {
                    if (this.route.address().proxyAuthenticator().authenticate(this.route, response) != null) continue block4;
                    throw (Throwable)new IOException("Failed to authenticate with proxy");
                    if (!StringsKt.equals("close", Response.header$default(response, "Connection", null, 2, null), true)) continue block4;
                    return nextRequest;
                }
            }
            break;
        }
        throw (Throwable)new IOException("Unexpected response code for CONNECT: " + response.code());
    }

    private final Request createTunnelRequest() throws IOException {
        Request proxyConnectRequest = new Request.Builder().url(this.route.address().url()).method("CONNECT", null).header("Host", Util.toHostHeader(this.route.address().url(), true)).header("Proxy-Connection", "Keep-Alive").header("User-Agent", "okhttp/4.9.3").build();
        Response fakeAuthChallengeResponse = new Response.Builder().request(proxyConnectRequest).protocol(Protocol.HTTP_1_1).code(407).message("Preemptive Authenticate").body(Util.EMPTY_RESPONSE).sentRequestAtMillis(-1L).receivedResponseAtMillis(-1L).header("Proxy-Authenticate", "OkHttp-Preemptive").build();
        Request authenticatedRequest = this.route.address().proxyAuthenticator().authenticate(this.route, fakeAuthChallengeResponse);
        Request request = authenticatedRequest;
        if (request == null) {
            request = proxyConnectRequest;
        }
        return request;
    }

    public final boolean isEligible$okhttp(@NotNull Address address, @Nullable List<Route> routes) {
        Intrinsics.checkNotNullParameter(address, "address");
        RealConnection $this$assertThreadHoldsLock$iv = this;
        boolean $i$f$assertThreadHoldsLock = false;
        if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) {
            StringBuilder stringBuilder = new StringBuilder().append("Thread ");
            Thread thread2 = Thread.currentThread();
            Intrinsics.checkNotNullExpressionValue(thread2, "Thread.currentThread()");
            throw (Throwable)((Object)new AssertionError((Object)stringBuilder.append(thread2.getName()).append(" MUST hold lock on ").append($this$assertThreadHoldsLock$iv).toString()));
        }
        if (this.calls.size() >= this.allocationLimit || this.noNewExchanges) {
            return false;
        }
        if (!this.route.address().equalsNonHost$okhttp(address)) {
            return false;
        }
        if (Intrinsics.areEqual(address.url().host(), this.route().address().url().host())) {
            return true;
        }
        if (this.http2Connection == null) {
            return false;
        }
        if (routes == null || !this.routeMatchesAny(routes)) {
            return false;
        }
        if (address.hostnameVerifier() != OkHostnameVerifier.INSTANCE) {
            return false;
        }
        if (!this.supportsUrl(address.url())) {
            return false;
        }
        try {
            CertificatePinner certificatePinner = address.certificatePinner();
            Intrinsics.checkNotNull(certificatePinner);
            String string = address.url().host();
            Handshake handshake2 = this.handshake();
            Intrinsics.checkNotNull(handshake2);
            certificatePinner.check(string, handshake2.peerCertificates());
        } catch (SSLPeerUnverifiedException _) {
            return false;
        }
        return true;
    }

    private final boolean routeMatchesAny(List<Route> candidates) {
        boolean bl;
        block3: {
            Iterable $this$any$iv = candidates;
            boolean $i$f$any = false;
            if ($this$any$iv instanceof Collection && ((Collection)$this$any$iv).isEmpty()) {
                bl = false;
            } else {
                for (Object element$iv : $this$any$iv) {
                    Route it = (Route)element$iv;
                    boolean bl2 = false;
                    if (!(it.proxy().type() == Proxy.Type.DIRECT && this.route.proxy().type() == Proxy.Type.DIRECT && Intrinsics.areEqual(this.route.socketAddress(), it.socketAddress()))) continue;
                    bl = true;
                    break block3;
                }
                bl = false;
            }
        }
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final boolean supportsUrl(HttpUrl url) {
        RealConnection $this$assertThreadHoldsLock$iv = this;
        boolean $i$f$assertThreadHoldsLock = false;
        if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) {
            StringBuilder stringBuilder = new StringBuilder().append("Thread ");
            Thread thread2 = Thread.currentThread();
            Intrinsics.checkNotNullExpressionValue(thread2, "Thread.currentThread()");
            throw (Throwable)((Object)new AssertionError((Object)stringBuilder.append(thread2.getName()).append(" MUST hold lock on ").append($this$assertThreadHoldsLock$iv).toString()));
        }
        HttpUrl routeUrl = this.route.address().url();
        if (url.port() != routeUrl.port()) {
            return false;
        }
        if (Intrinsics.areEqual(url.host(), routeUrl.host())) {
            return true;
        }
        if (this.noCoalescedConnections) return false;
        if (this.handshake == null) return false;
        Handshake handshake2 = this.handshake;
        Intrinsics.checkNotNull(handshake2);
        if (!this.certificateSupportHost(url, handshake2)) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final boolean certificateSupportHost(HttpUrl url, Handshake handshake2) {
        List<Certificate> peerCertificates2 = handshake2.peerCertificates();
        Collection collection = peerCertificates2;
        boolean bl = false;
        if (collection.isEmpty()) return false;
        boolean bl2 = true;
        if (!bl2) return false;
        Certificate certificate = peerCertificates2.get(0);
        if (certificate == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");
        }
        if (!OkHostnameVerifier.INSTANCE.verify(url.host(), (X509Certificate)certificate)) return false;
        return true;
    }

    @NotNull
    public final ExchangeCodec newCodec$okhttp(@NotNull OkHttpClient client, @NotNull RealInterceptorChain chain) throws SocketException {
        ExchangeCodec exchangeCodec;
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(chain, "chain");
        Socket socket = this.socket;
        Intrinsics.checkNotNull(socket);
        Socket socket2 = socket;
        BufferedSource bufferedSource = this.source;
        Intrinsics.checkNotNull(bufferedSource);
        BufferedSource source2 = bufferedSource;
        BufferedSink bufferedSink = this.sink;
        Intrinsics.checkNotNull(bufferedSink);
        BufferedSink sink2 = bufferedSink;
        Http2Connection http2Connection = this.http2Connection;
        if (http2Connection != null) {
            exchangeCodec = new Http2ExchangeCodec(client, this, chain, http2Connection);
        } else {
            socket2.setSoTimeout(chain.readTimeoutMillis());
            source2.timeout().timeout(chain.getReadTimeoutMillis$okhttp(), TimeUnit.MILLISECONDS);
            sink2.timeout().timeout(chain.getWriteTimeoutMillis$okhttp(), TimeUnit.MILLISECONDS);
            exchangeCodec = new Http1ExchangeCodec(client, this, source2, sink2);
        }
        return exchangeCodec;
    }

    @NotNull
    public final RealWebSocket.Streams newWebSocketStreams$okhttp(@NotNull Exchange exchange) throws SocketException {
        Intrinsics.checkNotNullParameter(exchange, "exchange");
        Socket socket = this.socket;
        Intrinsics.checkNotNull(socket);
        Socket socket2 = socket;
        BufferedSource bufferedSource = this.source;
        Intrinsics.checkNotNull(bufferedSource);
        BufferedSource source2 = bufferedSource;
        BufferedSink bufferedSink = this.sink;
        Intrinsics.checkNotNull(bufferedSink);
        BufferedSink sink2 = bufferedSink;
        socket2.setSoTimeout(0);
        this.noNewExchanges$okhttp();
        return new RealWebSocket.Streams(exchange, source2, sink2, true, source2, sink2){
            final /* synthetic */ Exchange $exchange;
            final /* synthetic */ BufferedSource $source;
            final /* synthetic */ BufferedSink $sink;

            public void close() {
                this.$exchange.bodyComplete(-1L, true, true, null);
            }
            {
                this.$exchange = $captured_local_variable$0;
                this.$source = $captured_local_variable$1;
                this.$sink = $captured_local_variable$2;
                super($super_call_param$3, $super_call_param$4, $super_call_param$5);
            }
        };
    }

    @Override
    @NotNull
    public Route route() {
        return this.route;
    }

    public final void cancel() {
        block0: {
            Socket socket = this.rawSocket;
            if (socket == null) break block0;
            Util.closeQuietly(socket);
        }
    }

    @Override
    @NotNull
    public Socket socket() {
        Socket socket = this.socket;
        Intrinsics.checkNotNull(socket);
        return socket;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final boolean isHealthy(boolean doExtensiveChecks) {
        long l;
        RealConnection $this$assertThreadDoesntHoldLock$iv = this;
        boolean $i$f$assertThreadDoesntHoldLock = false;
        if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv)) {
            StringBuilder stringBuilder = new StringBuilder().append("Thread ");
            Thread thread2 = Thread.currentThread();
            Intrinsics.checkNotNullExpressionValue(thread2, "Thread.currentThread()");
            throw (Throwable)((Object)new AssertionError((Object)stringBuilder.append(thread2.getName()).append(" MUST NOT hold lock on ").append($this$assertThreadDoesntHoldLock$iv).toString()));
        }
        long nowNs = System.nanoTime();
        Socket socket = this.rawSocket;
        Intrinsics.checkNotNull(socket);
        Socket rawSocket = socket;
        Socket socket2 = this.socket;
        Intrinsics.checkNotNull(socket2);
        Socket socket3 = socket2;
        BufferedSource bufferedSource = this.source;
        Intrinsics.checkNotNull(bufferedSource);
        BufferedSource source2 = bufferedSource;
        if (rawSocket.isClosed() || socket3.isClosed() || socket3.isInputShutdown() || socket3.isOutputShutdown()) {
            return false;
        }
        Http2Connection http2Connection = this.http2Connection;
        if (http2Connection != null) {
            return http2Connection.isHealthy(nowNs);
        }
        RealConnection realConnection = this;
        boolean bl = false;
        boolean bl2 = false;
        synchronized (realConnection) {
            boolean bl3 = false;
            l = nowNs - this.idleAtNs;
        }
        long idleDurationNs = l;
        if (idleDurationNs >= 10000000000L && doExtensiveChecks) {
            return Util.isHealthy(socket3, source2);
        }
        return true;
    }

    @Override
    public void onStream(@NotNull Http2Stream stream) throws IOException {
        Intrinsics.checkNotNullParameter(stream, "stream");
        stream.close(ErrorCode.REFUSED_STREAM, null);
    }

    @Override
    public synchronized void onSettings(@NotNull Http2Connection connection, @NotNull Settings settings) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.allocationLimit = settings.getMaxConcurrentStreams();
    }

    @Override
    @Nullable
    public Handshake handshake() {
        return this.handshake;
    }

    public final void connectFailed$okhttp(@NotNull OkHttpClient client, @NotNull Route failedRoute, @NotNull IOException failure) {
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(failedRoute, "failedRoute");
        Intrinsics.checkNotNullParameter(failure, "failure");
        if (failedRoute.proxy().type() != Proxy.Type.DIRECT) {
            Address address = failedRoute.address();
            address.proxySelector().connectFailed(address.url().uri(), failedRoute.proxy().address(), failure);
        }
        client.getRouteDatabase().failed(failedRoute);
    }

    public final synchronized void trackFailure$okhttp(@NotNull RealCall call, @Nullable IOException e) {
        Intrinsics.checkNotNullParameter(call, "call");
        if (e instanceof StreamResetException) {
            if (((StreamResetException)e).errorCode == ErrorCode.REFUSED_STREAM) {
                int n = this.refusedStreamCount;
                this.refusedStreamCount = n + 1;
                if (this.refusedStreamCount > 1) {
                    this.noNewExchanges = true;
                    n = this.routeFailureCount;
                    this.routeFailureCount = n + 1;
                }
            } else if (((StreamResetException)e).errorCode != ErrorCode.CANCEL || !call.isCanceled()) {
                this.noNewExchanges = true;
                int n = this.routeFailureCount;
                this.routeFailureCount = n + 1;
            }
        } else if (!this.isMultiplexed$okhttp() || e instanceof ConnectionShutdownException) {
            this.noNewExchanges = true;
            if (this.successCount == 0) {
                if (e != null) {
                    this.connectFailed$okhttp(call.getClient(), this.route, e);
                }
                int n = this.routeFailureCount;
                this.routeFailureCount = n + 1;
            }
        }
    }

    @Override
    @NotNull
    public Protocol protocol() {
        Protocol protocol = this.protocol;
        Intrinsics.checkNotNull((Object)protocol);
        return protocol;
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder().append("Connection{").append(this.route.address().url().host()).append(':').append(this.route.address().url().port()).append(',').append(" proxy=").append(this.route.proxy()).append(" hostAddress=").append(this.route.socketAddress()).append(" cipherSuite=");
        Object object = this.handshake;
        if (object == null || (object = ((Handshake)object).cipherSuite()) == null) {
            object = "none";
        }
        return stringBuilder.append(object).append(" protocol=").append((Object)this.protocol).append('}').toString();
    }

    @NotNull
    public final RealConnectionPool getConnectionPool() {
        return this.connectionPool;
    }

    public RealConnection(@NotNull RealConnectionPool connectionPool, @NotNull Route route) {
        Intrinsics.checkNotNullParameter(connectionPool, "connectionPool");
        Intrinsics.checkNotNullParameter(route, "route");
        this.connectionPool = connectionPool;
        this.route = route;
        this.allocationLimit = 1;
        boolean bl = false;
        this.calls = new ArrayList();
        this.idleAtNs = Long.MAX_VALUE;
    }

    public static final /* synthetic */ Handshake access$getHandshake$p(RealConnection $this) {
        return $this.handshake;
    }

    public static final /* synthetic */ void access$setHandshake$p(RealConnection $this, Handshake handshake2) {
        $this.handshake = handshake2;
    }

    public static final /* synthetic */ Socket access$getSocket$p(RealConnection $this) {
        return $this.socket;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J&\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2={"Lokhttp3/internal/connection/RealConnection$Companion;", "", "()V", "IDLE_CONNECTION_HEALTHY_NS", "", "MAX_TUNNEL_ATTEMPTS", "", "NPE_THROW_WITH_NULL", "", "newTestConnection", "Lokhttp3/internal/connection/RealConnection;", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "route", "Lokhttp3/Route;", "socket", "Ljava/net/Socket;", "idleAtNs", "okhttp"})
    public static final class Companion {
        @NotNull
        public final RealConnection newTestConnection(@NotNull RealConnectionPool connectionPool, @NotNull Route route, @NotNull Socket socket, long idleAtNs) {
            Intrinsics.checkNotNullParameter(connectionPool, "connectionPool");
            Intrinsics.checkNotNullParameter(route, "route");
            Intrinsics.checkNotNullParameter(socket, "socket");
            RealConnection result = new RealConnection(connectionPool, route);
            result.socket = socket;
            result.setIdleAtNs$okhttp(idleAtNs);
            return result;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

