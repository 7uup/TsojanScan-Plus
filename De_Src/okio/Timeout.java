/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\u0000H\u0016J\b\u0010\t\u001a\u00020\u0000H\u0016J\u0016\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\"\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00002\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0012H\u0086\b\u00f8\u0001\u0000J\b\u0010\u0013\u001a\u00020\u000fH\u0016J\u0018\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\u000e\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\u0018"}, d2={"Lokio/Timeout;", "", "()V", "deadlineNanoTime", "", "hasDeadline", "", "timeoutNanos", "clearDeadline", "clearTimeout", "deadline", "duration", "unit", "Ljava/util/concurrent/TimeUnit;", "intersectWith", "", "other", "block", "Lkotlin/Function0;", "throwIfReached", "timeout", "waitUntilNotified", "monitor", "Companion", "okio"})
public class Timeout {
    private boolean hasDeadline;
    private long deadlineNanoTime;
    private long timeoutNanos;
    @JvmField
    @NotNull
    public static final Timeout NONE;
    public static final Companion Companion;

    @NotNull
    public Timeout timeout(long timeout2, @NotNull TimeUnit unit) {
        Intrinsics.checkNotNullParameter((Object)unit, "unit");
        boolean bl = timeout2 >= 0L;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "timeout < 0: " + timeout2;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        this.timeoutNanos = unit.toNanos(timeout2);
        return this;
    }

    public long timeoutNanos() {
        return this.timeoutNanos;
    }

    public boolean hasDeadline() {
        return this.hasDeadline;
    }

    public long deadlineNanoTime() {
        boolean bl = this.hasDeadline;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "No deadline";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        return this.deadlineNanoTime;
    }

    @NotNull
    public Timeout deadlineNanoTime(long deadlineNanoTime) {
        this.hasDeadline = true;
        this.deadlineNanoTime = deadlineNanoTime;
        return this;
    }

    @NotNull
    public final Timeout deadline(long duration, @NotNull TimeUnit unit) {
        Intrinsics.checkNotNullParameter((Object)unit, "unit");
        boolean bl = duration > 0L;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "duration <= 0: " + duration;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        return this.deadlineNanoTime(System.nanoTime() + unit.toNanos(duration));
    }

    @NotNull
    public Timeout clearTimeout() {
        this.timeoutNanos = 0L;
        return this;
    }

    @NotNull
    public Timeout clearDeadline() {
        this.hasDeadline = false;
        return this;
    }

    public void throwIfReached() throws IOException {
        if (Thread.interrupted()) {
            Thread.currentThread().interrupt();
            throw (Throwable)new InterruptedIOException("interrupted");
        }
        if (this.hasDeadline && this.deadlineNanoTime - System.nanoTime() <= 0L) {
            throw (Throwable)new InterruptedIOException("deadline reached");
        }
    }

    public final void waitUntilNotified(@NotNull Object monitor) throws InterruptedIOException {
        Intrinsics.checkNotNullParameter(monitor, "monitor");
        try {
            long l;
            boolean hasDeadline = this.hasDeadline();
            long timeoutNanos = this.timeoutNanos();
            if (!hasDeadline && timeoutNanos == 0L) {
                monitor.wait();
                return;
            }
            long start = System.nanoTime();
            if (hasDeadline && timeoutNanos != 0L) {
                long deadlineNanos = this.deadlineNanoTime() - start;
                boolean bl = false;
                l = Math.min(timeoutNanos, deadlineNanos);
            } else {
                l = hasDeadline ? this.deadlineNanoTime() - start : timeoutNanos;
            }
            long waitNanos = l;
            long elapsedNanos = 0L;
            if (waitNanos > 0L) {
                long waitMillis = waitNanos / 1000000L;
                monitor.wait(waitMillis, (int)(waitNanos - waitMillis * 1000000L));
                elapsedNanos = System.nanoTime() - start;
            }
            if (elapsedNanos >= waitNanos) {
                throw (Throwable)new InterruptedIOException("timeout");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw (Throwable)new InterruptedIOException("interrupted");
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void intersectWith(@NotNull Timeout other, @NotNull Function0<Unit> block) {
        int $i$f$intersectWith = 0;
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(block, "block");
        long originalTimeout = this.timeoutNanos();
        this.timeout(Companion.minTimeout(other.timeoutNanos(), this.timeoutNanos()), TimeUnit.NANOSECONDS);
        if (this.hasDeadline()) {
            long originalDeadline = this.deadlineNanoTime();
            if (other.hasDeadline()) {
                this.deadlineNanoTime(Math.min(this.deadlineNanoTime(), other.deadlineNanoTime()));
            }
            try {
                block.invoke();
            } finally {
                InlineMarker.finallyStart(1);
                this.timeout(originalTimeout, TimeUnit.NANOSECONDS);
                if (other.hasDeadline()) {
                    this.deadlineNanoTime(originalDeadline);
                }
                InlineMarker.finallyEnd(1);
            }
        }
        if (other.hasDeadline()) {
            this.deadlineNanoTime(other.deadlineNanoTime());
        }
        try {
            block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            this.timeout(originalTimeout, TimeUnit.NANOSECONDS);
            if (other.hasDeadline()) {
                this.clearDeadline();
            }
            InlineMarker.finallyEnd(1);
        }
    }

    static {
        Companion = new Companion(null);
        NONE = new Timeout(){

            @NotNull
            public Timeout timeout(long timeout2, @NotNull TimeUnit unit) {
                Intrinsics.checkNotNullParameter((Object)((Object)unit), "unit");
                return this;
            }

            @NotNull
            public Timeout deadlineNanoTime(long deadlineNanoTime) {
                return this;
            }

            public void throwIfReached() {
            }
        };
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2={"Lokio/Timeout$Companion;", "", "()V", "NONE", "Lokio/Timeout;", "minTimeout", "", "aNanos", "bNanos", "okio"})
    public static final class Companion {
        public final long minTimeout(long aNanos, long bNanos) {
            return aNanos == 0L ? bNanos : (bNanos == 0L ? aNanos : (aNanos < bNanos ? aNanos : bNanos));
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

