/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.exceptions;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.StreamingNotifiable;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.protocol.PacketReceivedTimeHolder;
import com.mysql.cj.protocol.PacketSentTimeHolder;
import java.sql.SQLRecoverableException;

public class CommunicationsException
extends SQLRecoverableException
implements StreamingNotifiable {
    private static final long serialVersionUID = 4317904269000988676L;
    private String exceptionMessage;

    public CommunicationsException(JdbcConnection conn, PacketSentTimeHolder packetSentTimeHolder, PacketReceivedTimeHolder packetReceivedTimeHolder, Exception underlyingException) {
        this(ExceptionFactory.createLinkFailureMessageBasedOnHeuristics(conn.getPropertySet(), conn.getSession().getServerSession(), packetSentTimeHolder, packetReceivedTimeHolder, underlyingException), underlyingException);
    }

    public CommunicationsException(String message, Throwable underlyingException) {
        this.exceptionMessage = message;
        if (underlyingException != null) {
            this.initCause(underlyingException);
        }
    }

    @Override
    public String getMessage() {
        return this.exceptionMessage;
    }

    @Override
    public String getSQLState() {
        return "08S01";
    }

    @Override
    public void setWasStreamingResults() {
        this.exceptionMessage = Messages.getString("CommunicationsException.ClientWasStreaming");
    }
}

