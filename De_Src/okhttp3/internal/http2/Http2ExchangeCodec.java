/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.http2;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Header;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2Stream;
import okio.Sink;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 (2\u00020\u0001:\u0001(B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0014H\u0016J\b\u0010\u001c\u001a\u00020\u0014H\u0016J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0012\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020\fH\u0016J\u0010\u0010$\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020 H\u0016J\b\u0010%\u001a\u00020&H\u0016J\u0010\u0010'\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2={"Lokhttp3/internal/http2/Http2ExchangeCodec;", "Lokhttp3/internal/http/ExchangeCodec;", "client", "Lokhttp3/OkHttpClient;", "connection", "Lokhttp3/internal/connection/RealConnection;", "chain", "Lokhttp3/internal/http/RealInterceptorChain;", "http2Connection", "Lokhttp3/internal/http2/Http2Connection;", "(Lokhttp3/OkHttpClient;Lokhttp3/internal/connection/RealConnection;Lokhttp3/internal/http/RealInterceptorChain;Lokhttp3/internal/http2/Http2Connection;)V", "canceled", "", "getConnection", "()Lokhttp3/internal/connection/RealConnection;", "protocol", "Lokhttp3/Protocol;", "stream", "Lokhttp3/internal/http2/Http2Stream;", "cancel", "", "createRequestBody", "Lokio/Sink;", "request", "Lokhttp3/Request;", "contentLength", "", "finishRequest", "flushRequest", "openResponseBodySource", "Lokio/Source;", "response", "Lokhttp3/Response;", "readResponseHeaders", "Lokhttp3/Response$Builder;", "expectContinue", "reportedContentLength", "trailers", "Lokhttp3/Headers;", "writeRequestHeaders", "Companion", "okhttp"})
public final class Http2ExchangeCodec
implements ExchangeCodec {
    private volatile Http2Stream stream;
    private final Protocol protocol;
    private volatile boolean canceled;
    @NotNull
    private final RealConnection connection;
    private final RealInterceptorChain chain;
    private final Http2Connection http2Connection;
    private static final String CONNECTION = "connection";
    private static final String HOST = "host";
    private static final String KEEP_ALIVE = "keep-alive";
    private static final String PROXY_CONNECTION = "proxy-connection";
    private static final String TRANSFER_ENCODING = "transfer-encoding";
    private static final String TE = "te";
    private static final String ENCODING = "encoding";
    private static final String UPGRADE = "upgrade";
    private static final List<String> HTTP_2_SKIPPED_REQUEST_HEADERS;
    private static final List<String> HTTP_2_SKIPPED_RESPONSE_HEADERS;
    public static final Companion Companion;

    @Override
    @NotNull
    public Sink createRequestBody(@NotNull Request request, long contentLength) {
        Intrinsics.checkNotNullParameter(request, "request");
        Http2Stream http2Stream = this.stream;
        Intrinsics.checkNotNull(http2Stream);
        return http2Stream.getSink();
    }

    @Override
    public void writeRequestHeaders(@NotNull Request request) {
        Intrinsics.checkNotNullParameter(request, "request");
        if (this.stream != null) {
            return;
        }
        boolean hasRequestBody = request.body() != null;
        List<Header> requestHeaders = Companion.http2HeadersList(request);
        this.stream = this.http2Connection.newStream(requestHeaders, hasRequestBody);
        if (this.canceled) {
            Http2Stream http2Stream = this.stream;
            Intrinsics.checkNotNull(http2Stream);
            http2Stream.closeLater(ErrorCode.CANCEL);
            throw (Throwable)new IOException("Canceled");
        }
        Http2Stream http2Stream = this.stream;
        Intrinsics.checkNotNull(http2Stream);
        http2Stream.readTimeout().timeout(this.chain.getReadTimeoutMillis$okhttp(), TimeUnit.MILLISECONDS);
        Http2Stream http2Stream2 = this.stream;
        Intrinsics.checkNotNull(http2Stream2);
        http2Stream2.writeTimeout().timeout(this.chain.getWriteTimeoutMillis$okhttp(), TimeUnit.MILLISECONDS);
    }

    @Override
    public void flushRequest() {
        this.http2Connection.flush();
    }

    @Override
    public void finishRequest() {
        Http2Stream http2Stream = this.stream;
        Intrinsics.checkNotNull(http2Stream);
        http2Stream.getSink().close();
    }

    @Override
    @Nullable
    public Response.Builder readResponseHeaders(boolean expectContinue) {
        Http2Stream http2Stream = this.stream;
        Intrinsics.checkNotNull(http2Stream);
        Headers headers = http2Stream.takeHeaders();
        Response.Builder responseBuilder = Companion.readHttp2HeadersList(headers, this.protocol);
        return expectContinue && responseBuilder.getCode$okhttp() == 100 ? null : responseBuilder;
    }

    @Override
    public long reportedContentLength(@NotNull Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        return !HttpHeaders.promisesBody(response) ? 0L : Util.headersContentLength(response);
    }

    @Override
    @NotNull
    public Source openResponseBodySource(@NotNull Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        Http2Stream http2Stream = this.stream;
        Intrinsics.checkNotNull(http2Stream);
        return http2Stream.getSource$okhttp();
    }

    @Override
    @NotNull
    public Headers trailers() {
        Http2Stream http2Stream = this.stream;
        Intrinsics.checkNotNull(http2Stream);
        return http2Stream.trailers();
    }

    @Override
    public void cancel() {
        block0: {
            this.canceled = true;
            Http2Stream http2Stream = this.stream;
            if (http2Stream == null) break block0;
            http2Stream.closeLater(ErrorCode.CANCEL);
        }
    }

    @Override
    @NotNull
    public RealConnection getConnection() {
        return this.connection;
    }

    public Http2ExchangeCodec(@NotNull OkHttpClient client, @NotNull RealConnection connection, @NotNull RealInterceptorChain chain, @NotNull Http2Connection http2Connection) {
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(connection, CONNECTION);
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(http2Connection, "http2Connection");
        this.connection = connection;
        this.chain = chain;
        this.http2Connection = http2Connection;
        this.protocol = client.protocols().contains((Object)Protocol.H2_PRIOR_KNOWLEDGE) ? Protocol.H2_PRIOR_KNOWLEDGE : Protocol.HTTP_2;
    }

    static {
        Companion = new Companion(null);
        HTTP_2_SKIPPED_REQUEST_HEADERS = Util.immutableListOf(CONNECTION, HOST, KEEP_ALIVE, PROXY_CONNECTION, TE, TRANSFER_ENCODING, ENCODING, UPGRADE, ":method", ":path", ":scheme", ":authority");
        HTTP_2_SKIPPED_RESPONSE_HEADERS = Util.immutableListOf(CONNECTION, HOST, KEEP_ALIVE, PROXY_CONNECTION, TE, TRANSFER_ENCODING, ENCODING, UPGRADE);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\b2\u0006\u0010\u0011\u001a\u00020\u0012J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2={"Lokhttp3/internal/http2/Http2ExchangeCodec$Companion;", "", "()V", "CONNECTION", "", "ENCODING", "HOST", "HTTP_2_SKIPPED_REQUEST_HEADERS", "", "HTTP_2_SKIPPED_RESPONSE_HEADERS", "KEEP_ALIVE", "PROXY_CONNECTION", "TE", "TRANSFER_ENCODING", "UPGRADE", "http2HeadersList", "Lokhttp3/internal/http2/Header;", "request", "Lokhttp3/Request;", "readHttp2HeadersList", "Lokhttp3/Response$Builder;", "headerBlock", "Lokhttp3/Headers;", "protocol", "Lokhttp3/Protocol;", "okhttp"})
    public static final class Companion {
        /*
         * WARNING - void declaration
         */
        @NotNull
        public final List<Header> http2HeadersList(@NotNull Request request) {
            Intrinsics.checkNotNullParameter(request, "request");
            Headers headers = request.headers();
            ArrayList<Header> result = new ArrayList<Header>(headers.size() + 4);
            result.add(new Header(Header.TARGET_METHOD, request.method()));
            result.add(new Header(Header.TARGET_PATH, RequestLine.INSTANCE.requestPath(request.url())));
            String host = request.header("Host");
            if (host != null) {
                result.add(new Header(Header.TARGET_AUTHORITY, host));
            }
            result.add(new Header(Header.TARGET_SCHEME, request.url().scheme()));
            int n = 0;
            int n2 = headers.size();
            while (n < n2) {
                String name;
                Locale locale;
                void i;
                String string = headers.name((int)i);
                Intrinsics.checkNotNullExpressionValue(Locale.US, "Locale.US");
                boolean bl = false;
                String string2 = string;
                if (string2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                Intrinsics.checkNotNullExpressionValue(string2.toLowerCase(locale), "(this as java.lang.String).toLowerCase(locale)");
                if (!HTTP_2_SKIPPED_REQUEST_HEADERS.contains(name) || Intrinsics.areEqual(name, Http2ExchangeCodec.TE) && Intrinsics.areEqual(headers.value((int)i), "trailers")) {
                    result.add(new Header(name, headers.value((int)i)));
                }
                ++i;
            }
            return result;
        }

        /*
         * WARNING - void declaration
         */
        @NotNull
        public final Response.Builder readHttp2HeadersList(@NotNull Headers headerBlock, @NotNull Protocol protocol) {
            Intrinsics.checkNotNullParameter(headerBlock, "headerBlock");
            Intrinsics.checkNotNullParameter((Object)protocol, "protocol");
            StatusLine statusLine = null;
            Headers.Builder headersBuilder = new Headers.Builder();
            int n = 0;
            int n2 = headerBlock.size();
            while (n < n2) {
                void i;
                String name = headerBlock.name((int)i);
                String value = headerBlock.value((int)i);
                if (Intrinsics.areEqual(name, ":status")) {
                    statusLine = StatusLine.Companion.parse("HTTP/1.1 " + value);
                } else if (!HTTP_2_SKIPPED_RESPONSE_HEADERS.contains(name)) {
                    headersBuilder.addLenient$okhttp(name, value);
                }
                ++i;
            }
            if (statusLine == null) {
                throw (Throwable)new ProtocolException("Expected ':status' header not present");
            }
            return new Response.Builder().protocol(protocol).code(statusLine.code).message(statusLine.message).headers(headersBuilder.build());
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

