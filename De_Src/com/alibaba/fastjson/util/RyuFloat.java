/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.util;

public final class RyuFloat {
    private static final int[][] POW5_SPLIT = new int[][]{{0x20000000, 0}, {0x28000000, 0}, {0x32000000, 0}, {1048576000, 0}, {655360000, 0}, {819200000, 0}, {1024000000, 0}, {640000000, 0}, {800000000, 0}, {1000000000, 0}, {625000000, 0}, {781250000, 0}, {976562500, 0}, {610351562, 0x40000000}, {762939453, 0x10000000}, {953674316, 0x34000000}, {596046447, 0x60800000}, {745058059, 1486880768}, {931322574, 1321730048}, {582076609, 289210368}, {727595761, 898383872}, {909494701, 1659850752}, {568434188, 1305842176}, {710542735, 1632302720}, {888178419, 1503507488}, {555111512, 671256724}, {693889390, 839070905}, {867361737, 2122580455}, {542101086, 521306416}, {677626357, 1725374844}, {847032947, 546105819}, {1058791184, 145761362}, {661744490, 91100851}, {827180612, 1187617888}, {1033975765, 1484522360}, {646234853, 1196261931}, {807793566, 2032198326}, {1009741958, 1466506084}, {631088724, 379695390}, {788860905, 474619238}, {986076131, 1130144959}, {616297582, 437905143}, {770371977, 1621123253}, {962964972, 415791331}, {601853107, 1333611405}, {752316384, 1130143345}, {940395480, 1412679181}};
    private static final int[][] POW5_INV_SPLIT = new int[][]{{0x10000000, 1}, {0xCCCCCCC, 0x66666667}, {171798691, 1803886265}, {137438953, 1013612282}, {219902325, 1192282922}, {175921860, 953826338}, {140737488, 763061070}, {225179981, 791400982}, {180143985, 203624056}, {144115188, 162899245}, {230584300, 1978625710}, {184467440, 1582900568}, {147573952, 1266320455}, {236118324, 308125809}, {188894659, 675997377}, {151115727, 970294631}, {241785163, 1981968139}, {193428131, 297084323}, {154742504, 1955654377}, {247588007, 1840556814}, {198070406, 613451992}, {158456325, 61264864}, {253530120, 98023782}, {202824096, 78419026}, {162259276, 1780722139}, {259614842, 1990161963}, {207691874, 733136111}, {166153499, 1016005619}, {265845599, 337118801}, {212676479, 699191770}, {170141183, 988850146}};

    public static String toString(float value) {
        char[] result = new char[15];
        int len = RyuFloat.toString(value, result, 0);
        return new String(result, 0, len);
    }

