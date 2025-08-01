/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.Internal;
import com.google.common.util.concurrent.SmoothRateLimiter;
import com.google.common.util.concurrent.Uninterruptibles;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.time.Duration;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
@GwtIncompatible
public abstract class RateLimiter {
    private final SleepingStopwatch stopwatch;
    private volatile @Nullable Object mutexDoNotUseDirectly;

    public static RateLimiter create(double permitsPerSecond) {
        return RateLimiter.create(permitsPerSecond, SleepingStopwatch.createFromSystemTimer());
    }

    @VisibleForTesting
    static RateLimiter create(double permitsPerSecond, SleepingStopwatch stopwatch) {
        SmoothRateLimiter.SmoothBursty rateLimiter = new SmoothRateLimiter.SmoothBursty(stopwatch, 1.0);
        rateLimiter.setRate(permitsPerSecond);
        return rateLimiter;
    }

    public static RateLimiter create(double permitsPerSecond, Duration warmupPeriod) {
        return RateLimiter.create(permitsPerSecond, Internal.toNanosSaturated(warmupPeriod), TimeUnit.NANOSECONDS);
    }

    public static RateLimiter create(double permitsPerSecond, long warmupPeriod, TimeUnit unit) {
        Preconditions.checkArgument(warmupPeriod >= 0L, "warmupPeriod must not be negative: %s", warmupPeriod);
        return RateLimiter.create(permitsPerSecond, warmupPeriod, unit, 3.0, SleepingStopwatch.createFromSystemTimer());
    }

    @VisibleForTesting
    static RateLimiter create(double permitsPerSecond, long warmupPeriod, TimeUnit unit, double coldFactor, SleepingStopwatch stopwatch) {
        SmoothRateLimiter.SmoothWarmingUp rateLimiter = new SmoothRateLimiter.SmoothWarmingUp(stopwatch, warmupPeriod, unit, coldFactor);
        rateLimiter.setRate(permitsPerSecond);
        return rateLimiter;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private Object mutex() {
        Object mutex = this.mutexDoNotUseDirectly;
        if (mutex == null) {
            RateLimiter rateLimiter = this;
            synchronized (rateLimiter) {
                mutex = this.mutexDoNotUseDirectly;
                if (mutex == null) {
                    this.mutexDoNotUseDirectly = mutex = new Object();
                }
            }
        }
        return mutex;
    }

    RateLimiter(SleepingStopwatch stopwatch) {
        this.stopwatch = Preconditions.checkNotNull(stopwatch);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void setRate(double permitsPerSecond) {
        Preconditions.checkArgument(permitsPerSecond > 0.0 && !Double.isNaN(permitsPerSecond), "rate must be positive");
        Object object = this.mutex();
        synchronized (object) {
            this.doSetRate(permitsPerSecond, this.stopwatch.readMicros());
        }
    }

    abstract void doSetRate(double var1, long var3);

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final double getRate() {
        Object object = this.mutex();
        synchronized (object) {
            return this.doGetRate();
        }
    }

    abstract double doGetRate();

    @CanIgnoreReturnValue
    public double acquire() {
        return this.acquire(1);
    }

    @CanIgnoreReturnValue
    public double acquire(int permits) {
        long microsToWait = this.reserve(permits);
        this.stopwatch.sleepMicrosUninterruptibly(microsToWait);
        return 1.0 * (double)microsToWait / (double)TimeUnit.SECONDS.toMicros(1L);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    final long reserve(int permits) {
        RateLimiter.checkPermits(permits);
        Object object = this.mutex();
        synchronized (object) {
            return this.reserveAndGetWaitLength(permits, this.stopwatch.readMicros());
        }
    }

    public boolean tryAcquire(Duration timeout2) {
        return this.tryAcquire(1, Internal.toNanosSaturated(timeout2), TimeUnit.NANOSECONDS);
    }

    public boolean tryAcquire(long timeout2, TimeUnit unit) {
        return this.tryAcquire(1, timeout2, unit);
    }

    public boolean tryAcquire(int permits) {
        return this.tryAcquire(permits, 0L, TimeUnit.MICROSECONDS);
    }

    public boolean tryAcquire() {
        return this.tryAcquire(1, 0L, TimeUnit.MICROSECONDS);
    }

    public boolean tryAcquire(int permits, Duration timeout2) {
        return this.tryAcquire(permits, Internal.toNanosSaturated(timeout2), TimeUnit.NANOSECONDS);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean tryAcquire(int permits, long timeout2, TimeUnit unit) {
        long microsToWait;
        long timeoutMicros = Math.max(unit.toMicros(timeout2), 0L);
        RateLimiter.checkPermits(permits);
        Object object = this.mutex();
        synchronized (object) {
            long nowMicros = this.stopwatch.readMicros();
            if (!this.canAcquire(nowMicros, timeoutMicros)) {
                return false;
            }
            microsToWait = this.reserveAndGetWaitLength(permits, nowMicros);
        }
        this.stopwatch.sleepMicrosUninterruptibly(microsToWait);
        return true;
    }

    private boolean canAcquire(long nowMicros, long timeoutMicros) {
        return this.queryEarliestAvailable(nowMicros) - timeoutMicros <= nowMicros;
    }

    final long reserveAndGetWaitLength(int permits, long nowMicros) {
        long momentAvailable = this.reserveEarliestAvailable(permits, nowMicros);
        return Math.max(momentAvailable - nowMicros, 0L);
    }

    abstract long queryEarliestAvailable(long var1);

    abstract long reserveEarliestAvailable(int var1, long var2);

    public String toString() {
        return String.format(Locale.ROOT, "RateLimiter[stableRate=%3.1fqps]", this.getRate());
    }

    private static void checkPermits(int permits) {
        Preconditions.checkArgument(permits > 0, "Requested permits (%s) must be positive", permits);
    }

    static abstract class SleepingStopwatch {
        protected SleepingStopwatch() {
        }

        protected abstract long readMicros();

        protected abstract void sleepMicrosUninterruptibly(long var1);

        public static SleepingStopwatch createFromSystemTimer() {
            return new SleepingStopwatch(){
                final Stopwatch stopwatch = Stopwatch.createStarted();

                @Override
                protected long readMicros() {
                    return this.stopwatch.elapsed(TimeUnit.MICROSECONDS);
                }

                @Override
                protected void sleepMicrosUninterruptibly(long micros) {
                    if (micros > 0L) {
                        Uninterruptibles.sleepUninterruptibly(micros, TimeUnit.MICROSECONDS);
                    }
                }
            };
        }
    }
}

