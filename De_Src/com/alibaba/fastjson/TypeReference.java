/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson;

import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TypeReference<T> {
    static ConcurrentMap<Type, Type> classTypeCache = new ConcurrentHashMap<Type, Type>(16, 0.75f, 1);
    protected final Type type;
    public static final Type LIST_STRING = new TypeReference<List<String>>(){}.getType();

    protected TypeReference() {
        Type superClass = this.getClass().getGenericSuperclass();
        Type type = ((ParameterizedType)superClass).getActualTypeArguments()[0];
        Type cachedType = (Type)classTypeCache.get(type);
        if (cachedType == null) {
            classTypeCache.putIfAbsent(type, type);
            cachedType = (Type)classTypeCache.get(type);
        }
        this.type = cachedType;
    }

    protected TypeReference(Type ... actualTypeArguments) {
        Class<?> thisClass = this.getClass();
        Type superClass = thisClass.getGenericSuperclass();
        ParameterizedType argType = (ParameterizedType)((ParameterizedType)superClass).getActualTypeArguments()[0];
        Type rawType = argType.getRawType();
        Type[] argTypes = argType.getActualTypeArguments();
        int actualIndex = 0;
        for (int i = 0; i < argTypes.length; ++i) {
            if (argTypes[i] instanceof TypeVariable && actualIndex < actualTypeArguments.length) {
                argTypes[i] = actualTypeArguments[actualIndex++];
            }
            if (argTypes[i] instanceof GenericArrayType) {
                argTypes[i] = TypeUtils.checkPrimitiveArray((GenericArrayType)argTypes[i]);
            }
            if (!(argTypes[i] instanceof ParameterizedType)) continue;
            argTypes[i] = this.handlerParameterizedType((ParameterizedType)argTypes[i], actualTypeArguments, actualIndex);
        }
        ParameterizedTypeImpl key = new ParameterizedTypeImpl(argTypes, thisClass, rawType);
        Type cachedType = (Type)classTypeCache.get(key);
        if (cachedType == null) {
            classTypeCache.putIfAbsent(key, key);
            cachedType = (Type)classTypeCache.get(key);
        }
        this.type = cachedType;
    }

    public static Type intern(ParameterizedTypeImpl type) {
        Type cachedType = (Type)classTypeCache.get(type);
        if (cachedType == null) {
            classTypeCache.putIfAbsent(type, type);
            cachedType = (Type)classTypeCache.get(type);
        }
        return cachedType;
    }

    private Type handlerParameterizedType(ParameterizedType type, Type[] actualTypeArguments, int actualIndex) {
        Class<?> thisClass = this.getClass();
        Type rawType = type.getRawType();
        Type[] argTypes = type.getActualTypeArguments();
        for (int i = 0; i < argTypes.length; ++i) {
            if (argTypes[i] instanceof TypeVariable && actualIndex < actualTypeArguments.length) {
                argTypes[i] = actualTypeArguments[actualIndex++];
            }
            if (argTypes[i] instanceof GenericArrayType) {
                argTypes[i] = TypeUtils.checkPrimitiveArray((GenericArrayType)argTypes[i]);
            }
            if (!(argTypes[i] instanceof ParameterizedType)) continue;
            argTypes[i] = this.handlerParameterizedType((ParameterizedType)argTypes[i], actualTypeArguments, actualIndex);
        }
        ParameterizedTypeImpl key = new ParameterizedTypeImpl(argTypes, thisClass, rawType);
        return key;
    }

    public Type getType() {
        return this.type;
    }
}

