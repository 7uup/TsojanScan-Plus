/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicReference;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ReferenceCodec
implements ObjectSerializer,
ObjectDeserializer {
    public static final ReferenceCodec instance = new ReferenceCodec();

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        Object item;
        if (object instanceof AtomicReference) {
            AtomicReference val = (AtomicReference)object;
            item = val.get();
        } else {
            item = ((Reference)object).get();
        }
        serializer.write(item);
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        ParameterizedType paramType = (ParameterizedType)type;
        Type itemType = paramType.getActualTypeArguments()[0];
        Object itemObject = parser.parseObject(itemType);
        Type rawType = paramType.getRawType();
        if (rawType == AtomicReference.class) {
            return (T)new AtomicReference(itemObject);
        }
        if (rawType == WeakReference.class) {
            return (T)new WeakReference(itemObject);
        }
        if (rawType == SoftReference.class) {
            return (T)new SoftReference(itemObject);
        }
        throw new UnsupportedOperationException(rawType.toString());
    }

    @Override
    public int getFastMatchToken() {
        return 12;
    }
}

