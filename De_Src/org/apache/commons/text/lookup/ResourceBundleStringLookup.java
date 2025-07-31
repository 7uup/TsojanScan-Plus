/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.lookup;

import java.util.ResourceBundle;
import org.apache.commons.text.lookup.AbstractStringLookup;
import org.apache.commons.text.lookup.IllegalArgumentExceptions;

final class ResourceBundleStringLookup
extends AbstractStringLookup {
    private final String bundleName;
    static final ResourceBundleStringLookup INSTANCE = new ResourceBundleStringLookup();

    private ResourceBundleStringLookup() {
        this(null);
    }

    ResourceBundleStringLookup(String bundleName) {
        this.bundleName = bundleName;
    }

    @Override
    public String lookup(String key) {
        boolean anyBundle;
        if (key == null) {
            return null;
        }
        String[] keys2 = key.split(SPLIT_STR);
        int keyLen = keys2.length;
        boolean bl = anyBundle = this.bundleName == null;
        if (anyBundle && keyLen != 2) {
            throw IllegalArgumentExceptions.format("Bad resource bundle key format [%s]; expected format is BundleName:KeyName.", key);
        }
        if (this.bundleName != null && keyLen != 1) {
            throw IllegalArgumentExceptions.format("Bad resource bundle key format [%s]; expected format is KeyName.", key);
        }
        String keyBundleName = anyBundle ? keys2[0] : this.bundleName;
        String bundleKey = anyBundle ? keys2[1] : keys2[0];
        try {
            return ResourceBundle.getBundle(keyBundleName).getString(bundleKey);
        } catch (Exception e) {
            throw IllegalArgumentExceptions.format(e, "Error looking up resource bundle [%s] and key [%s].", keyBundleName, bundleKey);
        }
    }
}

