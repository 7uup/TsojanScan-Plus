/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import java.util.Objects;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Authenticator;
import okhttp3.CertificatePinner;
import okhttp3.ConnectionSpec;
import okhttp3.Dns;
import okhttp3.HttpUrl;
import okhttp3.Protocol;
import okhttp3.internal.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000f\u0018\u00002\u00020\u0001By\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015\u0012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0015\u0012\u0006\u0010\u0019\u001a\u00020\u001a\u00a2\u0006\u0002\u0010\u001bJ\u000f\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007\u00a2\u0006\u0002\b(J\u0013\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0015H\u0007\u00a2\u0006\u0002\b)J\r\u0010\u0006\u001a\u00020\u0007H\u0007\u00a2\u0006\u0002\b*J\u0013\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\u0015\u0010.\u001a\u00020,2\u0006\u0010/\u001a\u00020\u0000H\u0000\u00a2\u0006\u0002\b0J\b\u00101\u001a\u00020\u0005H\u0016J\u000f\u0010\f\u001a\u0004\u0018\u00010\rH\u0007\u00a2\u0006\u0002\b2J\u0013\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0007\u00a2\u0006\u0002\b3J\u000f\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0007\u00a2\u0006\u0002\b4J\r\u0010\u0010\u001a\u00020\u0011H\u0007\u00a2\u0006\u0002\b5J\r\u0010\u0019\u001a\u00020\u001aH\u0007\u00a2\u0006\u0002\b6J\r\u0010\b\u001a\u00020\tH\u0007\u00a2\u0006\u0002\b7J\u000f\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007\u00a2\u0006\u0002\b8J\b\u00109\u001a\u00020\u0003H\u0016J\r\u0010%\u001a\u00020&H\u0007\u00a2\u0006\u0002\b:R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u000f8\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u001cR\u0019\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u00158G\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u001dR\u0013\u0010\u0006\u001a\u00020\u00078\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u001eR\u0015\u0010\f\u001a\u0004\u0018\u00010\r8\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u001fR\u0019\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158G\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u001dR\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u00138\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010 R\u0013\u0010\u0010\u001a\u00020\u00118\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010!R\u0013\u0010\u0019\u001a\u00020\u001a8\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\"R\u0013\u0010\b\u001a\u00020\t8\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010#R\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010$R\u0013\u0010%\u001a\u00020&8G\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010'\u00a8\u0006;"}, d2={"Lokhttp3/Address;", "", "uriHost", "", "uriPort", "", "dns", "Lokhttp3/Dns;", "socketFactory", "Ljavax/net/SocketFactory;", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "hostnameVerifier", "Ljavax/net/ssl/HostnameVerifier;", "certificatePinner", "Lokhttp3/CertificatePinner;", "proxyAuthenticator", "Lokhttp3/Authenticator;", "proxy", "Ljava/net/Proxy;", "protocols", "", "Lokhttp3/Protocol;", "connectionSpecs", "Lokhttp3/ConnectionSpec;", "proxySelector", "Ljava/net/ProxySelector;", "(Ljava/lang/String;ILokhttp3/Dns;Ljavax/net/SocketFactory;Ljavax/net/ssl/SSLSocketFactory;Ljavax/net/ssl/HostnameVerifier;Lokhttp3/CertificatePinner;Lokhttp3/Authenticator;Ljava/net/Proxy;Ljava/util/List;Ljava/util/List;Ljava/net/ProxySelector;)V", "()Lokhttp3/CertificatePinner;", "()Ljava/util/List;", "()Lokhttp3/Dns;", "()Ljavax/net/ssl/HostnameVerifier;", "()Ljava/net/Proxy;", "()Lokhttp3/Authenticator;", "()Ljava/net/ProxySelector;", "()Ljavax/net/SocketFactory;", "()Ljavax/net/ssl/SSLSocketFactory;", "url", "Lokhttp3/HttpUrl;", "()Lokhttp3/HttpUrl;", "-deprecated_certificatePinner", "-deprecated_connectionSpecs", "-deprecated_dns", "equals", "", "other", "equalsNonHost", "that", "equalsNonHost$okhttp", "hashCode", "-deprecated_hostnameVerifier", "-deprecated_protocols", "-deprecated_proxy", "-deprecated_proxyAuthenticator", "-deprecated_proxySelector", "-deprecated_socketFactory", "-deprecated_sslSocketFactory", "toString", "-deprecated_url", "okhttp"})
public final class Address {
    @NotNull
    private final HttpUrl url;
    @NotNull
    private final List<Protocol> protocols;
    @NotNull
    private final List<ConnectionSpec> connectionSpecs;
    @NotNull
    private final Dns dns;
    @NotNull
    private final SocketFactory socketFactory;
    @Nullable
    private final SSLSocketFactory sslSocketFactory;
    @Nullable
    private final HostnameVerifier hostnameVerifier;
    @Nullable
    private final CertificatePinner certificatePinner;
    @NotNull
    private final Authenticator proxyAuthenticator;
    @Nullable
    private final Proxy proxy;
    @NotNull
    private final ProxySelector proxySelector;

