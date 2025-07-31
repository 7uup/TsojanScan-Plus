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
import java.math.BigInteger;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class BigIntegerCodec
implements ObjectSerializer,
ObjectDeserializer {
    private static final BigInteger LOW = BigInteger.valueOf(-9007199254740991L);
    private static final BigInteger HIGH = BigInteger.valueOf(0x1FFFFFFFFFFFFFL);
    public static final BigIntegerCodec instance = new BigIntegerCodec();

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull(SerializerFeature.WriteNullNumberAsZero);
            return;
        }
        BigInteger val = (BigInteger)object;
        String str = val.toString();
        if (str.length() >= 16 && SerializerFeature.isEnabled(features, out.features, SerializerFeature.BrowserCompatible) && (val.compareTo(LOW) < 0 || val.compareTo(HIGH) > 0)) {
            out.writeString(str);
            return;
        }
        out.write(str);
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        return BigIntegerCodec.deserialze(parser);
    }

    public static <T> T deserialze(DefaultJSONParser parser) {
        JSONLexer lexer = parser.lexer;
        if (lexer.token() == 2) {
            String val = lexer.numberString();
            lexer.nextToken(16);
            if (val.length() > 65535) {
                throw new JSONException("decimal overflow");
            }
            return (T)new BigInteger(val);
        }
        Object value = parser.parse();
        return (T)(value == null ? null : TypeUtils.castToBigInteger(value));
    }

    @Override
    public int getFastMatchToken() {
        return 2;
    }
}

