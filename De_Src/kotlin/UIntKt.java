/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.UnsignedKt;
import kotlin.internal.InlineOnly;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0003\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0004H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\t\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\nH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\fH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000e"}, d2={"toUInt", "Lkotlin/UInt;", "", "(B)I", "", "(D)I", "", "(F)I", "", "(I)I", "", "(J)I", "", "(S)I", "kotlin-stdlib"})
public final class UIntKt {
    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int toUInt(byte $this$toUInt) {
        int $i$f$toUInt = 0;
        return UInt.constructor-impl($this$toUInt);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int toUInt(short $this$toUInt) {
        int $i$f$toUInt = 0;
        return UInt.constructor-impl($this$toUInt);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int toUInt(int $this$toUInt) {
        int $i$f$toUInt = 0;
        return UInt.constructor-impl($this$toUInt);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int toUInt(long $this$toUInt) {
        int $i$f$toUInt = 0;
        return UInt.constructor-impl((int)$this$toUInt);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int toUInt(float $this$toUInt) {
        int $i$f$toUInt = 0;
        return UnsignedKt.doubleToUInt($this$toUInt);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int toUInt(double $this$toUInt) {
        int $i$f$toUInt = 0;
        return UnsignedKt.doubleToUInt($this$toUInt);
    }
}