    @JvmName(name="url")
    @NotNull
    public final HttpUrl url() {
        return this.url;
    }

    @JvmName(name="protocols")
    @NotNull
    public final List<Protocol> protocols() {
        return this.protocols;
    }

    @JvmName(name="connectionSpecs")
    @NotNull
    public final List<ConnectionSpec> connectionSpecs() {
        return this.connectionSpecs;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="url"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_url")
    @NotNull
    public final HttpUrl -deprecated_url() {
        return this.url;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="dns"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_dns")
    @NotNull
    public final Dns -deprecated_dns() {
        return this.dns;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="socketFactory"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_socketFactory")
    @NotNull
    public final SocketFactory -deprecated_socketFactory() {
        return this.socketFactory;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="proxyAuthenticator"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_proxyAuthenticator")
    @NotNull
    public final Authenticator -deprecated_proxyAuthenticator() {
        return this.proxyAuthenticator;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="protocols"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_protocols")
    @NotNull
    public final List<Protocol> -deprecated_protocols() {
        return this.protocols;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="connectionSpecs"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_connectionSpecs")
    @NotNull
    public final List<ConnectionSpec> -deprecated_connectionSpecs() {
        return this.connectionSpecs;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="proxySelector"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_proxySelector")
    @NotNull
    public final ProxySelector -deprecated_proxySelector() {
        return this.proxySelector;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="proxy"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_proxy")
    @Nullable
    public final Proxy -deprecated_proxy() {
        return this.proxy;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="sslSocketFactory"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_sslSocketFactory")
    @Nullable
    public final SSLSocketFactory -deprecated_sslSocketFactory() {
        return this.sslSocketFactory;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="hostnameVerifier"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_hostnameVerifier")
    @Nullable
    public final HostnameVerifier -deprecated_hostnameVerifier() {
        return this.hostnameVerifier;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="certificatePinner"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_certificatePinner")
    @Nullable
    public final CertificatePinner -deprecated_certificatePinner() {
        return this.certificatePinner;
    }

    public boolean equals(@Nullable Object other) {
        return other instanceof Address && Intrinsics.areEqual(this.url, ((Address)other).url) && this.equalsNonHost$okhttp((Address)other);
    }

    public int hashCode() {
        int result = 17;
        result = 31 * result + this.url.hashCode();
        result = 31 * result + this.dns.hashCode();
        result = 31 * result + this.proxyAuthenticator.hashCode();
        result = 31 * result + ((Object)this.protocols).hashCode();
        result = 31 * result + ((Object)this.connectionSpecs).hashCode();
        result = 31 * result + this.proxySelector.hashCode();
        result = 31 * result + Objects.hashCode(this.proxy);
        result = 31 * result + Objects.hashCode(this.sslSocketFactory);
        result = 31 * result + Objects.hashCode(this.hostnameVerifier);
        result = 31 * result + Objects.hashCode(this.certificatePinner);
        return result;
    }

    public final boolean equalsNonHost$okhttp(@NotNull Address that) {
        Intrinsics.checkNotNullParameter(that, "that");
        return Intrinsics.areEqual(this.dns, that.dns) && Intrinsics.areEqual(this.proxyAuthenticator, that.proxyAuthenticator) && Intrinsics.areEqual(this.protocols, that.protocols) && Intrinsics.areEqual(this.connectionSpecs, that.connectionSpecs) && Intrinsics.areEqual(this.proxySelector, that.proxySelector) && Intrinsics.areEqual(this.proxy, that.proxy) && Intrinsics.areEqual(this.sslSocketFactory, that.sslSocketFactory) && Intrinsics.areEqual(this.hostnameVerifier, that.hostnameVerifier) && Intrinsics.areEqual(this.certificatePinner, that.certificatePinner) && this.url.port() == that.url.port();
    }

    @NotNull
    public String toString() {
        return "Address{" + this.url.host() + ':' + this.url.port() + ", " + (this.proxy != null ? "proxy=" + this.proxy : "proxySelector=" + this.proxySelector) + "}";
    }

    @JvmName(name="dns")
    @NotNull
    public final Dns dns() {
        return this.dns;
    }

    @JvmName(name="socketFactory")
    @NotNull
    public final SocketFactory socketFactory() {
        return this.socketFactory;
    }

    @JvmName(name="sslSocketFactory")
    @Nullable
    public final SSLSocketFactory sslSocketFactory() {
        return this.sslSocketFactory;
    }

    @JvmName(name="hostnameVerifier")
    @Nullable
    public final HostnameVerifier hostnameVerifier() {
        return this.hostnameVerifier;
    }

    @JvmName(name="certificatePinner")
    @Nullable
    public final CertificatePinner certificatePinner() {
        return this.certificatePinner;
    }

    @JvmName(name="proxyAuthenticator")
    @NotNull
    public final Authenticator proxyAuthenticator() {
        return this.proxyAuthenticator;
    }

    @JvmName(name="proxy")
    @Nullable
    public final Proxy proxy() {
        return this.proxy;
    }

    @JvmName(name="proxySelector")
    @NotNull
    public final ProxySelector proxySelector() {
        return this.proxySelector;
    }

    public Address(@NotNull String uriHost, int uriPort, @NotNull Dns dns, @NotNull SocketFactory socketFactory, @Nullable SSLSocketFactory sslSocketFactory, @Nullable HostnameVerifier hostnameVerifier, @Nullable CertificatePinner certificatePinner, @NotNull Authenticator proxyAuthenticator, @Nullable Proxy proxy, @NotNull List<? extends Protocol> protocols, @NotNull List<ConnectionSpec> connectionSpecs, @NotNull ProxySelector proxySelector) {
        Intrinsics.checkNotNullParameter(uriHost, "uriHost");
        Intrinsics.checkNotNullParameter(dns, "dns");
        Intrinsics.checkNotNullParameter(socketFactory, "socketFactory");
        Intrinsics.checkNotNullParameter(proxyAuthenticator, "proxyAuthenticator");
        Intrinsics.checkNotNullParameter(protocols, "protocols");
        Intrinsics.checkNotNullParameter(connectionSpecs, "connectionSpecs");
        Intrinsics.checkNotNullParameter(proxySelector, "proxySelector");
        this.dns = dns;
        this.socketFactory = socketFactory;
        this.sslSocketFactory = sslSocketFactory;
        this.hostnameVerifier = hostnameVerifier;
        this.certificatePinner = certificatePinner;
        this.proxyAuthenticator = proxyAuthenticator;
        this.proxy = proxy;
        this.proxySelector = proxySelector;
        this.url = new HttpUrl.Builder().scheme(this.sslSocketFactory != null ? "https" : "http").host(uriHost).port(uriPort).build();
        this.protocols = Util.toImmutableList(protocols);
        this.connectionSpecs = Util.toImmutableList(connectionSpecs);
    }
}

