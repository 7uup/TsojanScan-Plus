/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.util;

import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class FieldInfo
implements Comparable<FieldInfo> {
    public final String name;
    public final Method method;
    public final Field field;
    private int ordinal = 0;
    public final Class<?> fieldClass;
    public final Type fieldType;
    public final Class<?> declaringClass;
    public final boolean getOnly;
    public final int serialzeFeatures;
    public final int parserFeatures;
    public final String label;
    private final JSONField fieldAnnotation;
    private final JSONField methodAnnotation;
    public final boolean fieldAccess;
    public final boolean fieldTransient;
    public final char[] name_chars;
    public final boolean isEnum;
    public final boolean jsonDirect;
    public final boolean unwrapped;
    public final String format;
    public final String[] alternateNames;
    public final long nameHashCode;

    public FieldInfo(String name, Class<?> declaringClass, Class<?> fieldClass, Type fieldType, Field field, int ordinal, int serialzeFeatures, int parserFeatures) {
        if (ordinal < 0) {
            ordinal = 0;
        }
        this.name = name;
        this.declaringClass = declaringClass;
        this.fieldClass = fieldClass;
        this.fieldType = fieldType;
        this.method = null;
        this.field = field;
        this.ordinal = ordinal;
        this.serialzeFeatures = serialzeFeatures;
        this.parserFeatures = parserFeatures;
        this.isEnum = fieldClass.isEnum();
        if (field != null) {
            int modifiers = field.getModifiers();
            this.fieldAccess = (modifiers & 1) != 0 || this.method == null;
            this.fieldTransient = Modifier.isTransient(modifiers);
        } else {
            this.fieldTransient = false;
            this.fieldAccess = false;
        }
        this.name_chars = this.genFieldNameChars();
        if (field != null) {
            TypeUtils.setAccessible(field);
        }
        this.label = "";
        this.fieldAnnotation = field == null ? null : TypeUtils.getAnnotation(field, JSONField.class);
        this.methodAnnotation = null;
        this.getOnly = false;
        this.jsonDirect = false;
        this.unwrapped = false;
        this.format = null;
        this.alternateNames = new String[0];
        this.nameHashCode = this.nameHashCode64(name, this.fieldAnnotation);
    }

    public FieldInfo(String name, Method method, Field field, Class<?> clazz, Type type, int ordinal, int serialzeFeatures, int parserFeatures, JSONField fieldAnnotation, JSONField methodAnnotation, String label) {
        this(name, method, field, clazz, type, ordinal, serialzeFeatures, parserFeatures, fieldAnnotation, methodAnnotation, label, null);
    }

    public FieldInfo(String name, Method method, Field field, Class<?> clazz, Type type, int ordinal, int serialzeFeatures, int parserFeatures, JSONField fieldAnnotation, JSONField methodAnnotation, String label, Map<TypeVariable, Type> genericInfo) {
        TypeVariable tv;
        Type genericFieldType;
        Class<?> fieldType;
        Class<?> fieldClass;
        String fieldName;
        if (field != null && (fieldName = field.getName()).equals(name)) {
            name = fieldName;
        }
        if (ordinal < 0) {
            ordinal = 0;
        }
        this.name = name;
        this.method = method;
        this.field = field;
        this.ordinal = ordinal;
        this.serialzeFeatures = serialzeFeatures;
        this.parserFeatures = parserFeatures;
        this.fieldAnnotation = fieldAnnotation;
        this.methodAnnotation = methodAnnotation;
        if (field != null) {
            int modifiers = field.getModifiers();
            this.fieldAccess = (modifiers & 1) != 0 || method == null;
            this.fieldTransient = Modifier.isTransient(modifiers) || TypeUtils.isTransient(method);
        } else {
            this.fieldAccess = false;
            this.fieldTransient = TypeUtils.isTransient(method);
        }
        this.label = label != null && label.length() > 0 ? label : "";
        String format = null;
        JSONField annotation = this.getAnnotation();
        this.nameHashCode = this.nameHashCode64(name, annotation);
        boolean jsonDirect = false;
        if (annotation != null) {
            format = annotation.format();
            if (format.trim().length() == 0) {
                format = null;
            }
            jsonDirect = annotation.jsonDirect();
            this.unwrapped = annotation.unwrapped();
            this.alternateNames = annotation.alternateNames();
        } else {
            jsonDirect = false;
            this.unwrapped = false;
            this.alternateNames = new String[0];
        }
        this.format = format;
        this.name_chars = this.genFieldNameChars();
        if (method != null) {
            TypeUtils.setAccessible(method);
        }
        if (field != null) {
            TypeUtils.setAccessible(field);
        }
        boolean getOnly = false;
        if (method != null) {
            Class<?>[] types = method.getParameterTypes();
            if (types.length == 1) {
                fieldClass = types[0];
                fieldType = method.getGenericParameterTypes()[0];
            } else if (types.length == 2 && types[0] == String.class && types[1] == Object.class) {
                fieldClass = types[0];
                fieldType = fieldClass;
            } else {
                fieldClass = method.getReturnType();
                fieldType = method.getGenericReturnType();
                getOnly = true;
            }
            this.declaringClass = method.getDeclaringClass();
        } else {
            fieldClass = field.getType();
            fieldType = field.getGenericType();
            this.declaringClass = field.getDeclaringClass();
            getOnly = Modifier.isFinal(field.getModifiers());
        }
        this.getOnly = getOnly;
        boolean bl = this.jsonDirect = jsonDirect && fieldClass == String.class;
        if (clazz != null && fieldClass == Object.class && fieldType instanceof TypeVariable && (genericFieldType = FieldInfo.getInheritGenericType(clazz, type, tv = (TypeVariable)((Object)fieldType))) != null) {
            this.fieldClass = TypeUtils.getClass(genericFieldType);
            this.fieldType = genericFieldType;
            this.isEnum = fieldClass.isEnum();
            return;
        }
        Type genericFieldType2 = fieldType;
        if (!(fieldType instanceof Class) && (genericFieldType2 = FieldInfo.getFieldType(clazz, type != null ? type : clazz, fieldType, genericInfo)) != fieldType) {
            if (genericFieldType2 instanceof ParameterizedType) {
                fieldClass = TypeUtils.getClass(genericFieldType2);
            } else if (genericFieldType2 instanceof Class) {
                fieldClass = TypeUtils.getClass(genericFieldType2);
            }
        }
        this.fieldType = genericFieldType2;
        this.fieldClass = fieldClass;
        this.isEnum = fieldClass.isEnum();
    }

    private long nameHashCode64(String name, JSONField annotation) {
        if (annotation != null && annotation.name().length() != 0) {
            return TypeUtils.fnv1a_64_lower(name);
        }
        return TypeUtils.fnv1a_64_extract(name);
    }

    protected char[] genFieldNameChars() {
        int nameLen = this.name.length();
        char[] name_chars = new char[nameLen + 3];
        this.name.getChars(0, this.name.length(), name_chars, 1);
        name_chars[0] = 34;
        name_chars[nameLen + 1] = 34;
        name_chars[nameLen + 2] = 58;
        return name_chars;
    }

    public <T extends Annotation> T getAnnation(Class<T> annotationClass) {
        if (annotationClass == JSONField.class) {
            return (T)this.getAnnotation();
        }
        T annotatition = null;
        if (this.method != null) {
            annotatition = TypeUtils.getAnnotation(this.method, annotationClass);
        }
        if (annotatition == null && this.field != null) {
            annotatition = TypeUtils.getAnnotation(this.field, annotationClass);
        }
        return annotatition;
    }

    public static Type getFieldType(Class<?> clazz, Type type, Type fieldType) {
        return FieldInfo.getFieldType(clazz, type, fieldType, null);
    }

    public static Type getFieldType(Class<?> clazz, Type type, Type fieldType, Map<TypeVariable, Type> genericInfo) {
        if (clazz == null || type == null) {
            return fieldType;
        }
        if (fieldType instanceof GenericArrayType) {
            Type componentTypeX;
            GenericArrayType genericArrayType = (GenericArrayType)fieldType;
            Type componentType = genericArrayType.getGenericComponentType();
            if (componentType != (componentTypeX = FieldInfo.getFieldType(clazz, type, componentType, genericInfo))) {
                Class<?> fieldTypeX = Array.newInstance(TypeUtils.getClass(componentTypeX), 0).getClass();
                return fieldTypeX;
            }
            return fieldType;
        }
        if (!TypeUtils.isGenericParamType(type)) {
            return fieldType;
        }
        if (fieldType instanceof TypeVariable) {
            ParameterizedType paramType = (ParameterizedType)TypeUtils.getGenericParamType(type);
            Class<?> parameterizedClass = TypeUtils.getClass(paramType);
            TypeVariable typeVar = (TypeVariable)fieldType;
            TypeVariable<Class<?>>[] typeVariables = parameterizedClass.getTypeParameters();
            for (int i = 0; i < typeVariables.length; ++i) {
                if (!typeVariables[i].getName().equals(typeVar.getName())) continue;
                fieldType = paramType.getActualTypeArguments()[i];
                return fieldType;
            }
        }
        if (fieldType instanceof ParameterizedType) {
            ParameterizedType parameterizedFieldType = (ParameterizedType)fieldType;
            Type[] arguments = parameterizedFieldType.getActualTypeArguments();
            boolean changed = FieldInfo.getArgument(arguments, genericInfo);
            if (!changed) {
                TypeVariable[] typeVariables;
                ParameterizedType paramType;
                if (type instanceof ParameterizedType) {
                    paramType = (ParameterizedType)type;
                    typeVariables = clazz.getTypeParameters();
                } else if (clazz.getGenericSuperclass() instanceof ParameterizedType) {
                    paramType = (ParameterizedType)clazz.getGenericSuperclass();
                    typeVariables = clazz.getSuperclass().getTypeParameters();
                } else {
                    paramType = parameterizedFieldType;
                    typeVariables = type.getClass().getTypeParameters();
                }
                changed = FieldInfo.getArgument(arguments, typeVariables, paramType.getActualTypeArguments());
            }
            if (changed) {
                fieldType = TypeReference.intern(new ParameterizedTypeImpl(arguments, parameterizedFieldType.getOwnerType(), parameterizedFieldType.getRawType()));
                return fieldType;
            }
        }
        return fieldType;
    }

    private static boolean getArgument(Type[] typeArgs, Map<TypeVariable, Type> genericInfo) {
        if (genericInfo == null || genericInfo.size() == 0) {
            return false;
        }
        boolean changed = false;
        for (int i = 0; i < typeArgs.length; ++i) {
            Type typeArg = typeArgs[i];
            if (typeArg instanceof ParameterizedType) {
                ParameterizedType p_typeArg = (ParameterizedType)typeArg;
                Type[] p_typeArg_args = p_typeArg.getActualTypeArguments();
                boolean p_changed = FieldInfo.getArgument(p_typeArg_args, genericInfo);
                if (!p_changed) continue;
                typeArgs[i] = TypeReference.intern(new ParameterizedTypeImpl(p_typeArg_args, p_typeArg.getOwnerType(), p_typeArg.getRawType()));
                changed = true;
                continue;
            }
            if (!(typeArg instanceof TypeVariable) || !genericInfo.containsKey(typeArg)) continue;
            typeArgs[i] = genericInfo.get(typeArg);
            changed = true;
        }
        return changed;
    }

    private static boolean getArgument(Type[] typeArgs, TypeVariable[] typeVariables, Type[] arguments) {
        if (arguments == null || typeVariables.length == 0) {
            return false;
        }
        boolean changed = false;
        for (int i = 0; i < typeArgs.length; ++i) {
            Type typeArg = typeArgs[i];
            if (typeArg instanceof ParameterizedType) {
                ParameterizedType p_typeArg = (ParameterizedType)typeArg;
                Type[] p_typeArg_args = p_typeArg.getActualTypeArguments();
                boolean p_changed = FieldInfo.getArgument(p_typeArg_args, typeVariables, arguments);
                if (!p_changed) continue;
                typeArgs[i] = TypeReference.intern(new ParameterizedTypeImpl(p_typeArg_args, p_typeArg.getOwnerType(), p_typeArg.getRawType()));
                changed = true;
                continue;
            }
            if (!(typeArg instanceof TypeVariable)) continue;
            for (int j = 0; j < typeVariables.length; ++j) {
                if (!typeArg.equals(typeVariables[j])) continue;
                typeArgs[i] = arguments[j];
                changed = true;
            }
        }
        return changed;
    }

    private static Type getInheritGenericType(Class<?> clazz, Type type, TypeVariable<?> tv) {
        Object gd = tv.getGenericDeclaration();
        Class class_gd = null;
        if (gd instanceof Class) {
            class_gd = (Class)tv.getGenericDeclaration();
        }
        Type[] arguments = null;
        if (class_gd == clazz) {
            if (type instanceof ParameterizedType) {
                ParameterizedType ptype = (ParameterizedType)type;
                arguments = ptype.getActualTypeArguments();
            }
        } else {
            for (Class<?> c = clazz; c != null && c != Object.class && c != class_gd; c = c.getSuperclass()) {
                Type superType = c.getGenericSuperclass();
                if (!(superType instanceof ParameterizedType)) continue;
                ParameterizedType p_superType = (ParameterizedType)superType;
                Type[] p_superType_args = p_superType.getActualTypeArguments();
                FieldInfo.getArgument(p_superType_args, c.getTypeParameters(), arguments);
                arguments = p_superType_args;
            }
        }
        if (arguments == null || class_gd == null) {
            return null;
        }
        Type actualType = null;
        TypeVariable<Class<T>>[] typeVariables = class_gd.getTypeParameters();
        for (int j = 0; j < typeVariables.length; ++j) {
            if (!tv.equals(typeVariables[j])) continue;
            actualType = arguments[j];
            break;
        }
        return actualType;
    }

    public String toString() {
        return this.name;
    }

    public Member getMember() {
        if (this.method != null) {
            return this.method;
        }
        return this.field;
    }

    protected Class<?> getDeclaredClass() {
        if (this.method != null) {
            return this.method.getDeclaringClass();
        }
        if (this.field != null) {
            return this.field.getDeclaringClass();
        }
        return null;
    }

    @Override
    public int compareTo(FieldInfo o) {
        boolean oSameType;
        if (o.method != null && this.method != null && o.method.isBridge() && !this.method.isBridge() && o.method.getName().equals(this.method.getName())) {
            return 1;
        }
        if (this.ordinal < o.ordinal) {
            return -1;
        }
        if (this.ordinal > o.ordinal) {
            return 1;
        }
        int result = this.name.compareTo(o.name);
        if (result != 0) {
            return result;
        }
        Class<?> thisDeclaringClass = this.getDeclaredClass();
        Class<?> otherDeclaringClass = o.getDeclaredClass();
        if (thisDeclaringClass != null && otherDeclaringClass != null && thisDeclaringClass != otherDeclaringClass) {
            if (thisDeclaringClass.isAssignableFrom(otherDeclaringClass)) {
                return -1;
            }
            if (otherDeclaringClass.isAssignableFrom(thisDeclaringClass)) {
                return 1;
            }
        }
        boolean isSampeType = this.field != null && this.field.getType() == this.fieldClass;
        boolean bl = oSameType = o.field != null && o.field.getType() == o.fieldClass;
        if (isSampeType && !oSameType) {
            return 1;
        }
        if (oSameType && !isSampeType) {
            return -1;
        }
        if (o.fieldClass.isPrimitive() && !this.fieldClass.isPrimitive()) {
            return 1;
        }
        if (this.fieldClass.isPrimitive() && !o.fieldClass.isPrimitive()) {
            return -1;
        }
        if (o.fieldClass.getName().startsWith("java.") && !this.fieldClass.getName().startsWith("java.")) {
            return 1;
        }
        if (this.fieldClass.getName().startsWith("java.") && !o.fieldClass.getName().startsWith("java.")) {
            return -1;
        }
        return this.fieldClass.getName().compareTo(o.fieldClass.getName());
    }

    public JSONField getAnnotation() {
        if (this.fieldAnnotation != null) {
            return this.fieldAnnotation;
        }
        return this.methodAnnotation;
    }

    public String getFormat() {
        return this.format;
    }

    public Object get(Object javaObject) throws IllegalAccessException, InvocationTargetException {
        return this.method != null ? this.method.invoke(javaObject, new Object[0]) : this.field.get(javaObject);
    }

    public void set(Object javaObject, Object value) throws IllegalAccessException, InvocationTargetException {
        if (this.method != null) {
            this.method.invoke(javaObject, value);
            return;
        }
        this.field.set(javaObject, value);
    }

    public void setAccessible() throws SecurityException {
        if (this.method != null) {
            TypeUtils.setAccessible(this.method);
            return;
        }
        TypeUtils.setAccessible(this.field);
    }
}

