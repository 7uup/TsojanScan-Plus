/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.exceptions;

import com.mysql.cj.exceptions.CJException;

public class UnableToConnectException
extends CJException {
    private static final long serialVersionUID = 6824175447292574109L;

    public UnableToConnectException() {
        this.setSQLState("08001");
    }

    public UnableToConnectException(String message) {
        super(message);
        this.setSQLState("08001");
    }

    public UnableToConnectException(String message, Throwable cause) {
        super(message, cause);
        this.setSQLState("08001");
    }

    public UnableToConnectException(Throwable cause) {
        super(cause);
        this.setSQLState("08001");
    }

    public UnableToConnectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.setSQLState("08001");
    }
}

