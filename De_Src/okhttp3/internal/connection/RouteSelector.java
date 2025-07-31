/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.connection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RouteDatabase;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 !2\u00020\u0001:\u0002!\"B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0015\u001a\u00020\u0016H\u0086\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0002J\t\u0010\u0018\u001a\u00020\u0019H\u0086\u0002J\b\u0010\u001a\u001a\u00020\u0014H\u0002J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0014H\u0002J\u001a\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020 2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0014H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2={"Lokhttp3/internal/connection/RouteSelector;", "", "address", "Lokhttp3/Address;", "routeDatabase", "Lokhttp3/internal/connection/RouteDatabase;", "call", "Lokhttp3/Call;", "eventListener", "Lokhttp3/EventListener;", "(Lokhttp3/Address;Lokhttp3/internal/connection/RouteDatabase;Lokhttp3/Call;Lokhttp3/EventListener;)V", "inetSocketAddresses", "", "Ljava/net/InetSocketAddress;", "nextProxyIndex", "", "postponedRoutes", "", "Lokhttp3/Route;", "proxies", "Ljava/net/Proxy;", "hasNext", "", "hasNextProxy", "next", "Lokhttp3/internal/connection/RouteSelector$Selection;", "nextProxy", "resetNextInetSocketAddress", "", "proxy", "resetNextProxy", "url", "Lokhttp3/HttpUrl;", "Companion", "Selection", "okhttp"})
public final class RouteSelector {
    private List<? extends Proxy> proxies;
    private int nextProxyIndex;
    private List<? extends InetSocketAddress> inetSocketAddresses;
    private final List<Route> postponedRoutes;
    private final Address address;
    private final RouteDatabase routeDatabase;
    private final Call call;
    private final EventListener eventListener;
    public static final Companion Companion = new Companion(null);

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean hasNext() {
        if (this.hasNextProxy()) return true;
        Collection collection = this.postponedRoutes;
        boolean bl = false;
        if (collection.isEmpty()) return false;
        return true;
    }

    @NotNull
    public final Selection next() throws IOException {
        boolean bl;
        if (!this.hasNext()) {
            throw (Throwable)new NoSuchElementException();
        }
        boolean bl2 = false;
        List routes = new ArrayList();
        while (this.hasNextProxy()) {
            Proxy proxy = this.nextProxy();
            for (InetSocketAddress inetSocketAddress : this.inetSocketAddresses) {
                boolean bl3;
                Collection collection;
                Route route = new Route(this.address, proxy, inetSocketAddress);
                if (this.routeDatabase.shouldPostpone(route)) {
                    collection = this.postponedRoutes;
                    bl3 = false;
                    collection.add(route);
                    continue;
                }
                collection = routes;
                bl3 = false;
                collection.add(route);
            }
            Collection iterable = routes;
            bl = false;
            if (!(!iterable.isEmpty())) continue;
            break;
        }
        if (routes.isEmpty()) {
            Collection collection = routes;
            Iterable iterable = this.postponedRoutes;
            bl = false;
            CollectionsKt.addAll(collection, iterable);
            this.postponedRoutes.clear();
        }
        return new Selection(routes);
    }

    private final void resetNextProxy(HttpUrl url, Proxy proxy) {
        Function0<List<? extends Proxy>> $fun$selectProxies$1 = new Function0<List<? extends Proxy>>(this, proxy, url){
            final /* synthetic */ RouteSelector this$0;
            final /* synthetic */ Proxy $proxy;
            final /* synthetic */ HttpUrl $url;

            @NotNull
            public final List<Proxy> invoke() {
                if (this.$proxy != null) {
                    return CollectionsKt.listOf(this.$proxy);
                }
                URI uri = this.$url.uri();
                if (uri.getHost() == null) {
                    return Util.immutableListOf(Proxy.NO_PROXY);
                }
                List<Proxy> proxiesOrNull = RouteSelector.access$getAddress$p(this.this$0).proxySelector().select(uri);
                Collection collection = proxiesOrNull;
                boolean bl = false;
                boolean bl2 = false;
                if (collection == null || collection.isEmpty()) {
                    return Util.immutableListOf(Proxy.NO_PROXY);
                }
                return Util.toImmutableList(proxiesOrNull);
            }
            {
                this.this$0 = routeSelector;
                this.$proxy = proxy;
                this.$url = httpUrl;
                super(0);
            }
        };
        this.eventListener.proxySelectStart(this.call, url);
        this.proxies = $fun$selectProxies$1.invoke();
        this.nextProxyIndex = 0;
        this.eventListener.proxySelectEnd(this.call, url, this.proxies);
    }

    private final boolean hasNextProxy() {
        return this.nextProxyIndex < this.proxies.size();
    }

    private final Proxy nextProxy() throws IOException {
        if (!this.hasNextProxy()) {
            throw (Throwable)new SocketException("No route to " + this.address.url().host() + "; exhausted proxy configurations: " + this.proxies);
        }
        int n = this.nextProxyIndex;
        this.nextProxyIndex = n + 1;
        Proxy result = this.proxies.get(n);
        this.resetNextInetSocketAddress(result);
        return result;
    }

