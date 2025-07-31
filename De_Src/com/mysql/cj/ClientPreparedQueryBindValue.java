/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj;

import com.mysql.cj.BindValue;
import com.mysql.cj.MysqlType;
import java.io.InputStream;

public class ClientPreparedQueryBindValue
implements BindValue {
    protected boolean isNull;
    private boolean isStream = false;
    protected MysqlType parameterType = MysqlType.NULL;
    private byte[] parameterValue = null;
    private InputStream parameterStream = null;
    private int streamLength;

    public ClientPreparedQueryBindValue() {
    }

    @Override
    public ClientPreparedQueryBindValue clone() {
        return new ClientPreparedQueryBindValue(this);
    }

    protected ClientPreparedQueryBindValue(ClientPreparedQueryBindValue copyMe) {
        this.isNull = copyMe.isNull;
        this.isStream = copyMe.isStream;
        this.parameterType = copyMe.parameterType;
        if (copyMe.parameterValue != null) {
            this.parameterValue = new byte[copyMe.parameterValue.length];
            System.arraycopy(copyMe.parameterValue, 0, this.parameterValue, 0, copyMe.parameterValue.length);
        }
        this.parameterStream = copyMe.parameterStream;
        this.streamLength = copyMe.streamLength;
    }

    @Override
    public void reset() {
        this.isNull = false;
        this.isStream = false;
        this.parameterType = MysqlType.NULL;
    }

    @Override
    public boolean isNull() {
        return this.isNull;
    }

    @Override
    public void setNull(boolean isNull) {
        this.isNull = isNull;
        if (isNull) {
            this.parameterType = MysqlType.NULL;
        }
    }

    @Override
    public boolean isStream() {
        return this.isStream;
    }

    @Override
    public void setIsStream(boolean isStream) {
        this.isStream = isStream;
    }

    @Override
    public MysqlType getMysqlType() {
        return this.parameterType;
    }

    @Override
    public void setMysqlType(MysqlType type) {
        this.parameterType = type;
    }

    @Override
    public byte[] getByteValue() {
        return this.parameterValue;
    }

    @Override
    public void setByteValue(byte[] parameterValue) {
        this.isNull = false;
        this.isStream = false;
        this.parameterValue = parameterValue;
        this.parameterStream = null;
        this.streamLength = 0;
    }

    @Override
    public InputStream getStreamValue() {
        return this.parameterStream;
    }

    @Override
    public void setStreamValue(InputStream parameterStream, int streamLength) {
        this.parameterStream = parameterStream;
        this.streamLength = streamLength;
    }

    @Override
    public int getStreamLength() {
        return this.streamLength;
    }

    @Override
    public boolean isSet() {
        return this.parameterValue != null || this.parameterStream != null;
    }
}

