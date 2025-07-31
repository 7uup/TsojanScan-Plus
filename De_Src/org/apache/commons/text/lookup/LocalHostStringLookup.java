/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.lookup;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.commons.text.lookup.AbstractStringLookup;

final class LocalHostStringLookup
extends AbstractStringLookup {
    static final LocalHostStringLookup INSTANCE = new LocalHostStringLookup();

    private LocalHostStringLookup() {
    }

    @Override
    public String lookup(String key) {
        if (key == null) {
            return null;
        }
        switch (key) {
            case "name": {
                try {
                    return InetAddress.getLocalHost().getHostName();
                } catch (UnknownHostException e) {
                    return null;
                }
            }
            case "canonical-name": {
                try {
                    return InetAddress.getLocalHost().getCanonicalHostName();
                } catch (UnknownHostException e) {
                    return null;
                }
            }
            case "address": {
                try {
                    return InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException e) {
                    return null;
                }
            }
        }
        throw new IllegalArgumentException(key);
    }
}

