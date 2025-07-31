/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class EnumCreatorDeserializer
implements ObjectDeserializer {
    private final Method creator;
    private final Class paramType;

    public EnumCreatorDeserializer(Method creator) {
        this.creator = creator;
        this.paramType = creator.getParameterTypes()[0];
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        Object arg = parser.parseObject(this.paramType);
        try {
            return (T)this.creator.invoke(null, arg);
        } catch (IllegalAccessException e) {
            throw new JSONException("parse enum error", e);
        } catch (InvocationTargetException e) {
            throw new JSONException("parse enum error", e);
        }
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}

