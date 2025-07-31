/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class OptionalCodec
implements ObjectSerializer,
ObjectDeserializer {
    public static OptionalCodec instance = new OptionalCodec();

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        if (type == OptionalInt.class) {
            Integer obj = parser.parseObject(Integer.class);
            Integer value = TypeUtils.castToInt(obj);
            if (value == null) {
                return (T)OptionalInt.empty();
            }
            return (T)OptionalInt.of(value);
        }
        if (type == OptionalLong.class) {
            Long obj = parser.parseObject(Long.class);
            Long value = TypeUtils.castToLong(obj);
            if (value == null) {
                return (T)OptionalLong.empty();
            }
            return (T)OptionalLong.of(value);
        }
        if (type == OptionalDouble.class) {
            Double obj = parser.parseObject(Double.class);
            Double value = TypeUtils.castToDouble(obj);
            if (value == null) {
                return (T)OptionalDouble.empty();
            }
            return (T)OptionalDouble.of(value);
        }
        Object value = parser.parseObject(type = TypeUtils.unwrapOptional(type));
        if (value == null) {
            return (T)Optional.empty();
        }
        return (T)Optional.of(value);
    }

    @Override
    public int getFastMatchToken() {
        return 12;
    }

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        if (object == null) {
            serializer.writeNull();
            return;
        }
        if (object instanceof Optional) {
            Optional optional = (Optional)object;
            Object value = optional.isPresent() ? optional.get() : null;
            serializer.write((Object)value);
            return;
        }
        if (object instanceof OptionalDouble) {
            OptionalDouble optional = (OptionalDouble)object;
            if (optional.isPresent()) {
                double value = optional.getAsDouble();
                serializer.write(value);
            } else {
                serializer.writeNull();
            }
            return;
        }
        if (object instanceof OptionalInt) {
            OptionalInt optional = (OptionalInt)object;
            if (optional.isPresent()) {
                int value = optional.getAsInt();
                serializer.out.writeInt(value);
            } else {
                serializer.writeNull();
            }
            return;
        }
        if (object instanceof OptionalLong) {
            OptionalLong optional = (OptionalLong)object;
            if (optional.isPresent()) {
                long value = optional.getAsLong();
                serializer.out.writeLong(value);
            } else {
                serializer.writeNull();
            }
            return;
        }
        throw new JSONException("not support optional : " + object.getClass());
    }
}

