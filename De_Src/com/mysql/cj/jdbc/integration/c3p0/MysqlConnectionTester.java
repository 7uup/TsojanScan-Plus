/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.mchange.v2.c3p0.C3P0ProxyConnection
 *  com.mchange.v2.c3p0.QueryConnectionTester
 */
package com.mysql.cj.jdbc.integration.c3p0;

import com.mchange.v2.c3p0.C3P0ProxyConnection;
import com.mchange.v2.c3p0.QueryConnectionTester;
import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public final class MysqlConnectionTester
implements QueryConnectionTester {
    private static final long serialVersionUID = 3256444690067896368L;
    private static final Object[] NO_ARGS_ARRAY = new Object[0];
    private transient Method pingMethod;

    public MysqlConnectionTester() {
        try {
            this.pingMethod = JdbcConnection.class.getMethod("ping", null);
        } catch (Exception exception) {
            // empty catch block
        }
    }

    public int activeCheckConnection(Connection con) {
        try {
            if (this.pingMethod != null) {
                if (con instanceof JdbcConnection) {
                    ((JdbcConnection)con).ping();
                } else {
                    C3P0ProxyConnection castCon = (C3P0ProxyConnection)con;
                    castCon.rawConnectionOperation(this.pingMethod, C3P0ProxyConnection.RAW_CONNECTION, NO_ARGS_ARRAY);
                }
            } else {
                try (Statement pingStatement = null;){
                    pingStatement = con.createStatement();
                    pingStatement.executeQuery("SELECT 1").close();
                }
            }
            return 0;
        } catch (Exception ex) {
            return -1;
        }
    }

    public int statusOnException(Connection arg0, Throwable throwable) {
        if (throwable instanceof CommunicationsException || throwable instanceof CJCommunicationsException) {
            return -1;
        }
        if (throwable instanceof SQLException) {
            String sqlState = ((SQLException)throwable).getSQLState();
            if (sqlState != null && sqlState.startsWith("08")) {
                return -1;
            }
            return 0;
        }
        return -1;
    }

    public int activeCheckConnection(Connection arg0, String arg1) {
        return 0;
    }
}

