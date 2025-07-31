/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0002\u0010\u0007\u001a\u001f\u0010\b\u001a\u00020\u00012\n\u0010\t\u001a\u00020\u0001\"\u00020\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b\u009920\u0001\u00a8\u0006\f"}, d2={"UShortArray", "Lkotlin/UShortArray;", "size", "", "init", "Lkotlin/Function1;", "Lkotlin/UShort;", "(ILkotlin/jvm/functions/Function1;)[S", "ushortArrayOf", "elements", "ushortArrayOf-rL5Bavg", "([S)[S", "kotlin-stdlib"})
public final class UShortArrayKt {
    /*
     * WARNING - void declaration
     */
    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final short[] UShortArray(int size, Function1<? super Integer, UShort> init) {
        int $i$f$UShortArray = 0;
        short[] sArray = new short[size];
        int n = 0;
        while (n < size) {
            short s2;
            void index;
            int n2 = n;
            int n3 = n++;
            short[] sArray2 = sArray;
            boolean bl = false;
            short s3 = init.invoke((Integer)((int)index)).unbox-impl();
            boolean bl2 = false;
            sArray2[n3] = s2 = s3;
        }
        return UShortArray.constructor-impl(sArray);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final short[] ushortArrayOf-rL5Bavg(short ... elements) {
        int n = 0;
        return elements;
    }
}

