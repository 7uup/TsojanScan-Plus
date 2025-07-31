/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3;

public class CharSequenceUtils {
    private static final int NOT_FOUND = -1;

    public static CharSequence subSequence(CharSequence cs, int start) {
        return cs == null ? null : cs.subSequence(start, cs.length());
    }

    static int indexOf(CharSequence cs, int searchChar, int start) {
        if (cs instanceof String) {
            return ((String)cs).indexOf(searchChar, start);
        }
        int sz = cs.length();
        if (start < 0) {
            start = 0;
        }
        if (searchChar < 65536) {
            for (int i = start; i < sz; ++i) {
                if (cs.charAt(i) != searchChar) continue;
                return i;
            }
        }
        if (searchChar <= 0x10FFFF) {
            char[] chars = Character.toChars(searchChar);
            for (int i = start; i < sz - 1; ++i) {
                char high = cs.charAt(i);
                char low = cs.charAt(i + 1);
                if (high != chars[0] || low != chars[1]) continue;
                return i;
            }
        }
        return -1;
    }

    static int indexOf(CharSequence cs, CharSequence searchChar, int start) {
        return cs.toString().indexOf(searchChar.toString(), start);
    }

    static int lastIndexOf(CharSequence cs, int searchChar, int start) {
        if (cs instanceof String) {
            return ((String)cs).lastIndexOf(searchChar, start);
        }
        int sz = cs.length();
        if (start < 0) {
            return -1;
        }
        if (start >= sz) {
            start = sz - 1;
        }
        if (searchChar < 65536) {
            for (int i = start; i >= 0; --i) {
                if (cs.charAt(i) != searchChar) continue;
                return i;
            }
        }
        if (searchChar <= 0x10FFFF) {
            char[] chars = Character.toChars(searchChar);
            if (start == sz - 1) {
                return -1;
            }
            for (int i = start; i >= 0; --i) {
                char high = cs.charAt(i);
                char low = cs.charAt(i + 1);
                if (chars[0] != high || chars[1] != low) continue;
                return i;
            }
        }
        return -1;
    }

    static int lastIndexOf(CharSequence cs, CharSequence searchChar, int start) {
        return cs.toString().lastIndexOf(searchChar.toString(), start);
    }

    static char[] toCharArray(CharSequence cs) {
        if (cs instanceof String) {
            return ((String)cs).toCharArray();
        }
        int sz = cs.length();
        char[] array = new char[cs.length()];
        for (int i = 0; i < sz; ++i) {
            array[i] = cs.charAt(i);
        }
        return array;
    }

    static boolean regionMatches(CharSequence cs, boolean ignoreCase, int thisStart, CharSequence substring, int start, int length) {
        if (cs instanceof String && substring instanceof String) {
            return ((String)cs).regionMatches(ignoreCase, thisStart, (String)substring, start, length);
        }
        int index1 = thisStart;
        int index2 = start;
        int tmpLen = length;
        int srcLen = cs.length() - thisStart;
        int otherLen = substring.length() - start;
        if (thisStart < 0 || start < 0 || length < 0) {
            return false;
        }
        if (srcLen < length || otherLen < length) {
            return false;
        }
        while (tmpLen-- > 0) {
            char c2;
            char c1;
            if ((c1 = cs.charAt(index1++)) == (c2 = substring.charAt(index2++))) continue;
            if (!ignoreCase) {
                return false;
            }
            if (Character.toUpperCase(c1) == Character.toUpperCase(c2) || Character.toLowerCase(c1) == Character.toLowerCase(c2)) continue;
            return false;
        }
        return true;
    }
}

