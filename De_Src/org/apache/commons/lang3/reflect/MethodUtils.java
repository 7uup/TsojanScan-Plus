/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.reflect.MemberUtils;
import org.apache.commons.lang3.reflect.TypeUtils;

public class MethodUtils {
    public static Object invokeMethod(Object object, String methodName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return MethodUtils.invokeMethod(object, methodName, ArrayUtils.EMPTY_OBJECT_ARRAY, null);
    }

    public static Object invokeMethod(Object object, boolean forceAccess, String methodName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return MethodUtils.invokeMethod(object, forceAccess, methodName, ArrayUtils.EMPTY_OBJECT_ARRAY, null);
    }

    public static Object invokeMethod(Object object, String methodName, Object ... args2) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        args2 = ArrayUtils.nullToEmpty(args2);
        Class<?>[] parameterTypes = ClassUtils.toClass(args2);
        return MethodUtils.invokeMethod(object, methodName, args2, parameterTypes);
    }

    public static Object invokeMethod(Object object, boolean forceAccess, String methodName, Object ... args2) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        args2 = ArrayUtils.nullToEmpty(args2);
        Class<?>[] parameterTypes = ClassUtils.toClass(args2);
        return MethodUtils.invokeMethod(object, forceAccess, methodName, args2, parameterTypes);
    }

    public static Object invokeMethod(Object object, boolean forceAccess, String methodName, Object[] args2, Class<?>[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String messagePrefix;
        parameterTypes = ArrayUtils.nullToEmpty(parameterTypes);
        args2 = ArrayUtils.nullToEmpty(args2);
        Method method = null;
        if (forceAccess) {
            messagePrefix = "No such method: ";
            method = MethodUtils.getMatchingMethod(object.getClass(), methodName, parameterTypes);
            if (method != null && !method.isAccessible()) {
                method.setAccessible(true);
            }
        } else {
            messagePrefix = "No such accessible method: ";
            method = MethodUtils.getMatchingAccessibleMethod(object.getClass(), methodName, parameterTypes);
        }
        if (method == null) {
            throw new NoSuchMethodException(messagePrefix + methodName + "() on object: " + object.getClass().getName());
        }
        args2 = MethodUtils.toVarArgs(method, args2);
        return method.invoke(object, args2);
    }

    public static Object invokeMethod(Object object, String methodName, Object[] args2, Class<?>[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return MethodUtils.invokeMethod(object, false, methodName, args2, parameterTypes);
    }

    public static Object invokeExactMethod(Object object, String methodName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return MethodUtils.invokeExactMethod(object, methodName, ArrayUtils.EMPTY_OBJECT_ARRAY, null);
    }

    public static Object invokeExactMethod(Object object, String methodName, Object ... args2) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        args2 = ArrayUtils.nullToEmpty(args2);
        Class<?>[] parameterTypes = ClassUtils.toClass(args2);
        return MethodUtils.invokeExactMethod(object, methodName, args2, parameterTypes);
    }

    public static Object invokeExactMethod(Object object, String methodName, Object[] args2, Class<?>[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        args2 = ArrayUtils.nullToEmpty(args2);
        parameterTypes = ArrayUtils.nullToEmpty(parameterTypes);
        Method method = MethodUtils.getAccessibleMethod(object.getClass(), methodName, parameterTypes);
        if (method == null) {
            throw new NoSuchMethodException("No such accessible method: " + methodName + "() on object: " + object.getClass().getName());
        }
        return method.invoke(object, args2);
    }

    public static Object invokeExactStaticMethod(Class<?> cls, String methodName, Object[] args2, Class<?>[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        args2 = ArrayUtils.nullToEmpty(args2);
        Method method = MethodUtils.getAccessibleMethod(cls, methodName, parameterTypes = ArrayUtils.nullToEmpty(parameterTypes));
        if (method == null) {
            throw new NoSuchMethodException("No such accessible method: " + methodName + "() on class: " + cls.getName());
        }
        return method.invoke(null, args2);
    }

    public static Object invokeStaticMethod(Class<?> cls, String methodName, Object ... args2) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        args2 = ArrayUtils.nullToEmpty(args2);
        Class<?>[] parameterTypes = ClassUtils.toClass(args2);
        return MethodUtils.invokeStaticMethod(cls, methodName, args2, parameterTypes);
    }

    public static Object invokeStaticMethod(Class<?> cls, String methodName, Object[] args2, Class<?>[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        args2 = ArrayUtils.nullToEmpty(args2);
        Method method = MethodUtils.getMatchingAccessibleMethod(cls, methodName, parameterTypes = ArrayUtils.nullToEmpty(parameterTypes));
        if (method == null) {
            throw new NoSuchMethodException("No such accessible method: " + methodName + "() on class: " + cls.getName());
        }
        args2 = MethodUtils.toVarArgs(method, args2);
        return method.invoke(null, args2);
    }

    private static Object[] toVarArgs(Method method, Object[] args2) {
        if (method.isVarArgs()) {
            Class<?>[] methodParameterTypes = method.getParameterTypes();
            args2 = MethodUtils.getVarArgs(args2, methodParameterTypes);
        }
        return args2;
    }

    static Object[] getVarArgs(Object[] args2, Class<?>[] methodParameterTypes) {
        if (args2.length == methodParameterTypes.length && args2[args2.length - 1].getClass().equals(methodParameterTypes[methodParameterTypes.length - 1])) {
            return args2;
        }
        Object[] newArgs = new Object[methodParameterTypes.length];
        System.arraycopy(args2, 0, newArgs, 0, methodParameterTypes.length - 1);
        Class<?> varArgComponentType = methodParameterTypes[methodParameterTypes.length - 1].getComponentType();
        int varArgLength = args2.length - methodParameterTypes.length + 1;
        Object varArgsArray = Array.newInstance(ClassUtils.primitiveToWrapper(varArgComponentType), varArgLength);
        System.arraycopy(args2, methodParameterTypes.length - 1, varArgsArray, 0, varArgLength);
        if (varArgComponentType.isPrimitive()) {
            varArgsArray = ArrayUtils.toPrimitive(varArgsArray);
        }
        newArgs[methodParameterTypes.length - 1] = varArgsArray;
        return newArgs;
    }

    public static Object invokeExactStaticMethod(Class<?> cls, String methodName, Object ... args2) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        args2 = ArrayUtils.nullToEmpty(args2);
        Class<?>[] parameterTypes = ClassUtils.toClass(args2);
        return MethodUtils.invokeExactStaticMethod(cls, methodName, args2, parameterTypes);
    }

    public static Method getAccessibleMethod(Class<?> cls, String methodName, Class<?> ... parameterTypes) {
        try {
            return MethodUtils.getAccessibleMethod(cls.getMethod(methodName, parameterTypes));
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static Method getAccessibleMethod(Method method) {
        Class<?>[] parameterTypes;
        if (!MemberUtils.isAccessible(method)) {
            return null;
        }
        Class<?> cls = method.getDeclaringClass();
        if (Modifier.isPublic(cls.getModifiers())) {
            return method;
        }
        String methodName = method.getName();
        if ((method = MethodUtils.getAccessibleMethodFromInterfaceNest(cls, methodName, parameterTypes = method.getParameterTypes())) == null) {
            method = MethodUtils.getAccessibleMethodFromSuperclass(cls, methodName, parameterTypes);
        }
        return method;
    }

    private static Method getAccessibleMethodFromSuperclass(Class<?> cls, String methodName, Class<?> ... parameterTypes) {
        for (Class<?> parentClass = cls.getSuperclass(); parentClass != null; parentClass = parentClass.getSuperclass()) {
            if (!Modifier.isPublic(parentClass.getModifiers())) continue;
            try {
                return parentClass.getMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException e) {
                return null;
            }
        }
        return null;
    }

    private static Method getAccessibleMethodFromInterfaceNest(Class<?> cls, String methodName, Class<?> ... parameterTypes) {
        while (cls != null) {
            Class<?>[] interfaces;
            for (Class<?> anInterface : interfaces = cls.getInterfaces()) {
                if (!Modifier.isPublic(anInterface.getModifiers())) continue;
                try {
                    return anInterface.getDeclaredMethod(methodName, parameterTypes);
                } catch (NoSuchMethodException noSuchMethodException) {
                    Method method = MethodUtils.getAccessibleMethodFromInterfaceNest(anInterface, methodName, parameterTypes);
                    if (method == null) continue;
                    return method;
                }
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    public static Method getMatchingAccessibleMethod(Class<?> cls, String methodName, Class<?> ... parameterTypes) {
        try {
            Method method = cls.getMethod(methodName, parameterTypes);
            MemberUtils.setAccessibleWorkaround(method);
            return method;
        } catch (NoSuchMethodException method) {
            Method[] methods;
            Method bestMatch = null;
            for (Method method2 : methods = cls.getMethods()) {
                Method accessibleMethod;
                if (!method2.getName().equals(methodName) || !MemberUtils.isMatchingMethod(method2, parameterTypes) || (accessibleMethod = MethodUtils.getAccessibleMethod(method2)) == null || bestMatch != null && MemberUtils.compareMethodFit(accessibleMethod, bestMatch, parameterTypes) >= 0) continue;
                bestMatch = accessibleMethod;
            }
            if (bestMatch != null) {
                MemberUtils.setAccessibleWorkaround(bestMatch);
            }
            if (bestMatch != null && bestMatch.isVarArgs() && bestMatch.getParameterTypes().length > 0 && parameterTypes.length > 0) {
                Class<?>[] methodParameterTypes = bestMatch.getParameterTypes();
                Class<?> methodParameterComponentType = methodParameterTypes[methodParameterTypes.length - 1].getComponentType();
                String methodParameterComponentTypeName = ClassUtils.primitiveToWrapper(methodParameterComponentType).getName();
                String parameterTypeName = parameterTypes[parameterTypes.length - 1].getName();
                String parameterTypeSuperClassName = parameterTypes[parameterTypes.length - 1].getSuperclass().getName();
                if (!methodParameterComponentTypeName.equals(parameterTypeName) && !methodParameterComponentTypeName.equals(parameterTypeSuperClassName)) {
                    return null;
                }
            }
            return bestMatch;
        }
    }

    public static Method getMatchingMethod(Class<?> cls, String methodName, Class<?> ... parameterTypes) {
        Validate.notNull(cls, "Null class not allowed.", new Object[0]);
        Validate.notEmpty(methodName, "Null or blank methodName not allowed.", new Object[0]);
        Method[] methodArray = cls.getDeclaredMethods();
        List<Class<?>> superclassList = ClassUtils.getAllSuperclasses(cls);
        for (Class<?> klass : superclassList) {
            methodArray = ArrayUtils.addAll(methodArray, klass.getDeclaredMethods());
        }
        Method inexactMatch = null;
        for (Method method : methodArray) {
            if (methodName.equals(method.getName()) && Objects.deepEquals(parameterTypes, method.getParameterTypes())) {
                return method;
            }
            if (!methodName.equals(method.getName()) || !ClassUtils.isAssignable(parameterTypes, method.getParameterTypes(), true)) continue;
            if (inexactMatch == null) {
                inexactMatch = method;
                continue;
            }
            if (MethodUtils.distance(parameterTypes, method.getParameterTypes()) >= MethodUtils.distance(parameterTypes, inexactMatch.getParameterTypes())) continue;
            inexactMatch = method;
        }
        return inexactMatch;
    }

    private static int distance(Class<?>[] classArray, Class<?>[] toClassArray) {
        int answer = 0;
        if (!ClassUtils.isAssignable(classArray, toClassArray, true)) {
            return -1;
        }
        for (int offset = 0; offset < classArray.length; ++offset) {
            if (classArray[offset].equals(toClassArray[offset])) continue;
            if (ClassUtils.isAssignable(classArray[offset], toClassArray[offset], true) && !ClassUtils.isAssignable(classArray[offset], toClassArray[offset], false)) {
                ++answer;
                continue;
            }
            answer += 2;
        }
        return answer;
    }

    public static Set<Method> getOverrideHierarchy(Method method, ClassUtils.Interfaces interfacesBehavior) {
        Validate.notNull(method);
        LinkedHashSet<Method> result = new LinkedHashSet<Method>();
        result.add(method);
        Object[] parameterTypes = method.getParameterTypes();
        Class<?> declaringClass = method.getDeclaringClass();
        Iterator<Class<?>> hierarchy = ClassUtils.hierarchy(declaringClass, interfacesBehavior).iterator();
        hierarchy.next();
        block0: while (hierarchy.hasNext()) {
            Class<?> c = hierarchy.next();
            Method m3 = MethodUtils.getMatchingAccessibleMethod(c, method.getName(), parameterTypes);
            if (m3 == null) continue;
            if (Arrays.equals(m3.getParameterTypes(), parameterTypes)) {
                result.add(m3);
                continue;
            }
            Map<TypeVariable<?>, Type> typeArguments = TypeUtils.getTypeArguments(declaringClass, m3.getDeclaringClass());
            for (int i = 0; i < parameterTypes.length; ++i) {
                Type parentType;
                Type childType = TypeUtils.unrollVariables(typeArguments, method.getGenericParameterTypes()[i]);
                if (!TypeUtils.equals(childType, parentType = TypeUtils.unrollVariables(typeArguments, m3.getGenericParameterTypes()[i]))) continue block0;
            }
            result.add(m3);
        }
        return result;
    }

    public static Method[] getMethodsWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationCls) {
        return MethodUtils.getMethodsWithAnnotation(cls, annotationCls, false, false);
    }

    public static List<Method> getMethodsListWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationCls) {
        return MethodUtils.getMethodsListWithAnnotation(cls, annotationCls, false, false);
    }

    public static Method[] getMethodsWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationCls, boolean searchSupers, boolean ignoreAccess) {
        List<Method> annotatedMethodsList = MethodUtils.getMethodsListWithAnnotation(cls, annotationCls, searchSupers, ignoreAccess);
        return annotatedMethodsList.toArray(new Method[annotatedMethodsList.size()]);
    }

    public static List<Method> getMethodsListWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationCls, boolean searchSupers, boolean ignoreAccess) {
        Validate.isTrue(cls != null, "The class must not be null", new Object[0]);
        Validate.isTrue(annotationCls != null, "The annotation class must not be null", new Object[0]);
        ArrayList classes = searchSupers ? MethodUtils.getAllSuperclassesAndInterfaces(cls) : new ArrayList();
        classes.add(0, cls);
        ArrayList<Method> annotatedMethods = new ArrayList<Method>();
        for (Class clazz : classes) {
            Method[] methods;
            for (Method method : methods = ignoreAccess ? clazz.getDeclaredMethods() : clazz.getMethods()) {
                if (method.getAnnotation(annotationCls) == null) continue;
                annotatedMethods.add(method);
            }
        }
        return annotatedMethods;
    }

    public static <A extends Annotation> A getAnnotation(Method method, Class<A> annotationCls, boolean searchSupers, boolean ignoreAccess) {
        Validate.isTrue(method != null, "The method must not be null", new Object[0]);
        Validate.isTrue(annotationCls != null, "The annotation class must not be null", new Object[0]);
        if (!ignoreAccess && !MemberUtils.isAccessible(method)) {
            return null;
        }
        A annotation = method.getAnnotation(annotationCls);
        if (annotation == null && searchSupers) {
            Class<?> mcls = method.getDeclaringClass();
            List<Class<?>> classes = MethodUtils.getAllSuperclassesAndInterfaces(mcls);
            for (Class<?> acls : classes) {
                Method equivalentMethod;
                try {
                    equivalentMethod = ignoreAccess ? acls.getDeclaredMethod(method.getName(), method.getParameterTypes()) : acls.getMethod(method.getName(), method.getParameterTypes());
                } catch (NoSuchMethodException e) {
                    continue;
                }
                annotation = equivalentMethod.getAnnotation(annotationCls);
                if (annotation == null) continue;
                break;
            }
        }
        return annotation;
    }

    private static List<Class<?>> getAllSuperclassesAndInterfaces(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        ArrayList allSuperClassesAndInterfaces = new ArrayList();
        List<Class<?>> allSuperclasses = ClassUtils.getAllSuperclasses(cls);
        int superClassIndex = 0;
        List<Class<?>> allInterfaces = ClassUtils.getAllInterfaces(cls);
        int interfaceIndex = 0;
        while (interfaceIndex < allInterfaces.size() || superClassIndex < allSuperclasses.size()) {
            Class<?> acls = interfaceIndex >= allInterfaces.size() ? allSuperclasses.get(superClassIndex++) : (superClassIndex >= allSuperclasses.size() ? allInterfaces.get(interfaceIndex++) : (interfaceIndex < superClassIndex ? allInterfaces.get(interfaceIndex++) : (superClassIndex < interfaceIndex ? allSuperclasses.get(superClassIndex++) : allInterfaces.get(interfaceIndex++))));
            allSuperClassesAndInterfaces.add(acls);
        }
        return allSuperClassesAndInterfaces;
    }
}

