/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.deserializer.ParseProcess;
import java.lang.reflect.Type;

public interface FieldTypeResolver
extends ParseProcess {
    public Type resolve(Object var1, String var2);
}

