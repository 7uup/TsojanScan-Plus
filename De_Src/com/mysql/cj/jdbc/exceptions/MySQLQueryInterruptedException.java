/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.exceptions;

import java.sql.SQLNonTransientException;

public class MySQLQueryInterruptedException
extends SQLNonTransientException {
    private static final long serialVersionUID = -8714521137662613517L;

    public MySQLQueryInterruptedException() {
    }

    public MySQLQueryInterruptedException(String reason, String SQLState, int vendorCode) {
        super(reason, SQLState, vendorCode);
    }

    public MySQLQueryInterruptedException(String reason, String SQLState) {
        super(reason, SQLState);
    }

    public MySQLQueryInterruptedException(String reason) {
        super(reason);
    }
}

