/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a;

import com.mysql.cj.Messages;
import com.mysql.cj.log.Log;
import com.mysql.cj.protocol.MessageReader;
import com.mysql.cj.protocol.a.NativePacketHeader;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.util.StringUtils;
import java.io.IOException;
import java.util.Optional;

public class TracingPacketReader
implements MessageReader<NativePacketHeader, NativePacketPayload> {
    private static final int MAX_PACKET_DUMP_LENGTH = 1024;
    private MessageReader<NativePacketHeader, NativePacketPayload> packetReader;
    private Log log;

    public TracingPacketReader(MessageReader<NativePacketHeader, NativePacketPayload> packetReader, Log log) {
        this.packetReader = packetReader;
        this.log = log;
    }

    @Override
    public NativePacketHeader readHeader() throws IOException {
        NativePacketHeader hdr = this.packetReader.readHeader();
        StringBuilder traceMessageBuf = new StringBuilder();
        traceMessageBuf.append(Messages.getString("PacketReader.3"));
        traceMessageBuf.append(hdr.getMessageSize());
        traceMessageBuf.append(Messages.getString("PacketReader.4"));
        traceMessageBuf.append(StringUtils.dumpAsHex(hdr.getBuffer().array(), 4));
        this.log.logTrace(traceMessageBuf.toString());
        return hdr;
    }

    @Override
    public NativePacketPayload readMessage(Optional<NativePacketPayload> reuse, NativePacketHeader header) throws IOException {
        int packetLength = header.getMessageSize();
        NativePacketPayload buf = this.packetReader.readMessage(reuse, header);
        StringBuilder traceMessageBuf = new StringBuilder();
        traceMessageBuf.append(Messages.getString(reuse.isPresent() ? "PacketReader.5" : "PacketReader.6"));
        traceMessageBuf.append(StringUtils.dumpAsHex(buf.getByteBuffer(), packetLength < 1024 ? packetLength : 1024));
        if (packetLength > 1024) {
            traceMessageBuf.append(Messages.getString("PacketReader.7"));
            traceMessageBuf.append(1024);
            traceMessageBuf.append(Messages.getString("PacketReader.8"));
        }
        this.log.logTrace(traceMessageBuf.toString());
        return buf;
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

