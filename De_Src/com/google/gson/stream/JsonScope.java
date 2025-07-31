/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.gson.stream;

final class JsonScope {
    static final int EMPTY_ARRAY = 1;
    static final int NONEMPTY_ARRAY = 2;
    static final int EMPTY_OBJECT = 3;
    static final int DANGLING_NAME = 4;
    static final int NONEMPTY_OBJECT = 5;
    static final int EMPTY_DOCUMENT = 6;
    static final int NONEMPTY_DOCUMENT = 7;
    static final int CLOSED = 8;

    JsonScope() {
    }
}

