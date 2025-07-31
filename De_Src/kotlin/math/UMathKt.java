/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.math;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.comparisons.UComparisonsKt;
import kotlin.internal.InlineOnly;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a#\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0005\u001a#\u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0007\u0010\b\u001a#\u0010\t\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\n\u0010\u0005\u001a#\u0010\t\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000b\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\f"}, d2={"max", "Lkotlin/UInt;", "a", "b", "max-J1ME1BU", "(II)I", "Lkotlin/ULong;", "max-eb3DHEI", "(JJ)J", "min", "min-J1ME1BU", "min-eb3DHEI", "kotlin-stdlib"})
public final class UMathKt {
    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int min-J1ME1BU(int a, int b) {
        int n = 0;
        return UComparisonsKt.minOf-J1ME1BU(a, b);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final long min-eb3DHEI(long a, long b) {
        int n = 0;
        return UComparisonsKt.minOf-eb3DHEI(a, b);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int max-J1ME1BU(int a, int b) {
        int n = 0;
        return UComparisonsKt.maxOf-J1ME1BU(a, b);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final long max-eb3DHEI(long a, long b) {
        int n = 0;
        return UComparisonsKt.maxOf-eb3DHEI(a, b);
    }
}

