/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.similarity;

import org.apache.commons.text.similarity.SimilarityScore;

public class LongestCommonSubsequence
implements SimilarityScore<Integer> {
    @Override
    public Integer apply(CharSequence left, CharSequence right) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("Inputs must not be null");
        }
        return this.longestCommonSubsequence(left, right).length();
    }

    @Deprecated
    public CharSequence logestCommonSubsequence(CharSequence left, CharSequence right) {
        return this.longestCommonSubsequence(left, right);
    }

    public CharSequence longestCommonSubsequence(CharSequence left, CharSequence right) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("Inputs must not be null");
        }
        StringBuilder longestCommonSubstringArray = new StringBuilder(Math.max(left.length(), right.length()));
        int[][] lcsLengthArray = this.longestCommonSubstringLengthArray(left, right);
        int i = left.length() - 1;
        int j = right.length() - 1;
        int k = lcsLengthArray[left.length()][right.length()] - 1;
        while (k >= 0) {
            if (left.charAt(i) == right.charAt(j)) {
                longestCommonSubstringArray.append(left.charAt(i));
                --i;
                --j;
                --k;
                continue;
            }
            if (lcsLengthArray[i + 1][j] < lcsLengthArray[i][j + 1]) {
                --i;
                continue;
            }
            --j;
        }
        return longestCommonSubstringArray.reverse().toString();
    }

    public int[][] longestCommonSubstringLengthArray(CharSequence left, CharSequence right) {
        int[][] lcsLengthArray = new int[left.length() + 1][right.length() + 1];
        for (int i = 0; i < left.length(); ++i) {
            for (int j = 0; j < right.length(); ++j) {
                if (i == 0) {
                    lcsLengthArray[i][j] = 0;
                }
                if (j == 0) {
                    lcsLengthArray[i][j] = 0;
                }
                lcsLengthArray[i + 1][j + 1] = left.charAt(i) == right.charAt(j) ? lcsLengthArray[i][j] + 1 : Math.max(lcsLengthArray[i + 1][j], lcsLengthArray[i][j + 1]);
            }
        }
        return lcsLengthArray;
    }
}

