/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.cache;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.http.DatesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u000b2\u00020\u0001:\u0002\u000b\fB\u001b\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\r"}, d2={"Lokhttp3/internal/cache/CacheStrategy;", "", "networkRequest", "Lokhttp3/Request;", "cacheResponse", "Lokhttp3/Response;", "(Lokhttp3/Request;Lokhttp3/Response;)V", "getCacheResponse", "()Lokhttp3/Response;", "getNetworkRequest", "()Lokhttp3/Request;", "Companion", "Factory", "okhttp"})
public final class CacheStrategy {
    @Nullable
    private final Request networkRequest;
    @Nullable
    private final Response cacheResponse;
    public static final Companion Companion = new Companion(null);

    @Nullable
    public final Request getNetworkRequest() {
        return this.networkRequest;
    }

    @Nullable
    public final Response getCacheResponse() {
        return this.cacheResponse;
    }

    public CacheStrategy(@Nullable Request networkRequest, @Nullable Response cacheResponse) {
        this.networkRequest = networkRequest;
        this.cacheResponse = cacheResponse;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u0017\u001a\u00020\u0003H\u0002J\u0006\u0010\u0018\u001a\u00020\u0019J\b\u0010\u001a\u001a\u00020\u0019H\u0002J\b\u0010\u001b\u001a\u00020\u0003H\u0002J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\b\u0010\u001e\u001a\u00020\u001dH\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2={"Lokhttp3/internal/cache/CacheStrategy$Factory;", "", "nowMillis", "", "request", "Lokhttp3/Request;", "cacheResponse", "Lokhttp3/Response;", "(JLokhttp3/Request;Lokhttp3/Response;)V", "ageSeconds", "", "etag", "", "expires", "Ljava/util/Date;", "lastModified", "lastModifiedString", "receivedResponseMillis", "getRequest$okhttp", "()Lokhttp3/Request;", "sentRequestMillis", "servedDate", "servedDateString", "cacheResponseAge", "compute", "Lokhttp3/internal/cache/CacheStrategy;", "computeCandidate", "computeFreshnessLifetime", "hasConditions", "", "isFreshnessLifetimeHeuristic", "okhttp"})
    public static final class Factory {
        private Date servedDate;
        private String servedDateString;
        private Date lastModified;
        private String lastModifiedString;
        private Date expires;
        private long sentRequestMillis;
        private long receivedResponseMillis;
        private String etag;
        private int ageSeconds;
        private final long nowMillis;
        @NotNull
        private final Request request;
        private final Response cacheResponse;

        private final boolean isFreshnessLifetimeHeuristic() {
            Response response = this.cacheResponse;
            Intrinsics.checkNotNull(response);
            return response.cacheControl().maxAgeSeconds() == -1 && this.expires == null;
        }

        @NotNull
        public final CacheStrategy compute() {
            CacheStrategy candidate = this.computeCandidate();
            if (candidate.getNetworkRequest() != null && this.request.cacheControl().onlyIfCached()) {
                return new CacheStrategy(null, null);
            }
            return candidate;
        }

