/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.exceptions;

import com.mysql.cj.exceptions.CJException;

public class ClosedOnExpiredPasswordException
extends CJException {
    private static final long serialVersionUID = -3807215681364413250L;

    public ClosedOnExpiredPasswordException() {
        this.setVendorCode(1862);
    }

    public ClosedOnExpiredPasswordException(String message) {
        super(message);
        this.setVendorCode(1862);
    }

    public ClosedOnExpiredPasswordException(String message, Throwable cause) {
        super(message, cause);
        this.setVendorCode(1862);
    }

    public ClosedOnExpiredPasswordException(Throwable cause) {
        super(cause);
        this.setVendorCode(1862);
    }

    protected ClosedOnExpiredPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.setVendorCode(1862);
    }
}

