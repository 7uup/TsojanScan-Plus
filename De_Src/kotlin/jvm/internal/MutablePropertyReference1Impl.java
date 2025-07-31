/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.MutablePropertyReference1;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;

public class MutablePropertyReference1Impl
extends MutablePropertyReference1 {
    public MutablePropertyReference1Impl(KDeclarationContainer owner, String name, String signature) {
        super(NO_RECEIVER, ((ClassBasedDeclarationContainer)owner).getJClass(), name, signature, owner instanceof KClass ? 0 : 1);
    }

    @SinceKotlin(version="1.4")
    public MutablePropertyReference1Impl(Class owner, String name, String signature, int flags) {
        super(NO_RECEIVER, owner, name, signature, flags);
    }

    @SinceKotlin(version="1.4")
    public MutablePropertyReference1Impl(Object receiver, Class owner, String name, String signature, int flags) {
        super(receiver, owner, name, signature, flags);
    }

    @Override
    public Object get(Object receiver) {
        return this.getGetter().call(receiver);
    }

    public void set(Object receiver, Object value) {
        this.getSetter().call(receiver, value);
    }
}

