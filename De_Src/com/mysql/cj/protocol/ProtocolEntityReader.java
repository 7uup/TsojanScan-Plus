/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.exceptions.CJOperationNotSupportedException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.protocol.ProtocolEntityFactory;
import java.io.IOException;

public interface ProtocolEntityReader<T extends ProtocolEntity, M extends Message> {
    default public T read(ProtocolEntityFactory<T, M> sf) throws IOException {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not allowed");
    }

    default public T read(int maxRows, boolean streamResults, M resultPacket, ColumnDefinition metadata, ProtocolEntityFactory<T, M> protocolEntityFactory) throws IOException {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not allowed");
    }
}

