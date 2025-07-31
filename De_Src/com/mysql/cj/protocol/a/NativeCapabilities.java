/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a;

import com.mysql.cj.ServerVersion;
import com.mysql.cj.protocol.ServerCapabilities;
import com.mysql.cj.protocol.a.NativeConstants;
import com.mysql.cj.protocol.a.NativePacketPayload;

public class NativeCapabilities
implements ServerCapabilities {
    private NativePacketPayload initialHandshakePacket;
    private byte protocolVersion = 0;
    private ServerVersion serverVersion;
    private long threadId = -1L;
    private String seed;
    private int capabilityFlags;
    private int serverDefaultCollationIndex;
    private int statusFlags = 0;
    private int authPluginDataLength = 0;
    private boolean serverHasFracSecsSupport = true;

    public NativePacketPayload getInitialHandshakePacket() {
        return this.initialHandshakePacket;
    }

    public void setInitialHandshakePacket(NativePacketPayload initialHandshakePacket) {
        this.initialHandshakePacket = initialHandshakePacket;
        this.setProtocolVersion((byte)initialHandshakePacket.readInteger(NativeConstants.IntegerDataType.INT1));
        this.setServerVersion(ServerVersion.parseVersion(initialHandshakePacket.readString(NativeConstants.StringSelfDataType.STRING_TERM, "ASCII")));
        this.setThreadId(initialHandshakePacket.readInteger(NativeConstants.IntegerDataType.INT4));
        this.setSeed(initialHandshakePacket.readString(NativeConstants.StringLengthDataType.STRING_FIXED, "ASCII", 8));
        initialHandshakePacket.readInteger(NativeConstants.IntegerDataType.INT1);
        int flags = 0;
        if (initialHandshakePacket.getPosition() < initialHandshakePacket.getPayloadLength()) {
            flags = (int)initialHandshakePacket.readInteger(NativeConstants.IntegerDataType.INT2);
        }
        this.setServerDefaultCollationIndex((int)initialHandshakePacket.readInteger(NativeConstants.IntegerDataType.INT1));
        this.setStatusFlags((int)initialHandshakePacket.readInteger(NativeConstants.IntegerDataType.INT2));
        this.setCapabilityFlags(flags |= (int)initialHandshakePacket.readInteger(NativeConstants.IntegerDataType.INT2) << 16);
        if ((flags & 0x80000) != 0) {
            this.authPluginDataLength = (int)initialHandshakePacket.readInteger(NativeConstants.IntegerDataType.INT1);
        } else {
            initialHandshakePacket.readInteger(NativeConstants.IntegerDataType.INT1);
        }
        initialHandshakePacket.setPosition(initialHandshakePacket.getPosition() + 10);
        this.serverHasFracSecsSupport = this.serverVersion.meetsMinimum(new ServerVersion(5, 6, 4));
    }

    @Override
    public int getCapabilityFlags() {
        return this.capabilityFlags;
    }

    @Override
    public void setCapabilityFlags(int capabilityFlags) {
        this.capabilityFlags = capabilityFlags;
    }

    public byte getProtocolVersion() {
        return this.protocolVersion;
    }

    public void setProtocolVersion(byte protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    @Override
    public ServerVersion getServerVersion() {
        return this.serverVersion;
    }

    @Override
    public void setServerVersion(ServerVersion serverVersion) {
        this.serverVersion = serverVersion;
    }

    public long getThreadId() {
        return this.threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public String getSeed() {
        return this.seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public int getServerDefaultCollationIndex() {
        return this.serverDefaultCollationIndex;
    }

    public void setServerDefaultCollationIndex(int serverDefaultCollationIndex) {
        this.serverDefaultCollationIndex = serverDefaultCollationIndex;
    }

    public int getStatusFlags() {
        return this.statusFlags;
    }

    public void setStatusFlags(int statusFlags) {
        this.statusFlags = statusFlags;
    }

    public int getAuthPluginDataLength() {
        return this.authPluginDataLength;
    }

    public void setAuthPluginDataLength(int authPluginDataLength) {
        this.authPluginDataLength = authPluginDataLength;
    }

    @Override
    public boolean serverSupportsFracSecs() {
        return this.serverHasFracSecsSupport;
    }
}

