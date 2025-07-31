/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.lookup;

import org.apache.commons.text.lookup.AbstractStringLookup;

final class SystemPropertyStringLookup
extends AbstractStringLookup {
    static final SystemPropertyStringLookup INSTANCE = new SystemPropertyStringLookup();

    private SystemPropertyStringLookup() {
    }

    @Override
    public String lookup(String key) {
        try {
            return System.getProperty(key);
        } catch (IllegalArgumentException | NullPointerException | SecurityException e) {
            return null;
        }
    }
}

