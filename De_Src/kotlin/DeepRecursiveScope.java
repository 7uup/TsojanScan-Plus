/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import kotlin.DeepRecursiveFunction;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.RestrictsSuspension;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00028\u00012\u0006\u0010\u0006\u001a\u00028\u0000H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J5\u0010\u0005\u001a\u0002H\b\"\u0004\b\u0002\u0010\t\"\u0004\b\u0003\u0010\b*\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\b0\n2\u0006\u0010\u0006\u001a\u0002H\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u001f\u0010\f\u001a\u00020\r*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\n2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003H\u0087\u0002\u0082\u0001\u0001\u000e\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2={"Lkotlin/DeepRecursiveScope;", "T", "R", "", "()V", "callRecursive", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "S", "U", "Lkotlin/DeepRecursiveFunction;", "(Lkotlin/DeepRecursiveFunction;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invoke", "", "Lkotlin/DeepRecursiveScopeImpl;", "kotlin-stdlib"})
@RestrictsSuspension
@SinceKotlin(version="1.4")
@ExperimentalStdlibApi
public abstract class DeepRecursiveScope<T, R> {
    @Nullable
    public abstract Object callRecursive(T var1, @NotNull Continuation<? super R> var2);

    @Nullable
    public abstract <U, S> Object callRecursive(@NotNull DeepRecursiveFunction<U, S> var1, U var2, @NotNull Continuation<? super S> var3);

    @Deprecated(level=DeprecationLevel.ERROR, message="'invoke' should not be called from DeepRecursiveScope. Use 'callRecursive' to do recursion in the heap instead of the call stack.", replaceWith=@ReplaceWith(imports={}, expression="this.callRecursive(value)"))
    @NotNull
    public final Void invoke(@NotNull DeepRecursiveFunction<?, ?> $this$invoke, @Nullable Object value) {
        Intrinsics.checkNotNullParameter($this$invoke, "$this$invoke");
        throw (Throwable)new UnsupportedOperationException("Should not be called from DeepRecursiveScope");
    }

    private DeepRecursiveScope() {
    }

    public /* synthetic */ DeepRecursiveScope(DefaultConstructorMarker $constructor_marker) {
        this();
    }
}

