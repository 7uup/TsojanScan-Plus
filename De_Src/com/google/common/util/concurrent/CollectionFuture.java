/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.AggregateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
abstract class CollectionFuture<V, C>
extends AggregateFuture<V, C> {
    private List<Present<V>> values;

    CollectionFuture(ImmutableCollection<? extends ListenableFuture<? extends V>> futures, boolean allMustSucceed) {
        super(futures, allMustSucceed, true);
        ImmutableList<Present<V>> values2 = futures.isEmpty() ? ImmutableList.of() : Lists.newArrayListWithCapacity(futures.size());
        for (int i = 0; i < futures.size(); ++i) {
            values2.add(null);
        }
        this.values = values2;
    }

    @Override
    final void collectOneValue(int index, @Nullable V returnValue) {
        List<Present<V>> localValues = this.values;
        if (localValues != null) {
            localValues.set(index, new Present<V>(returnValue));
        }
    }

    @Override
    final void handleAllCompleted() {
        List<Present<V>> localValues = this.values;
        if (localValues != null) {
            this.set(this.combine(localValues));
        }
    }

    @Override
    void releaseResources(AggregateFuture.ReleaseResourcesReason reason) {
        super.releaseResources(reason);
        this.values = null;
    }

    abstract C combine(List<Present<V>> var1);

    private static final class Present<V> {
        V value;

        Present(V value) {
            this.value = value;
        }
    }

    static final class ListFuture<V>
    extends CollectionFuture<V, List<V>> {
        ListFuture(ImmutableCollection<? extends ListenableFuture<? extends V>> futures, boolean allMustSucceed) {
            super(futures, allMustSucceed);
            this.init();
        }

        @Override
        public List<V> combine(List<Present<V>> values2) {
            ArrayList<Object> result = Lists.newArrayListWithCapacity(values2.size());
            for (Present<V> element : values2) {
                result.add(element != null ? (Object)element.value : null);
            }
            return Collections.unmodifiableList(result);
        }
    }
}

