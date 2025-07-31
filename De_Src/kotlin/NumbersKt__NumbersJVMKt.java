/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.NumbersKt__BigIntegersKt;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.FloatCompanionObject;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000*\n\u0000\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0002H\u0087\b\u001a\u0015\u0010\u0005\u001a\u00020\t*\u00020\n2\u0006\u0010\b\u001a\u00020\u0001H\u0087\b\u001a\r\u0010\u000b\u001a\u00020\f*\u00020\u0006H\u0087\b\u001a\r\u0010\u000b\u001a\u00020\f*\u00020\tH\u0087\b\u001a\r\u0010\r\u001a\u00020\f*\u00020\u0006H\u0087\b\u001a\r\u0010\r\u001a\u00020\f*\u00020\tH\u0087\b\u001a\r\u0010\u000e\u001a\u00020\f*\u00020\u0006H\u0087\b\u001a\r\u0010\u000e\u001a\u00020\f*\u00020\tH\u0087\b\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u000f\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0011\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0011\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0001H\u0087\b\u001a\r\u0010\u0012\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010\u0012\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010\u0013\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010\u0013\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010\u0014\u001a\u00020\u0002*\u00020\u0006H\u0087\b\u001a\r\u0010\u0014\u001a\u00020\u0001*\u00020\tH\u0087\b\u001a\r\u0010\u0015\u001a\u00020\u0002*\u00020\u0006H\u0087\b\u001a\r\u0010\u0015\u001a\u00020\u0001*\u00020\tH\u0087\b\u00a8\u0006\u0016"}, d2={"countLeadingZeroBits", "", "", "countOneBits", "countTrailingZeroBits", "fromBits", "", "Lkotlin/Double$Companion;", "bits", "", "Lkotlin/Float$Companion;", "isFinite", "", "isInfinite", "isNaN", "rotateLeft", "bitCount", "rotateRight", "takeHighestOneBit", "takeLowestOneBit", "toBits", "toRawBits", "kotlin-stdlib"}, xs="kotlin/NumbersKt")
class NumbersKt__NumbersJVMKt
extends NumbersKt__BigIntegersKt {
    @InlineOnly
    private static final boolean isNaN(double $this$isNaN) {
        int $i$f$isNaN = 0;
        return Double.isNaN($this$isNaN);
    }

    @InlineOnly
    private static final boolean isNaN(float $this$isNaN) {
        int $i$f$isNaN = 0;
        return Float.isNaN($this$isNaN);
    }

    @InlineOnly
    private static final boolean isInfinite(double $this$isInfinite) {
        int $i$f$isInfinite = 0;
        return Double.isInfinite($this$isInfinite);
    }

    @InlineOnly
    private static final boolean isInfinite(float $this$isInfinite) {
        int $i$f$isInfinite = 0;
        return Float.isInfinite($this$isInfinite);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @InlineOnly
    private static final boolean isFinite(double $this$isFinite) {
        int $i$f$isFinite = 0;
        double d = $this$isFinite;
        boolean bl = false;
        if (Double.isInfinite(d)) return false;
        d = $this$isFinite;
        bl = false;
        if (Double.isNaN(d)) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @InlineOnly
    private static final boolean isFinite(float $this$isFinite) {
        int $i$f$isFinite = 0;
        float f = $this$isFinite;
        boolean bl = false;
        if (Float.isInfinite(f)) return false;
        f = $this$isFinite;
        bl = false;
        if (Float.isNaN(f)) return false;
        return true;
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final long toBits(double $this$toBits) {
        int $i$f$toBits = 0;
        return Double.doubleToLongBits($this$toBits);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final long toRawBits(double $this$toRawBits) {
        int $i$f$toRawBits = 0;
        return Double.doubleToRawLongBits($this$toRawBits);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final double fromBits(DoubleCompanionObject $this$fromBits, long bits) {
        int $i$f$fromBits = 0;
        return Double.longBitsToDouble(bits);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final int toBits(float $this$toBits) {
        int $i$f$toBits = 0;
        return Float.floatToIntBits($this$toBits);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final int toRawBits(float $this$toRawBits) {
        int $i$f$toRawBits = 0;
        return Float.floatToRawIntBits($this$toRawBits);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final float fromBits(FloatCompanionObject $this$fromBits, int bits) {
        int $i$f$fromBits = 0;
        return Float.intBitsToFloat(bits);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countOneBits(int $this$countOneBits) {
        int $i$f$countOneBits = 0;
        return Integer.bitCount($this$countOneBits);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countLeadingZeroBits(int $this$countLeadingZeroBits) {
        int $i$f$countLeadingZeroBits = 0;
        return Integer.numberOfLeadingZeros($this$countLeadingZeroBits);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countTrailingZeroBits(int $this$countTrailingZeroBits) {
        int $i$f$countTrailingZeroBits = 0;
        return Integer.numberOfTrailingZeros($this$countTrailingZeroBits);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int takeHighestOneBit(int $this$takeHighestOneBit) {
        int $i$f$takeHighestOneBit = 0;
        return Integer.highestOneBit($this$takeHighestOneBit);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int takeLowestOneBit(int $this$takeLowestOneBit) {
        int $i$f$takeLowestOneBit = 0;
        return Integer.lowestOneBit($this$takeLowestOneBit);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final int rotateLeft(int $this$rotateLeft, int bitCount) {
        int $i$f$rotateLeft = 0;
        return Integer.rotateLeft($this$rotateLeft, bitCount);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final int rotateRight(int $this$rotateRight, int bitCount) {
        int $i$f$rotateRight = 0;
        return Integer.rotateRight($this$rotateRight, bitCount);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countOneBits(long $this$countOneBits) {
        int $i$f$countOneBits = 0;
        return Long.bitCount($this$countOneBits);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countLeadingZeroBits(long $this$countLeadingZeroBits) {
        int $i$f$countLeadingZeroBits = 0;
        return Long.numberOfLeadingZeros($this$countLeadingZeroBits);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countTrailingZeroBits(long $this$countTrailingZeroBits) {
        int $i$f$countTrailingZeroBits = 0;
        return Long.numberOfTrailingZeros($this$countTrailingZeroBits);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final long takeHighestOneBit(long $this$takeHighestOneBit) {
        int $i$f$takeHighestOneBit = 0;
        return Long.highestOneBit($this$takeHighestOneBit);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final long takeLowestOneBit(long $this$takeLowestOneBit) {
        int $i$f$takeLowestOneBit = 0;
        return Long.lowestOneBit($this$takeLowestOneBit);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final long rotateLeft(long $this$rotateLeft, int bitCount) {
        int $i$f$rotateLeft = 0;
        return Long.rotateLeft($this$rotateLeft, bitCount);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final long rotateRight(long $this$rotateRight, int bitCount) {
        int $i$f$rotateRight = 0;
        return Long.rotateRight($this$rotateRight, bitCount);
    }
}

