/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

public interface PacketReceivedTimeHolder {
    default public long getLastPacketReceivedTime() {
        return 0L;
    }
}

