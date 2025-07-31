/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.collections.CollectionsKt__IteratorsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a&\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u0087\b\u00a2\u0006\u0002\u0010\u0005\u001a\u0019\u0010\u0006\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0087\b\u001a!\u0010\u0006\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0087\b\u001a \u0010\t\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n*\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001a6\u0010\t\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\r0\fH\u0087\b\u00f8\u0001\u0000\u001a5\u0010\t\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u001a\u0010\u000e\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u000fj\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0010H\u0087\b\u001a2\u0010\u0011\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u001a\u0010\u000e\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u000fj\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0010\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\u0012"}, d2={"fill", "", "T", "", "value", "(Ljava/util/List;Ljava/lang/Object;)V", "shuffle", "random", "Ljava/util/Random;", "sort", "", "comparison", "Lkotlin/Function2;", "", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "sortWith", "kotlin-stdlib"}, xs="kotlin/collections/CollectionsKt")
class CollectionsKt__MutableCollectionsJVMKt
extends CollectionsKt__IteratorsKt {
    @Deprecated(message="Use sortWith(comparator) instead.", replaceWith=@ReplaceWith(imports={}, expression="this.sortWith(comparator)"), level=DeprecationLevel.ERROR)
    @InlineOnly
    private static final <T> void sort(List<T> $this$sort, Comparator<? super T> comparator) {
        int $i$f$sort = 0;
        throw (Throwable)new NotImplementedError(null, 1, null);
    }

    @Deprecated(message="Use sortWith(Comparator(comparison)) instead.", replaceWith=@ReplaceWith(imports={}, expression="this.sortWith(Comparator(comparison))"), level=DeprecationLevel.ERROR)
    @InlineOnly
    private static final <T> void sort(List<T> $this$sort, Function2<? super T, ? super T, Integer> comparison) {
        int $i$f$sort = 0;
        throw (Throwable)new NotImplementedError(null, 1, null);
    }

    public static final <T extends Comparable<? super T>> void sort(@NotNull List<T> $this$sort) {
        Intrinsics.checkNotNullParameter($this$sort, "$this$sort");
        if ($this$sort.size() > 1) {
            Collections.sort($this$sort);
        }
    }

    public static final <T> void sortWith(@NotNull List<T> $this$sortWith, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter($this$sortWith, "$this$sortWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if ($this$sortWith.size() > 1) {
            Collections.sort($this$sortWith, comparator);
        }
    }

    @InlineOnly
    @SinceKotlin(version="1.2")
    private static final <T> void fill(List<T> $this$fill, T value) {
        int $i$f$fill = 0;
        Collections.fill($this$fill, value);
    }

    @InlineOnly
    @SinceKotlin(version="1.2")
    private static final <T> void shuffle(List<T> $this$shuffle) {
        int $i$f$shuffle = 0;
        Collections.shuffle($this$shuffle);
    }

    @InlineOnly
    @SinceKotlin(version="1.2")
    private static final <T> void shuffle(List<T> $this$shuffle, Random random) {
        int $i$f$shuffle = 0;
        Collections.shuffle($this$shuffle, random);
    }
}

