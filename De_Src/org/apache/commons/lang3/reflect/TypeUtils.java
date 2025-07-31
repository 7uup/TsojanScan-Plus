/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.Builder;
import org.apache.commons.lang3.reflect.Typed;

public class TypeUtils {
    public static final WildcardType WILDCARD_ALL = TypeUtils.wildcardType().withUpperBounds(new Type[]{Object.class}).build();

    public static boolean isAssignable(Type type, Type toType) {
        return TypeUtils.isAssignable(type, toType, null);
    }

    private static boolean isAssignable(Type type, Type toType, Map<TypeVariable<?>, Type> typeVarAssigns) {
        if (toType == null || toType instanceof Class) {
            return TypeUtils.isAssignable(type, (Class)toType);
        }
        if (toType instanceof ParameterizedType) {
            return TypeUtils.isAssignable(type, (ParameterizedType)toType, typeVarAssigns);
        }
        if (toType instanceof GenericArrayType) {
            return TypeUtils.isAssignable(type, (GenericArrayType)toType, typeVarAssigns);
        }
        if (toType instanceof WildcardType) {
            return TypeUtils.isAssignable(type, (WildcardType)toType, typeVarAssigns);
        }
        if (toType instanceof TypeVariable) {
            return TypeUtils.isAssignable(type, (TypeVariable)toType, typeVarAssigns);
        }
        throw new IllegalStateException("found an unhandled type: " + toType);
    }

    private static boolean isAssignable(Type type, Class<?> toClass) {
        if (type == null) {
            return toClass == null || !toClass.isPrimitive();
        }
        if (toClass == null) {
            return false;
        }
        if (toClass.equals(type)) {
            return true;
        }
        if (type instanceof Class) {
            return ClassUtils.isAssignable((Class)type, toClass);
        }
        if (type instanceof ParameterizedType) {
            return TypeUtils.isAssignable(TypeUtils.getRawType((ParameterizedType)type), toClass);
        }
        if (type instanceof TypeVariable) {
            for (Type bound : ((TypeVariable)type).getBounds()) {
                if (!TypeUtils.isAssignable(bound, toClass)) continue;
                return true;
            }
            return false;
        }
        if (type instanceof GenericArrayType) {
            return toClass.equals(Object.class) || toClass.isArray() && TypeUtils.isAssignable(((GenericArrayType)type).getGenericComponentType(), toClass.getComponentType());
        }
        if (type instanceof WildcardType) {
            return false;
        }
        throw new IllegalStateException("found an unhandled type: " + type);
    }

    private static boolean isAssignable(Type type, ParameterizedType toParameterizedType, Map<TypeVariable<?>, Type> typeVarAssigns) {
        if (type == null) {
            return true;
        }
        if (toParameterizedType == null) {
            return false;
        }
        if (toParameterizedType.equals(type)) {
            return true;
        }
        Class<?> toClass = TypeUtils.getRawType(toParameterizedType);
        Map<TypeVariable<?>, Type> fromTypeVarAssigns = TypeUtils.getTypeArguments(type, toClass, null);
        if (fromTypeVarAssigns == null) {
            return false;
        }
        if (fromTypeVarAssigns.isEmpty()) {
            return true;
        }
        Map<TypeVariable<?>, Type> toTypeVarAssigns = TypeUtils.getTypeArguments(toParameterizedType, toClass, typeVarAssigns);
        for (TypeVariable<?> var : toTypeVarAssigns.keySet()) {
            Type toTypeArg = TypeUtils.unrollVariableAssignments(var, toTypeVarAssigns);
            Type fromTypeArg = TypeUtils.unrollVariableAssignments(var, fromTypeVarAssigns);
            if (toTypeArg == null && fromTypeArg instanceof Class || fromTypeArg == null || toTypeArg.equals(fromTypeArg) || toTypeArg instanceof WildcardType && TypeUtils.isAssignable(fromTypeArg, toTypeArg, typeVarAssigns)) continue;
            return false;
        }
        return true;
    }

    private static Type unrollVariableAssignments(TypeVariable<?> var, Map<TypeVariable<?>, Type> typeVarAssigns) {
        Type result;
        while ((result = typeVarAssigns.get(var)) instanceof TypeVariable && !result.equals(var)) {
            var = (TypeVariable)result;
        }
        return result;
    }

