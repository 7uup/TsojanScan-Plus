/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Sink;
import okio.Source;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0002B\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u001d\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0000\u00a2\u0006\u0002\b\fJ$\u0010\u0006\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0004H\u0007J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011J\u0015\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0000\u00a2\u0006\u0002\b\u0013J\u0010\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u0004H\u0002J\f\u0010\u0016\u001a\u00020\u0004*\u00020\u0004H\u0002J\f\u0010\u0017\u001a\u00020\u0004*\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2={"Lokio/Throttler;", "", "()V", "allocatedUntil", "", "(J)V", "bytesPerSecond", "maxByteCount", "waitByteCount", "byteCountOrWaitNanos", "now", "byteCount", "byteCountOrWaitNanos$okio", "", "sink", "Lokio/Sink;", "source", "Lokio/Source;", "take", "take$okio", "waitNanos", "nanosToWait", "bytesToNanos", "nanosToBytes", "okio"})
public final class Throttler {
    private long bytesPerSecond;
    private long waitByteCount;
    private long maxByteCount;
    private long allocatedUntil;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmOverloads
    public final void bytesPerSecond(long bytesPerSecond, long waitByteCount, long maxByteCount) {
        Throttler lock$iv = this;
        boolean $i$f$synchronized = false;
        boolean bl = false;
        boolean bl2 = false;
        synchronized (lock$iv) {
            boolean bl3 = false;
            boolean bl4 = bytesPerSecond >= 0L;
            boolean bl5 = false;
            boolean bl6 = false;
            bl6 = false;
            boolean bl7 = false;
            if (!bl4) {
                boolean bl8 = false;
                String string = "Failed requirement.";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            bl4 = waitByteCount > 0L;
            bl5 = false;
            bl6 = false;
            bl6 = false;
            bl7 = false;
            if (!bl4) {
                boolean bl9 = false;
                String string = "Failed requirement.";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            bl4 = maxByteCount >= waitByteCount;
            bl5 = false;
            bl6 = false;
            bl6 = false;
            bl7 = false;
            if (!bl4) {
                boolean bl10 = false;
                String string = "Failed requirement.";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            this.bytesPerSecond = bytesPerSecond;
            this.waitByteCount = waitByteCount;
            this.maxByteCount = maxByteCount;
            Throttler throttler = this;
            if (throttler == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
            }
            ((Object)throttler).notifyAll();
            Unit unit = Unit.INSTANCE;
        }
    }

    public static /* synthetic */ void bytesPerSecond$default(Throttler throttler, long l, long l2, long l3, int n, Object object) {
        if ((n & 2) != 0) {
            l2 = throttler.waitByteCount;
        }
        if ((n & 4) != 0) {
            l3 = throttler.maxByteCount;
        }
        throttler.bytesPerSecond(l, l2, l3);
    }

    @JvmOverloads
    public final void bytesPerSecond(long bytesPerSecond, long waitByteCount) {
        Throttler.bytesPerSecond$default(this, bytesPerSecond, waitByteCount, 0L, 4, null);
    }

    @JvmOverloads
    public final void bytesPerSecond(long bytesPerSecond) {
        Throttler.bytesPerSecond$default(this, bytesPerSecond, 0L, 0L, 6, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final long take$okio(long byteCount) {
        boolean bl = byteCount > 0L;
        boolean bl2 = false;
        boolean bl3 = false;
        bl3 = false;
        boolean bl4 = false;
        if (!bl) {
            boolean bl5 = false;
            String string = "Failed requirement.";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        Throttler lock$iv = this;
        boolean $i$f$synchronized = false;
        bl3 = false;
        bl4 = false;
        synchronized (lock$iv) {
            boolean bl6 = false;
            long now;
            long byteCountOrWaitNanos;
            while ((byteCountOrWaitNanos = this.byteCountOrWaitNanos$okio(now = System.nanoTime(), byteCount)) < 0L) {
                this.waitNanos(-byteCountOrWaitNanos);
            }
            return byteCountOrWaitNanos;
        }
    }

    public final long byteCountOrWaitNanos$okio(long now, long byteCount) {
        if (this.bytesPerSecond == 0L) {
            return byteCount;
        }
        long l = this.allocatedUntil - now;
        long l2 = 0L;
        boolean bl = false;
        long idleInNanos = Math.max(l, l2);
        long immediateBytes = this.maxByteCount - this.nanosToBytes(idleInNanos);
        if (immediateBytes >= byteCount) {
            this.allocatedUntil = now + idleInNanos + this.bytesToNanos(byteCount);
            return byteCount;
        }
        if (immediateBytes >= this.waitByteCount) {
            this.allocatedUntil = now + this.bytesToNanos(this.maxByteCount);
            return immediateBytes;
        }
        long l3 = this.waitByteCount;
        boolean bl2 = false;
        long minByteCount = Math.min(l3, byteCount);
        long minWaitNanos = idleInNanos + this.bytesToNanos(minByteCount - this.maxByteCount);
        if (minWaitNanos == 0L) {
            this.allocatedUntil = now + this.bytesToNanos(this.maxByteCount);
            return minByteCount;
        }
        return -minWaitNanos;
    }

    private final long nanosToBytes(long $this$nanosToBytes) {
        return $this$nanosToBytes * this.bytesPerSecond / 1000000000L;
    }

    private final long bytesToNanos(long $this$bytesToNanos) {
        return $this$bytesToNanos * 1000000000L / this.bytesPerSecond;
    }

    private final void waitNanos(long nanosToWait) {
        long millisToWait = nanosToWait / 1000000L;
        long remainderNanos = nanosToWait - millisToWait * 1000000L;
        Throttler throttler = this;
        if (throttler == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
        }
        ((Object)throttler).wait(millisToWait, (int)remainderNanos);
    }

    @NotNull
    public final Source source(@NotNull Source source2) {
        Intrinsics.checkNotNullParameter(source2, "source");
        return new ForwardingSource(this, source2, source2){
            final /* synthetic */ Throttler this$0;
            final /* synthetic */ Source $source;

            public long read(@NotNull Buffer sink2, long byteCount) {
                Intrinsics.checkNotNullParameter(sink2, "sink");
                try {
                    long toRead = this.this$0.take$okio(byteCount);
                    return super.read(sink2, toRead);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw (Throwable)new InterruptedIOException("interrupted");
                }
            }
            {
                this.this$0 = this$0;
                this.$source = $captured_local_variable$1;
                super($super_call_param$2);
            }
        };
    }

    @NotNull
    public final Sink sink(@NotNull Sink sink2) {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        return new ForwardingSink(this, sink2, sink2){
            final /* synthetic */ Throttler this$0;
            final /* synthetic */ Sink $sink;

            public void write(@NotNull Buffer source2, long byteCount) throws IOException {
                Intrinsics.checkNotNullParameter(source2, "source");
                try {
                    long toWrite;
                    for (long remaining = byteCount; remaining > 0L; remaining -= toWrite) {
                        toWrite = this.this$0.take$okio(remaining);
                        super.write(source2, toWrite);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw (Throwable)new InterruptedIOException("interrupted");
                }
            }
            {
                this.this$0 = this$0;
                this.$sink = $captured_local_variable$1;
                super($super_call_param$2);
            }
        };
    }

    public Throttler(long allocatedUntil) {
        this.allocatedUntil = allocatedUntil;
        this.waitByteCount = 8192L;
        this.maxByteCount = 262144L;
    }

    public Throttler() {
        this(System.nanoTime());
    }
}

