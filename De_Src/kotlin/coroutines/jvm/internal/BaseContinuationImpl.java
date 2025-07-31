/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.coroutines.jvm.internal;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.DebugMetadataKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b!\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\u00020\u00032\u00020\u0004B\u0017\u0012\u0010\u0010\u0005\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0001\u00a2\u0006\u0002\u0010\u0006J$\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00012\b\u0010\u000e\u001a\u0004\u0018\u00010\u00022\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0016J\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0016J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\"\u0010\u0011\u001a\u0004\u0018\u00010\u00022\u000e\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0013H$\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001a\u00020\rH\u0014J\u001e\u0010\u0016\u001a\u00020\r2\u000e\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0013\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001b\u0010\u0005\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001a"}, d2={"Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Ljava/io/Serializable;", "completion", "(Lkotlin/coroutines/Continuation;)V", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "getCompletion", "()Lkotlin/coroutines/Continuation;", "create", "", "value", "getStackTraceElement", "Ljava/lang/StackTraceElement;", "invokeSuspend", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)Ljava/lang/Object;", "releaseIntercepted", "resumeWith", "(Ljava/lang/Object;)V", "toString", "", "kotlin-stdlib"})
@SinceKotlin(version="1.3")
public abstract class BaseContinuationImpl
implements Continuation<Object>,
CoroutineStackFrame,
Serializable {
    @Nullable
    private final Continuation<Object> completion;

    @Override
    public final void resumeWith(@NotNull Object result) {
        Continuation<Object> completion;
        Object outcome;
        BaseContinuationImpl current = this;
        Object param = result;
        while (true) {
            Object object;
            DebugProbesKt.probeCoroutineResumed(current);
            BaseContinuationImpl baseContinuationImpl = current;
            boolean bl = false;
            boolean bl2 = false;
            BaseContinuationImpl $this$with = baseContinuationImpl;
            boolean bl3 = false;
            Intrinsics.checkNotNull($this$with.completion);
            try {
                Object outcome2 = $this$with.invokeSuspend(param);
                if (outcome2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    return;
                }
                Result.Companion companion = Result.Companion;
                boolean bl4 = false;
                object = Result.constructor-impl(outcome2);
            } catch (Throwable exception) {
                Result.Companion companion = Result.Companion;
                boolean bl5 = false;
                object = Result.constructor-impl(ResultKt.createFailure(exception));
            }
            outcome = object;
            $this$with.releaseIntercepted();
            if (!(completion instanceof BaseContinuationImpl)) break;
            current = (BaseContinuationImpl)completion;
            param = outcome;
        }
        completion.resumeWith(outcome);
    }

    @Nullable
    protected abstract Object invokeSuspend(@NotNull Object var1);

    protected void releaseIntercepted() {
    }

    @NotNull
    public Continuation<Unit> create(@NotNull Continuation<?> completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        throw (Throwable)new UnsupportedOperationException("create(Continuation) has not been overridden");
    }

    @NotNull
    public Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        throw (Throwable)new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }

    @NotNull
    public String toString() {
        StackTraceElement stackTraceElement = this.getStackTraceElement();
        return "Continuation at " + (stackTraceElement != null ? (Serializable)stackTraceElement : (Serializable)((Object)this.getClass().getName()));
    }

    @Override
    @Nullable
    public CoroutineStackFrame getCallerFrame() {
        Continuation<Object> continuation = this.completion;
        if (!(continuation instanceof CoroutineStackFrame)) {
            continuation = null;
        }
        return (CoroutineStackFrame)((Object)continuation);
    }

    @Override
    @Nullable
    public StackTraceElement getStackTraceElement() {
        return DebugMetadataKt.getStackTraceElement(this);
    }

    @Nullable
    public final Continuation<Object> getCompletion() {
        return this.completion;
    }

    public BaseContinuationImpl(@Nullable Continuation<Object> completion) {
        this.completion = completion;
    }
}

