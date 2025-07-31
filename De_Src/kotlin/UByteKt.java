/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.internal.InlineOnly;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0003\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0004H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\n"}, d2={"toUByte", "Lkotlin/UByte;", "", "(B)B", "", "(I)B", "", "(J)B", "", "(S)B", "kotlin-stdlib"})
public final class UByteKt {
    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final byte toUByte(byte $this$toUByte) {
        int $i$f$toUByte = 0;
        return UByte.constructor-impl($this$toUByte);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final byte toUByte(short $this$toUByte) {
        int $i$f$toUByte = 0;
        return UByte.constructor-impl((byte)$this$toUByte);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final byte toUByte(int $this$toUByte) {
        int $i$f$toUByte = 0;
        return UByte.constructor-impl((byte)$this$toUByte);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final byte toUByte(long $this$toUByte) {
        int $i$f$toUByte = 0;
        return UByte.constructor-impl((byte)$this$toUByte);
    }
}

