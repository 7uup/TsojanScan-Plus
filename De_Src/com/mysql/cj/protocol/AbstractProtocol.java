/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.MessageBuilder;
import com.mysql.cj.Session;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.log.Log;
import com.mysql.cj.protocol.AuthenticationProvider;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.PacketReceivedTimeHolder;
import com.mysql.cj.protocol.PacketSentTimeHolder;
import com.mysql.cj.protocol.Protocol;
import com.mysql.cj.protocol.SocketConnection;
import java.util.LinkedList;

public abstract class AbstractProtocol<M extends Message>
implements Protocol<M> {
    protected Session session;
    protected SocketConnection socketConnection;
    protected PropertySet propertySet;
    protected transient Log log;
    protected ExceptionInterceptor exceptionInterceptor;
    protected AuthenticationProvider<M> authProvider;
    protected MessageBuilder<M> messageBuilder;
    private PacketSentTimeHolder packetSentTimeHolder = new PacketSentTimeHolder(){};
    private PacketReceivedTimeHolder packetReceivedTimeHolder = new PacketReceivedTimeHolder(){};
    protected LinkedList<StringBuilder> packetDebugRingBuffer = null;

    @Override
    public SocketConnection getSocketConnection() {
        return this.socketConnection;
    }

    @Override
    public AuthenticationProvider<M> getAuthenticationProvider() {
        return this.authProvider;
    }

    @Override
    public ExceptionInterceptor getExceptionInterceptor() {
        return this.exceptionInterceptor;
    }

    @Override
    public PacketSentTimeHolder getPacketSentTimeHolder() {
        return this.packetSentTimeHolder;
    }

    @Override
    public void setPacketSentTimeHolder(PacketSentTimeHolder packetSentTimeHolder) {
        this.packetSentTimeHolder = packetSentTimeHolder;
    }

    @Override
    public PacketReceivedTimeHolder getPacketReceivedTimeHolder() {
        return this.packetReceivedTimeHolder;
    }

    @Override
    public void setPacketReceivedTimeHolder(PacketReceivedTimeHolder packetReceivedTimeHolder) {
        this.packetReceivedTimeHolder = packetReceivedTimeHolder;
    }

    @Override
    public PropertySet getPropertySet() {
        return this.propertySet;
    }

    @Override
    public void setPropertySet(PropertySet propertySet) {
        this.propertySet = propertySet;
    }

    @Override
    public MessageBuilder<M> getMessageBuilder() {
        return this.messageBuilder;
    }
}

