/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.gson.internal.reflect;

import com.google.gson.internal.reflect.ReflectionAccessor;
import java.lang.reflect.AccessibleObject;

final class PreJava9ReflectionAccessor
extends ReflectionAccessor {
    PreJava9ReflectionAccessor() {
    }

    @Override
    public void makeAccessible(AccessibleObject ao) {
        ao.setAccessible(true);
    }
}

