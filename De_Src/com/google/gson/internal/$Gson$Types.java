/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.gson.internal;

import com.google.gson.internal.$Gson$Preconditions;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

public final class $Gson$Types {
    static final Type[] EMPTY_TYPE_ARRAY = new Type[0];

    private $Gson$Types() {
        throw new UnsupportedOperationException();
    }

    public static ParameterizedType newParameterizedTypeWithOwner(Type ownerType, Type rawType, Type ... typeArguments) {
        return new ParameterizedTypeImpl(ownerType, rawType, typeArguments);
    }

    public static GenericArrayType arrayOf(Type componentType) {
        return new GenericArrayTypeImpl(componentType);
    }

    public static WildcardType subtypeOf(Type bound) {
        Type[] upperBounds = bound instanceof WildcardType ? ((WildcardType)bound).getUpperBounds() : new Type[]{bound};
        return new WildcardTypeImpl(upperBounds, EMPTY_TYPE_ARRAY);
    }

    public static WildcardType supertypeOf(Type bound) {
        Type[] lowerBounds = bound instanceof WildcardType ? ((WildcardType)bound).getLowerBounds() : new Type[]{bound};
        return new WildcardTypeImpl(new Type[]{Object.class}, lowerBounds);
    }

    public static Type canonicalize(Type type) {
        if (type instanceof Class) {
            Class c = (Class)type;
            return c.isArray() ? new GenericArrayTypeImpl($Gson$Types.canonicalize(c.getComponentType())) : c;
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType p = (ParameterizedType)type;
            return new ParameterizedTypeImpl(p.getOwnerType(), p.getRawType(), p.getActualTypeArguments());
        }
        if (type instanceof GenericArrayType) {
            GenericArrayType g2 = (GenericArrayType)type;
            return new GenericArrayTypeImpl(g2.getGenericComponentType());
        }
        if (type instanceof WildcardType) {
            WildcardType w = (WildcardType)type;
            return new WildcardTypeImpl(w.getUpperBounds(), w.getLowerBounds());
        }
        return type;
    }

