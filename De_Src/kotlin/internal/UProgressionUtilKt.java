/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.internal;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UnsignedKt;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006\u001a*\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\b\u0010\t\u001a*\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000f\u0010\u0006\u001a*\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2={"differenceModulo", "Lkotlin/UInt;", "a", "b", "c", "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", "start", "end", "step", "", "getProgressionLastElement-Nkh28Cs", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"})
public final class UProgressionUtilKt {
    private static final int differenceModulo-WZ9TVnA(int a, int b, int c) {
        int n;
        int n2 = a;
        int n3 = 0;
        int ac = UnsignedKt.uintRemainder-J1ME1BU(n2, c);
        n3 = b;
        boolean bl = false;
        int bc = UnsignedKt.uintRemainder-J1ME1BU(n3, c);
        n3 = ac;
        bl = false;
        if (UnsignedKt.uintCompare(n3, bc) >= 0) {
            n3 = ac;
            bl = false;
            n = UInt.constructor-impl(n3 - bc);
        } else {
            n3 = ac;
            bl = false;
            n3 = UInt.constructor-impl(n3 - bc);
            bl = false;
            n = UInt.constructor-impl(n3 + c);
        }
        return n;
    }

    private static final long differenceModulo-sambcqE(long a, long b, long c) {
        long l;
        long l2 = a;
        boolean bl = false;
        long ac = UnsignedKt.ulongRemainder-eb3DHEI(l2, c);
        long l3 = b;
        boolean bl2 = false;
        long bc = UnsignedKt.ulongRemainder-eb3DHEI(l3, c);
        l3 = ac;
        bl2 = false;
        if (UnsignedKt.ulongCompare(l3, bc) >= 0) {
            l3 = ac;
            bl2 = false;
            l = ULong.constructor-impl(l3 - bc);
        } else {
            l3 = ac;
            bl2 = false;
            l3 = ULong.constructor-impl(l3 - bc);
            bl2 = false;
            l = ULong.constructor-impl(l3 + c);
        }
        return l;
    }

    @PublishedApi
    @SinceKotlin(version="1.3")
    public static final int getProgressionLastElement-Nkh28Cs(int start, int end, int step) {
        int n;
        if (step > 0) {
            int n2 = start;
            int n3 = 0;
            if (UnsignedKt.uintCompare(n2, end) >= 0) {
                n = end;
            } else {
                n2 = end;
                n3 = step;
                boolean bl = false;
                n3 = UProgressionUtilKt.differenceModulo-WZ9TVnA(end, start, UInt.constructor-impl(n3));
                bl = false;
                n = UInt.constructor-impl(n2 - n3);
            }
        } else if (step < 0) {
            int n4 = start;
            int n5 = 0;
            if (UnsignedKt.uintCompare(n4, end) <= 0) {
                n = end;
            } else {
                n4 = end;
                n5 = -step;
                boolean bl = false;
                n5 = UProgressionUtilKt.differenceModulo-WZ9TVnA(start, end, UInt.constructor-impl(n5));
                bl = false;
                n = UInt.constructor-impl(n4 + n5);
            }
        } else {
            throw (Throwable)new IllegalArgumentException("Step is zero.");
        }
        return n;
    }

    @PublishedApi
    @SinceKotlin(version="1.3")
    public static final long getProgressionLastElement-7ftBX0g(long start, long end, long step) {
        long l;
        if (step > 0L) {
            long l2 = start;
            boolean bl = false;
            if (UnsignedKt.ulongCompare(l2, end) >= 0) {
                l = end;
            } else {
                l2 = end;
                long l3 = step;
                boolean bl2 = false;
                l3 = UProgressionUtilKt.differenceModulo-sambcqE(end, start, ULong.constructor-impl(l3));
                bl2 = false;
                l = ULong.constructor-impl(l2 - l3);
            }
        } else if (step < 0L) {
            long l4 = start;
            boolean bl = false;
            if (UnsignedKt.ulongCompare(l4, end) <= 0) {
                l = end;
            } else {
                l4 = end;
                long l5 = -step;
                boolean bl3 = false;
                l5 = UProgressionUtilKt.differenceModulo-sambcqE(start, end, ULong.constructor-impl(l5));
                bl3 = false;
                l = ULong.constructor-impl(l4 + l5);
            }
        } else {
            throw (Throwable)new IllegalArgumentException("Step is zero.");
        }
        return l;
    }
}

