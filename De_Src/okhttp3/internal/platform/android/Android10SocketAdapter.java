/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.net.ssl.SSLSockets
 *  android.os.Build$VERSION
 */
package okhttp3.internal.platform.android;

import android.annotation.SuppressLint;
import android.net.ssl.SSLSockets;
import android.os.Build;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;
import okhttp3.internal.SuppressSignatureCheck;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.platform.android.SocketAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005\u00a2\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0017J\u0012\u0010\f\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0017J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016\u00a8\u0006\u0011"}, d2={"Lokhttp3/internal/platform/android/Android10SocketAdapter;", "Lokhttp3/internal/platform/android/SocketAdapter;", "()V", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "getSelectedProtocol", "isSupported", "", "matchesSocket", "Companion", "okhttp"})
@SuppressLint(value={"NewApi"})
@SuppressSignatureCheck
public final class Android10SocketAdapter
implements SocketAdapter {
    public static final Companion Companion = new Companion(null);

    @Override
    public boolean matchesSocket(@NotNull SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        return SSLSockets.isSupportedSocket((SSLSocket)sslSocket);
    }

    @Override
    public boolean isSupported() {
        return Companion.isSupported();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    @SuppressLint(value={"NewApi"})
    @Nullable
    public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
        String string;
        String string2;
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        String string3 = string2 = sslSocket.getApplicationProtocol();
        if (string3 == null) return null;
        String string4 = string3;
        switch (string4.hashCode()) {
            case 0: {
                if (string4.equals("")) {
                    return null;
                }
            }
            default: {
                string = string2;
            }
        }
        return string;
    }

    @Override
    @SuppressLint(value={"NewApi"})
    public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List<? extends Protocol> protocols) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        Intrinsics.checkNotNullParameter(protocols, "protocols");
        try {
            SSLParameters sslParameters;
            SSLSockets.setUseSessionTickets((SSLSocket)sslSocket, (boolean)true);
            SSLParameters sSLParameters = sslParameters = sslSocket.getSSLParameters();
            Intrinsics.checkNotNullExpressionValue(sSLParameters, "sslParameters");
            Collection $this$toTypedArray$iv = Platform.Companion.alpnProtocolNames(protocols);
            boolean $i$f$toTypedArray = false;
            Collection thisCollection$iv = $this$toTypedArray$iv;
            String[] stringArray = thisCollection$iv.toArray(new String[0]);
            if (stringArray == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            sSLParameters.setApplicationProtocols(stringArray);
            sslSocket.setSSLParameters(sslParameters);
        } catch (IllegalArgumentException iae) {
            throw (Throwable)new IOException("Android internal error", iae);
        }
    }

    @Override
    @Nullable
    public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
        Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
        return SocketAdapter.DefaultImpls.trustManager(this, sslSocketFactory);
    }

    @Override
    public boolean matchesSocketFactory(@NotNull SSLSocketFactory sslSocketFactory) {
        Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
        return SocketAdapter.DefaultImpls.matchesSocketFactory(this, sslSocketFactory);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2={"Lokhttp3/internal/platform/android/Android10SocketAdapter$Companion;", "", "()V", "buildIfSupported", "Lokhttp3/internal/platform/android/SocketAdapter;", "isSupported", "", "okhttp"})
    @SuppressSignatureCheck
    public static final class Companion {
        @Nullable
        public final SocketAdapter buildIfSupported() {
            return this.isSupported() ? (SocketAdapter)new Android10SocketAdapter() : null;
        }

        public final boolean isSupported() {
            return Platform.Companion.isAndroid() && Build.VERSION.SDK_INT >= 29;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

