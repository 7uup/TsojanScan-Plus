/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.SSLSocket;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.CipherSuite;
import okhttp3.TlsVersion;
import okhttp3.internal.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\t\u0018\u0000 $2\u00020\u0001:\u0002#$B7\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\tJ\u001d\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0003H\u0000\u00a2\u0006\u0002\b\u0017J\u0015\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bH\u0007\u00a2\u0006\u0002\b\u0018J\u0013\u0010\u0019\u001a\u00020\u00032\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u000e\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u0015J\u0018\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0003H\u0002J\r\u0010\u0004\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\b J\u0015\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000bH\u0007\u00a2\u0006\u0002\b!J\b\u0010\"\u001a\u00020\u0007H\u0016R\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b8G\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\rR\u0018\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000eR\u0013\u0010\u0002\u001a\u00020\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u000fR\u0013\u0010\u0004\u001a\u00020\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000fR\u0019\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000b8G\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\rR\u0018\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000e\u00a8\u0006%"}, d2={"Lokhttp3/ConnectionSpec;", "", "isTls", "", "supportsTlsExtensions", "cipherSuitesAsString", "", "", "tlsVersionsAsString", "(ZZ[Ljava/lang/String;[Ljava/lang/String;)V", "cipherSuites", "", "Lokhttp3/CipherSuite;", "()Ljava/util/List;", "[Ljava/lang/String;", "()Z", "tlsVersions", "Lokhttp3/TlsVersion;", "apply", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "isFallback", "apply$okhttp", "-deprecated_cipherSuites", "equals", "other", "hashCode", "", "isCompatible", "socket", "supportedSpec", "-deprecated_supportsTlsExtensions", "-deprecated_tlsVersions", "toString", "Builder", "Companion", "okhttp"})
public final class ConnectionSpec {
    private final boolean isTls;
    private final boolean supportsTlsExtensions;
    private final String[] cipherSuitesAsString;
    private final String[] tlsVersionsAsString;
    private static final CipherSuite[] RESTRICTED_CIPHER_SUITES;
    private static final CipherSuite[] APPROVED_CIPHER_SUITES;
    @JvmField
    @NotNull
    public static final ConnectionSpec RESTRICTED_TLS;
    @JvmField
    @NotNull
    public static final ConnectionSpec MODERN_TLS;
    @JvmField
    @NotNull
    public static final ConnectionSpec COMPATIBLE_TLS;
    @JvmField
    @NotNull
    public static final ConnectionSpec CLEARTEXT;
    public static final Companion Companion;

