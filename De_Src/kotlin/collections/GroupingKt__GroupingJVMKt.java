/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.collections.Grouping;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000&\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0000\u001a0\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u0005H\u0007\u001aZ\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\t\"\u0004\b\u0002\u0010\b*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\u00072\u001e\u0010\n\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\f\u0012\u0004\u0012\u0002H\b0\u000bH\u0081\b\u00f8\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\r"}, d2={"eachCount", "", "K", "", "T", "Lkotlin/collections/Grouping;", "mapValuesInPlace", "", "R", "V", "f", "Lkotlin/Function1;", "", "kotlin-stdlib"}, xs="kotlin/collections/GroupingKt")
class GroupingKt__GroupingJVMKt {
    /*
     * WARNING - void declaration
     */
    @SinceKotlin(version="1.1")
    @NotNull
    public static final <T, K> Map<K, Integer> eachCount(@NotNull Grouping<T, ? extends K> $this$eachCount) {
        Serializable serializable;
        Map.Entry entry;
        Object e$iv$iv;
        void $this$foldTo$iv;
        Intrinsics.checkNotNullParameter($this$eachCount, "$this$eachCount");
        Object object = $this$eachCount;
        boolean bl = false;
        Map destination$iv = new LinkedHashMap();
        boolean $i$f$foldTo = false;
        void $this$aggregateTo$iv$iv = $this$foldTo$iv;
        boolean $i$f$aggregateTo = false;
        Iterator iterator22 = $this$aggregateTo$iv$iv.sourceIterator();
        boolean bl2 = false;
        Iterator iterator3 = iterator22;
        while (iterator3.hasNext()) {
            void acc;
            Serializable serializable2;
            void var18_26;
            void e$iv;
            void first$iv;
            void key$iv;
            e$iv$iv = iterator3.next();
            Object key$iv$iv = $this$aggregateTo$iv$iv.keyOf(e$iv$iv);
            Object accumulator$iv$iv = destination$iv.get(key$iv$iv);
            boolean bl3 = accumulator$iv$iv == null && !destination$iv.containsKey(key$iv$iv);
            Object t = e$iv$iv;
            Object v = accumulator$iv$iv;
            Object k = key$iv$iv;
            Object k2 = key$iv$iv;
            Map map = destination$iv;
            boolean bl4 = false;
            Map.Entry entry2 = key$iv;
            if (first$iv != false) {
                void var17_25 = e$iv;
                var18_26 = key$iv;
                entry = entry2;
                boolean bl5 = false;
                serializable = new Ref.IntRef();
                entry2 = entry;
                serializable2 = serializable;
            } else {
                void acc$iv;
                serializable2 = acc$iv;
            }
            void bl5 = e$iv;
            Ref.IntRef $noName_1 = (Ref.IntRef)serializable2;
            void $noName_0 = entry2;
            boolean bl6 = false;
            void var21_30 = acc;
            boolean bl7 = false;
            boolean bl8 = false;
            void $this$apply = var21_30;
            boolean bl9 = false;
            ++$this$apply.element;
            var18_26 = var21_30;
            map.put(k2, var18_26);
        }
        object = destination$iv;
        boolean bl10 = false;
        Iterable iterable = object.entrySet();
        boolean bl11 = false;
        for (Iterator iterator22 : iterable) {
            void it;
            Map.Entry entry3 = (Map.Entry)((Object)iterator22);
            boolean bl12 = false;
            Map.Entry entry4 = entry3;
            if (entry4 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableMap.MutableEntry<K, R>");
            }
            e$iv$iv = entry3;
            entry = TypeIntrinsics.asMutableMapEntry(entry4);
            boolean bl13 = false;
            serializable = ((Ref.IntRef)it.getValue()).element;
            entry.setValue(serializable);
        }
        return TypeIntrinsics.asMutableMap(object);
    }

    @PublishedApi
    @InlineOnly
    private static final <K, V, R> Map<K, R> mapValuesInPlace(Map<K, V> $this$mapValuesInPlace, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> f) {
        int $i$f$mapValuesInPlace = 0;
        Iterable $this$forEach$iv = $this$mapValuesInPlace.entrySet();
        boolean $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv) {
            Map.Entry it = (Map.Entry)element$iv;
            boolean bl = false;
            Map.Entry entry = it;
            if (entry == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableMap.MutableEntry<K, R>");
            }
            TypeIntrinsics.asMutableMapEntry(entry).setValue(f.invoke(it));
        }
        Map<K, V> map = $this$mapValuesInPlace;
        if (map == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableMap<K, R>");
        }
        return TypeIntrinsics.asMutableMap(map);
    }
}

