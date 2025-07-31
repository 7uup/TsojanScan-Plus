/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.lookup;

import org.apache.commons.text.lookup.AbstractStringLookup;

final class EnvironmentVariableStringLookup
extends AbstractStringLookup {
    static final EnvironmentVariableStringLookup INSTANCE = new EnvironmentVariableStringLookup();

    private EnvironmentVariableStringLookup() {
    }

    @Override
    public String lookup(String key) {
        return key != null ? System.getenv(key) : null;
    }
}

