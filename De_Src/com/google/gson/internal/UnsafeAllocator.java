/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.gson.internal;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public abstract class UnsafeAllocator {
    public abstract <T> T newInstance(Class<T> var1) throws Exception;

    public static UnsafeAllocator create() {
        try {
            Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
            Field f = unsafeClass.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            final Object unsafe = f.get(null);
            final Method allocateInstance = unsafeClass.getMethod("allocateInstance", Class.class);
            return new UnsafeAllocator(){

                @Override
                public <T> T newInstance(Class<T> c) throws Exception {
                    1.assertInstantiable(c);
                    return (T)allocateInstance.invoke(unsafe, c);
                }
            };
        } catch (Exception unsafeClass) {
            try {
                Method getConstructorId = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                getConstructorId.setAccessible(true);
                final int constructorId = (Integer)getConstructorId.invoke(null, Object.class);
                final Method newInstance = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                newInstance.setAccessible(true);
                return new UnsafeAllocator(){

                    @Override
                    public <T> T newInstance(Class<T> c) throws Exception {
                        2.assertInstantiable(c);
                        return (T)newInstance.invoke(null, c, constructorId);
                    }
                };
            } catch (Exception getConstructorId) {
                try {
                    final Method newInstance = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
                    newInstance.setAccessible(true);
                    return new UnsafeAllocator(){

                        @Override
                        public <T> T newInstance(Class<T> c) throws Exception {
                            3.assertInstantiable(c);
                            return (T)newInstance.invoke(null, c, Object.class);
                        }
                    };
                } catch (Exception exception) {
                    return new UnsafeAllocator(){

                        @Override
                        public <T> T newInstance(Class<T> c) {
                            throw new UnsupportedOperationException("Cannot allocate " + c);
                        }
                    };
                }
            }
        }
    }

    static void assertInstantiable(Class<?> c) {
        int modifiers = c.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            throw new UnsupportedOperationException("Interface can't be instantiated! Interface name: " + c.getName());
        }
        if (Modifier.isAbstract(modifiers)) {
            throw new UnsupportedOperationException("Abstract class can't be instantiated! Class name: " + c.getName());
        }
    }
}

