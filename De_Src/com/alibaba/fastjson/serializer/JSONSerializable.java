/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import java.io.IOException;
import java.lang.reflect.Type;

public interface JSONSerializable {
    public void write(JSONSerializer var1, Object var2, Type var3, int var4) throws IOException;
}

