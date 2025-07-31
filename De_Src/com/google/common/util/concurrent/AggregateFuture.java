/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.util.concurrent.AggregateFutureState;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.errorprone.annotations.ForOverride;
import com.google.errorprone.annotations.OverridingMethodsMustInvokeSuper;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
abstract class AggregateFuture<InputT, OutputT>
extends AggregateFutureState<OutputT> {
    private static final Logger logger = Logger.getLogger(AggregateFuture.class.getName());
    private @Nullable ImmutableCollection<? extends ListenableFuture<? extends InputT>> futures;
    private final boolean allMustSucceed;
    private final boolean collectsValues;

    AggregateFuture(ImmutableCollection<? extends ListenableFuture<? extends InputT>> futures, boolean allMustSucceed, boolean collectsValues) {
        super(futures.size());
        this.futures = Preconditions.checkNotNull(futures);
        this.allMustSucceed = allMustSucceed;
        this.collectsValues = collectsValues;
    }

    @Override
    protected final void afterDone() {
        super.afterDone();
        ImmutableCollection<ListenableFuture<InputT>> localFutures = this.futures;
        this.releaseResources(ReleaseResourcesReason.OUTPUT_FUTURE_DONE);
        if (this.isCancelled() & localFutures != null) {
            boolean wasInterrupted = this.wasInterrupted();
            for (Future future : localFutures) {
                future.cancel(wasInterrupted);
            }
        }
    }

    @Override
    protected final String pendingToString() {
        ImmutableCollection<? extends ListenableFuture<? extends InputT>> localFutures = this.futures;
        if (localFutures != null) {
            return "futures=" + localFutures;
        }
        return super.pendingToString();
    }

    final void init() {
        if (this.futures.isEmpty()) {
            this.handleAllCompleted();
            return;
        }
        if (this.allMustSucceed) {
            int i = 0;
            for (final ListenableFuture listenableFuture : this.futures) {
                final int n = i++;
                listenableFuture.addListener(new Runnable(){

                    @Override
                    public void run() {
                        try {
                            if (listenableFuture.isCancelled()) {
                                AggregateFuture.this.futures = null;
                                AggregateFuture.this.cancel(false);
                            } else {
                                AggregateFuture.this.collectValueFromNonCancelledFuture(n, listenableFuture);
                            }
                        } finally {
                            AggregateFuture.this.decrementCountAndMaybeComplete(null);
                        }
                    }
                }, MoreExecutors.directExecutor());
            }
        } else {
            final ImmutableCollection<ListenableFuture<InputT>> localFutures = this.collectsValues ? this.futures : null;
            Runnable listener = new Runnable(){

                @Override
                public void run() {
                    AggregateFuture.this.decrementCountAndMaybeComplete(localFutures);
                }
            };
            for (ListenableFuture listenableFuture : this.futures) {
                listenableFuture.addListener(listener, MoreExecutors.directExecutor());
            }
        }
    }

    private void handleException(Throwable throwable) {
        boolean firstTimeSeeingThisException;
        boolean completedWithFailure;
        Preconditions.checkNotNull(throwable);
        if (this.allMustSucceed && !(completedWithFailure = this.setException(throwable)) && (firstTimeSeeingThisException = AggregateFuture.addCausalChain(this.getOrInitSeenExceptions(), throwable))) {
            AggregateFuture.log(throwable);
            return;
        }
        if (throwable instanceof Error) {
            AggregateFuture.log(throwable);
        }
    }

    private static void log(Throwable throwable) {
        String message = throwable instanceof Error ? "Input Future failed with Error" : "Got more than one input Future failure. Logging failures after the first";
        logger.log(Level.SEVERE, message, throwable);
    }

    @Override
    final void addInitialException(Set<Throwable> seen) {
        Preconditions.checkNotNull(seen);
        if (!this.isCancelled()) {
            boolean bl = AggregateFuture.addCausalChain(seen, this.tryInternalFastPathGetFailure());
        }
    }

    private void collectValueFromNonCancelledFuture(int index, Future<? extends InputT> future) {
        try {
            this.collectOneValue(index, Futures.getDone(future));
        } catch (ExecutionException e) {
            this.handleException(e.getCause());
        } catch (Throwable t) {
            this.handleException(t);
        }
    }

    private void decrementCountAndMaybeComplete(@Nullable ImmutableCollection<? extends Future<? extends InputT>> futuresIfNeedToCollectAtCompletion) {
        int newRemaining = this.decrementRemainingAndGet();
        Preconditions.checkState(newRemaining >= 0, "Less than 0 remaining futures");
        if (newRemaining == 0) {
            this.processCompleted(futuresIfNeedToCollectAtCompletion);
        }
    }

    private void processCompleted(@Nullable ImmutableCollection<? extends Future<? extends InputT>> futuresIfNeedToCollectAtCompletion) {
        if (futuresIfNeedToCollectAtCompletion != null) {
            int i = 0;
            for (Future future : futuresIfNeedToCollectAtCompletion) {
                if (!future.isCancelled()) {
                    this.collectValueFromNonCancelledFuture(i, future);
                }
                ++i;
            }
        }
        this.clearSeenExceptions();
        this.handleAllCompleted();
        this.releaseResources(ReleaseResourcesReason.ALL_INPUT_FUTURES_PROCESSED);
    }

    @ForOverride
    @OverridingMethodsMustInvokeSuper
    void releaseResources(ReleaseResourcesReason reason) {
        Preconditions.checkNotNull(reason);
        this.futures = null;
    }

    abstract void collectOneValue(int var1, @Nullable InputT var2);

    abstract void handleAllCompleted();

    private static boolean addCausalChain(Set<Throwable> seen, Throwable t) {
        while (t != null) {
            boolean firstTimeSeen = seen.add(t);
            if (!firstTimeSeen) {
                return false;
            }
            t = t.getCause();
        }
        return true;
    }

    static enum ReleaseResourcesReason {
        OUTPUT_FUTURE_DONE,
        ALL_INPUT_FUTURES_PROCESSED;

    }
}

