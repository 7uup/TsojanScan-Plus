/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpMethod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001*BA\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0016\u0010\n\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f\u0012\u0004\u0012\u00020\u00010\u000b\u00a2\u0006\u0002\u0010\rJ\u000f\u0010\b\u001a\u0004\u0018\u00010\tH\u0007\u00a2\u0006\u0002\b\u001bJ\r\u0010\u000f\u001a\u00020\u0010H\u0007\u00a2\u0006\u0002\b\u001cJ\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001e\u001a\u00020\u0005J\r\u0010\u0006\u001a\u00020\u0007H\u0007\u00a2\u0006\u0002\b\u001fJ\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050 2\u0006\u0010\u001e\u001a\u00020\u0005J\r\u0010\u0004\u001a\u00020\u0005H\u0007\u00a2\u0006\u0002\b!J\u0006\u0010\"\u001a\u00020#J\b\u0010$\u001a\u0004\u0018\u00010\u0001J#\u0010$\u001a\u0004\u0018\u0001H%\"\u0004\b\u0000\u0010%2\u000e\u0010&\u001a\n\u0012\u0006\b\u0001\u0012\u0002H%0\f\u00a2\u0006\u0002\u0010'J\b\u0010(\u001a\u00020\u0005H\u0016J\r\u0010\u0002\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\b)R\u0015\u0010\b\u001a\u0004\u0018\u00010\t8\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u00108G\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011R\u0013\u0010\u0006\u001a\u00020\u00078\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00148F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0015R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0013\u0010\u0004\u001a\u00020\u00058\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0017R$\u0010\n\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f\u0012\u0004\u0012\u00020\u00010\u000bX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u0002\u001a\u00020\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u001a\u00a8\u0006+"}, d2={"Lokhttp3/Request;", "", "url", "Lokhttp3/HttpUrl;", "method", "", "headers", "Lokhttp3/Headers;", "body", "Lokhttp3/RequestBody;", "tags", "", "Ljava/lang/Class;", "(Lokhttp3/HttpUrl;Ljava/lang/String;Lokhttp3/Headers;Lokhttp3/RequestBody;Ljava/util/Map;)V", "()Lokhttp3/RequestBody;", "cacheControl", "Lokhttp3/CacheControl;", "()Lokhttp3/CacheControl;", "()Lokhttp3/Headers;", "isHttps", "", "()Z", "lazyCacheControl", "()Ljava/lang/String;", "getTags$okhttp", "()Ljava/util/Map;", "()Lokhttp3/HttpUrl;", "-deprecated_body", "-deprecated_cacheControl", "header", "name", "-deprecated_headers", "", "-deprecated_method", "newBuilder", "Lokhttp3/Request$Builder;", "tag", "T", "type", "(Ljava/lang/Class;)Ljava/lang/Object;", "toString", "-deprecated_url", "Builder", "okhttp"})
public final class Request {
    private CacheControl lazyCacheControl;
    @NotNull
    private final HttpUrl url;
    @NotNull
    private final String method;
    @NotNull
    private final Headers headers;
    @Nullable
    private final RequestBody body;
    @NotNull
    private final Map<Class<?>, Object> tags;

    public final boolean isHttps() {
        return this.url.isHttps();
    }

    @Nullable
    public final String header(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.headers.get(name);
    }

    @NotNull
    public final List<String> headers(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.headers.values(name);
    }

    @Nullable
    public final Object tag() {
        return this.tag(Object.class);
    }

    @Nullable
    public final <T> T tag(@NotNull Class<? extends T> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return type.cast(this.tags.get(type));
    }

