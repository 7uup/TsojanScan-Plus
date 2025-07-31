/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

public interface JsonValue {
    default public String toFormattedString() {
        return this.toString();
    }
}

