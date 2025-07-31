/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000\u001c\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a,\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0086\u0002\u00a2\u0006\u0002\u0010\u0004\u001a4\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0006H\u0086\u0002\u00a2\u0006\u0002\u0010\u0007\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0086\u0002\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\tH\u0086\u0002\u001a,\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0087\b\u00a2\u0006\u0002\u0010\u0004\u001a,\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0086\u0002\u00a2\u0006\u0002\u0010\u0004\u001a4\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0006H\u0086\u0002\u00a2\u0006\u0002\u0010\u0007\u001a-\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0086\u0002\u001a-\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\tH\u0086\u0002\u001a,\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0087\b\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\r"}, d2={"minus", "", "T", "element", "(Ljava/util/Set;Ljava/lang/Object;)Ljava/util/Set;", "elements", "", "(Ljava/util/Set;[Ljava/lang/Object;)Ljava/util/Set;", "", "Lkotlin/sequences/Sequence;", "minusElement", "plus", "plusElement", "kotlin-stdlib"}, xs="kotlin/collections/SetsKt")
class SetsKt___SetsKt
extends SetsKt__SetsKt {
    @NotNull
    public static final <T> Set<T> minus(@NotNull Set<? extends T> $this$minus, T element) {
        Intrinsics.checkNotNullParameter($this$minus, "$this$minus");
        LinkedHashSet result = new LinkedHashSet(MapsKt.mapCapacity($this$minus.size()));
        boolean removed = false;
        Iterable $this$filterTo$iv = $this$minus;
        boolean $i$f$filterTo = false;
        Iterator iterator2 = $this$filterTo$iv.iterator();
        while (iterator2.hasNext()) {
            boolean bl;
            Object element$iv;
            Object it = element$iv = iterator2.next();
            boolean bl2 = false;
            if (!removed && Intrinsics.areEqual(it, element)) {
                removed = true;
                bl = false;
            } else {
                bl = true;
            }
            if (!bl) continue;
            ((Collection)result).add(element$iv);
        }
        return (Set)((Collection)result);
    }

    @NotNull
    public static final <T> Set<T> minus(@NotNull Set<? extends T> $this$minus, @NotNull T[] elements) {
        Intrinsics.checkNotNullParameter($this$minus, "$this$minus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        LinkedHashSet result = new LinkedHashSet($this$minus);
        CollectionsKt.removeAll((Collection)result, elements);
        return result;
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final <T> Set<T> minus(@NotNull Set<? extends T> $this$minus, @NotNull Iterable<? extends T> elements) {
        Intrinsics.checkNotNullParameter($this$minus, "$this$minus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        Collection<T> other = CollectionsKt.convertToSetForSetOperationWith(elements, (Iterable)$this$minus);
        if (other.isEmpty()) {
            return CollectionsKt.toSet((Iterable)$this$minus);
        }
        if (other instanceof Set) {
            void $this$filterNotTo$iv;
            Iterable iterable = $this$minus;
            Collection destination$iv = new LinkedHashSet();
            boolean $i$f$filterNotTo = false;
            Iterator iterator2 = $this$filterNotTo$iv.iterator();
            while (iterator2.hasNext()) {
                Object element$iv;
                Object it = element$iv = iterator2.next();
                boolean bl = false;
                if (other.contains(it)) continue;
                destination$iv.add(element$iv);
            }
            return (Set)destination$iv;
        }
        LinkedHashSet result = new LinkedHashSet($this$minus);
        result.removeAll(other);
        return result;
    }

    @NotNull
    public static final <T> Set<T> minus(@NotNull Set<? extends T> $this$minus, @NotNull Sequence<? extends T> elements) {
        Intrinsics.checkNotNullParameter($this$minus, "$this$minus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        LinkedHashSet result = new LinkedHashSet($this$minus);
        CollectionsKt.removeAll((Collection)result, elements);
        return result;
    }

    @InlineOnly
    private static final <T> Set<T> minusElement(Set<? extends T> $this$minusElement, T element) {
        int $i$f$minusElement = 0;
        return SetsKt.minus($this$minusElement, element);
    }

    @NotNull
    public static final <T> Set<T> plus(@NotNull Set<? extends T> $this$plus, T element) {
        Intrinsics.checkNotNullParameter($this$plus, "$this$plus");
        LinkedHashSet<T> result = new LinkedHashSet<T>(MapsKt.mapCapacity($this$plus.size() + 1));
        result.addAll((Collection)$this$plus);
        result.add(element);
        return result;
    }

    @NotNull
    public static final <T> Set<T> plus(@NotNull Set<? extends T> $this$plus, @NotNull T[] elements) {
        Intrinsics.checkNotNullParameter($this$plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        LinkedHashSet result = new LinkedHashSet(MapsKt.mapCapacity($this$plus.size() + elements.length));
        result.addAll($this$plus);
        CollectionsKt.addAll((Collection)result, elements);
        return result;
    }

    @NotNull
    public static final <T> Set<T> plus(@NotNull Set<? extends T> $this$plus, @NotNull Iterable<? extends T> elements) {
        int n;
        Intrinsics.checkNotNullParameter($this$plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        Integer n2 = CollectionsKt.collectionSizeOrNull(elements);
        if (n2 != null) {
            int n3;
            Integer n4 = n2;
            boolean bl = false;
            boolean bl2 = false;
            int it = ((Number)n4).intValue();
            boolean bl3 = false;
            n = n3 = $this$plus.size() + it;
        } else {
            n = $this$plus.size() * 2;
        }
        int n5 = MapsKt.mapCapacity(n);
        LinkedHashSet result = new LinkedHashSet(n5);
        result.addAll($this$plus);
        CollectionsKt.addAll((Collection)result, elements);
        return result;
    }

    @NotNull
    public static final <T> Set<T> plus(@NotNull Set<? extends T> $this$plus, @NotNull Sequence<? extends T> elements) {
        Intrinsics.checkNotNullParameter($this$plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        LinkedHashSet result = new LinkedHashSet(MapsKt.mapCapacity($this$plus.size() * 2));
        result.addAll($this$plus);
        CollectionsKt.addAll((Collection)result, elements);
        return result;
    }

    @InlineOnly
    private static final <T> Set<T> plusElement(Set<? extends T> $this$plusElement, T element) {
        int $i$f$plusElement = 0;
        return SetsKt.plus($this$plusElement, element);
    }
}

