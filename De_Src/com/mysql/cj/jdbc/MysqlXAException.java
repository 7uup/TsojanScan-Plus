/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import javax.transaction.xa.XAException;

class MysqlXAException
extends XAException {
    private static final long serialVersionUID = -9075817535836563004L;
    private String message;
    protected String xidAsString;

    public MysqlXAException(int errorCode, String message, String xidAsString) {
        super(errorCode);
        this.message = message;
        this.xidAsString = xidAsString;
    }

    public MysqlXAException(String message, String xidAsString) {
        this.message = message;
        this.xidAsString = xidAsString;
    }

    @Override
    public String getMessage() {
        String superMessage = super.getMessage();
        StringBuilder returnedMessage = new StringBuilder();
        if (superMessage != null) {
            returnedMessage.append(superMessage);
            returnedMessage.append(":");
        }
        if (this.message != null) {
            returnedMessage.append(this.message);
        }
        return returnedMessage.toString();
    }
}

