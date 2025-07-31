/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
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
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0005\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001fB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\f\u00f8\u0001\u0000\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\u0097\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0010H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0013H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001c\u0010\u000fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001d\u0010\u0012J\u001b\u0010\u001b\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b \u0010\u0018J\u0013\u0010!\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010#H\u00d6\u0003J\t\u0010$\u001a\u00020\rH\u00d6\u0001J\u0016\u0010%\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b&\u0010\u0005J\u0016\u0010'\u001a\u00020\u0000H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b(\u0010\u0005J\u001b\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b*\u0010\u000fJ\u001b\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b+\u0010\u0012J\u001b\u0010)\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b,\u0010\u001fJ\u001b\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b-\u0010\u0018J\u001b\u0010.\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\f\u00f8\u0001\u0000\u00a2\u0006\u0004\b/\u0010\u000bJ\u001b\u00100\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b1\u0010\u000fJ\u001b\u00100\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b2\u0010\u0012J\u001b\u00100\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b3\u0010\u001fJ\u001b\u00100\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b4\u0010\u0018J\u001b\u00105\u001a\u0002062\u0006\u0010\t\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b7\u00108J\u001b\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b:\u0010\u000fJ\u001b\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b;\u0010\u0012J\u001b\u00109\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b<\u0010\u001fJ\u001b\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b=\u0010\u0018J\u001b\u0010>\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b?\u0010\u000fJ\u001b\u0010>\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b@\u0010\u0012J\u001b\u0010>\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\bA\u0010\u001fJ\u001b\u0010>\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\bB\u0010\u0018J\u0010\u0010C\u001a\u00020\u0003H\u0087\b\u00a2\u0006\u0004\bD\u0010\u0005J\u0010\u0010E\u001a\u00020FH\u0087\b\u00a2\u0006\u0004\bG\u0010HJ\u0010\u0010I\u001a\u00020JH\u0087\b\u00a2\u0006\u0004\bK\u0010LJ\u0010\u0010M\u001a\u00020\rH\u0087\b\u00a2\u0006\u0004\bN\u0010OJ\u0010\u0010P\u001a\u00020QH\u0087\b\u00a2\u0006\u0004\bR\u0010SJ\u0010\u0010T\u001a\u00020UH\u0087\b\u00a2\u0006\u0004\bV\u0010WJ\u000f\u0010X\u001a\u00020YH\u0016\u00a2\u0006\u0004\bZ\u0010[J\u0016\u0010\\\u001a\u00020\u0000H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b]\u0010\u0005J\u0016\u0010^\u001a\u00020\u0010H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b_\u0010OJ\u0016\u0010`\u001a\u00020\u0013H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\ba\u0010SJ\u0016\u0010b\u001a\u00020\u0016H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bc\u0010WJ\u001b\u0010d\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\f\u00f8\u0001\u0000\u00a2\u0006\u0004\be\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004\u00a2\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u00f8\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006g"}, d2={"Lkotlin/UByte;", "", "data", "", "constructor-impl", "(B)B", "getData$annotations", "()V", "and", "other", "and-7apg3OU", "(BB)B", "compareTo", "", "compareTo-7apg3OU", "(BB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(BI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(BJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(BS)I", "dec", "dec-w2LRezQ", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(BJ)J", "div-xj2QHRw", "equals", "", "", "hashCode", "inc", "inc-w2LRezQ", "inv", "inv-w2LRezQ", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-7apg3OU", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-7apg3OU", "(BB)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "toByte-impl", "toDouble", "", "toDouble-impl", "(B)D", "toFloat", "", "toFloat-impl", "(B)F", "toInt", "toInt-impl", "(B)I", "toLong", "", "toLong-impl", "(B)J", "toShort", "", "toShort-impl", "(B)S", "toString", "", "toString-impl", "(B)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-7apg3OU", "Companion", "kotlin-stdlib"})
@SinceKotlin(version="1.3")
@ExperimentalUnsignedTypes
public final class UByte
implements Comparable<UByte> {
    private final byte data;
    public static final byte MIN_VALUE = 0;
    public static final byte MAX_VALUE = -1;
    public static final int SIZE_BYTES = 1;
    public static final int SIZE_BITS = 8;
    public static final Companion Companion = new Companion(null);

    @InlineOnly
    private int compareTo-7apg3OU(byte by) {
        return UByte.compareTo-7apg3OU(this.data, by);
    }

    @NotNull
    public String toString() {
        return UByte.toString-impl(this.data);
    }

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    @PublishedApi
    private /* synthetic */ UByte(byte data) {
        this.data = data;
    }

    @InlineOnly
    private static int compareTo-7apg3OU(byte $this, byte other) {
        int n = 0;
        byte by = $this;
        boolean bl = false;
        int n2 = by & 0xFF;
        by = other;
        bl = false;
        return Intrinsics.compare(n2, by & 0xFF);
    }

    @InlineOnly
    private static final int compareTo-xj2QHRw(byte $this, short other) {
        int n = 0;
        short s2 = $this;
        boolean bl = false;
        int n2 = s2 & 0xFF;
        s2 = other;
        bl = false;
        return Intrinsics.compare(n2, s2 & 0xFFFF);
    }

    @InlineOnly
    private static final int compareTo-WZ4Q5Ns(byte $this, int other) {
        int n = 0;
        int n2 = $this;
        boolean bl = false;
        n2 = UInt.constructor-impl(n2 & 0xFF);
        bl = false;
        return UnsignedKt.uintCompare(n2, other);
    }

    @InlineOnly
    private static final int compareTo-VKZWuLQ(byte $this, long other) {
        int n = 0;
        byte by = $this;
        boolean bl = false;
        long l = ULong.constructor-impl((long)by & 0xFFL);
        boolean bl2 = false;
        return UnsignedKt.ulongCompare(l, other);
    }

    @InlineOnly
    private static final int plus-7apg3OU(byte $this, byte other) {
        int n = 0;
        int n2 = $this;
        int n3 = 0;
        n2 = UInt.constructor-impl(n2 & 0xFF);
        n3 = other;
        boolean bl = false;
        n3 = UInt.constructor-impl(n3 & 0xFF);
        bl = false;
        return UInt.constructor-impl(n2 + n3);
    }

    @InlineOnly
    private static final int plus-xj2QHRw(byte $this, short other) {
        int n = 0;
        int n2 = $this;
        int n3 = 0;
        n2 = UInt.constructor-impl(n2 & 0xFF);
        n3 = other;
        boolean bl = false;
        n3 = UInt.constructor-impl(n3 & 0xFFFF);
        bl = false;
        return UInt.constructor-impl(n2 + n3);
    }

    @InlineOnly
    private static final int plus-WZ4Q5Ns(byte $this, int other) {
        int n = 0;
        int n2 = $this;
        boolean bl = false;
        n2 = UInt.constructor-impl(n2 & 0xFF);
        bl = false;
        return UInt.constructor-impl(n2 + other);
    }

    @InlineOnly
    private static final long plus-VKZWuLQ(byte $this, long other) {
        int n = 0;
        byte by = $this;
        boolean bl = false;
        long l = ULong.constructor-impl((long)by & 0xFFL);
        boolean bl2 = false;
        return ULong.constructor-impl(l + other);
    }

    @InlineOnly
    private static final int minus-7apg3OU(byte $this, byte other) {
        int n = 0;
        int n2 = $this;
        int n3 = 0;
        n2 = UInt.constructor-impl(n2 & 0xFF);
        n3 = other;
        boolean bl = false;
        n3 = UInt.constructor-impl(n3 & 0xFF);
        bl = false;
        return UInt.constructor-impl(n2 - n3);
    }

    @InlineOnly
    private static final int minus-xj2QHRw(byte $this, short other) {
        int n = 0;
        int n2 = $this;
        int n3 = 0;
        n2 = UInt.constructor-impl(n2 & 0xFF);
        n3 = other;
        boolean bl = false;
        n3 = UInt.constructor-impl(n3 & 0xFFFF);
        bl = false;
        return UInt.constructor-impl(n2 - n3);
    }

    @InlineOnly
    private static final int minus-WZ4Q5Ns(byte $this, int other) {
        int n = 0;
        int n2 = $this;
        boolean bl = false;
        n2 = UInt.constructor-impl(n2 & 0xFF);
        bl = false;
        return UInt.constructor-impl(n2 - other);
    }

    @InlineOnly
    private static final long minus-VKZWuLQ(byte $this, long other) {
        int n = 0;
        byte by = $this;
        boolean bl = false;
        long l = ULong.constructor-impl((long)by & 0xFFL);
        boolean bl2 = false;
        return ULong.constructor-impl(l - other);
    }

    @InlineOnly
    private static final int times-7apg3OU(byte $this, byte other) {
        int n = 0;
        int n2 = $this;
        int n3 = 0;
        n2 = UInt.constructor-impl(n2 & 0xFF);
        n3 = other;
        boolean bl = false;
        n3 = UInt.constructor-impl(n3 & 0xFF);
        bl = false;
        return UInt.constructor-impl(n2 * n3);
    }

    @InlineOnly
    private static final int times-xj2QHRw(byte $this, short other) {
        int n = 0;
        int n2 = $this;
        int n3 = 0;
        n2 = UInt.constructor-impl(n2 & 0xFF);
        n3 = other;
        boolean bl = false;
        n3 = UInt.constructor-impl(n3 & 0xFFFF);
        bl = false;
        return UInt.constructor-impl(n2 * n3);
    }

    @InlineOnly
    private static final int times-WZ4Q5Ns(byte $this, int other) {
        int n = 0;
        int n2 = $this;
        boolean bl = false;
        n2 = UInt.constructor-impl(n2 & 0xFF);
        bl = false;
        return UInt.constructor-impl(n2 * other);
    }

    @InlineOnly
    private static final long times-VKZWuLQ(byte $this, long other) {
        int n = 0;
        byte by = $this;
        boolean bl = false;
        long l = ULong.constructor-impl((long)by & 0xFFL);
        boolean bl2 = false;
        return ULong.constructor-impl(l * other);
    }

    @InlineOnly
    private static final int div-7apg3OU(byte $this, byte other) {
        int n = 0;
        int n2 = $this;
        int n3 = 0;
        n2 = UInt.constructor-impl(n2 & 0xFF);
        n3 = other;
        boolean bl = false;
        n3 = UInt.constructor-impl(n3 & 0xFF);
        bl = false;
        return UnsignedKt.uintDivide-J1ME1BU(n2, n3);
    }

    @InlineOnly
    private static final int div-xj2QHRw(byte $this, short other) {
        int n = 0;
        int n2 = $this;
        int n3 = 0;
        n2 = UInt.constructor-impl(n2 & 0xFF);
        n3 = other;
        boolean bl = false;
        n3 = UInt.constructor-impl(n3 & 0xFFFF);
        bl = false;
        return UnsignedKt.uintDivide-J1ME1BU(n2, n3);
    }

    @InlineOnly
    private static final int div-WZ4Q5Ns(byte $this, int other) {
        int n = 0;
        int n2 = $this;
        boolean bl = false;
        n2 = UInt.constructor-impl(n2 & 0xFF);
        bl = false;
        return UnsignedKt.uintDivide-J1ME1BU(n2, other);
    }

    @InlineOnly
    private static final long div-VKZWuLQ(byte $this, long other) {
        int n = 0;
        byte by = $this;
        boolean bl = false;
        long l = ULong.constructor-impl((long)by & 0xFFL);
        boolean bl2 = false;
        return UnsignedKt.ulongDivide-eb3DHEI(l, other);
    }

    @InlineOnly
    private static final int rem-7apg3OU(byte $this, byte other) {
        int n = 0;
        int n2 = $this;
        int n3 = 0;
        n2 = UInt.constructor-impl(n2 & 0xFF);
        n3 = other;
        boolean bl = false;
        n3 = UInt.constructor-impl(n3 & 0xFF);
        bl = false;
        return UnsignedKt.uintRemainder-J1ME1BU(n2, n3);
    }

    @InlineOnly
    private static final int rem-xj2QHRw(byte $this, short other) {
        int n = 0;
        int n2 = $this;
        int n3 = 0;
        n2 = UInt.constructor-impl(n2 & 0xFF);
        n3 = other;
        boolean bl = false;
        n3 = UInt.constructor-impl(n3 & 0xFFFF);
        bl = false;
        return UnsignedKt.uintRemainder-J1ME1BU(n2, n3);
    }

    @InlineOnly
    private static final int rem-WZ4Q5Ns(byte $this, int other) {
        int n = 0;
        int n2 = $this;
        boolean bl = false;
        n2 = UInt.constructor-impl(n2 & 0xFF);
        bl = false;
        return UnsignedKt.uintRemainder-J1ME1BU(n2, other);
    }

    @InlineOnly
    private static final long rem-VKZWuLQ(byte $this, long other) {
        int n = 0;
        byte by = $this;
        boolean bl = false;
        long l = ULong.constructor-impl((long)by & 0xFFL);
        boolean bl2 = false;
        return UnsignedKt.ulongRemainder-eb3DHEI(l, other);
    }

    @InlineOnly
    private static final byte inc-w2LRezQ(byte $this) {
        int n = 0;
        return UByte.constructor-impl((byte)($this + 1));
    }

    @InlineOnly
    private static final byte dec-w2LRezQ(byte $this) {
        int n = 0;
        return UByte.constructor-impl((byte)($this + -1));
    }

    @InlineOnly
    private static final UIntRange rangeTo-7apg3OU(byte $this, byte other) {
        int n = 0;
        byte by = $this;
        boolean bl = false;
        int n2 = UInt.constructor-impl(by & 0xFF);
        by = other;
        bl = false;
        return new UIntRange(n2, UInt.constructor-impl(by & 0xFF), null);
    }

    @InlineOnly
    private static final byte and-7apg3OU(byte $this, byte other) {
        int n = 0;
        byte by = $this;
        byte by2 = other;
        boolean bl = false;
        return UByte.constructor-impl((byte)(by & by2));
    }

    @InlineOnly
    private static final byte or-7apg3OU(byte $this, byte other) {
        int n = 0;
        byte by = $this;
        byte by2 = other;
        boolean bl = false;
        return UByte.constructor-impl((byte)(by | by2));
    }

    @InlineOnly
    private static final byte xor-7apg3OU(byte $this, byte other) {
        int n = 0;
        byte by = $this;
        byte by2 = other;
        boolean bl = false;
        return UByte.constructor-impl((byte)(by ^ by2));
    }

    @InlineOnly
    private static final byte inv-w2LRezQ(byte $this) {
        int n = 0;
        byte by = $this;
        boolean bl = false;
        return UByte.constructor-impl(~by);
    }

    @InlineOnly
    private static final byte toByte-impl(byte $this) {
        int $i$f$toByte = 0;
        return $this;
    }

    @InlineOnly
    private static final short toShort-impl(byte $this) {
        int $i$f$toShort = 0;
        short s2 = $this;
        int n = 255;
        boolean bl = false;
        return (short)(s2 & n);
    }

    @InlineOnly
    private static final int toInt-impl(byte $this) {
        int $i$f$toInt = 0;
        return $this & 0xFF;
    }

    @InlineOnly
    private static final long toLong-impl(byte $this) {
        int $i$f$toLong = 0;
        return (long)$this & 0xFFL;
    }

    @InlineOnly
    private static final byte toUByte-w2LRezQ(byte $this) {
        int n = 0;
        return $this;
    }

    @InlineOnly
    private static final short toUShort-Mh2AYeg(byte $this) {
        int n = 0;
        short s2 = $this;
        int n2 = 255;
        boolean bl = false;
        return UShort.constructor-impl((short)(s2 & n2));
    }

    @InlineOnly
    private static final int toUInt-pVg5ArA(byte $this) {
        int n = 0;
        return UInt.constructor-impl($this & 0xFF);
    }

    @InlineOnly
    private static final long toULong-s-VKNKU(byte $this) {
        int n = 0;
        return ULong.constructor-impl((long)$this & 0xFFL);
    }

    @InlineOnly
    private static final float toFloat-impl(byte $this) {
        int $i$f$toFloat = 0;
        byte by = $this;
        boolean bl = false;
        return by & 0xFF;
    }

    @InlineOnly
    private static final double toDouble-impl(byte $this) {
        int $i$f$toDouble = 0;
        byte by = $this;
        boolean bl = false;
        return by & 0xFF;
    }

    @NotNull
    public static String toString-impl(byte $this) {
        byte by = $this;
        boolean bl = false;
        return String.valueOf(by & 0xFF);
    }

    @PublishedApi
    public static byte constructor-impl(byte data) {
        return data;
    }

    @NotNull
    public static final /* synthetic */ UByte box-impl(byte v) {
        return new UByte(v);
    }

    public static int hashCode-impl(byte by) {
        return by;
    }

    public static boolean equals-impl(byte by, @Nullable Object object) {
        byte by2;
        return object instanceof UByte && by == (by2 = ((UByte)object).unbox-impl());
    }

    public static final boolean equals-impl0(byte p1, byte p2) {
        return p1 == p2;
    }

    public final /* synthetic */ byte unbox-impl() {
        return this.data;
    }

    public int hashCode() {
        return UByte.hashCode-impl(this.data);
    }

    public boolean equals(Object object) {
        return UByte.equals-impl(this.data, object);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u0004X\u0086T\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u00020\u0004X\u0086T\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006\n"}, d2={"Lkotlin/UByte$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UByte;", "B", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

