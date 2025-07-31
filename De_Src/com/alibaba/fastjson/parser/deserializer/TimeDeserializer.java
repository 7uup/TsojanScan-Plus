/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Time;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TimeDeserializer
implements ObjectDeserializer {
    public static final TimeDeserializer instance = new TimeDeserializer();

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        JSONLexer lexer = parser.lexer;
        if (lexer.token() == 16) {
            lexer.nextToken(4);
            if (lexer.token() != 4) {
                throw new JSONException("syntax error");
            }
            lexer.nextTokenWithColon(2);
            if (lexer.token() != 2) {
                throw new JSONException("syntax error");
            }
            long time = lexer.longValue();
            lexer.nextToken(13);
            if (lexer.token() != 13) {
                throw new JSONException("syntax error");
            }
            lexer.nextToken(16);
            return (T)new Time(time);
        }
        Object val = parser.parse();
        if (val == null) {
            return null;
        }
        if (val instanceof Time) {
            return (T)val;
        }
        if (val instanceof BigDecimal) {
            return (T)new Time(TypeUtils.longValue((BigDecimal)val));
        }
        if (val instanceof Number) {
            return (T)new Time(((Number)val).longValue());
        }
        if (val instanceof String) {
            long longVal;
            String strVal = (String)val;
            if (strVal.length() == 0) {
                return null;
            }
            JSONScanner dateLexer = new JSONScanner(strVal);
            if (dateLexer.scanISO8601DateIfMatch()) {
                longVal = dateLexer.getCalendar().getTimeInMillis();
            } else {
                boolean isDigit = true;
                for (int i = 0; i < strVal.length(); ++i) {
                    char ch = strVal.charAt(i);
                    if (ch >= '0' && ch <= '9') continue;
                    isDigit = false;
                    break;
                }
                if (!isDigit) {
                    dateLexer.close();
                    return (T)Time.valueOf(strVal);
                }
                longVal = Long.parseLong(strVal);
            }
            dateLexer.close();
            return (T)new Time(longVal);
        }
        throw new JSONException("parse error");
    }

    @Override
    public int getFastMatchToken() {
        return 2;
    }
}

