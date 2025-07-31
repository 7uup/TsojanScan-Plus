/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.MysqlConnection;
import com.mysql.cj.Session;

public interface ResultsetRowsOwner {
    public void closeOwner(boolean var1);

    public MysqlConnection getConnection();

    public Session getSession();

    public Object getSyncMutex();

    public long getConnectionId();

    public String getPointOfOrigin();

    public int getOwnerFetchSize();

    public String getCurrentCatalog();

    public int getOwningStatementId();

    public int getOwningStatementMaxRows();

    public int getOwningStatementFetchSize();

    public long getOwningStatementServerId();
}

