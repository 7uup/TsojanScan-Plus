/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.connection;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RealConnectionPool;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.connection.RouteSelector;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ0\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020$H\u0002J8\u0010%\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020$2\u0006\u0010&\u001a\u00020$H\u0002J\u0006\u0010'\u001a\u00020$J\n\u0010(\u001a\u0004\u0018\u00010\u0010H\u0002J\u000e\u0010)\u001a\u00020$2\u0006\u0010*\u001a\u00020+J\u000e\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2={"Lokhttp3/internal/connection/ExchangeFinder;", "", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "address", "Lokhttp3/Address;", "call", "Lokhttp3/internal/connection/RealCall;", "eventListener", "Lokhttp3/EventListener;", "(Lokhttp3/internal/connection/RealConnectionPool;Lokhttp3/Address;Lokhttp3/internal/connection/RealCall;Lokhttp3/EventListener;)V", "getAddress$okhttp", "()Lokhttp3/Address;", "connectionShutdownCount", "", "nextRouteToTry", "Lokhttp3/Route;", "otherFailureCount", "refusedStreamCount", "routeSelection", "Lokhttp3/internal/connection/RouteSelector$Selection;", "routeSelector", "Lokhttp3/internal/connection/RouteSelector;", "find", "Lokhttp3/internal/http/ExchangeCodec;", "client", "Lokhttp3/OkHttpClient;", "chain", "Lokhttp3/internal/http/RealInterceptorChain;", "findConnection", "Lokhttp3/internal/connection/RealConnection;", "connectTimeout", "readTimeout", "writeTimeout", "pingIntervalMillis", "connectionRetryEnabled", "", "findHealthyConnection", "doExtensiveHealthChecks", "retryAfterFailure", "retryRoute", "sameHostAndPort", "url", "Lokhttp3/HttpUrl;", "trackFailure", "", "e", "Ljava/io/IOException;", "okhttp"})
public final class ExchangeFinder {
    private RouteSelector.Selection routeSelection;
    private RouteSelector routeSelector;
    private int refusedStreamCount;
    private int connectionShutdownCount;
    private int otherFailureCount;
    private Route nextRouteToTry;
    private final RealConnectionPool connectionPool;
    @NotNull
    private final Address address;
    private final RealCall call;
    private final EventListener eventListener;

    @NotNull
    public final ExchangeCodec find(@NotNull OkHttpClient client, @NotNull RealInterceptorChain chain) {
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(chain, "chain");
        try {
            RealConnection resultConnection = this.findHealthyConnection(chain.getConnectTimeoutMillis$okhttp(), chain.getReadTimeoutMillis$okhttp(), chain.getWriteTimeoutMillis$okhttp(), client.pingIntervalMillis(), client.retryOnConnectionFailure(), Intrinsics.areEqual(chain.getRequest$okhttp().method(), "GET") ^ true);
            return resultConnection.newCodec$okhttp(client, chain);
        } catch (RouteException e) {
            this.trackFailure(e.getLastConnectException());
            throw (Throwable)e;
        } catch (IOException e) {
            this.trackFailure(e);
            throw (Throwable)new RouteException(e);
        }
    }

