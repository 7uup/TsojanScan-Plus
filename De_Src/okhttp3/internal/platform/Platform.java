/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.platform;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.Provider;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Android10Platform;
import okhttp3.internal.platform.AndroidPlatform;
import okhttp3.internal.platform.BouncyCastlePlatform;
import okhttp3.internal.platform.ConscryptPlatform;
import okhttp3.internal.platform.Jdk8WithJettyBootPlatform;
import okhttp3.internal.platform.Jdk9Platform;
import okhttp3.internal.platform.OpenJSSEPlatform;
import okhttp3.internal.platform.android.AndroidLog;
import okhttp3.internal.tls.BasicCertificateChainCleaner;
import okhttp3.internal.tls.BasicTrustRootIndex;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.TrustRootIndex;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 /2\u00020\u0001:\u0001/B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nH\u0016J-\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0011\u0010\u0010\u001a\r\u0012\t\u0012\u00070\u0012\u00a2\u0006\u0002\b\u00130\u0011H\u0016J \u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0006\u0010\u001b\u001a\u00020\u000fJ\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001e\u001a\u00020\u000fH\u0016J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J&\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u000f2\b\b\u0002\u0010#\u001a\u00020\u001a2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u001a\u0010&\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u000f2\b\u0010'\u001a\u0004\u0018\u00010\u0001H\u0016J\b\u0010(\u001a\u00020)H\u0016J\u0010\u0010*\u001a\u00020+2\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010,\u001a\u00020\nH\u0016J\b\u0010-\u001a\u00020\u000fH\u0016J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010.\u001a\u00020+H\u0016\u00a8\u00060"}, d2={"Lokhttp3/internal/platform/Platform;", "", "()V", "afterHandshake", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "buildCertificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "buildTrustRootIndex", "Lokhttp3/internal/tls/TrustRootIndex;", "configureTlsExtensions", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "Lkotlin/jvm/JvmSuppressWildcards;", "connectSocket", "socket", "Ljava/net/Socket;", "address", "Ljava/net/InetSocketAddress;", "connectTimeout", "", "getPrefix", "getSelectedProtocol", "getStackTraceForCloseable", "closer", "isCleartextTrafficPermitted", "", "log", "message", "level", "t", "", "logCloseableLeak", "stackTrace", "newSSLContext", "Ljavax/net/ssl/SSLContext;", "newSslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "platformTrustManager", "toString", "sslSocketFactory", "Companion", "okhttp"})
public class Platform {
    private static volatile Platform platform;
    public static final int INFO = 4;
    public static final int WARN = 5;
    private static final Logger logger;
    public static final Companion Companion;

    @NotNull
    public final String getPrefix() {
        return "OkHttp";
    }

    @NotNull
    public SSLContext newSSLContext() {
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        Intrinsics.checkNotNullExpressionValue(sSLContext, "SSLContext.getInstance(\"TLS\")");
        return sSLContext;
    }

    @NotNull
    public X509TrustManager platformTrustManager() {
        TrustManagerFactory factory2 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
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

    @Nullable
    public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
        X509TrustManager x509TrustManager;
        Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
        try {
            Class<?> sslContextClass;
            Class<?> clazz = sslContextClass = Class.forName("sun.security.ssl.SSLContextImpl");
            Intrinsics.checkNotNullExpressionValue(clazz, "sslContextClass");
            Object obj = Util.readFieldOrNull(sslSocketFactory, clazz, "context");
            if (obj == null) {
                return null;
            }
            Object context = obj;
            x509TrustManager = Util.readFieldOrNull(context, X509TrustManager.class, "trustManager");
        } catch (ClassNotFoundException e) {
            x509TrustManager = null;
        } catch (RuntimeException e) {
            if (Intrinsics.areEqual(e.getClass().getName(), "java.lang.reflect.InaccessibleObjectException") ^ true) {
                throw (Throwable)e;
            }
            x509TrustManager = null;
        }
        return x509TrustManager;
    }

