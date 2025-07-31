/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a;

import com.mysql.cj.protocol.MessageHeader;
import java.nio.ByteBuffer;

public class NativePacketHeader
implements MessageHeader {
    protected ByteBuffer packetHeaderBuf;

    public NativePacketHeader() {
        this.packetHeaderBuf = ByteBuffer.allocate(4);
    }

    public NativePacketHeader(byte[] buf) {
        this.packetHeaderBuf = ByteBuffer.wrap(buf);
    }

    @Override
    public ByteBuffer getBuffer() {
        return this.packetHeaderBuf;
    }

    @Override
    public int getMessageSize() {
        return (this.packetHeaderBuf.array()[0] & 0xFF) + ((this.packetHeaderBuf.array()[1] & 0xFF) << 8) + ((this.packetHeaderBuf.array()[2] & 0xFF) << 16);
    }

    @Override
    public byte getMessageSequence() {
        return this.packetHeaderBuf.array()[3];
    }
}

