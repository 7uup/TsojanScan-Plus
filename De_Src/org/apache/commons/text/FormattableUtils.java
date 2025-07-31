/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text;

import java.util.Formattable;
import java.util.Formatter;

public class FormattableUtils {
    private static final String SIMPLEST_FORMAT = "%s";

    public static String toString(Formattable formattable) {
        return String.format(SIMPLEST_FORMAT, formattable);
    }

    public static Formatter append(CharSequence seq, Formatter formatter, int flags, int width, int precision) {
        return FormattableUtils.append(seq, formatter, flags, width, precision, ' ', null);
    }

    public static Formatter append(CharSequence seq, Formatter formatter, int flags, int width, int precision, char padChar) {
        return FormattableUtils.append(seq, formatter, flags, width, precision, padChar, null);
    }

    public static Formatter append(CharSequence seq, Formatter formatter, int flags, int width, int precision, CharSequence ellipsis) {
        return FormattableUtils.append(seq, formatter, flags, width, precision, ' ', ellipsis);
    }

    public static Formatter append(CharSequence seq, Formatter formatter, int flags, int width, int precision, char padChar, CharSequence ellipsis) {
        if (ellipsis != null && precision >= 0 && ellipsis.length() > precision) {
            throw new IllegalArgumentException(String.format("Specified ellipsis '%s' exceeds precision of %s", ellipsis, precision));
        }
        StringBuilder buf = new StringBuilder(seq);
        if (precision >= 0 && precision < seq.length()) {
            CharSequence _ellipsis = ellipsis == null ? "" : ellipsis;
            buf.replace(precision - _ellipsis.length(), seq.length(), _ellipsis.toString());
        }
        boolean leftJustify = (flags & 1) == 1;
        for (int i = buf.length(); i < width; ++i) {
            buf.insert(leftJustify ? i : 0, padChar);
        }
        formatter.format(buf.toString(), new Object[0]);
        return formatter;
    }
}