        private final CacheStrategy computeCandidate() {
            if (this.cacheResponse == null) {
                return new CacheStrategy(this.request, null);
            }
            if (this.request.isHttps() && this.cacheResponse.handshake() == null) {
                return new CacheStrategy(this.request, null);
            }
            if (!Companion.isCacheable(this.cacheResponse, this.request)) {
                return new CacheStrategy(this.request, null);
            }
            CacheControl requestCaching = this.request.cacheControl();
            if (requestCaching.noCache() || this.hasConditions(this.request)) {
                return new CacheStrategy(this.request, null);
            }
            CacheControl responseCaching = this.cacheResponse.cacheControl();
            long ageMillis = this.cacheResponseAge();
            long freshMillis = this.computeFreshnessLifetime();
            if (requestCaching.maxAgeSeconds() != -1) {
                long l = TimeUnit.SECONDS.toMillis(requestCaching.maxAgeSeconds());
                boolean bl = false;
                freshMillis = Math.min(freshMillis, l);
            }
            long minFreshMillis = 0L;
            if (requestCaching.minFreshSeconds() != -1) {
                minFreshMillis = TimeUnit.SECONDS.toMillis(requestCaching.minFreshSeconds());
            }
            long maxStaleMillis = 0L;
            if (!responseCaching.mustRevalidate() && requestCaching.maxStaleSeconds() != -1) {
                maxStaleMillis = TimeUnit.SECONDS.toMillis(requestCaching.maxStaleSeconds());
            }
            if (!responseCaching.noCache() && ageMillis + minFreshMillis < freshMillis + maxStaleMillis) {
                long oneDayMillis;
                Response.Builder builder = this.cacheResponse.newBuilder();
                if (ageMillis + minFreshMillis >= freshMillis) {
                    builder.addHeader("Warning", "110 HttpURLConnection \"Response is stale\"");
                }
                if (ageMillis > (oneDayMillis = 86400000L) && this.isFreshnessLifetimeHeuristic()) {
                    builder.addHeader("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                }
                return new CacheStrategy(null, builder.build());
            }
            String conditionName = null;
            String conditionValue = null;
            if (this.etag != null) {
                conditionName = "If-None-Match";
                conditionValue = this.etag;
            } else if (this.lastModified != null) {
                conditionName = "If-Modified-Since";
                conditionValue = this.lastModifiedString;
            } else if (this.servedDate != null) {
                conditionName = "If-Modified-Since";
                conditionValue = this.servedDateString;
            } else {
                return new CacheStrategy(this.request, null);
            }
            Headers.Builder conditionalRequestHeaders = this.request.headers().newBuilder();
            String string = conditionValue;
            Intrinsics.checkNotNull(string);
            conditionalRequestHeaders.addLenient$okhttp(conditionName, string);
            Request conditionalRequest = this.request.newBuilder().headers(conditionalRequestHeaders.build()).build();
            return new CacheStrategy(conditionalRequest, this.cacheResponse);
        }

        private final long computeFreshnessLifetime() {
            Response response = this.cacheResponse;
            Intrinsics.checkNotNull(response);
            CacheControl responseCaching = response.cacheControl();
            if (responseCaching.maxAgeSeconds() != -1) {
                return TimeUnit.SECONDS.toMillis(responseCaching.maxAgeSeconds());
            }
            Date expires = this.expires;
            if (expires != null) {
                Date date = this.servedDate;
                long servedMillis = date != null ? date.getTime() : this.receivedResponseMillis;
                long delta = expires.getTime() - servedMillis;
                return delta > 0L ? delta : 0L;
            }
            if (this.lastModified != null && this.cacheResponse.request().url().query() == null) {
                Date date = this.servedDate;
                long servedMillis = date != null ? date.getTime() : this.sentRequestMillis;
                Date date2 = this.lastModified;
                Intrinsics.checkNotNull(date2);
                long delta = servedMillis - date2.getTime();
                return delta > 0L ? delta / (long)10 : 0L;
            }
            return 0L;
        }

        private final long cacheResponseAge() {
            long l;
            long apparentReceivedAge;
            long l2;
            boolean bl;
            long l3;
            Date servedDate = this.servedDate;
            if (servedDate != null) {
                long l4 = 0L;
                l3 = this.receivedResponseMillis - servedDate.getTime();
                bl = false;
                l2 = Math.max(l4, l3);
            } else {
                l2 = apparentReceivedAge = 0L;
            }
            if (this.ageSeconds != -1) {
                l3 = TimeUnit.SECONDS.toMillis(this.ageSeconds);
                bl = false;
                l = Math.max(apparentReceivedAge, l3);
            } else {
                l = apparentReceivedAge;
            }
            long receivedAge = l;
            long responseDuration = this.receivedResponseMillis - this.sentRequestMillis;
            long residentDuration = this.nowMillis - this.receivedResponseMillis;
            return receivedAge + responseDuration + residentDuration;
        }

        private final boolean hasConditions(Request request) {
            return request.header("If-Modified-Since") != null || request.header("If-None-Match") != null;
        }

        @NotNull
        public final Request getRequest$okhttp() {
            return this.request;
        }

        /*
         * WARNING - void declaration
         */
        public Factory(long nowMillis, @NotNull Request request, @Nullable Response cacheResponse) {
            Intrinsics.checkNotNullParameter(request, "request");
            this.nowMillis = nowMillis;
            this.request = request;
            this.cacheResponse = cacheResponse;
            this.ageSeconds = -1;
            if (this.cacheResponse != null) {
                this.sentRequestMillis = this.cacheResponse.sentRequestAtMillis();
                this.receivedResponseMillis = this.cacheResponse.receivedResponseAtMillis();
                Headers headers = this.cacheResponse.headers();
                int n = 0;
                int n2 = headers.size();
                while (n < n2) {
                    void i;
                    String fieldName = headers.name((int)i);
                    String value = headers.value((int)i);
                    if (StringsKt.equals(fieldName, "Date", true)) {
                        this.servedDate = DatesKt.toHttpDateOrNull(value);
                        this.servedDateString = value;
                    } else if (StringsKt.equals(fieldName, "Expires", true)) {
                        this.expires = DatesKt.toHttpDateOrNull(value);
                    } else if (StringsKt.equals(fieldName, "Last-Modified", true)) {
                        this.lastModified = DatesKt.toHttpDateOrNull(value);
                        this.lastModifiedString = value;
                    } else if (StringsKt.equals(fieldName, "ETag", true)) {
                        this.etag = value;
                    } else if (StringsKt.equals(fieldName, "Age", true)) {
                        this.ageSeconds = Util.toNonNegativeInt(value, -1);
                    }
                    ++i;
                }
            }
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\t"}, d2={"Lokhttp3/internal/cache/CacheStrategy$Companion;", "", "()V", "isCacheable", "", "response", "Lokhttp3/Response;", "request", "Lokhttp3/Request;", "okhttp"})
    public static final class Companion {
        public final boolean isCacheable(@NotNull Response response, @NotNull Request request) {
            Intrinsics.checkNotNullParameter(response, "response");
            Intrinsics.checkNotNullParameter(request, "request");
            switch (response.code()) {
                case 200: 
                case 203: 
                case 204: 
                case 300: 
                case 301: 
                case 308: 
                case 404: 
                case 405: 
                case 410: 
                case 414: 
                case 501: {
                    break;
                }
                case 302: 
                case 307: {
                    if (Response.header$default(response, "Expires", null, 2, null) != null || response.cacheControl().maxAgeSeconds() != -1 || response.cacheControl().isPublic() || response.cacheControl().isPrivate()) break;
                    return false;
                }
                default: {
                    return false;
                }
            }
            return !response.cacheControl().noStore() && !request.cacheControl().noStore();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

