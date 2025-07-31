/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.math.BigDecimal;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class NumberDeserializer
implements ObjectDeserializer {
    public static final NumberDeserializer instance = new NumberDeserializer();

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        JSONLexer lexer = parser.lexer;
        if (lexer.token() == 2) {
            if (clazz == Double.TYPE || clazz == Double.class) {
                String val = lexer.numberString();
                lexer.nextToken(16);
                return (T)Double.valueOf(Double.parseDouble(val));
            }
            long val = lexer.longValue();
            lexer.nextToken(16);
            if (clazz == Short.TYPE || clazz == Short.class) {
                if (val > 32767L || val < -32768L) {
                    throw new JSONException("short overflow : " + val);
                }
                return (T)Short.valueOf((short)val);
            }
            if (clazz == Byte.TYPE || clazz == Byte.class) {
                if (val > 127L || val < -128L) {
                    throw new JSONException("short overflow : " + val);
                }
                return (T)Byte.valueOf((byte)val);
            }
            if (val >= Integer.MIN_VALUE && val <= Integer.MAX_VALUE) {
                return (T)Integer.valueOf((int)val);
            }
            return (T)Long.valueOf(val);
        }
        if (lexer.token() == 3) {
            if (clazz == Double.TYPE || clazz == Double.class) {
                String val = lexer.numberString();
                lexer.nextToken(16);
                return (T)Double.valueOf(Double.parseDouble(val));
            }
            if (clazz == Short.TYPE || clazz == Short.class) {
                BigDecimal val = lexer.decimalValue();
                lexer.nextToken(16);
                short shortValue = TypeUtils.shortValue(val);
                return (T)Short.valueOf(shortValue);
            }
            if (clazz == Byte.TYPE || clazz == Byte.class) {
                BigDecimal val = lexer.decimalValue();
                lexer.nextToken(16);
                byte byteValue = TypeUtils.byteValue(val);
                return (T)Byte.valueOf(byteValue);
            }
            BigDecimal val = lexer.decimalValue();
            lexer.nextToken(16);
            if (lexer.isEnabled(Feature.UseBigDecimal)) {
                return (T)val;
            }
            return (T)Double.valueOf(val.doubleValue());
        }
        if (lexer.token() == 18 && "NaN".equals(lexer.stringVal())) {
            lexer.nextToken();
            Number nan = null;
            if (clazz == Double.class) {
                nan = Double.NaN;
            } else if (clazz == Float.class) {
                nan = Float.valueOf(Float.NaN);
            }
            return (T)nan;
        }
        Object value = parser.parse();
        if (value == null) {
            return null;
        }
        if (clazz == Double.TYPE || clazz == Double.class) {
            try {
                return (T)TypeUtils.castToDouble(value);
            } catch (Exception ex) {
                throw new JSONException("parseDouble error, field : " + fieldName, ex);
            }
        }
        if (clazz == Short.TYPE || clazz == Short.class) {
            try {
                return (T)TypeUtils.castToShort(value);
            } catch (Exception ex) {
                throw new JSONException("parseShort error, field : " + fieldName, ex);
            }
        }
        if (clazz == Byte.TYPE || clazz == Byte.class) {
            try {
                return (T)TypeUtils.castToByte(value);
            } catch (Exception ex) {
                throw new JSONException("parseByte error, field : " + fieldName, ex);
            }
        }
        return (T)TypeUtils.castToBigDecimal(value);
    }

    @Override
    public int getFastMatchToken() {
        return 2;
    }
}

