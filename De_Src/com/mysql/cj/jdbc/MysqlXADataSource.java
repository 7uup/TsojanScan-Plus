/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.jdbc.MysqlXAConnection;
import com.mysql.cj.jdbc.SuspendableXAConnection;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.XAConnection;
import javax.sql.XADataSource;

public class MysqlXADataSource
extends MysqlDataSource
implements XADataSource {
    static final long serialVersionUID = 7911390333152247455L;

    @Override
    public XAConnection getXAConnection() throws SQLException {
        try {
            Connection conn = this.getConnection();
            return this.wrapConnection(conn);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    @Override
    public XAConnection getXAConnection(String u, String p) throws SQLException {
        try {
            Connection conn = this.getConnection(u, p);
            return this.wrapConnection(conn);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    private XAConnection wrapConnection(Connection conn) throws SQLException {
        if (this.getBooleanProperty("pinGlobalTxToPhysicalConnection").getValue().booleanValue() || ((JdbcConnection)conn).getPropertySet().getBooleanProperty("pinGlobalTxToPhysicalConnection").getValue().booleanValue()) {
            return SuspendableXAConnection.getInstance((JdbcConnection)conn);
        }
        return MysqlXAConnection.getInstance((JdbcConnection)conn, this.getBooleanProperty("logXaCommands").getValue());
    }
}

