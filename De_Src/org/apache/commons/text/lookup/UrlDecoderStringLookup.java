/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.lookup;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import org.apache.commons.text.lookup.AbstractStringLookup;
import org.apache.commons.text.lookup.IllegalArgumentExceptions;

final class UrlDecoderStringLookup
extends AbstractStringLookup {
    static final UrlDecoderStringLookup INSTANCE = new UrlDecoderStringLookup();

    private UrlDecoderStringLookup() {
    }

    @Override
    public String lookup(String key) {
        if (key == null) {
            return null;
        }
        String enc = StandardCharsets.UTF_8.name();
        try {
            return URLDecoder.decode(key, enc);
        } catch (UnsupportedEncodingException e) {
            throw IllegalArgumentExceptions.format(e, "%s: source=%s, encoding=%s", e, key, enc);
        }
    }
}

