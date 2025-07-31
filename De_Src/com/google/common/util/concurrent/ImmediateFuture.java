/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
class ImmediateFuture<V>
implements ListenableFuture<V> {
    static final ListenableFuture<?> NULL = new ImmediateFuture<Object>(null);
    private static final Logger log = Logger.getLogger(ImmediateFuture.class.getName());
    private final @Nullable V value;

    ImmediateFuture(@Nullable V value) {
        this.value = value;
    }

    @Override
    public void addListener(Runnable listener, Executor executor) {
        Preconditions.checkNotNull(listener, "Runnable was null.");
        Preconditions.checkNotNull(executor, "Executor was null.");
        try {
            executor.execute(listener);
        } catch (RuntimeException e) {
            log.log(Level.SEVERE, "RuntimeException while executing runnable " + listener + " with executor " + executor, e);
        }
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public V get() {
        return this.value;
    }

    @Override
    public V get(long timeout2, TimeUnit unit) throws ExecutionException {
        Preconditions.checkNotNull(unit);
        return this.get();
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return true;
    }

    public String toString() {
        return super.toString() + "[status=SUCCESS, result=[" + this.value + "]]";
    }

    static final class ImmediateCancelledFuture<V>
    extends AbstractFuture.TrustedFuture<V> {
        ImmediateCancelledFuture() {
            this.cancel(false);
        }
    }

    static final class ImmediateFailedFuture<V>
    extends AbstractFuture.TrustedFuture<V> {
        ImmediateFailedFuture(Throwable thrown) {
            this.setException(thrown);
        }
    }
}

