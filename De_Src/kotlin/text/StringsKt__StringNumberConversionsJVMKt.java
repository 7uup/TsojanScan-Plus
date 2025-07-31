/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.text;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.ScreenFloatValueRegEx;
import kotlin.text.StringsKt;
import kotlin.text.StringsKt__StringBuilderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000Z\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\u001a4\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00010\u0005H\u0082\b\u00a2\u0006\u0004\b\u0006\u0010\u0007\u001a\r\u0010\b\u001a\u00020\t*\u00020\u0003H\u0087\b\u001a\u0015\u0010\b\u001a\u00020\t*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0087\b\u001a\u000e\u0010\f\u001a\u0004\u0018\u00010\t*\u00020\u0003H\u0007\u001a\u0016\u0010\f\u001a\u0004\u0018\u00010\t*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0007\u001a\r\u0010\r\u001a\u00020\u000e*\u00020\u0003H\u0087\b\u001a\u0015\u0010\r\u001a\u00020\u000e*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u000e\u0010\u0011\u001a\u0004\u0018\u00010\u000e*\u00020\u0003H\u0007\u001a\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u000e*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0007\u001a\r\u0010\u0012\u001a\u00020\u0013*\u00020\u0003H\u0087\b\u001a\u0014\u0010\u0012\u001a\u00020\u0013*\u0004\u0018\u00010\u0003H\u0087\b\u00a2\u0006\u0002\b\u0014\u001a\r\u0010\u0015\u001a\u00020\u0016*\u00020\u0003H\u0087\b\u001a\u0015\u0010\u0015\u001a\u00020\u0016*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\r\u0010\u0017\u001a\u00020\u0018*\u00020\u0003H\u0087\b\u001a\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0018*\u00020\u0003H\u0007\u00a2\u0006\u0002\u0010\u001a\u001a\r\u0010\u001b\u001a\u00020\u001c*\u00020\u0003H\u0087\b\u001a\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u001c*\u00020\u0003H\u0007\u00a2\u0006\u0002\u0010\u001e\u001a\r\u0010\u001f\u001a\u00020\u0010*\u00020\u0003H\u0087\b\u001a\u0015\u0010\u001f\u001a\u00020\u0010*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\r\u0010 \u001a\u00020!*\u00020\u0003H\u0087\b\u001a\u0015\u0010 \u001a\u00020!*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\r\u0010\"\u001a\u00020#*\u00020\u0003H\u0087\b\u001a\u0015\u0010\"\u001a\u00020#*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010$\u001a\u00020\u0003*\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010$\u001a\u00020\u0003*\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010$\u001a\u00020\u0003*\u00020!2\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010$\u001a\u00020\u0003*\u00020#2\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u00a8\u0006%"}, d2={"screenFloatValue", "T", "str", "", "parse", "Lkotlin/Function1;", "screenFloatValue$StringsKt__StringNumberConversionsJVMKt", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "toBigDecimal", "Ljava/math/BigDecimal;", "mathContext", "Ljava/math/MathContext;", "toBigDecimalOrNull", "toBigInteger", "Ljava/math/BigInteger;", "radix", "", "toBigIntegerOrNull", "toBoolean", "", "toBooleanNullable", "toByte", "", "toDouble", "", "toDoubleOrNull", "(Ljava/lang/String;)Ljava/lang/Double;", "toFloat", "", "toFloatOrNull", "(Ljava/lang/String;)Ljava/lang/Float;", "toInt", "toLong", "", "toShort", "", "toString", "kotlin-stdlib"}, xs="kotlin/text/StringsKt")
class StringsKt__StringNumberConversionsJVMKt
extends StringsKt__StringBuilderKt {
    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final String toString(byte $this$toString, int radix) {
        int $i$f$toString = 0;
        byte by = $this$toString;
        int n = CharsKt.checkRadix(radix);
        boolean bl = false;
        String string = Integer.toString(by, CharsKt.checkRadix(n));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri\u2026(this, checkRadix(radix))");
        return string;
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final String toString(short $this$toString, int radix) {
        int $i$f$toString = 0;
        short s2 = $this$toString;
        int n = CharsKt.checkRadix(radix);
        boolean bl = false;
        String string = Integer.toString(s2, CharsKt.checkRadix(n));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri\u2026(this, checkRadix(radix))");
        return string;
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final String toString(int $this$toString, int radix) {
        int $i$f$toString = 0;
        String string = Integer.toString($this$toString, CharsKt.checkRadix(radix));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri\u2026(this, checkRadix(radix))");
        return string;
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final String toString(long $this$toString, int radix) {
        int $i$f$toString = 0;
        String string = Long.toString($this$toString, CharsKt.checkRadix(radix));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Long.toString(this, checkRadix(radix))");
        return string;
    }

    @Deprecated(message="Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince="1.4")
    @InlineOnly
    private static final /* synthetic */ boolean toBoolean(String $this$toBoolean) {
        int $i$f$toBoolean = 0;
        String string = $this$toBoolean;
        boolean bl = false;
        return Boolean.parseBoolean(string);
    }

    @JvmName(name="toBooleanNullable")
    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final boolean toBooleanNullable(String $this$toBoolean) {
        int $i$f$toBooleanNullable = 0;
        return Boolean.parseBoolean($this$toBoolean);
    }

    @InlineOnly
    private static final byte toByte(String $this$toByte) {
        int $i$f$toByte = 0;
        return Byte.parseByte($this$toByte);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final byte toByte(String $this$toByte, int radix) {
        int $i$f$toByte = 0;
        return Byte.parseByte($this$toByte, CharsKt.checkRadix(radix));
    }

    @InlineOnly
    private static final short toShort(String $this$toShort) {
        int $i$f$toShort = 0;
        return Short.parseShort($this$toShort);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final short toShort(String $this$toShort, int radix) {
        int $i$f$toShort = 0;
        return Short.parseShort($this$toShort, CharsKt.checkRadix(radix));
    }

    @InlineOnly
    private static final int toInt(String $this$toInt) {
        int $i$f$toInt = 0;
        return Integer.parseInt($this$toInt);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final int toInt(String $this$toInt, int radix) {
        int $i$f$toInt = 0;
        return Integer.parseInt($this$toInt, CharsKt.checkRadix(radix));
    }

    @InlineOnly
    private static final long toLong(String $this$toLong) {
        int $i$f$toLong = 0;
        return Long.parseLong($this$toLong);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final long toLong(String $this$toLong, int radix) {
        int $i$f$toLong = 0;
        return Long.parseLong($this$toLong, CharsKt.checkRadix(radix));
    }

    @InlineOnly
    private static final float toFloat(String $this$toFloat) {
        int $i$f$toFloat = 0;
        return Float.parseFloat($this$toFloat);
    }

    @InlineOnly
    private static final double toDouble(String $this$toDouble) {
        int $i$f$toDouble = 0;
        return Double.parseDouble($this$toDouble);
    }

    @SinceKotlin(version="1.1")
    @Nullable
    public static final Float toFloatOrNull(@NotNull String $this$toFloatOrNull) {
        Float f;
        Intrinsics.checkNotNullParameter($this$toFloatOrNull, "$this$toFloatOrNull");
        boolean $i$f$screenFloatValue$StringsKt__StringNumberConversionsJVMKt = false;
        try {
            Float f2;
            if (ScreenFloatValueRegEx.value.matches($this$toFloatOrNull)) {
                String p1 = $this$toFloatOrNull;
                boolean bl = false;
                f2 = Float.valueOf(Float.parseFloat(p1));
            } else {
                f2 = null;
            }
            f = f2;
        } catch (NumberFormatException e$iv) {
            f = null;
        }
        return f;
    }

    @SinceKotlin(version="1.1")
    @Nullable
    public static final Double toDoubleOrNull(@NotNull String $this$toDoubleOrNull) {
        Double d;
        Intrinsics.checkNotNullParameter($this$toDoubleOrNull, "$this$toDoubleOrNull");
        boolean $i$f$screenFloatValue$StringsKt__StringNumberConversionsJVMKt = false;
        try {
            Double d2;
            if (ScreenFloatValueRegEx.value.matches($this$toDoubleOrNull)) {
                String p1 = $this$toDoubleOrNull;
                boolean bl = false;
                d2 = Double.parseDouble(p1);
            } else {
                d2 = null;
            }
            d = d2;
        } catch (NumberFormatException e$iv) {
            d = null;
        }
        return d;
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigInteger toBigInteger(String $this$toBigInteger) {
        int $i$f$toBigInteger = 0;
        return new BigInteger($this$toBigInteger);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigInteger toBigInteger(String $this$toBigInteger, int radix) {
        int $i$f$toBigInteger = 0;
        return new BigInteger($this$toBigInteger, CharsKt.checkRadix(radix));
    }

    @SinceKotlin(version="1.2")
    @Nullable
    public static final BigInteger toBigIntegerOrNull(@NotNull String $this$toBigIntegerOrNull) {
        Intrinsics.checkNotNullParameter($this$toBigIntegerOrNull, "$this$toBigIntegerOrNull");
        return StringsKt.toBigIntegerOrNull($this$toBigIntegerOrNull, 10);
    }

    /*
     * WARNING - void declaration
     */
    @SinceKotlin(version="1.2")
    @Nullable
    public static final BigInteger toBigIntegerOrNull(@NotNull String $this$toBigIntegerOrNull, int radix) {
        int n;
        Intrinsics.checkNotNullParameter($this$toBigIntegerOrNull, "$this$toBigIntegerOrNull");
        CharsKt.checkRadix(radix);
        int length = $this$toBigIntegerOrNull.length();
        switch (length) {
            case 0: {
                return null;
            }
            case 1: {
                if (CharsKt.digitOf($this$toBigIntegerOrNull.charAt(0), radix) >= 0) break;
                return null;
            }
            default: {
                int start;
                n = start = $this$toBigIntegerOrNull.charAt(0) == '-' ? 1 : 0;
                int n2 = length;
                while (n < n2) {
                    void index;
                    if (CharsKt.digitOf($this$toBigIntegerOrNull.charAt((int)index), radix) < 0) {
                        return null;
                    }
                    ++index;
                }
                break block0;
            }
        }
        String string = $this$toBigIntegerOrNull;
        n = 0;
        return new BigInteger(string, CharsKt.checkRadix(radix));
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(String $this$toBigDecimal) {
        int $i$f$toBigDecimal = 0;
        return new BigDecimal($this$toBigDecimal);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(String $this$toBigDecimal, MathContext mathContext) {
        int $i$f$toBigDecimal = 0;
        return new BigDecimal($this$toBigDecimal, mathContext);
    }

    @SinceKotlin(version="1.2")
    @Nullable
    public static final BigDecimal toBigDecimalOrNull(@NotNull String $this$toBigDecimalOrNull) {
        BigDecimal bigDecimal;
        Intrinsics.checkNotNullParameter($this$toBigDecimalOrNull, "$this$toBigDecimalOrNull");
        boolean $i$f$screenFloatValue$StringsKt__StringNumberConversionsJVMKt = false;
        try {
            BigDecimal bigDecimal2;
            if (ScreenFloatValueRegEx.value.matches($this$toBigDecimalOrNull)) {
                String it = $this$toBigDecimalOrNull;
                boolean bl = false;
                String string = it;
                boolean bl2 = false;
                bigDecimal2 = new BigDecimal(string);
            } else {
                bigDecimal2 = null;
            }
            bigDecimal = bigDecimal2;
        } catch (NumberFormatException e$iv) {
            bigDecimal = null;
        }
        return bigDecimal;
    }

    @SinceKotlin(version="1.2")
    @Nullable
    public static final BigDecimal toBigDecimalOrNull(@NotNull String $this$toBigDecimalOrNull, @NotNull MathContext mathContext) {
        BigDecimal bigDecimal;
        Intrinsics.checkNotNullParameter($this$toBigDecimalOrNull, "$this$toBigDecimalOrNull");
        Intrinsics.checkNotNullParameter(mathContext, "mathContext");
        boolean $i$f$screenFloatValue$StringsKt__StringNumberConversionsJVMKt = false;
        try {
            BigDecimal bigDecimal2;
            if (ScreenFloatValueRegEx.value.matches($this$toBigDecimalOrNull)) {
                String it = $this$toBigDecimalOrNull;
                boolean bl = false;
                String string = it;
                MathContext mathContext2 = mathContext;
                boolean bl2 = false;
                bigDecimal2 = new BigDecimal(string, mathContext2);
            } else {
                bigDecimal2 = null;
            }
            bigDecimal = bigDecimal2;
        } catch (NumberFormatException e$iv) {
            bigDecimal = null;
        }
        return bigDecimal;
    }

    private static final <T> T screenFloatValue$StringsKt__StringNumberConversionsJVMKt(String str, Function1<? super String, ? extends T> parse) {
        Object var3_3;
        int $i$f$screenFloatValue$StringsKt__StringNumberConversionsJVMKt = 0;
        try {
            var3_3 = ScreenFloatValueRegEx.value.matches(str) ? parse.invoke(str) : null;
        } catch (NumberFormatException e) {
            var3_3 = null;
        }
        return var3_3;
    }
}

