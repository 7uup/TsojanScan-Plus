/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.http1;

import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.http1.HeadersReader;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingTimeout;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u0000 ?2\u00020\u0001:\u0007<=>?@ABB'\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020\u001cH\u0016J\b\u0010&\u001a\u00020\u001cH\u0016J\b\u0010'\u001a\u00020\u001eH\u0002J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\u0010\u0010,\u001a\u00020)2\u0006\u0010-\u001a\u00020!H\u0002J\b\u0010.\u001a\u00020\u001eH\u0002J\b\u0010/\u001a\u00020)H\u0002J\u0010\u00100\u001a\u00020)2\u0006\u00101\u001a\u00020\u0019H\u0016J\u0012\u00102\u001a\u0004\u0018\u0001032\u0006\u00104\u001a\u00020\u0010H\u0016J\u0010\u00105\u001a\u00020!2\u0006\u00101\u001a\u00020\u0019H\u0016J\u000e\u00106\u001a\u00020\u001c2\u0006\u00101\u001a\u00020\u0019J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0016\u00107\u001a\u00020\u001c2\u0006\u00108\u001a\u00020\u00152\u0006\u00109\u001a\u00020:J\u0010\u0010;\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u0017H\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u00108F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0018\u0010\u0016\u001a\u00020\u0010*\u00020\u00178BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0018R\u0018\u0010\u0016\u001a\u00020\u0010*\u00020\u00198BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u001a\u00a8\u0006C"}, d2={"Lokhttp3/internal/http1/Http1ExchangeCodec;", "Lokhttp3/internal/http/ExchangeCodec;", "client", "Lokhttp3/OkHttpClient;", "connection", "Lokhttp3/internal/connection/RealConnection;", "source", "Lokio/BufferedSource;", "sink", "Lokio/BufferedSink;", "(Lokhttp3/OkHttpClient;Lokhttp3/internal/connection/RealConnection;Lokio/BufferedSource;Lokio/BufferedSink;)V", "getConnection", "()Lokhttp3/internal/connection/RealConnection;", "headersReader", "Lokhttp3/internal/http1/HeadersReader;", "isClosed", "", "()Z", "state", "", "trailers", "Lokhttp3/Headers;", "isChunked", "Lokhttp3/Request;", "(Lokhttp3/Request;)Z", "Lokhttp3/Response;", "(Lokhttp3/Response;)Z", "cancel", "", "createRequestBody", "Lokio/Sink;", "request", "contentLength", "", "detachTimeout", "timeout", "Lokio/ForwardingTimeout;", "finishRequest", "flushRequest", "newChunkedSink", "newChunkedSource", "Lokio/Source;", "url", "Lokhttp3/HttpUrl;", "newFixedLengthSource", "length", "newKnownLengthSink", "newUnknownLengthSource", "openResponseBodySource", "response", "readResponseHeaders", "Lokhttp3/Response$Builder;", "expectContinue", "reportedContentLength", "skipConnectBody", "writeRequest", "headers", "requestLine", "", "writeRequestHeaders", "AbstractSource", "ChunkedSink", "ChunkedSource", "Companion", "FixedLengthSource", "KnownLengthSink", "UnknownLengthSource", "okhttp"})
public final class Http1ExchangeCodec
implements ExchangeCodec {
    private int state;
    private final HeadersReader headersReader;
    private Headers trailers;
    private final OkHttpClient client;
    @NotNull
    private final RealConnection connection;
    private final BufferedSource source;
    private final BufferedSink sink;
    private static final long NO_CHUNK_YET = -1L;
    private static final int STATE_IDLE = 0;
    private static final int STATE_OPEN_REQUEST_BODY = 1;
    private static final int STATE_WRITING_REQUEST_BODY = 2;
    private static final int STATE_READ_RESPONSE_HEADERS = 3;
    private static final int STATE_OPEN_RESPONSE_BODY = 4;
    private static final int STATE_READING_RESPONSE_BODY = 5;
    private static final int STATE_CLOSED = 6;
    public static final Companion Companion = new Companion(null);

    private final boolean isChunked(Response $this$isChunked) {
        return StringsKt.equals("chunked", Response.header$default($this$isChunked, "Transfer-Encoding", null, 2, null), true);
    }

    private final boolean isChunked(Request $this$isChunked) {
        return StringsKt.equals("chunked", $this$isChunked.header("Transfer-Encoding"), true);
    }

    public final boolean isClosed() {
        return this.state == 6;
    }

    @Override
    @NotNull
    public Sink createRequestBody(@NotNull Request request, long contentLength) {
        Sink sink2;
        Intrinsics.checkNotNullParameter(request, "request");
        if (request.body() != null && request.body().isDuplex()) {
            throw (Throwable)new ProtocolException("Duplex connections are not supported for HTTP/1");
        }
        if (this.isChunked(request)) {
            sink2 = this.newChunkedSink();
        } else if (contentLength != -1L) {
            sink2 = this.newKnownLengthSink();
        } else {
            throw (Throwable)new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
        }
        return sink2;
    }

    @Override
    public void cancel() {
        this.getConnection().cancel();
    }

    @Override
    public void writeRequestHeaders(@NotNull Request request) {
        Intrinsics.checkNotNullParameter(request, "request");
        Proxy.Type type = this.getConnection().route().proxy().type();
        Intrinsics.checkNotNullExpressionValue((Object)type, "connection.route().proxy.type()");
        String requestLine = RequestLine.INSTANCE.get(request, type);
        this.writeRequest(request.headers(), requestLine);
    }

    @Override
    public long reportedContentLength(@NotNull Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        return !HttpHeaders.promisesBody(response) ? 0L : (this.isChunked(response) ? -1L : Util.headersContentLength(response));
    }

    @Override
    @NotNull
    public Source openResponseBodySource(@NotNull Response response) {
        long contentLength;
        Intrinsics.checkNotNullParameter(response, "response");
        return !HttpHeaders.promisesBody(response) ? this.newFixedLengthSource(0L) : (this.isChunked(response) ? this.newChunkedSource(response.request().url()) : ((contentLength = Util.headersContentLength(response)) != -1L ? this.newFixedLengthSource(contentLength) : this.newUnknownLengthSource()));
    }

    @Override
    @NotNull
    public Headers trailers() {
        boolean bl = this.state == 6;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "too early; can't read the trailers yet";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        Headers headers = this.trailers;
        if (headers == null) {
            headers = Util.EMPTY_HEADERS;
        }
        return headers;
    }

    @Override
    public void flushRequest() {
        this.sink.flush();
    }

    @Override
    public void finishRequest() {
        this.sink.flush();
    }

    /*
     * WARNING - void declaration
     */
    public final void writeRequest(@NotNull Headers headers, @NotNull String requestLine) {
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(requestLine, "requestLine");
        int n = this.state == 0 ? 1 : 0;
        int n2 = 0;
        boolean bl = false;
        if (n == 0) {
            boolean bl2 = false;
            String string = "state: " + this.state;
            throw (Throwable)new IllegalStateException(string.toString());
        }
        this.sink.writeUtf8(requestLine).writeUtf8("\r\n");
        n = 0;
        n2 = headers.size();
        while (n < n2) {
            void i;
            this.sink.writeUtf8(headers.name((int)i)).writeUtf8(": ").writeUtf8(headers.value((int)i)).writeUtf8("\r\n");
            ++i;
        }
        this.sink.writeUtf8("\r\n");
        this.state = 1;
    }

    @Override
    @Nullable
    public Response.Builder readResponseHeaders(boolean expectContinue) {
        boolean bl = this.state == 1 || this.state == 3;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "state: " + this.state;
            throw (Throwable)new IllegalStateException(string.toString());
        }
        try {
            Response.Builder builder;
            StatusLine statusLine = StatusLine.Companion.parse(this.headersReader.readLine());
            Response.Builder responseBuilder = new Response.Builder().protocol(statusLine.protocol).code(statusLine.code).message(statusLine.message).headers(this.headersReader.readHeaders());
            if (expectContinue && statusLine.code == 100) {
                builder = null;
            } else if (statusLine.code == 100) {
                this.state = 3;
                builder = responseBuilder;
            } else {
                this.state = 4;
                builder = responseBuilder;
            }
            return builder;
        } catch (EOFException e) {
            String address = this.getConnection().route().address().url().redact();
            throw (Throwable)new IOException("unexpected end of stream on " + address, e);
        }
    }

    private final Sink newChunkedSink() {
        boolean bl = this.state == 1;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "state: " + this.state;
            throw (Throwable)new IllegalStateException(string.toString());
        }
        this.state = 2;
        return new ChunkedSink();
    }

    private final Sink newKnownLengthSink() {
        boolean bl = this.state == 1;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "state: " + this.state;
            throw (Throwable)new IllegalStateException(string.toString());
        }
        this.state = 2;
        return new KnownLengthSink();
    }

    private final Source newFixedLengthSource(long length) {
        boolean bl = this.state == 4;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "state: " + this.state;
            throw (Throwable)new IllegalStateException(string.toString());
        }
        this.state = 5;
        return new FixedLengthSource(length);
    }

    private final Source newChunkedSource(HttpUrl url) {
        boolean bl = this.state == 4;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "state: " + this.state;
            throw (Throwable)new IllegalStateException(string.toString());
        }
        this.state = 5;
        return new ChunkedSource(url);
    }

    private final Source newUnknownLengthSource() {
        boolean bl = this.state == 4;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "state: " + this.state;
            throw (Throwable)new IllegalStateException(string.toString());
        }
        this.state = 5;
        this.getConnection().noNewExchanges$okhttp();
        return new UnknownLengthSource();
    }

    private final void detachTimeout(ForwardingTimeout timeout2) {
        Timeout oldDelegate = timeout2.delegate();
        timeout2.setDelegate(Timeout.NONE);
        oldDelegate.clearDeadline();
        oldDelegate.clearTimeout();
    }

    public final void skipConnectBody(@NotNull Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        long contentLength = Util.headersContentLength(response);
        if (contentLength == -1L) {
            return;
        }
        Source body = this.newFixedLengthSource(contentLength);
        Util.skipAll(body, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
        body.close();
    }

    @Override
    @NotNull
    public RealConnection getConnection() {
        return this.connection;
    }

    public Http1ExchangeCodec(@Nullable OkHttpClient client, @NotNull RealConnection connection, @NotNull BufferedSource source2, @NotNull BufferedSink sink2) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        Intrinsics.checkNotNullParameter(source2, "source");
        Intrinsics.checkNotNullParameter(sink2, "sink");
        this.client = client;
        this.connection = connection;
        this.source = source2;
        this.sink = sink2;
        this.headersReader = new HeadersReader(this.source);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\u0005\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2={"Lokhttp3/internal/http1/Http1ExchangeCodec$KnownLengthSink;", "Lokio/Sink;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "closed", "", "timeout", "Lokio/ForwardingTimeout;", "close", "", "flush", "Lokio/Timeout;", "write", "source", "Lokio/Buffer;", "byteCount", "", "okhttp"})
    private final class KnownLengthSink
    implements Sink {
        private final ForwardingTimeout timeout;
        private boolean closed;

        @Override
        @NotNull
        public Timeout timeout() {
            return this.timeout;
        }

        @Override
        public void write(@NotNull Buffer source2, long byteCount) {
            Intrinsics.checkNotNullParameter(source2, "source");
            boolean bl = !this.closed;
            boolean bl2 = false;
            boolean bl3 = false;
            if (!bl) {
                boolean bl4 = false;
                String string = "closed";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            Util.checkOffsetAndCount(source2.size(), 0L, byteCount);
            Http1ExchangeCodec.this.sink.write(source2, byteCount);
        }

        @Override
        public void flush() {
            if (this.closed) {
                return;
            }
            Http1ExchangeCodec.this.sink.flush();
        }

        @Override
        public void close() {
            if (this.closed) {
                return;
            }
            this.closed = true;
            Http1ExchangeCodec.this.detachTimeout(this.timeout);
            Http1ExchangeCodec.this.state = 3;
        }

        public KnownLengthSink() {
            this.timeout = new ForwardingTimeout(Http1ExchangeCodec.this.sink.timeout());
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\u0005\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2={"Lokhttp3/internal/http1/Http1ExchangeCodec$ChunkedSink;", "Lokio/Sink;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "closed", "", "timeout", "Lokio/ForwardingTimeout;", "close", "", "flush", "Lokio/Timeout;", "write", "source", "Lokio/Buffer;", "byteCount", "", "okhttp"})
    private final class ChunkedSink
    implements Sink {
        private final ForwardingTimeout timeout;
        private boolean closed;

        @Override
        @NotNull
        public Timeout timeout() {
            return this.timeout;
        }

        @Override
        public void write(@NotNull Buffer source2, long byteCount) {
            Intrinsics.checkNotNullParameter(source2, "source");
            boolean bl = !this.closed;
            boolean bl2 = false;
            boolean bl3 = false;
            if (!bl) {
                boolean bl4 = false;
                String string = "closed";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            if (byteCount == 0L) {
                return;
            }
            Http1ExchangeCodec.this.sink.writeHexadecimalUnsignedLong(byteCount);
            Http1ExchangeCodec.this.sink.writeUtf8("\r\n");
            Http1ExchangeCodec.this.sink.write(source2, byteCount);
            Http1ExchangeCodec.this.sink.writeUtf8("\r\n");
        }

        @Override
        public synchronized void flush() {
            if (this.closed) {
                return;
            }
            Http1ExchangeCodec.this.sink.flush();
        }

        @Override
        public synchronized void close() {
            if (this.closed) {
                return;
            }
            this.closed = true;
            Http1ExchangeCodec.this.sink.writeUtf8("0\r\n\r\n");
            Http1ExchangeCodec.this.detachTimeout(this.timeout);
            Http1ExchangeCodec.this.state = 3;
        }

        public ChunkedSink() {
            this.timeout = new ForwardingTimeout(Http1ExchangeCodec.this.sink.timeout());
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u00a2\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000eH\u0016J\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\t\u001a\u00020\u0014H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0084\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nX\u0084\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0015"}, d2={"Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokio/Source;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "closed", "", "getClosed", "()Z", "setClosed", "(Z)V", "timeout", "Lokio/ForwardingTimeout;", "getTimeout", "()Lokio/ForwardingTimeout;", "read", "", "sink", "Lokio/Buffer;", "byteCount", "responseBodyComplete", "", "Lokio/Timeout;", "okhttp"})
    private abstract class AbstractSource
    implements Source {
        @NotNull
        private final ForwardingTimeout timeout;
        private boolean closed;

        @NotNull
        protected final ForwardingTimeout getTimeout() {
            return this.timeout;
        }

        protected final boolean getClosed() {
            return this.closed;
        }

        protected final void setClosed(boolean bl) {
            this.closed = bl;
        }

        @Override
        @NotNull
        public Timeout timeout() {
            return this.timeout;
        }

        @Override
        public long read(@NotNull Buffer sink2, long byteCount) {
            long l;
            Intrinsics.checkNotNullParameter(sink2, "sink");
            try {
                l = Http1ExchangeCodec.this.source.read(sink2, byteCount);
            } catch (IOException e) {
                Http1ExchangeCodec.this.getConnection().noNewExchanges$okhttp();
                this.responseBodyComplete();
                throw (Throwable)e;
            }
            return l;
        }

        public final void responseBodyComplete() {
            if (Http1ExchangeCodec.this.state == 6) {
                return;
            }
            if (Http1ExchangeCodec.this.state != 5) {
                throw (Throwable)new IllegalStateException("state: " + Http1ExchangeCodec.this.state);
            }
            Http1ExchangeCodec.this.detachTimeout(this.timeout);
            Http1ExchangeCodec.this.state = 6;
        }

        public AbstractSource() {
            this.timeout = new ForwardingTimeout(Http1ExchangeCodec.this.source.timeout());
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2={"Lokhttp3/internal/http1/Http1ExchangeCodec$FixedLengthSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec;", "bytesRemaining", "", "(Lokhttp3/internal/http1/Http1ExchangeCodec;J)V", "close", "", "read", "sink", "Lokio/Buffer;", "byteCount", "okhttp"})
    private final class FixedLengthSource
    extends AbstractSource {
        private long bytesRemaining;

        @Override
        public long read(@NotNull Buffer sink2, long byteCount) {
            Intrinsics.checkNotNullParameter(sink2, "sink");
            boolean bl = byteCount >= 0L;
            boolean bl2 = false;
            boolean bl3 = false;
            if (!bl) {
                boolean bl4 = false;
                String string = "byteCount < 0: " + byteCount;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            bl = !this.getClosed();
            bl2 = false;
            bl3 = false;
            if (!bl) {
                boolean bl5 = false;
                String string = "closed";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            if (this.bytesRemaining == 0L) {
                return -1L;
            }
            long l = this.bytesRemaining;
            boolean bl6 = false;
            long read = super.read(sink2, Math.min(l, byteCount));
            if (read == -1L) {
                Http1ExchangeCodec.this.getConnection().noNewExchanges$okhttp();
                ProtocolException e = new ProtocolException("unexpected end of stream");
                this.responseBodyComplete();
                throw (Throwable)e;
            }
            this.bytesRemaining -= read;
            if (this.bytesRemaining == 0L) {
                this.responseBodyComplete();
            }
            return read;
        }

        @Override
        public void close() {
            if (this.getClosed()) {
                return;
            }
            if (this.bytesRemaining != 0L && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                Http1ExchangeCodec.this.getConnection().noNewExchanges$okhttp();
                this.responseBodyComplete();
            }
            this.setClosed(true);
        }

        public FixedLengthSource(long bytesRemaining) {
            this.bytesRemaining = bytesRemaining;
            if (this.bytesRemaining == 0L) {
                this.responseBodyComplete();
            }
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0016J\b\u0010\u0010\u001a\u00020\u000bH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2={"Lokhttp3/internal/http1/Http1ExchangeCodec$ChunkedSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec;", "url", "Lokhttp3/HttpUrl;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;Lokhttp3/HttpUrl;)V", "bytesRemainingInChunk", "", "hasMoreChunks", "", "close", "", "read", "sink", "Lokio/Buffer;", "byteCount", "readChunkSize", "okhttp"})
    private final class ChunkedSource
    extends AbstractSource {
        private long bytesRemainingInChunk;
        private boolean hasMoreChunks;
        private final HttpUrl url;

        @Override
        public long read(@NotNull Buffer sink2, long byteCount) {
            Intrinsics.checkNotNullParameter(sink2, "sink");
            boolean bl = byteCount >= 0L;
            boolean bl2 = false;
            boolean bl3 = false;
            if (!bl) {
                boolean bl4 = false;
                String string = "byteCount < 0: " + byteCount;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            bl = !this.getClosed();
            bl2 = false;
            bl3 = false;
            if (!bl) {
                boolean bl5 = false;
                String string = "closed";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            if (!this.hasMoreChunks) {
                return -1L;
            }
            if (this.bytesRemainingInChunk == 0L || this.bytesRemainingInChunk == -1L) {
                this.readChunkSize();
                if (!this.hasMoreChunks) {
                    return -1L;
                }
            }
            long l = this.bytesRemainingInChunk;
            boolean bl6 = false;
            long read = super.read(sink2, Math.min(byteCount, l));
            if (read == -1L) {
                Http1ExchangeCodec.this.getConnection().noNewExchanges$okhttp();
                ProtocolException e = new ProtocolException("unexpected end of stream");
                this.responseBodyComplete();
                throw (Throwable)e;
            }
            this.bytesRemainingInChunk -= read;
            return read;
        }

        private final void readChunkSize() {
            block7: {
                if (this.bytesRemainingInChunk != -1L) {
                    Http1ExchangeCodec.this.source.readUtf8LineStrict();
                }
                try {
                    this.bytesRemainingInChunk = Http1ExchangeCodec.this.source.readHexadecimalUnsignedLong();
                    CharSequence charSequence = Http1ExchangeCodec.this.source.readUtf8LineStrict();
                    boolean bl = false;
                    String string = charSequence;
                    if (string == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
                    }
                    String extensions = ((Object)StringsKt.trim((CharSequence)string)).toString();
                    if (this.bytesRemainingInChunk >= 0L) {
                        charSequence = extensions;
                        bl = false;
                        if (!(charSequence.length() > 0) || StringsKt.startsWith$default(extensions, ";", false, 2, null)) break block7;
                    }
                    throw (Throwable)new ProtocolException("expected chunk size and optional extensions" + " but was \"" + this.bytesRemainingInChunk + extensions + '\"');
                } catch (NumberFormatException e) {
                    throw (Throwable)new ProtocolException(e.getMessage());
                }
            }
            if (this.bytesRemainingInChunk == 0L) {
                this.hasMoreChunks = false;
                Http1ExchangeCodec.this.trailers = Http1ExchangeCodec.this.headersReader.readHeaders();
                OkHttpClient okHttpClient = Http1ExchangeCodec.this.client;
                Intrinsics.checkNotNull(okHttpClient);
                CookieJar cookieJar = okHttpClient.cookieJar();
                Headers headers = Http1ExchangeCodec.this.trailers;
                Intrinsics.checkNotNull(headers);
                HttpHeaders.receiveHeaders(cookieJar, this.url, headers);
                this.responseBodyComplete();
            }
        }

        @Override
        public void close() {
            if (this.getClosed()) {
                return;
            }
            if (this.hasMoreChunks && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                Http1ExchangeCodec.this.getConnection().noNewExchanges$okhttp();
                this.responseBodyComplete();
            }
            this.setClosed(true);
        }

        public ChunkedSource(HttpUrl url) {
            Intrinsics.checkNotNullParameter(url, "url");
            this.url = url;
            this.bytesRemainingInChunk = -1L;
            this.hasMoreChunks = true;
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2={"Lokhttp3/internal/http1/Http1ExchangeCodec$UnknownLengthSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "inputExhausted", "", "close", "", "read", "", "sink", "Lokio/Buffer;", "byteCount", "okhttp"})
    private final class UnknownLengthSource
    extends AbstractSource {
        private boolean inputExhausted;

        @Override
        public long read(@NotNull Buffer sink2, long byteCount) {
            Intrinsics.checkNotNullParameter(sink2, "sink");
            boolean bl = byteCount >= 0L;
            boolean bl2 = false;
            boolean bl3 = false;
            if (!bl) {
                boolean bl4 = false;
                String string = "byteCount < 0: " + byteCount;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            bl = !this.getClosed();
            bl2 = false;
            bl3 = false;
            if (!bl) {
                boolean bl5 = false;
                String string = "closed";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            if (this.inputExhausted) {
                return -1L;
            }
            long read = super.read(sink2, byteCount);
            if (read == -1L) {
                this.inputExhausted = true;
                this.responseBodyComplete();
                return -1L;
            }
            return read;
        }

        @Override
        public void close() {
            if (this.getClosed()) {
                return;
            }
            if (!this.inputExhausted) {
                this.responseBodyComplete();
            }
            this.setClosed(true);
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2={"Lokhttp3/internal/http1/Http1ExchangeCodec$Companion;", "", "()V", "NO_CHUNK_YET", "", "STATE_CLOSED", "", "STATE_IDLE", "STATE_OPEN_REQUEST_BODY", "STATE_OPEN_RESPONSE_BODY", "STATE_READING_RESPONSE_BODY", "STATE_READ_RESPONSE_HEADERS", "STATE_WRITING_REQUEST_BODY", "okhttp"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

