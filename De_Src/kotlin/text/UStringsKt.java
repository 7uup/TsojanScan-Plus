/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.text;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.UnsignedKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000,\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\b\u0010\t\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000b\u0010\f\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000e\u0010\u000f\u001a\u0014\u0010\u0010\u001a\u00020\u0002*\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011\u001a\u001c\u0010\u0010\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012\u001a\u0011\u0010\u0013\u001a\u0004\u0018\u00010\u0002*\u00020\u0001H\u0007\u00f8\u0001\u0000\u001a\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u001a\u0014\u0010\u0014\u001a\u00020\u0007*\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015\u001a\u001c\u0010\u0014\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016\u001a\u0011\u0010\u0017\u001a\u0004\u0018\u00010\u0007*\u00020\u0001H\u0007\u00f8\u0001\u0000\u001a\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u0007*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u001a\u0014\u0010\u0018\u001a\u00020\n*\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019\u001a\u001c\u0010\u0018\u001a\u00020\n*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001a\u001a\u0011\u0010\u001b\u001a\u0004\u0018\u00010\n*\u00020\u0001H\u0007\u00f8\u0001\u0000\u001a\u0019\u0010\u001b\u001a\u0004\u0018\u00010\n*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u001a\u0014\u0010\u001c\u001a\u00020\r*\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001d\u001a\u001c\u0010\u001c\u001a\u00020\r*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001e\u001a\u0011\u0010\u001f\u001a\u0004\u0018\u00010\r*\u00020\u0001H\u0007\u00f8\u0001\u0000\u001a\u0019\u0010\u001f\u001a\u0004\u0018\u00010\r*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006 "}, d2={"toString", "", "Lkotlin/UByte;", "radix", "", "toString-LxnNnR4", "(BI)Ljava/lang/String;", "Lkotlin/UInt;", "toString-V7xB4Y4", "(II)Ljava/lang/String;", "Lkotlin/ULong;", "toString-JSWoG40", "(JI)Ljava/lang/String;", "Lkotlin/UShort;", "toString-olVBNx4", "(SI)Ljava/lang/String;", "toUByte", "(Ljava/lang/String;)B", "(Ljava/lang/String;I)B", "toUByteOrNull", "toUInt", "(Ljava/lang/String;)I", "(Ljava/lang/String;I)I", "toUIntOrNull", "toULong", "(Ljava/lang/String;)J", "(Ljava/lang/String;I)J", "toULongOrNull", "toUShort", "(Ljava/lang/String;)S", "(Ljava/lang/String;I)S", "toUShortOrNull", "kotlin-stdlib"})
@JvmName(name="UStringsKt")
public final class UStringsKt {
    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final String toString-LxnNnR4(byte $this$toString, int radix) {
        int n = $this$toString;
        boolean bl = false;
        bl = false;
        String string = Integer.toString(n &= 0xFF, CharsKt.checkRadix(radix));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri\u2026(this, checkRadix(radix))");
        return string;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final String toString-olVBNx4(short $this$toString, int radix) {
        int n = $this$toString;
        boolean bl = false;
        bl = false;
        String string = Integer.toString(n &= 0xFFFF, CharsKt.checkRadix(radix));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri\u2026(this, checkRadix(radix))");
        return string;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final String toString-V7xB4Y4(int $this$toString, int radix) {
        int n = $this$toString;
        boolean bl = false;
        long l = (long)n & 0xFFFFFFFFL;
        boolean bl2 = false;
        String string = Long.toString(l, CharsKt.checkRadix(radix));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Long.toString(this, checkRadix(radix))");
        return string;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final String toString-JSWoG40(long $this$toString, int radix) {
        long l = $this$toString;
        boolean bl = false;
        return UnsignedKt.ulongToString(l, CharsKt.checkRadix(radix));
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final byte toUByte(@NotNull String $this$toUByte) {
        Intrinsics.checkNotNullParameter($this$toUByte, "$this$toUByte");
        UByte uByte = UStringsKt.toUByteOrNull($this$toUByte);
        if (uByte == null) {
            Void void_ = StringsKt.numberFormatError($this$toUByte);
            throw new KotlinNothingValueException();
        }
        return uByte.unbox-impl();
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final byte toUByte(@NotNull String $this$toUByte, int radix) {
        Intrinsics.checkNotNullParameter($this$toUByte, "$this$toUByte");
        UByte uByte = UStringsKt.toUByteOrNull($this$toUByte, radix);
        if (uByte == null) {
            Void void_ = StringsKt.numberFormatError($this$toUByte);
            throw new KotlinNothingValueException();
        }
        return uByte.unbox-impl();
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final short toUShort(@NotNull String $this$toUShort) {
        Intrinsics.checkNotNullParameter($this$toUShort, "$this$toUShort");
        UShort uShort = UStringsKt.toUShortOrNull($this$toUShort);
        if (uShort == null) {
            Void void_ = StringsKt.numberFormatError($this$toUShort);
            throw new KotlinNothingValueException();
        }
        return uShort.unbox-impl();
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final short toUShort(@NotNull String $this$toUShort, int radix) {
        Intrinsics.checkNotNullParameter($this$toUShort, "$this$toUShort");
        UShort uShort = UStringsKt.toUShortOrNull($this$toUShort, radix);
        if (uShort == null) {
            Void void_ = StringsKt.numberFormatError($this$toUShort);
            throw new KotlinNothingValueException();
        }
        return uShort.unbox-impl();
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final int toUInt(@NotNull String $this$toUInt) {
        Intrinsics.checkNotNullParameter($this$toUInt, "$this$toUInt");
        UInt uInt = UStringsKt.toUIntOrNull($this$toUInt);
        if (uInt == null) {
            Void void_ = StringsKt.numberFormatError($this$toUInt);
            throw new KotlinNothingValueException();
        }
        return uInt.unbox-impl();
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final int toUInt(@NotNull String $this$toUInt, int radix) {
        Intrinsics.checkNotNullParameter($this$toUInt, "$this$toUInt");
        UInt uInt = UStringsKt.toUIntOrNull($this$toUInt, radix);
        if (uInt == null) {
            Void void_ = StringsKt.numberFormatError($this$toUInt);
            throw new KotlinNothingValueException();
        }
        return uInt.unbox-impl();
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final long toULong(@NotNull String $this$toULong) {
        Intrinsics.checkNotNullParameter($this$toULong, "$this$toULong");
        ULong uLong = UStringsKt.toULongOrNull($this$toULong);
        if (uLong == null) {
            Void void_ = StringsKt.numberFormatError($this$toULong);
            throw new KotlinNothingValueException();
        }
        return uLong.unbox-impl();
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    public static final long toULong(@NotNull String $this$toULong, int radix) {
        Intrinsics.checkNotNullParameter($this$toULong, "$this$toULong");
        ULong uLong = UStringsKt.toULongOrNull($this$toULong, radix);
        if (uLong == null) {
            Void void_ = StringsKt.numberFormatError($this$toULong);
            throw new KotlinNothingValueException();
        }
        return uLong.unbox-impl();
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    public static final UByte toUByteOrNull(@NotNull String $this$toUByteOrNull) {
        Intrinsics.checkNotNullParameter($this$toUByteOrNull, "$this$toUByteOrNull");
        return UStringsKt.toUByteOrNull($this$toUByteOrNull, 10);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    public static final UByte toUByteOrNull(@NotNull String $this$toUByteOrNull, int radix) {
        int n;
        Intrinsics.checkNotNullParameter($this$toUByteOrNull, "$this$toUByteOrNull");
        UInt uInt = UStringsKt.toUIntOrNull($this$toUByteOrNull, radix);
        if (uInt == null) {
            return null;
        }
        int n2 = n = uInt.unbox-impl();
        int n3 = -1;
        int n4 = 0;
        int n5 = n2;
        int n6 = n3;
        boolean bl = false;
        n6 = UInt.constructor-impl(n6 & 0xFF);
        bl = false;
        if (UnsignedKt.uintCompare(n5, n6) > 0) {
            return null;
        }
        n2 = n;
        n3 = 0;
        n4 = n2;
        n5 = 0;
        return UByte.box-impl(UByte.constructor-impl((byte)n4));
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    public static final UShort toUShortOrNull(@NotNull String $this$toUShortOrNull) {
        Intrinsics.checkNotNullParameter($this$toUShortOrNull, "$this$toUShortOrNull");
        return UStringsKt.toUShortOrNull($this$toUShortOrNull, 10);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    public static final UShort toUShortOrNull(@NotNull String $this$toUShortOrNull, int radix) {
        int n;
        Intrinsics.checkNotNullParameter($this$toUShortOrNull, "$this$toUShortOrNull");
        UInt uInt = UStringsKt.toUIntOrNull($this$toUShortOrNull, radix);
        if (uInt == null) {
            return null;
        }
        int n2 = n = uInt.unbox-impl();
        int n3 = -1;
        int n4 = 0;
        int n5 = n2;
        int n6 = n3;
        boolean bl = false;
        n6 = UInt.constructor-impl(n6 & 0xFFFF);
        bl = false;
        if (UnsignedKt.uintCompare(n5, n6) > 0) {
            return null;
        }
        n2 = n;
        n3 = 0;
        n4 = n2;
        n5 = 0;
        return UShort.box-impl(UShort.constructor-impl((short)n4));
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    public static final UInt toUIntOrNull(@NotNull String $this$toUIntOrNull) {
        Intrinsics.checkNotNullParameter($this$toUIntOrNull, "$this$toUIntOrNull");
        return UStringsKt.toUIntOrNull($this$toUIntOrNull, 10);
    }

    /*
     * WARNING - void declaration
     */
    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    public static final UInt toUIntOrNull(@NotNull String $this$toUIntOrNull, int radix) {
        int limitForMaxRadix;
        Intrinsics.checkNotNullParameter($this$toUIntOrNull, "$this$toUIntOrNull");
        CharsKt.checkRadix(radix);
        int length = $this$toUIntOrNull.length();
        if (length == 0) {
            return null;
        }
        int limit = -1;
        int start = 0;
        char firstChar = $this$toUIntOrNull.charAt(0);
        if (Intrinsics.compare(firstChar, 48) < 0) {
            if (length == 1 || firstChar != '+') {
                return null;
            }
            start = 1;
        } else {
            start = 0;
        }
        int limitBeforeMul = limitForMaxRadix = 0x71C71C7;
        int n = radix;
        int n2 = 0;
        int uradix = UInt.constructor-impl(n);
        int result = 0;
        n2 = start;
        int n3 = length;
        while (n2 < n3) {
            void i;
            int digit = CharsKt.digitOf($this$toUIntOrNull.charAt((int)i), radix);
            if (digit < 0) {
                return null;
            }
            int n4 = result;
            int n5 = 0;
            if (UnsignedKt.uintCompare(n4, limitBeforeMul) > 0) {
                if (limitBeforeMul == limitForMaxRadix) {
                    n4 = limit;
                    n5 = 0;
                    limitBeforeMul = UnsignedKt.uintDivide-J1ME1BU(n4, uradix);
                    n4 = result;
                    n5 = 0;
                    if (UnsignedKt.uintCompare(n4, limitBeforeMul) > 0) {
                        return null;
                    }
                } else {
                    return null;
                }
            }
            n4 = result;
            n5 = 0;
            int beforeAdding = result = UInt.constructor-impl(n4 * uradix);
            n5 = result;
            int n6 = digit;
            boolean bl = false;
            n6 = UInt.constructor-impl(n6);
            bl = false;
            n5 = result = UInt.constructor-impl(n5 + n6);
            n6 = 0;
            if (UnsignedKt.uintCompare(n5, beforeAdding) < 0) {
                return null;
            }
            ++i;
        }
        return UInt.box-impl(result);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    public static final ULong toULongOrNull(@NotNull String $this$toULongOrNull) {
        Intrinsics.checkNotNullParameter($this$toULongOrNull, "$this$toULongOrNull");
        return UStringsKt.toULongOrNull($this$toULongOrNull, 10);
    }

    /*
     * WARNING - void declaration
     */
    @SinceKotlin(version="1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    public static final ULong toULongOrNull(@NotNull String $this$toULongOrNull, int radix) {
        long limitForMaxRadix;
        Intrinsics.checkNotNullParameter($this$toULongOrNull, "$this$toULongOrNull");
        CharsKt.checkRadix(radix);
        int length = $this$toULongOrNull.length();
        if (length == 0) {
            return null;
        }
        long limit = -1L;
        int start = 0;
        char firstChar = $this$toULongOrNull.charAt(0);
        if (Intrinsics.compare(firstChar, 48) < 0) {
            if (length == 1 || firstChar != '+') {
                return null;
            }
            start = 1;
        } else {
            start = 0;
        }
        long limitBeforeMul = limitForMaxRadix = 0x71C71C71C71C71CL;
        int n = radix;
        boolean bl = false;
        long uradix = ULong.constructor-impl(n);
        long result = 0L;
        int n2 = start;
        int n3 = length;
        while (n2 < n3) {
            void i;
            int digit = CharsKt.digitOf($this$toULongOrNull.charAt((int)i), radix);
            if (digit < 0) {
                return null;
            }
            long l = result;
            boolean bl2 = false;
            if (UnsignedKt.ulongCompare(l, limitBeforeMul) > 0) {
                if (limitBeforeMul == limitForMaxRadix) {
                    l = limit;
                    bl2 = false;
                    limitBeforeMul = UnsignedKt.ulongDivide-eb3DHEI(l, uradix);
                    l = result;
                    bl2 = false;
                    if (UnsignedKt.ulongCompare(l, limitBeforeMul) > 0) {
                        return null;
                    }
                } else {
                    return null;
                }
            }
            l = result;
            bl2 = false;
            long beforeAdding = result = ULong.constructor-impl(l * uradix);
            long l2 = result;
            int n4 = digit;
            boolean bl3 = false;
            n4 = UInt.constructor-impl(n4);
            bl3 = false;
            long l3 = l2;
            int n5 = n4;
            boolean bl4 = false;
            long l4 = ULong.constructor-impl((long)n5 & 0xFFFFFFFFL);
            boolean bl5 = false;
            l2 = result = ULong.constructor-impl(l3 + l4);
            n4 = 0;
            if (UnsignedKt.ulongCompare(l2, beforeAdding) < 0) {
                return null;
            }
            ++i;
        }
        return ULong.box-impl(result);
    }
}

