/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.random;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.random.RandomKt;
import kotlin.ranges.UIntRange;
import kotlin.ranges.ULongRange;
import org.jetbrains.annotations.NotNull;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006\u001a\"\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\n\u001a\u001c\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010\u001a\u001e\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u0011\u001a\u00020\fH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0012\u0010\u0013\u001a2\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u0011\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u000fH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0016\u0010\u0017\u001a\u0014\u0010\u0018\u001a\u00020\u0003*\u00020\rH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019\u001a\u001e\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0003H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001a\u0010\u001b\u001a&\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001c\u0010\u001d\u001a\u001c\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001fH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 \u001a\u0014\u0010!\u001a\u00020\b*\u00020\rH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"\u001a\u001e\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u0004\u001a\u00020\bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b#\u0010$\u001a&\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b%\u0010&\u001a\u001c\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u001e\u001a\u00020'H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010(\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006)"}, d2={"checkUIntRangeBounds", "", "from", "Lkotlin/UInt;", "until", "checkUIntRangeBounds-J1ME1BU", "(II)V", "checkULongRangeBounds", "Lkotlin/ULong;", "checkULongRangeBounds-eb3DHEI", "(JJ)V", "nextUBytes", "Lkotlin/UByteArray;", "Lkotlin/random/Random;", "size", "", "(Lkotlin/random/Random;I)[B", "array", "nextUBytes-EVgfTAA", "(Lkotlin/random/Random;[B)[B", "fromIndex", "toIndex", "nextUBytes-Wvrt4B4", "(Lkotlin/random/Random;[BII)[B", "nextUInt", "(Lkotlin/random/Random;)I", "nextUInt-qCasIEU", "(Lkotlin/random/Random;I)I", "nextUInt-a8DCA5k", "(Lkotlin/random/Random;II)I", "range", "Lkotlin/ranges/UIntRange;", "(Lkotlin/random/Random;Lkotlin/ranges/UIntRange;)I", "nextULong", "(Lkotlin/random/Random;)J", "nextULong-V1Xi4fY", "(Lkotlin/random/Random;J)J", "nextULong-jmpaW-c", "(Lkotlin/random/Random;JJ)J", "Lkotlin/ranges/ULongRange;", "(Lkotlin/random/Random;Lkotlin/ranges/ULongRange;)J", "kotlin-stdlib"})
public final class URandomKt {
    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final int nextUInt(@NotNull Random $this$nextUInt) {
        Intrinsics.checkNotNullParameter($this$nextUInt, "$this$nextUInt");
        int n = $this$nextUInt.nextInt();
        boolean bl = false;
        return UInt.constructor-impl(n);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final int nextUInt-qCasIEU(@NotNull Random $this$nextUInt, int until) {
        Intrinsics.checkNotNullParameter($this$nextUInt, "$this$nextUInt");
        return URandomKt.nextUInt-a8DCA5k($this$nextUInt, 0, until);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final int nextUInt-a8DCA5k(@NotNull Random $this$nextUInt, int from, int until) {
        int signedResult;
        Intrinsics.checkNotNullParameter($this$nextUInt, "$this$nextUInt");
        URandomKt.checkUIntRangeBounds-J1ME1BU(from, until);
        int n = from;
        int n2 = 0;
        int signedFrom = n ^ Integer.MIN_VALUE;
        n2 = until;
        int n3 = 0;
        int signedUntil = n2 ^ Integer.MIN_VALUE;
        n3 = signedResult = $this$nextUInt.nextInt(signedFrom, signedUntil) ^ Integer.MIN_VALUE;
        boolean bl = false;
        return UInt.constructor-impl(n3);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final int nextUInt(@NotNull Random $this$nextUInt, @NotNull UIntRange range) {
        int n;
        Intrinsics.checkNotNullParameter($this$nextUInt, "$this$nextUInt");
        Intrinsics.checkNotNullParameter(range, "range");
        if (range.isEmpty()) {
            throw (Throwable)new IllegalArgumentException("Cannot get random in empty range: " + range);
        }
        int n2 = range.getLast-pVg5ArA();
        int n3 = -1;
        boolean bl = false;
        if (UnsignedKt.uintCompare(n2, n3) < 0) {
            n2 = range.getLast-pVg5ArA();
            n3 = 1;
            bl = false;
            n = URandomKt.nextUInt-a8DCA5k($this$nextUInt, range.getFirst-pVg5ArA(), UInt.constructor-impl(n2 + n3));
        } else {
            n2 = range.getFirst-pVg5ArA();
            n3 = 0;
            bl = false;
            if (UnsignedKt.uintCompare(n2, n3) > 0) {
                n2 = range.getFirst-pVg5ArA();
                n3 = 1;
                bl = false;
                n2 = URandomKt.nextUInt-a8DCA5k($this$nextUInt, UInt.constructor-impl(n2 - n3), range.getLast-pVg5ArA());
                n3 = 1;
                bl = false;
                n = UInt.constructor-impl(n2 + n3);
            } else {
                n = URandomKt.nextUInt($this$nextUInt);
            }
        }
        return n;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final long nextULong(@NotNull Random $this$nextULong) {
        Intrinsics.checkNotNullParameter($this$nextULong, "$this$nextULong");
        long l = $this$nextULong.nextLong();
        boolean bl = false;
        return ULong.constructor-impl(l);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final long nextULong-V1Xi4fY(@NotNull Random $this$nextULong, long until) {
        Intrinsics.checkNotNullParameter($this$nextULong, "$this$nextULong");
        return URandomKt.nextULong-jmpaW-c($this$nextULong, 0L, until);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final long nextULong-jmpaW-c(@NotNull Random $this$nextULong, long from, long until) {
        long signedResult;
        Intrinsics.checkNotNullParameter($this$nextULong, "$this$nextULong");
        URandomKt.checkULongRangeBounds-eb3DHEI(from, until);
        long l = from;
        boolean bl = false;
        long signedFrom = l ^ Long.MIN_VALUE;
        long l2 = until;
        boolean bl2 = false;
        long signedUntil = l2 ^ Long.MIN_VALUE;
        long l3 = signedResult = $this$nextULong.nextLong(signedFrom, signedUntil) ^ Long.MIN_VALUE;
        boolean bl3 = false;
        return ULong.constructor-impl(l3);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final long nextULong(@NotNull Random $this$nextULong, @NotNull ULongRange range) {
        long l;
        Intrinsics.checkNotNullParameter($this$nextULong, "$this$nextULong");
        Intrinsics.checkNotNullParameter(range, "range");
        if (range.isEmpty()) {
            throw (Throwable)new IllegalArgumentException("Cannot get random in empty range: " + range);
        }
        long l2 = range.getLast-s-VKNKU();
        long l3 = -1L;
        boolean bl = false;
        if (UnsignedKt.ulongCompare(l2, l3) < 0) {
            l2 = range.getLast-s-VKNKU();
            int n = 1;
            boolean bl2 = false;
            long l4 = l2;
            int n2 = n;
            boolean bl3 = false;
            long l5 = ULong.constructor-impl((long)n2 & 0xFFFFFFFFL);
            boolean bl4 = false;
            l = URandomKt.nextULong-jmpaW-c($this$nextULong, range.getFirst-s-VKNKU(), ULong.constructor-impl(l4 + l5));
        } else {
            l2 = range.getFirst-s-VKNKU();
            l3 = 0L;
            bl = false;
            if (UnsignedKt.ulongCompare(l2, l3) > 0) {
                l2 = range.getFirst-s-VKNKU();
                int n = 1;
                boolean bl5 = false;
                long l6 = l2;
                int n3 = n;
                boolean bl6 = false;
                long l7 = ULong.constructor-impl((long)n3 & 0xFFFFFFFFL);
                boolean bl7 = false;
                l2 = URandomKt.nextULong-jmpaW-c($this$nextULong, ULong.constructor-impl(l6 - l7), range.getLast-s-VKNKU());
                n = 1;
                bl5 = false;
                l6 = l2;
                n3 = n;
                bl6 = false;
                l7 = ULong.constructor-impl((long)n3 & 0xFFFFFFFFL);
                bl7 = false;
                l = ULong.constructor-impl(l6 + l7);
            } else {
                l = URandomKt.nextULong($this$nextULong);
            }
        }
        return l;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final byte[] nextUBytes-EVgfTAA(@NotNull Random $this$nextUBytes, @NotNull byte[] array) {
        Intrinsics.checkNotNullParameter($this$nextUBytes, "$this$nextUBytes");
        Intrinsics.checkNotNullParameter(array, "array");
        byte[] byArray = array;
        boolean bl = false;
        $this$nextUBytes.nextBytes(byArray);
        return array;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final byte[] nextUBytes(@NotNull Random $this$nextUBytes, int size) {
        Intrinsics.checkNotNullParameter($this$nextUBytes, "$this$nextUBytes");
        byte[] byArray = $this$nextUBytes.nextBytes(size);
        boolean bl = false;
        return UByteArray.constructor-impl(byArray);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final byte[] nextUBytes-Wvrt4B4(@NotNull Random $this$nextUBytes, @NotNull byte[] array, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter($this$nextUBytes, "$this$nextUBytes");
        Intrinsics.checkNotNullParameter(array, "array");
        byte[] byArray = array;
        boolean bl = false;
        $this$nextUBytes.nextBytes(byArray, fromIndex, toIndex);
        return array;
    }

    public static /* synthetic */ byte[] nextUBytes-Wvrt4B4$default(Random random, byte[] byArray, int n, int n2, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n = 0;
        }
        if ((n3 & 4) != 0) {
            n2 = UByteArray.getSize-impl(byArray);
        }
        return URandomKt.nextUBytes-Wvrt4B4(random, byArray, n, n2);
    }

    @ExperimentalUnsignedTypes
    public static final void checkUIntRangeBounds-J1ME1BU(int from, int until) {
        int n = until;
        boolean bl = false;
        n = UnsignedKt.uintCompare(n, from) > 0 ? 1 : 0;
        bl = false;
        boolean bl2 = false;
        if (n == 0) {
            boolean bl3 = false;
            String string = RandomKt.boundsErrorMessage(UInt.box-impl(from), UInt.box-impl(until));
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
    }

    @ExperimentalUnsignedTypes
    public static final void checkULongRangeBounds-eb3DHEI(long from, long until) {
        long l = until;
        boolean bl = false;
        boolean bl2 = UnsignedKt.ulongCompare(l, from) > 0;
        boolean bl3 = false;
        bl = false;
        if (!bl2) {
            boolean bl4 = false;
            String string = RandomKt.boundsErrorMessage(ULong.box-impl(from), ULong.box-impl(until));
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
    }
}

