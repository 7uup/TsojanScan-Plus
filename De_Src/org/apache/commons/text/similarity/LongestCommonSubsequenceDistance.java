/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.similarity;

import org.apache.commons.text.similarity.EditDistance;
import org.apache.commons.text.similarity.LongestCommonSubsequence;

public class LongestCommonSubsequenceDistance
implements EditDistance<Integer> {
    private final LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence();

    @Override
    public Integer apply(CharSequence left, CharSequence right) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("Inputs must not be null");
        }
        return left.length() + right.length() - 2 * this.longestCommonSubsequence.apply(left, right);
    }
}

