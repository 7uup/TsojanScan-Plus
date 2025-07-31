/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.lookup;

final class IllegalArgumentExceptions {
    static IllegalArgumentException format(String format, Object ... args2) {
        return new IllegalArgumentException(String.format(format, args2));
    }

    static IllegalArgumentException format(Throwable t, String format, Object ... args2) {
        return new IllegalArgumentException(String.format(format, args2), t);
    }

    private IllegalArgumentExceptions() {
    }
}

