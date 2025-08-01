/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.coroutines;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Result;
import kotlin.SinceKotlin;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0001\u0018\u0000 \u001a*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003:\u0001\u001aB\u0015\b\u0011\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u00a2\u0006\u0002\u0010\u0005B\u001f\b\u0000\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\bJ\n\u0010\u0011\u001a\u0004\u0018\u00010\u0007H\u0001J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u001e\u0010\u0014\u001a\u00020\u00152\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0016\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u0016\u0010\t\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001b"}, d2={"Lkotlin/coroutines/SafeContinuation;", "T", "Lkotlin/coroutines/Continuation;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "delegate", "(Lkotlin/coroutines/Continuation;)V", "initialResult", "", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;)V", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "result", "getOrThrow", "getStackTraceElement", "Ljava/lang/StackTraceElement;", "resumeWith", "", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "toString", "", "Companion", "kotlin-stdlib"})
@PublishedApi
@SinceKotlin(version="1.3")
public final class SafeContinuation<T>
implements Continuation<T>,
CoroutineStackFrame {
    private volatile Object result;
    private final Continuation<T> delegate;
    private static final AtomicReferenceFieldUpdater<SafeContinuation<?>, Object> RESULT;
    private static final Companion Companion;

    @Override
    @NotNull
    public CoroutineContext getContext() {
        return this.delegate.getContext();
    }

    @Override
    public void resumeWith(@NotNull Object result) {
        block2: {
            while (true) {
                Object cur = this.result;
                if (cur == CoroutineSingletons.UNDECIDED) {
                    if (!RESULT.compareAndSet(this, (Object)CoroutineSingletons.UNDECIDED, result)) continue;
                    return;
                }
                if (cur != IntrinsicsKt.getCOROUTINE_SUSPENDED()) break block2;
                if (RESULT.compareAndSet(this, IntrinsicsKt.getCOROUTINE_SUSPENDED(), (Object)CoroutineSingletons.RESUMED)) break;
            }
            this.delegate.resumeWith(result);
            return;
        }
        throw (Throwable)new IllegalStateException("Already resumed");
    }

    @PublishedApi
    @Nullable
    public final Object getOrThrow() {
        Object object;
        Object result = this.result;
        if (result == CoroutineSingletons.UNDECIDED) {
            if (RESULT.compareAndSet(this, (Object)CoroutineSingletons.UNDECIDED, IntrinsicsKt.getCOROUTINE_SUSPENDED())) {
                return IntrinsicsKt.getCOROUTINE_SUSPENDED();
            }
            result = this.result;
        }
        if (result == CoroutineSingletons.RESUMED) {
            object = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        } else {
            if (result instanceof Result.Failure) {
                throw ((Result.Failure)result).exception;
            }
            object = result;
        }
        return object;
    }

    @Override
    @Nullable
    public CoroutineStackFrame getCallerFrame() {
        Continuation<T> continuation = this.delegate;
        if (!(continuation instanceof CoroutineStackFrame)) {
            continuation = null;
        }
        return (CoroutineStackFrame)((Object)continuation);
    }

    @Override
    @Nullable
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    @NotNull
    public String toString() {
        return "SafeContinuation for " + this.delegate;
    }

    public SafeContinuation(@NotNull Continuation<? super T> delegate, @Nullable Object initialResult) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
        this.result = initialResult;
    }

    @PublishedApi
    public SafeContinuation(@NotNull Continuation<? super T> delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this(delegate, (Object)CoroutineSingletons.UNDECIDED);
    }

    static {
        Companion = new Companion(null);
        RESULT = AtomicReferenceFieldUpdater.newUpdater(SafeContinuation.class, Object.class, "result");
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002Rf\u0010\u0003\u001aR\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 \u0006*\b\u0012\u0002\b\u0003\u0018\u00010\u00050\u0005\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00010\u0001 \u0006*(\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 \u0006*\b\u0012\u0002\b\u0003\u0018\u00010\u00050\u0005\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00010\u0001\u0018\u00010\u00040\u00048\u0002X\u0083\u0004\u00a2\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002\u00a8\u0006\b"}, d2={"Lkotlin/coroutines/SafeContinuation$Companion;", "", "()V", "RESULT", "Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", "Lkotlin/coroutines/SafeContinuation;", "kotlin.jvm.PlatformType", "getRESULT$annotations", "kotlin-stdlib"})
    private static final class Companion {
        @JvmStatic
        private static /* synthetic */ void getRESULT$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

