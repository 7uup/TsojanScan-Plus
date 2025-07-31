/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.serializer.JSONSerializable;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import java.io.IOException;
import java.lang.reflect.Type;

public class JSONSerializableSerializer
implements ObjectSerializer {
    public static JSONSerializableSerializer instance = new JSONSerializableSerializer();

    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        JSONSerializable jsonSerializable = (JSONSerializable)object;
        if (jsonSerializable == null) {
            serializer.writeNull();
            return;
        }
        jsonSerializable.write(serializer, fieldName, fieldType, features);
    }
}

