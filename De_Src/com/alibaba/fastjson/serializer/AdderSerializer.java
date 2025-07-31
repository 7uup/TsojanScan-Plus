/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAdder;

public class AdderSerializer
implements ObjectSerializer {
    public static final AdderSerializer instance = new AdderSerializer();

    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object instanceof LongAdder) {
            out.writeFieldValue('{', "value", ((LongAdder)object).longValue());
            out.write(125);
        } else if (object instanceof DoubleAdder) {
            out.writeFieldValue('{', "value", ((DoubleAdder)object).doubleValue());
            out.write(125);
        }
    }
}

