/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.tls;

import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.tls.TrustRootIndex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0096\u0002J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016R \u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\t0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2={"Lokhttp3/internal/tls/BasicTrustRootIndex;", "Lokhttp3/internal/tls/TrustRootIndex;", "caCerts", "", "Ljava/security/cert/X509Certificate;", "([Ljava/security/cert/X509Certificate;)V", "subjectToCaCerts", "", "Ljavax/security/auth/x500/X500Principal;", "", "equals", "", "other", "", "findByIssuerAndSignature", "cert", "hashCode", "", "okhttp"})
public final class BasicTrustRootIndex
implements TrustRootIndex {
    private final Map<X500Principal, Set<X509Certificate>> subjectToCaCerts;

    @Override
    @Nullable
    public X509Certificate findByIssuerAndSignature(@NotNull X509Certificate cert) {
        Object v2;
        block4: {
            Intrinsics.checkNotNullParameter(cert, "cert");
            X500Principal issuer = cert.getIssuerX500Principal();
            Set<X509Certificate> set = this.subjectToCaCerts.get(issuer);
            if (set == null) {
                return null;
            }
            Set<X509Certificate> subjectCaCerts = set;
            Iterable $this$firstOrNull$iv = subjectCaCerts;
            boolean $i$f$firstOrNull = false;
            for (Object element$iv : $this$firstOrNull$iv) {
                boolean bl;
                X509Certificate it = (X509Certificate)element$iv;
                boolean bl2 = false;
                try {
                    cert.verify(it.getPublicKey());
                    bl = true;
                } catch (Exception _) {
                    bl = false;
                }
                if (!bl) continue;
                v2 = element$iv;
                break block4;
            }
            v2 = null;
        }
        return v2;
    }

    public boolean equals(@Nullable Object other) {
        return other == this || other instanceof BasicTrustRootIndex && Intrinsics.areEqual(((BasicTrustRootIndex)other).subjectToCaCerts, this.subjectToCaCerts);
    }

    public int hashCode() {
        return ((Object)this.subjectToCaCerts).hashCode();
    }

    /*
     * WARNING - void declaration
     */
    public BasicTrustRootIndex(@NotNull X509Certificate ... caCerts) {
        Intrinsics.checkNotNullParameter(caCerts, "caCerts");
        boolean bl = false;
        Map map = new LinkedHashMap();
        for (X509Certificate caCert : caCerts) {
            Object object;
            X500Principal key$iv;
            void $this$getOrPut$iv;
            Map map2 = map;
            Intrinsics.checkNotNullExpressionValue(caCert.getSubjectX500Principal(), "caCert.subjectX500Principal");
            boolean $i$f$getOrPut = false;
            Object value$iv = $this$getOrPut$iv.get(key$iv);
            if (value$iv == null) {
                boolean bl2 = false;
                boolean bl3 = false;
                Set answer$iv = new LinkedHashSet();
                $this$getOrPut$iv.put(key$iv, answer$iv);
                object = answer$iv;
            } else {
                object = value$iv;
            }
            ((Set)object).add(caCert);
        }
        this.subjectToCaCerts = map;
    }
}

