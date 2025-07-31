/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.ProtocolEntityFactory;

public interface MessageListener<M extends Message>
extends ProtocolEntityFactory<Boolean, M> {
    default public void error(Throwable ex) {
        ex.printStackTrace();
    }
}

