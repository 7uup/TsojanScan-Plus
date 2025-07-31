/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.x;

import com.mysql.cj.CharsetMapping;
import com.mysql.cj.MysqlType;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.x.XMessage;
import com.mysql.cj.result.Field;
import com.mysql.cj.util.LazyString;
import com.mysql.cj.x.protobuf.MysqlxResultset;
import java.io.UnsupportedEncodingException;

public class FieldFactory
implements ProtocolEntityFactory<Field, XMessage> {
    private static final int XPROTOCOL_COLUMN_BYTES_CONTENT_TYPE_GEOMETRY = 1;
    private static final int XPROTOCOL_COLUMN_BYTES_CONTENT_TYPE_JSON = 2;
    private static final int XPROTOCOL_COLUMN_FLAGS_UINT_ZEROFILL = 1;
    private static final int XPROTOCOL_COLUMN_FLAGS_DOUBLE_UNSIGNED = 1;
    private static final int XPROTOCOL_COLUMN_FLAGS_FLOAT_UNSIGNED = 1;
    private static final int XPROTOCOL_COLUMN_FLAGS_DECIMAL_UNSIGNED = 1;
    private static final int XPROTOCOL_COLUMN_FLAGS_BYTES_RIGHTPAD = 1;
    private static final int XPROTOCOL_COLUMN_FLAGS_DATETIME_TIMESTAMP = 1;
    private static final int XPROTOCOL_COLUMN_FLAGS_NOT_NULL = 16;
    private static final int XPROTOCOL_COLUMN_FLAGS_PRIMARY_KEY = 32;
    private static final int XPROTOCOL_COLUMN_FLAGS_UNIQUE_KEY = 64;
    private static final int XPROTOCOL_COLUMN_FLAGS_MULTIPLE_KEY = 128;
    private static final int XPROTOCOL_COLUMN_FLAGS_AUTO_INCREMENT = 256;
    String metadataCharacterSet;

    public FieldFactory(String metadataCharSet) {
        this.metadataCharacterSet = metadataCharSet;
    }

    @Override
    public Field createFromMessage(XMessage message) {
        return this.columnMetaDataToField((MysqlxResultset.ColumnMetaData)message.getMessage(), this.metadataCharacterSet);
    }

    private Field columnMetaDataToField(MysqlxResultset.ColumnMetaData col, String characterSet) {
        try {
            LazyString databaseName = new LazyString(col.getSchema().toString(characterSet));
            LazyString tableName = new LazyString(col.getTable().toString(characterSet));
            LazyString originalTableName = new LazyString(col.getOriginalTable().toString(characterSet));
            LazyString columnName = new LazyString(col.getName().toString(characterSet));
            LazyString originalColumnName = new LazyString(col.getOriginalName().toString(characterSet));
            long length = Integer.toUnsignedLong(col.getLength());
            int decimals = col.getFractionalDigits();
            int collationIndex = 0;
            if (col.hasCollation()) {
                collationIndex = (int)col.getCollation();
            }
            String encoding = CharsetMapping.COLLATION_INDEX_TO_COLLATION_NAME[collationIndex];
            MysqlType mysqlType = this.findMysqlType(col.getType(), col.getContentType(), col.getFlags(), collationIndex);
            int mysqlTypeId = this.xProtocolTypeToMysqlType(col.getType(), col.getContentType());
            short flags = 0;
            if (col.getType().equals(MysqlxResultset.ColumnMetaData.FieldType.UINT) && 0 < (col.getFlags() & 1)) {
                flags = (short)(flags | 0x40);
            } else if (col.getType().equals(MysqlxResultset.ColumnMetaData.FieldType.BYTES) && 0 < (col.getFlags() & 1)) {
                mysqlType = MysqlType.CHAR;
            } else if (col.getType().equals(MysqlxResultset.ColumnMetaData.FieldType.DATETIME) && 0 < (col.getFlags() & 1)) {
                mysqlType = MysqlType.TIMESTAMP;
            }
            if ((col.getFlags() & 0x10) > 0) {
                flags = (short)(flags | 1);
            }
            if ((col.getFlags() & 0x20) > 0) {
                flags = (short)(flags | 2);
            }
            if ((col.getFlags() & 0x40) > 0) {
                flags = (short)(flags | 4);
            }
            if ((col.getFlags() & 0x80) > 0) {
                flags = (short)(flags | 8);
            }
            if ((col.getFlags() & 0x100) > 0) {
                flags = (short)(flags | 0x200);
            }
            Field f = new Field(databaseName, tableName, originalTableName, columnName, originalColumnName, length, mysqlTypeId, flags, decimals, collationIndex, encoding, mysqlType);
            return f;
        } catch (UnsupportedEncodingException ex) {
            throw new WrongArgumentException("Unable to decode metadata strings", ex);
        }
    }

    private MysqlType findMysqlType(MysqlxResultset.ColumnMetaData.FieldType type, int contentType, int flags, int collationIndex) {
        switch (type) {
            case SINT: {
                return MysqlType.BIGINT;
            }
            case UINT: {
                return MysqlType.BIGINT_UNSIGNED;
            }
            case FLOAT: {
                return 0 < (flags & 1) ? MysqlType.FLOAT_UNSIGNED : MysqlType.FLOAT;
            }
            case DOUBLE: {
                return 0 < (flags & 1) ? MysqlType.DOUBLE_UNSIGNED : MysqlType.DOUBLE;
            }
            case DECIMAL: {
                return 0 < (flags & 1) ? MysqlType.DECIMAL_UNSIGNED : MysqlType.DECIMAL;
            }
            case BYTES: {
                switch (contentType) {
                    case 1: {
                        return MysqlType.GEOMETRY;
                    }
                    case 2: {
                        return MysqlType.JSON;
                    }
                }
                if (collationIndex == 33) {
                    return MysqlType.VARBINARY;
                }
                return MysqlType.VARCHAR;
            }
            case TIME: {
                return MysqlType.TIME;
            }
            case DATETIME: {
                return MysqlType.DATETIME;
            }
            case SET: {
                return MysqlType.SET;
            }
            case ENUM: {
                return MysqlType.ENUM;
            }
            case BIT: {
                return MysqlType.BIT;
            }
        }
        throw new WrongArgumentException("TODO: unknown field type: " + type);
    }

    private int xProtocolTypeToMysqlType(MysqlxResultset.ColumnMetaData.FieldType type, int contentType) {
        switch (type) {
            case SINT: {
                return 8;
            }
            case UINT: {
                return 8;
            }
            case FLOAT: {
                return 4;
            }
            case DOUBLE: {
                return 5;
            }
            case DECIMAL: {
                return 246;
            }
            case BYTES: {
                switch (contentType) {
                    case 1: {
                        return 255;
                    }
                    case 2: {
                        return 245;
                    }
                }
                return 15;
            }
            case TIME: {
                return 11;
            }
            case DATETIME: {
                return 12;
            }
            case SET: {
                return 248;
            }
            case ENUM: {
                return 247;
            }
            case BIT: {
                return 16;
            }
        }
        throw new WrongArgumentException("TODO: unknown field type: " + type);
    }
}

