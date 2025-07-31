/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a;

import com.mysql.cj.protocol.MessageSender;
import com.mysql.cj.protocol.PacketSentTimeHolder;
import com.mysql.cj.protocol.a.NativePacketPayload;
import java.io.IOException;

public class TimeTrackingPacketSender
implements MessageSender<NativePacketPayload>,
PacketSentTimeHolder {
    private MessageSender<NativePacketPayload> packetSender;
    private long lastPacketSentTime = 0L;
    private long previousPacketSentTime = 0L;

    public TimeTrackingPacketSender(MessageSender<NativePacketPayload> packetSender) {
        this.packetSender = packetSender;
    }

    @Override
    public void send(byte[] packet, int packetLen, byte packetSequence) throws IOException {
        this.packetSender.send(packet, packetLen, packetSequence);
        this.previousPacketSentTime = this.lastPacketSentTime;
        this.lastPacketSentTime = System.currentTimeMillis();
    }

    @Override
    public long getLastPacketSentTime() {
        return this.lastPacketSentTime;
    }

    @Override
    public long getPreviousPacketSentTime() {
        return this.previousPacketSentTime;
    }

    @Override
    public MessageSender<NativePacketPayload> undecorateAll() {
        return this.packetSender.undecorateAll();
    }

    @Override
    public MessageSender<NativePacketPayload> undecorate() {
        return this.packetSender;
    }
}

