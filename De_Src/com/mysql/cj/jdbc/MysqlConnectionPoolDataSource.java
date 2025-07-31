/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.jdbc.MysqlPooledConnection;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;

public class MysqlConnectionPoolDataSource
extends MysqlDataSource
implements ConnectionPoolDataSource {
    static final long serialVersionUID = -7767325445592304961L;

    @Override
    public synchronized PooledConnection getPooledConnection() throws SQLException {
        try {
            Connection connection = this.getConnection();
            MysqlPooledConnection mysqlPooledConnection = MysqlPooledConnection.getInstance((JdbcConnection)connection);
            return mysqlPooledConnection;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    @Override
    public synchronized PooledConnection getPooledConnection(String u, String p) throws SQLException {
        try {
            Connection connection = this.getConnection(u, p);
            MysqlPooledConnection mysqlPooledConnection = MysqlPooledConnection.getInstance((JdbcConnection)connection);
            return mysqlPooledConnection;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }
}

