/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class CharacterCodec
implements ObjectSerializer,
ObjectDeserializer {
    public static final CharacterCodec instance = new CharacterCodec();

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        Character value = (Character)object;
        if (value == null) {
            out.writeString("");
            return;
        }
        char c = value.charValue();
        if (c == '\u0000') {
            out.writeString("\u0000");
        } else {
            out.writeString(value.toString());
        }
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        Object value = parser.parse();
        return (T)(value == null ? null : TypeUtils.castToChar(value));
    }

    @Override
    public int getFastMatchToken() {
        return 4;
    }
}

