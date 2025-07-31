/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.MysqlConnection;
import com.mysql.cj.ServerVersion;
import com.mysql.cj.TransactionEventHandler;
import com.mysql.cj.interceptors.QueryInterceptor;
import com.mysql.cj.jdbc.ClientInfoProvider;
import com.mysql.cj.jdbc.JdbcPreparedStatement;
import com.mysql.cj.jdbc.JdbcPropertySet;
import com.mysql.cj.jdbc.JdbcStatement;
import com.mysql.cj.jdbc.result.CachedResultSetMetaData;
import com.mysql.cj.jdbc.result.ResultSetInternalMethods;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface JdbcConnection
extends Connection,
MysqlConnection,
TransactionEventHandler {
    @Override
    public JdbcPropertySet getPropertySet();

    public void changeUser(String var1, String var2) throws SQLException;

    @Deprecated
    public void clearHasTriedMaster();

    public PreparedStatement clientPrepareStatement(String var1) throws SQLException;

    public PreparedStatement clientPrepareStatement(String var1, int var2) throws SQLException;

    public PreparedStatement clientPrepareStatement(String var1, int var2, int var3) throws SQLException;

    public PreparedStatement clientPrepareStatement(String var1, int[] var2) throws SQLException;

    public PreparedStatement clientPrepareStatement(String var1, int var2, int var3, int var4) throws SQLException;

    public PreparedStatement clientPrepareStatement(String var1, String[] var2) throws SQLException;

    public int getActiveStatementCount();

    public long getIdleFor();

    public String getStatementComment();

    @Deprecated
    public boolean hasTriedMaster();

    public boolean isInGlobalTx();

    public void setInGlobalTx(boolean var1);

    public boolean isMasterConnection();

    public boolean isSameResource(JdbcConnection var1);

    public boolean lowerCaseTableNames();

    public void ping() throws SQLException;

    public void resetServerState() throws SQLException;

    public PreparedStatement serverPrepareStatement(String var1) throws SQLException;

    public PreparedStatement serverPrepareStatement(String var1, int var2) throws SQLException;

    public PreparedStatement serverPrepareStatement(String var1, int var2, int var3) throws SQLException;

    public PreparedStatement serverPrepareStatement(String var1, int var2, int var3, int var4) throws SQLException;

    public PreparedStatement serverPrepareStatement(String var1, int[] var2) throws SQLException;

    public PreparedStatement serverPrepareStatement(String var1, String[] var2) throws SQLException;

    public void setFailedOver(boolean var1);

    public void setStatementComment(String var1);

    public void shutdownServer() throws SQLException;

    public int getAutoIncrementIncrement();

    public boolean hasSameProperties(JdbcConnection var1);

    public String getHost();

    public String getHostPortPair();

    public void setProxy(JdbcConnection var1);

    public boolean isServerLocal() throws SQLException;

    public int getSessionMaxRows();

    public void setSessionMaxRows(int var1) throws SQLException;

    @Override
    public void setSchema(String var1) throws SQLException;

    public void abortInternal() throws SQLException;

    public boolean isProxySet();

    public CachedResultSetMetaData getCachedMetaData(String var1);

    public String getCharacterSetMetadata();

    public Statement getMetadataSafeStatement() throws SQLException;

    public ServerVersion getServerVersion();

    public List<QueryInterceptor> getQueryInterceptorsInstances();

    public void initializeResultsMetadataFromCache(String var1, CachedResultSetMetaData var2, ResultSetInternalMethods var3) throws SQLException;

    public void initializeSafeQueryInterceptors() throws SQLException;

    public boolean isReadOnly(boolean var1) throws SQLException;

    public void pingInternal(boolean var1, int var2) throws SQLException;

    public void realClose(boolean var1, boolean var2, boolean var3, Throwable var4) throws SQLException;

    public void recachePreparedStatement(JdbcPreparedStatement var1) throws SQLException;

    public void decachePreparedStatement(JdbcPreparedStatement var1) throws SQLException;

    public void registerStatement(JdbcStatement var1);

    public void setReadOnlyInternal(boolean var1) throws SQLException;

    public boolean storesLowerCaseTableName();

    public void throwConnectionClosedException() throws SQLException;

    public void unregisterStatement(JdbcStatement var1);

    public void unSafeQueryInterceptors() throws SQLException;

    public JdbcConnection getMultiHostSafeProxy();

    public JdbcConnection getActiveMySQLConnection();

    public ClientInfoProvider getClientInfoProviderImpl() throws SQLException;
}

