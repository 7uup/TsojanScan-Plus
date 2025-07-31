/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.BindValue;
import com.mysql.cj.CancelQueryTask;
import com.mysql.cj.CharsetMapping;
import com.mysql.cj.ClientPreparedQuery;
import com.mysql.cj.ClientPreparedQueryBindValue;
import com.mysql.cj.ClientPreparedQueryBindings;
import com.mysql.cj.Constants;
import com.mysql.cj.Messages;
import com.mysql.cj.MysqlType;
import com.mysql.cj.NativeSession;
import com.mysql.cj.ParseInfo;
import com.mysql.cj.PreparedQuery;
import com.mysql.cj.Query;
import com.mysql.cj.QueryBindings;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.FeatureNotAvailableException;
import com.mysql.cj.exceptions.StatementIsClosedException;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.JdbcPreparedStatement;
import com.mysql.cj.jdbc.MysqlParameterMetadata;
import com.mysql.cj.jdbc.MysqlSQLXML;
import com.mysql.cj.jdbc.ParameterBindings;
import com.mysql.cj.jdbc.StatementImpl;
import com.mysql.cj.jdbc.exceptions.MySQLStatementCancelledException;
import com.mysql.cj.jdbc.exceptions.MySQLTimeoutException;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import com.mysql.cj.jdbc.result.CachedResultSetMetaData;
import com.mysql.cj.jdbc.result.ResultSetImpl;
import com.mysql.cj.jdbc.result.ResultSetInternalMethods;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.log.ProfilerEventImpl;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.protocol.a.result.ByteArrayRow;
import com.mysql.cj.protocol.a.result.ResultsetRowsStatic;
import com.mysql.cj.result.DefaultColumnDefinition;
import com.mysql.cj.result.Field;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.util.Util;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.JDBCType;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

