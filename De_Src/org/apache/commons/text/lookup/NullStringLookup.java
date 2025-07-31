/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.lookup;

import org.apache.commons.text.lookup.AbstractStringLookup;

final class NullStringLookup
extends AbstractStringLookup {
    static final NullStringLookup INSTANCE = new NullStringLookup();

    private NullStringLookup() {
    }

    @Override
    public String lookup(String key) {
        return null;
    }
}

