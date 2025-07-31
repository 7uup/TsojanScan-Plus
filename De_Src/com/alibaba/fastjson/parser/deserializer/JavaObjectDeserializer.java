/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class JavaObjectDeserializer
implements ObjectDeserializer {
    public static final JavaObjectDeserializer instance = new JavaObjectDeserializer();

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        if (type instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType)type).getGenericComponentType();
            if (componentType instanceof TypeVariable) {
                TypeVariable componentVar = (TypeVariable)componentType;
                componentType = componentVar.getBounds()[0];
            }
            ArrayList list = new ArrayList();
            parser.parseArray(componentType, list);
            Class<?> componentClass = TypeUtils.getRawClass(componentType);
            Object[] array = (Object[])Array.newInstance(componentClass, list.size());
            list.toArray(array);
            return (T)array;
        }
        if (type instanceof Class && type != Object.class && type != Serializable.class && type != Cloneable.class && type != Closeable.class && type != Comparable.class) {
            return parser.parseObject(type);
        }
        return (T)parser.parse(fieldName);
    }

    @Override
    public int getFastMatchToken() {
        return 12;
    }
}

