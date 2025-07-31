/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.exceptions.CJOperationNotSupportedException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.protocol.Message;
import java.io.IOException;
import java.nio.channels.CompletionHandler;

public interface MessageSender<M extends Message> {
    default public void send(byte[] message, int messageLen, byte messageSequence) throws IOException {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    default public void send(M message) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    default public void send(M message, CompletionHandler<Long, Void> callback) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    default public void setMaxAllowedPacket(int maxAllowedPacket) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    default public MessageSender<M> undecorateAll() {
        return this;
    }

    default public MessageSender<M> undecorate() {
        return this;
    }
}

