/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj;

import com.mysql.cj.BindValue;
import com.mysql.cj.Messages;
import com.mysql.cj.MysqlType;
import com.mysql.cj.QueryBindings;
import com.mysql.cj.ServerPreparedQueryBindValue;
import com.mysql.cj.Session;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.protocol.a.NativeConstants;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.util.TimeUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Locale;

public abstract class AbstractQueryBindings<T extends BindValue>
implements QueryBindings<T> {
    protected static final byte[] HEX_DIGITS = new byte[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70};
    protected Session session;
    protected T[] bindValues;
    protected String charEncoding;
    protected int numberOfExecutions = 0;
    protected RuntimeProperty<Boolean> useStreamLengthsInPrepStmts;
    protected RuntimeProperty<Boolean> sendFractionalSeconds;
    private RuntimeProperty<Boolean> treatUtilDateAsTimestamp;
    protected boolean isLoadDataQuery = false;

    public AbstractQueryBindings(int parameterCount, Session sess) {
        this.session = sess;
        this.charEncoding = this.session.getPropertySet().getStringProperty("characterEncoding").getValue();
        this.sendFractionalSeconds = this.session.getPropertySet().getBooleanProperty("sendFractionalSeconds");
        this.treatUtilDateAsTimestamp = this.session.getPropertySet().getBooleanProperty("treatUtilDateAsTimestamp");
        this.useStreamLengthsInPrepStmts = this.session.getPropertySet().getBooleanProperty("useStreamLengthsInPrepStmts");
        this.initBindValues(parameterCount);
    }

    protected abstract void initBindValues(int var1);

    @Override
    public abstract AbstractQueryBindings<T> clone();

    @Override
    public boolean isLoadDataQuery() {
        return this.isLoadDataQuery;
    }

    @Override
    public void setLoadDataQuery(boolean isLoadDataQuery) {
        this.isLoadDataQuery = isLoadDataQuery;
    }

    @Override
    public T[] getBindValues() {
        return this.bindValues;
    }

    @Override
    public void setBindValues(T[] bindValues) {
        this.bindValues = bindValues;
    }

    @Override
    public boolean clearBindValues() {
        boolean hadLongData = false;
        if (this.bindValues != null) {
            for (int i = 0; i < this.bindValues.length; ++i) {
                if (this.bindValues[i] != null && ((ServerPreparedQueryBindValue)this.bindValues[i]).isLongData) {
                    hadLongData = true;
                }
                this.bindValues[i].reset();
            }
        }
        return hadLongData;
    }

    @Override
    public abstract void checkParameterSet(int var1);

    @Override
    public void checkAllParametersSet() {
        for (int i = 0; i < this.bindValues.length; ++i) {
            this.checkParameterSet(i);
        }
    }

    @Override
    public int getNumberOfExecutions() {
        return this.numberOfExecutions;
    }

    @Override
    public void setNumberOfExecutions(int numberOfExecutions) {
        this.numberOfExecutions = numberOfExecutions;
    }

    @Override
    public final synchronized void setValue(int paramIndex, byte[] val) {
        this.bindValues[paramIndex].setByteValue(val);
    }

    @Override
    public final synchronized void setValue(int paramIndex, byte[] val, MysqlType type) {
        this.bindValues[paramIndex].setByteValue(val);
        this.bindValues[paramIndex].setMysqlType(type);
    }

    @Override
    public final synchronized void setValue(int paramIndex, String val) {
        byte[] parameterAsBytes = StringUtils.getBytes(val, this.charEncoding);
        this.setValue(paramIndex, parameterAsBytes);
    }

    @Override
    public final synchronized void setValue(int paramIndex, String val, MysqlType type) {
        byte[] parameterAsBytes = StringUtils.getBytes(val, this.charEncoding);
        this.setValue(paramIndex, parameterAsBytes, type);
    }

    public final void hexEscapeBlock(byte[] buf, NativePacketPayload packet, int size) {
        for (int i = 0; i < size; ++i) {
            byte b = buf[i];
            int lowBits = (b & 0xFF) / 16;
            int highBits = (b & 0xFF) % 16;
            packet.writeInteger(NativeConstants.IntegerDataType.INT1, HEX_DIGITS[lowBits]);
            packet.writeInteger(NativeConstants.IntegerDataType.INT1, HEX_DIGITS[highBits]);
        }
    }

    @Override
    public void setObject(int parameterIndex, Object parameterObj) {
        if (parameterObj == null) {
            this.setNull(parameterIndex);
        } else if (parameterObj instanceof Byte) {
            this.setInt(parameterIndex, ((Byte)parameterObj).intValue());
        } else if (parameterObj instanceof String) {
            this.setString(parameterIndex, (String)parameterObj);
        } else if (parameterObj instanceof BigDecimal) {
            this.setBigDecimal(parameterIndex, (BigDecimal)parameterObj);
        } else if (parameterObj instanceof Short) {
            this.setShort(parameterIndex, (Short)parameterObj);
        } else if (parameterObj instanceof Integer) {
            this.setInt(parameterIndex, (Integer)parameterObj);
        } else if (parameterObj instanceof Long) {
            this.setLong(parameterIndex, (Long)parameterObj);
        } else if (parameterObj instanceof Float) {
            this.setFloat(parameterIndex, ((Float)parameterObj).floatValue());
        } else if (parameterObj instanceof Double) {
            this.setDouble(parameterIndex, (Double)parameterObj);
        } else if (parameterObj instanceof byte[]) {
            this.setBytes(parameterIndex, (byte[])parameterObj);
        } else if (parameterObj instanceof Date) {
            this.setDate(parameterIndex, (Date)parameterObj);
        } else if (parameterObj instanceof Time) {
            this.setTime(parameterIndex, (Time)parameterObj);
        } else if (parameterObj instanceof Timestamp) {
            this.setTimestamp(parameterIndex, (Timestamp)parameterObj);
        } else if (parameterObj instanceof Boolean) {
            this.setBoolean(parameterIndex, (Boolean)parameterObj);
        } else if (parameterObj instanceof InputStream) {
            this.setBinaryStream(parameterIndex, (InputStream)parameterObj, -1);
        } else if (parameterObj instanceof Blob) {
            this.setBlob(parameterIndex, (Blob)parameterObj);
        } else if (parameterObj instanceof Clob) {
            this.setClob(parameterIndex, (Clob)parameterObj);
        } else if (this.treatUtilDateAsTimestamp.getValue().booleanValue() && parameterObj instanceof java.util.Date) {
            this.setTimestamp(parameterIndex, new Timestamp(((java.util.Date)parameterObj).getTime()));
        } else if (parameterObj instanceof BigInteger) {
            this.setString(parameterIndex, parameterObj.toString());
        } else if (parameterObj instanceof LocalDate) {
            this.setDate(parameterIndex, Date.valueOf((LocalDate)parameterObj));
        } else if (parameterObj instanceof LocalDateTime) {
            this.setTimestamp(parameterIndex, Timestamp.valueOf((LocalDateTime)parameterObj));
        } else if (parameterObj instanceof LocalTime) {
            this.setTime(parameterIndex, Time.valueOf((LocalTime)parameterObj));
        } else {
            this.setSerializableObject(parameterIndex, parameterObj);
        }
    }

    @Override
    public void setObject(int parameterIndex, Object parameterObj, MysqlType targetMysqlType) {
        this.setObject(parameterIndex, parameterObj, targetMysqlType, parameterObj instanceof BigDecimal ? ((BigDecimal)parameterObj).scale() : 0);
    }

    @Override
    public void setObject(int parameterIndex, Object parameterObj, MysqlType targetMysqlType, int scaleOrLength) {
        if (parameterObj == null) {
            this.setNull(parameterIndex);
        } else {
            if (parameterObj instanceof LocalDate) {
                parameterObj = Date.valueOf((LocalDate)parameterObj);
            } else if (parameterObj instanceof LocalDateTime) {
                parameterObj = Timestamp.valueOf((LocalDateTime)parameterObj);
            } else if (parameterObj instanceof LocalTime) {
                parameterObj = Time.valueOf((LocalTime)parameterObj);
            }
            try {
                block1 : switch (targetMysqlType) {
                    case BOOLEAN: {
                        if (parameterObj instanceof Boolean) {
                            this.setBoolean(parameterIndex, (Boolean)parameterObj);
                            break;
                        }
                        if (parameterObj instanceof String) {
                            this.setBoolean(parameterIndex, "true".equalsIgnoreCase((String)parameterObj) || !"0".equalsIgnoreCase((String)parameterObj));
                            break;
                        }
                        if (parameterObj instanceof Number) {
                            int intValue = ((Number)parameterObj).intValue();
                            this.setBoolean(parameterIndex, intValue != 0);
                            break;
                        }
                        throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.66", new Object[]{parameterObj.getClass().getName()}), this.session.getExceptionInterceptor());
                    }
                    case BIT: 
                    case TINYINT: 
                    case TINYINT_UNSIGNED: 
                    case SMALLINT: 
                    case SMALLINT_UNSIGNED: 
                    case INT: 
                    case INT_UNSIGNED: 
                    case MEDIUMINT: 
                    case MEDIUMINT_UNSIGNED: 
                    case BIGINT: 
                    case BIGINT_UNSIGNED: 
                    case FLOAT: 
                    case FLOAT_UNSIGNED: 
                    case DOUBLE: 
                    case DOUBLE_UNSIGNED: 
                    case DECIMAL: 
                    case DECIMAL_UNSIGNED: {
                        this.setNumericObject(parameterIndex, parameterObj, targetMysqlType, scaleOrLength);
                        break;
                    }
                    case CHAR: 
                    case ENUM: 
                    case SET: 
                    case VARCHAR: 
                    case TINYTEXT: 
                    case TEXT: 
                    case MEDIUMTEXT: 
                    case LONGTEXT: 
                    case JSON: {
                        if (parameterObj instanceof BigDecimal) {
                            this.setString(parameterIndex, StringUtils.fixDecimalExponent(((BigDecimal)parameterObj).toPlainString()));
                            break;
                        }
                        if (parameterObj instanceof Clob) {
                            this.setClob(parameterIndex, (Clob)parameterObj);
                            break;
                        }
                        this.setString(parameterIndex, parameterObj.toString());
                        break;
                    }
                    case BINARY: 
                    case GEOMETRY: 
                    case VARBINARY: 
                    case TINYBLOB: 
                    case BLOB: 
                    case MEDIUMBLOB: 
                    case LONGBLOB: {
                        if (parameterObj instanceof byte[]) {
                            this.setBytes(parameterIndex, (byte[])parameterObj);
                            break;
                        }
                        if (parameterObj instanceof Blob) {
                            this.setBlob(parameterIndex, (Blob)parameterObj);
                            break;
                        }
                        this.setBytes(parameterIndex, StringUtils.getBytes(parameterObj.toString(), this.charEncoding));
                        break;
                    }
                    case DATE: 
                    case DATETIME: 
                    case TIMESTAMP: 
                    case YEAR: {
                        java.util.Date parameterAsDate;
                        if (parameterObj instanceof String) {
                            ParsePosition pp = new ParsePosition(0);
                            SimpleDateFormat sdf = new SimpleDateFormat(TimeUtil.getDateTimePattern((String)parameterObj, false), Locale.US);
                            parameterAsDate = ((DateFormat)sdf).parse((String)parameterObj, pp);
                        } else {
                            parameterAsDate = (java.util.Date)parameterObj;
                        }
                        switch (targetMysqlType) {
                            case DATE: {
                                if (parameterAsDate instanceof Date) {
                                    this.setDate(parameterIndex, (Date)parameterAsDate);
                                    break block1;
                                }
                                this.setDate(parameterIndex, new Date(parameterAsDate.getTime()));
                                break block1;
                            }
                            case DATETIME: 
                            case TIMESTAMP: {
                                if (parameterAsDate instanceof Timestamp) {
                                    this.setTimestamp(parameterIndex, (Timestamp)parameterAsDate);
                                    break block1;
                                }
                                this.setTimestamp(parameterIndex, new Timestamp(parameterAsDate.getTime()));
                                break block1;
                            }
                            case YEAR: {
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(parameterAsDate);
                                this.setNumericObject(parameterIndex, cal.get(1), targetMysqlType, scaleOrLength);
                                break block1;
                            }
                        }
                        break;
                    }
                    case TIME: {
                        if (parameterObj instanceof String) {
                            SimpleDateFormat sdf = new SimpleDateFormat(TimeUtil.getDateTimePattern((String)parameterObj, true), Locale.US);
                            this.setTime(parameterIndex, new Time(sdf.parse((String)parameterObj).getTime()));
                            break;
                        }
                        if (parameterObj instanceof Timestamp) {
                            Timestamp xT = (Timestamp)parameterObj;
                            this.setTime(parameterIndex, new Time(xT.getTime()));
                            break;
                        }
                        this.setTime(parameterIndex, (Time)parameterObj);
                        break;
                    }
                    case UNKNOWN: {
                        this.setSerializableObject(parameterIndex, parameterObj);
                        break;
                    }
                    default: {
                        throw ExceptionFactory.createException(Messages.getString("PreparedStatement.16"), this.session.getExceptionInterceptor());
                    }
                }
            } catch (Exception ex) {
                throw ExceptionFactory.createException(Messages.getString("PreparedStatement.17") + parameterObj.getClass().toString() + Messages.getString("PreparedStatement.18") + ex.getClass().getName() + Messages.getString("PreparedStatement.19") + ex.getMessage(), ex, this.session.getExceptionInterceptor());
            }
        }
    }

    private void setNumericObject(int parameterIndex, Object parameterObj, MysqlType targetMysqlType, int scale) {
        Number parameterAsNum;
        if (parameterObj instanceof Boolean) {
            parameterAsNum = (Boolean)parameterObj != false ? Integer.valueOf(1) : Integer.valueOf(0);
        } else if (parameterObj instanceof String) {
            switch (targetMysqlType) {
                case BIT: {
                    if ("1".equals(parameterObj) || "0".equals(parameterObj)) {
                        parameterAsNum = Integer.valueOf((String)parameterObj);
                        break;
                    }
                    boolean parameterAsBoolean = "true".equalsIgnoreCase((String)parameterObj);
                    parameterAsNum = parameterAsBoolean ? Integer.valueOf(1) : Integer.valueOf(0);
                    break;
                }
                case YEAR: 
                case TINYINT: 
                case TINYINT_UNSIGNED: 
                case SMALLINT: 
                case SMALLINT_UNSIGNED: 
                case INT: 
                case INT_UNSIGNED: 
                case MEDIUMINT: 
                case MEDIUMINT_UNSIGNED: {
                    parameterAsNum = Integer.valueOf((String)parameterObj);
                    break;
                }
                case BIGINT: {
                    parameterAsNum = Long.valueOf((String)parameterObj);
                    break;
                }
                case BIGINT_UNSIGNED: {
                    parameterAsNum = new BigInteger((String)parameterObj);
                    break;
                }
                case FLOAT: 
                case FLOAT_UNSIGNED: {
                    parameterAsNum = Float.valueOf((String)parameterObj);
                    break;
                }
                case DOUBLE: 
                case DOUBLE_UNSIGNED: {
                    parameterAsNum = Double.valueOf((String)parameterObj);
                    break;
                }
                default: {
                    parameterAsNum = new BigDecimal((String)parameterObj);
                    break;
                }
            }
        } else {
            parameterAsNum = (Number)parameterObj;
        }
        switch (targetMysqlType) {
            case YEAR: 
            case BIT: 
            case TINYINT: 
            case TINYINT_UNSIGNED: 
            case SMALLINT: 
            case SMALLINT_UNSIGNED: 
            case INT: 
            case INT_UNSIGNED: 
            case MEDIUMINT: 
            case MEDIUMINT_UNSIGNED: {
                this.setInt(parameterIndex, parameterAsNum.intValue());
                break;
            }
            case BIGINT: 
            case BIGINT_UNSIGNED: {
                this.setLong(parameterIndex, parameterAsNum.longValue());
                break;
            }
            case FLOAT: 
            case FLOAT_UNSIGNED: {
                this.setFloat(parameterIndex, parameterAsNum.floatValue());
                break;
            }
            case DOUBLE: 
            case DOUBLE_UNSIGNED: {
                this.setDouble(parameterIndex, parameterAsNum.doubleValue());
                break;
            }
            case DECIMAL: 
            case DECIMAL_UNSIGNED: {
                if (parameterAsNum instanceof BigDecimal) {
                    BigDecimal scaledBigDecimal = null;
                    try {
                        scaledBigDecimal = ((BigDecimal)parameterAsNum).setScale(scale);
                    } catch (ArithmeticException ex) {
                        try {
                            scaledBigDecimal = ((BigDecimal)parameterAsNum).setScale(scale, 4);
                        } catch (ArithmeticException arEx) {
                            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.65", new Object[]{scale, parameterAsNum}), this.session.getExceptionInterceptor());
                        }
                    }
                    this.setBigDecimal(parameterIndex, scaledBigDecimal);
                    break;
                }
                if (parameterAsNum instanceof BigInteger) {
                    this.setBigDecimal(parameterIndex, new BigDecimal((BigInteger)parameterAsNum, scale));
                    break;
                }
                this.setBigDecimal(parameterIndex, new BigDecimal(parameterAsNum.doubleValue()));
                break;
            }
        }
    }

    protected final void setSerializableObject(int parameterIndex, Object parameterObj) {
        try {
            ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
            ObjectOutputStream objectOut = new ObjectOutputStream(bytesOut);
            objectOut.writeObject(parameterObj);
            objectOut.flush();
            objectOut.close();
            bytesOut.flush();
            bytesOut.close();
            byte[] buf = bytesOut.toByteArray();
            ByteArrayInputStream bytesIn = new ByteArrayInputStream(buf);
            this.setBinaryStream(parameterIndex, (InputStream)bytesIn, buf.length);
            this.bindValues[parameterIndex].setMysqlType(MysqlType.BINARY);
        } catch (Exception ex) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.54") + ex.getClass().getName(), ex, this.session.getExceptionInterceptor());
        }
    }
}

