/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.exceptions;

import com.mysql.cj.exceptions.CJException;

public class DataReadException
extends CJException {
    private static final long serialVersionUID = 1684265521187171525L;

    public DataReadException(Exception cause) {
        super(cause);
        this.setSQLState("S1009");
    }

    public DataReadException(String msg) {
        super(msg);
        this.setSQLState("S1009");
    }
}

