/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.formatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatConversionException;
import java.util.IllegalFormatException;
import java.util.MissingFormatArgumentException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.checkerframework.checker.formatter.qual.ConversionCategory;
import org.checkerframework.checker.formatter.qual.ReturnsFormat;

public class FormatUtil {
    private static final String formatSpecifier = "%(\\d+\\$)?([-#+ 0,(\\<]*)?(\\d+)?(\\.\\d+)?([tT])?([a-zA-Z%])";
    private static Pattern fsPattern = Pattern.compile("%(\\d+\\$)?([-#+ 0,(\\<]*)?(\\d+)?(\\.\\d+)?([tT])?([a-zA-Z%])");

    @ReturnsFormat
    public static String asFormat(String format, ConversionCategory ... cc) throws IllegalFormatException {
        ConversionCategory[] fcc = FormatUtil.formatParameterCategories(format);
        if (fcc.length != cc.length) {
            throw new ExcessiveOrMissingFormatArgumentException(cc.length, fcc.length);
        }
        for (int i = 0; i < cc.length; ++i) {
            if (cc[i] == fcc[i]) continue;
            throw new IllegalFormatConversionCategoryException(cc[i], fcc[i]);
        }
        return format;
    }

    public static void tryFormatSatisfiability(String format) throws IllegalFormatException {
        String unused = String.format(format, (Object[])null);
    }

    public static ConversionCategory[] formatParameterCategories(String format) throws IllegalFormatException {
        FormatUtil.tryFormatSatisfiability(format);
        int last = -1;
        int lasto = -1;
        int maxindex = -1;
        Conversion[] cs = FormatUtil.parse(format);
        HashMap<Integer, ConversionCategory> conv = new HashMap<Integer, ConversionCategory>();
        for (Conversion c : cs) {
            int index = c.index();
            switch (index) {
                case -1: {
                    break;
                }
                case 0: {
                    last = ++lasto;
                    break;
                }
                default: {
                    last = index - 1;
                }
            }
            maxindex = Math.max(maxindex, last);
            conv.put(last, ConversionCategory.intersect(conv.containsKey(last) ? (ConversionCategory)((Object)conv.get(last)) : ConversionCategory.UNUSED, c.category()));
        }
        ConversionCategory[] res = new ConversionCategory[maxindex + 1];
        for (int i = 0; i <= maxindex; ++i) {
            res[i] = conv.containsKey(i) ? (ConversionCategory)((Object)conv.get(i)) : ConversionCategory.UNUSED;
        }
        return res;
    }

    private static int indexFromFormat(Matcher m3) {
        String s2 = m3.group(1);
        int index = s2 != null ? Integer.parseInt(s2.substring(0, s2.length() - 1)) : (m3.group(2) != null && m3.group(2).contains(String.valueOf('<')) ? -1 : 0);
        return index;
    }

    private static char conversionCharFromFormat(Matcher m3) {
        String dt = m3.group(5);
        if (dt == null) {
            return m3.group(6).charAt(0);
        }
        return dt.charAt(0);
    }

    private static Conversion[] parse(String format) {
        ArrayList<Conversion> cs = new ArrayList<Conversion>();
        Matcher m3 = fsPattern.matcher(format);
        block3: while (m3.find()) {
            char c = FormatUtil.conversionCharFromFormat(m3);
            switch (c) {
                case '%': 
                case 'n': {
                    continue block3;
                }
            }
            cs.add(new Conversion(c, FormatUtil.indexFromFormat(m3)));
        }
        return cs.toArray(new Conversion[cs.size()]);
    }

    public static class IllegalFormatConversionCategoryException
    extends IllegalFormatConversionException {
        private static final long serialVersionUID = 17000126L;
        private final ConversionCategory expected;
        private final ConversionCategory found;

        public IllegalFormatConversionCategoryException(ConversionCategory expected, ConversionCategory found) {
            super(expected.chars.length() == 0 ? (char)'-' : (char)expected.chars.charAt(0), found.types == null ? Object.class : found.types[0]);
            this.expected = expected;
            this.found = found;
        }

        public ConversionCategory getExpected() {
            return this.expected;
        }

        public ConversionCategory getFound() {
            return this.found;
        }

        @Override
        public String getMessage() {
            return String.format("Expected category %s but found %s.", new Object[]{this.expected, this.found});
        }
    }

    public static class ExcessiveOrMissingFormatArgumentException
    extends MissingFormatArgumentException {
        private static final long serialVersionUID = 17000126L;
        private final int expected;
        private final int found;

        public ExcessiveOrMissingFormatArgumentException(int expected, int found) {
            super("-");
            this.expected = expected;
            this.found = found;
        }

        public int getExpected() {
            return this.expected;
        }

        public int getFound() {
            return this.found;
        }

        @Override
        public String getMessage() {
            return String.format("Expected %d arguments but found %d.", this.expected, this.found);
        }
    }

    private static class Conversion {
        private final int index;
        private final ConversionCategory cath;

        public Conversion(char c, int index) {
            this.index = index;
            this.cath = ConversionCategory.fromConversionChar(c);
        }

        int index() {
            return this.index;
        }

        ConversionCategory category() {
            return this.cath;
        }
    }
}

