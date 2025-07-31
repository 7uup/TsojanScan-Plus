/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class JSONSerializerMap
extends SerializeConfig {
    public final boolean put(Class<?> clazz, ObjectSerializer serializer) {
        return super.put(clazz, serializer);
    }
}

