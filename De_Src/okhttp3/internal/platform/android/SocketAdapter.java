/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.platform.android;

import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH&J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\f\u001a\u00020\rH&J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0010\u001a\u00020\u0011H\u0016\u00a8\u0006\u0014"}, d2={"Lokhttp3/internal/platform/android/SocketAdapter;", "", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "getSelectedProtocol", "isSupported", "", "matchesSocket", "matchesSocketFactory", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "okhttp"})
public interface SocketAdapter {
    public boolean isSupported();

    @Nullable
    public X509TrustManager trustManager(@NotNull SSLSocketFactory var1);

    public boolean matchesSocket(@NotNull SSLSocket var1);

    public boolean matchesSocketFactory(@NotNull SSLSocketFactory var1);

    public void configureTlsExtensions(@NotNull SSLSocket var1, @Nullable String var2, @NotNull List<? extends Protocol> var3);

    @Nullable
    public String getSelectedProtocol(@NotNull SSLSocket var1);

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=3)
    public static final class DefaultImpls {
        @Nullable
        public static X509TrustManager trustManager(@NotNull SocketAdapter $this, @NotNull SSLSocketFactory sslSocketFactory) {
            Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
            return null;
        }

        public static boolean matchesSocketFactory(@NotNull SocketAdapter $this, @NotNull SSLSocketFactory sslSocketFactory) {
            Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
            return false;
        }
    }
}

