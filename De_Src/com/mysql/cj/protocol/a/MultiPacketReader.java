/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a;

import com.mysql.cj.Messages;
import com.mysql.cj.protocol.MessageReader;
import com.mysql.cj.protocol.a.NativeConstants;
import com.mysql.cj.protocol.a.NativePacketHeader;
import com.mysql.cj.protocol.a.NativePacketPayload;
import java.io.IOException;
import java.util.Optional;

public class MultiPacketReader
implements MessageReader<NativePacketHeader, NativePacketPayload> {
    private MessageReader<NativePacketHeader, NativePacketPayload> packetReader;

    public MultiPacketReader(MessageReader<NativePacketHeader, NativePacketPayload> packetReader) {
        this.packetReader = packetReader;
    }

    @Override
    public NativePacketHeader readHeader() throws IOException {
        return this.packetReader.readHeader();
    }

    @Override
    public NativePacketPayload readMessage(Optional<NativePacketPayload> reuse, NativePacketHeader header) throws IOException {
        int packetLength = header.getMessageSize();
        NativePacketPayload buf = this.packetReader.readMessage(reuse, header);
        if (packetLength == 0xFFFFFF) {
            buf.setPosition(0xFFFFFF);
            NativePacketPayload multiPacket = null;
            int multiPacketLength = -1;
            byte multiPacketSeq = this.getMessageSequence();
            do {
                NativePacketHeader hdr = this.readHeader();
                multiPacketLength = hdr.getMessageSize();
                if (multiPacket == null) {
                    multiPacket = new NativePacketPayload(multiPacketLength);
                }
                if ((multiPacketSeq = (byte)(multiPacketSeq + 1)) != hdr.getMessageSequence()) {
                    throw new IOException(Messages.getString("PacketReader.10"));
                }
                this.packetReader.readMessage(Optional.of(multiPacket), hdr);
                buf.writeBytes(NativeConstants.StringLengthDataType.STRING_FIXED, multiPacket.getByteBuffer(), 0, multiPacketLength);
            } while (multiPacketLength == 0xFFFFFF);
            buf.setPosition(0);
        }
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

