/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.lookup;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.text.lookup.AbstractStringLookup;

class ConstantStringLookup
extends AbstractStringLookup {
    private static final char FIELD_SEPRATOR = '.';
    static final ConstantStringLookup INSTANCE = new ConstantStringLookup();
    private static ConcurrentHashMap<String, String> constantCache = new ConcurrentHashMap();

    ConstantStringLookup() {
    }

    static void clear() {
        constantCache.clear();
    }

    @Override
    public synchronized String lookup(String key) {
        if (key == null) {
            return null;
        }
        String result = constantCache.get(key);
        if (result != null) {
            return result;
        }
        int fieldPos = key.lastIndexOf(46);
        if (fieldPos < 0) {
            return null;
        }
        try {
            Object value = this.resolveField(key.substring(0, fieldPos), key.substring(fieldPos + 1));
            if (value != null) {
                String string = Objects.toString(value, null);
                constantCache.put(key, string);
                result = string;
            }
        } catch (Exception ex) {
            return null;
        }
        return result;
    }

    protected Object resolveField(String className, String fieldName) throws Exception {
        Class<?> clazz = this.fetchClass(className);
        if (clazz == null) {
            return null;
        }
        return clazz.getField(fieldName).get(null);
    }

    protected Class<?> fetchClass(String className) throws ClassNotFoundException {
        return ClassUtils.getClass(className);
    }
}

