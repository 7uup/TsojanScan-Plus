/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.parser.deserializer.ContextObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.ContextObjectSerializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Jdk8DateCodec
extends ContextObjectDeserializer
implements ObjectSerializer,
ContextObjectSerializer,
ObjectDeserializer {
    public static final Jdk8DateCodec instance = new Jdk8DateCodec();
    private static final String defaultPatttern = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter defaultFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter defaultFormatter_23 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private static final DateTimeFormatter formatter_dt19_tw = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static final DateTimeFormatter formatter_dt19_cn = DateTimeFormatter.ofPattern("yyyy\u5e74M\u6708d\u65e5 HH:mm:ss");
    private static final DateTimeFormatter formatter_dt19_cn_1 = DateTimeFormatter.ofPattern("yyyy\u5e74M\u6708d\u65e5 H\u65f6m\u5206s\u79d2");
    private static final DateTimeFormatter formatter_dt19_kr = DateTimeFormatter.ofPattern("yyyy\ub144M\uc6d4d\uc77c HH:mm:ss");
    private static final DateTimeFormatter formatter_dt19_us = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
    private static final DateTimeFormatter formatter_dt19_eur = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final DateTimeFormatter formatter_dt19_de = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    private static final DateTimeFormatter formatter_dt19_in = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private static final DateTimeFormatter formatter_d8 = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter formatter_d10_tw = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private static final DateTimeFormatter formatter_d10_cn = DateTimeFormatter.ofPattern("yyyy\u5e74M\u6708d\u65e5");
    private static final DateTimeFormatter formatter_d10_kr = DateTimeFormatter.ofPattern("yyyy\ub144M\uc6d4d\uc77c");
    private static final DateTimeFormatter formatter_d10_us = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final DateTimeFormatter formatter_d10_eur = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter formatter_d10_de = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter formatter_d10_in = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter ISO_FIXED_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());
    private static final String formatter_iso8601_pattern = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String formatter_iso8601_pattern_23 = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    private static final String formatter_iso8601_pattern_29 = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS";
    private static final DateTimeFormatter formatter_iso8601 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName, String format, int feature) {
        JSONLexer lexer = parser.lexer;
        if (lexer.token() == 8) {
            lexer.nextToken();
            return null;
        }
        if (lexer.token() == 4) {
            String text = lexer.stringVal();
            lexer.nextToken();
            DateTimeFormatter formatter = null;
            if (format != null) {
                formatter = defaultPatttern.equals(format) ? defaultFormatter : DateTimeFormatter.ofPattern(format);
            }
            if ("".equals(text)) {
                return null;
            }
            if (type == LocalDateTime.class) {
                LocalDateTime localDateTime;
                if (text.length() == 10 || text.length() == 8) {
                    LocalDate localDate = this.parseLocalDate(text, format, formatter);
                    localDateTime = LocalDateTime.of(localDate, LocalTime.MIN);
                } else {
                    localDateTime = this.parseDateTime(text, formatter);
                }
                return (T)localDateTime;
            }
            if (type == LocalDate.class) {
                LocalDate localDate;
                if (text.length() == 23) {
                    LocalDateTime localDateTime = LocalDateTime.parse(text);
                    localDate = LocalDate.of(localDateTime.getYear(), localDateTime.getMonthValue(), localDateTime.getDayOfMonth());
                } else {
                    localDate = this.parseLocalDate(text, format, formatter);
                }
                return (T)localDate;
            }
            if (type == LocalTime.class) {
                LocalTime localTime;
                if (text.length() == 23) {
                    LocalDateTime localDateTime = LocalDateTime.parse(text);
                    localTime = LocalTime.of(localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond(), localDateTime.getNano());
                } else {
                    boolean digit = true;
                    for (int i = 0; i < text.length(); ++i) {
                        char ch = text.charAt(i);
                        if (ch >= '0' && ch <= '9') continue;
                        digit = false;
                        break;
                    }
                    if (digit && text.length() > 8 && text.length() < 19) {
                        long epochMillis = Long.parseLong(text);
                        localTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), JSON.defaultTimeZone.toZoneId()).toLocalTime();
                    } else {
                        localTime = LocalTime.parse(text);
                    }
                }
                return (T)localTime;
            }
            if (type == ZonedDateTime.class) {
                if (formatter == defaultFormatter) {
                    formatter = ISO_FIXED_FORMAT;
                }
                if (formatter == null && text.length() <= 19) {
                    JSONScanner s2 = new JSONScanner(text);
                    TimeZone timeZone = parser.lexer.getTimeZone();
                    s2.setTimeZone(timeZone);
                    boolean match = s2.scanISO8601DateIfMatch(false);
                    if (match) {
                        Date date = s2.getCalendar().getTime();
                        return (T)ZonedDateTime.ofInstant(date.toInstant(), timeZone.toZoneId());
                    }
                }
                ZonedDateTime zonedDateTime = this.parseZonedDateTime(text, formatter);
                return (T)zonedDateTime;
            }
            if (type == OffsetDateTime.class) {
                OffsetDateTime offsetDateTime = OffsetDateTime.parse(text);
                return (T)offsetDateTime;
            }
            if (type == OffsetTime.class) {
                OffsetTime offsetTime = OffsetTime.parse(text);
                return (T)offsetTime;
            }
            if (type == ZoneId.class) {
                ZoneId offsetTime = ZoneId.of(text);
                return (T)offsetTime;
            }
            if (type == Period.class) {
                Period period = Period.parse(text);
                return (T)period;
            }
            if (type == Duration.class) {
                Duration duration = Duration.parse(text);
                return (T)duration;
            }
            if (type == Instant.class) {
                boolean digit = true;
                for (int i = 0; i < text.length(); ++i) {
                    char ch = text.charAt(i);
                    if (ch >= '0' && ch <= '9') continue;
                    digit = false;
                    break;
                }
                if (digit && text.length() > 8 && text.length() < 19) {
                    long epochMillis = Long.parseLong(text);
                    return (T)Instant.ofEpochMilli(epochMillis);
                }
                Instant instant = Instant.parse(text);
                return (T)instant;
            }
        } else {
            if (lexer.token() == 2) {
                long millis = lexer.longValue();
                lexer.nextToken();
                if ("unixtime".equals(format)) {
                    millis *= 1000L;
                } else if ("yyyyMMddHHmmss".equals(format)) {
                    int yyyy = (int)(millis / 10000000000L);
                    int MM = (int)(millis / 100000000L % 100L);
                    int dd = (int)(millis / 1000000L % 100L);
                    int HH = (int)(millis / 10000L % 100L);
                    int mm3 = (int)(millis / 100L % 100L);
                    int ss = (int)(millis % 100L);
                    if (type == LocalDateTime.class) {
                        return (T)LocalDateTime.of(yyyy, MM, dd, HH, mm3, ss);
                    }
                }
                if (type == LocalDateTime.class) {
                    return (T)LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), JSON.defaultTimeZone.toZoneId());
                }
                if (type == LocalDate.class) {
                    return (T)LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), JSON.defaultTimeZone.toZoneId()).toLocalDate();
                }
                if (type == LocalTime.class) {
                    return (T)LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), JSON.defaultTimeZone.toZoneId()).toLocalTime();
                }
                if (type == ZonedDateTime.class) {
                    return (T)ZonedDateTime.ofInstant(Instant.ofEpochMilli(millis), JSON.defaultTimeZone.toZoneId());
                }
                if (type == Instant.class) {
                    return (T)Instant.ofEpochMilli(millis);
                }
                throw new UnsupportedOperationException();
            }
            if (lexer.token() == 12) {
                Long seconds;
                JSONObject object = parser.parseObject();
                if (type == Instant.class) {
                    Object epochSecond = object.get("epochSecond");
                    Object nano = object.get("nano");
                    if (epochSecond instanceof Number && nano instanceof Number) {
                        return (T)Instant.ofEpochSecond(TypeUtils.longExtractValue((Number)epochSecond), TypeUtils.longExtractValue((Number)nano));
                    }
                    if (epochSecond instanceof Number) {
                        return (T)Instant.ofEpochSecond(TypeUtils.longExtractValue((Number)epochSecond));
                    }
                } else if (type == Duration.class && (seconds = object.getLong("seconds")) != null) {
                    long nanos = object.getLongValue("nano");
                    return (T)Duration.ofSeconds(seconds, nanos);
                }
            } else {
                throw new UnsupportedOperationException();
            }
        }
        return null;
    }

    protected LocalDateTime parseDateTime(String text, DateTimeFormatter formatter) {
        if (formatter == null) {
            char c16;
            char c13;
            char c10;
            char c7;
            char c4;
            if (text.length() == 19) {
                c4 = text.charAt(4);
                c7 = text.charAt(7);
                c10 = text.charAt(10);
                c13 = text.charAt(13);
                c16 = text.charAt(16);
                if (c13 == ':' && c16 == ':') {
                    if (c4 == '-' && c7 == '-') {
                        if (c10 == 'T') {
                            formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                        } else if (c10 == ' ') {
                            formatter = defaultFormatter;
                        }
                    } else if (c4 == '/' && c7 == '/') {
                        formatter = formatter_dt19_tw;
                    } else {
                        char c0 = text.charAt(0);
                        char c1 = text.charAt(1);
                        char c2 = text.charAt(2);
                        char c3 = text.charAt(3);
                        char c5 = text.charAt(5);
                        if (c2 == '/' && c5 == '/') {
                            int v0 = (c0 - 48) * 10 + (c1 - 48);
                            int v1 = (c3 - 48) * 10 + (c4 - 48);
                            if (v0 > 12) {
                                formatter = formatter_dt19_eur;
                            } else if (v1 > 12) {
                                formatter = formatter_dt19_us;
                            } else {
                                String country = Locale.getDefault().getCountry();
                                if (country.equals("US")) {
                                    formatter = formatter_dt19_us;
                                } else if (country.equals("BR") || country.equals("AU")) {
                                    formatter = formatter_dt19_eur;
                                }
                            }
                        } else if (c2 == '.' && c5 == '.') {
                            formatter = formatter_dt19_de;
                        } else if (c2 == '-' && c5 == '-') {
                            formatter = formatter_dt19_in;
                        }
                    }
                }
            } else if (text.length() == 23) {
                c4 = text.charAt(4);
                c7 = text.charAt(7);
                c10 = text.charAt(10);
                c13 = text.charAt(13);
                c16 = text.charAt(16);
                char c19 = text.charAt(19);
                if (c13 == ':' && c16 == ':' && c4 == '-' && c7 == '-' && c10 == ' ' && c19 == '.') {
                    formatter = defaultFormatter_23;
                }
            }
            if (text.length() >= 17) {
                c4 = text.charAt(4);
                if (c4 == '\u5e74') {
                    formatter = text.charAt(text.length() - 1) == '\u79d2' ? formatter_dt19_cn_1 : formatter_dt19_cn;
                } else if (c4 == '\ub144') {
                    formatter = formatter_dt19_kr;
                }
            }
        }
        if (formatter == null) {
            JSONScanner dateScanner = new JSONScanner(text);
            if (dateScanner.scanISO8601DateIfMatch(false)) {
                Instant instant = dateScanner.getCalendar().toInstant();
                return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            }
            boolean digit = true;
            for (int i = 0; i < text.length(); ++i) {
                char ch = text.charAt(i);
                if (ch >= '0' && ch <= '9') continue;
                digit = false;
                break;
            }
            if (digit && text.length() > 8 && text.length() < 19) {
                long epochMillis = Long.parseLong(text);
                return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), JSON.defaultTimeZone.toZoneId());
            }
        }
        return formatter == null ? LocalDateTime.parse(text) : LocalDateTime.parse(text, formatter);
    }

    protected LocalDate parseLocalDate(String text, String format, DateTimeFormatter formatter) {
        if (formatter == null) {
            char c4;
            if (text.length() == 8) {
                formatter = formatter_d8;
            }
            if (text.length() == 10) {
                c4 = text.charAt(4);
                char c7 = text.charAt(7);
                if (c4 == '/' && c7 == '/') {
                    formatter = formatter_d10_tw;
                }
                char c0 = text.charAt(0);
                char c1 = text.charAt(1);
                char c2 = text.charAt(2);
                char c3 = text.charAt(3);
                char c5 = text.charAt(5);
                if (c2 == '/' && c5 == '/') {
                    int v0 = (c0 - 48) * 10 + (c1 - 48);
                    int v1 = (c3 - 48) * 10 + (c4 - 48);
                    if (v0 > 12) {
                        formatter = formatter_d10_eur;
                    } else if (v1 > 12) {
                        formatter = formatter_d10_us;
                    } else {
                        String country = Locale.getDefault().getCountry();
                        if (country.equals("US")) {
                            formatter = formatter_d10_us;
                        } else if (country.equals("BR") || country.equals("AU")) {
                            formatter = formatter_d10_eur;
                        }
                    }
                } else if (c2 == '.' && c5 == '.') {
                    formatter = formatter_d10_de;
                } else if (c2 == '-' && c5 == '-') {
                    formatter = formatter_d10_in;
                }
            }
            if (text.length() >= 9) {
                c4 = text.charAt(4);
                if (c4 == '\u5e74') {
                    formatter = formatter_d10_cn;
                } else if (c4 == '\ub144') {
                    formatter = formatter_d10_kr;
                }
            }
            boolean digit = true;
            for (int i = 0; i < text.length(); ++i) {
                char ch = text.charAt(i);
                if (ch >= '0' && ch <= '9') continue;
                digit = false;
                break;
            }
            if (digit && text.length() > 8 && text.length() < 19) {
                long epochMillis = Long.parseLong(text);
                return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), JSON.defaultTimeZone.toZoneId()).toLocalDate();
            }
        }
        return formatter == null ? LocalDate.parse(text) : LocalDate.parse(text, formatter);
    }

    protected ZonedDateTime parseZonedDateTime(String text, DateTimeFormatter formatter) {
        if (formatter == null) {
            char c4;
            if (text.length() == 19) {
                c4 = text.charAt(4);
                char c7 = text.charAt(7);
                char c10 = text.charAt(10);
                char c13 = text.charAt(13);
                char c16 = text.charAt(16);
                if (c13 == ':' && c16 == ':') {
                    if (c4 == '-' && c7 == '-') {
                        if (c10 == 'T') {
                            formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                        } else if (c10 == ' ') {
                            formatter = defaultFormatter;
                        }
                    } else if (c4 == '/' && c7 == '/') {
                        formatter = formatter_dt19_tw;
                    } else {
                        char c0 = text.charAt(0);
                        char c1 = text.charAt(1);
                        char c2 = text.charAt(2);
                        char c3 = text.charAt(3);
                        char c5 = text.charAt(5);
                        if (c2 == '/' && c5 == '/') {
                            int v0 = (c0 - 48) * 10 + (c1 - 48);
                            int v1 = (c3 - 48) * 10 + (c4 - 48);
                            if (v0 > 12) {
                                formatter = formatter_dt19_eur;
                            } else if (v1 > 12) {
                                formatter = formatter_dt19_us;
                            } else {
                                String country = Locale.getDefault().getCountry();
                                if (country.equals("US")) {
                                    formatter = formatter_dt19_us;
                                } else if (country.equals("BR") || country.equals("AU")) {
                                    formatter = formatter_dt19_eur;
                                }
                            }
                        } else if (c2 == '.' && c5 == '.') {
                            formatter = formatter_dt19_de;
                        } else if (c2 == '-' && c5 == '-') {
                            formatter = formatter_dt19_in;
                        }
                    }
                }
            }
            if (text.length() >= 17) {
                c4 = text.charAt(4);
                if (c4 == '\u5e74') {
                    formatter = text.charAt(text.length() - 1) == '\u79d2' ? formatter_dt19_cn_1 : formatter_dt19_cn;
                } else if (c4 == '\ub144') {
                    formatter = formatter_dt19_kr;
                }
            }
            boolean digit = true;
            for (int i = 0; i < text.length(); ++i) {
                char ch = text.charAt(i);
                if (ch >= '0' && ch <= '9') continue;
                digit = false;
                break;
            }
            if (digit && text.length() > 8 && text.length() < 19) {
                long epochMillis = Long.parseLong(text);
                return ZonedDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), JSON.defaultTimeZone.toZoneId());
            }
        }
        return formatter == null ? ZonedDateTime.parse(text) : ZonedDateTime.parse(text, formatter);
    }

    @Override
    public int getFastMatchToken() {
        return 4;
    }

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull();
        } else {
            if (fieldType == null) {
                fieldType = object.getClass();
            }
            if (fieldType == LocalDateTime.class) {
                int mask = SerializerFeature.UseISO8601DateFormat.getMask();
                LocalDateTime dateTime = (LocalDateTime)object;
                String format = serializer.getDateFormatPattern();
                if (format == null) {
                    int nano;
                    format = (features & mask) != 0 || serializer.isEnabled(SerializerFeature.UseISO8601DateFormat) ? formatter_iso8601_pattern : (serializer.isEnabled(SerializerFeature.WriteDateUseDateFormat) ? (serializer.getFastJsonConfigDateFormatPattern() != null && serializer.getFastJsonConfigDateFormatPattern().length() > 0 ? serializer.getFastJsonConfigDateFormatPattern() : JSON.DEFFAULT_DATE_FORMAT) : ((nano = dateTime.getNano()) == 0 ? formatter_iso8601_pattern : (nano % 1000000 == 0 ? formatter_iso8601_pattern_23 : formatter_iso8601_pattern_29)));
                }
                if (format != null) {
                    this.write(out, dateTime, format);
                } else {
                    out.writeLong(dateTime.atZone(JSON.defaultTimeZone.toZoneId()).toInstant().toEpochMilli());
                }
            } else {
                out.writeString(object.toString());
            }
        }
    }

    @Override
    public void write(JSONSerializer serializer, Object object, BeanContext context) throws IOException {
        SerializeWriter out = serializer.out;
        String format = context.getFormat();
        this.write(out, (TemporalAccessor)object, format);
    }

    private void write(SerializeWriter out, TemporalAccessor object, String format) {
        Instant instant;
        if ("unixtime".equals(format)) {
            instant = null;
            if (object instanceof ChronoZonedDateTime) {
                long seconds = ((ChronoZonedDateTime)object).toEpochSecond();
                out.writeInt((int)seconds);
                return;
            }
            if (object instanceof LocalDateTime) {
                long seconds = ((LocalDateTime)object).atZone(JSON.defaultTimeZone.toZoneId()).toEpochSecond();
                out.writeInt((int)seconds);
                return;
            }
        }
        if ("millis".equals(format)) {
            instant = null;
            if (object instanceof ChronoZonedDateTime) {
                instant = ((ChronoZonedDateTime)object).toInstant();
            } else if (object instanceof LocalDateTime) {
                instant = ((LocalDateTime)object).atZone(JSON.defaultTimeZone.toZoneId()).toInstant();
            }
            if (instant != null) {
                long millis = instant.toEpochMilli();
                out.writeLong(millis);
                return;
            }
        }
        DateTimeFormatter formatter = format == formatter_iso8601_pattern ? formatter_iso8601 : DateTimeFormatter.ofPattern(format);
        String text = formatter.format(object);
        out.writeString(text);
    }

    public static Object castToLocalDateTime(Object value, String format) {
        if (value == null) {
            return null;
        }
        if (format == null) {
            format = defaultPatttern;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(value.toString(), df);
    }
}

