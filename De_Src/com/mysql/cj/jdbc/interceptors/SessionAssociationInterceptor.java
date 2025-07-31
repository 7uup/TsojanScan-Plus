/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.interceptors;

import com.mysql.cj.MysqlConnection;
import com.mysql.cj.Query;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.interceptors.QueryInterceptor;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.log.Log;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.ServerSession;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.function.Supplier;

public class SessionAssociationInterceptor
implements QueryInterceptor {
    protected String currentSessionKey;
    protected static final ThreadLocal<String> sessionLocal = new ThreadLocal();
    private JdbcConnection connection;

    public static final void setSessionKey(String key) {
        sessionLocal.set(key);
    }

    public static final void resetSessionKey() {
        sessionLocal.set(null);
    }

    public static final String getSessionKey() {
        return sessionLocal.get();
    }

    @Override
    public boolean executeTopLevelOnly() {
        return true;
    }

    @Override
    public QueryInterceptor init(MysqlConnection conn, Properties props, Log log) {
        this.connection = (JdbcConnection)conn;
        return this;
    }

    @Override
    public <T extends Resultset> T postProcess(Supplier<String> sql, Query interceptedQuery, T originalResultSet, ServerSession serverSession) {
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public <T extends Resultset> T preProcess(Supplier<String> sql, Query interceptedQuery) {
        String key = SessionAssociationInterceptor.getSessionKey();
        if (key != null && !key.equals(this.currentSessionKey)) {
            try (PreparedStatement pstmt = this.connection.clientPrepareStatement("SET @mysql_proxy_session=?");){
                pstmt.setString(1, key);
                pstmt.execute();
            } catch (SQLException ex) {
                throw ExceptionFactory.createException(ex.getMessage(), ex);
            }
            this.currentSessionKey = key;
        }
        return null;
    }

    @Override
    public void destroy() {
        this.connection = null;
    }
}

