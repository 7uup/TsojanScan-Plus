/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.Statement;

public interface RemoveStatement
extends Statement<RemoveStatement, Result> {
    public RemoveStatement orderBy(String ... var1);

    public RemoveStatement limit(long var1);
}

