/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3;

import java.net.InetSocketAddress;
import java.net.Proxy;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\r\u0010\u0002\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\b\fJ\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\r\u0010\u0004\u001a\u00020\u0005H\u0007\u00a2\u0006\u0002\b\u0012J\u0006\u0010\u0013\u001a\u00020\u000eJ\r\u0010\u0006\u001a\u00020\u0007H\u0007\u00a2\u0006\u0002\b\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0013\u0010\u0002\u001a\u00020\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\tR\u0013\u0010\u0004\u001a\u00020\u00058\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\nR\u0013\u0010\u0006\u001a\u00020\u00078\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000b\u00a8\u0006\u0017"}, d2={"Lokhttp3/Route;", "", "address", "Lokhttp3/Address;", "proxy", "Ljava/net/Proxy;", "socketAddress", "Ljava/net/InetSocketAddress;", "(Lokhttp3/Address;Ljava/net/Proxy;Ljava/net/InetSocketAddress;)V", "()Lokhttp3/Address;", "()Ljava/net/Proxy;", "()Ljava/net/InetSocketAddress;", "-deprecated_address", "equals", "", "other", "hashCode", "", "-deprecated_proxy", "requiresTunnel", "-deprecated_socketAddress", "toString", "", "okhttp"})
public final class Route {
    @NotNull
    private final Address address;
    @NotNull
    private final Proxy proxy;
    @NotNull
    private final InetSocketAddress socketAddress;

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="address"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_address")
    @NotNull
    public final Address -deprecated_address() {
        return this.address;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="proxy"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_proxy")
    @NotNull
    public final Proxy -deprecated_proxy() {
        return this.proxy;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="socketAddress"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_socketAddress")
    @NotNull
    public final InetSocketAddress -deprecated_socketAddress() {
        return this.socketAddress;
    }

    public final boolean requiresTunnel() {
        return this.address.sslSocketFactory() != null && this.proxy.type() == Proxy.Type.HTTP;
    }

    public boolean equals(@Nullable Object other) {
        return other instanceof Route && Intrinsics.areEqual(((Route)other).address, this.address) && Intrinsics.areEqual(((Route)other).proxy, this.proxy) && Intrinsics.areEqual(((Route)other).socketAddress, this.socketAddress);
    }

    public int hashCode() {
        int result = 17;
        result = 31 * result + this.address.hashCode();
        result = 31 * result + this.proxy.hashCode();
        result = 31 * result + this.socketAddress.hashCode();
        return result;
    }

    @NotNull
    public String toString() {
        return "Route{" + this.socketAddress + '}';
    }

    @JvmName(name="address")
    @NotNull
    public final Address address() {
        return this.address;
    }

    @JvmName(name="proxy")
    @NotNull
    public final Proxy proxy() {
        return this.proxy;
    }

    @JvmName(name="socketAddress")
    @NotNull
    public final InetSocketAddress socketAddress() {
        return this.socketAddress;
    }

    public Route(@NotNull Address address, @NotNull Proxy proxy, @NotNull InetSocketAddress socketAddress) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(proxy, "proxy");
        Intrinsics.checkNotNullParameter(socketAddress, "socketAddress");
        this.address = address;
        this.proxy = proxy;
        this.socketAddress = socketAddress;
    }
}

