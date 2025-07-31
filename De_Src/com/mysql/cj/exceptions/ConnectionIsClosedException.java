/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.exceptions;

import com.mysql.cj.exceptions.CJException;

public class ConnectionIsClosedException
extends CJException {
    private static final long serialVersionUID = -8001652264426656450L;

    public ConnectionIsClosedException() {
        this.setSQLState("08003");
    }

    public ConnectionIsClosedException(String message) {
        super(message);
        this.setSQLState("08003");
    }

    public ConnectionIsClosedException(String message, Throwable cause) {
        super(message, cause);
        this.setSQLState("08003");
    }

    public ConnectionIsClosedException(Throwable cause) {
        super(cause);
        this.setSQLState("08003");
    }

    protected ConnectionIsClosedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.setSQLState("08003");
    }
}

