/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.DurationUnitKt;
import kotlin.time.ExperimentalTime;
import org.jetbrains.annotations.NotNull;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001f\u0010%\u001a\u00020\u0007*\u00020\b2\u0006\u0010&\u001a\u00020\u0007H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b'\u0010(\u001a\u001f\u0010%\u001a\u00020\u0007*\u00020\r2\u0006\u0010&\u001a\u00020\u0007H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b)\u0010*\u001a \u0010+\u001a\u00020\u0007*\u00020\b2\n\u0010,\u001a\u00060\u0001j\u0002`-H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010.\u001a \u0010+\u001a\u00020\u0007*\u00020\r2\n\u0010,\u001a\u00060\u0001j\u0002`-H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010/\u001a \u0010+\u001a\u00020\u0007*\u00020\u00102\n\u0010,\u001a\u00060\u0001j\u0002`-H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u00100\"\u001b\u0010\u0000\u001a\u00020\u00018\u00c2\u0002X\u0082\u0004\u00a2\u0006\f\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"!\u0010\u0006\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"!\u0010\u0006\u001a\u00020\u0007*\u00020\r8FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\t\u0010\u000e\u001a\u0004\b\u000b\u0010\u000f\"!\u0010\u0006\u001a\u00020\u0007*\u00020\u00108FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\t\u0010\u0011\u001a\u0004\b\u000b\u0010\u0012\"!\u0010\u0013\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u0014\u0010\n\u001a\u0004\b\u0015\u0010\f\"!\u0010\u0013\u001a\u00020\u0007*\u00020\r8FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u0014\u0010\u000e\u001a\u0004\b\u0015\u0010\u000f\"!\u0010\u0013\u001a\u00020\u0007*\u00020\u00108FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0012\"!\u0010\u0016\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u0017\u0010\n\u001a\u0004\b\u0018\u0010\f\"!\u0010\u0016\u001a\u00020\u0007*\u00020\r8FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u0017\u0010\u000e\u001a\u0004\b\u0018\u0010\u000f\"!\u0010\u0016\u001a\u00020\u0007*\u00020\u00108FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u0017\u0010\u0011\u001a\u0004\b\u0018\u0010\u0012\"!\u0010\u0019\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u001a\u0010\n\u001a\u0004\b\u001b\u0010\f\"!\u0010\u0019\u001a\u00020\u0007*\u00020\r8FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u001a\u0010\u000e\u001a\u0004\b\u001b\u0010\u000f\"!\u0010\u0019\u001a\u00020\u0007*\u00020\u00108FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u001a\u0010\u0011\u001a\u0004\b\u001b\u0010\u0012\"!\u0010\u001c\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u001d\u0010\n\u001a\u0004\b\u001e\u0010\f\"!\u0010\u001c\u001a\u00020\u0007*\u00020\r8FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u001d\u0010\u000e\u001a\u0004\b\u001e\u0010\u000f\"!\u0010\u001c\u001a\u00020\u0007*\u00020\u00108FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u001d\u0010\u0011\u001a\u0004\b\u001e\u0010\u0012\"!\u0010\u001f\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b \u0010\n\u001a\u0004\b!\u0010\f\"!\u0010\u001f\u001a\u00020\u0007*\u00020\r8FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b \u0010\u000e\u001a\u0004\b!\u0010\u000f\"!\u0010\u001f\u001a\u00020\u0007*\u00020\u00108FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b \u0010\u0011\u001a\u0004\b!\u0010\u0012\"!\u0010\"\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b#\u0010\n\u001a\u0004\b$\u0010\f\"!\u0010\"\u001a\u00020\u0007*\u00020\r8FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b#\u0010\u000e\u001a\u0004\b$\u0010\u000f\"!\u0010\"\u001a\u00020\u0007*\u00020\u00108FX\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b#\u0010\u0011\u001a\u0004\b$\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00061"}, d2={"storageUnit", "Ljava/util/concurrent/TimeUnit;", "getStorageUnit$annotations", "()V", "getStorageUnit", "()Ljava/util/concurrent/TimeUnit;", "days", "Lkotlin/time/Duration;", "", "getDays$annotations", "(D)V", "getDays", "(D)D", "", "(I)V", "(I)D", "", "(J)V", "(J)D", "hours", "getHours$annotations", "getHours", "microseconds", "getMicroseconds$annotations", "getMicroseconds", "milliseconds", "getMilliseconds$annotations", "getMilliseconds", "minutes", "getMinutes$annotations", "getMinutes", "nanoseconds", "getNanoseconds$annotations", "getNanoseconds", "seconds", "getSeconds$annotations", "getSeconds", "times", "duration", "times-kIfJnKk", "(DD)D", "times-mvk6XK0", "(ID)D", "toDuration", "unit", "Lkotlin/time/DurationUnit;", "(DLjava/util/concurrent/TimeUnit;)D", "(ILjava/util/concurrent/TimeUnit;)D", "(JLjava/util/concurrent/TimeUnit;)D", "kotlin-stdlib"})
public final class DurationKt {
    private static /* synthetic */ void getStorageUnit$annotations() {
    }

