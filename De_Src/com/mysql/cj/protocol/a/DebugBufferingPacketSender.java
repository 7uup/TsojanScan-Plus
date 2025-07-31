/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a;

import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.protocol.MessageSender;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.util.StringUtils;
import java.io.IOException;
import java.util.LinkedList;

public class DebugBufferingPacketSender
implements MessageSender<NativePacketPayload> {
    private MessageSender<NativePacketPayload> packetSender;
    private LinkedList<StringBuilder> packetDebugBuffer;
    private RuntimeProperty<Integer> packetDebugBufferSize;
    private int maxPacketDumpLength = 1024;
    private static final int DEBUG_MSG_LEN = 64;

    public DebugBufferingPacketSender(MessageSender<NativePacketPayload> packetSender, LinkedList<StringBuilder> packetDebugBuffer, RuntimeProperty<Integer> packetDebugBufferSize) {
        this.packetSender = packetSender;
        this.packetDebugBuffer = packetDebugBuffer;
        this.packetDebugBufferSize = packetDebugBufferSize;
    }

    public void setMaxPacketDumpLength(int maxPacketDumpLength) {
        this.maxPacketDumpLength = maxPacketDumpLength;
    }

    private void pushPacketToDebugBuffer(byte[] packet, int packetLen) {
        int bytesToDump = Math.min(this.maxPacketDumpLength, packetLen);
        String packetPayload = StringUtils.dumpAsHex(packet, bytesToDump);
        StringBuilder packetDump = new StringBuilder(68 + packetPayload.length());
        packetDump.append("Client ");
        packetDump.append(packet.toString());
        packetDump.append("--------------------> Server\n");
        packetDump.append("\nPacket payload:\n\n");
        packetDump.append(packetPayload);
        if (packetLen > this.maxPacketDumpLength) {
            packetDump.append("\nNote: Packet of " + packetLen + " bytes truncated to " + this.maxPacketDumpLength + " bytes.\n");
        }
        if (this.packetDebugBuffer.size() + 1 > this.packetDebugBufferSize.getValue()) {
            this.packetDebugBuffer.removeFirst();
        }
        this.packetDebugBuffer.addLast(packetDump);
    }

    @Override
    public void send(byte[] packet, int packetLen, byte packetSequence) throws IOException {
        this.pushPacketToDebugBuffer(packet, packetLen);
        this.packetSender.send(packet, packetLen, packetSequence);
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

