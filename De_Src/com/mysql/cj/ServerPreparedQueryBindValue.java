/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj;

import com.mysql.cj.BindValue;
import com.mysql.cj.ClientPreparedQueryBindValue;
import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.protocol.a.NativeConstants;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.util.StringUtils;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ServerPreparedQueryBindValue
extends ClientPreparedQueryBindValue
implements BindValue {
    public long boundBeforeExecutionNum = 0L;
    public long bindLength;
    public int bufferType;
    public double doubleBinding;
    public float floatBinding;
    public boolean isLongData;
    private boolean isSet = false;
    public long longBinding;
    public Object value;
    public TimeZone tz;

    public ServerPreparedQueryBindValue() {
    }

    @Override
    public ServerPreparedQueryBindValue clone() {
        return new ServerPreparedQueryBindValue(this);
    }

    private ServerPreparedQueryBindValue(ServerPreparedQueryBindValue copyMe) {
        super(copyMe);
        this.value = copyMe.value;
        this.isSet = copyMe.isSet;
        this.isLongData = copyMe.isLongData;
        this.bufferType = copyMe.bufferType;
        this.bindLength = copyMe.bindLength;
        this.longBinding = copyMe.longBinding;
        this.floatBinding = copyMe.floatBinding;
        this.doubleBinding = copyMe.doubleBinding;
        this.tz = copyMe.tz;
    }

    @Override
    public void reset() {
        super.reset();
        this.isSet = false;
        this.value = null;
        this.isLongData = false;
        this.longBinding = 0L;
        this.floatBinding = 0.0f;
        this.doubleBinding = 0.0;
        this.tz = null;
    }

    public boolean resetToType(int bufType, long numberOfExecutions) {
        boolean sendTypesToServer = false;
        this.reset();
        if ((bufType != 6 || this.bufferType == 0) && this.bufferType != bufType) {
            sendTypesToServer = true;
            this.bufferType = bufType;
        }
        this.isSet = true;
        this.boundBeforeExecutionNum = numberOfExecutions;
        return sendTypesToServer;
    }

    public String toString() {
        return this.toString(false);
    }

    public String toString(boolean quoteIfNeeded) {
        if (this.isLongData) {
            return "' STREAM DATA '";
        }
        if (this.isNull) {
            return "NULL";
        }
        switch (this.bufferType) {
            case 1: 
            case 2: 
            case 3: 
            case 8: {
                return String.valueOf(this.longBinding);
            }
            case 4: {
                return String.valueOf(this.floatBinding);
            }
            case 5: {
                return String.valueOf(this.doubleBinding);
            }
            case 7: 
            case 10: 
            case 11: 
            case 12: 
            case 15: 
            case 253: 
            case 254: {
                if (quoteIfNeeded) {
                    return "'" + String.valueOf(this.value) + "'";
                }
                return String.valueOf(this.value);
            }
        }
        if (this.value instanceof byte[]) {
            return "byte data";
        }
        if (quoteIfNeeded) {
            return "'" + String.valueOf(this.value) + "'";
        }
        return String.valueOf(this.value);
    }

    public long getBoundLength() {
        if (this.isNull) {
            return 0L;
        }
        if (this.isLongData) {
            return this.bindLength;
        }
        switch (this.bufferType) {
            case 1: {
                return 1L;
            }
            case 2: {
                return 2L;
            }
            case 3: {
                return 4L;
            }
            case 8: {
                return 8L;
            }
            case 4: {
                return 4L;
            }
            case 5: {
                return 8L;
            }
            case 11: {
                return 9L;
            }
            case 10: {
                return 7L;
            }
            case 7: 
            case 12: {
                return 11L;
            }
            case 0: 
            case 15: 
            case 246: 
            case 253: 
            case 254: {
                if (this.value instanceof byte[]) {
                    return ((byte[])this.value).length;
                }
                return ((String)this.value).length();
            }
        }
        return 0L;
    }

    @Override
    public boolean isSet() {
        return this.isSet;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void storeBinding(NativePacketPayload intoPacket, boolean isLoadDataQuery, String characterEncoding, ExceptionInterceptor interceptor) {
        ServerPreparedQueryBindValue serverPreparedQueryBindValue = this;
        synchronized (serverPreparedQueryBindValue) {
            try {
                switch (this.bufferType) {
                    case 1: {
                        intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, this.longBinding);
                        return;
                    }
                    case 2: {
                        intoPacket.writeInteger(NativeConstants.IntegerDataType.INT2, this.longBinding);
                        return;
                    }
                    case 3: {
                        intoPacket.writeInteger(NativeConstants.IntegerDataType.INT4, this.longBinding);
                        return;
                    }
                    case 8: {
                        intoPacket.writeInteger(NativeConstants.IntegerDataType.INT8, this.longBinding);
                        return;
                    }
                    case 4: {
                        intoPacket.writeInteger(NativeConstants.IntegerDataType.INT4, Float.floatToIntBits(this.floatBinding));
                        return;
                    }
                    case 5: {
                        intoPacket.writeInteger(NativeConstants.IntegerDataType.INT8, Double.doubleToLongBits(this.doubleBinding));
                        return;
                    }
                    case 11: {
                        this.storeTime(intoPacket);
                        return;
                    }
                    case 7: 
                    case 10: 
                    case 12: {
                        this.storeDateTime(intoPacket);
                        return;
                    }
                    case 0: 
                    case 15: 
                    case 246: 
                    case 253: 
                    case 254: {
                        if (this.value instanceof byte[]) {
                            intoPacket.writeBytes(NativeConstants.StringSelfDataType.STRING_LENENC, (byte[])this.value);
                        } else if (!isLoadDataQuery) {
                            intoPacket.writeBytes(NativeConstants.StringSelfDataType.STRING_LENENC, StringUtils.getBytes((String)this.value, characterEncoding));
                        } else {
                            intoPacket.writeBytes(NativeConstants.StringSelfDataType.STRING_LENENC, StringUtils.getBytes((String)this.value));
                        }
                        return;
                    }
                }
            } catch (CJException uEE) {
                throw ExceptionFactory.createException(Messages.getString("ServerPreparedStatement.22") + characterEncoding + "'", uEE, interceptor);
            }
        }
    }

    private void storeTime(NativePacketPayload intoPacket) {
        intoPacket.ensureCapacity(9);
        intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, 8L);
        intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, 0L);
        intoPacket.writeInteger(NativeConstants.IntegerDataType.INT4, 0L);
        Calendar cal = Calendar.getInstance(this.tz);
        cal.setTime((Date)this.value);
        intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, cal.get(11));
        intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, cal.get(12));
        intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, cal.get(13));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void storeDateTime(NativePacketPayload intoPacket) {
        ServerPreparedQueryBindValue serverPreparedQueryBindValue = this;
        synchronized (serverPreparedQueryBindValue) {
            Calendar cal = Calendar.getInstance(this.tz);
            cal.setTime((Date)this.value);
            if (this.value instanceof java.sql.Date) {
                cal.set(11, 0);
                cal.set(12, 0);
                cal.set(13, 0);
            }
            int length = 7;
            if (this.value instanceof Timestamp) {
                length = 11;
            }
            intoPacket.ensureCapacity(length);
            intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, length);
            int year = cal.get(1);
            int month = cal.get(2) + 1;
            int date = cal.get(5);
            intoPacket.writeInteger(NativeConstants.IntegerDataType.INT2, year);
            intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, month);
            intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, date);
            if (this.value instanceof java.sql.Date) {
                intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, 0L);
                intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, 0L);
                intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, 0L);
            } else {
                intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, cal.get(11));
                intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, cal.get(12));
                intoPacket.writeInteger(NativeConstants.IntegerDataType.INT1, cal.get(13));
            }
            if (length == 11) {
                intoPacket.writeInteger(NativeConstants.IntegerDataType.INT4, ((Timestamp)this.value).getNanos() / 1000);
            }
        }
    }
}

