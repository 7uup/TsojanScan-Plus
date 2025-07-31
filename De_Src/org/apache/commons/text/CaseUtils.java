/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text;

import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

public class CaseUtils {
    public static String toCamelCase(String str, boolean capitalizeFirstLetter, char ... delimiters) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        str = str.toLowerCase();
        int strLen = str.length();
        int[] newCodePoints = new int[strLen];
        int outOffset = 0;
        Set<Integer> delimiterSet = CaseUtils.generateDelimiterSet(delimiters);
        boolean capitalizeNext = false;
        if (capitalizeFirstLetter) {
            capitalizeNext = true;
        }
        int index = 0;
        while (index < strLen) {
            int codePoint = str.codePointAt(index);
            if (delimiterSet.contains(codePoint)) {
                capitalizeNext = true;
                if (outOffset == 0) {
                    capitalizeNext = false;
                }
                index += Character.charCount(codePoint);
                continue;
            }
            if (capitalizeNext || outOffset == 0 && capitalizeFirstLetter) {
                int titleCaseCodePoint = Character.toTitleCase(codePoint);
                newCodePoints[outOffset++] = titleCaseCodePoint;
                index += Character.charCount(titleCaseCodePoint);
                capitalizeNext = false;
                continue;
            }
            newCodePoints[outOffset++] = codePoint;
            index += Character.charCount(codePoint);
        }
        if (outOffset != 0) {
            return new String(newCodePoints, 0, outOffset);
        }
        return str;
    }

    private static Set<Integer> generateDelimiterSet(char[] delimiters) {
        HashSet<Integer> delimiterHashSet = new HashSet<Integer>();
        delimiterHashSet.add(Character.codePointAt(new char[]{' '}, 0));
        if (delimiters == null || delimiters.length == 0) {
            return delimiterHashSet;
        }
        for (int index = 0; index < delimiters.length; ++index) {
            delimiterHashSet.add(Character.codePointAt(delimiters, index));
        }
        return delimiterHashSet;
    }
}

