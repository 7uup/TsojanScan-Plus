/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.exceptions;

import com.mysql.cj.exceptions.CJException;

public class UnsupportedConnectionStringException
extends CJException {
    private static final long serialVersionUID = 3991597077197801820L;

    public UnsupportedConnectionStringException() {
        this.setSQLState("S1009");
    }

    public UnsupportedConnectionStringException(String message) {
        super(message);
        this.setSQLState("S1009");
    }

    public UnsupportedConnectionStringException(String message, Throwable cause) {
        super(message, cause);
        this.setSQLState("S1009");
    }

    public UnsupportedConnectionStringException(Throwable cause) {
        super(cause);
        this.setSQLState("S1009");
    }

    public UnsupportedConnectionStringException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.setSQLState("S1009");
    }
}

