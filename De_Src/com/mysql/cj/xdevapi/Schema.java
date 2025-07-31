/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.Collection;
import com.mysql.cj.xdevapi.DatabaseObject;
import com.mysql.cj.xdevapi.Table;
import java.util.List;

public interface Schema
extends DatabaseObject {
    public List<Collection> getCollections();

    public List<Collection> getCollections(String var1);

    public List<Table> getTables();

    public List<Table> getTables(String var1);

    public Collection getCollection(String var1);

    public Collection getCollection(String var1, boolean var2);

    public Table getCollectionAsTable(String var1);

    public Table getTable(String var1);

    public Table getTable(String var1, boolean var2);

    public Collection createCollection(String var1);

    public Collection createCollection(String var1, boolean var2);

    public void dropCollection(String var1);
}

