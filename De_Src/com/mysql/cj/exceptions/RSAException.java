/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.exceptions;

import com.mysql.cj.exceptions.CJException;

public class RSAException
extends CJException {
    private static final long serialVersionUID = -1878681511263159173L;

    public RSAException() {
    }

    public RSAException(String message) {
        super(message);
    }

    public RSAException(String message, Throwable cause) {
        super(message, cause);
    }

    public RSAException(Throwable cause) {
        super(cause);
    }

    protected RSAException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

