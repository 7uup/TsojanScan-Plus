/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.log;

public interface ProfilerEvent {
    public static final byte TYPE_WARN = 0;
    public static final byte TYPE_OBJECT_CREATION = 1;
    public static final byte TYPE_PREPARE = 2;
    public static final byte TYPE_QUERY = 3;
    public static final byte TYPE_EXECUTE = 4;
    public static final byte TYPE_FETCH = 5;
    public static final byte TYPE_SLOW_QUERY = 6;

    public byte getEventType();

    public void setEventType(byte var1);

    public long getEventDuration();

    public String getDurationUnits();

    public long getConnectionId();

    public int getResultSetId();

    public int getStatementId();

    public String getMessage();

    public long getEventCreationTime();

    public String getCatalog();

    public String getEventCreationPointAsString();

    public byte[] pack();
}