    /*
     * WARNING - void declaration
     */
    @JvmName(name="cipherSuites")
    @Nullable
    public final List<CipherSuite> cipherSuites() {
        List list;
        if (this.cipherSuitesAsString != null) {
            void $this$mapTo$iv$iv;
            String[] $this$map$iv = this.cipherSuitesAsString;
            boolean $i$f$map = false;
            String[] stringArray = $this$map$iv;
            Collection destination$iv$iv = new ArrayList($this$map$iv.length);
            boolean $i$f$mapTo = false;
            void var6_6 = $this$mapTo$iv$iv;
            int n = ((void)var6_6).length;
            for (int i = 0; i < n; ++i) {
                void it;
                void item$iv$iv;
                void var10_10 = item$iv$iv = var6_6[i];
                Collection collection = destination$iv$iv;
                boolean bl = false;
                CipherSuite cipherSuite = CipherSuite.Companion.forJavaName((String)it);
                collection.add(cipherSuite);
            }
            list = CollectionsKt.toList((List)destination$iv$iv);
        } else {
            list = null;
        }
        return list;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="cipherSuites"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_cipherSuites")
    @Nullable
    public final List<CipherSuite> -deprecated_cipherSuites() {
        return this.cipherSuites();
    }

    /*
     * WARNING - void declaration
     */
    @JvmName(name="tlsVersions")
    @Nullable
    public final List<TlsVersion> tlsVersions() {
        List list;
        if (this.tlsVersionsAsString != null) {
            void $this$mapTo$iv$iv;
            String[] $this$map$iv = this.tlsVersionsAsString;
            boolean $i$f$map = false;
            String[] stringArray = $this$map$iv;
            Collection destination$iv$iv = new ArrayList($this$map$iv.length);
            boolean $i$f$mapTo = false;
            void var6_6 = $this$mapTo$iv$iv;
            int n = ((void)var6_6).length;
            for (int i = 0; i < n; ++i) {
                void it;
                void item$iv$iv;
                void var10_10 = item$iv$iv = var6_6[i];
                Collection collection = destination$iv$iv;
                boolean bl = false;
                TlsVersion tlsVersion = TlsVersion.Companion.forJavaName((String)it);
                collection.add(tlsVersion);
            }
            list = CollectionsKt.toList((List)destination$iv$iv);
        } else {
            list = null;
        }
        return list;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="tlsVersions"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_tlsVersions")
    @Nullable
    public final List<TlsVersion> -deprecated_tlsVersions() {
        return this.tlsVersions();
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="supportsTlsExtensions"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_supportsTlsExtensions")
    public final boolean -deprecated_supportsTlsExtensions() {
        return this.supportsTlsExtensions;
    }

    public final void apply$okhttp(@NotNull SSLSocket sslSocket, boolean isFallback) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        ConnectionSpec specToApply = this.supportedSpec(sslSocket, isFallback);
        if (specToApply.tlsVersions() != null) {
            sslSocket.setEnabledProtocols(specToApply.tlsVersionsAsString);
        }
        if (specToApply.cipherSuites() != null) {
            sslSocket.setEnabledCipherSuites(specToApply.cipherSuitesAsString);
        }
    }

    private final ConnectionSpec supportedSpec(SSLSocket sslSocket, boolean isFallback) {
        String[] stringArray;
        String[] cipherSuitesIntersection;
        String[] stringArray2;
        if (this.cipherSuitesAsString != null) {
            String[] stringArray3 = sslSocket.getEnabledCipherSuites();
            Intrinsics.checkNotNullExpressionValue(stringArray3, "sslSocket.enabledCipherSuites");
            stringArray2 = Util.intersect(stringArray3, this.cipherSuitesAsString, CipherSuite.Companion.getORDER_BY_NAME$okhttp());
        } else {
            stringArray2 = cipherSuitesIntersection = sslSocket.getEnabledCipherSuites();
        }
        if (this.tlsVersionsAsString != null) {
            String[] stringArray4 = sslSocket.getEnabledProtocols();
            Intrinsics.checkNotNullExpressionValue(stringArray4, "sslSocket.enabledProtocols");
            stringArray = Util.intersect(stringArray4, this.tlsVersionsAsString, ComparisonsKt.naturalOrder());
        } else {
            stringArray = sslSocket.getEnabledProtocols();
        }
        String[] tlsVersionsIntersection = stringArray;
        String[] supportedCipherSuites = sslSocket.getSupportedCipherSuites();
        Intrinsics.checkNotNullExpressionValue(supportedCipherSuites, "supportedCipherSuites");
        int indexOfFallbackScsv = Util.indexOf(supportedCipherSuites, "TLS_FALLBACK_SCSV", CipherSuite.Companion.getORDER_BY_NAME$okhttp());
        if (isFallback && indexOfFallbackScsv != -1) {
            Intrinsics.checkNotNullExpressionValue(cipherSuitesIntersection, "cipherSuitesIntersection");
            String string = supportedCipherSuites[indexOfFallbackScsv];
            Intrinsics.checkNotNullExpressionValue(string, "supportedCipherSuites[indexOfFallbackScsv]");
            cipherSuitesIntersection = Util.concat(cipherSuitesIntersection, string);
        }
        Builder builder = new Builder(this);
        Intrinsics.checkNotNullExpressionValue(cipherSuitesIntersection, "cipherSuitesIntersection");
        Builder builder2 = builder.cipherSuites(Arrays.copyOf(cipherSuitesIntersection, cipherSuitesIntersection.length));
        String[] stringArray5 = tlsVersionsIntersection;
        Intrinsics.checkNotNullExpressionValue(tlsVersionsIntersection, "tlsVersionsIntersection");
        return builder2.tlsVersions(Arrays.copyOf(stringArray5, stringArray5.length)).build();
    }

    public final boolean isCompatible(@NotNull SSLSocket socket) {
        Intrinsics.checkNotNullParameter(socket, "socket");
        if (!this.isTls) {
            return false;
        }
        if (this.tlsVersionsAsString != null && !Util.hasIntersection(this.tlsVersionsAsString, socket.getEnabledProtocols(), ComparisonsKt.naturalOrder())) {
            return false;
        }
        return this.cipherSuitesAsString == null || Util.hasIntersection(this.cipherSuitesAsString, socket.getEnabledCipherSuites(), CipherSuite.Companion.getORDER_BY_NAME$okhttp());
    }

    public boolean equals(@Nullable Object other) {
        if (!(other instanceof ConnectionSpec)) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (this.isTls != ((ConnectionSpec)other).isTls) {
            return false;
        }
        if (this.isTls) {
            if (!Arrays.equals(this.cipherSuitesAsString, ((ConnectionSpec)other).cipherSuitesAsString)) {
                return false;
            }
            if (!Arrays.equals(this.tlsVersionsAsString, ((ConnectionSpec)other).tlsVersionsAsString)) {
                return false;
            }
            if (this.supportsTlsExtensions != ((ConnectionSpec)other).supportsTlsExtensions) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int result = 17;
        if (this.isTls) {
            int n;
            int n2;
            boolean bl;
            Object[] objectArray;
            if (this.cipherSuitesAsString != null) {
                objectArray = this.cipherSuitesAsString;
                bl = false;
                n2 = Arrays.hashCode(objectArray);
            } else {
                n2 = 0;
            }
            result = 31 * result + n2;
            if (this.tlsVersionsAsString != null) {
                objectArray = this.tlsVersionsAsString;
                bl = false;
                n = Arrays.hashCode(objectArray);
            } else {
                n = 0;
            }
            result = 31 * result + n;
            result = 31 * result + (this.supportsTlsExtensions ? 0 : 1);
        }
        return result;
    }

    @NotNull
    public String toString() {
        if (!this.isTls) {
            return "ConnectionSpec()";
        }
        return "ConnectionSpec(" + "cipherSuites=" + Objects.toString(this.cipherSuites(), "[all enabled]") + ", " + "tlsVersions=" + Objects.toString(this.tlsVersions(), "[all enabled]") + ", " + "supportsTlsExtensions=" + this.supportsTlsExtensions + ')';
    }

    @JvmName(name="isTls")
    public final boolean isTls() {
        return this.isTls;
    }

    @JvmName(name="supportsTlsExtensions")
    public final boolean supportsTlsExtensions() {
        return this.supportsTlsExtensions;
    }

    public ConnectionSpec(boolean isTls, boolean supportsTlsExtensions, @Nullable String[] cipherSuitesAsString, @Nullable String[] tlsVersionsAsString) {
        this.isTls = isTls;
        this.supportsTlsExtensions = supportsTlsExtensions;
        this.cipherSuitesAsString = cipherSuitesAsString;
        this.tlsVersionsAsString = tlsVersionsAsString;
    }

    static {
        Companion = new Companion(null);
        RESTRICTED_CIPHER_SUITES = new CipherSuite[]{CipherSuite.TLS_AES_128_GCM_SHA256, CipherSuite.TLS_AES_256_GCM_SHA384, CipherSuite.TLS_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256};
        APPROVED_CIPHER_SUITES = new CipherSuite[]{CipherSuite.TLS_AES_128_GCM_SHA256, CipherSuite.TLS_AES_256_GCM_SHA384, CipherSuite.TLS_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA};
        RESTRICTED_TLS = new Builder(true).cipherSuites(Arrays.copyOf(RESTRICTED_CIPHER_SUITES, RESTRICTED_CIPHER_SUITES.length)).tlsVersions(TlsVersion.TLS_1_3, TlsVersion.TLS_1_2).supportsTlsExtensions(true).build();
        MODERN_TLS = new Builder(true).cipherSuites(Arrays.copyOf(APPROVED_CIPHER_SUITES, APPROVED_CIPHER_SUITES.length)).tlsVersions(TlsVersion.TLS_1_3, TlsVersion.TLS_1_2).supportsTlsExtensions(true).build();
        COMPATIBLE_TLS = new Builder(true).cipherSuites(Arrays.copyOf(APPROVED_CIPHER_SUITES, APPROVED_CIPHER_SUITES.length)).tlsVersions(TlsVersion.TLS_1_3, TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0).supportsTlsExtensions(true).build();
        CLEARTEXT = new Builder(false).build();
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0006\u0010\u0019\u001a\u00020\u0000J\u0006\u0010\u001a\u001a\u00020\u0000J\u0006\u0010\u001b\u001a\u00020\u0006J\u001f\u0010\b\u001a\u00020\u00002\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\t\"\u00020\n\u00a2\u0006\u0002\u0010\u001cJ\u001f\u0010\b\u001a\u00020\u00002\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001d0\t\"\u00020\u001d\u00a2\u0006\u0002\u0010\u001eJ\u0010\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0003H\u0007J\u001f\u0010\u0016\u001a\u00020\u00002\u0012\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\t\"\u00020\n\u00a2\u0006\u0002\u0010\u001cJ\u001f\u0010\u0016\u001a\u00020\u00002\u0012\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001f0\t\"\u00020\u001f\u00a2\u0006\u0002\u0010 R$\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0080\u000e\u00a2\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0010\u001a\u00020\u0003X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0012\"\u0004\b\u0015\u0010\u0004R$\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0080\u000e\u00a2\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000e\u00a8\u0006!"}, d2={"Lokhttp3/ConnectionSpec$Builder;", "", "tls", "", "(Z)V", "connectionSpec", "Lokhttp3/ConnectionSpec;", "(Lokhttp3/ConnectionSpec;)V", "cipherSuites", "", "", "getCipherSuites$okhttp", "()[Ljava/lang/String;", "setCipherSuites$okhttp", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "supportsTlsExtensions", "getSupportsTlsExtensions$okhttp", "()Z", "setSupportsTlsExtensions$okhttp", "getTls$okhttp", "setTls$okhttp", "tlsVersions", "getTlsVersions$okhttp", "setTlsVersions$okhttp", "allEnabledCipherSuites", "allEnabledTlsVersions", "build", "([Ljava/lang/String;)Lokhttp3/ConnectionSpec$Builder;", "Lokhttp3/CipherSuite;", "([Lokhttp3/CipherSuite;)Lokhttp3/ConnectionSpec$Builder;", "Lokhttp3/TlsVersion;", "([Lokhttp3/TlsVersion;)Lokhttp3/ConnectionSpec$Builder;", "okhttp"})
    public static final class Builder {
        private boolean tls;
        @Nullable
        private String[] cipherSuites;
        @Nullable
        private String[] tlsVersions;
        private boolean supportsTlsExtensions;

        public final boolean getTls$okhttp() {
            return this.tls;
        }

        public final void setTls$okhttp(boolean bl) {
            this.tls = bl;
        }

        @Nullable
        public final String[] getCipherSuites$okhttp() {
            return this.cipherSuites;
        }

        public final void setCipherSuites$okhttp(@Nullable String[] stringArray) {
            this.cipherSuites = stringArray;
        }

        @Nullable
        public final String[] getTlsVersions$okhttp() {
            return this.tlsVersions;
        }

        public final void setTlsVersions$okhttp(@Nullable String[] stringArray) {
            this.tlsVersions = stringArray;
        }

        public final boolean getSupportsTlsExtensions$okhttp() {
            return this.supportsTlsExtensions;
        }

        public final void setSupportsTlsExtensions$okhttp(boolean bl) {
            this.supportsTlsExtensions = bl;
        }

        @NotNull
        public final Builder allEnabledCipherSuites() {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            boolean bl4 = $this$apply.tls;
            boolean bl5 = false;
            boolean bl6 = false;
            if (!bl4) {
                boolean bl7 = false;
                String string = "no cipher suites for cleartext connections";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            $this$apply.cipherSuites = null;
            return builder;
        }

        /*
         * WARNING - void declaration
         */
        @NotNull
        public final Builder cipherSuites(@NotNull CipherSuite ... cipherSuites) {
            void $this$mapTo$iv$iv;
            Intrinsics.checkNotNullParameter(cipherSuites, "cipherSuites");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            boolean bl4 = $this$apply.tls;
            boolean bl5 = false;
            boolean bl6 = false;
            if (!bl4) {
                boolean bl7 = false;
                String string = "no cipher suites for cleartext connections";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            CipherSuite[] $this$map$iv = cipherSuites;
            boolean $i$f$map = false;
            CipherSuite[] bl7 = $this$map$iv;
            Collection destination$iv$iv = new ArrayList($this$map$iv.length);
            boolean $i$f$mapTo = false;
            void var13_17 = $this$mapTo$iv$iv;
            int n = ((void)var13_17).length;
            for (int i = 0; i < n; ++i) {
                void it;
                void item$iv$iv;
                void var17_21 = item$iv$iv = var13_17[i];
                Collection collection = destination$iv$iv;
                boolean bl8 = false;
                String string = it.javaName();
                collection.add(string);
            }
            Collection $this$toTypedArray$iv = (List)destination$iv$iv;
            boolean $i$f$toTypedArray = false;
            Collection thisCollection$iv = $this$toTypedArray$iv;
            String[] stringArray = thisCollection$iv.toArray(new String[0]);
            if (stringArray == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            String[] strings = stringArray;
            return $this$apply.cipherSuites(Arrays.copyOf(strings, strings.length));
        }

        @NotNull
        public final Builder cipherSuites(@NotNull String ... cipherSuites) {
            Intrinsics.checkNotNullParameter(cipherSuites, "cipherSuites");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            boolean bl4 = $this$apply.tls;
            boolean bl5 = false;
            boolean bl6 = false;
            if (!bl4) {
                boolean bl7 = false;
                String string = "no cipher suites for cleartext connections";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            String[] stringArray = cipherSuites;
            bl5 = false;
            String[] stringArray2 = stringArray;
            boolean bl7 = false;
            boolean bl8 = !(stringArray2.length == 0);
            bl5 = false;
            boolean bl9 = false;
            if (!bl8) {
                boolean bl10 = false;
                String string = "At least one cipher suite is required";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            Object object = cipherSuites.clone();
            if (object == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.String>");
            }
            $this$apply.cipherSuites = (String[])object;
            return builder;
        }

        @NotNull
        public final Builder allEnabledTlsVersions() {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            boolean bl4 = $this$apply.tls;
            boolean bl5 = false;
            boolean bl6 = false;
            if (!bl4) {
                boolean bl7 = false;
                String string = "no TLS versions for cleartext connections";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            $this$apply.tlsVersions = null;
            return builder;
        }

        /*
         * WARNING - void declaration
         */
        @NotNull
        public final Builder tlsVersions(@NotNull TlsVersion ... tlsVersions) {
            void $this$mapTo$iv$iv;
            Intrinsics.checkNotNullParameter(tlsVersions, "tlsVersions");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            boolean bl4 = $this$apply.tls;
            boolean bl5 = false;
            boolean bl6 = false;
            if (!bl4) {
                boolean bl7 = false;
                String string = "no TLS versions for cleartext connections";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            TlsVersion[] $this$map$iv = tlsVersions;
            boolean $i$f$map = false;
            TlsVersion[] bl7 = $this$map$iv;
            Collection destination$iv$iv = new ArrayList($this$map$iv.length);
            boolean $i$f$mapTo = false;
            void var13_17 = $this$mapTo$iv$iv;
            int n = ((void)var13_17).length;
            for (int i = 0; i < n; ++i) {
                void it;
                void item$iv$iv;
                void var17_21 = item$iv$iv = var13_17[i];
                Collection collection = destination$iv$iv;
                boolean bl8 = false;
                String string = it.javaName();
                collection.add(string);
            }
            Collection $this$toTypedArray$iv = (List)destination$iv$iv;
            boolean $i$f$toTypedArray = false;
            Collection thisCollection$iv = $this$toTypedArray$iv;
            String[] stringArray = thisCollection$iv.toArray(new String[0]);
            if (stringArray == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            String[] strings = stringArray;
            return $this$apply.tlsVersions(Arrays.copyOf(strings, strings.length));
        }

        @NotNull
        public final Builder tlsVersions(@NotNull String ... tlsVersions) {
            Intrinsics.checkNotNullParameter(tlsVersions, "tlsVersions");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            boolean bl4 = $this$apply.tls;
            boolean bl5 = false;
            boolean bl6 = false;
            if (!bl4) {
                boolean bl7 = false;
                String string = "no TLS versions for cleartext connections";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            String[] stringArray = tlsVersions;
            bl5 = false;
            String[] stringArray2 = stringArray;
            boolean bl7 = false;
            boolean bl8 = !(stringArray2.length == 0);
            bl5 = false;
            boolean bl9 = false;
            if (!bl8) {
                boolean bl10 = false;
                String string = "At least one TLS version is required";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            Object object = tlsVersions.clone();
            if (object == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.String>");
            }
            $this$apply.tlsVersions = (String[])object;
            return builder;
        }

        @Deprecated(message="since OkHttp 3.13 all TLS-connections are expected to support TLS extensions.\nIn a future release setting this to true will be unnecessary and setting it to false\nwill have no effect.")
        @NotNull
        public final Builder supportsTlsExtensions(boolean supportsTlsExtensions) {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            boolean bl4 = $this$apply.tls;
            boolean bl5 = false;
            boolean bl6 = false;
            if (!bl4) {
                boolean bl7 = false;
                String string = "no TLS extensions for cleartext connections";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            $this$apply.supportsTlsExtensions = supportsTlsExtensions;
            return builder;
        }

        @NotNull
        public final ConnectionSpec build() {
            return new ConnectionSpec(this.tls, this.supportsTlsExtensions, this.cipherSuites, this.tlsVersions);
        }

        public Builder(boolean tls) {
            this.tls = tls;
        }

        public Builder(@NotNull ConnectionSpec connectionSpec) {
            Intrinsics.checkNotNullParameter(connectionSpec, "connectionSpec");
            this.tls = connectionSpec.isTls();
            this.cipherSuites = connectionSpec.cipherSuitesAsString;
            this.tlsVersions = connectionSpec.tlsVersionsAsString;
            this.supportsTlsExtensions = connectionSpec.supportsTlsExtensions();
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\f\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2={"Lokhttp3/ConnectionSpec$Companion;", "", "()V", "APPROVED_CIPHER_SUITES", "", "Lokhttp3/CipherSuite;", "[Lokhttp3/CipherSuite;", "CLEARTEXT", "Lokhttp3/ConnectionSpec;", "COMPATIBLE_TLS", "MODERN_TLS", "RESTRICTED_CIPHER_SUITES", "RESTRICTED_TLS", "okhttp"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

