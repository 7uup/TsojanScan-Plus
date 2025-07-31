/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.Statement;

public interface DeleteStatement
extends Statement<DeleteStatement, Result> {
    public DeleteStatement where(String var1);

    public DeleteStatement orderBy(String ... var1);

    public DeleteStatement limit(long var1);
}

