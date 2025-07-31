/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.LazyFieldLite;
import com.google.protobuf.MessageLite;
import java.util.Iterator;
import java.util.Map;

public class LazyField
extends LazyFieldLite {
    private final MessageLite defaultInstance;

    public LazyField(MessageLite defaultInstance, ExtensionRegistryLite extensionRegistry, ByteString bytes) {
        super(extensionRegistry, bytes);
        this.defaultInstance = defaultInstance;
    }

    public boolean containsDefaultInstance() {
        return super.containsDefaultInstance() || this.value == this.defaultInstance;
    }

    public MessageLite getValue() {
        return this.getValue(this.defaultInstance);
    }

    public int hashCode() {
        return this.getValue().hashCode();
    }

    public boolean equals(Object obj) {
        return this.getValue().equals(obj);
    }

    public String toString() {
        return this.getValue().toString();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static class LazyIterator<K>
    implements Iterator<Map.Entry<K, Object>> {
        private Iterator<Map.Entry<K, Object>> iterator;

        public LazyIterator(Iterator<Map.Entry<K, Object>> iterator2) {
            this.iterator = iterator2;
        }

        @Override
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override
        public Map.Entry<K, Object> next() {
            Map.Entry<K, Object> entry = this.iterator.next();
            if (entry.getValue() instanceof LazyField) {
                return new LazyEntry(entry);
            }
            return entry;
        }

        @Override
        public void remove() {
            this.iterator.remove();
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static class LazyEntry<K>
    implements Map.Entry<K, Object> {
        private Map.Entry<K, LazyField> entry;

        private LazyEntry(Map.Entry<K, LazyField> entry) {
            this.entry = entry;
        }

        @Override
        public K getKey() {
            return this.entry.getKey();
        }

        @Override
        public Object getValue() {
            LazyField field = this.entry.getValue();
            if (field == null) {
                return null;
            }
            return field.getValue();
        }

        public LazyField getField() {
            return this.entry.getValue();
        }

        @Override
        public Object setValue(Object value) {
            if (!(value instanceof MessageLite)) {
                throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
            }
            return this.entry.getValue().setValue((MessageLite)value);
        }
    }
}

