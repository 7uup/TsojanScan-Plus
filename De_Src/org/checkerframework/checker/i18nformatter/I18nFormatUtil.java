/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.i18nformatter;

import java.text.ChoiceFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Locale;
import org.checkerframework.checker.i18nformatter.qual.I18nChecksFormat;
import org.checkerframework.checker.i18nformatter.qual.I18nConversionCategory;
import org.checkerframework.checker.i18nformatter.qual.I18nValidFormat;

public class I18nFormatUtil {
    public static void tryFormatSatisfiability(String format) throws IllegalFormatException {
        MessageFormat.format(format, null);
    }

    public static I18nConversionCategory[] formatParameterCategories(String format) throws IllegalFormatException {
        I18nFormatUtil.tryFormatSatisfiability(format);
        I18nConversion[] cs = MessageFormatParser.parse(format);
        int maxIndex = -1;
        HashMap<Integer, I18nConversionCategory> conv = new HashMap<Integer, I18nConversionCategory>();
        for (I18nConversion c : cs) {
            int index = c.index;
            conv.put(index, I18nConversionCategory.intersect(c.category, conv.containsKey(index) ? (I18nConversionCategory)((Object)conv.get(index)) : I18nConversionCategory.UNUSED));
            maxIndex = Math.max(maxIndex, index);
        }
        I18nConversionCategory[] res = new I18nConversionCategory[maxIndex + 1];
        for (int i = 0; i <= maxIndex; ++i) {
            res[i] = conv.containsKey(i) ? (I18nConversionCategory)((Object)conv.get(i)) : I18nConversionCategory.UNUSED;
        }
        return res;
    }

    @I18nChecksFormat
    public static boolean hasFormat(String format, I18nConversionCategory ... cc) {
        I18nConversionCategory[] fcc = I18nFormatUtil.formatParameterCategories(format);
        if (fcc.length != cc.length) {
            return false;
        }
        for (int i = 0; i < cc.length; ++i) {
            if (I18nConversionCategory.isSubsetOf(cc[i], fcc[i])) continue;
            return false;
        }
        return true;
    }

