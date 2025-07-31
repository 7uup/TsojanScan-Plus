/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.connection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CertificatePinner;
import okhttp3.Dispatcher;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.internal.connection.ConnectInterceptor;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.ExchangeFinder;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RealConnectionPool;
import okhttp3.internal.http.BridgeInterceptor;
import okhttp3.internal.http.CallServerInterceptor;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;
import okhttp3.internal.platform.Platform;
import okio.AsyncTimeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u00a7\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001.\u0018\u00002\u00020\u0001:\u0002deB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u00101\u001a\u0002022\u0006\u0010\u0010\u001a\u00020\u000fJ!\u00103\u001a\u0002H4\"\n\b\u0000\u00104*\u0004\u0018\u0001052\u0006\u00106\u001a\u0002H4H\u0002\u00a2\u0006\u0002\u00107J\b\u00108\u001a\u000202H\u0002J\b\u00109\u001a\u000202H\u0016J\b\u0010:\u001a\u00020\u0000H\u0016J\u0010\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>H\u0002J\u0010\u0010?\u001a\u0002022\u0006\u0010@\u001a\u00020AH\u0016J\u0016\u0010B\u001a\u0002022\u0006\u0010C\u001a\u00020\u00052\u0006\u0010D\u001a\u00020\u0007J\b\u0010E\u001a\u00020FH\u0016J\u0015\u0010G\u001a\u0002022\u0006\u0010H\u001a\u00020\u0007H\u0000\u00a2\u0006\u0002\bIJ\r\u0010J\u001a\u00020FH\u0000\u00a2\u0006\u0002\bKJ\u0015\u0010L\u001a\u00020\u001e2\u0006\u0010M\u001a\u00020NH\u0000\u00a2\u0006\u0002\bOJ\b\u0010P\u001a\u00020\u0007H\u0016J\b\u0010Q\u001a\u00020\u0007H\u0016J;\u0010R\u001a\u0002H4\"\n\b\u0000\u00104*\u0004\u0018\u0001052\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010S\u001a\u00020\u00072\u0006\u0010T\u001a\u00020\u00072\u0006\u00106\u001a\u0002H4H\u0000\u00a2\u0006\u0004\bU\u0010VJ\u0019\u0010W\u001a\u0004\u0018\u0001052\b\u00106\u001a\u0004\u0018\u000105H\u0000\u00a2\u0006\u0002\bXJ\r\u0010Y\u001a\u00020ZH\u0000\u00a2\u0006\u0002\b[J\u000f\u0010\\\u001a\u0004\u0018\u00010]H\u0000\u00a2\u0006\u0002\b^J\b\u0010C\u001a\u00020\u0005H\u0016J\u0006\u0010_\u001a\u00020\u0007J\b\u0010-\u001a\u00020`H\u0016J\u0006\u00100\u001a\u000202J!\u0010a\u001a\u0002H4\"\n\b\u0000\u00104*\u0004\u0018\u0001052\u0006\u0010b\u001a\u0002H4H\u0002\u00a2\u0006\u0002\u00107J\b\u0010c\u001a\u00020ZH\u0002R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\"\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u001aX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\"\u0010&\u001a\u0004\u0018\u00010\u001e2\b\u0010\u000e\u001a\u0004\u0018\u00010\u001e@BX\u0080\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u00020.X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010/R\u000e\u00100\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006f"}, d2={"Lokhttp3/internal/connection/RealCall;", "Lokhttp3/Call;", "client", "Lokhttp3/OkHttpClient;", "originalRequest", "Lokhttp3/Request;", "forWebSocket", "", "(Lokhttp3/OkHttpClient;Lokhttp3/Request;Z)V", "callStackTrace", "", "canceled", "getClient", "()Lokhttp3/OkHttpClient;", "<set-?>", "Lokhttp3/internal/connection/RealConnection;", "connection", "getConnection", "()Lokhttp3/internal/connection/RealConnection;", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "connectionToCancel", "getConnectionToCancel", "setConnectionToCancel", "(Lokhttp3/internal/connection/RealConnection;)V", "eventListener", "Lokhttp3/EventListener;", "getEventListener$okhttp", "()Lokhttp3/EventListener;", "exchange", "Lokhttp3/internal/connection/Exchange;", "exchangeFinder", "Lokhttp3/internal/connection/ExchangeFinder;", "executed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "expectMoreExchanges", "getForWebSocket", "()Z", "interceptorScopedExchange", "getInterceptorScopedExchange$okhttp", "()Lokhttp3/internal/connection/Exchange;", "getOriginalRequest", "()Lokhttp3/Request;", "requestBodyOpen", "responseBodyOpen", "timeout", "okhttp3/internal/connection/RealCall$timeout$1", "Lokhttp3/internal/connection/RealCall$timeout$1;", "timeoutEarlyExit", "acquireConnectionNoEvents", "", "callDone", "E", "Ljava/io/IOException;", "e", "(Ljava/io/IOException;)Ljava/io/IOException;", "callStart", "cancel", "clone", "createAddress", "Lokhttp3/Address;", "url", "Lokhttp3/HttpUrl;", "enqueue", "responseCallback", "Lokhttp3/Callback;", "enterNetworkInterceptorExchange", "request", "newExchangeFinder", "execute", "Lokhttp3/Response;", "exitNetworkInterceptorExchange", "closeExchange", "exitNetworkInterceptorExchange$okhttp", "getResponseWithInterceptorChain", "getResponseWithInterceptorChain$okhttp", "initExchange", "chain", "Lokhttp3/internal/http/RealInterceptorChain;", "initExchange$okhttp", "isCanceled", "isExecuted", "messageDone", "requestDone", "responseDone", "messageDone$okhttp", "(Lokhttp3/internal/connection/Exchange;ZZLjava/io/IOException;)Ljava/io/IOException;", "noMoreExchanges", "noMoreExchanges$okhttp", "redactedUrl", "", "redactedUrl$okhttp", "releaseConnectionNoEvents", "Ljava/net/Socket;", "releaseConnectionNoEvents$okhttp", "retryAfterFailure", "Lokio/AsyncTimeout;", "timeoutExit", "cause", "toLoggableString", "AsyncCall", "CallReference", "okhttp"})
public final class RealCall
implements Call {
    private final RealConnectionPool connectionPool;
    @NotNull
    private final EventListener eventListener;
    private final timeout.1 timeout;
    private final AtomicBoolean executed;
    private Object callStackTrace;
    private ExchangeFinder exchangeFinder;
    @Nullable
    private RealConnection connection;
    private boolean timeoutEarlyExit;
    @Nullable
    private Exchange interceptorScopedExchange;
    private boolean requestBodyOpen;
    private boolean responseBodyOpen;
    private boolean expectMoreExchanges;
    private volatile boolean canceled;
    private volatile Exchange exchange;
    @Nullable
    private volatile RealConnection connectionToCancel;
    @NotNull
    private final OkHttpClient client;
    @NotNull
    private final Request originalRequest;
    private final boolean forWebSocket;

    @NotNull
    public final EventListener getEventListener$okhttp() {
        return this.eventListener;
    }

    @Nullable
    public final RealConnection getConnection() {
        return this.connection;
    }

    @Nullable
    public final Exchange getInterceptorScopedExchange$okhttp() {
        return this.interceptorScopedExchange;
    }

    @Nullable
    public final RealConnection getConnectionToCancel() {
        return this.connectionToCancel;
    }

    public final void setConnectionToCancel(@Nullable RealConnection realConnection) {
        this.connectionToCancel = realConnection;
    }

    @Override
    @NotNull
    public AsyncTimeout timeout() {
        return this.timeout;
    }

    @Override
    @NotNull
    public RealCall clone() {
        return new RealCall(this.client, this.originalRequest, this.forWebSocket);
    }

    @Override
    @NotNull
    public Request request() {
        return this.originalRequest;
    }

    @Override
    public void cancel() {
        if (this.canceled) {
            return;
        }
        this.canceled = true;
        Exchange exchange = this.exchange;
        if (exchange != null) {
            exchange.cancel();
        }
        RealConnection realConnection = this.connectionToCancel;
        if (realConnection != null) {
            realConnection.cancel();
        }
        this.eventListener.canceled(this);
    }

    @Override
    public boolean isCanceled() {
        return this.canceled;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @NotNull
    public Response execute() {
        boolean bl = this.executed.compareAndSet(false, true);
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "Already Executed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        this.timeout.enter();
        this.callStart();
        try {
            this.client.dispatcher().executed$okhttp(this);
            Response response = this.getResponseWithInterceptorChain$okhttp();
            return response;
        } finally {
            this.client.dispatcher().finished$okhttp(this);
        }
    }

    @Override
    public void enqueue(@NotNull Callback responseCallback) {
        Intrinsics.checkNotNullParameter(responseCallback, "responseCallback");
        boolean bl = this.executed.compareAndSet(false, true);
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "Already Executed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        this.callStart();
        this.client.dispatcher().enqueue$okhttp(new AsyncCall(responseCallback));
    }

    @Override
    public boolean isExecuted() {
        return this.executed.get();
    }

    private final void callStart() {
        this.callStackTrace = Platform.Companion.get().getStackTraceForCloseable("response.body().close()");
        this.eventListener.callStart(this);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @NotNull
    public final Response getResponseWithInterceptorChain$okhttp() throws IOException {
        Response response;
        boolean bl = false;
        List interceptors = new ArrayList();
        Collection collection = interceptors;
        Object object = this.client.interceptors();
        boolean bl2 = false;
        CollectionsKt.addAll(collection, object);
        collection = interceptors;
        object = new RetryAndFollowUpInterceptor(this.client);
        bl2 = false;
        collection.add(object);
        collection = interceptors;
        object = new BridgeInterceptor(this.client.cookieJar());
        bl2 = false;
        collection.add(object);
        collection = interceptors;
        object = new CacheInterceptor(this.client.cache());
        bl2 = false;
        collection.add(object);
        collection = interceptors;
        object = ConnectInterceptor.INSTANCE;
        bl2 = false;
        collection.add(object);
        if (!this.forWebSocket) {
            collection = interceptors;
            object = this.client.networkInterceptors();
            bl2 = false;
            CollectionsKt.addAll(collection, object);
        }
        collection = interceptors;
        object = new CallServerInterceptor(this.forWebSocket);
        bl2 = false;
        collection.add(object);
        RealInterceptorChain chain = new RealInterceptorChain(this, interceptors, 0, null, this.originalRequest, this.client.connectTimeoutMillis(), this.client.readTimeoutMillis(), this.client.writeTimeoutMillis());
        boolean calledNoMoreExchanges = false;
        try {
            Response response2 = chain.proceed(this.originalRequest);
            if (this.isCanceled()) {
                Util.closeQuietly(response2);
                throw (Throwable)new IOException("Canceled");
            }
            response = response2;
        } catch (IOException e) {
            try {
                calledNoMoreExchanges = true;
                IOException iOException = this.noMoreExchanges$okhttp(e);
                if (iOException == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
                }
                throw (Throwable)iOException;
            } catch (Throwable throwable) {
                if (!calledNoMoreExchanges) {
                    this.noMoreExchanges$okhttp(null);
                }
                throw throwable;
            }
        }
        this.noMoreExchanges$okhttp(null);
        return response;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void enterNetworkInterceptorExchange(@NotNull Request request, boolean newExchangeFinder) {
        Intrinsics.checkNotNullParameter(request, "request");
        boolean bl = this.interceptorScopedExchange == null;
        boolean bl2 = false;
        boolean bl3 = false;
        bl3 = false;
        boolean bl4 = false;
        if (!bl) {
            boolean bl5 = false;
            String string = "Check failed.";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealCall realCall = this;
        bl2 = false;
        bl3 = false;
        synchronized (realCall) {
            boolean bl6 = false;
            boolean bl7 = !this.responseBodyOpen;
            boolean bl8 = false;
            boolean bl9 = false;
            if (!bl7) {
                boolean bl10 = false;
                String string = "cannot make a new request because the previous response is still open: please call response.close()";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            bl7 = !this.requestBodyOpen;
            bl8 = false;
            bl9 = false;
            bl9 = false;
            boolean bl11 = false;
            if (!bl7) {
                boolean bl12 = false;
                String string = "Check failed.";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            Unit unit = Unit.INSTANCE;
        }
        if (newExchangeFinder) {
            this.exchangeFinder = new ExchangeFinder(this.connectionPool, this.createAddress(request.url()), this, this.eventListener);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @NotNull
    public final Exchange initExchange$okhttp(@NotNull RealInterceptorChain chain) {
        Exchange result;
        boolean bl;
        boolean bl2;
        Intrinsics.checkNotNullParameter(chain, "chain");
        RealCall realCall = this;
        boolean bl3 = false;
        boolean bl4 = false;
        synchronized (realCall) {
            boolean bl5 = false;
            bl2 = this.expectMoreExchanges;
            bl = false;
            boolean bl6 = false;
            if (!bl2) {
                boolean bl7 = false;
                String string = "released";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            bl2 = !this.responseBodyOpen;
            bl = false;
            bl6 = false;
            bl6 = false;
            boolean bl8 = false;
            if (!bl2) {
                boolean bl9 = false;
                String string = "Check failed.";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            bl2 = !this.requestBodyOpen;
            bl = false;
            bl6 = false;
            bl6 = false;
            bl8 = false;
            if (!bl2) {
                boolean bl10 = false;
                String string = "Check failed.";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            Unit unit = Unit.INSTANCE;
        }
        ExchangeFinder exchangeFinder = this.exchangeFinder;
        Intrinsics.checkNotNull(exchangeFinder);
        ExchangeFinder exchangeFinder2 = exchangeFinder;
        ExchangeCodec codec = exchangeFinder2.find(this.client, chain);
        this.interceptorScopedExchange = result = new Exchange(this, this.eventListener, exchangeFinder2, codec);
        this.exchange = result;
        RealCall realCall2 = this;
        bl2 = false;
        bl = false;
        synchronized (realCall2) {
            boolean bl11 = false;
            this.requestBodyOpen = true;
            this.responseBodyOpen = true;
            Unit unit = Unit.INSTANCE;
        }
        if (this.canceled) {
            throw (Throwable)new IOException("Canceled");
        }
        return result;
    }

    public final void acquireConnectionNoEvents(@NotNull RealConnection connection) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        RealConnection $this$assertThreadHoldsLock$iv = connection;
        boolean $i$f$assertThreadHoldsLock = false;
        if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) {
            StringBuilder stringBuilder = new StringBuilder().append("Thread ");
            Thread thread2 = Thread.currentThread();
            Intrinsics.checkNotNullExpressionValue(thread2, "Thread.currentThread()");
            throw (Throwable)((Object)new AssertionError((Object)stringBuilder.append(thread2.getName()).append(" MUST hold lock on ").append($this$assertThreadHoldsLock$iv).toString()));
        }
        boolean bl = this.connection == null;
        boolean bl2 = false;
        boolean bl3 = false;
        bl3 = false;
        boolean bl4 = false;
        if (!bl) {
            boolean bl5 = false;
            String string = "Check failed.";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        this.connection = connection;
        connection.getCalls().add(new CallReference(this, this.callStackTrace));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final <E extends IOException> E messageDone$okhttp(@NotNull Exchange exchange, boolean requestDone, boolean responseDone, E e) {
        Intrinsics.checkNotNullParameter(exchange, "exchange");
        if (Intrinsics.areEqual(exchange, this.exchange) ^ true) {
            return e;
        }
        boolean bothStreamsDone = false;
        boolean callDone = false;
        RealCall realCall = this;
        boolean bl = false;
        boolean bl2 = false;
        synchronized (realCall) {
            boolean bl3 = false;
            if (requestDone && this.requestBodyOpen || responseDone && this.responseBodyOpen) {
                if (requestDone) {
                    this.requestBodyOpen = false;
                }
                if (responseDone) {
                    this.responseBodyOpen = false;
                }
                bothStreamsDone = !this.requestBodyOpen && !this.responseBodyOpen;
                callDone = !this.requestBodyOpen && !this.responseBodyOpen && !this.expectMoreExchanges;
            }
            Unit unit = Unit.INSTANCE;
        }
        if (bothStreamsDone) {
            this.exchange = null;
            RealConnection realConnection = this.connection;
            if (realConnection != null) {
                realConnection.incrementSuccessCount$okhttp();
            }
        }
        if (callDone) {
            return this.callDone(e);
        }
        return e;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Nullable
    public final IOException noMoreExchanges$okhttp(@Nullable IOException e) {
        boolean callDone = false;
        RealCall realCall = this;
        boolean bl = false;
        boolean bl2 = false;
        synchronized (realCall) {
            boolean bl3 = false;
            if (this.expectMoreExchanges) {
                this.expectMoreExchanges = false;
                callDone = !this.requestBodyOpen && !this.responseBodyOpen;
            }
            Unit unit = Unit.INSTANCE;
        }
        if (callDone) {
            return this.callDone(e);
        }
        return e;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final <E extends IOException> E callDone(E e) {
        RealCall $this$assertThreadDoesntHoldLock$iv = this;
        boolean $i$f$assertThreadDoesntHoldLock = false;
        if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv)) {
            StringBuilder stringBuilder = new StringBuilder().append("Thread ");
            Thread thread2 = Thread.currentThread();
            Intrinsics.checkNotNullExpressionValue(thread2, "Thread.currentThread()");
            throw (Throwable)((Object)new AssertionError((Object)stringBuilder.append(thread2.getName()).append(" MUST NOT hold lock on ").append($this$assertThreadDoesntHoldLock$iv).toString()));
        }
        RealConnection connection = this.connection;
        if (connection != null) {
            Socket socket;
            RealConnection $this$assertThreadDoesntHoldLock$iv2 = connection;
            boolean $i$f$assertThreadDoesntHoldLock2 = false;
            if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv2)) {
                StringBuilder stringBuilder = new StringBuilder().append("Thread ");
                Thread thread3 = Thread.currentThread();
                Intrinsics.checkNotNullExpressionValue(thread3, "Thread.currentThread()");
                throw (Throwable)((Object)new AssertionError((Object)stringBuilder.append(thread3.getName()).append(" MUST NOT hold lock on ").append($this$assertThreadDoesntHoldLock$iv2).toString()));
            }
            boolean bl = false;
            boolean bl2 = false;
            synchronized (connection) {
                boolean bl3 = false;
                socket = this.releaseConnectionNoEvents$okhttp();
            }
            Socket socket2 = socket;
            if (this.connection == null) {
                Socket socket3 = socket2;
                if (socket3 != null) {
                    Util.closeQuietly(socket3);
                }
                this.eventListener.connectionReleased(this, connection);
            } else {
                bl = socket2 == null;
                boolean bl4 = false;
                boolean bl5 = false;
                bl5 = false;
                boolean bl6 = false;
                if (!bl) {
                    boolean bl7 = false;
                    String string = "Check failed.";
                    throw (Throwable)new IllegalStateException(string.toString());
                }
            }
        }
        E result = this.timeoutExit(e);
        if (e != null) {
            Call call = this;
            E e2 = result;
            Intrinsics.checkNotNull(e2);
            this.eventListener.callFailed(call, e2);
        } else {
            this.eventListener.callEnd(this);
        }
        return result;
    }

    @Nullable
    public final Socket releaseConnectionNoEvents$okhttp() {
        int n;
        List<Reference<RealCall>> calls;
        RealConnection connection;
        block6: {
            RealConnection realConnection = this.connection;
            Intrinsics.checkNotNull(realConnection);
            RealConnection $this$assertThreadHoldsLock$iv = connection = realConnection;
            boolean $i$f$assertThreadHoldsLock = false;
            if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) {
                StringBuilder stringBuilder = new StringBuilder().append("Thread ");
                Thread thread2 = Thread.currentThread();
                Intrinsics.checkNotNullExpressionValue(thread2, "Thread.currentThread()");
                throw (Throwable)((Object)new AssertionError((Object)stringBuilder.append(thread2.getName()).append(" MUST hold lock on ").append($this$assertThreadHoldsLock$iv).toString()));
            }
            List<Reference<RealCall>> $this$indexOfFirst$iv = calls = connection.getCalls();
            boolean $i$f$indexOfFirst = false;
            int index$iv = 0;
            Iterator<Reference<RealCall>> iterator2 = $this$indexOfFirst$iv.iterator();
            while (iterator2.hasNext()) {
                Reference<RealCall> item$iv;
                Reference<RealCall> it = item$iv = iterator2.next();
                boolean bl = false;
                if (Intrinsics.areEqual(it.get(), this)) {
                    n = index$iv;
                    break block6;
                }
                ++index$iv;
            }
            n = -1;
        }
        int index = n;
        boolean bl = index != -1;
        boolean bl2 = false;
        boolean bl3 = false;
        bl3 = false;
        boolean bl4 = false;
        if (!bl) {
            boolean bl5 = false;
            String string = "Check failed.";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        calls.remove(index);
        this.connection = null;
        if (calls.isEmpty()) {
            connection.setIdleAtNs$okhttp(System.nanoTime());
            if (this.connectionPool.connectionBecameIdle(connection)) {
                return connection.socket();
            }
        }
        return null;
    }

    private final <E extends IOException> E timeoutExit(E cause) {
        if (this.timeoutEarlyExit) {
            return cause;
        }
        if (!this.timeout.exit()) {
            return cause;
        }
        InterruptedIOException e = new InterruptedIOException("timeout");
        if (cause != null) {
            e.initCause(cause);
        }
        return (E)e;
    }

    public final void timeoutEarlyExit() {
        boolean bl = !this.timeoutEarlyExit;
        boolean bl2 = false;
        boolean bl3 = false;
        bl3 = false;
        boolean bl4 = false;
        if (!bl) {
            boolean bl5 = false;
            String string = "Check failed.";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        this.timeoutEarlyExit = true;
        this.timeout.exit();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void exitNetworkInterceptorExchange$okhttp(boolean closeExchange) {
        RealCall realCall = this;
        boolean bl = false;
        boolean bl2 = false;
        synchronized (realCall) {
            boolean bl3 = false;
            boolean bl4 = this.expectMoreExchanges;
            boolean bl5 = false;
            boolean bl6 = false;
            if (!bl4) {
                boolean bl7 = false;
                String string = "released";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            Unit unit = Unit.INSTANCE;
        }
        if (closeExchange) {
            Exchange exchange = this.exchange;
            if (exchange != null) {
                exchange.detachWithViolence();
            }
        }
        this.interceptorScopedExchange = null;
    }

    private final Address createAddress(HttpUrl url) {
        SSLSocketFactory sslSocketFactory = null;
        HostnameVerifier hostnameVerifier = null;
        CertificatePinner certificatePinner = null;
        if (url.isHttps()) {
            sslSocketFactory = this.client.sslSocketFactory();
            hostnameVerifier = this.client.hostnameVerifier();
            certificatePinner = this.client.certificatePinner();
        }
        return new Address(url.host(), url.port(), this.client.dns(), this.client.socketFactory(), sslSocketFactory, hostnameVerifier, certificatePinner, this.client.proxyAuthenticator(), this.client.proxy(), this.client.protocols(), this.client.connectionSpecs(), this.client.proxySelector());
    }

    public final boolean retryAfterFailure() {
        ExchangeFinder exchangeFinder = this.exchangeFinder;
        Intrinsics.checkNotNull(exchangeFinder);
        return exchangeFinder.retryAfterFailure();
    }

    private final String toLoggableString() {
        return (this.isCanceled() ? "canceled " : "") + (this.forWebSocket ? "web socket" : "call") + " to " + this.redactedUrl$okhttp();
    }

    @NotNull
    public final String redactedUrl$okhttp() {
        return this.originalRequest.url().redact();
    }

    @NotNull
    public final OkHttpClient getClient() {
        return this.client;
    }

    @NotNull
    public final Request getOriginalRequest() {
        return this.originalRequest;
    }

    public final boolean getForWebSocket() {
        return this.forWebSocket;
    }

    /*
     * WARNING - void declaration
     */
    public RealCall(@NotNull OkHttpClient client, @NotNull Request originalRequest, boolean forWebSocket) {
        void $this$apply;
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(originalRequest, "originalRequest");
        this.client = client;
        this.originalRequest = originalRequest;
        this.forWebSocket = forWebSocket;
        this.connectionPool = this.client.connectionPool().getDelegate$okhttp();
        this.eventListener = this.client.eventListenerFactory().create(this);
        AsyncTimeout asyncTimeout = new AsyncTimeout(this){
            final /* synthetic */ RealCall this$0;

            protected void timedOut() {
                this.this$0.cancel();
            }
            {
                this.this$0 = this$0;
            }
        };
        boolean bl = false;
        boolean bl2 = false;
        AsyncTimeout asyncTimeout2 = asyncTimeout;
        RealCall realCall = this;
        boolean bl3 = false;
        $this$apply.timeout(this.client.callTimeoutMillis(), TimeUnit.MILLISECONDS);
        Unit unit = Unit.INSTANCE;
        realCall.timeout = asyncTimeout;
        this.executed = new AtomicBoolean();
        this.expectMoreExchanges = true;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0080\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0012\u0010\u001a\u001a\u00020\u00172\n\u0010\u001b\u001a\u00060\u0000R\u00020\u0006J\b\u0010\u001c\u001a\u00020\u0017H\u0016R\u0011\u0010\u0005\u001a\u00020\u00068F\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f8F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00138F\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2={"Lokhttp3/internal/connection/RealCall$AsyncCall;", "Ljava/lang/Runnable;", "responseCallback", "Lokhttp3/Callback;", "(Lokhttp3/internal/connection/RealCall;Lokhttp3/Callback;)V", "call", "Lokhttp3/internal/connection/RealCall;", "getCall", "()Lokhttp3/internal/connection/RealCall;", "<set-?>", "Ljava/util/concurrent/atomic/AtomicInteger;", "callsPerHost", "getCallsPerHost", "()Ljava/util/concurrent/atomic/AtomicInteger;", "host", "", "getHost", "()Ljava/lang/String;", "request", "Lokhttp3/Request;", "getRequest", "()Lokhttp3/Request;", "executeOn", "", "executorService", "Ljava/util/concurrent/ExecutorService;", "reuseCallsPerHostFrom", "other", "run", "okhttp"})
    public final class AsyncCall
    implements Runnable {
        @NotNull
        private volatile AtomicInteger callsPerHost;
        private final Callback responseCallback;

        @NotNull
        public final AtomicInteger getCallsPerHost() {
            return this.callsPerHost;
        }

        public final void reuseCallsPerHostFrom(@NotNull AsyncCall other) {
            Intrinsics.checkNotNullParameter(other, "other");
            this.callsPerHost = other.callsPerHost;
        }

        @NotNull
        public final String getHost() {
            return RealCall.this.getOriginalRequest().url().host();
        }

        @NotNull
        public final Request getRequest() {
            return RealCall.this.getOriginalRequest();
        }

        @NotNull
        public final RealCall getCall() {
            return RealCall.this;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public final void executeOn(@NotNull ExecutorService executorService) {
            Intrinsics.checkNotNullParameter(executorService, "executorService");
            Dispatcher $this$assertThreadDoesntHoldLock$iv = RealCall.this.getClient().dispatcher();
            boolean $i$f$assertThreadDoesntHoldLock = false;
            if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv)) {
                StringBuilder stringBuilder = new StringBuilder().append("Thread ");
                Thread thread2 = Thread.currentThread();
                Intrinsics.checkNotNullExpressionValue(thread2, "Thread.currentThread()");
                throw (Throwable)((Object)new AssertionError((Object)stringBuilder.append(thread2.getName()).append(" MUST NOT hold lock on ").append($this$assertThreadDoesntHoldLock$iv).toString()));
            }
            boolean success = false;
            try {
                executorService.execute(this);
                success = true;
            } catch (RejectedExecutionException e) {
                InterruptedIOException ioException = new InterruptedIOException("executor rejected");
                ioException.initCause(e);
                RealCall.this.noMoreExchanges$okhttp(ioException);
                this.responseCallback.onFailure(RealCall.this, ioException);
            } finally {
                RealCall.this.getClient().dispatcher().finished$okhttp(this);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void run() {
            Thread currentThread$iv;
            String name$iv = "OkHttp " + RealCall.this.redactedUrl$okhttp();
            boolean $i$f$threadName = false;
            Thread thread2 = currentThread$iv = Thread.currentThread();
            Intrinsics.checkNotNullExpressionValue(thread2, "currentThread");
            String oldName$iv = thread2.getName();
            currentThread$iv.setName(name$iv);
            try {
                boolean bl = false;
                boolean signalledCallback = false;
                RealCall.this.timeout.enter();
                try {
                    Response response = RealCall.this.getResponseWithInterceptorChain$okhttp();
                    signalledCallback = true;
                    this.responseCallback.onResponse(RealCall.this, response);
                } catch (IOException e) {
                    if (signalledCallback) {
                        Platform.Companion.get().log("Callback failure for " + RealCall.this.toLoggableString(), 4, e);
                    } else {
                        this.responseCallback.onFailure(RealCall.this, e);
                    }
                } catch (Throwable t) {
                    RealCall.this.cancel();
                    if (!signalledCallback) {
                        IOException canceledException = new IOException("canceled due to " + t);
                        ExceptionsKt.addSuppressed(canceledException, t);
                        this.responseCallback.onFailure(RealCall.this, canceledException);
                    }
                    throw t;
                } finally {
                    RealCall.this.getClient().dispatcher().finished$okhttp(this);
                }
            } finally {
                currentThread$iv.setName(oldName$iv);
            }
        }

        public AsyncCall(Callback responseCallback) {
            Intrinsics.checkNotNullParameter(responseCallback, "responseCallback");
            this.responseCallback = responseCallback;
            this.callsPerHost = new AtomicInteger(0);
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2={"Lokhttp3/internal/connection/RealCall$CallReference;", "Ljava/lang/ref/WeakReference;", "Lokhttp3/internal/connection/RealCall;", "referent", "callStackTrace", "", "(Lokhttp3/internal/connection/RealCall;Ljava/lang/Object;)V", "getCallStackTrace", "()Ljava/lang/Object;", "okhttp"})
    public static final class CallReference
    extends WeakReference<RealCall> {
        @Nullable
        private final Object callStackTrace;

        @Nullable
        public final Object getCallStackTrace() {
            return this.callStackTrace;
        }

        public CallReference(@NotNull RealCall referent, @Nullable Object callStackTrace) {
            Intrinsics.checkNotNullParameter(referent, "referent");
            super(referent);
            this.callStackTrace = callStackTrace;
        }
    }
}

