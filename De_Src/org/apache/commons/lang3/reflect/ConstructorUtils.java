/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.reflect.MemberUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

public class ConstructorUtils {
    public static <T> T invokeConstructor(Class<T> cls, Object ... args2) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        args2 = ArrayUtils.nullToEmpty(args2);
        Class<?>[] parameterTypes = ClassUtils.toClass(args2);
        return ConstructorUtils.invokeConstructor(cls, args2, parameterTypes);
    }

    public static <T> T invokeConstructor(Class<T> cls, Object[] args2, Class<?>[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        args2 = ArrayUtils.nullToEmpty(args2);
        Constructor<T> ctor = ConstructorUtils.getMatchingAccessibleConstructor(cls, parameterTypes = ArrayUtils.nullToEmpty(parameterTypes));
        if (ctor == null) {
            throw new NoSuchMethodException("No such accessible constructor on object: " + cls.getName());
        }
        if (ctor.isVarArgs()) {
            Class<?>[] methodParameterTypes = ctor.getParameterTypes();
            args2 = MethodUtils.getVarArgs(args2, methodParameterTypes);
        }
        return ctor.newInstance(args2);
    }

    public static <T> T invokeExactConstructor(Class<T> cls, Object ... args2) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        args2 = ArrayUtils.nullToEmpty(args2);
        Class<?>[] parameterTypes = ClassUtils.toClass(args2);
        return ConstructorUtils.invokeExactConstructor(cls, args2, parameterTypes);
    }

    public static <T> T invokeExactConstructor(Class<T> cls, Object[] args2, Class<?>[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        args2 = ArrayUtils.nullToEmpty(args2);
        Constructor<T> ctor = ConstructorUtils.getAccessibleConstructor(cls, parameterTypes = ArrayUtils.nullToEmpty(parameterTypes));
        if (ctor == null) {
            throw new NoSuchMethodException("No such accessible constructor on object: " + cls.getName());
        }
        return ctor.newInstance(args2);
    }

    public static <T> Constructor<T> getAccessibleConstructor(Class<T> cls, Class<?> ... parameterTypes) {
        Validate.notNull(cls, "class cannot be null", new Object[0]);
        try {
            return ConstructorUtils.getAccessibleConstructor(cls.getConstructor(parameterTypes));
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static <T> Constructor<T> getAccessibleConstructor(Constructor<T> ctor) {
        Validate.notNull(ctor, "constructor cannot be null", new Object[0]);
        return MemberUtils.isAccessible(ctor) && ConstructorUtils.isAccessible(ctor.getDeclaringClass()) ? ctor : null;
    }

    public static <T> Constructor<T> getMatchingAccessibleConstructor(Class<T> cls, Class<?> ... parameterTypes) {
        Validate.notNull(cls, "class cannot be null", new Object[0]);
        try {
            Constructor<T> ctor = cls.getConstructor(parameterTypes);
            MemberUtils.setAccessibleWorkaround(ctor);
            return ctor;
        } catch (NoSuchMethodException ctor) {
            Constructor<?>[] ctors;
            Constructor<?> result = null;
            for (Constructor<?> ctor2 : ctors = cls.getConstructors()) {
                Constructor<?> constructor;
                if (!MemberUtils.isMatchingConstructor(ctor2, parameterTypes) || (ctor2 = ConstructorUtils.getAccessibleConstructor(ctor2)) == null) continue;
                MemberUtils.setAccessibleWorkaround(ctor2);
                if (result != null && MemberUtils.compareConstructorFit(ctor2, result, parameterTypes) >= 0) continue;
                result = constructor = ctor2;
            }
            return result;
        }
    }

    private static boolean isAccessible(Class<?> type) {
        for (Class<?> cls = type; cls != null; cls = cls.getEnclosingClass()) {
            if (Modifier.isPublic(cls.getModifiers())) continue;
            return false;
        }
        return true;
    }
}

