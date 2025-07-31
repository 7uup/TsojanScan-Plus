/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.jvm.internal.PropertyReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty0;

public abstract class PropertyReference0
extends PropertyReference
implements KProperty0 {
    public PropertyReference0() {
    }

    @SinceKotlin(version="1.1")
    public PropertyReference0(Object receiver) {
        super(receiver);
    }

    @SinceKotlin(version="1.4")
    public PropertyReference0(Object receiver, Class owner, String name, String signature, int flags) {
        super(receiver, owner, name, signature, flags);
    }

    @Override
    protected KCallable computeReflected() {
        return Reflection.property0(this);
    }

    @Override
    public Object invoke() {
        return this.get();
    }

    public KProperty0.Getter getGetter() {
        return ((KProperty0)this.getReflected()).getGetter();
    }

    @Override
    @SinceKotlin(version="1.1")
    public Object getDelegate() {
        return ((KProperty0)this.getReflected()).getDelegate();
    }
}

