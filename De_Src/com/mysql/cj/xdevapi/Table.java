/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.DatabaseObject;
import com.mysql.cj.xdevapi.DeleteStatement;
import com.mysql.cj.xdevapi.InsertStatement;
import com.mysql.cj.xdevapi.SelectStatement;
import com.mysql.cj.xdevapi.UpdateStatement;
import java.util.Map;

public interface Table
extends DatabaseObject {
    public InsertStatement insert();

    public InsertStatement insert(String ... var1);

    public InsertStatement insert(Map<String, Object> var1);

    public SelectStatement select(String ... var1);

    public UpdateStatement update();

    public DeleteStatement delete();

    public long count();

    public boolean isView();
}