    @NotNull
    public final Builder newBuilder() {
        return new Builder(this);
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

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="url"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_url")
    @NotNull
    public final HttpUrl -deprecated_url() {
        return this.url;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="method"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_method")
    @NotNull
    public final String -deprecated_method() {
        return this.method;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="headers"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_headers")
    @NotNull
    public final Headers -deprecated_headers() {
        return this.headers;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="body"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_body")
    @Nullable
    public final RequestBody -deprecated_body() {
        return this.body;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="cacheControl"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_cacheControl")
    @NotNull
    public final CacheControl -deprecated_cacheControl() {
        return this.cacheControl();
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public String toString() {
        boolean bl = false;
        boolean bl2 = false;
        StringBuilder stringBuilder = new StringBuilder();
        boolean bl3 = false;
        boolean bl4 = false;
        StringBuilder $this$buildString = stringBuilder;
        boolean bl5 = false;
        $this$buildString.append("Request{method=");
        $this$buildString.append(this.method);
        $this$buildString.append(", url=");
        $this$buildString.append(this.url);
        if (this.headers.size() != 0) {
            $this$buildString.append(", headers=[");
            Iterable $this$forEachIndexed$iv = this.headers;
            boolean $i$f$forEachIndexed = false;
            int index$iv = 0;
            for (Object item$iv : $this$forEachIndexed$iv) {
                void name;
                void $dstr$name$value;
                int n = index$iv++;
                boolean bl6 = false;
                if (n < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Pair pair = (Pair)item$iv;
                int index = n;
                boolean bl7 = false;
                String string = (String)$dstr$name$value.component1();
                String value = (String)$dstr$name$value.component2();
                if (index > 0) {
                    $this$buildString.append(", ");
                }
                $this$buildString.append((String)name);
                $this$buildString.append(':');
                $this$buildString.append(value);
            }
            $this$buildString.append(']');
        }
        Map<Class<?>, Object> map = this.tags;
        boolean bl8 = false;
        if (!map.isEmpty()) {
            $this$buildString.append(", tags=");
            $this$buildString.append(this.tags);
        }
        $this$buildString.append('}');
        String string = stringBuilder.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    @JvmName(name="url")
    @NotNull
    public final HttpUrl url() {
        return this.url;
    }

    @JvmName(name="method")
    @NotNull
    public final String method() {
        return this.method;
    }

    @JvmName(name="headers")
    @NotNull
    public final Headers headers() {
        return this.headers;
    }

    @JvmName(name="body")
    @Nullable
    public final RequestBody body() {
        return this.body;
    }

    @NotNull
    public final Map<Class<?>, Object> getTags$okhttp() {
        return this.tags;
    }

    public Request(@NotNull HttpUrl url, @NotNull String method, @NotNull Headers headers, @Nullable RequestBody body, @NotNull Map<Class<?>, ? extends Object> tags) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(tags, "tags");
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.body = body;
        this.tags = tags;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0002B\u000f\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0018\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u00132\u0006\u0010'\u001a\u00020\u0013H\u0016J\b\u0010(\u001a\u00020\u0004H\u0016J\u0010\u0010)\u001a\u00020\u00002\u0006\u0010)\u001a\u00020*H\u0016J\u0014\u0010+\u001a\u00020\u00002\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0017J\b\u0010,\u001a\u00020\u0000H\u0016J\b\u0010-\u001a\u00020\u0000H\u0016J\u0018\u0010.\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u00132\u0006\u0010'\u001a\u00020\u0013H\u0016J\u0010\u0010\f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020/H\u0016J\u001a\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0010\u00100\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u00101\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u00102\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u00103\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0013H\u0016J-\u00104\u001a\u00020\u0000\"\u0004\b\u0000\u001052\u000e\u00106\u001a\n\u0012\u0006\b\u0000\u0012\u0002H50\u001a2\b\u00104\u001a\u0004\u0018\u0001H5H\u0016\u00a2\u0006\u0002\u00107J\u0012\u00104\u001a\u00020\u00002\b\u00104\u001a\u0004\u0018\u00010\u0001H\u0016J\u0010\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u000208H\u0016J\u0010\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u0013H\u0016J\u0010\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020 H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R*\u0010\u0018\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a\u0012\u0004\u0012\u00020\u00010\u0019X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$\u00a8\u00069"}, d2={"Lokhttp3/Request$Builder;", "", "()V", "request", "Lokhttp3/Request;", "(Lokhttp3/Request;)V", "body", "Lokhttp3/RequestBody;", "getBody$okhttp", "()Lokhttp3/RequestBody;", "setBody$okhttp", "(Lokhttp3/RequestBody;)V", "headers", "Lokhttp3/Headers$Builder;", "getHeaders$okhttp", "()Lokhttp3/Headers$Builder;", "setHeaders$okhttp", "(Lokhttp3/Headers$Builder;)V", "method", "", "getMethod$okhttp", "()Ljava/lang/String;", "setMethod$okhttp", "(Ljava/lang/String;)V", "tags", "", "Ljava/lang/Class;", "getTags$okhttp", "()Ljava/util/Map;", "setTags$okhttp", "(Ljava/util/Map;)V", "url", "Lokhttp3/HttpUrl;", "getUrl$okhttp", "()Lokhttp3/HttpUrl;", "setUrl$okhttp", "(Lokhttp3/HttpUrl;)V", "addHeader", "name", "value", "build", "cacheControl", "Lokhttp3/CacheControl;", "delete", "get", "head", "header", "Lokhttp3/Headers;", "patch", "post", "put", "removeHeader", "tag", "T", "type", "(Ljava/lang/Class;Ljava/lang/Object;)Lokhttp3/Request$Builder;", "Ljava/net/URL;", "okhttp"})
    public static class Builder {
        @Nullable
        private HttpUrl url;
        @NotNull
        private String method;
        @NotNull
        private Headers.Builder headers;
        @Nullable
        private RequestBody body;
        @NotNull
        private Map<Class<?>, Object> tags;

        @Nullable
        public final HttpUrl getUrl$okhttp() {
            return this.url;
        }

        public final void setUrl$okhttp(@Nullable HttpUrl httpUrl) {
            this.url = httpUrl;
        }

        @NotNull
        public final String getMethod$okhttp() {
            return this.method;
        }

        public final void setMethod$okhttp(@NotNull String string) {
            Intrinsics.checkNotNullParameter(string, "<set-?>");
            this.method = string;
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
        public final RequestBody getBody$okhttp() {
            return this.body;
        }

        public final void setBody$okhttp(@Nullable RequestBody requestBody) {
            this.body = requestBody;
        }

        @NotNull
        public final Map<Class<?>, Object> getTags$okhttp() {
            return this.tags;
        }

        public final void setTags$okhttp(@NotNull Map<Class<?>, Object> map) {
            Intrinsics.checkNotNullParameter(map, "<set-?>");
            this.tags = map;
        }

        @NotNull
        public Builder url(@NotNull HttpUrl url) {
            Intrinsics.checkNotNullParameter(url, "url");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.url = url;
            return builder;
        }

        @NotNull
        public Builder url(@NotNull String url) {
            String string;
            boolean bl;
            int n;
            String string2;
            Intrinsics.checkNotNullParameter(url, "url");
            if (StringsKt.startsWith(url, "ws:", true)) {
                StringBuilder stringBuilder = new StringBuilder().append("http:");
                string2 = url;
                n = 3;
                bl = false;
                String string3 = string2.substring(n);
                Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.String).substring(startIndex)");
                string = stringBuilder.append(string3).toString();
            } else if (StringsKt.startsWith(url, "wss:", true)) {
                StringBuilder stringBuilder = new StringBuilder().append("https:");
                string2 = url;
                n = 4;
                bl = false;
                String string4 = string2.substring(n);
                Intrinsics.checkNotNullExpressionValue(string4, "(this as java.lang.String).substring(startIndex)");
                string = stringBuilder.append(string4).toString();
            } else {
                string = url;
            }
            String finalUrl = string;
            return this.url(HttpUrl.Companion.get(finalUrl));
        }

        @NotNull
        public Builder url(@NotNull URL url) {
            Intrinsics.checkNotNullParameter(url, "url");
            String string = url.toString();
            Intrinsics.checkNotNullExpressionValue(string, "url.toString()");
            return this.url(HttpUrl.Companion.get(string));
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
        public Builder cacheControl(@NotNull CacheControl cacheControl) {
            Intrinsics.checkNotNullParameter(cacheControl, "cacheControl");
            String value = cacheControl.toString();
            CharSequence charSequence = value;
            boolean bl = false;
            return charSequence.length() == 0 ? this.removeHeader("Cache-Control") : this.header("Cache-Control", value);
        }

        @NotNull
        public Builder get() {
            return this.method("GET", null);
        }

        @NotNull
        public Builder head() {
            return this.method("HEAD", null);
        }

        @NotNull
        public Builder post(@NotNull RequestBody body) {
            Intrinsics.checkNotNullParameter(body, "body");
            return this.method("POST", body);
        }

        @JvmOverloads
        @NotNull
        public Builder delete(@Nullable RequestBody body) {
            return this.method("DELETE", body);
        }

        public static /* synthetic */ Builder delete$default(Builder builder, RequestBody requestBody, int n, Object object) {
            if (object != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: delete");
            }
            if ((n & 1) != 0) {
                requestBody = Util.EMPTY_REQUEST;
            }
            return builder.delete(requestBody);
        }

        @JvmOverloads
        @NotNull
        public final Builder delete() {
            return Builder.delete$default(this, null, 1, null);
        }

        @NotNull
        public Builder put(@NotNull RequestBody body) {
            Intrinsics.checkNotNullParameter(body, "body");
            return this.method("PUT", body);
        }

        @NotNull
        public Builder patch(@NotNull RequestBody body) {
            Intrinsics.checkNotNullParameter(body, "body");
            return this.method("PATCH", body);
        }

        @NotNull
        public Builder method(@NotNull String method, @Nullable RequestBody body) {
            Intrinsics.checkNotNullParameter(method, "method");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            CharSequence charSequence = method;
            boolean bl4 = false;
            boolean bl5 = charSequence.length() > 0;
            bl4 = false;
            boolean bl6 = false;
            if (!bl5) {
                boolean bl7 = false;
                String string = "method.isEmpty() == true";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            if (body == null) {
                bl5 = !HttpMethod.requiresRequestBody(method);
                bl4 = false;
                bl6 = false;
                if (!bl5) {
                    boolean bl8 = false;
                    String string = "method " + method + " must have a request body.";
                    throw (Throwable)new IllegalArgumentException(string.toString());
                }
            } else {
                bl5 = HttpMethod.permitsRequestBody(method);
                bl4 = false;
                bl6 = false;
                if (!bl5) {
                    boolean bl9 = false;
                    String string = "method " + method + " must not have a request body.";
                    throw (Throwable)new IllegalArgumentException(string.toString());
                }
            }
            $this$apply.method = method;
            $this$apply.body = body;
            return builder;
        }

        @NotNull
        public Builder tag(@Nullable Object tag) {
            return this.tag(Object.class, tag);
        }

        @NotNull
        public <T> Builder tag(@NotNull Class<? super T> type, @Nullable T tag) {
            Intrinsics.checkNotNullParameter(type, "type");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            if (tag == null) {
                $this$apply.tags.remove(type);
            } else {
                if ($this$apply.tags.isEmpty()) {
                    boolean bl4 = false;
                    $this$apply.tags = new LinkedHashMap();
                }
                Map<Class<?>, Object> map = $this$apply.tags;
                T t = type.cast(tag);
                Intrinsics.checkNotNull(t);
                map.put(type, t);
            }
            return builder;
        }

        @NotNull
        public Request build() {
            HttpUrl httpUrl = this.url;
            boolean bl = false;
            boolean bl2 = false;
            if (httpUrl == null) {
                String string;
                boolean bl3 = false;
                String string2 = string = "url == null";
                throw (Throwable)new IllegalStateException(string2.toString());
            }
            Map<Class<?>, Object> map = Util.toImmutableMap(this.tags);
            RequestBody requestBody = this.body;
            Headers headers = this.headers.build();
            String string = this.method;
            HttpUrl httpUrl2 = httpUrl;
            return new Request(httpUrl2, string, headers, requestBody, map);
        }

        public Builder() {
            boolean bl = false;
            this.tags = new LinkedHashMap();
            this.method = "GET";
            this.headers = new Headers.Builder();
        }

        public Builder(@NotNull Request request) {
            Map map;
            Intrinsics.checkNotNullParameter(request, "request");
            boolean bl = false;
            this.tags = new LinkedHashMap();
            this.url = request.url();
            this.method = request.method();
            this.body = request.body();
            if (request.getTags$okhttp().isEmpty()) {
                bl = false;
                map = new LinkedHashMap();
            } else {
                map = MapsKt.toMutableMap(request.getTags$okhttp());
            }
            this.tags = map;
            this.headers = request.headers().newBuilder();
        }
    }
}

