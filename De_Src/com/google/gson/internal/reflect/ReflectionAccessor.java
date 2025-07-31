/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.gson.internal.reflect;

import com.google.gson.internal.JavaVersion;
import com.google.gson.internal.reflect.PreJava9ReflectionAccessor;
import com.google.gson.internal.reflect.UnsafeReflectionAccessor;
import java.lang.reflect.AccessibleObject;

public abstract class ReflectionAccessor {
    private static final ReflectionAccessor instance = JavaVersion.getMajorJavaVersion() < 9 ? new PreJava9ReflectionAccessor() : new UnsafeReflectionAccessor();

    public abstract void makeAccessible(AccessibleObject var1);

    public static ReflectionAccessor getInstance() {
        return instance;
    }
}

