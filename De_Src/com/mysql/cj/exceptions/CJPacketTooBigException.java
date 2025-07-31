/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.exceptions;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.CJException;

public class CJPacketTooBigException
extends CJException {
    private static final long serialVersionUID = 7186090399276725363L;

    public CJPacketTooBigException() {
    }

    public CJPacketTooBigException(String message) {
        super(message);
    }

    public CJPacketTooBigException(Throwable cause) {
        super(cause);
    }

    public CJPacketTooBigException(String message, Throwable cause) {
        super(message, cause);
    }

    public CJPacketTooBigException(long packetSize, long maximumPacketSize) {
        super(Messages.getString("PacketTooBigException.0", new Object[]{packetSize, maximumPacketSize}));
    }
}