    private final void resetNextInetSocketAddress(Proxy proxy) throws IOException {
        boolean bl;
        List mutableInetSocketAddresses;
        boolean bl2 = false;
        this.inetSocketAddresses = mutableInetSocketAddresses = (List)new ArrayList();
        String socketHost = null;
        int socketPort = 0;
        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.SOCKS) {
            socketHost = this.address.url().host();
            socketPort = this.address.url().port();
        } else {
            SocketAddress proxyAddress2 = proxy.address();
            boolean bl3 = proxyAddress2 instanceof InetSocketAddress;
            bl = false;
            boolean bl4 = false;
            if (!bl3) {
                boolean bl5 = false;
                String string = "Proxy.address() is not an InetSocketAddress: " + proxyAddress2.getClass();
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            socketHost = Companion.getSocketHost((InetSocketAddress)proxyAddress2);
            socketPort = ((InetSocketAddress)proxyAddress2).getPort();
        }
        int proxyAddress2 = socketPort;
        if (1 > proxyAddress2 || 65535 < proxyAddress2) {
            throw (Throwable)new SocketException("No route to " + socketHost + ':' + socketPort + "; port is out of range");
        }
        if (proxy.type() == Proxy.Type.SOCKS) {
            Collection proxyAddress2 = mutableInetSocketAddresses;
            InetSocketAddress inetSocketAddress = InetSocketAddress.createUnresolved(socketHost, socketPort);
            bl = false;
            proxyAddress2.add(inetSocketAddress);
        } else {
            this.eventListener.dnsStart(this.call, socketHost);
            List<InetAddress> addresses = this.address.dns().lookup(socketHost);
            if (addresses.isEmpty()) {
                throw (Throwable)new UnknownHostException(this.address.dns() + " returned no addresses for " + socketHost);
            }
            this.eventListener.dnsEnd(this.call, socketHost, addresses);
            for (InetAddress inetAddress : addresses) {
                Collection collection = mutableInetSocketAddresses;
                InetSocketAddress inetSocketAddress = new InetSocketAddress(inetAddress, socketPort);
                boolean bl6 = false;
                collection.add(inetSocketAddress);
            }
        }
    }

    public RouteSelector(@NotNull Address address, @NotNull RouteDatabase routeDatabase, @NotNull Call call, @NotNull EventListener eventListener) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(routeDatabase, "routeDatabase");
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(eventListener, "eventListener");
        this.address = address;
        this.routeDatabase = routeDatabase;
        this.call = call;
        this.eventListener = eventListener;
        this.proxies = CollectionsKt.emptyList();
        this.inetSocketAddresses = CollectionsKt.emptyList();
        boolean bl = false;
        this.postponedRoutes = new ArrayList();
        this.resetNextProxy(this.address.url(), this.address.proxy());
    }

    public static final /* synthetic */ Address access$getAddress$p(RouteSelector $this) {
        return $this.address;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\n\u001a\u00020\u000bH\u0086\u0002J\t\u0010\f\u001a\u00020\u0004H\u0086\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\r"}, d2={"Lokhttp3/internal/connection/RouteSelector$Selection;", "", "routes", "", "Lokhttp3/Route;", "(Ljava/util/List;)V", "nextRouteIndex", "", "getRoutes", "()Ljava/util/List;", "hasNext", "", "next", "okhttp"})
    public static final class Selection {
        private int nextRouteIndex;
        @NotNull
        private final List<Route> routes;

        public final boolean hasNext() {
            return this.nextRouteIndex < this.routes.size();
        }

        @NotNull
        public final Route next() {
            if (!this.hasNext()) {
                throw (Throwable)new NoSuchElementException();
            }
            int n = this.nextRouteIndex;
            this.nextRouteIndex = n + 1;
            return this.routes.get(n);
        }

        @NotNull
        public final List<Route> getRoutes() {
            return this.routes;
        }

        public Selection(@NotNull List<Route> routes) {
            Intrinsics.checkNotNullParameter(routes, "routes");
            this.routes = routes;
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0015\u0010\u0003\u001a\u00020\u0004*\u00020\u00058F\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2={"Lokhttp3/internal/connection/RouteSelector$Companion;", "", "()V", "socketHost", "", "Ljava/net/InetSocketAddress;", "getSocketHost", "(Ljava/net/InetSocketAddress;)Ljava/lang/String;", "okhttp"})
    public static final class Companion {
        @NotNull
        public final String getSocketHost(@NotNull InetSocketAddress $this$socketHost) {
            Intrinsics.checkNotNullParameter($this$socketHost, "$this$socketHost");
            InetAddress inetAddress = $this$socketHost.getAddress();
            if (inetAddress == null) {
                String string = $this$socketHost.getHostName();
                Intrinsics.checkNotNullExpressionValue(string, "hostName");
                return string;
            }
            InetAddress address = inetAddress;
            String string = address.getHostAddress();
            Intrinsics.checkNotNullExpressionValue(string, "address.hostAddress");
            return string;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

