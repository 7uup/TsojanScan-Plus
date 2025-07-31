/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.jvm.internal.PropertyReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty2;

public abstract class PropertyReference2
extends PropertyReference
implements KProperty2 {
    public PropertyReference2() {
    }

    @SinceKotlin(version="1.4")
    public PropertyReference2(Class owner, String name, String signature, int flags) {
        super(NO_RECEIVER, owner, name, signature, flags);
    }

    @Override
    protected KCallable computeReflected() {
        return Reflection.property2(this);
    }

    @Override
    public Object invoke(Object receiver1, Object receiver2) {
        return this.get(receiver1, receiver2);
    }

    public KProperty2.Getter getGetter() {
        return ((KProperty2)this.getReflected()).getGetter();
    }

    @SinceKotlin(version="1.1")
    public Object getDelegate(Object receiver1, Object receiver2) {
        return ((KProperty2)this.getReflected()).getDelegate(receiver1, receiver2);
    }
}