    public static int toString(float value, char[] result, int off) {
        int i;
        int current;
        int i2;
        boolean dmIsTrailingZeros;
        boolean dvIsTrailingZeros;
        boolean dpIsTrailingZeros;
        int e10;
        int dm;
        int dp;
        int dv;
        int q;
        int m22;
        int e2;
        int FLOAT_MANTISSA_MASK = 0x7FFFFF;
        int FLOAT_EXPONENT_MASK = 255;
        int FLOAT_EXPONENT_BIAS = 127;
        long LOG10_2_NUMERATOR = 3010299L;
        long LOG10_5_DENOMINATOR = 10000000L;
        long LOG10_5_NUMERATOR = 6989700L;
        int index = off;
        if (Float.isNaN(value)) {
            result[index++] = 78;
            result[index++] = 97;
            result[index++] = 78;
            return index - off;
        }
        if (value == Float.POSITIVE_INFINITY) {
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
        if (value == Float.NEGATIVE_INFINITY) {
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
        int bits = Float.floatToIntBits(value);
        if (bits == 0) {
            result[index++] = 48;
            result[index++] = 46;
            result[index++] = 48;
            return index - off;
        }
        if (bits == Integer.MIN_VALUE) {
            result[index++] = 45;
            result[index++] = 48;
            result[index++] = 46;
            result[index++] = 48;
            return index - off;
        }
        int ieeeExponent = bits >> 23 & 0xFF;
        int ieeeMantissa = bits & 0x7FFFFF;
        if (ieeeExponent == 0) {
            e2 = -149;
            m22 = ieeeMantissa;
        } else {
            e2 = ieeeExponent - 127 - 23;
            m22 = ieeeMantissa | 0x800000;
        }
        boolean sign = bits < 0;
        boolean even = (m22 & 1) == 0;
        int mv = 4 * m22;
        int mp = 4 * m22 + 2;
        int mm3 = 4 * m22 - ((long)m22 != 0x800000L || ieeeExponent <= 1 ? 2 : 1);
        int lastRemovedDigit = 0;
        if ((e2 -= 2) >= 0) {
            q = (int)((long)e2 * 3010299L / 10000000L);
            int k = 59 + (q == 0 ? 1 : (int)(((long)q * 23219280L + 10000000L - 1L) / 10000000L)) - 1;
            int i3 = -e2 + q + k;
            long pis0 = POW5_INV_SPLIT[q][0];
            long pis1 = POW5_INV_SPLIT[q][1];
            dv = (int)((long)mv * pis0 + ((long)mv * pis1 >> 31) >> i3 - 31);
            dp = (int)((long)mp * pis0 + ((long)mp * pis1 >> 31) >> i3 - 31);
            dm = (int)((long)mm3 * pis0 + ((long)mm3 * pis1 >> 31) >> i3 - 31);
            if (q != 0 && (dp - 1) / 10 <= dm / 10) {
                int e = q - 1;
                int l = 59 + (e == 0 ? 1 : (int)(((long)e * 23219280L + 10000000L - 1L) / 10000000L)) - 1;
                int qx = q - 1;
                int ii = -e2 + q - 1 + l;
                long mulPow5InvDivPow2 = (long)mv * (long)POW5_INV_SPLIT[qx][0] + ((long)mv * (long)POW5_INV_SPLIT[qx][1] >> 31) >> ii - 31;
                lastRemovedDigit = (int)(mulPow5InvDivPow2 % 10L);
            }
            e10 = q;
            int pow5Factor_mp = 0;
            int v = mp;
            while (v > 0 && v % 5 == 0) {
                v /= 5;
                ++pow5Factor_mp;
            }
            int pow5Factor_mv = 0;
            int v2 = mv;
            while (v2 > 0 && v2 % 5 == 0) {
                v2 /= 5;
                ++pow5Factor_mv;
            }
            int pow5Factor_mm = 0;
            int v3 = mm3;
            while (v3 > 0 && v3 % 5 == 0) {
                v3 /= 5;
                ++pow5Factor_mm;
            }
            dpIsTrailingZeros = pow5Factor_mp >= q;
            dvIsTrailingZeros = pow5Factor_mv >= q;
            dmIsTrailingZeros = pow5Factor_mm >= q;
        } else {
            q = (int)((long)(-e2) * 6989700L / 10000000L);
            int i4 = -e2 - q;
            int k = (i4 == 0 ? 1 : (int)(((long)i4 * 23219280L + 10000000L - 1L) / 10000000L)) - 61;
            int j = q - k;
            long ps0 = POW5_SPLIT[i4][0];
            long ps1 = POW5_SPLIT[i4][1];
            int j31 = j - 31;
            dv = (int)((long)mv * ps0 + ((long)mv * ps1 >> 31) >> j31);
            dp = (int)((long)mp * ps0 + ((long)mp * ps1 >> 31) >> j31);
            dm = (int)((long)mm3 * ps0 + ((long)mm3 * ps1 >> 31) >> j31);
            if (q != 0 && (dp - 1) / 10 <= dm / 10) {
                int e = i4 + 1;
                j = q - 1 - ((e == 0 ? 1 : (int)(((long)e * 23219280L + 10000000L - 1L) / 10000000L)) - 61);
                int ix = i4 + 1;
                long mulPow5divPow2 = (long)mv * (long)POW5_SPLIT[ix][0] + ((long)mv * (long)POW5_SPLIT[ix][1] >> 31) >> j - 31;
                lastRemovedDigit = (int)(mulPow5divPow2 % 10L);
            }
            e10 = q + e2;
            dpIsTrailingZeros = 1 >= q;
            dvIsTrailingZeros = q < 23 && (mv & (1 << q - 1) - 1) == 0;
            dmIsTrailingZeros = (mm3 % 2 == 1 ? 0 : 1) >= q;
        }
        int dplength = 10;
        for (int factor = 1000000000; dplength > 0 && dp < factor; factor /= 10, --dplength) {
        }
        int exp = e10 + dplength - 1;
        boolean scientificNotation = exp < -3 || exp >= 7;
        int removed = 0;
        if (dpIsTrailingZeros && !even) {
            --dp;
        }
        while (!(dp / 10 <= dm / 10 || dp < 100 && scientificNotation)) {
            dmIsTrailingZeros &= dm % 10 == 0;
            dp /= 10;
            lastRemovedDigit = dv % 10;
            dv /= 10;
            dm /= 10;
            ++removed;
        }
        if (dmIsTrailingZeros && even) {
            while (!(dm % 10 != 0 || dp < 100 && scientificNotation)) {
                dp /= 10;
                lastRemovedDigit = dv % 10;
                dv /= 10;
                dm /= 10;
                ++removed;
            }
        }
        if (dvIsTrailingZeros && lastRemovedDigit == 5 && dv % 2 == 0) {
            lastRemovedDigit = 4;
        }
        int output = dv + (dv == dm && (!dmIsTrailingZeros || !even) || lastRemovedDigit >= 5 ? 1 : 0);
        int olength = dplength - removed;
        if (sign) {
            result[index++] = 45;
        }
        if (scientificNotation) {
            for (i2 = 0; i2 < olength - 1; ++i2) {
                int c = output % 10;
                output /= 10;
                result[index + olength - i2] = (char)(48 + c);
            }
            result[index] = (char)(48 + output % 10);
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
            if (exp >= 10) {
                result[index++] = (char)(48 + exp / 10);
            }
            result[index++] = (char)(48 + exp % 10);
        } else if (exp < 0) {
            result[index++] = 48;
            result[index++] = 46;
            for (i2 = -1; i2 > exp; --i2) {
                result[index++] = 48;
            }
            current = index;
            for (i = 0; i < olength; ++i) {
                result[current + olength - i - 1] = (char)(48 + output % 10);
                output /= 10;
                ++index;
            }
        } else if (exp + 1 >= olength) {
            for (i2 = 0; i2 < olength; ++i2) {
                result[index + olength - i2 - 1] = (char)(48 + output % 10);
                output /= 10;
            }
            index += olength;
            for (i2 = olength; i2 < exp + 1; ++i2) {
                result[index++] = 48;
            }
            result[index++] = 46;
            result[index++] = 48;
        } else {
            current = index + 1;
            for (i = 0; i < olength; ++i) {
                if (olength - i - 1 == exp) {
                    result[current + olength - i - 1] = 46;
                    --current;
                }
                result[current + olength - i - 1] = (char)(48 + output % 10);
                output /= 10;
            }
            index += olength + 1;
        }
        return index - off;
    }
}

