/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.NumbersKt__NumbersJVMKt;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0005\n\u0002\u0010\n\n\u0002\b\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0003H\u0087\b\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0003H\u0087\b\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\u0003H\u0087\b\u001a\u0014\u0010\u0006\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0001H\u0007\u001a\u0014\u0010\u0006\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0001H\u0007\u001a\u0014\u0010\b\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0001H\u0007\u001a\u0014\u0010\b\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0001H\u0007\u001a\r\u0010\t\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010\t\u001a\u00020\u0003*\u00020\u0003H\u0087\b\u001a\r\u0010\n\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010\n\u001a\u00020\u0003*\u00020\u0003H\u0087\b\u00a8\u0006\u000b"}, d2={"countLeadingZeroBits", "", "", "", "countOneBits", "countTrailingZeroBits", "rotateLeft", "bitCount", "rotateRight", "takeHighestOneBit", "takeLowestOneBit", "kotlin-stdlib"}, xs="kotlin/NumbersKt")
class NumbersKt__NumbersKt
extends NumbersKt__NumbersJVMKt {
    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countOneBits(byte $this$countOneBits) {
        int $i$f$countOneBits = 0;
        int n = $this$countOneBits & 0xFF;
        boolean bl = false;
        return Integer.bitCount(n);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countLeadingZeroBits(byte $this$countLeadingZeroBits) {
        int $i$f$countLeadingZeroBits = 0;
        int n = $this$countLeadingZeroBits & 0xFF;
        boolean bl = false;
        return Integer.numberOfLeadingZeros(n) - 24;
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countTrailingZeroBits(byte $this$countTrailingZeroBits) {
        int $i$f$countTrailingZeroBits = 0;
        int n = $this$countTrailingZeroBits | 0x100;
        boolean bl = false;
        return Integer.numberOfTrailingZeros(n);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final byte takeHighestOneBit(byte $this$takeHighestOneBit) {
        int $i$f$takeHighestOneBit = 0;
        int n = $this$takeHighestOneBit & 0xFF;
        boolean bl = false;
        return (byte)Integer.highestOneBit(n);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final byte takeLowestOneBit(byte $this$takeLowestOneBit) {
        int $i$f$takeLowestOneBit = 0;
        byte by = $this$takeLowestOneBit;
        boolean bl = false;
        return (byte)Integer.lowestOneBit(by);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    public static final byte rotateLeft(byte $this$rotateLeft, int bitCount) {
        return (byte)($this$rotateLeft << (bitCount & 7) | ($this$rotateLeft & 0xFF) >>> 8 - (bitCount & 7));
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    public static final byte rotateRight(byte $this$rotateRight, int bitCount) {
        return (byte)($this$rotateRight << 8 - (bitCount & 7) | ($this$rotateRight & 0xFF) >>> (bitCount & 7));
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countOneBits(short $this$countOneBits) {
        int $i$f$countOneBits = 0;
        int n = $this$countOneBits & 0xFFFF;
        boolean bl = false;
        return Integer.bitCount(n);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countLeadingZeroBits(short $this$countLeadingZeroBits) {
        int $i$f$countLeadingZeroBits = 0;
        int n = $this$countLeadingZeroBits & 0xFFFF;
        boolean bl = false;
        return Integer.numberOfLeadingZeros(n) - 16;
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int countTrailingZeroBits(short $this$countTrailingZeroBits) {
        int $i$f$countTrailingZeroBits = 0;
        int n = $this$countTrailingZeroBits | 0x10000;
        boolean bl = false;
        return Integer.numberOfTrailingZeros(n);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final short takeHighestOneBit(short $this$takeHighestOneBit) {
        int $i$f$takeHighestOneBit = 0;
        int n = $this$takeHighestOneBit & 0xFFFF;
        boolean bl = false;
        return (short)Integer.highestOneBit(n);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final short takeLowestOneBit(short $this$takeLowestOneBit) {
        int $i$f$takeLowestOneBit = 0;
        short s2 = $this$takeLowestOneBit;
        boolean bl = false;
        return (short)Integer.lowestOneBit(s2);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    public static final short rotateLeft(short $this$rotateLeft, int bitCount) {
        return (short)($this$rotateLeft << (bitCount & 0xF) | ($this$rotateLeft & 0xFFFF) >>> 16 - (bitCount & 0xF));
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    public static final short rotateRight(short $this$rotateRight, int bitCount) {
        return (short)($this$rotateRight << 16 - (bitCount & 0xF) | ($this$rotateRight & 0xFFFF) >>> (bitCount & 0xF));
    }
}

