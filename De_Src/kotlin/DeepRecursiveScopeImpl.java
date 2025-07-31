/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import kotlin.DeepRecursiveFunction;
import kotlin.DeepRecursiveKt;
import kotlin.DeepRecursiveScope;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00020\u0004BK\u00129\u0010\u0005\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u00a2\u0006\u0002\b\b\u0012\u0006\u0010\t\u001a\u00028\u0000\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\u0015\u001a\u00028\u00012\u0006\u0010\t\u001a\u00028\u0000H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016Jc\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000429\u0010\u0018\u001a5\b\u0001\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u00a2\u0006\u0002\b\b2\u000e\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J\u001e\u0010\u001a\u001a\u00020\u001b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00010\u0013H\u0016\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001cJ\u000b\u0010\u001d\u001a\u00028\u0001\u00a2\u0006\u0002\u0010\u001eJ5\u0010\u0015\u001a\u0002H\u001f\"\u0004\b\u0002\u0010 \"\u0004\b\u0003\u0010\u001f*\u000e\u0012\u0004\u0012\u0002H \u0012\u0004\u0012\u0002H\u001f0!2\u0006\u0010\t\u001a\u0002H H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"R\u0018\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fRF\u0010\u0010\u001a5\b\u0001\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u00a2\u0006\u0002\b\bX\u0082\u000e\u00f8\u0001\u0000\u00a2\u0006\u0004\n\u0002\u0010\u0011R\u001e\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0013X\u0082\u000e\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\n\u0002\u0010\u0014R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006#"}, d2={"Lkotlin/DeepRecursiveScopeImpl;", "T", "R", "Lkotlin/DeepRecursiveScope;", "Lkotlin/coroutines/Continuation;", "block", "Lkotlin/Function3;", "", "Lkotlin/ExtensionFunctionType;", "value", "(Lkotlin/jvm/functions/Function3;Ljava/lang/Object;)V", "cont", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "function", "Lkotlin/jvm/functions/Function3;", "result", "Lkotlin/Result;", "Ljava/lang/Object;", "callRecursive", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "crossFunctionCompletion", "currentFunction", "(Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "resumeWith", "", "(Ljava/lang/Object;)V", "runCallLoop", "()Ljava/lang/Object;", "S", "U", "Lkotlin/DeepRecursiveFunction;", "(Lkotlin/DeepRecursiveFunction;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlin-stdlib"})
@ExperimentalStdlibApi
final class DeepRecursiveScopeImpl<T, R>
extends DeepRecursiveScope<T, R>
implements Continuation<R> {
    private Function3<? super DeepRecursiveScope<?, ?>, Object, ? super Continuation<Object>, ? extends Object> function;
    private Object value;
    private Continuation<Object> cont;
    private Object result;

    @Override
    @NotNull
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    @Override
    public void resumeWith(@NotNull Object result) {
        this.cont = null;
        this.result = result;
    }

    @Override
    @Nullable
    public Object callRecursive(T value, @NotNull Continuation<? super R> $completion) {
        Continuation<R> cont = $completion;
        boolean bl = false;
        Continuation<R> continuation = cont;
        if (continuation == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
        }
        this.cont = continuation;
        this.value = value;
        Object object = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended($completion);
        }
        return object;
    }

    @Override
    @Nullable
    public <U, S> Object callRecursive(@NotNull DeepRecursiveFunction<U, S> $this$callRecursive, U value, @NotNull Continuation<? super S> $completion) {
        Continuation<Object> cont = $completion;
        boolean bl = false;
        Function3<DeepRecursiveScope<U, S>, U, Continuation<S>, Object> function3 = $this$callRecursive.getBlock$kotlin_stdlib();
        if (function3 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.DeepRecursiveFunctionBlock /* = suspend kotlin.DeepRecursiveScope<*, *>.(kotlin.Any?) -> kotlin.Any? */");
        }
        Function3<DeepRecursiveScope<U, S>, U, Continuation<S>, Object> function = function3;
        DeepRecursiveScopeImpl deepRecursiveScopeImpl = this;
        boolean bl2 = false;
        boolean bl3 = false;
        DeepRecursiveScopeImpl $this$with = deepRecursiveScopeImpl;
        boolean bl4 = false;
        Function3<? super DeepRecursiveScope<?, ?>, Object, ? super Continuation<Object>, ? extends Object> currentFunction = $this$with.function;
        if (function != currentFunction) {
            $this$with.function = function;
            Continuation<Object> continuation = cont;
            if (continuation == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            }
            $this$with.cont = $this$with.crossFunctionCompletion(currentFunction, continuation);
        } else {
            Continuation<Object> continuation = cont;
            if (continuation == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            }
            $this$with.cont = continuation;
        }
        $this$with.value = value;
        Object object = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended($completion);
        }
        return object;
    }

    private final Continuation<Object> crossFunctionCompletion(Function3<? super DeepRecursiveScope<?, ?>, Object, ? super Continuation<Object>, ? extends Object> currentFunction, Continuation<Object> cont) {
        CoroutineContext coroutineContext = EmptyCoroutineContext.INSTANCE;
        boolean bl = false;
        return new Continuation<Object>(coroutineContext, this, currentFunction, cont){
            final /* synthetic */ CoroutineContext $context;
            final /* synthetic */ DeepRecursiveScopeImpl this$0;
            final /* synthetic */ Function3 $currentFunction$inlined;
            final /* synthetic */ Continuation $cont$inlined;
            {
                this.$context = $captured_local_variable$0;
                this.this$0 = deepRecursiveScopeImpl;
                this.$currentFunction$inlined = function3;
                this.$cont$inlined = continuation;
            }

            @NotNull
            public CoroutineContext getContext() {
                return this.$context;
            }

            public void resumeWith(@NotNull Object result) {
                Object it = result;
                boolean bl = false;
                DeepRecursiveScopeImpl.access$setFunction$p(this.this$0, this.$currentFunction$inlined);
                DeepRecursiveScopeImpl.access$setCont$p(this.this$0, this.$cont$inlined);
                DeepRecursiveScopeImpl.access$setResult$p(this.this$0, it);
            }
        };
    }

    public final R runCallLoop() {
        while (true) {
            Continuation<Object> cont;
            Object result = this.result;
            if (this.cont == null) {
                Object object = result;
                boolean bl = false;
                ResultKt.throwOnFailure(object);
                return (R)object;
            }
            if (Result.equals-impl0(DeepRecursiveKt.access$getUNDEFINED_RESULT$p(), result)) {
                boolean bl;
                Object object;
                Object object2;
                Object object3;
                try {
                    object3 = this.function;
                    object2 = this;
                    object = this.value;
                    bl = false;
                    Function3<? super DeepRecursiveScope<?, ?>, Object, ? super Continuation<Object>, ? extends Object> function3 = object3;
                    if (function3 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type (R, P, kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
                    }
                    object3 = ((Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function3, 3)).invoke(object2, object, cont);
                } catch (Throwable e) {
                    object = cont;
                    bl = false;
                    Result.Companion companion = Result.Companion;
                    boolean bl2 = false;
                    object.resumeWith(Result.constructor-impl(ResultKt.createFailure(e)));
                    continue;
                }
                Object r = object3;
                if (r == IntrinsicsKt.getCOROUTINE_SUSPENDED()) continue;
                object3 = cont;
                object2 = r;
                boolean bl3 = false;
                Result.Companion companion = Result.Companion;
                boolean bl4 = false;
                object3.resumeWith(Result.constructor-impl(object2));
                continue;
            }
            this.result = DeepRecursiveKt.access$getUNDEFINED_RESULT$p();
            cont.resumeWith(result);
        }
    }

    public DeepRecursiveScopeImpl(@NotNull Function3<? super DeepRecursiveScope<T, R>, ? super T, ? super Continuation<? super R>, ? extends Object> block, T value) {
        Intrinsics.checkNotNullParameter(block, "block");
        super(null);
        this.function = block;
        this.value = value;
        DeepRecursiveScopeImpl deepRecursiveScopeImpl = this;
        if (deepRecursiveScopeImpl == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
        }
        this.cont = deepRecursiveScopeImpl;
        this.result = DeepRecursiveKt.access$getUNDEFINED_RESULT$p();
    }

    public static final /* synthetic */ Function3 access$getFunction$p(DeepRecursiveScopeImpl $this) {
        return $this.function;
    }

    public static final /* synthetic */ void access$setFunction$p(DeepRecursiveScopeImpl $this, Function3 function3) {
        $this.function = function3;
    }

    public static final /* synthetic */ Continuation access$getCont$p(DeepRecursiveScopeImpl $this) {
        return $this.cont;
    }

    public static final /* synthetic */ void access$setCont$p(DeepRecursiveScopeImpl $this, Continuation continuation) {
        $this.cont = continuation;
    }

    public static final /* synthetic */ Object access$getResult$p-d1pmJ48(DeepRecursiveScopeImpl $this) {
        return $this.result;
    }

    public static final /* synthetic */ void access$setResult$p(DeepRecursiveScopeImpl $this, Object object) {
        $this.result = object;
    }
}

