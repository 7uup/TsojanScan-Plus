/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.AddResult;
import com.mysql.cj.xdevapi.DbDoc;
import com.mysql.cj.xdevapi.Statement;

public interface AddStatement
extends Statement<AddStatement, AddResult> {
    public AddStatement add(String var1);

    public AddStatement add(DbDoc ... var1);

    public boolean isUpsert();

    public AddStatement setUpsert(boolean var1);
}

