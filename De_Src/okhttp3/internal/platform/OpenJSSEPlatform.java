/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openjsse.javax.net.ssl.SSLParameters
 *  org.openjsse.javax.net.ssl.SSLSocket
 *  org.openjsse.net.ssl.OpenJSSE
 */
package okhttp3.internal.platform;

import java.security.KeyStore;
import java.security.Provider;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;
import okhttp3.internal.platform.Platform;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.openjsse.javax.net.ssl.SSLSocket;
import org.openjsse.net.ssl.OpenJSSE;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J-\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0011\u0010\u000b\u001a\r\u0012\t\u0012\u00070\r\u00a2\u0006\u0002\b\u000e0\fH\u0016J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2={"Lokhttp3/internal/platform/OpenJSSEPlatform;", "Lokhttp3/internal/platform/Platform;", "()V", "provider", "Ljava/security/Provider;", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "Lkotlin/jvm/JvmSuppressWildcards;", "getSelectedProtocol", "newSSLContext", "Ljavax/net/ssl/SSLContext;", "platformTrustManager", "Ljavax/net/ssl/X509TrustManager;", "trustManager", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "Companion", "okhttp"})
public final class OpenJSSEPlatform
extends Platform {
    private final Provider provider = (Provider)new OpenJSSE();
    private static final boolean isSupported;
    public static final Companion Companion;

    @Override
    @NotNull
    public SSLContext newSSLContext() {
        SSLContext sSLContext = SSLContext.getInstance("TLSv1.3", this.provider);
        Intrinsics.checkNotNullExpressionValue(sSLContext, "SSLContext.getInstance(\"TLSv1.3\", provider)");
        return sSLContext;
    }

    @Override
    @NotNull
    public X509TrustManager platformTrustManager() {
        TrustManagerFactory factory2 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm(), this.provider);
        factory2.init((KeyStore)null);
        TrustManagerFactory trustManagerFactory = factory2;
        Intrinsics.checkNotNullExpressionValue(trustManagerFactory, "factory");
        Object[] objectArray = trustManagerFactory.getTrustManagers();
        Intrinsics.checkNotNull(objectArray);
        Object[] trustManagers = objectArray;
        boolean bl = trustManagers.length == 1 && trustManagers[0] instanceof X509TrustManager;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            StringBuilder stringBuilder = new StringBuilder().append("Unexpected default trust managers: ");
            Object[] objectArray2 = trustManagers;
            boolean bl5 = false;
            String string = Arrays.toString(objectArray2);
            Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
            String string2 = stringBuilder.append(string).toString();
            throw (Throwable)new IllegalStateException(string2.toString());
        }
        TrustManager trustManager = trustManagers[0];
        if (trustManager == null) {
            throw new NullPointerException("null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
        }
        return (X509TrustManager)trustManager;
    }

    @Override
    @Nullable
    public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
        Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
        throw (Throwable)new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported with OpenJSSE");
    }

    @Override
    public void configureTlsExtensions(@NotNull javax.net.ssl.SSLSocket sslSocket, @Nullable String hostname, @NotNull List<Protocol> protocols) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        Intrinsics.checkNotNullParameter(protocols, "protocols");
        if (sslSocket instanceof SSLSocket) {
            SSLParameters sslParameters = ((SSLSocket)sslSocket).getSSLParameters();
            if (sslParameters instanceof org.openjsse.javax.net.ssl.SSLParameters) {
                List<String> names = Platform.Companion.alpnProtocolNames(protocols);
                Collection $this$toTypedArray$iv = names;
                boolean $i$f$toTypedArray = false;
                Collection thisCollection$iv = $this$toTypedArray$iv;
                String[] stringArray = thisCollection$iv.toArray(new String[0]);
                if (stringArray == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
                ((org.openjsse.javax.net.ssl.SSLParameters)sslParameters).setApplicationProtocols(stringArray);
                ((SSLSocket)sslSocket).setSSLParameters(sslParameters);
            }
        } else {
            super.configureTlsExtensions(sslSocket, hostname, protocols);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    @Nullable
    public String getSelectedProtocol(@NotNull javax.net.ssl.SSLSocket sslSocket) {
        String string;
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        if (sslSocket instanceof SSLSocket) {
            String string2;
            String string3 = string2 = ((SSLSocket)sslSocket).getApplicationProtocol();
            if (string3 == null) return null;
            String string4 = string3;
            switch (string4.hashCode()) {
                case 0: {
                    if (string4.equals("")) {
                        return null;
                    }
                }
                default: {
                    string = string2;
                    return string;
                }
            }
        } else {
            string = super.getSelectedProtocol(sslSocket);
        }
        return string;
    }

    private OpenJSSEPlatform() {
    }

    static {
        boolean bl;
        Companion = new Companion(null);
        try {
            Class.forName("org.openjsse.net.ssl.OpenJSSE", false, Companion.getClass().getClassLoader());
            bl = true;
        } catch (ClassNotFoundException _) {
            bl = false;
        }
        isSupported = bl;
    }

    public /* synthetic */ OpenJSSEPlatform(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0005\u00a8\u0006\b"}, d2={"Lokhttp3/internal/platform/OpenJSSEPlatform$Companion;", "", "()V", "isSupported", "", "()Z", "buildIfSupported", "Lokhttp3/internal/platform/OpenJSSEPlatform;", "okhttp"})
    public static final class Companion {
        public final boolean isSupported() {
            return isSupported;
        }

        @Nullable
        public final OpenJSSEPlatform buildIfSupported() {
            return this.isSupported() ? new OpenJSSEPlatform(null) : null;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

