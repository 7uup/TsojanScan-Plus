/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

public class WordUtils {
    public static String wrap(String str, int wrapLength) {
        return WordUtils.wrap(str, wrapLength, null, false);
    }

    public static String wrap(String str, int wrapLength, String newLineStr, boolean wrapLongWords) {
        return WordUtils.wrap(str, wrapLength, newLineStr, wrapLongWords, " ");
    }

    public static String wrap(String str, int wrapLength, String newLineStr, boolean wrapLongWords, String wrapOn) {
        if (str == null) {
            return null;
        }
        if (newLineStr == null) {
            newLineStr = System.lineSeparator();
        }
        if (wrapLength < 1) {
            wrapLength = 1;
        }
        if (StringUtils.isBlank(wrapOn)) {
            wrapOn = " ";
        }
        Pattern patternToWrapOn = Pattern.compile(wrapOn);
        int inputLineLength = str.length();
        int offset = 0;
        StringBuilder wrappedLine = new StringBuilder(inputLineLength + 32);
        while (offset < inputLineLength) {
            int spaceToWrapAt = -1;
            Matcher matcher = patternToWrapOn.matcher(str.substring(offset, Math.min((int)Math.min(Integer.MAX_VALUE, (long)(offset + wrapLength) + 1L), inputLineLength)));
            if (matcher.find()) {
                if (matcher.start() == 0) {
                    offset += matcher.end();
                    continue;
                }
                spaceToWrapAt = matcher.start() + offset;
            }
            if (inputLineLength - offset <= wrapLength) break;
            while (matcher.find()) {
                spaceToWrapAt = matcher.start() + offset;
            }
            if (spaceToWrapAt >= offset) {
                wrappedLine.append(str, offset, spaceToWrapAt);
                wrappedLine.append(newLineStr);
                offset = spaceToWrapAt + 1;
                continue;
            }
            if (wrapLongWords) {
                wrappedLine.append(str, offset, wrapLength + offset);
                wrappedLine.append(newLineStr);
                offset += wrapLength;
                continue;
            }
            matcher = patternToWrapOn.matcher(str.substring(offset + wrapLength));
            if (matcher.find()) {
                spaceToWrapAt = matcher.start() + offset + wrapLength;
            }
            if (spaceToWrapAt >= 0) {
                wrappedLine.append(str, offset, spaceToWrapAt);
                wrappedLine.append(newLineStr);
                offset = spaceToWrapAt + 1;
                continue;
            }
            wrappedLine.append(str, offset, str.length());
            offset = inputLineLength;
        }
        wrappedLine.append(str, offset, str.length());
        return wrappedLine.toString();
    }

    public static String capitalize(String str) {
        return WordUtils.capitalize(str, null);
    }

