/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.parser.deserializer.ContextObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class AbstractDateDeserializer
extends ContextObjectDeserializer
implements ObjectDeserializer {
    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        return this.deserialze(parser, clazz, fieldName, null, 0);
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName, String format, int features) {
        Object val;
        JSONLexer lexer = parser.lexer;
        if (lexer.token() == 2) {
            long millis = lexer.longValue();
            lexer.nextToken(16);
            if ("unixtime".equals(format)) {
                millis *= 1000L;
            }
            val = millis;
        } else if (lexer.token() == 4) {
            String strVal = lexer.stringVal();
            if (format != null) {
                String fromat2;
                SimpleDateFormat simpleDateFormat;
                block44: {
                    if ("yyyy-MM-dd HH:mm:ss.SSSSSSSSS".equals(format) && clazz instanceof Class && ((Class)clazz).getName().equals("java.sql.Timestamp")) {
                        return (T)TypeUtils.castToTimestamp(strVal);
                    }
                    simpleDateFormat = null;
                    try {
                        simpleDateFormat = new SimpleDateFormat(format, parser.lexer.getLocale());
                    } catch (IllegalArgumentException ex) {
                        if (!format.contains("T")) break block44;
                        fromat2 = format.replaceAll("T", "'T'");
                        try {
                            simpleDateFormat = new SimpleDateFormat(fromat2, parser.lexer.getLocale());
                        } catch (IllegalArgumentException e2) {
                            throw ex;
                        }
                    }
                }
                if (JSON.defaultTimeZone != null) {
                    simpleDateFormat.setTimeZone(parser.lexer.getTimeZone());
                }
                try {
                    val = simpleDateFormat.parse(strVal);
                } catch (ParseException ex) {
                    val = null;
                }
                if (val == null && JSON.defaultLocale == Locale.CHINA) {
                    block45: {
                        try {
                            simpleDateFormat = new SimpleDateFormat(format, Locale.US);
                        } catch (IllegalArgumentException ex) {
                            if (!format.contains("T")) break block45;
                            fromat2 = format.replaceAll("T", "'T'");
                            try {
                                simpleDateFormat = new SimpleDateFormat(fromat2, parser.lexer.getLocale());
                            } catch (IllegalArgumentException e2) {
                                throw ex;
                            }
                        }
                    }
                    simpleDateFormat.setTimeZone(parser.lexer.getTimeZone());
                    try {
                        val = simpleDateFormat.parse(strVal);
                    } catch (ParseException ex) {
                        val = null;
                    }
                }
                if (val == null) {
                    if (format.equals("yyyy-MM-dd'T'HH:mm:ss.SSS") && strVal.length() == 19) {
                        try {
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", JSON.defaultLocale);
                            df.setTimeZone(JSON.defaultTimeZone);
                            val = df.parse(strVal);
                        } catch (ParseException ex2) {
                            val = null;
                        }
                    } else {
                        val = null;
                    }
                }
            } else {
                val = null;
            }
            if (val == null) {
                val = strVal;
                lexer.nextToken(16);
                if (lexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                    JSONScanner iso8601Lexer = new JSONScanner(strVal);
                    if (iso8601Lexer.scanISO8601DateIfMatch()) {
                        val = iso8601Lexer.getCalendar().getTime();
                    }
                    iso8601Lexer.close();
                }
            }
        } else if (lexer.token() == 8) {
            lexer.nextToken();
            val = null;
        } else if (lexer.token() == 12) {
            lexer.nextToken();
            if (lexer.token() == 4) {
                String key = lexer.stringVal();
                if (JSON.DEFAULT_TYPE_KEY.equals(key)) {
                    lexer.nextToken();
                    parser.accept(17);
                    String typeName = lexer.stringVal();
                    Class<?> type = parser.getConfig().checkAutoType(typeName, null, lexer.getFeatures());
                    if (type != null) {
                        clazz = type;
                    }
                    parser.accept(4);
                    parser.accept(16);
                }
            } else {
                throw new JSONException("syntax error");
            }
            lexer.nextTokenWithColon(2);
            if (lexer.token() != 2) {
                throw new JSONException("syntax error : " + lexer.tokenName());
            }
            long timeMillis = lexer.longValue();
            lexer.nextToken();
            val = timeMillis;
            parser.accept(13);
        } else if (parser.getResolveStatus() == 2) {
            parser.setResolveStatus(0);
            parser.accept(16);
            if (lexer.token() == 4) {
                if (!"val".equals(lexer.stringVal())) {
                    throw new JSONException("syntax error");
                }
            } else {
                throw new JSONException("syntax error");
            }
            lexer.nextToken();
            parser.accept(17);
            val = parser.parse();
            parser.accept(13);
        } else {
            val = parser.parse();
        }
        return this.cast(parser, clazz, fieldName, val);
    }

    protected abstract <T> T cast(DefaultJSONParser var1, Type var2, Object var3, Object var4);
}

