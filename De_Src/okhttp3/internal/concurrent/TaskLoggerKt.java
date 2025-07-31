/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.concurrent;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000*\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0001H\u0002\u001a5\u0010\u000b\u001a\u0002H\f\"\u0004\b\u0000\u0010\f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\f0\u000eH\u0080\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000f\u001a*\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0080\b\u00f8\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\u0012"}, d2={"formatDuration", "", "ns", "", "log", "", "task", "Lokhttp3/internal/concurrent/Task;", "queue", "Lokhttp3/internal/concurrent/TaskQueue;", "message", "logElapsed", "T", "block", "Lkotlin/Function0;", "(Lokhttp3/internal/concurrent/Task;Lokhttp3/internal/concurrent/TaskQueue;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "taskLog", "messageBlock", "okhttp"})
public final class TaskLoggerKt {
    public static final void taskLog(@NotNull Task task, @NotNull TaskQueue queue, @NotNull Function0<String> messageBlock) {
        int $i$f$taskLog = 0;
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(queue, "queue");
        Intrinsics.checkNotNullParameter(messageBlock, "messageBlock");
        if (TaskRunner.Companion.getLogger().isLoggable(Level.FINE)) {
            TaskLoggerKt.log(task, queue, messageBlock.invoke());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static final <T> T logElapsed(@NotNull Task task, @NotNull TaskQueue queue, @NotNull Function0<? extends T> block) {
        T t;
        int $i$f$logElapsed = 0;
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(queue, "queue");
        Intrinsics.checkNotNullParameter(block, "block");
        long startNs = -1L;
        boolean loggingEnabled = TaskRunner.Companion.getLogger().isLoggable(Level.FINE);
        if (loggingEnabled) {
            startNs = queue.getTaskRunner$okhttp().getBackend().nanoTime();
            TaskLoggerKt.log(task, queue, "starting");
        }
        boolean completedNormally = false;
        try {
            T result = block.invoke();
            completedNormally = true;
            t = result;
        } catch (Throwable throwable) {
            InlineMarker.finallyStart(1);
            if (loggingEnabled) {
                long elapsedNs = queue.getTaskRunner$okhttp().getBackend().nanoTime() - startNs;
                if (completedNormally) {
                    TaskLoggerKt.log(task, queue, "finished run in " + TaskLoggerKt.formatDuration(elapsedNs));
                } else {
                    TaskLoggerKt.log(task, queue, "failed a run in " + TaskLoggerKt.formatDuration(elapsedNs));
                }
            }
            InlineMarker.finallyEnd(1);
            throw throwable;
        }
        InlineMarker.finallyStart(1);
        if (loggingEnabled) {
            long elapsedNs = queue.getTaskRunner$okhttp().getBackend().nanoTime() - startNs;
            TaskLoggerKt.log(task, queue, "finished run in " + TaskLoggerKt.formatDuration(elapsedNs));
        }
        InlineMarker.finallyEnd(1);
        return t;
    }

    private static final void log(Task task, TaskQueue queue, String message) {
        Logger logger = TaskRunner.Companion.getLogger();
        StringBuilder stringBuilder = new StringBuilder().append(queue.getName$okhttp()).append(' ');
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = "%-22s";
        Object[] objectArray = new Object[]{message};
        boolean bl = false;
        String string2 = String.format(string, Arrays.copyOf(objectArray, objectArray.length));
        Intrinsics.checkNotNullExpressionValue(string2, "java.lang.String.format(format, *args)");
        logger.fine(stringBuilder.append(string2).append(": ").append(task.getName()).toString());
    }

    @NotNull
    public static final String formatDuration(long ns) {
        String s2 = ns <= (long)-999500000 ? (ns - (long)500000000) / (long)1000000000 + " s " : (ns <= (long)-999500 ? (ns - (long)500000) / (long)1000000 + " ms" : (ns <= 0L ? (ns - (long)500) / (long)1000 + " \u00b5s" : (ns < (long)999500 ? (ns + (long)500) / (long)1000 + " \u00b5s" : (ns < (long)999500000 ? (ns + (long)500000) / (long)1000000 + " ms" : (ns + (long)500000000) / (long)1000000000 + " s "))));
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = "%6s";
        Object[] objectArray = new Object[]{s2};
        boolean bl = false;
        String string2 = String.format(string, Arrays.copyOf(objectArray, objectArray.length));
        Intrinsics.checkNotNullExpressionValue(string2, "java.lang.String.format(format, *args)");
        return string2;
    }
}