    private static final TimeUnit getStorageUnit() {
        int $i$f$getStorageUnit = 0;
        return TimeUnit.NANOSECONDS;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static final double toDuration(int $this$toDuration, @NotNull TimeUnit unit) {
        Intrinsics.checkNotNullParameter((Object)unit, "unit");
        return DurationKt.toDuration((double)$this$toDuration, unit);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static final double toDuration(long $this$toDuration, @NotNull TimeUnit unit) {
        Intrinsics.checkNotNullParameter((Object)unit, "unit");
        return DurationKt.toDuration((double)$this$toDuration, unit);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static final double toDuration(double $this$toDuration, @NotNull TimeUnit unit) {
        Intrinsics.checkNotNullParameter((Object)unit, "unit");
        boolean $i$f$getStorageUnit = false;
        return Duration.constructor-impl(DurationUnitKt.convertDurationUnit($this$toDuration, unit, TimeUnit.NANOSECONDS));
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getNanoseconds$annotations(int n) {
    }

    public static final double getNanoseconds(int $this$nanoseconds) {
        return DurationKt.toDuration($this$nanoseconds, TimeUnit.NANOSECONDS);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getNanoseconds$annotations(long l) {
    }

    public static final double getNanoseconds(long $this$nanoseconds) {
        return DurationKt.toDuration($this$nanoseconds, TimeUnit.NANOSECONDS);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getNanoseconds$annotations(double d) {
    }

    public static final double getNanoseconds(double $this$nanoseconds) {
        return DurationKt.toDuration($this$nanoseconds, TimeUnit.NANOSECONDS);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getMicroseconds$annotations(int n) {
    }

    public static final double getMicroseconds(int $this$microseconds) {
        return DurationKt.toDuration($this$microseconds, TimeUnit.MICROSECONDS);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getMicroseconds$annotations(long l) {
    }

    public static final double getMicroseconds(long $this$microseconds) {
        return DurationKt.toDuration($this$microseconds, TimeUnit.MICROSECONDS);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getMicroseconds$annotations(double d) {
    }

    public static final double getMicroseconds(double $this$microseconds) {
        return DurationKt.toDuration($this$microseconds, TimeUnit.MICROSECONDS);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getMilliseconds$annotations(int n) {
    }

    public static final double getMilliseconds(int $this$milliseconds) {
        return DurationKt.toDuration($this$milliseconds, TimeUnit.MILLISECONDS);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getMilliseconds$annotations(long l) {
    }

    public static final double getMilliseconds(long $this$milliseconds) {
        return DurationKt.toDuration($this$milliseconds, TimeUnit.MILLISECONDS);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getMilliseconds$annotations(double d) {
    }

    public static final double getMilliseconds(double $this$milliseconds) {
        return DurationKt.toDuration($this$milliseconds, TimeUnit.MILLISECONDS);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getSeconds$annotations(int n) {
    }

    public static final double getSeconds(int $this$seconds) {
        return DurationKt.toDuration($this$seconds, TimeUnit.SECONDS);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getSeconds$annotations(long l) {
    }

    public static final double getSeconds(long $this$seconds) {
        return DurationKt.toDuration($this$seconds, TimeUnit.SECONDS);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getSeconds$annotations(double d) {
    }

    public static final double getSeconds(double $this$seconds) {
        return DurationKt.toDuration($this$seconds, TimeUnit.SECONDS);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getMinutes$annotations(int n) {
    }

    public static final double getMinutes(int $this$minutes) {
        return DurationKt.toDuration($this$minutes, TimeUnit.MINUTES);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getMinutes$annotations(long l) {
    }

    public static final double getMinutes(long $this$minutes) {
        return DurationKt.toDuration($this$minutes, TimeUnit.MINUTES);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getMinutes$annotations(double d) {
    }

    public static final double getMinutes(double $this$minutes) {
        return DurationKt.toDuration($this$minutes, TimeUnit.MINUTES);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getHours$annotations(int n) {
    }

    public static final double getHours(int $this$hours) {
        return DurationKt.toDuration($this$hours, TimeUnit.HOURS);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getHours$annotations(long l) {
    }

    public static final double getHours(long $this$hours) {
        return DurationKt.toDuration($this$hours, TimeUnit.HOURS);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getHours$annotations(double d) {
    }

    public static final double getHours(double $this$hours) {
        return DurationKt.toDuration($this$hours, TimeUnit.HOURS);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getDays$annotations(int n) {
    }

    public static final double getDays(int $this$days) {
        return DurationKt.toDuration($this$days, TimeUnit.DAYS);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getDays$annotations(long l) {
    }

    public static final double getDays(long $this$days) {
        return DurationKt.toDuration($this$days, TimeUnit.DAYS);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void getDays$annotations(double d) {
    }

    public static final double getDays(double $this$days) {
        return DurationKt.toDuration($this$days, TimeUnit.DAYS);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    @InlineOnly
    private static final double times-mvk6XK0(int $this$times, double duration) {
        int n = 0;
        return Duration.times-UwyO8pc(duration, $this$times);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalTime
    @InlineOnly
    private static final double times-kIfJnKk(double $this$times, double duration) {
        int n = 0;
        return Duration.times-UwyO8pc(duration, $this$times);
    }

    public static final /* synthetic */ TimeUnit access$getStorageUnit$p() {
        return DurationKt.getStorageUnit();
    }
}

