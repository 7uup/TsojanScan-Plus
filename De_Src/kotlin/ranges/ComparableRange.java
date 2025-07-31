/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.ranges;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0012\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0006J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0016\u0010\u0005\u001a\u00028\u0000X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00028\u0000X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\b\u00a8\u0006\u0013"}, d2={"Lkotlin/ranges/ComparableRange;", "T", "", "Lkotlin/ranges/ClosedRange;", "start", "endInclusive", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)V", "getEndInclusive", "()Ljava/lang/Comparable;", "Ljava/lang/Comparable;", "getStart", "equals", "", "other", "", "hashCode", "", "toString", "", "kotlin-stdlib"})
class ComparableRange<T extends Comparable<? super T>>
implements ClosedRange<T> {
    @NotNull
    private final T start;
    @NotNull
    private final T endInclusive;

    public boolean equals(@Nullable Object other) {
        return other instanceof ComparableRange && (this.isEmpty() && ((ComparableRange)other).isEmpty() || Intrinsics.areEqual(this.getStart(), ((ComparableRange)other).getStart()) && Intrinsics.areEqual(this.getEndInclusive(), ((ComparableRange)other).getEndInclusive()));
    }

    public int hashCode() {
        return this.isEmpty() ? -1 : 31 * this.getStart().hashCode() + this.getEndInclusive().hashCode();
    }

    @NotNull
    public String toString() {
        return this.getStart() + ".." + this.getEndInclusive();
    }

    @Override
    @NotNull
    public T getStart() {
        return this.start;
    }

    @Override
    @NotNull
    public T getEndInclusive() {
        return this.endInclusive;
    }

    public ComparableRange(@NotNull T start, @NotNull T endInclusive) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(endInclusive, "endInclusive");
        this.start = start;
        this.endInclusive = endInclusive;
    }

    @Override
    public boolean contains(@NotNull T value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return ClosedRange.DefaultImpls.contains(this, value);
    }

    @Override
    public boolean isEmpty() {
        return ClosedRange.DefaultImpls.isEmpty(this);
    }
}

