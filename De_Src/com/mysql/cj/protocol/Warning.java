/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

public interface Warning {
    public int getLevel();

    public long getCode();

    public String getMessage();
}

