/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0001H\u0016J\b\u0010\u0007\u001a\u00020\u0001H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\b\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001J\b\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\tH\u0016R\u001c\u0010\u0002\u001a\u00020\u00018\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0004\"\u0004\b\u0005\u0010\u0003\u00a8\u0006\u0012"}, d2={"Lokio/ForwardingTimeout;", "Lokio/Timeout;", "delegate", "(Lokio/Timeout;)V", "()Lokio/Timeout;", "setDelegate", "clearDeadline", "clearTimeout", "deadlineNanoTime", "", "hasDeadline", "", "throwIfReached", "", "timeout", "unit", "Ljava/util/concurrent/TimeUnit;", "timeoutNanos", "okio"})
public class ForwardingTimeout
extends Timeout {
    @NotNull
    private Timeout delegate;

    @NotNull
    public final ForwardingTimeout setDelegate(@NotNull Timeout delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
        return this;
    }

    @Override
    @NotNull
    public Timeout timeout(long timeout2, @NotNull TimeUnit unit) {
        Intrinsics.checkNotNullParameter((Object)unit, "unit");
        return this.delegate.timeout(timeout2, unit);
    }

    @Override
    public long timeoutNanos() {
        return this.delegate.timeoutNanos();
    }

    @Override
    public boolean hasDeadline() {
        return this.delegate.hasDeadline();
    }

    @Override
    public long deadlineNanoTime() {
        return this.delegate.deadlineNanoTime();
    }

    @Override
    @NotNull
    public Timeout deadlineNanoTime(long deadlineNanoTime) {
        return this.delegate.deadlineNanoTime(deadlineNanoTime);
    }

    @Override
    @NotNull
    public Timeout clearTimeout() {
        return this.delegate.clearTimeout();
    }

    @Override
    @NotNull
    public Timeout clearDeadline() {
        return this.delegate.clearDeadline();
    }

    @Override
    public void throwIfReached() throws IOException {
        this.delegate.throwIfReached();
    }

    @JvmName(name="delegate")
    @NotNull
    public final Timeout delegate() {
        return this.delegate;
    }

    public final /* synthetic */ void setDelegate(@NotNull Timeout timeout2) {
        Intrinsics.checkNotNullParameter(timeout2, "<set-?>");
        this.delegate = timeout2;
    }

    public ForwardingTimeout(@NotNull Timeout delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
    }
}

