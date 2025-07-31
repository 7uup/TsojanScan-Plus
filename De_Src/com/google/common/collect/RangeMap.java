/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Range;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Map;
import java.util.function.BiFunction;
import org.checkerframework.checker.nullness.qual.Nullable;

@DoNotMock(value="Use ImmutableRangeMap or TreeRangeMap")
@Beta
@GwtIncompatible
public interface RangeMap<K extends Comparable, V> {
    public @Nullable V get(K var1);

    public @Nullable Map.Entry<Range<K>, V> getEntry(K var1);

    public Range<K> span();

    public void put(Range<K> var1, V var2);

    public void putCoalescing(Range<K> var1, V var2);

    public void putAll(RangeMap<K, V> var1);

    public void clear();

    public void remove(Range<K> var1);

    public void merge(Range<K> var1, @Nullable V var2, BiFunction<? super V, ? super V, ? extends V> var3);

    public Map<Range<K>, V> asMapOfRanges();

    public Map<Range<K>, V> asDescendingMapOfRanges();

    public RangeMap<K, V> subRangeMap(Range<K> var1);

    public boolean equals(@Nullable Object var1);

    public int hashCode();

    public String toString();
}

