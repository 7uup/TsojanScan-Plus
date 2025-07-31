/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.ranges;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedDoubleRange;
import kotlin.ranges.ClosedFloatRange;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.ComparableRange;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a@\u0010\u0006\u001a\u00020\u0003\"\b\b\u0000\u0010\u0007*\u00020\b\"\u0018\b\u0001\u0010\t*\b\u0012\u0004\u0012\u0002H\u00070\n*\b\u0012\u0004\u0012\u0002H\u00070\u000b*\u0002H\t2\b\u0010\f\u001a\u0004\u0018\u0001H\u0007H\u0087\n\u00a2\u0006\u0002\u0010\r\u001a0\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00070\u000b\"\u000e\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u000f*\u0002H\u00072\u0006\u0010\u0010\u001a\u0002H\u0007H\u0086\u0002\u00a2\u0006\u0002\u0010\u0011\u001a\u001b\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012*\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u0013H\u0087\u0002\u001a\u001b\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00140\u0012*\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u0014H\u0087\u0002\u00a8\u0006\u0015"}, d2={"checkStepIsPositive", "", "isPositive", "", "step", "", "contains", "T", "", "R", "", "Lkotlin/ranges/ClosedRange;", "element", "(Ljava/lang/Iterable;Ljava/lang/Object;)Z", "rangeTo", "", "that", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Lkotlin/ranges/ClosedRange;", "Lkotlin/ranges/ClosedFloatingPointRange;", "", "", "kotlin-stdlib"}, xs="kotlin/ranges/RangesKt")
class RangesKt__RangesKt {
    @NotNull
    public static final <T extends Comparable<? super T>> ClosedRange<T> rangeTo(@NotNull T $this$rangeTo, @NotNull T that) {
        Intrinsics.checkNotNullParameter($this$rangeTo, "$this$rangeTo");
        Intrinsics.checkNotNullParameter(that, "that");
        return new ComparableRange<T>($this$rangeTo, that);
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final ClosedFloatingPointRange<Double> rangeTo(double $this$rangeTo, double that) {
        return new ClosedDoubleRange($this$rangeTo, that);
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final ClosedFloatingPointRange<Float> rangeTo(float $this$rangeTo, float that) {
        return new ClosedFloatRange($this$rangeTo, that);
    }

    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final <T, R extends Iterable<? extends T> & ClosedRange<T>> boolean contains(R $this$contains, T element) {
        int $i$f$contains = 0;
        Intrinsics.checkNotNullParameter($this$contains, "$this$contains");
        return element != null && ((ClosedRange<Comparable>)$this$contains).contains((Comparable)element);
    }

    public static final void checkStepIsPositive(boolean isPositive, @NotNull Number step) {
        Intrinsics.checkNotNullParameter(step, "step");
        if (!isPositive) {
            throw (Throwable)new IllegalArgumentException("Step must be positive, was: " + step + '.');
        }
    }
}

