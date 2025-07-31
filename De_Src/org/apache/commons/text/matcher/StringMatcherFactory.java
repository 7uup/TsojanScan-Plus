/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.matcher;

import org.apache.commons.text.matcher.AbstractStringMatcher;
import org.apache.commons.text.matcher.StringMatcher;

public final class StringMatcherFactory {
    public static final StringMatcherFactory INSTANCE = new StringMatcherFactory();
    private static final AbstractStringMatcher.CharSetMatcher SPLIT_MATCHER = new AbstractStringMatcher.CharSetMatcher(" \t\n\r\f".toCharArray());
    private static final AbstractStringMatcher.CharMatcher COMMA_MATCHER = new AbstractStringMatcher.CharMatcher(',');
    private static final AbstractStringMatcher.CharMatcher TAB_MATCHER = new AbstractStringMatcher.CharMatcher('\t');
    private static final AbstractStringMatcher.CharMatcher SPACE_MATCHER = new AbstractStringMatcher.CharMatcher(' ');
    private static final AbstractStringMatcher.TrimMatcher TRIM_MATCHER = new AbstractStringMatcher.TrimMatcher();
    private static final AbstractStringMatcher.CharMatcher SINGLE_QUOTE_MATCHER = new AbstractStringMatcher.CharMatcher('\'');
    private static final AbstractStringMatcher.CharMatcher DOUBLE_QUOTE_MATCHER = new AbstractStringMatcher.CharMatcher('\"');
    private static final AbstractStringMatcher.CharSetMatcher QUOTE_MATCHER = new AbstractStringMatcher.CharSetMatcher("'\"".toCharArray());
    private static final AbstractStringMatcher.NoMatcher NONE_MATCHER = new AbstractStringMatcher.NoMatcher();

    private StringMatcherFactory() {
    }

    public StringMatcher charMatcher(char ch) {
        return new AbstractStringMatcher.CharMatcher(ch);
    }

    public StringMatcher charSetMatcher(char ... chars) {
        if (chars == null || chars.length == 0) {
            return NONE_MATCHER;
        }
        if (chars.length == 1) {
            return new AbstractStringMatcher.CharMatcher(chars[0]);
        }
        return new AbstractStringMatcher.CharSetMatcher(chars);
    }

    public StringMatcher charSetMatcher(String chars) {
        if (chars == null || chars.length() == 0) {
            return NONE_MATCHER;
        }
        if (chars.length() == 1) {
            return new AbstractStringMatcher.CharMatcher(chars.charAt(0));
        }
        return new AbstractStringMatcher.CharSetMatcher(chars.toCharArray());
    }

    public StringMatcher commaMatcher() {
        return COMMA_MATCHER;
    }

    public StringMatcher doubleQuoteMatcher() {
        return DOUBLE_QUOTE_MATCHER;
    }

    public StringMatcher noneMatcher() {
        return NONE_MATCHER;
    }

    public StringMatcher quoteMatcher() {
        return QUOTE_MATCHER;
    }

    public StringMatcher singleQuoteMatcher() {
        return SINGLE_QUOTE_MATCHER;
    }

    public StringMatcher spaceMatcher() {
        return SPACE_MATCHER;
    }

    public StringMatcher splitMatcher() {
        return SPLIT_MATCHER;
    }

    public StringMatcher stringMatcher(String str) {
        if (str == null || str.length() == 0) {
            return NONE_MATCHER;
        }
        return new AbstractStringMatcher.StringMatcher(str);
    }

    public StringMatcher tabMatcher() {
        return TAB_MATCHER;
    }

    public StringMatcher trimMatcher() {
        return TRIM_MATCHER;
    }
}

