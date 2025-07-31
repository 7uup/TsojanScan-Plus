/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm.functions;

import kotlin.Function;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.FunctionBase;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\bg\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003J&\u0010\b\u001a\u00028\u00002\u0016\u0010\t\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u000b0\n\"\u0004\u0018\u00010\u000bH\u00a6\u0002\u00a2\u0006\u0002\u0010\fR\u0012\u0010\u0004\u001a\u00020\u0005X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\r"}, d2={"Lkotlin/jvm/functions/FunctionN;", "R", "Lkotlin/Function;", "Lkotlin/jvm/internal/FunctionBase;", "arity", "", "getArity", "()I", "invoke", "args", "", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-stdlib"})
@SinceKotlin(version="1.3")
public interface FunctionN<R>
extends Function<R>,
FunctionBase<R> {
    public R invoke(@NotNull Object ... var1);

    @Override
    public int getArity();
}

