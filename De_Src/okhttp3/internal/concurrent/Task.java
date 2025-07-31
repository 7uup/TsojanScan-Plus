/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.concurrent;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.concurrent.TaskQueue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0015\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u0012H\u0000\u00a2\u0006\u0002\b\u0019J\b\u0010\u001a\u001a\u00020\fH&J\b\u0010\u001b\u001a\u00020\u0003H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016\u00a8\u0006\u001c"}, d2={"Lokhttp3/internal/concurrent/Task;", "", "name", "", "cancelable", "", "(Ljava/lang/String;Z)V", "getCancelable", "()Z", "getName", "()Ljava/lang/String;", "nextExecuteNanoTime", "", "getNextExecuteNanoTime$okhttp", "()J", "setNextExecuteNanoTime$okhttp", "(J)V", "queue", "Lokhttp3/internal/concurrent/TaskQueue;", "getQueue$okhttp", "()Lokhttp3/internal/concurrent/TaskQueue;", "setQueue$okhttp", "(Lokhttp3/internal/concurrent/TaskQueue;)V", "initQueue", "", "initQueue$okhttp", "runOnce", "toString", "okhttp"})
public abstract class Task {
    @Nullable
    private TaskQueue queue;
    private long nextExecuteNanoTime;
    @NotNull
    private final String name;
    private final boolean cancelable;

    @Nullable
    public final TaskQueue getQueue$okhttp() {
        return this.queue;
    }

    public final void setQueue$okhttp(@Nullable TaskQueue taskQueue) {
        this.queue = taskQueue;
    }

    public final long getNextExecuteNanoTime$okhttp() {
        return this.nextExecuteNanoTime;
    }

    public final void setNextExecuteNanoTime$okhttp(long l) {
        this.nextExecuteNanoTime = l;
    }

    public abstract long runOnce();

    public final void initQueue$okhttp(@NotNull TaskQueue queue) {
        Intrinsics.checkNotNullParameter(queue, "queue");
        if (this.queue == queue) {
            return;
        }
        boolean bl = this.queue == null;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "task is in multiple queues";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        this.queue = queue;
    }

    @NotNull
    public String toString() {
        return this.name;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final boolean getCancelable() {
        return this.cancelable;
    }

    public Task(@NotNull String name, boolean cancelable) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.cancelable = cancelable;
        this.nextExecuteNanoTime = -1L;
    }

    public /* synthetic */ Task(String string, boolean bl, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 2) != 0) {
            bl = true;
        }
        this(string, bl);
    }
}

