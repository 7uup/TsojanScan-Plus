/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.exceptions;

import com.mysql.cj.exceptions.DeadlockTimeoutRollbackMarker;
import java.sql.SQLTransactionRollbackException;

public class MySQLTransactionRollbackException
extends SQLTransactionRollbackException
implements DeadlockTimeoutRollbackMarker {
    static final long serialVersionUID = 6034999468737899730L;

    public MySQLTransactionRollbackException(String reason, String SQLState, int vendorCode) {
        super(reason, SQLState, vendorCode);
    }

    public MySQLTransactionRollbackException(String reason, String SQLState) {
        super(reason, SQLState);
    }

    public MySQLTransactionRollbackException(String reason) {
        super(reason);
    }

    public MySQLTransactionRollbackException() {
    }
}