    private final RealConnection findHealthyConnection(int connectTimeout, int readTimeout, int writeTimeout, int pingIntervalMillis, boolean connectionRetryEnabled, boolean doExtensiveHealthChecks) throws IOException {
        while (true) {
            boolean routesSelectionLeft;
            boolean routesLeft;
            RealConnection candidate;
            if ((candidate = this.findConnection(connectTimeout, readTimeout, writeTimeout, pingIntervalMillis, connectionRetryEnabled)).isHealthy(doExtensiveHealthChecks)) {
                return candidate;
            }
            candidate.noNewExchanges$okhttp();
            if (this.nextRouteToTry != null) continue;
            RouteSelector.Selection selection = this.routeSelection;
            boolean bl = routesLeft = selection != null ? selection.hasNext() : true;
            if (routesLeft) continue;
            RouteSelector routeSelector = this.routeSelector;
            boolean bl2 = routesSelectionLeft = routeSelector != null ? routeSelector.hasNext() : true;
            if (!routesSelectionLeft) break;
        }
        throw (Throwable)new IOException("exhausted all routes");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Unable to fully structure code
     */
    private final RealConnection findConnection(int connectTimeout, int readTimeout, int writeTimeout, int pingIntervalMillis, boolean connectionRetryEnabled) throws IOException {
        block24: {
            block23: {
                if (this.call.isCanceled()) {
                    throw (Throwable)new IOException("Canceled");
                }
                callConnection = this.call.getConnection();
                if (callConnection != null) {
                    toClose = null;
                    var8_8 = false;
                    var9_10 = false;
                    synchronized (callConnection) {
                        $i$a$-synchronized-ExchangeFinder$findConnection$1 = false;
                        if (callConnection.getNoNewExchanges() || !this.sameHostAndPort(callConnection.route().address().url())) {
                            toClose = this.call.releaseConnectionNoEvents$okhttp();
                        }
                        var9_11 = Unit.INSTANCE;
                    }
                    if (this.call.getConnection() != null) {
                        var8_8 = toClose == null;
                        var9_13 = false;
                        $i$a$-synchronized-ExchangeFinder$findConnection$1 = false;
                        $i$a$-synchronized-ExchangeFinder$findConnection$1 = false;
                        var11_18 = false;
                        if (!var8_8) {
                            var12_24 = false;
                            var11_19 = "Check failed.";
                            throw (Throwable)new IllegalStateException(var11_19.toString());
                        }
                        return callConnection;
                    }
                    v0 = toClose;
                    if (v0 != null) {
                        Util.closeQuietly(v0);
                    }
                    this.eventListener.connectionReleased(this.call, callConnection);
                }
                this.refusedStreamCount = 0;
                this.connectionShutdownCount = 0;
                this.otherFailureCount = 0;
                if (this.connectionPool.callAcquirePooledConnection(this.address, this.call, null, false)) {
                    v1 = this.call.getConnection();
                    Intrinsics.checkNotNull(v1);
                    result = v1;
                    this.eventListener.connectionAcquired(this.call, result);
                    return result;
                }
                routes = null;
                route = null;
                if (this.nextRouteToTry == null) break block23;
                routes = null;
                v2 = this.nextRouteToTry;
                Intrinsics.checkNotNull(v2);
                route = v2;
                this.nextRouteToTry = null;
                break block24;
            }
            if (this.routeSelection == null) ** GOTO lbl-1000
            v3 = this.routeSelection;
            Intrinsics.checkNotNull(v3);
            if (v3.hasNext()) {
                routes = null;
                v4 = this.routeSelection;
                Intrinsics.checkNotNull(v4);
                route = v4.next();
            } else lbl-1000:
            // 2 sources

            {
                if ((localRouteSelector = this.routeSelector) == null) {
                    this.routeSelector = localRouteSelector = new RouteSelector(this.address, this.call.getClient().getRouteDatabase(), this.call, this.eventListener);
                }
                this.routeSelection = localRouteSelection = localRouteSelector.next();
                routes = localRouteSelection.getRoutes();
                if (this.call.isCanceled()) {
                    throw (Throwable)new IOException("Canceled");
                }
                if (this.connectionPool.callAcquirePooledConnection(this.address, this.call, routes, false)) {
                    v5 = this.call.getConnection();
                    Intrinsics.checkNotNull(v5);
                    result = v5;
                    this.eventListener.connectionAcquired(this.call, result);
                    return result;
                }
                route = localRouteSelection.next();
            }
        }
        newConnection = new RealConnection(this.connectionPool, route);
        this.call.setConnectionToCancel(newConnection);
        try {
            newConnection.connect(connectTimeout, readTimeout, writeTimeout, pingIntervalMillis, connectionRetryEnabled, this.call, this.eventListener);
        } finally {
            this.call.setConnectionToCancel(null);
        }
        this.call.getClient().getRouteDatabase().connected(newConnection.route());
        if (this.connectionPool.callAcquirePooledConnection(this.address, this.call, routes, true)) {
            v6 = this.call.getConnection();
            Intrinsics.checkNotNull(v6);
            result = v6;
            this.nextRouteToTry = route;
            Util.closeQuietly(newConnection.socket());
            this.eventListener.connectionAcquired(this.call, result);
            return result;
        }
        var10_14 = false;
        var11_21 = false;
        synchronized (newConnection) {
            $i$a$-synchronized-ExchangeFinder$findConnection$2 = false;
            this.connectionPool.put(newConnection);
            this.call.acquireConnectionNoEvents(newConnection);
            var11_22 = Unit.INSTANCE;
        }
        this.eventListener.connectionAcquired(this.call, newConnection);
        return newConnection;
    }

    public final void trackFailure(@NotNull IOException e) {
        Intrinsics.checkNotNullParameter(e, "e");
        this.nextRouteToTry = null;
        if (e instanceof StreamResetException && ((StreamResetException)e).errorCode == ErrorCode.REFUSED_STREAM) {
            int n = this.refusedStreamCount;
            this.refusedStreamCount = n + 1;
        } else if (e instanceof ConnectionShutdownException) {
            int n = this.connectionShutdownCount;
            this.connectionShutdownCount = n + 1;
        } else {
            int n = this.otherFailureCount;
            this.otherFailureCount = n + 1;
        }
    }

    public final boolean retryAfterFailure() {
        if (this.refusedStreamCount == 0 && this.connectionShutdownCount == 0 && this.otherFailureCount == 0) {
            return false;
        }
        if (this.nextRouteToTry != null) {
            return true;
        }
        Route retryRoute = this.retryRoute();
        if (retryRoute != null) {
            this.nextRouteToTry = retryRoute;
            return true;
        }
        RouteSelector.Selection selection = this.routeSelection;
        if (selection != null) {
            if (selection.hasNext()) {
                return true;
            }
        }
        RouteSelector routeSelector = this.routeSelector;
        if (routeSelector == null) {
            return true;
        }
        RouteSelector localRouteSelector = routeSelector;
        return localRouteSelector.hasNext();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final Route retryRoute() {
        if (this.refusedStreamCount > 1 || this.connectionShutdownCount > 1 || this.otherFailureCount > 0) {
            return null;
        }
        RealConnection realConnection = this.call.getConnection();
        if (realConnection == null) {
            return null;
        }
        RealConnection connection = realConnection;
        boolean bl = false;
        boolean bl2 = false;
        synchronized (connection) {
            block8: {
                block7: {
                    boolean bl3 = false;
                    if (connection.getRouteFailureCount$okhttp() == 0) break block7;
                    Route route = null;
                    return route;
                }
                if (Util.canReuseConnectionFor(connection.route().address().url(), this.address.url())) break block8;
                Route route = null;
                return route;
            }
            Route route = connection.route();
            return route;
        }
    }

    public final boolean sameHostAndPort(@NotNull HttpUrl url) {
        Intrinsics.checkNotNullParameter(url, "url");
        HttpUrl routeUrl = this.address.url();
        return url.port() == routeUrl.port() && Intrinsics.areEqual(url.host(), routeUrl.host());
    }

    @NotNull
    public final Address getAddress$okhttp() {
        return this.address;
    }

    public ExchangeFinder(@NotNull RealConnectionPool connectionPool, @NotNull Address address, @NotNull RealCall call, @NotNull EventListener eventListener) {
        Intrinsics.checkNotNullParameter(connectionPool, "connectionPool");
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(eventListener, "eventListener");
        this.connectionPool = connectionPool;
        this.address = address;
        this.call = call;
        this.eventListener = eventListener;
    }
}

