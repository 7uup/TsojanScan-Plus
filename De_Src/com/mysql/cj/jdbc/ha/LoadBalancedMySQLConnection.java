/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.ha;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import com.mysql.cj.jdbc.ha.LoadBalancedConnection;
import com.mysql.cj.jdbc.ha.LoadBalancedConnectionProxy;
import com.mysql.cj.jdbc.ha.MultiHostMySQLConnection;
import java.sql.SQLException;

public class LoadBalancedMySQLConnection
extends MultiHostMySQLConnection
implements LoadBalancedConnection {
    public LoadBalancedMySQLConnection(LoadBalancedConnectionProxy proxy) {
        super(proxy);
    }

    @Override
    public LoadBalancedConnectionProxy getThisAsProxy() {
        return (LoadBalancedConnectionProxy)super.getThisAsProxy();
    }

    @Override
    public void close() throws SQLException {
        try {
            this.getThisAsProxy().doClose();
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void ping() throws SQLException {
        try {
            this.ping(true);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void ping(boolean allConnections) throws SQLException {
        try {
            if (allConnections) {
                this.getThisAsProxy().doPing();
            } else {
                this.getActiveMySQLConnection().ping();
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean addHost(String host) throws SQLException {
        try {
            return this.getThisAsProxy().addHost(host);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void removeHost(String host) throws SQLException {
        try {
            this.getThisAsProxy().removeHost(host);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void removeHostWhenNotInUse(String host) throws SQLException {
        try {
            this.getThisAsProxy().removeHostWhenNotInUse(host);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        try {
            return iface.isInstance(this);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        try {
            try {
                return iface.cast(this);
            } catch (ClassCastException cce) {
                throw SQLError.createSQLException(Messages.getString("Common.UnableToUnwrap", new Object[]{iface.toString()}), "S1009", this.getExceptionInterceptor());
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }
}

