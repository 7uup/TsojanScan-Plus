/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import org.jetbrains.annotations.NotNull;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004\u001a\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0003H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007\u001a\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0001\u001a\"\u0010\f\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u0001H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\r\u0010\u000e\u001a\"\u0010\u000f\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u0001H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0010\u0010\u000e\u001a\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\tH\u0001\u001a\u0018\u0010\u0012\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\u0013H\u0001\u001a\"\u0010\u0014\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0015\u0010\u0016\u001a\"\u0010\u0017\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0018\u0010\u0016\u001a\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0013H\u0001\u001a\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0002\u001a\u00020\u0013H\u0000\u001a\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0002\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\tH\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001d"}, d2={"doubleToUInt", "Lkotlin/UInt;", "v", "", "(D)I", "doubleToULong", "Lkotlin/ULong;", "(D)J", "uintCompare", "", "v1", "v2", "uintDivide", "uintDivide-J1ME1BU", "(II)I", "uintRemainder", "uintRemainder-J1ME1BU", "uintToDouble", "ulongCompare", "", "ulongDivide", "ulongDivide-eb3DHEI", "(JJ)J", "ulongRemainder", "ulongRemainder-eb3DHEI", "ulongToDouble", "ulongToString", "", "base", "kotlin-stdlib"})
@JvmName(name="UnsignedKt")
public final class UnsignedKt {
    @PublishedApi
    public static final int uintCompare(int v1, int v2) {
        return Intrinsics.compare(v1 ^ Integer.MIN_VALUE, v2 ^ Integer.MIN_VALUE);
    }

    @PublishedApi
    public static final int ulongCompare(long v1, long v2) {
        long l = (v1 ^ Long.MIN_VALUE) - (v2 ^ Long.MIN_VALUE);
        return l == 0L ? 0 : (l < 0L ? -1 : 1);
    }

    @PublishedApi
    public static final int uintDivide-J1ME1BU(int v1, int v2) {
        int n = v1;
        boolean bl = false;
        long l = (long)n & 0xFFFFFFFFL;
        n = v2;
        bl = false;
        long l2 = l / ((long)n & 0xFFFFFFFFL);
        boolean bl2 = false;
        return UInt.constructor-impl((int)l2);
    }

    @PublishedApi
    public static final int uintRemainder-J1ME1BU(int v1, int v2) {
        int n = v1;
        boolean bl = false;
        long l = (long)n & 0xFFFFFFFFL;
        n = v2;
        bl = false;
        long l2 = l % ((long)n & 0xFFFFFFFFL);
        boolean bl2 = false;
        return UInt.constructor-impl((int)l2);
    }

    @PublishedApi
    public static final long ulongDivide-eb3DHEI(long v1, long v2) {
        long l = v1;
        boolean bl = false;
        long dividend = l;
        long l2 = v2;
        boolean bl2 = false;
        long divisor = l2;
        if (divisor < 0L) {
            l2 = v1;
            bl2 = false;
            return UnsignedKt.ulongCompare(l2, v2) < 0 ? ULong.constructor-impl(0L) : ULong.constructor-impl(1L);
        }
        if (dividend >= 0L) {
            return ULong.constructor-impl(dividend / divisor);
        }
        long quotient = (dividend >>> 1) / divisor << 1;
        long rem = dividend - quotient * divisor;
        long l3 = ULong.constructor-impl(rem);
        long l4 = ULong.constructor-impl(divisor);
        boolean bl3 = false;
        return ULong.constructor-impl(quotient + (long)(UnsignedKt.ulongCompare(l3, l4) >= 0 ? 1 : 0));
    }

    @PublishedApi
    public static final long ulongRemainder-eb3DHEI(long v1, long v2) {
        long l = v1;
        boolean bl = false;
        long dividend = l;
        long l2 = v2;
        boolean bl2 = false;
        long divisor = l2;
        if (divisor < 0L) {
            long l3;
            l2 = v1;
            bl2 = false;
            if (UnsignedKt.ulongCompare(l2, v2) < 0) {
                l3 = v1;
            } else {
                l2 = v1;
                bl2 = false;
                l3 = ULong.constructor-impl(l2 - v2);
            }
            return l3;
        }
        if (dividend >= 0L) {
            return ULong.constructor-impl(dividend % divisor);
        }
        long quotient = (dividend >>> 1) / divisor << 1;
        long rem = dividend - quotient * divisor;
        long l4 = ULong.constructor-impl(rem);
        long l5 = ULong.constructor-impl(divisor);
        boolean bl3 = false;
        return ULong.constructor-impl(rem - (UnsignedKt.ulongCompare(l4, l5) >= 0 ? divisor : 0L));
    }

