/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.result;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.DataReadException;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.ResultsetRow;
import com.mysql.cj.protocol.ValueDecoder;
import com.mysql.cj.result.Field;
import com.mysql.cj.result.Row;
import com.mysql.cj.result.ValueFactory;

public abstract class AbstractResultsetRow
implements ResultsetRow {
    protected ExceptionInterceptor exceptionInterceptor;
    protected ColumnDefinition metadata;
    protected ValueDecoder valueDecoder;
    protected boolean wasNull;

    protected AbstractResultsetRow(ExceptionInterceptor exceptionInterceptor) {
        this.exceptionInterceptor = exceptionInterceptor;
    }

    private <T> T decodeAndCreateReturnValue(int columnIndex, byte[] bytes, int offset, int length, ValueFactory<T> vf) {
        Field f = this.metadata.getFields()[columnIndex];
        switch (f.getMysqlTypeId()) {
            case 7: 
            case 12: {
                return this.valueDecoder.decodeTimestamp(bytes, offset, length, vf);
            }
            case 10: {
                return this.valueDecoder.decodeDate(bytes, offset, length, vf);
            }
            case 11: {
                return this.valueDecoder.decodeTime(bytes, offset, length, vf);
            }
            case 1: {
                if (f.isUnsigned()) {
                    return this.valueDecoder.decodeUInt1(bytes, offset, length, vf);
                }
                return this.valueDecoder.decodeInt1(bytes, offset, length, vf);
            }
            case 2: 
            case 13: {
                if (f.isUnsigned()) {
                    return this.valueDecoder.decodeUInt2(bytes, offset, length, vf);
                }
                return this.valueDecoder.decodeInt2(bytes, offset, length, vf);
            }
            case 3: {
                if (f.isUnsigned()) {
                    return this.valueDecoder.decodeUInt4(bytes, offset, length, vf);
                }
                return this.valueDecoder.decodeInt4(bytes, offset, length, vf);
            }
            case 9: {
                return this.valueDecoder.decodeInt4(bytes, offset, length, vf);
            }
            case 8: {
                if (f.isUnsigned()) {
                    return this.valueDecoder.decodeUInt8(bytes, offset, length, vf);
                }
                return this.valueDecoder.decodeInt8(bytes, offset, length, vf);
            }
            case 4: {
                return this.valueDecoder.decodeFloat(bytes, offset, length, vf);
            }
            case 5: {
                return this.valueDecoder.decodeDouble(bytes, offset, length, vf);
            }
            case 0: 
            case 246: {
                return this.valueDecoder.decodeDecimal(bytes, offset, length, vf);
            }
            case 15: 
            case 245: 
            case 247: 
            case 249: 
            case 250: 
            case 251: 
            case 252: 
            case 253: 
            case 254: 
            case 255: {
                return this.valueDecoder.decodeByteArray(bytes, offset, length, vf);
            }
            case 248: {
                return this.valueDecoder.decodeSet(bytes, offset, length, vf);
            }
            case 16: {
                return this.valueDecoder.decodeBit(bytes, offset, length, vf);
            }
            case 6: {
                return vf.createFromNull();
            }
        }
        switch (f.getMysqlType()) {
            case TINYINT: {
                return this.valueDecoder.decodeInt1(bytes, offset, length, vf);
            }
            case TINYINT_UNSIGNED: {
                return this.valueDecoder.decodeUInt1(bytes, offset, length, vf);
            }
            case SMALLINT: 
            case YEAR: {
                return this.valueDecoder.decodeInt2(bytes, offset, length, vf);
            }
            case SMALLINT_UNSIGNED: {
                return this.valueDecoder.decodeUInt2(bytes, offset, length, vf);
            }
            case INT: 
            case MEDIUMINT: {
                return this.valueDecoder.decodeInt4(bytes, offset, length, vf);
            }
            case INT_UNSIGNED: 
            case MEDIUMINT_UNSIGNED: {
                return this.valueDecoder.decodeUInt4(bytes, offset, length, vf);
            }
            case BIGINT: {
                return this.valueDecoder.decodeInt8(bytes, offset, length, vf);
            }
            case BIGINT_UNSIGNED: {
                return this.valueDecoder.decodeUInt8(bytes, offset, length, vf);
            }
            case FLOAT: 
            case FLOAT_UNSIGNED: {
                return this.valueDecoder.decodeFloat(bytes, offset, length, vf);
            }
            case DOUBLE: 
            case DOUBLE_UNSIGNED: {
                return this.valueDecoder.decodeDouble(bytes, offset, length, vf);
            }
            case DECIMAL: 
            case DECIMAL_UNSIGNED: {
                return this.valueDecoder.decodeDecimal(bytes, offset, length, vf);
            }
            case BOOLEAN: 
            case VARBINARY: 
            case VARCHAR: 
            case BINARY: 
            case CHAR: 
            case TINYBLOB: 
            case BLOB: 
            case MEDIUMBLOB: 
            case LONGBLOB: 
            case TINYTEXT: 
            case TEXT: 
            case MEDIUMTEXT: 
            case LONGTEXT: 
            case JSON: 
            case ENUM: 
            case SET: 
            case GEOMETRY: 
            case UNKNOWN: {
                return this.valueDecoder.decodeByteArray(bytes, offset, length, vf);
            }
            case BIT: {
                return this.valueDecoder.decodeBit(bytes, offset, length, vf);
            }
            case DATETIME: 
            case TIMESTAMP: {
                return this.valueDecoder.decodeTimestamp(bytes, offset, length, vf);
            }
            case DATE: {
                return this.valueDecoder.decodeDate(bytes, offset, length, vf);
            }
            case TIME: {
                return this.valueDecoder.decodeTime(bytes, offset, length, vf);
            }
            case NULL: {
                return vf.createFromNull();
            }
        }
        throw new DataReadException(Messages.getString("ResultSet.UnknownSourceType"));
    }

    protected <T> T getValueFromBytes(int columnIndex, byte[] bytes, int offset, int length, ValueFactory<T> vf) {
        if (this.getNull(columnIndex)) {
            return vf.createFromNull();
        }
        T retVal = this.decodeAndCreateReturnValue(columnIndex, bytes, offset, length, vf);
        this.wasNull = retVal == null;
        return retVal;
    }

    @Override
    public Row setMetadata(ColumnDefinition f) {
        this.metadata = f;
        return this;
    }

    @Override
    public boolean wasNull() {
        return this.wasNull;
    }
}

