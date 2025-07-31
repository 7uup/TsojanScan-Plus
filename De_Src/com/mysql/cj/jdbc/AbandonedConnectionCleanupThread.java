/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.jdbc.NonRegisteringDriver;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class AbandonedConnectionCleanupThread
implements Runnable {
    private static final ExecutorService cleanupThreadExcecutorService;
    static Thread threadRef;

    private AbandonedConnectionCleanupThread() {
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void run() {
        while (true) {
            try {
                while (true) lbl-1000:
                // 4 sources

                {
                    this.checkContextClassLoaders();
                    ref = NonRegisteringDriver.refQueue.remove(5000L);
                    if (ref == null) continue;
                    try {
                        ((NonRegisteringDriver.ConnectionPhantomReference)ref).cleanup();
                    } finally {
                        NonRegisteringDriver.connectionPhantomRefs.remove(ref);
                        continue;
                    }
                    break;
                }
            } catch (InterruptedException e) {
                AbandonedConnectionCleanupThread.threadRef = null;
                return;
            } catch (Exception var1_3) {
                continue;
            }
            ** GOTO lbl-1000
            break;
        }
    }

    private void checkContextClassLoaders() {
        try {
            threadRef.getContextClassLoader().getResource("");
        } catch (Throwable e) {
            AbandonedConnectionCleanupThread.uncheckedShutdown();
        }
    }

    private static boolean consistentClassLoaders() {
        ClassLoader callerCtxClassLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader threadCtxClassLoader = threadRef.getContextClassLoader();
        return callerCtxClassLoader != null && threadCtxClassLoader != null && callerCtxClassLoader == threadCtxClassLoader;
    }

    public static void checkedShutdown() {
        AbandonedConnectionCleanupThread.shutdown(true);
    }

    public static void uncheckedShutdown() {
        AbandonedConnectionCleanupThread.shutdown(false);
    }

    private static void shutdown(boolean checked) {
        if (checked && !AbandonedConnectionCleanupThread.consistentClassLoaders()) {
            return;
        }
        cleanupThreadExcecutorService.shutdownNow();
    }

    @Deprecated
    public static void shutdown() {
        AbandonedConnectionCleanupThread.checkedShutdown();
    }

    static {
        threadRef = null;
        cleanupThreadExcecutorService = Executors.newSingleThreadExecutor(new ThreadFactory(){

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r, "Abandoned connection cleanup thread");
                t.setDaemon(true);
                t.setContextClassLoader(AbandonedConnectionCleanupThread.class.getClassLoader());
                threadRef = t;
                return threadRef;
            }
        });
        cleanupThreadExcecutorService.execute(new AbandonedConnectionCleanupThread());
    }
}

