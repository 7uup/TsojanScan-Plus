/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.tls;

import java.security.GeneralSecurityException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.TrustRootIndex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2={"Lokhttp3/internal/tls/BasicCertificateChainCleaner;", "Lokhttp3/internal/tls/CertificateChainCleaner;", "trustRootIndex", "Lokhttp3/internal/tls/TrustRootIndex;", "(Lokhttp3/internal/tls/TrustRootIndex;)V", "clean", "", "Ljava/security/cert/Certificate;", "chain", "hostname", "", "equals", "", "other", "", "hashCode", "", "verifySignature", "toVerify", "Ljava/security/cert/X509Certificate;", "signingCert", "Companion", "okhttp"})
public final class BasicCertificateChainCleaner
extends CertificateChainCleaner {
    private final TrustRootIndex trustRootIndex;
    private static final int MAX_SIGNERS = 9;
    public static final Companion Companion = new Companion(null);

    /*
     * WARNING - void declaration
     */
    @Override
    @NotNull
    public List<Certificate> clean(@NotNull List<? extends Certificate> chain, @NotNull String hostname) throws SSLPeerUnverifiedException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        Deque queue = new ArrayDeque(chain);
        boolean bl = false;
        List result = new ArrayList();
        Object e = queue.removeFirst();
        Intrinsics.checkNotNullExpressionValue(e, "queue.removeFirst()");
        result.add(e);
        boolean foundTrustedCertificate = false;
        int n = 0;
        int n2 = 9;
        while (n < n2) {
            void c;
            block9: {
                Object e2 = result.get(result.size() - 1);
                if (e2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");
                }
                X509Certificate toVerify = (X509Certificate)e2;
                X509Certificate trustedCert = this.trustRootIndex.findByIssuerAndSignature(toVerify);
                if (trustedCert != null) {
                    if (result.size() > 1 || Intrinsics.areEqual(toVerify, trustedCert) ^ true) {
                        result.add(trustedCert);
                    }
                    if (this.verifySignature(trustedCert, trustedCert)) {
                        return result;
                    }
                    foundTrustedCertificate = true;
                } else {
                    Iterator i;
                    Intrinsics.checkNotNullExpressionValue(queue.iterator(), "queue.iterator()");
                    while (i.hasNext()) {
                        Object e3 = i.next();
                        if (e3 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");
                        }
                        X509Certificate signingCert = (X509Certificate)e3;
                        if (!this.verifySignature(toVerify, signingCert)) continue;
                        i.remove();
                        result.add(signingCert);
                        break block9;
                    }
                    if (foundTrustedCertificate) {
                        return result;
                    }
                    throw (Throwable)new SSLPeerUnverifiedException("Failed to find a trusted cert that signed " + toVerify);
                }
            }
            ++c;
        }
        throw (Throwable)new SSLPeerUnverifiedException("Certificate chain too long: " + result);
    }

    private final boolean verifySignature(X509Certificate toVerify, X509Certificate signingCert) {
        boolean bl;
        if (Intrinsics.areEqual(toVerify.getIssuerDN(), signingCert.getSubjectDN()) ^ true) {
            return false;
        }
        try {
            toVerify.verify(signingCert.getPublicKey());
            bl = true;
        } catch (GeneralSecurityException verifyFailed) {
            bl = false;
        }
        return bl;
    }

    public int hashCode() {
        return this.trustRootIndex.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        return other == this ? true : other instanceof BasicCertificateChainCleaner && Intrinsics.areEqual(((BasicCertificateChainCleaner)other).trustRootIndex, this.trustRootIndex);
    }

    public BasicCertificateChainCleaner(@NotNull TrustRootIndex trustRootIndex) {
        Intrinsics.checkNotNullParameter(trustRootIndex, "trustRootIndex");
        this.trustRootIndex = trustRootIndex;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2={"Lokhttp3/internal/tls/BasicCertificateChainCleaner$Companion;", "", "()V", "MAX_SIGNERS", "", "okhttp"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

