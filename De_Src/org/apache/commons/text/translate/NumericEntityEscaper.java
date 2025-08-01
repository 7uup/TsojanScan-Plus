/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.translate;

import java.io.IOException;
import java.io.Writer;
import org.apache.commons.lang3.Range;
import org.apache.commons.text.translate.CodePointTranslator;

public class NumericEntityEscaper
extends CodePointTranslator {
    private final boolean between;
    private final Range<Integer> range;

    private NumericEntityEscaper(int below, int above, boolean between) {
        this.range = Range.between(below, above);
        this.between = between;
    }

    public NumericEntityEscaper() {
        this(0, Integer.MAX_VALUE, true);
    }

    public static NumericEntityEscaper below(int codepoint) {
        return NumericEntityEscaper.outsideOf(codepoint, Integer.MAX_VALUE);
    }

    public static NumericEntityEscaper above(int codepoint) {
        return NumericEntityEscaper.outsideOf(0, codepoint);
    }

    public static NumericEntityEscaper between(int codepointLow, int codepointHigh) {
        return new NumericEntityEscaper(codepointLow, codepointHigh, true);
    }

    public static NumericEntityEscaper outsideOf(int codepointLow, int codepointHigh) {
        return new NumericEntityEscaper(codepointLow, codepointHigh, false);
    }

    @Override
    public boolean translate(int codepoint, Writer out) throws IOException {
        if (this.between != this.range.contains(codepoint)) {
            return false;
        }
        out.write("&#");
        out.write(Integer.toString(codepoint, 10));
        out.write(59);
        return true;
    }
}

