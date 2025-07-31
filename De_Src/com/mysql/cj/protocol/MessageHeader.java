/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import java.nio.ByteBuffer;

public interface MessageHeader {
    public ByteBuffer getBuffer();

    public int getMessageSize();

    public byte getMessageSequence();
}

