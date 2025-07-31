/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.util;

import java.util.Arrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class IdentityHashMap<K, V> {
    private final Entry<K, V>[] buckets;
    private final int indexMask;
    public static final int DEFAULT_SIZE = 8192;

    public IdentityHashMap() {
        this(8192);
    }

    public IdentityHashMap(int tableSize) {
        this.indexMask = tableSize - 1;
        this.buckets = new Entry[tableSize];
    }

    public final V get(K key) {
        int hash = System.identityHashCode(key);
        int bucket = hash & this.indexMask;
        Entry<K, V> entry = this.buckets[bucket];
        while (entry != null) {
            if (key == entry.key) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    public Class findClass(String keyString) {
        for (int i = 0; i < this.buckets.length; ++i) {
            Entry<K, V> bucket = this.buckets[i];
            if (bucket == null) continue;
            Entry<K, V> entry = bucket;
            while (entry != null) {
                Class clazz;
                String className;
                Object key = bucket.key;
                if (key instanceof Class && (className = (clazz = (Class)key).getName()).equals(keyString)) {
                    return clazz;
                }
                entry = entry.next;
            }
        }
        return null;
    }

    public boolean put(K key, V value) {
        int hash = System.identityHashCode(key);
        int bucket = hash & this.indexMask;
        Entry<K, V> entry = this.buckets[bucket];
        while (entry != null) {
            if (key == entry.key) {
                entry.value = value;
                return true;
            }
            entry = entry.next;
        }
        this.buckets[bucket] = entry = new Entry<K, V>(key, value, hash, this.buckets[bucket]);
        return false;
    }

    public void clear() {
        Arrays.fill(this.buckets, null);
    }

    public int size() {
        int count = 0;
        for (Entry<K, V> bucket : this.buckets) {
            while (bucket != null) {
                ++count;
                bucket = bucket.next;
            }
        }
        return count;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    protected static final class Entry<K, V> {
        public final int hashCode;
        public final K key;
        public V value;
        public final Entry<K, V> next;

        public Entry(K key, V value, int hash, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.hashCode = hash;
        }
    }
}

