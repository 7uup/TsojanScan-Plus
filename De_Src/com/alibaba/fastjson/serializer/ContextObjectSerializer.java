/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import java.io.IOException;

public interface ContextObjectSerializer
extends ObjectSerializer {
    public void write(JSONSerializer var1, Object var2, BeanContext var3) throws IOException;
}

