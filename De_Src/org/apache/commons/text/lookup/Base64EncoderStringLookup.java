/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.lookup;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.apache.commons.text.lookup.AbstractStringLookup;

final class Base64EncoderStringLookup
extends AbstractStringLookup {
    static final Base64EncoderStringLookup INSTANCE = new Base64EncoderStringLookup();

    private Base64EncoderStringLookup() {
    }

    @Override
    public String lookup(String key) {
        if (key == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(key.getBytes(StandardCharsets.ISO_8859_1));
    }
}

