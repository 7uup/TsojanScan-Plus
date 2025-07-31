/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.gson.internal.bind.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class ISO8601Utils {
    private static final String UTC_ID = "UTC";
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone("UTC");

    public static String format(Date date) {
        return ISO8601Utils.format(date, false, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean millis) {
        return ISO8601Utils.format(date, millis, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean millis, TimeZone tz) {
        int offset;
        GregorianCalendar calendar = new GregorianCalendar(tz, Locale.US);
        calendar.setTime(date);
        int capacity = "yyyy-MM-ddThh:mm:ss".length();
        capacity += millis ? ".sss".length() : 0;
        StringBuilder formatted = new StringBuilder(capacity += tz.getRawOffset() == 0 ? "Z".length() : "+hh:mm".length());
        ISO8601Utils.padInt(formatted, calendar.get(1), "yyyy".length());
        formatted.append('-');
        ISO8601Utils.padInt(formatted, calendar.get(2) + 1, "MM".length());
        formatted.append('-');
        ISO8601Utils.padInt(formatted, calendar.get(5), "dd".length());
        formatted.append('T');
        ISO8601Utils.padInt(formatted, calendar.get(11), "hh".length());
        formatted.append(':');
        ISO8601Utils.padInt(formatted, calendar.get(12), "mm".length());
        formatted.append(':');
        ISO8601Utils.padInt(formatted, calendar.get(13), "ss".length());
        if (millis) {
            formatted.append('.');
            ISO8601Utils.padInt(formatted, calendar.get(14), "sss".length());
        }
        if ((offset = tz.getOffset(calendar.getTimeInMillis())) != 0) {
            int hours = Math.abs(offset / 60000 / 60);
            int minutes = Math.abs(offset / 60000 % 60);
            formatted.append(offset < 0 ? (char)'-' : '+');
            ISO8601Utils.padInt(formatted, hours, "hh".length());
            formatted.append(':');
            ISO8601Utils.padInt(formatted, minutes, "mm".length());
        } else {
            formatted.append('Z');
        }
        return formatted.toString();
    }

    public static Date parse(String date, ParsePosition pos) throws ParseException {
        RuntimeException fail = null;
        try {
            int offset = pos.getIndex();
            int year = ISO8601Utils.parseInt(date, offset, offset += 4);
            if (ISO8601Utils.checkOffset(date, offset, '-')) {
                // empty if block
            }
            int month = ISO8601Utils.parseInt(date, ++offset, offset += 2);
            if (ISO8601Utils.checkOffset(date, offset, '-')) {
                // empty if block
            }
            int day = ISO8601Utils.parseInt(date, ++offset, offset += 2);
            int hour = 0;
            int minutes = 0;
            int seconds = 0;
            int milliseconds = 0;
            boolean hasT = ISO8601Utils.checkOffset(date, offset, 'T');
            if (!hasT && date.length() <= offset) {
                GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
                pos.setIndex(offset);
                return calendar.getTime();
            }
            if (hasT) {
                char c;
                hour = ISO8601Utils.parseInt(date, ++offset, offset += 2);
                if (ISO8601Utils.checkOffset(date, offset, ':')) {
                    // empty if block
                }
                minutes = ISO8601Utils.parseInt(date, ++offset, offset += 2);
                if (ISO8601Utils.checkOffset(date, offset, ':')) {
                    ++offset;
                }
                if (date.length() > offset && (c = date.charAt(offset)) != 'Z' && c != '+' && c != '-') {
                    if ((seconds = ISO8601Utils.parseInt(date, offset, offset += 2)) > 59 && seconds < 63) {
                        seconds = 59;
                    }
                    if (ISO8601Utils.checkOffset(date, offset, '.')) {
                        int endOffset = ISO8601Utils.indexOfNonDigit(date, ++offset + 1);
                        int parseEndOffset = Math.min(endOffset, offset + 3);
                        int fraction = ISO8601Utils.parseInt(date, offset, parseEndOffset);
                        switch (parseEndOffset - offset) {
                            case 2: {
                                milliseconds = fraction * 10;
                                break;
                            }
                            case 1: {
                                milliseconds = fraction * 100;
                                break;
                            }
                            default: {
                                milliseconds = fraction;
                            }
                        }
                        offset = endOffset;
                    }
                }
            }
            if (date.length() <= offset) {
                throw new IllegalArgumentException("No time zone indicator");
            }
            TimeZone timezone = null;
            char timezoneIndicator = date.charAt(offset);
            if (timezoneIndicator == 'Z') {
                timezone = TIMEZONE_UTC;
                ++offset;
            } else if (timezoneIndicator == '+' || timezoneIndicator == '-') {
                String timezoneOffset = date.substring(offset);
                timezoneOffset = timezoneOffset.length() >= 5 ? timezoneOffset : timezoneOffset + "00";
                offset += timezoneOffset.length();
                if ("+0000".equals(timezoneOffset) || "+00:00".equals(timezoneOffset)) {
                    timezone = TIMEZONE_UTC;
                } else {
                    String cleaned;
                    String timezoneId = "GMT" + timezoneOffset;
                    timezone = TimeZone.getTimeZone(timezoneId);
                    String act = timezone.getID();
                    if (!act.equals(timezoneId) && !(cleaned = act.replace(":", "")).equals(timezoneId)) {
                        throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + timezoneId + " given, resolves to " + timezone.getID());
                    }
                }
            } else {
                throw new IndexOutOfBoundsException("Invalid time zone indicator '" + timezoneIndicator + "'");
            }
            GregorianCalendar calendar = new GregorianCalendar(timezone);
            calendar.setLenient(false);
            calendar.set(1, year);
            calendar.set(2, month - 1);
            calendar.set(5, day);
            calendar.set(11, hour);
            calendar.set(12, minutes);
            calendar.set(13, seconds);
            calendar.set(14, milliseconds);
            pos.setIndex(offset);
            return calendar.getTime();
        } catch (IndexOutOfBoundsException e) {
            fail = e;
        } catch (NumberFormatException e) {
            fail = e;
        } catch (IllegalArgumentException e) {
            fail = e;
        }
        String input = date == null ? null : '\"' + date + '\"';
        String msg = fail.getMessage();
        if (msg == null || msg.isEmpty()) {
            msg = "(" + fail.getClass().getName() + ")";
        }
        ParseException ex = new ParseException("Failed to parse date [" + input + "]: " + msg, pos.getIndex());
        ex.initCause(fail);
        throw ex;
    }

    private static boolean checkOffset(String value, int offset, char expected) {
        return offset < value.length() && value.charAt(offset) == expected;
    }

    private static int parseInt(String value, int beginIndex, int endIndex) throws NumberFormatException {
        int digit;
        if (beginIndex < 0 || endIndex > value.length() || beginIndex > endIndex) {
            throw new NumberFormatException(value);
        }
        int i = beginIndex;
        int result = 0;
        if (i < endIndex) {
            if ((digit = Character.digit(value.charAt(i++), 10)) < 0) {
                throw new NumberFormatException("Invalid number: " + value.substring(beginIndex, endIndex));
            }
            result = -digit;
        }
        while (i < endIndex) {
            if ((digit = Character.digit(value.charAt(i++), 10)) < 0) {
                throw new NumberFormatException("Invalid number: " + value.substring(beginIndex, endIndex));
            }
            result *= 10;
            result -= digit;
        }
        return -result;
    }

    private static void padInt(StringBuilder buffer, int value, int length) {
        String strValue = Integer.toString(value);
        for (int i = length - strValue.length(); i > 0; --i) {
            buffer.append('0');
        }
        buffer.append(strValue);
    }

    private static int indexOfNonDigit(String string, int offset) {
        for (int i = offset; i < string.length(); ++i) {
            char c = string.charAt(i);
            if (c >= '0' && c <= '9') continue;
            return i;
        }
        return string.length();
    }
}

