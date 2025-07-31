/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.JsonValue;
import java.util.Map;

public interface DbDoc
extends JsonValue,
Map<String, JsonValue> {
    public DbDoc add(String var1, JsonValue var2);
}

