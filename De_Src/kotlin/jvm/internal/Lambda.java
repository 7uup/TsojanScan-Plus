/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm.internal;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.FunctionBase;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b&\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u000b"}, d2={"Lkotlin/jvm/internal/Lambda;", "R", "Lkotlin/jvm/internal/FunctionBase;", "Ljava/io/Serializable;", "arity", "", "(I)V", "getArity", "()I", "toString", "", "kotlin-stdlib"})
public abstract class Lambda<R>
implements FunctionBase<R>,
Serializable {
    private final int arity;

    @NotNull
    public String toString() {
        String string = Reflection.renderLambdaToString(this);
        Intrinsics.checkNotNullExpressionValue(string, "Reflection.renderLambdaToString(this)");
        return string;
    }

    @Override
    public int getArity() {
        return this.arity;
    }

    public Lambda(int arity) {
        this.arity = arity;
    }
}

