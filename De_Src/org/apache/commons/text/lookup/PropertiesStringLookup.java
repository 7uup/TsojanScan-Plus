/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.lookup;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.Properties;
import org.apache.commons.text.lookup.AbstractStringLookup;
import org.apache.commons.text.lookup.IllegalArgumentExceptions;

final class PropertiesStringLookup
extends AbstractStringLookup {
    static final PropertiesStringLookup INSTANCE = new PropertiesStringLookup();

    private PropertiesStringLookup() {
    }

    @Override
    public String lookup(String key) {
        if (key == null) {
            return null;
        }
        String[] keys2 = key.split("::");
        int keyLen = keys2.length;
        if (keyLen < 2) {
            throw IllegalArgumentExceptions.format("Bad properties key format [%s]; expected format is DocumentPath::Key.", key);
        }
        String documentPath = keys2[0];
        String propertyKey = this.substringAfter(key, "::");
        try {
            Properties properties = new Properties();
            try (InputStream inputStream2 = Files.newInputStream(Paths.get(documentPath, new String[0]), new OpenOption[0]);){
                properties.load(inputStream2);
            }
            return properties.getProperty(propertyKey);
        } catch (Exception e) {
            throw IllegalArgumentExceptions.format(e, "Error looking up properties [%s] and key [%s].", documentPath, propertyKey);
        }
    }
}

