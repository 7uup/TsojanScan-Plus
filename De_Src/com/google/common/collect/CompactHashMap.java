/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMapEntry;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.CollectSpliterators;
import com.google.common.collect.CompactHashing;
import com.google.common.collect.Hashing;
import com.google.common.collect.Maps;
import com.google.common.collect.ObjectArrays;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtIncompatible
class CompactHashMap<K, V>
extends AbstractMap<K, V>
implements Serializable {
    private static final Object NOT_FOUND = new Object();
    @VisibleForTesting
    static final double HASH_FLOODING_FPP = 0.001;
    private static final int MAX_HASH_BUCKET_LENGTH = 9;
    private transient @Nullable Object table;
    @VisibleForTesting
    transient int @Nullable [] entries;
    @VisibleForTesting
    transient Object @Nullable [] keys;
    @VisibleForTesting
    transient Object @Nullable [] values;
    private transient int metadata;
    private transient int size;
    private transient @Nullable Set<K> keySetView;
    private transient @Nullable Set<Map.Entry<K, V>> entrySetView;
    private transient @Nullable Collection<V> valuesView;

    public static <K, V> CompactHashMap<K, V> create() {
        return new CompactHashMap<K, V>();
    }

    public static <K, V> CompactHashMap<K, V> createWithExpectedSize(int expectedSize) {
        return new CompactHashMap<K, V>(expectedSize);
    }

    CompactHashMap() {
        this.init(3);
    }

    CompactHashMap(int expectedSize) {
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
        this.keys = new Object[expectedSize];
        this.values = new Object[expectedSize];
        return expectedSize;
    }

    @VisibleForTesting
    @Nullable Map<K, V> delegateOrNull() {
        if (this.table instanceof Map) {
            return (Map)this.table;
        }
        return null;
    }

    Map<K, V> createHashFloodingResistantDelegate(int tableSize) {
        return new LinkedHashMap(tableSize, 1.0f);
    }

    @VisibleForTesting
    @CanIgnoreReturnValue
    Map<K, V> convertToHashFloodingResistantImplementation() {
        Map<Object, Object> newDelegate = this.createHashFloodingResistantDelegate(this.hashTableMask() + 1);
        int i = this.firstEntryIndex();
        while (i >= 0) {
            newDelegate.put(this.keys[i], this.values[i]);
            i = this.getSuccessor(i);
        }
        this.table = newDelegate;
        this.entries = null;
        this.keys = null;
        this.values = null;
        this.incrementModCount();
        return newDelegate;
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

    void accessEntry(int index) {
    }

    @Override
    @CanIgnoreReturnValue
    public @Nullable V put(@Nullable K key, @Nullable V value) {
        int mask;
        Map<K, V> delegate;
        if (this.needsAllocArrays()) {
            this.allocArrays();
        }
        if ((delegate = this.delegateOrNull()) != null) {
            return delegate.put(key, value);
        }
        int[] entries = this.entries;
        Object[] keys2 = this.keys;
        Object[] values2 = this.values;
        int newEntryIndex = this.size;
        int newSize = newEntryIndex + 1;
        int hash = Hashing.smearedHash(key);
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
                if (CompactHashing.getHashPrefix(entry = entries[entryIndex = next - 1], mask) == hashPrefix && Objects.equal(key, keys2[entryIndex])) {
                    Object oldValue = values2[entryIndex];
                    values2[entryIndex] = value;
                    this.accessEntry(entryIndex);
                    return (V)oldValue;
                }
                next = CompactHashing.getNext(entry, mask);
                ++bucketLength;
            } while (next != 0);
            if (bucketLength >= 9) {
                return this.convertToHashFloodingResistantImplementation().put(key, value);
            }
            if (newSize > mask) {
                mask = this.resizeTable(mask, CompactHashing.newCapacity(mask), hash, newEntryIndex);
            } else {
                entries[entryIndex] = CompactHashing.maskCombine(entry, newEntryIndex + 1, mask);
            }
        }
        this.resizeMeMaybe(newSize);
        this.insertEntry(newEntryIndex, key, value, hash, mask);
        this.size = newSize;
        this.incrementModCount();
        return null;
    }

    void insertEntry(int entryIndex, @Nullable K key, @Nullable V value, int hash, int mask) {
        this.entries[entryIndex] = CompactHashing.maskCombine(hash, 0, mask);
        this.keys[entryIndex] = key;
        this.values[entryIndex] = value;
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
        this.keys = Arrays.copyOf(this.keys, newCapacity);
        this.values = Arrays.copyOf(this.values, newCapacity);
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

    private int indexOf(@Nullable Object key) {
        int entry;
        int mask;
        if (this.needsAllocArrays()) {
            return -1;
        }
        int hash = Hashing.smearedHash(key);
        int next = CompactHashing.tableGet(this.table, hash & (mask = this.hashTableMask()));
        if (next == 0) {
            return -1;
        }
        int hashPrefix = CompactHashing.getHashPrefix(hash, mask);
        do {
            int entryIndex;
            if (CompactHashing.getHashPrefix(entry = this.entries[entryIndex = next - 1], mask) != hashPrefix || !Objects.equal(key, this.keys[entryIndex])) continue;
            return entryIndex;
        } while ((next = CompactHashing.getNext(entry, mask)) != 0);
        return -1;
    }

    @Override
    public boolean containsKey(@Nullable Object key) {
        @Nullable Map<K, V> delegate = this.delegateOrNull();
        return delegate != null ? delegate.containsKey(key) : this.indexOf(key) != -1;
    }

    @Override
    public V get(@Nullable Object key) {
        @Nullable Map<K, V> delegate = this.delegateOrNull();
        if (delegate != null) {
            return delegate.get(key);
        }
        int index = this.indexOf(key);
        if (index == -1) {
            return null;
        }
        this.accessEntry(index);
        return (V)this.values[index];
    }

    @Override
    @CanIgnoreReturnValue
    public @Nullable V remove(@Nullable Object key) {
        @Nullable Map<K, V> delegate = this.delegateOrNull();
        if (delegate != null) {
            return delegate.remove(key);
        }
        Object oldValue = this.removeHelper(key);
        return (V)(oldValue == NOT_FOUND ? null : oldValue);
    }

    private @Nullable Object removeHelper(@Nullable Object key) {
        if (this.needsAllocArrays()) {
            return NOT_FOUND;
        }
        int mask = this.hashTableMask();
        int index = CompactHashing.remove(key, null, mask, this.table, this.entries, this.keys, null);
        if (index == -1) {
            return NOT_FOUND;
        }
        Object oldValue = this.values[index];
        this.moveLastEntry(index, mask);
        --this.size;
        this.incrementModCount();
        return oldValue;
    }

    void moveLastEntry(int dstIndex, int mask) {
        int srcIndex = this.size() - 1;
        if (dstIndex < srcIndex) {
            int srcNext;
            Object key;
            this.keys[dstIndex] = key = this.keys[srcIndex];
            this.values[dstIndex] = this.values[srcIndex];
            this.keys[srcIndex] = null;
            this.values[srcIndex] = null;
            this.entries[dstIndex] = this.entries[srcIndex];
            this.entries[srcIndex] = 0;
            int tableIndex = Hashing.smearedHash(key) & mask;
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
            this.keys[dstIndex] = null;
            this.values[dstIndex] = null;
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
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Preconditions.checkNotNull(function);
        @Nullable Map<? super K, ? extends V> delegate = this.delegateOrNull();
        if (delegate != null) {
            delegate.replaceAll(function);
        } else {
            for (int i = 0; i < this.size; ++i) {
                this.values[i] = function.apply(this.keys[i], this.values[i]);
            }
        }
    }

    @Override
    public Set<K> keySet() {
        return this.keySetView == null ? (this.keySetView = this.createKeySet()) : this.keySetView;
    }

    Set<K> createKeySet() {
        return new KeySetView();
    }

    Iterator<K> keySetIterator() {
        @Nullable Map<K, V> delegate = this.delegateOrNull();
        if (delegate != null) {
            return delegate.keySet().iterator();
        }
        return new Itr<K>(){

            @Override
            K getOutput(int entry) {
                return CompactHashMap.this.keys[entry];
            }
        };
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        Preconditions.checkNotNull(action);
        @Nullable Map<? super K, ? super V> delegate = this.delegateOrNull();
        if (delegate != null) {
            delegate.forEach(action);
        } else {
            int i = this.firstEntryIndex();
            while (i >= 0) {
                action.accept(this.keys[i], this.values[i]);
                i = this.getSuccessor(i);
            }
        }
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return this.entrySetView == null ? (this.entrySetView = this.createEntrySet()) : this.entrySetView;
    }

    Set<Map.Entry<K, V>> createEntrySet() {
        return new EntrySetView();
    }

    Iterator<Map.Entry<K, V>> entrySetIterator() {
        @Nullable Map<K, V> delegate = this.delegateOrNull();
        if (delegate != null) {
            return delegate.entrySet().iterator();
        }
        return new Itr<Map.Entry<K, V>>(){

            @Override
            Map.Entry<K, V> getOutput(int entry) {
                return new MapEntry(entry);
            }
        };
    }

    @Override
    public int size() {
        @Nullable Map<K, V> delegate = this.delegateOrNull();
        return delegate != null ? delegate.size() : this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean containsValue(@Nullable Object value) {
        @Nullable Map<K, V> delegate = this.delegateOrNull();
        if (delegate != null) {
            return delegate.containsValue(value);
        }
        for (int i = 0; i < this.size; ++i) {
            if (!Objects.equal(value, this.values[i])) continue;
            return true;
        }
        return false;
    }

    @Override
    public Collection<V> values() {
        return this.valuesView == null ? (this.valuesView = this.createValues()) : this.valuesView;
    }

    Collection<V> createValues() {
        return new ValuesView();
    }

    Iterator<V> valuesIterator() {
        @Nullable Map<K, V> delegate = this.delegateOrNull();
        if (delegate != null) {
            return delegate.values().iterator();
        }
        return new Itr<V>(){

            @Override
            V getOutput(int entry) {
                return CompactHashMap.this.values[entry];
            }
        };
    }

    public void trimToSize() {
        int mask;
        int minimumTableSize;
        if (this.needsAllocArrays()) {
            return;
        }
        @Nullable Map<K, V> delegate = this.delegateOrNull();
        if (delegate != null) {
            Map<K, V> newDelegate = this.createHashFloodingResistantDelegate(this.size());
            newDelegate.putAll(delegate);
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
        @Nullable Map<K, V> delegate = this.delegateOrNull();
        if (delegate != null) {
            this.metadata = Ints.constrainToRange(this.size(), 3, 0x3FFFFFFF);
            delegate.clear();
            this.table = null;
            this.size = 0;
        } else {
            Arrays.fill(this.keys, 0, this.size, null);
            Arrays.fill(this.values, 0, this.size, null);
            CompactHashing.tableClear(this.table);
            Arrays.fill(this.entries, 0, this.size, 0);
            this.size = 0;
        }
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeInt(this.size());
        Iterator<Map.Entry<K, V>> entryIterator = this.entrySetIterator();
        while (entryIterator.hasNext()) {
            Map.Entry<K, V> e = entryIterator.next();
            stream.writeObject(e.getKey());
            stream.writeObject(e.getValue());
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
            Object key = stream.readObject();
            Object value = stream.readObject();
            this.put(key, value);
        }
    }

    class ValuesView
    extends Maps.Values<K, V> {
        ValuesView() {
            super(CompactHashMap.this);
        }

        @Override
        public Iterator<V> iterator() {
            return CompactHashMap.this.valuesIterator();
        }

        @Override
        public void forEach(Consumer<? super V> action) {
            Preconditions.checkNotNull(action);
            @Nullable Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            if (delegate != null) {
                delegate.values().forEach(action);
            } else {
                int i = CompactHashMap.this.firstEntryIndex();
                while (i >= 0) {
                    action.accept(CompactHashMap.this.values[i]);
                    i = CompactHashMap.this.getSuccessor(i);
                }
            }
        }

        @Override
        public Spliterator<V> spliterator() {
            if (CompactHashMap.this.needsAllocArrays()) {
                return Spliterators.spliterator(new Object[0], 16);
            }
            @Nullable Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            return delegate != null ? delegate.values().spliterator() : Spliterators.spliterator(CompactHashMap.this.values, 0, CompactHashMap.this.size, 16);
        }

        @Override
        public Object[] toArray() {
            if (CompactHashMap.this.needsAllocArrays()) {
                return new Object[0];
            }
            @Nullable Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            return delegate != null ? delegate.values().toArray() : ObjectArrays.copyAsObjectArray(CompactHashMap.this.values, 0, CompactHashMap.this.size);
        }

        @Override
        public <T> T[] toArray(T[] a) {
            if (CompactHashMap.this.needsAllocArrays()) {
                if (a.length > 0) {
                    a[0] = null;
                }
                return a;
            }
            @Nullable Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            return delegate != null ? delegate.values().toArray(a) : ObjectArrays.toArrayImpl(CompactHashMap.this.values, 0, CompactHashMap.this.size, a);
        }
    }

    final class MapEntry
    extends AbstractMapEntry<K, V> {
        private final @Nullable K key;
        private int lastKnownIndex;

        MapEntry(int index) {
            this.key = CompactHashMap.this.keys[index];
            this.lastKnownIndex = index;
        }

        @Override
        public @Nullable K getKey() {
            return this.key;
        }

        private void updateLastKnownIndex() {
            if (this.lastKnownIndex == -1 || this.lastKnownIndex >= CompactHashMap.this.size() || !Objects.equal(this.key, CompactHashMap.this.keys[this.lastKnownIndex])) {
                this.lastKnownIndex = CompactHashMap.this.indexOf(this.key);
            }
        }

        @Override
        public @Nullable V getValue() {
            @Nullable Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            if (delegate != null) {
                return delegate.get(this.key);
            }
            this.updateLastKnownIndex();
            return this.lastKnownIndex == -1 ? null : CompactHashMap.this.values[this.lastKnownIndex];
        }

        @Override
        public V setValue(V value) {
            @Nullable Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            if (delegate != null) {
                return delegate.put(this.key, value);
            }
            this.updateLastKnownIndex();
            if (this.lastKnownIndex == -1) {
                CompactHashMap.this.put(this.key, value);
                return null;
            }
            Object old = CompactHashMap.this.values[this.lastKnownIndex];
            CompactHashMap.this.values[this.lastKnownIndex] = value;
            return old;
        }
    }

    class EntrySetView
    extends Maps.EntrySet<K, V> {
        EntrySetView() {
        }

        @Override
        Map<K, V> map() {
            return CompactHashMap.this;
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return CompactHashMap.this.entrySetIterator();
        }

        @Override
        public Spliterator<Map.Entry<K, V>> spliterator() {
            @Nullable Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            return delegate != null ? delegate.entrySet().spliterator() : CollectSpliterators.indexed(CompactHashMap.this.size, 17, x$0 -> new MapEntry(x$0));
        }

        @Override
        public boolean contains(@Nullable Object o) {
            @Nullable Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            if (delegate != null) {
                return delegate.entrySet().contains(o);
            }
            if (o instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry)o;
                int index = CompactHashMap.this.indexOf(entry.getKey());
                return index != -1 && Objects.equal(CompactHashMap.this.values[index], entry.getValue());
            }
            return false;
        }

        @Override
        public boolean remove(@Nullable Object o) {
            @Nullable Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            if (delegate != null) {
                return delegate.entrySet().remove(o);
            }
            if (o instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry)o;
                if (CompactHashMap.this.needsAllocArrays()) {
                    return false;
                }
                int mask = CompactHashMap.this.hashTableMask();
                int index = CompactHashing.remove(entry.getKey(), entry.getValue(), mask, CompactHashMap.this.table, CompactHashMap.this.entries, CompactHashMap.this.keys, CompactHashMap.this.values);
                if (index == -1) {
                    return false;
                }
                CompactHashMap.this.moveLastEntry(index, mask);
                CompactHashMap.this.size--;
                CompactHashMap.this.incrementModCount();
                return true;
            }
            return false;
        }
    }

    class KeySetView
    extends Maps.KeySet<K, V> {
        KeySetView() {
            super(CompactHashMap.this);
        }

        @Override
        public Object[] toArray() {
            if (CompactHashMap.this.needsAllocArrays()) {
                return new Object[0];
            }
            @Nullable Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            return delegate != null ? delegate.keySet().toArray() : ObjectArrays.copyAsObjectArray(CompactHashMap.this.keys, 0, CompactHashMap.this.size);
        }

        @Override
        public <T> T[] toArray(T[] a) {
            if (CompactHashMap.this.needsAllocArrays()) {
                if (a.length > 0) {
                    a[0] = null;
                }
                return a;
            }
            @Nullable Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            return delegate != null ? delegate.keySet().toArray(a) : ObjectArrays.toArrayImpl(CompactHashMap.this.keys, 0, CompactHashMap.this.size, a);
        }

        @Override
        public boolean remove(@Nullable Object o) {
            @Nullable Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            return delegate != null ? delegate.keySet().remove(o) : CompactHashMap.this.removeHelper(o) != NOT_FOUND;
        }

        @Override
        public Iterator<K> iterator() {
            return CompactHashMap.this.keySetIterator();
        }

        @Override
        public Spliterator<K> spliterator() {
            if (CompactHashMap.this.needsAllocArrays()) {
                return Spliterators.spliterator(new Object[0], 17);
            }
            @Nullable Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            return delegate != null ? delegate.keySet().spliterator() : Spliterators.spliterator(CompactHashMap.this.keys, 0, CompactHashMap.this.size, 17);
        }

        @Override
        public void forEach(Consumer<? super K> action) {
            Preconditions.checkNotNull(action);
            @Nullable Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            if (delegate != null) {
                delegate.keySet().forEach(action);
            } else {
                int i = CompactHashMap.this.firstEntryIndex();
                while (i >= 0) {
                    action.accept(CompactHashMap.this.keys[i]);
                    i = CompactHashMap.this.getSuccessor(i);
                }
            }
        }
    }

    private abstract class Itr<T>
    implements Iterator<T> {
        int expectedMetadata;
        int currentIndex;
        int indexToRemove;

        private Itr() {
            this.expectedMetadata = CompactHashMap.this.metadata;
            this.currentIndex = CompactHashMap.this.firstEntryIndex();
            this.indexToRemove = -1;
        }

        @Override
        public boolean hasNext() {
            return this.currentIndex >= 0;
        }

        abstract T getOutput(int var1);

        @Override
        public T next() {
            this.checkForConcurrentModification();
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            this.indexToRemove = this.currentIndex;
            T result = this.getOutput(this.currentIndex);
            this.currentIndex = CompactHashMap.this.getSuccessor(this.currentIndex);
            return result;
        }

        @Override
        public void remove() {
            this.checkForConcurrentModification();
            CollectPreconditions.checkRemove(this.indexToRemove >= 0);
            this.incrementExpectedModCount();
            CompactHashMap.this.remove(CompactHashMap.this.keys[this.indexToRemove]);
            this.currentIndex = CompactHashMap.this.adjustAfterRemove(this.currentIndex, this.indexToRemove);
            this.indexToRemove = -1;
        }

        void incrementExpectedModCount() {
            this.expectedMetadata += 32;
        }

        private void checkForConcurrentModification() {
            if (CompactHashMap.this.metadata != this.expectedMetadata) {
                throw new ConcurrentModificationException();
            }
        }
    }
}