    public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List<Protocol> protocols) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        Intrinsics.checkNotNullParameter(protocols, "protocols");
    }

    public void afterHandshake(@NotNull SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    }

    @Nullable
    public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        return null;
    }

    public void connectSocket(@NotNull Socket socket, @NotNull InetSocketAddress address, int connectTimeout) throws IOException {
        Intrinsics.checkNotNullParameter(socket, "socket");
        Intrinsics.checkNotNullParameter(address, "address");
        socket.connect(address, connectTimeout);
    }

    public void log(@NotNull String message, int level, @Nullable Throwable t) {
        Intrinsics.checkNotNullParameter(message, "message");
        Level logLevel = level == 5 ? Level.WARNING : Level.INFO;
        logger.log(logLevel, message, t);
    }

    public static /* synthetic */ void log$default(Platform platform, String string, int n, Throwable throwable, int n2, Object object) {
        if (object != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: log");
        }
        if ((n2 & 2) != 0) {
            n = 4;
        }
        if ((n2 & 4) != 0) {
            throwable = null;
        }
        platform.log(string, n, throwable);
    }

    public boolean isCleartextTrafficPermitted(@NotNull String hostname) {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        return true;
    }

    @Nullable
    public Object getStackTraceForCloseable(@NotNull String closer) {
        Intrinsics.checkNotNullParameter(closer, "closer");
        return logger.isLoggable(Level.FINE) ? new Throwable(closer) : null;
    }

    public void logCloseableLeak(@NotNull String message, @Nullable Object stackTrace) {
        Intrinsics.checkNotNullParameter(message, "message");
        String logMessage = message;
        if (stackTrace == null) {
            logMessage = logMessage + " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);";
        }
        this.log(logMessage, 5, (Throwable)stackTrace);
    }

    @NotNull
    public CertificateChainCleaner buildCertificateChainCleaner(@NotNull X509TrustManager trustManager) {
        Intrinsics.checkNotNullParameter(trustManager, "trustManager");
        return new BasicCertificateChainCleaner(this.buildTrustRootIndex(trustManager));
    }

    @NotNull
    public TrustRootIndex buildTrustRootIndex(@NotNull X509TrustManager trustManager) {
        Intrinsics.checkNotNullParameter(trustManager, "trustManager");
        X509Certificate[] x509CertificateArray = trustManager.getAcceptedIssuers();
        Intrinsics.checkNotNullExpressionValue(x509CertificateArray, "trustManager.acceptedIssuers");
        return new BasicTrustRootIndex(Arrays.copyOf(x509CertificateArray, x509CertificateArray.length));
    }

    @NotNull
    public SSLSocketFactory newSslSocketFactory(@NotNull X509TrustManager trustManager) {
        Intrinsics.checkNotNullParameter(trustManager, "trustManager");
        try {
            SSLContext sSLContext = this.newSSLContext();
            boolean bl = false;
            boolean bl2 = false;
            SSLContext $this$apply = sSLContext;
            boolean bl3 = false;
            $this$apply.init(null, new TrustManager[]{trustManager}, null);
            SSLSocketFactory sSLSocketFactory = sSLContext.getSocketFactory();
            Intrinsics.checkNotNullExpressionValue(sSLSocketFactory, "newSSLContext().apply {\n\u2026ll)\n      }.socketFactory");
            return sSLSocketFactory;
        } catch (GeneralSecurityException e) {
            throw (Throwable)((Object)new AssertionError("No System TLS: " + e, e));
        }
    }

    @NotNull
    public String toString() {
        String string = this.getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(string, "javaClass.simpleName");
        return string;
    }

    static {
        Companion = new Companion(null);
        platform = Platform.Companion.findPlatform();
        logger = Logger.getLogger(OkHttpClient.class.getName());
    }

    @JvmStatic
    @NotNull
    public static final Platform get() {
        return Companion.get();
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0012J\u0014\u0010\u0016\u001a\u00020\u00172\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0012J\b\u0010\u0018\u001a\u00020\u0010H\u0002J\b\u0010\u0019\u001a\u00020\u0010H\u0002J\b\u0010\u001a\u001a\u00020\u0010H\u0002J\b\u0010\u001b\u001a\u00020\u0010H\u0007J\u0010\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078F\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\bR\u0014\u0010\t\u001a\u00020\u00078BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\bR\u0014\u0010\n\u001a\u00020\u00078BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0014\u0010\u000b\u001a\u00020\u00078BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\bR\u0016\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2={"Lokhttp3/internal/platform/Platform$Companion;", "", "()V", "INFO", "", "WARN", "isAndroid", "", "()Z", "isBouncyCastlePreferred", "isConscryptPreferred", "isOpenJSSEPreferred", "logger", "Ljava/util/logging/Logger;", "kotlin.jvm.PlatformType", "platform", "Lokhttp3/internal/platform/Platform;", "alpnProtocolNames", "", "", "protocols", "Lokhttp3/Protocol;", "concatLengthPrefixed", "", "findAndroidPlatform", "findJvmPlatform", "findPlatform", "get", "resetForTests", "", "okhttp"})
    public static final class Companion {
        @JvmStatic
        @NotNull
        public final Platform get() {
            return platform;
        }

        public final void resetForTests(@NotNull Platform platform) {
            Intrinsics.checkNotNullParameter(platform, "platform");
            Platform.platform = platform;
        }

        public static /* synthetic */ void resetForTests$default(Companion companion, Platform platform, int n, Object object) {
            if ((n & 1) != 0) {
                platform = companion.findPlatform();
            }
            companion.resetForTests(platform);
        }

        /*
         * WARNING - void declaration
         */
        @NotNull
        public final List<String> alpnProtocolNames(@NotNull List<? extends Protocol> protocols) {
            void $this$mapTo$iv$iv;
            Protocol it;
            Iterable $this$filterTo$iv$iv;
            Intrinsics.checkNotNullParameter(protocols, "protocols");
            Iterable $this$filter$iv = protocols;
            boolean $i$f$filter = false;
            Iterable iterable = $this$filter$iv;
            Collection destination$iv$iv = new ArrayList();
            boolean $i$f$filterTo = false;
            for (Object element$iv$iv : $this$filterTo$iv$iv) {
                it = (Protocol)((Object)element$iv$iv);
                boolean bl = false;
                if (!(it != Protocol.HTTP_1_0)) continue;
                destination$iv$iv.add(element$iv$iv);
            }
            Iterable $this$map$iv = (List)destination$iv$iv;
            boolean $i$f$map = false;
            $this$filterTo$iv$iv = $this$map$iv;
            destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            boolean $i$f$mapTo = false;
            for (Object item$iv$iv : $this$mapTo$iv$iv) {
                it = (Protocol)((Object)item$iv$iv);
                Collection collection = destination$iv$iv;
                boolean bl = false;
                String string = it.toString();
                collection.add(string);
            }
            return (List)destination$iv$iv;
        }

        public final boolean isAndroid() {
            return Intrinsics.areEqual("Dalvik", System.getProperty("java.vm.name"));
        }

        private final boolean isConscryptPreferred() {
            Provider provider = Security.getProviders()[0];
            Intrinsics.checkNotNullExpressionValue(provider, "Security.getProviders()[0]");
            String preferredProvider = provider.getName();
            return Intrinsics.areEqual("Conscrypt", preferredProvider);
        }

        private final boolean isOpenJSSEPreferred() {
            Provider provider = Security.getProviders()[0];
            Intrinsics.checkNotNullExpressionValue(provider, "Security.getProviders()[0]");
            String preferredProvider = provider.getName();
            return Intrinsics.areEqual("OpenJSSE", preferredProvider);
        }

        private final boolean isBouncyCastlePreferred() {
            Provider provider = Security.getProviders()[0];
            Intrinsics.checkNotNullExpressionValue(provider, "Security.getProviders()[0]");
            String preferredProvider = provider.getName();
            return Intrinsics.areEqual("BC", preferredProvider);
        }

        private final Platform findPlatform() {
            return this.isAndroid() ? this.findAndroidPlatform() : this.findJvmPlatform();
        }

        private final Platform findAndroidPlatform() {
            AndroidLog.INSTANCE.enable();
            Platform platform = Android10Platform.Companion.buildIfSupported();
            if (platform == null) {
                Platform platform2 = AndroidPlatform.Companion.buildIfSupported();
                platform = platform2;
                Intrinsics.checkNotNull(platform2);
            }
            return platform;
        }

        private final Platform findJvmPlatform() {
            OpenJSSEPlatform openJSSE;
            BouncyCastlePlatform bc;
            ConscryptPlatform conscrypt;
            if (this.isConscryptPreferred() && (conscrypt = ConscryptPlatform.Companion.buildIfSupported()) != null) {
                return conscrypt;
            }
            if (this.isBouncyCastlePreferred() && (bc = BouncyCastlePlatform.Companion.buildIfSupported()) != null) {
                return bc;
            }
            if (this.isOpenJSSEPreferred() && (openJSSE = OpenJSSEPlatform.Companion.buildIfSupported()) != null) {
                return openJSSE;
            }
            Jdk9Platform jdk9 = Jdk9Platform.Companion.buildIfSupported();
            if (jdk9 != null) {
                return jdk9;
            }
            Platform jdkWithJettyBoot = Jdk8WithJettyBootPlatform.Companion.buildIfSupported();
            if (jdkWithJettyBoot != null) {
                return jdkWithJettyBoot;
            }
            return new Platform();
        }

        @NotNull
        public final byte[] concatLengthPrefixed(@NotNull List<? extends Protocol> protocols) {
            Intrinsics.checkNotNullParameter(protocols, "protocols");
            Buffer result = new Buffer();
            for (String protocol : this.alpnProtocolNames(protocols)) {
                result.writeByte(protocol.length());
                result.writeUtf8(protocol);
            }
            return result.readByteArray();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

