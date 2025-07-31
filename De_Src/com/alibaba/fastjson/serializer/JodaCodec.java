/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
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
import java.util.Locale;
import java.util.TimeZone;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.joda.time.ReadablePartial;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class JodaCodec
implements ObjectSerializer,
ContextObjectSerializer,
ObjectDeserializer {
    public static final JodaCodec instance = new JodaCodec();
    private static final String defaultPatttern = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter defaultFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter defaultFormatter_23 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private static final DateTimeFormatter formatter_dt19_tw = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");
    private static final DateTimeFormatter formatter_dt19_cn = DateTimeFormat.forPattern("yyyy\u5e74M\u6708d\u65e5 HH:mm:ss");
    private static final DateTimeFormatter formatter_dt19_cn_1 = DateTimeFormat.forPattern("yyyy\u5e74M\u6708d\u65e5 H\u65f6m\u5206s\u79d2");
    private static final DateTimeFormatter formatter_dt19_kr = DateTimeFormat.forPattern("yyyy\ub144M\uc6d4d\uc77c HH:mm:ss");
    private static final DateTimeFormatter formatter_dt19_us = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
    private static final DateTimeFormatter formatter_dt19_eur = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
    private static final DateTimeFormatter formatter_dt19_de = DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss");
    private static final DateTimeFormatter formatter_dt19_in = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss");
    private static final DateTimeFormatter formatter_d8 = DateTimeFormat.forPattern("yyyyMMdd");
    private static final DateTimeFormatter formatter_d10_tw = DateTimeFormat.forPattern("yyyy/MM/dd");
    private static final DateTimeFormatter formatter_d10_cn = DateTimeFormat.forPattern("yyyy\u5e74M\u6708d\u65e5");
    private static final DateTimeFormatter formatter_d10_kr = DateTimeFormat.forPattern("yyyy\ub144M\uc6d4d\uc77c");
    private static final DateTimeFormatter formatter_d10_us = DateTimeFormat.forPattern("MM/dd/yyyy");
    private static final DateTimeFormatter formatter_d10_eur = DateTimeFormat.forPattern("dd/MM/yyyy");
    private static final DateTimeFormatter formatter_d10_de = DateTimeFormat.forPattern("dd.MM.yyyy");
    private static final DateTimeFormatter formatter_d10_in = DateTimeFormat.forPattern("dd-MM-yyyy");
    private static final DateTimeFormatter ISO_FIXED_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").withZone(DateTimeZone.getDefault());
    private static final String formatter_iso8601_pattern = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String formatter_iso8601_pattern_23 = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    private static final String formatter_iso8601_pattern_29 = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS";
    private static final DateTimeFormatter formatter_iso8601 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        return this.deserialze(parser, type, fieldName, null, 0);
    }

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
                formatter = defaultPatttern.equals(format) ? defaultFormatter : DateTimeFormat.forPattern(format);
            }
            if ("".equals(text)) {
                return null;
            }
            if (type == LocalDateTime.class) {
                LocalDateTime localDateTime;
                if (text.length() == 10 || text.length() == 8) {
                    LocalDate localDate = this.parseLocalDate(text, format, formatter);
                    localDateTime = localDate.toLocalDateTime(LocalTime.MIDNIGHT);
                } else {
                    localDateTime = this.parseDateTime(text, formatter);
                }
                return (T)localDateTime;
            }
            if (type == LocalDate.class) {
                LocalDate localDate;
                if (text.length() == 23) {
                    LocalDateTime localDateTime = LocalDateTime.parse(text);
                    localDate = localDateTime.toLocalDate();
                } else {
                    localDate = this.parseLocalDate(text, format, formatter);
                }
                return (T)localDate;
            }
            if (type == LocalTime.class) {
                LocalTime localDate;
                if (text.length() == 23) {
                    LocalDateTime localDateTime = LocalDateTime.parse(text);
                    localDate = localDateTime.toLocalTime();
                } else {
                    localDate = LocalTime.parse(text);
                }
                return (T)localDate;
            }
            if (type == DateTime.class) {
                if (formatter == defaultFormatter) {
                    formatter = ISO_FIXED_FORMAT;
                }
                DateTime zonedDateTime = this.parseZonedDateTime(text, formatter);
                return (T)zonedDateTime;
            }
            if (type == DateTimeZone.class) {
                DateTimeZone offsetTime = DateTimeZone.forID(text);
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
                    return (T)new Instant(epochMillis);
                }
                Instant instant = Instant.parse(text);
                return (T)instant;
            }
            if (type == DateTimeFormatter.class) {
                return (T)DateTimeFormat.forPattern(text);
            }
        } else {
            if (lexer.token() == 2) {
                long millis = lexer.longValue();
                lexer.nextToken();
                TimeZone timeZone = JSON.defaultTimeZone;
                if (timeZone == null) {
                    timeZone = TimeZone.getDefault();
                }
                if (type == DateTime.class) {
                    return (T)new DateTime(millis, DateTimeZone.forTimeZone(timeZone));
                }
                LocalDateTime localDateTime = new LocalDateTime(millis, DateTimeZone.forTimeZone(timeZone));
                if (type == LocalDateTime.class) {
                    return (T)localDateTime;
                }
                if (type == LocalDate.class) {
                    return (T)localDateTime.toLocalDate();
                }
                if (type == LocalTime.class) {
                    return (T)localDateTime.toLocalTime();
                }
                if (type == Instant.class) {
                    Instant instant = new Instant(millis);
                    return (T)instant;
                }
                throw new UnsupportedOperationException();
            }
            if (lexer.token() == 12) {
                JSONObject object = parser.parseObject();
                if (type == Instant.class) {
                    Object epochSecond = object.get("epochSecond");
                    if (epochSecond instanceof Number) {
                        return (T)Instant.ofEpochSecond(TypeUtils.longExtractValue((Number)epochSecond));
                    }
                    Object millis = object.get("millis");
                    if (millis instanceof Number) {
                        return (T)Instant.ofEpochMilli(TypeUtils.longExtractValue((Number)millis));
                    }
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
                            formatter = formatter_iso8601;
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
            boolean digit = true;
            for (int i = 0; i < text.length(); ++i) {
                char ch = text.charAt(i);
                if (ch >= '0' && ch <= '9') continue;
                digit = false;
                break;
            }
            if (digit && text.length() > 8 && text.length() < 19) {
                long epochMillis = Long.parseLong(text);
                return new LocalDateTime(epochMillis, DateTimeZone.forTimeZone(JSON.defaultTimeZone));
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
                return new LocalDateTime(epochMillis, DateTimeZone.forTimeZone(JSON.defaultTimeZone)).toLocalDate();
            }
        }
        return formatter == null ? LocalDate.parse(text) : LocalDate.parse(text, formatter);
    }

    protected DateTime parseZonedDateTime(String text, DateTimeFormatter formatter) {
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
                            formatter = formatter_iso8601;
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
        }
        return formatter == null ? DateTime.parse(text) : DateTime.parse(text, formatter);
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
                    int millis;
                    format = (features & mask) != 0 || serializer.isEnabled(SerializerFeature.UseISO8601DateFormat) ? formatter_iso8601_pattern : (serializer.isEnabled(SerializerFeature.WriteDateUseDateFormat) ? JSON.DEFFAULT_DATE_FORMAT : ((millis = dateTime.getMillisOfSecond()) == 0 ? formatter_iso8601_pattern_23 : formatter_iso8601_pattern_29));
                }
                if (format != null) {
                    this.write(out, dateTime, format);
                } else {
                    out.writeLong(dateTime.toDateTime(DateTimeZone.forTimeZone(JSON.defaultTimeZone)).toInstant().getMillis());
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
        this.write(out, (ReadablePartial)object, format);
    }

    private void write(SerializeWriter out, ReadablePartial object, String format) {
        DateTimeFormatter formatter = format.equals(formatter_iso8601_pattern) ? formatter_iso8601 : DateTimeFormat.forPattern(format);
        String text = formatter.print(object);
        out.writeString(text);
    }
}

