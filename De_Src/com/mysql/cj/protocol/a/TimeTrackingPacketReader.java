/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a;

import com.mysql.cj.protocol.MessageReader;
import com.mysql.cj.protocol.PacketReceivedTimeHolder;
import com.mysql.cj.protocol.a.NativePacketHeader;
import com.mysql.cj.protocol.a.NativePacketPayload;
import java.io.IOException;
import java.util.Optional;

public class TimeTrackingPacketReader
implements MessageReader<NativePacketHeader, NativePacketPayload>,
PacketReceivedTimeHolder {
    private MessageReader<NativePacketHeader, NativePacketPayload> packetReader;
    private long lastPacketReceivedTimeMs = 0L;

    public TimeTrackingPacketReader(MessageReader<NativePacketHeader, NativePacketPayload> messageReader) {
        this.packetReader = messageReader;
    }

    @Override
    public NativePacketHeader readHeader() throws IOException {
        return this.packetReader.readHeader();
    }

    @Override
    public NativePacketPayload readMessage(Optional<NativePacketPayload> reuse, NativePacketHeader header) throws IOException {
        NativePacketPayload buf = this.packetReader.readMessage(reuse, header);
        this.lastPacketReceivedTimeMs = System.currentTimeMillis();
        return buf;
    }

    @Override
    public long getLastPacketReceivedTime() {
        return this.lastPacketReceivedTimeMs;
    }

    @Override
    public byte getMessageSequence() {
        return this.packetReader.getMessageSequence();
    }

    @Override
    public void resetMessageSequence() {
        this.packetReader.resetMessageSequence();
    }

    @Override
    public MessageReader<NativePacketHeader, NativePacketPayload> undecorateAll() {
        return this.packetReader.undecorateAll();
    }

    @Override
    public MessageReader<NativePacketHeader, NativePacketPayload> undecorate() {
        return this.packetReader;
    }
}

