/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.exceptions;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.CJException;

public class CJTimeoutException
extends CJException {
    private static final long serialVersionUID = -7440108828056331100L;

    public CJTimeoutException() {
        super(Messages.getString("MySQLTimeoutException.0"));
    }

    public CJTimeoutException(String message) {
        super(message);
    }

    public CJTimeoutException(Throwable cause) {
        super(cause);
    }

    public CJTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}

