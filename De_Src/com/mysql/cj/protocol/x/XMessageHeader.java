/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.x;

import com.mysql.cj.protocol.MessageHeader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class XMessageHeader
implements MessageHeader {
    private ByteBuffer headerBuf;
    private int messageType = -1;
    private int messageSize = -1;

    public XMessageHeader() {
        this.headerBuf = ByteBuffer.allocate(5).order(ByteOrder.LITTLE_ENDIAN);
    }

    public XMessageHeader(byte[] buf) {
        this.headerBuf = ByteBuffer.wrap(buf).order(ByteOrder.LITTLE_ENDIAN);
    }

    private void parseBuffer() {
        if (this.messageSize == -1) {
            this.headerBuf.position(0);
            this.messageSize = this.headerBuf.getInt() - 1;
            this.messageType = this.headerBuf.get();
        }
    }

    @Override
    public ByteBuffer getBuffer() {
        return this.headerBuf;
    }

    @Override
    public int getMessageSize() {
        this.parseBuffer();
        return this.messageSize;
    }

    @Override
    public byte getMessageSequence() {
        return 0;
    }

    public int getMessageType() {
        this.parseBuffer();
        return this.messageType;
    }
}

