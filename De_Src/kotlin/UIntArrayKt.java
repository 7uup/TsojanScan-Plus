/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0002\u0010\u0007\u001a\u001f\u0010\b\u001a\u00020\u00012\n\u0010\t\u001a\u00020\u0001\"\u00020\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b\u009920\u0001\u00a8\u0006\f"}, d2={"UIntArray", "Lkotlin/UIntArray;", "size", "", "init", "Lkotlin/Function1;", "Lkotlin/UInt;", "(ILkotlin/jvm/functions/Function1;)[I", "uintArrayOf", "elements", "uintArrayOf--ajY-9A", "([I)[I", "kotlin-stdlib"})
public final class UIntArrayKt {
    /*
     * WARNING - void declaration
     */
    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int[] UIntArray(int size, Function1<? super Integer, UInt> init) {
        int $i$f$UIntArray = 0;
        int[] nArray = new int[size];
        int n = 0;
        while (n < size) {
            int n2;
            void index;
            int n3 = n;
            int n4 = n++;
            int[] nArray2 = nArray;
            boolean bl = false;
            int n5 = init.invoke((Integer)((int)index)).unbox-impl();
            boolean bl2 = false;
            nArray2[n4] = n2 = n5;
        }
        return UIntArray.constructor-impl(nArray);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int[] uintArrayOf--ajY-9A(int ... elements) {
        int n = 0;
        return elements;
    }
}

