/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeFilter;

public abstract class BeforeFilter
implements SerializeFilter {
    private static final ThreadLocal<JSONSerializer> serializerLocal = new ThreadLocal();
    private static final ThreadLocal<Character> seperatorLocal = new ThreadLocal();
    private static final Character COMMA = Character.valueOf(',');

    final char writeBefore(JSONSerializer serializer, Object object, char seperator) {
        JSONSerializer last = serializerLocal.get();
        serializerLocal.set(serializer);
        seperatorLocal.set(Character.valueOf(seperator));
        this.writeBefore(object);
        serializerLocal.set(last);
        return seperatorLocal.get().charValue();
    }

    protected final void writeKeyValue(String key, Object value) {
        JSONSerializer serializer = serializerLocal.get();
        char seperator = seperatorLocal.get().charValue();
        boolean ref = serializer.references.containsKey(value);
        serializer.writeKeyValue(seperator, key, value);
        if (!ref) {
            serializer.references.remove(value);
        }
        if (seperator != ',') {
            seperatorLocal.set(COMMA);
        }
    }

    public abstract void writeBefore(Object var1);
}

