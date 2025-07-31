/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.gson;

import java.lang.reflect.Type;

public interface InstanceCreator<T> {
    public T createInstance(Type var1);
}

