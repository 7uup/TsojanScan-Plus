/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj;

import com.mysql.cj.MysqlType;
import java.io.InputStream;

public interface BindValue {
    public BindValue clone();

    public void reset();

    public boolean isNull();

    public void setNull(boolean var1);

    public boolean isStream();

    public void setIsStream(boolean var1);

    public MysqlType getMysqlType();

    public void setMysqlType(MysqlType var1);

    public byte[] getByteValue();

    public void setByteValue(byte[] var1);

    public InputStream getStreamValue();

    public void setStreamValue(InputStream var1, int var2);

    public int getStreamLength();

    public boolean isSet();
}

