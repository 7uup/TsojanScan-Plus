/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.util.concurrent.Internal;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@DoNotMock(value="Use FakeTimeLimiter")
@Beta
@GwtIncompatible
public interface TimeLimiter {
    public <T> T newProxy(T var1, Class<T> var2, long var3, TimeUnit var5);

    default public <T> T newProxy(T target, Class<T> interfaceType, Duration timeout2) {
        return this.newProxy(target, interfaceType, Internal.toNanosSaturated(timeout2), TimeUnit.NANOSECONDS);
    }

    @CanIgnoreReturnValue
    public <T> T callWithTimeout(Callable<T> var1, long var2, TimeUnit var4) throws TimeoutException, InterruptedException, ExecutionException;

    @CanIgnoreReturnValue
    default public <T> T callWithTimeout(Callable<T> callable, Duration timeout2) throws TimeoutException, InterruptedException, ExecutionException {
        return this.callWithTimeout(callable, Internal.toNanosSaturated(timeout2), TimeUnit.NANOSECONDS);
    }

    @CanIgnoreReturnValue
    public <T> T callUninterruptiblyWithTimeout(Callable<T> var1, long var2, TimeUnit var4) throws TimeoutException, ExecutionException;

    @CanIgnoreReturnValue
    default public <T> T callUninterruptiblyWithTimeout(Callable<T> callable, Duration timeout2) throws TimeoutException, ExecutionException {
        return this.callUninterruptiblyWithTimeout(callable, Internal.toNanosSaturated(timeout2), TimeUnit.NANOSECONDS);
    }

    public void runWithTimeout(Runnable var1, long var2, TimeUnit var4) throws TimeoutException, InterruptedException;

    default public void runWithTimeout(Runnable runnable2, Duration timeout2) throws TimeoutException, InterruptedException {
        this.runWithTimeout(runnable2, Internal.toNanosSaturated(timeout2), TimeUnit.NANOSECONDS);
    }

    public void runUninterruptiblyWithTimeout(Runnable var1, long var2, TimeUnit var4) throws TimeoutException;

    default public void runUninterruptiblyWithTimeout(Runnable runnable2, Duration timeout2) throws TimeoutException {
        this.runUninterruptiblyWithTimeout(runnable2, Internal.toNanosSaturated(timeout2), TimeUnit.NANOSECONDS);
    }
}

