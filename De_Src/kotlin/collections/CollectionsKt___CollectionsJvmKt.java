/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.collections;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import kotlin.Metadata;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.SinceKotlin;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__ReversedViewsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000R\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a(\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0006\u0012\u0002\b\u00030\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005\u001aA\u0010\u0006\u001a\u0002H\u0007\"\u0010\b\u0000\u0010\u0007*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\b\"\u0004\b\u0001\u0010\u0002*\u0006\u0012\u0002\b\u00030\u00032\u0006\u0010\t\u001a\u0002H\u00072\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005\u00a2\u0006\u0002\u0010\n\u001a\u0016\u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u000e\u001a5\u0010\u000f\u001a\u00020\u0010\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u00032\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u00020\u00100\u0012H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\b\u0013\u001a5\u0010\u000f\u001a\u00020\u0014\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u00032\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u00020\u00140\u0012H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\b\u0015\u001a&\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\r0\u0017\"\u000e\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u0018*\b\u0012\u0004\u0012\u0002H\r0\u0003\u001a8\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\r0\u0017\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u00032\u001a\u0010\u0019\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\r0\u001aj\n\u0012\u0006\b\u0000\u0012\u0002H\r`\u001b\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\u001c"}, d2={"filterIsInstance", "", "R", "", "klass", "Ljava/lang/Class;", "filterIsInstanceTo", "C", "", "destination", "(Ljava/lang/Iterable;Ljava/util/Collection;Ljava/lang/Class;)Ljava/util/Collection;", "reverse", "", "T", "", "sumOf", "Ljava/math/BigDecimal;", "selector", "Lkotlin/Function1;", "sumOfBigDecimal", "Ljava/math/BigInteger;", "sumOfBigInteger", "toSortedSet", "Ljava/util/SortedSet;", "", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "kotlin-stdlib"}, xs="kotlin/collections/CollectionsKt")
class CollectionsKt___CollectionsJvmKt
extends CollectionsKt__ReversedViewsKt {
    @NotNull
    public static final <R> List<R> filterIsInstance(@NotNull Iterable<?> $this$filterIsInstance, @NotNull Class<R> klass) {
        Intrinsics.checkNotNullParameter($this$filterIsInstance, "$this$filterIsInstance");
        Intrinsics.checkNotNullParameter(klass, "klass");
        return (List)CollectionsKt.filterIsInstanceTo($this$filterIsInstance, (Collection)new ArrayList(), klass);
    }

    @NotNull
    public static final <C extends Collection<? super R>, R> C filterIsInstanceTo(@NotNull Iterable<?> $this$filterIsInstanceTo, @NotNull C destination, @NotNull Class<R> klass) {
        Intrinsics.checkNotNullParameter($this$filterIsInstanceTo, "$this$filterIsInstanceTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(klass, "klass");
        for (Object element : $this$filterIsInstanceTo) {
            if (!klass.isInstance(element)) continue;
            destination.add(element);
        }
        return destination;
    }

    public static final <T> void reverse(@NotNull List<T> $this$reverse) {
        Intrinsics.checkNotNullParameter($this$reverse, "$this$reverse");
        Collections.reverse($this$reverse);
    }

    @NotNull
    public static final <T extends Comparable<? super T>> SortedSet<T> toSortedSet(@NotNull Iterable<? extends T> $this$toSortedSet) {
        Intrinsics.checkNotNullParameter($this$toSortedSet, "$this$toSortedSet");
        return (SortedSet)CollectionsKt.toCollection($this$toSortedSet, (Collection)new TreeSet());
    }

    @NotNull
    public static final <T> SortedSet<T> toSortedSet(@NotNull Iterable<? extends T> $this$toSortedSet, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter($this$toSortedSet, "$this$toSortedSet");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return (SortedSet)CollectionsKt.toCollection($this$toSortedSet, (Collection)new TreeSet<T>(comparator));
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @JvmName(name="sumOfBigDecimal")
    @InlineOnly
    private static final <T> BigDecimal sumOfBigDecimal(Iterable<? extends T> $this$sumOf, Function1<? super T, ? extends BigDecimal> selector) {
        int $i$f$sumOfBigDecimal = 0;
        int n = 0;
        boolean bl = false;
        BigDecimal bigDecimal = BigDecimal.valueOf(n);
        Intrinsics.checkNotNullExpressionValue(bigDecimal, "BigDecimal.valueOf(this.toLong())");
        BigDecimal sum = bigDecimal;
        for (T element : $this$sumOf) {
            BigDecimal bigDecimal2 = sum;
            BigDecimal bigDecimal3 = selector.invoke(element);
            boolean bl2 = false;
            Intrinsics.checkNotNullExpressionValue(bigDecimal2.add(bigDecimal3), "this.add(other)");
        }
        return sum;
    }

    @SinceKotlin(version="1.4")
    @OverloadResolutionByLambdaReturnType
    @JvmName(name="sumOfBigInteger")
    @InlineOnly
    private static final <T> BigInteger sumOfBigInteger(Iterable<? extends T> $this$sumOf, Function1<? super T, ? extends BigInteger> selector) {
        int $i$f$sumOfBigInteger = 0;
        int n = 0;
        boolean bl = false;
        BigInteger bigInteger = BigInteger.valueOf(n);
        Intrinsics.checkNotNullExpressionValue(bigInteger, "BigInteger.valueOf(this.toLong())");
        BigInteger sum = bigInteger;
        for (T element : $this$sumOf) {
            BigInteger bigInteger2 = sum;
            BigInteger bigInteger3 = selector.invoke(element);
            boolean bl2 = false;
            Intrinsics.checkNotNullExpressionValue(bigInteger2.add(bigInteger3), "this.add(other)");
        }
        return sum;
    }
}

