/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a;

import com.mysql.cj.protocol.MessageSender;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.protocol.a.NativeUtils;
import com.mysql.cj.protocol.a.PacketSplitter;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class SimplePacketSender
implements MessageSender<NativePacketPayload> {
    private BufferedOutputStream outputStream;

    public SimplePacketSender(BufferedOutputStream outputStream2) {
        this.outputStream = outputStream2;
    }

    @Override
    public void send(byte[] packet, int packetLen, byte packetSequence) throws IOException {
        PacketSplitter packetSplitter = new PacketSplitter(packetLen);
        while (packetSplitter.nextPacket()) {
            this.outputStream.write(NativeUtils.encodeMysqlThreeByteInteger(packetSplitter.getPacketLen()));
            byte by = packetSequence;
            packetSequence = (byte)(packetSequence + 1);
            this.outputStream.write(by);
            this.outputStream.write(packet, packetSplitter.getOffset(), packetSplitter.getPacketLen());
        }
        this.outputStream.flush();
    }

    @Override
    public MessageSender<NativePacketPayload> undecorateAll() {
        return this;
    }

    @Override
    public MessageSender<NativePacketPayload> undecorate() {
        return this;
    }
}

