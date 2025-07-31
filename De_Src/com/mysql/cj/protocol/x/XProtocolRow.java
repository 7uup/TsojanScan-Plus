/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.x;

import com.google.protobuf.ByteString;
import com.mysql.cj.exceptions.DataReadException;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.x.XProtocolDecoder;
import com.mysql.cj.result.Field;
import com.mysql.cj.result.Row;
import com.mysql.cj.result.ValueFactory;
import com.mysql.cj.x.protobuf.MysqlxResultset;

public class XProtocolRow
implements Row {
    private ColumnDefinition metadata;
    private MysqlxResultset.Row rowMessage;
    private boolean wasNull = false;

    public XProtocolRow(ColumnDefinition metadata, MysqlxResultset.Row rowMessage) {
        this.metadata = metadata;
        this.rowMessage = rowMessage;
    }

    @Override
    public <T> T getValue(int columnIndex, ValueFactory<T> vf) {
        if (columnIndex >= this.metadata.getFields().length) {
            throw new DataReadException("Invalid column");
        }
        Field f = this.metadata.getFields()[columnIndex];
        ByteString byteString = this.rowMessage.getField(columnIndex);
        if (byteString.size() == 0) {
            T result = vf.createFromNull();
            this.wasNull = result == null;
            return result;
        }
        switch (f.getMysqlTypeId()) {
            case 16: {
                this.wasNull = false;
                return XProtocolDecoder.instance.decodeBit(byteString.toByteArray(), 0, byteString.size(), vf);
            }
            case 12: {
                this.wasNull = false;
                return XProtocolDecoder.instance.decodeTimestamp(byteString.toByteArray(), 0, byteString.size(), vf);
            }
            case 5: {
                this.wasNull = false;
                return XProtocolDecoder.instance.decodeDouble(byteString.toByteArray(), 0, byteString.size(), vf);
            }
            case 247: {
                this.wasNull = false;
                return XProtocolDecoder.instance.decodeByteArray(byteString.toByteArray(), 0, byteString.size(), vf);
            }
            case 4: {
                this.wasNull = false;
                return XProtocolDecoder.instance.decodeFloat(byteString.toByteArray(), 0, byteString.size(), vf);
            }
            case 245: {
                this.wasNull = false;
                return XProtocolDecoder.instance.decodeByteArray(byteString.toByteArray(), 0, byteString.size(), vf);
            }
            case 8: {
                this.wasNull = false;
                if (f.isUnsigned()) {
                    return XProtocolDecoder.instance.decodeUInt8(byteString.toByteArray(), 0, byteString.size(), vf);
                }
                return XProtocolDecoder.instance.decodeInt8(byteString.toByteArray(), 0, byteString.size(), vf);
            }
            case 246: {
                this.wasNull = false;
                return XProtocolDecoder.instance.decodeDecimal(byteString.toByteArray(), 0, byteString.size(), vf);
            }
            case 248: {
                this.wasNull = false;
                return XProtocolDecoder.instance.decodeSet(byteString.toByteArray(), 0, byteString.size(), vf);
            }
            case 11: {
                this.wasNull = false;
                return XProtocolDecoder.instance.decodeTime(byteString.toByteArray(), 0, byteString.size(), vf);
            }
            case 15: {
                this.wasNull = false;
                return XProtocolDecoder.instance.decodeByteArray(byteString.toByteArray(), 0, byteString.size(), vf);
            }
            case 253: {
                this.wasNull = false;
                return XProtocolDecoder.instance.decodeByteArray(byteString.toByteArray(), 0, byteString.size(), vf);
            }
        }
        throw new DataReadException("Unknown MySQL type constant: " + f.getMysqlTypeId());
    }

    @Override
    public boolean getNull(int columnIndex) {
        ByteString byteString = this.rowMessage.getField(columnIndex);
        this.wasNull = byteString.size() == 0;
        return this.wasNull;
    }

    @Override
    public boolean wasNull() {
        return this.wasNull;
    }
}

