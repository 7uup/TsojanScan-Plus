/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.PropertyProcessable;
import java.lang.reflect.Type;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class PropertyProcessableDeserializer
implements ObjectDeserializer {
    public final Class<PropertyProcessable> type;

    public PropertyProcessableDeserializer(Class<PropertyProcessable> type) {
        this.type = type;
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        PropertyProcessable processable;
        try {
            processable = this.type.newInstance();
        } catch (Exception e) {
            throw new JSONException("craete instance error");
        }
        Object object = parser.parse(processable, fieldName);
        return (T)object;
    }

    @Override
    public int getFastMatchToken() {
        return 12;
    }
}

