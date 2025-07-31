/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.similarity;

import java.util.Arrays;
import org.apache.commons.text.similarity.SimilarityScore;

public class JaroWinklerDistance
implements SimilarityScore<Double> {
    public static final int INDEX_NOT_FOUND = -1;

    @Override
    public Double apply(CharSequence left, CharSequence right) {
        double defaultScalingFactor = 0.1;
        if (left == null || right == null) {
            throw new IllegalArgumentException("CharSequences must not be null");
        }
        int[] mtp = JaroWinklerDistance.matches(left, right);
        double m3 = mtp[0];
        if (m3 == 0.0) {
            return 0.0;
        }
        double j = (m3 / (double)left.length() + m3 / (double)right.length() + (m3 - (double)mtp[1] / 2.0) / m3) / 3.0;
        double jw = j < 0.7 ? j : j + 0.1 * (double)mtp[2] * (1.0 - j);
        return jw;
    }

    protected static int[] matches(CharSequence first, CharSequence second) {
        int i;
        CharSequence min2;
        CharSequence max;
        if (first.length() > second.length()) {
            max = first;
            min2 = second;
        } else {
            max = second;
            min2 = first;
        }
        int range = Math.max(max.length() / 2 - 1, 0);
        int[] matchIndexes = new int[min2.length()];
        Arrays.fill(matchIndexes, -1);
        boolean[] matchFlags = new boolean[max.length()];
        int matches = 0;
        block0: for (int mi = 0; mi < min2.length(); ++mi) {
            char c1 = min2.charAt(mi);
            int xn = Math.min(mi + range + 1, max.length());
            for (int xi = Math.max(mi - range, 0); xi < xn; ++xi) {
                if (matchFlags[xi] || c1 != max.charAt(xi)) continue;
                matchIndexes[mi] = xi;
                matchFlags[xi] = true;
                ++matches;
                continue block0;
            }
        }
        char[] ms1 = new char[matches];
        char[] ms2 = new char[matches];
        int si = 0;
        for (i = 0; i < min2.length(); ++i) {
            if (matchIndexes[i] == -1) continue;
            ms1[si] = min2.charAt(i);
            ++si;
        }
        si = 0;
        for (i = 0; i < max.length(); ++i) {
            if (!matchFlags[i]) continue;
            ms2[si] = max.charAt(i);
            ++si;
        }
        int halfTranspositions = 0;
        for (int mi = 0; mi < ms1.length; ++mi) {
            if (ms1[mi] == ms2[mi]) continue;
            ++halfTranspositions;
        }
        int prefix = 0;
        for (int mi = 0; mi < Math.min(4, min2.length()) && first.charAt(mi) == second.charAt(mi); ++mi) {
            ++prefix;
        }
        return new int[]{matches, halfTranspositions, prefix};
    }
}

