/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.MutablePropertyReference2;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;

public class MutablePropertyReference2Impl
extends MutablePropertyReference2 {
    public MutablePropertyReference2Impl(KDeclarationContainer owner, String name, String signature) {
        super(((ClassBasedDeclarationContainer)owner).getJClass(), name, signature, owner instanceof KClass ? 0 : 1);
    }

    @SinceKotlin(version="1.4")
    public MutablePropertyReference2Impl(Class owner, String name, String signature, int flags) {
        super(owner, name, signature, flags);
    }

    @Override
    public Object get(Object receiver1, Object receiver2) {
        return this.getGetter().call(receiver1, receiver2);
    }

    public void set(Object receiver1, Object receiver2, Object value) {
        this.getSetter().call(receiver1, receiver2, value);
    }
}

