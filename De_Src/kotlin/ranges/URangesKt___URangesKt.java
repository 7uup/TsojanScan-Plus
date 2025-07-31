/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.ExperimentalStdlibApi;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.UnsignedKt;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.random.URandomKt;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.RangesKt;
import kotlin.ranges.UIntProgression;
import kotlin.ranges.UIntRange;
import kotlin.ranges.ULongProgression;
import kotlin.ranges.ULongRange;
import kotlin.ranges.URangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\n\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0003\u0010\u0004\u001a\u001e\u0010\u0000\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0005H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007\u001a\u001e\u0010\u0000\u001a\u00020\b*\u00020\b2\u0006\u0010\u0002\u001a\u00020\bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\n\u001a\u001e\u0010\u0000\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u000bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\f\u0010\r\u001a\u001e\u0010\u000e\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0010\u0010\u0004\u001a\u001e\u0010\u000e\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\u0007\u001a\u001e\u0010\u000e\u001a\u00020\b*\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0012\u0010\n\u001a\u001e\u0010\u000e\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0013\u0010\r\u001a&\u0010\u0014\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0015\u0010\u0016\u001a&\u0010\u0014\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0018\u001a$\u0010\u0014\u001a\u00020\u0005*\u00020\u00052\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u001aH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001b\u0010\u001c\u001a&\u0010\u0014\u001a\u00020\b*\u00020\b2\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001d\u0010\u001e\u001a$\u0010\u0014\u001a\u00020\b*\u00020\b2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u001aH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001f\u0010 \u001a&\u0010\u0014\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b!\u0010\"\u001a\u001f\u0010#\u001a\u00020$*\u00020%2\u0006\u0010&\u001a\u00020\u0001H\u0087\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b'\u0010(\u001a\u001f\u0010#\u001a\u00020$*\u00020%2\b\u0010)\u001a\u0004\u0018\u00010\u0005H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0002\b*\u001a\u001f\u0010#\u001a\u00020$*\u00020%2\u0006\u0010&\u001a\u00020\bH\u0087\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b+\u0010,\u001a\u001f\u0010#\u001a\u00020$*\u00020%2\u0006\u0010&\u001a\u00020\u000bH\u0087\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b-\u0010.\u001a\u001f\u0010#\u001a\u00020$*\u00020/2\u0006\u0010&\u001a\u00020\u0001H\u0087\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b0\u00101\u001a\u001f\u0010#\u001a\u00020$*\u00020/2\u0006\u0010&\u001a\u00020\u0005H\u0087\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b2\u00103\u001a\u001f\u0010#\u001a\u00020$*\u00020/2\b\u0010)\u001a\u0004\u0018\u00010\bH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0002\b4\u001a\u001f\u0010#\u001a\u00020$*\u00020/2\u0006\u0010&\u001a\u00020\u000bH\u0087\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b5\u00106\u001a\u001f\u00107\u001a\u000208*\u00020\u00012\u0006\u00109\u001a\u00020\u0001H\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\b:\u0010;\u001a\u001f\u00107\u001a\u000208*\u00020\u00052\u0006\u00109\u001a\u00020\u0005H\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\b<\u0010=\u001a\u001f\u00107\u001a\u00020>*\u00020\b2\u0006\u00109\u001a\u00020\bH\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\b?\u0010@\u001a\u001f\u00107\u001a\u000208*\u00020\u000b2\u0006\u00109\u001a\u00020\u000bH\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\bA\u0010B\u001a\u0015\u0010C\u001a\u00020\u0005*\u00020%H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010D\u001a\u001c\u0010C\u001a\u00020\u0005*\u00020%2\u0006\u0010C\u001a\u00020EH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010F\u001a\u0015\u0010C\u001a\u00020\b*\u00020/H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010G\u001a\u001c\u0010C\u001a\u00020\b*\u00020/2\u0006\u0010C\u001a\u00020EH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010H\u001a\u0012\u0010I\u001a\u0004\u0018\u00010\u0005*\u00020%H\u0087\b\u00f8\u0001\u0000\u001a\u0019\u0010I\u001a\u0004\u0018\u00010\u0005*\u00020%2\u0006\u0010C\u001a\u00020EH\u0007\u00f8\u0001\u0000\u001a\u0012\u0010I\u001a\u0004\u0018\u00010\b*\u00020/H\u0087\b\u00f8\u0001\u0000\u001a\u0019\u0010I\u001a\u0004\u0018\u00010\b*\u00020/2\u0006\u0010C\u001a\u00020EH\u0007\u00f8\u0001\u0000\u001a\f\u0010J\u001a\u000208*\u000208H\u0007\u001a\f\u0010J\u001a\u00020>*\u00020>H\u0007\u001a\u0015\u0010K\u001a\u000208*\u0002082\u0006\u0010K\u001a\u00020LH\u0087\u0004\u001a\u0015\u0010K\u001a\u00020>*\u00020>2\u0006\u0010K\u001a\u00020MH\u0087\u0004\u001a\u001f\u0010N\u001a\u00020%*\u00020\u00012\u0006\u00109\u001a\u00020\u0001H\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\bO\u0010P\u001a\u001f\u0010N\u001a\u00020%*\u00020\u00052\u0006\u00109\u001a\u00020\u0005H\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\bQ\u0010R\u001a\u001f\u0010N\u001a\u00020/*\u00020\b2\u0006\u00109\u001a\u00020\bH\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\bS\u0010T\u001a\u001f\u0010N\u001a\u00020%*\u00020\u000b2\u0006\u00109\u001a\u00020\u000bH\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\bU\u0010V\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006W"}, d2={"coerceAtLeast", "Lkotlin/UByte;", "minimumValue", "coerceAtLeast-Kr8caGY", "(BB)B", "Lkotlin/UInt;", "coerceAtLeast-J1ME1BU", "(II)I", "Lkotlin/ULong;", "coerceAtLeast-eb3DHEI", "(JJ)J", "Lkotlin/UShort;", "coerceAtLeast-5PvTz6A", "(SS)S", "coerceAtMost", "maximumValue", "coerceAtMost-Kr8caGY", "coerceAtMost-J1ME1BU", "coerceAtMost-eb3DHEI", "coerceAtMost-5PvTz6A", "coerceIn", "coerceIn-b33U2AM", "(BBB)B", "coerceIn-WZ9TVnA", "(III)I", "range", "Lkotlin/ranges/ClosedRange;", "coerceIn-wuiCnnA", "(ILkotlin/ranges/ClosedRange;)I", "coerceIn-sambcqE", "(JJJ)J", "coerceIn-JPwROB0", "(JLkotlin/ranges/ClosedRange;)J", "coerceIn-VKSA0NQ", "(SSS)S", "contains", "", "Lkotlin/ranges/UIntRange;", "value", "contains-68kG9v0", "(Lkotlin/ranges/UIntRange;B)Z", "element", "contains-biwQdVI", "contains-fz5IDCE", "(Lkotlin/ranges/UIntRange;J)Z", "contains-ZsK3CEQ", "(Lkotlin/ranges/UIntRange;S)Z", "Lkotlin/ranges/ULongRange;", "contains-ULb-yJY", "(Lkotlin/ranges/ULongRange;B)Z", "contains-Gab390E", "(Lkotlin/ranges/ULongRange;I)Z", "contains-GYNo2lE", "contains-uhHAxoY", "(Lkotlin/ranges/ULongRange;S)Z", "downTo", "Lkotlin/ranges/UIntProgression;", "to", "downTo-Kr8caGY", "(BB)Lkotlin/ranges/UIntProgression;", "downTo-J1ME1BU", "(II)Lkotlin/ranges/UIntProgression;", "Lkotlin/ranges/ULongProgression;", "downTo-eb3DHEI", "(JJ)Lkotlin/ranges/ULongProgression;", "downTo-5PvTz6A", "(SS)Lkotlin/ranges/UIntProgression;", "random", "(Lkotlin/ranges/UIntRange;)I", "Lkotlin/random/Random;", "(Lkotlin/ranges/UIntRange;Lkotlin/random/Random;)I", "(Lkotlin/ranges/ULongRange;)J", "(Lkotlin/ranges/ULongRange;Lkotlin/random/Random;)J", "randomOrNull", "reversed", "step", "", "", "until", "until-Kr8caGY", "(BB)Lkotlin/ranges/UIntRange;", "until-J1ME1BU", "(II)Lkotlin/ranges/UIntRange;", "until-eb3DHEI", "(JJ)Lkotlin/ranges/ULongRange;", "until-5PvTz6A", "(SS)Lkotlin/ranges/UIntRange;", "kotlin-stdlib"}, xs="kotlin/ranges/URangesKt")
class URangesKt___URangesKt {
    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int random(UIntRange $this$random) {
        int $i$f$random = 0;
        return URangesKt.random($this$random, (Random)Random.Default);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final long random(ULongRange $this$random) {
        int $i$f$random = 0;
        return URangesKt.random($this$random, (Random)Random.Default);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final int random(@NotNull UIntRange $this$random, @NotNull Random random) {
        Intrinsics.checkNotNullParameter($this$random, "$this$random");
        Intrinsics.checkNotNullParameter(random, "random");
        try {
            return URandomKt.nextUInt(random, $this$random);
        } catch (IllegalArgumentException e) {
            throw (Throwable)new NoSuchElementException(e.getMessage());
        }
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final long random(@NotNull ULongRange $this$random, @NotNull Random random) {
        Intrinsics.checkNotNullParameter($this$random, "$this$random");
        Intrinsics.checkNotNullParameter(random, "random");
        try {
            return URandomKt.nextULong(random, $this$random);
        } catch (IllegalArgumentException e) {
            throw (Throwable)new NoSuchElementException(e.getMessage());
        }
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final UInt randomOrNull(UIntRange $this$randomOrNull) {
        int $i$f$randomOrNull = 0;
        return URangesKt.randomOrNull($this$randomOrNull, (Random)Random.Default);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final ULong randomOrNull(ULongRange $this$randomOrNull) {
        int $i$f$randomOrNull = 0;
        return URangesKt.randomOrNull($this$randomOrNull, (Random)Random.Default);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @ExperimentalUnsignedTypes
    @Nullable
    public static final UInt randomOrNull(@NotNull UIntRange $this$randomOrNull, @NotNull Random random) {
        Intrinsics.checkNotNullParameter($this$randomOrNull, "$this$randomOrNull");
        Intrinsics.checkNotNullParameter(random, "random");
        if ($this$randomOrNull.isEmpty()) {
            return null;
        }
        return UInt.box-impl(URandomKt.nextUInt(random, $this$randomOrNull));
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @ExperimentalUnsignedTypes
    @Nullable
    public static final ULong randomOrNull(@NotNull ULongRange $this$randomOrNull, @NotNull Random random) {
        Intrinsics.checkNotNullParameter($this$randomOrNull, "$this$randomOrNull");
        Intrinsics.checkNotNullParameter(random, "random");
        if ($this$randomOrNull.isEmpty()) {
            return null;
        }
        return ULong.box-impl(URandomKt.nextULong(random, $this$randomOrNull));
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final boolean contains-biwQdVI(UIntRange $this$contains, UInt element) {
        int n = 0;
        Intrinsics.checkNotNullParameter($this$contains, "$this$contains");
        return element != null && $this$contains.contains-WZ4Q5Ns(element.unbox-impl());
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final boolean contains-GYNo2lE(ULongRange $this$contains, ULong element) {
        int n = 0;
        Intrinsics.checkNotNullParameter($this$contains, "$this$contains");
        return element != null && $this$contains.contains-VKZWuLQ(element.unbox-impl());
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final boolean contains-68kG9v0(@NotNull UIntRange $this$contains, byte value) {
        Intrinsics.checkNotNullParameter($this$contains, "$this$contains");
        byte by = value;
        boolean bl = false;
        return $this$contains.contains-WZ4Q5Ns(UInt.constructor-impl(by & 0xFF));
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final boolean contains-ULb-yJY(@NotNull ULongRange $this$contains, byte value) {
        Intrinsics.checkNotNullParameter($this$contains, "$this$contains");
        byte by = value;
        boolean bl = false;
        return $this$contains.contains-VKZWuLQ(ULong.constructor-impl((long)by & 0xFFL));
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final boolean contains-Gab390E(@NotNull ULongRange $this$contains, int value) {
        Intrinsics.checkNotNullParameter($this$contains, "$this$contains");
        int n = value;
        boolean bl = false;
        return $this$contains.contains-VKZWuLQ(ULong.constructor-impl((long)n & 0xFFFFFFFFL));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final boolean contains-fz5IDCE(@NotNull UIntRange $this$contains, long value) {
        Intrinsics.checkNotNullParameter($this$contains, "$this$contains");
        long l = value;
        int n = 32;
        boolean bl = false;
        if (ULong.constructor-impl(l >>> n) != 0L) return false;
        l = value;
        n = 0;
        long l2 = l;
        boolean bl2 = false;
        if (!$this$contains.contains-WZ4Q5Ns(UInt.constructor-impl((int)l2))) return false;
        return true;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final boolean contains-ZsK3CEQ(@NotNull UIntRange $this$contains, short value) {
        Intrinsics.checkNotNullParameter($this$contains, "$this$contains");
        short s2 = value;
        boolean bl = false;
        return $this$contains.contains-WZ4Q5Ns(UInt.constructor-impl(s2 & 0xFFFF));
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final boolean contains-uhHAxoY(@NotNull ULongRange $this$contains, short value) {
        Intrinsics.checkNotNullParameter($this$contains, "$this$contains");
        short s2 = value;
        boolean bl = false;
        return $this$contains.contains-VKZWuLQ(ULong.constructor-impl((long)s2 & 0xFFFFL));
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final UIntProgression downTo-Kr8caGY(byte $this$downTo, byte to) {
        byte by = $this$downTo;
        boolean bl = false;
        int n = UInt.constructor-impl(by & 0xFF);
        by = to;
        bl = false;
        return UIntProgression.Companion.fromClosedRange-Nkh28Cs(n, UInt.constructor-impl(by & 0xFF), -1);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final UIntProgression downTo-J1ME1BU(int $this$downTo, int to) {
        return UIntProgression.Companion.fromClosedRange-Nkh28Cs($this$downTo, to, -1);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final ULongProgression downTo-eb3DHEI(long $this$downTo, long to) {
        return ULongProgression.Companion.fromClosedRange-7ftBX0g($this$downTo, to, -1L);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final UIntProgression downTo-5PvTz6A(short $this$downTo, short to) {
        short s2 = $this$downTo;
        boolean bl = false;
        int n = UInt.constructor-impl(s2 & 0xFFFF);
        s2 = to;
        bl = false;
        return UIntProgression.Companion.fromClosedRange-Nkh28Cs(n, UInt.constructor-impl(s2 & 0xFFFF), -1);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final UIntProgression reversed(@NotNull UIntProgression $this$reversed) {
        Intrinsics.checkNotNullParameter($this$reversed, "$this$reversed");
        return UIntProgression.Companion.fromClosedRange-Nkh28Cs($this$reversed.getLast-pVg5ArA(), $this$reversed.getFirst-pVg5ArA(), -$this$reversed.getStep());
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final ULongProgression reversed(@NotNull ULongProgression $this$reversed) {
        Intrinsics.checkNotNullParameter($this$reversed, "$this$reversed");
        return ULongProgression.Companion.fromClosedRange-7ftBX0g($this$reversed.getLast-s-VKNKU(), $this$reversed.getFirst-s-VKNKU(), -$this$reversed.getStep());
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final UIntProgression step(@NotNull UIntProgression $this$step, int step) {
        Intrinsics.checkNotNullParameter($this$step, "$this$step");
        RangesKt.checkStepIsPositive(step > 0, step);
        return UIntProgression.Companion.fromClosedRange-Nkh28Cs($this$step.getFirst-pVg5ArA(), $this$step.getLast-pVg5ArA(), $this$step.getStep() > 0 ? step : -step);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final ULongProgression step(@NotNull ULongProgression $this$step, long step) {
        Intrinsics.checkNotNullParameter($this$step, "$this$step");
        RangesKt.checkStepIsPositive(step > 0L, step);
        return ULongProgression.Companion.fromClosedRange-7ftBX0g($this$step.getFirst-s-VKNKU(), $this$step.getLast-s-VKNKU(), $this$step.getStep() > 0L ? step : -step);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final UIntRange until-Kr8caGY(byte $this$until, byte to) {
        int n = to;
        int n2 = 0;
        int n3 = 0;
        int n4 = n;
        int n5 = 0;
        int n6 = n4 & 0xFF;
        n4 = n2;
        n5 = 0;
        if (Intrinsics.compare(n6, n4 & 0xFF) <= 0) {
            return UIntRange.Companion.getEMPTY();
        }
        n = $this$until;
        n2 = 0;
        n = UInt.constructor-impl(n & 0xFF);
        n2 = to;
        n3 = 1;
        n4 = 0;
        n5 = n2;
        boolean bl = false;
        n5 = UInt.constructor-impl(n5 & 0xFF);
        bl = false;
        n2 = UInt.constructor-impl(n5 - n3);
        n3 = 0;
        n3 = 0;
        return new UIntRange(n, n2, null);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final UIntRange until-J1ME1BU(int $this$until, int to) {
        int n = to;
        int n2 = 0;
        int n3 = 0;
        if (UnsignedKt.uintCompare(n, n2) <= 0) {
            return UIntRange.Companion.getEMPTY();
        }
        n = $this$until;
        n2 = to;
        n3 = 1;
        boolean bl = false;
        n2 = UInt.constructor-impl(n2 - n3);
        n3 = 0;
        n3 = 0;
        return new UIntRange(n, n2, null);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final ULongRange until-eb3DHEI(long $this$until, long to) {
        long l = to;
        long l2 = 0L;
        int n = 0;
        if (UnsignedKt.ulongCompare(l, l2) <= 0) {
            return ULongRange.Companion.getEMPTY();
        }
        l = $this$until;
        l2 = to;
        n = 1;
        boolean bl = false;
        long l3 = l2;
        int n2 = n;
        boolean bl2 = false;
        long l4 = ULong.constructor-impl((long)n2 & 0xFFFFFFFFL);
        boolean bl3 = false;
        l2 = ULong.constructor-impl(l3 - l4);
        n = 0;
        n = 0;
        return new ULongRange(l, l2, null);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final UIntRange until-5PvTz6A(short $this$until, short to) {
        int n = to;
        int n2 = 0;
        int n3 = 0;
        int n4 = n;
        int n5 = 0;
        int n6 = n4 & 0xFFFF;
        n4 = n2;
        n5 = 0;
        if (Intrinsics.compare(n6, n4 & 0xFFFF) <= 0) {
            return UIntRange.Companion.getEMPTY();
        }
        n = $this$until;
        n2 = 0;
        n = UInt.constructor-impl(n & 0xFFFF);
        n2 = to;
        n3 = 1;
        n4 = 0;
        n5 = n2;
        boolean bl = false;
        n5 = UInt.constructor-impl(n5 & 0xFFFF);
        bl = false;
        n2 = UInt.constructor-impl(n5 - n3);
        n3 = 0;
        n3 = 0;
        return new UIntRange(n, n2, null);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final int coerceAtLeast-J1ME1BU(int $this$coerceAtLeast, int minimumValue) {
        int n = $this$coerceAtLeast;
        boolean bl = false;
        return UnsignedKt.uintCompare(n, minimumValue) < 0 ? minimumValue : $this$coerceAtLeast;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final long coerceAtLeast-eb3DHEI(long $this$coerceAtLeast, long minimumValue) {
        long l = $this$coerceAtLeast;
        boolean bl = false;
        return UnsignedKt.ulongCompare(l, minimumValue) < 0 ? minimumValue : $this$coerceAtLeast;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final byte coerceAtLeast-Kr8caGY(byte $this$coerceAtLeast, byte minimumValue) {
        byte by = $this$coerceAtLeast;
        boolean bl = false;
        byte by2 = by;
        boolean bl2 = false;
        int n = by2 & 0xFF;
        by2 = minimumValue;
        bl2 = false;
        return Intrinsics.compare(n, by2 & 0xFF) < 0 ? minimumValue : $this$coerceAtLeast;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final short coerceAtLeast-5PvTz6A(short $this$coerceAtLeast, short minimumValue) {
        short s2 = $this$coerceAtLeast;
        boolean bl = false;
        short s3 = s2;
        boolean bl2 = false;
        int n = s3 & 0xFFFF;
        s3 = minimumValue;
        bl2 = false;
        return Intrinsics.compare(n, s3 & 0xFFFF) < 0 ? minimumValue : $this$coerceAtLeast;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final int coerceAtMost-J1ME1BU(int $this$coerceAtMost, int maximumValue) {
        int n = $this$coerceAtMost;
        boolean bl = false;
        return UnsignedKt.uintCompare(n, maximumValue) > 0 ? maximumValue : $this$coerceAtMost;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final long coerceAtMost-eb3DHEI(long $this$coerceAtMost, long maximumValue) {
        long l = $this$coerceAtMost;
        boolean bl = false;
        return UnsignedKt.ulongCompare(l, maximumValue) > 0 ? maximumValue : $this$coerceAtMost;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final byte coerceAtMost-Kr8caGY(byte $this$coerceAtMost, byte maximumValue) {
        byte by = $this$coerceAtMost;
        boolean bl = false;
        byte by2 = by;
        boolean bl2 = false;
        int n = by2 & 0xFF;
        by2 = maximumValue;
        bl2 = false;
        return Intrinsics.compare(n, by2 & 0xFF) > 0 ? maximumValue : $this$coerceAtMost;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final short coerceAtMost-5PvTz6A(short $this$coerceAtMost, short maximumValue) {
        short s2 = $this$coerceAtMost;
        boolean bl = false;
        short s3 = s2;
        boolean bl2 = false;
        int n = s3 & 0xFFFF;
        s3 = maximumValue;
        bl2 = false;
        return Intrinsics.compare(n, s3 & 0xFFFF) > 0 ? maximumValue : $this$coerceAtMost;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final int coerceIn-WZ9TVnA(int $this$coerceIn, int minimumValue, int maximumValue) {
        int n = minimumValue;
        boolean bl = false;
        if (UnsignedKt.uintCompare(n, maximumValue) > 0) {
            throw (Throwable)new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + UInt.toString-impl(maximumValue) + " is less than minimum " + UInt.toString-impl(minimumValue) + '.');
        }
        n = $this$coerceIn;
        bl = false;
        if (UnsignedKt.uintCompare(n, minimumValue) < 0) {
            return minimumValue;
        }
        n = $this$coerceIn;
        bl = false;
        if (UnsignedKt.uintCompare(n, maximumValue) > 0) {
            return maximumValue;
        }
        return $this$coerceIn;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final long coerceIn-sambcqE(long $this$coerceIn, long minimumValue, long maximumValue) {
        long l = minimumValue;
        boolean bl = false;
        if (UnsignedKt.ulongCompare(l, maximumValue) > 0) {
            throw (Throwable)new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + ULong.toString-impl(maximumValue) + " is less than minimum " + ULong.toString-impl(minimumValue) + '.');
        }
        l = $this$coerceIn;
        bl = false;
        if (UnsignedKt.ulongCompare(l, minimumValue) < 0) {
            return minimumValue;
        }
        l = $this$coerceIn;
        bl = false;
        if (UnsignedKt.ulongCompare(l, maximumValue) > 0) {
            return maximumValue;
        }
        return $this$coerceIn;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final byte coerceIn-b33U2AM(byte $this$coerceIn, byte minimumValue, byte maximumValue) {
        byte by = minimumValue;
        boolean bl = false;
        byte by2 = by;
        boolean bl2 = false;
        int n = by2 & 0xFF;
        by2 = maximumValue;
        bl2 = false;
        if (Intrinsics.compare(n, by2 & 0xFF) > 0) {
            throw (Throwable)new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + UByte.toString-impl(maximumValue) + " is less than minimum " + UByte.toString-impl(minimumValue) + '.');
        }
        by = $this$coerceIn;
        bl = false;
        by2 = by;
        bl2 = false;
        int n2 = by2 & 0xFF;
        by2 = minimumValue;
        bl2 = false;
        if (Intrinsics.compare(n2, by2 & 0xFF) < 0) {
            return minimumValue;
        }
        by = $this$coerceIn;
        bl = false;
        by2 = by;
        bl2 = false;
        int n3 = by2 & 0xFF;
        by2 = maximumValue;
        bl2 = false;
        if (Intrinsics.compare(n3, by2 & 0xFF) > 0) {
            return maximumValue;
        }
        return $this$coerceIn;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final short coerceIn-VKSA0NQ(short $this$coerceIn, short minimumValue, short maximumValue) {
        short s2 = minimumValue;
        boolean bl = false;
        short s3 = s2;
        boolean bl2 = false;
        int n = s3 & 0xFFFF;
        s3 = maximumValue;
        bl2 = false;
        if (Intrinsics.compare(n, s3 & 0xFFFF) > 0) {
            throw (Throwable)new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + UShort.toString-impl(maximumValue) + " is less than minimum " + UShort.toString-impl(minimumValue) + '.');
        }
        s2 = $this$coerceIn;
        bl = false;
        s3 = s2;
        bl2 = false;
        int n2 = s3 & 0xFFFF;
        s3 = minimumValue;
        bl2 = false;
        if (Intrinsics.compare(n2, s3 & 0xFFFF) < 0) {
            return minimumValue;
        }
        s2 = $this$coerceIn;
        bl = false;
        s3 = s2;
        bl2 = false;
        int n3 = s3 & 0xFFFF;
        s3 = maximumValue;
        bl2 = false;
        if (Intrinsics.compare(n3, s3 & 0xFFFF) > 0) {
            return maximumValue;
        }
        return $this$coerceIn;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final int coerceIn-wuiCnnA(int $this$coerceIn, @NotNull ClosedRange<UInt> range) {
        int n;
        Intrinsics.checkNotNullParameter(range, "range");
        if (range instanceof ClosedFloatingPointRange) {
            return RangesKt.coerceIn(UInt.box-impl($this$coerceIn), (ClosedFloatingPointRange)range).unbox-impl();
        }
        if (range.isEmpty()) {
            throw (Throwable)new IllegalArgumentException("Cannot coerce value to an empty range: " + range + '.');
        }
        int n2 = $this$coerceIn;
        int n3 = range.getStart().unbox-impl();
        boolean bl = false;
        if (UnsignedKt.uintCompare(n2, n3) < 0) {
            n = range.getStart().unbox-impl();
        } else {
            n2 = $this$coerceIn;
            n3 = range.getEndInclusive().unbox-impl();
            bl = false;
            n = UnsignedKt.uintCompare(n2, n3) > 0 ? range.getEndInclusive().unbox-impl() : $this$coerceIn;
        }
        return n;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final long coerceIn-JPwROB0(long $this$coerceIn, @NotNull ClosedRange<ULong> range) {
        long l;
        Intrinsics.checkNotNullParameter(range, "range");
        if (range instanceof ClosedFloatingPointRange) {
            return RangesKt.coerceIn(ULong.box-impl($this$coerceIn), (ClosedFloatingPointRange)range).unbox-impl();
        }
        if (range.isEmpty()) {
            throw (Throwable)new IllegalArgumentException("Cannot coerce value to an empty range: " + range + '.');
        }
        long l2 = $this$coerceIn;
        long l3 = range.getStart().unbox-impl();
        boolean bl = false;
        if (UnsignedKt.ulongCompare(l2, l3) < 0) {
            l = range.getStart().unbox-impl();
        } else {
            l2 = $this$coerceIn;
            l3 = range.getEndInclusive().unbox-impl();
            bl = false;
            l = UnsignedKt.ulongCompare(l2, l3) > 0 ? range.getEndInclusive().unbox-impl() : $this$coerceIn;
        }
        return l;
    }
}

