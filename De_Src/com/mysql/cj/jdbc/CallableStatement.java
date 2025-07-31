/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.Messages;
import com.mysql.cj.MysqlType;
import com.mysql.cj.PreparedQuery;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.FeatureNotAvailableException;
import com.mysql.cj.jdbc.ClientPreparedStatement;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.StatementImpl;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import com.mysql.cj.jdbc.result.ResultSetImpl;
import com.mysql.cj.jdbc.result.ResultSetInternalMethods;
import com.mysql.cj.protocol.a.result.ByteArrayRow;
import com.mysql.cj.protocol.a.result.ResultsetRowsStatic;
import com.mysql.cj.result.DefaultColumnDefinition;
import com.mysql.cj.result.Field;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.util.Util;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.JDBCType;
import java.sql.NClob;
import java.sql.ParameterMetaData;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CallableStatement
extends ClientPreparedStatement
implements java.sql.CallableStatement {
    private static final int NOT_OUTPUT_PARAMETER_INDICATOR = Integer.MIN_VALUE;
    private static final String PARAMETER_NAMESPACE_PREFIX = "@com_mysql_jdbc_outparam_";
    private boolean callingStoredFunction = false;
    private ResultSetInternalMethods functionReturnValueResults;
    private boolean hasOutputParams = false;
    private ResultSetInternalMethods outputParameterResults;
    protected boolean outputParamWasNull = false;
    private int[] parameterIndexToRsIndex;
    protected CallableStatementParamInfo paramInfo;
    private CallableStatementParam returnValueParam;
    private boolean noAccessToProcedureBodies;
    private int[] placeholderToParameterIndexMap;

    private static String mangleParameterName(String origParameterName) {
        if (origParameterName == null) {
            return null;
        }
        int offset = 0;
        if (origParameterName.length() > 0 && origParameterName.charAt(0) == '@') {
            offset = 1;
        }
        StringBuilder paramNameBuf = new StringBuilder(PARAMETER_NAMESPACE_PREFIX.length() + origParameterName.length());
        paramNameBuf.append(PARAMETER_NAMESPACE_PREFIX);
        paramNameBuf.append(origParameterName.substring(offset));
        return paramNameBuf.toString();
    }

    public CallableStatement(JdbcConnection conn, CallableStatementParamInfo paramInfo) throws SQLException {
        super(conn, paramInfo.nativeSql, paramInfo.catalogInUse);
        this.paramInfo = paramInfo;
        this.callingStoredFunction = this.paramInfo.isFunctionCall;
        if (this.callingStoredFunction) {
            ((PreparedQuery)this.query).setParameterCount(((PreparedQuery)this.query).getParameterCount() + 1);
        }
        this.retrieveGeneratedKeys = true;
        this.noAccessToProcedureBodies = conn.getPropertySet().getBooleanProperty("noAccessToProcedureBodies").getValue();
    }

    protected static CallableStatement getInstance(JdbcConnection conn, String sql, String catalog, boolean isFunctionCall) throws SQLException {
        return new CallableStatement(conn, sql, catalog, isFunctionCall);
    }

    protected static CallableStatement getInstance(JdbcConnection conn, CallableStatementParamInfo paramInfo) throws SQLException {
        return new CallableStatement(conn, paramInfo);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void generateParameterMap() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (this.paramInfo == null) {
                    return;
                }
                int parameterCountFromMetaData = this.paramInfo.getParameterCount();
                if (this.callingStoredFunction) {
                    --parameterCountFromMetaData;
                }
                PreparedQuery q = (PreparedQuery)this.query;
                if (this.paramInfo != null && q.getParameterCount() != parameterCountFromMetaData) {
                    int parenClosePos;
                    int parenOpenPos;
                    int startPos;
                    this.placeholderToParameterIndexMap = new int[q.getParameterCount()];
                    int n = startPos = this.callingStoredFunction ? StringUtils.indexOfIgnoreCase(q.getOriginalSql(), "SELECT") : StringUtils.indexOfIgnoreCase(q.getOriginalSql(), "CALL");
                    if (startPos != -1 && (parenOpenPos = q.getOriginalSql().indexOf(40, startPos + 4)) != -1 && (parenClosePos = StringUtils.indexOfIgnoreCase(parenOpenPos, q.getOriginalSql(), ")", "'", "'", StringUtils.SEARCH_MODE__ALL)) != -1) {
                        List<String> parsedParameters = StringUtils.split(q.getOriginalSql().substring(parenOpenPos + 1, parenClosePos), ",", "'\"", "'\"", true);
                        int numParsedParameters = parsedParameters.size();
                        if (numParsedParameters != q.getParameterCount()) {
                            // empty if block
                        }
                        int placeholderCount = 0;
                        for (int i = 0; i < numParsedParameters; ++i) {
                            if (!parsedParameters.get(i).equals("?")) continue;
                            this.placeholderToParameterIndexMap[placeholderCount++] = i;
                        }
                    }
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    public CallableStatement(JdbcConnection conn, String sql, String catalog, boolean isFunctionCall) throws SQLException {
        super(conn, sql, catalog);
        this.callingStoredFunction = isFunctionCall;
        if (!this.callingStoredFunction) {
            if (!StringUtils.startsWithIgnoreCaseAndWs(sql, "CALL")) {
                this.fakeParameterTypes(false);
            } else {
                this.determineParameterTypes();
            }
            this.generateParameterMap();
        } else {
            this.determineParameterTypes();
            this.generateParameterMap();
            ((PreparedQuery)this.query).setParameterCount(((PreparedQuery)this.query).getParameterCount() + 1);
        }
        this.retrieveGeneratedKeys = true;
        this.noAccessToProcedureBodies = conn.getPropertySet().getBooleanProperty("noAccessToProcedureBodies").getValue();
    }

    @Override
    public void addBatch() throws SQLException {
        try {
            this.setOutParams();
            super.addBatch();
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private CallableStatementParam checkIsOutputParam(int paramIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (this.callingStoredFunction) {
                    if (paramIndex == 1) {
                        if (this.returnValueParam == null) {
                            this.returnValueParam = new CallableStatementParam("", 0, false, true, MysqlType.VARCHAR.getJdbcType(), "VARCHAR", 0, 0, 2, 5);
                        }
                        return this.returnValueParam;
                    }
                    --paramIndex;
                }
                this.checkParameterIndexBounds(paramIndex);
                int localParamIndex = paramIndex - 1;
                if (this.placeholderToParameterIndexMap != null) {
                    localParamIndex = this.placeholderToParameterIndexMap[localParamIndex];
                }
                CallableStatementParam paramDescriptor = this.paramInfo.getParameter(localParamIndex);
                if (this.noAccessToProcedureBodies) {
                    paramDescriptor.isOut = true;
                    paramDescriptor.isIn = true;
                    paramDescriptor.inOutModifier = 2;
                } else if (!paramDescriptor.isOut) {
                    throw SQLError.createSQLException(Messages.getString("CallableStatement.9", new Object[]{paramIndex}), "S1009", this.getExceptionInterceptor());
                }
                this.hasOutputParams = true;
                return paramDescriptor;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void checkParameterIndexBounds(int paramIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                this.paramInfo.checkBounds(paramIndex);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    private void checkStreamability() throws SQLException {
        if (this.hasOutputParams && this.createStreamingResultSet()) {
            throw SQLError.createSQLException(Messages.getString("CallableStatement.14"), "S1C00", this.getExceptionInterceptor());
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
                super.clearParameters();
                try {
                    if (this.outputParameterResults != null) {
                        this.outputParameterResults.close();
                    }
                } finally {
                    this.outputParameterResults = null;
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
    private void fakeParameterTypes(boolean isReallyProcedure) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                String encoding = this.connection.getSession().getServerSession().getCharacterSetMetadata();
                int collationIndex = this.connection.getSession().getServerSession().getMetadataCollationIndex();
                Field[] fields = new Field[]{new Field("", "PROCEDURE_CAT", collationIndex, encoding, MysqlType.CHAR, 0), new Field("", "PROCEDURE_SCHEM", collationIndex, encoding, MysqlType.CHAR, 0), new Field("", "PROCEDURE_NAME", collationIndex, encoding, MysqlType.CHAR, 0), new Field("", "COLUMN_NAME", collationIndex, encoding, MysqlType.CHAR, 0), new Field("", "COLUMN_TYPE", collationIndex, encoding, MysqlType.CHAR, 0), new Field("", "DATA_TYPE", collationIndex, encoding, MysqlType.SMALLINT, 0), new Field("", "TYPE_NAME", collationIndex, encoding, MysqlType.CHAR, 0), new Field("", "PRECISION", collationIndex, encoding, MysqlType.INT, 0), new Field("", "LENGTH", collationIndex, encoding, MysqlType.INT, 0), new Field("", "SCALE", collationIndex, encoding, MysqlType.SMALLINT, 0), new Field("", "RADIX", collationIndex, encoding, MysqlType.SMALLINT, 0), new Field("", "NULLABLE", collationIndex, encoding, MysqlType.SMALLINT, 0), new Field("", "REMARKS", collationIndex, encoding, MysqlType.CHAR, 0)};
                String procName = isReallyProcedure ? this.extractProcedureName() : null;
                byte[] procNameAsBytes = null;
                procNameAsBytes = procName == null ? null : StringUtils.getBytes(procName, "UTF-8");
                ArrayList<ByteArrayRow> resultRows = new ArrayList<ByteArrayRow>();
                for (int i = 0; i < ((PreparedQuery)this.query).getParameterCount(); ++i) {
                    byte[][] row = new byte[][]{null, null, procNameAsBytes, this.s2b(String.valueOf(i)), this.s2b(String.valueOf(1)), this.s2b(String.valueOf(MysqlType.VARCHAR.getJdbcType())), this.s2b(MysqlType.VARCHAR.getName()), this.s2b(Integer.toString(65535)), this.s2b(Integer.toString(65535)), this.s2b(Integer.toString(0)), this.s2b(Integer.toString(10)), this.s2b(Integer.toString(2)), null};
                    resultRows.add(new ByteArrayRow(row, this.getExceptionInterceptor()));
                }
                ResultSetImpl paramTypesRs = this.resultSetFactory.createFromResultsetRows(1007, 1004, new ResultsetRowsStatic(resultRows, new DefaultColumnDefinition(fields)));
                this.convertGetProcedureColumnsToInternalDescriptors(paramTypesRs);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void determineParameterTypes() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSet paramTypesRs = null;
                try {
                    String procName = this.extractProcedureName();
                    String quotedId = this.session.getIdentifierQuoteString();
                    List<String> parseList = StringUtils.splitDBdotName(procName, "", quotedId, this.session.getServerSession().isNoBackslashEscapesSet());
                    String tmpCatalog = "";
                    if (parseList.size() == 2) {
                        tmpCatalog = parseList.get(0);
                        procName = parseList.get(1);
                    }
                    DatabaseMetaData dbmd = this.connection.getMetaData();
                    boolean useCatalog = false;
                    if (tmpCatalog.length() <= 0) {
                        useCatalog = true;
                    }
                    paramTypesRs = dbmd.getProcedureColumns(useCatalog ? this.getCurrentCatalog() : tmpCatalog, null, procName, "%");
                    boolean hasResults = false;
                    try {
                        if (paramTypesRs.next()) {
                            paramTypesRs.previous();
                            hasResults = true;
                        }
                    } catch (Exception exception) {
                        // empty catch block
                    }
                    if (hasResults) {
                        this.convertGetProcedureColumnsToInternalDescriptors(paramTypesRs);
                    } else {
                        this.fakeParameterTypes(true);
                    }
                } finally {
                    SQLException sqlExRethrow = null;
                    if (paramTypesRs != null) {
                        try {
                            paramTypesRs.close();
                        } catch (SQLException sqlEx) {
                            sqlExRethrow = sqlEx;
                        }
                        paramTypesRs = null;
                    }
                    if (sqlExRethrow != null) {
                        throw sqlExRethrow;
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
    private void convertGetProcedureColumnsToInternalDescriptors(ResultSet paramTypesRs) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                this.paramInfo = new CallableStatementParamInfo(paramTypesRs);
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
    public boolean execute() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                boolean returnVal = false;
                this.checkStreamability();
                this.setInOutParamsOnServer();
                this.setOutParams();
                returnVal = super.execute();
                if (this.callingStoredFunction) {
                    this.functionReturnValueResults = this.results;
                    this.functionReturnValueResults.next();
                    this.results = null;
                }
                this.retrieveOutParams();
                if (!this.callingStoredFunction) {
                    return returnVal;
                }
                return false;
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
                this.checkStreamability();
                ResultSet execResults = null;
                this.setInOutParamsOnServer();
                this.setOutParams();
                execResults = super.executeQuery();
                this.retrieveOutParams();
                return execResults;
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

    private String extractProcedureName() throws SQLException {
        String sanitizedSql = StringUtils.stripComments(((PreparedQuery)this.query).getOriginalSql(), "`\"'", "`\"'", true, false, true, true);
        int endCallIndex = StringUtils.indexOfIgnoreCase(sanitizedSql, "CALL ");
        int offset = 5;
        if (endCallIndex == -1) {
            endCallIndex = StringUtils.indexOfIgnoreCase(sanitizedSql, "SELECT ");
            offset = 7;
        }
        if (endCallIndex != -1) {
            char c;
            StringBuilder nameBuf = new StringBuilder();
            String trimmedStatement = sanitizedSql.substring(endCallIndex + offset).trim();
            int statementLength = trimmedStatement.length();
            for (int i = 0; i < statementLength && !Character.isWhitespace(c = trimmedStatement.charAt(i)) && c != '(' && c != '?'; ++i) {
                nameBuf.append(c);
            }
            return nameBuf.toString();
        }
        throw SQLError.createSQLException(Messages.getString("CallableStatement.1"), "S1000", this.getExceptionInterceptor());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected String fixParameterName(String paramNameIn) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (paramNameIn == null) {
                    paramNameIn = "nullpn";
                }
                if (this.noAccessToProcedureBodies) {
                    throw SQLError.createSQLException(Messages.getString("CallableStatement.23"), "S1009", this.getExceptionInterceptor());
                }
                return CallableStatement.mangleParameterName(paramNameIn);
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Array getArray(int i) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(i);
                Array retValue = rs.getArray(this.mapOutputParameterIndexToRsIndex(i));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Array getArray(String parameterName) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(0);
                Array retValue = rs.getArray(this.fixParameterName(parameterName));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
                BigDecimal retValue = rs.getBigDecimal(this.mapOutputParameterIndexToRsIndex(parameterIndex));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
                BigDecimal retValue = rs.getBigDecimal(this.mapOutputParameterIndexToRsIndex(parameterIndex), scale);
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigDecimal getBigDecimal(String parameterName) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(0);
                BigDecimal retValue = rs.getBigDecimal(this.fixParameterName(parameterName));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Blob getBlob(int parameterIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
                Blob retValue = rs.getBlob(this.mapOutputParameterIndexToRsIndex(parameterIndex));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Blob getBlob(String parameterName) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(0);
                Blob retValue = rs.getBlob(this.fixParameterName(parameterName));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getBoolean(int parameterIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
                boolean retValue = rs.getBoolean(this.mapOutputParameterIndexToRsIndex(parameterIndex));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getBoolean(String parameterName) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(0);
                boolean retValue = rs.getBoolean(this.fixParameterName(parameterName));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte getByte(int parameterIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
                byte retValue = rs.getByte(this.mapOutputParameterIndexToRsIndex(parameterIndex));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte getByte(String parameterName) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(0);
                byte retValue = rs.getByte(this.fixParameterName(parameterName));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getBytes(int parameterIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
                byte[] retValue = rs.getBytes(this.mapOutputParameterIndexToRsIndex(parameterIndex));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getBytes(String parameterName) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(0);
                byte[] retValue = rs.getBytes(this.fixParameterName(parameterName));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Clob getClob(int parameterIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
                Clob retValue = rs.getClob(this.mapOutputParameterIndexToRsIndex(parameterIndex));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Clob getClob(String parameterName) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(0);
                Clob retValue = rs.getClob(this.fixParameterName(parameterName));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Date getDate(int parameterIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
                Date retValue = rs.getDate(this.mapOutputParameterIndexToRsIndex(parameterIndex));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
                Date retValue = rs.getDate(this.mapOutputParameterIndexToRsIndex(parameterIndex), cal);
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Date getDate(String parameterName) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(0);
                Date retValue = rs.getDate(this.fixParameterName(parameterName));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Date getDate(String parameterName, Calendar cal) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(0);
                Date retValue = rs.getDate(this.fixParameterName(parameterName), cal);
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public double getDouble(int parameterIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
                double retValue = rs.getDouble(this.mapOutputParameterIndexToRsIndex(parameterIndex));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public double getDouble(String parameterName) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(0);
                double retValue = rs.getDouble(this.fixParameterName(parameterName));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public float getFloat(int parameterIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
                float retValue = rs.getFloat(this.mapOutputParameterIndexToRsIndex(parameterIndex));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public float getFloat(String parameterName) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(0);
                float retValue = rs.getFloat(this.fixParameterName(parameterName));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getInt(int parameterIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
                int retValue = rs.getInt(this.mapOutputParameterIndexToRsIndex(parameterIndex));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getInt(String parameterName) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(0);
                int retValue = rs.getInt(this.fixParameterName(parameterName));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getLong(int parameterIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
                long retValue = rs.getLong(this.mapOutputParameterIndexToRsIndex(parameterIndex));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getLong(String parameterName) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(0);
                long retValue = rs.getLong(this.fixParameterName(parameterName));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected int getNamedParamIndex(String paramName, boolean forOut) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                CallableStatementParam namedParamInfo;
                if (this.noAccessToProcedureBodies) {
                    throw SQLError.createSQLException("No access to parameters by name when connection has been configured not to access procedure bodies", "S1009", this.getExceptionInterceptor());
                }
                if (paramName == null || paramName.length() == 0) {
                    throw SQLError.createSQLException(Messages.getString("CallableStatement.2"), "S1009", this.getExceptionInterceptor());
                }
                if (this.paramInfo == null || (namedParamInfo = this.paramInfo.getParameter(paramName)) == null) {
                    throw SQLError.createSQLException(Messages.getString("CallableStatement.3", new Object[]{paramName}), "S1009", this.getExceptionInterceptor());
                }
                if (forOut && !namedParamInfo.isOut) {
                    throw SQLError.createSQLException(Messages.getString("CallableStatement.5", new Object[]{paramName}), "S1009", this.getExceptionInterceptor());
                }
                if (this.placeholderToParameterIndexMap == null) {
                    return namedParamInfo.index + 1;
                }
                for (int i = 0; i < this.placeholderToParameterIndexMap.length; ++i) {
                    if (this.placeholderToParameterIndexMap[i] != namedParamInfo.index) continue;
                    return i + 1;
                }
                throw SQLError.createSQLException(Messages.getString("CallableStatement.6", new Object[]{paramName}), "S1009", this.getExceptionInterceptor());
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getObject(int parameterIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                CallableStatementParam paramDescriptor = this.checkIsOutputParam(parameterIndex);
                ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
                Object retVal = rs.getObjectStoredProc(this.mapOutputParameterIndexToRsIndex(parameterIndex), paramDescriptor.desiredMysqlType.getJdbcType());
                this.outputParamWasNull = rs.wasNull();
                return retVal;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getObject(int parameterIndex, Map<String, Class<?>> map) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
                Object retVal = rs.getObject(this.mapOutputParameterIndexToRsIndex(parameterIndex), map);
                this.outputParamWasNull = rs.wasNull();
                return retVal;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getObject(String parameterName) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(0);
                Object retValue = rs.getObject(this.fixParameterName(parameterName));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(0);
                Object retValue = rs.getObject(this.fixParameterName(parameterName), map);
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public <T> T getObject(int parameterIndex, Class<T> type) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
                T retVal = ((ResultSetImpl)rs).getObject(this.mapOutputParameterIndexToRsIndex(parameterIndex), type);
                this.outputParamWasNull = rs.wasNull();
                return retVal;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public <T> T getObject(String parameterName, Class<T> type) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(0);
                T retValue = ((ResultSetImpl)rs).getObject(this.fixParameterName(parameterName), type);
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected ResultSetInternalMethods getOutputParameters(int paramIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                this.outputParamWasNull = false;
                if (paramIndex == 1 && this.callingStoredFunction && this.returnValueParam != null) {
                    return this.functionReturnValueResults;
                }
                if (this.outputParameterResults == null) {
                    if (this.paramInfo.numberOfParameters() == 0) {
                        throw SQLError.createSQLException(Messages.getString("CallableStatement.7"), "S1009", this.getExceptionInterceptor());
                    }
                    throw SQLError.createSQLException(Messages.getString("CallableStatement.8"), "S1000", this.getExceptionInterceptor());
                }
                return this.outputParameterResults;
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
                if (this.placeholderToParameterIndexMap == null) {
                    return this.paramInfo;
                }
                return new CallableStatementParamInfo(this.paramInfo);
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Ref getRef(int parameterIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
                Ref retValue = rs.getRef(this.mapOutputParameterIndexToRsIndex(parameterIndex));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Ref getRef(String parameterName) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(0);
                Ref retValue = rs.getRef(this.fixParameterName(parameterName));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public short getShort(int parameterIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
                short retValue = rs.getShort(this.mapOutputParameterIndexToRsIndex(parameterIndex));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public short getShort(String parameterName) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(0);
                short retValue = rs.getShort(this.fixParameterName(parameterName));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getString(int parameterIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
                String retValue = rs.getString(this.mapOutputParameterIndexToRsIndex(parameterIndex));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getString(String parameterName) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(0);
                String retValue = rs.getString(this.fixParameterName(parameterName));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Time getTime(int parameterIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
                Time retValue = rs.getTime(this.mapOutputParameterIndexToRsIndex(parameterIndex));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
                Time retValue = rs.getTime(this.mapOutputParameterIndexToRsIndex(parameterIndex), cal);
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Time getTime(String parameterName) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(0);
                Time retValue = rs.getTime(this.fixParameterName(parameterName));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Time getTime(String parameterName, Calendar cal) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(0);
                Time retValue = rs.getTime(this.fixParameterName(parameterName), cal);
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Timestamp getTimestamp(int parameterIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
                Timestamp retValue = rs.getTimestamp(this.mapOutputParameterIndexToRsIndex(parameterIndex));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
                Timestamp retValue = rs.getTimestamp(this.mapOutputParameterIndexToRsIndex(parameterIndex), cal);
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Timestamp getTimestamp(String parameterName) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(0);
                Timestamp retValue = rs.getTimestamp(this.fixParameterName(parameterName));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(0);
                Timestamp retValue = rs.getTimestamp(this.fixParameterName(parameterName), cal);
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public URL getURL(int parameterIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
                URL retValue = rs.getURL(this.mapOutputParameterIndexToRsIndex(parameterIndex));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public URL getURL(String parameterName) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                ResultSetInternalMethods rs = this.getOutputParameters(0);
                URL retValue = rs.getURL(this.fixParameterName(parameterName));
                this.outputParamWasNull = rs.wasNull();
                return retValue;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected int mapOutputParameterIndexToRsIndex(int paramIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                int rsIndex;
                if (this.returnValueParam != null && paramIndex == 1) {
                    return 1;
                }
                this.checkParameterIndexBounds(paramIndex);
                int localParamIndex = paramIndex - 1;
                if (this.placeholderToParameterIndexMap != null) {
                    localParamIndex = this.placeholderToParameterIndexMap[localParamIndex];
                }
                if ((rsIndex = this.parameterIndexToRsIndex[localParamIndex]) == Integer.MIN_VALUE) {
                    throw SQLError.createSQLException(Messages.getString("CallableStatement.21", new Object[]{paramIndex}), "S1009", this.getExceptionInterceptor());
                }
                return rsIndex + 1;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected void registerOutParameter(int parameterIndex, MysqlType mysqlType) throws SQLException {
        CallableStatementParam paramDescriptor = this.checkIsOutputParam(parameterIndex);
        paramDescriptor.desiredMysqlType = mysqlType;
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
        try {
            try {
                MysqlType mt = MysqlType.getByJdbcType(sqlType);
                this.registerOutParameter(parameterIndex, mt);
            } catch (FeatureNotAvailableException nae) {
                throw SQLError.createSQLFeatureNotSupportedException(Messages.getString("Statement.UnsupportedSQLType") + JDBCType.valueOf(sqlType), "S1C00", this.getExceptionInterceptor());
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void registerOutParameter(int parameterIndex, SQLType sqlType) throws SQLException {
        try {
            if (sqlType instanceof MysqlType) {
                this.registerOutParameter(parameterIndex, (MysqlType)sqlType);
            } else {
                this.registerOutParameter(parameterIndex, (int)sqlType.getVendorTypeNumber());
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected void registerOutParameter(int parameterIndex, MysqlType mysqlType, int scale) throws SQLException {
        this.registerOutParameter(parameterIndex, mysqlType);
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {
        try {
            this.registerOutParameter(parameterIndex, sqlType);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void registerOutParameter(int parameterIndex, SQLType sqlType, int scale) throws SQLException {
        try {
            if (sqlType instanceof MysqlType) {
                this.registerOutParameter(parameterIndex, (MysqlType)sqlType, scale);
            } else {
                this.registerOutParameter(parameterIndex, (int)sqlType.getVendorTypeNumber(), scale);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected void registerOutParameter(int parameterIndex, MysqlType mysqlType, String typeName) throws SQLException {
        this.registerOutParameter(parameterIndex, mysqlType);
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType, String typeName) throws SQLException {
        try {
            try {
                MysqlType mt = MysqlType.getByJdbcType(sqlType);
                this.registerOutParameter(parameterIndex, mt, typeName);
            } catch (FeatureNotAvailableException nae) {
                throw SQLError.createSQLFeatureNotSupportedException(Messages.getString("Statement.UnsupportedSQLType") + JDBCType.valueOf(sqlType), "S1C00", this.getExceptionInterceptor());
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void registerOutParameter(int parameterIndex, SQLType sqlType, String typeName) throws SQLException {
        try {
            if (sqlType instanceof MysqlType) {
                this.registerOutParameter(parameterIndex, (MysqlType)sqlType, typeName);
            } else {
                this.registerOutParameter(parameterIndex, (int)sqlType.getVendorTypeNumber(), typeName);
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
    public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                this.registerOutParameter(this.getNamedParamIndex(parameterName, true), sqlType);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void registerOutParameter(String parameterName, SQLType sqlType) throws SQLException {
        try {
            if (sqlType instanceof MysqlType) {
                this.registerOutParameter(this.getNamedParamIndex(parameterName, true), (MysqlType)sqlType);
            } else {
                this.registerOutParameter(this.getNamedParamIndex(parameterName, true), (int)sqlType.getVendorTypeNumber());
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
        try {
            this.registerOutParameter(this.getNamedParamIndex(parameterName, true), sqlType, scale);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void registerOutParameter(String parameterName, SQLType sqlType, int scale) throws SQLException {
        try {
            if (sqlType instanceof MysqlType) {
                this.registerOutParameter(this.getNamedParamIndex(parameterName, true), (MysqlType)sqlType, scale);
            } else {
                this.registerOutParameter(this.getNamedParamIndex(parameterName, true), (int)sqlType.getVendorTypeNumber(), scale);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
        try {
            this.registerOutParameter(this.getNamedParamIndex(parameterName, true), sqlType, typeName);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void registerOutParameter(String parameterName, SQLType sqlType, String typeName) throws SQLException {
        try {
            if (sqlType instanceof MysqlType) {
                this.registerOutParameter(this.getNamedParamIndex(parameterName, true), (MysqlType)sqlType, typeName);
            } else {
                this.registerOutParameter(parameterName, (int)sqlType.getVendorTypeNumber(), typeName);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void retrieveOutParams() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                int numParameters = this.paramInfo.numberOfParameters();
                this.parameterIndexToRsIndex = new int[numParameters];
                for (int i = 0; i < numParameters; ++i) {
                    this.parameterIndexToRsIndex[i] = Integer.MIN_VALUE;
                }
                int localParamIndex = 0;
                if (numParameters > 0) {
                    StringBuilder outParameterQuery = new StringBuilder("SELECT ");
                    boolean firstParam = true;
                    boolean hadOutputParams = false;
                    Iterator<CallableStatementParam> paramIter = this.paramInfo.iterator();
                    while (paramIter.hasNext()) {
                        CallableStatementParam retrParamInfo = paramIter.next();
                        if (!retrParamInfo.isOut) continue;
                        hadOutputParams = true;
                        this.parameterIndexToRsIndex[retrParamInfo.index] = localParamIndex++;
                        if (retrParamInfo.paramName == null) {
                            retrParamInfo.paramName = "nullnp" + retrParamInfo.index;
                        }
                        String outParameterName = CallableStatement.mangleParameterName(retrParamInfo.paramName);
                        if (!firstParam) {
                            outParameterQuery.append(",");
                        } else {
                            firstParam = false;
                        }
                        if (!outParameterName.startsWith("@")) {
                            outParameterQuery.append('@');
                        }
                        outParameterQuery.append(outParameterName);
                    }
                    if (hadOutputParams) {
                        ResultSet outParamRs = null;
                        try (Statement outParameterStmt = null;){
                            outParameterStmt = this.connection.createStatement();
                            outParamRs = outParameterStmt.executeQuery(outParameterQuery.toString());
                            this.outputParameterResults = this.resultSetFactory.createFromResultsetRows(outParamRs.getConcurrency(), outParamRs.getType(), ((ResultSetInternalMethods)outParamRs).getRows());
                            if (this.outputParameterResults.next()) return;
                            this.outputParameterResults.close();
                            this.outputParameterResults = null;
                        }
                    } else {
                        this.outputParameterResults = null;
                    }
                } else {
                    this.outputParameterResults = null;
                }
                return;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
        try {
            this.setAsciiStream(this.getNamedParamIndex(parameterName, false), x, length);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
        try {
            this.setBigDecimal(this.getNamedParamIndex(parameterName, false), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
        try {
            this.setBinaryStream(this.getNamedParamIndex(parameterName, false), x, length);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setBoolean(String parameterName, boolean x) throws SQLException {
        try {
            this.setBoolean(this.getNamedParamIndex(parameterName, false), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setByte(String parameterName, byte x) throws SQLException {
        try {
            this.setByte(this.getNamedParamIndex(parameterName, false), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setBytes(String parameterName, byte[] x) throws SQLException {
        try {
            this.setBytes(this.getNamedParamIndex(parameterName, false), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
        try {
            this.setCharacterStream(this.getNamedParamIndex(parameterName, false), reader, length);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setDate(String parameterName, Date x) throws SQLException {
        try {
            this.setDate(this.getNamedParamIndex(parameterName, false), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
        try {
            this.setDate(this.getNamedParamIndex(parameterName, false), x, cal);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setDouble(String parameterName, double x) throws SQLException {
        try {
            this.setDouble(this.getNamedParamIndex(parameterName, false), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setFloat(String parameterName, float x) throws SQLException {
        try {
            this.setFloat(this.getNamedParamIndex(parameterName, false), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void setInOutParamsOnServer() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (this.paramInfo.numParameters > 0) {
                    Iterator<CallableStatementParam> paramIter = this.paramInfo.iterator();
                    while (paramIter.hasNext()) {
                        CallableStatementParam inParamInfo = paramIter.next();
                        if (!inParamInfo.isOut || !inParamInfo.isIn) continue;
                        if (inParamInfo.paramName == null) {
                            inParamInfo.paramName = "nullnp" + inParamInfo.index;
                        }
                        String inOutParameterName = CallableStatement.mangleParameterName(inParamInfo.paramName);
                        StringBuilder queryBuf = new StringBuilder(4 + inOutParameterName.length() + 1 + 1);
                        queryBuf.append("SET ");
                        queryBuf.append(inOutParameterName);
                        queryBuf.append("=?");
                        try (StatementImpl setPstmt = null;){
                            setPstmt = this.connection.clientPrepareStatement(queryBuf.toString()).unwrap(ClientPreparedStatement.class);
                            if (((PreparedQuery)this.query).getQueryBindings().getBindValues()[inParamInfo.index].isNull()) {
                                ((ClientPreparedStatement)setPstmt).setBytesNoEscapeNoQuotes(1, "NULL".getBytes());
                            } else {
                                byte[] parameterAsBytes = this.getBytesRepresentation(inParamInfo.index);
                                if (parameterAsBytes != null) {
                                    if (parameterAsBytes.length > 8 && parameterAsBytes[0] == 95 && parameterAsBytes[1] == 98 && parameterAsBytes[2] == 105 && parameterAsBytes[3] == 110 && parameterAsBytes[4] == 97 && parameterAsBytes[5] == 114 && parameterAsBytes[6] == 121 && parameterAsBytes[7] == 39) {
                                        ((ClientPreparedStatement)setPstmt).setBytesNoEscapeNoQuotes(1, parameterAsBytes);
                                    } else {
                                        switch (inParamInfo.desiredMysqlType) {
                                            case BIT: 
                                            case BINARY: 
                                            case GEOMETRY: 
                                            case TINYBLOB: 
                                            case BLOB: 
                                            case MEDIUMBLOB: 
                                            case LONGBLOB: 
                                            case VARBINARY: {
                                                ((ClientPreparedStatement)setPstmt).setBytes(1, parameterAsBytes);
                                                break;
                                            }
                                            default: {
                                                ((ClientPreparedStatement)setPstmt).setBytesNoEscape(1, parameterAsBytes);
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    ((ClientPreparedStatement)setPstmt).setNull(1, MysqlType.NULL);
                                }
                            }
                            ((ClientPreparedStatement)setPstmt).executeUpdate();
                        }
                    }
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setInt(String parameterName, int x) throws SQLException {
        try {
            this.setInt(this.getNamedParamIndex(parameterName, false), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setLong(String parameterName, long x) throws SQLException {
        try {
            this.setLong(this.getNamedParamIndex(parameterName, false), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setNull(String parameterName, int sqlType) throws SQLException {
        try {
            this.setNull(this.getNamedParamIndex(parameterName, false), sqlType);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
        try {
            this.setNull(this.getNamedParamIndex(parameterName, false), sqlType, typeName);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setObject(String parameterName, Object x) throws SQLException {
        try {
            this.setObject(this.getNamedParamIndex(parameterName, false), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
        try {
            this.setObject(this.getNamedParamIndex(parameterName, false), x, targetSqlType);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setObject(String parameterName, Object x, SQLType targetSqlType) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                this.setObject(this.getNamedParamIndex(parameterName, false), x, targetSqlType);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
        try {
            this.setObject(this.getNamedParamIndex(parameterName, false), x, targetSqlType, scale);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setObject(String parameterName, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                this.setObject(this.getNamedParamIndex(parameterName, false), x, targetSqlType, scaleOrLength);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void setOutParams() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (this.paramInfo.numParameters > 0) {
                    Iterator<CallableStatementParam> paramIter = this.paramInfo.iterator();
                    while (paramIter.hasNext()) {
                        CallableStatementParam outParamInfo = paramIter.next();
                        if (this.callingStoredFunction || !outParamInfo.isOut) continue;
                        if (outParamInfo.paramName == null) {
                            outParamInfo.paramName = "nullnp" + outParamInfo.index;
                        }
                        String outParameterName = CallableStatement.mangleParameterName(outParamInfo.paramName);
                        int outParamIndex = 0;
                        if (this.placeholderToParameterIndexMap == null) {
                            outParamIndex = outParamInfo.index + 1;
                        } else {
                            boolean found = false;
                            for (int i = 0; i < this.placeholderToParameterIndexMap.length; ++i) {
                                if (this.placeholderToParameterIndexMap[i] != outParamInfo.index) continue;
                                outParamIndex = i + 1;
                                found = true;
                                break;
                            }
                            if (!found) {
                                throw SQLError.createSQLException(Messages.getString("CallableStatement.21", new Object[]{outParamInfo.paramName}), "S1009", this.getExceptionInterceptor());
                            }
                        }
                        this.setBytesNoEscapeNoQuotes(outParamIndex, StringUtils.getBytes(outParameterName, this.charEncoding));
                    }
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setShort(String parameterName, short x) throws SQLException {
        try {
            this.setShort(this.getNamedParamIndex(parameterName, false), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setString(String parameterName, String x) throws SQLException {
        try {
            this.setString(this.getNamedParamIndex(parameterName, false), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setTime(String parameterName, Time x) throws SQLException {
        try {
            this.setTime(this.getNamedParamIndex(parameterName, false), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
        try {
            this.setTime(this.getNamedParamIndex(parameterName, false), x, cal);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x) throws SQLException {
        try {
            this.setTimestamp(this.getNamedParamIndex(parameterName, false), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
        try {
            this.setTimestamp(this.getNamedParamIndex(parameterName, false), x, cal);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setURL(String parameterName, URL val) throws SQLException {
        try {
            this.setURL(this.getNamedParamIndex(parameterName, false), val);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean wasNull() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                return this.outputParamWasNull;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int[] executeBatch() throws SQLException {
        try {
            return Util.truncateAndConvertToInt(this.executeLargeBatch());
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    protected int getParameterIndexOffset() {
        if (this.callingStoredFunction) {
            return -1;
        }
        return super.getParameterIndexOffset();
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x) throws SQLException {
        try {
            this.setAsciiStream(this.getNamedParamIndex(parameterName, false), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {
        try {
            this.setAsciiStream(this.getNamedParamIndex(parameterName, false), x, length);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x) throws SQLException {
        try {
            this.setBinaryStream(this.getNamedParamIndex(parameterName, false), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {
        try {
            this.setBinaryStream(this.getNamedParamIndex(parameterName, false), x, length);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setBlob(String parameterName, Blob x) throws SQLException {
        try {
            this.setBlob(this.getNamedParamIndex(parameterName, false), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream2) throws SQLException {
        try {
            this.setBlob(this.getNamedParamIndex(parameterName, false), inputStream2);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream2, long length) throws SQLException {
        try {
            this.setBlob(this.getNamedParamIndex(parameterName, false), inputStream2, length);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
        try {
            this.setCharacterStream(this.getNamedParamIndex(parameterName, false), reader);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
        try {
            this.setCharacterStream(this.getNamedParamIndex(parameterName, false), reader, length);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setClob(String parameterName, Clob x) throws SQLException {
        try {
            this.setClob(this.getNamedParamIndex(parameterName, false), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setClob(String parameterName, Reader reader) throws SQLException {
        try {
            this.setClob(this.getNamedParamIndex(parameterName, false), reader);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setClob(String parameterName, Reader reader, long length) throws SQLException {
        try {
            this.setClob(this.getNamedParamIndex(parameterName, false), reader, length);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setNCharacterStream(String parameterName, Reader value) throws SQLException {
        try {
            this.setNCharacterStream(this.getNamedParamIndex(parameterName, false), value);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {
        try {
            this.setNCharacterStream(this.getNamedParamIndex(parameterName, false), value, length);
            return;
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
    private boolean checkReadOnlyProcedure() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (this.noAccessToProcedureBodies) {
                    return false;
                }
                if (this.paramInfo.isReadOnlySafeChecked) {
                    return this.paramInfo.isReadOnlySafeProcedure;
                }
                ResultSet rs = null;
                Statement ps = null;
                try {
                    String sqlDataAccess;
                    String procName = this.extractProcedureName();
                    String catalog = this.getCurrentCatalog();
                    if (procName.indexOf(".") != -1) {
                        catalog = procName.substring(0, procName.indexOf("."));
                        if (StringUtils.startsWithIgnoreCaseAndWs(catalog, "`") && catalog.trim().endsWith("`")) {
                            catalog = catalog.substring(1, catalog.length() - 1);
                        }
                        procName = procName.substring(procName.indexOf(".") + 1);
                        procName = StringUtils.toString(StringUtils.stripEnclosure(StringUtils.getBytes(procName), "`", "`"));
                    }
                    ps = this.connection.prepareStatement("SELECT SQL_DATA_ACCESS FROM information_schema.routines WHERE routine_schema = ? AND routine_name = ?");
                    ps.setMaxRows(0);
                    ps.setFetchSize(0);
                    ps.setString(1, catalog);
                    ps.setString(2, procName);
                    rs = ps.executeQuery();
                    if (rs.next() && ("READS SQL DATA".equalsIgnoreCase(sqlDataAccess = rs.getString(1)) || "NO SQL".equalsIgnoreCase(sqlDataAccess))) {
                        CallableStatementParamInfo callableStatementParamInfo = this.paramInfo;
                        synchronized (callableStatementParamInfo) {
                            this.paramInfo.isReadOnlySafeChecked = true;
                            this.paramInfo.isReadOnlySafeProcedure = true;
                        }
                        boolean bl = true;
                        return bl;
                    }
                } catch (SQLException sQLException) {
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                }
                this.paramInfo.isReadOnlySafeChecked = false;
                this.paramInfo.isReadOnlySafeProcedure = false;
                return false;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    protected boolean checkReadOnlySafeStatement() throws SQLException {
        return super.checkReadOnlySafeStatement() || this.checkReadOnlyProcedure();
    }

    @Override
    public RowId getRowId(int parameterIndex) throws SQLException {
        try {
            ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
            RowId retValue = rs.getRowId(this.mapOutputParameterIndexToRsIndex(parameterIndex));
            this.outputParamWasNull = rs.wasNull();
            return retValue;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public RowId getRowId(String parameterName) throws SQLException {
        try {
            ResultSetInternalMethods rs = this.getOutputParameters(0);
            RowId retValue = rs.getRowId(this.fixParameterName(parameterName));
            this.outputParamWasNull = rs.wasNull();
            return retValue;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setRowId(String parameterName, RowId x) throws SQLException {
        try {
            this.setRowId(this.getNamedParamIndex(parameterName, false), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setNString(String parameterName, String value) throws SQLException {
        try {
            this.setNString(this.getNamedParamIndex(parameterName, false), value);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setNClob(String parameterName, NClob value) throws SQLException {
        try {
            this.setNClob(this.getNamedParamIndex(parameterName, false), value);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setNClob(String parameterName, Reader reader) throws SQLException {
        try {
            this.setNClob(this.getNamedParamIndex(parameterName, false), reader);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
        try {
            this.setNClob(this.getNamedParamIndex(parameterName, false), reader, length);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
        try {
            this.setSQLXML(this.getNamedParamIndex(parameterName, false), xmlObject);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public SQLXML getSQLXML(int parameterIndex) throws SQLException {
        try {
            ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
            SQLXML retValue = rs.getSQLXML(this.mapOutputParameterIndexToRsIndex(parameterIndex));
            this.outputParamWasNull = rs.wasNull();
            return retValue;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public SQLXML getSQLXML(String parameterName) throws SQLException {
        try {
            ResultSetInternalMethods rs = this.getOutputParameters(0);
            SQLXML retValue = rs.getSQLXML(this.fixParameterName(parameterName));
            this.outputParamWasNull = rs.wasNull();
            return retValue;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public String getNString(int parameterIndex) throws SQLException {
        try {
            ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
            String retValue = rs.getNString(this.mapOutputParameterIndexToRsIndex(parameterIndex));
            this.outputParamWasNull = rs.wasNull();
            return retValue;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public String getNString(String parameterName) throws SQLException {
        try {
            ResultSetInternalMethods rs = this.getOutputParameters(0);
            String retValue = rs.getNString(this.fixParameterName(parameterName));
            this.outputParamWasNull = rs.wasNull();
            return retValue;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Reader getNCharacterStream(int parameterIndex) throws SQLException {
        try {
            ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
            Reader retValue = rs.getNCharacterStream(this.mapOutputParameterIndexToRsIndex(parameterIndex));
            this.outputParamWasNull = rs.wasNull();
            return retValue;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Reader getNCharacterStream(String parameterName) throws SQLException {
        try {
            ResultSetInternalMethods rs = this.getOutputParameters(0);
            Reader retValue = rs.getNCharacterStream(this.fixParameterName(parameterName));
            this.outputParamWasNull = rs.wasNull();
            return retValue;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Reader getCharacterStream(int parameterIndex) throws SQLException {
        try {
            ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
            Reader retValue = rs.getCharacterStream(this.mapOutputParameterIndexToRsIndex(parameterIndex));
            this.outputParamWasNull = rs.wasNull();
            return retValue;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Reader getCharacterStream(String parameterName) throws SQLException {
        try {
            ResultSetInternalMethods rs = this.getOutputParameters(0);
            Reader retValue = rs.getCharacterStream(this.fixParameterName(parameterName));
            this.outputParamWasNull = rs.wasNull();
            return retValue;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public NClob getNClob(int parameterIndex) throws SQLException {
        try {
            ResultSetInternalMethods rs = this.getOutputParameters(parameterIndex);
            NClob retValue = rs.getNClob(this.mapOutputParameterIndexToRsIndex(parameterIndex));
            this.outputParamWasNull = rs.wasNull();
            return retValue;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public NClob getNClob(String parameterName) throws SQLException {
        try {
            ResultSetInternalMethods rs = this.getOutputParameters(0);
            NClob retValue = rs.getNClob(this.fixParameterName(parameterName));
            this.outputParamWasNull = rs.wasNull();
            return retValue;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected byte[] s2b(String s2) {
        return s2 == null ? null : StringUtils.getBytes(s2, this.charEncoding);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long executeLargeUpdate() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                long returnVal = -1L;
                this.checkStreamability();
                if (this.callingStoredFunction) {
                    this.execute();
                    return -1L;
                }
                this.setInOutParamsOnServer();
                this.setOutParams();
                returnVal = super.executeLargeUpdate();
                this.retrieveOutParams();
                return returnVal;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public long[] executeLargeBatch() throws SQLException {
        try {
            if (this.hasOutputParams) {
                throw SQLError.createSQLException("Can't call executeBatch() on CallableStatement with OUTPUT parameters", "S1009", this.getExceptionInterceptor());
            }
            return super.executeLargeBatch();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    public class CallableStatementParamInfo
    implements ParameterMetaData {
        String catalogInUse;
        boolean isFunctionCall;
        String nativeSql;
        int numParameters;
        List<CallableStatementParam> parameterList;
        Map<String, CallableStatementParam> parameterMap;
        boolean isReadOnlySafeProcedure = false;
        boolean isReadOnlySafeChecked = false;

        CallableStatementParamInfo(CallableStatementParamInfo fullParamInfo) {
            this.nativeSql = ((PreparedQuery)CallableStatement.this.query).getOriginalSql();
            this.catalogInUse = CallableStatement.this.getCurrentCatalog();
            this.isFunctionCall = fullParamInfo.isFunctionCall;
            int[] localParameterMap = CallableStatement.this.placeholderToParameterIndexMap;
            int parameterMapLength = localParameterMap.length;
            this.isReadOnlySafeProcedure = fullParamInfo.isReadOnlySafeProcedure;
            this.isReadOnlySafeChecked = fullParamInfo.isReadOnlySafeChecked;
            this.parameterList = new ArrayList<CallableStatementParam>(fullParamInfo.numParameters);
            this.parameterMap = new HashMap<String, CallableStatementParam>(fullParamInfo.numParameters);
            if (this.isFunctionCall) {
                this.parameterList.add(fullParamInfo.parameterList.get(0));
            }
            int offset = this.isFunctionCall ? 1 : 0;
            for (int i = 0; i < parameterMapLength; ++i) {
                if (localParameterMap[i] == 0) continue;
                CallableStatementParam param = fullParamInfo.parameterList.get(localParameterMap[i] + offset);
                this.parameterList.add(param);
                this.parameterMap.put(param.paramName, param);
            }
            this.numParameters = this.parameterList.size();
        }

        CallableStatementParamInfo(ResultSet paramTypesRs) throws SQLException {
            boolean hadRows = paramTypesRs.last();
            this.nativeSql = ((PreparedQuery)CallableStatement.this.query).getOriginalSql();
            this.catalogInUse = CallableStatement.this.getCurrentCatalog();
            this.isFunctionCall = CallableStatement.this.callingStoredFunction;
            if (hadRows) {
                this.numParameters = paramTypesRs.getRow();
                this.parameterList = new ArrayList<CallableStatementParam>(this.numParameters);
                this.parameterMap = new HashMap<String, CallableStatementParam>(this.numParameters);
                paramTypesRs.beforeFirst();
                this.addParametersFromDBMD(paramTypesRs);
            } else {
                this.numParameters = 0;
            }
            if (this.isFunctionCall) {
                ++this.numParameters;
            }
        }

        private void addParametersFromDBMD(ResultSet paramTypesRs) throws SQLException {
            int i = 0;
            while (paramTypesRs.next()) {
                int inOutModifier;
                String paramName = paramTypesRs.getString(4);
                switch (paramTypesRs.getInt(5)) {
                    case 1: {
                        inOutModifier = 1;
                        break;
                    }
                    case 2: {
                        inOutModifier = 2;
                        break;
                    }
                    case 4: 
                    case 5: {
                        inOutModifier = 4;
                        break;
                    }
                    default: {
                        inOutModifier = 0;
                    }
                }
                boolean isOutParameter = false;
                boolean isInParameter = false;
                if (i == 0 && this.isFunctionCall) {
                    isOutParameter = true;
                    isInParameter = false;
                } else if (inOutModifier == 2) {
                    isOutParameter = true;
                    isInParameter = true;
                } else if (inOutModifier == 1) {
                    isOutParameter = false;
                    isInParameter = true;
                } else if (inOutModifier == 4) {
                    isOutParameter = true;
                    isInParameter = false;
                }
                int jdbcType = paramTypesRs.getInt(6);
                String typeName = paramTypesRs.getString(7);
                int precision = paramTypesRs.getInt(8);
                int scale = paramTypesRs.getInt(10);
                short nullability = paramTypesRs.getShort(12);
                CallableStatementParam paramInfoToAdd = new CallableStatementParam(paramName, i++, isInParameter, isOutParameter, jdbcType, typeName, precision, scale, nullability, inOutModifier);
                this.parameterList.add(paramInfoToAdd);
                this.parameterMap.put(paramName, paramInfoToAdd);
            }
        }

        protected void checkBounds(int paramIndex) throws SQLException {
            int localParamIndex = paramIndex - 1;
            if (paramIndex < 0 || localParamIndex >= this.numParameters) {
                throw SQLError.createSQLException(Messages.getString("CallableStatement.11", new Object[]{paramIndex, this.numParameters}), "S1009", CallableStatement.this.getExceptionInterceptor());
            }
        }

        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        CallableStatementParam getParameter(int index) {
            return this.parameterList.get(index);
        }

        CallableStatementParam getParameter(String name) {
            return this.parameterMap.get(name);
        }

        @Override
        public String getParameterClassName(int arg0) throws SQLException {
            try {
                String mysqlTypeName = this.getParameterTypeName(arg0);
                MysqlType mysqlType = MysqlType.getByName(mysqlTypeName);
                switch (mysqlType) {
                    case YEAR: {
                        if (!CallableStatement.this.session.getPropertySet().getBooleanProperty("yearIsDateType").getValue().booleanValue()) {
                            return Short.class.getName();
                        }
                        return mysqlType.getClassName();
                    }
                }
                return mysqlType.getClassName();
            } catch (CJException cJException) {
                throw SQLExceptionsMapping.translateException(cJException);
            }
        }

        @Override
        public int getParameterCount() throws SQLException {
            try {
                if (this.parameterList == null) {
                    return 0;
                }
                return this.parameterList.size();
            } catch (CJException cJException) {
                throw SQLExceptionsMapping.translateException(cJException);
            }
        }

        @Override
        public int getParameterMode(int arg0) throws SQLException {
            try {
                this.checkBounds(arg0);
                return this.getParameter((int)(arg0 - 1)).inOutModifier;
            } catch (CJException cJException) {
                throw SQLExceptionsMapping.translateException(cJException);
            }
        }

        @Override
        public int getParameterType(int arg0) throws SQLException {
            try {
                this.checkBounds(arg0);
                return this.getParameter((int)(arg0 - 1)).jdbcType;
            } catch (CJException cJException) {
                throw SQLExceptionsMapping.translateException(cJException);
            }
        }

        @Override
        public String getParameterTypeName(int arg0) throws SQLException {
            try {
                this.checkBounds(arg0);
                return this.getParameter((int)(arg0 - 1)).typeName;
            } catch (CJException cJException) {
                throw SQLExceptionsMapping.translateException(cJException);
            }
        }

        @Override
        public int getPrecision(int arg0) throws SQLException {
            try {
                this.checkBounds(arg0);
                return this.getParameter((int)(arg0 - 1)).precision;
            } catch (CJException cJException) {
                throw SQLExceptionsMapping.translateException(cJException);
            }
        }

        @Override
        public int getScale(int arg0) throws SQLException {
            try {
                this.checkBounds(arg0);
                return this.getParameter((int)(arg0 - 1)).scale;
            } catch (CJException cJException) {
                throw SQLExceptionsMapping.translateException(cJException);
            }
        }

        @Override
        public int isNullable(int arg0) throws SQLException {
            try {
                this.checkBounds(arg0);
                return this.getParameter((int)(arg0 - 1)).nullability;
            } catch (CJException cJException) {
                throw SQLExceptionsMapping.translateException(cJException);
            }
        }

        @Override
        public boolean isSigned(int arg0) throws SQLException {
            try {
                this.checkBounds(arg0);
                return false;
            } catch (CJException cJException) {
                throw SQLExceptionsMapping.translateException(cJException);
            }
        }

        Iterator<CallableStatementParam> iterator() {
            return this.parameterList.iterator();
        }

        int numberOfParameters() {
            return this.numParameters;
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            try {
                CallableStatement.this.checkClosed();
                return iface.isInstance(this);
            } catch (CJException cJException) {
                throw SQLExceptionsMapping.translateException(cJException);
            }
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            try {
                try {
                    return iface.cast(this);
                } catch (ClassCastException cce) {
                    throw SQLError.createSQLException(Messages.getString("Common.UnableToUnwrap", new Object[]{iface.toString()}), "S1009", CallableStatement.this.getExceptionInterceptor());
                }
            } catch (CJException cJException) {
                throw SQLExceptionsMapping.translateException(cJException);
            }
        }
    }

    protected static class CallableStatementParam {
        int index;
        int inOutModifier;
        boolean isIn;
        boolean isOut;
        int jdbcType;
        short nullability;
        String paramName;
        int precision;
        int scale;
        String typeName;
        MysqlType desiredMysqlType = MysqlType.UNKNOWN;

        CallableStatementParam(String name, int idx, boolean in, boolean out, int jdbcType, String typeName, int precision, int scale, short nullability, int inOutModifier) {
            this.paramName = name;
            this.isIn = in;
            this.isOut = out;
            this.index = idx;
            this.jdbcType = jdbcType;
            this.typeName = typeName;
            this.precision = precision;
            this.scale = scale;
            this.nullability = nullability;
            this.inOutModifier = inOutModifier;
        }

        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}

