/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.conscrypt.Conscrypt
 *  org.conscrypt.Conscrypt$Version
 *  org.conscrypt.ConscryptHostnameVerifier
 */
package okhttp3.internal.platform;

import java.security.KeyStore;
import java.security.Provider;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;
import okhttp3.internal.platform.Platform;
import org.conscrypt.Conscrypt;
import org.conscrypt.ConscryptHostnameVerifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00182\u00020\u0001:\u0002\u0018\u0019B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J-\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0011\u0010\u000b\u001a\r\u0012\t\u0012\u00070\r\u00a2\u0006\u0002\b\u000e0\fH\u0016J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0015H\u0016J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0017\u001a\u00020\u0013H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2={"Lokhttp3/internal/platform/ConscryptPlatform;", "Lokhttp3/internal/platform/Platform;", "()V", "provider", "Ljava/security/Provider;", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "Lkotlin/jvm/JvmSuppressWildcards;", "getSelectedProtocol", "newSSLContext", "Ljavax/net/ssl/SSLContext;", "newSslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "platformTrustManager", "sslSocketFactory", "Companion", "DisabledHostnameVerifier", "okhttp"})
public final class ConscryptPlatform
extends Platform {
    private final Provider provider;
    private static final boolean isSupported;
    public static final Companion Companion;

    @Override
    @NotNull
    public SSLContext newSSLContext() {
        SSLContext sSLContext = SSLContext.getInstance("TLS", this.provider);
        Intrinsics.checkNotNullExpressionValue(sSLContext, "SSLContext.getInstance(\"TLS\", provider)");
        return sSLContext;
    }

    @Override
    @NotNull
    public X509TrustManager platformTrustManager() {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        boolean bl = false;
        boolean bl2 = false;
        TrustManagerFactory $this$apply = trustManagerFactory;
        boolean bl3 = false;
        $this$apply.init((KeyStore)null);
        TrustManagerFactory trustManagerFactory2 = trustManagerFactory;
        Intrinsics.checkNotNullExpressionValue(trustManagerFactory2, "TrustManagerFactory.getI\u2026(null as KeyStore?)\n    }");
        Object[] objectArray = trustManagerFactory2.getTrustManagers();
        Intrinsics.checkNotNull(objectArray);
        Object[] trustManagers = objectArray;
        boolean bl4 = trustManagers.length == 1 && trustManagers[0] instanceof X509TrustManager;
        bl = false;
        bl2 = false;
        if (!bl4) {
            boolean bl5 = false;
            StringBuilder stringBuilder = new StringBuilder().append("Unexpected default trust managers: ");
            Object[] objectArray2 = trustManagers;
            boolean bl6 = false;
            String string = Arrays.toString(objectArray2);
            Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
            String string2 = stringBuilder.append(string).toString();
            throw (Throwable)new IllegalStateException(string2.toString());
        }
        TrustManager trustManager = trustManagers[0];
        if (trustManager == null) {
            throw new NullPointerException("null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
        }
        X509TrustManager x509TrustManager = (X509TrustManager)trustManager;
        Conscrypt.setHostnameVerifier((TrustManager)x509TrustManager, (ConscryptHostnameVerifier)DisabledHostnameVerifier.INSTANCE);
        return x509TrustManager;
    }

    @Override
    @Nullable
    public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
        Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
        return null;
    }

    @Override
    public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List<Protocol> protocols) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        Intrinsics.checkNotNullParameter(protocols, "protocols");
        if (Conscrypt.isConscrypt((SSLSocket)sslSocket)) {
            Conscrypt.setUseSessionTickets((SSLSocket)sslSocket, (boolean)true);
            List<String> names = Platform.Companion.alpnProtocolNames(protocols);
            Collection $this$toTypedArray$iv = names;
            boolean $i$f$toTypedArray = false;
            Collection thisCollection$iv = $this$toTypedArray$iv;
            String[] stringArray = thisCollection$iv.toArray(new String[0]);
            if (stringArray == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            Conscrypt.setApplicationProtocols((SSLSocket)sslSocket, (String[])stringArray);
        } else {
            super.configureTlsExtensions(sslSocket, hostname, protocols);
        }
    }

    @Override
    @Nullable
    public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        return Conscrypt.isConscrypt((SSLSocket)sslSocket) ? Conscrypt.getApplicationProtocol((SSLSocket)sslSocket) : super.getSelectedProtocol(sslSocket);
    }

    @Override
    @NotNull
    public SSLSocketFactory newSslSocketFactory(@NotNull X509TrustManager trustManager) {
        Intrinsics.checkNotNullParameter(trustManager, "trustManager");
        SSLContext sSLContext = this.newSSLContext();
        boolean bl = false;
        boolean bl2 = false;
        SSLContext $this$apply = sSLContext;
        boolean bl3 = false;
        $this$apply.init(null, new TrustManager[]{trustManager}, null);
        SSLSocketFactory sSLSocketFactory = sSLContext.getSocketFactory();
        Intrinsics.checkNotNullExpressionValue(sSLSocketFactory, "newSSLContext().apply {\n\u2026null)\n    }.socketFactory");
        return sSLSocketFactory;
    }

    private ConscryptPlatform() {
        Provider provider = Conscrypt.newProvider();
        Intrinsics.checkNotNullExpressionValue(provider, "Conscrypt.newProvider()");
        this.provider = provider;
    }

    static {
        boolean bl;
        Companion = new Companion(null);
        try {
            Class.forName("org.conscrypt.Conscrypt$Version", false, Companion.getClass().getClassLoader());
            bl = Conscrypt.isAvailable() && Companion.atLeastVersion(2, 1, 0);
        } catch (NoClassDefFoundError e) {
            bl = false;
        } catch (ClassNotFoundException e) {
            bl = false;
        }
        isSupported = bl;
    }

    public /* synthetic */ ConscryptPlatform(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c0\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J3\u0010\u0003\u001a\u00020\u00042\u0010\u0010\u0005\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0007\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016\u00a2\u0006\u0002\u0010\fJ\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a8\u0006\r"}, d2={"Lokhttp3/internal/platform/ConscryptPlatform$DisabledHostnameVerifier;", "Lorg/conscrypt/ConscryptHostnameVerifier;", "()V", "verify", "", "certs", "", "Ljava/security/cert/X509Certificate;", "hostname", "", "session", "Ljavax/net/ssl/SSLSession;", "([Ljava/security/cert/X509Certificate;Ljava/lang/String;Ljavax/net/ssl/SSLSession;)Z", "okhttp"})
    public static final class DisabledHostnameVerifier
    implements ConscryptHostnameVerifier {
        public static final DisabledHostnameVerifier INSTANCE;

        public final boolean verify(@Nullable String hostname, @Nullable SSLSession session) {
            return true;
        }

        public boolean verify(@Nullable X509Certificate[] certs, @Nullable String hostname, @Nullable SSLSession session) {
            return true;
        }

        private DisabledHostnameVerifier() {
        }

        static {
            DisabledHostnameVerifier disabledHostnameVerifier;
            INSTANCE = disabledHostnameVerifier = new DisabledHostnameVerifier();
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\"\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\bJ\b\u0010\u000b\u001a\u0004\u0018\u00010\fR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0005\u00a8\u0006\r"}, d2={"Lokhttp3/internal/platform/ConscryptPlatform$Companion;", "", "()V", "isSupported", "", "()Z", "atLeastVersion", "major", "", "minor", "patch", "buildIfSupported", "Lokhttp3/internal/platform/ConscryptPlatform;", "okhttp"})
    public static final class Companion {
        public final boolean isSupported() {
            return isSupported;
        }

        @Nullable
        public final ConscryptPlatform buildIfSupported() {
            return this.isSupported() ? new ConscryptPlatform(null) : null;
        }

        public final boolean atLeastVersion(int major, int minor, int patch) {
            Conscrypt.Version conscryptVersion = Conscrypt.version();
            if (conscryptVersion.major() != major) {
                return conscryptVersion.major() > major;
            }
            if (conscryptVersion.minor() != minor) {
                return conscryptVersion.minor() > minor;
            }
            return conscryptVersion.patch() >= patch;
        }

        public static /* synthetic */ boolean atLeastVersion$default(Companion companion, int n, int n2, int n3, int n4, Object object) {
            if ((n4 & 2) != 0) {
                n2 = 0;
            }
            if ((n4 & 4) != 0) {
                n3 = 0;
            }
            return companion.atLeastVersion(n, n2, n3);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

