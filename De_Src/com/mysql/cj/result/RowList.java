/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.CJOperationNotSupportedException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.result.Row;
import java.util.Iterator;

public interface RowList
extends Iterator<Row> {
    public static final int RESULT_SET_SIZE_UNKNOWN = -1;

    default public Row previous() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, Messages.getString("OperationNotSupportedException.0"));
    }

    default public Row get(int n) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, Messages.getString("OperationNotSupportedException.0"));
    }

    default public int getPosition() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, Messages.getString("OperationNotSupportedException.0"));
    }

    default public int size() {
        return -1;
    }
}