    public static Class<?> getRawType(Type type) {
        if (type instanceof Class) {
            return (Class)type;
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType)type;
            Type rawType = parameterizedType.getRawType();
            $Gson$Preconditions.checkArgument(rawType instanceof Class);
            return (Class)rawType;
        }
        if (type instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType)type).getGenericComponentType();
            return Array.newInstance($Gson$Types.getRawType(componentType), 0).getClass();
        }
        if (type instanceof TypeVariable) {
            return Object.class;
        }
        if (type instanceof WildcardType) {
            return $Gson$Types.getRawType(((WildcardType)type).getUpperBounds()[0]);
        }
        String className = type == null ? "null" : type.getClass().getName();
        throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + className);
    }

    static boolean equal(Object a, Object b) {
        return a == b || a != null && a.equals(b);
    }

    public static boolean equals(Type a, Type b) {
        if (a == b) {
            return true;
        }
        if (a instanceof Class) {
            return a.equals(b);
        }
        if (a instanceof ParameterizedType) {
            if (!(b instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType pa = (ParameterizedType)a;
            ParameterizedType pb = (ParameterizedType)b;
            return $Gson$Types.equal(pa.getOwnerType(), pb.getOwnerType()) && pa.getRawType().equals(pb.getRawType()) && Arrays.equals(pa.getActualTypeArguments(), pb.getActualTypeArguments());
        }
        if (a instanceof GenericArrayType) {
            if (!(b instanceof GenericArrayType)) {
                return false;
            }
            GenericArrayType ga = (GenericArrayType)a;
            GenericArrayType gb = (GenericArrayType)b;
            return $Gson$Types.equals(ga.getGenericComponentType(), gb.getGenericComponentType());
        }
        if (a instanceof WildcardType) {
            if (!(b instanceof WildcardType)) {
                return false;
            }
            WildcardType wa = (WildcardType)a;
            WildcardType wb = (WildcardType)b;
            return Arrays.equals(wa.getUpperBounds(), wb.getUpperBounds()) && Arrays.equals(wa.getLowerBounds(), wb.getLowerBounds());
        }
        if (a instanceof TypeVariable) {
            if (!(b instanceof TypeVariable)) {
                return false;
            }
            TypeVariable va = (TypeVariable)a;
            TypeVariable vb = (TypeVariable)b;
            return va.getGenericDeclaration() == vb.getGenericDeclaration() && va.getName().equals(vb.getName());
        }
        return false;
    }

    static int hashCodeOrZero(Object o) {
        return o != null ? o.hashCode() : 0;
    }

    public static String typeToString(Type type) {
        return type instanceof Class ? ((Class)type).getName() : type.toString();
    }

    static Type getGenericSupertype(Type context, Class<?> rawType, Class<?> toResolve) {
        if (toResolve == rawType) {
            return context;
        }
        if (toResolve.isInterface()) {
            Class<?>[] interfaces = rawType.getInterfaces();
            int length = interfaces.length;
            for (int i = 0; i < length; ++i) {
                if (interfaces[i] == toResolve) {
                    return rawType.getGenericInterfaces()[i];
                }
                if (!toResolve.isAssignableFrom(interfaces[i])) continue;
                return $Gson$Types.getGenericSupertype(rawType.getGenericInterfaces()[i], interfaces[i], toResolve);
            }
        }
        if (!rawType.isInterface()) {
            while (rawType != Object.class) {
                Class<?> rawSupertype = rawType.getSuperclass();
                if (rawSupertype == toResolve) {
                    return rawType.getGenericSuperclass();
                }
                if (toResolve.isAssignableFrom(rawSupertype)) {
                    return $Gson$Types.getGenericSupertype(rawType.getGenericSuperclass(), rawSupertype, toResolve);
                }
                rawType = rawSupertype;
            }
        }
        return toResolve;
    }

    static Type getSupertype(Type context, Class<?> contextRawType, Class<?> supertype) {
        if (context instanceof WildcardType) {
            context = ((WildcardType)context).getUpperBounds()[0];
        }
        $Gson$Preconditions.checkArgument(supertype.isAssignableFrom(contextRawType));
        return $Gson$Types.resolve(context, contextRawType, $Gson$Types.getGenericSupertype(context, contextRawType, supertype));
    }

    public static Type getArrayComponentType(Type array) {
        return array instanceof GenericArrayType ? ((GenericArrayType)array).getGenericComponentType() : ((Class)array).getComponentType();
    }

    public static Type getCollectionElementType(Type context, Class<?> contextRawType) {
        Type collectionType = $Gson$Types.getSupertype(context, contextRawType, Collection.class);
        if (collectionType instanceof WildcardType) {
            collectionType = ((WildcardType)collectionType).getUpperBounds()[0];
        }
        if (collectionType instanceof ParameterizedType) {
            return ((ParameterizedType)collectionType).getActualTypeArguments()[0];
        }
        return Object.class;
    }

    public static Type[] getMapKeyAndValueTypes(Type context, Class<?> contextRawType) {
        if (context == Properties.class) {
            return new Type[]{String.class, String.class};
        }
        Type mapType = $Gson$Types.getSupertype(context, contextRawType, Map.class);
        if (mapType instanceof ParameterizedType) {
            ParameterizedType mapParameterizedType = (ParameterizedType)mapType;
            return mapParameterizedType.getActualTypeArguments();
        }
        return new Type[]{Object.class, Object.class};
    }

    public static Type resolve(Type context, Class<?> contextRawType, Type toResolve) {
        return $Gson$Types.resolve(context, contextRawType, toResolve, new HashSet<TypeVariable>());
    }

    private static Type resolve(Type context, Class<?> contextRawType, Type toResolve, Collection<TypeVariable> visitedTypeVariables) {
        Type original;
        while (toResolve instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable)toResolve;
            if (visitedTypeVariables.contains(typeVariable)) {
                return toResolve;
            }
            visitedTypeVariables.add(typeVariable);
            toResolve = $Gson$Types.resolveTypeVariable(context, contextRawType, typeVariable);
            if (toResolve != typeVariable) continue;
            return toResolve;
        }
        if (toResolve instanceof Class && ((Class)toResolve).isArray()) {
            Type newComponentType;
            original = (Class)toResolve;
            Class<?> componentType = ((Class)original).getComponentType();
            return componentType == (newComponentType = $Gson$Types.resolve(context, contextRawType, componentType, visitedTypeVariables)) ? original : $Gson$Types.arrayOf(newComponentType);
        }
        if (toResolve instanceof GenericArrayType) {
            Type newComponentType;
            original = (GenericArrayType)toResolve;
            Type componentType = original.getGenericComponentType();
            return componentType == (newComponentType = $Gson$Types.resolve(context, contextRawType, componentType, visitedTypeVariables)) ? original : $Gson$Types.arrayOf(newComponentType);
        }
        if (toResolve instanceof ParameterizedType) {
            original = (ParameterizedType)toResolve;
            Type ownerType = original.getOwnerType();
            Type newOwnerType = $Gson$Types.resolve(context, contextRawType, ownerType, visitedTypeVariables);
            boolean changed = newOwnerType != ownerType;
            Type[] args2 = original.getActualTypeArguments();
            int length = args2.length;
            for (int t = 0; t < length; ++t) {
                Type resolvedTypeArgument = $Gson$Types.resolve(context, contextRawType, args2[t], visitedTypeVariables);
                if (resolvedTypeArgument == args2[t]) continue;
                if (!changed) {
                    args2 = (Type[])args2.clone();
                    changed = true;
                }
                args2[t] = resolvedTypeArgument;
            }
            return changed ? $Gson$Types.newParameterizedTypeWithOwner(newOwnerType, original.getRawType(), args2) : original;
        }
        if (toResolve instanceof WildcardType) {
            Type upperBound;
            original = (WildcardType)toResolve;
            Type[] originalLowerBound = original.getLowerBounds();
            Type[] originalUpperBound = original.getUpperBounds();
            if (originalLowerBound.length == 1) {
                Type lowerBound = $Gson$Types.resolve(context, contextRawType, originalLowerBound[0], visitedTypeVariables);
                if (lowerBound != originalLowerBound[0]) {
                    return $Gson$Types.supertypeOf(lowerBound);
                }
            } else if (originalUpperBound.length == 1 && (upperBound = $Gson$Types.resolve(context, contextRawType, originalUpperBound[0], visitedTypeVariables)) != originalUpperBound[0]) {
                return $Gson$Types.subtypeOf(upperBound);
            }
            return original;
        }
        return toResolve;
    }

    static Type resolveTypeVariable(Type context, Class<?> contextRawType, TypeVariable<?> unknown) {
        Class<?> declaredByRaw = $Gson$Types.declaringClassOf(unknown);
        if (declaredByRaw == null) {
            return unknown;
        }
        Type declaredBy = $Gson$Types.getGenericSupertype(context, contextRawType, declaredByRaw);
        if (declaredBy instanceof ParameterizedType) {
            int index = $Gson$Types.indexOf(declaredByRaw.getTypeParameters(), unknown);
            return ((ParameterizedType)declaredBy).getActualTypeArguments()[index];
        }
        return unknown;
    }

    private static int indexOf(Object[] array, Object toFind) {
        int length = array.length;
        for (int i = 0; i < length; ++i) {
            if (!toFind.equals(array[i])) continue;
            return i;
        }
        throw new NoSuchElementException();
    }

    private static Class<?> declaringClassOf(TypeVariable<?> typeVariable) {
        Object genericDeclaration = typeVariable.getGenericDeclaration();
        return genericDeclaration instanceof Class ? (Class)genericDeclaration : null;
    }

    static void checkNotPrimitive(Type type) {
        $Gson$Preconditions.checkArgument(!(type instanceof Class) || !((Class)type).isPrimitive());
    }

    private static final class WildcardTypeImpl
    implements WildcardType,
    Serializable {
        private final Type upperBound;
        private final Type lowerBound;
        private static final long serialVersionUID = 0L;

        public WildcardTypeImpl(Type[] upperBounds, Type[] lowerBounds) {
            $Gson$Preconditions.checkArgument(lowerBounds.length <= 1);
            $Gson$Preconditions.checkArgument(upperBounds.length == 1);
            if (lowerBounds.length == 1) {
                $Gson$Preconditions.checkNotNull(lowerBounds[0]);
                $Gson$Types.checkNotPrimitive(lowerBounds[0]);
                $Gson$Preconditions.checkArgument(upperBounds[0] == Object.class);
                this.lowerBound = $Gson$Types.canonicalize(lowerBounds[0]);
                this.upperBound = Object.class;
            } else {
                $Gson$Preconditions.checkNotNull(upperBounds[0]);
                $Gson$Types.checkNotPrimitive(upperBounds[0]);
                this.lowerBound = null;
                this.upperBound = $Gson$Types.canonicalize(upperBounds[0]);
            }
        }

        @Override
        public Type[] getUpperBounds() {
            return new Type[]{this.upperBound};
        }

        @Override
        public Type[] getLowerBounds() {
            Type[] typeArray;
            if (this.lowerBound != null) {
                Type[] typeArray2 = new Type[1];
                typeArray = typeArray2;
                typeArray2[0] = this.lowerBound;
            } else {
                typeArray = EMPTY_TYPE_ARRAY;
            }
            return typeArray;
        }

        public boolean equals(Object other) {
            return other instanceof WildcardType && $Gson$Types.equals(this, (WildcardType)other);
        }

        public int hashCode() {
            return (this.lowerBound != null ? 31 + this.lowerBound.hashCode() : 1) ^ 31 + this.upperBound.hashCode();
        }

        public String toString() {
            if (this.lowerBound != null) {
                return "? super " + $Gson$Types.typeToString(this.lowerBound);
            }
            if (this.upperBound == Object.class) {
                return "?";
            }
            return "? extends " + $Gson$Types.typeToString(this.upperBound);
        }
    }

    private static final class GenericArrayTypeImpl
    implements GenericArrayType,
    Serializable {
        private final Type componentType;
        private static final long serialVersionUID = 0L;

        public GenericArrayTypeImpl(Type componentType) {
            this.componentType = $Gson$Types.canonicalize(componentType);
        }

        @Override
        public Type getGenericComponentType() {
            return this.componentType;
        }

        public boolean equals(Object o) {
            return o instanceof GenericArrayType && $Gson$Types.equals(this, (GenericArrayType)o);
        }

        public int hashCode() {
            return this.componentType.hashCode();
        }

        public String toString() {
            return $Gson$Types.typeToString(this.componentType) + "[]";
        }
    }

    private static final class ParameterizedTypeImpl
    implements ParameterizedType,
    Serializable {
        private final Type ownerType;
        private final Type rawType;
        private final Type[] typeArguments;
        private static final long serialVersionUID = 0L;

        public ParameterizedTypeImpl(Type ownerType, Type rawType, Type ... typeArguments) {
            if (rawType instanceof Class) {
                Class rawTypeAsClass = (Class)rawType;
                boolean isStaticOrTopLevelClass = Modifier.isStatic(rawTypeAsClass.getModifiers()) || rawTypeAsClass.getEnclosingClass() == null;
                $Gson$Preconditions.checkArgument(ownerType != null || isStaticOrTopLevelClass);
            }
            this.ownerType = ownerType == null ? null : $Gson$Types.canonicalize(ownerType);
            this.rawType = $Gson$Types.canonicalize(rawType);
            this.typeArguments = (Type[])typeArguments.clone();
            int length = this.typeArguments.length;
            for (int t = 0; t < length; ++t) {
                $Gson$Preconditions.checkNotNull(this.typeArguments[t]);
                $Gson$Types.checkNotPrimitive(this.typeArguments[t]);
                this.typeArguments[t] = $Gson$Types.canonicalize(this.typeArguments[t]);
            }
        }

        @Override
        public Type[] getActualTypeArguments() {
            return (Type[])this.typeArguments.clone();
        }

        @Override
        public Type getRawType() {
            return this.rawType;
        }

        @Override
        public Type getOwnerType() {
            return this.ownerType;
        }

        public boolean equals(Object other) {
            return other instanceof ParameterizedType && $Gson$Types.equals(this, (ParameterizedType)other);
        }

        public int hashCode() {
            return Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode() ^ $Gson$Types.hashCodeOrZero(this.ownerType);
        }

        public String toString() {
            int length = this.typeArguments.length;
            if (length == 0) {
                return $Gson$Types.typeToString(this.rawType);
            }
            StringBuilder stringBuilder = new StringBuilder(30 * (length + 1));
            stringBuilder.append($Gson$Types.typeToString(this.rawType)).append("<").append($Gson$Types.typeToString(this.typeArguments[0]));
            for (int i = 1; i < length; ++i) {
                stringBuilder.append(", ").append($Gson$Types.typeToString(this.typeArguments[i]));
            }
            return stringBuilder.append(">").toString();
        }
    }
}

