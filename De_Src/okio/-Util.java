/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import okio.internal.ByteStringKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000:\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0000\u001a \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\fH\u0000\u001a\u0019\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\fH\u0080\b\u001a\u0019\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010\u000f\u001a\u00020\u0005*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u0080\f\u001a\u0015\u0010\u000f\u001a\u00020\f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\fH\u0080\f\u001a\u0015\u0010\u000f\u001a\u00020\f*\u00020\u00052\u0006\u0010\u0011\u001a\u00020\fH\u0080\f\u001a\f\u0010\u0012\u001a\u00020\u0005*\u00020\u0005H\u0000\u001a\f\u0010\u0012\u001a\u00020\f*\u00020\fH\u0000\u001a\f\u0010\u0012\u001a\u00020\u0013*\u00020\u0013H\u0000\u001a\u0015\u0010\u0014\u001a\u00020\u0005*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u0080\f\u001a\u0015\u0010\u0015\u001a\u00020\u0005*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u0080\f\u001a\f\u0010\u0016\u001a\u00020\u0017*\u00020\u0010H\u0000\u001a\f\u0010\u0016\u001a\u00020\u0017*\u00020\u0005H\u0000\u001a\f\u0010\u0016\u001a\u00020\u0017*\u00020\fH\u0000\u00a8\u0006\u0018"}, d2={"arrayRangeEquals", "", "a", "", "aOffset", "", "b", "bOffset", "byteCount", "checkOffsetAndCount", "", "size", "", "offset", "minOf", "and", "", "other", "reverseBytes", "", "shl", "shr", "toHexString", "", "okio"})
@JvmName(name="-Util")
public final class -Util {
    public static final void checkOffsetAndCount(long size, long offset, long byteCount) {
        if ((offset | byteCount) < 0L || offset > size || size - offset < byteCount) {
            throw (Throwable)new ArrayIndexOutOfBoundsException("size=" + size + " offset=" + offset + " byteCount=" + byteCount);
        }
    }

    public static final short reverseBytes(short $this$reverseBytes) {
        int i = $this$reverseBytes & 0xFFFF;
        int reversed = (i & 0xFF00) >>> 8 | (i & 0xFF) << 8;
        return (short)reversed;
    }

    public static final int reverseBytes(int $this$reverseBytes) {
        return ($this$reverseBytes & 0xFF000000) >>> 24 | ($this$reverseBytes & 0xFF0000) >>> 8 | ($this$reverseBytes & 0xFF00) << 8 | ($this$reverseBytes & 0xFF) << 24;
    }

    public static final long reverseBytes(long $this$reverseBytes) {
        return ($this$reverseBytes & 0xFF00000000000000L) >>> 56 | ($this$reverseBytes & 0xFF000000000000L) >>> 40 | ($this$reverseBytes & 0xFF0000000000L) >>> 24 | ($this$reverseBytes & 0xFF00000000L) >>> 8 | ($this$reverseBytes & 0xFF000000L) << 8 | ($this$reverseBytes & 0xFF0000L) << 24 | ($this$reverseBytes & 0xFF00L) << 40 | ($this$reverseBytes & 0xFFL) << 56;
    }

    public static final int shr(byte $this$shr, int other) {
        int $i$f$shr = 0;
        return $this$shr >> other;
    }

    public static final int shl(byte $this$shl, int other) {
        int $i$f$shl = 0;
        return $this$shl << other;
    }

    public static final int and(byte $this$and, int other) {
        int $i$f$and = 0;
        return $this$and & other;
    }

    public static final long and(byte $this$and, long other) {
        int $i$f$and = 0;
        return (long)$this$and & other;
    }

    public static final long and(int $this$and, long other) {
        int $i$f$and = 0;
        return (long)$this$and & other;
    }

    public static final long minOf(long a, int b) {
        int $i$f$minOf = 0;
        long l = b;
        boolean bl = false;
        return Math.min(a, l);
    }

    public static final long minOf(int a, long b) {
        int $i$f$minOf = 0;
        long l = a;
        boolean bl = false;
        return Math.min(l, b);
    }

    /*
     * WARNING - void declaration
     */
    public static final boolean arrayRangeEquals(@NotNull byte[] a, int aOffset, @NotNull byte[] b, int bOffset, int byteCount) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        int n = 0;
        int n2 = byteCount;
        while (n < n2) {
            void i;
            if (a[i + aOffset] != b[i + bOffset]) {
                return false;
            }
            ++i;
        }
        return true;
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final String toHexString(byte $this$toHexString) {
        void $this$and$iv;
        byte $this$shr$iv;
        char[] result = new char[2];
        byte by = $this$toHexString;
        int other$iv = 4;
        boolean $i$f$shr = false;
        result[0] = ByteStringKt.getHEX_DIGIT_CHARS()[$this$shr$iv >> other$iv & 0xF];
        $this$shr$iv = $this$toHexString;
        other$iv = 15;
        boolean $i$f$and = false;
        result[1] = ByteStringKt.getHEX_DIGIT_CHARS()[$this$and$iv & other$iv];
        by = 0;
        return new String(result);
    }

    @NotNull
    public static final String toHexString(int $this$toHexString) {
        int i;
        if ($this$toHexString == 0) {
            return "0";
        }
        char[] result = new char[]{ByteStringKt.getHEX_DIGIT_CHARS()[$this$toHexString >> 28 & 0xF], ByteStringKt.getHEX_DIGIT_CHARS()[$this$toHexString >> 24 & 0xF], ByteStringKt.getHEX_DIGIT_CHARS()[$this$toHexString >> 20 & 0xF], ByteStringKt.getHEX_DIGIT_CHARS()[$this$toHexString >> 16 & 0xF], ByteStringKt.getHEX_DIGIT_CHARS()[$this$toHexString >> 12 & 0xF], ByteStringKt.getHEX_DIGIT_CHARS()[$this$toHexString >> 8 & 0xF], ByteStringKt.getHEX_DIGIT_CHARS()[$this$toHexString >> 4 & 0xF], ByteStringKt.getHEX_DIGIT_CHARS()[$this$toHexString & 0xF]};
        for (i = 0; i < result.length && result[i] == '0'; ++i) {
        }
        int n = result.length - i;
        boolean bl = false;
        return new String(result, i, n);
    }

    @NotNull
    public static final String toHexString(long $this$toHexString) {
        int i;
        if ($this$toHexString == 0L) {
            return "0";
        }
        char[] result = new char[]{ByteStringKt.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 60 & 0xFL)], ByteStringKt.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 56 & 0xFL)], ByteStringKt.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 52 & 0xFL)], ByteStringKt.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 48 & 0xFL)], ByteStringKt.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 44 & 0xFL)], ByteStringKt.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 40 & 0xFL)], ByteStringKt.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 36 & 0xFL)], ByteStringKt.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 32 & 0xFL)], ByteStringKt.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 28 & 0xFL)], ByteStringKt.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 24 & 0xFL)], ByteStringKt.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 20 & 0xFL)], ByteStringKt.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 16 & 0xFL)], ByteStringKt.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 12 & 0xFL)], ByteStringKt.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 8 & 0xFL)], ByteStringKt.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 4 & 0xFL)], ByteStringKt.getHEX_DIGIT_CHARS()[(int)($this$toHexString & 0xFL)]};
        for (i = 0; i < result.length && result[i] == '0'; ++i) {
        }
        int n = result.length - i;
        boolean bl = false;
        return new String(result, i, n);
    }
}

