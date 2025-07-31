/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskLoggerKt;
import okhttp3.internal.concurrent.TaskQueue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 #2\u00020\u0001:\u0003\"#$B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u0014J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\rH\u0002J\b\u0010\u001a\u001a\u0004\u0018\u00010\u0018J\u0010\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0006\u0010\u001c\u001a\u00020\u0016J\u0015\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\tH\u0000\u00a2\u0006\u0002\b\u001fJ\u0006\u0010 \u001a\u00020\tJ\u0010\u0010!\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2={"Lokhttp3/internal/concurrent/TaskRunner;", "", "backend", "Lokhttp3/internal/concurrent/TaskRunner$Backend;", "(Lokhttp3/internal/concurrent/TaskRunner$Backend;)V", "getBackend", "()Lokhttp3/internal/concurrent/TaskRunner$Backend;", "busyQueues", "", "Lokhttp3/internal/concurrent/TaskQueue;", "coordinatorWaiting", "", "coordinatorWakeUpAt", "", "nextQueueName", "", "readyQueues", "runnable", "Ljava/lang/Runnable;", "activeQueues", "", "afterRun", "", "task", "Lokhttp3/internal/concurrent/Task;", "delayNanos", "awaitTaskToRun", "beforeRun", "cancelAll", "kickCoordinator", "taskQueue", "kickCoordinator$okhttp", "newQueue", "runTask", "Backend", "Companion", "RealBackend", "okhttp"})
public final class TaskRunner {
    private int nextQueueName;
    private boolean coordinatorWaiting;
    private long coordinatorWakeUpAt;
    private final List<TaskQueue> busyQueues;
    private final List<TaskQueue> readyQueues;
    private final Runnable runnable;
    @NotNull
    private final Backend backend;
    @JvmField
    @NotNull
    public static final TaskRunner INSTANCE;
    @NotNull
    private static final Logger logger;
    public static final Companion Companion;

