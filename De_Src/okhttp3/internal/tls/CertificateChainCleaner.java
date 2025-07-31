/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.tls;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.BasicCertificateChainCleaner;
import okhttp3.internal.tls.BasicTrustRootIndex;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b&\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005\u00a2\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\n"}, d2={"Lokhttp3/internal/tls/CertificateChainCleaner;", "", "()V", "clean", "", "Ljava/security/cert/Certificate;", "chain", "hostname", "", "Companion", "okhttp"})
public abstract class CertificateChainCleaner {
    public static final Companion Companion = new Companion(null);

    @NotNull
    public abstract List<Certificate> clean(@NotNull List<? extends Certificate> var1, @NotNull String var2) throws SSLPeerUnverifiedException;

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u001f\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\t\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u000b"}, d2={"Lokhttp3/internal/tls/CertificateChainCleaner$Companion;", "", "()V", "get", "Lokhttp3/internal/tls/CertificateChainCleaner;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "caCerts", "", "Ljava/security/cert/X509Certificate;", "([Ljava/security/cert/X509Certificate;)Lokhttp3/internal/tls/CertificateChainCleaner;", "okhttp"})
    public static final class Companion {
        @NotNull
        public final CertificateChainCleaner get(@NotNull X509TrustManager trustManager) {
            Intrinsics.checkNotNullParameter(trustManager, "trustManager");
            return Platform.Companion.get().buildCertificateChainCleaner(trustManager);
        }

        @NotNull
        public final CertificateChainCleaner get(@NotNull X509Certificate ... caCerts) {
            Intrinsics.checkNotNullParameter(caCerts, "caCerts");
            return new BasicCertificateChainCleaner(new BasicTrustRootIndex(Arrays.copyOf(caCerts, caCerts.length)));
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

