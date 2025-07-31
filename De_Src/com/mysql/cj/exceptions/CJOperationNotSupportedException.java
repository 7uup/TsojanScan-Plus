/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.exceptions;

import com.mysql.cj.exceptions.CJException;

public class CJOperationNotSupportedException
extends CJException {
    private static final long serialVersionUID = 2619184100062994443L;

    public CJOperationNotSupportedException() {
    }

    public CJOperationNotSupportedException(String message) {
        super(message);
    }
}

