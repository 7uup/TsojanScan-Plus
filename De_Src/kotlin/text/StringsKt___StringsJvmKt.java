/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.text;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;
import kotlin.Metadata;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000,\n\u0000\n\u0002\u0010\f\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a)\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\b\t\u001a)\u0010\u0005\u001a\u00020\n*\u00020\u00022\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\n0\bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\b\u000b\u001a\u0010\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\r*\u00020\u0002\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\u000e"}, d2={"elementAt", "", "", "index", "", "sumOf", "Ljava/math/BigDecimal;", "selector", "Lkotlin/Function1;", "sumOfBigDecimal", "Ljava/math/BigInteger;", "sumOfBigInteger", "toSortedSet", "Ljava/util/SortedSet;", "kotlin-stdlib"}, xs="kotlin/text/StringsKt")
class StringsKt___StringsJvmKt
extends StringsKt__StringsKt {
    @InlineOnly
    private static final char elementAt(CharSequence $this$elementAt, int index) {
        int $i$f$elementAt = 0;
        return $this$elementAt.charAt(index);
    }

    @NotNull
    public static final SortedSet<Character> toSortedSet(@NotNull CharSequence $this$toSortedSet) {
        Intrinsics.checkNotNullParameter($this$toSortedSet, "$this$toSortedSet");
        return (SortedSet)StringsKt.toCollection($this$toSortedSet, (Collection)new TreeSet());
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @JvmName(name="sumOfBigDecimal")
    @InlineOnly
    private static final BigDecimal sumOfBigDecimal(CharSequence $this$sumOf, Function1<? super Character, ? extends BigDecimal> selector) {
        int $i$f$sumOfBigDecimal = 0;
        int n = 0;
        int n2 = 0;
        BigDecimal bigDecimal = BigDecimal.valueOf(n);
        Intrinsics.checkNotNullExpressionValue(bigDecimal, "BigDecimal.valueOf(this.toLong())");
        BigDecimal sum = bigDecimal;
        CharSequence charSequence = $this$sumOf;
        for (n2 = 0; n2 < charSequence.length(); ++n2) {
            char element = charSequence.charAt(n2);
            BigDecimal bigDecimal2 = sum;
            BigDecimal bigDecimal3 = selector.invoke(Character.valueOf(element));
            boolean bl = false;
            Intrinsics.checkNotNullExpressionValue(bigDecimal2.add(bigDecimal3), "this.add(other)");
        }
        return sum;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @JvmName(name="sumOfBigInteger")
    @InlineOnly
    private static final BigInteger sumOfBigInteger(CharSequence $this$sumOf, Function1<? super Character, ? extends BigInteger> selector) {
        int $i$f$sumOfBigInteger = 0;
        int n = 0;
        int n2 = 0;
        BigInteger bigInteger = BigInteger.valueOf(n);
        Intrinsics.checkNotNullExpressionValue(bigInteger, "BigInteger.valueOf(this.toLong())");
        BigInteger sum = bigInteger;
        CharSequence charSequence = $this$sumOf;
        for (n2 = 0; n2 < charSequence.length(); ++n2) {
            char element = charSequence.charAt(n2);
            BigInteger bigInteger2 = sum;
            BigInteger bigInteger3 = selector.invoke(Character.valueOf(element));
            boolean bl = false;
            Intrinsics.checkNotNullExpressionValue(bigInteger2.add(bigInteger3), "this.add(other)");
        }
        return sum;
    }
}

