/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.ha;

import com.mysql.cj.Messages;
import com.mysql.cj.ServerVersion;
import com.mysql.cj.Session;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.interceptors.QueryInterceptor;
import com.mysql.cj.jdbc.ClientInfoProvider;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.JdbcPreparedStatement;
import com.mysql.cj.jdbc.JdbcPropertySet;
import com.mysql.cj.jdbc.JdbcStatement;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import com.mysql.cj.jdbc.ha.MultiHostConnectionProxy;
import com.mysql.cj.jdbc.result.CachedResultSetMetaData;
import com.mysql.cj.jdbc.result.ResultSetInternalMethods;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
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
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class MultiHostMySQLConnection
implements JdbcConnection {
    protected MultiHostConnectionProxy thisAsProxy;

    public MultiHostMySQLConnection(MultiHostConnectionProxy proxy) {
        this.thisAsProxy = proxy;
    }

    public MultiHostConnectionProxy getThisAsProxy() {
        return this.thisAsProxy;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public JdbcConnection getActiveMySQLConnection() {
        MultiHostConnectionProxy multiHostConnectionProxy = this.thisAsProxy;
        synchronized (multiHostConnectionProxy) {
            return this.thisAsProxy.currentConnection;
        }
    }

    @Override
    public void abortInternal() throws SQLException {
        try {
            this.getActiveMySQLConnection().abortInternal();
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void changeUser(String userName, String newPassword) throws SQLException {
        try {
            this.getActiveMySQLConnection().changeUser(userName, newPassword);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void checkClosed() {
        this.getActiveMySQLConnection().checkClosed();
    }

    @Override
    @Deprecated
    public void clearHasTriedMaster() {
        this.getActiveMySQLConnection().clearHasTriedMaster();
    }

    @Override
    public void clearWarnings() throws SQLException {
        try {
            this.getActiveMySQLConnection().clearWarnings();
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement clientPrepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        try {
            return this.getActiveMySQLConnection().clientPrepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement clientPrepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        try {
            return this.getActiveMySQLConnection().clientPrepareStatement(sql, resultSetType, resultSetConcurrency);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement clientPrepareStatement(String sql, int autoGenKeyIndex) throws SQLException {
        try {
            return this.getActiveMySQLConnection().clientPrepareStatement(sql, autoGenKeyIndex);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement clientPrepareStatement(String sql, int[] autoGenKeyIndexes) throws SQLException {
        try {
            return this.getActiveMySQLConnection().clientPrepareStatement(sql, autoGenKeyIndexes);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement clientPrepareStatement(String sql, String[] autoGenKeyColNames) throws SQLException {
        try {
            return this.getActiveMySQLConnection().clientPrepareStatement(sql, autoGenKeyColNames);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement clientPrepareStatement(String sql) throws SQLException {
        try {
            return this.getActiveMySQLConnection().clientPrepareStatement(sql);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void close() throws SQLException {
        try {
            this.getActiveMySQLConnection().close();
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void commit() throws SQLException {
        try {
            this.getActiveMySQLConnection().commit();
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void createNewIO(boolean isForReconnect) {
        this.getActiveMySQLConnection().createNewIO(isForReconnect);
    }

    @Override
    public Statement createStatement() throws SQLException {
        try {
            return this.getActiveMySQLConnection().createStatement();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        try {
            return this.getActiveMySQLConnection().createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        try {
            return this.getActiveMySQLConnection().createStatement(resultSetType, resultSetConcurrency);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getActiveStatementCount() {
        return this.getActiveMySQLConnection().getActiveStatementCount();
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        try {
            return this.getActiveMySQLConnection().getAutoCommit();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getAutoIncrementIncrement() {
        return this.getActiveMySQLConnection().getAutoIncrementIncrement();
    }

    @Override
    public CachedResultSetMetaData getCachedMetaData(String sql) {
        return this.getActiveMySQLConnection().getCachedMetaData(sql);
    }

    @Override
    public String getCatalog() throws SQLException {
        try {
            return this.getActiveMySQLConnection().getCatalog();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public String getCharacterSetMetadata() {
        return this.getActiveMySQLConnection().getCharacterSetMetadata();
    }

    @Override
    public ExceptionInterceptor getExceptionInterceptor() {
        return this.getActiveMySQLConnection().getExceptionInterceptor();
    }

    @Override
    public int getHoldability() throws SQLException {
        try {
            return this.getActiveMySQLConnection().getHoldability();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public String getHost() {
        return this.getActiveMySQLConnection().getHost();
    }

    @Override
    public long getId() {
        return this.getActiveMySQLConnection().getId();
    }

    @Override
    public long getIdleFor() {
        return this.getActiveMySQLConnection().getIdleFor();
    }

    @Override
    public JdbcConnection getMultiHostSafeProxy() {
        return this.getThisAsProxy().getProxy();
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        try {
            return this.getActiveMySQLConnection().getMetaData();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Statement getMetadataSafeStatement() throws SQLException {
        try {
            return this.getActiveMySQLConnection().getMetadataSafeStatement();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Properties getProperties() {
        return this.getActiveMySQLConnection().getProperties();
    }

    @Override
    public ServerVersion getServerVersion() {
        return this.getActiveMySQLConnection().getServerVersion();
    }

    @Override
    public Session getSession() {
        return this.getActiveMySQLConnection().getSession();
    }

    @Override
    public String getStatementComment() {
        return this.getActiveMySQLConnection().getStatementComment();
    }

    @Override
    public List<QueryInterceptor> getQueryInterceptorsInstances() {
        return this.getActiveMySQLConnection().getQueryInterceptorsInstances();
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        try {
            return this.getActiveMySQLConnection().getTransactionIsolation();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        try {
            return this.getActiveMySQLConnection().getTypeMap();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public String getURL() {
        return this.getActiveMySQLConnection().getURL();
    }

    @Override
    public String getUser() {
        return this.getActiveMySQLConnection().getUser();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        try {
            return this.getActiveMySQLConnection().getWarnings();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean hasSameProperties(JdbcConnection c) {
        return this.getActiveMySQLConnection().hasSameProperties(c);
    }

    @Override
    @Deprecated
    public boolean hasTriedMaster() {
        return this.getActiveMySQLConnection().hasTriedMaster();
    }

    @Override
    public void initializeResultsMetadataFromCache(String sql, CachedResultSetMetaData cachedMetaData, ResultSetInternalMethods resultSet) throws SQLException {
        try {
            this.getActiveMySQLConnection().initializeResultsMetadataFromCache(sql, cachedMetaData, resultSet);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void initializeSafeQueryInterceptors() throws SQLException {
        try {
            this.getActiveMySQLConnection().initializeSafeQueryInterceptors();
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isInGlobalTx() {
        return this.getActiveMySQLConnection().isInGlobalTx();
    }

    @Override
    public boolean isMasterConnection() {
        return this.getThisAsProxy().isMasterConnection();
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        try {
            return this.getActiveMySQLConnection().isReadOnly();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isReadOnly(boolean useSessionStatus) throws SQLException {
        try {
            return this.getActiveMySQLConnection().isReadOnly(useSessionStatus);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isSameResource(JdbcConnection otherConnection) {
        return this.getActiveMySQLConnection().isSameResource(otherConnection);
    }

    @Override
    public boolean lowerCaseTableNames() {
        return this.getActiveMySQLConnection().lowerCaseTableNames();
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        try {
            return this.getActiveMySQLConnection().nativeSQL(sql);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void ping() throws SQLException {
        try {
            this.getActiveMySQLConnection().ping();
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void pingInternal(boolean checkForClosedConnection, int timeoutMillis) throws SQLException {
        try {
            this.getActiveMySQLConnection().pingInternal(checkForClosedConnection, timeoutMillis);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        try {
            return this.getActiveMySQLConnection().prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        try {
            return this.getActiveMySQLConnection().prepareCall(sql, resultSetType, resultSetConcurrency);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        try {
            return this.getActiveMySQLConnection().prepareCall(sql);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        try {
            return this.getActiveMySQLConnection().prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        try {
            return this.getActiveMySQLConnection().prepareStatement(sql, resultSetType, resultSetConcurrency);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGenKeyIndex) throws SQLException {
        try {
            return this.getActiveMySQLConnection().prepareStatement(sql, autoGenKeyIndex);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] autoGenKeyIndexes) throws SQLException {
        try {
            return this.getActiveMySQLConnection().prepareStatement(sql, autoGenKeyIndexes);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] autoGenKeyColNames) throws SQLException {
        try {
            return this.getActiveMySQLConnection().prepareStatement(sql, autoGenKeyColNames);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        try {
            return this.getActiveMySQLConnection().prepareStatement(sql);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void realClose(boolean calledExplicitly, boolean issueRollback, boolean skipLocalTeardown, Throwable reason) throws SQLException {
        try {
            this.getActiveMySQLConnection().realClose(calledExplicitly, issueRollback, skipLocalTeardown, reason);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void recachePreparedStatement(JdbcPreparedStatement pstmt) throws SQLException {
        try {
            this.getActiveMySQLConnection().recachePreparedStatement(pstmt);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void decachePreparedStatement(JdbcPreparedStatement pstmt) throws SQLException {
        try {
            this.getActiveMySQLConnection().decachePreparedStatement(pstmt);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void registerStatement(JdbcStatement stmt) {
        this.getActiveMySQLConnection().registerStatement(stmt);
    }

    @Override
    public void releaseSavepoint(Savepoint arg0) throws SQLException {
        try {
            this.getActiveMySQLConnection().releaseSavepoint(arg0);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void resetServerState() throws SQLException {
        try {
            this.getActiveMySQLConnection().resetServerState();
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void rollback() throws SQLException {
        try {
            this.getActiveMySQLConnection().rollback();
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        try {
            this.getActiveMySQLConnection().rollback(savepoint);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement serverPrepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        try {
            return this.getActiveMySQLConnection().serverPrepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement serverPrepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        try {
            return this.getActiveMySQLConnection().serverPrepareStatement(sql, resultSetType, resultSetConcurrency);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement serverPrepareStatement(String sql, int autoGenKeyIndex) throws SQLException {
        try {
            return this.getActiveMySQLConnection().serverPrepareStatement(sql, autoGenKeyIndex);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement serverPrepareStatement(String sql, int[] autoGenKeyIndexes) throws SQLException {
        try {
            return this.getActiveMySQLConnection().serverPrepareStatement(sql, autoGenKeyIndexes);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement serverPrepareStatement(String sql, String[] autoGenKeyColNames) throws SQLException {
        try {
            return this.getActiveMySQLConnection().serverPrepareStatement(sql, autoGenKeyColNames);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement serverPrepareStatement(String sql) throws SQLException {
        try {
            return this.getActiveMySQLConnection().serverPrepareStatement(sql);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setAutoCommit(boolean autoCommitFlag) throws SQLException {
        try {
            this.getActiveMySQLConnection().setAutoCommit(autoCommitFlag);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {
        try {
            this.getActiveMySQLConnection().setCatalog(catalog);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setFailedOver(boolean flag) {
        this.getActiveMySQLConnection().setFailedOver(flag);
    }

    @Override
    public void setHoldability(int arg0) throws SQLException {
        try {
            this.getActiveMySQLConnection().setHoldability(arg0);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setInGlobalTx(boolean flag) {
        this.getActiveMySQLConnection().setInGlobalTx(flag);
    }

    @Override
    public void setProxy(JdbcConnection proxy) {
        this.getThisAsProxy().setProxy(proxy);
    }

    @Override
    public void setReadOnly(boolean readOnlyFlag) throws SQLException {
        try {
            this.getActiveMySQLConnection().setReadOnly(readOnlyFlag);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setReadOnlyInternal(boolean readOnlyFlag) throws SQLException {
        try {
            this.getActiveMySQLConnection().setReadOnlyInternal(readOnlyFlag);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        try {
            return this.getActiveMySQLConnection().setSavepoint();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        try {
            return this.getActiveMySQLConnection().setSavepoint(name);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setStatementComment(String comment) {
        this.getActiveMySQLConnection().setStatementComment(comment);
    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        try {
            this.getActiveMySQLConnection().setTransactionIsolation(level);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void shutdownServer() throws SQLException {
        try {
            this.getActiveMySQLConnection().shutdownServer();
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean storesLowerCaseTableName() {
        return this.getActiveMySQLConnection().storesLowerCaseTableName();
    }

    @Override
    public void throwConnectionClosedException() throws SQLException {
        try {
            this.getActiveMySQLConnection().throwConnectionClosedException();
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void transactionBegun() {
        this.getActiveMySQLConnection().transactionBegun();
    }

    @Override
    public void transactionCompleted() {
        this.getActiveMySQLConnection().transactionCompleted();
    }

    @Override
    public void unregisterStatement(JdbcStatement stmt) {
        this.getActiveMySQLConnection().unregisterStatement(stmt);
    }

    @Override
    public void unSafeQueryInterceptors() throws SQLException {
        try {
            this.getActiveMySQLConnection().unSafeQueryInterceptors();
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isClosed() throws SQLException {
        try {
            return this.getThisAsProxy().isClosed;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isProxySet() {
        return this.getActiveMySQLConnection().isProxySet();
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        try {
            this.getActiveMySQLConnection().setTypeMap(map);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isServerLocal() throws SQLException {
        try {
            return this.getActiveMySQLConnection().isServerLocal();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setSchema(String schema) throws SQLException {
        try {
            this.getActiveMySQLConnection().setSchema(schema);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public String getSchema() throws SQLException {
        try {
            return this.getActiveMySQLConnection().getSchema();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        try {
            this.getActiveMySQLConnection().abort(executor);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        try {
            this.getActiveMySQLConnection().setNetworkTimeout(executor, milliseconds);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        try {
            return this.getActiveMySQLConnection().getNetworkTimeout();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Object getConnectionMutex() {
        return this.getActiveMySQLConnection().getConnectionMutex();
    }

    @Override
    public int getSessionMaxRows() {
        return this.getActiveMySQLConnection().getSessionMaxRows();
    }

    @Override
    public void setSessionMaxRows(int max) throws SQLException {
        try {
            this.getActiveMySQLConnection().setSessionMaxRows(max);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        try {
            return this.getActiveMySQLConnection().createSQLXML();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        try {
            return this.getActiveMySQLConnection().createArrayOf(typeName, elements);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        try {
            return this.getActiveMySQLConnection().createStruct(typeName, attributes);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        try {
            return this.getActiveMySQLConnection().getClientInfo();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        try {
            return this.getActiveMySQLConnection().getClientInfo(name);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isValid(int timeout2) throws SQLException {
        try {
            return this.getActiveMySQLConnection().isValid(timeout2);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        this.getActiveMySQLConnection().setClientInfo(properties);
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        this.getActiveMySQLConnection().setClientInfo(name, value);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        try {
            return iface.isInstance(this);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        try {
            try {
                return iface.cast(this);
            } catch (ClassCastException cce) {
                throw SQLError.createSQLException(Messages.getString("Common.UnableToUnwrap", new Object[]{iface.toString()}), "S1009", this.getExceptionInterceptor());
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Blob createBlob() throws SQLException {
        try {
            return this.getActiveMySQLConnection().createBlob();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Clob createClob() throws SQLException {
        try {
            return this.getActiveMySQLConnection().createClob();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public NClob createNClob() throws SQLException {
        try {
            return this.getActiveMySQLConnection().createNClob();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ClientInfoProvider getClientInfoProviderImpl() throws SQLException {
        try {
            MultiHostConnectionProxy multiHostConnectionProxy = this.getThisAsProxy();
            synchronized (multiHostConnectionProxy) {
                return this.getActiveMySQLConnection().getClientInfoProviderImpl();
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public JdbcPropertySet getPropertySet() {
        return this.getActiveMySQLConnection().getPropertySet();
    }

    @Override
    public String getHostPortPair() {
        return this.getActiveMySQLConnection().getHostPortPair();
    }

    @Override
    public void normalClose() {
        this.getActiveMySQLConnection().normalClose();
    }

    @Override
    public void cleanup(Throwable whyCleanedUp) {
        this.getActiveMySQLConnection().cleanup(whyCleanedUp);
    }
}

