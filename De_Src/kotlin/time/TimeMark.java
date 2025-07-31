/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.time.AdjustedTimeMark;
import kotlin.time.Duration;
import kotlin.time.ExperimentalTime;
import org.jetbrains.annotations.NotNull;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\b'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u0004H&\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bJ\u001b\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0004H\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0004H\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000f\u0010\r\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006\u0010"}, d2={"Lkotlin/time/TimeMark;", "", "()V", "elapsedNow", "Lkotlin/time/Duration;", "elapsedNow-UwyO8pc", "()D", "hasNotPassedNow", "", "hasPassedNow", "minus", "duration", "minus-LRDsOJo", "(D)Lkotlin/time/TimeMark;", "plus", "plus-LRDsOJo", "kotlin-stdlib"})
@SinceKotlin(version="1.3")
@ExperimentalTime
public abstract class TimeMark {
    public abstract double elapsedNow-UwyO8pc();

    @NotNull
    public TimeMark plus-LRDsOJo(double duration) {
        return new AdjustedTimeMark(this, duration, null);
    }

    @NotNull
    public TimeMark minus-LRDsOJo(double duration) {
        return this.plus-LRDsOJo(Duration.unaryMinus-UwyO8pc(duration));
    }

    public final boolean hasPassedNow() {
        return !Duration.isNegative-impl(this.elapsedNow-UwyO8pc());
    }

    public final boolean hasNotPassedNow() {
        return Duration.isNegative-impl(this.elapsedNow-UwyO8pc());
    }
}

