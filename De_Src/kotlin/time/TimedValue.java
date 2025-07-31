/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.ExperimentalTime;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0018\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\r\u001a\u00028\u0000H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\u000e\u001a\u00020\u0005H\u00c6\u0003\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u000f\u0010\bJ-\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001R\u0019\u0010\u0004\u001a\u00020\u0005\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0003\u001a\u00028\u0000\u00a2\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006\u001a"}, d2={"Lkotlin/time/TimedValue;", "T", "", "value", "duration", "Lkotlin/time/Duration;", "(Ljava/lang/Object;DLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getDuration-UwyO8pc", "()D", "D", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "component2-UwyO8pc", "copy", "copy-RFiDyg4", "(Ljava/lang/Object;D)Lkotlin/time/TimedValue;", "equals", "", "other", "hashCode", "", "toString", "", "kotlin-stdlib"})
@SinceKotlin(version="1.3")
@ExperimentalTime
public final class TimedValue<T> {
    private final T value;
    private final double duration;

    public final T getValue() {
        return this.value;
    }

    public final double getDuration-UwyO8pc() {
        return this.duration;
    }

    private TimedValue(T value, double duration) {
        this.value = value;
        this.duration = duration;
    }

    public /* synthetic */ TimedValue(Object value, double duration, DefaultConstructorMarker $constructor_marker) {
        this(value, duration);
    }

    public final T component1() {
        return this.value;
    }

    public final double component2-UwyO8pc() {
        return this.duration;
    }

    @NotNull
    public final TimedValue<T> copy-RFiDyg4(T value, double duration) {
        return new TimedValue<T>(value, duration);
    }

    public static /* synthetic */ TimedValue copy-RFiDyg4$default(TimedValue timedValue, Object object, double d, int n, Object object2) {
        if ((n & 1) != 0) {
            object = timedValue.value;
        }
        if ((n & 2) != 0) {
            d = timedValue.duration;
        }
        return timedValue.copy-RFiDyg4(object, d);
    }

    @NotNull
    public String toString() {
        return "TimedValue(value=" + this.value + ", duration=" + Duration.toString-impl(this.duration) + ")";
    }

    public int hashCode() {
        T t = this.value;
        long l = Double.doubleToLongBits(this.duration);
        return (t != null ? t.hashCode() : 0) * 31 + (int)(l ^ l >>> 32);
    }

    public boolean equals(@Nullable Object object) {
        block3: {
            block2: {
                if (this == object) break block2;
                if (!(object instanceof TimedValue)) break block3;
                TimedValue timedValue = (TimedValue)object;
                if (!Intrinsics.areEqual(this.value, timedValue.value) || Double.compare(this.duration, timedValue.duration) != 0) break block3;
            }
            return true;
        }
        return false;
    }
}

