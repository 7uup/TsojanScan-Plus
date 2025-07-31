/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.Messages;
import com.mysql.cj.jdbc.ConnectionWrapper;
import com.mysql.cj.jdbc.JdbcStatement;
import com.mysql.cj.jdbc.MysqlPooledConnection;
import com.mysql.cj.jdbc.StatementImpl;
import com.mysql.cj.jdbc.WrapperBase;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.result.ResultSetInternalMethods;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.HashMap;

public class StatementWrapper
extends WrapperBase
implements Statement {
    protected Statement wrappedStmt;
    protected ConnectionWrapper wrappedConn;

    protected static StatementWrapper getInstance(ConnectionWrapper c, MysqlPooledConnection conn, Statement toWrap) throws SQLException {
        return new StatementWrapper(c, conn, toWrap);
    }

    public StatementWrapper(ConnectionWrapper c, MysqlPooledConnection conn, Statement toWrap) {
        super(conn);
        this.wrappedStmt = toWrap;
        this.wrappedConn = c;
    }

    @Override
    public Connection getConnection() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedConn;
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public void setCursorName(String name) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
            }
            this.wrappedStmt.setCursorName(name);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
            }
            this.wrappedStmt.setEscapeProcessing(enable);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
            }
            this.wrappedStmt.setFetchDirection(direction);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public int getFetchDirection() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedStmt.getFetchDirection();
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return 1000;
        }
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
            }
            this.wrappedStmt.setFetchSize(rows);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public int getFetchSize() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedStmt.getFetchSize();
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return 0;
        }
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedStmt.getGeneratedKeys();
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
            }
            this.wrappedStmt.setMaxFieldSize(max);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedStmt.getMaxFieldSize();
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return 0;
        }
    }

    @Override
    public void setMaxRows(int max) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
            }
            this.wrappedStmt.setMaxRows(max);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public int getMaxRows() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedStmt.getMaxRows();
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return 0;
        }
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedStmt.getMoreResults();
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return false;
        }
    }

    @Override
    public boolean getMoreResults(int current) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedStmt.getMoreResults(current);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return false;
        }
    }

    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
            }
            this.wrappedStmt.setQueryTimeout(seconds);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public int getQueryTimeout() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedStmt.getQueryTimeout();
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return 0;
        }
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                ResultSet rs = this.wrappedStmt.getResultSet();
                if (rs != null) {
                    ((ResultSetInternalMethods)rs).setWrapperStatement(this);
                }
                return rs;
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedStmt.getResultSetConcurrency();
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return 0;
        }
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedStmt.getResultSetHoldability();
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return 1;
        }
    }

    @Override
    public int getResultSetType() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedStmt.getResultSetType();
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return 1003;
        }
    }

    @Override
    public int getUpdateCount() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedStmt.getUpdateCount();
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return -1;
        }
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedStmt.getWarnings();
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public void addBatch(String sql) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
            }
            this.wrappedStmt.addBatch(sql);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void cancel() throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
            }
            this.wrappedStmt.cancel();
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void clearBatch() throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
            }
            this.wrappedStmt.clearBatch();
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void clearWarnings() throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
            }
            this.wrappedStmt.clearWarnings();
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void close() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                this.wrappedStmt.close();
            }
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        } finally {
            this.wrappedStmt = null;
            this.pooledConnection = null;
            this.unwrappedInterfaces = null;
        }
    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedStmt.execute(sql, autoGeneratedKeys);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return false;
        }
    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedStmt.execute(sql, columnIndexes);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return false;
        }
    }

    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedStmt.execute(sql, columnNames);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return false;
        }
    }

    @Override
    public boolean execute(String sql) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedStmt.execute(sql);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return false;
        }
    }

    @Override
    public int[] executeBatch() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedStmt.executeBatch();
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
            }
            rs = this.wrappedStmt.executeQuery(sql);
            ((ResultSetInternalMethods)rs).setWrapperStatement(this);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
        return rs;
    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedStmt.executeUpdate(sql, autoGeneratedKeys);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return -1;
        }
    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedStmt.executeUpdate(sql, columnIndexes);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return -1;
        }
    }

    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedStmt.executeUpdate(sql, columnNames);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return -1;
        }
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedStmt.executeUpdate(sql);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return -1;
        }
    }

    public void enableStreamingResults() throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((JdbcStatement)this.wrappedStmt).enableStreamingResults();
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public synchronized <T> T unwrap(Class<T> iface) throws SQLException {
        try {
            Object cachedUnwrapped;
            if ("java.sql.Statement".equals(iface.getName()) || "java.sql.Wrapper.class".equals(iface.getName())) {
                return iface.cast(this);
            }
            if (this.unwrappedInterfaces == null) {
                this.unwrappedInterfaces = new HashMap();
            }
            if ((cachedUnwrapped = this.unwrappedInterfaces.get(iface)) == null) {
                cachedUnwrapped = Proxy.newProxyInstance(this.wrappedStmt.getClass().getClassLoader(), new Class[]{iface}, new WrapperBase.ConnectionErrorFiringInvocationHandler(this, this.wrappedStmt));
                this.unwrappedInterfaces.put(iface, cachedUnwrapped);
            }
            return iface.cast(cachedUnwrapped);
        } catch (ClassCastException cce) {
            throw SQLError.createSQLException(Messages.getString("Common.UnableToUnwrap", new Object[]{iface.toString()}), "S1009", this.exceptionInterceptor);
        }
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        boolean isInstance = iface.isInstance(this);
        if (isInstance) {
            return true;
        }
        String interfaceClassName = iface.getName();
        return interfaceClassName.equals("com.mysql.cj.jdbc.Statement") || interfaceClassName.equals("java.sql.Statement") || interfaceClassName.equals("java.sql.Wrapper");
    }

    @Override
    public boolean isClosed() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedStmt.isClosed();
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return false;
        }
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
            }
            this.wrappedStmt.setPoolable(poolable);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public boolean isPoolable() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return this.wrappedStmt.isPoolable();
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return false;
        }
    }

    @Override
    public void closeOnCompletion() throws SQLException {
        if (this.wrappedStmt == null) {
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        }
    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        if (this.wrappedStmt == null) {
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        }
        return false;
    }

    @Override
    public long[] executeLargeBatch() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((StatementImpl)this.wrappedStmt).executeLargeBatch();
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public long executeLargeUpdate(String sql) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((StatementImpl)this.wrappedStmt).executeLargeUpdate(sql);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return -1L;
        }
    }

    @Override
    public long executeLargeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((StatementImpl)this.wrappedStmt).executeLargeUpdate(sql, autoGeneratedKeys);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return -1L;
        }
    }

    @Override
    public long executeLargeUpdate(String sql, int[] columnIndexes) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((StatementImpl)this.wrappedStmt).executeLargeUpdate(sql, columnIndexes);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return -1L;
        }
    }

    @Override
    public long executeLargeUpdate(String sql, String[] columnNames) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((StatementImpl)this.wrappedStmt).executeLargeUpdate(sql, columnNames);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return -1L;
        }
    }

    @Override
    public long getLargeMaxRows() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((StatementImpl)this.wrappedStmt).getLargeMaxRows();
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return 0L;
        }
    }

    @Override
    public long getLargeUpdateCount() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((StatementImpl)this.wrappedStmt).getLargeUpdateCount();
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return -1L;
        }
    }

    @Override
    public void setLargeMaxRows(long max) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
            }
            ((StatementImpl)this.wrappedStmt).setLargeMaxRows(max);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }
}

