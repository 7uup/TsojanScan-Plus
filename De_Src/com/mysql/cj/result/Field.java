/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.CharsetMapping;
import com.mysql.cj.MysqlType;
import com.mysql.cj.ServerVersion;
import com.mysql.cj.util.LazyString;

public class Field {
    private int collationIndex = 0;
    private String encoding = "US-ASCII";
    private int colDecimals;
    private short colFlag;
    private LazyString databaseName = null;
    private LazyString tableName = null;
    private LazyString originalTableName = null;
    private LazyString columnName = null;
    private LazyString originalColumnName = null;
    private String fullName = null;
    private long length;
    private int mysqlTypeId = -1;
    private MysqlType mysqlType = MysqlType.UNKNOWN;

    public Field(LazyString databaseName, LazyString tableName, LazyString originalTableName, LazyString columnName, LazyString originalColumnName, long length, int mysqlTypeId, short colFlag, int colDecimals, int collationIndex, String encoding, MysqlType mysqlType) {
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.originalTableName = originalTableName;
        this.columnName = columnName;
        this.originalColumnName = originalColumnName;
        this.length = length;
        this.colFlag = colFlag;
        this.colDecimals = colDecimals;
        this.mysqlTypeId = mysqlTypeId;
        this.collationIndex = collationIndex;
        String string = this.encoding = "UnicodeBig".equals(encoding) ? "UTF-16" : encoding;
        if (mysqlType == MysqlType.JSON) {
            this.encoding = "UTF-8";
        }
        this.mysqlType = mysqlType;
        this.adjustFlagsByMysqlType();
    }

    private void adjustFlagsByMysqlType() {
        switch (this.mysqlType) {
            case BIT: {
                if (this.length <= 1L) break;
                this.colFlag = (short)(this.colFlag | 0x80);
                this.colFlag = (short)(this.colFlag | 0x10);
                break;
            }
            case BINARY: 
            case VARBINARY: {
                this.colFlag = (short)(this.colFlag | 0x80);
                this.colFlag = (short)(this.colFlag | 0x10);
                break;
            }
            case DECIMAL_UNSIGNED: 
            case TINYINT_UNSIGNED: 
            case SMALLINT_UNSIGNED: 
            case INT_UNSIGNED: 
            case FLOAT_UNSIGNED: 
            case DOUBLE_UNSIGNED: 
            case BIGINT_UNSIGNED: 
            case MEDIUMINT_UNSIGNED: {
                this.colFlag = (short)(this.colFlag | 0x20);
                break;
            }
        }
    }