    @PublishedApi
    public static final int doubleToUInt(double v) {
        int n;
        double d = v;
        boolean bl = false;
        if (Double.isNaN(d)) {
            n = 0;
        } else {
            int n2 = 0;
            int n3 = 0;
            if (v <= UnsignedKt.uintToDouble(n2)) {
                n = 0;
            } else {
                n2 = -1;
                n3 = 0;
                if (v >= UnsignedKt.uintToDouble(n2)) {
                    n = -1;
                } else if (v <= (double)Integer.MAX_VALUE) {
                    n2 = (int)v;
                    n3 = 0;
                    n = UInt.constructor-impl(n2);
                } else {
                    n2 = (int)(v - (double)Integer.MAX_VALUE);
                    n3 = 0;
                    n2 = UInt.constructor-impl(n2);
                    n3 = Integer.MAX_VALUE;
                    bl = false;
                    n3 = UInt.constructor-impl(n3);
                    bl = false;
                    n = UInt.constructor-impl(n2 + n3);
                }
            }
        }
        return n;
    }

    @PublishedApi
    public static final long doubleToULong(double v) {
        long l;
        double d = v;
        boolean bl = false;
        if (Double.isNaN(d)) {
            l = 0L;
        } else {
            long l2 = 0L;
            bl = false;
            if (v <= UnsignedKt.ulongToDouble(l2)) {
                l = 0L;
            } else {
                l2 = -1L;
                bl = false;
                if (v >= UnsignedKt.ulongToDouble(l2)) {
                    l = -1L;
                } else if (v < (double)Long.MAX_VALUE) {
                    l2 = (long)v;
                    bl = false;
                    l = ULong.constructor-impl(l2);
                } else {
                    l2 = (long)(v - 9.223372036854776E18);
                    bl = false;
                    l2 = ULong.constructor-impl(l2);
                    long l3 = Long.MIN_VALUE;
                    boolean bl2 = false;
                    l = ULong.constructor-impl(l2 + l3);
                }
            }
        }
        return l;
    }

    @PublishedApi
    public static final double uintToDouble(int v) {
        return (double)(v & Integer.MAX_VALUE) + (double)(v >>> 31 << 30) * (double)2;
    }

    @PublishedApi
    public static final double ulongToDouble(long v) {
        return (double)(v >>> 11) * (double)2048 + (double)(v & 0x7FFL);
    }

    @NotNull
    public static final String ulongToString(long v) {
        return UnsignedKt.ulongToString(v, 10);
    }

    @NotNull
    public static final String ulongToString(long v, int base) {
        if (v >= 0L) {
            long l = v;
            boolean bl = false;
            String string = Long.toString(l, CharsKt.checkRadix(base));
            Intrinsics.checkNotNullExpressionValue(string, "java.lang.Long.toString(this, checkRadix(radix))");
            return string;
        }
        long quotient = (v >>> 1) / (long)base << 1;
        long rem = v - quotient * (long)base;
        if (rem >= (long)base) {
            rem -= (long)base;
            ++quotient;
        }
        StringBuilder stringBuilder = new StringBuilder();
        long l = quotient;
        boolean bl = false;
        String string = Long.toString(l, CharsKt.checkRadix(base));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Long.toString(this, checkRadix(radix))");
        StringBuilder stringBuilder2 = stringBuilder.append(string);
        l = rem;
        bl = false;
        String string2 = Long.toString(l, CharsKt.checkRadix(base));
        Intrinsics.checkNotNullExpressionValue(string2, "java.lang.Long.toString(this, checkRadix(radix))");
        return stringBuilder2.append(string2).toString();
    }
}

