/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.util;

import java.math.BigInteger;

public final class RyuDouble {
    private static final int[][] POW5_SPLIT = new int[326][4];
    private static final int[][] POW5_INV_SPLIT = new int[291][4];

    public static String toString(double value) {
        char[] result = new char[24];
        int len = RyuDouble.toString(value, result, 0);
        return new String(result, 0, len);
    }

    public static int toString(double value, char[] result, int off) {
        long output;
        int e10;
        long dm;
        long dp;
        long dv;
        int q;
        long m22;
        int e2;
        long DOUBLE_MANTISSA_MASK = 0xFFFFFFFFFFFFFL;
        int DOUBLE_EXPONENT_MASK = 2047;
        int DOUBLE_EXPONENT_BIAS = 1023;
        long LOG10_5_NUMERATOR = 6989700L;
        long LOG10_2_NUMERATOR = 3010299L;
        int index = off;
        if (Double.isNaN(value)) {
            result[index++] = 78;
            result[index++] = 97;
            result[index++] = 78;
            return index - off;
        }
        if (value == Double.POSITIVE_INFINITY) {
            result[index++] = 73;
            result[index++] = 110;
            result[index++] = 102;
            result[index++] = 105;
            result[index++] = 110;
            result[index++] = 105;
            result[index++] = 116;
            result[index++] = 121;
            return index - off;
        }
        if (value == Double.NEGATIVE_INFINITY) {
            result[index++] = 45;
            result[index++] = 73;
            result[index++] = 110;
            result[index++] = 102;
            result[index++] = 105;
            result[index++] = 110;
            result[index++] = 105;
            result[index++] = 116;
            result[index++] = 121;
            return index - off;
        }
        long bits = Double.doubleToLongBits(value);
        if (bits == 0L) {
            result[index++] = 48;
            result[index++] = 46;
            result[index++] = 48;
            return index - off;
        }
        if (bits == Long.MIN_VALUE) {
            result[index++] = 45;
            result[index++] = 48;
            result[index++] = 46;
            result[index++] = 48;
            return index - off;
        }
        int DOUBLE_MANTISSA_BITS = 52;
        int ieeeExponent = (int)(bits >>> 52 & 0x7FFL);
        long ieeeMantissa = bits & 0xFFFFFFFFFFFFFL;
        if (ieeeExponent == 0) {
            e2 = -1074;
            m22 = ieeeMantissa;
        } else {
            e2 = ieeeExponent - 1023 - 52;
            m22 = ieeeMantissa | 0x10000000000000L;
        }
        boolean sign = bits < 0L;
        boolean even = (m22 & 1L) == 0L;
        long mv = 4L * m22;
        long mp = 4L * m22 + 2L;
        int mmShift = m22 != 0x10000000000000L || ieeeExponent <= 1 ? 1 : 0;
        long mm3 = 4L * m22 - 1L - (long)mmShift;
        boolean dmIsTrailingZeros = false;
        boolean dvIsTrailingZeros = false;
        if ((e2 -= 2) >= 0) {
            int k = 122 + ((q = Math.max(0, (int)((long)e2 * 3010299L / 10000000L) - 1)) == 0 ? 1 : (int)(((long)q * 23219280L + 10000000L - 1L) / 10000000L)) - 1;
            int i = -e2 + q + k;
            int actualShift = i - 93 - 21;
            if (actualShift < 0) {
                throw new IllegalArgumentException("" + actualShift);
            }
            int[] ints = POW5_INV_SPLIT[q];
            long mHigh = mv >>> 31;
            long mLow = mv & Integer.MAX_VALUE;
            long bits13 = mHigh * (long)ints[0];
            long bits03 = mLow * (long)ints[0];
            long bits12 = mHigh * (long)ints[1];
            long bits02 = mLow * (long)ints[1];
            long bits11 = mHigh * (long)ints[2];
            long bits01 = mLow * (long)ints[2];
            long bits10 = mHigh * (long)ints[3];
            long bits00 = mLow * (long)ints[3];
            dv = ((((bits00 >>> 31) + bits01 + bits10 >>> 31) + bits02 + bits11 >>> 31) + bits03 + bits12 >>> 21) + (bits13 << 10) >>> actualShift;
            mHigh = mp >>> 31;
            mLow = mp & Integer.MAX_VALUE;
            bits13 = mHigh * (long)ints[0];
            bits03 = mLow * (long)ints[0];
            bits12 = mHigh * (long)ints[1];
            bits02 = mLow * (long)ints[1];
            bits11 = mHigh * (long)ints[2];
            bits01 = mLow * (long)ints[2];
            bits10 = mHigh * (long)ints[3];
            bits00 = mLow * (long)ints[3];
            dp = ((((bits00 >>> 31) + bits01 + bits10 >>> 31) + bits02 + bits11 >>> 31) + bits03 + bits12 >>> 21) + (bits13 << 10) >>> actualShift;
            mHigh = mm3 >>> 31;
            mLow = mm3 & Integer.MAX_VALUE;
            bits13 = mHigh * (long)ints[0];
            bits03 = mLow * (long)ints[0];
            bits12 = mHigh * (long)ints[1];
            bits02 = mLow * (long)ints[1];
            bits11 = mHigh * (long)ints[2];
            bits01 = mLow * (long)ints[2];
            bits10 = mHigh * (long)ints[3];
            bits00 = mLow * (long)ints[3];
            dm = ((((bits00 >>> 31) + bits01 + bits10 >>> 31) + bits02 + bits11 >>> 31) + bits03 + bits12 >>> 21) + (bits13 << 10) >>> actualShift;
            e10 = q;
            if (q <= 21) {
                long v;
                if (mv % 5L == 0L) {
                    int pow5Factor_mv;
                    v = mv;
                    if (v % 5L != 0L) {
                        pow5Factor_mv = 0;
                    } else if (v % 25L != 0L) {
                        pow5Factor_mv = 1;
                    } else if (v % 125L != 0L) {
                        pow5Factor_mv = 2;
                    } else if (v % 625L != 0L) {
                        pow5Factor_mv = 3;
                    } else {
                        pow5Factor_mv = 4;
                        v /= 625L;
                        while (v > 0L && v % 5L == 0L) {
                            v /= 5L;
                            ++pow5Factor_mv;
                        }
                    }
                    dvIsTrailingZeros = pow5Factor_mv >= q;
                } else if (even) {
                    int pow5Factor_mm;
                    v = mm3;
                    if (v % 5L != 0L) {
                        pow5Factor_mm = 0;
                    } else if (v % 25L != 0L) {
                        pow5Factor_mm = 1;
                    } else if (v % 125L != 0L) {
                        pow5Factor_mm = 2;
                    } else if (v % 625L != 0L) {
                        pow5Factor_mm = 3;
                    } else {
                        pow5Factor_mm = 4;
                        v /= 625L;
                        while (v > 0L && v % 5L == 0L) {
                            v /= 5L;
                            ++pow5Factor_mm;
                        }
                    }
                    dmIsTrailingZeros = pow5Factor_mm >= q;
                } else {
                    int pow5Factor_mp;
                    v = mp;
                    if (v % 5L != 0L) {
                        pow5Factor_mp = 0;
                    } else if (v % 25L != 0L) {
                        pow5Factor_mp = 1;
                    } else if (v % 125L != 0L) {
                        pow5Factor_mp = 2;
                    } else if (v % 625L != 0L) {
                        pow5Factor_mp = 3;
                    } else {
                        pow5Factor_mp = 4;
                        v /= 625L;
                        while (v > 0L && v % 5L == 0L) {
                            v /= 5L;
                            ++pow5Factor_mp;
                        }
                    }
                    if (pow5Factor_mp >= q) {
                        --dp;
                    }
                }
            }
        } else {
            int i = -e2 - (q = Math.max(0, (int)((long)(-e2) * 6989700L / 10000000L) - 1));
            int k = (i == 0 ? 1 : (int)(((long)i * 23219280L + 10000000L - 1L) / 10000000L)) - 121;
            int j = q - k;
            int actualShift = j - 93 - 21;
            if (actualShift < 0) {
                throw new IllegalArgumentException("" + actualShift);
            }
            int[] ints = POW5_SPLIT[i];
            long mHigh = mv >>> 31;
            long mLow = mv & Integer.MAX_VALUE;
            long bits13 = mHigh * (long)ints[0];
            long bits03 = mLow * (long)ints[0];
            long bits12 = mHigh * (long)ints[1];
            long bits02 = mLow * (long)ints[1];
            long bits11 = mHigh * (long)ints[2];
            long bits01 = mLow * (long)ints[2];
            long bits10 = mHigh * (long)ints[3];
            long bits00 = mLow * (long)ints[3];
            dv = ((((bits00 >>> 31) + bits01 + bits10 >>> 31) + bits02 + bits11 >>> 31) + bits03 + bits12 >>> 21) + (bits13 << 10) >>> actualShift;
            mHigh = mp >>> 31;
            mLow = mp & Integer.MAX_VALUE;
            bits13 = mHigh * (long)ints[0];
            bits03 = mLow * (long)ints[0];
            bits12 = mHigh * (long)ints[1];
            bits02 = mLow * (long)ints[1];
            bits11 = mHigh * (long)ints[2];
            bits01 = mLow * (long)ints[2];
            bits10 = mHigh * (long)ints[3];
            bits00 = mLow * (long)ints[3];
            dp = ((((bits00 >>> 31) + bits01 + bits10 >>> 31) + bits02 + bits11 >>> 31) + bits03 + bits12 >>> 21) + (bits13 << 10) >>> actualShift;
            mHigh = mm3 >>> 31;
            mLow = mm3 & Integer.MAX_VALUE;
            bits13 = mHigh * (long)ints[0];
            bits03 = mLow * (long)ints[0];
            bits12 = mHigh * (long)ints[1];
            bits02 = mLow * (long)ints[1];
            bits11 = mHigh * (long)ints[2];
            bits01 = mLow * (long)ints[2];
            bits10 = mHigh * (long)ints[3];
            bits00 = mLow * (long)ints[3];
            dm = ((((bits00 >>> 31) + bits01 + bits10 >>> 31) + bits02 + bits11 >>> 31) + bits03 + bits12 >>> 21) + (bits13 << 10) >>> actualShift;
            e10 = q + e2;
            if (q <= 1) {
                dvIsTrailingZeros = true;
                if (even) {
                    dmIsTrailingZeros = mmShift == 1;
                } else {
                    --dp;
                }
            } else if (q < 63) {
                boolean bl = dvIsTrailingZeros = (mv & (1L << q - 1) - 1L) == 0L;
            }
        }
        int vplength = dp >= 1000000000000000000L ? 19 : (dp >= 100000000000000000L ? 18 : (dp >= 10000000000000000L ? 17 : (dp >= 1000000000000000L ? 16 : (dp >= 100000000000000L ? 15 : (dp >= 10000000000000L ? 14 : (dp >= 1000000000000L ? 13 : (dp >= 100000000000L ? 12 : (dp >= 10000000000L ? 11 : (dp >= 1000000000L ? 10 : (dp >= 100000000L ? 9 : (dp >= 10000000L ? 8 : (dp >= 1000000L ? 7 : (dp >= 100000L ? 6 : (dp >= 10000L ? 5 : (dp >= 1000L ? 4 : (dp >= 100L ? 3 : (dp >= 10L ? 2 : 1)))))))))))))))));
        int exp = e10 + vplength - 1;
        boolean scientificNotation = exp < -3 || exp >= 7;
        int removed = 0;
        int lastRemovedDigit = 0;
        if (dmIsTrailingZeros || dvIsTrailingZeros) {
            while (!(dp / 10L <= dm / 10L || dp < 100L && scientificNotation)) {
                dmIsTrailingZeros &= dm % 10L == 0L;
                dvIsTrailingZeros &= lastRemovedDigit == 0;
                lastRemovedDigit = (int)(dv % 10L);
                dp /= 10L;
                dv /= 10L;
                dm /= 10L;
                ++removed;
            }
            if (dmIsTrailingZeros && even) {
                while (!(dm % 10L != 0L || dp < 100L && scientificNotation)) {
                    dvIsTrailingZeros &= lastRemovedDigit == 0;
                    lastRemovedDigit = (int)(dv % 10L);
                    dp /= 10L;
                    dv /= 10L;
                    dm /= 10L;
                    ++removed;
                }
            }
            if (dvIsTrailingZeros && lastRemovedDigit == 5 && dv % 2L == 0L) {
                lastRemovedDigit = 4;
            }
            output = dv + (long)(dv == dm && (!dmIsTrailingZeros || !even) || lastRemovedDigit >= 5 ? 1 : 0);
        } else {
            while (!(dp / 10L <= dm / 10L || dp < 100L && scientificNotation)) {
                lastRemovedDigit = (int)(dv % 10L);
                dp /= 10L;
                dv /= 10L;
                dm /= 10L;
                ++removed;
            }
            output = dv + (long)(dv == dm || lastRemovedDigit >= 5 ? 1 : 0);
        }
        int olength = vplength - removed;
        if (sign) {
            result[index++] = 45;
        }
        if (scientificNotation) {
            for (int i = 0; i < olength - 1; ++i) {
                int c = (int)(output % 10L);
                output /= 10L;
                result[index + olength - i] = (char)(48 + c);
            }
            result[index] = (char)(48L + output % 10L);
            result[index + 1] = 46;
            index += olength + 1;
            if (olength == 1) {
                result[index++] = 48;
            }
            result[index++] = 69;
            if (exp < 0) {
                result[index++] = 45;
                exp = -exp;
            }
            if (exp >= 100) {
                result[index++] = (char)(48 + exp / 100);
                result[index++] = (char)(48 + (exp %= 100) / 10);
            } else if (exp >= 10) {
                result[index++] = (char)(48 + exp / 10);
            }
            result[index++] = (char)(48 + exp % 10);
            return index - off;
        }
        if (exp < 0) {
            result[index++] = 48;
            result[index++] = 46;
            for (int i = -1; i > exp; --i) {
                result[index++] = 48;
            }
            int current = index;
            for (int i = 0; i < olength; ++i) {
                result[current + olength - i - 1] = (char)(48L + output % 10L);
                output /= 10L;
                ++index;
            }
        } else if (exp + 1 >= olength) {
            int i;
            for (i = 0; i < olength; ++i) {
                result[index + olength - i - 1] = (char)(48L + output % 10L);
                output /= 10L;
            }
            index += olength;
            for (i = olength; i < exp + 1; ++i) {
                result[index++] = 48;
            }
            result[index++] = 46;
            result[index++] = 48;
        } else {
            int current = index + 1;
            for (int i = 0; i < olength; ++i) {
                if (olength - i - 1 == exp) {
                    result[current + olength - i - 1] = 46;
                    --current;
                }
                result[current + olength - i - 1] = (char)(48L + output % 10L);
                output /= 10L;
            }
            index += olength + 1;
        }
        return index - off;
    }

