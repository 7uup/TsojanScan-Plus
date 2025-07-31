/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.serializer.SerializeFilter;

public interface PropertyFilter
extends SerializeFilter {
    public boolean apply(Object var1, String var2, Object var3);
}

