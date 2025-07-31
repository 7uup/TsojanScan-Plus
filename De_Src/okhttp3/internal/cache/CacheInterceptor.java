/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.cache;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.RealResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\bH\u0002J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2={"Lokhttp3/internal/cache/CacheInterceptor;", "Lokhttp3/Interceptor;", "cache", "Lokhttp3/Cache;", "(Lokhttp3/Cache;)V", "getCache$okhttp", "()Lokhttp3/Cache;", "cacheWritingResponse", "Lokhttp3/Response;", "cacheRequest", "Lokhttp3/internal/cache/CacheRequest;", "response", "intercept", "chain", "Lokhttp3/Interceptor$Chain;", "Companion", "okhttp"})
public final class CacheInterceptor
implements Interceptor {
    @Nullable
    private final Cache cache;
    public static final Companion Companion = new Companion(null);

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        Response networkResponse;
        Object listener;
        Response cacheResponse;
        Request networkRequest;
        Call call;
        block25: {
            ResponseBody responseBody;
            Object object;
            Call call2;
            Intrinsics.checkNotNullParameter(chain, "chain");
            call = chain.call();
            Cache cache = this.cache;
            Response cacheCandidate = cache != null ? cache.get$okhttp(chain.request()) : null;
            long now = System.currentTimeMillis();
            CacheStrategy strategy = new CacheStrategy.Factory(now, chain.request(), cacheCandidate).compute();
            networkRequest = strategy.getNetworkRequest();
            cacheResponse = strategy.getCacheResponse();
            Cache cache2 = this.cache;
            if (cache2 != null) {
                cache2.trackResponse$okhttp(strategy);
            }
            if (!((call2 = call) instanceof RealCall)) {
                call2 = null;
            }
            if ((object = (RealCall)call2) == null || (object = ((RealCall)object).getEventListener$okhttp()) == null) {
                object = listener = EventListener.NONE;
            }
            if (cacheCandidate != null && cacheResponse == null) {
                ResponseBody responseBody2 = cacheCandidate.body();
                if (responseBody2 != null) {
                    Util.closeQuietly(responseBody2);
                }
            }
            if (networkRequest == null && cacheResponse == null) {
                Response response = new Response.Builder().request(chain.request()).protocol(Protocol.HTTP_1_1).code(504).message("Unsatisfiable Request (only-if-cached)").body(Util.EMPTY_RESPONSE).sentRequestAtMillis(-1L).receivedResponseAtMillis(System.currentTimeMillis()).build();
                boolean bl = false;
                boolean bl2 = false;
                Response it = response;
                boolean bl3 = false;
                ((EventListener)listener).satisfactionFailure(call, it);
                return response;
            }
            if (networkRequest == null) {
                Response response = cacheResponse;
                Intrinsics.checkNotNull(response);
                Response response2 = response.newBuilder().cacheResponse(CacheInterceptor.Companion.stripBody(cacheResponse)).build();
                boolean bl = false;
                boolean bl4 = false;
                Response it = response2;
                boolean bl5 = false;
                ((EventListener)listener).cacheHit(call, it);
                return response2;
            }
            if (cacheResponse != null) {
                ((EventListener)listener).cacheConditionalHit(call, cacheResponse);
            } else if (this.cache != null) {
                ((EventListener)listener).cacheMiss(call);
            }
            networkResponse = null;
            try {
                networkResponse = chain.proceed(networkRequest);
                if (networkResponse != null || cacheCandidate == null) break block25;
                responseBody = cacheCandidate.body();
            } catch (Throwable throwable) {
                if (cacheCandidate != null) {
                    ResponseBody responseBody3 = cacheCandidate.body();
                    if (responseBody3 != null) {
                        Util.closeQuietly(responseBody3);
                    }
                }
                throw throwable;
            }
            if (responseBody != null) {
                Util.closeQuietly(responseBody);
            }
        }
        if (cacheResponse != null) {
            Response response = networkResponse;
            if (response != null) {
                if (response.code() == 304) {
                    Response response3 = cacheResponse.newBuilder().headers(CacheInterceptor.Companion.combine(cacheResponse.headers(), networkResponse.headers())).sentRequestAtMillis(networkResponse.sentRequestAtMillis()).receivedResponseAtMillis(networkResponse.receivedResponseAtMillis()).cacheResponse(CacheInterceptor.Companion.stripBody(cacheResponse)).networkResponse(CacheInterceptor.Companion.stripBody(networkResponse)).build();
                    ResponseBody responseBody = networkResponse.body();
                    Intrinsics.checkNotNull(responseBody);
                    responseBody.close();
                    Cache cache = this.cache;
                    Intrinsics.checkNotNull(cache);
                    cache.trackConditionalCacheHit$okhttp();
                    this.cache.update$okhttp(cacheResponse, response3);
                    Response response4 = response3;
                    boolean bl = false;
                    boolean bl6 = false;
                    Response it = response4;
                    boolean bl7 = false;
                    ((EventListener)listener).cacheHit(call, it);
                    return response4;
                }
            }
            ResponseBody responseBody = cacheResponse.body();
            if (responseBody != null) {
                Util.closeQuietly(responseBody);
            }
        }
        Response response = networkResponse;
        Intrinsics.checkNotNull(response);
        Response response5 = response.newBuilder().cacheResponse(CacheInterceptor.Companion.stripBody(cacheResponse)).networkResponse(CacheInterceptor.Companion.stripBody(networkResponse)).build();
        if (this.cache != null) {
            if (HttpHeaders.promisesBody(response5) && CacheStrategy.Companion.isCacheable(response5, networkRequest)) {
                CacheRequest cacheRequest = this.cache.put$okhttp(response5);
                Response response6 = this.cacheWritingResponse(cacheRequest, response5);
                boolean bl = false;
                boolean bl8 = false;
                Response it = response6;
                boolean bl9 = false;
                if (cacheResponse != null) {
                    ((EventListener)listener).cacheMiss(call);
                }
                return response6;
            }
            if (HttpMethod.INSTANCE.invalidatesCache(networkRequest.method())) {
                try {
                    this.cache.remove$okhttp(networkRequest);
                } catch (IOException iOException) {
                    // empty catch block
                }
            }
        }
        return response5;
    }

    private final Response cacheWritingResponse(CacheRequest cacheRequest, Response response) throws IOException {
        if (cacheRequest == null) {
            return response;
        }
        Sink cacheBodyUnbuffered = cacheRequest.body();
        ResponseBody responseBody = response.body();
        Intrinsics.checkNotNull(responseBody);
        BufferedSource source2 = responseBody.source();
        BufferedSink cacheBody = Okio.buffer(cacheBodyUnbuffered);
        Source cacheWritingSource2 = new Source(source2, cacheRequest, cacheBody){
            private boolean cacheRequestClosed;
            final /* synthetic */ BufferedSource $source;
            final /* synthetic */ CacheRequest $cacheRequest;
            final /* synthetic */ BufferedSink $cacheBody;

            public long read(@NotNull Buffer sink2, long byteCount) throws IOException {
                Intrinsics.checkNotNullParameter(sink2, "sink");
                long bytesRead = 0L;
                try {
                    bytesRead = this.$source.read(sink2, byteCount);
                } catch (IOException e) {
                    if (!this.cacheRequestClosed) {
                        this.cacheRequestClosed = true;
                        this.$cacheRequest.abort();
                    }
                    throw (Throwable)e;
                }
                if (bytesRead == -1L) {
                    if (!this.cacheRequestClosed) {
                        this.cacheRequestClosed = true;
                        this.$cacheBody.close();
                    }
                    return -1L;
                }
                sink2.copyTo(this.$cacheBody.getBuffer(), sink2.size() - bytesRead, bytesRead);
                this.$cacheBody.emitCompleteSegments();
                return bytesRead;
            }

            @NotNull
            public Timeout timeout() {
                return this.$source.timeout();
            }

            public void close() throws IOException {
                if (!this.cacheRequestClosed && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                    this.cacheRequestClosed = true;
                    this.$cacheRequest.abort();
                }
                this.$source.close();
            }
            {
                this.$source = $captured_local_variable$0;
                this.$cacheRequest = $captured_local_variable$1;
                this.$cacheBody = $captured_local_variable$2;
            }
        };
        String contentType = Response.header$default(response, "Content-Type", null, 2, null);
        long contentLength = response.body().contentLength();
        return response.newBuilder().body(new RealResponseBody(contentType, contentLength, Okio.buffer(cacheWritingSource2))).build();
    }

    @Nullable
    public final Cache getCache$okhttp() {
        return this.cache;
    }

    public CacheInterceptor(@Nullable Cache cache) {
        this.cache = cache;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0014\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0002\u00a8\u0006\u000f"}, d2={"Lokhttp3/internal/cache/CacheInterceptor$Companion;", "", "()V", "combine", "Lokhttp3/Headers;", "cachedHeaders", "networkHeaders", "isContentSpecificHeader", "", "fieldName", "", "isEndToEnd", "stripBody", "Lokhttp3/Response;", "response", "okhttp"})
    public static final class Companion {
        private final Response stripBody(Response response) {
            Response response2 = response;
            return (response2 != null ? response2.body() : null) != null ? response.newBuilder().body(null).build() : response;
        }

        private final Headers combine(Headers cachedHeaders, Headers networkHeaders) {
            String fieldName;
            int index;
            Headers.Builder result = new Headers.Builder();
            int n = 0;
            int n2 = cachedHeaders.size();
            while (n < n2) {
                fieldName = cachedHeaders.name(index);
                String value = cachedHeaders.value(index);
                if (!(StringsKt.equals("Warning", fieldName, true) && StringsKt.startsWith$default(value, "1", false, 2, null) || !this.isContentSpecificHeader(fieldName) && this.isEndToEnd(fieldName) && networkHeaders.get(fieldName) != null)) {
                    result.addLenient$okhttp(fieldName, value);
                }
                ++index;
            }
            n2 = networkHeaders.size();
            for (index = 0; index < n2; ++index) {
                fieldName = networkHeaders.name(index);
                if (this.isContentSpecificHeader(fieldName) || !this.isEndToEnd(fieldName)) continue;
                result.addLenient$okhttp(fieldName, networkHeaders.value(index));
            }
            return result.build();
        }

        private final boolean isEndToEnd(String fieldName) {
            return !StringsKt.equals("Connection", fieldName, true) && !StringsKt.equals("Keep-Alive", fieldName, true) && !StringsKt.equals("Proxy-Authenticate", fieldName, true) && !StringsKt.equals("Proxy-Authorization", fieldName, true) && !StringsKt.equals("TE", fieldName, true) && !StringsKt.equals("Trailers", fieldName, true) && !StringsKt.equals("Transfer-Encoding", fieldName, true) && !StringsKt.equals("Upgrade", fieldName, true);
        }

        private final boolean isContentSpecificHeader(String fieldName) {
            return StringsKt.equals("Content-Length", fieldName, true) || StringsKt.equals("Content-Encoding", fieldName, true) || StringsKt.equals("Content-Type", fieldName, true);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

