/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.lookup;

import org.apache.commons.text.lookup.StringLookup;

abstract class AbstractStringLookup
implements StringLookup {
    private static final String EMPTY = "";
    protected static final char SPLIT_CH = ':';
    protected static final String SPLIT_STR = String.valueOf(':');

    AbstractStringLookup() {
    }

    protected String substringAfter(String value, char ch) {
        int indexOf = value.indexOf(ch);
        return indexOf > -1 ? value.substring(indexOf + 1) : EMPTY;
    }

    protected String substringAfterLast(String value, char ch) {
        int indexOf = value.lastIndexOf(ch);
        return indexOf > -1 ? value.substring(indexOf + 1) : EMPTY;
    }

    protected String substringAfter(String value, String str) {
        int indexOf = value.indexOf(str);
        return indexOf > -1 ? value.substring(indexOf + str.length()) : EMPTY;
    }
}

