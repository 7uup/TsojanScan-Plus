/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.similarity;

import java.util.Arrays;
import org.apache.commons.text.similarity.EditDistance;

public class LevenshteinDistance
implements EditDistance<Integer> {
    private static final LevenshteinDistance DEFAULT_INSTANCE = new LevenshteinDistance();
    private final Integer threshold;

    public LevenshteinDistance() {
        this(null);
    }

    public LevenshteinDistance(Integer threshold) {
        if (threshold != null && threshold < 0) {
            throw new IllegalArgumentException("Threshold must not be negative");
        }
        this.threshold = threshold;
    }

    @Override
    public Integer apply(CharSequence left, CharSequence right) {
        if (this.threshold != null) {
            return LevenshteinDistance.limitedCompare(left, right, this.threshold);
        }
        return LevenshteinDistance.unlimitedCompare(left, right);
    }

    public static LevenshteinDistance getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public Integer getThreshold() {
        return this.threshold;
    }

    private static int limitedCompare(CharSequence left, CharSequence right, int threshold) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("CharSequences must not be null");
        }
        if (threshold < 0) {
            throw new IllegalArgumentException("Threshold must not be negative");
        }
        int n = left.length();
        int m3 = right.length();
        if (n == 0) {
            return m3 <= threshold ? m3 : -1;
        }
        if (m3 == 0) {
            return n <= threshold ? n : -1;
        }
        if (n > m3) {
            CharSequence tmp = left;
            left = right;
            right = tmp;
            n = m3;
            m3 = right.length();
        }
        if (m3 - n > threshold) {
            return -1;
        }
        int[] p = new int[n + 1];
        int[] d = new int[n + 1];
        int boundary = Math.min(n, threshold) + 1;
        for (int i = 0; i < boundary; ++i) {
            p[i] = i;
        }
        Arrays.fill(p, boundary, p.length, Integer.MAX_VALUE);
        Arrays.fill(d, Integer.MAX_VALUE);
        for (int j = 1; j <= m3; ++j) {
            int max;
            char rightJ = right.charAt(j - 1);
            d[0] = j;
            int min2 = Math.max(1, j - threshold);
            int n2 = max = j > Integer.MAX_VALUE - threshold ? n : Math.min(n, j + threshold);
            if (min2 > 1) {
                d[min2 - 1] = Integer.MAX_VALUE;
            }
            for (int i = min2; i <= max; ++i) {
                d[i] = left.charAt(i - 1) == rightJ ? p[i - 1] : 1 + Math.min(Math.min(d[i - 1], p[i]), p[i - 1]);
            }
            int[] tempD = p;
            p = d;
            d = tempD;
        }
        if (p[n] <= threshold) {
            return p[n];
        }
        return -1;
    }

    private static int unlimitedCompare(CharSequence left, CharSequence right) {
        int i;
        if (left == null || right == null) {
            throw new IllegalArgumentException("CharSequences must not be null");
        }
        int n = left.length();
        int m3 = right.length();
        if (n == 0) {
            return m3;
        }
        if (m3 == 0) {
            return n;
        }
        if (n > m3) {
            CharSequence tmp = left;
            left = right;
            right = tmp;
            n = m3;
            m3 = right.length();
        }
        int[] p = new int[n + 1];
        for (i = 0; i <= n; ++i) {
            p[i] = i;
        }
        for (int j = 1; j <= m3; ++j) {
            int upperLeft = p[0];
            char rightJ = right.charAt(j - 1);
            p[0] = j;
            for (i = 1; i <= n; ++i) {
                int upper = p[i];
                int cost = left.charAt(i - 1) == rightJ ? 0 : 1;
                p[i] = Math.min(Math.min(p[i - 1] + 1, p[i] + 1), upperLeft + cost);
                upperLeft = upper;
            }
        }
        return p[n];
    }
}

