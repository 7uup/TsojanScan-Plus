/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import java.io.IOException;
import java.lang.reflect.Type;

public class JSONAwareSerializer
implements ObjectSerializer {
    public static JSONAwareSerializer instance = new JSONAwareSerializer();

    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull();
            return;
        }
        JSONAware aware = (JSONAware)object;
        out.write(aware.toJSONString());
    }
}

