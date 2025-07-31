/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.http;

import java.net.Proxy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r\u00a8\u0006\u000e"}, d2={"Lokhttp3/internal/http/RequestLine;", "", "()V", "get", "", "request", "Lokhttp3/Request;", "proxyType", "Ljava/net/Proxy$Type;", "includeAuthorityInRequestLine", "", "requestPath", "url", "Lokhttp3/HttpUrl;", "okhttp"})
public final class RequestLine {
    public static final RequestLine INSTANCE;

    @NotNull
    public final String get(@NotNull Request request, @NotNull Proxy.Type proxyType) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter((Object)proxyType, "proxyType");
        boolean bl = false;
        boolean bl2 = false;
        StringBuilder stringBuilder = new StringBuilder();
        boolean bl3 = false;
        boolean bl4 = false;
        StringBuilder $this$buildString = stringBuilder;
        boolean bl5 = false;
        $this$buildString.append(request.method());
        $this$buildString.append(' ');
        if (INSTANCE.includeAuthorityInRequestLine(request, proxyType)) {
            $this$buildString.append(request.url());
        } else {
            $this$buildString.append(INSTANCE.requestPath(request.url()));
        }
        $this$buildString.append(" HTTP/1.1");
        String string = stringBuilder.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    private final boolean includeAuthorityInRequestLine(Request request, Proxy.Type proxyType) {
        return !request.isHttps() && proxyType == Proxy.Type.HTTP;
    }

    @NotNull
    public final String requestPath(@NotNull HttpUrl url) {
        Intrinsics.checkNotNullParameter(url, "url");
        String path = url.encodedPath();
        String query = url.encodedQuery();
        return query != null ? path + '?' + query : path;
    }

    private RequestLine() {
    }

    static {
        RequestLine requestLine;
        INSTANCE = requestLine = new RequestLine();
    }
}

