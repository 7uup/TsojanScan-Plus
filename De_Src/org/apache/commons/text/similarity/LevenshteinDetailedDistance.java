/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.similarity;

import java.util.Arrays;
import org.apache.commons.text.similarity.EditDistance;
import org.apache.commons.text.similarity.LevenshteinResults;

public class LevenshteinDetailedDistance
implements EditDistance<LevenshteinResults> {
    private static final LevenshteinDetailedDistance DEFAULT_INSTANCE = new LevenshteinDetailedDistance();
    private final Integer threshold;

    public LevenshteinDetailedDistance() {
        this(null);
    }

    public LevenshteinDetailedDistance(Integer threshold) {
        if (threshold != null && threshold < 0) {
            throw new IllegalArgumentException("Threshold must not be negative");
        }
        this.threshold = threshold;
    }

    @Override
    public LevenshteinResults apply(CharSequence left, CharSequence right) {
        if (this.threshold != null) {
            return LevenshteinDetailedDistance.limitedCompare(left, right, this.threshold);
        }
        return LevenshteinDetailedDistance.unlimitedCompare(left, right);
    }

    public static LevenshteinDetailedDistance getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public Integer getThreshold() {
        return this.threshold;
    }

    private static LevenshteinResults limitedCompare(CharSequence left, CharSequence right, int threshold) {
        int index;
        if (left == null || right == null) {
            throw new IllegalArgumentException("CharSequences must not be null");
        }
        if (threshold < 0) {
            throw new IllegalArgumentException("Threshold must not be negative");
        }
        int n = left.length();
        int m3 = right.length();
        if (n == 0) {
            return m3 <= threshold ? new LevenshteinResults(m3, m3, 0, 0) : new LevenshteinResults(-1, 0, 0, 0);
        }
        if (m3 == 0) {
            return n <= threshold ? new LevenshteinResults(n, 0, n, 0) : new LevenshteinResults(-1, 0, 0, 0);
        }
        boolean swapped = false;
        if (n > m3) {
            CharSequence tmp = left;
            left = right;
            right = tmp;
            n = m3;
            m3 = right.length();
            swapped = true;
        }
        int[] p = new int[n + 1];
        int[] d = new int[n + 1];
        int[][] matrix = new int[m3 + 1][n + 1];
        for (index = 0; index <= n; ++index) {
            matrix[0][index] = index;
        }
        for (index = 0; index <= m3; ++index) {
            matrix[index][0] = index;
        }
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
            if (min2 > max) {
                return new LevenshteinResults(-1, 0, 0, 0);
            }
            if (min2 > 1) {
                d[min2 - 1] = Integer.MAX_VALUE;
            }
            for (int i = min2; i <= max; ++i) {
                d[i] = left.charAt(i - 1) == rightJ ? p[i - 1] : 1 + Math.min(Math.min(d[i - 1], p[i]), p[i - 1]);
                matrix[j][i] = d[i];
            }
            int[] tempD = p;
            p = d;
            d = tempD;
        }
        if (p[n] <= threshold) {
            return LevenshteinDetailedDistance.findDetailedResults(left, right, matrix, swapped);
        }
        return new LevenshteinResults(-1, 0, 0, 0);
    }

    private static LevenshteinResults unlimitedCompare(CharSequence left, CharSequence right) {
        int i;
        int index;
        if (left == null || right == null) {
            throw new IllegalArgumentException("CharSequences must not be null");
        }
        int n = left.length();
        int m3 = right.length();
        if (n == 0) {
            return new LevenshteinResults(m3, m3, 0, 0);
        }
        if (m3 == 0) {
            return new LevenshteinResults(n, 0, n, 0);
        }
        boolean swapped = false;
        if (n > m3) {
            CharSequence tmp = left;
            left = right;
            right = tmp;
            n = m3;
            m3 = right.length();
            swapped = true;
        }
        int[] p = new int[n + 1];
        int[] d = new int[n + 1];
        int[][] matrix = new int[m3 + 1][n + 1];
        for (index = 0; index <= n; ++index) {
            matrix[0][index] = index;
        }
        for (index = 0; index <= m3; ++index) {
            matrix[index][0] = index;
        }
        for (i = 0; i <= n; ++i) {
            p[i] = i;
        }
        for (int j = 1; j <= m3; ++j) {
            char rightJ = right.charAt(j - 1);
            d[0] = j;
            for (i = 1; i <= n; ++i) {
                int cost = left.charAt(i - 1) == rightJ ? 0 : 1;
                d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
                matrix[j][i] = d[i];
            }
            int[] tempD = p;
            p = d;
            d = tempD;
        }
        return LevenshteinDetailedDistance.findDetailedResults(left, right, matrix, swapped);
    }

    private static LevenshteinResults findDetailedResults(CharSequence left, CharSequence right, int[][] matrix, boolean swapped) {
        int delCount = 0;
        int addCount = 0;
        int subCount = 0;
        int rowIndex = right.length();
        int columnIndex = left.length();
        int dataAtLeft = 0;
        int dataAtTop = 0;
        int dataAtDiagonal = 0;
        int data = 0;
        boolean deleted = false;
        boolean added = false;
        while (rowIndex >= 0 && columnIndex >= 0) {
            dataAtLeft = columnIndex == 0 ? -1 : matrix[rowIndex][columnIndex - 1];
            dataAtTop = rowIndex == 0 ? -1 : matrix[rowIndex - 1][columnIndex];
            dataAtDiagonal = rowIndex > 0 && columnIndex > 0 ? matrix[rowIndex - 1][columnIndex - 1] : -1;
            if (dataAtLeft == -1 && dataAtTop == -1 && dataAtDiagonal == -1) break;
            data = matrix[rowIndex][columnIndex];
            if (columnIndex > 0 && rowIndex > 0 && left.charAt(columnIndex - 1) == right.charAt(rowIndex - 1)) {
                --columnIndex;
                --rowIndex;
                continue;
            }
            deleted = false;
            added = false;
            if (data - 1 == dataAtLeft && data <= dataAtDiagonal && data <= dataAtTop || dataAtDiagonal == -1 && dataAtTop == -1) {
                --columnIndex;
                if (swapped) {
                    ++addCount;
                    added = true;
                } else {
                    ++delCount;
                    deleted = true;
                }
            } else if (data - 1 == dataAtTop && data <= dataAtDiagonal && data <= dataAtLeft || dataAtDiagonal == -1 && dataAtLeft == -1) {
                --rowIndex;
                if (swapped) {
                    ++delCount;
                    deleted = true;
                } else {
                    ++addCount;
                    added = true;
                }
            }
            if (added || deleted) continue;
            ++subCount;
            --columnIndex;
            --rowIndex;
        }
        return new LevenshteinResults(addCount + delCount + subCount, addCount, delCount, subCount);
    }
}