    static {
        BigInteger mask = BigInteger.ONE.shiftLeft(31).subtract(BigInteger.ONE);
        BigInteger invMask = BigInteger.ONE.shiftLeft(31).subtract(BigInteger.ONE);
        for (int i = 0; i < 326; ++i) {
            int j;
            int expectedPow5Bits;
            BigInteger pow = BigInteger.valueOf(5L).pow(i);
            int pow5len = pow.bitLength();
            int n = expectedPow5Bits = i == 0 ? 1 : (int)(((long)i * 23219280L + 10000000L - 1L) / 10000000L);
            if (expectedPow5Bits != pow5len) {
                throw new IllegalStateException(pow5len + " != " + expectedPow5Bits);
            }
            if (i < POW5_SPLIT.length) {
                for (j = 0; j < 4; ++j) {
                    RyuDouble.POW5_SPLIT[i][j] = pow.shiftRight(pow5len - 121 + (3 - j) * 31).and(mask).intValue();
                }
            }
            if (i >= POW5_INV_SPLIT.length) continue;
            j = pow5len + 121;
            BigInteger inv = BigInteger.ONE.shiftLeft(j).divide(pow).add(BigInteger.ONE);
            for (int k = 0; k < 4; ++k) {
                RyuDouble.POW5_INV_SPLIT[i][k] = k == 0 ? inv.shiftRight((3 - k) * 31).intValue() : inv.shiftRight((3 - k) * 31).and(invMask).intValue();
            }
        }
    }
}

