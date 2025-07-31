/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.deserializer.ParseProcess;
import java.lang.reflect.Type;

public interface ExtraTypeProvider
extends ParseProcess {
    public Type getExtraType(Object var1, String var2);
}

