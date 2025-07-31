/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.exceptions;

import com.mysql.cj.Messages;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.protocol.PacketSentTimeHolder;
import com.mysql.cj.protocol.ServerSession;

public class CJConnectionFeatureNotAvailableException
extends CJCommunicationsException {
    private static final long serialVersionUID = -4129847384681995107L;

    public CJConnectionFeatureNotAvailableException() {
    }

    public CJConnectionFeatureNotAvailableException(PropertySet propertySet, ServerSession serverSession, PacketSentTimeHolder packetSentTimeHolder, Exception underlyingException) {
        super(underlyingException);
        this.init(propertySet, serverSession, packetSentTimeHolder, null);
    }

    @Override
    public String getMessage() {
        return Messages.getString("ConnectionFeatureNotAvailableException.0");
    }
}

