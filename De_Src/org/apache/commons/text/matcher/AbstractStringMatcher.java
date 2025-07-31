/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.matcher;

import java.util.Arrays;

abstract class AbstractStringMatcher
implements org.apache.commons.text.matcher.StringMatcher {
    protected AbstractStringMatcher() {
    }

    public int isMatch(char[] buffer, int pos) {
        return this.isMatch(buffer, pos, 0, buffer.length);
    }

    static final class TrimMatcher
    extends AbstractStringMatcher {
        private static final int SPACE_INT = 32;

        TrimMatcher() {
        }

        @Override
        public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd) {
            return buffer[pos] <= ' ' ? 1 : 0;
        }
    }

    static final class StringMatcher
    extends AbstractStringMatcher {
        private final char[] chars;

        StringMatcher(String str) {
            this.chars = str.toCharArray();
        }

        @Override
        public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd) {
            int len = this.chars.length;
            if (pos + len > bufferEnd) {
                return 0;
            }
            int i = 0;
            while (i < this.chars.length) {
                if (this.chars[i] != buffer[pos]) {
                    return 0;
                }
                ++i;
                ++pos;
            }
            return len;
        }

        public String toString() {
            return super.toString() + ' ' + Arrays.toString(this.chars);
        }
    }

    static final class NoMatcher
    extends AbstractStringMatcher {
        NoMatcher() {
        }

        @Override
        public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd) {
            return 0;
        }
    }

    static final class CharSetMatcher
    extends AbstractStringMatcher {
        private final char[] chars;

        CharSetMatcher(char[] chars) {
            this.chars = (char[])chars.clone();
            Arrays.sort(this.chars);
        }

        @Override
        public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd) {
            return Arrays.binarySearch(this.chars, buffer[pos]) >= 0 ? 1 : 0;
        }
    }

    static final class CharMatcher
    extends AbstractStringMatcher {
        private final char ch;

        CharMatcher(char ch) {
            this.ch = ch;
        }

        @Override
        public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd) {
            return this.ch == buffer[pos] ? 1 : 0;
        }
    }
}

