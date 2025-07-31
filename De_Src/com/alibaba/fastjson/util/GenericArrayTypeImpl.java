/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.util;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

public class GenericArrayTypeImpl
implements GenericArrayType {
    private final Type genericComponentType;

    public GenericArrayTypeImpl(Type genericComponentType) {
        assert (genericComponentType != null);
        this.genericComponentType = genericComponentType;
    }

    public Type getGenericComponentType() {
        return this.genericComponentType;
    }

    public String toString() {
        Type genericComponentType = this.getGenericComponentType();
        StringBuilder builder = new StringBuilder();
        if (genericComponentType instanceof Class) {
            builder.append(((Class)genericComponentType).getName());
        } else {
            builder.append(genericComponentType.toString());
        }
        builder.append("[]");
        return builder.toString();
    }

    public boolean equals(Object obj) {
        if (obj instanceof GenericArrayType) {
            GenericArrayType that = (GenericArrayType)obj;
            return this.genericComponentType.equals(that.getGenericComponentType());
        }
        return false;
    }

    public int hashCode() {
        return this.genericComponentType.hashCode();
    }
}

