/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.gson;

import com.google.gson.JsonParseException;

public final class JsonSyntaxException
extends JsonParseException {
    private static final long serialVersionUID = 1L;

    public JsonSyntaxException(String msg) {
        super(msg);
    }

    public JsonSyntaxException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JsonSyntaxException(Throwable cause) {
        super(cause);
    }
}

