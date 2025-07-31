/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.connection;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.ConnectionPool;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RealConnectionPool;
import okhttp3.internal.platform.Platform;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0001\u000e\u0018\u0000 (2\u00020\u0001:\u0001(B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ.\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u000e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001b2\u0006\u0010\u001d\u001a\u00020\u0015J\u000e\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u0007J\u000e\u0010 \u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u0012J\u0006\u0010\"\u001a\u00020\u0005J\u0006\u0010#\u001a\u00020$J\u0006\u0010%\u001a\u00020\u0005J\u0018\u0010&\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u0007H\u0002J\u000e\u0010'\u001a\u00020$2\u0006\u0010!\u001a\u00020\u0012R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2={"Lokhttp3/internal/connection/RealConnectionPool;", "", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "maxIdleConnections", "", "keepAliveDuration", "", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "(Lokhttp3/internal/concurrent/TaskRunner;IJLjava/util/concurrent/TimeUnit;)V", "cleanupQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "cleanupTask", "okhttp3/internal/connection/RealConnectionPool$cleanupTask$1", "Lokhttp3/internal/connection/RealConnectionPool$cleanupTask$1;", "connections", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Lokhttp3/internal/connection/RealConnection;", "keepAliveDurationNs", "callAcquirePooledConnection", "", "address", "Lokhttp3/Address;", "call", "Lokhttp3/internal/connection/RealCall;", "routes", "", "Lokhttp3/Route;", "requireMultiplexed", "cleanup", "now", "connectionBecameIdle", "connection", "connectionCount", "evictAll", "", "idleConnectionCount", "pruneAndGetAllocationCount", "put", "Companion", "okhttp"})
public final class RealConnectionPool {
    private final long keepAliveDurationNs;
    private final TaskQueue cleanupQueue;
    private final cleanupTask.1 cleanupTask;
    private final ConcurrentLinkedQueue<RealConnection> connections;
    private final int maxIdleConnections;
    public static final Companion Companion = new Companion(null);

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * WARNING - void declaration
     */
    public final int idleConnectionCount() {
        int n;
        Iterable $this$count$iv = this.connections;
        boolean $i$f$count = false;
        if ($this$count$iv instanceof Collection && ((Collection)$this$count$iv).isEmpty()) {
            n = 0;
        } else {
            void var3_3;
            int count$iv = 0;
            for (Object element$iv : $this$count$iv) {
                RealConnection realConnection;
                RealConnection it = (RealConnection)element$iv;
                boolean bl = false;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                boolean bl2 = false;
                boolean bl3 = false;
                synchronized (realConnection) {
                    boolean bl4 = false;
                    bl3 = it.getCalls().isEmpty();
                }
                if (!bl3) continue;
                int n2 = ++count$iv;
                boolean bl5 = false;
                if (n2 >= 0) continue;
                CollectionsKt.throwCountOverflow();
            }
            n = var3_3;
        }
        return n;
    }

    public final int connectionCount() {
        return this.connections.size();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final boolean callAcquirePooledConnection(@NotNull Address address, @NotNull RealCall call, @Nullable List<Route> routes, boolean requireMultiplexed) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(call, "call");
        for (RealConnection connection : this.connections) {
            RealConnection realConnection;
            Intrinsics.checkNotNullExpressionValue(connection, "connection");
            boolean bl = false;
            boolean bl2 = false;
            synchronized (realConnection) {
                block5: {
                    boolean bl3 = false;
                    if (requireMultiplexed && !connection.isMultiplexed$okhttp() || !connection.isEligible$okhttp(address, routes)) break block5;
                    call.acquireConnectionNoEvents(connection);
                    boolean bl4 = true;
                    return bl4;
                }
                Unit unit = Unit.INSTANCE;
            }
        }
        return false;
    }

    public final void put(@NotNull RealConnection connection) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        RealConnection $this$assertThreadHoldsLock$iv = connection;
        boolean $i$f$assertThreadHoldsLock = false;
        if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) {
            StringBuilder stringBuilder = new StringBuilder().append("Thread ");
            Thread thread2 = Thread.currentThread();
            Intrinsics.checkNotNullExpressionValue(thread2, "Thread.currentThread()");
            throw (Throwable)((Object)new AssertionError((Object)stringBuilder.append(thread2.getName()).append(" MUST hold lock on ").append($this$assertThreadHoldsLock$iv).toString()));
        }
        this.connections.add(connection);
        TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
    }

    public final boolean connectionBecameIdle(@NotNull RealConnection connection) {
        boolean bl;
        Intrinsics.checkNotNullParameter(connection, "connection");
        RealConnection $this$assertThreadHoldsLock$iv = connection;
        boolean $i$f$assertThreadHoldsLock = false;
        if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) {
            StringBuilder stringBuilder = new StringBuilder().append("Thread ");
            Thread thread2 = Thread.currentThread();
            Intrinsics.checkNotNullExpressionValue(thread2, "Thread.currentThread()");
            throw (Throwable)((Object)new AssertionError((Object)stringBuilder.append(thread2.getName()).append(" MUST hold lock on ").append($this$assertThreadHoldsLock$iv).toString()));
        }
        if (connection.getNoNewExchanges() || this.maxIdleConnections == 0) {
            connection.setNoNewExchanges(true);
            this.connections.remove(connection);
            if (this.connections.isEmpty()) {
                this.cleanupQueue.cancelAll();
            }
            bl = true;
        } else {
            TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
            bl = false;
        }
        return bl;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void evictAll() {
        Iterator<RealConnection> iterator2 = this.connections.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator2, "connections.iterator()");
        Iterator<RealConnection> i = iterator2;
        while (i.hasNext()) {
            Socket socketToClose;
            Socket socket;
            RealConnection realConnection;
            RealConnection connection = i.next();
            Intrinsics.checkNotNullExpressionValue(connection, "connection");
            boolean bl = false;
            boolean bl2 = false;
            synchronized (realConnection) {
                Socket socket2;
                boolean bl3 = false;
                if (connection.getCalls().isEmpty()) {
                    i.remove();
                    connection.setNoNewExchanges(true);
                    socket2 = connection.socket();
                } else {
                    socket2 = null;
                }
                socket = socket2;
            }
            Socket socket3 = socketToClose = socket;
            if (socket3 == null) continue;
            Util.closeQuietly(socket3);
        }
        if (this.connections.isEmpty()) {
            this.cleanupQueue.cancelAll();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final long cleanup(long now) {
        Object object;
        int inUseConnectionCount = 0;
        int idleConnectionCount = 0;
        RealConnection longestIdleConnection = null;
        long longestIdleDurationNs = Long.MIN_VALUE;
        for (RealConnection connection : this.connections) {
            RealConnection realConnection;
            Intrinsics.checkNotNullExpressionValue(connection, "connection");
            boolean bl = false;
            boolean bl2 = false;
            synchronized (realConnection) {
                Object object2;
                int n;
                boolean bl3 = false;
                if (this.pruneAndGetAllocationCount(connection, now) > 0) {
                    n = inUseConnectionCount;
                    inUseConnectionCount = n + 1;
                    object2 = n;
                } else {
                    n = idleConnectionCount;
                    idleConnectionCount = n + 1;
                    long idleDurationNs = now - connection.getIdleAtNs$okhttp();
                    if (idleDurationNs > longestIdleDurationNs) {
                        longestIdleDurationNs = idleDurationNs;
                        longestIdleConnection = connection;
                        object2 = Unit.INSTANCE;
                    } else {
                        object2 = Unit.INSTANCE;
                    }
                }
                object = object2;
            }
        }
        if (longestIdleDurationNs >= this.keepAliveDurationNs || idleConnectionCount > this.maxIdleConnections) {
            RealConnection connection;
            RealConnection realConnection = longestIdleConnection;
            Intrinsics.checkNotNull(realConnection);
            connection = realConnection;
            boolean bl = false;
            boolean bl4 = false;
            synchronized (connection) {
                block18: {
                    block17: {
                        boolean bl5 = false;
                        object = connection.getCalls();
                        boolean bl6 = false;
                        if (!(!object.isEmpty())) break block17;
                        long l = 0L;
                        return l;
                    }
                    if (connection.getIdleAtNs$okhttp() + longestIdleDurationNs == now) break block18;
                    long l = 0L;
                    return l;
                }
                connection.setNoNewExchanges(true);
                bl4 = this.connections.remove(longestIdleConnection);
            }
            Util.closeQuietly(connection.socket());
            if (this.connections.isEmpty()) {
                this.cleanupQueue.cancelAll();
            }
            return 0L;
        }
        if (idleConnectionCount > 0) {
            return this.keepAliveDurationNs - longestIdleDurationNs;
        }
        if (inUseConnectionCount > 0) {
            return this.keepAliveDurationNs;
        }
        return -1L;
    }

    private final int pruneAndGetAllocationCount(RealConnection connection, long now) {
        RealConnection $this$assertThreadHoldsLock$iv = connection;
        boolean $i$f$assertThreadHoldsLock = false;
        if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) {
            StringBuilder stringBuilder = new StringBuilder().append("Thread ");
            Thread thread2 = Thread.currentThread();
            Intrinsics.checkNotNullExpressionValue(thread2, "Thread.currentThread()");
            throw (Throwable)((Object)new AssertionError((Object)stringBuilder.append(thread2.getName()).append(" MUST hold lock on ").append($this$assertThreadHoldsLock$iv).toString()));
        }
        List<Reference<RealCall>> references = connection.getCalls();
        int i = 0;
        while (i < references.size()) {
            Reference<RealCall> reference = references.get(i);
            if (reference.get() != null) {
                ++i;
                continue;
            }
            Reference<RealCall> reference2 = reference;
            if (reference2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type okhttp3.internal.connection.RealCall.CallReference");
            }
            RealCall.CallReference callReference = (RealCall.CallReference)reference2;
            String message = "A connection to " + connection.route().address().url() + " was leaked. " + "Did you forget to close a response body?";
            Platform.Companion.get().logCloseableLeak(message, callReference.getCallStackTrace());
            references.remove(i);
            connection.setNoNewExchanges(true);
            if (!references.isEmpty()) continue;
            connection.setIdleAtNs$okhttp(now - this.keepAliveDurationNs);
            return 0;
        }
        return references.size();
    }

    public RealConnectionPool(@NotNull TaskRunner taskRunner, int maxIdleConnections, long keepAliveDuration, @NotNull TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        Intrinsics.checkNotNullParameter((Object)timeUnit, "timeUnit");
        this.maxIdleConnections = maxIdleConnections;
        this.keepAliveDurationNs = timeUnit.toNanos(keepAliveDuration);
        this.cleanupQueue = taskRunner.newQueue();
        this.cleanupTask = new Task(this, Util.okHttpName + " ConnectionPool"){
            final /* synthetic */ RealConnectionPool this$0;

            public long runOnce() {
                return this.this$0.cleanup(System.nanoTime());
            }
            {
                this.this$0 = this$0;
                super($super_call_param$1, false, 2, null);
            }
        };
        this.connections = new ConcurrentLinkedQueue();
        boolean bl = keepAliveDuration > 0L;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "keepAliveDuration <= 0: " + keepAliveDuration;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2={"Lokhttp3/internal/connection/RealConnectionPool$Companion;", "", "()V", "get", "Lokhttp3/internal/connection/RealConnectionPool;", "connectionPool", "Lokhttp3/ConnectionPool;", "okhttp"})
    public static final class Companion {
        @NotNull
        public final RealConnectionPool get(@NotNull ConnectionPool connectionPool) {
            Intrinsics.checkNotNullParameter(connectionPool, "connectionPool");
            return connectionPool.getDelegate$okhttp();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

