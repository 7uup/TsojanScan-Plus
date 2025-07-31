/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.AbstractService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.Service;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

@GwtIncompatible
public abstract class AbstractExecutionThreadService
implements Service {
    private static final Logger logger = Logger.getLogger(AbstractExecutionThreadService.class.getName());
    private final Service delegate = new AbstractService(){

        @Override
        protected final void doStart() {
            Executor executor = MoreExecutors.renamingDecorator(AbstractExecutionThreadService.this.executor(), new Supplier<String>(){

                @Override
                public String get() {
                    return AbstractExecutionThreadService.this.serviceName();
                }
            });
            executor.execute(new Runnable(){

                @Override
                public void run() {
                    try {
                        AbstractExecutionThreadService.this.startUp();
                        this.notifyStarted();
                        if (this.isRunning()) {
                            try {
                                AbstractExecutionThreadService.this.run();
                            } catch (Throwable t) {
                                try {
                                    AbstractExecutionThreadService.this.shutDown();
                                } catch (Exception ignored) {
                                    logger.log(Level.WARNING, "Error while attempting to shut down the service after failure.", ignored);
                                }
                                this.notifyFailed(t);
                                return;
                            }
                        }
                        AbstractExecutionThreadService.this.shutDown();
                        this.notifyStopped();
                    } catch (Throwable t) {
                        this.notifyFailed(t);
                    }
                }
            });
        }

        @Override
        protected void doStop() {
            AbstractExecutionThreadService.this.triggerShutdown();
        }

        @Override
        public String toString() {
            return AbstractExecutionThreadService.this.toString();
        }
    };

    protected AbstractExecutionThreadService() {
    }

    protected void startUp() throws Exception {
    }

    protected abstract void run() throws Exception;

    protected void shutDown() throws Exception {
    }

    @Beta
    protected void triggerShutdown() {
    }

    protected Executor executor() {
        return new Executor(){

            @Override
            public void execute(Runnable command) {
                MoreExecutors.newThread(AbstractExecutionThreadService.this.serviceName(), command).start();
            }
        };
    }

    public String toString() {
        return this.serviceName() + " [" + (Object)((Object)this.state()) + "]";
    }

    @Override
    public final boolean isRunning() {
        return this.delegate.isRunning();
    }

    @Override
    public final Service.State state() {
        return this.delegate.state();
    }

    @Override
    public final void addListener(Service.Listener listener, Executor executor) {
        this.delegate.addListener(listener, executor);
    }

    @Override
    public final Throwable failureCause() {
        return this.delegate.failureCause();
    }

    @Override
    @CanIgnoreReturnValue
    public final Service startAsync() {
        this.delegate.startAsync();
        return this;
    }

    @Override
    @CanIgnoreReturnValue
    public final Service stopAsync() {
        this.delegate.stopAsync();
        return this;
    }

    @Override
    public final void awaitRunning() {
        this.delegate.awaitRunning();
    }

    @Override
    public final void awaitRunning(Duration timeout2) throws TimeoutException {
        Service.super.awaitRunning(timeout2);
    }

    @Override
    public final void awaitRunning(long timeout2, TimeUnit unit) throws TimeoutException {
        this.delegate.awaitRunning(timeout2, unit);
    }

    @Override
    public final void awaitTerminated() {
        this.delegate.awaitTerminated();
    }

    @Override
    public final void awaitTerminated(Duration timeout2) throws TimeoutException {
        Service.super.awaitTerminated(timeout2);
    }

    @Override
    public final void awaitTerminated(long timeout2, TimeUnit unit) throws TimeoutException {
        this.delegate.awaitTerminated(timeout2, unit);
    }

    protected String serviceName() {
        return this.getClass().getSimpleName();
    }
}

