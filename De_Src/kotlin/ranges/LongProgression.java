/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.ranges;

import kotlin.Metadata;
import kotlin.collections.LongIterator;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.LongProgressionIterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00182\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0018B\u001f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0002\u0010\u0006J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u000eH\u0016J\t\u0010\u0014\u001a\u00020\u0015H\u0096\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u0011\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0002\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u00a8\u0006\u0019"}, d2={"Lkotlin/ranges/LongProgression;", "", "", "start", "endInclusive", "step", "(JJJ)V", "first", "getFirst", "()J", "last", "getLast", "getStep", "equals", "", "other", "", "hashCode", "", "isEmpty", "iterator", "Lkotlin/collections/LongIterator;", "toString", "", "Companion", "kotlin-stdlib"})
public class LongProgression
implements Iterable<Long>,
KMappedMarker {
    private final long first;
    private final long last;
    private final long step;
    public static final Companion Companion = new Companion(null);

    public final long getFirst() {
        return this.first;
    }

    public final long getLast() {
        return this.last;
    }

    public final long getStep() {
        return this.step;
    }

    @NotNull
    public LongIterator iterator() {
        return new LongProgressionIterator(this.first, this.last, this.step);
    }

    public boolean isEmpty() {
        return this.step > 0L ? this.first > this.last : this.first < this.last;
    }

    public boolean equals(@Nullable Object other) {
        return other instanceof LongProgression && (this.isEmpty() && ((LongProgression)other).isEmpty() || this.first == ((LongProgression)other).first && this.last == ((LongProgression)other).last && this.step == ((LongProgression)other).step);
    }

    public int hashCode() {
        return this.isEmpty() ? -1 : (int)((long)31 * ((long)31 * (this.first ^ this.first >>> 32) + (this.last ^ this.last >>> 32)) + (this.step ^ this.step >>> 32));
    }

    @NotNull
    public String toString() {
        return this.step > 0L ? this.first + ".." + this.last + " step " + this.step : this.first + " downTo " + this.last + " step " + -this.step;
    }

    public LongProgression(long start, long endInclusive, long step) {
        if (step == 0L) {
            throw (Throwable)new IllegalArgumentException("Step must be non-zero.");
        }
        if (step == Long.MIN_VALUE) {
            throw (Throwable)new IllegalArgumentException("Step must be greater than Long.MIN_VALUE to avoid overflow on negation.");
        }
        this.first = start;
        this.last = ProgressionUtilKt.getProgressionLastElement(start, endInclusive, step);
        this.step = step;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006\u00a8\u0006\t"}, d2={"Lkotlin/ranges/LongProgression$Companion;", "", "()V", "fromClosedRange", "Lkotlin/ranges/LongProgression;", "rangeStart", "", "rangeEnd", "step", "kotlin-stdlib"})
    public static final class Companion {
        @NotNull
        public final LongProgression fromClosedRange(long rangeStart, long rangeEnd, long step) {
            return new LongProgression(rangeStart, rangeEnd, step);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

