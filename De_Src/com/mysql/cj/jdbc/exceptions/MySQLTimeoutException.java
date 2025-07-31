/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.exceptions;

import com.mysql.cj.Messages;
import java.sql.SQLTimeoutException;

public class MySQLTimeoutException
extends SQLTimeoutException {
    static final long serialVersionUID = -789621240523239939L;

    public MySQLTimeoutException(String reason, String SQLState, int vendorCode) {
        super(reason, SQLState, vendorCode);
    }

    public MySQLTimeoutException(String reason, String SQLState) {
        super(reason, SQLState);
    }

    public MySQLTimeoutException(String reason) {
        super(reason);
    }

    public MySQLTimeoutException() {
        super(Messages.getString("MySQLTimeoutException.0"));
    }
}

