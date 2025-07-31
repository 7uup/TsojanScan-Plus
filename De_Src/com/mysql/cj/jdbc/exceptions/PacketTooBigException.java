/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.exceptions;

import com.mysql.cj.Messages;
import java.sql.SQLException;

public class PacketTooBigException
extends SQLException {
    static final long serialVersionUID = 7248633977685452174L;

    public PacketTooBigException(long packetSize, long maximumPacketSize) {
        super(Messages.getString("PacketTooBigException.0", new Object[]{packetSize, maximumPacketSize}), "S1000");
    }

    public PacketTooBigException(String message) {
        super(message, "S1000");
    }
}

