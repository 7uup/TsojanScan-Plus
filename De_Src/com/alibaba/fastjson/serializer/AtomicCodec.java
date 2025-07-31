/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class AtomicCodec
implements ObjectSerializer,
ObjectDeserializer {
    public static final AtomicCodec instance = new AtomicCodec();

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object instanceof AtomicInteger) {
            AtomicInteger val = (AtomicInteger)object;
            out.writeInt(val.get());
            return;
        }
        if (object instanceof AtomicLong) {
            AtomicLong val = (AtomicLong)object;
            out.writeLong(val.get());
            return;
        }
        if (object instanceof AtomicBoolean) {
            AtomicBoolean val = (AtomicBoolean)object;
            out.append(val.get() ? "true" : "false");
            return;
        }
        if (object == null) {
            out.writeNull(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        if (object instanceof AtomicIntegerArray) {
            AtomicIntegerArray array = (AtomicIntegerArray)object;
            int len = array.length();
            out.write(91);
            for (int i = 0; i < len; ++i) {
                int val = array.get(i);
                if (i != 0) {
                    out.write(44);
                }
                out.writeInt(val);
            }
            out.write(93);
            return;
        }
        AtomicLongArray array = (AtomicLongArray)object;
        int len = array.length();
        out.write(91);
        for (int i = 0; i < len; ++i) {
            long val = array.get(i);
            if (i != 0) {
                out.write(44);
            }
            out.writeLong(val);
        }
        out.write(93);
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        if (parser.lexer.token() == 8) {
            parser.lexer.nextToken(16);
            return null;
        }
        JSONArray array = new JSONArray();
        parser.parseArray(array);
        if (clazz == AtomicIntegerArray.class) {
            AtomicIntegerArray atomicArray = new AtomicIntegerArray(array.size());
            for (int i = 0; i < array.size(); ++i) {
                atomicArray.set(i, array.getInteger(i));
            }
            return (T)atomicArray;
        }
        AtomicLongArray atomicArray = new AtomicLongArray(array.size());
        for (int i = 0; i < array.size(); ++i) {
            atomicArray.set(i, array.getLong(i));
        }
        return (T)atomicArray;
    }

    @Override
    public int getFastMatchToken() {
        return 14;
    }
}

