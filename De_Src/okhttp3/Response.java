/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.CacheControl;
import okhttp3.Challenge;
import okhttp3.Handshake;
import okhttp3.Headers;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u0001FB{\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0000\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0000\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0000\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u00a2\u0006\u0002\u0010\u0018J\u000f\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007\u00a2\u0006\u0002\b+J\r\u0010\u001a\u001a\u00020\u001bH\u0007\u00a2\u0006\u0002\b,J\u000f\u0010\u0011\u001a\u0004\u0018\u00010\u0000H\u0007\u00a2\u0006\u0002\b-J\f\u0010.\u001a\b\u0012\u0004\u0012\u0002000/J\b\u00101\u001a\u000202H\u0016J\r\u0010\b\u001a\u00020\tH\u0007\u00a2\u0006\u0002\b3J\u000f\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007\u00a2\u0006\u0002\b4J\u001e\u00105\u001a\u0004\u0018\u00010\u00072\u0006\u00106\u001a\u00020\u00072\n\b\u0002\u00107\u001a\u0004\u0018\u00010\u0007H\u0007J\r\u0010\f\u001a\u00020\rH\u0007\u00a2\u0006\u0002\b8J\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070/2\u0006\u00106\u001a\u00020\u0007J\r\u0010\u0006\u001a\u00020\u0007H\u0007\u00a2\u0006\u0002\b9J\u000f\u0010\u0010\u001a\u0004\u0018\u00010\u0000H\u0007\u00a2\u0006\u0002\b:J\u0006\u0010;\u001a\u00020<J\u000e\u0010=\u001a\u00020\u000f2\u0006\u0010>\u001a\u00020\u0014J\u000f\u0010\u0012\u001a\u0004\u0018\u00010\u0000H\u0007\u00a2\u0006\u0002\b?J\r\u0010\u0004\u001a\u00020\u0005H\u0007\u00a2\u0006\u0002\b@J\r\u0010\u0015\u001a\u00020\u0014H\u0007\u00a2\u0006\u0002\bAJ\r\u0010\u0002\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\bBJ\r\u0010\u0013\u001a\u00020\u0014H\u0007\u00a2\u0006\u0002\bCJ\b\u0010D\u001a\u00020\u0007H\u0016J\u0006\u0010E\u001a\u00020\rR\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u000f8\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u001b8G\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u001cR\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u00008\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u001dR\u0013\u0010\b\u001a\u00020\t8\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u001eR\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00178\u0001X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u001fR\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010 R\u0013\u0010\f\u001a\u00020\r8\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010!R\u0011\u0010\"\u001a\u00020#8F\u00a2\u0006\u0006\u001a\u0004\b\"\u0010$R\u0011\u0010%\u001a\u00020#8F\u00a2\u0006\u0006\u001a\u0004\b%\u0010$R\u0010\u0010&\u001a\u0004\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0013\u0010\u0006\u001a\u00020\u00078\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010'R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u00008\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u001dR\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u00008\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u001dR\u0013\u0010\u0004\u001a\u00020\u00058\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010(R\u0013\u0010\u0015\u001a\u00020\u00148\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010)R\u0013\u0010\u0002\u001a\u00020\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010*R\u0013\u0010\u0013\u001a\u00020\u00148\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010)\u00a8\u0006G"}, d2={"Lokhttp3/Response;", "Ljava/io/Closeable;", "request", "Lokhttp3/Request;", "protocol", "Lokhttp3/Protocol;", "message", "", "code", "", "handshake", "Lokhttp3/Handshake;", "headers", "Lokhttp3/Headers;", "body", "Lokhttp3/ResponseBody;", "networkResponse", "cacheResponse", "priorResponse", "sentRequestAtMillis", "", "receivedResponseAtMillis", "exchange", "Lokhttp3/internal/connection/Exchange;", "(Lokhttp3/Request;Lokhttp3/Protocol;Ljava/lang/String;ILokhttp3/Handshake;Lokhttp3/Headers;Lokhttp3/ResponseBody;Lokhttp3/Response;Lokhttp3/Response;Lokhttp3/Response;JJLokhttp3/internal/connection/Exchange;)V", "()Lokhttp3/ResponseBody;", "cacheControl", "Lokhttp3/CacheControl;", "()Lokhttp3/CacheControl;", "()Lokhttp3/Response;", "()I", "()Lokhttp3/internal/connection/Exchange;", "()Lokhttp3/Handshake;", "()Lokhttp3/Headers;", "isRedirect", "", "()Z", "isSuccessful", "lazyCacheControl", "()Ljava/lang/String;", "()Lokhttp3/Protocol;", "()J", "()Lokhttp3/Request;", "-deprecated_body", "-deprecated_cacheControl", "-deprecated_cacheResponse", "challenges", "", "Lokhttp3/Challenge;", "close", "", "-deprecated_code", "-deprecated_handshake", "header", "name", "defaultValue", "-deprecated_headers", "-deprecated_message", "-deprecated_networkResponse", "newBuilder", "Lokhttp3/Response$Builder;", "peekBody", "byteCount", "-deprecated_priorResponse", "-deprecated_protocol", "-deprecated_receivedResponseAtMillis", "-deprecated_request", "-deprecated_sentRequestAtMillis", "toString", "trailers", "Builder", "okhttp"})
public final class Response
implements Closeable {
    private CacheControl lazyCacheControl;
    @NotNull
    private final Request request;
    @NotNull
    private final Protocol protocol;
    @NotNull
    private final String message;
    private final int code;
    @Nullable
    private final Handshake handshake;
    @NotNull
    private final Headers headers;
    @Nullable
    private final ResponseBody body;
    @Nullable
    private final Response networkResponse;
    @Nullable
    private final Response cacheResponse;
    @Nullable
    private final Response priorResponse;
    private final long sentRequestAtMillis;
    private final long receivedResponseAtMillis;
    @Nullable
    private final Exchange exchange;

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="request"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_request")
    @NotNull
    public final Request -deprecated_request() {
        return this.request;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="protocol"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_protocol")
    @NotNull
    public final Protocol -deprecated_protocol() {
        return this.protocol;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="code"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_code")
    public final int -deprecated_code() {
        return this.code;
    }

    public final boolean isSuccessful() {
        int n = this.code;
        return 200 <= n && 299 >= n;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="message"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_message")
    @NotNull
    public final String -deprecated_message() {
        return this.message;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="handshake"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_handshake")
    @Nullable
    public final Handshake -deprecated_handshake() {
        return this.handshake;
    }

    @NotNull
    public final List<String> headers(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.headers.values(name);
    }

    @JvmOverloads
    @Nullable
    public final String header(@NotNull String name, @Nullable String defaultValue) {
        Intrinsics.checkNotNullParameter(name, "name");
        String string = this.headers.get(name);
        if (string == null) {
            string = defaultValue;
        }
        return string;
    }

    public static /* synthetic */ String header$default(Response response, String string, String string2, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = null;
        }
        return response.header(string, string2);
    }

    @JvmOverloads
    @Nullable
    public final String header(@NotNull String name) {
        return Response.header$default(this, name, null, 2, null);
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="headers"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_headers")
    @NotNull
    public final Headers -deprecated_headers() {
        return this.headers;
    }

    @NotNull
    public final Headers trailers() throws IOException {
        Exchange exchange = this.exchange;
        boolean bl = false;
        boolean bl2 = false;
        if (exchange == null) {
            boolean bl3 = false;
            String string = "trailers not available";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        return exchange.trailers();
    }

    @NotNull
    public final ResponseBody peekBody(long byteCount) throws IOException {
        ResponseBody responseBody = this.body;
        Intrinsics.checkNotNull(responseBody);
        BufferedSource peeked = responseBody.source().peek();
        Buffer buffer = new Buffer();
        peeked.request(byteCount);
        long l = peeked.getBuffer().size();
        boolean bl = false;
        buffer.write(peeked, Math.min(byteCount, l));
        return ResponseBody.Companion.create(buffer, this.body.contentType(), buffer.size());
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="body"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_body")
    @Nullable
    public final ResponseBody -deprecated_body() {
        return this.body;
    }

    @NotNull
    public final Builder newBuilder() {
        return new Builder(this);
    }

    public final boolean isRedirect() {
        boolean bl;
        switch (this.code) {
            case 300: 
            case 301: 
            case 302: 
            case 303: 
            case 307: 
            case 308: {
                bl = true;
                break;
            }
            default: {
                bl = false;
            }
        }
        return bl;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="networkResponse"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_networkResponse")
    @Nullable
    public final Response -deprecated_networkResponse() {
        return this.networkResponse;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="cacheResponse"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_cacheResponse")
    @Nullable
    public final Response -deprecated_cacheResponse() {
        return this.cacheResponse;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="priorResponse"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_priorResponse")
    @Nullable
    public final Response -deprecated_priorResponse() {
        return this.priorResponse;
    }

    @NotNull
    public final List<Challenge> challenges() {
        String string;
        switch (this.code) {
            case 401: {
                string = "WWW-Authenticate";
                break;
            }
            case 407: {
                string = "Proxy-Authenticate";
                break;
            }
            default: {
                return CollectionsKt.emptyList();
            }
        }
        return HttpHeaders.parseChallenges(this.headers, string);
    }

    @JvmName(name="cacheControl")
    @NotNull
    public final CacheControl cacheControl() {
        CacheControl result = this.lazyCacheControl;
        if (result == null) {
            this.lazyCacheControl = result = CacheControl.Companion.parse(this.headers);
        }
        return result;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="cacheControl"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_cacheControl")
    @NotNull
    public final CacheControl -deprecated_cacheControl() {
        return this.cacheControl();
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="sentRequestAtMillis"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_sentRequestAtMillis")
    public final long -deprecated_sentRequestAtMillis() {
        return this.sentRequestAtMillis;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="receivedResponseAtMillis"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_receivedResponseAtMillis")
    public final long -deprecated_receivedResponseAtMillis() {
        return this.receivedResponseAtMillis;
    }

    @Override
    public void close() {
        ResponseBody responseBody = this.body;
        boolean bl = false;
        boolean bl2 = false;
        if (responseBody == null) {
            boolean bl3 = false;
            String string = "response is not eligible for a body and must not be closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        responseBody.close();
    }

    @NotNull
    public String toString() {
        return "Response{protocol=" + (Object)((Object)this.protocol) + ", code=" + this.code + ", message=" + this.message + ", url=" + this.request.url() + '}';
    }

    @JvmName(name="request")
    @NotNull
    public final Request request() {
        return this.request;
    }

    @JvmName(name="protocol")
    @NotNull
    public final Protocol protocol() {
        return this.protocol;
    }

    @JvmName(name="message")
    @NotNull
    public final String message() {
        return this.message;
    }

    @JvmName(name="code")
    public final int code() {
        return this.code;
    }

    @JvmName(name="handshake")
    @Nullable
    public final Handshake handshake() {
        return this.handshake;
    }

    @JvmName(name="headers")
    @NotNull
    public final Headers headers() {
        return this.headers;
    }

    @JvmName(name="body")
    @Nullable
    public final ResponseBody body() {
        return this.body;
    }

    @JvmName(name="networkResponse")
    @Nullable
    public final Response networkResponse() {
        return this.networkResponse;
    }

    @JvmName(name="cacheResponse")
    @Nullable
    public final Response cacheResponse() {
        return this.cacheResponse;
    }

    @JvmName(name="priorResponse")
    @Nullable
    public final Response priorResponse() {
        return this.priorResponse;
    }

    @JvmName(name="sentRequestAtMillis")
    public final long sentRequestAtMillis() {
        return this.sentRequestAtMillis;
    }

    @JvmName(name="receivedResponseAtMillis")
    public final long receivedResponseAtMillis() {
        return this.receivedResponseAtMillis;
    }

    @JvmName(name="exchange")
    @Nullable
    public final Exchange exchange() {
        return this.exchange;
    }

    public Response(@NotNull Request request, @NotNull Protocol protocol, @NotNull String message, int code, @Nullable Handshake handshake2, @NotNull Headers headers, @Nullable ResponseBody body, @Nullable Response networkResponse, @Nullable Response cacheResponse, @Nullable Response priorResponse, long sentRequestAtMillis, long receivedResponseAtMillis, @Nullable Exchange exchange) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter((Object)protocol, "protocol");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.request = request;
        this.protocol = protocol;
        this.message = message;
        this.code = code;
        this.handshake = handshake2;
        this.headers = headers;
        this.body = body;
        this.networkResponse = networkResponse;
        this.cacheResponse = cacheResponse;
        this.priorResponse = priorResponse;
        this.sentRequestAtMillis = sentRequestAtMillis;
        this.receivedResponseAtMillis = receivedResponseAtMillis;
        this.exchange = exchange;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0002B\u000f\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0018\u0010I\u001a\u00020\u00002\u0006\u0010J\u001a\u00020)2\u0006\u0010K\u001a\u00020)H\u0016J\u0012\u0010\u0006\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\b\u0010L\u001a\u00020\u0004H\u0016J\u0012\u0010\f\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010M\u001a\u00020N2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0002J\u001a\u0010O\u001a\u00020N2\u0006\u0010J\u001a\u00020)2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0002J\u0010\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0012\u0010\u001c\u001a\u00020\u00002\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0018\u0010P\u001a\u00020\u00002\u0006\u0010J\u001a\u00020)2\u0006\u0010K\u001a\u00020)H\u0016J\u0010\u0010\"\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020QH\u0016J\u0015\u0010R\u001a\u00020N2\u0006\u0010S\u001a\u00020\u0017H\u0000\u00a2\u0006\u0002\bTJ\u0010\u0010(\u001a\u00020\u00002\u0006\u0010(\u001a\u00020)H\u0016J\u0012\u0010.\u001a\u00020\u00002\b\u0010.\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u00101\u001a\u00020\u00002\b\u00101\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u00104\u001a\u00020\u00002\u0006\u00104\u001a\u000205H\u0016J\u0010\u0010:\u001a\u00020\u00002\u0006\u0010:\u001a\u00020;H\u0016J\u0010\u0010U\u001a\u00020\u00002\u0006\u0010J\u001a\u00020)H\u0016J\u0010\u0010@\u001a\u00020\u00002\u0006\u0010@\u001a\u00020AH\u0016J\u0010\u0010F\u001a\u00020\u00002\u0006\u0010F\u001a\u00020;H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0005R\u001a\u0010\u0010\u001a\u00020\u0011X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020#X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001c\u0010(\u001a\u0004\u0018\u00010)X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001c\u0010.\u001a\u0004\u0018\u00010\u0004X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u000e\"\u0004\b0\u0010\u0005R\u001c\u00101\u001a\u0004\u0018\u00010\u0004X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u000e\"\u0004\b3\u0010\u0005R\u001c\u00104\u001a\u0004\u0018\u000105X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001a\u0010:\u001a\u00020;X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001c\u0010@\u001a\u0004\u0018\u00010AX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u001a\u0010F\u001a\u00020;X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bG\u0010=\"\u0004\bH\u0010?\u00a8\u0006V"}, d2={"Lokhttp3/Response$Builder;", "", "()V", "response", "Lokhttp3/Response;", "(Lokhttp3/Response;)V", "body", "Lokhttp3/ResponseBody;", "getBody$okhttp", "()Lokhttp3/ResponseBody;", "setBody$okhttp", "(Lokhttp3/ResponseBody;)V", "cacheResponse", "getCacheResponse$okhttp", "()Lokhttp3/Response;", "setCacheResponse$okhttp", "code", "", "getCode$okhttp", "()I", "setCode$okhttp", "(I)V", "exchange", "Lokhttp3/internal/connection/Exchange;", "getExchange$okhttp", "()Lokhttp3/internal/connection/Exchange;", "setExchange$okhttp", "(Lokhttp3/internal/connection/Exchange;)V", "handshake", "Lokhttp3/Handshake;", "getHandshake$okhttp", "()Lokhttp3/Handshake;", "setHandshake$okhttp", "(Lokhttp3/Handshake;)V", "headers", "Lokhttp3/Headers$Builder;", "getHeaders$okhttp", "()Lokhttp3/Headers$Builder;", "setHeaders$okhttp", "(Lokhttp3/Headers$Builder;)V", "message", "", "getMessage$okhttp", "()Ljava/lang/String;", "setMessage$okhttp", "(Ljava/lang/String;)V", "networkResponse", "getNetworkResponse$okhttp", "setNetworkResponse$okhttp", "priorResponse", "getPriorResponse$okhttp", "setPriorResponse$okhttp", "protocol", "Lokhttp3/Protocol;", "getProtocol$okhttp", "()Lokhttp3/Protocol;", "setProtocol$okhttp", "(Lokhttp3/Protocol;)V", "receivedResponseAtMillis", "", "getReceivedResponseAtMillis$okhttp", "()J", "setReceivedResponseAtMillis$okhttp", "(J)V", "request", "Lokhttp3/Request;", "getRequest$okhttp", "()Lokhttp3/Request;", "setRequest$okhttp", "(Lokhttp3/Request;)V", "sentRequestAtMillis", "getSentRequestAtMillis$okhttp", "setSentRequestAtMillis$okhttp", "addHeader", "name", "value", "build", "checkPriorResponse", "", "checkSupportResponse", "header", "Lokhttp3/Headers;", "initExchange", "deferredTrailers", "initExchange$okhttp", "removeHeader", "okhttp"})
    public static class Builder {
        @Nullable
        private Request request;
        @Nullable
        private Protocol protocol;
        private int code;
        @Nullable
        private String message;
        @Nullable
        private Handshake handshake;
        @NotNull
        private Headers.Builder headers;
        @Nullable
        private ResponseBody body;
        @Nullable
        private Response networkResponse;
        @Nullable
        private Response cacheResponse;
        @Nullable
        private Response priorResponse;
        private long sentRequestAtMillis;
        private long receivedResponseAtMillis;
        @Nullable
        private Exchange exchange;

        @Nullable
        public final Request getRequest$okhttp() {
            return this.request;
        }

        public final void setRequest$okhttp(@Nullable Request request) {
            this.request = request;
        }

        @Nullable
        public final Protocol getProtocol$okhttp() {
            return this.protocol;
        }

        public final void setProtocol$okhttp(@Nullable Protocol protocol) {
            this.protocol = protocol;
        }

        public final int getCode$okhttp() {
            return this.code;
        }

        public final void setCode$okhttp(int n) {
            this.code = n;
        }

        @Nullable
        public final String getMessage$okhttp() {
            return this.message;
        }

        public final void setMessage$okhttp(@Nullable String string) {
            this.message = string;
        }

        @Nullable
        public final Handshake getHandshake$okhttp() {
            return this.handshake;
        }

        public final void setHandshake$okhttp(@Nullable Handshake handshake2) {
            this.handshake = handshake2;
        }

        @NotNull
        public final Headers.Builder getHeaders$okhttp() {
            return this.headers;
        }

        public final void setHeaders$okhttp(@NotNull Headers.Builder builder) {
            Intrinsics.checkNotNullParameter(builder, "<set-?>");
            this.headers = builder;
        }

        @Nullable
        public final ResponseBody getBody$okhttp() {
            return this.body;
        }

        public final void setBody$okhttp(@Nullable ResponseBody responseBody) {
            this.body = responseBody;
        }

        @Nullable
        public final Response getNetworkResponse$okhttp() {
            return this.networkResponse;
        }

        public final void setNetworkResponse$okhttp(@Nullable Response response) {
            this.networkResponse = response;
        }

        @Nullable
        public final Response getCacheResponse$okhttp() {
            return this.cacheResponse;
        }

        public final void setCacheResponse$okhttp(@Nullable Response response) {
            this.cacheResponse = response;
        }

        @Nullable
        public final Response getPriorResponse$okhttp() {
            return this.priorResponse;
        }

        public final void setPriorResponse$okhttp(@Nullable Response response) {
            this.priorResponse = response;
        }

        public final long getSentRequestAtMillis$okhttp() {
            return this.sentRequestAtMillis;
        }

        public final void setSentRequestAtMillis$okhttp(long l) {
            this.sentRequestAtMillis = l;
        }

        public final long getReceivedResponseAtMillis$okhttp() {
            return this.receivedResponseAtMillis;
        }

        public final void setReceivedResponseAtMillis$okhttp(long l) {
            this.receivedResponseAtMillis = l;
        }

        @Nullable
        public final Exchange getExchange$okhttp() {
            return this.exchange;
        }

        public final void setExchange$okhttp(@Nullable Exchange exchange) {
            this.exchange = exchange;
        }

        @NotNull
        public Builder request(@NotNull Request request) {
            Intrinsics.checkNotNullParameter(request, "request");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.request = request;
            return builder;
        }

        @NotNull
        public Builder protocol(@NotNull Protocol protocol) {
            Intrinsics.checkNotNullParameter((Object)protocol, "protocol");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.protocol = protocol;
            return builder;
        }

        @NotNull
        public Builder code(int code) {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.code = code;
            return builder;
        }

        @NotNull
        public Builder message(@NotNull String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.message = message;
            return builder;
        }

        @NotNull
        public Builder handshake(@Nullable Handshake handshake2) {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.handshake = handshake2;
            return builder;
        }

        @NotNull
        public Builder header(@NotNull String name, @NotNull String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.headers.set(name, value);
            return builder;
        }

        @NotNull
        public Builder addHeader(@NotNull String name, @NotNull String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.headers.add(name, value);
            return builder;
        }

        @NotNull
        public Builder removeHeader(@NotNull String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.headers.removeAll(name);
            return builder;
        }

        @NotNull
        public Builder headers(@NotNull Headers headers) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.headers = headers.newBuilder();
            return builder;
        }

        @NotNull
        public Builder body(@Nullable ResponseBody body) {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.body = body;
            return builder;
        }

        @NotNull
        public Builder networkResponse(@Nullable Response networkResponse) {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.checkSupportResponse("networkResponse", networkResponse);
            $this$apply.networkResponse = networkResponse;
            return builder;
        }

        @NotNull
        public Builder cacheResponse(@Nullable Response cacheResponse) {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.checkSupportResponse("cacheResponse", cacheResponse);
            $this$apply.cacheResponse = cacheResponse;
            return builder;
        }

        private final void checkSupportResponse(String name, Response response) {
            Response response2 = response;
            if (response2 != null) {
                Response response3 = response2;
                boolean bl = false;
                boolean bl2 = false;
                Response $this$apply = response3;
                boolean bl3 = false;
                boolean bl4 = $this$apply.body() == null;
                boolean bl5 = false;
                boolean bl6 = false;
                if (!bl4) {
                    boolean bl7 = false;
                    String string = name + ".body != null";
                    throw (Throwable)new IllegalArgumentException(string.toString());
                }
                bl4 = $this$apply.networkResponse() == null;
                bl5 = false;
                bl6 = false;
                if (!bl4) {
                    boolean bl8 = false;
                    String string = name + ".networkResponse != null";
                    throw (Throwable)new IllegalArgumentException(string.toString());
                }
                bl4 = $this$apply.cacheResponse() == null;
                bl5 = false;
                bl6 = false;
                if (!bl4) {
                    boolean bl9 = false;
                    String string = name + ".cacheResponse != null";
                    throw (Throwable)new IllegalArgumentException(string.toString());
                }
                bl4 = $this$apply.priorResponse() == null;
                bl5 = false;
                bl6 = false;
                if (!bl4) {
                    boolean bl10 = false;
                    String string = name + ".priorResponse != null";
                    throw (Throwable)new IllegalArgumentException(string.toString());
                }
            }
        }

        @NotNull
        public Builder priorResponse(@Nullable Response priorResponse) {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.checkPriorResponse(priorResponse);
            $this$apply.priorResponse = priorResponse;
            return builder;
        }

        private final void checkPriorResponse(Response response) {
            Response response2 = response;
            if (response2 != null) {
                Response response3 = response2;
                boolean bl = false;
                boolean bl2 = false;
                Response $this$apply = response3;
                boolean bl3 = false;
                boolean bl4 = $this$apply.body() == null;
                boolean bl5 = false;
                boolean bl6 = false;
                if (!bl4) {
                    boolean bl7 = false;
                    String string = "priorResponse.body != null";
                    throw (Throwable)new IllegalArgumentException(string.toString());
                }
            }
        }

        @NotNull
        public Builder sentRequestAtMillis(long sentRequestAtMillis) {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.sentRequestAtMillis = sentRequestAtMillis;
            return builder;
        }

        @NotNull
        public Builder receivedResponseAtMillis(long receivedResponseAtMillis) {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.receivedResponseAtMillis = receivedResponseAtMillis;
            return builder;
        }

        public final void initExchange$okhttp(@NotNull Exchange deferredTrailers) {
            Intrinsics.checkNotNullParameter(deferredTrailers, "deferredTrailers");
            this.exchange = deferredTrailers;
        }

        @NotNull
        public Response build() {
            boolean bl = this.code >= 0;
            boolean bl2 = false;
            boolean bl3 = false;
            if (!bl) {
                boolean bl4 = false;
                String string = "code < 0: " + this.code;
                throw (Throwable)new IllegalStateException(string.toString());
            }
            Object object = this.request;
            bl2 = false;
            bl3 = false;
            if (object == null) {
                String string;
                boolean bl5 = false;
                String string2 = string = "request == null";
                throw (Throwable)new IllegalStateException(string2.toString());
            }
            Request request = object;
            object = this.protocol;
            bl2 = false;
            bl3 = false;
            if (object == null) {
                Request request2 = request;
                boolean bl6 = false;
                String string = "protocol == null";
                Request request3 = request2;
                String string3 = string;
                throw (Throwable)new IllegalStateException(string3.toString());
            }
            Object object2 = object;
            object = this.message;
            bl2 = false;
            bl3 = false;
            if (object == null) {
                Object object3 = object2;
                Request request4 = request;
                boolean bl7 = false;
                String string = "message == null";
                Request request5 = request4;
                Object object4 = object3;
                String string4 = string;
                throw (Throwable)new IllegalStateException(string4.toString());
            }
            Exchange exchange = this.exchange;
            long l = this.receivedResponseAtMillis;
            long l2 = this.sentRequestAtMillis;
            Response response = this.priorResponse;
            Response response2 = this.cacheResponse;
            Response response3 = this.networkResponse;
            ResponseBody responseBody = this.body;
            Headers headers = this.headers.build();
            Handshake handshake2 = this.handshake;
            int n = this.code;
            Object object5 = object;
            Object object6 = object2;
            Request request6 = request;
            return new Response(request6, (Protocol)((Object)object6), (String)object5, n, handshake2, headers, responseBody, response3, response2, response, l2, l, exchange);
        }

        public Builder() {
            this.code = -1;
            this.headers = new Headers.Builder();
        }

        public Builder(@NotNull Response response) {
            Intrinsics.checkNotNullParameter(response, "response");
            this.code = -1;
            this.request = response.request();
            this.protocol = response.protocol();
            this.code = response.code();
            this.message = response.message();
            this.handshake = response.handshake();
            this.headers = response.headers().newBuilder();
            this.body = response.body();
            this.networkResponse = response.networkResponse();
            this.cacheResponse = response.cacheResponse();
            this.priorResponse = response.priorResponse();
            this.sentRequestAtMillis = response.sentRequestAtMillis();
            this.receivedResponseAtMillis = response.receivedResponseAtMillis();
            this.exchange = response.exchange();
        }
    }
}

