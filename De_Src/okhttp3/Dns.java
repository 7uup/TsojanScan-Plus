/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\b"}, d2={"Lokhttp3/Dns;", "", "lookup", "", "Ljava/net/InetAddress;", "hostname", "", "Companion", "okhttp"})
public interface Dns {
    @JvmField
    @NotNull
    public static final Dns SYSTEM;
    public static final Companion Companion;

    @NotNull
    public List<InetAddress> lookup(@NotNull String var1) throws UnknownHostException;

    static {
        Companion = new Companion(null);
        SYSTEM = new Companion.DnsSystem();
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0005B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0001\u0082\u0002\u0007\n\u0005\b\u0091F0\u0001\u00a8\u0006\u0006"}, d2={"Lokhttp3/Dns$Companion;", "", "()V", "SYSTEM", "Lokhttp3/Dns;", "DnsSystem", "okhttp"})
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE;

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016\u00a8\u0006\b"}, d2={"Lokhttp3/Dns$Companion$DnsSystem;", "Lokhttp3/Dns;", "()V", "lookup", "", "Ljava/net/InetAddress;", "hostname", "", "okhttp"})
        private static final class DnsSystem
        implements Dns {
            @Override
            @NotNull
            public List<InetAddress> lookup(@NotNull String hostname) {
                Intrinsics.checkNotNullParameter(hostname, "hostname");
                try {
                    InetAddress[] inetAddressArray = InetAddress.getAllByName(hostname);
                    Intrinsics.checkNotNullExpressionValue(inetAddressArray, "InetAddress.getAllByName(hostname)");
                    return ArraysKt.toList(inetAddressArray);
                } catch (NullPointerException e) {
                    UnknownHostException unknownHostException = new UnknownHostException("Broken system behaviour for dns lookup of " + hostname);
                    boolean bl = false;
                    boolean bl2 = false;
                    UnknownHostException $this$apply = unknownHostException;
                    boolean bl3 = false;
                    $this$apply.initCause(e);
                    throw (Throwable)unknownHostException;
                }
            }
        }
    }
}

