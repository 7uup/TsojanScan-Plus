/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.http;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\b\u0012\u0006\u0010\u000f\u001a\u00020\b\u00a2\u0006\u0002\u0010\u0010J\b\u0010\u0002\u001a\u00020\u001cH\u0016J\b\u0010\r\u001a\u00020\bH\u0016J\n\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016JK\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\bH\u0000\u00a2\u0006\u0002\b J\u0010\u0010!\u001a\u00020\"2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\u000e\u001a\u00020\bH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010#\u001a\u00020\u00012\u0006\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020&H\u0016J\u0018\u0010'\u001a\u00020\u00012\u0006\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020&H\u0016J\u0018\u0010(\u001a\u00020\u00012\u0006\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020&H\u0016J\b\u0010\u000f\u001a\u00020\bH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\bX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\t\u001a\u0004\u0018\u00010\nX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\bX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0014\u0010\u000b\u001a\u00020\fX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u000f\u001a\u00020\bX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015\u00a8\u0006)"}, d2={"Lokhttp3/internal/http/RealInterceptorChain;", "Lokhttp3/Interceptor$Chain;", "call", "Lokhttp3/internal/connection/RealCall;", "interceptors", "", "Lokhttp3/Interceptor;", "index", "", "exchange", "Lokhttp3/internal/connection/Exchange;", "request", "Lokhttp3/Request;", "connectTimeoutMillis", "readTimeoutMillis", "writeTimeoutMillis", "(Lokhttp3/internal/connection/RealCall;Ljava/util/List;ILokhttp3/internal/connection/Exchange;Lokhttp3/Request;III)V", "getCall$okhttp", "()Lokhttp3/internal/connection/RealCall;", "calls", "getConnectTimeoutMillis$okhttp", "()I", "getExchange$okhttp", "()Lokhttp3/internal/connection/Exchange;", "getReadTimeoutMillis$okhttp", "getRequest$okhttp", "()Lokhttp3/Request;", "getWriteTimeoutMillis$okhttp", "Lokhttp3/Call;", "connection", "Lokhttp3/Connection;", "copy", "copy$okhttp", "proceed", "Lokhttp3/Response;", "withConnectTimeout", "timeout", "unit", "Ljava/util/concurrent/TimeUnit;", "withReadTimeout", "withWriteTimeout", "okhttp"})
public final class RealInterceptorChain
implements Interceptor.Chain {
    private int calls;
    @NotNull
    private final RealCall call;
    private final List<Interceptor> interceptors;
    private final int index;
    @Nullable
    private final Exchange exchange;
    @NotNull
    private final Request request;
    private final int connectTimeoutMillis;
    private final int readTimeoutMillis;
    private final int writeTimeoutMillis;

    @NotNull
    public final RealInterceptorChain copy$okhttp(int index, @Nullable Exchange exchange, @NotNull Request request, int connectTimeoutMillis, int readTimeoutMillis, int writeTimeoutMillis) {
        Intrinsics.checkNotNullParameter(request, "request");
        return new RealInterceptorChain(this.call, this.interceptors, index, exchange, request, connectTimeoutMillis, readTimeoutMillis, writeTimeoutMillis);
    }

    public static /* synthetic */ RealInterceptorChain copy$okhttp$default(RealInterceptorChain realInterceptorChain, int n, Exchange exchange, Request request, int n2, int n3, int n4, int n5, Object object) {
        if ((n5 & 1) != 0) {
            n = realInterceptorChain.index;
        }
        if ((n5 & 2) != 0) {
            exchange = realInterceptorChain.exchange;
        }
        if ((n5 & 4) != 0) {
            request = realInterceptorChain.request;
        }
        if ((n5 & 8) != 0) {
            n2 = realInterceptorChain.connectTimeoutMillis;
        }
        if ((n5 & 0x10) != 0) {
            n3 = realInterceptorChain.readTimeoutMillis;
        }
        if ((n5 & 0x20) != 0) {
            n4 = realInterceptorChain.writeTimeoutMillis;
        }
        return realInterceptorChain.copy$okhttp(n, exchange, request, n2, n3, n4);
    }

    @Override
    @Nullable
    public Connection connection() {
        Exchange exchange = this.exchange;
        return exchange != null ? exchange.getConnection$okhttp() : null;
    }

    @Override
    public int connectTimeoutMillis() {
        return this.connectTimeoutMillis;
    }

    @Override
    @NotNull
    public Interceptor.Chain withConnectTimeout(int timeout2, @NotNull TimeUnit unit) {
        Intrinsics.checkNotNullParameter((Object)unit, "unit");
        boolean bl = this.exchange == null;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "Timeouts can't be adjusted in a network interceptor";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        return RealInterceptorChain.copy$okhttp$default(this, 0, null, null, Util.checkDuration("connectTimeout", timeout2, unit), 0, 0, 55, null);
    }

    @Override
    public int readTimeoutMillis() {
        return this.readTimeoutMillis;
    }

    @Override
    @NotNull
    public Interceptor.Chain withReadTimeout(int timeout2, @NotNull TimeUnit unit) {
        Intrinsics.checkNotNullParameter((Object)unit, "unit");
        boolean bl = this.exchange == null;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "Timeouts can't be adjusted in a network interceptor";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        return RealInterceptorChain.copy$okhttp$default(this, 0, null, null, 0, Util.checkDuration("readTimeout", timeout2, unit), 0, 47, null);
    }

    @Override
    public int writeTimeoutMillis() {
        return this.writeTimeoutMillis;
    }

    @Override
    @NotNull
    public Interceptor.Chain withWriteTimeout(int timeout2, @NotNull TimeUnit unit) {
        Intrinsics.checkNotNullParameter((Object)unit, "unit");
        boolean bl = this.exchange == null;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "Timeouts can't be adjusted in a network interceptor";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        return RealInterceptorChain.copy$okhttp$default(this, 0, null, null, 0, 0, Util.checkDuration("writeTimeout", timeout2, unit), 31, null);
    }

    @Override
    @NotNull
    public Call call() {
        return this.call;
    }

    @Override
    @NotNull
    public Request request() {
        return this.request;
    }

    @Override
    @NotNull
    public Response proceed(@NotNull Request request) throws IOException {
        boolean bl;
        boolean bl2;
        Intrinsics.checkNotNullParameter(request, "request");
        int n = this.index < this.interceptors.size() ? 1 : 0;
        boolean bl3 = false;
        boolean bl4 = false;
        bl4 = false;
        boolean bl5 = false;
        if (n == 0) {
            boolean bl6 = false;
            String string = "Check failed.";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        n = this.calls;
        this.calls = n + 1;
        if (this.exchange != null) {
            n = this.exchange.getFinder$okhttp().sameHostAndPort(request.url()) ? 1 : 0;
            bl3 = false;
            bl4 = false;
            if (n == 0) {
                boolean bl7 = false;
                String string = "network interceptor " + this.interceptors.get(this.index - 1) + " must retain the same host and port";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            n = this.calls == 1 ? 1 : 0;
            bl3 = false;
            bl4 = false;
            if (n == 0) {
                boolean bl8 = false;
                String string = "network interceptor " + this.interceptors.get(this.index - 1) + " must call proceed() exactly once";
                throw (Throwable)new IllegalStateException(string.toString());
            }
        }
        RealInterceptorChain next = RealInterceptorChain.copy$okhttp$default(this, this.index + 1, null, request, 0, 0, 0, 58, null);
        Interceptor interceptor = this.interceptors.get(this.index);
        Response response = interceptor.intercept(next);
        if (response == null) {
            throw (Throwable)new NullPointerException("interceptor " + interceptor + " returned null");
        }
        Response response2 = response;
        if (this.exchange != null) {
            bl5 = this.index + 1 >= this.interceptors.size() || next.calls == 1;
            bl2 = false;
            bl = false;
            if (!bl5) {
                boolean bl9 = false;
                String string = "network interceptor " + interceptor + " must call proceed() exactly once";
                throw (Throwable)new IllegalStateException(string.toString());
            }
        }
        bl5 = response2.body() != null;
        bl2 = false;
        bl = false;
        if (!bl5) {
            boolean bl10 = false;
            String string = "interceptor " + interceptor + " returned a response with no body";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        return response2;
    }

    @NotNull
    public final RealCall getCall$okhttp() {
        return this.call;
    }

    @Nullable
    public final Exchange getExchange$okhttp() {
        return this.exchange;
    }

    @NotNull
    public final Request getRequest$okhttp() {
        return this.request;
    }

    public final int getConnectTimeoutMillis$okhttp() {
        return this.connectTimeoutMillis;
    }

    public final int getReadTimeoutMillis$okhttp() {
        return this.readTimeoutMillis;
    }

    public final int getWriteTimeoutMillis$okhttp() {
        return this.writeTimeoutMillis;
    }

    public RealInterceptorChain(@NotNull RealCall call, @NotNull List<? extends Interceptor> interceptors, int index, @Nullable Exchange exchange, @NotNull Request request, int connectTimeoutMillis, int readTimeoutMillis, int writeTimeoutMillis) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(interceptors, "interceptors");
        Intrinsics.checkNotNullParameter(request, "request");
        this.call = call;
        this.interceptors = interceptors;
        this.index = index;
        this.exchange = exchange;
        this.request = request;
        this.connectTimeoutMillis = connectTimeoutMillis;
        this.readTimeoutMillis = readTimeoutMillis;
        this.writeTimeoutMillis = writeTimeoutMillis;
    }
}

