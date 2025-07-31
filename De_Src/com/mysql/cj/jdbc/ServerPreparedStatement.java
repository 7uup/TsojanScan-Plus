/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.BindValue;
import com.mysql.cj.CancelQueryTask;
import com.mysql.cj.Messages;
import com.mysql.cj.MysqlType;
import com.mysql.cj.ParseInfo;
import com.mysql.cj.PreparedQuery;
import com.mysql.cj.ServerPreparedQuery;
import com.mysql.cj.ServerPreparedQueryBindValue;
import com.mysql.cj.ServerPreparedQueryBindings;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.jdbc.ClientPreparedStatement;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.MysqlParameterMetadata;
import com.mysql.cj.jdbc.StatementImpl;
import com.mysql.cj.jdbc.exceptions.MySQLStatementCancelledException;
import com.mysql.cj.jdbc.exceptions.MySQLTimeoutException;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import com.mysql.cj.jdbc.result.ResultSetInternalMethods;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.Message;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.Date;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ServerPreparedStatement
extends ClientPreparedStatement {
    private boolean hasOnDuplicateKeyUpdate = false;
    private boolean invalid = false;
    private CJException invalidationException;
    protected boolean isCached = false;

    protected static ServerPreparedStatement getInstance(JdbcConnection conn, String sql, String catalog, int resultSetType, int resultSetConcurrency) throws SQLException {
        return new ServerPreparedStatement(conn, sql, catalog, resultSetType, resultSetConcurrency);
    }

    protected ServerPreparedStatement(JdbcConnection conn, String sql, String catalog, int resultSetType, int resultSetConcurrency) throws SQLException {
        super(conn, catalog);
        this.checkNullOrEmptyQuery(sql);
        String statementComment = this.session.getProtocol().getQueryComment();
        ((PreparedQuery)this.query).setOriginalSql(statementComment == null ? sql : "/* " + statementComment + " */ " + sql);
        ((PreparedQuery)this.query).setParseInfo(new ParseInfo(((PreparedQuery)this.query).getOriginalSql(), this.session, this.charEncoding));
        this.hasOnDuplicateKeyUpdate = ((PreparedQuery)this.query).getParseInfo().getFirstStmtChar() == 'I' && this.containsOnDuplicateKeyInString(sql);
        try {
            this.serverPrepare(sql);
        } catch (CJException | SQLException sqlEx) {
            this.realClose(false, true);
            throw SQLExceptionsMapping.translateException(sqlEx, this.exceptionInterceptor);
        }
        this.setResultSetType(resultSetType);
        this.setResultSetConcurrency(resultSetConcurrency);
    }

    @Override
    protected void initQuery() {
        this.query = ServerPreparedQuery.getInstance(this.session);
    }

    @Override
    public String toString() {
        StringBuilder toStringBuf = new StringBuilder();
        toStringBuf.append(this.getClass().getName() + "[");
        toStringBuf.append(((ServerPreparedQuery)this.query).getServerStatementId());
        toStringBuf.append("]: ");
        try {
            toStringBuf.append(this.asSql());
        } catch (SQLException sqlEx) {
            toStringBuf.append(Messages.getString("ServerPreparedStatement.6"));
            toStringBuf.append(sqlEx);
        }
        return toStringBuf.toString();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addBatch() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                this.query.addBatch(((PreparedQuery)this.query).getQueryBindings().clone());
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
    public String asSql(boolean quoteStreamsAndUnknowns) throws SQLException {
        Object object = this.checkClosed().getConnectionMutex();
        synchronized (object) {
            String string;
            block21: {
                StatementImpl pStmtForSub = null;
                try {
                    pStmtForSub = ClientPreparedStatement.getInstance(this.connection, ((PreparedQuery)this.query).getOriginalSql(), this.getCurrentCatalog());
                    int numParameters = ((PreparedQuery)((ClientPreparedStatement)pStmtForSub).query).getParameterCount();
                    int ourNumParameters = ((PreparedQuery)this.query).getParameterCount();
                    ServerPreparedQueryBindValue[] parameterBindings = (ServerPreparedQueryBindValue[])((ServerPreparedQueryBindings)((ServerPreparedQuery)this.query).getQueryBindings()).getBindValues();
                    block18: for (int i = 0; i < numParameters && i < ourNumParameters; ++i) {
                        if (parameterBindings[i] == null) continue;
                        if (parameterBindings[i].isNull()) {
                            ((ClientPreparedStatement)pStmtForSub).setNull(i + 1, MysqlType.NULL);
                            continue;
                        }
                        ServerPreparedQueryBindValue bindValue = parameterBindings[i];
                        switch (bindValue.bufferType) {
                            case 1: {
                                ((ClientPreparedStatement)pStmtForSub).setByte(i + 1, (byte)bindValue.longBinding);
                                continue block18;
                            }
                            case 2: {
                                ((ClientPreparedStatement)pStmtForSub).setShort(i + 1, (short)bindValue.longBinding);
                                continue block18;
                            }
                            case 3: {
                                ((ClientPreparedStatement)pStmtForSub).setInt(i + 1, (int)bindValue.longBinding);
                                continue block18;
                            }
                            case 8: {
                                ((ClientPreparedStatement)pStmtForSub).setLong(i + 1, bindValue.longBinding);
                                continue block18;
                            }
                            case 4: {
                                ((ClientPreparedStatement)pStmtForSub).setFloat(i + 1, bindValue.floatBinding);
                                continue block18;
                            }
                            case 5: {
                                ((ClientPreparedStatement)pStmtForSub).setDouble(i + 1, bindValue.doubleBinding);
                                continue block18;
                            }
                            default: {
                                ((ClientPreparedStatement)pStmtForSub).setObject(i + 1, parameterBindings[i].value);
                            }
                        }
                    }
                    string = ((ClientPreparedStatement)pStmtForSub).asSql(quoteStreamsAndUnknowns);
                    if (pStmtForSub == null) break block21;
                } catch (Throwable throwable) {
                    if (pStmtForSub != null) {
                        try {
                            pStmtForSub.close();
                        } catch (SQLException sQLException) {
                            // empty catch block
                        }
                    }
                    throw throwable;
                }
                try {
                    pStmtForSub.close();
                } catch (SQLException sQLException) {
                    // empty catch block
                }
            }
            return string;
        }
    }

    @Override
    protected JdbcConnection checkClosed() {
        if (this.invalid) {
            throw this.invalidationException;
        }
        return super.checkClosed();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void clearParameters() {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((ServerPreparedQuery)this.query).clearParameters(true);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected void setClosed(boolean flag) {
        this.isClosed = flag;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void close() throws SQLException {
        try {
            JdbcConnection locallyScopedConn = this.connection;
            if (locallyScopedConn == null) {
                return;
            }
            Object object = locallyScopedConn.getConnectionMutex();
            synchronized (object) {
                if (this.isCached && this.isPoolable() && !this.isClosed) {
                    this.clearParameters();
                    this.isClosed = true;
                    this.connection.recachePreparedStatement(this);
                    return;
                }
                this.isClosed = false;
                this.realClose(true, true);
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
    protected long[] executeBatchSerially(int batchTimeout) throws SQLException {
        Object object = this.checkClosed().getConnectionMutex();
        synchronized (object) {
            long[] lArray;
            JdbcConnection locallyScopedConn = this.connection;
            if (locallyScopedConn.isReadOnly()) {
                throw SQLError.createSQLException(Messages.getString("ServerPreparedStatement.2") + Messages.getString("ServerPreparedStatement.3"), "S1009", this.exceptionInterceptor);
            }
            this.clearWarnings();
            BindValue[] oldBindValues = (ServerPreparedQueryBindValue[])((ServerPreparedQueryBindings)((ServerPreparedQuery)this.query).getQueryBindings()).getBindValues();
            try {
                long[] updateCounts = null;
                if (this.query.getBatchedArgs() != null) {
                    int nbrCommands = this.query.getBatchedArgs().size();
                    updateCounts = new long[nbrCommands];
                    if (this.retrieveGeneratedKeys) {
                        this.batchedGeneratedKeys = new ArrayList(nbrCommands);
                    }
                    for (int i = 0; i < nbrCommands; ++i) {
                        updateCounts[i] = -3L;
                    }
                    SQLException sqlEx = null;
                    int commandIndex = 0;
                    ServerPreparedQueryBindValue[] previousBindValuesForBatch = null;
                    CancelQueryTask timeoutTask = null;
                    try {
                        timeoutTask = this.startQueryTimer(this, batchTimeout);
                        for (commandIndex = 0; commandIndex < nbrCommands; ++commandIndex) {
                            Object arg = this.query.getBatchedArgs().get(commandIndex);
                            try {
                                if (arg instanceof String) {
                                    updateCounts[commandIndex] = this.executeUpdateInternal((String)arg, true, this.retrieveGeneratedKeys);
                                    this.getBatchedGeneratedKeys(this.results.getFirstCharOfQuery() == 'I' && this.containsOnDuplicateKeyInString((String)arg) ? 1 : 0);
                                    continue;
                                }
                                ((ServerPreparedQuery)this.query).setQueryBindings((ServerPreparedQueryBindings)arg);
                                ServerPreparedQueryBindValue[] parameterBindings = (ServerPreparedQueryBindValue[])((ServerPreparedQueryBindings)((ServerPreparedQuery)this.query).getQueryBindings()).getBindValues();
                                if (previousBindValuesForBatch != null) {
                                    for (int j = 0; j < parameterBindings.length; ++j) {
                                        if (parameterBindings[j].bufferType == previousBindValuesForBatch[j].bufferType) continue;
                                        ((ServerPreparedQueryBindings)((ServerPreparedQuery)this.query).getQueryBindings()).getSendTypesToServer().set(true);
                                        break;
                                    }
                                }
                                try {
                                    updateCounts[commandIndex] = this.executeUpdateInternal(false, true);
                                } finally {
                                    previousBindValuesForBatch = parameterBindings;
                                }
                                this.getBatchedGeneratedKeys(this.containsOnDuplicateKeyUpdateInSQL() ? 1 : 0);
                                continue;
                            } catch (SQLException ex) {
                                updateCounts[commandIndex] = -3L;
                                if (this.continueBatchOnError && !(ex instanceof MySQLTimeoutException) && !(ex instanceof MySQLStatementCancelledException) && !this.hasDeadlockOrTimeoutRolledBackTx(ex)) {
                                    sqlEx = ex;
                                    continue;
                                }
                                long[] newUpdateCounts = new long[commandIndex];
                                System.arraycopy(updateCounts, 0, newUpdateCounts, 0, commandIndex);
                                throw SQLError.createBatchUpdateException(ex, newUpdateCounts, this.exceptionInterceptor);
                            }
                        }
                    } finally {
                        this.stopQueryTimer(timeoutTask, false, false);
                        this.resetCancelledState();
                    }
                    if (sqlEx != null) {
                        throw SQLError.createBatchUpdateException(sqlEx, updateCounts, this.exceptionInterceptor);
                    }
                }
                lArray = updateCounts != null ? updateCounts : new long[]{};
            } catch (Throwable throwable) {
                ((ServerPreparedQueryBindings)((ServerPreparedQuery)this.query).getQueryBindings()).setBindValues(oldBindValues);
                ((ServerPreparedQueryBindings)((ServerPreparedQuery)this.query).getQueryBindings()).getSendTypesToServer().set(true);
                this.clearBatch();
                throw throwable;
            }
            ((ServerPreparedQueryBindings)((ServerPreparedQuery)this.query).getQueryBindings()).setBindValues(oldBindValues);
            ((ServerPreparedQueryBindings)((ServerPreparedQuery)this.query).getQueryBindings()).getSendTypesToServer().set(true);
            this.clearBatch();
            return lArray;
        }
    }

    private static SQLException appendMessageToException(SQLException sqlEx, String messageToAppend, ExceptionInterceptor interceptor) {
        String sqlState = sqlEx.getSQLState();
        int vendorErrorCode = sqlEx.getErrorCode();
        SQLException sqlExceptionWithNewMessage = SQLError.createSQLException(sqlEx.getMessage() + messageToAppend, sqlState, vendorErrorCode, interceptor);
        sqlExceptionWithNewMessage.setStackTrace(sqlEx.getStackTrace());
        return sqlExceptionWithNewMessage;
    }

    @Override
    protected <M extends Message> ResultSetInternalMethods executeInternal(int maxRowsToRetrieve, M sendPacket, boolean createStreamingResultSet, boolean queryIsSelectOnly, ColumnDefinition metadata, boolean isBatch) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ((PreparedQuery)this.query).getQueryBindings().setNumberOfExecutions(((PreparedQuery)this.query).getQueryBindings().getNumberOfExecutions() + 1);
                try {
                    return this.serverExecute(maxRowsToRetrieve, createStreamingResultSet, metadata);
                } catch (SQLException sqlEx) {
                    if (this.session.getPropertySet().getBooleanProperty("enablePacketDebug").getValue().booleanValue()) {
                        this.session.dumpPacketRingBuffer();
                    }
                    if (((Boolean)this.dumpQueriesOnException.getValue()).booleanValue()) {
                        String extractedSql = this.toString();
                        StringBuilder messageBuf = new StringBuilder(extractedSql.length() + 32);
                        messageBuf.append("\n\nQuery being executed when exception was thrown:\n");
                        messageBuf.append(extractedSql);
                        messageBuf.append("\n\n");
                        sqlEx = ServerPreparedStatement.appendMessageToException(sqlEx, messageBuf.toString(), this.exceptionInterceptor);
                    }
                    throw sqlEx;
                } catch (Exception ex) {
                    if (this.session.getPropertySet().getBooleanProperty("enablePacketDebug").getValue().booleanValue()) {
                        this.session.dumpPacketRingBuffer();
                    }
                    SQLException sqlEx = SQLError.createSQLException(ex.toString(), "S1000", ex, this.exceptionInterceptor);
                    if (((Boolean)this.dumpQueriesOnException.getValue()).booleanValue()) {
                        String extractedSql = this.toString();
                        StringBuilder messageBuf = new StringBuilder(extractedSql.length() + 32);
                        messageBuf.append("\n\nQuery being executed when exception was thrown:\n");
                        messageBuf.append(extractedSql);
                        messageBuf.append("\n\n");
                        sqlEx = ServerPreparedStatement.appendMessageToException(sqlEx, messageBuf.toString(), this.exceptionInterceptor);
                    }
                    throw sqlEx;
                }
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected ServerPreparedQueryBindValue getBinding(int parameterIndex, boolean forLongData) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                int i = this.getCoreParameterIndex(parameterIndex);
                return ((ServerPreparedQueryBindings)((ServerPreparedQuery)this.query).getQueryBindings()).getBinding(i, forLongData);
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
                ColumnDefinition resultFields = ((ServerPreparedQuery)this.query).getResultFields();
                return resultFields == null || resultFields.getFields() == null ? null : new ResultSetMetaData(this.session, resultFields.getFields(), this.session.getPropertySet().getBooleanProperty("useOldAliasMetadataBehavior").getValue(), this.session.getPropertySet().getBooleanProperty("yearIsDateType").getValue(), this.exceptionInterceptor);
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
                    this.parameterMetaData = new MysqlParameterMetadata(this.session, ((ServerPreparedQuery)this.query).getParameterFields(), ((PreparedQuery)this.query).getParameterCount(), this.exceptionInterceptor);
                }
                return this.parameterMetaData;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isNull(int paramIndex) {
        throw new IllegalArgumentException(Messages.getString("ServerPreparedStatement.7"));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void realClose(boolean calledExplicitly, boolean closeOpenResults) throws SQLException {
        try {
            JdbcConnection locallyScopedConn = this.connection;
            if (locallyScopedConn == null) {
                return;
            }
            Object object = locallyScopedConn.getConnectionMutex();
            synchronized (object) {
                if (this.connection != null) {
                    CJException exceptionDuringClose = null;
                    if (calledExplicitly && !this.connection.isClosed()) {
                        Object object2 = this.connection.getConnectionMutex();
                        synchronized (object2) {
                            try {
                                this.session.sendCommand(this.commandBuilder.buildComStmtClose(null, ((ServerPreparedQuery)this.query).getServerStatementId()), true, 0);
                            } catch (CJException sqlEx) {
                                exceptionDuringClose = sqlEx;
                            }
                        }
                    }
                    if (this.isCached) {
                        this.connection.decachePreparedStatement(this);
                        this.isCached = false;
                    }
                    super.realClose(calledExplicitly, closeOpenResults);
                    ((ServerPreparedQuery)this.query).clearParameters(false);
                    if (exceptionDuringClose != null) {
                        throw exceptionDuringClose;
                    }
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
    protected void rePrepare() {
        Object object = this.checkClosed().getConnectionMutex();
        synchronized (object) {
            this.invalidationException = null;
            try {
                this.serverPrepare(((PreparedQuery)this.query).getOriginalSql());
            } catch (Exception ex) {
                this.invalidationException = ExceptionFactory.createException(ex.getMessage(), ex);
            }
            if (this.invalidationException != null) {
                this.invalid = true;
                this.query.closeQuery();
                if (this.results != null) {
                    try {
                        this.results.close();
                    } catch (Exception exception) {
                        // empty catch block
                    }
                }
                if (this.generatedKeysResults != null) {
                    try {
                        this.generatedKeysResults.close();
                    } catch (Exception exception) {
                        // empty catch block
                    }
                }
                try {
                    this.closeAllOpenResults();
                } catch (Exception exception) {
                    // empty catch block
                }
                if (this.connection != null && !((Boolean)this.dontTrackOpenResources.getValue()).booleanValue()) {
                    this.connection.unregisterStatement(this);
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected ResultSetInternalMethods serverExecute(int maxRowsToRetrieve, boolean createStreamingResultSet, ColumnDefinition metadata) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                this.results = ((ServerPreparedQuery)this.query).serverExecute(maxRowsToRetrieve, createStreamingResultSet, metadata, this.resultSetFactory);
                return this.results;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void serverPrepare(String sql) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                try {
                    ServerPreparedQuery q = (ServerPreparedQuery)this.query;
                    q.serverPrepare(sql);
                } catch (IOException ioEx) {
                    throw SQLError.createCommunicationsException(this.connection, this.session.getProtocol().getPacketSentTimeHolder(), this.session.getProtocol().getPacketReceivedTimeHolder(), ioEx, this.exceptionInterceptor);
                } catch (CJException sqlEx) {
                    SQLException ex = SQLExceptionsMapping.translateException(sqlEx);
                    if (((Boolean)this.dumpQueriesOnException.getValue()).booleanValue()) {
                        StringBuilder messageBuf = new StringBuilder(((PreparedQuery)this.query).getOriginalSql().length() + 32);
                        messageBuf.append("\n\nQuery being prepared when exception was thrown:\n\n");
                        messageBuf.append(((PreparedQuery)this.query).getOriginalSql());
                        ex = ServerPreparedStatement.appendMessageToException(ex, messageBuf.toString(), this.exceptionInterceptor);
                    }
                    throw ex;
                } finally {
                    this.session.clearInputStream();
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    protected void checkBounds(int parameterIndex, int parameterIndexOffset) throws SQLException {
        int paramCount = ((PreparedQuery)this.query).getParameterCount();
        if (paramCount == 0) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ServerPreparedStatement.8"), this.session.getExceptionInterceptor());
        }
        if (parameterIndex < 0 || parameterIndex > paramCount) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ServerPreparedStatement.9") + (parameterIndex + 1) + Messages.getString("ServerPreparedStatement.10") + paramCount, this.session.getExceptionInterceptor());
        }
    }

    @Override
    @Deprecated
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        try {
            this.checkClosed();
            throw SQLError.createSQLFeatureNotSupportedException();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setURL(int parameterIndex, URL x) throws SQLException {
        try {
            this.checkClosed();
            this.setString(parameterIndex, x.toString());
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public long getServerStatementId() {
        return ((ServerPreparedQuery)this.query).getServerStatementId();
    }

    @Override
    protected int setOneBatchedParameterSet(PreparedStatement batchedStatement, int batchedParamIndex, Object paramSet) throws SQLException {
        ServerPreparedQueryBindValue[] paramArg = (ServerPreparedQueryBindValue[])((ServerPreparedQueryBindings)paramSet).getBindValues();
        block12: for (int j = 0; j < paramArg.length; ++j) {
            Object value;
            if (paramArg[j].isNull()) {
                batchedStatement.setNull(batchedParamIndex++, MysqlType.NULL.getJdbcType());
                continue;
            }
            if (paramArg[j].isLongData) {
                value = paramArg[j].value;
                if (value instanceof InputStream) {
                    batchedStatement.setBinaryStream(batchedParamIndex++, (InputStream)value, (int)paramArg[j].bindLength);
                    continue;
                }
                batchedStatement.setCharacterStream(batchedParamIndex++, (Reader)value, (int)paramArg[j].bindLength);
                continue;
            }
            switch (paramArg[j].bufferType) {
                case 1: {
                    batchedStatement.setByte(batchedParamIndex++, (byte)paramArg[j].longBinding);
                    continue block12;
                }
                case 2: {
                    batchedStatement.setShort(batchedParamIndex++, (short)paramArg[j].longBinding);
                    continue block12;
                }
                case 3: {
                    batchedStatement.setInt(batchedParamIndex++, (int)paramArg[j].longBinding);
                    continue block12;
                }
                case 8: {
                    batchedStatement.setLong(batchedParamIndex++, paramArg[j].longBinding);
                    continue block12;
                }
                case 4: {
                    batchedStatement.setFloat(batchedParamIndex++, paramArg[j].floatBinding);
                    continue block12;
                }
                case 5: {
                    batchedStatement.setDouble(batchedParamIndex++, paramArg[j].doubleBinding);
                    continue block12;
                }
                case 11: {
                    batchedStatement.setTime(batchedParamIndex++, (Time)paramArg[j].value);
                    continue block12;
                }
                case 10: {
                    batchedStatement.setDate(batchedParamIndex++, (Date)paramArg[j].value);
                    continue block12;
                }
                case 7: 
                case 12: {
                    batchedStatement.setTimestamp(batchedParamIndex++, (Timestamp)paramArg[j].value);
                    continue block12;
                }
                case 0: 
                case 15: 
                case 246: 
                case 253: 
                case 254: {
                    value = paramArg[j].value;
                    if (value instanceof byte[]) {
                        batchedStatement.setBytes(batchedParamIndex, (byte[])value);
                    } else {
                        batchedStatement.setString(batchedParamIndex, (String)value);
                    }
                    if (batchedStatement instanceof ServerPreparedStatement) {
                        ServerPreparedQueryBindValue asBound = ((ServerPreparedStatement)batchedStatement).getBinding(batchedParamIndex, false);
                        asBound.bufferType = paramArg[j].bufferType;
                    }
                    ++batchedParamIndex;
                    continue block12;
                }
                default: {
                    throw new IllegalArgumentException(Messages.getString("ServerPreparedStatement.26", new Object[]{batchedParamIndex}));
                }
            }
        }
        return batchedParamIndex;
    }

    @Override
    protected boolean containsOnDuplicateKeyUpdateInSQL() {
        return this.hasOnDuplicateKeyUpdate;
    }

    @Override
    protected ClientPreparedStatement prepareBatchedInsertSQL(JdbcConnection localConn, int numBatches) throws SQLException {
        Object object = this.checkClosed().getConnectionMutex();
        synchronized (object) {
            try {
                ClientPreparedStatement pstmt = localConn.prepareStatement(((PreparedQuery)this.query).getParseInfo().getSqlForBatch(numBatches), this.resultSetConcurrency, this.query.getResultType().getIntValue()).unwrap(ClientPreparedStatement.class);
                pstmt.setRetrieveGeneratedKeys(this.retrieveGeneratedKeys);
                return pstmt;
            } catch (UnsupportedEncodingException e) {
                SQLException sqlEx = SQLError.createSQLException(Messages.getString("ServerPreparedStatement.27"), "S1000", this.exceptionInterceptor);
                sqlEx.initCause(e);
                throw sqlEx;
            }
        }
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        try {
            if (!poolable) {
                this.connection.decachePreparedStatement(this);
            }
            super.setPoolable(poolable);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }
}

