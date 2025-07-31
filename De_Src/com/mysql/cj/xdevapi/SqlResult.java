/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.InsertResult;
import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.RowResult;

public interface SqlResult
extends Result,
InsertResult,
RowResult {
    public boolean nextResult();
}

