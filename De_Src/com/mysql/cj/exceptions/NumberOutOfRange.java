/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.exceptions;

import com.mysql.cj.exceptions.DataReadException;

public class NumberOutOfRange
extends DataReadException {
    private static final long serialVersionUID = -61091413023651438L;

    public NumberOutOfRange(String msg) {
        super(msg);
        this.setSQLState("22003");
    }
}

