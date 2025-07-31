/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.platform;

import java.util.Collection;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Protocol;
import okhttp3.internal.SuppressSignatureCheck;
import okhttp3.internal.platform.Platform;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005\u00a2\u0006\u0002\u0010\u0002J-\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0011\u0010\t\u001a\r\u0012\t\u0012\u00070\u000b\u00a2\u0006\u0002\b\f0\nH\u0017J\u0012\u0010\r\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0017J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016\u00a8\u0006\u0013"}, d2={"Lokhttp3/internal/platform/Jdk9Platform;", "Lokhttp3/internal/platform/Platform;", "()V", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "Lkotlin/jvm/JvmSuppressWildcards;", "getSelectedProtocol", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "Companion", "okhttp"})
public class Jdk9Platform
extends Platform {
    private static final boolean isAvailable;
    public static final Companion Companion;

    @Override
    @SuppressSignatureCheck
    public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List<Protocol> protocols) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        Intrinsics.checkNotNullParameter(protocols, "protocols");
        SSLParameters sslParameters = sslSocket.getSSLParameters();
        List<String> names = Platform.Companion.alpnProtocolNames(protocols);
        SSLParameters sSLParameters = sslParameters;
        Intrinsics.checkNotNullExpressionValue(sSLParameters, "sslParameters");
        Collection $this$toTypedArray$iv = names;
        boolean $i$f$toTypedArray = false;
        Collection thisCollection$iv = $this$toTypedArray$iv;
        String[] stringArray = thisCollection$iv.toArray(new String[0]);
        if (stringArray == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        sSLParameters.setApplicationProtocols(stringArray);
        sslSocket.setSSLParameters(sslParameters);
    }

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    @SuppressSignatureCheck
    @Nullable
    public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        try {
            String string;
            String string2;
            String string3 = string2 = sslSocket.getApplicationProtocol();
            if (string3 == null) return null;
            String string4 = string3;
            switch (string4.hashCode()) {
                case 0: {
                    if (string4.equals("")) {
                        return null;
                    }
                }
                default: {
                    void protocol;
                    string = protocol;
                }
            }
            return string;
        } catch (UnsupportedOperationException e) {
            return null;
        }
    }

    @Override
    @Nullable
    public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
        Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
        throw (Throwable)new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported on JDK 9+");
    }

    static {
        boolean bl;
        Integer majorVersion;
        String jdkVersion;
        Companion = new Companion(null);
        String string = jdkVersion = System.getProperty("java.specification.version");
        Integer n = majorVersion = string != null ? StringsKt.toIntOrNull(string) : null;
        if (majorVersion != null) {
            bl = majorVersion >= 9;
        } else {
            boolean bl2;
            try {
                SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]);
                bl2 = true;
            } catch (NoSuchMethodException nsme) {
                bl2 = false;
            }
            bl = bl2;
        }
        isAvailable = bl;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0005\u00a8\u0006\b"}, d2={"Lokhttp3/internal/platform/Jdk9Platform$Companion;", "", "()V", "isAvailable", "", "()Z", "buildIfSupported", "Lokhttp3/internal/platform/Jdk9Platform;", "okhttp"})
    public static final class Companion {
        public final boolean isAvailable() {
            return isAvailable;
        }

        @Nullable
        public final Jdk9Platform buildIfSupported() {
            return this.isAvailable() ? new Jdk9Platform() : null;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

