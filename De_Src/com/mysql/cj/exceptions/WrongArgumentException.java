/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.exceptions;

import com.mysql.cj.exceptions.CJException;

public class WrongArgumentException
extends CJException {
    private static final long serialVersionUID = 3991597077197801820L;

    public WrongArgumentException() {
        this.setSQLState("S1009");
    }

    public WrongArgumentException(String message) {
        super(message);
        this.setSQLState("S1009");
    }

    public WrongArgumentException(String message, Throwable cause) {
        super(message, cause);
        this.setSQLState("S1009");
    }

    public WrongArgumentException(Throwable cause) {
        super(cause);
        this.setSQLState("S1009");
    }

    public WrongArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.setSQLState("S1009");
    }
}

