/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.time;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.time.Duration;
import kotlin.time.ExperimentalTime;
import kotlin.time.TimeMark;
import org.jetbrains.annotations.NotNull;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0003\u0018\u00002\u00020\u0001B\u0018\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005J\u0015\u0010\u000b\u001a\u00020\u0004H\u0016\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\f\u0010\u0007J\u001b\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0004H\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0003\u001a\u00020\u0004\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006\u0011"}, d2={"Lkotlin/time/AdjustedTimeMark;", "Lkotlin/time/TimeMark;", "mark", "adjustment", "Lkotlin/time/Duration;", "(Lkotlin/time/TimeMark;DLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getAdjustment-UwyO8pc", "()D", "D", "getMark", "()Lkotlin/time/TimeMark;", "elapsedNow", "elapsedNow-UwyO8pc", "plus", "duration", "plus-LRDsOJo", "(D)Lkotlin/time/TimeMark;", "kotlin-stdlib"})
@ExperimentalTime
final class AdjustedTimeMark
extends TimeMark {
    @NotNull
    private final TimeMark mark;
    private final double adjustment;

    @Override
    public double elapsedNow-UwyO8pc() {
        return Duration.minus-LRDsOJo(this.mark.elapsedNow-UwyO8pc(), this.adjustment);
    }

    @Override
    @NotNull
    public TimeMark plus-LRDsOJo(double duration) {
        return new AdjustedTimeMark(this.mark, Duration.plus-LRDsOJo(this.adjustment, duration), null);
    }

    @NotNull
    public final TimeMark getMark() {
        return this.mark;
    }

    public final double getAdjustment-UwyO8pc() {
        return this.adjustment;
    }

    private AdjustedTimeMark(TimeMark mark, double adjustment) {
        this.mark = mark;
        this.adjustment = adjustment;
    }

    public /* synthetic */ AdjustedTimeMark(TimeMark mark, double adjustment, DefaultConstructorMarker $constructor_marker) {
        this(mark, adjustment);
    }
}

