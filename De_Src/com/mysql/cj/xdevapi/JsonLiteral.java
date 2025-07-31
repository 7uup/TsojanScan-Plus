/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.JsonValue;

public enum JsonLiteral implements JsonValue
{
    TRUE("true"),
    FALSE("false"),
    NULL("null");

    public final String value;

    private JsonLiteral(String val) {
        this.value = val;
    }

    public String toString() {
        return this.value;
    }
}

