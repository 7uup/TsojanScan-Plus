/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.lookup;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.text.lookup.AbstractStringLookup;
import org.apache.commons.text.lookup.MapStringLookup;
import org.apache.commons.text.lookup.StringLookup;
import org.apache.commons.text.lookup.StringLookupFactory;

class InterpolatorStringLookup
extends AbstractStringLookup {
    static final AbstractStringLookup INSTANCE = new InterpolatorStringLookup();
    private static final char PREFIX_SEPARATOR = ':';
    private final StringLookup defaultStringLookup;
    private final Map<String, StringLookup> stringLookupMap;

    InterpolatorStringLookup() {
        this((Map)null);
    }

    <V> InterpolatorStringLookup(Map<String, V> defaultMap) {
        this(MapStringLookup.on(defaultMap == null ? new HashMap() : defaultMap));
    }

    InterpolatorStringLookup(StringLookup defaultStringLookup) {
        this(new HashMap<String, StringLookup>(), defaultStringLookup, true);
    }

    InterpolatorStringLookup(Map<String, StringLookup> stringLookupMap, StringLookup defaultStringLookup, boolean addDefaultLookups) {
        this.defaultStringLookup = defaultStringLookup;
        this.stringLookupMap = new HashMap<String, StringLookup>(stringLookupMap.size());
        for (Map.Entry<String, StringLookup> entry : stringLookupMap.entrySet()) {
            this.stringLookupMap.put(entry.getKey().toLowerCase(Locale.ROOT), entry.getValue());
        }
        if (addDefaultLookups) {
            StringLookupFactory.INSTANCE.addDefaultStringLookups(this.stringLookupMap);
        }
    }

    public Map<String, StringLookup> getStringLookupMap() {
        return this.stringLookupMap;
    }

    @Override
    public String lookup(String var) {
        if (var == null) {
            return null;
        }
        int prefixPos = var.indexOf(58);
        if (prefixPos >= 0) {
            String prefix = var.substring(0, prefixPos).toLowerCase(Locale.ROOT);
            String name = var.substring(prefixPos + 1);
            StringLookup lookup = this.stringLookupMap.get(prefix);
            String value = null;
            if (lookup != null) {
                value = lookup.lookup(name);
            }
            if (value != null) {
                return value;
            }
            var = var.substring(prefixPos + 1);
        }
        if (this.defaultStringLookup != null) {
            return this.defaultStringLookup.lookup(var);
        }
        return null;
    }

    public String toString() {
        return this.getClass().getName() + " [stringLookupMap=" + this.stringLookupMap + ", defaultStringLookup=" + this.defaultStringLookup + "]";
    }
}

