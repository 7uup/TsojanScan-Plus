/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.admin;

import com.mysql.cj.Messages;
import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.exceptions.SQLError;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class MiniAdmin {
    private JdbcConnection conn;

    public MiniAdmin(Connection conn) throws SQLException {
        if (conn == null) {
            throw SQLError.createSQLException(Messages.getString("MiniAdmin.0"), "S1000", null);
        }
        if (!(conn instanceof JdbcConnection)) {
            throw SQLError.createSQLException(Messages.getString("MiniAdmin.1"), "S1000", ((ConnectionImpl)conn).getExceptionInterceptor());
        }
        this.conn = (JdbcConnection)conn;
    }

    public MiniAdmin(String jdbcUrl) throws SQLException {
        this(jdbcUrl, new Properties());
    }

    public MiniAdmin(String jdbcUrl, Properties props) throws SQLException {
        this.conn = (JdbcConnection)new Driver().connect(jdbcUrl, props);
    }

    public void shutdown() throws SQLException {
        this.conn.shutdownServer();
    }
}

