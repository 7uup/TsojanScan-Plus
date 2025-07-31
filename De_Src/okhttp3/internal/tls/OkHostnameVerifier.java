/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.tls;

import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.HostnamesKt;
import okhttp3.internal.Util;
import okio.Utf8;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nJ\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0004H\u0002J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u001c\u0010\u0012\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\bH\u0002J\u0018\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\f\u0010\u0017\u001a\u00020\b*\u00020\bH\u0002J\f\u0010\u0018\u001a\u00020\u000e*\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2={"Lokhttp3/internal/tls/OkHostnameVerifier;", "Ljavax/net/ssl/HostnameVerifier;", "()V", "ALT_DNS_NAME", "", "ALT_IPA_NAME", "allSubjectAltNames", "", "", "certificate", "Ljava/security/cert/X509Certificate;", "getSubjectAltNames", "type", "verify", "", "host", "session", "Ljavax/net/ssl/SSLSession;", "verifyHostname", "hostname", "pattern", "verifyIpAddress", "ipAddress", "asciiToLowercase", "isAscii", "okhttp"})
public final class OkHostnameVerifier
implements HostnameVerifier {
    private static final int ALT_DNS_NAME = 2;
    private static final int ALT_IPA_NAME = 7;
    public static final OkHostnameVerifier INSTANCE;

    @Override
    public boolean verify(@NotNull String host, @NotNull SSLSession session) {
        boolean bl;
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(session, "session");
        if (!this.isAscii(host)) {
            bl = false;
        } else {
            boolean bl2;
            try {
                Certificate certificate = session.getPeerCertificates()[0];
                if (certificate == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");
                }
                bl2 = this.verify(host, (X509Certificate)certificate);
            } catch (SSLException _) {
                bl2 = false;
            }
            bl = bl2;
        }
        return bl;
    }

    public final boolean verify(@NotNull String host, @NotNull X509Certificate certificate) {
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(certificate, "certificate");
        return Util.canParseAsIpAddress(host) ? this.verifyIpAddress(host, certificate) : this.verifyHostname(host, certificate);
    }

    private final boolean verifyIpAddress(String ipAddress, X509Certificate certificate) {
        boolean bl;
        block3: {
            String canonicalIpAddress = HostnamesKt.toCanonicalHost(ipAddress);
            Iterable $this$any$iv = this.getSubjectAltNames(certificate, 7);
            boolean $i$f$any = false;
            if ($this$any$iv instanceof Collection && ((Collection)$this$any$iv).isEmpty()) {
                bl = false;
            } else {
                for (Object element$iv : $this$any$iv) {
                    String it = (String)element$iv;
                    boolean bl2 = false;
                    if (!Intrinsics.areEqual(canonicalIpAddress, HostnamesKt.toCanonicalHost(it))) continue;
                    bl = true;
                    break block3;
                }
                bl = false;
            }
        }
        return bl;
    }

    private final boolean verifyHostname(String hostname, X509Certificate certificate) {
        boolean bl;
        block3: {
            String hostname2 = this.asciiToLowercase(hostname);
            Iterable $this$any$iv = this.getSubjectAltNames(certificate, 2);
            boolean $i$f$any = false;
            if ($this$any$iv instanceof Collection && ((Collection)$this$any$iv).isEmpty()) {
                bl = false;
            } else {
                for (Object element$iv : $this$any$iv) {
                    String it = (String)element$iv;
                    boolean bl2 = false;
                    if (!INSTANCE.verifyHostname(hostname2, it)) continue;
                    bl = true;
                    break block3;
                }
                bl = false;
            }
        }
        return bl;
    }

    private final String asciiToLowercase(String $this$asciiToLowercase) {
        String string;
        if (this.isAscii($this$asciiToLowercase)) {
            String string2 = $this$asciiToLowercase;
            Locale locale = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale, "Locale.US");
            Locale locale2 = locale;
            boolean bl = false;
            String string3 = string2;
            if (string3 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String string4 = string3.toLowerCase(locale2);
            string = string4;
            Intrinsics.checkNotNullExpressionValue(string4, "(this as java.lang.String).toLowerCase(locale)");
        } else {
            string = $this$asciiToLowercase;
        }
        return string;
    }

    private final boolean isAscii(String $this$isAscii) {
        return $this$isAscii.length() == (int)Utf8.size$default($this$isAscii, 0, 0, 3, null);
    }

    private final boolean verifyHostname(String hostname, String pattern) {
        String hostname2 = hostname;
        String pattern2 = pattern;
        CharSequence charSequence = hostname2;
        boolean bl = false;
        int n = 0;
        if (charSequence == null || charSequence.length() == 0 || StringsKt.startsWith$default(hostname2, ".", false, 2, null) || StringsKt.endsWith$default(hostname2, "..", false, 2, null)) {
            return false;
        }
        charSequence = pattern2;
        bl = false;
        n = 0;
        if (charSequence == null || charSequence.length() == 0 || StringsKt.startsWith$default(pattern2, ".", false, 2, null) || StringsKt.endsWith$default(pattern2, "..", false, 2, null)) {
            return false;
        }
        if (!StringsKt.endsWith$default(hostname2, ".", false, 2, null)) {
            hostname2 = hostname2 + ".";
        }
        if (!StringsKt.endsWith$default(pattern2, ".", false, 2, null)) {
            pattern2 = pattern2 + ".";
        }
        if (!StringsKt.contains$default((CharSequence)(pattern2 = this.asciiToLowercase(pattern2)), "*", false, 2, null)) {
            return Intrinsics.areEqual(hostname2, pattern2);
        }
        if (!StringsKt.startsWith$default(pattern2, "*.", false, 2, null) || StringsKt.indexOf$default((CharSequence)pattern2, '*', 1, false, 4, null) != -1) {
            return false;
        }
        if (hostname2.length() < pattern2.length()) {
            return false;
        }
        if (Intrinsics.areEqual("*.", pattern2)) {
            return false;
        }
        String string = pattern2;
        n = 1;
        boolean bl2 = false;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.substring(n);
        Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.String).substring(startIndex)");
        String suffix = string3;
        if (!StringsKt.endsWith$default(hostname2, suffix, false, 2, null)) {
            return false;
        }
        int suffixStartIndexInHostname = hostname2.length() - suffix.length();
        return suffixStartIndexInHostname <= 0 || StringsKt.lastIndexOf$default((CharSequence)hostname2, '.', suffixStartIndexInHostname - 1, false, 4, null) == -1;
    }

    @NotNull
    public final List<String> allSubjectAltNames(@NotNull X509Certificate certificate) {
        Intrinsics.checkNotNullParameter(certificate, "certificate");
        List<String> altIpaNames = this.getSubjectAltNames(certificate, 7);
        List<String> altDnsNames = this.getSubjectAltNames(certificate, 2);
        return CollectionsKt.plus((Collection)altIpaNames, (Iterable)altDnsNames);
    }

    private final List<String> getSubjectAltNames(X509Certificate certificate, int type) {
        try {
            Collection<List<?>> collection = certificate.getSubjectAlternativeNames();
            if (collection == null) {
                return CollectionsKt.emptyList();
            }
            Collection<List<?>> subjectAltNames = collection;
            boolean bl = false;
            List result = new ArrayList();
            for (List<?> subjectAltName : subjectAltNames) {
                Object altName;
                if (subjectAltName == null || subjectAltName.size() < 2 || Intrinsics.areEqual(subjectAltName.get(0), (Object)type) ^ true) continue;
                if (subjectAltName.get(1) == null) {
                    continue;
                }
                Object obj = altName;
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
                result.add((String)obj);
            }
            return result;
        } catch (CertificateParsingException _) {
            return CollectionsKt.emptyList();
        }
    }

    private OkHostnameVerifier() {
    }

    static {
        OkHostnameVerifier okHostnameVerifier;
        INSTANCE = okHostnameVerifier = new OkHostnameVerifier();
    }
}