    @I18nValidFormat
    public static boolean isFormat(String format) {
        try {
            I18nFormatUtil.formatParameterCategories(format);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private static class MessageFormatParser {
        public static int maxOffset;
        private static Locale locale;
        private static List<I18nConversionCategory> categories;
        private static List<Integer> argumentIndices;
        private static int numFormat;
        private static final int SEG_RAW = 0;
        private static final int SEG_INDEX = 1;
        private static final int SEG_TYPE = 2;
        private static final int SEG_MODIFIER = 3;
        private static final int TYPE_NULL = 0;
        private static final int TYPE_NUMBER = 1;
        private static final int TYPE_DATE = 2;
        private static final int TYPE_TIME = 3;
        private static final int TYPE_CHOICE = 4;
        private static final String[] TYPE_KEYWORDS;
        private static final int MODIFIER_DEFAULT = 0;
        private static final int MODIFIER_CURRENCY = 1;
        private static final int MODIFIER_PERCENT = 2;
        private static final int MODIFIER_INTEGER = 3;
        private static final String[] NUMBER_MODIFIER_KEYWORDS;
        private static final String[] DATE_TIME_MODIFIER_KEYWORDS;

        private MessageFormatParser() {
        }

        public static I18nConversion[] parse(String pattern) {
            categories = new ArrayList<I18nConversionCategory>();
            argumentIndices = new ArrayList<Integer>();
            locale = Locale.getDefault(Locale.Category.FORMAT);
            MessageFormatParser.applyPattern(pattern);
            I18nConversion[] ret = new I18nConversion[numFormat];
            for (int i = 0; i < numFormat; ++i) {
                ret[i] = new I18nConversion(argumentIndices.get(i), categories.get(i));
            }
            return ret;
        }

        private static void applyPattern(String pattern) {
            StringBuilder[] segments = new StringBuilder[4];
            segments[0] = new StringBuilder();
            int part = 0;
            numFormat = 0;
            boolean inQuote = false;
            int braceStack = 0;
            maxOffset = -1;
            block7: for (int i = 0; i < pattern.length(); ++i) {
                char ch = pattern.charAt(i);
                if (part == 0) {
                    if (ch == '\'') {
                        if (i + 1 < pattern.length() && pattern.charAt(i + 1) == '\'') {
                            segments[part].append(ch);
                            ++i;
                            continue;
                        }
                        inQuote = !inQuote;
                        continue;
                    }
                    if (ch == '{' && !inQuote) {
                        part = 1;
                        if (segments[1] != null) continue;
                        segments[1] = new StringBuilder();
                        continue;
                    }
                    segments[part].append(ch);
                    continue;
                }
                if (inQuote) {
                    segments[part].append(ch);
                    if (ch != '\'') continue;
                    inQuote = false;
                    continue;
                }
                switch (ch) {
                    case ',': {
                        if (part < 3) {
                            if (segments[++part] != null) continue block7;
                            segments[part] = new StringBuilder();
                            continue block7;
                        }
                        segments[part].append(ch);
                        continue block7;
                    }
                    case '{': {
                        ++braceStack;
                        segments[part].append(ch);
                        continue block7;
                    }
                    case '}': {
                        if (braceStack == 0) {
                            part = 0;
                            MessageFormatParser.makeFormat(numFormat, segments);
                            ++numFormat;
                            segments[1] = null;
                            segments[2] = null;
                            segments[3] = null;
                            continue block7;
                        }
                        --braceStack;
                        segments[part].append(ch);
                        continue block7;
                    }
                    case ' ': {
                        if (part == 2 && segments[2].length() <= 0) continue block7;
                        segments[part].append(ch);
                        continue block7;
                    }
                    case '\'': {
                        inQuote = true;
                        segments[part].append(ch);
                        continue block7;
                    }
                    default: {
                        segments[part].append(ch);
                    }
                }
            }
            if (braceStack == 0 && part != 0) {
                maxOffset = -1;
                throw new IllegalArgumentException("Unmatched braces in the pattern");
            }
        }

        private static void makeFormat(int offsetNumber, StringBuilder[] textSegments) {
            int argumentNumber;
            String[] segments = new String[textSegments.length];
            for (int i = 0; i < textSegments.length; ++i) {
                StringBuilder oneseg = textSegments[i];
                segments[i] = oneseg != null ? oneseg.toString() : "";
            }
            try {
                argumentNumber = Integer.parseInt(segments[1]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("can't parse argument number: " + segments[1], e);
            }
            if (argumentNumber < 0) {
                throw new IllegalArgumentException("negative argument number: " + argumentNumber);
            }
            int oldMaxOffset = maxOffset;
            maxOffset = offsetNumber;
            argumentIndices.add(argumentNumber);
            I18nConversionCategory category = null;
            if (segments[2].length() != 0) {
                int type = MessageFormatParser.findKeyword(segments[2], TYPE_KEYWORDS);
                switch (type) {
                    case 0: {
                        category = I18nConversionCategory.GENERAL;
                        break;
                    }
                    case 1: {
                        switch (MessageFormatParser.findKeyword(segments[3], NUMBER_MODIFIER_KEYWORDS)) {
                            case 0: 
                            case 1: 
                            case 2: 
                            case 3: {
                                break;
                            }
                            default: {
                                try {
                                    new DecimalFormat(segments[3], DecimalFormatSymbols.getInstance(locale));
                                    break;
                                } catch (IllegalArgumentException e) {
                                    maxOffset = oldMaxOffset;
                                    throw e;
                                }
                            }
                        }
                        category = I18nConversionCategory.NUMBER;
                        break;
                    }
                    case 2: 
                    case 3: {
                        int mod = MessageFormatParser.findKeyword(segments[3], DATE_TIME_MODIFIER_KEYWORDS);
                        if (mod < 0 || mod >= DATE_TIME_MODIFIER_KEYWORDS.length) {
                            try {
                                new SimpleDateFormat(segments[3], locale);
                            } catch (IllegalArgumentException e) {
                                maxOffset = oldMaxOffset;
                                throw e;
                            }
                        }
                        category = I18nConversionCategory.DATE;
                        break;
                    }
                    case 4: {
                        if (segments[3].length() == 0) {
                            throw new IllegalArgumentException("Choice Pattern requires Subformat Pattern: " + segments[3]);
                        }
                        try {
                            new ChoiceFormat(segments[3]);
                        } catch (Exception e) {
                            maxOffset = oldMaxOffset;
                            throw new IllegalArgumentException("Choice Pattern incorrect: " + segments[3], e);
                        }
                        category = I18nConversionCategory.NUMBER;
                        break;
                    }
                    default: {
                        maxOffset = oldMaxOffset;
                        throw new IllegalArgumentException("unknown format type: " + segments[2]);
                    }
                }
            } else {
                category = I18nConversionCategory.GENERAL;
            }
            categories.add(category);
        }

        private static final int findKeyword(String s2, String[] list) {
            for (int i = 0; i < list.length; ++i) {
                if (!s2.equals(list[i])) continue;
                return i;
            }
            String ls = s2.trim().toLowerCase(Locale.ROOT);
            if (ls != s2) {
                for (int i = 0; i < list.length; ++i) {
                    if (!ls.equals(list[i])) continue;
                    return i;
                }
            }
            return -1;
        }

        static {
            TYPE_KEYWORDS = new String[]{"", "number", "date", "time", "choice"};
            NUMBER_MODIFIER_KEYWORDS = new String[]{"", "currency", "percent", "integer"};
            DATE_TIME_MODIFIER_KEYWORDS = new String[]{"", "short", "medium", "long", "full"};
        }
    }

    private static class I18nConversion {
        public int index;
        public I18nConversionCategory category;

        public I18nConversion(int index, I18nConversionCategory category) {
            this.index = index;
            this.category = category;
        }

        public String toString() {
            return this.category.toString() + "(index: " + this.index + ")";
        }
    }
}

