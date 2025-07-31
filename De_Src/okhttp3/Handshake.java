/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3;

import java.io.IOException;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
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
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 &2\u00020\u0001:\u0001&B9\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n\u00a2\u0006\u0002\u0010\u000bJ\r\u0010\u0004\u001a\u00020\u0005H\u0007\u00a2\u0006\u0002\b\u001aJ\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0007\u00a2\u0006\u0002\b J\u000f\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007\u00a2\u0006\u0002\b!J\u0013\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0007\u00a2\u0006\u0002\b\"J\u000f\u0010\u0014\u001a\u0004\u0018\u00010\u000fH\u0007\u00a2\u0006\u0002\b#J\r\u0010\u0002\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\b$J\b\u0010%\u001a\u00020\u0017H\u0016R\u0013\u0010\u0004\u001a\u00020\u00058\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\fR\u0019\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\rR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f8G\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010R!\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u00078GX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0011\u0010\rR\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u000f8G\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\u0010R\u0013\u0010\u0002\u001a\u00020\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0015R\u0018\u0010\u0016\u001a\u00020\u0017*\u00020\b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006'"}, d2={"Lokhttp3/Handshake;", "", "tlsVersion", "Lokhttp3/TlsVersion;", "cipherSuite", "Lokhttp3/CipherSuite;", "localCertificates", "", "Ljava/security/cert/Certificate;", "peerCertificatesFn", "Lkotlin/Function0;", "(Lokhttp3/TlsVersion;Lokhttp3/CipherSuite;Ljava/util/List;Lkotlin/jvm/functions/Function0;)V", "()Lokhttp3/CipherSuite;", "()Ljava/util/List;", "localPrincipal", "Ljava/security/Principal;", "()Ljava/security/Principal;", "peerCertificates", "peerCertificates$delegate", "Lkotlin/Lazy;", "peerPrincipal", "()Lokhttp3/TlsVersion;", "name", "", "getName", "(Ljava/security/cert/Certificate;)Ljava/lang/String;", "-deprecated_cipherSuite", "equals", "", "other", "hashCode", "", "-deprecated_localCertificates", "-deprecated_localPrincipal", "-deprecated_peerCertificates", "-deprecated_peerPrincipal", "-deprecated_tlsVersion", "toString", "Companion", "okhttp"})
public final class Handshake {
    @NotNull
    private final Lazy peerCertificates$delegate;
    @NotNull
    private final TlsVersion tlsVersion;
    @NotNull
    private final CipherSuite cipherSuite;
    @NotNull
    private final List<Certificate> localCertificates;
    public static final Companion Companion = new Companion(null);

