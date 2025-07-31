/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.similarity;

import java.util.HashSet;
import org.apache.commons.text.similarity.SimilarityScore;

public class JaccardSimilarity
implements SimilarityScore<Double> {
    @Override
    public Double apply(CharSequence left, CharSequence right) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        return (double)Math.round(this.calculateJaccardSimilarity(left, right) * 100.0) / 100.0;
    }

    private Double calculateJaccardSimilarity(CharSequence left, CharSequence right) {
        int leftLength = left.length();
        int rightLength = right.length();
        if (leftLength == 0 || rightLength == 0) {
            return 0.0;
        }
        HashSet<Character> leftSet = new HashSet<Character>();
        for (int i = 0; i < leftLength; ++i) {
            leftSet.add(Character.valueOf(left.charAt(i)));
        }
        HashSet<Character> rightSet = new HashSet<Character>();
        for (int i = 0; i < rightLength; ++i) {
            rightSet.add(Character.valueOf(right.charAt(i)));
        }
        HashSet<Character> unionSet = new HashSet<Character>(leftSet);
        unionSet.addAll(rightSet);
        int intersectionSize = leftSet.size() + rightSet.size() - unionSet.size();
        return 1.0 * (double)intersectionSize / (double)unionSet.size();
    }
}

