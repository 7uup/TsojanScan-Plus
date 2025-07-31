/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.math;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.math.Constants;
import kotlin.math.MathKt;
import kotlin.math.MathKt__MathHKt;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000\"\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b7\u001a\u0011\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\tH\u0087\b\u001a\u0011\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\fH\u0087\b\u001a\u0011\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0010\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0007\u001a\u0011\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0010\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0007\u001a\u0011\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0019\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0019\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0010\u0010 \u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0007\u001a\u0011\u0010 \u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010!\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010!\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010\"\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010\"\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010#\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010#\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010$\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010$\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010%\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010%\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010&\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010&\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0019\u0010'\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u0001H\u0087\b\u001a\u0019\u0010'\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010(\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010(\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010)\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010)\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0018\u0010*\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010+\u001a\u00020\u0001H\u0007\u001a\u0018\u0010*\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010+\u001a\u00020\u0006H\u0007\u001a\u0011\u0010,\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010,\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0010\u0010-\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0007\u001a\u0010\u0010-\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0007\u001a\u0019\u0010.\u001a\u00020\u00012\u0006\u0010/\u001a\u00020\u00012\u0006\u00100\u001a\u00020\u0001H\u0087\b\u001a\u0019\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u0006H\u0087\b\u001a\u0019\u0010.\u001a\u00020\t2\u0006\u0010/\u001a\u00020\t2\u0006\u00100\u001a\u00020\tH\u0087\b\u001a\u0019\u0010.\u001a\u00020\f2\u0006\u0010/\u001a\u00020\f2\u0006\u00100\u001a\u00020\fH\u0087\b\u001a\u0019\u00101\u001a\u00020\u00012\u0006\u0010/\u001a\u00020\u00012\u0006\u00100\u001a\u00020\u0001H\u0087\b\u001a\u0019\u00101\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u0006H\u0087\b\u001a\u0019\u00101\u001a\u00020\t2\u0006\u0010/\u001a\u00020\t2\u0006\u00100\u001a\u00020\tH\u0087\b\u001a\u0019\u00101\u001a\u00020\f2\u0006\u0010/\u001a\u00020\f2\u0006\u00100\u001a\u00020\fH\u0087\b\u001a\u0011\u00102\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u00102\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u00103\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u00103\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u00104\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u00104\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u00105\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u00105\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u00106\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u00106\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u00107\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u00107\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0010\u00108\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0007\u001a\u0010\u00108\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0007\u001a\u0015\u00109\u001a\u00020\u0001*\u00020\u00012\u0006\u0010:\u001a\u00020\u0001H\u0087\b\u001a\u0015\u00109\u001a\u00020\u0006*\u00020\u00062\u0006\u0010:\u001a\u00020\u0006H\u0087\b\u001a\r\u0010;\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010;\u001a\u00020\u0006*\u00020\u0006H\u0087\b\u001a\u0015\u0010<\u001a\u00020\u0001*\u00020\u00012\u0006\u0010=\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010<\u001a\u00020\u0006*\u00020\u00062\u0006\u0010=\u001a\u00020\u0006H\u0087\b\u001a\r\u0010>\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010>\u001a\u00020\u0006*\u00020\u0006H\u0087\b\u001a\u0015\u0010?\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010?\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0018\u001a\u00020\tH\u0087\b\u001a\u0015\u0010?\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0015\u0010?\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0018\u001a\u00020\tH\u0087\b\u001a\f\u0010@\u001a\u00020\t*\u00020\u0001H\u0007\u001a\f\u0010@\u001a\u00020\t*\u00020\u0006H\u0007\u001a\f\u0010A\u001a\u00020\f*\u00020\u0001H\u0007\u001a\f\u0010A\u001a\u00020\f*\u00020\u0006H\u0007\u001a\u0015\u0010B\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010B\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\tH\u0087\b\u001a\u0015\u0010B\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0087\b\u001a\u0015\u0010B\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u000f\u001a\u00020\tH\u0087\b\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00018\u00c6\u0002X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u001f\u0010\u0000\u001a\u00020\u0006*\u00020\u00068\u00c6\u0002X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0002\u0010\u0007\u001a\u0004\b\u0004\u0010\b\"\u001f\u0010\u0000\u001a\u00020\t*\u00020\t8\u00c6\u0002X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0002\u0010\n\u001a\u0004\b\u0004\u0010\u000b\"\u001f\u0010\u0000\u001a\u00020\f*\u00020\f8\u00c6\u0002X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0002\u0010\r\u001a\u0004\b\u0004\u0010\u000e\"\u001f\u0010\u000f\u001a\u00020\u0001*\u00020\u00018\u00c6\u0002X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0010\u0010\u0003\u001a\u0004\b\u0011\u0010\u0005\"\u001f\u0010\u000f\u001a\u00020\u0006*\u00020\u00068\u00c6\u0002X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0010\u0010\u0007\u001a\u0004\b\u0011\u0010\b\"\u001e\u0010\u000f\u001a\u00020\t*\u00020\t8FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0010\u0010\n\u001a\u0004\b\u0011\u0010\u000b\"\u001e\u0010\u000f\u001a\u00020\t*\u00020\f8FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u0012\"\u001f\u0010\u0013\u001a\u00020\u0001*\u00020\u00018\u00c6\u0002X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0014\u0010\u0003\u001a\u0004\b\u0015\u0010\u0005\"\u001f\u0010\u0013\u001a\u00020\u0006*\u00020\u00068\u00c6\u0002X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0014\u0010\u0007\u001a\u0004\b\u0015\u0010\b\u00a8\u0006C"}, d2={"absoluteValue", "", "getAbsoluteValue$annotations", "(D)V", "getAbsoluteValue", "(D)D", "", "(F)V", "(F)F", "", "(I)V", "(I)I", "", "(J)V", "(J)J", "sign", "getSign$annotations", "getSign", "(J)I", "ulp", "getUlp$annotations", "getUlp", "abs", "x", "n", "acos", "acosh", "asin", "asinh", "atan", "atan2", "y", "atanh", "ceil", "cos", "cosh", "exp", "expm1", "floor", "hypot", "ln", "ln1p", "log", "base", "log10", "log2", "max", "a", "b", "min", "round", "sin", "sinh", "sqrt", "tan", "tanh", "truncate", "IEEErem", "divisor", "nextDown", "nextTowards", "to", "nextUp", "pow", "roundToInt", "roundToLong", "withSign", "kotlin-stdlib"}, xs="kotlin/math/MathKt")
class MathKt__MathJVMKt
extends MathKt__MathHKt {
    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double sin(double x) {
        int $i$f$sin = 0;
        return Math.sin(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double cos(double x) {
        int $i$f$cos = 0;
        return Math.cos(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double tan(double x) {
        int $i$f$tan = 0;
        return Math.tan(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double asin(double x) {
        int $i$f$asin = 0;
        return Math.asin(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double acos(double x) {
        int $i$f$acos = 0;
        return Math.acos(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double atan(double x) {
        int $i$f$atan = 0;
        return Math.atan(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double atan2(double y, double x) {
        int $i$f$atan2 = 0;
        return Math.atan2(y, x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double sinh(double x) {
        int $i$f$sinh = 0;
        return Math.sinh(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double cosh(double x) {
        int $i$f$cosh = 0;
        return Math.cosh(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double tanh(double x) {
        int $i$f$tanh = 0;
        return Math.tanh(x);
    }

    /*
     * WARNING - void declaration
     */
    @SinceKotlin(version="1.2")
    public static final double asinh(double x) {
        double d;
        if (x >= Constants.taylor_n_bound) {
            d = x > Constants.upper_taylor_n_bound ? (x > Constants.upper_taylor_2_bound ? Math.log(x) + Constants.LN2 : Math.log(x * (double)2 + 1.0 / (x * (double)2))) : Math.log(x + Math.sqrt(x * x + 1.0));
        } else if (x <= -Constants.taylor_n_bound) {
            d = -MathKt.asinh(-x);
        } else {
            void var2_1;
            double result = x;
            if (Math.abs(x) >= Constants.taylor_2_bound) {
                result -= x * x * x / (double)6;
            }
            d = var2_1;
        }
        return d;
    }

    @SinceKotlin(version="1.2")
    public static final double acosh(double x) {
        double d;
        if (x < 1.0) {
            d = Double.NaN;
        } else if (x > Constants.upper_taylor_2_bound) {
            d = Math.log(x) + Constants.LN2;
        } else if (x - 1.0 >= Constants.taylor_n_bound) {
            d = Math.log(x + Math.sqrt(x * x - 1.0));
        } else {
            double y;
            double result = y = Math.sqrt(x - 1.0);
            if (y >= Constants.taylor_2_bound) {
                result -= y * y * y / (double)12;
            }
            d = Math.sqrt(2.0) * result;
        }
        return d;
    }

    @SinceKotlin(version="1.2")
    public static final double atanh(double x) {
        if (Math.abs(x) < Constants.taylor_n_bound) {
            double result = x;
            if (Math.abs(x) > Constants.taylor_2_bound) {
                result += x * x * x / (double)3;
            }
            return result;
        }
        return Math.log((1.0 + x) / (1.0 - x)) / (double)2;
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double hypot(double x, double y) {
        int $i$f$hypot = 0;
        return Math.hypot(x, y);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double sqrt(double x) {
        int $i$f$sqrt = 0;
        return Math.sqrt(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double exp(double x) {
        int $i$f$exp = 0;
        return Math.exp(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double expm1(double x) {
        int $i$f$expm1 = 0;
        return Math.expm1(x);
    }

    @SinceKotlin(version="1.2")
    public static final double log(double x, double base) {
        if (base <= 0.0 || base == 1.0) {
            return Double.NaN;
        }
        return Math.log(x) / Math.log(base);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double ln(double x) {
        int $i$f$ln = 0;
        return Math.log(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double log10(double x) {
        int $i$f$log10 = 0;
        return Math.log10(x);
    }

    @SinceKotlin(version="1.2")
    public static final double log2(double x) {
        return Math.log(x) / Constants.LN2;
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double ln1p(double x) {
        int $i$f$ln1p = 0;
        return Math.log1p(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double ceil(double x) {
        int $i$f$ceil = 0;
        return Math.ceil(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double floor(double x) {
        int $i$f$floor = 0;
        return Math.floor(x);
    }

    /*
     * Enabled aggressive block sorting
     */
    @SinceKotlin(version="1.2")
    public static final double truncate(double x) {
        double d;
        block5: {
            block4: {
                double d2 = x;
                boolean bl = false;
                if (Double.isNaN(d2)) break block4;
                d2 = x;
                bl = false;
                if (!Double.isInfinite(d2)) break block5;
            }
            d = x;
            return d;
        }
        if (x > 0.0) {
            boolean bl = false;
            d = Math.floor(x);
            return d;
        }
        boolean bl = false;
        d = Math.ceil(x);
        return d;
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double round(double x) {
        int $i$f$round = 0;
        return Math.rint(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double abs(double x) {
        int $i$f$abs = 0;
        return Math.abs(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double sign(double x) {
        int $i$f$sign = 0;
        return Math.signum(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double min(double a, double b) {
        int $i$f$min = 0;
        return Math.min(a, b);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double max(double a, double b) {
        int $i$f$max = 0;
        return Math.max(a, b);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double pow(double $this$pow, double x) {
        int $i$f$pow = 0;
        return Math.pow($this$pow, x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double pow(double $this$pow, int n) {
        int $i$f$pow = 0;
        return Math.pow($this$pow, n);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double IEEErem(double $this$IEEErem, double divisor) {
        int $i$f$IEEErem = 0;
        return Math.IEEEremainder($this$IEEErem, divisor);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    public static /* synthetic */ void getAbsoluteValue$annotations(double d) {
    }

    private static final double getAbsoluteValue(double $this$absoluteValue) {
        int $i$f$getAbsoluteValue = 0;
        return Math.abs($this$absoluteValue);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    public static /* synthetic */ void getSign$annotations(double d) {
    }

    private static final double getSign(double $this$sign) {
        int $i$f$getSign = 0;
        return Math.signum($this$sign);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double withSign(double $this$withSign, double sign) {
        int $i$f$withSign = 0;
        return Math.copySign($this$withSign, sign);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double withSign(double $this$withSign, int sign) {
        int $i$f$withSign = 0;
        return Math.copySign($this$withSign, (double)sign);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    public static /* synthetic */ void getUlp$annotations(double d) {
    }

    private static final double getUlp(double $this$ulp) {
        int $i$f$getUlp = 0;
        return Math.ulp($this$ulp);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double nextUp(double $this$nextUp) {
        int $i$f$nextUp = 0;
        return Math.nextUp($this$nextUp);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double nextDown(double $this$nextDown) {
        int $i$f$nextDown = 0;
        return Math.nextAfter($this$nextDown, Double.NEGATIVE_INFINITY);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double nextTowards(double $this$nextTowards, double to) {
        int $i$f$nextTowards = 0;
        return Math.nextAfter($this$nextTowards, to);
    }

    @SinceKotlin(version="1.2")
    public static final int roundToInt(double $this$roundToInt) {
        double d = $this$roundToInt;
        boolean bl = false;
        if (Double.isNaN(d)) {
            throw (Throwable)new IllegalArgumentException("Cannot round NaN value.");
        }
        return $this$roundToInt > (double)Integer.MAX_VALUE ? Integer.MAX_VALUE : ($this$roundToInt < (double)Integer.MIN_VALUE ? Integer.MIN_VALUE : (int)Math.round($this$roundToInt));
    }

    @SinceKotlin(version="1.2")
    public static final long roundToLong(double $this$roundToLong) {
        double d = $this$roundToLong;
        boolean bl = false;
        if (Double.isNaN(d)) {
            throw (Throwable)new IllegalArgumentException("Cannot round NaN value.");
        }
        return Math.round($this$roundToLong);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float sin(float x) {
        int $i$f$sin = 0;
        return (float)Math.sin(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float cos(float x) {
        int $i$f$cos = 0;
        return (float)Math.cos(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float tan(float x) {
        int $i$f$tan = 0;
        return (float)Math.tan(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float asin(float x) {
        int $i$f$asin = 0;
        return (float)Math.asin(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float acos(float x) {
        int $i$f$acos = 0;
        return (float)Math.acos(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float atan(float x) {
        int $i$f$atan = 0;
        return (float)Math.atan(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float atan2(float y, float x) {
        int $i$f$atan2 = 0;
        return (float)Math.atan2(y, x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float sinh(float x) {
        int $i$f$sinh = 0;
        return (float)Math.sinh(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float cosh(float x) {
        int $i$f$cosh = 0;
        return (float)Math.cosh(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float tanh(float x) {
        int $i$f$tanh = 0;
        return (float)Math.tanh(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float asinh(float x) {
        int $i$f$asinh = 0;
        return (float)MathKt.asinh((double)x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float acosh(float x) {
        int $i$f$acosh = 0;
        return (float)MathKt.acosh((double)x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float atanh(float x) {
        int $i$f$atanh = 0;
        return (float)MathKt.atanh((double)x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float hypot(float x, float y) {
        int $i$f$hypot = 0;
        return (float)Math.hypot(x, y);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float sqrt(float x) {
        int $i$f$sqrt = 0;
        return (float)Math.sqrt(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float exp(float x) {
        int $i$f$exp = 0;
        return (float)Math.exp(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float expm1(float x) {
        int $i$f$expm1 = 0;
        return (float)Math.expm1(x);
    }

    @SinceKotlin(version="1.2")
    public static final float log(float x, float base) {
        if (base <= 0.0f || base == 1.0f) {
            return Float.NaN;
        }
        return (float)(Math.log(x) / Math.log(base));
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float ln(float x) {
        int $i$f$ln = 0;
        return (float)Math.log(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float log10(float x) {
        int $i$f$log10 = 0;
        return (float)Math.log10(x);
    }

    @SinceKotlin(version="1.2")
    public static final float log2(float x) {
        return (float)(Math.log(x) / Constants.LN2);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float ln1p(float x) {
        int $i$f$ln1p = 0;
        return (float)Math.log1p(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float ceil(float x) {
        int $i$f$ceil = 0;
        return (float)Math.ceil(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float floor(float x) {
        int $i$f$floor = 0;
        return (float)Math.floor(x);
    }

    /*
     * Enabled aggressive block sorting
     */
    @SinceKotlin(version="1.2")
    public static final float truncate(float x) {
        float f;
        block5: {
            block4: {
                float f2 = x;
                boolean bl = false;
                if (Float.isNaN(f2)) break block4;
                f2 = x;
                bl = false;
                if (!Float.isInfinite(f2)) break block5;
            }
            f = x;
            return f;
        }
        if (x > 0.0f) {
            boolean bl = false;
            f = (float)Math.floor(x);
            return f;
        }
        boolean bl = false;
        f = (float)Math.ceil(x);
        return f;
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float round(float x) {
        int $i$f$round = 0;
        return (float)Math.rint(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float abs(float x) {
        int $i$f$abs = 0;
        return Math.abs(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float sign(float x) {
        int $i$f$sign = 0;
        return Math.signum(x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float min(float a, float b) {
        int $i$f$min = 0;
        return Math.min(a, b);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float max(float a, float b) {
        int $i$f$max = 0;
        return Math.max(a, b);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float pow(float $this$pow, float x) {
        int $i$f$pow = 0;
        return (float)Math.pow($this$pow, x);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float pow(float $this$pow, int n) {
        int $i$f$pow = 0;
        return (float)Math.pow($this$pow, n);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float IEEErem(float $this$IEEErem, float divisor) {
        int $i$f$IEEErem = 0;
        return (float)Math.IEEEremainder($this$IEEErem, divisor);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    public static /* synthetic */ void getAbsoluteValue$annotations(float f) {
    }

    private static final float getAbsoluteValue(float $this$absoluteValue) {
        int $i$f$getAbsoluteValue = 0;
        return Math.abs($this$absoluteValue);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    public static /* synthetic */ void getSign$annotations(float f) {
    }

    private static final float getSign(float $this$sign) {
        int $i$f$getSign = 0;
        return Math.signum($this$sign);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float withSign(float $this$withSign, float sign) {
        int $i$f$withSign = 0;
        return Math.copySign($this$withSign, sign);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float withSign(float $this$withSign, int sign) {
        int $i$f$withSign = 0;
        return Math.copySign($this$withSign, sign);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    public static /* synthetic */ void getUlp$annotations(float f) {
    }

    private static final float getUlp(float $this$ulp) {
        int $i$f$getUlp = 0;
        return Math.ulp($this$ulp);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float nextUp(float $this$nextUp) {
        int $i$f$nextUp = 0;
        return Math.nextUp($this$nextUp);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float nextDown(float $this$nextDown) {
        int $i$f$nextDown = 0;
        return Math.nextAfter($this$nextDown, Double.NEGATIVE_INFINITY);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float nextTowards(float $this$nextTowards, float to) {
        int $i$f$nextTowards = 0;
        return Math.nextAfter($this$nextTowards, (double)to);
    }

    @SinceKotlin(version="1.2")
    public static final int roundToInt(float $this$roundToInt) {
        float f = $this$roundToInt;
        boolean bl = false;
        if (Float.isNaN(f)) {
            throw (Throwable)new IllegalArgumentException("Cannot round NaN value.");
        }
        return Math.round($this$roundToInt);
    }

    @SinceKotlin(version="1.2")
    public static final long roundToLong(float $this$roundToLong) {
        return MathKt.roundToLong((double)$this$roundToLong);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final int abs(int n) {
        int $i$f$abs = 0;
        return Math.abs(n);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final int min(int a, int b) {
        int $i$f$min = 0;
        return Math.min(a, b);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final int max(int a, int b) {
        int $i$f$max = 0;
        return Math.max(a, b);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    public static /* synthetic */ void getAbsoluteValue$annotations(int n) {
    }

    private static final int getAbsoluteValue(int $this$absoluteValue) {
        int $i$f$getAbsoluteValue = 0;
        return Math.abs($this$absoluteValue);
    }

    @SinceKotlin(version="1.2")
    public static /* synthetic */ void getSign$annotations(int n) {
    }

    public static final int getSign(int $this$sign) {
        return $this$sign < 0 ? -1 : ($this$sign > 0 ? 1 : 0);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final long abs(long n) {
        int $i$f$abs = 0;
        return Math.abs(n);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final long min(long a, long b) {
        int $i$f$min = 0;
        return Math.min(a, b);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final long max(long a, long b) {
        int $i$f$max = 0;
        return Math.max(a, b);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    public static /* synthetic */ void getAbsoluteValue$annotations(long l) {
    }

    private static final long getAbsoluteValue(long $this$absoluteValue) {
        int $i$f$getAbsoluteValue = 0;
        return Math.abs($this$absoluteValue);
    }

    @SinceKotlin(version="1.2")
    public static /* synthetic */ void getSign$annotations(long l) {
    }

    public static final int getSign(long $this$sign) {
        return $this$sign < 0L ? -1 : ($this$sign > 0L ? 1 : 0);
    }
}

