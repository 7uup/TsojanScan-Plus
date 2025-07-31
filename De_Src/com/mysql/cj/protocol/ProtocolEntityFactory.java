/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.exceptions.CJOperationNotSupportedException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.protocol.Resultset;

public interface ProtocolEntityFactory<T, M extends Message> {
    default public T createFromMessage(M message) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not allowed");
    }

    default public Resultset.Type getResultSetType() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not allowed");
    }

    default public Resultset.Concurrency getResultSetConcurrency() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not allowed");
    }

    default public int getFetchSize() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not allowed");
    }

    default public T createFromProtocolEntity(ProtocolEntity protocolEntity) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not allowed");
    }
}

