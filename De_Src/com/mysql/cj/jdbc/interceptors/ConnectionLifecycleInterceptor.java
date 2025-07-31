/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.interceptors;

import com.mysql.cj.MysqlConnection;
import com.mysql.cj.log.Log;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Properties;

public interface ConnectionLifecycleInterceptor {
    public ConnectionLifecycleInterceptor init(MysqlConnection var1, Properties var2, Log var3);

    public void destroy();

    public void close() throws SQLException;

    public boolean commit() throws SQLException;

    public boolean rollback() throws SQLException;

    public boolean rollback(Savepoint var1) throws SQLException;

    public boolean setAutoCommit(boolean var1) throws SQLException;

    public boolean setCatalog(String var1) throws SQLException;

    public boolean transactionBegun();

    public boolean transactionCompleted();
}

