/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.result;

import com.mysql.cj.Constants;
import com.mysql.cj.Messages;
import com.mysql.cj.MysqlType;
import com.mysql.cj.exceptions.AssertionFailedException;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.FeatureNotAvailableException;
import com.mysql.cj.jdbc.ClientPreparedStatement;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.MysqlSQLXML;
import com.mysql.cj.jdbc.NClob;
import com.mysql.cj.jdbc.StatementImpl;
import com.mysql.cj.jdbc.exceptions.NotUpdatable;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import com.mysql.cj.jdbc.result.ResultSetImpl;
import com.mysql.cj.log.ProfilerEventHandlerFactory;
import com.mysql.cj.log.ProfilerEventImpl;
import com.mysql.cj.protocol.ResultsetRow;
import com.mysql.cj.protocol.ResultsetRows;
import com.mysql.cj.protocol.a.result.ByteArrayRow;
import com.mysql.cj.result.Field;
import com.mysql.cj.result.Row;
import com.mysql.cj.util.StringUtils;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UpdatableResultSet
extends ResultSetImpl {
    static final byte[] STREAM_DATA_MARKER = StringUtils.getBytes("** STREAM DATA **");
    private String charEncoding;
    private byte[][] defaultColumnValue;
    private ClientPreparedStatement deleter = null;
    private String deleteSQL = null;
    protected ClientPreparedStatement inserter = null;
    private String insertSQL = null;
    private boolean isUpdatable = false;
    private String notUpdatableReason = null;
    private List<Integer> primaryKeyIndicies = null;
    private String qualifiedAndQuotedTableName;
    private String quotedIdChar = null;
    private ClientPreparedStatement refresher;
    private String refreshSQL = null;
    private Row savedCurrentRow;
    protected ClientPreparedStatement updater = null;
    private String updateSQL = null;
    private boolean populateInserterWithDefaultValues = false;
    private boolean hasLongColumnInfo = false;
    private Map<String, Map<String, Map<String, Integer>>> databasesUsedToTablesUsed = null;
    private boolean onInsertRow = false;
    protected boolean doingUpdates = false;

    public UpdatableResultSet(ResultsetRows tuples, JdbcConnection conn, StatementImpl creatorStmt) throws SQLException {
        super(tuples, conn, creatorStmt);
        this.checkUpdatability();
        this.populateInserterWithDefaultValues = this.getSession().getPropertySet().getBooleanProperty("populateInsertRowWithDefaultValues").getValue();
        this.hasLongColumnInfo = this.getSession().getServerSession().hasLongColumnInfo();
    }

    @Override
    public boolean absolute(int row) throws SQLException {
        try {
            if (this.onInsertRow) {
                this.onInsertRow = false;
            }
            if (this.doingUpdates) {
                this.doingUpdates = false;
            }
            return super.absolute(row);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void afterLast() throws SQLException {
        try {
            if (this.onInsertRow) {
                this.onInsertRow = false;
            }
            if (this.doingUpdates) {
                this.doingUpdates = false;
            }
            super.afterLast();
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void beforeFirst() throws SQLException {
        try {
            if (this.onInsertRow) {
                this.onInsertRow = false;
            }
            if (this.doingUpdates) {
                this.doingUpdates = false;
            }
            super.beforeFirst();
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void cancelRowUpdates() throws SQLException {
        try {
            if (this.doingUpdates) {
                this.doingUpdates = false;
                this.updater.clearParameters();
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    protected void checkRowPos() throws SQLException {
        if (!this.onInsertRow) {
            super.checkRowPos();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void checkUpdatability() throws SQLException {
        try {
            if (this.getMetadata() == null) {
                return;
            }
            String singleTableName = null;
            String catalogName = null;
            int primaryKeyCount = 0;
            Field[] fields = this.getMetadata().getFields();
            if (this.catalog == null || this.catalog.length() == 0) {
                this.catalog = fields[0].getDatabaseName();
                if (this.catalog == null || this.catalog.length() == 0) {
                    throw SQLError.createSQLException(Messages.getString("UpdatableResultSet.43"), "S1009", this.getExceptionInterceptor());
                }
            }
            if (fields.length > 0) {
                singleTableName = fields[0].getOriginalTableName();
                catalogName = fields[0].getDatabaseName();
                if (singleTableName == null) {
                    singleTableName = fields[0].getTableName();
                    catalogName = this.catalog;
                }
                if (singleTableName == null) {
                    this.isUpdatable = false;
                    this.notUpdatableReason = Messages.getString("NotUpdatableReason.3");
                    return;
                }
                if (fields[0].isPrimaryKey()) {
                    ++primaryKeyCount;
                }
                for (int i = 1; i < fields.length; ++i) {
                    String otherTableName = fields[i].getOriginalTableName();
                    String otherCatalogName = fields[i].getDatabaseName();
                    if (otherTableName == null) {
                        otherTableName = fields[i].getTableName();
                        otherCatalogName = this.catalog;
                    }
                    if (otherTableName == null) {
                        this.isUpdatable = false;
                        this.notUpdatableReason = Messages.getString("NotUpdatableReason.3");
                        return;
                    }
                    if (!otherTableName.equals(singleTableName)) {
                        this.isUpdatable = false;
                        this.notUpdatableReason = Messages.getString("NotUpdatableReason.0");
                        return;
                    }
                    if (catalogName == null || !otherCatalogName.equals(catalogName)) {
                        this.isUpdatable = false;
                        this.notUpdatableReason = Messages.getString("NotUpdatableReason.1");
                        return;
                    }
                    if (!fields[i].isPrimaryKey()) continue;
                    ++primaryKeyCount;
                }
            } else {
                this.isUpdatable = false;
                this.notUpdatableReason = Messages.getString("NotUpdatableReason.3");
                return;
            }
            if (this.getSession().getPropertySet().getBooleanProperty("strictUpdates").getValue().booleanValue()) {
                DatabaseMetaData dbmd = this.getConnection().getMetaData();
                ResultSet rs = null;
                HashMap<String, String> primaryKeyNames = new HashMap<String, String>();
                try {
                    rs = dbmd.getPrimaryKeys(catalogName, null, singleTableName);
                    while (rs.next()) {
                        String keyName = rs.getString(4);
                        keyName = keyName.toUpperCase();
                        primaryKeyNames.put(keyName, keyName);
                    }
                } finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (Exception ex) {
                            AssertionFailedException.shouldNotHappen(ex);
                        }
                        rs = null;
                    }
                }
                int existingPrimaryKeysCount = primaryKeyNames.size();
                if (existingPrimaryKeysCount == 0) {
                    this.isUpdatable = false;
                    this.notUpdatableReason = Messages.getString("NotUpdatableReason.5");
                    return;
                }
                for (int i = 0; i < fields.length; ++i) {
                    String originalName;
                    String columnNameUC;
                    if (!fields[i].isPrimaryKey() || primaryKeyNames.remove(columnNameUC = fields[i].getName().toUpperCase()) != null || (originalName = fields[i].getOriginalName()) == null || primaryKeyNames.remove(originalName.toUpperCase()) != null) continue;
                    this.isUpdatable = false;
                    this.notUpdatableReason = Messages.getString("NotUpdatableReason.6", new Object[]{originalName});
                    return;
                }
                this.isUpdatable = primaryKeyNames.isEmpty();
                if (!this.isUpdatable) {
                    this.notUpdatableReason = existingPrimaryKeysCount > 1 ? Messages.getString("NotUpdatableReason.7") : Messages.getString("NotUpdatableReason.4");
                    return;
                }
            }
            if (primaryKeyCount == 0) {
                this.isUpdatable = false;
                this.notUpdatableReason = Messages.getString("NotUpdatableReason.4");
                return;
            }
            this.isUpdatable = true;
            this.notUpdatableReason = null;
            return;
        } catch (SQLException sqlEx) {
            this.isUpdatable = false;
            this.notUpdatableReason = sqlEx.getMessage();
            return;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void deleteRow() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.isUpdatable) {
                    throw new NotUpdatable(this.notUpdatableReason);
                }
                if (this.onInsertRow) {
                    throw SQLError.createSQLException(Messages.getString("UpdatableResultSet.1"), this.getExceptionInterceptor());
                }
                if (this.rowData.size() == 0) {
                    throw SQLError.createSQLException(Messages.getString("UpdatableResultSet.2"), this.getExceptionInterceptor());
                }
                if (this.isBeforeFirst()) {
                    throw SQLError.createSQLException(Messages.getString("UpdatableResultSet.3"), this.getExceptionInterceptor());
                }
                if (this.isAfterLast()) {
                    throw SQLError.createSQLException(Messages.getString("UpdatableResultSet.4"), this.getExceptionInterceptor());
                }
                if (this.deleter == null) {
                    if (this.deleteSQL == null) {
                        this.generateStatements();
                    }
                    this.deleter = (ClientPreparedStatement)this.connection.clientPrepareStatement(this.deleteSQL);
                }
                this.deleter.clearParameters();
                int numKeys = this.primaryKeyIndicies.size();
                if (numKeys == 1) {
                    int index = this.primaryKeyIndicies.get(0);
                    this.setParamValue(this.deleter, 1, this.thisRow, index, this.getMetadata().getFields()[index].getMysqlType());
                } else {
                    for (int i = 0; i < numKeys; ++i) {
                        int index = this.primaryKeyIndicies.get(i);
                        this.setParamValue(this.deleter, i + 1, this.thisRow, index, this.getMetadata().getFields()[index].getMysqlType());
                    }
                }
                this.deleter.executeUpdate();
                this.rowData.remove();
                this.previous();
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    private void setParamValue(ClientPreparedStatement ps, int psIdx, Row row, int rsIdx, MysqlType mysqlType) throws SQLException {
        byte[] val = row.getBytes(rsIdx);
        if (val == null) {
            ps.setNull(psIdx, MysqlType.NULL);
            return;
        }
        switch (mysqlType) {
            case NULL: {
                ps.setNull(psIdx, MysqlType.NULL);
                break;
            }
            case TINYINT: 
            case TINYINT_UNSIGNED: 
            case SMALLINT: 
            case SMALLINT_UNSIGNED: 
            case MEDIUMINT: 
            case MEDIUMINT_UNSIGNED: 
            case INT: 
            case INT_UNSIGNED: 
            case YEAR: {
                ps.setInt(psIdx, this.getInt(rsIdx + 1));
                break;
            }
            case BIGINT: {
                ps.setLong(psIdx, this.getLong(rsIdx + 1));
                break;
            }
            case BIGINT_UNSIGNED: {
                ps.setBigInteger(psIdx, this.getBigInteger(rsIdx + 1));
                break;
            }
            case CHAR: 
            case ENUM: 
            case SET: 
            case VARCHAR: 
            case JSON: 
            case TINYTEXT: 
            case TEXT: 
            case MEDIUMTEXT: 
            case LONGTEXT: 
            case DECIMAL: 
            case DECIMAL_UNSIGNED: {
                ps.setString(psIdx, this.getString(rsIdx + 1));
                break;
            }
            case DATE: {
                ps.setDate(psIdx, this.getDate(rsIdx + 1));
                break;
            }
            case TIMESTAMP: 
            case DATETIME: {
                ps.setTimestamp(psIdx, this.getTimestamp(rsIdx + 1));
                break;
            }
            case TIME: {
                ps.setTime(psIdx, this.getTime(rsIdx + 1));
                break;
            }
            case DOUBLE: 
            case DOUBLE_UNSIGNED: 
            case FLOAT: 
            case FLOAT_UNSIGNED: 
            case BOOLEAN: 
            case BIT: {
                ps.setBytesNoEscapeNoQuotes(psIdx, val);
                break;
            }
            default: {
                ps.setBytes(psIdx, val);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void extractDefaultValues() throws SQLException {
        DatabaseMetaData dbmd = this.getConnection().getMetaData();
        this.defaultColumnValue = new byte[this.getMetadata().getFields().length][];
        ResultSet columnsResultSet = null;
        for (Map.Entry<String, Map<String, Map<String, Integer>>> dbEntry : this.databasesUsedToTablesUsed.entrySet()) {
            for (Map.Entry<String, Map<String, Integer>> tableEntry : dbEntry.getValue().entrySet()) {
                String tableName = tableEntry.getKey();
                Map<String, Integer> columnNamesToIndices = tableEntry.getValue();
                try {
                    columnsResultSet = dbmd.getColumns(this.catalog, null, tableName, "%");
                    while (columnsResultSet.next()) {
                        String columnName = columnsResultSet.getString("COLUMN_NAME");
                        byte[] defaultValue = columnsResultSet.getBytes("COLUMN_DEF");
                        if (!columnNamesToIndices.containsKey(columnName)) continue;
                        int localColumnIndex = columnNamesToIndices.get(columnName);
                        this.defaultColumnValue[localColumnIndex] = defaultValue;
                    }
                } finally {
                    if (columnsResultSet == null) continue;
                    columnsResultSet.close();
                    columnsResultSet = null;
                }
            }
        }
    }

    @Override
    public boolean first() throws SQLException {
        try {
            if (this.onInsertRow) {
                this.onInsertRow = false;
            }
            if (this.doingUpdates) {
                this.doingUpdates = false;
            }
            return super.first();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected void generateStatements() throws SQLException {
        try {
            if (!this.isUpdatable) {
                this.doingUpdates = false;
                this.onInsertRow = false;
                throw new NotUpdatable(this.notUpdatableReason);
            }
            String quotedId = this.getQuotedIdChar();
            TreeMap<String, String> tableNamesSoFar = null;
            if (this.session.getServerSession().isLowerCaseTableNames()) {
                tableNamesSoFar = new TreeMap(String.CASE_INSENSITIVE_ORDER);
                this.databasesUsedToTablesUsed = new TreeMap<String, Map<String, Map<String, Integer>>>(String.CASE_INSENSITIVE_ORDER);
            } else {
                tableNamesSoFar = new TreeMap<String, String>();
                this.databasesUsedToTablesUsed = new TreeMap<String, Map<String, Map<String, Integer>>>();
            }
            this.primaryKeyIndicies = new ArrayList<Integer>();
            StringBuilder fieldValues = new StringBuilder();
            StringBuilder keyValues = new StringBuilder();
            StringBuilder columnNames = new StringBuilder();
            StringBuilder insertPlaceHolders = new StringBuilder();
            StringBuilder allTablesBuf = new StringBuilder();
            HashMap<Integer, String> columnIndicesToTable = new HashMap<Integer, String>();
            boolean firstTime = true;
            boolean keysFirstTime = true;
            Field[] fields = this.getMetadata().getFields();
            for (int i = 0; i < fields.length; ++i) {
                StringBuilder tableNameBuffer = new StringBuilder();
                Map<String, Integer> updColumnNameToIndex = null;
                if (fields[i].getOriginalTableName() != null) {
                    String databaseName = fields[i].getDatabaseName();
                    if (databaseName != null && databaseName.length() > 0) {
                        tableNameBuffer.append(quotedId);
                        tableNameBuffer.append(databaseName);
                        tableNameBuffer.append(quotedId);
                        tableNameBuffer.append('.');
                    }
                    String tableOnlyName = fields[i].getOriginalTableName();
                    tableNameBuffer.append(quotedId);
                    tableNameBuffer.append(tableOnlyName);
                    tableNameBuffer.append(quotedId);
                    String fqTableName = tableNameBuffer.toString();
                    if (!tableNamesSoFar.containsKey(fqTableName)) {
                        if (!tableNamesSoFar.isEmpty()) {
                            allTablesBuf.append(',');
                        }
                        allTablesBuf.append(fqTableName);
                        tableNamesSoFar.put(fqTableName, fqTableName);
                    }
                    columnIndicesToTable.put(i, fqTableName);
                    updColumnNameToIndex = this.getColumnsToIndexMapForTableAndDB(databaseName, tableOnlyName);
                } else {
                    String tableOnlyName = fields[i].getTableName();
                    if (tableOnlyName != null) {
                        tableNameBuffer.append(quotedId);
                        tableNameBuffer.append(tableOnlyName);
                        tableNameBuffer.append(quotedId);
                        String fqTableName = tableNameBuffer.toString();
                        if (!tableNamesSoFar.containsKey(fqTableName)) {
                            if (!tableNamesSoFar.isEmpty()) {
                                allTablesBuf.append(',');
                            }
                            allTablesBuf.append(fqTableName);
                            tableNamesSoFar.put(fqTableName, fqTableName);
                        }
                        columnIndicesToTable.put(i, fqTableName);
                        updColumnNameToIndex = this.getColumnsToIndexMapForTableAndDB(this.catalog, tableOnlyName);
                    }
                }
                String originalColumnName = fields[i].getOriginalName();
                String columnName = null;
                columnName = this.hasLongColumnInfo && originalColumnName != null && originalColumnName.length() > 0 ? originalColumnName : fields[i].getName();
                if (updColumnNameToIndex != null && columnName != null) {
                    updColumnNameToIndex.put(columnName, i);
                }
                String originalTableName = fields[i].getOriginalTableName();
                String tableName = null;
                tableName = this.hasLongColumnInfo && originalTableName != null && originalTableName.length() > 0 ? originalTableName : fields[i].getTableName();
                StringBuilder fqcnBuf = new StringBuilder();
                String databaseName = fields[i].getDatabaseName();
                if (databaseName != null && databaseName.length() > 0) {
                    fqcnBuf.append(quotedId);
                    fqcnBuf.append(databaseName);
                    fqcnBuf.append(quotedId);
                    fqcnBuf.append('.');
                }
                fqcnBuf.append(quotedId);
                fqcnBuf.append(tableName);
                fqcnBuf.append(quotedId);
                fqcnBuf.append('.');
                fqcnBuf.append(quotedId);
                fqcnBuf.append(columnName);
                fqcnBuf.append(quotedId);
                String qualifiedColumnName = fqcnBuf.toString();
                if (fields[i].isPrimaryKey()) {
                    this.primaryKeyIndicies.add(i);
                    if (!keysFirstTime) {
                        keyValues.append(" AND ");
                    } else {
                        keysFirstTime = false;
                    }
                    keyValues.append(qualifiedColumnName);
                    keyValues.append("<=>");
                    keyValues.append("?");
                }
                if (firstTime) {
                    firstTime = false;
                    fieldValues.append("SET ");
                } else {
                    fieldValues.append(",");
                    columnNames.append(",");
                    insertPlaceHolders.append(",");
                }
                insertPlaceHolders.append("?");
                columnNames.append(qualifiedColumnName);
                fieldValues.append(qualifiedColumnName);
                fieldValues.append("=?");
            }
            this.qualifiedAndQuotedTableName = allTablesBuf.toString();
            this.updateSQL = "UPDATE " + this.qualifiedAndQuotedTableName + " " + fieldValues.toString() + " WHERE " + keyValues.toString();
            this.insertSQL = "INSERT INTO " + this.qualifiedAndQuotedTableName + " (" + columnNames.toString() + ") VALUES (" + insertPlaceHolders.toString() + ")";
            this.refreshSQL = "SELECT " + columnNames.toString() + " FROM " + this.qualifiedAndQuotedTableName + " WHERE " + keyValues.toString();
            this.deleteSQL = "DELETE FROM " + this.qualifiedAndQuotedTableName + " WHERE " + keyValues.toString();
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    private Map<String, Integer> getColumnsToIndexMapForTableAndDB(String databaseName, String tableName) {
        Map<String, Integer> nameToIndex;
        Map<String, Map<String, Integer>> tablesUsedToColumnsMap = this.databasesUsedToTablesUsed.get(databaseName);
        if (tablesUsedToColumnsMap == null) {
            tablesUsedToColumnsMap = this.session.getServerSession().isLowerCaseTableNames() ? new TreeMap<String, Map<String, Integer>>(String.CASE_INSENSITIVE_ORDER) : new TreeMap<String, Map<String, Integer>>();
            this.databasesUsedToTablesUsed.put(databaseName, tablesUsedToColumnsMap);
        }
        if ((nameToIndex = tablesUsedToColumnsMap.get(tableName)) == null) {
            nameToIndex = new HashMap<String, Integer>();
            tablesUsedToColumnsMap.put(tableName, nameToIndex);
        }
        return nameToIndex;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getConcurrency() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                return this.isUpdatable ? 1008 : 1007;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    private String getQuotedIdChar() throws SQLException {
        if (this.quotedIdChar == null) {
            this.quotedIdChar = this.session.getIdentifierQuoteString();
        }
        return this.quotedIdChar;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertRow() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.onInsertRow) {
                    throw SQLError.createSQLException(Messages.getString("UpdatableResultSet.7"), this.getExceptionInterceptor());
                }
                this.inserter.executeUpdate();
                long autoIncrementId = this.inserter.getLastInsertID();
                Field[] fields = this.getMetadata().getFields();
                int numFields = fields.length;
                byte[][] newRow = new byte[numFields][];
                for (int i = 0; i < numFields; ++i) {
                    newRow[i] = (byte[])(this.inserter.isNull(i) ? null : this.inserter.getBytesRepresentation(i));
                    if (!fields[i].isAutoIncrement() || autoIncrementId <= 0L) continue;
                    newRow[i] = StringUtils.getBytes(String.valueOf(autoIncrementId));
                    this.inserter.setBytesNoEscapeNoQuotes(i + 1, newRow[i]);
                }
                ByteArrayRow resultSetRow = new ByteArrayRow(newRow, this.getExceptionInterceptor());
                this.refreshRow(this.inserter, resultSetRow);
                this.rowData.addRow(resultSetRow);
                this.resetInserter();
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isAfterLast() throws SQLException {
        try {
            return super.isAfterLast();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isBeforeFirst() throws SQLException {
        try {
            return super.isBeforeFirst();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isFirst() throws SQLException {
        try {
            return super.isFirst();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isLast() throws SQLException {
        try {
            return super.isLast();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    boolean isUpdatable() {
        return this.isUpdatable;
    }

    @Override
    public boolean last() throws SQLException {
        try {
            if (this.onInsertRow) {
                this.onInsertRow = false;
            }
            if (this.doingUpdates) {
                this.doingUpdates = false;
            }
            return super.last();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void moveToCurrentRow() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.isUpdatable) {
                    throw new NotUpdatable(this.notUpdatableReason);
                }
                if (this.onInsertRow) {
                    this.onInsertRow = false;
                    this.thisRow = this.savedCurrentRow;
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
    public void moveToInsertRow() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.isUpdatable) {
                    throw new NotUpdatable(this.notUpdatableReason);
                }
                if (this.inserter == null) {
                    if (this.insertSQL == null) {
                        this.generateStatements();
                    }
                    this.inserter = (ClientPreparedStatement)this.getConnection().clientPrepareStatement(this.insertSQL);
                    if (this.populateInserterWithDefaultValues) {
                        this.extractDefaultValues();
                    }
                    this.resetInserter();
                } else {
                    this.resetInserter();
                }
                Field[] fields = this.getMetadata().getFields();
                int numFields = fields.length;
                this.onInsertRow = true;
                this.doingUpdates = false;
                this.savedCurrentRow = this.thisRow;
                Object newRowData = new byte[numFields][];
                this.thisRow = new ByteArrayRow((byte[][])newRowData, this.getExceptionInterceptor());
                this.thisRow.setMetadata(this.getMetadata());
                for (int i = 0; i < numFields; ++i) {
                    if (!this.populateInserterWithDefaultValues) {
                        this.inserter.setBytesNoEscapeNoQuotes(i + 1, StringUtils.getBytes("DEFAULT"));
                        newRowData = null;
                        continue;
                    }
                    if (this.defaultColumnValue[i] != null) {
                        Field f = fields[i];
                        switch (f.getMysqlTypeId()) {
                            case 7: 
                            case 10: 
                            case 11: 
                            case 12: {
                                if (this.defaultColumnValue[i].length > 7 && this.defaultColumnValue[i][0] == 67 && this.defaultColumnValue[i][1] == 85 && this.defaultColumnValue[i][2] == 82 && this.defaultColumnValue[i][3] == 82 && this.defaultColumnValue[i][4] == 69 && this.defaultColumnValue[i][5] == 78 && this.defaultColumnValue[i][6] == 84 && this.defaultColumnValue[i][7] == 95) {
                                    this.inserter.setBytesNoEscapeNoQuotes(i + 1, this.defaultColumnValue[i]);
                                    break;
                                }
                                this.inserter.setBytes(i + 1, this.defaultColumnValue[i], false, false);
                                break;
                            }
                            default: {
                                this.inserter.setBytes(i + 1, this.defaultColumnValue[i], false, false);
                            }
                        }
                        byte[] defaultValueCopy = new byte[this.defaultColumnValue[i].length];
                        System.arraycopy(this.defaultColumnValue[i], 0, defaultValueCopy, 0, defaultValueCopy.length);
                        newRowData[i] = defaultValueCopy;
                        continue;
                    }
                    this.inserter.setNull(i + 1, MysqlType.NULL);
                    newRowData[i] = null;
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean next() throws SQLException {
        try {
            if (this.onInsertRow) {
                this.onInsertRow = false;
            }
            if (this.doingUpdates) {
                this.doingUpdates = false;
            }
            return super.next();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean prev() throws SQLException {
        return super.prev();
    }

    @Override
    public boolean previous() throws SQLException {
        try {
            if (this.onInsertRow) {
                this.onInsertRow = false;
            }
            if (this.doingUpdates) {
                this.doingUpdates = false;
            }
            return super.previous();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void realClose(boolean calledExplicitly) throws SQLException {
        try {
            if (this.isClosed) {
                return;
            }
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                SQLException sqlEx = null;
                if (this.useUsageAdvisor && this.deleter == null && this.inserter == null && this.refresher == null && this.updater == null) {
                    this.eventSink = ProfilerEventHandlerFactory.getInstance(this.session);
                    String message = Messages.getString("UpdatableResultSet.34");
                    this.eventSink.consumeEvent(new ProfilerEventImpl(0, "", this.getOwningStatement() == null ? "N/A" : this.getOwningStatement().getCurrentCatalog(), this.getConnectionId(), this.getOwningStatement() == null ? -1 : this.getOwningStatement().getId(), this.resultId, System.currentTimeMillis(), 0L, Constants.MILLIS_I18N, null, this.getPointOfOrigin(), message));
                }
                try {
                    if (this.deleter != null) {
                        this.deleter.close();
                    }
                } catch (SQLException ex) {
                    sqlEx = ex;
                }
                try {
                    if (this.inserter != null) {
                        this.inserter.close();
                    }
                } catch (SQLException ex) {
                    sqlEx = ex;
                }
                try {
                    if (this.refresher != null) {
                        this.refresher.close();
                    }
                } catch (SQLException ex) {
                    sqlEx = ex;
                }
                try {
                    if (this.updater != null) {
                        this.updater.close();
                    }
                } catch (SQLException ex) {
                    sqlEx = ex;
                }
                super.realClose(calledExplicitly);
                if (sqlEx != null) {
                    throw sqlEx;
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
    public void refreshRow() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.isUpdatable) {
                    throw new NotUpdatable(Messages.getString("NotUpdatable.0"));
                }
                if (this.onInsertRow) {
                    throw SQLError.createSQLException(Messages.getString("UpdatableResultSet.8"), this.getExceptionInterceptor());
                }
                if (this.rowData.size() == 0) {
                    throw SQLError.createSQLException(Messages.getString("UpdatableResultSet.9"), this.getExceptionInterceptor());
                }
                if (this.isBeforeFirst()) {
                    throw SQLError.createSQLException(Messages.getString("UpdatableResultSet.10"), this.getExceptionInterceptor());
                }
                if (this.isAfterLast()) {
                    throw SQLError.createSQLException(Messages.getString("UpdatableResultSet.11"), this.getExceptionInterceptor());
                }
                this.refreshRow(this.updater, this.thisRow);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void refreshRow(ClientPreparedStatement updateInsertStmt, Row rowToRefresh) throws SQLException {
        block22: {
            if (this.refresher == null) {
                if (this.refreshSQL == null) {
                    this.generateStatements();
                }
                this.refresher = ((ResultsetRow)this.thisRow).isBinaryEncoded() ? (ClientPreparedStatement)this.getConnection().serverPrepareStatement(this.refreshSQL) : (ClientPreparedStatement)this.getConnection().clientPrepareStatement(this.refreshSQL);
            }
            this.refresher.clearParameters();
            int numKeys = this.primaryKeyIndicies.size();
            if (numKeys == 1) {
                byte[] dataFrom = null;
                int index = this.primaryKeyIndicies.get(0);
                if (!this.doingUpdates && !this.onInsertRow) {
                    dataFrom = rowToRefresh.getBytes(index);
                } else {
                    dataFrom = updateInsertStmt.getBytesRepresentation(index);
                    dataFrom = updateInsertStmt.isNull(index) || dataFrom.length == 0 ? rowToRefresh.getBytes(index) : this.stripBinaryPrefix(dataFrom);
                }
                if (this.getMetadata().getFields()[index].getValueNeedsQuoting()) {
                    this.refresher.setBytesNoEscape(1, dataFrom);
                } else {
                    this.refresher.setBytesNoEscapeNoQuotes(1, dataFrom);
                }
            } else {
                for (int i = 0; i < numKeys; ++i) {
                    byte[] dataFrom = null;
                    int index = this.primaryKeyIndicies.get(i);
                    if (!this.doingUpdates && !this.onInsertRow) {
                        dataFrom = rowToRefresh.getBytes(index);
                    } else {
                        dataFrom = updateInsertStmt.getBytesRepresentation(index);
                        dataFrom = updateInsertStmt.isNull(index) || dataFrom.length == 0 ? rowToRefresh.getBytes(index) : this.stripBinaryPrefix(dataFrom);
                    }
                    this.refresher.setBytesNoEscape(i + 1, dataFrom);
                }
            }
            ResultSet rs = null;
            try {
                rs = this.refresher.executeQuery();
                int numCols = rs.getMetaData().getColumnCount();
                if (rs.next()) {
                    for (int i = 0; i < numCols; ++i) {
                        byte[] val = rs.getBytes(i + 1);
                        if (val == null || rs.wasNull()) {
                            rowToRefresh.setBytes(i, null);
                            continue;
                        }
                        rowToRefresh.setBytes(i, rs.getBytes(i + 1));
                    }
                    break block22;
                }
                throw SQLError.createSQLException(Messages.getString("UpdatableResultSet.12"), "S1000", this.getExceptionInterceptor());
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException sQLException) {}
                }
            }
        }
    }

    @Override
    public boolean relative(int rows) throws SQLException {
        try {
            return super.relative(rows);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    private void resetInserter() throws SQLException {
        this.inserter.clearParameters();
        for (int i = 0; i < this.getMetadata().getFields().length; ++i) {
            this.inserter.setNull(i + 1, 0);
        }
    }

    @Override
    public boolean rowDeleted() throws SQLException {
        try {
            throw SQLError.createSQLFeatureNotSupportedException();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean rowInserted() throws SQLException {
        try {
            throw SQLError.createSQLFeatureNotSupportedException();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean rowUpdated() throws SQLException {
        try {
            throw SQLError.createSQLFeatureNotSupportedException();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setResultSetConcurrency(int concurrencyFlag) {
        super.setResultSetConcurrency(concurrencyFlag);
    }

    private byte[] stripBinaryPrefix(byte[] dataFrom) {
        return StringUtils.stripEnclosure(dataFrom, "_binary'", "'");
    }

    protected void syncUpdate() throws SQLException {
        if (this.updater == null) {
            if (this.updateSQL == null) {
                this.generateStatements();
            }
            this.updater = (ClientPreparedStatement)this.getConnection().clientPrepareStatement(this.updateSQL);
        }
        Field[] fields = this.getMetadata().getFields();
        int numFields = fields.length;
        this.updater.clearParameters();
        for (int i = 0; i < numFields; ++i) {
            if (this.thisRow.getBytes(i) != null) {
                this.updater.setObject(i + 1, this.getObject(i + 1), fields[i].getMysqlType());
                continue;
            }
            this.updater.setNull(i + 1, 0);
        }
        int numKeys = this.primaryKeyIndicies.size();
        if (numKeys == 1) {
            int index = this.primaryKeyIndicies.get(0);
            this.setParamValue(this.updater, numFields + 1, this.thisRow, index, fields[index].getMysqlType());
        } else {
            for (int i = 0; i < numKeys; ++i) {
                int idx = this.primaryKeyIndicies.get(i);
                this.setParamValue(this.updater, numFields + i + 1, this.thisRow, idx, fields[idx].getMysqlType());
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.onInsertRow) {
                    if (!this.doingUpdates) {
                        this.doingUpdates = true;
                        this.syncUpdate();
                    }
                    this.updater.setAsciiStream(columnIndex, x, length);
                } else {
                    this.inserter.setAsciiStream(columnIndex, x, length);
                    this.thisRow.setBytes(columnIndex - 1, STREAM_DATA_MARKER);
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateAsciiStream(String columnName, InputStream x, int length) throws SQLException {
        try {
            this.updateAsciiStream(this.findColumn(columnName), x, length);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.onInsertRow) {
                    if (!this.doingUpdates) {
                        this.doingUpdates = true;
                        this.syncUpdate();
                    }
                    this.updater.setBigDecimal(columnIndex, x);
                } else {
                    this.inserter.setBigDecimal(columnIndex, x);
                    if (x == null) {
                        this.thisRow.setBytes(columnIndex - 1, null);
                    } else {
                        this.thisRow.setBytes(columnIndex - 1, StringUtils.getBytes(x.toString()));
                    }
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateBigDecimal(String columnName, BigDecimal x) throws SQLException {
        try {
            this.updateBigDecimal(this.findColumn(columnName), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.onInsertRow) {
                    if (!this.doingUpdates) {
                        this.doingUpdates = true;
                        this.syncUpdate();
                    }
                    this.updater.setBinaryStream(columnIndex, x, length);
                } else {
                    this.inserter.setBinaryStream(columnIndex, x, length);
                    if (x == null) {
                        this.thisRow.setBytes(columnIndex - 1, null);
                    } else {
                        this.thisRow.setBytes(columnIndex - 1, STREAM_DATA_MARKER);
                    }
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateBinaryStream(String columnName, InputStream x, int length) throws SQLException {
        try {
            this.updateBinaryStream(this.findColumn(columnName), x, length);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void updateBlob(int columnIndex, Blob blob) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.onInsertRow) {
                    if (!this.doingUpdates) {
                        this.doingUpdates = true;
                        this.syncUpdate();
                    }
                    this.updater.setBlob(columnIndex, blob);
                } else {
                    this.inserter.setBlob(columnIndex, blob);
                    if (blob == null) {
                        this.thisRow.setBytes(columnIndex - 1, null);
                    } else {
                        this.thisRow.setBytes(columnIndex - 1, STREAM_DATA_MARKER);
                    }
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateBlob(String columnName, Blob blob) throws SQLException {
        try {
            this.updateBlob(this.findColumn(columnName), blob);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void updateBoolean(int columnIndex, boolean x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.onInsertRow) {
                    if (!this.doingUpdates) {
                        this.doingUpdates = true;
                        this.syncUpdate();
                    }
                    this.updater.setBoolean(columnIndex, x);
                } else {
                    this.inserter.setBoolean(columnIndex, x);
                    this.thisRow.setBytes(columnIndex - 1, this.inserter.getBytesRepresentation(columnIndex - 1));
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateBoolean(String columnName, boolean x) throws SQLException {
        try {
            this.updateBoolean(this.findColumn(columnName), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void updateByte(int columnIndex, byte x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.onInsertRow) {
                    if (!this.doingUpdates) {
                        this.doingUpdates = true;
                        this.syncUpdate();
                    }
                    this.updater.setByte(columnIndex, x);
                } else {
                    this.inserter.setByte(columnIndex, x);
                    this.thisRow.setBytes(columnIndex - 1, this.inserter.getBytesRepresentation(columnIndex - 1));
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateByte(String columnName, byte x) throws SQLException {
        try {
            this.updateByte(this.findColumn(columnName), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void updateBytes(int columnIndex, byte[] x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.onInsertRow) {
                    if (!this.doingUpdates) {
                        this.doingUpdates = true;
                        this.syncUpdate();
                    }
                    this.updater.setBytes(columnIndex, x);
                } else {
                    this.inserter.setBytes(columnIndex, x);
                    this.thisRow.setBytes(columnIndex - 1, x);
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateBytes(String columnName, byte[] x) throws SQLException {
        try {
            this.updateBytes(this.findColumn(columnName), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.onInsertRow) {
                    if (!this.doingUpdates) {
                        this.doingUpdates = true;
                        this.syncUpdate();
                    }
                    this.updater.setCharacterStream(columnIndex, x, length);
                } else {
                    this.inserter.setCharacterStream(columnIndex, x, length);
                    if (x == null) {
                        this.thisRow.setBytes(columnIndex - 1, null);
                    } else {
                        this.thisRow.setBytes(columnIndex - 1, STREAM_DATA_MARKER);
                    }
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateCharacterStream(String columnName, Reader reader, int length) throws SQLException {
        try {
            this.updateCharacterStream(this.findColumn(columnName), reader, length);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void updateClob(int columnIndex, Clob clob) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (clob == null) {
                    this.updateNull(columnIndex);
                } else {
                    this.updateCharacterStream(columnIndex, clob.getCharacterStream(), (int)clob.length());
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
    public void updateDate(int columnIndex, Date x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.onInsertRow) {
                    if (!this.doingUpdates) {
                        this.doingUpdates = true;
                        this.syncUpdate();
                    }
                    this.updater.setDate(columnIndex, x);
                } else {
                    this.inserter.setDate(columnIndex, x);
                    this.thisRow.setBytes(columnIndex - 1, this.inserter.getBytesRepresentation(columnIndex - 1));
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateDate(String columnName, Date x) throws SQLException {
        try {
            this.updateDate(this.findColumn(columnName), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void updateDouble(int columnIndex, double x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.onInsertRow) {
                    if (!this.doingUpdates) {
                        this.doingUpdates = true;
                        this.syncUpdate();
                    }
                    this.updater.setDouble(columnIndex, x);
                } else {
                    this.inserter.setDouble(columnIndex, x);
                    this.thisRow.setBytes(columnIndex - 1, this.inserter.getBytesRepresentation(columnIndex - 1));
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateDouble(String columnName, double x) throws SQLException {
        try {
            this.updateDouble(this.findColumn(columnName), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void updateFloat(int columnIndex, float x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.onInsertRow) {
                    if (!this.doingUpdates) {
                        this.doingUpdates = true;
                        this.syncUpdate();
                    }
                    this.updater.setFloat(columnIndex, x);
                } else {
                    this.inserter.setFloat(columnIndex, x);
                    this.thisRow.setBytes(columnIndex - 1, this.inserter.getBytesRepresentation(columnIndex - 1));
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateFloat(String columnName, float x) throws SQLException {
        try {
            this.updateFloat(this.findColumn(columnName), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void updateInt(int columnIndex, int x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.onInsertRow) {
                    if (!this.doingUpdates) {
                        this.doingUpdates = true;
                        this.syncUpdate();
                    }
                    this.updater.setInt(columnIndex, x);
                } else {
                    this.inserter.setInt(columnIndex, x);
                    this.thisRow.setBytes(columnIndex - 1, this.inserter.getBytesRepresentation(columnIndex - 1));
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateInt(String columnName, int x) throws SQLException {
        try {
            this.updateInt(this.findColumn(columnName), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void updateLong(int columnIndex, long x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.onInsertRow) {
                    if (!this.doingUpdates) {
                        this.doingUpdates = true;
                        this.syncUpdate();
                    }
                    this.updater.setLong(columnIndex, x);
                } else {
                    this.inserter.setLong(columnIndex, x);
                    this.thisRow.setBytes(columnIndex - 1, this.inserter.getBytesRepresentation(columnIndex - 1));
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateLong(String columnName, long x) throws SQLException {
        try {
            this.updateLong(this.findColumn(columnName), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void updateNull(int columnIndex) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.onInsertRow) {
                    if (!this.doingUpdates) {
                        this.doingUpdates = true;
                        this.syncUpdate();
                    }
                    this.updater.setNull(columnIndex, 0);
                } else {
                    this.inserter.setNull(columnIndex, 0);
                    this.thisRow.setBytes(columnIndex - 1, null);
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateNull(String columnName) throws SQLException {
        try {
            this.updateNull(this.findColumn(columnName));
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateObject(int columnIndex, Object x) throws SQLException {
        try {
            this.updateObjectInternal(columnIndex, x, (Integer)null, 0);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateObject(int columnIndex, Object x, int scale) throws SQLException {
        try {
            this.updateObjectInternal(columnIndex, x, (Integer)null, scale);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    protected void updateObjectInternal(int columnIndex, Object x, Integer targetType, int scaleOrLength) throws SQLException {
        try {
            MysqlType targetMysqlType = targetType == null ? null : MysqlType.getByJdbcType(targetType);
            this.updateObjectInternal(columnIndex, x, targetMysqlType, scaleOrLength);
        } catch (FeatureNotAvailableException nae) {
            throw SQLError.createSQLFeatureNotSupportedException(Messages.getString("Statement.UnsupportedSQLType") + JDBCType.valueOf(targetType), "S1C00", this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void updateObjectInternal(int columnIndex, Object x, SQLType targetType, int scaleOrLength) throws SQLException {
        Object object = this.checkClosed().getConnectionMutex();
        synchronized (object) {
            if (!this.onInsertRow) {
                if (!this.doingUpdates) {
                    this.doingUpdates = true;
                    this.syncUpdate();
                }
                if (targetType == null) {
                    this.updater.setObject(columnIndex, x);
                } else {
                    this.updater.setObject(columnIndex, x, targetType);
                }
            } else {
                if (targetType == null) {
                    this.inserter.setObject(columnIndex, x);
                } else {
                    this.inserter.setObject(columnIndex, x, targetType);
                }
                this.thisRow.setBytes(columnIndex - 1, this.inserter.getBytesRepresentation(columnIndex - 1));
            }
        }
    }

    @Override
    public void updateObject(String columnName, Object x) throws SQLException {
        try {
            this.updateObject(this.findColumn(columnName), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateObject(String columnName, Object x, int scale) throws SQLException {
        try {
            this.updateObject(this.findColumn(columnName), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateObject(int columnIndex, Object x, SQLType targetSqlType) throws SQLException {
        try {
            this.updateObjectInternal(columnIndex, x, targetSqlType, 0);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateObject(int columnIndex, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
        try {
            this.updateObjectInternal(columnIndex, x, targetSqlType, scaleOrLength);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateObject(String columnLabel, Object x, SQLType targetSqlType) throws SQLException {
        try {
            this.updateObjectInternal(this.findColumn(columnLabel), x, targetSqlType, 0);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateObject(String columnLabel, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
        try {
            this.updateObjectInternal(this.findColumn(columnLabel), x, targetSqlType, scaleOrLength);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void updateRow() throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.isUpdatable) {
                    throw new NotUpdatable(this.notUpdatableReason);
                }
                if (this.doingUpdates) {
                    this.updater.executeUpdate();
                    this.refreshRow();
                    this.doingUpdates = false;
                } else if (this.onInsertRow) {
                    throw SQLError.createSQLException(Messages.getString("UpdatableResultSet.44"), this.getExceptionInterceptor());
                }
                this.syncUpdate();
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
    public void updateShort(int columnIndex, short x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.onInsertRow) {
                    if (!this.doingUpdates) {
                        this.doingUpdates = true;
                        this.syncUpdate();
                    }
                    this.updater.setShort(columnIndex, x);
                } else {
                    this.inserter.setShort(columnIndex, x);
                    this.thisRow.setBytes(columnIndex - 1, this.inserter.getBytesRepresentation(columnIndex - 1));
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateShort(String columnName, short x) throws SQLException {
        try {
            this.updateShort(this.findColumn(columnName), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void updateString(int columnIndex, String x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.onInsertRow) {
                    if (!this.doingUpdates) {
                        this.doingUpdates = true;
                        this.syncUpdate();
                    }
                    this.updater.setString(columnIndex, x);
                } else {
                    this.inserter.setString(columnIndex, x);
                    if (x == null) {
                        this.thisRow.setBytes(columnIndex - 1, null);
                    } else {
                        this.thisRow.setBytes(columnIndex - 1, StringUtils.getBytes(x, this.charEncoding));
                    }
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateString(String columnName, String x) throws SQLException {
        try {
            this.updateString(this.findColumn(columnName), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void updateTime(int columnIndex, Time x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.onInsertRow) {
                    if (!this.doingUpdates) {
                        this.doingUpdates = true;
                        this.syncUpdate();
                    }
                    this.updater.setTime(columnIndex, x);
                } else {
                    this.inserter.setTime(columnIndex, x);
                    this.thisRow.setBytes(columnIndex - 1, this.inserter.getBytesRepresentation(columnIndex - 1));
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateTime(String columnName, Time x) throws SQLException {
        try {
            this.updateTime(this.findColumn(columnName), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                if (!this.onInsertRow) {
                    if (!this.doingUpdates) {
                        this.doingUpdates = true;
                        this.syncUpdate();
                    }
                    this.updater.setTimestamp(columnIndex, x);
                } else {
                    this.inserter.setTimestamp(columnIndex, x);
                    this.thisRow.setBytes(columnIndex - 1, this.inserter.getBytesRepresentation(columnIndex - 1));
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateTimestamp(String columnName, Timestamp x) throws SQLException {
        try {
            this.updateTimestamp(this.findColumn(columnName), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
        try {
            if (!this.onInsertRow) {
                if (!this.doingUpdates) {
                    this.doingUpdates = true;
                    this.syncUpdate();
                }
                this.updater.setAsciiStream(columnIndex, x);
            } else {
                this.inserter.setAsciiStream(columnIndex, x);
                this.thisRow.setBytes(columnIndex - 1, STREAM_DATA_MARKER);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
        try {
            if (!this.onInsertRow) {
                if (!this.doingUpdates) {
                    this.doingUpdates = true;
                    this.syncUpdate();
                }
                this.updater.setAsciiStream(columnIndex, x, length);
            } else {
                this.inserter.setAsciiStream(columnIndex, x, length);
                this.thisRow.setBytes(columnIndex - 1, STREAM_DATA_MARKER);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
        try {
            if (!this.onInsertRow) {
                if (!this.doingUpdates) {
                    this.doingUpdates = true;
                    this.syncUpdate();
                }
                this.updater.setBinaryStream(columnIndex, x);
            } else {
                this.inserter.setBinaryStream(columnIndex, x);
                if (x == null) {
                    this.thisRow.setBytes(columnIndex - 1, null);
                } else {
                    this.thisRow.setBytes(columnIndex - 1, STREAM_DATA_MARKER);
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
        try {
            if (!this.onInsertRow) {
                if (!this.doingUpdates) {
                    this.doingUpdates = true;
                    this.syncUpdate();
                }
                this.updater.setBinaryStream(columnIndex, x, length);
            } else {
                this.inserter.setBinaryStream(columnIndex, x, length);
                if (x == null) {
                    this.thisRow.setBytes(columnIndex - 1, null);
                } else {
                    this.thisRow.setBytes(columnIndex - 1, STREAM_DATA_MARKER);
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream2) throws SQLException {
        try {
            if (!this.onInsertRow) {
                if (!this.doingUpdates) {
                    this.doingUpdates = true;
                    this.syncUpdate();
                }
                this.updater.setBlob(columnIndex, inputStream2);
            } else {
                this.inserter.setBlob(columnIndex, inputStream2);
                if (inputStream2 == null) {
                    this.thisRow.setBytes(columnIndex - 1, null);
                } else {
                    this.thisRow.setBytes(columnIndex - 1, STREAM_DATA_MARKER);
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream2, long length) throws SQLException {
        try {
            if (!this.onInsertRow) {
                if (!this.doingUpdates) {
                    this.doingUpdates = true;
                    this.syncUpdate();
                }
                this.updater.setBlob(columnIndex, inputStream2, length);
            } else {
                this.inserter.setBlob(columnIndex, inputStream2, length);
                if (inputStream2 == null) {
                    this.thisRow.setBytes(columnIndex - 1, null);
                } else {
                    this.thisRow.setBytes(columnIndex - 1, STREAM_DATA_MARKER);
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
        try {
            if (!this.onInsertRow) {
                if (!this.doingUpdates) {
                    this.doingUpdates = true;
                    this.syncUpdate();
                }
                this.updater.setCharacterStream(columnIndex, x);
            } else {
                this.inserter.setCharacterStream(columnIndex, x);
                if (x == null) {
                    this.thisRow.setBytes(columnIndex - 1, null);
                } else {
                    this.thisRow.setBytes(columnIndex - 1, STREAM_DATA_MARKER);
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        try {
            if (!this.onInsertRow) {
                if (!this.doingUpdates) {
                    this.doingUpdates = true;
                    this.syncUpdate();
                }
                this.updater.setCharacterStream(columnIndex, x, length);
            } else {
                this.inserter.setCharacterStream(columnIndex, x, length);
                if (x == null) {
                    this.thisRow.setBytes(columnIndex - 1, null);
                } else {
                    this.thisRow.setBytes(columnIndex - 1, STREAM_DATA_MARKER);
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateClob(int columnIndex, Reader reader) throws SQLException {
        try {
            this.updateCharacterStream(columnIndex, reader);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
        try {
            this.updateCharacterStream(columnIndex, reader, length);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
        try {
            String fieldEncoding = this.getMetadata().getFields()[columnIndex - 1].getEncoding();
            if (fieldEncoding == null || !fieldEncoding.equals("UTF-8")) {
                throw new SQLException(Messages.getString("ResultSet.16"));
            }
            if (!this.onInsertRow) {
                if (!this.doingUpdates) {
                    this.doingUpdates = true;
                    this.syncUpdate();
                }
                this.updater.setNCharacterStream(columnIndex, x);
            } else {
                this.inserter.setNCharacterStream(columnIndex, x);
                if (x == null) {
                    this.thisRow.setBytes(columnIndex - 1, null);
                } else {
                    this.thisRow.setBytes(columnIndex - 1, STREAM_DATA_MARKER);
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateNClob(int columnIndex, Reader reader) throws SQLException {
        try {
            String fieldEncoding = this.getMetadata().getFields()[columnIndex - 1].getEncoding();
            if (fieldEncoding == null || !fieldEncoding.equals("UTF-8")) {
                throw new SQLException(Messages.getString("ResultSet.17"));
            }
            this.updateCharacterStream(columnIndex, reader);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
        try {
            String fieldEncoding = this.getMetadata().getFields()[columnIndex - 1].getEncoding();
            if (fieldEncoding == null || !fieldEncoding.equals("UTF-8")) {
                throw new SQLException(Messages.getString("ResultSet.17"));
            }
            this.updateCharacterStream(columnIndex, reader, length);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
        try {
            this.updateString(columnIndex, ((MysqlSQLXML)xmlObject).getString());
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
        try {
            this.updateAsciiStream(this.findColumn(columnLabel), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
        try {
            this.updateAsciiStream(this.findColumn(columnLabel), x, length);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
        try {
            this.updateBinaryStream(this.findColumn(columnLabel), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
        try {
            this.updateBinaryStream(this.findColumn(columnLabel), x, length);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream2) throws SQLException {
        try {
            this.updateBlob(this.findColumn(columnLabel), inputStream2);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream2, long length) throws SQLException {
        try {
            this.updateBlob(this.findColumn(columnLabel), inputStream2, length);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
        try {
            this.updateCharacterStream(this.findColumn(columnLabel), reader);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        try {
            this.updateCharacterStream(this.findColumn(columnLabel), reader, length);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateClob(String columnLabel, Reader reader) throws SQLException {
        try {
            this.updateClob(this.findColumn(columnLabel), reader);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
        try {
            this.updateClob(this.findColumn(columnLabel), reader, length);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
        try {
            this.updateNCharacterStream(this.findColumn(columnLabel), reader);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateNClob(String columnLabel, Reader reader) throws SQLException {
        try {
            this.updateNClob(this.findColumn(columnLabel), reader);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
        try {
            this.updateNClob(this.findColumn(columnLabel), reader, length);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
        try {
            this.updateSQLXML(this.findColumn(columnLabel), xmlObject);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                String fieldEncoding = this.getMetadata().getFields()[columnIndex - 1].getEncoding();
                if (fieldEncoding == null || !fieldEncoding.equals("UTF-8")) {
                    throw new SQLException(Messages.getString("ResultSet.16"));
                }
                if (!this.onInsertRow) {
                    if (!this.doingUpdates) {
                        this.doingUpdates = true;
                        this.syncUpdate();
                    }
                    this.updater.setNCharacterStream(columnIndex, x, length);
                } else {
                    this.inserter.setNCharacterStream(columnIndex, x, length);
                    if (x == null) {
                        this.thisRow.setBytes(columnIndex - 1, null);
                    } else {
                        this.thisRow.setBytes(columnIndex - 1, STREAM_DATA_MARKER);
                    }
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        try {
            this.updateNCharacterStream(this.findColumn(columnLabel), reader, length);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void updateNClob(int columnIndex, java.sql.NClob nClob) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                String fieldEncoding = this.getMetadata().getFields()[columnIndex - 1].getEncoding();
                if (fieldEncoding == null || !fieldEncoding.equals("UTF-8")) {
                    throw new SQLException(Messages.getString("ResultSet.17"));
                }
                if (nClob == null) {
                    this.updateNull(columnIndex);
                } else {
                    this.updateNCharacterStream(columnIndex, nClob.getCharacterStream(), (long)((int)nClob.length()));
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateNClob(String columnName, java.sql.NClob nClob) throws SQLException {
        try {
            this.updateNClob(this.findColumn(columnName), nClob);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void updateNString(int columnIndex, String x) throws SQLException {
        try {
            Object object = this.checkClosed().getConnectionMutex();
            synchronized (object) {
                String fieldEncoding = this.getMetadata().getFields()[columnIndex - 1].getEncoding();
                if (fieldEncoding == null || !fieldEncoding.equals("UTF-8")) {
                    throw new SQLException(Messages.getString("ResultSet.18"));
                }
                if (!this.onInsertRow) {
                    if (!this.doingUpdates) {
                        this.doingUpdates = true;
                        this.syncUpdate();
                    }
                    this.updater.setNString(columnIndex, x);
                } else {
                    this.inserter.setNString(columnIndex, x);
                    if (x == null) {
                        this.thisRow.setBytes(columnIndex - 1, null);
                    } else {
                        this.thisRow.setBytes(columnIndex - 1, StringUtils.getBytes(x, fieldEncoding));
                    }
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void updateNString(String columnName, String x) throws SQLException {
        try {
            this.updateNString(this.findColumn(columnName), x);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getHoldability() throws SQLException {
        try {
            throw SQLError.createSQLFeatureNotSupportedException();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Reader getNCharacterStream(int columnIndex) throws SQLException {
        try {
            String fieldEncoding = this.getMetadata().getFields()[columnIndex - 1].getEncoding();
            if (fieldEncoding == null || !fieldEncoding.equals("UTF-8")) {
                throw new SQLException(Messages.getString("ResultSet.11"));
            }
            return this.getCharacterStream(columnIndex);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Reader getNCharacterStream(String columnName) throws SQLException {
        try {
            return this.getNCharacterStream(this.findColumn(columnName));
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public java.sql.NClob getNClob(int columnIndex) throws SQLException {
        try {
            String fieldEncoding = this.getMetadata().getFields()[columnIndex - 1].getEncoding();
            if (fieldEncoding == null || !fieldEncoding.equals("UTF-8")) {
                throw new SQLException("Can not call getNClob() when field's charset isn't UTF-8");
            }
            String asString2 = this.getStringForNClob(columnIndex);
            if (asString2 == null) {
                return null;
            }
            return new NClob(asString2, this.getExceptionInterceptor());
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public java.sql.NClob getNClob(String columnName) throws SQLException {
        try {
            return this.getNClob(this.findColumn(columnName));
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public String getNString(int columnIndex) throws SQLException {
        try {
            String fieldEncoding = this.getMetadata().getFields()[columnIndex - 1].getEncoding();
            if (fieldEncoding == null || !fieldEncoding.equals("UTF-8")) {
                throw new SQLException("Can not call getNString() when field's charset isn't UTF-8");
            }
            return this.getString(columnIndex);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public String getNString(String columnName) throws SQLException {
        try {
            return this.getNString(this.findColumn(columnName));
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public SQLXML getSQLXML(int columnIndex) throws SQLException {
        try {
            return new MysqlSQLXML(this, columnIndex, this.getExceptionInterceptor());
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public SQLXML getSQLXML(String columnLabel) throws SQLException {
        try {
            return this.getSQLXML(this.findColumn(columnLabel));
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    private String getStringForNClob(int columnIndex) throws SQLException {
        String asString2 = null;
        String forcedEncoding = "UTF-8";
        try {
            byte[] asBytes = null;
            asBytes = this.getBytes(columnIndex);
            if (asBytes != null) {
                asString2 = new String(asBytes, forcedEncoding);
            }
        } catch (UnsupportedEncodingException uee) {
            throw SQLError.createSQLException("Unsupported character encoding " + forcedEncoding, "S1009", this.getExceptionInterceptor());
        }
        return asString2;
    }

    @Override
    public boolean isClosed() throws SQLException {
        try {
            return this.isClosed;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        try {
            this.checkClosed();
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
                throw SQLError.createSQLException("Unable to unwrap to " + iface.toString(), "S1009", this.getExceptionInterceptor());
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }
}

