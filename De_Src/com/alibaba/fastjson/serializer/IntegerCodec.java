/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class IntegerCodec
implements ObjectSerializer,
ObjectDeserializer {
    public static IntegerCodec instance = new IntegerCodec();

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        Number value = (Number)object;
        if (value == null) {
            out.writeNull(SerializerFeature.WriteNullNumberAsZero);
            return;
        }
        if (object instanceof Long) {
            out.writeLong(value.longValue());
        } else {
            out.writeInt(value.intValue());
        }
        if (out.isEnabled(SerializerFeature.WriteClassName)) {
            Class<?> clazz = value.getClass();
            if (clazz == Byte.class) {
                out.write(66);
            } else if (clazz == Short.class) {
                out.write(83);
            }
        }
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        Integer intObj;
        JSONLexer lexer = parser.lexer;
        int token = lexer.token();
        if (token == 8) {
            lexer.nextToken(16);
            return null;
        }
        try {
            if (token == 2) {
                int val = lexer.intValue();
                lexer.nextToken(16);
                intObj = val;
            } else if (token == 3) {
                BigDecimal number = lexer.decimalValue();
                intObj = TypeUtils.intValue(number);
                lexer.nextToken(16);
            } else if (token == 12) {
                JSONObject jsonObject = new JSONObject(true);
                parser.parseObject(jsonObject);
                intObj = TypeUtils.castToInt(jsonObject);
            } else {
                Object value = parser.parse();
                intObj = TypeUtils.castToInt(value);
            }
        } catch (Exception ex) {
            String message = "parseInt error";
            if (fieldName != null) {
                message = message + ", field : " + fieldName;
            }
            throw new JSONException(message, ex);
        }
        if (clazz == AtomicInteger.class) {
            return (T)new AtomicInteger(intObj);
        }
        return (T)intObj;
    }

    @Override
    public int getFastMatchToken() {
        return 2;
    }
}

