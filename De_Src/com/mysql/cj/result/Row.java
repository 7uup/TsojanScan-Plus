/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.CJOperationNotSupportedException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.result.ValueFactory;

public interface Row
extends ProtocolEntity {
    public <T> T getValue(int var1, ValueFactory<T> var2);

    default public Row setMetadata(ColumnDefinition columnDefinition) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, Messages.getString("OperationNotSupportedException.0"));
    }

    default public byte[] getBytes(int columnIndex) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, Messages.getString("OperationNotSupportedException.0"));
    }

    default public void setBytes(int columnIndex, byte[] value) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, Messages.getString("OperationNotSupportedException.0"));
    }

    public boolean getNull(int var1);

    public boolean wasNull();
}

