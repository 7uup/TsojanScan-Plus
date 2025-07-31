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
import java.math.BigDecimal;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class BigDecimalCodec
implements ObjectSerializer,
ObjectDeserializer {
    static final BigDecimal LOW = BigDecimal.valueOf(-9007199254740991L);
    static final BigDecimal HIGH = BigDecimal.valueOf(0x1FFFFFFFFFFFFFL);
    public static final BigDecimalCodec instance = new BigDecimalCodec();

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull(SerializerFeature.WriteNullNumberAsZero);
        } else {
            BigDecimal val = (BigDecimal)object;
            int scale = val.scale();
            String outText = SerializerFeature.isEnabled(features, out.features, SerializerFeature.WriteBigDecimalAsPlain) && scale >= -100 && scale < 100 ? val.toPlainString() : val.toString();
            if (scale == 0 && outText.length() >= 16 && SerializerFeature.isEnabled(features, out.features, SerializerFeature.BrowserCompatible) && (val.compareTo(LOW) < 0 || val.compareTo(HIGH) > 0)) {
                out.writeString(outText);
                return;
            }
            out.write(outText);
            if (out.isEnabled(SerializerFeature.WriteClassName) && fieldType != BigDecimal.class && val.scale() == 0) {
                out.write(46);
            }
        }
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        try {
            return BigDecimalCodec.deserialze(parser);
        } catch (Exception ex) {
            throw new JSONException("parseDecimal error, field : " + fieldName, ex);
        }
    }

    public static <T> T deserialze(DefaultJSONParser parser) {
        JSONLexer lexer = parser.lexer;
        if (lexer.token() == 2) {
            BigDecimal decimalValue = lexer.decimalValue();
            lexer.nextToken(16);
            return (T)decimalValue;
        }
        if (lexer.token() == 3) {
            BigDecimal val = lexer.decimalValue();
            lexer.nextToken(16);
            return (T)val;
        }
        Object value = parser.parse();
        return (T)(value == null ? null : TypeUtils.castToBigDecimal(value));
    }

    @Override
    public int getFastMatchToken() {
        return 2;
    }
}

