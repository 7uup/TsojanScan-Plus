/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Ref;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

public interface ParameterBindings {
    public Array getArray(int var1) throws SQLException;

    public InputStream getAsciiStream(int var1) throws SQLException;

    public BigDecimal getBigDecimal(int var1) throws SQLException;

    public InputStream getBinaryStream(int var1) throws SQLException;

    public Blob getBlob(int var1) throws SQLException;

    public boolean getBoolean(int var1) throws SQLException;

    public byte getByte(int var1) throws SQLException;

    public byte[] getBytes(int var1) throws SQLException;

    public Reader getCharacterStream(int var1) throws SQLException;

    public Clob getClob(int var1) throws SQLException;

    public Date getDate(int var1) throws SQLException;

    public double getDouble(int var1) throws SQLException;

    public float getFloat(int var1) throws SQLException;

    public int getInt(int var1) throws SQLException;

    public BigInteger getBigInteger(int var1) throws SQLException;

    public long getLong(int var1) throws SQLException;

    public Reader getNCharacterStream(int var1) throws SQLException;

    public Reader getNClob(int var1) throws SQLException;

    public Object getObject(int var1) throws SQLException;

    public Ref getRef(int var1) throws SQLException;

    public short getShort(int var1) throws SQLException;

    public String getString(int var1) throws SQLException;

    public Time getTime(int var1) throws SQLException;

    public Timestamp getTimestamp(int var1) throws SQLException;

    public URL getURL(int var1) throws SQLException;

    public boolean isNull(int var1) throws SQLException;
}

