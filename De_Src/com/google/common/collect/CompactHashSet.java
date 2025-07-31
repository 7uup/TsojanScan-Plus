/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.CompactHashing;
import com.google.common.collect.Hashing;
import com.google.common.collect.ObjectArrays;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtIncompatible
class CompactHashSet<E>
extends AbstractSet<E>
implements Serializable {
    @VisibleForTesting
    static final double HASH_FLOODING_FPP = 0.001;
    private static final int MAX_HASH_BUCKET_LENGTH = 9;
    private transient @Nullable Object table;
    private transient int @Nullable [] entries;
    @VisibleForTesting
    transient Object @Nullable [] elements;
    private transient int metadata;
    private transient int size;

    public static <E> CompactHashSet<E> create() {
        return new CompactHashSet<E>();
    }

    public static <E> CompactHashSet<E> create(Collection<? extends E> collection) {
        CompactHashSet<E> set = CompactHashSet.createWithExpectedSize(collection.size());
        set.addAll(collection);
        return set;
    }

    @SafeVarargs
    public static <E> CompactHashSet<E> create(E ... elements) {
        CompactHashSet<E> set = CompactHashSet.createWithExpectedSize(elements.length);
        Collections.addAll(set, elements);
        return set;
    }

    public static <E> CompactHashSet<E> createWithExpectedSize(int expectedSize) {
        return new CompactHashSet<E>(expectedSize);
    }

    CompactHashSet() {
        this.init(3);
    }

    CompactHashSet(int expectedSize) {
        this.init(expectedSize);
    }

    void init(int expectedSize) {
        Preconditions.checkArgument(expectedSize >= 0, "Expected size must be >= 0");
        this.metadata = Ints.constrainToRange(expectedSize, 1, 0x3FFFFFFF);
    }

    @VisibleForTesting
    boolean needsAllocArrays() {
        return this.table == null;
    }

    @CanIgnoreReturnValue
    int allocArrays() {
        Preconditions.checkState(this.needsAllocArrays(), "Arrays already allocated");
        int expectedSize = this.metadata;
        int buckets = CompactHashing.tableSize(expectedSize);
        this.table = CompactHashing.createTable(buckets);
        this.setHashTableMask(buckets - 1);
        this.entries = new int[expectedSize];
        this.elements = new Object[expectedSize];
        return expectedSize;
    }

    @VisibleForTesting
    @Nullable Set<E> delegateOrNull() {
        if (this.table instanceof Set) {
            return (Set)this.table;
        }
        return null;
    }

    private Set<E> createHashFloodingResistantDelegate(int tableSize) {
        return new LinkedHashSet(tableSize, 1.0f);
    }

    @VisibleForTesting
    @CanIgnoreReturnValue
    Set<E> convertToHashFloodingResistantImplementation() {
        Set<Object> newDelegate = this.createHashFloodingResistantDelegate(this.hashTableMask() + 1);
        int i = this.firstEntryIndex();
        while (i >= 0) {
            newDelegate.add(this.elements[i]);
            i = this.getSuccessor(i);
        }
        this.table = newDelegate;
        this.entries = null;
        this.elements = null;
        this.incrementModCount();
        return newDelegate;
    }

    @VisibleForTesting
    boolean isUsingHashFloodingResistance() {
        return this.delegateOrNull() != null;
    }

    private void setHashTableMask(int mask) {
        int hashTableBits = 32 - Integer.numberOfLeadingZeros(mask);
        this.metadata = CompactHashing.maskCombine(this.metadata, hashTableBits, 31);
    }

    private int hashTableMask() {
        return (1 << (this.metadata & 0x1F)) - 1;
    }

    void incrementModCount() {
        this.metadata += 32;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean add(@Nullable E object) {
        int mask;
        Set<E> delegate;
        if (this.needsAllocArrays()) {
            this.allocArrays();
        }
        if ((delegate = this.delegateOrNull()) != null) {
            return delegate.add(object);
        }
        int[] entries = this.entries;
        Object[] elements = this.elements;
        int newEntryIndex = this.size;
        int newSize = newEntryIndex + 1;
        int hash = Hashing.smearedHash(object);
        int tableIndex = hash & (mask = this.hashTableMask());
        int next = CompactHashing.tableGet(this.table, tableIndex);
        if (next == 0) {
            if (newSize > mask) {
                mask = this.resizeTable(mask, CompactHashing.newCapacity(mask), hash, newEntryIndex);
            } else {
                CompactHashing.tableSet(this.table, tableIndex, newEntryIndex + 1);
            }
        } else {
            int entry;
            int hashPrefix = CompactHashing.getHashPrefix(hash, mask);
            int bucketLength = 0;
            do {
                int entryIndex;
                if (CompactHashing.getHashPrefix(entry = entries[entryIndex = next - 1], mask) == hashPrefix && Objects.equal(object, elements[entryIndex])) {
                    return false;
                }
                next = CompactHashing.getNext(entry, mask);
                ++bucketLength;
            } while (next != 0);
            if (bucketLength >= 9) {
                return this.convertToHashFloodingResistantImplementation().add(object);
            }
            if (newSize > mask) {
                mask = this.resizeTable(mask, CompactHashing.newCapacity(mask), hash, newEntryIndex);
            } else {
                entries[entryIndex] = CompactHashing.maskCombine(entry, newEntryIndex + 1, mask);
            }
        }
        this.resizeMeMaybe(newSize);
        this.insertEntry(newEntryIndex, object, hash, mask);
        this.size = newSize;
        this.incrementModCount();
        return true;
    }

    void insertEntry(int entryIndex, @Nullable E object, int hash, int mask) {
        this.entries[entryIndex] = CompactHashing.maskCombine(hash, 0, mask);
        this.elements[entryIndex] = object;
    }

    private void resizeMeMaybe(int newSize) {
        int newCapacity;
        int entriesSize = this.entries.length;
        if (newSize > entriesSize && (newCapacity = Math.min(0x3FFFFFFF, entriesSize + Math.max(1, entriesSize >>> 1) | 1)) != entriesSize) {
            this.resizeEntries(newCapacity);
        }
    }

    void resizeEntries(int newCapacity) {
        this.entries = Arrays.copyOf(this.entries, newCapacity);
        this.elements = Arrays.copyOf(this.elements, newCapacity);
    }

    @CanIgnoreReturnValue
    private int resizeTable(int mask, int newCapacity, int targetHash, int targetEntryIndex) {
        Object newTable = CompactHashing.createTable(newCapacity);
        int newMask = newCapacity - 1;
        if (targetEntryIndex != 0) {
            CompactHashing.tableSet(newTable, targetHash & newMask, targetEntryIndex + 1);
        }
        Object table = this.table;
        int[] entries = this.entries;
        for (int tableIndex = 0; tableIndex <= mask; ++tableIndex) {
            int next = CompactHashing.tableGet(table, tableIndex);
            while (next != 0) {
                int entryIndex = next - 1;
                int entry = entries[entryIndex];
                int hash = CompactHashing.getHashPrefix(entry, mask) | tableIndex;
                int newTableIndex = hash & newMask;
                int newNext = CompactHashing.tableGet(newTable, newTableIndex);
                CompactHashing.tableSet(newTable, newTableIndex, next);
                entries[entryIndex] = CompactHashing.maskCombine(hash, newNext, newMask);
                next = CompactHashing.getNext(entry, mask);
            }
        }
        this.table = newTable;
        this.setHashTableMask(newMask);
        return newMask;
    }

    @Override
    public boolean contains(@Nullable Object object) {
        int entry;
        int mask;
        if (this.needsAllocArrays()) {
            return false;
        }
        @Nullable Set<E> delegate = this.delegateOrNull();
        if (delegate != null) {
            return delegate.contains(object);
        }
        int hash = Hashing.smearedHash(object);
        int next = CompactHashing.tableGet(this.table, hash & (mask = this.hashTableMask()));
        if (next == 0) {
            return false;
        }
        int hashPrefix = CompactHashing.getHashPrefix(hash, mask);
        do {
            int entryIndex;
            if (CompactHashing.getHashPrefix(entry = this.entries[entryIndex = next - 1], mask) != hashPrefix || !Objects.equal(object, this.elements[entryIndex])) continue;
            return true;
        } while ((next = CompactHashing.getNext(entry, mask)) != 0);
        return false;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean remove(@Nullable Object object) {
        if (this.needsAllocArrays()) {
            return false;
        }
        @Nullable Set<E> delegate = this.delegateOrNull();
        if (delegate != null) {
            return delegate.remove(object);
        }
        int mask = this.hashTableMask();
        int index = CompactHashing.remove(object, null, mask, this.table, this.entries, this.elements, null);
        if (index == -1) {
            return false;
        }
        this.moveLastEntry(index, mask);
        --this.size;
        this.incrementModCount();
        return true;
    }

    void moveLastEntry(int dstIndex, int mask) {
        int srcIndex = this.size() - 1;
        if (dstIndex < srcIndex) {
            int srcNext;
            Object object;
            this.elements[dstIndex] = object = this.elements[srcIndex];
            this.elements[srcIndex] = null;
            this.entries[dstIndex] = this.entries[srcIndex];
            this.entries[srcIndex] = 0;
            int tableIndex = Hashing.smearedHash(object) & mask;
            int next = CompactHashing.tableGet(this.table, tableIndex);
            if (next == (srcNext = srcIndex + 1)) {
                CompactHashing.tableSet(this.table, tableIndex, dstIndex + 1);
            } else {
                int entryIndex;
                int entry;
                while ((next = CompactHashing.getNext(entry = this.entries[entryIndex = next - 1], mask)) != srcNext) {
                }
                this.entries[entryIndex] = CompactHashing.maskCombine(entry, dstIndex + 1, mask);
            }
        } else {
            this.elements[dstIndex] = null;
            this.entries[dstIndex] = 0;
        }
    }

    int firstEntryIndex() {
        return this.isEmpty() ? -1 : 0;
    }

    int getSuccessor(int entryIndex) {
        return entryIndex + 1 < this.size ? entryIndex + 1 : -1;
    }

    int adjustAfterRemove(int indexBeforeRemove, int indexRemoved) {
        return indexBeforeRemove - 1;
    }

    @Override
    public Iterator<E> iterator() {
        @Nullable Set<E> delegate = this.delegateOrNull();
        if (delegate != null) {
            return delegate.iterator();
        }
        return new Iterator<E>(){
            int expectedMetadata;
            int currentIndex;
            int indexToRemove;
            {
                this.expectedMetadata = CompactHashSet.this.metadata;
                this.currentIndex = CompactHashSet.this.firstEntryIndex();
                this.indexToRemove = -1;
            }

            @Override
            public boolean hasNext() {
                return this.currentIndex >= 0;
            }

            @Override
            public E next() {
                this.checkForConcurrentModification();
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                this.indexToRemove = this.currentIndex;
                Object result = CompactHashSet.this.elements[this.currentIndex];
                this.currentIndex = CompactHashSet.this.getSuccessor(this.currentIndex);
                return result;
            }

            @Override
            public void remove() {
                this.checkForConcurrentModification();
                CollectPreconditions.checkRemove(this.indexToRemove >= 0);
                this.incrementExpectedModCount();
                CompactHashSet.this.remove(CompactHashSet.this.elements[this.indexToRemove]);
                this.currentIndex = CompactHashSet.this.adjustAfterRemove(this.currentIndex, this.indexToRemove);
                this.indexToRemove = -1;
            }

            void incrementExpectedModCount() {
                this.expectedMetadata += 32;
            }

            private void checkForConcurrentModification() {
                if (CompactHashSet.this.metadata != this.expectedMetadata) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    @Override
    public Spliterator<E> spliterator() {
        if (this.needsAllocArrays()) {
            return Spliterators.spliterator(new Object[0], 17);
        }
        @Nullable Set<E> delegate = this.delegateOrNull();
        return delegate != null ? delegate.spliterator() : Spliterators.spliterator(this.elements, 0, this.size, 17);
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Preconditions.checkNotNull(action);
        @Nullable Set<E> delegate = this.delegateOrNull();
        if (delegate != null) {
            delegate.forEach(action);
        } else {
            int i = this.firstEntryIndex();
            while (i >= 0) {
                action.accept(this.elements[i]);
                i = this.getSuccessor(i);
            }
        }
    }

    @Override
    public int size() {
        @Nullable Set<E> delegate = this.delegateOrNull();
        return delegate != null ? delegate.size() : this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public Object[] toArray() {
        if (this.needsAllocArrays()) {
            return new Object[0];
        }
        @Nullable Set<E> delegate = this.delegateOrNull();
        return delegate != null ? delegate.toArray() : Arrays.copyOf(this.elements, this.size);
    }

    @Override
    @CanIgnoreReturnValue
    public <T> T[] toArray(T[] a) {
        if (this.needsAllocArrays()) {
            if (a.length > 0) {
                a[0] = null;
            }
            return a;
        }
        @Nullable Set<E> delegate = this.delegateOrNull();
        return delegate != null ? delegate.toArray(a) : ObjectArrays.toArrayImpl(this.elements, 0, this.size, a);
    }

    public void trimToSize() {
        int mask;
        int minimumTableSize;
        if (this.needsAllocArrays()) {
            return;
        }
        @Nullable Set<E> delegate = this.delegateOrNull();
        if (delegate != null) {
            Set<E> newDelegate = this.createHashFloodingResistantDelegate(this.size());
            newDelegate.addAll(delegate);
            this.table = newDelegate;
            return;
        }
        int size = this.size;
        if (size < this.entries.length) {
            this.resizeEntries(size);
        }
        if ((minimumTableSize = CompactHashing.tableSize(size)) < (mask = this.hashTableMask())) {
            this.resizeTable(mask, minimumTableSize, 0, 0);
        }
    }

    @Override
    public void clear() {
        if (this.needsAllocArrays()) {
            return;
        }
        this.incrementModCount();
        @Nullable Set<E> delegate = this.delegateOrNull();
        if (delegate != null) {
            this.metadata = Ints.constrainToRange(this.size(), 3, 0x3FFFFFFF);
            delegate.clear();
            this.table = null;
            this.size = 0;
        } else {
            Arrays.fill(this.elements, 0, this.size, null);
            CompactHashing.tableClear(this.table);
            Arrays.fill(this.entries, 0, this.size, 0);
            this.size = 0;
        }
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeInt(this.size());
        for (E e : this) {
            stream.writeObject(e);
        }
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        int elementCount = stream.readInt();
        if (elementCount < 0) {
            throw new InvalidObjectException("Invalid size: " + elementCount);
        }
        this.init(elementCount);
        for (int i = 0; i < elementCount; ++i) {
            Object element = stream.readObject();
            this.add(element);
        }
    }
}

