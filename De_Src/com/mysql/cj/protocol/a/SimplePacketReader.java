/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a;

import com.mysql.cj.Messages;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.CJPacketTooBigException;
import com.mysql.cj.protocol.MessageReader;
import com.mysql.cj.protocol.SocketConnection;
import com.mysql.cj.protocol.a.NativePacketHeader;
import com.mysql.cj.protocol.a.NativePacketPayload;
import java.io.IOException;
import java.util.Optional;

public class SimplePacketReader
implements MessageReader<NativePacketHeader, NativePacketPayload> {
    protected SocketConnection socketConnection;
    protected RuntimeProperty<Integer> maxAllowedPacket;
    private byte readPacketSequence = (byte)-1;

    public SimplePacketReader(SocketConnection socketConnection, RuntimeProperty<Integer> maxAllowedPacket) {
        this.socketConnection = socketConnection;
        this.maxAllowedPacket = maxAllowedPacket;
    }

    @Override
    public NativePacketHeader readHeader() throws IOException {
        NativePacketHeader hdr = new NativePacketHeader();
        try {
            this.socketConnection.getMysqlInput().readFully(hdr.getBuffer().array(), 0, 4);
            int packetLength = hdr.getMessageSize();
            if (packetLength > this.maxAllowedPacket.getValue()) {
                throw new CJPacketTooBigException(packetLength, this.maxAllowedPacket.getValue().intValue());
            }
        } catch (CJPacketTooBigException | IOException e) {
            try {
                this.socketConnection.forceClose();
            } catch (Exception exception) {
                // empty catch block
            }
            throw e;
        }
        this.readPacketSequence = hdr.getMessageSequence();
        return hdr;
    }

    @Override
    public NativePacketPayload readMessage(Optional<NativePacketPayload> reuse, NativePacketHeader header) throws IOException {
        try {
            NativePacketPayload buf;
            int packetLength = header.getMessageSize();
            if (reuse.isPresent()) {
                buf = reuse.get();
                buf.setPosition(0);
                if (buf.getByteBuffer().length < packetLength) {
                    buf.setByteBuffer(new byte[packetLength]);
                }
                buf.setPayloadLength(packetLength);
            } else {
                buf = new NativePacketPayload(new byte[packetLength]);
            }
            int numBytesRead = this.socketConnection.getMysqlInput().readFully(buf.getByteBuffer(), 0, packetLength);
            if (numBytesRead != packetLength) {
                throw new IOException(Messages.getString("PacketReader.1", new Object[]{packetLength, numBytesRead}));
            }
            return buf;
        } catch (IOException e) {
            try {
                this.socketConnection.forceClose();
            } catch (Exception exception) {
                // empty catch block
            }
            throw e;
        }
    }

    @Override
    public byte getMessageSequence() {
        return this.readPacketSequence;
    }

    @Override
    public void resetMessageSequence() {
        this.readPacketSequence = 0;
    }
}

