/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.CJOperationNotSupportedException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.protocol.ResultsetRowsOwner;
import com.mysql.cj.result.Row;
import com.mysql.cj.result.RowList;

public interface ResultsetRows
extends RowList,
ProtocolEntity {
    default public void addRow(Row row) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, Messages.getString("OperationNotSupportedException.0"));
    }

    default public void afterLast() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, Messages.getString("OperationNotSupportedException.0"));
    }

    default public void beforeFirst() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, Messages.getString("OperationNotSupportedException.0"));
    }

    default public void beforeLast() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, Messages.getString("OperationNotSupportedException.0"));
    }

    default public void close() {
    }

    public ResultsetRowsOwner getOwner();

    public boolean isAfterLast();

    public boolean isBeforeFirst();

    default public boolean isDynamic() {
        return true;
    }

    default public boolean isEmpty() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, Messages.getString("OperationNotSupportedException.0"));
    }

    default public boolean isFirst() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, Messages.getString("OperationNotSupportedException.0"));
    }

    default public boolean isLast() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, Messages.getString("OperationNotSupportedException.0"));
    }

    default public void moveRowRelative(int rows) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, Messages.getString("OperationNotSupportedException.0"));
    }

    default public void setCurrentRow(int rowNumber) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, Messages.getString("OperationNotSupportedException.0"));
    }

    public void setOwner(ResultsetRowsOwner var1);

    public boolean wasEmpty();

    public void setMetadata(ColumnDefinition var1);

    public ColumnDefinition getMetadata();
}

