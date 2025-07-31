/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import kotlin.ExperimentalStdlibApi;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.NumbersKt;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000&\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b)\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0003\u0010\u0004\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u0005H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\n\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u000bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\f\u0010\r\u001a\u0017\u0010\u000e\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000f\u0010\u0004\u001a\u0017\u0010\u000e\u001a\u00020\u0001*\u00020\u0005H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0010\u0010\u0007\u001a\u0017\u0010\u000e\u001a\u00020\u0001*\u00020\bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\n\u001a\u0017\u0010\u000e\u001a\u00020\u0001*\u00020\u000bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0012\u0010\r\u001a\u0017\u0010\u0013\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0014\u0010\u0004\u001a\u0017\u0010\u0013\u001a\u00020\u0001*\u00020\u0005H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0015\u0010\u0007\u001a\u0017\u0010\u0013\u001a\u00020\u0001*\u00020\bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0016\u0010\n\u001a\u0017\u0010\u0013\u001a\u00020\u0001*\u00020\u000bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\r\u001a\u001f\u0010\u0018\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001a\u0010\u001b\u001a\u001f\u0010\u0018\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001c\u0010\u001d\u001a\u001f\u0010\u0018\u001a\u00020\b*\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001e\u0010\u001f\u001a\u001f\u0010\u0018\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b \u0010!\u001a\u001f\u0010\"\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b#\u0010\u001b\u001a\u001f\u0010\"\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b$\u0010\u001d\u001a\u001f\u0010\"\u001a\u00020\b*\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b%\u0010\u001f\u001a\u001f\u0010\"\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b&\u0010!\u001a\u0017\u0010'\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b(\u0010)\u001a\u0017\u0010'\u001a\u00020\u0005*\u00020\u0005H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b*\u0010\u0007\u001a\u0017\u0010'\u001a\u00020\b*\u00020\bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b+\u0010,\u001a\u0017\u0010'\u001a\u00020\u000b*\u00020\u000bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b-\u0010.\u001a\u0017\u0010/\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b0\u0010)\u001a\u0017\u0010/\u001a\u00020\u0005*\u00020\u0005H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b1\u0010\u0007\u001a\u0017\u0010/\u001a\u00020\b*\u00020\bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b2\u0010,\u001a\u0017\u0010/\u001a\u00020\u000b*\u00020\u000bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b3\u0010.\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00064"}, d2={"countLeadingZeroBits", "", "Lkotlin/UByte;", "countLeadingZeroBits-7apg3OU", "(B)I", "Lkotlin/UInt;", "countLeadingZeroBits-WZ4Q5Ns", "(I)I", "Lkotlin/ULong;", "countLeadingZeroBits-VKZWuLQ", "(J)I", "Lkotlin/UShort;", "countLeadingZeroBits-xj2QHRw", "(S)I", "countOneBits", "countOneBits-7apg3OU", "countOneBits-WZ4Q5Ns", "countOneBits-VKZWuLQ", "countOneBits-xj2QHRw", "countTrailingZeroBits", "countTrailingZeroBits-7apg3OU", "countTrailingZeroBits-WZ4Q5Ns", "countTrailingZeroBits-VKZWuLQ", "countTrailingZeroBits-xj2QHRw", "rotateLeft", "bitCount", "rotateLeft-LxnNnR4", "(BI)B", "rotateLeft-V7xB4Y4", "(II)I", "rotateLeft-JSWoG40", "(JI)J", "rotateLeft-olVBNx4", "(SI)S", "rotateRight", "rotateRight-LxnNnR4", "rotateRight-V7xB4Y4", "rotateRight-JSWoG40", "rotateRight-olVBNx4", "takeHighestOneBit", "takeHighestOneBit-7apg3OU", "(B)B", "takeHighestOneBit-WZ4Q5Ns", "takeHighestOneBit-VKZWuLQ", "(J)J", "takeHighestOneBit-xj2QHRw", "(S)S", "takeLowestOneBit", "takeLowestOneBit-7apg3OU", "takeLowestOneBit-WZ4Q5Ns", "takeLowestOneBit-VKZWuLQ", "takeLowestOneBit-xj2QHRw", "kotlin-stdlib"})
@JvmName(name="UNumbersKt")
public final class UNumbersKt {
    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int countOneBits-WZ4Q5Ns(int $this$countOneBits) {
        int n = 0;
        int n2 = $this$countOneBits;
        boolean bl = false;
        bl = false;
        return Integer.bitCount(n2);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int countLeadingZeroBits-WZ4Q5Ns(int $this$countLeadingZeroBits) {
        int n = 0;
        int n2 = $this$countLeadingZeroBits;
        boolean bl = false;
        bl = false;
        return Integer.numberOfLeadingZeros(n2);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int countTrailingZeroBits-WZ4Q5Ns(int $this$countTrailingZeroBits) {
        int n = 0;
        int n2 = $this$countTrailingZeroBits;
        boolean bl = false;
        bl = false;
        return Integer.numberOfTrailingZeros(n2);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int takeHighestOneBit-WZ4Q5Ns(int $this$takeHighestOneBit) {
        int n = 0;
        int n2 = $this$takeHighestOneBit;
        boolean bl = false;
        bl = false;
        n2 = Integer.highestOneBit(n2);
        bl = false;
        return UInt.constructor-impl(n2);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int takeLowestOneBit-WZ4Q5Ns(int $this$takeLowestOneBit) {
        int n = 0;
        int n2 = $this$takeLowestOneBit;
        boolean bl = false;
        bl = false;
        n2 = Integer.lowestOneBit(n2);
        bl = false;
        return UInt.constructor-impl(n2);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int rotateLeft-V7xB4Y4(int $this$rotateLeft, int bitCount) {
        int n = 0;
        int n2 = $this$rotateLeft;
        boolean bl = false;
        bl = false;
        n2 = Integer.rotateLeft(n2, bitCount);
        bl = false;
        return UInt.constructor-impl(n2);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int rotateRight-V7xB4Y4(int $this$rotateRight, int bitCount) {
        int n = 0;
        int n2 = $this$rotateRight;
        boolean bl = false;
        bl = false;
        n2 = Integer.rotateRight(n2, bitCount);
        bl = false;
        return UInt.constructor-impl(n2);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int countOneBits-VKZWuLQ(long $this$countOneBits) {
        int n = 0;
        long l = $this$countOneBits;
        boolean bl = false;
        bl = false;
        return Long.bitCount(l);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int countLeadingZeroBits-VKZWuLQ(long $this$countLeadingZeroBits) {
        int n = 0;
        long l = $this$countLeadingZeroBits;
        boolean bl = false;
        bl = false;
        return Long.numberOfLeadingZeros(l);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int countTrailingZeroBits-VKZWuLQ(long $this$countTrailingZeroBits) {
        int n = 0;
        long l = $this$countTrailingZeroBits;
        boolean bl = false;
        bl = false;
        return Long.numberOfTrailingZeros(l);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final long takeHighestOneBit-VKZWuLQ(long $this$takeHighestOneBit) {
        int n = 0;
        long l = $this$takeHighestOneBit;
        boolean bl = false;
        bl = false;
        l = Long.highestOneBit(l);
        bl = false;
        return ULong.constructor-impl(l);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final long takeLowestOneBit-VKZWuLQ(long $this$takeLowestOneBit) {
        int n = 0;
        long l = $this$takeLowestOneBit;
        boolean bl = false;
        bl = false;
        l = Long.lowestOneBit(l);
        bl = false;
        return ULong.constructor-impl(l);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final long rotateLeft-JSWoG40(long $this$rotateLeft, int bitCount) {
        int n = 0;
        long l = $this$rotateLeft;
        boolean bl = false;
        bl = false;
        l = Long.rotateLeft(l, bitCount);
        bl = false;
        return ULong.constructor-impl(l);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final long rotateRight-JSWoG40(long $this$rotateRight, int bitCount) {
        int n = 0;
        long l = $this$rotateRight;
        boolean bl = false;
        bl = false;
        l = Long.rotateRight(l, bitCount);
        bl = false;
        return ULong.constructor-impl(l);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int countOneBits-7apg3OU(byte $this$countOneBits) {
        int n = 0;
        int n2 = $this$countOneBits;
        boolean bl = false;
        n2 = UInt.constructor-impl(n2 & 0xFF);
        bl = false;
        int n3 = n2;
        boolean bl2 = false;
        bl2 = false;
        return Integer.bitCount(n3);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int countLeadingZeroBits-7apg3OU(byte $this$countLeadingZeroBits) {
        int n = 0;
        byte by = $this$countLeadingZeroBits;
        boolean bl = false;
        bl = false;
        int n2 = by & 0xFF;
        boolean bl2 = false;
        return Integer.numberOfLeadingZeros(n2) - 24;
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int countTrailingZeroBits-7apg3OU(byte $this$countTrailingZeroBits) {
        int n = 0;
        byte by = $this$countTrailingZeroBits;
        boolean bl = false;
        bl = false;
        int n2 = by | 0x100;
        boolean bl2 = false;
        return Integer.numberOfTrailingZeros(n2);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final byte takeHighestOneBit-7apg3OU(byte $this$takeHighestOneBit) {
        int n = 0;
        int n2 = $this$takeHighestOneBit;
        boolean bl = false;
        n2 &= 0xFF;
        bl = false;
        n2 = Integer.highestOneBit(n2);
        bl = false;
        return UByte.constructor-impl((byte)n2);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final byte takeLowestOneBit-7apg3OU(byte $this$takeLowestOneBit) {
        int n = 0;
        int n2 = $this$takeLowestOneBit;
        boolean bl = false;
        n2 &= 0xFF;
        bl = false;
        n2 = Integer.lowestOneBit(n2);
        bl = false;
        return UByte.constructor-impl((byte)n2);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final byte rotateLeft-LxnNnR4(byte $this$rotateLeft, int bitCount) {
        int n = 0;
        byte by = $this$rotateLeft;
        boolean bl = false;
        by = NumbersKt.rotateLeft(by, bitCount);
        bl = false;
        return UByte.constructor-impl(by);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final byte rotateRight-LxnNnR4(byte $this$rotateRight, int bitCount) {
        int n = 0;
        byte by = $this$rotateRight;
        boolean bl = false;
        by = NumbersKt.rotateRight(by, bitCount);
        bl = false;
        return UByte.constructor-impl(by);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int countOneBits-xj2QHRw(short $this$countOneBits) {
        int n = 0;
        int n2 = $this$countOneBits;
        boolean bl = false;
        n2 = UInt.constructor-impl(n2 & 0xFFFF);
        bl = false;
        int n3 = n2;
        boolean bl2 = false;
        bl2 = false;
        return Integer.bitCount(n3);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int countLeadingZeroBits-xj2QHRw(short $this$countLeadingZeroBits) {
        int n = 0;
        short s2 = $this$countLeadingZeroBits;
        boolean bl = false;
        bl = false;
        int n2 = s2 & 0xFFFF;
        boolean bl2 = false;
        return Integer.numberOfLeadingZeros(n2) - 16;
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int countTrailingZeroBits-xj2QHRw(short $this$countTrailingZeroBits) {
        int n = 0;
        short s2 = $this$countTrailingZeroBits;
        boolean bl = false;
        bl = false;
        int n2 = s2 | 0x10000;
        boolean bl2 = false;
        return Integer.numberOfTrailingZeros(n2);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final short takeHighestOneBit-xj2QHRw(short $this$takeHighestOneBit) {
        int n = 0;
        int n2 = $this$takeHighestOneBit;
        boolean bl = false;
        n2 &= 0xFFFF;
        bl = false;
        n2 = Integer.highestOneBit(n2);
        bl = false;
        return UShort.constructor-impl((short)n2);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final short takeLowestOneBit-xj2QHRw(short $this$takeLowestOneBit) {
        int n = 0;
        int n2 = $this$takeLowestOneBit;
        boolean bl = false;
        n2 &= 0xFFFF;
        bl = false;
        n2 = Integer.lowestOneBit(n2);
        bl = false;
        return UShort.constructor-impl((short)n2);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final short rotateLeft-olVBNx4(short $this$rotateLeft, int bitCount) {
        int n = 0;
        short s2 = $this$rotateLeft;
        boolean bl = false;
        s2 = NumbersKt.rotateLeft(s2, bitCount);
        bl = false;
        return UShort.constructor-impl(s2);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final short rotateRight-olVBNx4(short $this$rotateRight, int bitCount) {
        int n = 0;
        short s2 = $this$rotateRight;
        boolean bl = false;
        s2 = NumbersKt.rotateRight(s2, bitCount);
        bl = false;
        return UShort.constructor-impl(s2);
    }
}

