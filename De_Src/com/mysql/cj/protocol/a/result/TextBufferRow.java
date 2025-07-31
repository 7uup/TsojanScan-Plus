/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a.result;

import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.ValueDecoder;
import com.mysql.cj.protocol.a.NativeConstants;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.protocol.a.result.AbstractBufferRow;
import com.mysql.cj.result.Row;
import com.mysql.cj.result.ValueFactory;

public class TextBufferRow
extends AbstractBufferRow {
    public TextBufferRow(NativePacketPayload buf, ColumnDefinition cd2, ExceptionInterceptor exceptionInterceptor, ValueDecoder valueDecoder) {
        super(exceptionInterceptor);
        this.rowFromServer = buf;
        this.homePosition = this.rowFromServer.getPosition();
        this.valueDecoder = valueDecoder;
        if (cd2.getFields() != null) {
            this.setMetadata(cd2);
        }
    }

    @Override
    protected int findAndSeekToOffset(int index) {
        if (index == 0) {
            this.lastRequestedIndex = 0;
            this.lastRequestedPos = this.homePosition;
            this.rowFromServer.setPosition(this.homePosition);
            return 0;
        }
        if (index == this.lastRequestedIndex) {
            this.rowFromServer.setPosition(this.lastRequestedPos);
            return this.lastRequestedPos;
        }
        int startingIndex = 0;
        if (index > this.lastRequestedIndex) {
            startingIndex = this.lastRequestedIndex >= 0 ? this.lastRequestedIndex : 0;
            this.rowFromServer.setPosition(this.lastRequestedPos);
        } else {
            this.rowFromServer.setPosition(this.homePosition);
        }
        for (int i = startingIndex; i < index; ++i) {
            this.rowFromServer.skipBytes(NativeConstants.StringSelfDataType.STRING_LENENC);
        }
        this.lastRequestedIndex = index;
        this.lastRequestedPos = this.rowFromServer.getPosition();
        return this.lastRequestedPos;
    }

    @Override
    public byte[] getBytes(int index) {
        if (this.getNull(index)) {
            return null;
        }
        this.findAndSeekToOffset(index);
        return this.rowFromServer.readBytes(NativeConstants.StringSelfDataType.STRING_LENENC);
    }

    @Override
    public boolean getNull(int columnIndex) {
        this.findAndSeekToOffset(columnIndex);
        this.wasNull = this.rowFromServer.readInteger(NativeConstants.IntegerDataType.INT_LENENC) == -1L;
        return this.wasNull;
    }

    @Override
    public Row setMetadata(ColumnDefinition f) {
        super.setMetadata(f);
        return this;
    }

    @Override
    public <T> T getValue(int columnIndex, ValueFactory<T> vf) {
        this.findAndSeekToOffset(columnIndex);
        int length = (int)this.rowFromServer.readInteger(NativeConstants.IntegerDataType.INT_LENENC);
        return this.getValueFromBytes(columnIndex, this.rowFromServer.getByteBuffer(), this.rowFromServer.getPosition(), length, vf);
    }
}

