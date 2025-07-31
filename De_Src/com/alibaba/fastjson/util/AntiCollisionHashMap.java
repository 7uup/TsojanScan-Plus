/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class AntiCollisionHashMap<K, V>
extends AbstractMap<K, V>
implements Map<K, V>,
Cloneable,
Serializable {
    volatile transient Set<K> keySet = null;
    volatile transient Collection<V> values = null;
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final int MAXIMUM_CAPACITY = 0x40000000;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    transient Entry<K, V>[] table;
    transient int size;
    int threshold;
    final float loadFactor;
    volatile transient int modCount;
    static final int M_MASK = -2023358765;
    static final int SEED = -2128831035;
    static final int KEY = 16777619;
    final int random = new Random().nextInt(99999);
    private transient Set<Map.Entry<K, V>> entrySet = null;
    private static final long serialVersionUID = 362498820763181265L;

    private int hashString(String key) {
        int hash = -2128831035 * this.random;
        for (int i = 0; i < key.length(); ++i) {
            hash = hash * 16777619 ^ key.charAt(i);
        }
        return (hash ^ hash >> 1) & 0x8765FED3;
    }

    public AntiCollisionHashMap(int initialCapacity, float loadFactor) {
        int capacity;
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        }
        if (initialCapacity > 0x40000000) {
            initialCapacity = 0x40000000;
        }
        if (loadFactor <= 0.0f || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        }
        for (capacity = 1; capacity < initialCapacity; capacity <<= 1) {
        }
        this.loadFactor = loadFactor;
        this.threshold = (int)((float)capacity * loadFactor);
        this.table = new Entry[capacity];
        this.init();
    }

    public AntiCollisionHashMap(int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    public AntiCollisionHashMap() {
        this.loadFactor = 0.75f;
        this.threshold = 12;
        this.table = new Entry[16];
        this.init();
    }

    public AntiCollisionHashMap(Map<? extends K, ? extends V> m3) {
        this(Math.max((int)((float)m3.size() / 0.75f) + 1, 16), 0.75f);
        super.putAllForCreate(m3);
    }

    void init() {
    }

    static int hash(int h2) {
        h2 *= h2;
        h2 ^= h2 >>> 20 ^ h2 >>> 12;
        return h2 ^ h2 >>> 7 ^ h2 >>> 4;
    }

    static int indexFor(int h2, int length) {
        return h2 & length - 1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public V get(Object key) {
        if (key == null) {
            return this.getForNullKey();
        }
        int hash = 0;
        hash = key instanceof String ? AntiCollisionHashMap.hash(this.hashString((String)key)) : AntiCollisionHashMap.hash(key.hashCode());
        Entry<K, V> e = this.table[AntiCollisionHashMap.indexFor(hash, this.table.length)];
        while (e != null) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                return e.value;
            }
            e = e.next;
        }
        return null;
    }

    private V getForNullKey() {
        Entry<K, V> e = this.table[0];
        while (e != null) {
            if (e.key == null) {
                return e.value;
            }
            e = e.next;
        }
        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        return this.getEntry(key) != null;
    }

    final Entry<K, V> getEntry(Object key) {
        int hash = key == null ? 0 : (key instanceof String ? AntiCollisionHashMap.hash(this.hashString((String)key)) : AntiCollisionHashMap.hash(key.hashCode()));
        Entry<K, V> e = this.table[AntiCollisionHashMap.indexFor(hash, this.table.length)];
        while (e != null) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key != null && key.equals(k))) {
                return e;
            }
            e = e.next;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            return this.putForNullKey(value);
        }
        int hash = 0;
        hash = key instanceof String ? AntiCollisionHashMap.hash(this.hashString((String)key)) : AntiCollisionHashMap.hash(key.hashCode());
        int i = AntiCollisionHashMap.indexFor(hash, this.table.length);
        Entry<K, V> e = this.table[i];
        while (e != null) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                Object oldValue = e.value;
                e.value = value;
                return oldValue;
            }
            e = e.next;
        }
        ++this.modCount;
        this.addEntry(hash, key, value, i);
        return null;
    }

    private V putForNullKey(V value) {
        Entry<K, V> e = this.table[0];
        while (e != null) {
            if (e.key == null) {
                Object oldValue = e.value;
                e.value = value;
                return oldValue;
            }
            e = e.next;
        }
        ++this.modCount;
        this.addEntry(0, null, value, 0);
        return null;
    }

    private void putForCreate(K key, V value) {
        int hash = key == null ? 0 : (key instanceof String ? AntiCollisionHashMap.hash(this.hashString((String)key)) : AntiCollisionHashMap.hash(key.hashCode()));
        int i = AntiCollisionHashMap.indexFor(hash, this.table.length);
        Entry<K, V> e = this.table[i];
        while (e != null) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key != null && key.equals(k))) {
                e.value = value;
                return;
            }
            e = e.next;
        }
        this.createEntry(hash, key, value, i);
    }

    private void putAllForCreate(Map<? extends K, ? extends V> m3) {
        for (Map.Entry<K, V> e : m3.entrySet()) {
            this.putForCreate(e.getKey(), e.getValue());
        }
    }

    void resize(int newCapacity) {
        Entry<K, V>[] oldTable = this.table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == 0x40000000) {
            this.threshold = Integer.MAX_VALUE;
            return;
        }
        Entry[] newTable = new Entry[newCapacity];
        this.transfer(newTable);
        this.table = newTable;
        this.threshold = (int)((float)newCapacity * this.loadFactor);
    }

    void transfer(Entry[] newTable) {
        Entry<K, V>[] src = this.table;
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; ++j) {
            Entry next;
            Entry<K, V> e = src[j];
            if (e == null) continue;
            src[j] = null;
            do {
                next = e.next;
                int i = AntiCollisionHashMap.indexFor(e.hash, newCapacity);
                e.next = newTable[i];
                newTable[i] = e;
            } while ((e = next) != null);
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m3) {
        int numKeysToBeAdded = m3.size();
        if (numKeysToBeAdded == 0) {
            return;
        }
        if (numKeysToBeAdded > this.threshold) {
            int newCapacity;
            int targetCapacity = (int)((float)numKeysToBeAdded / this.loadFactor + 1.0f);
            if (targetCapacity > 0x40000000) {
                targetCapacity = 0x40000000;
            }
            for (newCapacity = this.table.length; newCapacity < targetCapacity; newCapacity <<= 1) {
            }
            if (newCapacity > this.table.length) {
                this.resize(newCapacity);
            }
        }
        for (Map.Entry<K, V> e : m3.entrySet()) {
            this.put(e.getKey(), e.getValue());
        }
    }

    @Override
    public V remove(Object key) {
        Entry<K, V> e = this.removeEntryForKey(key);
        return e == null ? null : (V)e.value;
    }

    final Entry<K, V> removeEntryForKey(Object key) {
        Entry<K, V> prev;
        int hash = key == null ? 0 : (key instanceof String ? AntiCollisionHashMap.hash(this.hashString((String)key)) : AntiCollisionHashMap.hash(key.hashCode()));
        int i = AntiCollisionHashMap.indexFor(hash, this.table.length);
        Entry<K, V> e = prev = this.table[i];
        while (e != null) {
            Object k;
            Entry next = e.next;
            if (e.hash == hash && ((k = e.key) == key || key != null && key.equals(k))) {
                ++this.modCount;
                --this.size;
                if (prev == e) {
                    this.table[i] = next;
                } else {
                    prev.next = next;
                }
                return e;
            }
            prev = e;
            e = next;
        }
        return e;
    }

    final Entry<K, V> removeMapping(Object o) {
        Entry<K, V> prev;
        if (!(o instanceof Map.Entry)) {
            return null;
        }
        Map.Entry entry = (Map.Entry)o;
        Object key = entry.getKey();
        int hash = key == null ? 0 : (key instanceof String ? AntiCollisionHashMap.hash(this.hashString((String)key)) : AntiCollisionHashMap.hash(key.hashCode()));
        int i = AntiCollisionHashMap.indexFor(hash, this.table.length);
        Entry<K, V> e = prev = this.table[i];
        while (e != null) {
            Entry next = e.next;
            if (e.hash == hash && e.equals(entry)) {
                ++this.modCount;
                --this.size;
                if (prev == e) {
                    this.table[i] = next;
                } else {
                    prev.next = next;
                }
                return e;
            }
            prev = e;
            e = next;
        }
        return e;
    }

    @Override
    public void clear() {
        ++this.modCount;
        Entry<K, V>[] tab = this.table;
        for (int i = 0; i < tab.length; ++i) {
            tab[i] = null;
        }
        this.size = 0;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value == null) {
            return this.containsNullValue();
        }
        Entry<K, V>[] tab = this.table;
        for (int i = 0; i < tab.length; ++i) {
            Entry<K, V> e = tab[i];
            while (e != null) {
                if (value.equals(e.value)) {
                    return true;
                }
                e = e.next;
            }
        }
        return false;
    }

    private boolean containsNullValue() {
        Entry<K, V>[] tab = this.table;
        for (int i = 0; i < tab.length; ++i) {
            Entry<K, V> e = tab[i];
            while (e != null) {
                if (e.value == null) {
                    return true;
                }
                e = e.next;
            }
        }
        return false;
    }

    @Override
    public Object clone() {
        AntiCollisionHashMap result = null;
        try {
            result = (AntiCollisionHashMap)super.clone();
        } catch (CloneNotSupportedException cloneNotSupportedException) {
            // empty catch block
        }
        result.table = new Entry[this.table.length];
        result.entrySet = null;
        result.modCount = 0;
        result.size = 0;
        result.init();
        result.putAllForCreate(this);
        return result;
    }

    void addEntry(int hash, K key, V value, int bucketIndex) {
        Entry<K, V> e = this.table[bucketIndex];
        this.table[bucketIndex] = new Entry<K, V>(hash, key, value, e);
        if (this.size++ >= this.threshold) {
            this.resize(2 * this.table.length);
        }
    }

    void createEntry(int hash, K key, V value, int bucketIndex) {
        Entry<K, V> e = this.table[bucketIndex];
        this.table[bucketIndex] = new Entry<K, V>(hash, key, value, e);
        ++this.size;
    }

    Iterator<K> newKeyIterator() {
        return new KeyIterator();
    }

    Iterator<V> newValueIterator() {
        return new ValueIterator();
    }

    Iterator<Map.Entry<K, V>> newEntryIterator() {
        return new EntryIterator();
    }

    @Override
    public Set<K> keySet() {
        KeySet ks = this.keySet;
        return ks != null ? ks : (this.keySet = new KeySet());
    }

    @Override
    public Collection<V> values() {
        Values vs = this.values;
        return vs != null ? vs : (this.values = new Values());
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return this.entrySet0();
    }

    private Set<Map.Entry<K, V>> entrySet0() {
        EntrySet es = this.entrySet;
        return es != null ? es : (this.entrySet = new EntrySet());
    }

    private void writeObject(ObjectOutputStream s2) throws IOException {
        Iterator<Map.Entry<K, V>> i = this.size > 0 ? this.entrySet0().iterator() : null;
        s2.defaultWriteObject();
        s2.writeInt(this.table.length);
        s2.writeInt(this.size);
        if (i != null) {
            while (i.hasNext()) {
                Map.Entry<K, V> e = i.next();
                s2.writeObject(e.getKey());
                s2.writeObject(e.getValue());
            }
        }
    }

    private void readObject(ObjectInputStream s2) throws IOException, ClassNotFoundException {
        s2.defaultReadObject();
        int numBuckets = s2.readInt();
        this.table = new Entry[numBuckets];
        this.init();
        int size = s2.readInt();
        for (int i = 0; i < size; ++i) {
            Object key = s2.readObject();
            Object value = s2.readObject();
            this.putForCreate(key, value);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private final class EntrySet
    extends AbstractSet<Map.Entry<K, V>> {
        private EntrySet() {
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return AntiCollisionHashMap.this.newEntryIterator();
        }

        @Override
        public boolean contains(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry e = (Map.Entry)o;
            Entry candidate = AntiCollisionHashMap.this.getEntry(e.getKey());
            return candidate != null && candidate.equals(e);
        }

        @Override
        public boolean remove(Object o) {
            return AntiCollisionHashMap.this.removeMapping(o) != null;
        }

        @Override
        public int size() {
            return AntiCollisionHashMap.this.size;
        }

        @Override
        public void clear() {
            AntiCollisionHashMap.this.clear();
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private final class Values
    extends AbstractCollection<V> {
        private Values() {
        }

        @Override
        public Iterator<V> iterator() {
            return AntiCollisionHashMap.this.newValueIterator();
        }

        @Override
        public int size() {
            return AntiCollisionHashMap.this.size;
        }

        @Override
        public boolean contains(Object o) {
            return AntiCollisionHashMap.this.containsValue(o);
        }

        @Override
        public void clear() {
            AntiCollisionHashMap.this.clear();
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private final class KeySet
    extends AbstractSet<K> {
        private KeySet() {
        }

        @Override
        public Iterator<K> iterator() {
            return AntiCollisionHashMap.this.newKeyIterator();
        }

        @Override
        public int size() {
            return AntiCollisionHashMap.this.size;
        }

        @Override
        public boolean contains(Object o) {
            return AntiCollisionHashMap.this.containsKey(o);
        }

        @Override
        public boolean remove(Object o) {
            return AntiCollisionHashMap.this.removeEntryForKey(o) != null;
        }

        @Override
        public void clear() {
            AntiCollisionHashMap.this.clear();
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private final class EntryIterator
    extends HashIterator<Map.Entry<K, V>> {
        private EntryIterator() {
        }

        @Override
        public Map.Entry<K, V> next() {
            return this.nextEntry();
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private final class KeyIterator
    extends HashIterator<K> {
        private KeyIterator() {
        }

        @Override
        public K next() {
            return this.nextEntry().getKey();
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private final class ValueIterator
    extends HashIterator<V> {
        private ValueIterator() {
        }

        @Override
        public V next() {
            return this.nextEntry().value;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private abstract class HashIterator<E>
    implements Iterator<E> {
        Entry<K, V> next;
        int expectedModCount;
        int index;
        Entry<K, V> current;

        HashIterator() {
            this.expectedModCount = AntiCollisionHashMap.this.modCount;
            if (AntiCollisionHashMap.this.size > 0) {
                Entry<K, V>[] t = AntiCollisionHashMap.this.table;
                while (this.index < t.length && (this.next = t[this.index++]) == null) {
                }
            }
        }

        @Override
        public final boolean hasNext() {
            return this.next != null;
        }

        final Entry<K, V> nextEntry() {
            if (AntiCollisionHashMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            Entry e = this.next;
            if (e == null) {
                throw new NoSuchElementException();
            }
            this.next = e.next;
            if (this.next == null) {
                Entry<K, V>[] t = AntiCollisionHashMap.this.table;
                while (this.index < t.length && (this.next = t[this.index++]) == null) {
                }
            }
            this.current = e;
            return e;
        }

        @Override
        public void remove() {
            if (this.current == null) {
                throw new IllegalStateException();
            }
            if (AntiCollisionHashMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            Object k = this.current.key;
            this.current = null;
            AntiCollisionHashMap.this.removeEntryForKey(k);
            this.expectedModCount = AntiCollisionHashMap.this.modCount;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static class Entry<K, V>
    implements Map.Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;
        final int hash;

        Entry(int h2, K k, V v, Entry<K, V> n) {
            this.value = v;
            this.next = n;
            this.key = k;
            this.hash = h2;
        }

        @Override
        public final K getKey() {
            return this.key;
        }

        @Override
        public final V getValue() {
            return this.value;
        }

        @Override
        public final V setValue(V newValue) {
            V oldValue = this.value;
            this.value = newValue;
            return oldValue;
        }

        @Override
        public final boolean equals(Object o) {
            Object k2;
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry e = (Map.Entry)o;
            K k1 = this.getKey();
            if (k1 == (k2 = e.getKey()) || k1 != null && k1.equals(k2)) {
                Object v2;
                V v1 = this.getValue();
                return v1 == (v2 = e.getValue()) || v1 != null && v1.equals(v2);
            }
            return false;
        }

        @Override
        public final int hashCode() {
            return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : this.value.hashCode());
        }

        public final String toString() {
            return this.getKey() + "=" + this.getValue();
        }
    }
}

