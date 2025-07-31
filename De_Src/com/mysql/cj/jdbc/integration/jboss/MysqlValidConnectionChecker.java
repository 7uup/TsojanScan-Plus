/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jboss.resource.adapter.jdbc.ValidConnectionChecker
 */
package com.mysql.cj.jdbc.integration.jboss;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.jboss.resource.adapter.jdbc.ValidConnectionChecker;

public final class MysqlValidConnectionChecker
implements ValidConnectionChecker,
Serializable {
    private static final long serialVersionUID = 8909421133577519177L;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public SQLException isValidConnection(Connection conn) {
        Statement pingStatement = null;
        try {
            pingStatement = conn.createStatement();
            pingStatement.executeQuery("/* ping */ SELECT 1").close();
            SQLException sQLException = null;
            return sQLException;
        } catch (SQLException sqlEx) {
            SQLException sQLException = sqlEx;
            return sQLException;
        } finally {
            if (pingStatement != null) {
                try {
                    pingStatement.close();
                } catch (SQLException sQLException) {}
            }
        }
    }
}

