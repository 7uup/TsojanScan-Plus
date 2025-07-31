/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.gson.internal.reflect;

import com.google.gson.JsonIOException;
import com.google.gson.internal.reflect.ReflectionAccessor;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

final class UnsafeReflectionAccessor
extends ReflectionAccessor {
    private static Class unsafeClass;
    private final Object theUnsafe = UnsafeReflectionAccessor.getUnsafeInstance();
    private final Field overrideField = UnsafeReflectionAccessor.getOverrideField();

    UnsafeReflectionAccessor() {
    }

    @Override
    public void makeAccessible(AccessibleObject ao) {
        boolean success = this.makeAccessibleWithUnsafe(ao);
        if (!success) {
            try {
                ao.setAccessible(true);
            } catch (SecurityException e) {
                throw new JsonIOException("Gson couldn't modify fields for " + ao + "\nand sun.misc.Unsafe not found.\nEither write a custom type adapter, or make fields accessible, or include sun.misc.Unsafe.", e);
            }
        }
    }

    boolean makeAccessibleWithUnsafe(AccessibleObject ao) {
        if (this.theUnsafe != null && this.overrideField != null) {
            try {
                Method method = unsafeClass.getMethod("objectFieldOffset", Field.class);
                long overrideOffset = (Long)method.invoke(this.theUnsafe, this.overrideField);
                Method putBooleanMethod = unsafeClass.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE);
                putBooleanMethod.invoke(this.theUnsafe, ao, overrideOffset, true);
                return true;
            } catch (Exception exception) {
                // empty catch block
            }
        }
        return false;
    }

    private static Object getUnsafeInstance() {
        try {
            unsafeClass = Class.forName("sun.misc.Unsafe");
            Field unsafeField = unsafeClass.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            return unsafeField.get(null);
        } catch (Exception e) {
            return null;
        }
    }

    private static Field getOverrideField() {
        try {
            return AccessibleObject.class.getDeclaredField("override");
        } catch (NoSuchFieldException e) {
            return null;
        }
    }
}

