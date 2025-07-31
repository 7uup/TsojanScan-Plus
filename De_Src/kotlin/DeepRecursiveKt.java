/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import kotlin.DeepRecursiveFunction;
import kotlin.DeepRecursiveScopeImpl;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.SinceKotlin;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a2\u0010\u0004\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00050\u00072\u0006\u0010\b\u001a\u0002H\u0006H\u0087\u0002\u00a2\u0006\u0002\u0010\t\"\u0019\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\n\u0002\u0010\u0003*v\b\u0003\u0010\n\"5\b\u0001\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000b\u00a2\u0006\u0002\b\u000e25\b\u0001\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000b\u00a2\u0006\u0002\b\u000eB\u0002\b\u000f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2={"UNDEFINED_RESULT", "Lkotlin/Result;", "", "Ljava/lang/Object;", "invoke", "R", "T", "Lkotlin/DeepRecursiveFunction;", "value", "(Lkotlin/DeepRecursiveFunction;Ljava/lang/Object;)Ljava/lang/Object;", "DeepRecursiveFunctionBlock", "Lkotlin/Function3;", "Lkotlin/DeepRecursiveScope;", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "Lkotlin/ExperimentalStdlibApi;", "kotlin-stdlib"})
public final class DeepRecursiveKt {
    private static final Object UNDEFINED_RESULT;

    @SinceKotlin(version="1.4")
    @ExperimentalStdlibApi
    public static final <T, R> R invoke(@NotNull DeepRecursiveFunction<T, R> $this$invoke, T value) {
        Intrinsics.checkNotNullParameter($this$invoke, "$this$invoke");
        return new DeepRecursiveScopeImpl<T, R>($this$invoke.getBlock$kotlin_stdlib(), value).runCallLoop();
    }

    @ExperimentalStdlibApi
    private static /* synthetic */ void DeepRecursiveFunctionBlock$annotations() {
    }

    static {
        Result.Companion companion = Result.Companion;
        Object object = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        boolean bl = false;
        UNDEFINED_RESULT = Result.constructor-impl(object);
    }

    public static final /* synthetic */ Object access$getUNDEFINED_RESULT$p() {
        return UNDEFINED_RESULT;
    }
}