    public final void kickCoordinator$okhttp(@NotNull TaskQueue taskQueue) {
        Intrinsics.checkNotNullParameter(taskQueue, "taskQueue");
        TaskRunner $this$assertThreadHoldsLock$iv = this;
        boolean $i$f$assertThreadHoldsLock = false;
        if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) {
            StringBuilder stringBuilder = new StringBuilder().append("Thread ");
            Thread thread2 = Thread.currentThread();
            Intrinsics.checkNotNullExpressionValue(thread2, "Thread.currentThread()");
            throw (Throwable)((Object)new AssertionError((Object)stringBuilder.append(thread2.getName()).append(" MUST hold lock on ").append($this$assertThreadHoldsLock$iv).toString()));
        }
        if (taskQueue.getActiveTask$okhttp() == null) {
            Collection collection = taskQueue.getFutureTasks$okhttp();
            boolean bl = false;
            if (!collection.isEmpty()) {
                Util.addIfAbsent(this.readyQueues, taskQueue);
            } else {
                this.readyQueues.remove(taskQueue);
            }
        }
        if (this.coordinatorWaiting) {
            this.backend.coordinatorNotify(this);
        } else {
            this.backend.execute(this.runnable);
        }
    }

    private final void beforeRun(Task task) {
        TaskRunner $this$assertThreadHoldsLock$iv = this;
        boolean $i$f$assertThreadHoldsLock = false;
        if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) {
            StringBuilder stringBuilder = new StringBuilder().append("Thread ");
            Thread thread2 = Thread.currentThread();
            Intrinsics.checkNotNullExpressionValue(thread2, "Thread.currentThread()");
            throw (Throwable)((Object)new AssertionError((Object)stringBuilder.append(thread2.getName()).append(" MUST hold lock on ").append($this$assertThreadHoldsLock$iv).toString()));
        }
        task.setNextExecuteNanoTime$okhttp(-1L);
        TaskQueue taskQueue = task.getQueue$okhttp();
        Intrinsics.checkNotNull(taskQueue);
        TaskQueue queue = taskQueue;
        queue.getFutureTasks$okhttp().remove(task);
        this.readyQueues.remove(queue);
        queue.setActiveTask$okhttp(task);
        this.busyQueues.add(queue);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void runTask(Task task) {
        Thread currentThread;
        TaskRunner $this$assertThreadDoesntHoldLock$iv = this;
        boolean $i$f$assertThreadDoesntHoldLock = false;
        if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv)) {
            StringBuilder stringBuilder = new StringBuilder().append("Thread ");
            Thread thread2 = Thread.currentThread();
            Intrinsics.checkNotNullExpressionValue(thread2, "Thread.currentThread()");
            throw (Throwable)((Object)new AssertionError((Object)stringBuilder.append(thread2.getName()).append(" MUST NOT hold lock on ").append($this$assertThreadDoesntHoldLock$iv).toString()));
        }
        Thread thread3 = currentThread = Thread.currentThread();
        Intrinsics.checkNotNullExpressionValue(thread3, "currentThread");
        String oldName = thread3.getName();
        currentThread.setName(task.getName());
        long delayNanos = -1L;
        try {
            delayNanos = task.runOnce();
        } finally {
            TaskRunner taskRunner = this;
            boolean bl = false;
            boolean bl2 = false;
            synchronized (taskRunner) {
                boolean bl3 = false;
                this.afterRun(task, delayNanos);
                Unit unit = Unit.INSTANCE;
            }
            currentThread.setName(oldName);
        }
    }

    private final void afterRun(Task task, long delayNanos) {
        TaskRunner $this$assertThreadHoldsLock$iv = this;
        boolean $i$f$assertThreadHoldsLock = false;
        if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) {
            StringBuilder stringBuilder = new StringBuilder().append("Thread ");
            Thread thread2 = Thread.currentThread();
            Intrinsics.checkNotNullExpressionValue(thread2, "Thread.currentThread()");
            throw (Throwable)((Object)new AssertionError((Object)stringBuilder.append(thread2.getName()).append(" MUST hold lock on ").append($this$assertThreadHoldsLock$iv).toString()));
        }
        TaskQueue taskQueue = task.getQueue$okhttp();
        Intrinsics.checkNotNull(taskQueue);
        TaskQueue queue = taskQueue;
        $i$f$assertThreadHoldsLock = queue.getActiveTask$okhttp() == task;
        boolean bl = false;
        boolean bl2 = false;
        bl2 = false;
        boolean bl3 = false;
        if (!$i$f$assertThreadHoldsLock) {
            boolean bl4 = false;
            String string = "Check failed.";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        boolean cancelActiveTask = queue.getCancelActiveTask$okhttp();
        queue.setCancelActiveTask$okhttp(false);
        queue.setActiveTask$okhttp(null);
        this.busyQueues.remove(queue);
        if (delayNanos != -1L && !cancelActiveTask && !queue.getShutdown$okhttp()) {
            queue.scheduleAndDecide$okhttp(task, delayNanos, true);
        }
        Collection collection = queue.getFutureTasks$okhttp();
        bl2 = false;
        if (!collection.isEmpty()) {
            this.readyQueues.add(queue);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Nullable
    public final Task awaitTaskToRun() {
        TaskRunner $this$assertThreadHoldsLock$iv = this;
        boolean $i$f$assertThreadHoldsLock = false;
        if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) {
            StringBuilder stringBuilder = new StringBuilder().append("Thread ");
            Thread thread2 = Thread.currentThread();
            Intrinsics.checkNotNullExpressionValue(thread2, "Thread.currentThread()");
            throw (Throwable)((Object)new AssertionError((Object)stringBuilder.append(thread2.getName()).append(" MUST hold lock on ").append($this$assertThreadHoldsLock$iv).toString()));
        }
        while (true) {
            long minDelayNanos;
            long now;
            block16: {
                Task readyTask;
                block18: {
                    block17: {
                        if (this.readyQueues.isEmpty()) {
                            return null;
                        }
                        now = this.backend.nanoTime();
                        minDelayNanos = Long.MAX_VALUE;
                        readyTask = null;
                        boolean multipleReadyTasks = false;
                        for (TaskQueue taskQueue : this.readyQueues) {
                            Task candidate = taskQueue.getFutureTasks$okhttp().get(0);
                            long l = 0L;
                            long l2 = candidate.getNextExecuteNanoTime$okhttp() - now;
                            boolean bl = false;
                            long candidateDelay = Math.max(l, l2);
                            if (candidateDelay > 0L) {
                                boolean bl2 = false;
                                minDelayNanos = Math.min(candidateDelay, minDelayNanos);
                                continue;
                            }
                            if (readyTask != null) {
                                multipleReadyTasks = true;
                                break;
                            }
                            readyTask = candidate;
                        }
                        if (readyTask == null) break block16;
                        this.beforeRun(readyTask);
                        if (multipleReadyTasks) break block17;
                        if (this.coordinatorWaiting) break block18;
                        Collection collection = this.readyQueues;
                        boolean bl = false;
                        if (!(!collection.isEmpty())) break block18;
                    }
                    this.backend.execute(this.runnable);
                }
                return readyTask;
            }
            if (this.coordinatorWaiting) {
                if (minDelayNanos < this.coordinatorWakeUpAt - now) {
                    this.backend.coordinatorNotify(this);
                }
                return null;
            }
            this.coordinatorWaiting = true;
            this.coordinatorWakeUpAt = now + minDelayNanos;
            try {
                this.backend.coordinatorWait(this, minDelayNanos);
                continue;
            } catch (InterruptedException interruptedException) {
                this.cancelAll();
                continue;
            } finally {
                this.coordinatorWaiting = false;
                continue;
            }
            break;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @NotNull
    public final TaskQueue newQueue() {
        TaskRunner taskRunner = this;
        boolean bl = false;
        int n = 0;
        synchronized (taskRunner) {
            boolean bl2 = false;
            int n2 = this.nextQueueName;
            this.nextQueueName = n2 + 1;
            n = n2;
        }
        int name = n;
        return new TaskQueue(this, "" + 'Q' + name);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @NotNull
    public final List<TaskQueue> activeQueues() {
        TaskRunner taskRunner = this;
        boolean bl = false;
        boolean bl2 = false;
        synchronized (taskRunner) {
            boolean bl3 = false;
            List<TaskQueue> list = CollectionsKt.plus((Collection)this.busyQueues, (Iterable)this.readyQueues);
            return list;
        }
    }

    public final void cancelAll() {
        int i;
        int n = this.busyQueues.size() - 1;
        boolean bl = false;
        while (n >= 0) {
            this.busyQueues.get(i).cancelAllAndDecide$okhttp();
            --i;
        }
        bl = false;
        for (i = this.readyQueues.size() - 1; i >= 0; --i) {
            TaskQueue queue = this.readyQueues.get(i);
            queue.cancelAllAndDecide$okhttp();
            if (!queue.getFutureTasks$okhttp().isEmpty()) continue;
            this.readyQueues.remove(i);
        }
    }

    @NotNull
    public final Backend getBackend() {
        return this.backend;
    }

    public TaskRunner(@NotNull Backend backend) {
        Intrinsics.checkNotNullParameter(backend, "backend");
        this.backend = backend;
        this.nextQueueName = 10000;
        boolean bl = false;
        this.busyQueues = new ArrayList();
        bl = false;
        this.readyQueues = new ArrayList();
        this.runnable = new Runnable(this){
            final /* synthetic */ TaskRunner this$0;

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            public void run() {
                while (true) {
                    long elapsedNs$iv;
                    TaskQueue queue$iv;
                    Task task;
                    Task task2;
                    TaskRunner taskRunner = this.this$0;
                    boolean bl = false;
                    boolean bl2 = false;
                    synchronized (taskRunner) {
                        boolean bl3 = false;
                        task2 = this.this$0.awaitTaskToRun();
                    }
                    if (task2 == null) return;
                    Intrinsics.checkNotNull(task.getQueue$okhttp());
                    boolean $i$f$logElapsed = false;
                    long startNs$iv = -1L;
                    boolean loggingEnabled$iv = TaskRunner.Companion.getLogger().isLoggable(Level.FINE);
                    if (loggingEnabled$iv) {
                        startNs$iv = queue$iv.getTaskRunner$okhttp().getBackend().nanoTime();
                        TaskLoggerKt.access$log(task, queue$iv, "starting");
                    }
                    boolean completedNormally$iv = false;
                    try {
                        boolean bl4 = false;
                        boolean completedNormally = false;
                        try {
                            TaskRunner.access$runTask(this.this$0, task);
                            completedNormally = true;
                        } catch (Throwable throwable) {
                            this.this$0.getBackend().execute(this);
                            throw throwable;
                        }
                        Unit result$iv = Unit.INSTANCE;
                        completedNormally$iv = true;
                        Unit unit = result$iv;
                        if (!loggingEnabled$iv) continue;
                        elapsedNs$iv = queue$iv.getTaskRunner$okhttp().getBackend().nanoTime() - startNs$iv;
                    } catch (Throwable throwable) {
                        if (!loggingEnabled$iv) throw throwable;
                        long elapsedNs$iv2 = queue$iv.getTaskRunner$okhttp().getBackend().nanoTime() - startNs$iv;
                        if (completedNormally$iv) {
                            TaskLoggerKt.access$log(task, queue$iv, "finished run in " + TaskLoggerKt.formatDuration(elapsedNs$iv2));
                            throw throwable;
                        }
                        TaskLoggerKt.access$log(task, queue$iv, "failed a run in " + TaskLoggerKt.formatDuration(elapsedNs$iv2));
                        throw throwable;
                    }
                    TaskLoggerKt.access$log(task, queue$iv, "finished run in " + TaskLoggerKt.formatDuration(elapsedNs$iv));
                }
            }
            {
                this.this$0 = this$0;
            }
        };
    }

    static {
        Companion = new Companion(null);
        INSTANCE = new TaskRunner(new RealBackend(Util.threadFactory(Util.okHttpName + " TaskRunner", true)));
        Logger logger = Logger.getLogger(TaskRunner.class.getName());
        Intrinsics.checkNotNullExpressionValue(logger, "Logger.getLogger(TaskRunner::class.java.name)");
        TaskRunner.logger = logger;
    }

    public static final /* synthetic */ void access$runTask(TaskRunner $this, Task task) {
        $this.runTask(task);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\tH&\u00a8\u0006\u000e"}, d2={"Lokhttp3/internal/concurrent/TaskRunner$Backend;", "", "beforeTask", "", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "coordinatorNotify", "coordinatorWait", "nanos", "", "execute", "runnable", "Ljava/lang/Runnable;", "nanoTime", "okhttp"})
    public static interface Backend {
        public void beforeTask(@NotNull TaskRunner var1);

        public long nanoTime();

        public void coordinatorNotify(@NotNull TaskRunner var1);

        public void coordinatorWait(@NotNull TaskRunner var1, long var2);

        public void execute(@NotNull Runnable var1);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u000eH\u0016J\u0006\u0010\u0013\u001a\u00020\bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2={"Lokhttp3/internal/concurrent/TaskRunner$RealBackend;", "Lokhttp3/internal/concurrent/TaskRunner$Backend;", "threadFactory", "Ljava/util/concurrent/ThreadFactory;", "(Ljava/util/concurrent/ThreadFactory;)V", "executor", "Ljava/util/concurrent/ThreadPoolExecutor;", "beforeTask", "", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "coordinatorNotify", "coordinatorWait", "nanos", "", "execute", "runnable", "Ljava/lang/Runnable;", "nanoTime", "shutdown", "okhttp"})
    public static final class RealBackend
    implements Backend {
        private final ThreadPoolExecutor executor;

        @Override
        public void beforeTask(@NotNull TaskRunner taskRunner) {
            Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        }

        @Override
        public long nanoTime() {
            return System.nanoTime();
        }

        @Override
        public void coordinatorNotify(@NotNull TaskRunner taskRunner) {
            Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
            TaskRunner $this$notify$iv = taskRunner;
            boolean $i$f$notify = false;
            ((Object)$this$notify$iv).notify();
        }

        @Override
        public void coordinatorWait(@NotNull TaskRunner taskRunner, long nanos) throws InterruptedException {
            Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
            long ms = nanos / 1000000L;
            long ns = nanos - ms * 1000000L;
            if (ms > 0L || nanos > 0L) {
                ((Object)taskRunner).wait(ms, (int)ns);
            }
        }

        @Override
        public void execute(@NotNull Runnable runnable2) {
            Intrinsics.checkNotNullParameter(runnable2, "runnable");
            this.executor.execute(runnable2);
        }

        public final void shutdown() {
            this.executor.shutdown();
        }

        public RealBackend(@NotNull ThreadFactory threadFactory2) {
            Intrinsics.checkNotNullParameter(threadFactory2, "threadFactory");
            this.executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, (BlockingQueue<Runnable>)new SynchronousQueue(), threadFactory2);
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2={"Lokhttp3/internal/concurrent/TaskRunner$Companion;", "", "()V", "INSTANCE", "Lokhttp3/internal/concurrent/TaskRunner;", "logger", "Ljava/util/logging/Logger;", "getLogger", "()Ljava/util/logging/Logger;", "okhttp"})
    public static final class Companion {
        @NotNull
        public final Logger getLogger() {
            return logger;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

