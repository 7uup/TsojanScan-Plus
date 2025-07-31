/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.util.concurrent.Internal;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@DoNotMock(value="Create an AbstractIdleService")
@GwtIncompatible
public interface Service {
    @CanIgnoreReturnValue
    public Service startAsync();

    public boolean isRunning();

    public State state();

    @CanIgnoreReturnValue
    public Service stopAsync();

    public void awaitRunning();

    default public void awaitRunning(Duration timeout2) throws TimeoutException {
        this.awaitRunning(Internal.toNanosSaturated(timeout2), TimeUnit.NANOSECONDS);
    }

    public void awaitRunning(long var1, TimeUnit var3) throws TimeoutException;

    public void awaitTerminated();

    default public void awaitTerminated(Duration timeout2) throws TimeoutException {
        this.awaitTerminated(Internal.toNanosSaturated(timeout2), TimeUnit.NANOSECONDS);
    }

    public void awaitTerminated(long var1, TimeUnit var3) throws TimeoutException;

    public Throwable failureCause();

    public void addListener(Listener var1, Executor var2);

    public static abstract class Listener {
        public void starting() {
        }

        public void running() {
        }

        public void stopping(State from) {
        }

        public void terminated(State from) {
        }

        public void failed(State from, Throwable failure) {
        }
    }

    public static enum State {
        NEW{

            @Override
            boolean isTerminal() {
                return false;
            }
        }
        ,
        STARTING{

            @Override
            boolean isTerminal() {
                return false;
            }
        }
        ,
        RUNNING{

            @Override
            boolean isTerminal() {
                return false;
            }
        }
        ,
        STOPPING{

            @Override
            boolean isTerminal() {
                return false;
            }
        }
        ,
        TERMINATED{

            @Override
            boolean isTerminal() {
                return true;
            }
        }
        ,
        FAILED{

            @Override
            boolean isTerminal() {
                return true;
            }
        };


        abstract boolean isTerminal();
    }
}

