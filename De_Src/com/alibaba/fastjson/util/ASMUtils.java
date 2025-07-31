/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.util;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.asm.ClassReader;
import com.alibaba.fastjson.asm.TypeCollector;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ASMUtils {
    public static final String JAVA_VM_NAME = System.getProperty("java.vm.name");
    public static final boolean IS_ANDROID = ASMUtils.isAndroid(JAVA_VM_NAME);

    public static boolean isAndroid(String vmName) {
        if (vmName == null) {
            return false;
        }
        String lowerVMName = vmName.toLowerCase();
        return lowerVMName.contains("dalvik") || lowerVMName.contains("lemur");
    }

    public static String desc(Method method) {
        Class<?>[] types = method.getParameterTypes();
        StringBuilder buf = new StringBuilder(types.length + 1 << 4);
        buf.append('(');
        for (int i = 0; i < types.length; ++i) {
            buf.append(ASMUtils.desc(types[i]));
        }
        buf.append(')');
        buf.append(ASMUtils.desc(method.getReturnType()));
        return buf.toString();
    }

    public static String desc(Class<?> returnType) {
        if (returnType.isPrimitive()) {
            return ASMUtils.getPrimitiveLetter(returnType);
        }
        if (returnType.isArray()) {
            return "[" + ASMUtils.desc(returnType.getComponentType());
        }
        return "L" + ASMUtils.type(returnType) + ";";
    }

    public static String type(Class<?> parameterType) {
        if (parameterType.isArray()) {
            return "[" + ASMUtils.desc(parameterType.getComponentType());
        }
        if (!parameterType.isPrimitive()) {
            String clsName = parameterType.getName();
            return clsName.replace('.', '/');
        }
        return ASMUtils.getPrimitiveLetter(parameterType);
    }

    public static String getPrimitiveLetter(Class<?> type) {
        if (Integer.TYPE == type) {
            return "I";
        }
        if (Void.TYPE == type) {
            return "V";
        }
        if (Boolean.TYPE == type) {
            return "Z";
        }
        if (Character.TYPE == type) {
            return "C";
        }
        if (Byte.TYPE == type) {
            return "B";
        }
        if (Short.TYPE == type) {
            return "S";
        }
        if (Float.TYPE == type) {
            return "F";
        }
        if (Long.TYPE == type) {
            return "J";
        }
        if (Double.TYPE == type) {
            return "D";
        }
        throw new IllegalStateException("Type: " + type.getCanonicalName() + " is not a primitive type");
    }

    public static Type getMethodType(Class<?> clazz, String methodName) {
        try {
            Method method = clazz.getMethod(methodName, new Class[0]);
            return method.getGenericReturnType();
        } catch (Exception ex) {
            return null;
        }
    }

    public static boolean checkName(String name) {
        for (int i = 0; i < name.length(); ++i) {
            char c = name.charAt(i);
            if (c >= '\u0001' && c <= '\u007f' && c != '.') continue;
            return false;
        }
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String[] lookupParameterNames(AccessibleObject methodOrCtor) {
        String className;
        String resourceName;
        InputStream is;
        Annotation[][] parameterAnnotations;
        Class<Object> declaringClass;
        String name;
        Class<?>[] types;
        if (IS_ANDROID) {
            return new String[0];
        }
        if (methodOrCtor instanceof Method) {
            Method method = (Method)methodOrCtor;
            types = method.getParameterTypes();
            name = method.getName();
            declaringClass = method.getDeclaringClass();
            parameterAnnotations = TypeUtils.getParameterAnnotations(method);
        } else {
            Constructor constructor = (Constructor)methodOrCtor;
            types = constructor.getParameterTypes();
            declaringClass = constructor.getDeclaringClass();
            name = "<init>";
            parameterAnnotations = TypeUtils.getParameterAnnotations(constructor);
        }
        if (types.length == 0) {
            return new String[0];
        }
        ClassLoader classLoader = declaringClass.getClassLoader();
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        if ((is = classLoader.getResourceAsStream(resourceName = (className = declaringClass.getName()).replace('.', '/') + ".class")) == null) {
            return new String[0];
        }
        try {
            ClassReader reader = new ClassReader(is, false);
            TypeCollector visitor = new TypeCollector(name, types);
            reader.accept(visitor);
            String[] parameterNames = visitor.getParameterNamesForMethod();
            for (int i = 0; i < parameterNames.length; ++i) {
                Annotation[] annotations = parameterAnnotations[i];
                if (annotations == null) continue;
                for (int j = 0; j < annotations.length; ++j) {
                    JSONField jsonField;
                    String fieldName;
                    if (!(annotations[j] instanceof JSONField) || (fieldName = (jsonField = (JSONField)annotations[j]).name()) == null || fieldName.length() <= 0) continue;
                    parameterNames[i] = fieldName;
                }
            }
            String[] stringArray = parameterNames;
            return stringArray;
        } catch (IOException e) {
            String[] stringArray = new String[]{};
            return stringArray;
        } finally {
            IOUtils.close(is);
        }
    }
}

