/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.AddStatement;
import com.mysql.cj.xdevapi.DatabaseObject;
import com.mysql.cj.xdevapi.DbDoc;
import com.mysql.cj.xdevapi.FindStatement;
import com.mysql.cj.xdevapi.ModifyStatement;
import com.mysql.cj.xdevapi.RemoveStatement;
import com.mysql.cj.xdevapi.Result;
import java.util.Map;

public interface Collection
extends DatabaseObject {
    public AddStatement add(Map<String, ?> var1);

    public AddStatement add(String ... var1);

    public AddStatement add(DbDoc var1);

    public AddStatement add(DbDoc ... var1);

    public FindStatement find();

    public FindStatement find(String var1);

    public ModifyStatement modify(String var1);

    public RemoveStatement remove(String var1);

    public Result createIndex(String var1, DbDoc var2);

    public Result createIndex(String var1, String var2);

    public void dropIndex(String var1);

    public long count();

    public DbDoc newDoc();

    public Result replaceOne(String var1, DbDoc var2);

    public Result replaceOne(String var1, String var2);

    public Result addOrReplaceOne(String var1, DbDoc var2);

    public Result addOrReplaceOne(String var1, String var2);

    public DbDoc getOne(String var1);

    public Result removeOne(String var1);
}