    public static String capitalize(String str, char ... delimiters) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        Set<Integer> delimiterSet = WordUtils.generateDelimiterSet(delimiters);
        int strLen = str.length();
        int[] newCodePoints = new int[strLen];
        int outOffset = 0;
        boolean capitalizeNext = true;
        int index = 0;
        while (index < strLen) {
            int codePoint = str.codePointAt(index);
            if (delimiterSet.contains(codePoint)) {
                capitalizeNext = true;
                newCodePoints[outOffset++] = codePoint;
                index += Character.charCount(codePoint);
                continue;
            }
            if (capitalizeNext) {
                int titleCaseCodePoint = Character.toTitleCase(codePoint);
                newCodePoints[outOffset++] = titleCaseCodePoint;
                index += Character.charCount(titleCaseCodePoint);
                capitalizeNext = false;
                continue;
            }
            newCodePoints[outOffset++] = codePoint;
            index += Character.charCount(codePoint);
        }
        return new String(newCodePoints, 0, outOffset);
    }

    public static String capitalizeFully(String str) {
        return WordUtils.capitalizeFully(str, null);
    }

    public static String capitalizeFully(String str, char ... delimiters) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        str = str.toLowerCase();
        return WordUtils.capitalize(str, delimiters);
    }

    public static String uncapitalize(String str) {
        return WordUtils.uncapitalize(str, null);
    }

    public static String uncapitalize(String str, char ... delimiters) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        Set<Integer> delimiterSet = WordUtils.generateDelimiterSet(delimiters);
        int strLen = str.length();
        int[] newCodePoints = new int[strLen];
        int outOffset = 0;
        boolean uncapitalizeNext = true;
        int index = 0;
        while (index < strLen) {
            int codePoint = str.codePointAt(index);
            if (delimiterSet.contains(codePoint)) {
                uncapitalizeNext = true;
                newCodePoints[outOffset++] = codePoint;
                index += Character.charCount(codePoint);
                continue;
            }
            if (uncapitalizeNext) {
                int titleCaseCodePoint = Character.toLowerCase(codePoint);
                newCodePoints[outOffset++] = titleCaseCodePoint;
                index += Character.charCount(titleCaseCodePoint);
                uncapitalizeNext = false;
                continue;
            }
            newCodePoints[outOffset++] = codePoint;
            index += Character.charCount(codePoint);
        }
        return new String(newCodePoints, 0, outOffset);
    }

    public static String swapCase(String str) {
        int newCodePoint;
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        int strLen = str.length();
        int[] newCodePoints = new int[strLen];
        int outOffset = 0;
        boolean whitespace = true;
        for (int index = 0; index < strLen; index += Character.charCount(newCodePoint)) {
            int oldCodepoint = str.codePointAt(index);
            if (Character.isUpperCase(oldCodepoint) || Character.isTitleCase(oldCodepoint)) {
                newCodePoint = Character.toLowerCase(oldCodepoint);
                whitespace = false;
            } else if (Character.isLowerCase(oldCodepoint)) {
                if (whitespace) {
                    newCodePoint = Character.toTitleCase(oldCodepoint);
                    whitespace = false;
                } else {
                    newCodePoint = Character.toUpperCase(oldCodepoint);
                }
            } else {
                whitespace = Character.isWhitespace(oldCodepoint);
                newCodePoint = oldCodepoint;
            }
            newCodePoints[outOffset++] = newCodePoint;
        }
        return new String(newCodePoints, 0, outOffset);
    }

    public static String initials(String str) {
        return WordUtils.initials(str, null);
    }

    public static String initials(String str, char ... delimiters) {
        int codePoint;
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        if (delimiters != null && delimiters.length == 0) {
            return "";
        }
        Set<Integer> delimiterSet = WordUtils.generateDelimiterSet(delimiters);
        int strLen = str.length();
        int[] newCodePoints = new int[strLen / 2 + 1];
        int count = 0;
        boolean lastWasGap = true;
        for (int i = 0; i < strLen; i += Character.charCount(codePoint)) {
            codePoint = str.codePointAt(i);
            if (delimiterSet.contains(codePoint) || delimiters == null && Character.isWhitespace(codePoint)) {
                lastWasGap = true;
                continue;
            }
            if (!lastWasGap) continue;
            newCodePoints[count++] = codePoint;
            lastWasGap = false;
        }
        return new String(newCodePoints, 0, count);
    }

    public static boolean containsAllWords(CharSequence word, CharSequence ... words) {
        if (StringUtils.isEmpty(word) || ArrayUtils.isEmpty(words)) {
            return false;
        }
        for (CharSequence w : words) {
            if (StringUtils.isBlank(w)) {
                return false;
            }
            Pattern p = Pattern.compile(".*\\b" + w + "\\b.*");
            if (p.matcher(word).matches()) continue;
            return false;
        }
        return true;
    }

    @Deprecated
    public static boolean isDelimiter(char ch, char[] delimiters) {
        if (delimiters == null) {
            return Character.isWhitespace(ch);
        }
        for (char delimiter : delimiters) {
            if (ch != delimiter) continue;
            return true;
        }
        return false;
    }

    @Deprecated
    public static boolean isDelimiter(int codePoint, char[] delimiters) {
        if (delimiters == null) {
            return Character.isWhitespace(codePoint);
        }
        for (int index = 0; index < delimiters.length; ++index) {
            int delimiterCodePoint = Character.codePointAt(delimiters, index);
            if (delimiterCodePoint != codePoint) continue;
            return true;
        }
        return false;
    }

    public static String abbreviate(String str, int lower, int upper, String appendToEnd) {
        Validate.isTrue(upper >= -1, "upper value cannot be less than -1", new Object[0]);
        Validate.isTrue(upper >= lower || upper == -1, "upper value is less than lower value", new Object[0]);
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        if (lower > str.length()) {
            lower = str.length();
        }
        if (upper == -1 || upper > str.length()) {
            upper = str.length();
        }
        StringBuilder result = new StringBuilder();
        int index = StringUtils.indexOf((CharSequence)str, " ", lower);
        if (index == -1) {
            result.append(str, 0, upper);
            if (upper != str.length()) {
                result.append(StringUtils.defaultString(appendToEnd));
            }
        } else if (index > upper) {
            result.append(str, 0, upper);
            result.append(StringUtils.defaultString(appendToEnd));
        } else {
            result.append(str, 0, index);
            result.append(StringUtils.defaultString(appendToEnd));
        }
        return result.toString();
    }

    private static Set<Integer> generateDelimiterSet(char[] delimiters) {
        HashSet<Integer> delimiterHashSet = new HashSet<Integer>();
        if (delimiters == null || delimiters.length == 0) {
            if (delimiters == null) {
                delimiterHashSet.add(Character.codePointAt(new char[]{' '}, 0));
            }
            return delimiterHashSet;
        }
        for (int index = 0; index < delimiters.length; ++index) {
            delimiterHashSet.add(Character.codePointAt(delimiters, index));
        }
        return delimiterHashSet;
    }
}

