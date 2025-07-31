/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ContextObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.ContextObjectSerializer;
import com.alibaba.fastjson.serializer.DateCodec;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.IOUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class CalendarCodec
extends ContextObjectDeserializer
implements ObjectSerializer,
ObjectDeserializer,
ContextObjectSerializer {
    public static final CalendarCodec instance = new CalendarCodec();
    private DatatypeFactory dateFactory;

    @Override
    public void write(JSONSerializer serializer, Object object, BeanContext context) throws IOException {
        SerializeWriter out = serializer.out;
        String format = context.getFormat();
        Calendar calendar = (Calendar)object;
        if (format.equals("unixtime")) {
            long seconds = calendar.getTimeInMillis() / 1000L;
            out.writeInt((int)seconds);
            return;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat(JSON.DEFFAULT_DATE_FORMAT, serializer.locale);
        }
        dateFormat.setTimeZone(serializer.timeZone);
        String text = dateFormat.format(calendar.getTime());
        out.writeString(text);
    }

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull();
            return;
        }
        Calendar calendar = object instanceof XMLGregorianCalendar ? ((XMLGregorianCalendar)object).toGregorianCalendar() : (Calendar)object;
        if (out.isEnabled(SerializerFeature.UseISO8601DateFormat)) {
            char[] buf;
            char quote = out.isEnabled(SerializerFeature.UseSingleQuotes) ? (char)'\'' : '\"';
            out.append(quote);
            int year = calendar.get(1);
            int month = calendar.get(2) + 1;
            int day = calendar.get(5);
            int hour = calendar.get(11);
            int minute = calendar.get(12);
            int second = calendar.get(13);
            int millis = calendar.get(14);
            if (millis != 0) {
                buf = "0000-00-00T00:00:00.000".toCharArray();
                IOUtils.getChars(millis, 23, buf);
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
            out.write(buf);
            float timeZoneF = (float)calendar.getTimeZone().getOffset(calendar.getTimeInMillis()) / 3600000.0f;
            int timeZone = (int)timeZoneF;
            if ((double)timeZone == 0.0) {
                out.write(90);
            } else {
                if (timeZone > 9) {
                    out.write(43);
                    out.writeInt(timeZone);
                } else if (timeZone > 0) {
                    out.write(43);
                    out.write(48);
                    out.writeInt(timeZone);
                } else if (timeZone < -9) {
                    out.write(45);
                    out.writeInt(timeZone);
                } else if (timeZone < 0) {
                    out.write(45);
                    out.write(48);
                    out.writeInt(-timeZone);
                }
                out.write(58);
                int offSet = (int)((timeZoneF - (float)timeZone) * 60.0f);
                out.append(String.format("%02d", offSet));
            }
            out.append(quote);
        } else {
            Date date = calendar.getTime();
            serializer.write(date);
        }
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        return this.deserialze(parser, clazz, fieldName, null, 0);
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName, String format, int features) {
        Object value = DateCodec.instance.deserialze(parser, type, fieldName, format, features);
        if (value instanceof Calendar) {
            return value;
        }
        Date date = (Date)value;
        if (date == null) {
            return null;
        }
        JSONLexer lexer = parser.lexer;
        Calendar calendar = Calendar.getInstance(lexer.getTimeZone(), lexer.getLocale());
        calendar.setTime(date);
        if (type == XMLGregorianCalendar.class) {
            return (T)this.createXMLGregorianCalendar((GregorianCalendar)calendar);
        }
        return (T)calendar;
    }

    public XMLGregorianCalendar createXMLGregorianCalendar(Calendar calendar) {
        if (this.dateFactory == null) {
            try {
                this.dateFactory = DatatypeFactory.newInstance();
            } catch (DatatypeConfigurationException e) {
                throw new IllegalStateException("Could not obtain an instance of DatatypeFactory.", e);
            }
        }
        return this.dateFactory.newXMLGregorianCalendar((GregorianCalendar)calendar);
    }

    @Override
    public int getFastMatchToken() {
        return 2;
    }
}

