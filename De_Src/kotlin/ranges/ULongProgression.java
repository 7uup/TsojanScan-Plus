/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.ranges;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.collections.ULongIterator;
import kotlin.internal.UProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.ULongProgressionIterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0017\u0018\u0000 \u001a2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001aB\"\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0096\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0010H\u0016J\t\u0010\u0016\u001a\u00020\u0017H\u0096\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u0019\u0010\b\u001a\u00020\u0002\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0019\u0010\f\u001a\u00020\u0002\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n\u00f8\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006\u001b"}, d2={"Lkotlin/ranges/ULongProgression;", "", "Lkotlin/ULong;", "start", "endInclusive", "step", "", "(JJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "first", "getFirst-s-VKNKU", "()J", "J", "last", "getLast-s-VKNKU", "getStep", "equals", "", "other", "", "hashCode", "", "isEmpty", "iterator", "Lkotlin/collections/ULongIterator;", "toString", "", "Companion", "kotlin-stdlib"})
@SinceKotlin(version="1.3")
@ExperimentalUnsignedTypes
public class ULongProgression
implements Iterable<ULong>,
KMappedMarker {
    private final long first;
    private final long last;
    private final long step;
    public static final Companion Companion = new Companion(null);

    public final long getFirst-s-VKNKU() {
        return this.first;
    }

    public final long getLast-s-VKNKU() {
        return this.last;
    }

    public final long getStep() {
        return this.step;
    }

    @NotNull
    public ULongIterator iterator() {
        return new ULongProgressionIterator(this.first, this.last, this.step, null);
    }

    public boolean isEmpty() {
        boolean bl;
        if (this.step > 0L) {
            long l = this.first;
            long l2 = this.last;
            boolean bl2 = false;
            bl = UnsignedKt.ulongCompare(l, l2) > 0;
        } else {
            long l = this.first;
            long l3 = this.last;
            boolean bl3 = false;
            bl = UnsignedKt.ulongCompare(l, l3) < 0;
        }
        return bl;
    }

    public boolean equals(@Nullable Object other) {
        return other instanceof ULongProgression && (this.isEmpty() && ((ULongProgression)other).isEmpty() || this.first == ((ULongProgression)other).first && this.last == ((ULongProgression)other).last && this.step == ((ULongProgression)other).step);
    }

    public int hashCode() {
        int n;
        if (this.isEmpty()) {
            n = -1;
        } else {
            long l = this.first;
            long l2 = this.first;
            int n2 = 32;
            boolean bl = false;
            l2 = ULong.constructor-impl(l2 >>> n2);
            n2 = 0;
            l = ULong.constructor-impl(l ^ l2);
            boolean bl2 = false;
            int n3 = 31 * (int)l;
            l = this.last;
            long l3 = this.last;
            n2 = 32;
            bl = false;
            l3 = ULong.constructor-impl(l3 >>> n2);
            n2 = 0;
            l = ULong.constructor-impl(l ^ l3);
            boolean bl3 = false;
            n = 31 * (n3 + (int)l) + (int)(this.step ^ this.step >>> 32);
        }
        return n;
    }

    @NotNull
    public String toString() {
        return this.step > 0L ? ULong.toString-impl(this.first) + ".." + ULong.toString-impl(this.last) + " step " + this.step : ULong.toString-impl(this.first) + " downTo " + ULong.toString-impl(this.last) + " step " + -this.step;
    }

    private ULongProgression(long start, long endInclusive, long step) {
        if (step == 0L) {
            throw (Throwable)new IllegalArgumentException("Step must be non-zero.");
        }
        if (step == Long.MIN_VALUE) {
            throw (Throwable)new IllegalArgumentException("Step must be greater than Long.MIN_VALUE to avoid overflow on negation.");
        }
        this.first = start;
        this.last = UProgressionUtilKt.getProgressionLastElement-7ftBX0g(start, endInclusive, step);
        this.step = step;
    }

    public /* synthetic */ ULongProgression(long start, long endInclusive, long step, DefaultConstructorMarker $constructor_marker) {
        this(start, endInclusive, step);
    }

    /*
     * Illegal identifiers - consider using --renameillegalidents true
     */
    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t\u00f8\u0001\u0000\u00a2\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\f"}, d2={"Lkotlin/ranges/ULongProgression$Companion;", "", "()V", "fromClosedRange", "Lkotlin/ranges/ULongProgression;", "rangeStart", "Lkotlin/ULong;", "rangeEnd", "step", "", "fromClosedRange-7ftBX0g", "(JJJ)Lkotlin/ranges/ULongProgression;", "kotlin-stdlib"})
    public static final class Companion {
        @NotNull
        public final ULongProgression fromClosedRange-7ftBX0g(long rangeStart, long rangeEnd, long step) {
            return new ULongProgression(rangeStart, rangeEnd, step, null);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