    private static boolean isAssignable(Type type, GenericArrayType toGenericArrayType, Map<TypeVariable<?>, Type> typeVarAssigns) {
        if (type == null) {
            return true;
        }
        if (toGenericArrayType == null) {
            return false;
        }
        if (toGenericArrayType.equals(type)) {
            return true;
        }
        Type toComponentType = toGenericArrayType.getGenericComponentType();
        if (type instanceof Class) {
            Class cls = (Class)type;
            return cls.isArray() && TypeUtils.isAssignable(cls.getComponentType(), toComponentType, typeVarAssigns);
        }
        if (type instanceof GenericArrayType) {
            return TypeUtils.isAssignable(((GenericArrayType)type).getGenericComponentType(), toComponentType, typeVarAssigns);
        }
        if (type instanceof WildcardType) {
            for (Type bound : TypeUtils.getImplicitUpperBounds((WildcardType)type)) {
                if (!TypeUtils.isAssignable(bound, toGenericArrayType)) continue;
                return true;
            }
            return false;
        }
        if (type instanceof TypeVariable) {
            for (Type bound : TypeUtils.getImplicitBounds((TypeVariable)type)) {
                if (!TypeUtils.isAssignable(bound, toGenericArrayType)) continue;
                return true;
            }
            return false;
        }
        if (type instanceof ParameterizedType) {
            return false;
        }
        throw new IllegalStateException("found an unhandled type: " + type);
    }

