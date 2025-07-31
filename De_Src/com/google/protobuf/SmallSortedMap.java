/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.FieldSet;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class SmallSortedMap<K extends Comparable<K>, V>
extends AbstractMap<K, V> {
    private final int maxArraySize;
    private List<Entry> entryList;
    private Map<K, V> overflowEntries;
    private boolean isImmutable;
    private volatile EntrySet lazyEntrySet;

    static <FieldDescriptorType extends FieldSet.FieldDescriptorLite<FieldDescriptorType>> SmallSortedMap<FieldDescriptorType, Object> newFieldMap(int arraySize) {
        return new SmallSortedMap<FieldDescriptorType, Object>(arraySize){

            @Override
            public void makeImmutable() {
                if (!this.isImmutable()) {
                    List value;
                    for (int i = 0; i < this.getNumArrayEntries(); ++i) {
                        Map.Entry entry = this.getArrayEntryAt(i);
                        if (!((FieldSet.FieldDescriptorLite)entry.getKey()).isRepeated()) continue;
                        value = (List)entry.getValue();
                        entry.setValue(Collections.unmodifiableList(value));
                    }
                    for (Map.Entry entry : this.getOverflowEntries()) {
                        if (!((FieldSet.FieldDescriptorLite)entry.getKey()).isRepeated()) continue;
                        value = (List)entry.getValue();
                        entry.setValue(Collections.unmodifiableList(value));
                    }
                }
                super.makeImmutable();
            }
        };
    }

    static <K extends Comparable<K>, V> SmallSortedMap<K, V> newInstanceForTest(int arraySize) {
        return new SmallSortedMap<K, V>(arraySize);
    }

    private SmallSortedMap(int arraySize) {
        this.maxArraySize = arraySize;
        this.entryList = Collections.emptyList();
        this.overflowEntries = Collections.emptyMap();
    }

    public void makeImmutable() {
        if (!this.isImmutable) {
            this.overflowEntries = this.overflowEntries.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.overflowEntries);
            this.isImmutable = true;
        }
    }

    public boolean isImmutable() {
        return this.isImmutable;
    }

    public int getNumArrayEntries() {
        return this.entryList.size();
    }

    public Map.Entry<K, V> getArrayEntryAt(int index) {
        return this.entryList.get(index);
    }

    public int getNumOverflowEntries() {
        return this.overflowEntries.size();
    }

    public Iterable<Map.Entry<K, V>> getOverflowEntries() {
        return this.overflowEntries.isEmpty() ? EmptySet.iterable() : this.overflowEntries.entrySet();
    }

    @Override
    public int size() {
        return this.entryList.size() + this.overflowEntries.size();
    }

    @Override
    public boolean containsKey(Object o) {
        Comparable key = (Comparable)o;
        return this.binarySearchInArray(key) >= 0 || this.overflowEntries.containsKey(key);
    }

    @Override
    public V get(Object o) {
        Comparable key = (Comparable)o;
        int index = this.binarySearchInArray(key);
        if (index >= 0) {
            return this.entryList.get(index).getValue();
        }
        return this.overflowEntries.get(key);
    }

    @Override
    public V put(K key, V value) {
        this.checkMutable();
        int index = this.binarySearchInArray(key);
        if (index >= 0) {
            return this.entryList.get(index).setValue(value);
        }
        this.ensureEntryArrayMutable();
        int insertionPoint = -(index + 1);
        if (insertionPoint >= this.maxArraySize) {
            return this.getOverflowEntriesMutable().put(key, value);
        }
        if (this.entryList.size() == this.maxArraySize) {
            Entry lastEntryInArray = this.entryList.remove(this.maxArraySize - 1);
            this.getOverflowEntriesMutable().put(lastEntryInArray.getKey(), lastEntryInArray.getValue());
        }
        this.entryList.add(insertionPoint, new Entry(this, key, value));
        return null;
    }

    @Override
    public void clear() {
        this.checkMutable();
        if (!this.entryList.isEmpty()) {
            this.entryList.clear();
        }
        if (!this.overflowEntries.isEmpty()) {
            this.overflowEntries.clear();
        }
    }

    @Override
    public V remove(Object o) {
        this.checkMutable();
        Comparable key = (Comparable)o;
        int index = this.binarySearchInArray(key);
        if (index >= 0) {
            return this.removeArrayEntryAt(index);
        }
        if (this.overflowEntries.isEmpty()) {
            return null;
        }
        return this.overflowEntries.remove(key);
    }

    private V removeArrayEntryAt(int index) {
        this.checkMutable();
        Object removed = this.entryList.remove(index).getValue();
        if (!this.overflowEntries.isEmpty()) {
            Iterator<Map.Entry<K, V>> iterator2 = this.getOverflowEntriesMutable().entrySet().iterator();
            this.entryList.add(new Entry(this, iterator2.next()));
            iterator2.remove();
        }
        return removed;
    }

    private int binarySearchInArray(K key) {
        int left = 0;
        int right = this.entryList.size() - 1;
        if (right >= 0) {
            int cmp = key.compareTo((Object)this.entryList.get(right).getKey());
            if (cmp > 0) {
                return -(right + 2);
            }
            if (cmp == 0) {
                return right;
            }
        }
        while (left <= right) {
            int mid = (left + right) / 2;
            int cmp = key.compareTo((Object)this.entryList.get(mid).getKey());
            if (cmp < 0) {
                right = mid - 1;
                continue;
            }
            if (cmp > 0) {
                left = mid + 1;
                continue;
            }
            return mid;
        }
        return -(left + 1);
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.lazyEntrySet == null) {
            this.lazyEntrySet = new EntrySet();
        }
        return this.lazyEntrySet;
    }

    private void checkMutable() {
        if (this.isImmutable) {
            throw new UnsupportedOperationException();
        }
    }

    private SortedMap<K, V> getOverflowEntriesMutable() {
        this.checkMutable();
        if (this.overflowEntries.isEmpty() && !(this.overflowEntries instanceof TreeMap)) {
            this.overflowEntries = new TreeMap();
        }
        return (SortedMap)this.overflowEntries;
    }

    private void ensureEntryArrayMutable() {
        this.checkMutable();
        if (this.entryList.isEmpty() && !(this.entryList instanceof ArrayList)) {
            this.entryList = new ArrayList<Entry>(this.maxArraySize);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class EmptySet {
        private static final Iterator<Object> ITERATOR = new Iterator<Object>(){

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Object next() {
                throw new NoSuchElementException();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        private static final Iterable<Object> ITERABLE = new Iterable<Object>(){

            @Override
            public Iterator<Object> iterator() {
                return ITERATOR;
            }
        };

        private EmptySet() {
        }

        static <T> Iterable<T> iterable() {
            return ITERABLE;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private class EntryIterator
    implements Iterator<Map.Entry<K, V>> {
        private int pos = -1;
        private boolean nextCalledBeforeRemove;
        private Iterator<Map.Entry<K, V>> lazyOverflowIterator;

        private EntryIterator() {
        }

        @Override
        public boolean hasNext() {
            return this.pos + 1 < SmallSortedMap.this.entryList.size() || this.getOverflowIterator().hasNext();
        }

        @Override
        public Map.Entry<K, V> next() {
            this.nextCalledBeforeRemove = true;
            if (++this.pos < SmallSortedMap.this.entryList.size()) {
                return (Map.Entry)SmallSortedMap.this.entryList.get(this.pos);
            }
            return this.getOverflowIterator().next();
        }

        @Override
        public void remove() {
            if (!this.nextCalledBeforeRemove) {
                throw new IllegalStateException("remove() was called before next()");
            }
            this.nextCalledBeforeRemove = false;
            SmallSortedMap.this.checkMutable();
            if (this.pos < SmallSortedMap.this.entryList.size()) {
                SmallSortedMap.this.removeArrayEntryAt(this.pos--);
            } else {
                this.getOverflowIterator().remove();
            }
        }

        private Iterator<Map.Entry<K, V>> getOverflowIterator() {
            if (this.lazyOverflowIterator == null) {
                this.lazyOverflowIterator = SmallSortedMap.this.overflowEntries.entrySet().iterator();
            }
            return this.lazyOverflowIterator;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private class EntrySet
    extends AbstractSet<Map.Entry<K, V>> {
        private EntrySet() {
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        @Override
        public int size() {
            return SmallSortedMap.this.size();
        }

        @Override
        public boolean contains(Object o) {
            Object value;
            Map.Entry entry = (Map.Entry)o;
            Object existing = SmallSortedMap.this.get(entry.getKey());
            return existing == (value = entry.getValue()) || existing != null && existing.equals(value);
        }

        @Override
        public boolean add(Map.Entry<K, V> entry) {
            if (!this.contains(entry)) {
                SmallSortedMap.this.put((Comparable)entry.getKey(), entry.getValue());
                return true;
            }
            return false;
        }

        @Override
        public boolean remove(Object o) {
            Map.Entry entry = (Map.Entry)o;
            if (this.contains(entry)) {
                SmallSortedMap.this.remove(entry.getKey());
                return true;
            }
            return false;
        }

        @Override
        public void clear() {
            SmallSortedMap.this.clear();
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class Entry
    implements Map.Entry<K, V>,
    Comparable<Entry> {
        private final K key;
        private V value;
        final /* synthetic */ SmallSortedMap this$0;

        Entry(SmallSortedMap smallSortedMap, Map.Entry<K, V> copy) {
            this(smallSortedMap, (Comparable)copy.getKey(), copy.getValue());
        }

        Entry(K key, V value) {
            this.this$0 = var1_1;
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public int compareTo(Entry other) {
            return this.getKey().compareTo(other.getKey());
        }

        @Override
        public V setValue(V newValue) {
            this.this$0.checkMutable();
            Object oldValue = this.value;
            this.value = newValue;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry other = (Map.Entry)o;
            return this.equals(this.key, other.getKey()) && this.equals(this.value, other.getValue());
        }

        @Override
        public int hashCode() {
            return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : this.value.hashCode());
        }

        public String toString() {
            String string = String.valueOf(String.valueOf(this.key));
            String string2 = String.valueOf(String.valueOf(this.value));
            return new StringBuilder(1 + string.length() + string2.length()).append(string).append("=").append(string2).toString();
        }

        private boolean equals(Object o1, Object o2) {
            return o1 == null ? o2 == null : o1.equals(o2);
        }
    }
}

