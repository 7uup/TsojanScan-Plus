/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class StringCodec
implements ObjectSerializer,
ObjectDeserializer {
    public static StringCodec instance = new StringCodec();

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        this.write(serializer, (String)object);
    }

    public void write(JSONSerializer serializer, String value) {
        SerializeWriter out = serializer.out;
        if (value == null) {
            out.writeNull(SerializerFeature.WriteNullStringAsEmpty);
            return;
        }
        out.writeString(value);
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        if (clazz == StringBuffer.class) {
            JSONLexer lexer = parser.lexer;
            if (lexer.token() == 4) {
                String val = lexer.stringVal();
                lexer.nextToken(16);
                return (T)new StringBuffer(val);
            }
            Object value = parser.parse();
            if (value == null) {
                return null;
            }
            return (T)new StringBuffer(value.toString());
        }
        if (clazz == StringBuilder.class) {
            JSONLexer lexer = parser.lexer;
            if (lexer.token() == 4) {
                String val = lexer.stringVal();
                lexer.nextToken(16);
                return (T)new StringBuilder(val);
            }
            Object value = parser.parse();
            if (value == null) {
                return null;
            }
            return (T)new StringBuilder(value.toString());
        }
        return StringCodec.deserialze(parser);
    }

    public static <T> T deserialze(DefaultJSONParser parser) {
        JSONLexer lexer = parser.getLexer();
        if (lexer.token() == 4) {
            String val = lexer.stringVal();
            lexer.nextToken(16);
            return (T)val;
        }
        if (lexer.token() == 2) {
            String val = lexer.numberString();
            lexer.nextToken(16);
            return (T)val;
        }
        Object value = parser.parse();
        if (value == null) {
            return null;
        }
        return (T)value.toString();
    }

    @Override
    public int getFastMatchToken() {
        return 4;
    }
}

