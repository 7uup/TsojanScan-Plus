/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.proxy;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0018\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016\u00a8\u0006\u000e"}, d2={"Lokhttp3/internal/proxy/NullProxySelector;", "Ljava/net/ProxySelector;", "()V", "connectFailed", "", "uri", "Ljava/net/URI;", "sa", "Ljava/net/SocketAddress;", "ioe", "Ljava/io/IOException;", "select", "", "Ljava/net/Proxy;", "okhttp"})
public final class NullProxySelector
extends ProxySelector {
    public static final NullProxySelector INSTANCE;

    @Override
    @NotNull
    public List<Proxy> select(@Nullable URI uri) {
        boolean bl = false;
        boolean bl2 = false;
        if (uri == null) {
            boolean bl3 = false;
            String string = "uri must not be null";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        return CollectionsKt.listOf(Proxy.NO_PROXY);
    }

    @Override
    public void connectFailed(@Nullable URI uri, @Nullable SocketAddress sa, @Nullable IOException ioe) {
    }

    private NullProxySelector() {
    }

    static {
        NullProxySelector nullProxySelector;
        INSTANCE = nullProxySelector = new NullProxySelector();
    }
}

