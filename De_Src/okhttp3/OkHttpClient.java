/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
 */
package okhttp3;

import java.net.Proxy;
import java.net.ProxySelector;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.CookieJar;
import okhttp3.Dispatcher;
import okhttp3.Dns;
import okhttp3.EventListener;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.proxy.NullProxySelector;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u00ee\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 y2\u00020\u00012\u00020\u00022\u00020\u0003:\u0002xyB\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0004B\u000f\b\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\r\u0010\b\u001a\u00020\tH\u0007\u00a2\u0006\u0002\bSJ\u000f\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007\u00a2\u0006\u0002\bTJ\r\u0010\u000e\u001a\u00020\u000fH\u0007\u00a2\u0006\u0002\bUJ\r\u0010\u0014\u001a\u00020\u0015H\u0007\u00a2\u0006\u0002\bVJ\r\u0010\u0017\u001a\u00020\u000fH\u0007\u00a2\u0006\u0002\bWJ\r\u0010\u0018\u001a\u00020\u0019H\u0007\u00a2\u0006\u0002\bXJ\u0013\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0007\u00a2\u0006\u0002\bYJ\r\u0010\u001f\u001a\u00020 H\u0007\u00a2\u0006\u0002\bZJ\r\u0010\"\u001a\u00020#H\u0007\u00a2\u0006\u0002\b[J\r\u0010%\u001a\u00020&H\u0007\u00a2\u0006\u0002\b\\J\r\u0010(\u001a\u00020)H\u0007\u00a2\u0006\u0002\b]J\r\u0010+\u001a\u00020,H\u0007\u00a2\u0006\u0002\b^J\r\u0010.\u001a\u00020,H\u0007\u00a2\u0006\u0002\b_J\r\u0010/\u001a\u000200H\u0007\u00a2\u0006\u0002\b`J\u0013\u00102\u001a\b\u0012\u0004\u0012\u0002030\u001cH\u0007\u00a2\u0006\u0002\baJ\u0013\u00107\u001a\b\u0012\u0004\u0012\u0002030\u001cH\u0007\u00a2\u0006\u0002\bbJ\b\u0010c\u001a\u00020\u0006H\u0016J\u0010\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u00020gH\u0016J\u0018\u0010h\u001a\u00020i2\u0006\u0010f\u001a\u00020g2\u0006\u0010j\u001a\u00020kH\u0016J\r\u00108\u001a\u00020\u000fH\u0007\u00a2\u0006\u0002\blJ\u0013\u00109\u001a\b\u0012\u0004\u0012\u00020:0\u001cH\u0007\u00a2\u0006\u0002\bmJ\u000f\u0010;\u001a\u0004\u0018\u00010<H\u0007\u00a2\u0006\u0002\bnJ\r\u0010>\u001a\u00020\tH\u0007\u00a2\u0006\u0002\boJ\r\u0010?\u001a\u00020@H\u0007\u00a2\u0006\u0002\bpJ\r\u0010B\u001a\u00020\u000fH\u0007\u00a2\u0006\u0002\bqJ\r\u0010C\u001a\u00020,H\u0007\u00a2\u0006\u0002\brJ\r\u0010H\u001a\u00020IH\u0007\u00a2\u0006\u0002\bsJ\r\u0010K\u001a\u00020LH\u0007\u00a2\u0006\u0002\btJ\b\u0010u\u001a\u00020vH\u0002J\r\u0010O\u001a\u00020\u000fH\u0007\u00a2\u0006\u0002\bwR\u0013\u0010\b\u001a\u00020\t8G\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\nR\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f8G\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\rR\u0013\u0010\u000e\u001a\u00020\u000f8G\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0010R\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u00128G\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0013R\u0013\u0010\u0014\u001a\u00020\u00158G\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0016R\u0013\u0010\u0017\u001a\u00020\u000f8G\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0013\u0010\u0018\u001a\u00020\u00198G\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u001aR\u0019\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c8G\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001eR\u0013\u0010\u001f\u001a\u00020 8G\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010!R\u0013\u0010\"\u001a\u00020#8G\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010$R\u0013\u0010%\u001a\u00020&8G\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010'R\u0013\u0010(\u001a\u00020)8G\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010*R\u0013\u0010+\u001a\u00020,8G\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010-R\u0013\u0010.\u001a\u00020,8G\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010-R\u0013\u0010/\u001a\u0002008G\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u00101R\u0019\u00102\u001a\b\u0012\u0004\u0012\u0002030\u001c8G\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001eR\u0013\u00104\u001a\u0002058G\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u00106R\u0019\u00107\u001a\b\u0012\u0004\u0012\u0002030\u001c8G\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010\u001eR\u0013\u00108\u001a\u00020\u000f8G\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010\u0010R\u0019\u00109\u001a\b\u0012\u0004\u0012\u00020:0\u001c8G\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010\u001eR\u0015\u0010;\u001a\u0004\u0018\u00010<8G\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010=R\u0013\u0010>\u001a\u00020\t8G\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010\nR\u0013\u0010?\u001a\u00020@8G\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u0010AR\u0013\u0010B\u001a\u00020\u000f8G\u00a2\u0006\b\n\u0000\u001a\u0004\bB\u0010\u0010R\u0013\u0010C\u001a\u00020,8G\u00a2\u0006\b\n\u0000\u001a\u0004\bC\u0010-R\u0011\u0010D\u001a\u00020E\u00a2\u0006\b\n\u0000\u001a\u0004\bF\u0010GR\u0013\u0010H\u001a\u00020I8G\u00a2\u0006\b\n\u0000\u001a\u0004\bH\u0010JR\u0011\u0010K\u001a\u00020L8G\u00a2\u0006\u0006\u001a\u0004\bK\u0010MR\u0010\u0010N\u001a\u0004\u0018\u00010LX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0013\u0010O\u001a\u00020\u000f8G\u00a2\u0006\b\n\u0000\u001a\u0004\bO\u0010\u0010R\u0015\u0010P\u001a\u0004\u0018\u00010Q8G\u00a2\u0006\b\n\u0000\u001a\u0004\bP\u0010R\u00a8\u0006z"}, d2={"Lokhttp3/OkHttpClient;", "", "Lokhttp3/Call$Factory;", "Lokhttp3/WebSocket$Factory;", "()V", "builder", "Lokhttp3/OkHttpClient$Builder;", "(Lokhttp3/OkHttpClient$Builder;)V", "authenticator", "Lokhttp3/Authenticator;", "()Lokhttp3/Authenticator;", "cache", "Lokhttp3/Cache;", "()Lokhttp3/Cache;", "callTimeoutMillis", "", "()I", "certificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "()Lokhttp3/internal/tls/CertificateChainCleaner;", "certificatePinner", "Lokhttp3/CertificatePinner;", "()Lokhttp3/CertificatePinner;", "connectTimeoutMillis", "connectionPool", "Lokhttp3/ConnectionPool;", "()Lokhttp3/ConnectionPool;", "connectionSpecs", "", "Lokhttp3/ConnectionSpec;", "()Ljava/util/List;", "cookieJar", "Lokhttp3/CookieJar;", "()Lokhttp3/CookieJar;", "dispatcher", "Lokhttp3/Dispatcher;", "()Lokhttp3/Dispatcher;", "dns", "Lokhttp3/Dns;", "()Lokhttp3/Dns;", "eventListenerFactory", "Lokhttp3/EventListener$Factory;", "()Lokhttp3/EventListener$Factory;", "followRedirects", "", "()Z", "followSslRedirects", "hostnameVerifier", "Ljavax/net/ssl/HostnameVerifier;", "()Ljavax/net/ssl/HostnameVerifier;", "interceptors", "Lokhttp3/Interceptor;", "minWebSocketMessageToCompress", "", "()J", "networkInterceptors", "pingIntervalMillis", "protocols", "Lokhttp3/Protocol;", "proxy", "Ljava/net/Proxy;", "()Ljava/net/Proxy;", "proxyAuthenticator", "proxySelector", "Ljava/net/ProxySelector;", "()Ljava/net/ProxySelector;", "readTimeoutMillis", "retryOnConnectionFailure", "routeDatabase", "Lokhttp3/internal/connection/RouteDatabase;", "getRouteDatabase", "()Lokhttp3/internal/connection/RouteDatabase;", "socketFactory", "Ljavax/net/SocketFactory;", "()Ljavax/net/SocketFactory;", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "()Ljavax/net/ssl/SSLSocketFactory;", "sslSocketFactoryOrNull", "writeTimeoutMillis", "x509TrustManager", "Ljavax/net/ssl/X509TrustManager;", "()Ljavax/net/ssl/X509TrustManager;", "-deprecated_authenticator", "-deprecated_cache", "-deprecated_callTimeoutMillis", "-deprecated_certificatePinner", "-deprecated_connectTimeoutMillis", "-deprecated_connectionPool", "-deprecated_connectionSpecs", "-deprecated_cookieJar", "-deprecated_dispatcher", "-deprecated_dns", "-deprecated_eventListenerFactory", "-deprecated_followRedirects", "-deprecated_followSslRedirects", "-deprecated_hostnameVerifier", "-deprecated_interceptors", "-deprecated_networkInterceptors", "newBuilder", "newCall", "Lokhttp3/Call;", "request", "Lokhttp3/Request;", "newWebSocket", "Lokhttp3/WebSocket;", "listener", "Lokhttp3/WebSocketListener;", "-deprecated_pingIntervalMillis", "-deprecated_protocols", "-deprecated_proxy", "-deprecated_proxyAuthenticator", "-deprecated_proxySelector", "-deprecated_readTimeoutMillis", "-deprecated_retryOnConnectionFailure", "-deprecated_socketFactory", "-deprecated_sslSocketFactory", "verifyClientState", "", "-deprecated_writeTimeoutMillis", "Builder", "Companion", "okhttp"})
public class OkHttpClient
implements Cloneable,
Call.Factory,
WebSocket.Factory {
    @NotNull
    private final Dispatcher dispatcher;
    @NotNull
    private final ConnectionPool connectionPool;
    @NotNull
    private final List<Interceptor> interceptors;
    @NotNull
    private final List<Interceptor> networkInterceptors;
    @NotNull
    private final EventListener.Factory eventListenerFactory;
    private final boolean retryOnConnectionFailure;
    @NotNull
    private final Authenticator authenticator;
    private final boolean followRedirects;
    private final boolean followSslRedirects;
    @NotNull
    private final CookieJar cookieJar;
    @Nullable
    private final Cache cache;
    @NotNull
    private final Dns dns;
    @Nullable
    private final Proxy proxy;
    @NotNull
    private final ProxySelector proxySelector;
    @NotNull
    private final Authenticator proxyAuthenticator;
    @NotNull
    private final SocketFactory socketFactory;
    private final SSLSocketFactory sslSocketFactoryOrNull;
    @Nullable
    private final X509TrustManager x509TrustManager;
    @NotNull
    private final List<ConnectionSpec> connectionSpecs;
    @NotNull
    private final List<Protocol> protocols;
    @NotNull
    private final HostnameVerifier hostnameVerifier;
    @NotNull
    private final CertificatePinner certificatePinner;
    @Nullable
    private final CertificateChainCleaner certificateChainCleaner;
    private final int callTimeoutMillis;
    private final int connectTimeoutMillis;
    private final int readTimeoutMillis;
    private final int writeTimeoutMillis;
    private final int pingIntervalMillis;
    private final long minWebSocketMessageToCompress;
    @NotNull
    private final RouteDatabase routeDatabase;
    @NotNull
    private static final List<Protocol> DEFAULT_PROTOCOLS;
    @NotNull
    private static final List<ConnectionSpec> DEFAULT_CONNECTION_SPECS;
    public static final Companion Companion;

    @JvmName(name="dispatcher")
    @NotNull
    public final Dispatcher dispatcher() {
        return this.dispatcher;
    }

    @JvmName(name="connectionPool")
    @NotNull
    public final ConnectionPool connectionPool() {
        return this.connectionPool;
    }

    @JvmName(name="interceptors")
    @NotNull
    public final List<Interceptor> interceptors() {
        return this.interceptors;
    }

    @JvmName(name="networkInterceptors")
    @NotNull
    public final List<Interceptor> networkInterceptors() {
        return this.networkInterceptors;
    }

    @JvmName(name="eventListenerFactory")
    @NotNull
    public final EventListener.Factory eventListenerFactory() {
        return this.eventListenerFactory;
    }

    @JvmName(name="retryOnConnectionFailure")
    public final boolean retryOnConnectionFailure() {
        return this.retryOnConnectionFailure;
    }

    @JvmName(name="authenticator")
    @NotNull
    public final Authenticator authenticator() {
        return this.authenticator;
    }

    @JvmName(name="followRedirects")
    public final boolean followRedirects() {
        return this.followRedirects;
    }

    @JvmName(name="followSslRedirects")
    public final boolean followSslRedirects() {
        return this.followSslRedirects;
    }

    @JvmName(name="cookieJar")
    @NotNull
    public final CookieJar cookieJar() {
        return this.cookieJar;
    }

    @JvmName(name="cache")
    @Nullable
    public final Cache cache() {
        return this.cache;
    }

    @JvmName(name="dns")
    @NotNull
    public final Dns dns() {
        return this.dns;
    }

    @JvmName(name="proxy")
    @Nullable
    public final Proxy proxy() {
        return this.proxy;
    }

    @JvmName(name="proxySelector")
    @NotNull
    public final ProxySelector proxySelector() {
        return this.proxySelector;
    }

    @JvmName(name="proxyAuthenticator")
    @NotNull
    public final Authenticator proxyAuthenticator() {
        return this.proxyAuthenticator;
    }

    @JvmName(name="socketFactory")
    @NotNull
    public final SocketFactory socketFactory() {
        return this.socketFactory;
    }

    @JvmName(name="sslSocketFactory")
    @NotNull
    public final SSLSocketFactory sslSocketFactory() {
        SSLSocketFactory sSLSocketFactory = this.sslSocketFactoryOrNull;
        if (sSLSocketFactory == null) {
            throw (Throwable)new IllegalStateException("CLEARTEXT-only client");
        }
        return sSLSocketFactory;
    }

    @JvmName(name="x509TrustManager")
    @Nullable
    public final X509TrustManager x509TrustManager() {
        return this.x509TrustManager;
    }

    @JvmName(name="connectionSpecs")
    @NotNull
    public final List<ConnectionSpec> connectionSpecs() {
        return this.connectionSpecs;
    }

    @JvmName(name="protocols")
    @NotNull
    public final List<Protocol> protocols() {
        return this.protocols;
    }

    @JvmName(name="hostnameVerifier")
    @NotNull
    public final HostnameVerifier hostnameVerifier() {
        return this.hostnameVerifier;
    }

    @JvmName(name="certificatePinner")
    @NotNull
    public final CertificatePinner certificatePinner() {
        return this.certificatePinner;
    }

    @JvmName(name="certificateChainCleaner")
    @Nullable
    public final CertificateChainCleaner certificateChainCleaner() {
        return this.certificateChainCleaner;
    }

    @JvmName(name="callTimeoutMillis")
    public final int callTimeoutMillis() {
        return this.callTimeoutMillis;
    }

    @JvmName(name="connectTimeoutMillis")
    public final int connectTimeoutMillis() {
        return this.connectTimeoutMillis;
    }

    @JvmName(name="readTimeoutMillis")
    public final int readTimeoutMillis() {
        return this.readTimeoutMillis;
    }

    @JvmName(name="writeTimeoutMillis")
    public final int writeTimeoutMillis() {
        return this.writeTimeoutMillis;
    }

    @JvmName(name="pingIntervalMillis")
    public final int pingIntervalMillis() {
        return this.pingIntervalMillis;
    }

    @JvmName(name="minWebSocketMessageToCompress")
    public final long minWebSocketMessageToCompress() {
        return this.minWebSocketMessageToCompress;
    }

    @NotNull
    public final RouteDatabase getRouteDatabase() {
        return this.routeDatabase;
    }

    private final void verifyClientState() {
        boolean bl;
        boolean bl2;
        block16: {
            List<Interceptor> list = this.interceptors;
            if (list == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<okhttp3.Interceptor?>");
            }
            boolean bl3 = !list.contains(null);
            bl2 = false;
            boolean bl4 = false;
            if (!bl3) {
                boolean bl5 = false;
                String string = "Null interceptor: " + this.interceptors;
                throw (Throwable)new IllegalStateException(string.toString());
            }
            List<Interceptor> list2 = this.networkInterceptors;
            if (list2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<okhttp3.Interceptor?>");
            }
            bl3 = !list2.contains(null);
            bl2 = false;
            bl4 = false;
            if (!bl3) {
                boolean bl6 = false;
                String string = "Null network interceptor: " + this.networkInterceptors;
                throw (Throwable)new IllegalStateException(string.toString());
            }
            Iterable $this$none$iv = this.connectionSpecs;
            boolean $i$f$none = false;
            if ($this$none$iv instanceof Collection && ((Collection)$this$none$iv).isEmpty()) {
                bl = true;
            } else {
                for (Object element$iv2 : $this$none$iv) {
                    ConnectionSpec it = (ConnectionSpec)element$iv2;
                    boolean bl7 = false;
                    if (!it.isTls()) continue;
                    bl = false;
                    break block16;
                }
                bl = true;
            }
        }
        if (bl) {
            boolean bl8 = this.sslSocketFactoryOrNull == null;
            bl2 = false;
            boolean bl9 = false;
            bl9 = false;
            boolean element$iv2 = false;
            if (!bl8) {
                boolean bl10 = false;
                String element$iv2 = "Check failed.";
                throw (Throwable)new IllegalStateException(element$iv2.toString());
            }
            bl8 = this.certificateChainCleaner == null;
            bl2 = false;
            bl9 = false;
            bl9 = false;
            element$iv2 = false;
            if (!bl8) {
                boolean bl11 = false;
                String element$iv2 = "Check failed.";
                throw (Throwable)new IllegalStateException(element$iv2.toString());
            }
            bl8 = this.x509TrustManager == null;
            bl2 = false;
            bl9 = false;
            bl9 = false;
            element$iv2 = false;
            if (!bl8) {
                boolean bl12 = false;
                String element$iv2 = "Check failed.";
                throw (Throwable)new IllegalStateException(element$iv2.toString());
            }
            bl8 = Intrinsics.areEqual(this.certificatePinner, CertificatePinner.DEFAULT);
            bl2 = false;
            bl9 = false;
            bl9 = false;
            element$iv2 = false;
            if (!bl8) {
                boolean bl13 = false;
                String element$iv2 = "Check failed.";
                throw (Throwable)new IllegalStateException(element$iv2.toString());
            }
        } else {
            Object object = this.sslSocketFactoryOrNull;
            bl2 = false;
            boolean bl14 = false;
            if (object == null) {
                boolean bl15 = false;
                String string = "sslSocketFactory == null";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            object = this.certificateChainCleaner;
            bl2 = false;
            bl14 = false;
            if (object == null) {
                boolean bl16 = false;
                String string = "certificateChainCleaner == null";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            object = this.x509TrustManager;
            bl2 = false;
            bl14 = false;
            if (object == null) {
                boolean bl17 = false;
                String string = "x509TrustManager == null";
                throw (Throwable)new IllegalStateException(string.toString());
            }
        }
    }

    @Override
    @NotNull
    public Call newCall(@NotNull Request request) {
        Intrinsics.checkNotNullParameter(request, "request");
        return new RealCall(this, request, false);
    }

    @Override
    @NotNull
    public WebSocket newWebSocket(@NotNull Request request, @NotNull WebSocketListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        RealWebSocket webSocket = new RealWebSocket(TaskRunner.INSTANCE, request, listener, new Random(), this.pingIntervalMillis, null, this.minWebSocketMessageToCompress);
        webSocket.connect(this);
        return webSocket;
    }

    @NotNull
    public Builder newBuilder() {
        return new Builder(this);
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="dispatcher"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_dispatcher")
    @NotNull
    public final Dispatcher -deprecated_dispatcher() {
        return this.dispatcher;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="connectionPool"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_connectionPool")
    @NotNull
    public final ConnectionPool -deprecated_connectionPool() {
        return this.connectionPool;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="interceptors"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_interceptors")
    @NotNull
    public final List<Interceptor> -deprecated_interceptors() {
        return this.interceptors;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="networkInterceptors"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_networkInterceptors")
    @NotNull
    public final List<Interceptor> -deprecated_networkInterceptors() {
        return this.networkInterceptors;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="eventListenerFactory"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_eventListenerFactory")
    @NotNull
    public final EventListener.Factory -deprecated_eventListenerFactory() {
        return this.eventListenerFactory;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="retryOnConnectionFailure"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_retryOnConnectionFailure")
    public final boolean -deprecated_retryOnConnectionFailure() {
        return this.retryOnConnectionFailure;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="authenticator"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_authenticator")
    @NotNull
    public final Authenticator -deprecated_authenticator() {
        return this.authenticator;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="followRedirects"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_followRedirects")
    public final boolean -deprecated_followRedirects() {
        return this.followRedirects;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="followSslRedirects"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_followSslRedirects")
    public final boolean -deprecated_followSslRedirects() {
        return this.followSslRedirects;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="cookieJar"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_cookieJar")
    @NotNull
    public final CookieJar -deprecated_cookieJar() {
        return this.cookieJar;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="cache"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_cache")
    @Nullable
    public final Cache -deprecated_cache() {
        return this.cache;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="dns"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_dns")
    @NotNull
    public final Dns -deprecated_dns() {
        return this.dns;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="proxy"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_proxy")
    @Nullable
    public final Proxy -deprecated_proxy() {
        return this.proxy;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="proxySelector"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_proxySelector")
    @NotNull
    public final ProxySelector -deprecated_proxySelector() {
        return this.proxySelector;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="proxyAuthenticator"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_proxyAuthenticator")
    @NotNull
    public final Authenticator -deprecated_proxyAuthenticator() {
        return this.proxyAuthenticator;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="socketFactory"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_socketFactory")
    @NotNull
    public final SocketFactory -deprecated_socketFactory() {
        return this.socketFactory;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="sslSocketFactory"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_sslSocketFactory")
    @NotNull
    public final SSLSocketFactory -deprecated_sslSocketFactory() {
        return this.sslSocketFactory();
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="connectionSpecs"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_connectionSpecs")
    @NotNull
    public final List<ConnectionSpec> -deprecated_connectionSpecs() {
        return this.connectionSpecs;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="protocols"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_protocols")
    @NotNull
    public final List<Protocol> -deprecated_protocols() {
        return this.protocols;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="hostnameVerifier"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_hostnameVerifier")
    @NotNull
    public final HostnameVerifier -deprecated_hostnameVerifier() {
        return this.hostnameVerifier;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="certificatePinner"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_certificatePinner")
    @NotNull
    public final CertificatePinner -deprecated_certificatePinner() {
        return this.certificatePinner;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="callTimeoutMillis"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_callTimeoutMillis")
    public final int -deprecated_callTimeoutMillis() {
        return this.callTimeoutMillis;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="connectTimeoutMillis"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_connectTimeoutMillis")
    public final int -deprecated_connectTimeoutMillis() {
        return this.connectTimeoutMillis;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="readTimeoutMillis"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_readTimeoutMillis")
    public final int -deprecated_readTimeoutMillis() {
        return this.readTimeoutMillis;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="writeTimeoutMillis"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_writeTimeoutMillis")
    public final int -deprecated_writeTimeoutMillis() {
        return this.writeTimeoutMillis;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="pingIntervalMillis"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_pingIntervalMillis")
    public final int -deprecated_pingIntervalMillis() {
        return this.pingIntervalMillis;
    }

    public OkHttpClient(@NotNull Builder builder) {
        boolean bl;
        block12: {
            ProxySelector proxySelector;
            Intrinsics.checkNotNullParameter(builder, "builder");
            this.dispatcher = builder.getDispatcher$okhttp();
            this.connectionPool = builder.getConnectionPool$okhttp();
            this.interceptors = Util.toImmutableList(builder.getInterceptors$okhttp());
            this.networkInterceptors = Util.toImmutableList(builder.getNetworkInterceptors$okhttp());
            this.eventListenerFactory = builder.getEventListenerFactory$okhttp();
            this.retryOnConnectionFailure = builder.getRetryOnConnectionFailure$okhttp();
            this.authenticator = builder.getAuthenticator$okhttp();
            this.followRedirects = builder.getFollowRedirects$okhttp();
            this.followSslRedirects = builder.getFollowSslRedirects$okhttp();
            this.cookieJar = builder.getCookieJar$okhttp();
            this.cache = builder.getCache$okhttp();
            this.dns = builder.getDns$okhttp();
            this.proxy = builder.getProxy$okhttp();
            if (builder.getProxy$okhttp() != null) {
                proxySelector = NullProxySelector.INSTANCE;
            } else {
                ProxySelector proxySelector2 = builder.getProxySelector$okhttp();
                if (proxySelector2 == null) {
                    proxySelector2 = proxySelector = ProxySelector.getDefault();
                }
                if (proxySelector2 == null) {
                    proxySelector = NullProxySelector.INSTANCE;
                }
            }
            this.proxySelector = proxySelector;
            this.proxyAuthenticator = builder.getProxyAuthenticator$okhttp();
            this.socketFactory = builder.getSocketFactory$okhttp();
            this.connectionSpecs = builder.getConnectionSpecs$okhttp();
            this.protocols = builder.getProtocols$okhttp();
            this.hostnameVerifier = builder.getHostnameVerifier$okhttp();
            this.callTimeoutMillis = builder.getCallTimeout$okhttp();
            this.connectTimeoutMillis = builder.getConnectTimeout$okhttp();
            this.readTimeoutMillis = builder.getReadTimeout$okhttp();
            this.writeTimeoutMillis = builder.getWriteTimeout$okhttp();
            this.pingIntervalMillis = builder.getPingInterval$okhttp();
            this.minWebSocketMessageToCompress = builder.getMinWebSocketMessageToCompress$okhttp();
            RouteDatabase routeDatabase = builder.getRouteDatabase$okhttp();
            if (routeDatabase == null) {
                routeDatabase = new RouteDatabase();
            }
            this.routeDatabase = routeDatabase;
            Iterable $this$none$iv = this.connectionSpecs;
            boolean $i$f$none = false;
            if ($this$none$iv instanceof Collection && ((Collection)$this$none$iv).isEmpty()) {
                bl = true;
            } else {
                for (Object element$iv : $this$none$iv) {
                    ConnectionSpec it = (ConnectionSpec)element$iv;
                    boolean bl2 = false;
                    if (!it.isTls()) continue;
                    bl = false;
                    break block12;
                }
                bl = true;
            }
        }
        if (bl) {
            this.sslSocketFactoryOrNull = null;
            this.certificateChainCleaner = null;
            this.x509TrustManager = null;
            this.certificatePinner = CertificatePinner.DEFAULT;
        } else if (builder.getSslSocketFactoryOrNull$okhttp() != null) {
            this.sslSocketFactoryOrNull = builder.getSslSocketFactoryOrNull$okhttp();
            CertificateChainCleaner certificateChainCleaner = builder.getCertificateChainCleaner$okhttp();
            Intrinsics.checkNotNull(certificateChainCleaner);
            this.certificateChainCleaner = certificateChainCleaner;
            X509TrustManager x509TrustManager = builder.getX509TrustManagerOrNull$okhttp();
            Intrinsics.checkNotNull(x509TrustManager);
            this.x509TrustManager = x509TrustManager;
            CertificatePinner certificatePinner = builder.getCertificatePinner$okhttp();
            CertificateChainCleaner certificateChainCleaner2 = this.certificateChainCleaner;
            Intrinsics.checkNotNull(certificateChainCleaner2);
            this.certificatePinner = certificatePinner.withCertificateChainCleaner$okhttp(certificateChainCleaner2);
        } else {
            this.x509TrustManager = Platform.Companion.get().platformTrustManager();
            Platform platform = Platform.Companion.get();
            X509TrustManager x509TrustManager = this.x509TrustManager;
            Intrinsics.checkNotNull(x509TrustManager);
            this.sslSocketFactoryOrNull = platform.newSslSocketFactory(x509TrustManager);
            X509TrustManager x509TrustManager2 = this.x509TrustManager;
            Intrinsics.checkNotNull(x509TrustManager2);
            this.certificateChainCleaner = CertificateChainCleaner.Companion.get(x509TrustManager2);
            CertificatePinner certificatePinner = builder.getCertificatePinner$okhttp();
            CertificateChainCleaner certificateChainCleaner = this.certificateChainCleaner;
            Intrinsics.checkNotNull(certificateChainCleaner);
            this.certificatePinner = certificatePinner.withCertificateChainCleaner$okhttp(certificateChainCleaner);
        }
        this.verifyClientState();
    }

    public OkHttpClient() {
        this(new Builder());
    }

    static {
        Companion = new Companion(null);
        DEFAULT_PROTOCOLS = Util.immutableListOf(Protocol.HTTP_2, Protocol.HTTP_1_1);
        DEFAULT_CONNECTION_SPECS = Util.immutableListOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT);
    }

    @NotNull
    public Object clone() {
        return super.clone();
    }

    /*
     * Illegal identifiers - consider using --renameillegalidents true
     */
    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u00f8\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\u0005\u00a2\u0006\u0002\u0010\u0005J?\u0010\u009e\u0001\u001a\u00020\u00002*\b\u0004\u0010\u009f\u0001\u001a#\u0012\u0017\u0012\u00150\u00a1\u0001\u00a2\u0006\u000f\b\u00a2\u0001\u0012\n\b\u00a3\u0001\u0012\u0005\b\b(\u00a4\u0001\u0012\u0005\u0012\u00030\u00a5\u00010\u00a0\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0003\b\u00a6\u0001J\u0010\u0010\u009e\u0001\u001a\u00020\u00002\u0007\u0010\u00a7\u0001\u001a\u00020]J?\u0010\u00a8\u0001\u001a\u00020\u00002*\b\u0004\u0010\u009f\u0001\u001a#\u0012\u0017\u0012\u00150\u00a1\u0001\u00a2\u0006\u000f\b\u00a2\u0001\u0012\n\b\u00a3\u0001\u0012\u0005\b\b(\u00a4\u0001\u0012\u0005\u0012\u00030\u00a5\u00010\u00a0\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0003\b\u00a9\u0001J\u0010\u0010\u00a8\u0001\u001a\u00020\u00002\u0007\u0010\u00a7\u0001\u001a\u00020]J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007J\u0007\u0010\u00aa\u0001\u001a\u00020\u0003J\u0010\u0010\f\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u0012\u0010\u0012\u001a\u00020\u00002\b\u0010\u00ab\u0001\u001a\u00030\u00ac\u0001H\u0007J\u0019\u0010\u0012\u001a\u00020\u00002\u0007\u0010\u00ad\u0001\u001a\u00020`2\b\u0010\u00ae\u0001\u001a\u00030\u00af\u0001J\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001fJ\u0012\u0010$\u001a\u00020\u00002\b\u0010\u00ab\u0001\u001a\u00030\u00ac\u0001H\u0007J\u0019\u0010$\u001a\u00020\u00002\u0007\u0010\u00ad\u0001\u001a\u00020`2\b\u0010\u00ae\u0001\u001a\u00030\u00af\u0001J\u000e\u0010'\u001a\u00020\u00002\u0006\u0010'\u001a\u00020(J\u0014\u0010-\u001a\u00020\u00002\f\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.J\u000e\u00104\u001a\u00020\u00002\u0006\u00104\u001a\u000205J\u000e\u0010:\u001a\u00020\u00002\u0006\u0010:\u001a\u00020;J\u000e\u0010@\u001a\u00020\u00002\u0006\u0010@\u001a\u00020AJ\u0011\u0010\u00b0\u0001\u001a\u00020\u00002\b\u0010\u00b0\u0001\u001a\u00030\u00b1\u0001J\u000e\u0010F\u001a\u00020\u00002\u0006\u0010F\u001a\u00020GJ\u000e\u0010L\u001a\u00020\u00002\u0006\u0010L\u001a\u00020MJ\u000f\u0010R\u001a\u00020\u00002\u0007\u0010\u00b2\u0001\u001a\u00020MJ\u000e\u0010U\u001a\u00020\u00002\u0006\u0010U\u001a\u00020VJ\f\u0010[\u001a\b\u0012\u0004\u0012\u00020]0\\J\u000f\u0010_\u001a\u00020\u00002\u0007\u0010\u00b3\u0001\u001a\u00020`J\f\u0010e\u001a\b\u0012\u0004\u0012\u00020]0\\J\u0012\u0010g\u001a\u00020\u00002\b\u0010\u00ab\u0001\u001a\u00030\u00ac\u0001H\u0007J\u0019\u0010g\u001a\u00020\u00002\u0007\u0010\u00b4\u0001\u001a\u00020`2\b\u0010\u00ae\u0001\u001a\u00030\u00af\u0001J\u0014\u0010j\u001a\u00020\u00002\f\u0010j\u001a\b\u0012\u0004\u0012\u00020k0.J\u0010\u0010n\u001a\u00020\u00002\b\u0010n\u001a\u0004\u0018\u00010oJ\u000e\u0010t\u001a\u00020\u00002\u0006\u0010t\u001a\u00020\u0007J\u000e\u0010w\u001a\u00020\u00002\u0006\u0010w\u001a\u00020xJ\u0012\u0010}\u001a\u00020\u00002\b\u0010\u00ab\u0001\u001a\u00030\u00ac\u0001H\u0007J\u0019\u0010}\u001a\u00020\u00002\u0007\u0010\u00ad\u0001\u001a\u00020`2\b\u0010\u00ae\u0001\u001a\u00030\u00af\u0001J\u0010\u0010\u0080\u0001\u001a\u00020\u00002\u0007\u0010\u0080\u0001\u001a\u00020MJ\u0011\u0010\u0089\u0001\u001a\u00020\u00002\b\u0010\u0089\u0001\u001a\u00030\u008a\u0001J\u0013\u0010\u00b5\u0001\u001a\u00020\u00002\b\u0010\u00b5\u0001\u001a\u00030\u0090\u0001H\u0007J\u001b\u0010\u00b5\u0001\u001a\u00020\u00002\b\u0010\u00b5\u0001\u001a\u00030\u0090\u00012\b\u0010\u00b6\u0001\u001a\u00030\u0099\u0001J\u0013\u0010\u0095\u0001\u001a\u00020\u00002\b\u0010\u00ab\u0001\u001a\u00030\u00ac\u0001H\u0007J\u001a\u0010\u0095\u0001\u001a\u00020\u00002\u0007\u0010\u00ad\u0001\u001a\u00020`2\b\u0010\u00ae\u0001\u001a\u00030\u00af\u0001R\u001a\u0010\u0006\u001a\u00020\u0007X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001fX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\u0013X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0015\"\u0004\b&\u0010\u0017R\u001a\u0010'\u001a\u00020(X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R \u0010-\u001a\b\u0012\u0004\u0012\u00020/0.X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001a\u00104\u001a\u000205X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001a\u0010:\u001a\u00020;X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001a\u0010@\u001a\u00020AX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u001a\u0010F\u001a\u00020GX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\u001a\u0010L\u001a\u00020MX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\u001a\u0010R\u001a\u00020MX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bS\u0010O\"\u0004\bT\u0010QR\u001a\u0010U\u001a\u00020VX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR\u001a\u0010[\u001a\b\u0012\u0004\u0012\u00020]0\\X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b^\u00101R\u001a\u0010_\u001a\u00020`X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\ba\u0010b\"\u0004\bc\u0010dR\u001a\u0010e\u001a\b\u0012\u0004\u0012\u00020]0\\X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bf\u00101R\u001a\u0010g\u001a\u00020\u0013X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bh\u0010\u0015\"\u0004\bi\u0010\u0017R \u0010j\u001a\b\u0012\u0004\u0012\u00020k0.X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bl\u00101\"\u0004\bm\u00103R\u001c\u0010n\u001a\u0004\u0018\u00010oX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bp\u0010q\"\u0004\br\u0010sR\u001a\u0010t\u001a\u00020\u0007X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bu\u0010\t\"\u0004\bv\u0010\u000bR\u001c\u0010w\u001a\u0004\u0018\u00010xX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\by\u0010z\"\u0004\b{\u0010|R\u001a\u0010}\u001a\u00020\u0013X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b~\u0010\u0015\"\u0004\b\u007f\u0010\u0017R\u001d\u0010\u0080\u0001\u001a\u00020MX\u0080\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0081\u0001\u0010O\"\u0005\b\u0082\u0001\u0010QR\"\u0010\u0083\u0001\u001a\u0005\u0018\u00010\u0084\u0001X\u0080\u000e\u00a2\u0006\u0012\n\u0000\u001a\u0006\b\u0085\u0001\u0010\u0086\u0001\"\u0006\b\u0087\u0001\u0010\u0088\u0001R \u0010\u0089\u0001\u001a\u00030\u008a\u0001X\u0080\u000e\u00a2\u0006\u0012\n\u0000\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001\"\u0006\b\u008d\u0001\u0010\u008e\u0001R\"\u0010\u008f\u0001\u001a\u0005\u0018\u00010\u0090\u0001X\u0080\u000e\u00a2\u0006\u0012\n\u0000\u001a\u0006\b\u0091\u0001\u0010\u0092\u0001\"\u0006\b\u0093\u0001\u0010\u0094\u0001R\u001d\u0010\u0095\u0001\u001a\u00020\u0013X\u0080\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0096\u0001\u0010\u0015\"\u0005\b\u0097\u0001\u0010\u0017R\"\u0010\u0098\u0001\u001a\u0005\u0018\u00010\u0099\u0001X\u0080\u000e\u00a2\u0006\u0012\n\u0000\u001a\u0006\b\u009a\u0001\u0010\u009b\u0001\"\u0006\b\u009c\u0001\u0010\u009d\u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\u00b7\u0001"}, d2={"Lokhttp3/OkHttpClient$Builder;", "", "okHttpClient", "Lokhttp3/OkHttpClient;", "(Lokhttp3/OkHttpClient;)V", "()V", "authenticator", "Lokhttp3/Authenticator;", "getAuthenticator$okhttp", "()Lokhttp3/Authenticator;", "setAuthenticator$okhttp", "(Lokhttp3/Authenticator;)V", "cache", "Lokhttp3/Cache;", "getCache$okhttp", "()Lokhttp3/Cache;", "setCache$okhttp", "(Lokhttp3/Cache;)V", "callTimeout", "", "getCallTimeout$okhttp", "()I", "setCallTimeout$okhttp", "(I)V", "certificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "getCertificateChainCleaner$okhttp", "()Lokhttp3/internal/tls/CertificateChainCleaner;", "setCertificateChainCleaner$okhttp", "(Lokhttp3/internal/tls/CertificateChainCleaner;)V", "certificatePinner", "Lokhttp3/CertificatePinner;", "getCertificatePinner$okhttp", "()Lokhttp3/CertificatePinner;", "setCertificatePinner$okhttp", "(Lokhttp3/CertificatePinner;)V", "connectTimeout", "getConnectTimeout$okhttp", "setConnectTimeout$okhttp", "connectionPool", "Lokhttp3/ConnectionPool;", "getConnectionPool$okhttp", "()Lokhttp3/ConnectionPool;", "setConnectionPool$okhttp", "(Lokhttp3/ConnectionPool;)V", "connectionSpecs", "", "Lokhttp3/ConnectionSpec;", "getConnectionSpecs$okhttp", "()Ljava/util/List;", "setConnectionSpecs$okhttp", "(Ljava/util/List;)V", "cookieJar", "Lokhttp3/CookieJar;", "getCookieJar$okhttp", "()Lokhttp3/CookieJar;", "setCookieJar$okhttp", "(Lokhttp3/CookieJar;)V", "dispatcher", "Lokhttp3/Dispatcher;", "getDispatcher$okhttp", "()Lokhttp3/Dispatcher;", "setDispatcher$okhttp", "(Lokhttp3/Dispatcher;)V", "dns", "Lokhttp3/Dns;", "getDns$okhttp", "()Lokhttp3/Dns;", "setDns$okhttp", "(Lokhttp3/Dns;)V", "eventListenerFactory", "Lokhttp3/EventListener$Factory;", "getEventListenerFactory$okhttp", "()Lokhttp3/EventListener$Factory;", "setEventListenerFactory$okhttp", "(Lokhttp3/EventListener$Factory;)V", "followRedirects", "", "getFollowRedirects$okhttp", "()Z", "setFollowRedirects$okhttp", "(Z)V", "followSslRedirects", "getFollowSslRedirects$okhttp", "setFollowSslRedirects$okhttp", "hostnameVerifier", "Ljavax/net/ssl/HostnameVerifier;", "getHostnameVerifier$okhttp", "()Ljavax/net/ssl/HostnameVerifier;", "setHostnameVerifier$okhttp", "(Ljavax/net/ssl/HostnameVerifier;)V", "interceptors", "", "Lokhttp3/Interceptor;", "getInterceptors$okhttp", "minWebSocketMessageToCompress", "", "getMinWebSocketMessageToCompress$okhttp", "()J", "setMinWebSocketMessageToCompress$okhttp", "(J)V", "networkInterceptors", "getNetworkInterceptors$okhttp", "pingInterval", "getPingInterval$okhttp", "setPingInterval$okhttp", "protocols", "Lokhttp3/Protocol;", "getProtocols$okhttp", "setProtocols$okhttp", "proxy", "Ljava/net/Proxy;", "getProxy$okhttp", "()Ljava/net/Proxy;", "setProxy$okhttp", "(Ljava/net/Proxy;)V", "proxyAuthenticator", "getProxyAuthenticator$okhttp", "setProxyAuthenticator$okhttp", "proxySelector", "Ljava/net/ProxySelector;", "getProxySelector$okhttp", "()Ljava/net/ProxySelector;", "setProxySelector$okhttp", "(Ljava/net/ProxySelector;)V", "readTimeout", "getReadTimeout$okhttp", "setReadTimeout$okhttp", "retryOnConnectionFailure", "getRetryOnConnectionFailure$okhttp", "setRetryOnConnectionFailure$okhttp", "routeDatabase", "Lokhttp3/internal/connection/RouteDatabase;", "getRouteDatabase$okhttp", "()Lokhttp3/internal/connection/RouteDatabase;", "setRouteDatabase$okhttp", "(Lokhttp3/internal/connection/RouteDatabase;)V", "socketFactory", "Ljavax/net/SocketFactory;", "getSocketFactory$okhttp", "()Ljavax/net/SocketFactory;", "setSocketFactory$okhttp", "(Ljavax/net/SocketFactory;)V", "sslSocketFactoryOrNull", "Ljavax/net/ssl/SSLSocketFactory;", "getSslSocketFactoryOrNull$okhttp", "()Ljavax/net/ssl/SSLSocketFactory;", "setSslSocketFactoryOrNull$okhttp", "(Ljavax/net/ssl/SSLSocketFactory;)V", "writeTimeout", "getWriteTimeout$okhttp", "setWriteTimeout$okhttp", "x509TrustManagerOrNull", "Ljavax/net/ssl/X509TrustManager;", "getX509TrustManagerOrNull$okhttp", "()Ljavax/net/ssl/X509TrustManager;", "setX509TrustManagerOrNull$okhttp", "(Ljavax/net/ssl/X509TrustManager;)V", "addInterceptor", "block", "Lkotlin/Function1;", "Lokhttp3/Interceptor$Chain;", "Lkotlin/ParameterName;", "name", "chain", "Lokhttp3/Response;", "-addInterceptor", "interceptor", "addNetworkInterceptor", "-addNetworkInterceptor", "build", "duration", "Ljava/time/Duration;", "timeout", "unit", "Ljava/util/concurrent/TimeUnit;", "eventListener", "Lokhttp3/EventListener;", "followProtocolRedirects", "bytes", "interval", "sslSocketFactory", "trustManager", "okhttp"})
    public static final class Builder {
        @NotNull
        private Dispatcher dispatcher;
        @NotNull
        private ConnectionPool connectionPool;
        @NotNull
        private final List<Interceptor> interceptors;
        @NotNull
        private final List<Interceptor> networkInterceptors;
        @NotNull
        private EventListener.Factory eventListenerFactory;
        private boolean retryOnConnectionFailure;
        @NotNull
        private Authenticator authenticator;
        private boolean followRedirects;
        private boolean followSslRedirects;
        @NotNull
        private CookieJar cookieJar;
        @Nullable
        private Cache cache;
        @NotNull
        private Dns dns;
        @Nullable
        private Proxy proxy;
        @Nullable
        private ProxySelector proxySelector;
        @NotNull
        private Authenticator proxyAuthenticator;
        @NotNull
        private SocketFactory socketFactory;
        @Nullable
        private SSLSocketFactory sslSocketFactoryOrNull;
        @Nullable
        private X509TrustManager x509TrustManagerOrNull;
        @NotNull
        private List<ConnectionSpec> connectionSpecs;
        @NotNull
        private List<? extends Protocol> protocols;
        @NotNull
        private HostnameVerifier hostnameVerifier;
        @NotNull
        private CertificatePinner certificatePinner;
        @Nullable
        private CertificateChainCleaner certificateChainCleaner;
        private int callTimeout;
        private int connectTimeout;
        private int readTimeout;
        private int writeTimeout;
        private int pingInterval;
        private long minWebSocketMessageToCompress;
        @Nullable
        private RouteDatabase routeDatabase;

        @NotNull
        public final Dispatcher getDispatcher$okhttp() {
            return this.dispatcher;
        }

        public final void setDispatcher$okhttp(@NotNull Dispatcher dispatcher) {
            Intrinsics.checkNotNullParameter(dispatcher, "<set-?>");
            this.dispatcher = dispatcher;
        }

        @NotNull
        public final ConnectionPool getConnectionPool$okhttp() {
            return this.connectionPool;
        }

        public final void setConnectionPool$okhttp(@NotNull ConnectionPool connectionPool) {
            Intrinsics.checkNotNullParameter(connectionPool, "<set-?>");
            this.connectionPool = connectionPool;
        }

        @NotNull
        public final List<Interceptor> getInterceptors$okhttp() {
            return this.interceptors;
        }

        @NotNull
        public final List<Interceptor> getNetworkInterceptors$okhttp() {
            return this.networkInterceptors;
        }

        @NotNull
        public final EventListener.Factory getEventListenerFactory$okhttp() {
            return this.eventListenerFactory;
        }

        public final void setEventListenerFactory$okhttp(@NotNull EventListener.Factory factory2) {
            Intrinsics.checkNotNullParameter(factory2, "<set-?>");
            this.eventListenerFactory = factory2;
        }

        public final boolean getRetryOnConnectionFailure$okhttp() {
            return this.retryOnConnectionFailure;
        }

        public final void setRetryOnConnectionFailure$okhttp(boolean bl) {
            this.retryOnConnectionFailure = bl;
        }

        @NotNull
        public final Authenticator getAuthenticator$okhttp() {
            return this.authenticator;
        }

        public final void setAuthenticator$okhttp(@NotNull Authenticator authenticator) {
            Intrinsics.checkNotNullParameter(authenticator, "<set-?>");
            this.authenticator = authenticator;
        }

        public final boolean getFollowRedirects$okhttp() {
            return this.followRedirects;
        }

        public final void setFollowRedirects$okhttp(boolean bl) {
            this.followRedirects = bl;
        }

        public final boolean getFollowSslRedirects$okhttp() {
            return this.followSslRedirects;
        }

        public final void setFollowSslRedirects$okhttp(boolean bl) {
            this.followSslRedirects = bl;
        }

        @NotNull
        public final CookieJar getCookieJar$okhttp() {
            return this.cookieJar;
        }

        public final void setCookieJar$okhttp(@NotNull CookieJar cookieJar) {
            Intrinsics.checkNotNullParameter(cookieJar, "<set-?>");
            this.cookieJar = cookieJar;
        }

        @Nullable
        public final Cache getCache$okhttp() {
            return this.cache;
        }

        public final void setCache$okhttp(@Nullable Cache cache) {
            this.cache = cache;
        }

        @NotNull
        public final Dns getDns$okhttp() {
            return this.dns;
        }

        public final void setDns$okhttp(@NotNull Dns dns) {
            Intrinsics.checkNotNullParameter(dns, "<set-?>");
            this.dns = dns;
        }

        @Nullable
        public final Proxy getProxy$okhttp() {
            return this.proxy;
        }

        public final void setProxy$okhttp(@Nullable Proxy proxy) {
            this.proxy = proxy;
        }

        @Nullable
        public final ProxySelector getProxySelector$okhttp() {
            return this.proxySelector;
        }

        public final void setProxySelector$okhttp(@Nullable ProxySelector proxySelector) {
            this.proxySelector = proxySelector;
        }

        @NotNull
        public final Authenticator getProxyAuthenticator$okhttp() {
            return this.proxyAuthenticator;
        }

        public final void setProxyAuthenticator$okhttp(@NotNull Authenticator authenticator) {
            Intrinsics.checkNotNullParameter(authenticator, "<set-?>");
            this.proxyAuthenticator = authenticator;
        }

        @NotNull
        public final SocketFactory getSocketFactory$okhttp() {
            return this.socketFactory;
        }

        public final void setSocketFactory$okhttp(@NotNull SocketFactory socketFactory) {
            Intrinsics.checkNotNullParameter(socketFactory, "<set-?>");
            this.socketFactory = socketFactory;
        }

        @Nullable
        public final SSLSocketFactory getSslSocketFactoryOrNull$okhttp() {
            return this.sslSocketFactoryOrNull;
        }

        public final void setSslSocketFactoryOrNull$okhttp(@Nullable SSLSocketFactory sSLSocketFactory) {
            this.sslSocketFactoryOrNull = sSLSocketFactory;
        }

        @Nullable
        public final X509TrustManager getX509TrustManagerOrNull$okhttp() {
            return this.x509TrustManagerOrNull;
        }

        public final void setX509TrustManagerOrNull$okhttp(@Nullable X509TrustManager x509TrustManager) {
            this.x509TrustManagerOrNull = x509TrustManager;
        }

        @NotNull
        public final List<ConnectionSpec> getConnectionSpecs$okhttp() {
            return this.connectionSpecs;
        }

        public final void setConnectionSpecs$okhttp(@NotNull List<ConnectionSpec> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.connectionSpecs = list;
        }

        @NotNull
        public final List<Protocol> getProtocols$okhttp() {
            return this.protocols;
        }

        public final void setProtocols$okhttp(@NotNull List<? extends Protocol> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.protocols = list;
        }

        @NotNull
        public final HostnameVerifier getHostnameVerifier$okhttp() {
            return this.hostnameVerifier;
        }

        public final void setHostnameVerifier$okhttp(@NotNull HostnameVerifier hostnameVerifier) {
            Intrinsics.checkNotNullParameter(hostnameVerifier, "<set-?>");
            this.hostnameVerifier = hostnameVerifier;
        }

        @NotNull
        public final CertificatePinner getCertificatePinner$okhttp() {
            return this.certificatePinner;
        }

        public final void setCertificatePinner$okhttp(@NotNull CertificatePinner certificatePinner) {
            Intrinsics.checkNotNullParameter(certificatePinner, "<set-?>");
            this.certificatePinner = certificatePinner;
        }

        @Nullable
        public final CertificateChainCleaner getCertificateChainCleaner$okhttp() {
            return this.certificateChainCleaner;
        }

        public final void setCertificateChainCleaner$okhttp(@Nullable CertificateChainCleaner certificateChainCleaner) {
            this.certificateChainCleaner = certificateChainCleaner;
        }

        public final int getCallTimeout$okhttp() {
            return this.callTimeout;
        }

        public final void setCallTimeout$okhttp(int n) {
            this.callTimeout = n;
        }

        public final int getConnectTimeout$okhttp() {
            return this.connectTimeout;
        }

        public final void setConnectTimeout$okhttp(int n) {
            this.connectTimeout = n;
        }

        public final int getReadTimeout$okhttp() {
            return this.readTimeout;
        }

        public final void setReadTimeout$okhttp(int n) {
            this.readTimeout = n;
        }

        public final int getWriteTimeout$okhttp() {
            return this.writeTimeout;
        }

        public final void setWriteTimeout$okhttp(int n) {
            this.writeTimeout = n;
        }

        public final int getPingInterval$okhttp() {
            return this.pingInterval;
        }

        public final void setPingInterval$okhttp(int n) {
            this.pingInterval = n;
        }

        public final long getMinWebSocketMessageToCompress$okhttp() {
            return this.minWebSocketMessageToCompress;
        }

        public final void setMinWebSocketMessageToCompress$okhttp(long l) {
            this.minWebSocketMessageToCompress = l;
        }

        @Nullable
        public final RouteDatabase getRouteDatabase$okhttp() {
            return this.routeDatabase;
        }

        public final void setRouteDatabase$okhttp(@Nullable RouteDatabase routeDatabase) {
            this.routeDatabase = routeDatabase;
        }

        @NotNull
        public final Builder dispatcher(@NotNull Dispatcher dispatcher) {
            Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.dispatcher = dispatcher;
            return builder;
        }

        @NotNull
        public final Builder connectionPool(@NotNull ConnectionPool connectionPool) {
            Intrinsics.checkNotNullParameter(connectionPool, "connectionPool");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.connectionPool = connectionPool;
            return builder;
        }

        @NotNull
        public final List<Interceptor> interceptors() {
            return this.interceptors;
        }

        @NotNull
        public final Builder addInterceptor(@NotNull Interceptor interceptor) {
            Intrinsics.checkNotNullParameter(interceptor, "interceptor");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            Collection collection = $this$apply.interceptors;
            Interceptor interceptor2 = interceptor;
            boolean bl4 = false;
            collection.add(interceptor2);
            return builder;
        }

        @JvmName(name="-addInterceptor")
        @NotNull
        public final Builder -addInterceptor(@NotNull Function1<? super Interceptor.Chain, Response> block) {
            int n = 0;
            Intrinsics.checkNotNullParameter(block, "block");
            return this.addInterceptor(new Interceptor(block){
                final /* synthetic */ Function1 $block;

                @NotNull
                public final Response intercept(@NotNull Interceptor.Chain chain) {
                    Intrinsics.checkNotNullParameter(chain, "chain");
                    return (Response)this.$block.invoke(chain);
                }
                {
                    this.$block = function1;
                }
            });
        }

        @NotNull
        public final List<Interceptor> networkInterceptors() {
            return this.networkInterceptors;
        }

        @NotNull
        public final Builder addNetworkInterceptor(@NotNull Interceptor interceptor) {
            Intrinsics.checkNotNullParameter(interceptor, "interceptor");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            Collection collection = $this$apply.networkInterceptors;
            Interceptor interceptor2 = interceptor;
            boolean bl4 = false;
            collection.add(interceptor2);
            return builder;
        }

        @JvmName(name="-addNetworkInterceptor")
        @NotNull
        public final Builder -addNetworkInterceptor(@NotNull Function1<? super Interceptor.Chain, Response> block) {
            int n = 0;
            Intrinsics.checkNotNullParameter(block, "block");
            return this.addNetworkInterceptor(new Interceptor(block){
                final /* synthetic */ Function1 $block;

                @NotNull
                public final Response intercept(@NotNull Interceptor.Chain chain) {
                    Intrinsics.checkNotNullParameter(chain, "chain");
                    return (Response)this.$block.invoke(chain);
                }
                {
                    this.$block = function1;
                }
            });
        }

        @NotNull
        public final Builder eventListener(@NotNull EventListener eventListener) {
            Intrinsics.checkNotNullParameter(eventListener, "eventListener");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.eventListenerFactory = Util.asFactory(eventListener);
            return builder;
        }

        @NotNull
        public final Builder eventListenerFactory(@NotNull EventListener.Factory eventListenerFactory) {
            Intrinsics.checkNotNullParameter(eventListenerFactory, "eventListenerFactory");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.eventListenerFactory = eventListenerFactory;
            return builder;
        }

        @NotNull
        public final Builder retryOnConnectionFailure(boolean retryOnConnectionFailure) {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.retryOnConnectionFailure = retryOnConnectionFailure;
            return builder;
        }

        @NotNull
        public final Builder authenticator(@NotNull Authenticator authenticator) {
            Intrinsics.checkNotNullParameter(authenticator, "authenticator");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.authenticator = authenticator;
            return builder;
        }

        @NotNull
        public final Builder followRedirects(boolean followRedirects) {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.followRedirects = followRedirects;
            return builder;
        }

        @NotNull
        public final Builder followSslRedirects(boolean followProtocolRedirects) {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.followSslRedirects = followProtocolRedirects;
            return builder;
        }

        @NotNull
        public final Builder cookieJar(@NotNull CookieJar cookieJar) {
            Intrinsics.checkNotNullParameter(cookieJar, "cookieJar");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.cookieJar = cookieJar;
            return builder;
        }

        @NotNull
        public final Builder cache(@Nullable Cache cache) {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.cache = cache;
            return builder;
        }

        @NotNull
        public final Builder dns(@NotNull Dns dns) {
            Intrinsics.checkNotNullParameter(dns, "dns");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            if (Intrinsics.areEqual(dns, $this$apply.dns) ^ true) {
                $this$apply.routeDatabase = null;
            }
            $this$apply.dns = dns;
            return builder;
        }

        @NotNull
        public final Builder proxy(@Nullable Proxy proxy) {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            if (Intrinsics.areEqual(proxy, $this$apply.proxy) ^ true) {
                $this$apply.routeDatabase = null;
            }
            $this$apply.proxy = proxy;
            return builder;
        }

        @NotNull
        public final Builder proxySelector(@NotNull ProxySelector proxySelector) {
            Intrinsics.checkNotNullParameter(proxySelector, "proxySelector");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            if (Intrinsics.areEqual(proxySelector, $this$apply.proxySelector) ^ true) {
                $this$apply.routeDatabase = null;
            }
            $this$apply.proxySelector = proxySelector;
            return builder;
        }

        @NotNull
        public final Builder proxyAuthenticator(@NotNull Authenticator proxyAuthenticator) {
            Intrinsics.checkNotNullParameter(proxyAuthenticator, "proxyAuthenticator");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            if (Intrinsics.areEqual(proxyAuthenticator, $this$apply.proxyAuthenticator) ^ true) {
                $this$apply.routeDatabase = null;
            }
            $this$apply.proxyAuthenticator = proxyAuthenticator;
            return builder;
        }

        @NotNull
        public final Builder socketFactory(@NotNull SocketFactory socketFactory) {
            Intrinsics.checkNotNullParameter(socketFactory, "socketFactory");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            boolean bl4 = !(socketFactory instanceof SSLSocketFactory);
            boolean bl5 = false;
            boolean bl6 = false;
            if (!bl4) {
                boolean bl7 = false;
                String string = "socketFactory instanceof SSLSocketFactory";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            if (Intrinsics.areEqual(socketFactory, $this$apply.socketFactory) ^ true) {
                $this$apply.routeDatabase = null;
            }
            $this$apply.socketFactory = socketFactory;
            return builder;
        }

        @Deprecated(message="Use the sslSocketFactory overload that accepts a X509TrustManager.", level=DeprecationLevel.ERROR)
        @NotNull
        public final Builder sslSocketFactory(@NotNull SSLSocketFactory sslSocketFactory) {
            Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            if (Intrinsics.areEqual(sslSocketFactory, $this$apply.sslSocketFactoryOrNull) ^ true) {
                $this$apply.routeDatabase = null;
            }
            $this$apply.sslSocketFactoryOrNull = sslSocketFactory;
            X509TrustManager x509TrustManager = Platform.Companion.get().trustManager(sslSocketFactory);
            if (x509TrustManager == null) {
                throw (Throwable)new IllegalStateException("Unable to extract the trust manager on " + Platform.Companion.get() + ", " + "sslSocketFactory is " + sslSocketFactory.getClass());
            }
            $this$apply.x509TrustManagerOrNull = x509TrustManager;
            Platform platform = Platform.Companion.get();
            X509TrustManager x509TrustManager2 = $this$apply.x509TrustManagerOrNull;
            Intrinsics.checkNotNull(x509TrustManager2);
            $this$apply.certificateChainCleaner = platform.buildCertificateChainCleaner(x509TrustManager2);
            return builder;
        }

        @NotNull
        public final Builder sslSocketFactory(@NotNull SSLSocketFactory sslSocketFactory, @NotNull X509TrustManager trustManager) {
            Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
            Intrinsics.checkNotNullParameter(trustManager, "trustManager");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            if (Intrinsics.areEqual(sslSocketFactory, $this$apply.sslSocketFactoryOrNull) ^ true || Intrinsics.areEqual(trustManager, $this$apply.x509TrustManagerOrNull) ^ true) {
                $this$apply.routeDatabase = null;
            }
            $this$apply.sslSocketFactoryOrNull = sslSocketFactory;
            $this$apply.certificateChainCleaner = CertificateChainCleaner.Companion.get(trustManager);
            $this$apply.x509TrustManagerOrNull = trustManager;
            return builder;
        }

        @NotNull
        public final Builder connectionSpecs(@NotNull List<ConnectionSpec> connectionSpecs) {
            Intrinsics.checkNotNullParameter(connectionSpecs, "connectionSpecs");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            if (Intrinsics.areEqual(connectionSpecs, $this$apply.connectionSpecs) ^ true) {
                $this$apply.routeDatabase = null;
            }
            $this$apply.connectionSpecs = Util.toImmutableList(connectionSpecs);
            return builder;
        }

        @NotNull
        public final Builder protocols(@NotNull List<? extends Protocol> protocols) {
            Intrinsics.checkNotNullParameter(protocols, "protocols");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            List protocolsCopy = CollectionsKt.toMutableList((Collection)protocols);
            boolean bl4 = protocolsCopy.contains((Object)Protocol.H2_PRIOR_KNOWLEDGE) || protocolsCopy.contains((Object)Protocol.HTTP_1_1);
            boolean bl5 = false;
            boolean bl6 = false;
            if (!bl4) {
                boolean bl7 = false;
                String string = "protocols must contain h2_prior_knowledge or http/1.1: " + protocolsCopy;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            bl4 = !protocolsCopy.contains((Object)Protocol.H2_PRIOR_KNOWLEDGE) || protocolsCopy.size() <= 1;
            bl5 = false;
            bl6 = false;
            if (!bl4) {
                boolean bl8 = false;
                String string = "protocols containing h2_prior_knowledge cannot use other protocols: " + protocolsCopy;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            bl4 = !protocolsCopy.contains((Object)Protocol.HTTP_1_0);
            bl5 = false;
            bl6 = false;
            if (!bl4) {
                boolean bl9 = false;
                String string = "protocols must not contain http/1.0: " + protocolsCopy;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            List list = protocolsCopy;
            if (list == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<okhttp3.Protocol?>");
            }
            bl4 = !list.contains(null);
            bl5 = false;
            bl6 = false;
            if (!bl4) {
                boolean bl10 = false;
                String string = "protocols must not contain null";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            protocolsCopy.remove((Object)Protocol.SPDY_3);
            if (Intrinsics.areEqual(protocolsCopy, $this$apply.protocols) ^ true) {
                $this$apply.routeDatabase = null;
            }
            List list2 = Collections.unmodifiableList(protocolsCopy);
            Intrinsics.checkNotNullExpressionValue(list2, "Collections.unmodifiableList(protocolsCopy)");
            $this$apply.protocols = list2;
            return builder;
        }

        @NotNull
        public final Builder hostnameVerifier(@NotNull HostnameVerifier hostnameVerifier) {
            Intrinsics.checkNotNullParameter(hostnameVerifier, "hostnameVerifier");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            if (Intrinsics.areEqual(hostnameVerifier, $this$apply.hostnameVerifier) ^ true) {
                $this$apply.routeDatabase = null;
            }
            $this$apply.hostnameVerifier = hostnameVerifier;
            return builder;
        }

        @NotNull
        public final Builder certificatePinner(@NotNull CertificatePinner certificatePinner) {
            Intrinsics.checkNotNullParameter(certificatePinner, "certificatePinner");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            if (Intrinsics.areEqual(certificatePinner, $this$apply.certificatePinner) ^ true) {
                $this$apply.routeDatabase = null;
            }
            $this$apply.certificatePinner = certificatePinner;
            return builder;
        }

        @NotNull
        public final Builder callTimeout(long timeout2, @NotNull TimeUnit unit) {
            Intrinsics.checkNotNullParameter((Object)unit, "unit");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.callTimeout = Util.checkDuration("timeout", timeout2, unit);
            return builder;
        }

        @IgnoreJRERequirement
        @NotNull
        public final Builder callTimeout(@NotNull Duration duration) {
            Intrinsics.checkNotNullParameter(duration, "duration");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.callTimeout(duration.toMillis(), TimeUnit.MILLISECONDS);
            return builder;
        }

        @NotNull
        public final Builder connectTimeout(long timeout2, @NotNull TimeUnit unit) {
            Intrinsics.checkNotNullParameter((Object)unit, "unit");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.connectTimeout = Util.checkDuration("timeout", timeout2, unit);
            return builder;
        }

        @IgnoreJRERequirement
        @NotNull
        public final Builder connectTimeout(@NotNull Duration duration) {
            Intrinsics.checkNotNullParameter(duration, "duration");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.connectTimeout(duration.toMillis(), TimeUnit.MILLISECONDS);
            return builder;
        }

        @NotNull
        public final Builder readTimeout(long timeout2, @NotNull TimeUnit unit) {
            Intrinsics.checkNotNullParameter((Object)unit, "unit");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.readTimeout = Util.checkDuration("timeout", timeout2, unit);
            return builder;
        }

        @IgnoreJRERequirement
        @NotNull
        public final Builder readTimeout(@NotNull Duration duration) {
            Intrinsics.checkNotNullParameter(duration, "duration");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.readTimeout(duration.toMillis(), TimeUnit.MILLISECONDS);
            return builder;
        }

        @NotNull
        public final Builder writeTimeout(long timeout2, @NotNull TimeUnit unit) {
            Intrinsics.checkNotNullParameter((Object)unit, "unit");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.writeTimeout = Util.checkDuration("timeout", timeout2, unit);
            return builder;
        }

        @IgnoreJRERequirement
        @NotNull
        public final Builder writeTimeout(@NotNull Duration duration) {
            Intrinsics.checkNotNullParameter(duration, "duration");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.writeTimeout(duration.toMillis(), TimeUnit.MILLISECONDS);
            return builder;
        }

        @NotNull
        public final Builder pingInterval(long interval, @NotNull TimeUnit unit) {
            Intrinsics.checkNotNullParameter((Object)unit, "unit");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.pingInterval = Util.checkDuration("interval", interval, unit);
            return builder;
        }

        @IgnoreJRERequirement
        @NotNull
        public final Builder pingInterval(@NotNull Duration duration) {
            Intrinsics.checkNotNullParameter(duration, "duration");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.pingInterval(duration.toMillis(), TimeUnit.MILLISECONDS);
            return builder;
        }

        @NotNull
        public final Builder minWebSocketMessageToCompress(long bytes) {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            boolean bl4 = bytes >= 0L;
            boolean bl5 = false;
            boolean bl6 = false;
            if (!bl4) {
                boolean bl7 = false;
                String string = "minWebSocketMessageToCompress must be positive: " + bytes;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            $this$apply.minWebSocketMessageToCompress = bytes;
            return builder;
        }

        @NotNull
        public final OkHttpClient build() {
            return new OkHttpClient(this);
        }

        public Builder() {
            this.dispatcher = new Dispatcher();
            this.connectionPool = new ConnectionPool();
            boolean bl = false;
            this.interceptors = new ArrayList();
            bl = false;
            this.networkInterceptors = new ArrayList();
            this.eventListenerFactory = Util.asFactory(EventListener.NONE);
            this.retryOnConnectionFailure = true;
            this.authenticator = Authenticator.NONE;
            this.followRedirects = true;
            this.followSslRedirects = true;
            this.cookieJar = CookieJar.NO_COOKIES;
            this.dns = Dns.SYSTEM;
            this.proxyAuthenticator = Authenticator.NONE;
            SocketFactory socketFactory = SocketFactory.getDefault();
            Intrinsics.checkNotNullExpressionValue(socketFactory, "SocketFactory.getDefault()");
            this.socketFactory = socketFactory;
            this.connectionSpecs = Companion.getDEFAULT_CONNECTION_SPECS$okhttp();
            this.protocols = Companion.getDEFAULT_PROTOCOLS$okhttp();
            this.hostnameVerifier = OkHostnameVerifier.INSTANCE;
            this.certificatePinner = CertificatePinner.DEFAULT;
            this.connectTimeout = 10000;
            this.readTimeout = 10000;
            this.writeTimeout = 10000;
            this.minWebSocketMessageToCompress = 1024L;
        }

        public Builder(@NotNull OkHttpClient okHttpClient) {
            Intrinsics.checkNotNullParameter(okHttpClient, "okHttpClient");
            this();
            this.dispatcher = okHttpClient.dispatcher();
            this.connectionPool = okHttpClient.connectionPool();
            Collection collection = this.interceptors;
            Iterable iterable = okHttpClient.interceptors();
            boolean bl = false;
            CollectionsKt.addAll(collection, iterable);
            collection = this.networkInterceptors;
            iterable = okHttpClient.networkInterceptors();
            bl = false;
            CollectionsKt.addAll(collection, iterable);
            this.eventListenerFactory = okHttpClient.eventListenerFactory();
            this.retryOnConnectionFailure = okHttpClient.retryOnConnectionFailure();
            this.authenticator = okHttpClient.authenticator();
            this.followRedirects = okHttpClient.followRedirects();
            this.followSslRedirects = okHttpClient.followSslRedirects();
            this.cookieJar = okHttpClient.cookieJar();
            this.cache = okHttpClient.cache();
            this.dns = okHttpClient.dns();
            this.proxy = okHttpClient.proxy();
            this.proxySelector = okHttpClient.proxySelector();
            this.proxyAuthenticator = okHttpClient.proxyAuthenticator();
            this.socketFactory = okHttpClient.socketFactory();
            this.sslSocketFactoryOrNull = okHttpClient.sslSocketFactoryOrNull;
            this.x509TrustManagerOrNull = okHttpClient.x509TrustManager();
            this.connectionSpecs = okHttpClient.connectionSpecs();
            this.protocols = okHttpClient.protocols();
            this.hostnameVerifier = okHttpClient.hostnameVerifier();
            this.certificatePinner = okHttpClient.certificatePinner();
            this.certificateChainCleaner = okHttpClient.certificateChainCleaner();
            this.callTimeout = okHttpClient.callTimeoutMillis();
            this.connectTimeout = okHttpClient.connectTimeoutMillis();
            this.readTimeout = okHttpClient.readTimeoutMillis();
            this.writeTimeout = okHttpClient.writeTimeoutMillis();
            this.pingInterval = okHttpClient.pingIntervalMillis();
            this.minWebSocketMessageToCompress = okHttpClient.minWebSocketMessageToCompress();
            this.routeDatabase = okHttpClient.getRouteDatabase();
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007\u00a8\u0006\u000b"}, d2={"Lokhttp3/OkHttpClient$Companion;", "", "()V", "DEFAULT_CONNECTION_SPECS", "", "Lokhttp3/ConnectionSpec;", "getDEFAULT_CONNECTION_SPECS$okhttp", "()Ljava/util/List;", "DEFAULT_PROTOCOLS", "Lokhttp3/Protocol;", "getDEFAULT_PROTOCOLS$okhttp", "okhttp"})
    public static final class Companion {
        @NotNull
        public final List<Protocol> getDEFAULT_PROTOCOLS$okhttp() {
            return DEFAULT_PROTOCOLS;
        }

        @NotNull
        public final List<ConnectionSpec> getDEFAULT_CONNECTION_SPECS$okhttp() {
            return DEFAULT_CONNECTION_SPECS;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

