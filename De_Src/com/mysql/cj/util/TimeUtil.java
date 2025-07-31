/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.util;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.exceptions.InvalidConnectionAttributeException;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TimeZone;

public class TimeUtil {
    static final TimeZone GMT_TIMEZONE;
    private static final String TIME_ZONE_MAPPINGS_RESOURCE = "/com/mysql/cj/util/TimeZoneMapping.properties";
    private static Properties timeZoneMappings;
    protected static final Method systemNanoTimeMethod;

    public static boolean nanoTimeAvailable() {
        return systemNanoTimeMethod != null;
    }

    public static long getCurrentTimeNanosOrMillis() {
        if (systemNanoTimeMethod != null) {
            try {
                return (Long)systemNanoTimeMethod.invoke(null, null);
            } catch (IllegalArgumentException illegalArgumentException) {
            } catch (IllegalAccessException illegalAccessException) {
            } catch (InvocationTargetException invocationTargetException) {
                // empty catch block
            }
        }
        return System.currentTimeMillis();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String getCanonicalTimezone(String timezoneStr, ExceptionInterceptor exceptionInterceptor) {
        if (timezoneStr == null) {
            return null;
        }
        if ((timezoneStr = timezoneStr.trim()).length() > 2 && (timezoneStr.charAt(0) == '+' || timezoneStr.charAt(0) == '-') && Character.isDigit(timezoneStr.charAt(1))) {
            return "GMT" + timezoneStr;
        }
        Class<TimeUtil> clazz = TimeUtil.class;
        synchronized (TimeUtil.class) {
            if (timeZoneMappings == null) {
                TimeUtil.loadTimeZoneMappings(exceptionInterceptor);
            }
            // ** MonitorExit[var2_2] (shouldn't be in output)
            String canonicalTz = timeZoneMappings.getProperty(timezoneStr);
            if (canonicalTz != null) {
                return canonicalTz;
            }
            throw ExceptionFactory.createException(InvalidConnectionAttributeException.class, Messages.getString("TimeUtil.UnrecognizedTimezoneId", new Object[]{timezoneStr}), exceptionInterceptor);
        }
    }

    public static String formatNanos(int nanos, boolean usingMicros) {
        if (nanos > 999999999) {
            nanos %= 100000000;
        }
        if (usingMicros) {
            nanos /= 1000;
        }
        if (nanos == 0) {
            return "0";
        }
        int digitCount = usingMicros ? 6 : 9;
        String nanosString = Integer.toString(nanos);
        String zeroPadding = usingMicros ? "000000" : "000000000";
        nanosString = zeroPadding.substring(0, digitCount - nanosString.length()) + nanosString;
        int pos = digitCount - 1;
        while (nanosString.charAt(pos) == '0') {
            --pos;
        }
        nanosString = nanosString.substring(0, pos + 1);
        return nanosString;
    }

    private static void loadTimeZoneMappings(ExceptionInterceptor exceptionInterceptor) {
        timeZoneMappings = new Properties();
        try {
            timeZoneMappings.load(TimeUtil.class.getResourceAsStream(TIME_ZONE_MAPPINGS_RESOURCE));
        } catch (IOException e) {
            throw ExceptionFactory.createException(Messages.getString("TimeUtil.LoadTimeZoneMappingError"), exceptionInterceptor);
        }
        for (String tz : TimeZone.getAvailableIDs()) {
            if (timeZoneMappings.containsKey(tz)) continue;
            timeZoneMappings.put(tz, tz);
        }
    }

    public static Timestamp truncateFractionalSeconds(Timestamp timestamp) {
        Timestamp truncatedTimestamp = new Timestamp(timestamp.getTime());
        truncatedTimestamp.setNanos(0);
        return truncatedTimestamp;
    }

    public static final String getDateTimePattern(String dt, boolean toTime) throws IOException {
        int i;
        int size;
        char c;
        int n;
        Object[] v;
        int z;
        int dtLength;
        int n2 = dtLength = dt != null ? dt.length() : 0;
        if (dtLength >= 8 && dtLength <= 10) {
            int dashCount = 0;
            boolean isDateOnly = true;
            for (int i2 = 0; i2 < dtLength; ++i2) {
                char c2 = dt.charAt(i2);
                if (!Character.isDigit(c2) && c2 != '-') {
                    isDateOnly = false;
                    break;
                }
                if (c2 != '-') continue;
                ++dashCount;
            }
            if (isDateOnly && dashCount == 2) {
                return "yyyy-MM-dd";
            }
        }
        boolean colonsOnly = true;
        for (int i3 = 0; i3 < dtLength; ++i3) {
            char c3 = dt.charAt(i3);
            if (Character.isDigit(c3) || c3 == ':') continue;
            colonsOnly = false;
            break;
        }
        if (colonsOnly) {
            return "HH:mm:ss";
        }
        StringReader reader = new StringReader(dt + " ");
        ArrayList<Object[]> vec = new ArrayList<Object[]>();
        ArrayList<Object[]> vecRemovelist = new ArrayList<Object[]>();
        Object[] nv = new Object[]{Character.valueOf('y'), new StringBuilder(), 0};
        vec.add(nv);
        if (toTime) {
            nv = new Object[]{Character.valueOf('h'), new StringBuilder(), 0};
            vec.add(nv);
        }
        while ((z = reader.read()) != -1) {
            char separator = (char)z;
            int maxvecs = vec.size();
            for (int count = 0; count < maxvecs; ++count) {
                v = (Object[])vec.get(count);
                n = (Integer)v[2];
                c = TimeUtil.getSuccessor(((Character)v[0]).charValue(), n);
                if (!Character.isLetterOrDigit(separator)) {
                    if (c == ((Character)v[0]).charValue() && c != 'S') {
                        vecRemovelist.add(v);
                        continue;
                    }
                    ((StringBuilder)v[1]).append(separator);
                    if (c != 'X' && c != 89) continue;
                    v[2] = 4;
                    continue;
                }
                if (c == 'X') {
                    c = 'y';
                    nv = new Object[3];
                    nv[1] = new StringBuilder(((StringBuilder)v[1]).toString()).append('M');
                    nv[0] = Character.valueOf('M');
                    nv[2] = 1;
                    vec.add(nv);
                } else if (c == 'Y') {
                    c = 'M';
                    nv = new Object[3];
                    nv[1] = new StringBuilder(((StringBuilder)v[1]).toString()).append('d');
                    nv[0] = Character.valueOf('d');
                    nv[2] = 1;
                    vec.add(nv);
                }
                ((StringBuilder)v[1]).append(c);
                if (c == ((Character)v[0]).charValue()) {
                    v[2] = n + 1;
                    continue;
                }
                v[0] = Character.valueOf(c);
                v[2] = 1;
            }
            size = vecRemovelist.size();
            for (i = 0; i < size; ++i) {
                v = (Object[])vecRemovelist.get(i);
                vec.remove(v);
            }
            vecRemovelist.clear();
        }
        size = vec.size();
        for (i = 0; i < size; ++i) {
            boolean containsEnd;
            v = (Object[])vec.get(i);
            c = ((Character)v[0]).charValue();
            boolean bk = TimeUtil.getSuccessor(c, n = ((Integer)v[2]).intValue()) != c;
            boolean atEnd = (c == 's' || c == 'm' || c == 'h' && toTime) && bk;
            boolean finishesAtDate = bk && c == 'd' && !toTime;
            boolean bl = containsEnd = ((StringBuilder)v[1]).toString().indexOf(87) != -1;
            if ((atEnd || finishesAtDate) && !containsEnd) continue;
            vecRemovelist.add(v);
        }
        size = vecRemovelist.size();
        for (i = 0; i < size; ++i) {
            vec.remove(vecRemovelist.get(i));
        }
        vecRemovelist.clear();
        v = (Object[])vec.get(0);
        StringBuilder format = (StringBuilder)v[1];
        format.setLength(format.length() - 1);
        return format.toString();
    }

    private static final char getSuccessor(char c, int n) {
        return (char)(c == 'y' && n == 2 ? 88 : (c == 'y' && n < 4 ? 121 : (c == 'y' ? 77 : (c == 'M' && n == 2 ? 89 : (c == 'M' && n < 3 ? 77 : (c == 'M' ? 100 : (c == 'd' && n < 2 ? 100 : (c == 'd' ? 72 : (c == 'H' && n < 2 ? 72 : (c == 'H' ? 109 : (c == 'm' && n < 2 ? 109 : (c == 'm' ? 115 : (c == 's' && n < 2 ? 115 : 87)))))))))))));
    }

    static {
        Method aMethod;
        GMT_TIMEZONE = TimeZone.getTimeZone("GMT");
        timeZoneMappings = null;
        try {
            aMethod = System.class.getMethod("nanoTime", null);
        } catch (SecurityException e) {
            aMethod = null;
        } catch (NoSuchMethodException e) {
            aMethod = null;
        }
        systemNanoTimeMethod = aMethod;
    }
}

