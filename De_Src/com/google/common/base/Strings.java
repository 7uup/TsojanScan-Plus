/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Platform;
import com.google.common.base.Preconditions;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public final class Strings {
    private Strings() {
    }

    public static String nullToEmpty(@Nullable String string) {
        return Platform.nullToEmpty(string);
    }

    public static @Nullable String emptyToNull(@Nullable String string) {
        return Platform.emptyToNull(string);
    }

    public static boolean isNullOrEmpty(@Nullable String string) {
        return Platform.stringIsNullOrEmpty(string);
    }

    public static String padStart(String string, int minLength, char padChar) {
        Preconditions.checkNotNull(string);
        if (string.length() >= minLength) {
            return string;
        }
        StringBuilder sb = new StringBuilder(minLength);
        for (int i = string.length(); i < minLength; ++i) {
            sb.append(padChar);
        }
        sb.append(string);
        return sb.toString();
    }

    public static String padEnd(String string, int minLength, char padChar) {
        Preconditions.checkNotNull(string);
        if (string.length() >= minLength) {
            return string;
        }
        StringBuilder sb = new StringBuilder(minLength);
        sb.append(string);
        for (int i = string.length(); i < minLength; ++i) {
            sb.append(padChar);
        }
        return sb.toString();
    }

    public static String repeat(String string, int count) {
        int n;
        Preconditions.checkNotNull(string);
        if (count <= 1) {
            Preconditions.checkArgument(count >= 0, "invalid count: %s", count);
            return count == 0 ? "" : string;
        }
        int len = string.length();
        long longSize = (long)len * (long)count;
        int size = (int)longSize;
        if ((long)size != longSize) {
            throw new ArrayIndexOutOfBoundsException("Required array size too large: " + longSize);
        }
        char[] array = new char[size];
        string.getChars(0, len, array, 0);
        for (n = len; n < size - n; n <<= 1) {
            System.arraycopy(array, 0, array, n, n);
        }
        System.arraycopy(array, 0, array, n, size - n);
        return new String(array);
    }

    public static String commonPrefix(CharSequence a, CharSequence b) {
        int p;
        Preconditions.checkNotNull(a);
        Preconditions.checkNotNull(b);
        int maxPrefixLength = Math.min(a.length(), b.length());
        for (p = 0; p < maxPrefixLength && a.charAt(p) == b.charAt(p); ++p) {
        }
        if (Strings.validSurrogatePairAt(a, p - 1) || Strings.validSurrogatePairAt(b, p - 1)) {
            --p;
        }
        return a.subSequence(0, p).toString();
    }

    public static String commonSuffix(CharSequence a, CharSequence b) {
        int s2;
        Preconditions.checkNotNull(a);
        Preconditions.checkNotNull(b);
        int maxSuffixLength = Math.min(a.length(), b.length());
        for (s2 = 0; s2 < maxSuffixLength && a.charAt(a.length() - s2 - 1) == b.charAt(b.length() - s2 - 1); ++s2) {
        }
        if (Strings.validSurrogatePairAt(a, a.length() - s2 - 1) || Strings.validSurrogatePairAt(b, b.length() - s2 - 1)) {
            --s2;
        }
        return a.subSequence(a.length() - s2, a.length()).toString();
    }

    @VisibleForTesting
    static boolean validSurrogatePairAt(CharSequence string, int index) {
        return index >= 0 && index <= string.length() - 2 && Character.isHighSurrogate(string.charAt(index)) && Character.isLowSurrogate(string.charAt(index + 1));
    }

    public static String lenientFormat(@Nullable String template, @Nullable Object @Nullable ... args2) {
        int placeholderStart;
        template = String.valueOf(template);
        if (args2 == null) {
            args2 = new Object[]{"(Object[])null"};
        } else {
            for (int i = 0; i < args2.length; ++i) {
                args2[i] = Strings.lenientToString(args2[i]);
            }
        }
        StringBuilder builder = new StringBuilder(template.length() + 16 * args2.length);
        int templateStart = 0;
        int i = 0;
        while (i < args2.length && (placeholderStart = template.indexOf("%s", templateStart)) != -1) {
            builder.append(template, templateStart, placeholderStart);
            builder.append(args2[i++]);
            templateStart = placeholderStart + 2;
        }
        builder.append(template, templateStart, template.length());
        if (i < args2.length) {
            builder.append(" [");
            builder.append(args2[i++]);
            while (i < args2.length) {
                builder.append(", ");
                builder.append(args2[i++]);
            }
            builder.append(']');
        }
        return builder.toString();
    }

    private static String lenientToString(@Nullable Object o) {
        if (o == null) {
            return "null";
        }
        try {
            return o.toString();
        } catch (Exception e) {
            String objectToString = o.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(o));
            Logger.getLogger("com.google.common.base.Strings").log(Level.WARNING, "Exception during lenientFormat for " + objectToString, e);
            return "<" + objectToString + " threw " + e.getClass().getName() + ">";
        }
    }
}

