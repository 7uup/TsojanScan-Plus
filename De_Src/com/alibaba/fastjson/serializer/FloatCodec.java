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
import java.text.DecimalFormat;
import java.text.NumberFormat;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class FloatCodec
implements ObjectSerializer,
ObjectDeserializer {
    private NumberFormat decimalFormat;
    public static FloatCodec instance = new FloatCodec();

    public FloatCodec() {
    }

    public FloatCodec(DecimalFormat decimalFormat) {
        this.decimalFormat = decimalFormat;
    }

    public FloatCodec(String decimalFormat) {
        this(new DecimalFormat(decimalFormat));
    }

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull(SerializerFeature.WriteNullNumberAsZero);
            return;
        }
        float floatValue = ((Float)object).floatValue();
        if (this.decimalFormat != null) {
            String floatText = this.decimalFormat.format(floatValue);
            out.write(floatText);
        } else {
            out.writeFloat(floatValue, true);
        }
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        try {
            return FloatCodec.deserialze(parser);
        } catch (Exception ex) {
            throw new JSONException("parseLong error, field : " + fieldName, ex);
        }
    }

    public static <T> T deserialze(DefaultJSONParser parser) {
        JSONLexer lexer = parser.lexer;
        if (lexer.token() == 2) {
            String val = lexer.numberString();
            lexer.nextToken(16);
            return (T)Float.valueOf(Float.parseFloat(val));
        }
        if (lexer.token() == 3) {
            float val = lexer.floatValue();
            lexer.nextToken(16);
            return (T)Float.valueOf(val);
        }
        Object value = parser.parse();
        if (value == null) {
            return null;
        }
        return (T)TypeUtils.castToFloat(value);
    }

    @Override
    public int getFastMatchToken() {
        return 2;
    }
}

