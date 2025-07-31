/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.parser.deserializer.AbstractDateDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class DateCodec
extends AbstractDateDeserializer
implements ObjectSerializer,
ObjectDeserializer {
    public static final DateCodec instance = new DateCodec();

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        TimeZone timeZone;
        int offset;
        long millis;
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull();
            return;
        }
        Class<?> clazz = object.getClass();
        if (clazz == Date.class && !out.isEnabled(SerializerFeature.WriteDateUseDateFormat) && ((millis = ((Date)object).getTime()) + (long)(offset = (timeZone = serializer.timeZone).getOffset(millis))) % 86400000L == 0L && !SerializerFeature.isEnabled(out.features, features, SerializerFeature.WriteClassName)) {
            out.writeString(object.toString());
            return;
        }
        if (clazz == Time.class) {
            millis = ((Time)object).getTime();
            if ("unixtime".equals(serializer.getDateFormatPattern())) {
                long seconds = millis / 1000L;
                out.writeLong(seconds);
                return;
            }
            if ("millis".equals(serializer.getDateFormatPattern())) {
                long seconds = millis;
                out.writeLong(millis);
                return;
            }
            if (millis < 86400000L) {
                out.writeString(object.toString());
                return;
            }
        }
        int nanos = 0;
        if (clazz == Timestamp.class) {
            Timestamp ts = (Timestamp)object;
            nanos = ts.getNanos();
        }
        java.util.Date date = object instanceof java.util.Date ? (java.util.Date)object : TypeUtils.castToDate(object);
        if ("unixtime".equals(serializer.getDateFormatPattern())) {
            long seconds = date.getTime() / 1000L;
            out.writeLong(seconds);
            return;
        }
        if ("millis".equals(serializer.getDateFormatPattern())) {
            long millis2 = date.getTime();
            out.writeLong(millis2);
            return;
        }
        if (out.isEnabled(SerializerFeature.WriteDateUseDateFormat)) {
            DateFormat format = serializer.getDateFormat();
            if (format == null) {
                String dateFormatPattern = serializer.getFastJsonConfigDateFormatPattern();
                if (dateFormatPattern == null) {
                    dateFormatPattern = JSON.DEFFAULT_DATE_FORMAT;
                }
                format = new SimpleDateFormat(dateFormatPattern, serializer.locale);
                format.setTimeZone(serializer.timeZone);
            }
            String text = format.format(date);
            out.writeString(text);
            return;
        }
        if (out.isEnabled(SerializerFeature.WriteClassName) && clazz != fieldType) {
            if (clazz == java.util.Date.class) {
                out.write("new Date(");
                out.writeLong(((java.util.Date)object).getTime());
                out.write(41);
            } else {
                out.write(123);
                out.writeFieldName(JSON.DEFAULT_TYPE_KEY);
                serializer.write(clazz.getName());
                out.writeFieldValue(',', "val", ((java.util.Date)object).getTime());
                out.write(125);
            }
            return;
        }
        long time = date.getTime();
        if (out.isEnabled(SerializerFeature.UseISO8601DateFormat)) {
            char[] buf;
            int quote = out.isEnabled(SerializerFeature.UseSingleQuotes) ? 39 : 34;
            out.write(quote);
            Calendar calendar = Calendar.getInstance(serializer.timeZone, serializer.locale);
            calendar.setTimeInMillis(time);
            int year = calendar.get(1);
            int month = calendar.get(2) + 1;
            int day = calendar.get(5);
            int hour = calendar.get(11);
            int minute = calendar.get(12);
            int second = calendar.get(13);
            int millis3 = calendar.get(14);
            if (nanos > 0) {
                buf = "0000-00-00 00:00:00.000000000".toCharArray();
                IOUtils.getChars(nanos, 29, buf);
                IOUtils.getChars(second, 19, buf);
                IOUtils.getChars(minute, 16, buf);
                IOUtils.getChars(hour, 13, buf);
                IOUtils.getChars(day, 10, buf);
                IOUtils.getChars(month, 7, buf);
                IOUtils.getChars(year, 4, buf);
            } else if (millis3 != 0) {
                buf = "0000-00-00T00:00:00.000".toCharArray();
                IOUtils.getChars(millis3, 23, buf);
                IOUtils.getChars(second, 19, buf);
                IOUtils.getChars(minute, 16, buf);
                IOUtils.getChars(hour, 13, buf);
                IOUtils.getChars(day, 10, buf);
                IOUtils.getChars(month, 7, buf);
                IOUtils.getChars(year, 4, buf);
            } else if (second == 0 && minute == 0 && hour == 0) {
                buf = "0000-00-00".toCharArray();
                IOUtils.getChars(day, 10, buf);
                IOUtils.getChars(month, 7, buf);
                IOUtils.getChars(year, 4, buf);
            } else {
                buf = "0000-00-00T00:00:00".toCharArray();
                IOUtils.getChars(second, 19, buf);
                IOUtils.getChars(minute, 16, buf);
                IOUtils.getChars(hour, 13, buf);
                IOUtils.getChars(day, 10, buf);
                IOUtils.getChars(month, 7, buf);
                IOUtils.getChars(year, 4, buf);
            }
            if (nanos > 0) {
                int off;
                int i;
                for (i = 0; i < 9 && buf[off = buf.length - i - 1] == '0'; ++i) {
                }
                out.write(buf, 0, buf.length - i);
                out.write(quote);
                return;
            }
            out.write(buf);
            float timeZoneF = (float)calendar.getTimeZone().getOffset(calendar.getTimeInMillis()) / 3600000.0f;
            int timeZone2 = (int)timeZoneF;
            if ((double)timeZone2 == 0.0) {
                out.write(90);
            } else {
                if (timeZone2 > 9) {
                    out.write(43);
                    out.writeInt(timeZone2);
                } else if (timeZone2 > 0) {
                    out.write(43);
                    out.write(48);
                    out.writeInt(timeZone2);
                } else if (timeZone2 < -9) {
                    out.write(45);
                    out.writeInt(-timeZone2);
                } else if (timeZone2 < 0) {
                    out.write(45);
                    out.write(48);
                    out.writeInt(-timeZone2);
                }
                out.write(58);
                int offSet = (int)(Math.abs(timeZoneF - (float)timeZone2) * 60.0f);
                out.append(String.format("%02d", offSet));
            }
            out.write(quote);
        } else {
            out.writeLong(time);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public <T> T cast(DefaultJSONParser parser, Type clazz, Object fieldName, Object val) {
        if (val == null) {
            return null;
        }
        if (val instanceof java.util.Date) {
            return (T)val;
        }
        if (val instanceof BigDecimal) {
            return (T)new java.util.Date(TypeUtils.longValue((BigDecimal)val));
        }
        if (val instanceof Number) {
            return (T)new java.util.Date(((Number)val).longValue());
        }
        if (val instanceof String) {
            String tzStr;
            TimeZone timeZone;
            boolean formatMatch;
            String strVal = (String)val;
            if (strVal.length() == 0) {
                return null;
            }
            if (strVal.length() == 23 && strVal.endsWith(" 000")) {
                strVal = strVal.substring(0, 19);
            }
            JSONScanner dateLexer = new JSONScanner(strVal);
            try {
                if (dateLexer.scanISO8601DateIfMatch(false)) {
                    Calendar calendar = dateLexer.getCalendar();
                    if (clazz == Calendar.class) {
                        Calendar calendar2 = calendar;
                        return (T)calendar2;
                    }
                    java.util.Date date = calendar.getTime();
                    return (T)date;
                }
            } finally {
                dateLexer.close();
            }
            String dateFomartPattern = parser.getDateFomartPattern();
            boolean bl = formatMatch = strVal.length() == dateFomartPattern.length() || strVal.length() == 22 && dateFomartPattern.equals("yyyyMMddHHmmssSSSZ") || strVal.indexOf(84) != -1 && dateFomartPattern.contains("'T'") && strVal.length() + 2 == dateFomartPattern.length();
            if (formatMatch) {
                DateFormat dateFormat = parser.getDateFormat();
                try {
                    return (T)dateFormat.parse(strVal);
                } catch (ParseException parseException) {
                    // empty catch block
                }
            }
            if (strVal.startsWith("/Date(") && strVal.endsWith(")/")) {
                String dotnetDateStr;
                strVal = dotnetDateStr = strVal.substring(6, strVal.length() - 2);
            }
            if ("0000-00-00".equals(strVal) || "0000-00-00T00:00:00".equalsIgnoreCase(strVal) || "0001-01-01T00:00:00+08:00".equalsIgnoreCase(strVal)) {
                return null;
            }
            int index = strVal.lastIndexOf(124);
            if (index > 20 && !"GMT".equals((timeZone = TimeZone.getTimeZone(tzStr = strVal.substring(index + 1))).getID())) {
                String subStr = strVal.substring(0, index);
                JSONScanner dateLexer2 = new JSONScanner(subStr);
                try {
                    if (dateLexer2.scanISO8601DateIfMatch(false)) {
                        Calendar calendar = dateLexer2.getCalendar();
                        calendar.setTimeZone(timeZone);
                        if (clazz == Calendar.class) {
                            Calendar calendar3 = calendar;
                            return (T)calendar3;
                        }
                        java.util.Date date = calendar.getTime();
                        return (T)date;
                    }
                } finally {
                    dateLexer2.close();
                }
            }
            long longVal = Long.parseLong(strVal);
            return (T)new java.util.Date(longVal);
        }
        throw new JSONException("parse error");
    }

    @Override
    public int getFastMatchToken() {
        return 2;
    }
}

