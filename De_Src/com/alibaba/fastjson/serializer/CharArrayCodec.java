/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.lang.reflect.Type;
import java.util.Collection;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class CharArrayCodec
implements ObjectDeserializer {
    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        return CharArrayCodec.deserialze(parser);
    }

    public static <T> T deserialze(DefaultJSONParser parser) {
        JSONLexer lexer = parser.lexer;
        if (lexer.token() == 4) {
            String val = lexer.stringVal();
            lexer.nextToken(16);
            return (T)val.toCharArray();
        }
        if (lexer.token() == 2) {
            Number val = lexer.integerValue();
            lexer.nextToken(16);
            return (T)val.toString().toCharArray();
        }
        Object value = parser.parse();
        if (value instanceof String) {
            return (T)((String)value).toCharArray();
        }
        if (value instanceof Collection) {
            Collection collection = (Collection)value;
            boolean accept = true;
            for (Object item : collection) {
                int itemLength;
                if (!(item instanceof String) || (itemLength = ((String)item).length()) == 1) continue;
                accept = false;
                break;
            }
            if (!accept) {
                throw new JSONException("can not cast to char[]");
            }
            char[] chars = new char[collection.size()];
            int pos = 0;
            for (Object item : collection) {
                chars[pos++] = ((String)item).charAt(0);
            }
            return (T)chars;
        }
        return (T)(value == null ? null : JSON.toJSONString(value).toCharArray());
    }

    @Override
    public int getFastMatchToken() {
        return 4;
    }
}