public class ClientPreparedStatement
extends StatementImpl
implements JdbcPreparedStatement {
    protected boolean batchHasPlainStatements = false;
    protected MysqlParameterMetadata parameterMetaData;
    private java.sql.ResultSetMetaData pstmtResultMetaData;
    protected String batchedValuesClause;
    private boolean doPingInstead;
    private boolean compensateForOnDuplicateKeyUpdate = this.session.getPropertySet().getBooleanProperty("compensateOnDuplicateKeyUpdateCounts").getValue();
    protected RuntimeProperty<Boolean> useStreamLengthsInPrepStmts = this.session.getPropertySet().getBooleanProperty("useStreamLengthsInPrepStmts");
    protected RuntimeProperty<Boolean> autoClosePStmtStreams = this.session.getPropertySet().getBooleanProperty("autoClosePStmtStreams");
    protected int rewrittenBatchSize = 0;

    protected static ClientPreparedStatement getInstance(JdbcConnection conn, String sql, String catalog) throws SQLException {
        return new ClientPreparedStatement(conn, sql, catalog);
    }

    protected static ClientPreparedStatement getInstance(JdbcConnection conn, String sql, String catalog, ParseInfo cachedParseInfo) throws SQLException {
        return new ClientPreparedStatement(conn, sql, catalog, cachedParseInfo);
    }

    @Override
    protected void initQuery() {
        this.query = new ClientPreparedQuery(this.session);
    }

    protected ClientPreparedStatement(JdbcConnection conn, String catalog) throws SQLException {
        super(conn, catalog);
    }

    public ClientPreparedStatement(JdbcConnection conn, String sql, String catalog) throws SQLException {
        this(conn, sql, catalog, null);
    }

    public ClientPreparedStatement(JdbcConnection conn, String sql, String catalog, ParseInfo cachedParseInfo) throws SQLException {
        this(conn, catalog);
        try {
            ((PreparedQuery)this.query).checkNullOrEmptyQuery(sql);
            ((PreparedQuery)this.query).setOriginalSql(sql);
            ((PreparedQuery)this.query).setParseInfo(cachedParseInfo != null ? cachedParseInfo : new ParseInfo(sql, this.session, this.charEncoding));
        } catch (CJException e) {
            throw SQLExceptionsMapping.translateException(e, this.exceptionInterceptor);
        }
        this.doPingInstead = sql.startsWith("/* ping */");
        this.initializeFromParseInfo();
    }

    @Override
    public QueryBindings<?> getQueryBindings() {
        return ((PreparedQuery)this.query).getQueryBindings();
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(this.getClass().getName());
        buf.append(": ");
        try {
            buf.append(this.asSql());
        } catch (SQLException sqlEx) {
            buf.append("EXCEPTION: " + sqlEx.toString());
        }
        return buf.toString();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addBatch() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                Object queryBindings = ((PreparedQuery)this.query).getQueryBindings();
                queryBindings.checkAllParametersSet();
                this.query.addBatch(queryBindings.clone());
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addBatch(String sql) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                this.batchHasPlainStatements = true;
                super.addBatch(sql);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    public String asSql() throws SQLException {
        return ((PreparedQuery)this.query).asSql(false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String asSql(boolean quoteStreamsAndUnknowns) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                return ((PreparedQuery)this.query).asSql(quoteStreamsAndUnknowns);
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void clearBatch() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                this.batchHasPlainStatements = false;
                super.clearBatch();
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void clearParameters() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                for (BindValue bv : ((PreparedQuery)this.query).getQueryBindings().getBindValues()) {
                    bv.setNull(false);
                    bv.setIsStream(false);
                    bv.setMysqlType(MysqlType.NULL);
                    bv.setByteValue(null);
                    bv.setStreamValue(null, 0);
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected boolean checkReadOnlySafeStatement() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                return ((PreparedQuery)this.query).getParseInfo().getFirstStmtChar() == 'S' || !this.connection.isReadOnly();
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean execute() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                JdbcConnection locallyScopedConn = this.connection;
                if (!this.doPingInstead && !this.checkReadOnlySafeStatement()) {
                    throw SQLError.createSQLException(Messages.getString("PreparedStatement.20") + Messages.getString("PreparedStatement.21"), "S1009", this.exceptionInterceptor);
                }
                ResultSetInternalMethods rs = null;
                this.lastQueryIsOnDupKeyUpdate = false;
                if (this.retrieveGeneratedKeys) {
                    this.lastQueryIsOnDupKeyUpdate = this.containsOnDuplicateKeyUpdateInSQL();
                }
                this.batchedGeneratedKeys = null;
                this.resetCancelledState();
                this.implicitlyCloseAllOpenResults();
                this.clearWarnings();
                if (this.doPingInstead) {
                    this.doPingInstead();
                    return true;
                }
                this.setupStreamingTimeout(locallyScopedConn);
                Object sendPacket = ((PreparedQuery)this.query).fillSendPacket();
                String oldCatalog = null;
                if (!locallyScopedConn.getCatalog().equals(this.getCurrentCatalog())) {
                    oldCatalog = locallyScopedConn.getCatalog();
                    locallyScopedConn.setCatalog(this.getCurrentCatalog());
                }
                CachedResultSetMetaData cachedMetadata = null;
                boolean cacheResultSetMetadata = locallyScopedConn.getPropertySet().getBooleanProperty("cacheResultSetMetadata").getValue();
                if (cacheResultSetMetadata) {
                    cachedMetadata = locallyScopedConn.getCachedMetaData(((PreparedQuery)this.query).getOriginalSql());
                }
                locallyScopedConn.setSessionMaxRows(((PreparedQuery)this.query).getParseInfo().getFirstStmtChar() == 'S' ? this.maxRows : -1);
                rs = this.executeInternal(this.maxRows, sendPacket, this.createStreamingResultSet(), ((PreparedQuery)this.query).getParseInfo().getFirstStmtChar() == 'S', cachedMetadata, false);
                if (cachedMetadata != null) {
                    locallyScopedConn.initializeResultsMetadataFromCache(((PreparedQuery)this.query).getOriginalSql(), cachedMetadata, rs);
                } else if (rs.hasRows() && cacheResultSetMetadata) {
                    locallyScopedConn.initializeResultsMetadataFromCache(((PreparedQuery)this.query).getOriginalSql(), null, rs);
                }
                if (this.retrieveGeneratedKeys) {
                    rs.setFirstCharOfQuery(((PreparedQuery)this.query).getParseInfo().getFirstStmtChar());
                }
                if (oldCatalog != null) {
                    locallyScopedConn.setCatalog(oldCatalog);
                }
                if (rs != null) {
                    this.lastInsertId = rs.getUpdateID();
                    this.results = rs;
                }
                return rs != null && rs.hasRows();
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    protected long[] executeBatchInternal() throws SQLException {
        Object object = this.checkClosed().getConnectionMutex();
        synchronized (object) {
            if (this.connection.isReadOnly()) {
                throw new SQLException(Messages.getString("PreparedStatement.25") + Messages.getString("PreparedStatement.26"), "S1009");
            }
            if (this.query.getBatchedArgs() == null || this.query.getBatchedArgs().size() == 0) {
                return new long[0];
            }
            int batchTimeout = this.getTimeoutInMillis();
            this.setTimeoutInMillis(0);
            this.resetCancelledState();
            try {
                this.statementBegins();
                this.clearWarnings();
                if (!this.batchHasPlainStatements && ((Boolean)this.rewriteBatchedStatements.getValue()).booleanValue()) {
                    if (((PreparedQuery)this.query).getParseInfo().canRewriteAsMultiValueInsertAtSqlLevel()) {
                        long[] lArray = this.executeBatchedInserts(batchTimeout);
                        return lArray;
                    }
                    if (!this.batchHasPlainStatements && this.query.getBatchedArgs() != null && this.query.getBatchedArgs().size() > 3) {
                        long[] lArray = this.executePreparedBatchAsMultiStatement(batchTimeout);
                        return lArray;
                    }
                }
                long[] lArray = this.executeBatchSerially(batchTimeout);
                return lArray;
            } finally {
                this.query.getStatementExecuting().set(false);
                this.clearBatch();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     */
    protected long[] executePreparedBatchAsMultiStatement(int batchTimeout) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                long[] lArray;
                Statement batchedStatement;
                CancelQueryTask timeoutTask;
                boolean multiQueriesEnabled;
                JdbcConnection locallyScopedConn;
                block37: {
                    int numValuesPerBatch;
                    if (this.batchedValuesClause == null) {
                        this.batchedValuesClause = ((PreparedQuery)this.query).getOriginalSql() + ";";
                    }
                    locallyScopedConn = this.connection;
                    multiQueriesEnabled = locallyScopedConn.getPropertySet().getBooleanProperty("allowMultiQueries").getValue();
                    timeoutTask = null;
                    this.clearWarnings();
                    int numBatchedArgs = this.query.getBatchedArgs().size();
                    if (this.retrieveGeneratedKeys) {
                        this.batchedGeneratedKeys = new ArrayList(numBatchedArgs);
                    }
                    if (numBatchedArgs < (numValuesPerBatch = ((PreparedQuery)this.query).computeBatchSize(numBatchedArgs))) {
                        numValuesPerBatch = numBatchedArgs;
                    }
                    batchedStatement = null;
                    int batchedParamIndex = 1;
                    int numberToExecuteAsMultiValue = 0;
                    int batchCounter = 0;
                    int updateCountCounter = 0;
                    long[] updateCounts = new long[numBatchedArgs];
                    SQLException sqlEx = null;
                    try {
                        if (!multiQueriesEnabled) {
                            ((NativeSession)locallyScopedConn.getSession()).enableMultiQueries();
                        }
                        batchedStatement = this.retrieveGeneratedKeys ? locallyScopedConn.prepareStatement(this.generateMultiStatementForBatch(numValuesPerBatch), 1).unwrap(PreparedStatement.class) : locallyScopedConn.prepareStatement(this.generateMultiStatementForBatch(numValuesPerBatch)).unwrap(PreparedStatement.class);
                        timeoutTask = this.startQueryTimer((StatementImpl)batchedStatement, batchTimeout);
                        numberToExecuteAsMultiValue = numBatchedArgs < numValuesPerBatch ? numBatchedArgs : numBatchedArgs / numValuesPerBatch;
                        int numberArgsToExecute = numberToExecuteAsMultiValue * numValuesPerBatch;
                        for (int i = 0; i < numberArgsToExecute; ++i) {
                            if (i != 0 && i % numValuesPerBatch == 0) {
                                try {
                                    batchedStatement.execute();
                                } catch (SQLException ex) {
                                    sqlEx = this.handleExceptionForBatch(batchCounter, numValuesPerBatch, updateCounts, ex);
                                }
                                updateCountCounter = this.processMultiCountsAndKeys((StatementImpl)batchedStatement, updateCountCounter, updateCounts);
                                batchedStatement.clearParameters();
                                batchedParamIndex = 1;
                            }
                            batchedParamIndex = this.setOneBatchedParameterSet((PreparedStatement)batchedStatement, batchedParamIndex, this.query.getBatchedArgs().get(batchCounter++));
                        }
                        try {
                            batchedStatement.execute();
                        } catch (SQLException ex) {
                            sqlEx = this.handleExceptionForBatch(batchCounter - 1, numValuesPerBatch, updateCounts, ex);
                        }
                        updateCountCounter = this.processMultiCountsAndKeys((StatementImpl)batchedStatement, updateCountCounter, updateCounts);
                        batchedStatement.clearParameters();
                        numValuesPerBatch = numBatchedArgs - batchCounter;
                        if (timeoutTask != null) {
                            ((JdbcPreparedStatement)batchedStatement).checkCancelTimeout();
                        }
                    } finally {
                        if (batchedStatement != null) {
                            batchedStatement.close();
                            batchedStatement = null;
                        }
                    }
                    if (numValuesPerBatch > 0) {
                        Statement statement = batchedStatement = this.retrieveGeneratedKeys ? locallyScopedConn.prepareStatement(this.generateMultiStatementForBatch(numValuesPerBatch), 1) : locallyScopedConn.prepareStatement(this.generateMultiStatementForBatch(numValuesPerBatch));
                        if (timeoutTask != null) {
                            timeoutTask.setQueryToCancel((Query)((Object)batchedStatement));
                        }
                        batchedParamIndex = 1;
                        while (batchCounter < numBatchedArgs) {
                            batchedParamIndex = this.setOneBatchedParameterSet((PreparedStatement)batchedStatement, batchedParamIndex, this.query.getBatchedArgs().get(batchCounter++));
                        }
                        try {
                            batchedStatement.execute();
                        } catch (SQLException ex) {
                            sqlEx = this.handleExceptionForBatch(batchCounter - 1, numValuesPerBatch, updateCounts, ex);
                        }
                        updateCountCounter = this.processMultiCountsAndKeys((StatementImpl)batchedStatement, updateCountCounter, updateCounts);
                        batchedStatement.clearParameters();
                    }
                    if (timeoutTask != null) {
                        this.stopQueryTimer(timeoutTask, true, true);
                        timeoutTask = null;
                    }
                    if (sqlEx != null) {
                        throw SQLError.createBatchUpdateException(sqlEx, updateCounts, this.exceptionInterceptor);
                    }
                    lArray = updateCounts;
                    if (batchedStatement == null) break block37;
                    batchedStatement.close();
                }
                this.stopQueryTimer(timeoutTask, false, false);
                this.resetCancelledState();
                if (!multiQueriesEnabled) {
                    ((NativeSession)locallyScopedConn.getSession()).disableMultiQueries();
                }
                this.clearBatch();
                return lArray;
                {
                    catch (Throwable throwable) {
                        try {
                            if (batchedStatement != null) {
                                batchedStatement.close();
                            }
                            throw throwable;
                        } catch (Throwable throwable2) {
                            this.stopQueryTimer(timeoutTask, false, false);
                            this.resetCancelledState();
                            if (!multiQueriesEnabled) {
                                ((NativeSession)locallyScopedConn.getSession()).disableMultiQueries();
                            }
                            this.clearBatch();
                            throw throwable2;
                        }
                    }
                }
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected int setOneBatchedParameterSet(PreparedStatement batchedStatement, int batchedParamIndex, Object paramSet) throws SQLException {
        QueryBindings paramArg = (QueryBindings)paramSet;
        BindValue[] bindValues = paramArg.getBindValues();
        for (int j = 0; j < bindValues.length; ++j) {
            if (bindValues[j].isNull()) {
                batchedStatement.setNull(batchedParamIndex++, MysqlType.NULL.getJdbcType());
                continue;
            }
            if (bindValues[j].isStream()) {
                batchedStatement.setBinaryStream(batchedParamIndex++, bindValues[j].getStreamValue(), bindValues[j].getStreamLength());
                continue;
            }
            ((JdbcPreparedStatement)batchedStatement).setBytesNoEscapeNoQuotes(batchedParamIndex++, bindValues[j].getByteValue());
        }
        return batchedParamIndex;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private String generateMultiStatementForBatch(int numBatches) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                String origSql = ((PreparedQuery)this.query).getOriginalSql();
                StringBuilder newStatementSql = new StringBuilder((origSql.length() + 1) * numBatches);
                newStatementSql.append(origSql);
                for (int i = 0; i < numBatches - 1; ++i) {
                    newStatementSql.append(';');
                    newStatementSql.append(origSql);
                }
                return newStatementSql.toString();
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     */
    protected long[] executeBatchedInserts(int batchTimeout) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                long[] lArray;
                CancelQueryTask timeoutTask;
                ClientPreparedStatement batchedStatement;
                block35: {
                    int numValuesPerBatch;
                    String valuesClause = ((PreparedQuery)this.query).getParseInfo().getValuesClause();
                    JdbcConnection locallyScopedConn = this.connection;
                    if (valuesClause == null) {
                        return this.executeBatchSerially(batchTimeout);
                    }
                    int numBatchedArgs = this.query.getBatchedArgs().size();
                    if (this.retrieveGeneratedKeys) {
                        this.batchedGeneratedKeys = new ArrayList(numBatchedArgs);
                    }
                    if (numBatchedArgs < (numValuesPerBatch = ((PreparedQuery)this.query).computeBatchSize(numBatchedArgs))) {
                        numValuesPerBatch = numBatchedArgs;
                    }
                    batchedStatement = null;
                    int batchedParamIndex = 1;
                    long updateCountRunningTotal = 0L;
                    int numberToExecuteAsMultiValue = 0;
                    int batchCounter = 0;
                    timeoutTask = null;
                    SQLException sqlEx = null;
                    long[] updateCounts = new long[numBatchedArgs];
                    try {
                        batchedStatement = this.prepareBatchedInsertSQL(locallyScopedConn, numValuesPerBatch);
                        timeoutTask = this.startQueryTimer(batchedStatement, batchTimeout);
                        numberToExecuteAsMultiValue = numBatchedArgs < numValuesPerBatch ? numBatchedArgs : numBatchedArgs / numValuesPerBatch;
                        int numberArgsToExecute = numberToExecuteAsMultiValue * numValuesPerBatch;
                        for (int i = 0; i < numberArgsToExecute; ++i) {
                            if (i != 0 && i % numValuesPerBatch == 0) {
                                try {
                                    updateCountRunningTotal += batchedStatement.executeLargeUpdate();
                                } catch (SQLException ex) {
                                    sqlEx = this.handleExceptionForBatch(batchCounter - 1, numValuesPerBatch, updateCounts, ex);
                                }
                                this.getBatchedGeneratedKeys(batchedStatement);
                                batchedStatement.clearParameters();
                                batchedParamIndex = 1;
                            }
                            batchedParamIndex = this.setOneBatchedParameterSet(batchedStatement, batchedParamIndex, this.query.getBatchedArgs().get(batchCounter++));
                        }
                        try {
                            updateCountRunningTotal += batchedStatement.executeLargeUpdate();
                        } catch (SQLException ex) {
                            sqlEx = this.handleExceptionForBatch(batchCounter - 1, numValuesPerBatch, updateCounts, ex);
                        }
                        this.getBatchedGeneratedKeys(batchedStatement);
                        numValuesPerBatch = numBatchedArgs - batchCounter;
                    } finally {
                        if (batchedStatement != null) {
                            batchedStatement.close();
                            batchedStatement = null;
                        }
                    }
                    if (numValuesPerBatch > 0) {
                        batchedStatement = this.prepareBatchedInsertSQL(locallyScopedConn, numValuesPerBatch);
                        if (timeoutTask != null) {
                            timeoutTask.setQueryToCancel(batchedStatement);
                        }
                        batchedParamIndex = 1;
                        while (batchCounter < numBatchedArgs) {
                            batchedParamIndex = this.setOneBatchedParameterSet(batchedStatement, batchedParamIndex, this.query.getBatchedArgs().get(batchCounter++));
                        }
                        try {
                            updateCountRunningTotal += batchedStatement.executeLargeUpdate();
                        } catch (SQLException ex) {
                            sqlEx = this.handleExceptionForBatch(batchCounter - 1, numValuesPerBatch, updateCounts, ex);
                        }
                        this.getBatchedGeneratedKeys(batchedStatement);
                    }
                    if (sqlEx != null) {
                        throw SQLError.createBatchUpdateException(sqlEx, updateCounts, this.exceptionInterceptor);
                    }
                    if (numBatchedArgs > 1) {
                        long updCount = updateCountRunningTotal > 0L ? -2L : 0L;
                        for (int j = 0; j < numBatchedArgs; ++j) {
                            updateCounts[j] = updCount;
                        }
                    } else {
                        updateCounts[0] = updateCountRunningTotal;
                    }
                    lArray = updateCounts;
                    if (batchedStatement == null) break block35;
                    batchedStatement.close();
                }
                this.stopQueryTimer(timeoutTask, false, false);
                this.resetCancelledState();
                return lArray;
                {
                    catch (Throwable throwable) {
                        try {
                            if (batchedStatement != null) {
                                batchedStatement.close();
                            }
                            throw throwable;
                        } catch (Throwable throwable2) {
                            this.stopQueryTimer(timeoutTask, false, false);
                            this.resetCancelledState();
                            throw throwable2;
                        }
                    }
                }
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected long[] executeBatchSerially(int batchTimeout) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (this.connection == null) {
                    this.checkClosed();
                }
                long[] updateCounts = null;
                if (this.query.getBatchedArgs() != null) {
                    int nbrCommands = this.query.getBatchedArgs().size();
                    updateCounts = new long[nbrCommands];
                    for (int i = 0; i < nbrCommands; ++i) {
                        updateCounts[i] = -3L;
                    }
                    SQLException sqlEx = null;
                    CancelQueryTask timeoutTask = null;
                    try {
                        timeoutTask = this.startQueryTimer(this, batchTimeout);
                        if (this.retrieveGeneratedKeys) {
                            this.batchedGeneratedKeys = new ArrayList(nbrCommands);
                        }
                        int batchCommandIndex = ((PreparedQuery)this.query).getBatchCommandIndex();
                        for (batchCommandIndex = 0; batchCommandIndex < nbrCommands; ++batchCommandIndex) {
                            ((PreparedQuery)this.query).setBatchCommandIndex(batchCommandIndex);
                            Object arg = this.query.getBatchedArgs().get(batchCommandIndex);
                            try {
                                if (arg instanceof String) {
                                    updateCounts[batchCommandIndex] = this.executeUpdateInternal((String)arg, true, this.retrieveGeneratedKeys);
                                    this.getBatchedGeneratedKeys(this.results.getFirstCharOfQuery() == 'I' && this.containsOnDuplicateKeyInString((String)arg) ? 1 : 0);
                                    continue;
                                }
                                QueryBindings queryBindings = (QueryBindings)arg;
                                updateCounts[batchCommandIndex] = this.executeUpdateInternal(queryBindings, true);
                                this.getBatchedGeneratedKeys(this.containsOnDuplicateKeyUpdateInSQL() ? 1 : 0);
                                continue;
                            } catch (SQLException ex) {
                                updateCounts[batchCommandIndex] = -3L;
                                if (this.continueBatchOnError && !(ex instanceof MySQLTimeoutException) && !(ex instanceof MySQLStatementCancelledException) && !this.hasDeadlockOrTimeoutRolledBackTx(ex)) {
                                    sqlEx = ex;
                                    continue;
                                }
                                long[] newUpdateCounts = new long[batchCommandIndex];
                                System.arraycopy(updateCounts, 0, newUpdateCounts, 0, batchCommandIndex);
                                throw SQLError.createBatchUpdateException(ex, newUpdateCounts, this.exceptionInterceptor);
                            }
                        }
                        if (sqlEx != null) {
                            throw SQLError.createBatchUpdateException(sqlEx, updateCounts, this.exceptionInterceptor);
                        }
                    } catch (NullPointerException npe) {
                        try {
                            this.checkClosed();
                        } catch (StatementIsClosedException connectionClosedEx) {
                            int batchCommandIndex = ((PreparedQuery)this.query).getBatchCommandIndex();
                            updateCounts[batchCommandIndex] = -3L;
                            long[] newUpdateCounts = new long[batchCommandIndex];
                            System.arraycopy(updateCounts, 0, newUpdateCounts, 0, batchCommandIndex);
                            throw SQLError.createBatchUpdateException(SQLExceptionsMapping.translateException(connectionClosedEx), newUpdateCounts, this.exceptionInterceptor);
                        }
                        throw npe;
                    } finally {
                        ((PreparedQuery)this.query).setBatchCommandIndex(-1);
                        this.stopQueryTimer(timeoutTask, false, false);
                        this.resetCancelledState();
                    }
                }
                return updateCounts != null ? updateCounts : new long[]{};
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected <M extends Message> ResultSetInternalMethods executeInternal(int maxRowsToRetrieve, M sendPacket, boolean createStreamingResultSet, boolean queryIsSelectOnly, ColumnDefinition metadata, boolean isBatch) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                try {
                    ResultSetInternalMethods rs;
                    JdbcConnection locallyScopedConnection = this.connection;
                    ((PreparedQuery)this.query).getQueryBindings().setNumberOfExecutions(((PreparedQuery)this.query).getQueryBindings().getNumberOfExecutions() + 1);
                    CancelQueryTask timeoutTask = null;
                    try {
                        timeoutTask = this.startQueryTimer(this, this.getTimeoutInMillis());
                        if (!isBatch) {
                            this.statementBegins();
                        }
                        rs = (ResultSetInternalMethods)((NativeSession)locallyScopedConnection.getSession()).execSQL(this, null, maxRowsToRetrieve, (NativePacketPayload)sendPacket, createStreamingResultSet, this.getResultSetFactory(), this.getCurrentCatalog(), metadata, isBatch);
                        if (timeoutTask != null) {
                            this.stopQueryTimer(timeoutTask, true, true);
                            timeoutTask = null;
                        }
                    } finally {
                        if (!isBatch) {
                            this.query.getStatementExecuting().set(false);
                        }
                        this.stopQueryTimer(timeoutTask, false, false);
                    }
                    return rs;
                } catch (NullPointerException npe) {
                    this.checkClosed();
                    throw npe;
                }
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ResultSet executeQuery() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                JdbcConnection locallyScopedConn = this.connection;
                this.checkForDml(((PreparedQuery)this.query).getOriginalSql(), ((PreparedQuery)this.query).getParseInfo().getFirstStmtChar());
                this.batchedGeneratedKeys = null;
                this.resetCancelledState();
                this.implicitlyCloseAllOpenResults();
                this.clearWarnings();
                if (this.doPingInstead) {
                    this.doPingInstead();
                    return this.results;
                }
                this.setupStreamingTimeout(locallyScopedConn);
                Object sendPacket = ((PreparedQuery)this.query).fillSendPacket();
                String oldCatalog = null;
                if (!locallyScopedConn.getCatalog().equals(this.getCurrentCatalog())) {
                    oldCatalog = locallyScopedConn.getCatalog();
                    locallyScopedConn.setCatalog(this.getCurrentCatalog());
                }
                CachedResultSetMetaData cachedMetadata = null;
                boolean cacheResultSetMetadata = locallyScopedConn.getPropertySet().getBooleanProperty("cacheResultSetMetadata").getValue();
                String origSql = ((PreparedQuery)this.query).getOriginalSql();
                if (cacheResultSetMetadata) {
                    cachedMetadata = locallyScopedConn.getCachedMetaData(origSql);
                }
                locallyScopedConn.setSessionMaxRows(this.maxRows);
                this.results = this.executeInternal(this.maxRows, sendPacket, this.createStreamingResultSet(), true, cachedMetadata, false);
                if (oldCatalog != null) {
                    locallyScopedConn.setCatalog(oldCatalog);
                }
                if (cachedMetadata != null) {
                    locallyScopedConn.initializeResultsMetadataFromCache(origSql, cachedMetadata, this.results);
                } else if (cacheResultSetMetadata) {
                    locallyScopedConn.initializeResultsMetadataFromCache(origSql, null, this.results);
                }
                this.lastInsertId = this.results.getUpdateID();
                return this.results;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int executeUpdate() throws SQLException {
        try {
            return Util.truncateAndConvertToInt(this.executeLargeUpdate());
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected long executeUpdateInternal(boolean clearBatchedGeneratedKeysAndWarnings, boolean isBatch) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (clearBatchedGeneratedKeysAndWarnings) {
                    this.clearWarnings();
                    this.batchedGeneratedKeys = null;
                }
                return this.executeUpdateInternal((QueryBindings<?>)((PreparedQuery)this.query).getQueryBindings(), isBatch);
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected long executeUpdateInternal(QueryBindings<?> bindings, boolean isReallyBatch) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                JdbcConnection locallyScopedConn = this.connection;
                if (locallyScopedConn.isReadOnly(false)) {
                    throw SQLError.createSQLException(Messages.getString("PreparedStatement.34") + Messages.getString("PreparedStatement.35"), "S1009", this.exceptionInterceptor);
                }
                if (((PreparedQuery)this.query).getParseInfo().getFirstStmtChar() == 'S' && this.isSelectQuery()) {
                    throw SQLError.createSQLException(Messages.getString("PreparedStatement.37"), "01S03", this.exceptionInterceptor);
                }
                this.resetCancelledState();
                this.implicitlyCloseAllOpenResults();
                ResultSetInternalMethods rs = null;
                Object sendPacket = ((PreparedQuery)this.query).fillSendPacket(bindings);
                String oldCatalog = null;
                if (!locallyScopedConn.getCatalog().equals(this.getCurrentCatalog())) {
                    oldCatalog = locallyScopedConn.getCatalog();
                    locallyScopedConn.setCatalog(this.getCurrentCatalog());
                }
                locallyScopedConn.setSessionMaxRows(-1);
                rs = this.executeInternal(-1, sendPacket, false, false, null, isReallyBatch);
                if (this.retrieveGeneratedKeys) {
                    rs.setFirstCharOfQuery(((PreparedQuery)this.query).getParseInfo().getFirstStmtChar());
                }
                if (oldCatalog != null) {
                    locallyScopedConn.setCatalog(oldCatalog);
                }
                this.results = rs;
                this.updateCount = rs.getUpdateCount();
                if (this.containsOnDuplicateKeyUpdateInSQL() && this.compensateForOnDuplicateKeyUpdate && (this.updateCount == 2L || this.updateCount == 0L)) {
                    this.updateCount = 1L;
                }
                this.lastInsertId = rs.getUpdateID();
                return this.updateCount;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected boolean containsOnDuplicateKeyUpdateInSQL() {
        return ((PreparedQuery)this.query).getParseInfo().containsOnDuplicateKeyUpdateInSQL();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected ClientPreparedStatement prepareBatchedInsertSQL(JdbcConnection localConn, int numBatches) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ClientPreparedStatement pstmt = new ClientPreparedStatement(localConn, "Rewritten batch of: " + ((PreparedQuery)this.query).getOriginalSql(), this.getCurrentCatalog(), ((PreparedQuery)this.query).getParseInfo().getParseInfoForBatch(numBatches));
                pstmt.setRetrieveGeneratedKeys(this.retrieveGeneratedKeys);
                pstmt.rewrittenBatchSize = numBatches;
                return pstmt;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void setRetrieveGeneratedKeys(boolean flag) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                this.retrieveGeneratedKeys = flag;
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getBytesRepresentation(int parameterIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                return ((ClientPreparedQuery)this.query).getBytesRepresentation(parameterIndex);
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected byte[] getBytesRepresentationForBatch(int parameterIndex, int commandIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                return ((ClientPreparedQuery)this.query).getBytesRepresentationForBatch(parameterIndex, commandIndex);
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public java.sql.ResultSetMetaData getMetaData() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.isSelectQuery()) {
                    return null;
                }
                Statement mdStmt = null;
                ResultSet mdRs = null;
                if (this.pstmtResultMetaData == null) {
                    try {
                        mdStmt = new ClientPreparedStatement(this.connection, ((PreparedQuery)this.query).getOriginalSql(), this.getCurrentCatalog(), ((PreparedQuery)this.query).getParseInfo());
                        mdStmt.setMaxRows(1);
                        int paramCount = ((PreparedQuery)this.query).getParameterCount();
                        for (int i = 1; i <= paramCount; ++i) {
                            mdStmt.setString(i, "");
                        }
                        boolean hadResults = mdStmt.execute();
                        if (hadResults) {
                            mdRs = mdStmt.getResultSet();
                            this.pstmtResultMetaData = mdRs.getMetaData();
                        } else {
                            this.pstmtResultMetaData = new ResultSetMetaData(this.session, new Field[0], this.session.getPropertySet().getBooleanProperty("useOldAliasMetadataBehavior").getValue(), this.session.getPropertySet().getBooleanProperty("yearIsDateType").getValue(), this.exceptionInterceptor);
                        }
                    } finally {
                        SQLException sqlExRethrow = null;
                        if (mdRs != null) {
                            try {
                                mdRs.close();
                            } catch (SQLException sqlEx) {
                                sqlExRethrow = sqlEx;
                            }
                            mdRs = null;
                        }
                        if (mdStmt != null) {
                            try {
                                mdStmt.close();
                            } catch (SQLException sqlEx) {
                                sqlExRethrow = sqlEx;
                            }
                            mdStmt = null;
                        }
                        if (sqlExRethrow != null) {
                            throw sqlExRethrow;
                        }
                    }
                }
                return this.pstmtResultMetaData;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected boolean isSelectQuery() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                return StringUtils.startsWithIgnoreCaseAndWs(StringUtils.stripComments(((PreparedQuery)this.query).getOriginalSql(), "'\"", "'\"", true, false, true, true), "SELECT");
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (this.parameterMetaData == null) {
                    this.parameterMetaData = this.session.getPropertySet().getBooleanProperty("generateSimpleParameterMetadata").getValue() != false ? new MysqlParameterMetadata(((PreparedQuery)this.query).getParameterCount()) : new MysqlParameterMetadata(this.session, null, ((PreparedQuery)this.query).getParameterCount(), this.exceptionInterceptor);
                }
                return this.parameterMetaData;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public ParseInfo getParseInfo() {
        return ((PreparedQuery)this.query).getParseInfo();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void initializeFromParseInfo() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                int parameterCount = ((PreparedQuery)this.query).getParseInfo().getStaticSql().length - 1;
                ((PreparedQuery)this.query).setParameterCount(parameterCount);
                ((PreparedQuery)this.query).setQueryBindings(new ClientPreparedQueryBindings(parameterCount, this.session));
                ((ClientPreparedQueryBindings)((ClientPreparedQuery)this.query).getQueryBindings()).setLoadDataQuery(((PreparedQuery)this.query).getParseInfo().isFoundLoadData());
                this.clearParameters();
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isNull(int paramIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                return ((PreparedQuery)this.query).getQueryBindings().getBindValues()[paramIndex].isNull();
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void realClose(boolean calledExplicitly, boolean closeOpenResults) throws SQLException {
        JdbcConnection locallyScopedConn = this.connection;
        if (locallyScopedConn == null) {
            return;
        }
        Object object = locallyScopedConn.getConnectionMutex();
        synchronized (object) {
            if (this.isClosed) {
                return;
            }
            if (this.useUsageAdvisor && ((PreparedQuery)this.query).getQueryBindings().getNumberOfExecutions() <= 1) {
                String message = Messages.getString("PreparedStatement.43");
                this.query.getEventSink().consumeEvent(new ProfilerEventImpl(0, "", this.getCurrentCatalog(), this.session.getThreadId(), this.getId(), -1, System.currentTimeMillis(), 0L, Constants.MILLIS_I18N, null, this.pointOfOrigin, message));
            }
            super.realClose(calledExplicitly, closeOpenResults);
            ((PreparedQuery)this.query).setOriginalSql(null);
            ((PreparedQuery)this.query).setQueryBindings(null);
        }
    }

    @Override
    public String getPreparedSql() {
        Object object = this.checkClosed().getConnectionMutex();
        synchronized (object) {
            if (this.rewrittenBatchSize == 0) {
                return ((PreparedQuery)this.query).getOriginalSql();
            }
            try {
                return ((PreparedQuery)this.query).getParseInfo().getSqlForBatch();
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int getUpdateCount() throws SQLException {
        try {
            int count = super.getUpdateCount();
            if (this.containsOnDuplicateKeyUpdateInSQL() && this.compensateForOnDuplicateKeyUpdate && (count == 2 || count == 0)) {
                count = 1;
            }
            return count;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public long executeLargeUpdate() throws SQLException {
        try {
            return this.executeUpdateInternal(true, false);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ParameterBindings getParameterBindings() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                return new EmulatedPreparedStatementBindings();
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected int getParameterIndexOffset() {
        return 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void checkBounds(int paramIndex, int parameterIndexOffset) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (paramIndex < 1) {
                    throw SQLError.createSQLException(Messages.getString("PreparedStatement.49") + paramIndex + Messages.getString("PreparedStatement.50"), "S1009", this.exceptionInterceptor);
                }
                if (paramIndex > ((PreparedQuery)this.query).getParameterCount()) {
                    throw SQLError.createSQLException(Messages.getString("PreparedStatement.51") + paramIndex + Messages.getString("PreparedStatement.52") + ((PreparedQuery)this.query).getParameterCount() + Messages.getString("PreparedStatement.53"), "S1009", this.exceptionInterceptor);
                }
                if (parameterIndexOffset == -1 && paramIndex == 1) {
                    throw SQLError.createSQLException(Messages.getString("PreparedStatement.63"), "S1009", this.exceptionInterceptor);
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected final int getCoreParameterIndex(int paramIndex) throws SQLException {
        int parameterIndexOffset = this.getParameterIndexOffset();
        this.checkBounds(paramIndex, parameterIndexOffset);
        return paramIndex - 1 + parameterIndexOffset;
    }

    @Override
    public void setArray(int i, Array x) throws SQLException {
        try {
            throw SQLError.createSQLFeatureNotSupportedException();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setAsciiStream(this.getCoreParameterIndex(parameterIndex), x);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setAsciiStream(this.getCoreParameterIndex(parameterIndex), x, length);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setAsciiStream(this.getCoreParameterIndex(parameterIndex), x, length);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setBigDecimal(this.getCoreParameterIndex(parameterIndex), x);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setBinaryStream(this.getCoreParameterIndex(parameterIndex), x);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setBinaryStream(this.getCoreParameterIndex(parameterIndex), x, length);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setBinaryStream(this.getCoreParameterIndex(parameterIndex), x, length);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBlob(int i, Blob x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setBlob(this.getCoreParameterIndex(i), x);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBlob(int parameterIndex, InputStream inputStream2) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setBlob(this.getCoreParameterIndex(parameterIndex), inputStream2);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBlob(int parameterIndex, InputStream inputStream2, long length) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setBlob(this.getCoreParameterIndex(parameterIndex), inputStream2, length);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setBoolean(this.getCoreParameterIndex(parameterIndex), x);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setByte(this.getCoreParameterIndex(parameterIndex), x);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setBytes(this.getCoreParameterIndex(parameterIndex), x);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBytes(int parameterIndex, byte[] x, boolean checkForIntroducer, boolean escapeForMBChars) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setBytes(this.getCoreParameterIndex(parameterIndex), x, checkForIntroducer, escapeForMBChars);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setBytesNoEscape(int parameterIndex, byte[] parameterAsBytes) throws SQLException {
        ((PreparedQuery)this.query).getQueryBindings().setBytesNoEscape(this.getCoreParameterIndex(parameterIndex), parameterAsBytes);
    }

    @Override
    public void setBytesNoEscapeNoQuotes(int parameterIndex, byte[] parameterAsBytes) throws SQLException {
        ((PreparedQuery)this.query).getQueryBindings().setBytesNoEscapeNoQuotes(this.getCoreParameterIndex(parameterIndex), parameterAsBytes);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setCharacterStream(this.getCoreParameterIndex(parameterIndex), reader);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setCharacterStream(this.getCoreParameterIndex(parameterIndex), reader, length);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setCharacterStream(this.getCoreParameterIndex(parameterIndex), reader, length);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setCharacterStream(this.getCoreParameterIndex(parameterIndex), reader);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setCharacterStream(this.getCoreParameterIndex(parameterIndex), reader, length);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setClob(int i, Clob x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setClob(this.getCoreParameterIndex(i), x);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setDate(this.getCoreParameterIndex(parameterIndex), x);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setDate(this.getCoreParameterIndex(parameterIndex), x, cal);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setDouble(this.getCoreParameterIndex(parameterIndex), x);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {
        try {
            ((PreparedQuery)this.query).getQueryBindings().setFloat(this.getCoreParameterIndex(parameterIndex), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setInt(this.getCoreParameterIndex(parameterIndex), x);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setLong(this.getCoreParameterIndex(parameterIndex), x);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBigInteger(int parameterIndex, BigInteger x) throws SQLException {
        Object object = this.checkClosed().getConnectionMutex();
        synchronized (object) {
            ((PreparedQuery)this.query).getQueryBindings().setBigInteger(this.getCoreParameterIndex(parameterIndex), x);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setNCharacterStream(this.getCoreParameterIndex(parameterIndex), value);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setNCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setNCharacterStream(this.getCoreParameterIndex(parameterIndex), reader, length);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setNClob(this.getCoreParameterIndex(parameterIndex), reader);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setNClob(this.getCoreParameterIndex(parameterIndex), reader, length);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setNClob(this.getCoreParameterIndex(parameterIndex), value);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setNString(int parameterIndex, String x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setNString(this.getCoreParameterIndex(parameterIndex), x);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setNull(this.getCoreParameterIndex(parameterIndex));
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setNull(this.getCoreParameterIndex(parameterIndex));
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setNull(int parameterIndex, MysqlType mysqlType) throws SQLException {
        this.setNull(parameterIndex, mysqlType.getJdbcType());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setObject(int parameterIndex, Object parameterObj) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setObject(this.getCoreParameterIndex(parameterIndex), parameterObj);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setObject(int parameterIndex, Object parameterObj, int targetSqlType) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                try {
                    ((PreparedQuery)this.query).getQueryBindings().setObject(this.getCoreParameterIndex(parameterIndex), parameterObj, MysqlType.getByJdbcType(targetSqlType));
                } catch (FeatureNotAvailableException nae) {
                    throw SQLError.createSQLFeatureNotSupportedException(Messages.getString("Statement.UnsupportedSQLType") + JDBCType.valueOf(targetSqlType), "S1C00", this.exceptionInterceptor);
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setObject(int parameterIndex, Object parameterObj, SQLType targetSqlType) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (targetSqlType instanceof MysqlType) {
                    ((PreparedQuery)this.query).getQueryBindings().setObject(this.getCoreParameterIndex(parameterIndex), parameterObj, (MysqlType)targetSqlType);
                } else {
                    this.setObject(parameterIndex, parameterObj, targetSqlType.getVendorTypeNumber());
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setObject(int parameterIndex, Object parameterObj, int targetSqlType, int scale) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                try {
                    ((PreparedQuery)this.query).getQueryBindings().setObject(this.getCoreParameterIndex(parameterIndex), parameterObj, MysqlType.getByJdbcType(targetSqlType), scale);
                } catch (FeatureNotAvailableException nae) {
                    throw SQLError.createSQLFeatureNotSupportedException(Messages.getString("Statement.UnsupportedSQLType") + JDBCType.valueOf(targetSqlType), "S1C00", this.exceptionInterceptor);
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (targetSqlType instanceof MysqlType) {
                    ((PreparedQuery)this.query).getQueryBindings().setObject(this.getCoreParameterIndex(parameterIndex), x, (MysqlType)targetSqlType, scaleOrLength);
                } else {
                    this.setObject(parameterIndex, x, targetSqlType.getVendorTypeNumber(), scaleOrLength);
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setRef(int i, Ref x) throws SQLException {
        try {
            throw SQLError.createSQLFeatureNotSupportedException();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        try {
            throw SQLError.createSQLFeatureNotSupportedException();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setShort(this.getCoreParameterIndex(parameterIndex), x);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        try {
            if (xmlObject == null) {
                this.setNull(parameterIndex, MysqlType.VARCHAR);
            } else {
                this.setCharacterStream(parameterIndex, ((MysqlSQLXML)xmlObject).serializeAsCharacterStream());
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setString(int parameterIndex, String x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setString(this.getCoreParameterIndex(parameterIndex), x);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTime(int parameterIndex, Time x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setTime(this.getCoreParameterIndex(parameterIndex), x);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setTime(this.getCoreParameterIndex(parameterIndex), x, cal);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setTimestamp(this.getCoreParameterIndex(parameterIndex), x);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setTimestamp(this.getCoreParameterIndex(parameterIndex), x, cal);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    @Deprecated
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        try {
            this.setBinaryStream(parameterIndex, x, length);
            ((PreparedQuery)this.query).getQueryBindings().getBindValues()[this.getCoreParameterIndex(parameterIndex)].setMysqlType(MysqlType.TEXT);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setURL(int parameterIndex, URL arg) throws SQLException {
        try {
            if (arg == null) {
                this.setNull(parameterIndex, MysqlType.VARCHAR);
            } else {
                this.setString(parameterIndex, arg.toString());
                ((PreparedQuery)this.query).getQueryBindings().getBindValues()[this.getCoreParameterIndex(parameterIndex)].setMysqlType(MysqlType.VARCHAR);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    class EmulatedPreparedStatementBindings
    implements ParameterBindings {
        private ResultSetImpl bindingsAsRs;
        private ClientPreparedQueryBindValue[] bindValues;

        EmulatedPreparedStatementBindings() throws SQLException {
            ArrayList<ByteArrayRow> rows = new ArrayList<ByteArrayRow>();
            int paramCount = ((PreparedQuery)ClientPreparedStatement.this.query).getParameterCount();
            this.bindValues = new ClientPreparedQueryBindValue[paramCount];
            for (int i = 0; i < paramCount; ++i) {
                this.bindValues[i] = ((ClientPreparedQueryBindValue[])((ClientPreparedQueryBindings)((PreparedQuery)ClientPreparedStatement.this.query).getQueryBindings()).getBindValues())[i].clone();
            }
            byte[][] rowData = new byte[paramCount][];
            Field[] typeMetadata = new Field[paramCount];
            for (int i = 0; i < paramCount; ++i) {
                Field parameterMetadata;
                int batchCommandIndex = ((PreparedQuery)ClientPreparedStatement.this.query).getBatchCommandIndex();
                rowData[i] = batchCommandIndex == -1 ? ClientPreparedStatement.this.getBytesRepresentation(i) : ClientPreparedStatement.this.getBytesRepresentationForBatch(i, batchCommandIndex);
                int charsetIndex = 0;
                switch (((PreparedQuery)ClientPreparedStatement.this.query).getQueryBindings().getBindValues()[i].getMysqlType()) {
                    case BINARY: 
                    case BLOB: 
                    case GEOMETRY: 
                    case LONGBLOB: 
                    case MEDIUMBLOB: 
                    case TINYBLOB: 
                    case UNKNOWN: 
                    case VARBINARY: {
                        charsetIndex = 63;
                        break;
                    }
                    default: {
                        try {
                            charsetIndex = CharsetMapping.getCollationIndexForJavaEncoding(ClientPreparedStatement.this.session.getPropertySet().getStringProperty("characterEncoding").getValue(), ClientPreparedStatement.this.session.getServerSession().getServerVersion());
                            break;
                        } catch (RuntimeException ex) {
                            throw SQLError.createSQLException(ex.toString(), "S1009", ex, null);
                        }
                    }
                }
                typeMetadata[i] = parameterMetadata = new Field(null, "parameter_" + (i + 1), charsetIndex, ClientPreparedStatement.this.charEncoding, ((PreparedQuery)ClientPreparedStatement.this.query).getQueryBindings().getBindValues()[i].getMysqlType(), rowData[i].length);
            }
            rows.add(new ByteArrayRow(rowData, ClientPreparedStatement.this.exceptionInterceptor));
            this.bindingsAsRs = ClientPreparedStatement.this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(rows, new DefaultColumnDefinition(typeMetadata)));
            this.bindingsAsRs.next();
        }

        @Override
        public Array getArray(int parameterIndex) throws SQLException {
            return this.bindingsAsRs.getArray(parameterIndex);
        }

        @Override
        public InputStream getAsciiStream(int parameterIndex) throws SQLException {
            return this.bindingsAsRs.getAsciiStream(parameterIndex);
        }

        @Override
        public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
            return this.bindingsAsRs.getBigDecimal(parameterIndex);
        }

        @Override
        public InputStream getBinaryStream(int parameterIndex) throws SQLException {
            return this.bindingsAsRs.getBinaryStream(parameterIndex);
        }

        @Override
        public Blob getBlob(int parameterIndex) throws SQLException {
            return this.bindingsAsRs.getBlob(parameterIndex);
        }

        @Override
        public boolean getBoolean(int parameterIndex) throws SQLException {
            return this.bindingsAsRs.getBoolean(parameterIndex);
        }

        @Override
        public byte getByte(int parameterIndex) throws SQLException {
            return this.bindingsAsRs.getByte(parameterIndex);
        }

        @Override
        public byte[] getBytes(int parameterIndex) throws SQLException {
            return this.bindingsAsRs.getBytes(parameterIndex);
        }

        @Override
        public Reader getCharacterStream(int parameterIndex) throws SQLException {
            return this.bindingsAsRs.getCharacterStream(parameterIndex);
        }

        @Override
        public Clob getClob(int parameterIndex) throws SQLException {
            return this.bindingsAsRs.getClob(parameterIndex);
        }

        @Override
        public Date getDate(int parameterIndex) throws SQLException {
            return this.bindingsAsRs.getDate(parameterIndex);
        }

        @Override
        public double getDouble(int parameterIndex) throws SQLException {
            return this.bindingsAsRs.getDouble(parameterIndex);
        }

        @Override
        public float getFloat(int parameterIndex) throws SQLException {
            return this.bindingsAsRs.getFloat(parameterIndex);
        }

        @Override
        public int getInt(int parameterIndex) throws SQLException {
            return this.bindingsAsRs.getInt(parameterIndex);
        }

        @Override
        public BigInteger getBigInteger(int parameterIndex) throws SQLException {
            return this.bindingsAsRs.getBigInteger(parameterIndex);
        }

        @Override
        public long getLong(int parameterIndex) throws SQLException {
            return this.bindingsAsRs.getLong(parameterIndex);
        }

        @Override
        public Reader getNCharacterStream(int parameterIndex) throws SQLException {
            return this.bindingsAsRs.getCharacterStream(parameterIndex);
        }

        @Override
        public Reader getNClob(int parameterIndex) throws SQLException {
            return this.bindingsAsRs.getCharacterStream(parameterIndex);
        }

        @Override
        public Object getObject(int parameterIndex) throws SQLException {
            ClientPreparedStatement.this.checkBounds(parameterIndex, 0);
            if (this.bindValues[parameterIndex - 1].isNull()) {
                return null;
            }
            switch (((PreparedQuery)ClientPreparedStatement.this.query).getQueryBindings().getBindValues()[parameterIndex - 1].getMysqlType()) {
                case TINYINT: 
                case TINYINT_UNSIGNED: {
                    return this.getByte(parameterIndex);
                }
                case SMALLINT: 
                case SMALLINT_UNSIGNED: {
                    return this.getShort(parameterIndex);
                }
                case INT: 
                case INT_UNSIGNED: {
                    return this.getInt(parameterIndex);
                }
                case BIGINT: {
                    return this.getLong(parameterIndex);
                }
                case BIGINT_UNSIGNED: {
                    return this.getBigInteger(parameterIndex);
                }
                case FLOAT: 
                case FLOAT_UNSIGNED: {
                    return Float.valueOf(this.getFloat(parameterIndex));
                }
                case DOUBLE: 
                case DOUBLE_UNSIGNED: {
                    return this.getDouble(parameterIndex);
                }
            }
            return this.bindingsAsRs.getObject(parameterIndex);
        }

        @Override
        public Ref getRef(int parameterIndex) throws SQLException {
            return this.bindingsAsRs.getRef(parameterIndex);
        }

        @Override
        public short getShort(int parameterIndex) throws SQLException {
            return this.bindingsAsRs.getShort(parameterIndex);
        }

        @Override
        public String getString(int parameterIndex) throws SQLException {
            return this.bindingsAsRs.getString(parameterIndex);
        }

        @Override
        public Time getTime(int parameterIndex) throws SQLException {
            return this.bindingsAsRs.getTime(parameterIndex);
        }

        @Override
        public Timestamp getTimestamp(int parameterIndex) throws SQLException {
            return this.bindingsAsRs.getTimestamp(parameterIndex);
        }

        @Override
        public URL getURL(int parameterIndex) throws SQLException {
            return this.bindingsAsRs.getURL(parameterIndex);
        }

        @Override
        public boolean isNull(int parameterIndex) throws SQLException {
            ClientPreparedStatement.this.checkBounds(parameterIndex, 0);
            return this.bindValues[parameterIndex - 1].isNull();
        }
    }
}