    private static boolean isAssignable(Type type, WildcardType toWildcardType, Map<TypeVariable<?>, Type> typeVarAssigns) {
        if (type == null) {
            return true;
        }
        if (toWildcardType == null) {
            return false;
        }
        if (toWildcardType.equals(type)) {
            return true;
        }
        Type[] toUpperBounds = TypeUtils.getImplicitUpperBounds(toWildcardType);
        Type[] toLowerBounds = TypeUtils.getImplicitLowerBounds(toWildcardType);
        if (type instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType)type;
            Type[] upperBounds = TypeUtils.getImplicitUpperBounds(wildcardType);
            Type[] lowerBounds = TypeUtils.getImplicitLowerBounds(wildcardType);
            for (Type toBound : toUpperBounds) {
                toBound = TypeUtils.substituteTypeVariables(toBound, typeVarAssigns);
                for (Type bound : upperBounds) {
                    if (TypeUtils.isAssignable(bound, toBound, typeVarAssigns)) continue;
                    return false;
                }
            }
            for (Type toBound : toLowerBounds) {
                toBound = TypeUtils.substituteTypeVariables(toBound, typeVarAssigns);
                for (Type bound : lowerBounds) {
                    if (TypeUtils.isAssignable(toBound, bound, typeVarAssigns)) continue;
                    return false;
                }
            }
            return true;
        }
        for (Type toBound : toUpperBounds) {
            if (TypeUtils.isAssignable(type, TypeUtils.substituteTypeVariables(toBound, typeVarAssigns), typeVarAssigns)) continue;
            return false;
        }
        for (Type toBound : toLowerBounds) {
            if (TypeUtils.isAssignable(TypeUtils.substituteTypeVariables(toBound, typeVarAssigns), type, typeVarAssigns)) continue;
            return false;
        }
        return true;
    }

    private static boolean isAssignable(Type type, TypeVariable<?> toTypeVariable, Map<TypeVariable<?>, Type> typeVarAssigns) {
        if (type == null) {
            return true;
        }
        if (toTypeVariable == null) {
            return false;
        }
        if (toTypeVariable.equals(type)) {
            return true;
        }
        if (type instanceof TypeVariable) {
            Type[] bounds;
            for (Type bound : bounds = TypeUtils.getImplicitBounds((TypeVariable)type)) {
                if (!TypeUtils.isAssignable(bound, toTypeVariable, typeVarAssigns)) continue;
                return true;
            }
        }
        if (type instanceof Class || type instanceof ParameterizedType || type instanceof GenericArrayType || type instanceof WildcardType) {
            return false;
        }
        throw new IllegalStateException("found an unhandled type: " + type);
    }

    private static Type substituteTypeVariables(Type type, Map<TypeVariable<?>, Type> typeVarAssigns) {
        if (type instanceof TypeVariable && typeVarAssigns != null) {
            Type replacementType = typeVarAssigns.get(type);
            if (replacementType == null) {
                throw new IllegalArgumentException("missing assignment type for type variable " + type);
            }
            return replacementType;
        }
        return type;
    }

    public static Map<TypeVariable<?>, Type> getTypeArguments(ParameterizedType type) {
        return TypeUtils.getTypeArguments(type, TypeUtils.getRawType(type), null);
    }

    public static Map<TypeVariable<?>, Type> getTypeArguments(Type type, Class<?> toClass) {
        return TypeUtils.getTypeArguments(type, toClass, null);
    }

    private static Map<TypeVariable<?>, Type> getTypeArguments(Type type, Class<?> toClass, Map<TypeVariable<?>, Type> subtypeVarAssigns) {
        if (type instanceof Class) {
            return TypeUtils.getTypeArguments((Class)type, toClass, subtypeVarAssigns);
        }
        if (type instanceof ParameterizedType) {
            return TypeUtils.getTypeArguments((ParameterizedType)type, toClass, subtypeVarAssigns);
        }
        if (type instanceof GenericArrayType) {
            return TypeUtils.getTypeArguments(((GenericArrayType)type).getGenericComponentType(), toClass.isArray() ? toClass.getComponentType() : toClass, subtypeVarAssigns);
        }
        if (type instanceof WildcardType) {
            for (Type bound : TypeUtils.getImplicitUpperBounds((WildcardType)type)) {
                if (!TypeUtils.isAssignable(bound, toClass)) continue;
                return TypeUtils.getTypeArguments(bound, toClass, subtypeVarAssigns);
            }
            return null;
        }
        if (type instanceof TypeVariable) {
            for (Type bound : TypeUtils.getImplicitBounds((TypeVariable)type)) {
                if (!TypeUtils.isAssignable(bound, toClass)) continue;
                return TypeUtils.getTypeArguments(bound, toClass, subtypeVarAssigns);
            }
            return null;
        }
        throw new IllegalStateException("found an unhandled type: " + type);
    }

    private static Map<TypeVariable<?>, Type> getTypeArguments(ParameterizedType parameterizedType, Class<?> toClass, Map<TypeVariable<?>, Type> subtypeVarAssigns) {
        HashMap<TypeVariable<?>, Type> typeVarAssigns;
        Class<?> cls = TypeUtils.getRawType(parameterizedType);
        if (!TypeUtils.isAssignable(cls, toClass)) {
            return null;
        }
        Type ownerType = parameterizedType.getOwnerType();
        if (ownerType instanceof ParameterizedType) {
            ParameterizedType parameterizedOwnerType = (ParameterizedType)ownerType;
            typeVarAssigns = TypeUtils.getTypeArguments(parameterizedOwnerType, TypeUtils.getRawType(parameterizedOwnerType), subtypeVarAssigns);
        } else {
            typeVarAssigns = subtypeVarAssigns == null ? new HashMap() : new HashMap(subtypeVarAssigns);
        }
        Type[] typeArgs = parameterizedType.getActualTypeArguments();
        TypeVariable<Class<?>>[] typeParams = cls.getTypeParameters();
        for (int i = 0; i < typeParams.length; ++i) {
            Type typeArg = typeArgs[i];
            typeVarAssigns.put(typeParams[i], typeVarAssigns.containsKey(typeArg) ? (Type)typeVarAssigns.get(typeArg) : typeArg);
        }
        if (toClass.equals(cls)) {
            return typeVarAssigns;
        }
        return TypeUtils.getTypeArguments(TypeUtils.getClosestParentType(cls, toClass), toClass, typeVarAssigns);
    }

    private static Map<TypeVariable<?>, Type> getTypeArguments(Class<?> cls, Class<?> toClass, Map<TypeVariable<?>, Type> subtypeVarAssigns) {
        HashMap typeVarAssigns;
        if (!TypeUtils.isAssignable(cls, toClass)) {
            return null;
        }
        if (cls.isPrimitive()) {
            if (toClass.isPrimitive()) {
                return new HashMap();
            }
            cls = ClassUtils.primitiveToWrapper(cls);
        }
        HashMap hashMap = typeVarAssigns = subtypeVarAssigns == null ? new HashMap() : new HashMap(subtypeVarAssigns);
        if (toClass.equals(cls)) {
            return typeVarAssigns;
        }
        return TypeUtils.getTypeArguments(TypeUtils.getClosestParentType(cls, toClass), toClass, typeVarAssigns);
    }

    public static Map<TypeVariable<?>, Type> determineTypeArguments(Class<?> cls, ParameterizedType superType) {
        Validate.notNull(cls, "cls is null", new Object[0]);
        Validate.notNull(superType, "superType is null", new Object[0]);
        Class<?> superClass = TypeUtils.getRawType(superType);
        if (!TypeUtils.isAssignable(cls, superClass)) {
            return null;
        }
        if (cls.equals(superClass)) {
            return TypeUtils.getTypeArguments(superType, superClass, null);
        }
        Type midType = TypeUtils.getClosestParentType(cls, superClass);
        if (midType instanceof Class) {
            return TypeUtils.determineTypeArguments((Class)midType, superType);
        }
        ParameterizedType midParameterizedType = (ParameterizedType)midType;
        Class<?> midClass = TypeUtils.getRawType(midParameterizedType);
        Map<TypeVariable<?>, Type> typeVarAssigns = TypeUtils.determineTypeArguments(midClass, superType);
        TypeUtils.mapTypeVariablesToArguments(cls, midParameterizedType, typeVarAssigns);
        return typeVarAssigns;
    }

    private static <T> void mapTypeVariablesToArguments(Class<T> cls, ParameterizedType parameterizedType, Map<TypeVariable<?>, Type> typeVarAssigns) {
        Type ownerType = parameterizedType.getOwnerType();
        if (ownerType instanceof ParameterizedType) {
            TypeUtils.mapTypeVariablesToArguments(cls, (ParameterizedType)ownerType, typeVarAssigns);
        }
        Type[] typeArgs = parameterizedType.getActualTypeArguments();
        TypeVariable<Class<?>>[] typeVars = TypeUtils.getRawType(parameterizedType).getTypeParameters();
        List<TypeVariable<Class<T>>> typeVarList = Arrays.asList(cls.getTypeParameters());
        for (int i = 0; i < typeArgs.length; ++i) {
            TypeVariable<Class<?>> typeVar = typeVars[i];
            Type typeArg = typeArgs[i];
            if (!typeVarList.contains(typeArg) || !typeVarAssigns.containsKey(typeVar)) continue;
            typeVarAssigns.put((TypeVariable)typeArg, typeVarAssigns.get(typeVar));
        }
    }

    private static Type getClosestParentType(Class<?> cls, Class<?> superClass) {
        if (superClass.isInterface()) {
            Type[] interfaceTypes = cls.getGenericInterfaces();
            Type genericInterface = null;
            for (Type midType : interfaceTypes) {
                Class midClass = null;
                if (midType instanceof ParameterizedType) {
                    midClass = TypeUtils.getRawType((ParameterizedType)midType);
                } else if (midType instanceof Class) {
                    midClass = (Class)midType;
                } else {
                    throw new IllegalStateException("Unexpected generic interface type found: " + midType);
                }
                if (!TypeUtils.isAssignable((Type)midClass, superClass) || !TypeUtils.isAssignable(genericInterface, (Type)midClass)) continue;
                genericInterface = midType;
            }
            if (genericInterface != null) {
                return genericInterface;
            }
        }
        return cls.getGenericSuperclass();
    }

    public static boolean isInstance(Object value, Type type) {
        if (type == null) {
            return false;
        }
        return value == null ? !(type instanceof Class) || !((Class)type).isPrimitive() : TypeUtils.isAssignable(value.getClass(), type, null);
    }

    public static Type[] normalizeUpperBounds(Type[] bounds) {
        Validate.notNull(bounds, "null value specified for bounds array", new Object[0]);
        if (bounds.length < 2) {
            return bounds;
        }
        HashSet<Type> types = new HashSet<Type>(bounds.length);
        for (Type type1 : bounds) {
            boolean subtypeFound = false;
            for (Type type2 : bounds) {
                if (type1 == type2 || !TypeUtils.isAssignable(type2, type1, null)) continue;
                subtypeFound = true;
                break;
            }
            if (subtypeFound) continue;
            types.add(type1);
        }
        return types.toArray(new Type[types.size()]);
    }

    public static Type[] getImplicitBounds(TypeVariable<?> typeVariable) {
        Type[] typeArray;
        Validate.notNull(typeVariable, "typeVariable is null", new Object[0]);
        Type[] bounds = typeVariable.getBounds();
        if (bounds.length == 0) {
            Type[] typeArray2 = new Type[1];
            typeArray = typeArray2;
            typeArray2[0] = Object.class;
        } else {
            typeArray = TypeUtils.normalizeUpperBounds(bounds);
        }
        return typeArray;
    }

    public static Type[] getImplicitUpperBounds(WildcardType wildcardType) {
        Type[] typeArray;
        Validate.notNull(wildcardType, "wildcardType is null", new Object[0]);
        Type[] bounds = wildcardType.getUpperBounds();
        if (bounds.length == 0) {
            Type[] typeArray2 = new Type[1];
            typeArray = typeArray2;
            typeArray2[0] = Object.class;
        } else {
            typeArray = TypeUtils.normalizeUpperBounds(bounds);
        }
        return typeArray;
    }

    public static Type[] getImplicitLowerBounds(WildcardType wildcardType) {
        Type[] typeArray;
        Validate.notNull(wildcardType, "wildcardType is null", new Object[0]);
        Type[] bounds = wildcardType.getLowerBounds();
        if (bounds.length == 0) {
            Type[] typeArray2 = new Type[1];
            typeArray = typeArray2;
            typeArray2[0] = null;
        } else {
            typeArray = bounds;
        }
        return typeArray;
    }

    public static boolean typesSatisfyVariables(Map<TypeVariable<?>, Type> typeVarAssigns) {
        Validate.notNull(typeVarAssigns, "typeVarAssigns is null", new Object[0]);
        for (Map.Entry<TypeVariable<?>, Type> entry : typeVarAssigns.entrySet()) {
            TypeVariable<?> typeVar = entry.getKey();
            Type type = entry.getValue();
            for (Type bound : TypeUtils.getImplicitBounds(typeVar)) {
                if (TypeUtils.isAssignable(type, TypeUtils.substituteTypeVariables(bound, typeVarAssigns), typeVarAssigns)) continue;
                return false;
            }
        }
        return true;
    }

    private static Class<?> getRawType(ParameterizedType parameterizedType) {
        Type rawType = parameterizedType.getRawType();
        if (!(rawType instanceof Class)) {
            throw new IllegalStateException("Wait... What!? Type of rawType: " + rawType);
        }
        return (Class)rawType;
    }

    public static Class<?> getRawType(Type type, Type assigningType) {
        if (type instanceof Class) {
            return (Class)type;
        }
        if (type instanceof ParameterizedType) {
            return TypeUtils.getRawType((ParameterizedType)type);
        }
        if (type instanceof TypeVariable) {
            if (assigningType == null) {
                return null;
            }
            Object genericDeclaration = ((TypeVariable)type).getGenericDeclaration();
            if (!(genericDeclaration instanceof Class)) {
                return null;
            }
            Map<TypeVariable<?>, Type> typeVarAssigns = TypeUtils.getTypeArguments(assigningType, (Class)genericDeclaration);
            if (typeVarAssigns == null) {
                return null;
            }
            Type typeArgument = typeVarAssigns.get(type);
            if (typeArgument == null) {
                return null;
            }
            return TypeUtils.getRawType(typeArgument, assigningType);
        }
        if (type instanceof GenericArrayType) {
            Class<?> rawComponentType = TypeUtils.getRawType(((GenericArrayType)type).getGenericComponentType(), assigningType);
            return Array.newInstance(rawComponentType, 0).getClass();
        }
        if (type instanceof WildcardType) {
            return null;
        }
        throw new IllegalArgumentException("unknown type: " + type);
    }

    public static boolean isArrayType(Type type) {
        return type instanceof GenericArrayType || type instanceof Class && ((Class)type).isArray();
    }

    public static Type getArrayComponentType(Type type) {
        if (type instanceof Class) {
            Class clazz = (Class)type;
            return clazz.isArray() ? clazz.getComponentType() : null;
        }
        if (type instanceof GenericArrayType) {
            return ((GenericArrayType)type).getGenericComponentType();
        }
        return null;
    }

    public static Type unrollVariables(Map<TypeVariable<?>, Type> typeArguments, Type type) {
        if (typeArguments == null) {
            typeArguments = Collections.emptyMap();
        }
        if (TypeUtils.containsTypeVariables(type)) {
            if (type instanceof TypeVariable) {
                return TypeUtils.unrollVariables(typeArguments, typeArguments.get(type));
            }
            if (type instanceof ParameterizedType) {
                Map<TypeVariable<?>, Type> parameterizedTypeArguments;
                ParameterizedType p = (ParameterizedType)type;
                if (p.getOwnerType() == null) {
                    parameterizedTypeArguments = typeArguments;
                } else {
                    parameterizedTypeArguments = new HashMap(typeArguments);
                    parameterizedTypeArguments.putAll(TypeUtils.getTypeArguments(p));
                }
                Type[] args2 = p.getActualTypeArguments();
                for (int i = 0; i < args2.length; ++i) {
                    Type unrolled = TypeUtils.unrollVariables(parameterizedTypeArguments, args2[i]);
                    if (unrolled == null) continue;
                    args2[i] = unrolled;
                }
                return TypeUtils.parameterizeWithOwner(p.getOwnerType(), (Class)p.getRawType(), args2);
            }
            if (type instanceof WildcardType) {
                WildcardType wild = (WildcardType)type;
                return TypeUtils.wildcardType().withUpperBounds(TypeUtils.unrollBounds(typeArguments, wild.getUpperBounds())).withLowerBounds(TypeUtils.unrollBounds(typeArguments, wild.getLowerBounds())).build();
            }
        }
        return type;
    }

    private static Type[] unrollBounds(Map<TypeVariable<?>, Type> typeArguments, Type[] bounds) {
        Type[] result = bounds;
        for (int i = 0; i < result.length; ++i) {
            Type unrolled = TypeUtils.unrollVariables(typeArguments, result[i]);
            if (unrolled == null) {
                result = ArrayUtils.remove(result, i--);
                continue;
            }
            result[i] = unrolled;
        }
        return result;
    }

    public static boolean containsTypeVariables(Type type) {
        if (type instanceof TypeVariable) {
            return true;
        }
        if (type instanceof Class) {
            return ((Class)type).getTypeParameters().length > 0;
        }
        if (type instanceof ParameterizedType) {
            for (Type arg : ((ParameterizedType)type).getActualTypeArguments()) {
                if (!TypeUtils.containsTypeVariables(arg)) continue;
                return true;
            }
            return false;
        }
        if (type instanceof WildcardType) {
            WildcardType wild = (WildcardType)type;
            return TypeUtils.containsTypeVariables(TypeUtils.getImplicitLowerBounds(wild)[0]) || TypeUtils.containsTypeVariables(TypeUtils.getImplicitUpperBounds(wild)[0]);
        }
        return false;
    }

    public static final ParameterizedType parameterize(Class<?> raw, Type ... typeArguments) {
        return TypeUtils.parameterizeWithOwner(null, raw, typeArguments);
    }

    public static final ParameterizedType parameterize(Class<?> raw, Map<TypeVariable<?>, Type> typeArgMappings) {
        Validate.notNull(raw, "raw class is null", new Object[0]);
        Validate.notNull(typeArgMappings, "typeArgMappings is null", new Object[0]);
        return TypeUtils.parameterizeWithOwner(null, raw, TypeUtils.extractTypeArgumentsFrom(typeArgMappings, raw.getTypeParameters()));
    }

    public static final ParameterizedType parameterizeWithOwner(Type owner, Class<?> raw, Type ... typeArguments) {
        Type useOwner;
        Validate.notNull(raw, "raw class is null", new Object[0]);
        if (raw.getEnclosingClass() == null) {
            Validate.isTrue(owner == null, "no owner allowed for top-level %s", raw);
            useOwner = null;
        } else if (owner == null) {
            useOwner = raw.getEnclosingClass();
        } else {
            Validate.isTrue(TypeUtils.isAssignable(owner, raw.getEnclosingClass()), "%s is invalid owner type for parameterized %s", owner, raw);
            useOwner = owner;
        }
        Validate.noNullElements(typeArguments, "null type argument at index %s", new Object[0]);
        Validate.isTrue(raw.getTypeParameters().length == typeArguments.length, "invalid number of type parameters specified: expected %d, got %d", raw.getTypeParameters().length, typeArguments.length);
        return new ParameterizedTypeImpl(raw, useOwner, typeArguments);
    }

    public static final ParameterizedType parameterizeWithOwner(Type owner, Class<?> raw, Map<TypeVariable<?>, Type> typeArgMappings) {
        Validate.notNull(raw, "raw class is null", new Object[0]);
        Validate.notNull(typeArgMappings, "typeArgMappings is null", new Object[0]);
        return TypeUtils.parameterizeWithOwner(owner, raw, TypeUtils.extractTypeArgumentsFrom(typeArgMappings, raw.getTypeParameters()));
    }

    private static Type[] extractTypeArgumentsFrom(Map<TypeVariable<?>, Type> mappings, TypeVariable<?>[] variables) {
        Type[] result = new Type[variables.length];
        int index = 0;
        for (TypeVariable<?> var : variables) {
            Validate.isTrue(mappings.containsKey(var), "missing argument mapping for %s", TypeUtils.toString(var));
            result[index++] = mappings.get(var);
        }
        return result;
    }

    public static WildcardTypeBuilder wildcardType() {
        return new WildcardTypeBuilder();
    }

    public static GenericArrayType genericArrayType(Type componentType) {
        return new GenericArrayTypeImpl(Validate.notNull(componentType, "componentType is null", new Object[0]));
    }

    public static boolean equals(Type t1, Type t2) {
        if (Objects.equals(t1, t2)) {
            return true;
        }
        if (t1 instanceof ParameterizedType) {
            return TypeUtils.equals((ParameterizedType)t1, t2);
        }
        if (t1 instanceof GenericArrayType) {
            return TypeUtils.equals((GenericArrayType)t1, t2);
        }
        if (t1 instanceof WildcardType) {
            return TypeUtils.equals((WildcardType)t1, t2);
        }
        return false;
    }

    private static boolean equals(ParameterizedType p, Type t) {
        if (t instanceof ParameterizedType) {
            ParameterizedType other = (ParameterizedType)t;
            if (TypeUtils.equals(p.getRawType(), other.getRawType()) && TypeUtils.equals(p.getOwnerType(), other.getOwnerType())) {
                return TypeUtils.equals(p.getActualTypeArguments(), other.getActualTypeArguments());
            }
        }
        return false;
    }

    private static boolean equals(GenericArrayType a, Type t) {
        return t instanceof GenericArrayType && TypeUtils.equals(a.getGenericComponentType(), ((GenericArrayType)t).getGenericComponentType());
    }

    private static boolean equals(WildcardType w, Type t) {
        if (t instanceof WildcardType) {
            WildcardType other = (WildcardType)t;
            return TypeUtils.equals(TypeUtils.getImplicitLowerBounds(w), TypeUtils.getImplicitLowerBounds(other)) && TypeUtils.equals(TypeUtils.getImplicitUpperBounds(w), TypeUtils.getImplicitUpperBounds(other));
        }
        return false;
    }

    private static boolean equals(Type[] t1, Type[] t2) {
        if (t1.length == t2.length) {
            for (int i = 0; i < t1.length; ++i) {
                if (TypeUtils.equals(t1[i], t2[i])) continue;
                return false;
            }
            return true;
        }
        return false;
    }

    public static String toString(Type type) {
        Validate.notNull(type);
        if (type instanceof Class) {
            return TypeUtils.classToString((Class)type);
        }
        if (type instanceof ParameterizedType) {
            return TypeUtils.parameterizedTypeToString((ParameterizedType)type);
        }
        if (type instanceof WildcardType) {
            return TypeUtils.wildcardTypeToString((WildcardType)type);
        }
        if (type instanceof TypeVariable) {
            return TypeUtils.typeVariableToString((TypeVariable)type);
        }
        if (type instanceof GenericArrayType) {
            return TypeUtils.genericArrayTypeToString((GenericArrayType)type);
        }
        throw new IllegalArgumentException(ObjectUtils.identityToString(type));
    }

    public static String toLongString(TypeVariable<?> var) {
        StringBuilder buf;
        block5: {
            Validate.notNull(var, "var is null", new Object[0]);
            buf = new StringBuilder();
            Object d = var.getGenericDeclaration();
            if (d instanceof Class) {
                Class<?> c = (Class<?>)d;
                while (true) {
                    if (c.getEnclosingClass() == null) {
                        buf.insert(0, c.getName());
                        break block5;
                    }
                    buf.insert(0, c.getSimpleName()).insert(0, '.');
                    c = c.getEnclosingClass();
                }
            }
            if (d instanceof Type) {
                buf.append(TypeUtils.toString((Type)d));
            } else {
                buf.append(d);
            }
        }
        return buf.append(':').append(TypeUtils.typeVariableToString(var)).toString();
    }

    public static <T> Typed<T> wrap(final Type type) {
        return new Typed<T>(){

            @Override
            public Type getType() {
                return type;
            }
        };
    }

    public static <T> Typed<T> wrap(Class<T> type) {
        return TypeUtils.wrap(type);
    }

    private static String classToString(Class<?> c) {
        if (c.isArray()) {
            return TypeUtils.toString(c.getComponentType()) + "[]";
        }
        StringBuilder buf = new StringBuilder();
        if (c.getEnclosingClass() != null) {
            buf.append(TypeUtils.classToString(c.getEnclosingClass())).append('.').append(c.getSimpleName());
        } else {
            buf.append(c.getName());
        }
        if (c.getTypeParameters().length > 0) {
            buf.append('<');
            TypeUtils.appendAllTo(buf, ", ", c.getTypeParameters());
            buf.append('>');
        }
        return buf.toString();
    }

    private static String typeVariableToString(TypeVariable<?> v) {
        StringBuilder buf = new StringBuilder(v.getName());
        Type[] bounds = v.getBounds();
        if (!(bounds.length <= 0 || bounds.length == 1 && Object.class.equals((Object)bounds[0]))) {
            buf.append(" extends ");
            TypeUtils.appendAllTo(buf, " & ", v.getBounds());
        }
        return buf.toString();
    }

    private static String parameterizedTypeToString(ParameterizedType p) {
        StringBuilder buf = new StringBuilder();
        Type useOwner = p.getOwnerType();
        Class raw = (Class)p.getRawType();
        if (useOwner == null) {
            buf.append(raw.getName());
        } else {
            if (useOwner instanceof Class) {
                buf.append(((Class)useOwner).getName());
            } else {
                buf.append(useOwner.toString());
            }
            buf.append('.').append(raw.getSimpleName());
        }
        int[] recursiveTypeIndexes = TypeUtils.findRecursiveTypes(p);
        if (recursiveTypeIndexes.length > 0) {
            TypeUtils.appendRecursiveTypes(buf, recursiveTypeIndexes, p.getActualTypeArguments());
        } else {
            TypeUtils.appendAllTo(buf.append('<'), ", ", p.getActualTypeArguments()).append('>');
        }
        return buf.toString();
    }

    private static void appendRecursiveTypes(StringBuilder buf, int[] recursiveTypeIndexes, Type[] argumentTypes) {
        for (int i = 0; i < recursiveTypeIndexes.length; ++i) {
            TypeUtils.appendAllTo(buf.append('<'), ", ", argumentTypes[i].toString()).append('>');
        }
        Type[] argumentsFiltered = ArrayUtils.removeAll(argumentTypes, recursiveTypeIndexes);
        if (argumentsFiltered.length > 0) {
            TypeUtils.appendAllTo(buf.append('<'), ", ", argumentsFiltered).append('>');
        }
    }

    private static int[] findRecursiveTypes(ParameterizedType p) {
        Type[] filteredArgumentTypes = Arrays.copyOf(p.getActualTypeArguments(), p.getActualTypeArguments().length);
        int[] indexesToRemove = new int[]{};
        for (int i = 0; i < filteredArgumentTypes.length; ++i) {
            if (!(filteredArgumentTypes[i] instanceof TypeVariable) || !TypeUtils.containsVariableTypeSameParametrizedTypeBound((TypeVariable)filteredArgumentTypes[i], p)) continue;
            indexesToRemove = ArrayUtils.add(indexesToRemove, i);
        }
        return indexesToRemove;
    }

    private static boolean containsVariableTypeSameParametrizedTypeBound(TypeVariable<?> typeVariable, ParameterizedType p) {
        return ArrayUtils.contains(typeVariable.getBounds(), p);
    }

    private static String wildcardTypeToString(WildcardType w) {
        StringBuilder buf = new StringBuilder().append('?');
        Type[] lowerBounds = w.getLowerBounds();
        Type[] upperBounds = w.getUpperBounds();
        if (lowerBounds.length > 1 || lowerBounds.length == 1 && lowerBounds[0] != null) {
            TypeUtils.appendAllTo(buf.append(" super "), " & ", lowerBounds);
        } else if (upperBounds.length > 1 || upperBounds.length == 1 && !Object.class.equals((Object)upperBounds[0])) {
            TypeUtils.appendAllTo(buf.append(" extends "), " & ", upperBounds);
        }
        return buf.toString();
    }

    private static String genericArrayTypeToString(GenericArrayType g2) {
        return String.format("%s[]", TypeUtils.toString(g2.getGenericComponentType()));
    }

    private static <T> StringBuilder appendAllTo(StringBuilder buf, String sep, T ... types) {
        Validate.notEmpty(Validate.noNullElements(types));
        if (types.length > 0) {
            buf.append(TypeUtils.toString(types[0]));
            for (int i = 1; i < types.length; ++i) {
                buf.append(sep).append(TypeUtils.toString(types[i]));
            }
        }
        return buf;
    }

    private static <T> String toString(T object) {
        return object instanceof Type ? TypeUtils.toString((Type)object) : object.toString();
    }

    private static final class WildcardTypeImpl
    implements WildcardType {
        private static final Type[] EMPTY_BOUNDS = new Type[0];
        private final Type[] upperBounds;
        private final Type[] lowerBounds;

        private WildcardTypeImpl(Type[] upperBounds, Type[] lowerBounds) {
            this.upperBounds = ObjectUtils.defaultIfNull(upperBounds, EMPTY_BOUNDS);
            this.lowerBounds = ObjectUtils.defaultIfNull(lowerBounds, EMPTY_BOUNDS);
        }

        @Override
        public Type[] getUpperBounds() {
            return (Type[])this.upperBounds.clone();
        }

        @Override
        public Type[] getLowerBounds() {
            return (Type[])this.lowerBounds.clone();
        }

        public String toString() {
            return TypeUtils.toString(this);
        }

        public boolean equals(Object obj) {
            return obj == this || obj instanceof WildcardType && TypeUtils.equals(this, (WildcardType)obj);
        }

        public int hashCode() {
            int result = 18688;
            result |= Arrays.hashCode(this.upperBounds);
            result <<= 8;
            return result |= Arrays.hashCode(this.lowerBounds);
        }
    }

    private static final class ParameterizedTypeImpl
    implements ParameterizedType {
        private final Class<?> raw;
        private final Type useOwner;
        private final Type[] typeArguments;

        private ParameterizedTypeImpl(Class<?> raw, Type useOwner, Type[] typeArguments) {
            this.raw = raw;
            this.useOwner = useOwner;
            this.typeArguments = (Type[])Arrays.copyOf(typeArguments, typeArguments.length, Type[].class);
        }

        @Override
        public Type getRawType() {
            return this.raw;
        }

        @Override
        public Type getOwnerType() {
            return this.useOwner;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return (Type[])this.typeArguments.clone();
        }

        public String toString() {
            return TypeUtils.toString(this);
        }

        public boolean equals(Object obj) {
            return obj == this || obj instanceof ParameterizedType && TypeUtils.equals(this, (ParameterizedType)obj);
        }

        public int hashCode() {
            int result = 1136;
            result |= this.raw.hashCode();
            result <<= 4;
            result |= Objects.hashCode(this.useOwner);
            result <<= 8;
            return result |= Arrays.hashCode(this.typeArguments);
        }
    }

    private static final class GenericArrayTypeImpl
    implements GenericArrayType {
        private final Type componentType;

        private GenericArrayTypeImpl(Type componentType) {
            this.componentType = componentType;
        }

        @Override
        public Type getGenericComponentType() {
            return this.componentType;
        }

        public String toString() {
            return TypeUtils.toString(this);
        }

        public boolean equals(Object obj) {
            return obj == this || obj instanceof GenericArrayType && TypeUtils.equals(this, (GenericArrayType)obj);
        }

        public int hashCode() {
            int result = 1072;
            return result |= this.componentType.hashCode();
        }
    }

    public static class WildcardTypeBuilder
    implements Builder<WildcardType> {
        private Type[] upperBounds;
        private Type[] lowerBounds;

        private WildcardTypeBuilder() {
        }

        public WildcardTypeBuilder withUpperBounds(Type ... bounds) {
            this.upperBounds = bounds;
            return this;
        }

        public WildcardTypeBuilder withLowerBounds(Type ... bounds) {
            this.lowerBounds = bounds;
            return this;
        }

        @Override
        public WildcardType build() {
            return new WildcardTypeImpl(this.upperBounds, this.lowerBounds);
        }
    }
}

