/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.serializer.SerializeFilter;

public interface NameFilter
extends SerializeFilter {
    public String process(Object var1, String var2, Object var3);
}

