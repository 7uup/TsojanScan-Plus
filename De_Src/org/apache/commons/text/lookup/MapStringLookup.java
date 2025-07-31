/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.lookup;

import java.util.Map;
import org.apache.commons.text.lookup.StringLookup;

final class MapStringLookup<V>
implements StringLookup {
    private final Map<String, V> map;

    static <T> MapStringLookup<T> on(Map<String, T> map) {
        return new MapStringLookup<T>(map);
    }

    private MapStringLookup(Map<String, V> map) {
        this.map = map;
    }

    Map<String, V> getMap() {
        return this.map;
    }

    @Override
    public String lookup(String key) {
        V obj;
        if (this.map == null) {
            return null;
        }
        try {
            obj = this.map.get(key);
        } catch (NullPointerException e) {
            return null;
        }
        return obj != null ? obj.toString() : null;
    }

    public String toString() {
        return this.getClass().getName() + " [map=" + this.map + "]";
    }
}

