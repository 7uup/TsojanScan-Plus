/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ThrowableDeserializer
extends JavaBeanDeserializer {
    public ThrowableDeserializer(ParserConfig mapping, Class<?> clazz) {
        super(mapping, clazz, clazz);
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        HashMap<String, Object> otherValues;
        StackTraceElement[] stackTrace;
        String message;
        Class<?> exClass;
        Throwable cause;
        block37: {
            Class<?> clazz;
            JSONLexer lexer = parser.lexer;
            if (lexer.token() == 8) {
                lexer.nextToken();
                return null;
            }
            if (parser.getResolveStatus() == 2) {
                parser.setResolveStatus(0);
            } else if (lexer.token() != 12) {
                throw new JSONException("syntax error");
            }
            cause = null;
            exClass = null;
            if (type != null && type instanceof Class && Throwable.class.isAssignableFrom(clazz = (Class<?>)type)) {
                exClass = clazz;
            }
            message = null;
            stackTrace = null;
            otherValues = null;
            while (true) {
                String key;
                if ((key = lexer.scanSymbol(parser.getSymbolTable())) == null) {
                    if (lexer.token() == 13) {
                        lexer.nextToken(16);
                        break block37;
                    }
                    if (lexer.token() == 16 && lexer.isEnabled(Feature.AllowArbitraryCommas)) continue;
                }
                lexer.nextTokenWithColon(4);
                if (JSON.DEFAULT_TYPE_KEY.equals(key)) {
                    if (lexer.token() != 4) {
                        throw new JSONException("syntax error");
                    }
                    String exClassName = lexer.stringVal();
                    exClass = parser.getConfig().checkAutoType(exClassName, Throwable.class, lexer.getFeatures());
                    lexer.nextToken(16);
                } else if ("message".equals(key)) {
                    if (lexer.token() == 8) {
                        message = null;
                    } else if (lexer.token() == 4) {
                        message = lexer.stringVal();
                    } else {
                        throw new JSONException("syntax error");
                    }
                    lexer.nextToken();
                } else if ("cause".equals(key)) {
                    cause = (Throwable)this.deserialze(parser, null, "cause");
                } else if ("stackTrace".equals(key)) {
                    stackTrace = parser.parseObject(StackTraceElement[].class);
                } else {
                    if (otherValues == null) {
                        otherValues = new HashMap<String, Object>();
                    }
                    otherValues.put(key, parser.parse());
                }
                if (lexer.token() == 13) break;
            }
            lexer.nextToken(16);
        }
        Throwable ex = null;
        if (exClass == null) {
            ex = new Exception(message, cause);
        } else {
            if (!Throwable.class.isAssignableFrom(exClass)) {
                throw new JSONException("type not match, not Throwable. " + exClass.getName());
            }
            try {
                ex = this.createException(message, cause, exClass);
                if (ex == null) {
                    ex = new Exception(message, cause);
                }
            } catch (Exception e) {
                throw new JSONException("create instance error", e);
            }
        }
        if (stackTrace != null) {
            ex.setStackTrace(stackTrace);
        }
        if (otherValues != null) {
            JavaBeanDeserializer exBeanDeser = null;
            if (exClass != null) {
                if (exClass == this.clazz) {
                    exBeanDeser = this;
                } else {
                    ObjectDeserializer exDeser = parser.getConfig().getDeserializer(exClass);
                    if (exDeser instanceof JavaBeanDeserializer) {
                        exBeanDeser = (JavaBeanDeserializer)exDeser;
                    }
                }
            }
            if (exBeanDeser != null) {
                for (Map.Entry entry : otherValues.entrySet()) {
                    String key = (String)entry.getKey();
                    Object value = entry.getValue();
                    FieldDeserializer fieldDeserializer = exBeanDeser.getFieldDeserializer(key);
                    if (fieldDeserializer == null) continue;
                    FieldInfo fieldInfo = fieldDeserializer.fieldInfo;
                    if (!fieldInfo.fieldClass.isInstance(value)) {
                        value = TypeUtils.cast(value, fieldInfo.fieldType, parser.getConfig());
                    }
                    fieldDeserializer.setValue((Object)ex, value);
                }
            }
        }
        return (T)ex;
    }

    private Throwable createException(String message, Throwable cause, Class<?> exClass) throws Exception {
        Constructor<?> defaultConstructor = null;
        Constructor<?> messageConstructor = null;
        Constructor<?> causeConstructor = null;
        for (Constructor<?> constructor : exClass.getConstructors()) {
            Class<?>[] types = constructor.getParameterTypes();
            if (types.length == 0) {
                defaultConstructor = constructor;
                continue;
            }
            if (types.length == 1 && types[0] == String.class) {
                messageConstructor = constructor;
                continue;
            }
            if (types.length != 2 || types[0] != String.class || types[1] != Throwable.class) continue;
            causeConstructor = constructor;
        }
        if (causeConstructor != null) {
            return (Throwable)causeConstructor.newInstance(message, cause);
        }
        if (messageConstructor != null) {
            return (Throwable)messageConstructor.newInstance(message);
        }
        if (defaultConstructor != null) {
            return (Throwable)defaultConstructor.newInstance(new Object[0]);
        }
        return null;
    }

    @Override
    public int getFastMatchToken() {
        return 12;
    }
}

