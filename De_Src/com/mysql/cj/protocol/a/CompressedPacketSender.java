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
import java.util.zip.Deflater;

public class CompressedPacketSender
implements MessageSender<NativePacketPayload> {
    private BufferedOutputStream outputStream;
    private Deflater deflater = new Deflater();
    private byte[] compressedPacket;
    private byte compressedSequenceId = 0;
    private int compressedPayloadLen = 0;
    public static final int COMP_HEADER_LENGTH = 7;
    public static final int MIN_COMPRESS_LEN = 50;

    public CompressedPacketSender(BufferedOutputStream outputStream2) {
        this.outputStream = outputStream2;
    }

    public void stop() {
        this.deflater.end();
        this.deflater = null;
    }

    private void resetPacket() {
        this.compressedPayloadLen = 0;
        this.deflater.reset();
    }

    private void addUncompressedHeader(byte packetSequence, int uncompressedPacketLen) {
        byte[] uncompressedHeader = new byte[4];
        NativeUtils.encodeMysqlThreeByteInteger(uncompressedPacketLen, uncompressedHeader, 0);
        uncompressedHeader[3] = packetSequence;
        this.deflater.setInput(uncompressedHeader);
        this.compressedPayloadLen += this.deflater.deflate(this.compressedPacket, this.compressedPayloadLen, this.compressedPacket.length - this.compressedPayloadLen);
    }

    private void addPayload(byte[] payload, int payloadOffset, int payloadLen) {
        this.deflater.setInput(payload, payloadOffset, payloadLen);
        this.compressedPayloadLen += this.deflater.deflate(this.compressedPacket, this.compressedPayloadLen, this.compressedPacket.length - this.compressedPayloadLen);
    }

    private void completeCompression() {
        this.deflater.finish();
        this.compressedPayloadLen += this.deflater.deflate(this.compressedPacket, this.compressedPayloadLen, this.compressedPacket.length - this.compressedPayloadLen);
    }

    private void writeCompressedHeader(int compLen, byte seq, int uncompLen) throws IOException {
        this.outputStream.write(NativeUtils.encodeMysqlThreeByteInteger(compLen));
        this.outputStream.write(seq);
        this.outputStream.write(NativeUtils.encodeMysqlThreeByteInteger(uncompLen));
    }

    private void writeUncompressedHeader(int packetLen, byte packetSequence) throws IOException {
        this.outputStream.write(NativeUtils.encodeMysqlThreeByteInteger(packetLen));
        this.outputStream.write(packetSequence);
    }

    private void sendCompressedPacket(int uncompressedPayloadLen) throws IOException {
        byte by = this.compressedSequenceId;
        this.compressedSequenceId = (byte)(by + 1);
        this.writeCompressedHeader(this.compressedPayloadLen, by, uncompressedPayloadLen);
        this.outputStream.write(this.compressedPacket, 0, this.compressedPayloadLen);
    }

    @Override
    public void send(byte[] packet, int packetLen, byte packetSequence) throws IOException {
        this.compressedSequenceId = packetSequence;
        if (packetLen < 50) {
            this.writeCompressedHeader(packetLen + 4, this.compressedSequenceId, 0);
            this.writeUncompressedHeader(packetLen, packetSequence);
            this.outputStream.write(packet, 0, packetLen);
            this.outputStream.flush();
            return;
        }
        this.compressedPacket = packetLen + 4 > 0xFFFFFF ? new byte[0xFFFFFF] : new byte[4 + packetLen];
        PacketSplitter packetSplitter = new PacketSplitter(packetLen);
        int unsentPayloadLen = 0;
        int unsentOffset = 0;
        while (true) {
            this.compressedPayloadLen = 0;
            if (!packetSplitter.nextPacket()) break;
            if (unsentPayloadLen > 0) {
                this.addPayload(packet, unsentOffset, unsentPayloadLen);
            }
            int remaining = 0xFFFFFF - unsentPayloadLen;
            int len = Math.min(remaining, 4 + packetSplitter.getPacketLen());
            int lenNoHdr = len - 4;
            this.addUncompressedHeader(packetSequence, packetSplitter.getPacketLen());
            this.addPayload(packet, packetSplitter.getOffset(), lenNoHdr);
            this.completeCompression();
            if (this.compressedPayloadLen >= len) {
                byte by = this.compressedSequenceId;
                this.compressedSequenceId = (byte)(by + 1);
                this.writeCompressedHeader(unsentPayloadLen + len, by, 0);
                this.outputStream.write(packet, unsentOffset, unsentPayloadLen);
                this.writeUncompressedHeader(lenNoHdr, packetSequence);
                this.outputStream.write(packet, packetSplitter.getOffset(), lenNoHdr);
            } else {
                this.sendCompressedPacket(len + unsentPayloadLen);
            }
            packetSequence = (byte)(packetSequence + 1);
            unsentPayloadLen = packetSplitter.getPacketLen() - lenNoHdr;
            unsentOffset = packetSplitter.getOffset() + lenNoHdr;
            this.resetPacket();
        }
        if (unsentPayloadLen > 0) {
            this.addPayload(packet, unsentOffset, unsentPayloadLen);
            this.completeCompression();
            if (this.compressedPayloadLen >= unsentPayloadLen) {
                this.writeCompressedHeader(unsentPayloadLen, this.compressedSequenceId, 0);
                this.outputStream.write(packet, unsentOffset, unsentPayloadLen);
            } else {
                this.sendCompressedPacket(unsentPayloadLen);
            }
            this.resetPacket();
        }
        this.outputStream.flush();
        this.compressedPacket = null;
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

