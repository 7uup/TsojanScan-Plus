/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.DbDoc;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public interface Row {
    public BigDecimal getBigDecimal(String var1);

    public BigDecimal getBigDecimal(int var1);

    public boolean getBoolean(String var1);

    public boolean getBoolean(int var1);

    public byte getByte(String var1);

    public byte getByte(int var1);

    public Date getDate(String var1);

    public Date getDate(int var1);

    public DbDoc getDbDoc(String var1);

    public DbDoc getDbDoc(int var1);

    public double getDouble(String var1);

    public double getDouble(int var1);

    public int getInt(String var1);

    public int getInt(int var1);

    public long getLong(String var1);

    public long getLong(int var1);

    public String getString(String var1);

    public String getString(int var1);

    public Time getTime(String var1);

    public Time getTime(int var1);

    public Timestamp getTimestamp(String var1);

    public Timestamp getTimestamp(int var1);
}

