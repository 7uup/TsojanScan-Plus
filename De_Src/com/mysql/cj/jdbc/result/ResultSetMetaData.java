/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.result;

import com.mysql.cj.CharsetMapping;
import com.mysql.cj.Messages;
import com.mysql.cj.MysqlType;
import com.mysql.cj.NativeSession;
import com.mysql.cj.Session;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import com.mysql.cj.result.Field;
import java.sql.SQLException;

public class ResultSetMetaData
implements java.sql.ResultSetMetaData {
    private Session session;
    private Field[] fields;
    boolean useOldAliasBehavior = false;
    boolean treatYearAsDate = true;
    private ExceptionInterceptor exceptionInterceptor;

    private static int clampedGetLength(Field f) {
        long fieldLength = f.getLength();
        if (fieldLength > Integer.MAX_VALUE) {
            fieldLength = Integer.MAX_VALUE;
        }
        return (int)fieldLength;
    }

    public ResultSetMetaData(Session session, Field[] fields, boolean useOldAliasBehavior, boolean treatYearAsDate, ExceptionInterceptor exceptionInterceptor) {
        this.session = session;
        this.fields = fields;
        this.useOldAliasBehavior = useOldAliasBehavior;
        this.treatYearAsDate = treatYearAsDate;
        this.exceptionInterceptor = exceptionInterceptor;
    }

    @Override
    public String getCatalogName(int column) throws SQLException {
        try {
            Field f = this.getField(column);
            String database = f.getDatabaseName();
            return database == null ? "" : database;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    public String getColumnCharacterEncoding(int column) throws SQLException {
        return this.getField(column).getEncoding();
    }

    public String getColumnCharacterSet(int column) throws SQLException {
        int index = this.getField(column).getCollationIndex();
        String charsetName = null;
        if (((NativeSession)this.session).getProtocol().getServerSession().indexToCustomMysqlCharset != null) {
            charsetName = ((NativeSession)this.session).getProtocol().getServerSession().indexToCustomMysqlCharset.get(index);
        }
        if (charsetName == null) {
            charsetName = CharsetMapping.getMysqlCharsetNameForCollationIndex(index);
        }
        return charsetName;
    }

    @Override
    public String getColumnClassName(int column) throws SQLException {
        try {
            Field f = this.getField(column);
            switch (f.getMysqlType()) {
                case YEAR: {
                    if (!this.treatYearAsDate) {
                        return Short.class.getName();
                    }
                    return f.getMysqlType().getClassName();
                }
            }
            return f.getMysqlType().getClassName();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public int getColumnCount() throws SQLException {
        try {
            return this.fields.length;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public int getColumnDisplaySize(int column) throws SQLException {
        try {
            Field f = this.getField(column);
            int lengthInBytes = ResultSetMetaData.clampedGetLength(f);
            return lengthInBytes / this.session.getServerSession().getMaxBytesPerChar(f.getCollationIndex(), f.getEncoding());
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public String getColumnLabel(int column) throws SQLException {
        try {
            if (this.useOldAliasBehavior) {
                return this.getColumnName(column);
            }
            return this.getField(column).getColumnLabel();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public String getColumnName(int column) throws SQLException {
        try {
            if (this.useOldAliasBehavior) {
                return this.getField(column).getName();
            }
            String name = this.getField(column).getOriginalName();
            if (name == null) {
                return this.getField(column).getName();
            }
            return name;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public int getColumnType(int column) throws SQLException {
        try {
            return this.getField(column).getJavaType();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public String getColumnTypeName(int column) throws SQLException {
        try {
            Field field = this.getField(column);
            return field.getMysqlType().getName();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    protected Field getField(int columnIndex) throws SQLException {
        if (columnIndex < 1 || columnIndex > this.fields.length) {
            throw SQLError.createSQLException(Messages.getString("ResultSetMetaData.46"), "S1002", this.exceptionInterceptor);
        }
        return this.fields[columnIndex - 1];
    }

    @Override
    public int getPrecision(int column) throws SQLException {
        try {
            Field f = this.getField(column);
            if (f.getMysqlType().isDecimal()) {
                if (f.getDecimals() > 0) {
                    return ResultSetMetaData.clampedGetLength(f) - 1 + this.getPrecisionAdjustFactor(f);
                }
                return ResultSetMetaData.clampedGetLength(f) + this.getPrecisionAdjustFactor(f);
            }
            switch (f.getMysqlType()) {
                case TINYBLOB: 
                case BLOB: 
                case MEDIUMBLOB: 
                case LONGBLOB: {
                    return ResultSetMetaData.clampedGetLength(f);
                }
            }
            return ResultSetMetaData.clampedGetLength(f) / this.session.getServerSession().getMaxBytesPerChar(f.getCollationIndex(), f.getEncoding());
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    public int getPrecisionAdjustFactor(Field f) {
        if (!f.isUnsigned()) {
            switch (f.getMysqlTypeId()) {
                case 0: 
                case 246: {
                    return -1;
                }
                case 4: 
                case 5: {
                    return 1;
                }
            }
        } else {
            switch (f.getMysqlTypeId()) {
                case 4: 
                case 5: {
                    return 1;
                }
            }
        }
        return 0;
    }

    @Override
    public int getScale(int column) throws SQLException {
        try {
            Field f = this.getField(column);
            if (f.getMysqlType().isDecimal()) {
                return f.getDecimals();
            }
            return 0;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public String getSchemaName(int column) throws SQLException {
        try {
            return "";
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public String getTableName(int column) throws SQLException {
        try {
            if (this.useOldAliasBehavior) {
                return this.getField(column).getTableName();
            }
            return this.getField(column).getOriginalTableName();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public boolean isAutoIncrement(int column) throws SQLException {
        try {
            Field f = this.getField(column);
            return f.isAutoIncrement();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public boolean isCaseSensitive(int column) throws SQLException {
        try {
            Field field = this.getField(column);
            switch (field.getMysqlType()) {
                case YEAR: 
                case BIT: 
                case TINYINT: 
                case TINYINT_UNSIGNED: 
                case SMALLINT: 
                case SMALLINT_UNSIGNED: 
                case INT: 
                case INT_UNSIGNED: 
                case MEDIUMINT: 
                case MEDIUMINT_UNSIGNED: 
                case BIGINT: 
                case BIGINT_UNSIGNED: 
                case FLOAT: 
                case FLOAT_UNSIGNED: 
                case DOUBLE: 
                case DOUBLE_UNSIGNED: 
                case DATE: 
                case TIME: 
                case TIMESTAMP: 
                case DATETIME: {
                    return false;
                }
                case CHAR: 
                case VARCHAR: 
                case TINYTEXT: 
                case TEXT: 
                case MEDIUMTEXT: 
                case LONGTEXT: 
                case JSON: 
                case ENUM: 
                case SET: {
                    String collationName = CharsetMapping.COLLATION_INDEX_TO_COLLATION_NAME[field.getCollationIndex()];
                    return collationName != null && !collationName.endsWith("_ci");
                }
            }
            return true;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public boolean isCurrency(int column) throws SQLException {
        try {
            return false;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public boolean isDefinitelyWritable(int column) throws SQLException {
        try {
            return this.isWritable(column);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public int isNullable(int column) throws SQLException {
        try {
            if (!this.getField(column).isNotNull()) {
                return 1;
            }
            return 0;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public boolean isReadOnly(int column) throws SQLException {
        try {
            return this.getField(column).isReadOnly();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public boolean isSearchable(int column) throws SQLException {
        try {
            return true;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public boolean isSigned(int column) throws SQLException {
        try {
            return MysqlType.isSigned(this.getField(column).getMysqlType());
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public boolean isWritable(int column) throws SQLException {
        try {
            return !this.isReadOnly(column);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    public String toString() {
        StringBuilder toStringBuf = new StringBuilder();
        toStringBuf.append(super.toString());
        toStringBuf.append(" - Field level information: ");
        for (int i = 0; i < this.fields.length; ++i) {
            toStringBuf.append("\n\t");
            toStringBuf.append(this.fields[i].toString());
        }
        return toStringBuf.toString();
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        try {
            return iface.isInstance(this);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        try {
            try {
                return iface.cast(this);
            } catch (ClassCastException cce) {
                throw SQLError.createSQLException(Messages.getString("Common.UnableToUnwrap", new Object[]{iface.toString()}), "S1009", this.exceptionInterceptor);
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    public Field[] getFields() {
        return this.fields;
    }
}

