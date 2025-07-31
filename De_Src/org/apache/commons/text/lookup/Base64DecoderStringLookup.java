/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.lookup;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.apache.commons.text.lookup.AbstractStringLookup;

final class Base64DecoderStringLookup
extends AbstractStringLookup {
    static final Base64DecoderStringLookup INSTANCE = new Base64DecoderStringLookup();

    private Base64DecoderStringLookup() {
    }

    @Override
    public String lookup(String key) {
        if (key == null) {
            return null;
        }
        return new String(Base64.getDecoder().decode(key), StandardCharsets.ISO_8859_1);
    }
}

