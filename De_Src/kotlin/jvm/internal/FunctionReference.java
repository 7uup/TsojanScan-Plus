/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionBase;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KCallable;
import kotlin.reflect.KFunction;

public class FunctionReference
extends CallableReference
implements FunctionBase,
KFunction {
    private final int arity;
    @SinceKotlin(version="1.4")
    private final int flags;

    public FunctionReference(int arity) {
        this(arity, NO_RECEIVER, null, null, null, 0);
    }

    @SinceKotlin(version="1.1")
    public FunctionReference(int arity, Object receiver) {
        this(arity, receiver, null, null, null, 0);
    }

    @SinceKotlin(version="1.4")
    public FunctionReference(int arity, Object receiver, Class owner, String name, String signature, int flags) {
        super(receiver, owner, name, signature, (flags & 1) == 1);
        this.arity = arity;
        this.flags = flags >> 1;
    }

    @Override
    public int getArity() {
        return this.arity;
    }

    @Override
    @SinceKotlin(version="1.1")
    protected KFunction getReflected() {
        return (KFunction)super.getReflected();
    }

    @Override
    @SinceKotlin(version="1.1")
    protected KCallable computeReflected() {
        return Reflection.function(this);
    }

    @Override
    @SinceKotlin(version="1.1")
    public boolean isInline() {
        return this.getReflected().isInline();
    }

    @Override
    @SinceKotlin(version="1.1")
    public boolean isExternal() {
        return this.getReflected().isExternal();
    }

    @Override
    @SinceKotlin(version="1.1")
    public boolean isOperator() {
        return this.getReflected().isOperator();
    }

    @Override
    @SinceKotlin(version="1.1")
    public boolean isInfix() {
        return this.getReflected().isInfix();
    }

    @Override
    @SinceKotlin(version="1.1")
    public boolean isSuspend() {
        return this.getReflected().isSuspend();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FunctionReference) {
            FunctionReference other = (FunctionReference)obj;
            return Intrinsics.areEqual(this.getOwner(), other.getOwner()) && this.getName().equals(other.getName()) && this.getSignature().equals(other.getSignature()) && this.flags == other.flags && this.arity == other.arity && Intrinsics.areEqual(this.getBoundReceiver(), other.getBoundReceiver());
        }
        if (obj instanceof KFunction) {
            return obj.equals(this.compute());
        }
        return false;
    }

    public int hashCode() {
        return ((this.getOwner() == null ? 0 : this.getOwner().hashCode() * 31) + this.getName().hashCode()) * 31 + this.getSignature().hashCode();
    }

    public String toString() {
        KCallable reflected = this.compute();
        if (reflected != this) {
            return reflected.toString();
        }
        return "<init>".equals(this.getName()) ? "constructor (Kotlin reflection is not available)" : "function " + this.getName() + " (Kotlin reflection is not available)";
    }
}

