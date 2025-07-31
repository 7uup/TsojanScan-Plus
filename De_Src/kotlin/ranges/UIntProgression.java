/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.ranges;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.UnsignedKt;
import kotlin.collections.UIntIterator;
import kotlin.internal.UProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.UIntProgressionIterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0017\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0019B\"\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0096\u0002J\b\u0010\u0013\u001a\u00020\u0006H\u0016J\b\u0010\u0014\u001a\u00020\u0010H\u0016J\t\u0010\u0015\u001a\u00020\u0016H\u0096\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u0019\u0010\b\u001a\u00020\u0002\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0019\u0010\f\u001a\u00020\u0002\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n\u00f8\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006\u001a"}, d2={"Lkotlin/ranges/UIntProgression;", "", "Lkotlin/UInt;", "start", "endInclusive", "step", "", "(IIILkotlin/jvm/internal/DefaultConstructorMarker;)V", "first", "getFirst-pVg5ArA", "()I", "I", "last", "getLast-pVg5ArA", "getStep", "equals", "", "other", "", "hashCode", "isEmpty", "iterator", "Lkotlin/collections/UIntIterator;", "toString", "", "Companion", "kotlin-stdlib"})
@SinceKotlin(version="1.3")
@ExperimentalUnsignedTypes
public class UIntProgression
implements Iterable<UInt>,
KMappedMarker {
    private final int first;
    private final int last;
    private final int step;
    public static final Companion Companion = new Companion(null);

    public final int getFirst-pVg5ArA() {
        return this.first;
    }

    public final int getLast-pVg5ArA() {
        return this.last;
    }

    public final int getStep() {
        return this.step;
    }

    @NotNull
    public UIntIterator iterator() {
        return new UIntProgressionIterator(this.first, this.last, this.step, null);
    }

    public boolean isEmpty() {
        boolean bl;
        if (this.step > 0) {
            int n = this.first;
            int n2 = this.last;
            boolean bl2 = false;
            bl = UnsignedKt.uintCompare(n, n2) > 0;
        } else {
            int n = this.first;
            int n3 = this.last;
            boolean bl3 = false;
            bl = UnsignedKt.uintCompare(n, n3) < 0;
        }
        return bl;
    }

    public boolean equals(@Nullable Object other) {
        return other instanceof UIntProgression && (this.isEmpty() && ((UIntProgression)other).isEmpty() || this.first == ((UIntProgression)other).first && this.last == ((UIntProgression)other).last && this.step == ((UIntProgression)other).step);
    }

    public int hashCode() {
        int n;
        if (this.isEmpty()) {
            n = -1;
        } else {
            int n2 = this.first;
            boolean bl = false;
            int n3 = 31 * n2;
            n2 = this.last;
            bl = false;
            n = 31 * (n3 + n2) + this.step;
        }
        return n;
    }

    @NotNull
    public String toString() {
        return this.step > 0 ? UInt.toString-impl(this.first) + ".." + UInt.toString-impl(this.last) + " step " + this.step : UInt.toString-impl(this.first) + " downTo " + UInt.toString-impl(this.last) + " step " + -this.step;
    }

    private UIntProgression(int start, int endInclusive, int step) {
        if (step == 0) {
            throw (Throwable)new IllegalArgumentException("Step must be non-zero.");
        }
        if (step == Integer.MIN_VALUE) {
            throw (Throwable)new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        this.first = start;
        this.last = UProgressionUtilKt.getProgressionLastElement-Nkh28Cs(start, endInclusive, step);
        this.step = step;
    }

    public /* synthetic */ UIntProgression(int start, int endInclusive, int step, DefaultConstructorMarker $constructor_marker) {
        this(start, endInclusive, step);
    }

    /*
     * Illegal identifiers - consider using --renameillegalidents true
     */
    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t\u00f8\u0001\u0000\u00a2\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\f"}, d2={"Lkotlin/ranges/UIntProgression$Companion;", "", "()V", "fromClosedRange", "Lkotlin/ranges/UIntProgression;", "rangeStart", "Lkotlin/UInt;", "rangeEnd", "step", "", "fromClosedRange-Nkh28Cs", "(III)Lkotlin/ranges/UIntProgression;", "kotlin-stdlib"})
    public static final class Companion {
        @NotNull
        public final UIntProgression fromClosedRange-Nkh28Cs(int rangeStart, int rangeEnd, int step) {
            return new UIntProgression(rangeStart, rangeEnd, step, null);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

