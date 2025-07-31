/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.CompactHashSet;
import com.google.common.collect.ObjectArrays;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtIncompatible
class CompactLinkedHashSet<E>
extends CompactHashSet<E> {
    private static final int ENDPOINT = -2;
    private transient int @Nullable [] predecessor;
    private transient int @Nullable [] successor;
    private transient int firstEntry;
    private transient int lastEntry;

    public static <E> CompactLinkedHashSet<E> create() {
        return new CompactLinkedHashSet<E>();
    }

    public static <E> CompactLinkedHashSet<E> create(Collection<? extends E> collection) {
        CompactLinkedHashSet<E> set = CompactLinkedHashSet.createWithExpectedSize(collection.size());
        set.addAll(collection);
        return set;
    }

    @SafeVarargs
    public static <E> CompactLinkedHashSet<E> create(E ... elements) {
        CompactLinkedHashSet<E> set = CompactLinkedHashSet.createWithExpectedSize(elements.length);
        Collections.addAll(set, elements);
        return set;
    }

    public static <E> CompactLinkedHashSet<E> createWithExpectedSize(int expectedSize) {
        return new CompactLinkedHashSet<E>(expectedSize);
    }

    CompactLinkedHashSet() {
    }

    CompactLinkedHashSet(int expectedSize) {
        super(expectedSize);
    }

    @Override
    void init(int expectedSize) {
        super.init(expectedSize);
        this.firstEntry = -2;
        this.lastEntry = -2;
    }

    @Override
    int allocArrays() {
        int expectedSize = super.allocArrays();
        this.predecessor = new int[expectedSize];
        this.successor = new int[expectedSize];
        return expectedSize;
    }

    @Override
    @CanIgnoreReturnValue
    Set<E> convertToHashFloodingResistantImplementation() {
        Set result = super.convertToHashFloodingResistantImplementation();
        this.predecessor = null;
        this.successor = null;
        return result;
    }

    private int getPredecessor(int entry) {
        return this.predecessor[entry] - 1;
    }

    @Override
    int getSuccessor(int entry) {
        return this.successor[entry] - 1;
    }

    private void setSuccessor(int entry, int succ) {
        this.successor[entry] = succ + 1;
    }

    private void setPredecessor(int entry, int pred) {
        this.predecessor[entry] = pred + 1;
    }

    private void setSucceeds(int pred, int succ) {
        if (pred == -2) {
            this.firstEntry = succ;
        } else {
            this.setSuccessor(pred, succ);
        }
        if (succ == -2) {
            this.lastEntry = pred;
        } else {
            this.setPredecessor(succ, pred);
        }
    }

    @Override
    void insertEntry(int entryIndex, @Nullable E object, int hash, int mask) {
        super.insertEntry(entryIndex, object, hash, mask);
        this.setSucceeds(this.lastEntry, entryIndex);
        this.setSucceeds(entryIndex, -2);
    }

    @Override
    void moveLastEntry(int dstIndex, int mask) {
        int srcIndex = this.size() - 1;
        super.moveLastEntry(dstIndex, mask);
        this.setSucceeds(this.getPredecessor(dstIndex), this.getSuccessor(dstIndex));
        if (dstIndex < srcIndex) {
            this.setSucceeds(this.getPredecessor(srcIndex), dstIndex);
            this.setSucceeds(dstIndex, this.getSuccessor(srcIndex));
        }
        this.predecessor[srcIndex] = 0;
        this.successor[srcIndex] = 0;
    }

    @Override
    void resizeEntries(int newCapacity) {
        super.resizeEntries(newCapacity);
        this.predecessor = Arrays.copyOf(this.predecessor, newCapacity);
        this.successor = Arrays.copyOf(this.successor, newCapacity);
    }

    @Override
    int firstEntryIndex() {
        return this.firstEntry;
    }

    @Override
    int adjustAfterRemove(int indexBeforeRemove, int indexRemoved) {
        return indexBeforeRemove >= this.size() ? indexRemoved : indexBeforeRemove;
    }

    @Override
    public Object[] toArray() {
        return ObjectArrays.toArrayImpl(this);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return ObjectArrays.toArrayImpl(this, a);
    }

    @Override
    public Spliterator<E> spliterator() {
        return Spliterators.spliterator(this, 17);
    }

    @Override
    public void clear() {
        if (this.needsAllocArrays()) {
            return;
        }
        this.firstEntry = -2;
        this.lastEntry = -2;
        if (this.predecessor != null) {
            Arrays.fill(this.predecessor, 0, this.size(), 0);
            Arrays.fill(this.successor, 0, this.size(), 0);
        }
        super.clear();
    }
}

