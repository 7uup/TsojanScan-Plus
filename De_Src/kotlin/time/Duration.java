/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnitKt;
import kotlin.time.ExperimentalTime;
import kotlin.time.FormatToDecimalsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0087@\u0018\u0000 s2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001sB\u0014\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020\u0000H\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b'\u0010(J\u001e\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u0003H\u0086\u0002\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b+\u0010,J\u001e\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\tH\u0086\u0002\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b+\u0010-J\u001b\u0010)\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u0000H\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b.\u0010,J\u0013\u0010/\u001a\u0002002\b\u0010&\u001a\u0004\u0018\u000101H\u00d6\u0003J\t\u00102\u001a\u00020\tH\u00d6\u0001J\r\u00103\u001a\u000200\u00a2\u0006\u0004\b4\u00105J\r\u00106\u001a\u000200\u00a2\u0006\u0004\b7\u00105J\r\u00108\u001a\u000200\u00a2\u0006\u0004\b9\u00105J\r\u0010:\u001a\u000200\u00a2\u0006\u0004\b;\u00105J\u001b\u0010<\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b=\u0010,J\u001b\u0010>\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b?\u0010,J\u0017\u0010@\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\bA\u0010(J\u001e\u0010B\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u0003H\u0086\u0002\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bC\u0010,J\u001e\u0010B\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\tH\u0086\u0002\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bC\u0010-J\u009d\u0001\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E2u\u0010F\u001aq\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(J\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(K\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0GH\u0086\b\u00f8\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0004\bO\u0010PJ\u0088\u0001\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E2`\u0010F\u001a\\\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(K\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0QH\u0086\b\u00f8\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0004\bO\u0010RJs\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E2K\u0010F\u001aG\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0SH\u0086\b\u00f8\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0004\bO\u0010TJ^\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E26\u0010F\u001a2\u0012\u0013\u0012\u00110V\u00a2\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0UH\u0086\b\u00f8\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0004\bO\u0010WJ\u0019\u0010X\u001a\u00020\u00032\n\u0010Y\u001a\u00060Zj\u0002`[\u00a2\u0006\u0004\b\\\u0010]J\u0019\u0010^\u001a\u00020\t2\n\u0010Y\u001a\u00060Zj\u0002`[\u00a2\u0006\u0004\b_\u0010`J\r\u0010a\u001a\u00020b\u00a2\u0006\u0004\bc\u0010dJ\u0019\u0010e\u001a\u00020V2\n\u0010Y\u001a\u00060Zj\u0002`[\u00a2\u0006\u0004\bf\u0010gJ\r\u0010h\u001a\u00020V\u00a2\u0006\u0004\bi\u0010jJ\r\u0010k\u001a\u00020V\u00a2\u0006\u0004\bl\u0010jJ\u000f\u0010m\u001a\u00020bH\u0016\u00a2\u0006\u0004\bn\u0010dJ#\u0010m\u001a\u00020b2\n\u0010Y\u001a\u00060Zj\u0002`[2\b\b\u0002\u0010o\u001a\u00020\t\u00a2\u0006\u0004\bn\u0010pJ\u0016\u0010q\u001a\u00020\u0000H\u0086\u0002\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\br\u0010\u0005R\u0017\u0010\u0006\u001a\u00020\u00008F\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u001a\u0010\b\u001a\u00020\t8@X\u0081\u0004\u00a2\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0005R\u0011\u0010\u0010\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0005R\u0011\u0010\u0012\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0005R\u0011\u0010\u0014\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0005R\u0011\u0010\u0016\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0005R\u0011\u0010\u0018\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u0005R\u0011\u0010\u001a\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u001b\u0010\u0005R\u001a\u0010\u001c\u001a\u00020\t8@X\u0081\u0004\u00a2\u0006\f\u0012\u0004\b\u001d\u0010\u000b\u001a\u0004\b\u001e\u0010\rR\u001a\u0010\u001f\u001a\u00020\t8@X\u0081\u0004\u00a2\u0006\f\u0012\u0004\b \u0010\u000b\u001a\u0004\b!\u0010\rR\u001a\u0010\"\u001a\u00020\t8@X\u0081\u0004\u00a2\u0006\f\u0012\u0004\b#\u0010\u000b\u001a\u0004\b$\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0080\u0004\u00a2\u0006\u0002\n\u0000\u00f8\u0001\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b\u009920\u0001\u00a8\u0006t"}, d2={"Lkotlin/time/Duration;", "", "value", "", "constructor-impl", "(D)D", "absoluteValue", "getAbsoluteValue-UwyO8pc", "hoursComponent", "", "getHoursComponent$annotations", "()V", "getHoursComponent-impl", "(D)I", "inDays", "getInDays-impl", "inHours", "getInHours-impl", "inMicroseconds", "getInMicroseconds-impl", "inMilliseconds", "getInMilliseconds-impl", "inMinutes", "getInMinutes-impl", "inNanoseconds", "getInNanoseconds-impl", "inSeconds", "getInSeconds-impl", "minutesComponent", "getMinutesComponent$annotations", "getMinutesComponent-impl", "nanosecondsComponent", "getNanosecondsComponent$annotations", "getNanosecondsComponent-impl", "secondsComponent", "getSecondsComponent$annotations", "getSecondsComponent-impl", "compareTo", "other", "compareTo-LRDsOJo", "(DD)I", "div", "scale", "div-UwyO8pc", "(DD)D", "(DI)D", "div-LRDsOJo", "equals", "", "", "hashCode", "isFinite", "isFinite-impl", "(D)Z", "isInfinite", "isInfinite-impl", "isNegative", "isNegative-impl", "isPositive", "isPositive-impl", "minus", "minus-LRDsOJo", "plus", "plus-LRDsOJo", "precision", "precision-impl", "times", "times-UwyO8pc", "toComponents", "T", "action", "Lkotlin/Function5;", "Lkotlin/ParameterName;", "name", "days", "hours", "minutes", "seconds", "nanoseconds", "toComponents-impl", "(DLkotlin/jvm/functions/Function5;)Ljava/lang/Object;", "Lkotlin/Function4;", "(DLkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "Lkotlin/Function3;", "(DLkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "Lkotlin/Function2;", "", "(DLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "toDouble", "unit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "toDouble-impl", "(DLjava/util/concurrent/TimeUnit;)D", "toInt", "toInt-impl", "(DLjava/util/concurrent/TimeUnit;)I", "toIsoString", "", "toIsoString-impl", "(D)Ljava/lang/String;", "toLong", "toLong-impl", "(DLjava/util/concurrent/TimeUnit;)J", "toLongMilliseconds", "toLongMilliseconds-impl", "(D)J", "toLongNanoseconds", "toLongNanoseconds-impl", "toString", "toString-impl", "decimals", "(DLjava/util/concurrent/TimeUnit;I)Ljava/lang/String;", "unaryMinus", "unaryMinus-UwyO8pc", "Companion", "kotlin-stdlib"})
@SinceKotlin(version="1.3")
@ExperimentalTime
public final class Duration
implements Comparable<Duration> {
    private final double value;
    private static final double ZERO;
    private static final double INFINITE;
    public static final Companion Companion;

    public int compareTo-LRDsOJo(double d) {
        return Duration.compareTo-LRDsOJo(this.value, d);
    }

    @PublishedApi
    public static /* synthetic */ void getHoursComponent$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getMinutesComponent$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getSecondsComponent$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getNanosecondsComponent$annotations() {
    }

    @NotNull
    public String toString() {
        return Duration.toString-impl(this.value);
    }

    private /* synthetic */ Duration(double value) {
        this.value = value;
    }

    public static final double unaryMinus-UwyO8pc(double $this) {
        return Duration.constructor-impl(-$this);
    }

    public static final double plus-LRDsOJo(double $this, double other) {
        return Duration.constructor-impl($this + other);
    }

    public static final double minus-LRDsOJo(double $this, double other) {
        return Duration.constructor-impl($this - other);
    }

    public static final double times-UwyO8pc(double $this, int scale) {
        return Duration.constructor-impl($this * (double)scale);
    }

    public static final double times-UwyO8pc(double $this, double scale) {
        return Duration.constructor-impl($this * scale);
    }

    public static final double div-UwyO8pc(double $this, int scale) {
        return Duration.constructor-impl($this / (double)scale);
    }

    public static final double div-UwyO8pc(double $this, double scale) {
        return Duration.constructor-impl($this / scale);
    }

    public static final double div-LRDsOJo(double $this, double other) {
        return $this / other;
    }

    public static final boolean isNegative-impl(double $this) {
        return $this < 0.0;
    }

    public static final boolean isPositive-impl(double $this) {
        return $this > 0.0;
    }

    public static final boolean isInfinite-impl(double $this) {
        double d = $this;
        boolean bl = false;
        return Double.isInfinite(d);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static final boolean isFinite-impl(double $this) {
        double d = $this;
        boolean bl = false;
        double d2 = d;
        boolean bl2 = false;
        if (Double.isInfinite(d2)) return false;
        d2 = d;
        bl2 = false;
        if (Double.isNaN(d2)) return false;
        return true;
    }

    public static final double getAbsoluteValue-UwyO8pc(double $this) {
        return Duration.isNegative-impl($this) ? Duration.unaryMinus-UwyO8pc($this) : $this;
    }

    public static int compareTo-LRDsOJo(double $this, double other) {
        return Double.compare($this, other);
    }

    public static final <T> T toComponents-impl(double $this, @NotNull Function5<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        int $i$f$toComponents = 0;
        Intrinsics.checkNotNullParameter(action, "action");
        boolean bl = false;
        return action.invoke((int)Duration.getInDays-impl($this), Duration.getHoursComponent-impl($this), Duration.getMinutesComponent-impl($this), Duration.getSecondsComponent-impl($this), Duration.getNanosecondsComponent-impl($this));
    }

    public static final <T> T toComponents-impl(double $this, @NotNull Function4<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        int $i$f$toComponents = 0;
        Intrinsics.checkNotNullParameter(action, "action");
        boolean bl = false;
        return action.invoke((int)Duration.getInHours-impl($this), Duration.getMinutesComponent-impl($this), Duration.getSecondsComponent-impl($this), Duration.getNanosecondsComponent-impl($this));
    }

    public static final <T> T toComponents-impl(double $this, @NotNull Function3<? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        int $i$f$toComponents = 0;
        Intrinsics.checkNotNullParameter(action, "action");
        boolean bl = false;
        return action.invoke((int)Duration.getInMinutes-impl($this), Duration.getSecondsComponent-impl($this), Duration.getNanosecondsComponent-impl($this));
    }

    public static final <T> T toComponents-impl(double $this, @NotNull Function2<? super Long, ? super Integer, ? extends T> action) {
        int $i$f$toComponents = 0;
        Intrinsics.checkNotNullParameter(action, "action");
        boolean bl = false;
        return action.invoke((long)Duration.getInSeconds-impl($this), Duration.getNanosecondsComponent-impl($this));
    }

    public static final int getHoursComponent-impl(double $this) {
        return (int)(Duration.getInHours-impl($this) % (double)24);
    }

    public static final int getMinutesComponent-impl(double $this) {
        return (int)(Duration.getInMinutes-impl($this) % (double)60);
    }

    public static final int getSecondsComponent-impl(double $this) {
        return (int)(Duration.getInSeconds-impl($this) % (double)60);
    }

    public static final int getNanosecondsComponent-impl(double $this) {
        return (int)(Duration.getInNanoseconds-impl($this) % 1.0E9);
    }

    public static final double toDouble-impl(double $this, @NotNull TimeUnit unit) {
        Intrinsics.checkNotNullParameter((Object)unit, "unit");
        return DurationUnitKt.convertDurationUnit($this, DurationKt.access$getStorageUnit$p(), unit);
    }

    public static final long toLong-impl(double $this, @NotNull TimeUnit unit) {
        Intrinsics.checkNotNullParameter((Object)unit, "unit");
        return (long)Duration.toDouble-impl($this, unit);
    }

    public static final int toInt-impl(double $this, @NotNull TimeUnit unit) {
        Intrinsics.checkNotNullParameter((Object)unit, "unit");
        return (int)Duration.toDouble-impl($this, unit);
    }

    public static final double getInDays-impl(double $this) {
        return Duration.toDouble-impl($this, TimeUnit.DAYS);
    }

    public static final double getInHours-impl(double $this) {
        return Duration.toDouble-impl($this, TimeUnit.HOURS);
    }

    public static final double getInMinutes-impl(double $this) {
        return Duration.toDouble-impl($this, TimeUnit.MINUTES);
    }

    public static final double getInSeconds-impl(double $this) {
        return Duration.toDouble-impl($this, TimeUnit.SECONDS);
    }

    public static final double getInMilliseconds-impl(double $this) {
        return Duration.toDouble-impl($this, TimeUnit.MILLISECONDS);
    }

    public static final double getInMicroseconds-impl(double $this) {
        return Duration.toDouble-impl($this, TimeUnit.MICROSECONDS);
    }

    public static final double getInNanoseconds-impl(double $this) {
        return Duration.toDouble-impl($this, TimeUnit.NANOSECONDS);
    }

    public static final long toLongNanoseconds-impl(double $this) {
        return Duration.toLong-impl($this, TimeUnit.NANOSECONDS);
    }

    public static final long toLongMilliseconds-impl(double $this) {
        return Duration.toLong-impl($this, TimeUnit.MILLISECONDS);
    }

    @NotNull
    public static String toString-impl(double $this) {
        String string;
        if (Duration.isInfinite-impl($this)) {
            string = String.valueOf($this);
        } else if ($this == 0.0) {
            string = "0s";
        } else {
            String string2;
            TimeUnit timeUnit;
            TimeUnit it;
            boolean bl;
            boolean bl2;
            TimeUnit timeUnit2;
            double absNs = Duration.getInNanoseconds-impl(Duration.getAbsoluteValue-UwyO8pc($this));
            boolean scientific = false;
            int maxDecimals = 0;
            if (absNs < 1.0E-6) {
                timeUnit2 = TimeUnit.SECONDS;
                bl2 = false;
                bl = false;
                it = timeUnit2;
                boolean bl3 = false;
                scientific = true;
                timeUnit = timeUnit2;
            } else if (absNs < 1.0) {
                timeUnit2 = TimeUnit.NANOSECONDS;
                bl2 = false;
                bl = false;
                it = timeUnit2;
                boolean bl4 = false;
                maxDecimals = 7;
                timeUnit = timeUnit2;
            } else if (absNs < 1000.0) {
                timeUnit = TimeUnit.NANOSECONDS;
            } else if (absNs < 1000000.0) {
                timeUnit = TimeUnit.MICROSECONDS;
            } else if (absNs < 1.0E9) {
                timeUnit = TimeUnit.MILLISECONDS;
            } else if (absNs < 1.0E12) {
                timeUnit = TimeUnit.SECONDS;
            } else if (absNs < 6.0E13) {
                timeUnit = TimeUnit.MINUTES;
            } else if (absNs < 3.6E15) {
                timeUnit = TimeUnit.HOURS;
            } else if (absNs < 8.64E20) {
                timeUnit = TimeUnit.DAYS;
            } else {
                timeUnit2 = TimeUnit.DAYS;
                bl2 = false;
                bl = false;
                it = timeUnit2;
                boolean bl5 = false;
                scientific = true;
                timeUnit = timeUnit2;
            }
            TimeUnit unit = timeUnit;
            double value = Duration.toDouble-impl($this, unit);
            StringBuilder stringBuilder = new StringBuilder();
            if (scientific) {
                string2 = FormatToDecimalsKt.formatScientific(value);
            } else if (maxDecimals > 0) {
                string2 = FormatToDecimalsKt.formatUpToDecimals(value, maxDecimals);
            } else {
                bl = false;
                string2 = FormatToDecimalsKt.formatToExactDecimals(value, Duration.precision-impl($this, Math.abs(value)));
            }
            string = stringBuilder.append(string2).append(DurationUnitKt.shortName(unit)).toString();
        }
        return string;
    }

    private static final int precision-impl(double $this, double value) {
        return value < 1.0 ? 3 : (value < (double)10 ? 2 : (value < (double)100 ? 1 : 0));
    }

    @NotNull
    public static final String toString-impl(double $this, @NotNull TimeUnit unit, int decimals) {
        Intrinsics.checkNotNullParameter((Object)unit, "unit");
        boolean bl = decimals >= 0;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "decimals must be not negative, but was " + decimals;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        if (Duration.isInfinite-impl($this)) {
            return String.valueOf($this);
        }
        double number = Duration.toDouble-impl($this, unit);
        bl3 = false;
        return (Math.abs(number) < 1.0E14 ? FormatToDecimalsKt.formatToExactDecimals(number, RangesKt.coerceAtMost(decimals, 12)) : FormatToDecimalsKt.formatScientific(number)) + DurationUnitKt.shortName(unit);
    }

    public static /* synthetic */ String toString-impl$default(double d, TimeUnit timeUnit, int n, int n2, Object object) {
        if ((n2 & 2) != 0) {
            n = 0;
        }
        return Duration.toString-impl(d, timeUnit, n);
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final String toIsoString-impl(double $this) {
        void minutes;
        boolean hasMinutes;
        void nanoseconds;
        void seconds;
        boolean bl = false;
        boolean bl2 = false;
        StringBuilder stringBuilder = new StringBuilder();
        boolean bl3 = false;
        boolean bl4 = false;
        StringBuilder $this$buildString = stringBuilder;
        boolean bl5 = false;
        if (Duration.isNegative-impl($this)) {
            $this$buildString.append('-');
        }
        $this$buildString.append("PT");
        double $this$iv = Duration.getAbsoluteValue-UwyO8pc($this);
        boolean $i$f$toComponents = false;
        boolean bl6 = false;
        int n = Duration.getNanosecondsComponent-impl($this$iv);
        int n2 = Duration.getSecondsComponent-impl($this$iv);
        int n3 = Duration.getMinutesComponent-impl($this$iv);
        int hours = (int)Duration.getInHours-impl($this$iv);
        boolean bl7 = false;
        boolean hasHours = hours != 0;
        boolean hasSeconds = seconds != false || nanoseconds != false;
        boolean bl8 = hasMinutes = minutes != false || hasSeconds && hasHours;
        if (hasHours) {
            $this$buildString.append(hours).append('H');
        }
        if (hasMinutes) {
            $this$buildString.append((int)minutes).append('M');
        }
        if (hasSeconds || !hasHours && !hasMinutes) {
            $this$buildString.append((int)seconds);
            if (nanoseconds != false) {
                $this$buildString.append('.');
                String nss = StringsKt.padStart(String.valueOf((int)nanoseconds), 9, '0');
                if (nanoseconds % 1000000 == false) {
                    StringBuilder stringBuilder2 = $this$buildString;
                    int n4 = 0;
                    int n5 = 3;
                    boolean bl9 = false;
                    Intrinsics.checkNotNullExpressionValue(stringBuilder2.append(nss, n4, n5), "this.append(value, startIndex, endIndex)");
                } else if (nanoseconds % 1000 == false) {
                    StringBuilder stringBuilder3 = $this$buildString;
                    int n6 = 0;
                    int n7 = 6;
                    boolean bl10 = false;
                    Intrinsics.checkNotNullExpressionValue(stringBuilder3.append(nss, n6, n7), "this.append(value, startIndex, endIndex)");
                } else {
                    $this$buildString.append(nss);
                }
            }
            $this$buildString.append('S');
        }
        String string = stringBuilder.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public static double constructor-impl(double value) {
        return value;
    }

    @NotNull
    public static final /* synthetic */ Duration box-impl(double v) {
        return new Duration(v);
    }

    public static int hashCode-impl(double d) {
        long l = Double.doubleToLongBits(d);
        return (int)(l ^ l >>> 32);
    }

    public static boolean equals-impl(double d, @Nullable Object object) {
        double d2;
        return object instanceof Duration && Double.compare(d, d2 = ((Duration)object).unbox-impl()) == 0;
    }

    public static final boolean equals-impl0(double p1, double p2) {
        return Double.compare(p1, p2) == 0;
    }

    public final /* synthetic */ double unbox-impl() {
        return this.value;
    }

    static {
        Companion = new Companion(null);
        ZERO = Duration.constructor-impl(0.0);
        INFINITE = Duration.constructor-impl(Double.POSITIVE_INFINITY);
    }

    public int hashCode() {
        return Duration.hashCode-impl(this.value);
    }

    public boolean equals(Object object) {
        return Duration.equals-impl(this.value, object);
    }

    /*
     * Illegal identifiers - consider using --renameillegalidents true
     */
    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J&\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\n\u0010\u0010\u001a\u00060\u000ej\u0002`\u000fR\u0019\u0010\u0003\u001a\u00020\u0004\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u0004\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006\u0011"}, d2={"Lkotlin/time/Duration$Companion;", "", "()V", "INFINITE", "Lkotlin/time/Duration;", "getINFINITE-UwyO8pc", "()D", "D", "ZERO", "getZERO-UwyO8pc", "convert", "", "value", "sourceUnit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "targetUnit", "kotlin-stdlib"})
    public static final class Companion {
        public final double getZERO-UwyO8pc() {
            return ZERO;
        }

        public final double getINFINITE-UwyO8pc() {
            return INFINITE;
        }

        public final double convert(double value, @NotNull TimeUnit sourceUnit, @NotNull TimeUnit targetUnit) {
            Intrinsics.checkNotNullParameter((Object)sourceUnit, "sourceUnit");
            Intrinsics.checkNotNullParameter((Object)targetUnit, "targetUnit");
            return DurationUnitKt.convertDurationUnit(value, sourceUnit, targetUnit);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

