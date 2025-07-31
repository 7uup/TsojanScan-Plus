/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.util.concurrent.Internal;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@GwtIncompatible
public interface ListeningScheduledExecutorService
extends ScheduledExecutorService,
ListeningExecutorService {
    public ListenableScheduledFuture<?> schedule(Runnable var1, long var2, TimeUnit var4);

    default public ListenableScheduledFuture<?> schedule(Runnable command, Duration delay) {
        return this.schedule(command, Internal.toNanosSaturated(delay), TimeUnit.NANOSECONDS);
    }

    public <V> ListenableScheduledFuture<V> schedule(Callable<V> var1, long var2, TimeUnit var4);

    default public <V> ListenableScheduledFuture<V> schedule(Callable<V> callable, Duration delay) {
        return this.schedule((Callable)callable, Internal.toNanosSaturated(delay), TimeUnit.NANOSECONDS);
    }

    public ListenableScheduledFuture<?> scheduleAtFixedRate(Runnable var1, long var2, long var4, TimeUnit var6);

    default public ListenableScheduledFuture<?> scheduleAtFixedRate(Runnable command, Duration initialDelay, Duration period) {
        return this.scheduleAtFixedRate(command, Internal.toNanosSaturated(initialDelay), Internal.toNanosSaturated(period), TimeUnit.NANOSECONDS);
    }

    public ListenableScheduledFuture<?> scheduleWithFixedDelay(Runnable var1, long var2, long var4, TimeUnit var6);

    default public ListenableScheduledFuture<?> scheduleWithFixedDelay(Runnable command, Duration initialDelay, Duration delay) {
        return this.scheduleWithFixedDelay(command, Internal.toNanosSaturated(initialDelay), Internal.toNanosSaturated(delay), TimeUnit.NANOSECONDS);
    }
}

