/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3;

import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.HostnamesKt;
import okhttp3.internal.tls.CertificateChainCleaner;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 \"2\u00020\u0001:\u0003!\"#B!\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007J)\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011H\u0000\u00a2\u0006\u0002\b\u0014J)\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170\u0016\"\u00020\u0017H\u0007\u00a2\u0006\u0002\u0010\u0018J\u001c\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0012J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u001d\u001a\u00020\u001eH\u0016J\u0015\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006H\u0000\u00a2\u0006\u0002\b R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006$"}, d2={"Lokhttp3/CertificatePinner;", "", "pins", "", "Lokhttp3/CertificatePinner$Pin;", "certificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "(Ljava/util/Set;Lokhttp3/internal/tls/CertificateChainCleaner;)V", "getCertificateChainCleaner$okhttp", "()Lokhttp3/internal/tls/CertificateChainCleaner;", "getPins", "()Ljava/util/Set;", "check", "", "hostname", "", "cleanedPeerCertificatesFn", "Lkotlin/Function0;", "", "Ljava/security/cert/X509Certificate;", "check$okhttp", "peerCertificates", "", "Ljava/security/cert/Certificate;", "(Ljava/lang/String;[Ljava/security/cert/Certificate;)V", "equals", "", "other", "findMatchingPins", "hashCode", "", "withCertificateChainCleaner", "withCertificateChainCleaner$okhttp", "Builder", "Companion", "Pin", "okhttp"})
public final class CertificatePinner {
    @NotNull
    private final Set<Pin> pins;
    @Nullable
    private final CertificateChainCleaner certificateChainCleaner;
    @JvmField
    @NotNull
    public static final CertificatePinner DEFAULT;
    public static final Companion Companion;

    public final void check(@NotNull String hostname, @NotNull List<? extends Certificate> peerCertificates2) throws SSLPeerUnverifiedException {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        Intrinsics.checkNotNullParameter(peerCertificates2, "peerCertificates");
        this.check$okhttp(hostname, (Function0<? extends List<? extends X509Certificate>>)new Function0<List<? extends X509Certificate>>(this, peerCertificates2, hostname){
            final /* synthetic */ CertificatePinner this$0;
            final /* synthetic */ List $peerCertificates;
            final /* synthetic */ String $hostname;

            /*
             * WARNING - void declaration
             */
            @NotNull
            public final List<X509Certificate> invoke() {
                void $this$mapTo$iv$iv;
                Object object = this.this$0.getCertificateChainCleaner$okhttp();
                if (object == null || (object = ((CertificateChainCleaner)object).clean(this.$peerCertificates, this.$hostname)) == null) {
                    object = this.$peerCertificates;
                }
                Iterable $this$map$iv = (Iterable)object;
                boolean $i$f$map = false;
                Iterable iterable = $this$map$iv;
                Collection destination$iv$iv = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                boolean $i$f$mapTo = false;
                for (T item$iv$iv : $this$mapTo$iv$iv) {
                    void it;
                    Certificate certificate = (Certificate)item$iv$iv;
                    Collection collection = destination$iv$iv;
                    boolean bl = false;
                    void v1 = it;
                    if (v1 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");
                    }
                    X509Certificate x509Certificate = (X509Certificate)v1;
                    collection.add(x509Certificate);
                }
                return (List)destination$iv$iv;
            }
            {
                this.this$0 = certificatePinner;
                this.$peerCertificates = list;
                this.$hostname = string;
                super(0);
            }
        });
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final void check$okhttp(@NotNull String hostname, @NotNull Function0<? extends List<? extends X509Certificate>> cleanedPeerCertificatesFn) {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        Intrinsics.checkNotNullParameter(cleanedPeerCertificatesFn, "cleanedPeerCertificatesFn");
        List<Pin> pins = this.findMatchingPins(hostname);
        if (pins.isEmpty()) {
            return;
        }
        List<? extends X509Certificate> peerCertificates2 = cleanedPeerCertificatesFn.invoke();
        for (X509Certificate x509Certificate : peerCertificates2) {
            ByteString sha1 = null;
            ByteString sha256 = null;
            block5: for (Pin pin : pins) {
                String string = pin.getHashAlgorithm();
                switch (string.hashCode()) {
                    case 3528965: {
                        if (!string.equals("sha1")) throw (Throwable)((Object)new AssertionError((Object)("unsupported hashAlgorithm: " + pin.getHashAlgorithm())));
                        break;
                    }
                    case -903629273: {
                        if (!string.equals("sha256")) throw (Throwable)((Object)new AssertionError((Object)("unsupported hashAlgorithm: " + pin.getHashAlgorithm())));
                        if (sha256 == null) {
                            sha256 = Companion.sha256Hash(x509Certificate);
                        }
                        if (!Intrinsics.areEqual(pin.getHash(), sha256)) continue block5;
                        return;
                    }
                }
                if (sha1 == null) {
                    sha1 = Companion.sha1Hash(x509Certificate);
                }
                if (!Intrinsics.areEqual(pin.getHash(), sha1)) continue;
                return;
                throw (Throwable)((Object)new AssertionError((Object)("unsupported hashAlgorithm: " + pin.getHashAlgorithm())));
            }
        }
        boolean bl = false;
        boolean bl2 = false;
        StringBuilder stringBuilder = new StringBuilder();
        boolean bl3 = false;
        boolean bl4 = false;
        StringBuilder $this$buildString = stringBuilder;
        boolean bl5 = false;
        $this$buildString.append("Certificate pinning failure!");
        $this$buildString.append("\n  Peer certificate chain:");
        for (X509Certificate x509Certificate : peerCertificates2) {
            $this$buildString.append("\n    ");
            $this$buildString.append(Companion.pin(x509Certificate));
            $this$buildString.append(": ");
            Principal principal = x509Certificate.getSubjectDN();
            Intrinsics.checkNotNullExpressionValue(principal, "element.subjectDN");
            $this$buildString.append(principal.getName());
        }
        $this$buildString.append("\n  Pinned certificates for ");
        $this$buildString.append(hostname);
        $this$buildString.append(":");
        for (Pin pin : pins) {
            $this$buildString.append("\n    ");
            $this$buildString.append(pin);
        }
        String string = stringBuilder.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        String string2 = string;
        throw (Throwable)new SSLPeerUnverifiedException(string2);
    }

    @Deprecated(message="replaced with {@link #check(String, List)}.", replaceWith=@ReplaceWith(imports={}, expression="check(hostname, peerCertificates.toList())"))
    public final void check(@NotNull String hostname, @NotNull Certificate ... peerCertificates2) throws SSLPeerUnverifiedException {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        Intrinsics.checkNotNullParameter(peerCertificates2, "peerCertificates");
        this.check(hostname, ArraysKt.toList(peerCertificates2));
    }

    @NotNull
    public final List<Pin> findMatchingPins(@NotNull String hostname) {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        Iterable $this$filterList$iv = this.pins;
        boolean $i$f$filterList = false;
        List result$iv = CollectionsKt.emptyList();
        for (Object i$iv : $this$filterList$iv) {
            Pin $this$filterList = (Pin)i$iv;
            boolean bl = false;
            if (!$this$filterList.matchesHostname(hostname)) continue;
            if (result$iv.isEmpty()) {
                boolean bl2 = false;
                result$iv = new ArrayList();
            }
            List list = result$iv;
            if (list == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableList<T>");
            }
            TypeIntrinsics.asMutableList(list).add(i$iv);
        }
        return result$iv;
    }

    @NotNull
    public final CertificatePinner withCertificateChainCleaner$okhttp(@NotNull CertificateChainCleaner certificateChainCleaner) {
        Intrinsics.checkNotNullParameter(certificateChainCleaner, "certificateChainCleaner");
        return Intrinsics.areEqual(this.certificateChainCleaner, certificateChainCleaner) ? this : new CertificatePinner(this.pins, certificateChainCleaner);
    }

    public boolean equals(@Nullable Object other) {
        return other instanceof CertificatePinner && Intrinsics.areEqual(((CertificatePinner)other).pins, this.pins) && Intrinsics.areEqual(((CertificatePinner)other).certificateChainCleaner, this.certificateChainCleaner);
    }

    public int hashCode() {
        int result = 37;
        result = 41 * result + ((Object)this.pins).hashCode();
        CertificateChainCleaner certificateChainCleaner = this.certificateChainCleaner;
        boolean bl = false;
        CertificateChainCleaner certificateChainCleaner2 = certificateChainCleaner;
        result = 41 * result + (certificateChainCleaner2 != null ? certificateChainCleaner2.hashCode() : 0);
        return result;
    }

    @NotNull
    public final Set<Pin> getPins() {
        return this.pins;
    }

    @Nullable
    public final CertificateChainCleaner getCertificateChainCleaner$okhttp() {
        return this.certificateChainCleaner;
    }

    public CertificatePinner(@NotNull Set<Pin> pins, @Nullable CertificateChainCleaner certificateChainCleaner) {
        Intrinsics.checkNotNullParameter(pins, "pins");
        this.pins = pins;
        this.certificateChainCleaner = certificateChainCleaner;
    }

    public /* synthetic */ CertificatePinner(Set set, CertificateChainCleaner certificateChainCleaner, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 2) != 0) {
            certificateChainCleaner = null;
        }
        this(set, certificateChainCleaner);
    }

    static {
        Companion = new Companion(null);
        DEFAULT = new Builder().build();
    }

    @JvmStatic
    @NotNull
    public static final ByteString sha1Hash(@NotNull X509Certificate $this$sha1Hash) {
        return Companion.sha1Hash($this$sha1Hash);
    }

    @JvmStatic
    @NotNull
    public static final ByteString sha256Hash(@NotNull X509Certificate $this$sha256Hash) {
        return Companion.sha256Hash($this$sha256Hash);
    }

    @JvmStatic
    @NotNull
    public static final String pin(@NotNull Certificate certificate) {
        return Companion.pin(certificate);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u000e\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0003J\b\u0010\u0018\u001a\u00020\u0003H\u0016R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f\u00a8\u0006\u0019"}, d2={"Lokhttp3/CertificatePinner$Pin;", "", "pattern", "", "pin", "(Ljava/lang/String;Ljava/lang/String;)V", "hash", "Lokio/ByteString;", "getHash", "()Lokio/ByteString;", "hashAlgorithm", "getHashAlgorithm", "()Ljava/lang/String;", "getPattern", "equals", "", "other", "hashCode", "", "matchesCertificate", "certificate", "Ljava/security/cert/X509Certificate;", "matchesHostname", "hostname", "toString", "okhttp"})
    public static final class Pin {
        @NotNull
        private final String pattern;
        @NotNull
        private final String hashAlgorithm;
        @NotNull
        private final ByteString hash;

        @NotNull
        public final String getPattern() {
            return this.pattern;
        }

        @NotNull
        public final String getHashAlgorithm() {
            return this.hashAlgorithm;
        }

        @NotNull
        public final ByteString getHash() {
            return this.hash;
        }

        public final boolean matchesHostname(@NotNull String hostname) {
            boolean bl;
            Intrinsics.checkNotNullParameter(hostname, "hostname");
            if (StringsKt.startsWith$default(this.pattern, "**.", false, 2, null)) {
                int suffixLength = this.pattern.length() - 3;
                int prefixLength = hostname.length() - suffixLength;
                bl = StringsKt.regionMatches$default(hostname, hostname.length() - suffixLength, this.pattern, 3, suffixLength, false, 16, null) && (prefixLength == 0 || hostname.charAt(prefixLength - 1) == '.');
            } else if (StringsKt.startsWith$default(this.pattern, "*.", false, 2, null)) {
                int suffixLength = this.pattern.length() - 1;
                int prefixLength = hostname.length() - suffixLength;
                bl = StringsKt.regionMatches$default(hostname, hostname.length() - suffixLength, this.pattern, 1, suffixLength, false, 16, null) && StringsKt.lastIndexOf$default((CharSequence)hostname, '.', prefixLength - 1, false, 4, null) == -1;
            } else {
                bl = Intrinsics.areEqual(hostname, this.pattern);
            }
            return bl;
        }

        /*
         * WARNING - bad return control flow
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public final boolean matchesCertificate(@NotNull X509Certificate certificate) {
            boolean bl;
            Intrinsics.checkNotNullParameter(certificate, "certificate");
            String string = this.hashAlgorithm;
            switch (string.hashCode()) {
                case 3528965: {
                    if (!string.equals("sha1")) return false;
                    break;
                }
                case -903629273: {
                    if (!string.equals("sha256")) return false;
                    bl = Intrinsics.areEqual(this.hash, Companion.sha256Hash(certificate));
                    return bl;
                }
            }
            bl = Intrinsics.areEqual(this.hash, Companion.sha1Hash(certificate));
            return bl;
            return false;
        }

        @NotNull
        public String toString() {
            return this.hashAlgorithm + '/' + this.hash.base64();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Pin)) {
                return false;
            }
            if (Intrinsics.areEqual(this.pattern, ((Pin)other).pattern) ^ true) {
                return false;
            }
            if (Intrinsics.areEqual(this.hashAlgorithm, ((Pin)other).hashAlgorithm) ^ true) {
                return false;
            }
            return !(Intrinsics.areEqual(this.hash, ((Pin)other).hash) ^ true);
        }

        public int hashCode() {
            int result = this.pattern.hashCode();
            result = 31 * result + this.hashAlgorithm.hashCode();
            result = 31 * result + this.hash.hashCode();
            return result;
        }

        public Pin(@NotNull String pattern, @NotNull String pin) {
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            Intrinsics.checkNotNullParameter(pin, "pin");
            boolean bl = StringsKt.startsWith$default(pattern, "*.", false, 2, null) && StringsKt.indexOf$default((CharSequence)pattern, "*", 1, false, 4, null) == -1 || StringsKt.startsWith$default(pattern, "**.", false, 2, null) && StringsKt.indexOf$default((CharSequence)pattern, "*", 2, false, 4, null) == -1 || StringsKt.indexOf$default((CharSequence)pattern, "*", 0, false, 6, null) == -1;
            int n = 0;
            boolean bl2 = false;
            if (!bl) {
                boolean bl3 = false;
                String string = "Unexpected pattern: " + pattern;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            String string = HostnamesKt.toCanonicalHost(pattern);
            if (string == null) {
                throw (Throwable)new IllegalArgumentException("Invalid pattern: " + pattern);
            }
            this.pattern = string;
            if (StringsKt.startsWith$default(pin, "sha1/", false, 2, null)) {
                this.hashAlgorithm = "sha1";
                String string2 = pin;
                n = "sha1/".length();
                bl2 = false;
                String string3 = string2.substring(n);
                Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.String).substring(startIndex)");
                ByteString byteString = ByteString.Companion.decodeBase64(string3);
                if (byteString == null) {
                    throw (Throwable)new IllegalArgumentException("Invalid pin hash: " + pin);
                }
                this.hash = byteString;
            } else if (StringsKt.startsWith$default(pin, "sha256/", false, 2, null)) {
                this.hashAlgorithm = "sha256";
                String string4 = pin;
                n = "sha256/".length();
                bl2 = false;
                String string5 = string4.substring(n);
                Intrinsics.checkNotNullExpressionValue(string5, "(this as java.lang.String).substring(startIndex)");
                ByteString byteString = ByteString.Companion.decodeBase64(string5);
                if (byteString == null) {
                    throw (Throwable)new IllegalArgumentException("Invalid pin hash: " + pin);
                }
                this.hash = byteString;
            } else {
                throw (Throwable)new IllegalArgumentException("pins must start with 'sha256/' or 'sha1/': " + pin);
            }
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J'\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u000b\"\u00020\n\u00a2\u0006\u0002\u0010\fJ\u0006\u0010\r\u001a\u00020\u000eR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u000f"}, d2={"Lokhttp3/CertificatePinner$Builder;", "", "()V", "pins", "", "Lokhttp3/CertificatePinner$Pin;", "getPins", "()Ljava/util/List;", "add", "pattern", "", "", "(Ljava/lang/String;[Ljava/lang/String;)Lokhttp3/CertificatePinner$Builder;", "build", "Lokhttp3/CertificatePinner;", "okhttp"})
    public static final class Builder {
        @NotNull
        private final List<Pin> pins;

        @NotNull
        public final List<Pin> getPins() {
            return this.pins;
        }

        @NotNull
        public final Builder add(@NotNull String pattern, @NotNull String ... pins) {
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            Intrinsics.checkNotNullParameter(pins, "pins");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            for (String pin : pins) {
                $this$apply.pins.add(new Pin(pattern, pin));
            }
            return builder;
        }

        @NotNull
        public final CertificatePinner build() {
            return new CertificatePinner(CollectionsKt.toSet((Iterable)this.pins), null, 2, null);
        }

        public Builder() {
            boolean bl = false;
            this.pins = new ArrayList();
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\f\u0010\t\u001a\u00020\n*\u00020\u000bH\u0007J\f\u0010\f\u001a\u00020\n*\u00020\u000bH\u0007R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2={"Lokhttp3/CertificatePinner$Companion;", "", "()V", "DEFAULT", "Lokhttp3/CertificatePinner;", "pin", "", "certificate", "Ljava/security/cert/Certificate;", "sha1Hash", "Lokio/ByteString;", "Ljava/security/cert/X509Certificate;", "sha256Hash", "okhttp"})
    public static final class Companion {
        @JvmStatic
        @NotNull
        public final ByteString sha1Hash(@NotNull X509Certificate $this$sha1Hash) {
            Intrinsics.checkNotNullParameter($this$sha1Hash, "$this$sha1Hash");
            PublicKey publicKey = $this$sha1Hash.getPublicKey();
            Intrinsics.checkNotNullExpressionValue(publicKey, "publicKey");
            byte[] byArray = publicKey.getEncoded();
            Intrinsics.checkNotNullExpressionValue(byArray, "publicKey.encoded");
            return ByteString.Companion.of$default(ByteString.Companion, byArray, 0, 0, 3, null).sha1();
        }

        @JvmStatic
        @NotNull
        public final ByteString sha256Hash(@NotNull X509Certificate $this$sha256Hash) {
            Intrinsics.checkNotNullParameter($this$sha256Hash, "$this$sha256Hash");
            PublicKey publicKey = $this$sha256Hash.getPublicKey();
            Intrinsics.checkNotNullExpressionValue(publicKey, "publicKey");
            byte[] byArray = publicKey.getEncoded();
            Intrinsics.checkNotNullExpressionValue(byArray, "publicKey.encoded");
            return ByteString.Companion.of$default(ByteString.Companion, byArray, 0, 0, 3, null).sha256();
        }

        @JvmStatic
        @NotNull
        public final String pin(@NotNull Certificate certificate) {
            Intrinsics.checkNotNullParameter(certificate, "certificate");
            boolean bl = certificate instanceof X509Certificate;
            boolean bl2 = false;
            boolean bl3 = false;
            if (!bl) {
                boolean bl4 = false;
                String string = "Certificate pinning requires X509 certificates";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            return "sha256/" + this.sha256Hash((X509Certificate)certificate).base64();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

