/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.Messages;
import com.mysql.cj.MysqlConnection;
import com.mysql.cj.ServerVersion;
import com.mysql.cj.Session;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.ConnectionIsClosedException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.interceptors.QueryInterceptor;
import com.mysql.cj.jdbc.CallableStatementWrapper;
import com.mysql.cj.jdbc.ClientInfoProvider;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.JdbcPreparedStatement;
import com.mysql.cj.jdbc.JdbcPropertySet;
import com.mysql.cj.jdbc.JdbcStatement;
import com.mysql.cj.jdbc.MysqlPooledConnection;
import com.mysql.cj.jdbc.PreparedStatementWrapper;
import com.mysql.cj.jdbc.StatementWrapper;
import com.mysql.cj.jdbc.WrapperBase;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import com.mysql.cj.jdbc.result.CachedResultSetMetaData;
import com.mysql.cj.jdbc.result.ResultSetInternalMethods;
import java.lang.reflect.Proxy;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.sql.Wrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class ConnectionWrapper
extends WrapperBase
implements JdbcConnection {
    protected JdbcConnection mc = null;
    private String invalidHandleStr = "Logical handle no longer valid";
    private boolean closed;
    private boolean isForXa;

    protected static ConnectionWrapper getInstance(MysqlPooledConnection mysqlPooledConnection, JdbcConnection mysqlConnection, boolean forXa) throws SQLException {
        return new ConnectionWrapper(mysqlPooledConnection, mysqlConnection, forXa);
    }

    public ConnectionWrapper(MysqlPooledConnection mysqlPooledConnection, JdbcConnection mysqlConnection, boolean forXa) throws SQLException {
        super(mysqlPooledConnection);
        this.mc = mysqlConnection;
        this.closed = false;
        this.isForXa = forXa;
        if (this.isForXa) {
            this.setInGlobalTx(false);
        }
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        try {
            this.checkClosed();
            if (autoCommit && this.isInGlobalTx()) {
                throw SQLError.createSQLException(Messages.getString("ConnectionWrapper.0"), "2D000", 1401, this.exceptionInterceptor);
            }
            try {
                this.mc.setAutoCommit(autoCommit);
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        try {
            this.checkClosed();
            try {
                return this.mc.getAutoCommit();
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return false;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {
        try {
            this.checkClosed();
            try {
                this.mc.setCatalog(catalog);
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public String getCatalog() throws SQLException {
        try {
            this.checkClosed();
            try {
                return this.mc.getCatalog();
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public boolean isClosed() throws SQLException {
        try {
            return this.closed || this.mc.isClosed();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public boolean isMasterConnection() {
        return this.mc.isMasterConnection();
    }

    @Override
    public void setHoldability(int arg0) throws SQLException {
        try {
            this.checkClosed();
            try {
                this.mc.setHoldability(arg0);
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public int getHoldability() throws SQLException {
        try {
            this.checkClosed();
            try {
                return this.mc.getHoldability();
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return 1;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public long getIdleFor() {
        return this.mc.getIdleFor();
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        try {
            this.checkClosed();
            try {
                return this.mc.getMetaData();
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        try {
            this.checkClosed();
            try {
                this.mc.setReadOnly(readOnly);
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        try {
            this.checkClosed();
            try {
                return this.mc.isReadOnly();
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return false;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        try {
            this.checkClosed();
            if (this.isInGlobalTx()) {
                throw SQLError.createSQLException(Messages.getString("ConnectionWrapper.0"), "2D000", 1401, this.exceptionInterceptor);
            }
            try {
                return this.mc.setSavepoint();
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public Savepoint setSavepoint(String arg0) throws SQLException {
        try {
            this.checkClosed();
            if (this.isInGlobalTx()) {
                throw SQLError.createSQLException(Messages.getString("ConnectionWrapper.0"), "2D000", 1401, this.exceptionInterceptor);
            }
            try {
                return this.mc.setSavepoint(arg0);
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        try {
            this.checkClosed();
            try {
                this.mc.setTransactionIsolation(level);
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        try {
            this.checkClosed();
            try {
                return this.mc.getTransactionIsolation();
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return 4;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        try {
            this.checkClosed();
            try {
                return this.mc.getTypeMap();
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        try {
            this.checkClosed();
            try {
                return this.mc.getWarnings();
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public void clearWarnings() throws SQLException {
        try {
            this.checkClosed();
            try {
                this.mc.clearWarnings();
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public void close() throws SQLException {
        try {
            try {
                this.close(true);
            } finally {
                this.unwrappedInterfaces = null;
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public void commit() throws SQLException {
        try {
            this.checkClosed();
            if (this.isInGlobalTx()) {
                throw SQLError.createSQLException(Messages.getString("ConnectionWrapper.1"), "2D000", 1401, this.exceptionInterceptor);
            }
            try {
                this.mc.commit();
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public Statement createStatement() throws SQLException {
        try {
            this.checkClosed();
            try {
                return StatementWrapper.getInstance(this, this.pooledConnection, this.mc.createStatement());
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        try {
            this.checkClosed();
            try {
                return StatementWrapper.getInstance(this, this.pooledConnection, this.mc.createStatement(resultSetType, resultSetConcurrency));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public Statement createStatement(int arg0, int arg1, int arg2) throws SQLException {
        try {
            this.checkClosed();
            try {
                return StatementWrapper.getInstance(this, this.pooledConnection, this.mc.createStatement(arg0, arg1, arg2));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        try {
            this.checkClosed();
            try {
                return this.mc.nativeSQL(sql);
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        try {
            this.checkClosed();
            try {
                return CallableStatementWrapper.getInstance(this, this.pooledConnection, this.mc.prepareCall(sql));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        try {
            this.checkClosed();
            try {
                return CallableStatementWrapper.getInstance(this, this.pooledConnection, this.mc.prepareCall(sql, resultSetType, resultSetConcurrency));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public CallableStatement prepareCall(String arg0, int arg1, int arg2, int arg3) throws SQLException {
        try {
            this.checkClosed();
            try {
                return CallableStatementWrapper.getInstance(this, this.pooledConnection, this.mc.prepareCall(arg0, arg1, arg2, arg3));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    public PreparedStatement clientPrepare(String sql) throws SQLException {
        try {
            this.checkClosed();
            try {
                return new PreparedStatementWrapper(this, this.pooledConnection, this.mc.clientPrepareStatement(sql));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    public PreparedStatement clientPrepare(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        try {
            this.checkClosed();
            try {
                return new PreparedStatementWrapper(this, this.pooledConnection, this.mc.clientPrepareStatement(sql, resultSetType, resultSetConcurrency));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        try {
            this.checkClosed();
            PreparedStatementWrapper res = null;
            try {
                res = PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.prepareStatement(sql));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
            }
            return res;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        try {
            this.checkClosed();
            try {
                return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.prepareStatement(sql, resultSetType, resultSetConcurrency));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public PreparedStatement prepareStatement(String arg0, int arg1, int arg2, int arg3) throws SQLException {
        try {
            this.checkClosed();
            try {
                return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.prepareStatement(arg0, arg1, arg2, arg3));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public PreparedStatement prepareStatement(String arg0, int arg1) throws SQLException {
        try {
            this.checkClosed();
            try {
                return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.prepareStatement(arg0, arg1));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public PreparedStatement prepareStatement(String arg0, int[] arg1) throws SQLException {
        try {
            this.checkClosed();
            try {
                return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.prepareStatement(arg0, arg1));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public PreparedStatement prepareStatement(String arg0, String[] arg1) throws SQLException {
        try {
            this.checkClosed();
            try {
                return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.prepareStatement(arg0, arg1));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public void releaseSavepoint(Savepoint arg0) throws SQLException {
        try {
            this.checkClosed();
            try {
                this.mc.releaseSavepoint(arg0);
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public void rollback() throws SQLException {
        try {
            this.checkClosed();
            if (this.isInGlobalTx()) {
                throw SQLError.createSQLException(Messages.getString("ConnectionWrapper.2"), "2D000", 1401, this.exceptionInterceptor);
            }
            try {
                this.mc.rollback();
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public void rollback(Savepoint arg0) throws SQLException {
        try {
            this.checkClosed();
            if (this.isInGlobalTx()) {
                throw SQLError.createSQLException(Messages.getString("ConnectionWrapper.2"), "2D000", 1401, this.exceptionInterceptor);
            }
            try {
                this.mc.rollback(arg0);
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public boolean isSameResource(JdbcConnection c) {
        if (c instanceof ConnectionWrapper) {
            return this.mc.isSameResource(((ConnectionWrapper)c).mc);
        }
        return this.mc.isSameResource(c);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void close(boolean fireClosedEvent) throws SQLException {
        MysqlPooledConnection mysqlPooledConnection = this.pooledConnection;
        synchronized (mysqlPooledConnection) {
            if (this.closed) {
                return;
            }
            if (!this.isInGlobalTx() && this.mc.getPropertySet().getBooleanProperty("rollbackOnPooledClose").getValue().booleanValue() && !this.getAutoCommit()) {
                this.rollback();
            }
            if (fireClosedEvent) {
                this.pooledConnection.callConnectionEventListeners(2, null);
            }
            this.closed = true;
        }
    }

    @Override
    public void checkClosed() {
        if (this.closed) {
            throw ExceptionFactory.createException(ConnectionIsClosedException.class, this.invalidHandleStr, this.exceptionInterceptor);
        }
    }

    @Override
    public boolean isInGlobalTx() {
        return this.mc.isInGlobalTx();
    }

    @Override
    public void setInGlobalTx(boolean flag) {
        this.mc.setInGlobalTx(flag);
    }

    @Override
    public void ping() throws SQLException {
        try {
            if (this.mc != null) {
                this.mc.ping();
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public void changeUser(String userName, String newPassword) throws SQLException {
        try {
            this.checkClosed();
            try {
                this.mc.changeUser(userName, newPassword);
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    @Deprecated
    public void clearHasTriedMaster() {
        this.mc.clearHasTriedMaster();
    }

    @Override
    public PreparedStatement clientPrepareStatement(String sql) throws SQLException {
        try {
            this.checkClosed();
            try {
                return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.clientPrepareStatement(sql));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public PreparedStatement clientPrepareStatement(String sql, int autoGenKeyIndex) throws SQLException {
        try {
            this.checkClosed();
            try {
                return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.clientPrepareStatement(sql, autoGenKeyIndex));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public PreparedStatement clientPrepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        try {
            this.checkClosed();
            try {
                return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.clientPrepareStatement(sql, resultSetType, resultSetConcurrency));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public PreparedStatement clientPrepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        try {
            this.checkClosed();
            try {
                return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.clientPrepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public PreparedStatement clientPrepareStatement(String sql, int[] autoGenKeyIndexes) throws SQLException {
        try {
            this.checkClosed();
            try {
                return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.clientPrepareStatement(sql, autoGenKeyIndexes));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public PreparedStatement clientPrepareStatement(String sql, String[] autoGenKeyColNames) throws SQLException {
        try {
            this.checkClosed();
            try {
                return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.clientPrepareStatement(sql, autoGenKeyColNames));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public int getActiveStatementCount() {
        return this.mc.getActiveStatementCount();
    }

    @Override
    public String getStatementComment() {
        return this.mc.getStatementComment();
    }

    @Override
    @Deprecated
    public boolean hasTriedMaster() {
        return this.mc.hasTriedMaster();
    }

    @Override
    public boolean lowerCaseTableNames() {
        return this.mc.lowerCaseTableNames();
    }

    @Override
    public void resetServerState() throws SQLException {
        try {
            this.checkClosed();
            try {
                this.mc.resetServerState();
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public PreparedStatement serverPrepareStatement(String sql) throws SQLException {
        try {
            this.checkClosed();
            try {
                return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.serverPrepareStatement(sql));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public PreparedStatement serverPrepareStatement(String sql, int autoGenKeyIndex) throws SQLException {
        try {
            this.checkClosed();
            try {
                return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.serverPrepareStatement(sql, autoGenKeyIndex));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public PreparedStatement serverPrepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        try {
            this.checkClosed();
            try {
                return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.serverPrepareStatement(sql, resultSetType, resultSetConcurrency));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public PreparedStatement serverPrepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        try {
            this.checkClosed();
            try {
                return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.serverPrepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public PreparedStatement serverPrepareStatement(String sql, int[] autoGenKeyIndexes) throws SQLException {
        try {
            this.checkClosed();
            try {
                return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.serverPrepareStatement(sql, autoGenKeyIndexes));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public PreparedStatement serverPrepareStatement(String sql, String[] autoGenKeyColNames) throws SQLException {
        try {
            this.checkClosed();
            try {
                return PreparedStatementWrapper.getInstance(this, this.pooledConnection, this.mc.serverPrepareStatement(sql, autoGenKeyColNames));
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public void setFailedOver(boolean flag) {
        this.mc.setFailedOver(flag);
    }

    @Override
    public void setStatementComment(String comment) {
        this.mc.setStatementComment(comment);
    }

    @Override
    public void shutdownServer() throws SQLException {
        try {
            this.checkClosed();
            try {
                this.mc.shutdownServer();
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public int getAutoIncrementIncrement() {
        return this.mc.getAutoIncrementIncrement();
    }

    @Override
    public ExceptionInterceptor getExceptionInterceptor() {
        return this.pooledConnection.getExceptionInterceptor();
    }

    @Override
    public boolean hasSameProperties(JdbcConnection c) {
        return this.mc.hasSameProperties(c);
    }

    @Override
    public Properties getProperties() {
        return this.mc.getProperties();
    }

    @Override
    public String getHost() {
        return this.mc.getHost();
    }

    @Override
    public void setProxy(JdbcConnection conn) {
        this.mc.setProxy(conn);
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        try {
            this.checkClosed();
            try {
                this.mc.setTypeMap(map);
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public boolean isServerLocal() throws SQLException {
        try {
            return this.mc.isServerLocal();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public void setSchema(String schema) throws SQLException {
        try {
            this.mc.setSchema(schema);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public String getSchema() throws SQLException {
        try {
            return this.mc.getSchema();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        try {
            this.mc.abort(executor);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        try {
            this.mc.setNetworkTimeout(executor, milliseconds);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        try {
            return this.mc.getNetworkTimeout();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public void abortInternal() throws SQLException {
        try {
            this.mc.abortInternal();
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public Object getConnectionMutex() {
        return this.mc.getConnectionMutex();
    }

    @Override
    public int getSessionMaxRows() {
        return this.mc.getSessionMaxRows();
    }

    @Override
    public void setSessionMaxRows(int max) throws SQLException {
        try {
            this.mc.setSessionMaxRows(max);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public Clob createClob() throws SQLException {
        try {
            this.checkClosed();
            try {
                return this.mc.createClob();
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public Blob createBlob() throws SQLException {
        try {
            this.checkClosed();
            try {
                return this.mc.createBlob();
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public NClob createNClob() throws SQLException {
        try {
            this.checkClosed();
            try {
                return this.mc.createNClob();
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        try {
            this.checkClosed();
            try {
                return this.mc.createSQLXML();
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public synchronized boolean isValid(int timeout2) throws SQLException {
        try {
            try {
                return this.mc.isValid(timeout2);
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return false;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        try {
            try {
                this.checkClosed();
                this.mc.setClientInfo(name, value);
            } catch (SQLException sqlException) {
                try {
                    this.checkAndFireConnectionError(sqlException);
                } catch (SQLException sqlEx2) {
                    SQLClientInfoException clientEx = new SQLClientInfoException();
                    clientEx.initCause(sqlEx2);
                    throw clientEx;
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        try {
            try {
                this.checkClosed();
                this.mc.setClientInfo(properties);
            } catch (SQLException sqlException) {
                try {
                    this.checkAndFireConnectionError(sqlException);
                } catch (SQLException sqlEx2) {
                    SQLClientInfoException clientEx = new SQLClientInfoException();
                    clientEx.initCause(sqlEx2);
                    throw clientEx;
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        try {
            this.checkClosed();
            try {
                return this.mc.getClientInfo(name);
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        try {
            this.checkClosed();
            try {
                return this.mc.getClientInfo();
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        try {
            this.checkClosed();
            try {
                return this.mc.createArrayOf(typeName, elements);
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        try {
            this.checkClosed();
            try {
                return this.mc.createStruct(typeName, attributes);
            } catch (SQLException sqlException) {
                this.checkAndFireConnectionError(sqlException);
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public synchronized <T> T unwrap(Class<T> iface) throws SQLException {
        try {
            try {
                Object cachedUnwrapped;
                if ("java.sql.Connection".equals(iface.getName()) || "java.sql.Wrapper.class".equals(iface.getName())) {
                    return iface.cast(this);
                }
                if (this.unwrappedInterfaces == null) {
                    this.unwrappedInterfaces = new HashMap();
                }
                if ((cachedUnwrapped = this.unwrappedInterfaces.get(iface)) == null) {
                    cachedUnwrapped = Proxy.newProxyInstance(this.mc.getClass().getClassLoader(), new Class[]{iface}, new WrapperBase.ConnectionErrorFiringInvocationHandler(this, this.mc));
                    this.unwrappedInterfaces.put(iface, cachedUnwrapped);
                }
                return iface.cast(cachedUnwrapped);
            } catch (ClassCastException cce) {
                throw SQLError.createSQLException(Messages.getString("Common.UnableToUnwrap", new Object[]{iface.toString()}), "S1009", this.exceptionInterceptor);
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        try {
            boolean isInstance = iface.isInstance(this);
            if (isInstance) {
                return true;
            }
            return iface.getName().equals(JdbcConnection.class.getName()) || iface.getName().equals(MysqlConnection.class.getName()) || iface.getName().equals(Connection.class.getName()) || iface.getName().equals(Wrapper.class.getName()) || iface.getName().equals(AutoCloseable.class.getName());
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public Session getSession() {
        return this.mc.getSession();
    }

    @Override
    public long getId() {
        return this.mc.getId();
    }

    @Override
    public String getURL() {
        return this.mc.getURL();
    }

    @Override
    public String getUser() {
        return this.mc.getUser();
    }

    @Override
    public void createNewIO(boolean isForReconnect) {
        this.mc.createNewIO(isForReconnect);
    }

    @Override
    public boolean isProxySet() {
        return this.mc.isProxySet();
    }

    @Override
    public JdbcPropertySet getPropertySet() {
        return this.mc.getPropertySet();
    }

    @Override
    public CachedResultSetMetaData getCachedMetaData(String sql) {
        return this.mc.getCachedMetaData(sql);
    }

    @Override
    public String getCharacterSetMetadata() {
        return this.mc.getCharacterSetMetadata();
    }

    @Override
    public Statement getMetadataSafeStatement() throws SQLException {
        try {
            return this.mc.getMetadataSafeStatement();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public ServerVersion getServerVersion() {
        return this.mc.getServerVersion();
    }

    @Override
    public List<QueryInterceptor> getQueryInterceptorsInstances() {
        return this.mc.getQueryInterceptorsInstances();
    }

    @Override
    public void initializeResultsMetadataFromCache(String sql, CachedResultSetMetaData cachedMetaData, ResultSetInternalMethods resultSet) throws SQLException {
        try {
            this.mc.initializeResultsMetadataFromCache(sql, cachedMetaData, resultSet);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public void initializeSafeQueryInterceptors() throws SQLException {
        try {
            this.mc.initializeSafeQueryInterceptors();
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public boolean isReadOnly(boolean useSessionStatus) throws SQLException {
        try {
            this.checkClosed();
            return this.mc.isReadOnly(useSessionStatus);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public void pingInternal(boolean checkForClosedConnection, int timeoutMillis) throws SQLException {
        try {
            this.mc.pingInternal(checkForClosedConnection, timeoutMillis);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public void realClose(boolean calledExplicitly, boolean issueRollback, boolean skipLocalTeardown, Throwable reason) throws SQLException {
        try {
            this.mc.realClose(calledExplicitly, issueRollback, skipLocalTeardown, reason);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public void recachePreparedStatement(JdbcPreparedStatement pstmt) throws SQLException {
        try {
            this.mc.recachePreparedStatement(pstmt);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public void decachePreparedStatement(JdbcPreparedStatement pstmt) throws SQLException {
        try {
            this.mc.decachePreparedStatement(pstmt);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public void registerStatement(JdbcStatement stmt) {
        this.mc.registerStatement(stmt);
    }

    @Override
    public void setReadOnlyInternal(boolean readOnlyFlag) throws SQLException {
        try {
            this.mc.setReadOnlyInternal(readOnlyFlag);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public boolean storesLowerCaseTableName() {
        return this.mc.storesLowerCaseTableName();
    }

    @Override
    public void throwConnectionClosedException() throws SQLException {
        try {
            this.mc.throwConnectionClosedException();
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public void transactionBegun() {
        this.mc.transactionBegun();
    }

    @Override
    public void transactionCompleted() {
        this.mc.transactionCompleted();
    }

    @Override
    public void unregisterStatement(JdbcStatement stmt) {
        this.mc.unregisterStatement(stmt);
    }

    @Override
    public void unSafeQueryInterceptors() throws SQLException {
        try {
            this.mc.unSafeQueryInterceptors();
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public JdbcConnection getMultiHostSafeProxy() {
        return this.mc.getMultiHostSafeProxy();
    }

    @Override
    public JdbcConnection getActiveMySQLConnection() {
        return this.mc.getActiveMySQLConnection();
    }

    @Override
    public ClientInfoProvider getClientInfoProviderImpl() throws SQLException {
        try {
            return this.mc.getClientInfoProviderImpl();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public String getHostPortPair() {
        return this.mc.getHostPortPair();
    }

    @Override
    public void normalClose() {
        this.mc.normalClose();
    }

    @Override
    public void cleanup(Throwable whyCleanedUp) {
        this.mc.cleanup(whyCleanedUp);
    }
}

