/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
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
import java.util.concurrent.atomic.AtomicBoolean;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class BooleanCodec
implements ObjectSerializer,
ObjectDeserializer {
    public static final BooleanCodec instance = new BooleanCodec();

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        Boolean value = (Boolean)object;
        if (value == null) {
            out.writeNull(SerializerFeature.WriteNullBooleanAsFalse);
            return;
        }
        if (value.booleanValue()) {
            out.write("true");
        } else {
            out.write("false");
        }
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        Boolean boolObj;
        JSONLexer lexer = parser.lexer;
        try {
            if (lexer.token() == 6) {
                lexer.nextToken(16);
                boolObj = Boolean.TRUE;
            } else if (lexer.token() == 7) {
                lexer.nextToken(16);
                boolObj = Boolean.FALSE;
            } else if (lexer.token() == 2) {
                int intValue = lexer.intValue();
                lexer.nextToken(16);
                boolObj = intValue == 1 ? Boolean.TRUE : Boolean.FALSE;
            } else {
                Object value = parser.parse();
                if (value == null) {
                    return null;
                }
                boolObj = TypeUtils.castToBoolean(value);
            }
        } catch (Exception ex) {
            throw new JSONException("parseBoolean error, field : " + fieldName, ex);
        }
        if (clazz == AtomicBoolean.class) {
            return (T)new AtomicBoolean(boolObj);
        }
        return (T)boolObj;
    }

    @Override
    public int getFastMatchToken() {
        return 6;
    }
}

