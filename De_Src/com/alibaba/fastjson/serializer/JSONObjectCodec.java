/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.MapSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public class JSONObjectCodec
implements ObjectSerializer {
    public static final JSONObjectCodec instance = new JSONObjectCodec();

    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        MapSerializer mapSerializer = MapSerializer.instance;
        try {
            Field mapField = object.getClass().getDeclaredField("map");
            if (Modifier.isPrivate(mapField.getModifiers())) {
                mapField.setAccessible(true);
            }
            Object map = mapField.get(object);
            mapSerializer.write(serializer, map, fieldName, fieldType, features);
        } catch (Exception e) {
            out.writeNull();
        }
    }
}

