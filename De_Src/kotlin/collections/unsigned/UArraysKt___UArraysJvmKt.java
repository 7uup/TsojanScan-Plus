/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.collections.unsigned;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.RandomAccess;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.UnsignedKt;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.collections.unsigned.UArraysKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000T\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001*\u00020\u0007H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\b\u0010\t\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\n0\u0001*\u00020\u000bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\f\u0010\r\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0001*\u00020\u000fH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0010\u0010\u0011\u001a2\u0010\u0012\u001a\u00020\u0013*\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00022\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0018\u001a2\u0010\u0012\u001a\u00020\u0013*\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0019\u0010\u001a\u001a2\u0010\u0012\u001a\u00020\u0013*\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\n2\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001b\u0010\u001c\u001a2\u0010\u0012\u001a\u00020\u0013*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000e2\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001d\u0010\u001e\u001a\u001f\u0010\u001f\u001a\u00020\u0002*\u00020\u00032\u0006\u0010 \u001a\u00020\u0013H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b!\u0010\"\u001a\u001f\u0010\u001f\u001a\u00020\u0006*\u00020\u00072\u0006\u0010 \u001a\u00020\u0013H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b#\u0010$\u001a\u001f\u0010\u001f\u001a\u00020\n*\u00020\u000b2\u0006\u0010 \u001a\u00020\u0013H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b%\u0010&\u001a\u001f\u0010\u001f\u001a\u00020\u000e*\u00020\u000f2\u0006\u0010 \u001a\u00020\u0013H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b'\u0010(\u001a.\u0010)\u001a\u00020**\u00020\u00032\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020*0,H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b-\u0010.\u001a.\u0010)\u001a\u00020/*\u00020\u00032\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020/0,H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b0\u00101\u001a.\u0010)\u001a\u00020**\u00020\u00072\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020*0,H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b-\u00102\u001a.\u0010)\u001a\u00020/*\u00020\u00072\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020/0,H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b0\u00103\u001a.\u0010)\u001a\u00020**\u00020\u000b2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020*0,H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b-\u00104\u001a.\u0010)\u001a\u00020/*\u00020\u000b2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020/0,H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b0\u00105\u001a.\u0010)\u001a\u00020**\u00020\u000f2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020*0,H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b-\u00106\u001a.\u0010)\u001a\u00020/*\u00020\u000f2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020/0,H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b0\u00107\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b\u009920\u0001\u00a8\u00068"}, d2={"asList", "", "Lkotlin/UByte;", "Lkotlin/UByteArray;", "asList-GBYM_sE", "([B)Ljava/util/List;", "Lkotlin/UInt;", "Lkotlin/UIntArray;", "asList--ajY-9A", "([I)Ljava/util/List;", "Lkotlin/ULong;", "Lkotlin/ULongArray;", "asList-QwZRm1k", "([J)Ljava/util/List;", "Lkotlin/UShort;", "Lkotlin/UShortArray;", "asList-rL5Bavg", "([S)Ljava/util/List;", "binarySearch", "", "element", "fromIndex", "toIndex", "binarySearch-WpHrYlw", "([BBII)I", "binarySearch-2fe2U9s", "([IIII)I", "binarySearch-K6DWlUc", "([JJII)I", "binarySearch-EtDCXyQ", "([SSII)I", "elementAt", "index", "elementAt-PpDY95g", "([BI)B", "elementAt-qFRl0hI", "([II)I", "elementAt-r7IrZao", "([JI)J", "elementAt-nggk6HY", "([SI)S", "sumOf", "Ljava/math/BigDecimal;", "selector", "Lkotlin/Function1;", "sumOfBigDecimal", "([BLkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "Ljava/math/BigInteger;", "sumOfBigInteger", "([BLkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "([ILkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "([ILkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "([JLkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "([JLkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "([SLkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "([SLkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "kotlin-stdlib"}, xs="kotlin/collections/unsigned/UArraysKt", pn="kotlin.collections")
class UArraysKt___UArraysJvmKt {
    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int elementAt-qFRl0hI(int[] $this$elementAt, int index) {
        int n = 0;
        return UIntArray.get-pVg5ArA($this$elementAt, index);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final long elementAt-r7IrZao(long[] $this$elementAt, int index) {
        int n = 0;
        return ULongArray.get-s-VKNKU($this$elementAt, index);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final byte elementAt-PpDY95g(byte[] $this$elementAt, int index) {
        int n = 0;
        return UByteArray.get-w2LRezQ($this$elementAt, index);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final short elementAt-nggk6HY(short[] $this$elementAt, int index) {
        int n = 0;
        return UShortArray.get-Mh2AYeg($this$elementAt, index);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final List<UInt> asList--ajY-9A(@NotNull int[] $this$asList) {
        Intrinsics.checkNotNullParameter($this$asList, "$this$asList");
        return (List)((Object)new RandomAccess($this$asList){
            final /* synthetic */ int[] $this_asList;

            public int getSize() {
                return UIntArray.getSize-impl(this.$this_asList);
            }

            public boolean isEmpty() {
                return UIntArray.isEmpty-impl(this.$this_asList);
            }

            public boolean contains-WZ4Q5Ns(int element) {
                return UIntArray.contains-WZ4Q5Ns(this.$this_asList, element);
            }

            public int get-pVg5ArA(int index) {
                return UIntArray.get-pVg5ArA(this.$this_asList, index);
            }

            public int indexOf-WZ4Q5Ns(int element) {
                int[] nArray = this.$this_asList;
                boolean bl = false;
                int n = element;
                boolean bl2 = false;
                return ArraysKt.indexOf(nArray, n);
            }

            public int lastIndexOf-WZ4Q5Ns(int element) {
                int[] nArray = this.$this_asList;
                boolean bl = false;
                int n = element;
                boolean bl2 = false;
                return ArraysKt.lastIndexOf(nArray, n);
            }
            {
                this.$this_asList = $receiver;
            }
        });
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final List<ULong> asList-QwZRm1k(@NotNull long[] $this$asList) {
        Intrinsics.checkNotNullParameter($this$asList, "$this$asList");
        return (List)((Object)new RandomAccess($this$asList){
            final /* synthetic */ long[] $this_asList;

            public int getSize() {
                return ULongArray.getSize-impl(this.$this_asList);
            }

            public boolean isEmpty() {
                return ULongArray.isEmpty-impl(this.$this_asList);
            }

            public boolean contains-VKZWuLQ(long element) {
                return ULongArray.contains-VKZWuLQ(this.$this_asList, element);
            }

            public long get-s-VKNKU(int index) {
                return ULongArray.get-s-VKNKU(this.$this_asList, index);
            }

            public int indexOf-VKZWuLQ(long element) {
                long[] lArray = this.$this_asList;
                boolean bl = false;
                long l = element;
                boolean bl2 = false;
                return ArraysKt.indexOf(lArray, l);
            }

            public int lastIndexOf-VKZWuLQ(long element) {
                long[] lArray = this.$this_asList;
                boolean bl = false;
                long l = element;
                boolean bl2 = false;
                return ArraysKt.lastIndexOf(lArray, l);
            }
            {
                this.$this_asList = $receiver;
            }
        });
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final List<UByte> asList-GBYM_sE(@NotNull byte[] $this$asList) {
        Intrinsics.checkNotNullParameter($this$asList, "$this$asList");
        return (List)((Object)new RandomAccess($this$asList){
            final /* synthetic */ byte[] $this_asList;

            public int getSize() {
                return UByteArray.getSize-impl(this.$this_asList);
            }

            public boolean isEmpty() {
                return UByteArray.isEmpty-impl(this.$this_asList);
            }

            public boolean contains-7apg3OU(byte element) {
                return UByteArray.contains-7apg3OU(this.$this_asList, element);
            }

            public byte get-w2LRezQ(int index) {
                return UByteArray.get-w2LRezQ(this.$this_asList, index);
            }

            public int indexOf-7apg3OU(byte element) {
                byte[] byArray = this.$this_asList;
                boolean bl = false;
                byte by = element;
                boolean bl2 = false;
                return ArraysKt.indexOf(byArray, by);
            }

            public int lastIndexOf-7apg3OU(byte element) {
                byte[] byArray = this.$this_asList;
                boolean bl = false;
                byte by = element;
                boolean bl2 = false;
                return ArraysKt.lastIndexOf(byArray, by);
            }
            {
                this.$this_asList = $receiver;
            }
        });
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final List<UShort> asList-rL5Bavg(@NotNull short[] $this$asList) {
        Intrinsics.checkNotNullParameter($this$asList, "$this$asList");
        return (List)((Object)new RandomAccess($this$asList){
            final /* synthetic */ short[] $this_asList;

            public int getSize() {
                return UShortArray.getSize-impl(this.$this_asList);
            }

            public boolean isEmpty() {
                return UShortArray.isEmpty-impl(this.$this_asList);
            }

            public boolean contains-xj2QHRw(short element) {
                return UShortArray.contains-xj2QHRw(this.$this_asList, element);
            }

            public short get-Mh2AYeg(int index) {
                return UShortArray.get-Mh2AYeg(this.$this_asList, index);
            }

            public int indexOf-xj2QHRw(short element) {
                short[] sArray = this.$this_asList;
                boolean bl = false;
                short s2 = element;
                boolean bl2 = false;
                return ArraysKt.indexOf(sArray, s2);
            }

            public int lastIndexOf-xj2QHRw(short element) {
                short[] sArray = this.$this_asList;
                boolean bl = false;
                short s2 = element;
                boolean bl2 = false;
                return ArraysKt.lastIndexOf(sArray, s2);
            }
            {
                this.$this_asList = $receiver;
            }
        });
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final int binarySearch-2fe2U9s(@NotNull int[] $this$binarySearch, int element, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter($this$binarySearch, "$this$binarySearch");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, UIntArray.getSize-impl($this$binarySearch));
        int n = element;
        boolean bl = false;
        int signedElement = n;
        int low = fromIndex;
        int high = toIndex - 1;
        while (low <= high) {
            int mid = low + high >>> 1;
            int midVal = $this$binarySearch[mid];
            int cmp = UnsignedKt.uintCompare(midVal, signedElement);
            if (cmp < 0) {
                low = mid + 1;
                continue;
            }
            if (cmp > 0) {
                high = mid - 1;
                continue;
            }
            return mid;
        }
        return -(low + 1);
    }

    public static /* synthetic */ int binarySearch-2fe2U9s$default(int[] nArray, int n, int n2, int n3, int n4, Object object) {
        if ((n4 & 2) != 0) {
            n2 = 0;
        }
        if ((n4 & 4) != 0) {
            n3 = UIntArray.getSize-impl(nArray);
        }
        return UArraysKt.binarySearch-2fe2U9s(nArray, n, n2, n3);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final int binarySearch-K6DWlUc(@NotNull long[] $this$binarySearch, long element, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter($this$binarySearch, "$this$binarySearch");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, ULongArray.getSize-impl($this$binarySearch));
        long l = element;
        boolean bl = false;
        long signedElement = l;
        int low = fromIndex;
        int high = toIndex - 1;
        while (low <= high) {
            int mid = low + high >>> 1;
            long midVal = $this$binarySearch[mid];
            int cmp = UnsignedKt.ulongCompare(midVal, signedElement);
            if (cmp < 0) {
                low = mid + 1;
                continue;
            }
            if (cmp > 0) {
                high = mid - 1;
                continue;
            }
            return mid;
        }
        return -(low + 1);
    }

    public static /* synthetic */ int binarySearch-K6DWlUc$default(long[] lArray, long l, int n, int n2, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n = 0;
        }
        if ((n3 & 4) != 0) {
            n2 = ULongArray.getSize-impl(lArray);
        }
        return UArraysKt.binarySearch-K6DWlUc(lArray, l, n, n2);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final int binarySearch-WpHrYlw(@NotNull byte[] $this$binarySearch, byte element, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter($this$binarySearch, "$this$binarySearch");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, UByteArray.getSize-impl($this$binarySearch));
        byte by = element;
        boolean bl = false;
        int signedElement = by & 0xFF;
        int low = fromIndex;
        int high = toIndex - 1;
        while (low <= high) {
            int mid = low + high >>> 1;
            byte midVal = $this$binarySearch[mid];
            int cmp = UnsignedKt.uintCompare(midVal, signedElement);
            if (cmp < 0) {
                low = mid + 1;
                continue;
            }
            if (cmp > 0) {
                high = mid - 1;
                continue;
            }
            return mid;
        }
        return -(low + 1);
    }

    public static /* synthetic */ int binarySearch-WpHrYlw$default(byte[] byArray, byte by, int n, int n2, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n = 0;
        }
        if ((n3 & 4) != 0) {
            n2 = UByteArray.getSize-impl(byArray);
        }
        return UArraysKt.binarySearch-WpHrYlw(byArray, by, n, n2);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final int binarySearch-EtDCXyQ(@NotNull short[] $this$binarySearch, short element, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter($this$binarySearch, "$this$binarySearch");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, UShortArray.getSize-impl($this$binarySearch));
        short s2 = element;
        boolean bl = false;
        int signedElement = s2 & 0xFFFF;
        int low = fromIndex;
        int high = toIndex - 1;
        while (low <= high) {
            int mid = low + high >>> 1;
            short midVal = $this$binarySearch[mid];
            int cmp = UnsignedKt.uintCompare(midVal, signedElement);
            if (cmp < 0) {
                low = mid + 1;
                continue;
            }
            if (cmp > 0) {
                high = mid - 1;
                continue;
            }
            return mid;
        }
        return -(low + 1);
    }

    public static /* synthetic */ int binarySearch-EtDCXyQ$default(short[] sArray, short s2, int n, int n2, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n = 0;
        }
        if ((n3 & 4) != 0) {
            n2 = UShortArray.getSize-impl(sArray);
        }
        return UArraysKt.binarySearch-EtDCXyQ(sArray, s2, n, n2);
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @JvmName(name="sumOfBigDecimal")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final BigDecimal sumOfBigDecimal(int[] $this$sumOf, Function1<? super UInt, ? extends BigDecimal> selector) {
        int $i$f$sumOfBigDecimal = 0;
        int n = 0;
        int n2 = 0;
        BigDecimal bigDecimal = BigDecimal.valueOf(n);
        Intrinsics.checkNotNullExpressionValue(bigDecimal, "BigDecimal.valueOf(this.toLong())");
        BigDecimal sum = bigDecimal;
        for (int element : $this$sumOf) {
            BigDecimal bigDecimal2 = sum;
            BigDecimal bigDecimal3 = selector.invoke(UInt.box-impl(element));
            boolean bl = false;
            Intrinsics.checkNotNullExpressionValue(bigDecimal2.add(bigDecimal3), "this.add(other)");
        }
        return sum;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @JvmName(name="sumOfBigDecimal")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final BigDecimal sumOfBigDecimal(long[] $this$sumOf, Function1<? super ULong, ? extends BigDecimal> selector) {
        int $i$f$sumOfBigDecimal = 0;
        int n = 0;
        boolean bl = false;
        BigDecimal bigDecimal = BigDecimal.valueOf(n);
        Intrinsics.checkNotNullExpressionValue(bigDecimal, "BigDecimal.valueOf(this.toLong())");
        BigDecimal sum = bigDecimal;
        for (long element : $this$sumOf) {
            BigDecimal bigDecimal2 = sum;
            BigDecimal bigDecimal3 = selector.invoke(ULong.box-impl(element));
            boolean bl2 = false;
            Intrinsics.checkNotNullExpressionValue(bigDecimal2.add(bigDecimal3), "this.add(other)");
        }
        return sum;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @JvmName(name="sumOfBigDecimal")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final BigDecimal sumOfBigDecimal(byte[] $this$sumOf, Function1<? super UByte, ? extends BigDecimal> selector) {
        int $i$f$sumOfBigDecimal = 0;
        int n = 0;
        int n2 = 0;
        BigDecimal bigDecimal = BigDecimal.valueOf(n);
        Intrinsics.checkNotNullExpressionValue(bigDecimal, "BigDecimal.valueOf(this.toLong())");
        BigDecimal sum = bigDecimal;
        for (byte element : $this$sumOf) {
            BigDecimal bigDecimal2 = sum;
            BigDecimal bigDecimal3 = selector.invoke(UByte.box-impl(element));
            boolean bl = false;
            Intrinsics.checkNotNullExpressionValue(bigDecimal2.add(bigDecimal3), "this.add(other)");
        }
        return sum;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @JvmName(name="sumOfBigDecimal")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final BigDecimal sumOfBigDecimal(short[] $this$sumOf, Function1<? super UShort, ? extends BigDecimal> selector) {
        int $i$f$sumOfBigDecimal = 0;
        int n = 0;
        int n2 = 0;
        BigDecimal bigDecimal = BigDecimal.valueOf(n);
        Intrinsics.checkNotNullExpressionValue(bigDecimal, "BigDecimal.valueOf(this.toLong())");
        BigDecimal sum = bigDecimal;
        for (short element : $this$sumOf) {
            BigDecimal bigDecimal2 = sum;
            BigDecimal bigDecimal3 = selector.invoke(UShort.box-impl(element));
            boolean bl = false;
            Intrinsics.checkNotNullExpressionValue(bigDecimal2.add(bigDecimal3), "this.add(other)");
        }
        return sum;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @JvmName(name="sumOfBigInteger")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final BigInteger sumOfBigInteger(int[] $this$sumOf, Function1<? super UInt, ? extends BigInteger> selector) {
        int $i$f$sumOfBigInteger = 0;
        int n = 0;
        int n2 = 0;
        BigInteger bigInteger = BigInteger.valueOf(n);
        Intrinsics.checkNotNullExpressionValue(bigInteger, "BigInteger.valueOf(this.toLong())");
        BigInteger sum = bigInteger;
        for (int element : $this$sumOf) {
            BigInteger bigInteger2 = sum;
            BigInteger bigInteger3 = selector.invoke(UInt.box-impl(element));
            boolean bl = false;
            Intrinsics.checkNotNullExpressionValue(bigInteger2.add(bigInteger3), "this.add(other)");
        }
        return sum;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @JvmName(name="sumOfBigInteger")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final BigInteger sumOfBigInteger(long[] $this$sumOf, Function1<? super ULong, ? extends BigInteger> selector) {
        int $i$f$sumOfBigInteger = 0;
        int n = 0;
        boolean bl = false;
        BigInteger bigInteger = BigInteger.valueOf(n);
        Intrinsics.checkNotNullExpressionValue(bigInteger, "BigInteger.valueOf(this.toLong())");
        BigInteger sum = bigInteger;
        for (long element : $this$sumOf) {
            BigInteger bigInteger2 = sum;
            BigInteger bigInteger3 = selector.invoke(ULong.box-impl(element));
            boolean bl2 = false;
            Intrinsics.checkNotNullExpressionValue(bigInteger2.add(bigInteger3), "this.add(other)");
        }
        return sum;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @JvmName(name="sumOfBigInteger")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final BigInteger sumOfBigInteger(byte[] $this$sumOf, Function1<? super UByte, ? extends BigInteger> selector) {
        int $i$f$sumOfBigInteger = 0;
        int n = 0;
        int n2 = 0;
        BigInteger bigInteger = BigInteger.valueOf(n);
        Intrinsics.checkNotNullExpressionValue(bigInteger, "BigInteger.valueOf(this.toLong())");
        BigInteger sum = bigInteger;
        for (byte element : $this$sumOf) {
            BigInteger bigInteger2 = sum;
            BigInteger bigInteger3 = selector.invoke(UByte.box-impl(element));
            boolean bl = false;
            Intrinsics.checkNotNullExpressionValue(bigInteger2.add(bigInteger3), "this.add(other)");
        }
        return sum;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @JvmName(name="sumOfBigInteger")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final BigInteger sumOfBigInteger(short[] $this$sumOf, Function1<? super UShort, ? extends BigInteger> selector) {
        int $i$f$sumOfBigInteger = 0;
        int n = 0;
        int n2 = 0;
        BigInteger bigInteger = BigInteger.valueOf(n);
        Intrinsics.checkNotNullExpressionValue(bigInteger, "BigInteger.valueOf(this.toLong())");
        BigInteger sum = bigInteger;
        for (short element : $this$sumOf) {
            BigInteger bigInteger2 = sum;
            BigInteger bigInteger3 = selector.invoke(UShort.box-impl(element));
            boolean bl = false;
            Intrinsics.checkNotNullExpressionValue(bigInteger2.add(bigInteger3), "this.add(other)");
        }
        return sum;
    }
}

