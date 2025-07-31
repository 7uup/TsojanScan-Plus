/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.escape.ArrayBasedEscaperMap;
import com.google.common.escape.CharEscaper;
import java.util.Map;

@Beta
@GwtCompatible
public abstract class ArrayBasedCharEscaper
extends CharEscaper {
    private final char[][] replacements;
    private final int replacementsLength;
    private final char safeMin;
    private final char safeMax;

    protected ArrayBasedCharEscaper(Map<Character, String> replacementMap, char safeMin, char safeMax) {
        this(ArrayBasedEscaperMap.create(replacementMap), safeMin, safeMax);
    }

    protected ArrayBasedCharEscaper(ArrayBasedEscaperMap escaperMap, char safeMin, char safeMax) {
        Preconditions.checkNotNull(escaperMap);
        this.replacements = escaperMap.getReplacementArray();
        this.replacementsLength = this.replacements.length;
        if (safeMax < safeMin) {
            safeMax = '\u0000';
            safeMin = (char)65535;
        }
        this.safeMin = safeMin;
        this.safeMax = safeMax;
    }

    @Override
    public final String escape(String s2) {
        Preconditions.checkNotNull(s2);
        for (int i = 0; i < s2.length(); ++i) {
            char c = s2.charAt(i);
            if ((c >= this.replacementsLength || this.replacements[c] == null) && c <= this.safeMax && c >= this.safeMin) continue;
            return this.escapeSlow(s2, i);
        }
        return s2;
    }

    @Override
    protected final char[] escape(char c) {
        char[] chars;
        if (c < this.replacementsLength && (chars = this.replacements[c]) != null) {
            return chars;
        }
        if (c >= this.safeMin && c <= this.safeMax) {
            return null;
        }
        return this.escapeUnsafe(c);
    }

    protected abstract char[] escapeUnsafe(char var1);
}

