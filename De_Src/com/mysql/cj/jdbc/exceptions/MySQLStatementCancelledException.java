/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.exceptions;

import com.mysql.cj.Messages;
import java.sql.SQLNonTransientException;

public class MySQLStatementCancelledException
extends SQLNonTransientException {
    static final long serialVersionUID = -8762717748377197378L;

    public MySQLStatementCancelledException(String reason, String SQLState, int vendorCode) {
        super(reason, SQLState, vendorCode);
    }

    public MySQLStatementCancelledException(String reason, String SQLState) {
        super(reason, SQLState);
    }

    public MySQLStatementCancelledException(String reason) {
        super(reason);
    }

    public MySQLStatementCancelledException() {
        super(Messages.getString("MySQLStatementCancelledException.0"));
    }
}

