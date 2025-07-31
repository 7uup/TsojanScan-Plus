/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.DbDoc;
import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.Statement;

public interface ModifyStatement
extends Statement<ModifyStatement, Result> {
    public ModifyStatement sort(String ... var1);

    public ModifyStatement limit(long var1);

    public ModifyStatement set(String var1, Object var2);

    public ModifyStatement change(String var1, Object var2);

    public ModifyStatement unset(String ... var1);

    public ModifyStatement patch(DbDoc var1);

    public ModifyStatement patch(String var1);

    public ModifyStatement arrayInsert(String var1, Object var2);

    public ModifyStatement arrayAppend(String var1, Object var2);
}

