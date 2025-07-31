/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.lookup;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import org.apache.commons.text.lookup.AbstractStringLookup;
import org.apache.commons.text.lookup.IllegalArgumentExceptions;

final class UrlStringLookup
extends AbstractStringLookup {
    static final UrlStringLookup INSTANCE = new UrlStringLookup();

    private UrlStringLookup() {
    }

    @Override
    public String lookup(String key) {
        if (key == null) {
            return null;
        }
        String[] keys2 = key.split(SPLIT_STR);
        int keyLen = keys2.length;
        if (keyLen < 2) {
            throw IllegalArgumentExceptions.format("Bad URL key format [%s]; expected format is DocumentPath:Key.", key);
        }
        String charsetName = keys2[0];
        String urlStr = this.substringAfter(key, ':');
        try {
            URL url = new URL(urlStr);
            int size = 8192;
            StringWriter writer = new StringWriter(8192);
            char[] buffer = new char[8192];
            try (InputStreamReader reader = new InputStreamReader((InputStream)new BufferedInputStream(url.openStream()), charsetName);){
                int n;
                while (-1 != (n = reader.read(buffer))) {
                    writer.write(buffer, 0, n);
                }
            }
            return writer.toString();
        } catch (Exception e) {
            throw IllegalArgumentExceptions.format(e, "Error looking up URL [%s] with Charset [%s].", urlStr, charsetName);
        }
    }
}

