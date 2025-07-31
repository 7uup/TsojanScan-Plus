/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.coroutines.jvm.internal;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.RestrictedContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.internal.FunctionBase;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b!\u0018\u00002\u00020\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u00020\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007B\u001f\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0010\u0010\b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\t\u00a2\u0006\u0002\u0010\nJ\b\u0010\r\u001a\u00020\u000eH\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u000f"}, d2={"Lkotlin/coroutines/jvm/internal/RestrictedSuspendLambda;", "Lkotlin/coroutines/jvm/internal/RestrictedContinuationImpl;", "Lkotlin/jvm/internal/FunctionBase;", "", "Lkotlin/coroutines/jvm/internal/SuspendFunction;", "arity", "", "(I)V", "completion", "Lkotlin/coroutines/Continuation;", "(ILkotlin/coroutines/Continuation;)V", "getArity", "()I", "toString", "", "kotlin-stdlib"})
@SinceKotlin(version="1.3")
public abstract class RestrictedSuspendLambda
extends RestrictedContinuationImpl
implements FunctionBase<Object>,
SuspendFunction {
    private final int arity;

    @Override
    @NotNull
    public String toString() {
        String string;
        if (this.getCompletion() == null) {
            String string2 = Reflection.renderLambdaToString(this);
            string = string2;
            Intrinsics.checkNotNullExpressionValue(string2, "Reflection.renderLambdaToString(this)");
        } else {
            string = super.toString();
        }
        return string;
    }

    @Override
    public int getArity() {
        return this.arity;
    }

    public RestrictedSuspendLambda(int arity, @Nullable Continuation<Object> completion) {
        super(completion);
        this.arity = arity;
    }

    public RestrictedSuspendLambda(int arity) {
        this(arity, null);
    }
}

