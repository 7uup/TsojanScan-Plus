/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.exceptions.CJOperationNotSupportedException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.MessageHeader;
import com.mysql.cj.protocol.MessageListener;
import java.io.IOException;
import java.util.Optional;

public interface MessageReader<H extends MessageHeader, M extends Message> {
    public H readHeader() throws IOException;

    public M readMessage(Optional<M> var1, H var2) throws IOException;

    default public M readMessage(Optional<M> reuse, int expectedType) throws IOException {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    default public void pushMessageListener(MessageListener<M> l) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    default public byte getMessageSequence() {
        return 0;
    }

    default public void resetMessageSequence() {
    }

    default public MessageReader<H, M> undecorateAll() {
        return this;
    }

    default public MessageReader<H, M> undecorate() {
        return this;
    }

    default public void start() {
    }

    default public void stopAfterNextMessage() {
    }
}

