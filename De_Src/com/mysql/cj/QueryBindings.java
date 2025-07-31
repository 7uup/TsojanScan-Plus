/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj;

import com.mysql.cj.BindValue;
import com.mysql.cj.MysqlType;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;

public interface QueryBindings<T extends BindValue> {
    public QueryBindings<T> clone();

    public boolean isLoadDataQuery();

    public void setLoadDataQuery(boolean var1);

    public T[] getBindValues();

    public void setBindValues(T[] var1);

    public boolean clearBindValues();

    public void checkParameterSet(int var1);

    public void checkAllParametersSet();

    public int getNumberOfExecutions();

    public void setNumberOfExecutions(int var1);

    public void setValue(int var1, byte[] var2);

    public void setValue(int var1, byte[] var2, MysqlType var3);

    public void setValue(int var1, String var2);

    public void setValue(int var1, String var2, MysqlType var3);

    public void setAsciiStream(int var1, InputStream var2);

    public void setAsciiStream(int var1, InputStream var2, int var3);

    public void setAsciiStream(int var1, InputStream var2, long var3);

    public void setBigDecimal(int var1, BigDecimal var2);

    public void setBigInteger(int var1, BigInteger var2);

    public void setBinaryStream(int var1, InputStream var2);

    public void setBinaryStream(int var1, InputStream var2, int var3);

    public void setBinaryStream(int var1, InputStream var2, long var3);

    public void setBlob(int var1, Blob var2);

    public void setBlob(int var1, InputStream var2);

    public void setBlob(int var1, InputStream var2, long var3);

    public void setBoolean(int var1, boolean var2);

    public void setByte(int var1, byte var2);

    public void setBytes(int var1, byte[] var2);

    public void setBytes(int var1, byte[] var2, boolean var3, boolean var4);

    public void setBytesNoEscape(int var1, byte[] var2);

    public void setBytesNoEscapeNoQuotes(int var1, byte[] var2);

    public void setCharacterStream(int var1, Reader var2);

    public void setCharacterStream(int var1, Reader var2, int var3);

    public void setCharacterStream(int var1, Reader var2, long var3);

    public void setClob(int var1, Clob var2);

    public void setClob(int var1, Reader var2);

    public void setClob(int var1, Reader var2, long var3);

    public void setDate(int var1, Date var2);

    public void setDate(int var1, Date var2, Calendar var3);

    public void setDate(int var1, Date var2, TimeZone var3);

    public void setDouble(int var1, double var2);

    public void setFloat(int var1, float var2);

    public void setInt(int var1, int var2);

    public void setLong(int var1, long var2);

    public void setNCharacterStream(int var1, Reader var2);

    public void setNCharacterStream(int var1, Reader var2, long var3);

    public void setNClob(int var1, Reader var2);

    public void setNClob(int var1, Reader var2, long var3);

    public void setNClob(int var1, NClob var2);

    public void setNString(int var1, String var2);

    public void setNull(int var1);

    public void setObject(int var1, Object var2);

    public void setObject(int var1, Object var2, MysqlType var3);

    public void setObject(int var1, Object var2, MysqlType var3, int var4);

    public void setShort(int var1, short var2);

    public void setString(int var1, String var2);

    public void setTime(int var1, Time var2);

    public void setTime(int var1, Time var2, Calendar var3);

    public void setTime(int var1, Time var2, TimeZone var3);

    public void setTimestamp(int var1, Timestamp var2, Calendar var3);

    public void setTimestamp(int var1, Timestamp var2);

    public void setTimestamp(int var1, Timestamp var2, TimeZone var3);
}

