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
import java.util.concurrent.atomic.AtomicLong;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class LongCodec
implements ObjectSerializer,
ObjectDeserializer {
    public static LongCodec instance = new LongCodec();

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull(SerializerFeature.WriteNullNumberAsZero);
        } else {
            long value = (Long)object;
            out.writeLong(value);
            if (out.isEnabled(SerializerFeature.WriteClassName) && value <= Integer.MAX_VALUE && value >= Integer.MIN_VALUE && fieldType != Long.class && fieldType != Long.TYPE) {
                out.write(76);
            }
        }
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        Long longObject;
        JSONLexer lexer = parser.lexer;
        try {
            int token = lexer.token();
            if (token == 2) {
                long longValue = lexer.longValue();
                lexer.nextToken(16);
                longObject = longValue;
            } else if (token == 3) {
                BigDecimal number = lexer.decimalValue();
                longObject = TypeUtils.longValue(number);
                lexer.nextToken(16);
            } else {
                if (token == 12) {
                    JSONObject jsonObject = new JSONObject(true);
                    parser.parseObject(jsonObject);
                    longObject = TypeUtils.castToLong(jsonObject);
                } else {
                    Object value = parser.parse();
                    longObject = TypeUtils.castToLong(value);
                }
                if (longObject == null) {
                    return null;
                }
            }
        } catch (Exception ex) {
            throw new JSONException("parseLong error, field : " + fieldName, ex);
        }
        return (T)(clazz == AtomicLong.class ? new AtomicLong(longObject) : longObject);
    }

    @Override
    public int getFastMatchToken() {
        return 2;
    }
}

