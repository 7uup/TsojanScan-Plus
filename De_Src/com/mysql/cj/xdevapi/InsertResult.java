/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.Result;

public interface InsertResult
extends Result {
    public Long getAutoIncrementValue();
}

