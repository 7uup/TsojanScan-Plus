/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.Internal;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.time.Duration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;

@GwtCompatible(emulated=true)
public final class Uninterruptibles {
    @GwtIncompatible
    public static void awaitUninterruptibly(CountDownLatch latch) {
        boolean interrupted = false;
        while (true) {
            try {
                latch.await();
                return;
            } catch (InterruptedException e) {
                interrupted = true;
                continue;
            }
            break;
        }
        finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    @Beta
    public static boolean awaitUninterruptibly(CountDownLatch latch, Duration timeout2) {
        return Uninterruptibles.awaitUninterruptibly(latch, Internal.toNanosSaturated(timeout2), TimeUnit.NANOSECONDS);
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    public static boolean awaitUninterruptibly(CountDownLatch latch, long timeout2, TimeUnit unit) {
        boolean interrupted = false;
        try {
            long remainingNanos = unit.toNanos(timeout2);
            long end = System.nanoTime() + remainingNanos;
            while (true) {
                try {
                    boolean bl = latch.await(remainingNanos, TimeUnit.NANOSECONDS);
                    return bl;
                } catch (InterruptedException e) {
                    interrupted = true;
                    remainingNanos = end - System.nanoTime();
                    continue;
                }
                break;
            }
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @GwtIncompatible
    @Beta
    public static boolean awaitUninterruptibly(Condition condition, Duration timeout2) {
        return Uninterruptibles.awaitUninterruptibly(condition, Internal.toNanosSaturated(timeout2), TimeUnit.NANOSECONDS);
    }

    @GwtIncompatible
    public static boolean awaitUninterruptibly(Condition condition, long timeout2, TimeUnit unit) {
        boolean interrupted = false;
        try {
            long remainingNanos = unit.toNanos(timeout2);
            long end = System.nanoTime() + remainingNanos;
            while (true) {
                try {
                    boolean bl = condition.await(remainingNanos, TimeUnit.NANOSECONDS);
                    return bl;
                } catch (InterruptedException e) {
                    interrupted = true;
                    remainingNanos = end - System.nanoTime();
                    continue;
                }
                break;
            }
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @GwtIncompatible
    public static void joinUninterruptibly(Thread toJoin) {
        boolean interrupted = false;
        while (true) {
            try {
                toJoin.join();
                return;
            } catch (InterruptedException e) {
                interrupted = true;
                continue;
            }
            break;
        }
        finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @GwtIncompatible
    @Beta
    public static void joinUninterruptibly(Thread toJoin, Duration timeout2) {
        Uninterruptibles.joinUninterruptibly(toJoin, Internal.toNanosSaturated(timeout2), TimeUnit.NANOSECONDS);
    }

    @GwtIncompatible
    public static void joinUninterruptibly(Thread toJoin, long timeout2, TimeUnit unit) {
        Preconditions.checkNotNull(toJoin);
        boolean interrupted = false;
        try {
            long remainingNanos = unit.toNanos(timeout2);
            long end = System.nanoTime() + remainingNanos;
            while (true) {
                try {
                    TimeUnit.NANOSECONDS.timedJoin(toJoin, remainingNanos);
                    return;
                } catch (InterruptedException e) {
                    interrupted = true;
                    remainingNanos = end - System.nanoTime();
                    continue;
                }
                break;
            }
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @CanIgnoreReturnValue
    public static <V> V getUninterruptibly(Future<V> future) throws ExecutionException {
        boolean interrupted = false;
        while (true) {
            try {
                V v = future.get();
                return v;
            } catch (InterruptedException e) {
                interrupted = true;
                continue;
            }
            break;
        }
        finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    @Beta
    public static <V> V getUninterruptibly(Future<V> future, Duration timeout2) throws ExecutionException, TimeoutException {
        return Uninterruptibles.getUninterruptibly(future, Internal.toNanosSaturated(timeout2), TimeUnit.NANOSECONDS);
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    public static <V> V getUninterruptibly(Future<V> future, long timeout2, TimeUnit unit) throws ExecutionException, TimeoutException {
        boolean interrupted = false;
        try {
            long remainingNanos = unit.toNanos(timeout2);
            long end = System.nanoTime() + remainingNanos;
            while (true) {
                V v;
                try {
                    v = future.get(remainingNanos, TimeUnit.NANOSECONDS);
                } catch (InterruptedException e) {
                    interrupted = true;
                    remainingNanos = end - System.nanoTime();
                    continue;
                }
                return v;
            }
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @GwtIncompatible
    public static <E> E takeUninterruptibly(BlockingQueue<E> queue) {
        boolean interrupted = false;
        while (true) {
            try {
                E e = queue.take();
                return e;
            } catch (InterruptedException e) {
                interrupted = true;
                continue;
            }
            break;
        }
        finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @GwtIncompatible
    public static <E> void putUninterruptibly(BlockingQueue<E> queue, E element) {
        boolean interrupted = false;
        while (true) {
            try {
                queue.put(element);
                return;
            } catch (InterruptedException e) {
                interrupted = true;
                continue;
            }
            break;
        }
        finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @GwtIncompatible
    @Beta
    public static void sleepUninterruptibly(Duration sleepFor) {
        Uninterruptibles.sleepUninterruptibly(Internal.toNanosSaturated(sleepFor), TimeUnit.NANOSECONDS);
    }

    @GwtIncompatible
    public static void sleepUninterruptibly(long sleepFor, TimeUnit unit) {
        boolean interrupted = false;
        try {
            long remainingNanos = unit.toNanos(sleepFor);
            long end = System.nanoTime() + remainingNanos;
            while (true) {
                try {
                    TimeUnit.NANOSECONDS.sleep(remainingNanos);
                    return;
                } catch (InterruptedException e) {
                    interrupted = true;
                    remainingNanos = end - System.nanoTime();
                    continue;
                }
                break;
            }
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @GwtIncompatible
    @Beta
    public static boolean tryAcquireUninterruptibly(Semaphore semaphore, Duration timeout2) {
        return Uninterruptibles.tryAcquireUninterruptibly(semaphore, Internal.toNanosSaturated(timeout2), TimeUnit.NANOSECONDS);
    }

    @GwtIncompatible
    public static boolean tryAcquireUninterruptibly(Semaphore semaphore, long timeout2, TimeUnit unit) {
        return Uninterruptibles.tryAcquireUninterruptibly(semaphore, 1, timeout2, unit);
    }

    @GwtIncompatible
    @Beta
    public static boolean tryAcquireUninterruptibly(Semaphore semaphore, int permits, Duration timeout2) {
        return Uninterruptibles.tryAcquireUninterruptibly(semaphore, permits, Internal.toNanosSaturated(timeout2), TimeUnit.NANOSECONDS);
    }

    @GwtIncompatible
    public static boolean tryAcquireUninterruptibly(Semaphore semaphore, int permits, long timeout2, TimeUnit unit) {
        boolean interrupted = false;
        try {
            long remainingNanos = unit.toNanos(timeout2);
            long end = System.nanoTime() + remainingNanos;
            while (true) {
                try {
                    boolean bl = semaphore.tryAcquire(permits, remainingNanos, TimeUnit.NANOSECONDS);
                    return bl;
                } catch (InterruptedException e) {
                    interrupted = true;
                    remainingNanos = end - System.nanoTime();
                    continue;
                }
                break;
            }
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private Uninterruptibles() {
    }
}

