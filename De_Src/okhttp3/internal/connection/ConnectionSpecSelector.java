/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.connection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ConnectionSpec;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\rH\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2={"Lokhttp3/internal/connection/ConnectionSpecSelector;", "", "connectionSpecs", "", "Lokhttp3/ConnectionSpec;", "(Ljava/util/List;)V", "isFallback", "", "isFallbackPossible", "nextModeIndex", "", "configureSecureSocket", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "connectionFailed", "e", "Ljava/io/IOException;", "socket", "okhttp"})
public final class ConnectionSpecSelector {
    private int nextModeIndex;
    private boolean isFallbackPossible;
    private boolean isFallback;
    private final List<ConnectionSpec> connectionSpecs;

    /*
     * WARNING - void declaration
     */
    @NotNull
    public final ConnectionSpec configureSecureSocket(@NotNull SSLSocket sslSocket) throws IOException {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        ConnectionSpec tlsConfiguration = null;
        int n = this.nextModeIndex;
        int n2 = this.connectionSpecs.size();
        while (n < n2) {
            void i;
            ConnectionSpec connectionSpec = this.connectionSpecs.get((int)i);
            if (connectionSpec.isCompatible(sslSocket)) {
                tlsConfiguration = connectionSpec;
                this.nextModeIndex = i + true;
                break;
            }
            ++i;
        }
        if (tlsConfiguration == null) {
            StringBuilder stringBuilder = new StringBuilder().append("Unable to find acceptable protocols. isFallback=").append(this.isFallback).append(',').append(" modes=").append(this.connectionSpecs).append(',').append(" supported protocols=");
            Object[] objectArray = sslSocket.getEnabledProtocols();
            Intrinsics.checkNotNull(objectArray);
            Object[] objectArray2 = objectArray;
            n2 = 0;
            String string = Arrays.toString(objectArray2);
            Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
            throw (Throwable)new UnknownServiceException(stringBuilder.append(string).toString());
        }
        this.isFallbackPossible = this.isFallbackPossible(sslSocket);
        tlsConfiguration.apply$okhttp(sslSocket, this.isFallback);
        return tlsConfiguration;
    }

    public final boolean connectionFailed(@NotNull IOException e) {
        Intrinsics.checkNotNullParameter(e, "e");
        this.isFallback = true;
        return !this.isFallbackPossible ? false : (e instanceof ProtocolException ? false : (e instanceof InterruptedIOException ? false : (e instanceof SSLHandshakeException && e.getCause() instanceof CertificateException ? false : (e instanceof SSLPeerUnverifiedException ? false : e instanceof SSLException))));
    }

    /*
     * WARNING - void declaration
     */
    private final boolean isFallbackPossible(SSLSocket socket) {
        int n = this.nextModeIndex;
        int n2 = this.connectionSpecs.size();
        while (n < n2) {
            void i;
            if (this.connectionSpecs.get((int)i).isCompatible(socket)) {
                return true;
            }
            ++i;
        }
        return false;
    }

    public ConnectionSpecSelector(@NotNull List<ConnectionSpec> connectionSpecs) {
        Intrinsics.checkNotNullParameter(connectionSpecs, "connectionSpecs");
        this.connectionSpecs = connectionSpecs;
    }
}

