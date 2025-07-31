/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.coroutines.jvm.internal;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0006\u0010\u000e\u001a\u00020\u0002J\u001e\u0010\u000f\u001a\u00020\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tH\u0016\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R(\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\tX\u0086\u000e\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006\u0011"}, d2={"Lkotlin/coroutines/jvm/internal/RunSuspend;", "Lkotlin/coroutines/Continuation;", "", "()V", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "result", "Lkotlin/Result;", "getResult-xLWZpok", "()Lkotlin/Result;", "setResult", "(Lkotlin/Result;)V", "await", "resumeWith", "(Ljava/lang/Object;)V", "kotlin-stdlib"})
final class RunSuspend
implements Continuation<Unit> {
    @Nullable
    private Result<Unit> result;

    @Override
    @NotNull
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    @Nullable
    public final Result<Unit> getResult-xLWZpok() {
        return this.result;
    }

    public final void setResult(@Nullable Result<Unit> result) {
        this.result = result;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void resumeWith(@NotNull Object result) {
        RunSuspend runSuspend = this;
        boolean bl = false;
        boolean bl2 = false;
        synchronized (runSuspend) {
            boolean bl3 = false;
            this.result = Result.box-impl(result);
            RunSuspend runSuspend2 = this;
            if (runSuspend2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
            }
            ((Object)runSuspend2).notifyAll();
            Unit unit = Unit.INSTANCE;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void await() {
        RunSuspend runSuspend = this;
        boolean bl = false;
        boolean bl2 = false;
        synchronized (runSuspend) {
            Result<Unit> result;
            boolean bl3 = false;
            while ((result = this.result) == null) {
                RunSuspend runSuspend2 = this;
                if (runSuspend2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                }
                ((Object)runSuspend2).wait();
            }
            Object object = result.unbox-impl();
            boolean bl4 = false;
            ResultKt.throwOnFailure(object);
            return;
        }
    }
}

