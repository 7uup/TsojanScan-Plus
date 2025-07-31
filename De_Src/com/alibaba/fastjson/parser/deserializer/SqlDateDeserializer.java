/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.parser.deserializer.AbstractDateDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SqlDateDeserializer
extends AbstractDateDeserializer
implements ObjectDeserializer {
    public static final SqlDateDeserializer instance = new SqlDateDeserializer();
    public static final SqlDateDeserializer instance_timestamp = new SqlDateDeserializer(true);
    private boolean timestamp = false;

    public SqlDateDeserializer() {
    }

    public SqlDateDeserializer(boolean timestmap) {
        this.timestamp = true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected <T> T cast(DefaultJSONParser parser, Type clazz, Object fieldName, Object val) {
        if (this.timestamp) {
            return this.castTimestamp(parser, clazz, fieldName, val);
        }
        if (val == null) {
            return null;
        }
        if (val instanceof Date) {
            val = new java.sql.Date(((Date)val).getTime());
        } else if (val instanceof BigDecimal) {
            val = new java.sql.Date(TypeUtils.longValue((BigDecimal)val));
        } else if (val instanceof Number) {
            val = new java.sql.Date(((Number)val).longValue());
        } else {
            if (val instanceof String) {
                long longVal;
                block17: {
                    String strVal = (String)val;
                    if (strVal.length() == 0) {
                        return null;
                    }
                    JSONScanner dateLexer = new JSONScanner(strVal);
                    try {
                        java.sql.Date date;
                        if (dateLexer.scanISO8601DateIfMatch()) {
                            longVal = dateLexer.getCalendar().getTimeInMillis();
                            break block17;
                        }
                        DateFormat dateFormat = parser.getDateFormat();
                        try {
                            java.sql.Date sqlDate;
                            Date date2 = dateFormat.parse(strVal);
                            date = sqlDate = new java.sql.Date(date2.getTime());
                        } catch (ParseException parseException) {
                            longVal = Long.parseLong(strVal);
                            break block17;
                        }
                        return (T)date;
                    } finally {
                        dateLexer.close();
                    }
                }
                return (T)new java.sql.Date(longVal);
            }
            throw new JSONException("parse error : " + val);
        }
        return (T)val;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected <T> T castTimestamp(DefaultJSONParser parser, Type clazz, Object fieldName, Object val) {
        if (val == null) {
            return null;
        }
        if (val instanceof Date) {
            return (T)new Timestamp(((Date)val).getTime());
        }
        if (val instanceof BigDecimal) {
            return (T)new Timestamp(TypeUtils.longValue((BigDecimal)val));
        }
        if (val instanceof Number) {
            return (T)new Timestamp(((Number)val).longValue());
        }
        if (val instanceof String) {
            long longVal;
            block15: {
                String strVal = (String)val;
                if (strVal.length() == 0) {
                    return null;
                }
                JSONScanner dateLexer = new JSONScanner(strVal);
                try {
                    Timestamp timestamp;
                    String dateFomartPattern;
                    if (strVal.length() > 19 && strVal.charAt(4) == '-' && strVal.charAt(7) == '-' && strVal.charAt(10) == ' ' && strVal.charAt(13) == ':' && strVal.charAt(16) == ':' && strVal.charAt(19) == '.' && (dateFomartPattern = parser.getDateFomartPattern()).length() != strVal.length() && dateFomartPattern == JSON.DEFFAULT_DATE_FORMAT) {
                        Timestamp timestamp2 = Timestamp.valueOf(strVal);
                        return (T)timestamp2;
                    }
                    if (dateLexer.scanISO8601DateIfMatch(false)) {
                        longVal = dateLexer.getCalendar().getTimeInMillis();
                        break block15;
                    }
                    DateFormat dateFormat = parser.getDateFormat();
                    try {
                        Timestamp sqlDate;
                        Date date = dateFormat.parse(strVal);
                        timestamp = sqlDate = new Timestamp(date.getTime());
                    } catch (ParseException parseException) {
                        longVal = Long.parseLong(strVal);
                        break block15;
                    }
                    return (T)timestamp;
                } finally {
                    dateLexer.close();
                }
            }
            return (T)new Timestamp(longVal);
        }
        throw new JSONException("parse error");
    }

    @Override
    public int getFastMatchToken() {
        return 2;
    }
}

