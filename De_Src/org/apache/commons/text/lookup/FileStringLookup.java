/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.lookup;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.text.lookup.AbstractStringLookup;
import org.apache.commons.text.lookup.IllegalArgumentExceptions;

final class FileStringLookup
extends AbstractStringLookup {
    static final AbstractStringLookup INSTANCE = new FileStringLookup();

    private FileStringLookup() {
    }

    @Override
    public String lookup(String key) {
        if (key == null) {
            return null;
        }
        String[] keys2 = key.split(String.valueOf(':'));
        int keyLen = keys2.length;
        if (keyLen < 2) {
            throw IllegalArgumentExceptions.format("Bad file key format [%s], expected format is CharsetName:DocumentPath.", key);
        }
        String charsetName = keys2[0];
        String fileName = this.substringAfter(key, ':');
        try {
            return new String(Files.readAllBytes(Paths.get(fileName, new String[0])), charsetName);
        } catch (Exception e) {
            throw IllegalArgumentExceptions.format(e, "Error looking up file [%s] with charset [%s].", fileName, charsetName);
        }
    }
}

