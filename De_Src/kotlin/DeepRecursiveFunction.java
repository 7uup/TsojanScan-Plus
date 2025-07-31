/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import kotlin.DeepRecursiveScope;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003BC\u00129\u0010\u0004\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0005\u00a2\u0006\u0002\b\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tRL\u0010\u0004\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0005\u00a2\u0006\u0002\b\bX\u0080\u0004\u00f8\u0001\u0000\u00a2\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\r"}, d2={"Lkotlin/DeepRecursiveFunction;", "T", "R", "", "block", "Lkotlin/Function3;", "Lkotlin/DeepRecursiveScope;", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function3;)V", "getBlock$kotlin_stdlib", "()Lkotlin/jvm/functions/Function3;", "Lkotlin/jvm/functions/Function3;", "kotlin-stdlib"})
@SinceKotlin(version="1.4")
@ExperimentalStdlibApi
public final class DeepRecursiveFunction<T, R> {
    @NotNull
    private final Function3<DeepRecursiveScope<T, R>, T, Continuation<? super R>, Object> block;

    @NotNull
    public final Function3<DeepRecursiveScope<T, R>, T, Continuation<? super R>, Object> getBlock$kotlin_stdlib() {
        return this.block;
    }

    public DeepRecursiveFunction(@NotNull Function3<? super DeepRecursiveScope<T, R>, ? super T, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.block = block;
    }
}

