/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.deserializer.ParseProcess;

public interface ExtraProcessor
extends ParseProcess {
    public void processExtra(Object var1, String var2, Object var3);
}

