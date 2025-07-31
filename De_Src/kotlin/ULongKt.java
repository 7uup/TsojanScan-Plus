/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.internal.InlineOnly;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0003\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0004H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\t\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\nH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\fH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000e"}, d2={"toULong", "Lkotlin/ULong;", "", "(B)J", "", "(D)J", "", "(F)J", "", "(I)J", "", "(J)J", "", "(S)J", "kotlin-stdlib"})
public final class ULongKt {
    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final long toULong(byte $this$toULong) {
        int $i$f$toULong = 0;
        return ULong.constructor-impl($this$toULong);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final long toULong(short $this$toULong) {
        int $i$f$toULong = 0;
        return ULong.constructor-impl($this$toULong);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final long toULong(int $this$toULong) {
        int $i$f$toULong = 0;
        return ULong.constructor-impl($this$toULong);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final long toULong(long $this$toULong) {
        int $i$f$toULong = 0;
        return ULong.constructor-impl($this$toULong);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final long toULong(float $this$toULong) {
        int $i$f$toULong = 0;
        return UnsignedKt.doubleToULong($this$toULong);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final long toULong(double $this$toULong) {
        int $i$f$toULong = 0;
        return UnsignedKt.doubleToULong($this$toULong);
    }
}