    public Field(String tableName, String columnName, int collationIndex, String encoding, MysqlType mysqlType, int length) {
        this.databaseName = new LazyString(null);
        this.tableName = new LazyString(tableName);
        this.originalTableName = new LazyString(null);
        this.columnName = new LazyString(columnName);
        this.originalColumnName = new LazyString(null);
        this.length = length;
        this.mysqlType = mysqlType;
        this.colFlag = 0;
        this.colDecimals = 0;
        this.adjustFlagsByMysqlType();
        switch (mysqlType) {
            case CHAR: 
            case VARCHAR: 
            case TINYTEXT: 
            case TEXT: 
            case MEDIUMTEXT: 
            case LONGTEXT: 
            case JSON: {
                this.collationIndex = collationIndex;
                this.encoding = "UnicodeBig".equals(encoding) ? "UTF-16" : encoding;
                break;
            }
        }
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void setEncoding(String javaEncodingName, ServerVersion version) {
        this.encoding = javaEncodingName;
        this.collationIndex = CharsetMapping.getCollationIndexForJavaEncoding(javaEncodingName, version);
    }

    public String getColumnLabel() {
        return this.getName();
    }

    public String getDatabaseName() {
        return this.databaseName.toString();
    }

    public int getDecimals() {
        return this.colDecimals;
    }

    public String getFullName() {
        if (this.fullName == null) {
            StringBuilder fullNameBuf = new StringBuilder(this.tableName.length() + 1 + this.columnName.length());
            fullNameBuf.append(this.tableName.toString());
            fullNameBuf.append('.');
            fullNameBuf.append(this.columnName.toString());
            this.fullName = fullNameBuf.toString();
        }
        return this.fullName;
    }

    public long getLength() {
        return this.length;
    }

    public int getMysqlTypeId() {
        return this.mysqlTypeId;
    }

    public void setMysqlTypeId(int id) {
        this.mysqlTypeId = id;
    }

    public String getName() {
        return this.columnName.toString();
    }

    public String getOriginalName() {
        return this.originalColumnName.toString();
    }

    public String getOriginalTableName() {
        return this.originalTableName.toString();
    }

    public int getJavaType() {
        return this.mysqlType.getJdbcType();
    }

    public String getTableName() {
        return this.tableName.toString();
    }

    public boolean isAutoIncrement() {
        return (this.colFlag & 0x200) > 0;
    }

    public boolean isBinary() {
        return (this.colFlag & 0x80) > 0;
    }

    public void setBinary() {
        this.colFlag = (short)(this.colFlag | 0x80);
    }

    public boolean isBlob() {
        return (this.colFlag & 0x10) > 0;
    }

    public void setBlob() {
        this.colFlag = (short)(this.colFlag | 0x10);
    }

    public boolean isMultipleKey() {
        return (this.colFlag & 8) > 0;
    }

    public boolean isNotNull() {
        return (this.colFlag & 1) > 0;
    }

    public boolean isPrimaryKey() {
        return (this.colFlag & 2) > 0;
    }

    public boolean isFromFunction() {
        return this.originalTableName.length() == 0;
    }

    public boolean isReadOnly() {
        return this.originalColumnName.length() == 0 && this.originalTableName.length() == 0;
    }

    public boolean isUniqueKey() {
        return (this.colFlag & 4) > 0;
    }

    public boolean isUnsigned() {
        return (this.colFlag & 0x20) > 0;
    }

    public boolean isZeroFill() {
        return (this.colFlag & 0x40) > 0;
    }

    public String toString() {
        try {
            StringBuilder asString2 = new StringBuilder();
            asString2.append(super.toString());
            asString2.append("[");
            asString2.append("catalog=");
            asString2.append(this.getDatabaseName());
            asString2.append(",tableName=");
            asString2.append(this.getTableName());
            asString2.append(",originalTableName=");
            asString2.append(this.getOriginalTableName());
            asString2.append(",columnName=");
            asString2.append(this.getName());
            asString2.append(",originalColumnName=");
            asString2.append(this.getOriginalName());
            asString2.append(",mysqlType=");
            asString2.append(this.getMysqlTypeId());
            asString2.append("(");
            MysqlType ft = this.getMysqlType();
            if (ft.equals(MysqlType.UNKNOWN)) {
                asString2.append(" Unknown MySQL Type # ");
                asString2.append(this.getMysqlTypeId());
            } else {
                asString2.append("FIELD_TYPE_");
                asString2.append(ft.getName());
            }
            asString2.append(")");
            asString2.append(",sqlType=");
            asString2.append(ft.getJdbcType());
            asString2.append(",flags=");
            if (this.isAutoIncrement()) {
                asString2.append(" AUTO_INCREMENT");
            }
            if (this.isPrimaryKey()) {
                asString2.append(" PRIMARY_KEY");
            }
            if (this.isUniqueKey()) {
                asString2.append(" UNIQUE_KEY");
            }
            if (this.isBinary()) {
                asString2.append(" BINARY");
            }
            if (this.isBlob()) {
                asString2.append(" BLOB");
            }
            if (this.isMultipleKey()) {
                asString2.append(" MULTI_KEY");
            }
            if (this.isUnsigned()) {
                asString2.append(" UNSIGNED");
            }
            if (this.isZeroFill()) {
                asString2.append(" ZEROFILL");
            }
            asString2.append(", charsetIndex=");
            asString2.append(this.collationIndex);
            asString2.append(", charsetName=");
            asString2.append(this.encoding);
            asString2.append("]");
            return asString2.toString();
        } catch (Throwable t) {
            return super.toString() + "[<unable to generate contents>]";
        }
    }

    public boolean isSingleBit() {
        return this.length <= 1L;
    }

    public boolean getValueNeedsQuoting() {
        switch (this.mysqlType) {
            case BIT: 
            case DECIMAL_UNSIGNED: 
            case TINYINT_UNSIGNED: 
            case SMALLINT_UNSIGNED: 
            case INT_UNSIGNED: 
            case FLOAT_UNSIGNED: 
            case DOUBLE_UNSIGNED: 
            case BIGINT_UNSIGNED: 
            case MEDIUMINT_UNSIGNED: 
            case BIGINT: 
            case DECIMAL: 
            case DOUBLE: 
            case INT: 
            case MEDIUMINT: 
            case FLOAT: 
            case SMALLINT: 
            case TINYINT: {
                return false;
            }
        }
        return true;
    }

    public int getCollationIndex() {
        return this.collationIndex;
    }

    public MysqlType getMysqlType() {
        return this.mysqlType;
    }

    public void setMysqlType(MysqlType mysqlType) {
        this.mysqlType = mysqlType;
    }

    public short getFlags() {
        return this.colFlag;
    }

    public void setFlags(short colFlag) {
        this.colFlag = colFlag;
    }
}

