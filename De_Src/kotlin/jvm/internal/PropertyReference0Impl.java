/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.PropertyReference0;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;

public class PropertyReference0Impl
extends PropertyReference0 {
    public PropertyReference0Impl(KDeclarationContainer owner, String name, String signature) {
        super(NO_RECEIVER, ((ClassBasedDeclarationContainer)owner).getJClass(), name, signature, owner instanceof KClass ? 0 : 1);
    }

    @SinceKotlin(version="1.4")
    public PropertyReference0Impl(Class owner, String name, String signature, int flags) {
        super(NO_RECEIVER, owner, name, signature, flags);
    }

    @SinceKotlin(version="1.4")
    public PropertyReference0Impl(Object receiver, Class owner, String name, String signature, int flags) {
        super(receiver, owner, name, signature, flags);
    }

    public Object get() {
        return this.getGetter().call(new Object[0]);
    }
}

