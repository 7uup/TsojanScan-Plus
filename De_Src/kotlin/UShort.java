/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\n\n\u0002\b\t\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001fB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\f\u00f8\u0001\u0000\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u000eH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0011H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0014H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\u0097\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001c\u0010\u0010J\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001d\u0010\u0013J\u001b\u0010\u001b\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b \u0010\u0018J\u0013\u0010!\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010#H\u00d6\u0003J\t\u0010$\u001a\u00020\rH\u00d6\u0001J\u0016\u0010%\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b&\u0010\u0005J\u0016\u0010'\u001a\u00020\u0000H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b(\u0010\u0005J\u001b\u0010)\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b*\u0010\u0010J\u001b\u0010)\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b+\u0010\u0013J\u001b\u0010)\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b,\u0010\u001fJ\u001b\u0010)\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b-\u0010\u0018J\u001b\u0010.\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\f\u00f8\u0001\u0000\u00a2\u0006\u0004\b/\u0010\u000bJ\u001b\u00100\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b1\u0010\u0010J\u001b\u00100\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b2\u0010\u0013J\u001b\u00100\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b3\u0010\u001fJ\u001b\u00100\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b4\u0010\u0018J\u001b\u00105\u001a\u0002062\u0006\u0010\t\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b7\u00108J\u001b\u00109\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b:\u0010\u0010J\u001b\u00109\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b;\u0010\u0013J\u001b\u00109\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b<\u0010\u001fJ\u001b\u00109\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b=\u0010\u0018J\u001b\u0010>\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b?\u0010\u0010J\u001b\u0010>\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b@\u0010\u0013J\u001b\u0010>\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\bA\u0010\u001fJ\u001b\u0010>\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\bB\u0010\u0018J\u0010\u0010C\u001a\u00020DH\u0087\b\u00a2\u0006\u0004\bE\u0010FJ\u0010\u0010G\u001a\u00020HH\u0087\b\u00a2\u0006\u0004\bI\u0010JJ\u0010\u0010K\u001a\u00020LH\u0087\b\u00a2\u0006\u0004\bM\u0010NJ\u0010\u0010O\u001a\u00020\rH\u0087\b\u00a2\u0006\u0004\bP\u0010QJ\u0010\u0010R\u001a\u00020SH\u0087\b\u00a2\u0006\u0004\bT\u0010UJ\u0010\u0010V\u001a\u00020\u0003H\u0087\b\u00a2\u0006\u0004\bW\u0010\u0005J\u000f\u0010X\u001a\u00020YH\u0016\u00a2\u0006\u0004\bZ\u0010[J\u0016\u0010\\\u001a\u00020\u000eH\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b]\u0010FJ\u0016\u0010^\u001a\u00020\u0011H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b_\u0010QJ\u0016\u0010`\u001a\u00020\u0014H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\ba\u0010UJ\u0016\u0010b\u001a\u00020\u0000H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bc\u0010\u0005J\u001b\u0010d\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\f\u00f8\u0001\u0000\u00a2\u0006\u0004\be\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004\u00a2\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u00f8\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006g"}, d2={"Lkotlin/UShort;", "", "data", "", "constructor-impl", "(S)S", "getData$annotations", "()V", "and", "other", "and-xj2QHRw", "(SS)S", "compareTo", "", "Lkotlin/UByte;", "compareTo-7apg3OU", "(SB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(SI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(SJ)I", "compareTo-xj2QHRw", "(SS)I", "dec", "dec-Mh2AYeg", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(SJ)J", "div-xj2QHRw", "equals", "", "", "hashCode", "inc", "inc-Mh2AYeg", "inv", "inv-Mh2AYeg", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-xj2QHRw", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-xj2QHRw", "(SS)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(S)B", "toDouble", "", "toDouble-impl", "(S)D", "toFloat", "", "toFloat-impl", "(S)F", "toInt", "toInt-impl", "(S)I", "toLong", "", "toLong-impl", "(S)J", "toShort", "toShort-impl", "toString", "", "toString-impl", "(S)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-xj2QHRw", "Companion", "kotlin-stdlib"})
@SinceKotlin(version="1.3")
@ExperimentalUnsignedTypes
public final class UShort
implements Comparable<UShort> {
    private final short data;
    public static final short MIN_VALUE = 0;
    public static final short MAX_VALUE = -1;
    public static final int SIZE_BYTES = 2;
    public static final int SIZE_BITS = 16;
    public static final Companion Companion = new Companion(null);

    @InlineOnly
    private int compareTo-xj2QHRw(short s2) {
        return UShort.compareTo-xj2QHRw(this.data, s2);
    }

    @NotNull
    public String toString() {
        return UShort.toString-impl(this.data);
    }

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    @PublishedApi
    private /* synthetic */ UShort(short data) {
        this.data = data;
    }

    @InlineOnly
    private static final int compareTo-7apg3OU(short $this, byte other) {
        int n = 0;
        short s2 = $this;
        boolean bl = false;
        int n2 = s2 & 0xFFFF;
        s2 = other;
        bl = false;
        return Intrinsics.compare(n2, s2 & 0xFF);
    }

    @InlineOnly
    private static int compareTo-xj2QHRw(short $this, short other) {
        int n = 0;
        short s2 = $this;
        boolean bl = false;
        int n2 = s2 & 0xFFFF;
        s2 = other;
        bl = false;
        return Intrinsics.compare(n2, s2 & 0xFFFF);
    }

    @InlineOnly
    private static final int compareTo-WZ4Q5Ns(short $this, int other) {
        int n = 0;
        int n2 = $this;
        boolean bl = false;
        n2 = UInt.constructor-impl(n2 & 0xFFFF);
        bl = false;
        return UnsignedKt.uintCompare(n2, other);
    }

    @InlineOnly
    private static final int compareTo-VKZWuLQ(short $this, long other) {
        int n = 0;
        short s2 = $this;
        boolean bl = false;
        long l = ULong.constructor-impl((long)s2 & 0xFFFFL);
        boolean bl2 = false;
        return UnsignedKt.ulongCompare(l, other);
    }

    @InlineOnly
    private static final int plus-7apg3OU(short $this, byte other) {
        int n = 0;
        int n2 = $this;
        int n3 = 0;
        n2 = UInt.constructor-impl(n2 & 0xFFFF);
        n3 = other;
        boolean bl = false;
        n3 = UInt.constructor-impl(n3 & 0xFF);
        bl = false;
        return UInt.constructor-impl(n2 + n3);
    }

    @InlineOnly
    private static final int plus-xj2QHRw(short $this, short other) {
        int n = 0;
        int n2 = $this;
        int n3 = 0;
        n2 = UInt.constructor-impl(n2 & 0xFFFF);
        n3 = other;
        boolean bl = false;
        n3 = UInt.constructor-impl(n3 & 0xFFFF);
        bl = false;
        return UInt.constructor-impl(n2 + n3);
    }

    @InlineOnly
    private static final int plus-WZ4Q5Ns(short $this, int other) {
        int n = 0;
        int n2 = $this;
        boolean bl = false;
        n2 = UInt.constructor-impl(n2 & 0xFFFF);
        bl = false;
        return UInt.constructor-impl(n2 + other);
    }

    @InlineOnly
    private static final long plus-VKZWuLQ(short $this, long other) {
        int n = 0;
        short s2 = $this;
        boolean bl = false;
        long l = ULong.constructor-impl((long)s2 & 0xFFFFL);
        boolean bl2 = false;
        return ULong.constructor-impl(l + other);
    }

    @InlineOnly
    private static final int minus-7apg3OU(short $this, byte other) {
        int n = 0;
        int n2 = $this;
        int n3 = 0;
        n2 = UInt.constructor-impl(n2 & 0xFFFF);
        n3 = other;
        boolean bl = false;
        n3 = UInt.constructor-impl(n3 & 0xFF);
        bl = false;
        return UInt.constructor-impl(n2 - n3);
    }

    @InlineOnly
    private static final int minus-xj2QHRw(short $this, short other) {
        int n = 0;
        int n2 = $this;
        int n3 = 0;
        n2 = UInt.constructor-impl(n2 & 0xFFFF);
        n3 = other;
        boolean bl = false;
        n3 = UInt.constructor-impl(n3 & 0xFFFF);
        bl = false;
        return UInt.constructor-impl(n2 - n3);
    }

    @InlineOnly
    private static final int minus-WZ4Q5Ns(short $this, int other) {
        int n = 0;
        int n2 = $this;
        boolean bl = false;
        n2 = UInt.constructor-impl(n2 & 0xFFFF);
        bl = false;
        return UInt.constructor-impl(n2 - other);
    }

    @InlineOnly
    private static final long minus-VKZWuLQ(short $this, long other) {
        int n = 0;
        short s2 = $this;
        boolean bl = false;
        long l = ULong.constructor-impl((long)s2 & 0xFFFFL);
        boolean bl2 = false;
        return ULong.constructor-impl(l - other);
    }

    @InlineOnly
    private static final int times-7apg3OU(short $this, byte other) {
        int n = 0;
        int n2 = $this;
        int n3 = 0;
        n2 = UInt.constructor-impl(n2 & 0xFFFF);
        n3 = other;
        boolean bl = false;
        n3 = UInt.constructor-impl(n3 & 0xFF);
        bl = false;
        return UInt.constructor-impl(n2 * n3);
    }

    @InlineOnly
    private static final int times-xj2QHRw(short $this, short other) {
        int n = 0;
        int n2 = $this;
        int n3 = 0;
        n2 = UInt.constructor-impl(n2 & 0xFFFF);
        n3 = other;
        boolean bl = false;
        n3 = UInt.constructor-impl(n3 & 0xFFFF);
        bl = false;
        return UInt.constructor-impl(n2 * n3);
    }

    @InlineOnly
    private static final int times-WZ4Q5Ns(short $this, int other) {
        int n = 0;
        int n2 = $this;
        boolean bl = false;
        n2 = UInt.constructor-impl(n2 & 0xFFFF);
        bl = false;
        return UInt.constructor-impl(n2 * other);
    }

    @InlineOnly
    private static final long times-VKZWuLQ(short $this, long other) {
        int n = 0;
        short s2 = $this;
        boolean bl = false;
        long l = ULong.constructor-impl((long)s2 & 0xFFFFL);
        boolean bl2 = false;
        return ULong.constructor-impl(l * other);
    }

    @InlineOnly
    private static final int div-7apg3OU(short $this, byte other) {
        int n = 0;
        int n2 = $this;
        int n3 = 0;
        n2 = UInt.constructor-impl(n2 & 0xFFFF);
        n3 = other;
        boolean bl = false;
        n3 = UInt.constructor-impl(n3 & 0xFF);
        bl = false;
        return UnsignedKt.uintDivide-J1ME1BU(n2, n3);
    }

    @InlineOnly
    private static final int div-xj2QHRw(short $this, short other) {
        int n = 0;
        int n2 = $this;
        int n3 = 0;
        n2 = UInt.constructor-impl(n2 & 0xFFFF);
        n3 = other;
        boolean bl = false;
        n3 = UInt.constructor-impl(n3 & 0xFFFF);
        bl = false;
        return UnsignedKt.uintDivide-J1ME1BU(n2, n3);
    }

    @InlineOnly
    private static final int div-WZ4Q5Ns(short $this, int other) {
        int n = 0;
        int n2 = $this;
        boolean bl = false;
        n2 = UInt.constructor-impl(n2 & 0xFFFF);
        bl = false;
        return UnsignedKt.uintDivide-J1ME1BU(n2, other);
    }

    @InlineOnly
    private static final long div-VKZWuLQ(short $this, long other) {
        int n = 0;
        short s2 = $this;
        boolean bl = false;
        long l = ULong.constructor-impl((long)s2 & 0xFFFFL);
        boolean bl2 = false;
        return UnsignedKt.ulongDivide-eb3DHEI(l, other);
    }

    @InlineOnly
    private static final int rem-7apg3OU(short $this, byte other) {
        int n = 0;
        int n2 = $this;
        int n3 = 0;
        n2 = UInt.constructor-impl(n2 & 0xFFFF);
        n3 = other;
        boolean bl = false;
        n3 = UInt.constructor-impl(n3 & 0xFF);
        bl = false;
        return UnsignedKt.uintRemainder-J1ME1BU(n2, n3);
    }

    @InlineOnly
    private static final int rem-xj2QHRw(short $this, short other) {
        int n = 0;
        int n2 = $this;
        int n3 = 0;
        n2 = UInt.constructor-impl(n2 & 0xFFFF);
        n3 = other;
        boolean bl = false;
        n3 = UInt.constructor-impl(n3 & 0xFFFF);
        bl = false;
        return UnsignedKt.uintRemainder-J1ME1BU(n2, n3);
    }

    @InlineOnly
    private static final int rem-WZ4Q5Ns(short $this, int other) {
        int n = 0;
        int n2 = $this;
        boolean bl = false;
        n2 = UInt.constructor-impl(n2 & 0xFFFF);
        bl = false;
        return UnsignedKt.uintRemainder-J1ME1BU(n2, other);
    }

    @InlineOnly
    private static final long rem-VKZWuLQ(short $this, long other) {
        int n = 0;
        short s2 = $this;
        boolean bl = false;
        long l = ULong.constructor-impl((long)s2 & 0xFFFFL);
        boolean bl2 = false;
        return UnsignedKt.ulongRemainder-eb3DHEI(l, other);
    }

    @InlineOnly
    private static final short inc-Mh2AYeg(short $this) {
        int n = 0;
        return UShort.constructor-impl((short)($this + 1));
    }

    @InlineOnly
    private static final short dec-Mh2AYeg(short $this) {
        int n = 0;
        return UShort.constructor-impl((short)($this + -1));
    }

    @InlineOnly
    private static final UIntRange rangeTo-xj2QHRw(short $this, short other) {
        int n = 0;
        short s2 = $this;
        boolean bl = false;
        int n2 = UInt.constructor-impl(s2 & 0xFFFF);
        s2 = other;
        bl = false;
        return new UIntRange(n2, UInt.constructor-impl(s2 & 0xFFFF), null);
    }

    @InlineOnly
    private static final short and-xj2QHRw(short $this, short other) {
        int n = 0;
        short s2 = $this;
        short s3 = other;
        boolean bl = false;
        return UShort.constructor-impl((short)(s2 & s3));
    }

    @InlineOnly
    private static final short or-xj2QHRw(short $this, short other) {
        int n = 0;
        short s2 = $this;
        short s3 = other;
        boolean bl = false;
        return UShort.constructor-impl((short)(s2 | s3));
    }

    @InlineOnly
    private static final short xor-xj2QHRw(short $this, short other) {
        int n = 0;
        short s2 = $this;
        short s3 = other;
        boolean bl = false;
        return UShort.constructor-impl((short)(s2 ^ s3));
    }

    @InlineOnly
    private static final short inv-Mh2AYeg(short $this) {
        int n = 0;
        short s2 = $this;
        boolean bl = false;
        return UShort.constructor-impl(~s2);
    }

    @InlineOnly
    private static final byte toByte-impl(short $this) {
        int $i$f$toByte = 0;
        return (byte)$this;
    }

    @InlineOnly
    private static final short toShort-impl(short $this) {
        int $i$f$toShort = 0;
        return $this;
    }

    @InlineOnly
    private static final int toInt-impl(short $this) {
        int $i$f$toInt = 0;
        return $this & 0xFFFF;
    }

    @InlineOnly
    private static final long toLong-impl(short $this) {
        int $i$f$toLong = 0;
        return (long)$this & 0xFFFFL;
    }

    @InlineOnly
    private static final byte toUByte-w2LRezQ(short $this) {
        int n = 0;
        short s2 = $this;
        boolean bl = false;
        return UByte.constructor-impl((byte)s2);
    }

    @InlineOnly
    private static final short toUShort-Mh2AYeg(short $this) {
        int n = 0;
        return $this;
    }

    @InlineOnly
    private static final int toUInt-pVg5ArA(short $this) {
        int n = 0;
        return UInt.constructor-impl($this & 0xFFFF);
    }

    @InlineOnly
    private static final long toULong-s-VKNKU(short $this) {
        int n = 0;
        return ULong.constructor-impl((long)$this & 0xFFFFL);
    }

    @InlineOnly
    private static final float toFloat-impl(short $this) {
        int $i$f$toFloat = 0;
        short s2 = $this;
        boolean bl = false;
        return s2 & 0xFFFF;
    }

    @InlineOnly
    private static final double toDouble-impl(short $this) {
        int $i$f$toDouble = 0;
        short s2 = $this;
        boolean bl = false;
        return s2 & 0xFFFF;
    }

    @NotNull
    public static String toString-impl(short $this) {
        short s2 = $this;
        boolean bl = false;
        return String.valueOf(s2 & 0xFFFF);
    }

    @PublishedApi
    public static short constructor-impl(short data) {
        return data;
    }

    @NotNull
    public static final /* synthetic */ UShort box-impl(short v) {
        return new UShort(v);
    }

    public static int hashCode-impl(short s2) {
        return s2;
    }

    public static boolean equals-impl(short s2, @Nullable Object object) {
        short s3;
        return object instanceof UShort && s2 == (s3 = ((UShort)object).unbox-impl());
    }

    public static final boolean equals-impl0(short p1, short p2) {
        return p1 == p2;
    }

    public final /* synthetic */ short unbox-impl() {
        return this.data;
    }

    public int hashCode() {
        return UShort.hashCode-impl(this.data);
    }

    public boolean equals(Object object) {
        return UShort.equals-impl(this.data, object);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u0004X\u0086T\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u00020\u0004X\u0086T\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006\n"}, d2={"Lkotlin/UShort$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UShort;", "S", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

