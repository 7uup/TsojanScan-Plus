/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.-Util;
import okio.Buffer;
import okio.Segment;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001b\u001cB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0001J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u0004J\u0012\u0010\u000e\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0014J\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0007H\u0002J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0015\u001a\u00020\fH\u0014J%\u0010\u0016\u001a\u0002H\u0017\"\u0004\b\u0000\u0010\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0019H\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0000X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\u001d"}, d2={"Lokio/AsyncTimeout;", "Lokio/Timeout;", "()V", "inQueue", "", "next", "timeoutAt", "", "access$newTimeoutException", "Ljava/io/IOException;", "cause", "enter", "", "exit", "newTimeoutException", "remainingNanos", "now", "sink", "Lokio/Sink;", "source", "Lokio/Source;", "timedOut", "withTimeout", "T", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Companion", "Watchdog", "okio"})
public class AsyncTimeout
extends Timeout {
    private boolean inQueue;
    private AsyncTimeout next;
    private long timeoutAt;
    private static final int TIMEOUT_WRITE_SIZE = 65536;
    private static final long IDLE_TIMEOUT_MILLIS;
    private static final long IDLE_TIMEOUT_NANOS;
    private static AsyncTimeout head;
    public static final Companion Companion;

    public final void enter() {
        boolean bl = !this.inQueue;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "Unbalanced enter/exit";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        long timeoutNanos = this.timeoutNanos();
        boolean hasDeadline = this.hasDeadline();
        if (timeoutNanos == 0L && !hasDeadline) {
            return;
        }
        this.inQueue = true;
        AsyncTimeout.Companion.scheduleTimeout(this, timeoutNanos, hasDeadline);
    }

    public final boolean exit() {
        if (!this.inQueue) {
            return false;
        }
        this.inQueue = false;
        return AsyncTimeout.Companion.cancelScheduledTimeout(this);
    }

    private final long remainingNanos(long now) {
        return this.timeoutAt - now;
    }

    protected void timedOut() {
    }

    @NotNull
    public final Sink sink(@NotNull Sink sink2) {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        return new Sink(this, sink2){
            final /* synthetic */ AsyncTimeout this$0;
            final /* synthetic */ Sink $sink;

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            public void write(@NotNull Buffer source2, long byteCount) {
                long toWrite;
                Intrinsics.checkNotNullParameter(source2, "source");
                -Util.checkOffsetAndCount(source2.size(), 0L, byteCount);
                for (long remaining = byteCount; remaining > 0L; remaining -= toWrite) {
                    toWrite = 0L;
                    Intrinsics.checkNotNull(source2.head);
                    while (toWrite < (long)65536) {
                        Segment s2;
                        int segmentSize = s2.limit - s2.pos;
                        if ((toWrite += (long)segmentSize) >= remaining) {
                            toWrite = remaining;
                            break;
                        }
                        Intrinsics.checkNotNull(s2.next);
                    }
                    AsyncTimeout this_$iv = this.this$0;
                    boolean $i$f$withTimeout = false;
                    boolean throwOnTimeout$iv = false;
                    this_$iv.enter();
                    try {
                        boolean bl = false;
                        this.$sink.write(source2, toWrite);
                        Unit result$iv = Unit.INSTANCE;
                        throwOnTimeout$iv = true;
                        Unit unit = result$iv;
                        boolean timedOut$iv = this_$iv.exit();
                        if (!timedOut$iv) continue;
                    } catch (IOException e$iv) {
                        try {
                            throw !this_$iv.exit() ? (Throwable)e$iv : (Throwable)this_$iv.access$newTimeoutException(e$iv);
                        } catch (Throwable throwable) {
                            boolean timedOut$iv = this_$iv.exit();
                            if (timedOut$iv && throwOnTimeout$iv) {
                                throw (Throwable)this_$iv.access$newTimeoutException(null);
                            }
                            throw throwable;
                        }
                    }
                    throw (Throwable)this_$iv.access$newTimeoutException(null);
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public void flush() {
                AsyncTimeout this_$iv = this.this$0;
                boolean $i$f$withTimeout = false;
                boolean throwOnTimeout$iv = false;
                this_$iv.enter();
                try {
                    boolean bl = false;
                    this.$sink.flush();
                    Unit result$iv = Unit.INSTANCE;
                    throwOnTimeout$iv = true;
                    Unit unit = result$iv;
                    boolean timedOut$iv = this_$iv.exit();
                    if (!timedOut$iv) return;
                } catch (IOException e$iv) {
                    try {
                        throw !this_$iv.exit() ? (Throwable)e$iv : (Throwable)this_$iv.access$newTimeoutException(e$iv);
                    } catch (Throwable throwable) {
                        boolean timedOut$iv = this_$iv.exit();
                        if (!timedOut$iv || !throwOnTimeout$iv) throw throwable;
                        throw (Throwable)this_$iv.access$newTimeoutException(null);
                    }
                }
                throw (Throwable)this_$iv.access$newTimeoutException(null);
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public void close() {
                AsyncTimeout this_$iv = this.this$0;
                boolean $i$f$withTimeout = false;
                boolean throwOnTimeout$iv = false;
                this_$iv.enter();
                try {
                    boolean bl = false;
                    this.$sink.close();
                    Unit result$iv = Unit.INSTANCE;
                    throwOnTimeout$iv = true;
                    Unit unit = result$iv;
                    boolean timedOut$iv = this_$iv.exit();
                    if (!timedOut$iv) return;
                } catch (IOException e$iv) {
                    try {
                        throw !this_$iv.exit() ? (Throwable)e$iv : (Throwable)this_$iv.access$newTimeoutException(e$iv);
                    } catch (Throwable throwable) {
                        boolean timedOut$iv = this_$iv.exit();
                        if (!timedOut$iv || !throwOnTimeout$iv) throw throwable;
                        throw (Throwable)this_$iv.access$newTimeoutException(null);
                    }
                }
                throw (Throwable)this_$iv.access$newTimeoutException(null);
            }

            @NotNull
            public AsyncTimeout timeout() {
                return this.this$0;
            }

            @NotNull
            public String toString() {
                return "AsyncTimeout.sink(" + this.$sink + ')';
            }
            {
                this.this$0 = this$0;
                this.$sink = $captured_local_variable$1;
            }
        };
    }

    @NotNull
    public final Source source(@NotNull Source source2) {
        Intrinsics.checkNotNullParameter(source2, "source");
        return new Source(this, source2){
            final /* synthetic */ AsyncTimeout this$0;
            final /* synthetic */ Source $source;

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public long read(@NotNull Buffer sink2, long byteCount) {
                Intrinsics.checkNotNullParameter(sink2, "sink");
                AsyncTimeout this_$iv = this.this$0;
                boolean $i$f$withTimeout = false;
                boolean throwOnTimeout$iv = false;
                this_$iv.enter();
                try {
                    boolean bl = false;
                    long result$iv = this.$source.read(sink2, byteCount);
                    throwOnTimeout$iv = true;
                    long l = result$iv;
                    boolean timedOut$iv = this_$iv.exit();
                    if (!timedOut$iv) return l;
                } catch (IOException e$iv) {
                    try {
                        throw !this_$iv.exit() ? (Throwable)e$iv : (Throwable)this_$iv.access$newTimeoutException(e$iv);
                    } catch (Throwable throwable) {
                        boolean timedOut$iv = this_$iv.exit();
                        if (!timedOut$iv || !throwOnTimeout$iv) throw throwable;
                        throw (Throwable)this_$iv.access$newTimeoutException(null);
                    }
                }
                throw (Throwable)this_$iv.access$newTimeoutException(null);
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public void close() {
                AsyncTimeout this_$iv = this.this$0;
                boolean $i$f$withTimeout = false;
                boolean throwOnTimeout$iv = false;
                this_$iv.enter();
                try {
                    boolean bl = false;
                    this.$source.close();
                    Unit result$iv = Unit.INSTANCE;
                    throwOnTimeout$iv = true;
                    Unit unit = result$iv;
                    boolean timedOut$iv = this_$iv.exit();
                    if (!timedOut$iv) return;
                } catch (IOException e$iv) {
                    try {
                        throw !this_$iv.exit() ? (Throwable)e$iv : (Throwable)this_$iv.access$newTimeoutException(e$iv);
                    } catch (Throwable throwable) {
                        boolean timedOut$iv = this_$iv.exit();
                        if (!timedOut$iv || !throwOnTimeout$iv) throw throwable;
                        throw (Throwable)this_$iv.access$newTimeoutException(null);
                    }
                }
                throw (Throwable)this_$iv.access$newTimeoutException(null);
            }

            @NotNull
            public AsyncTimeout timeout() {
                return this.this$0;
            }

            @NotNull
            public String toString() {
                return "AsyncTimeout.source(" + this.$source + ')';
            }
            {
                this.this$0 = this$0;
                this.$source = $captured_local_variable$1;
            }
        };
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final <T> T withTimeout(@NotNull Function0<? extends T> block) {
        T t;
        int $i$f$withTimeout = 0;
        Intrinsics.checkNotNullParameter(block, "block");
        boolean throwOnTimeout = false;
        this.enter();
        try {
            T result = block.invoke();
            throwOnTimeout = true;
            t = result;
        } catch (IOException e) {
            try {
                throw !this.exit() ? (Throwable)e : (Throwable)this.access$newTimeoutException(e);
            } catch (Throwable throwable) {
                InlineMarker.finallyStart(1);
                boolean timedOut = this.exit();
                if (timedOut && throwOnTimeout) {
                    throw (Throwable)this.access$newTimeoutException(null);
                }
                InlineMarker.finallyEnd(1);
                throw throwable;
            }
        }
        InlineMarker.finallyStart(1);
        boolean timedOut = this.exit();
        if (timedOut) {
            throw (Throwable)this.access$newTimeoutException(null);
        }
        InlineMarker.finallyEnd(1);
        return t;
    }

    @PublishedApi
    @NotNull
    public final IOException access$newTimeoutException(@Nullable IOException cause) {
        return this.newTimeoutException(cause);
    }

    @NotNull
    protected IOException newTimeoutException(@Nullable IOException cause) {
        InterruptedIOException e = new InterruptedIOException("timeout");
        if (cause != null) {
            e.initCause(cause);
        }
        return e;
    }

    static {
        Companion = new Companion(null);
        IDLE_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(60L);
        IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(IDLE_TIMEOUT_MILLIS);
    }

    public static final /* synthetic */ long access$getTimeoutAt$p(AsyncTimeout $this) {
        return $this.timeoutAt;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0000\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016\u00a8\u0006\u0005"}, d2={"Lokio/AsyncTimeout$Watchdog;", "Ljava/lang/Thread;", "()V", "run", "", "okio"})
    private static final class Watchdog
    extends Thread {
        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void run() {
            while (true) {
                try {
                    AsyncTimeout timedOut = null;
                    Class<AsyncTimeout> lock$iv = AsyncTimeout.class;
                    boolean $i$f$synchronized = false;
                    boolean bl = false;
                    boolean bl2 = false;
                    synchronized (lock$iv) {
                        boolean bl3 = false;
                        timedOut = Companion.awaitTimeout$okio();
                        if (timedOut == head) {
                            head = (AsyncTimeout)null;
                            return;
                        }
                        Unit unit = Unit.INSTANCE;
                    }
                    AsyncTimeout asyncTimeout = timedOut;
                    if (asyncTimeout != null) {
                        asyncTimeout.timedOut();
                    }
                } catch (InterruptedException interruptedException) {
                }
            }
        }

        public Watchdog() {
            super("Okio Watchdog");
            this.setDaemon(true);
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000f\u0010\n\u001a\u0004\u0018\u00010\tH\u0000\u00a2\u0006\u0002\b\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0002J \u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2={"Lokio/AsyncTimeout$Companion;", "", "()V", "IDLE_TIMEOUT_MILLIS", "", "IDLE_TIMEOUT_NANOS", "TIMEOUT_WRITE_SIZE", "", "head", "Lokio/AsyncTimeout;", "awaitTimeout", "awaitTimeout$okio", "cancelScheduledTimeout", "", "node", "scheduleTimeout", "", "timeoutNanos", "hasDeadline", "okio"})
    public static final class Companion {
        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private final void scheduleTimeout(AsyncTimeout node, long timeoutNanos, boolean hasDeadline) {
            Class<AsyncTimeout> lock$iv = AsyncTimeout.class;
            boolean $i$f$synchronized = false;
            boolean bl = false;
            boolean bl2 = false;
            synchronized (lock$iv) {
                boolean bl3 = false;
                if (head == null) {
                    head = new AsyncTimeout();
                    new Watchdog().start();
                }
                long now = System.nanoTime();
                if (timeoutNanos != 0L && hasDeadline) {
                    long l = timeoutNanos;
                    long l2 = node.deadlineNanoTime() - now;
                    boolean bl4 = false;
                    node.timeoutAt = now + Math.min(l, l2);
                } else if (timeoutNanos != 0L) {
                    node.timeoutAt = now + timeoutNanos;
                } else if (hasDeadline) {
                    node.timeoutAt = node.deadlineNanoTime();
                } else {
                    throw (Throwable)((Object)new AssertionError());
                }
                long remainingNanos = node.remainingNanos(now);
                AsyncTimeout asyncTimeout = head;
                Intrinsics.checkNotNull(asyncTimeout);
                AsyncTimeout prev = asyncTimeout;
                while (true) {
                    block15: {
                        block14: {
                            if (prev.next == null) break block14;
                            AsyncTimeout asyncTimeout2 = prev.next;
                            Intrinsics.checkNotNull(asyncTimeout2);
                            if (remainingNanos >= asyncTimeout2.remainingNanos(now)) break block15;
                        }
                        node.next = prev.next;
                        prev.next = node;
                        if (prev != head) break;
                        ((Object)AsyncTimeout.class).notify();
                        break;
                    }
                    Intrinsics.checkNotNull(prev.next);
                }
                Unit unit = Unit.INSTANCE;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private final boolean cancelScheduledTimeout(AsyncTimeout node) {
            Class<AsyncTimeout> lock$iv = AsyncTimeout.class;
            boolean $i$f$synchronized = false;
            boolean bl = false;
            boolean bl2 = false;
            synchronized (lock$iv) {
                boolean bl3 = false;
                AsyncTimeout prev = head;
                while (prev != null) {
                    if (prev.next == node) {
                        prev.next = node.next;
                        node.next = (AsyncTimeout)null;
                        return false;
                    }
                    prev = prev.next;
                }
                return true;
            }
        }

        @Nullable
        public final AsyncTimeout awaitTimeout$okio() throws InterruptedException {
            AsyncTimeout asyncTimeout = head;
            Intrinsics.checkNotNull(asyncTimeout);
            AsyncTimeout node = asyncTimeout.next;
            if (node == null) {
                long startNanos = System.nanoTime();
                ((Object)AsyncTimeout.class).wait(IDLE_TIMEOUT_MILLIS);
                AsyncTimeout asyncTimeout2 = head;
                Intrinsics.checkNotNull(asyncTimeout2);
                return asyncTimeout2.next == null && System.nanoTime() - startNanos >= IDLE_TIMEOUT_NANOS ? head : null;
            }
            long waitNanos = node.remainingNanos(System.nanoTime());
            if (waitNanos > 0L) {
                long waitMillis = waitNanos / 1000000L;
                ((Object)AsyncTimeout.class).wait(waitMillis, (int)(waitNanos -= waitMillis * 1000000L));
                return null;
            }
            AsyncTimeout asyncTimeout3 = head;
            Intrinsics.checkNotNull(asyncTimeout3);
            asyncTimeout3.next = node.next;
            node.next = (AsyncTimeout)null;
            return node;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

