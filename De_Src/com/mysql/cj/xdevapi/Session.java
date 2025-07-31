/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.Schema;
import com.mysql.cj.xdevapi.SqlStatement;
import java.util.List;

public interface Session {
    public List<Schema> getSchemas();

    public Schema getSchema(String var1);

    public String getDefaultSchemaName();

    public Schema getDefaultSchema();

    public Schema createSchema(String var1);

    public Schema createSchema(String var1, boolean var2);

    public void dropSchema(String var1);

    public String getUri();

    public boolean isOpen();

    public void close();

    public void startTransaction();

    public void commit();

    public void rollback();

    public String setSavepoint();

    public String setSavepoint(String var1);

    public void rollbackTo(String var1);

    public void releaseSavepoint(String var1);

    public SqlStatement sql(String var1);
}

