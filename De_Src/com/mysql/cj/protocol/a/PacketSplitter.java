/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a;

public class PacketSplitter {
    private int totalSize;
    private int currentPacketLen = 0;
    private int offset = 0;

    public PacketSplitter(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getPacketLen() {
        return this.currentPacketLen;
    }

    public int getOffset() {
        return this.offset;
    }

    public boolean nextPacket() {
        this.offset += this.currentPacketLen;
        if (this.currentPacketLen == 0xFFFFFF && this.offset == this.totalSize) {
            this.currentPacketLen = 0;
            return true;
        }
        if (this.totalSize == 0) {
            this.totalSize = -1;
            return true;
        }
        this.currentPacketLen = this.totalSize - this.offset;
        if (this.currentPacketLen > 0xFFFFFF) {
            this.currentPacketLen = 0xFFFFFF;
        }
        return this.offset < this.totalSize;
    }
}

