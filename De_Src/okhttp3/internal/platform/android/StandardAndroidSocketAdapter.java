/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.platform.android;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.platform.android.AndroidSocketAdapter;
import okhttp3.internal.platform.android.SocketAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB1\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00040\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00060\u0003\u0012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0003\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J\u0012\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000b\u001a\u00020\u0006H\u0016R\u0012\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00060\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2={"Lokhttp3/internal/platform/android/StandardAndroidSocketAdapter;", "Lokhttp3/internal/platform/android/AndroidSocketAdapter;", "sslSocketClass", "Ljava/lang/Class;", "Ljavax/net/ssl/SSLSocket;", "sslSocketFactoryClass", "Ljavax/net/ssl/SSLSocketFactory;", "paramClass", "(Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;)V", "matchesSocketFactory", "", "sslSocketFactory", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "Companion", "okhttp"})
public final class StandardAndroidSocketAdapter
extends AndroidSocketAdapter {
    private final Class<? super SSLSocketFactory> sslSocketFactoryClass;
    private final Class<?> paramClass;
    public static final Companion Companion = new Companion(null);

    @Override
    public boolean matchesSocketFactory(@NotNull SSLSocketFactory sslSocketFactory) {
        Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
        return this.sslSocketFactoryClass.isInstance(sslSocketFactory);
    }

    @Override
    @Nullable
    public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
        Object context;
        Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
        Object obj = context = Util.readFieldOrNull(sslSocketFactory, this.paramClass, "sslParameters");
        Intrinsics.checkNotNull(obj);
        X509TrustManager x509TrustManager = Util.readFieldOrNull(obj, X509TrustManager.class, "x509TrustManager");
        X509TrustManager x509TrustManager2 = x509TrustManager;
        if (x509TrustManager2 == null) {
            x509TrustManager2 = Util.readFieldOrNull(context, X509TrustManager.class, "trustManager");
        }
        return x509TrustManager2;
    }

    public StandardAndroidSocketAdapter(@NotNull Class<? super SSLSocket> sslSocketClass, @NotNull Class<? super SSLSocketFactory> sslSocketFactoryClass, @NotNull Class<?> paramClass) {
        Intrinsics.checkNotNullParameter(sslSocketClass, "sslSocketClass");
        Intrinsics.checkNotNullParameter(sslSocketFactoryClass, "sslSocketFactoryClass");
        Intrinsics.checkNotNullParameter(paramClass, "paramClass");
        super(sslSocketClass);
        this.sslSocketFactoryClass = sslSocketFactoryClass;
        this.paramClass = paramClass;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2={"Lokhttp3/internal/platform/android/StandardAndroidSocketAdapter$Companion;", "", "()V", "buildIfSupported", "Lokhttp3/internal/platform/android/SocketAdapter;", "packageName", "", "okhttp"})
    public static final class Companion {
        @Nullable
        public final SocketAdapter buildIfSupported(@NotNull String packageName) {
            SocketAdapter socketAdapter;
            Intrinsics.checkNotNullParameter(packageName, "packageName");
            try {
                Class<?> paramsClass;
                Class<?> clazz = Class.forName(packageName + ".OpenSSLSocketImpl");
                if (clazz == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<in javax.net.ssl.SSLSocket>");
                }
                Class<?> sslSocketClass = clazz;
                Class<?> clazz2 = Class.forName(packageName + ".OpenSSLSocketFactoryImpl");
                if (clazz2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<in javax.net.ssl.SSLSocketFactory>");
                }
                Class<?> sslSocketFactoryClass = clazz2;
                Class<?> clazz3 = paramsClass = Class.forName(packageName + ".SSLParametersImpl");
                Intrinsics.checkNotNullExpressionValue(clazz3, "paramsClass");
                socketAdapter = new StandardAndroidSocketAdapter(sslSocketClass, sslSocketFactoryClass, clazz3);
            } catch (Exception e) {
                Throwable throwable = e;
                String string = "unable to load android socket classes";
                int n = 5;
                Platform.Companion.get().log(string, n, throwable);
                socketAdapter = null;
            }
            return socketAdapter;
        }

        public static /* synthetic */ SocketAdapter buildIfSupported$default(Companion companion, String string, int n, Object object) {
            if ((n & 1) != 0) {
                string = "com.android.org.conscrypt";
            }
            return companion.buildIfSupported(string);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