    @JvmName(name="peerCertificates")
    @NotNull
    public final List<Certificate> peerCertificates() {
        Lazy lazy = this.peerCertificates$delegate;
        Handshake handshake2 = this;
        Object var3_3 = null;
        boolean bl = false;
        return (List)lazy.getValue();
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="tlsVersion"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_tlsVersion")
    @NotNull
    public final TlsVersion -deprecated_tlsVersion() {
        return this.tlsVersion;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="cipherSuite"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_cipherSuite")
    @NotNull
    public final CipherSuite -deprecated_cipherSuite() {
        return this.cipherSuite;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="peerCertificates"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_peerCertificates")
    @NotNull
    public final List<Certificate> -deprecated_peerCertificates() {
        return this.peerCertificates();
    }

    @JvmName(name="peerPrincipal")
    @Nullable
    public final Principal peerPrincipal() {
        Certificate certificate = CollectionsKt.firstOrNull(this.peerCertificates());
        if (!(certificate instanceof X509Certificate)) {
            certificate = null;
        }
        X509Certificate x509Certificate = (X509Certificate)certificate;
        return x509Certificate != null ? x509Certificate.getSubjectX500Principal() : null;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="peerPrincipal"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_peerPrincipal")
    @Nullable
    public final Principal -deprecated_peerPrincipal() {
        return this.peerPrincipal();
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="localCertificates"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_localCertificates")
    @NotNull
    public final List<Certificate> -deprecated_localCertificates() {
        return this.localCertificates;
    }

    @JvmName(name="localPrincipal")
    @Nullable
    public final Principal localPrincipal() {
        Certificate certificate = CollectionsKt.firstOrNull(this.localCertificates);
        if (!(certificate instanceof X509Certificate)) {
            certificate = null;
        }
        X509Certificate x509Certificate = (X509Certificate)certificate;
        return x509Certificate != null ? x509Certificate.getSubjectX500Principal() : null;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="localPrincipal"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_localPrincipal")
    @Nullable
    public final Principal -deprecated_localPrincipal() {
        return this.localPrincipal();
    }

    public boolean equals(@Nullable Object other) {
        return other instanceof Handshake && ((Handshake)other).tlsVersion == this.tlsVersion && Intrinsics.areEqual(((Handshake)other).cipherSuite, this.cipherSuite) && Intrinsics.areEqual(((Handshake)other).peerCertificates(), this.peerCertificates()) && Intrinsics.areEqual(((Handshake)other).localCertificates, this.localCertificates);
    }

    public int hashCode() {
        int result = 17;
        result = 31 * result + this.tlsVersion.hashCode();
        result = 31 * result + this.cipherSuite.hashCode();
        result = 31 * result + ((Object)this.peerCertificates()).hashCode();
        result = 31 * result + ((Object)this.localCertificates).hashCode();
        return result;
    }

    @NotNull
    public String toString() {
        Object object;
        Certificate it;
        Object object2;
        Iterable $this$mapTo$iv$iv;
        Iterable $this$map$iv = this.peerCertificates();
        boolean $i$f$map = false;
        Iterable iterable = $this$map$iv;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        boolean $i$f$mapTo = false;
        for (Object item$iv$iv : $this$mapTo$iv$iv) {
            Certificate certificate = (Certificate)item$iv$iv;
            object2 = destination$iv$iv;
            boolean bl = false;
            object = this.getName(it);
            object2.add(object);
        }
        String peerCertificatesString = ((List)destination$iv$iv).toString();
        $this$map$iv = this.localCertificates;
        object2 = new StringBuilder().append("Handshake{").append("tlsVersion=").append((Object)this.tlsVersion).append(' ').append("cipherSuite=").append(this.cipherSuite).append(' ').append("peerCertificates=").append(peerCertificatesString).append(' ').append("localCertificates=");
        $i$f$map = false;
        $this$mapTo$iv$iv = $this$map$iv;
        destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        $i$f$mapTo = false;
        for (Object item$iv$iv : $this$mapTo$iv$iv) {
            it = (Certificate)item$iv$iv;
            object = destination$iv$iv;
            boolean bl = false;
            String string = this.getName(it);
            object.add(string);
        }
        object = (List)destination$iv$iv;
        return ((StringBuilder)object2).append(object).append('}').toString();
    }

    private final String getName(Certificate $this$name) {
        String string;
        Certificate certificate = $this$name;
        if (certificate instanceof X509Certificate) {
            string = ((Object)((X509Certificate)$this$name).getSubjectDN()).toString();
        } else {
            String string2 = $this$name.getType();
            string = string2;
            Intrinsics.checkNotNullExpressionValue(string2, "type");
        }
        return string;
    }

    @JvmName(name="tlsVersion")
    @NotNull
    public final TlsVersion tlsVersion() {
        return this.tlsVersion;
    }

    @JvmName(name="cipherSuite")
    @NotNull
    public final CipherSuite cipherSuite() {
        return this.cipherSuite;
    }

    @JvmName(name="localCertificates")
    @NotNull
    public final List<Certificate> localCertificates() {
        return this.localCertificates;
    }

    public Handshake(@NotNull TlsVersion tlsVersion, @NotNull CipherSuite cipherSuite, @NotNull List<? extends Certificate> localCertificates, @NotNull Function0<? extends List<? extends Certificate>> peerCertificatesFn) {
        Intrinsics.checkNotNullParameter((Object)tlsVersion, "tlsVersion");
        Intrinsics.checkNotNullParameter(cipherSuite, "cipherSuite");
        Intrinsics.checkNotNullParameter(localCertificates, "localCertificates");
        Intrinsics.checkNotNullParameter(peerCertificatesFn, "peerCertificatesFn");
        this.tlsVersion = tlsVersion;
        this.cipherSuite = cipherSuite;
        this.localCertificates = localCertificates;
        this.peerCertificates$delegate = LazyKt.lazy((Function0)new Function0<List<? extends Certificate>>(peerCertificatesFn){
            final /* synthetic */ Function0 $peerCertificatesFn;

            @NotNull
            public final List<Certificate> invoke() {
                List<Certificate> list;
                try {
                    list = (List<Certificate>)this.$peerCertificatesFn.invoke();
                } catch (SSLPeerUnverifiedException spue) {
                    boolean bl = false;
                    list = CollectionsKt.emptyList();
                }
                return list;
            }
            {
                this.$peerCertificatesFn = function0;
                super(0);
            }
        });
    }

    @JvmStatic
    @JvmName(name="get")
    @NotNull
    public static final Handshake get(@NotNull SSLSession $this$handshake) throws IOException {
        return Companion.get($this$handshake);
    }

    @JvmStatic
    @NotNull
    public static final Handshake get(@NotNull TlsVersion tlsVersion, @NotNull CipherSuite cipherSuite, @NotNull List<? extends Certificate> peerCertificates2, @NotNull List<? extends Certificate> localCertificates) {
        return Companion.get(tlsVersion, cipherSuite, peerCertificates2, localCertificates);
    }

    /*
     * Illegal identifiers - consider using --renameillegalidents true
     */
    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a2\u0006\u0002\b\u0007J4\u0010\u0003\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0007J\u0011\u0010\u0010\u001a\u00020\u0004*\u00020\u0006H\u0007\u00a2\u0006\u0002\b\u0003J!\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\r*\f\u0012\u0006\b\u0001\u0012\u00020\u000e\u0018\u00010\u0012H\u0002\u00a2\u0006\u0002\u0010\u0013\u00a8\u0006\u0014"}, d2={"Lokhttp3/Handshake$Companion;", "", "()V", "get", "Lokhttp3/Handshake;", "sslSession", "Ljavax/net/ssl/SSLSession;", "-deprecated_get", "tlsVersion", "Lokhttp3/TlsVersion;", "cipherSuite", "Lokhttp3/CipherSuite;", "peerCertificates", "", "Ljava/security/cert/Certificate;", "localCertificates", "handshake", "toImmutableList", "", "([Ljava/security/cert/Certificate;)Ljava/util/List;", "okhttp"})
    public static final class Companion {
        /*
         * WARNING - void declaration
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @JvmStatic
        @JvmName(name="get")
        @NotNull
        public final Handshake get(@NotNull SSLSession $this$handshake) throws IOException {
            void var7_16;
            String cipherSuiteString;
            Intrinsics.checkNotNullParameter($this$handshake, "$this$handshake");
            String string = $this$handshake.getCipherSuite();
            boolean bl = false;
            boolean bl2 = false;
            if (string == null) {
                boolean bl3 = false;
                String string2 = "cipherSuite == null";
                throw (Throwable)new IllegalStateException(string2.toString());
            }
            String string3 = cipherSuiteString = string;
            switch (string3.hashCode()) {
                case 1208658923: {
                    if (!string3.equals("SSL_NULL_WITH_NULL_NULL")) break;
                    throw (Throwable)new IOException("cipherSuite == " + cipherSuiteString);
                }
                case 1019404634: {
                    if (!string3.equals("TLS_NULL_WITH_NULL_NULL")) break;
                    throw (Throwable)new IOException("cipherSuite == " + cipherSuiteString);
                }
            }
            CipherSuite cipherSuite = CipherSuite.Companion.forJavaName(cipherSuiteString);
            String string4 = $this$handshake.getProtocol();
            boolean bl4 = false;
            boolean bl5 = false;
            if (string4 == null) {
                boolean bl6 = false;
                String string5 = "tlsVersion == null";
                throw (Throwable)new IllegalStateException(string5.toString());
            }
            String tlsVersionString = string4;
            if (Intrinsics.areEqual("NONE", tlsVersionString)) {
                throw (Throwable)new IOException("tlsVersion == NONE");
            }
            TlsVersion tlsVersion = TlsVersion.Companion.forJavaName(tlsVersionString);
            try {
                List<Certificate> list = this.toImmutableList($this$handshake.getPeerCertificates());
            } catch (SSLPeerUnverifiedException _) {
                boolean bl7 = false;
                List list = CollectionsKt.emptyList();
            }
            void var6_10 = var7_16;
            return new Handshake(tlsVersion, cipherSuite, this.toImmutableList($this$handshake.getLocalCertificates()), (Function0<? extends List<? extends Certificate>>)new Function0<List<? extends Certificate>>((List)var6_10){
                final /* synthetic */ List $peerCertificatesCopy;

                @NotNull
                public final List<Certificate> invoke() {
                    return this.$peerCertificatesCopy;
                }
                {
                    this.$peerCertificatesCopy = list;
                    super(0);
                }
            });
        }

        private final List<Certificate> toImmutableList(Certificate[] $this$toImmutableList) {
            return $this$toImmutableList != null ? Util.immutableListOf(Arrays.copyOf($this$toImmutableList, $this$toImmutableList.length)) : CollectionsKt.emptyList();
        }

        @Deprecated(message="moved to extension function", replaceWith=@ReplaceWith(imports={}, expression="sslSession.handshake()"), level=DeprecationLevel.ERROR)
        @JvmName(name="-deprecated_get")
        @NotNull
        public final Handshake -deprecated_get(@NotNull SSLSession sslSession) throws IOException {
            Intrinsics.checkNotNullParameter(sslSession, "sslSession");
            return this.get(sslSession);
        }

        @JvmStatic
        @NotNull
        public final Handshake get(@NotNull TlsVersion tlsVersion, @NotNull CipherSuite cipherSuite, @NotNull List<? extends Certificate> peerCertificates2, @NotNull List<? extends Certificate> localCertificates) {
            Intrinsics.checkNotNullParameter((Object)tlsVersion, "tlsVersion");
            Intrinsics.checkNotNullParameter(cipherSuite, "cipherSuite");
            Intrinsics.checkNotNullParameter(peerCertificates2, "peerCertificates");
            Intrinsics.checkNotNullParameter(localCertificates, "localCertificates");
            List<? extends Certificate> peerCertificatesCopy = Util.toImmutableList(peerCertificates2);
            return new Handshake(tlsVersion, cipherSuite, Util.toImmutableList(localCertificates), (Function0<? extends List<? extends Certificate>>)new Function0<List<? extends Certificate>>(peerCertificatesCopy){
                final /* synthetic */ List $peerCertificatesCopy;

                @NotNull
                public final List<Certificate> invoke() {
                    return this.$peerCertificatesCopy;
                }
                {
                    this.$peerCertificatesCopy = list;
                    super(0);
                }
            });
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

