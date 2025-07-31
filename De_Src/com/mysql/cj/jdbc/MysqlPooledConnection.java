/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.jdbc.ConnectionWrapper;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import javax.sql.PooledConnection;
import javax.sql.StatementEvent;
import javax.sql.StatementEventListener;

public class MysqlPooledConnection
implements PooledConnection {
    public static final int CONNECTION_ERROR_EVENT = 1;
    public static final int CONNECTION_CLOSED_EVENT = 2;
    private Map<ConnectionEventListener, ConnectionEventListener> connectionEventListeners;
    private Connection logicalHandle = null;
    private JdbcConnection physicalConn;
    private ExceptionInterceptor exceptionInterceptor;
    private final Map<StatementEventListener, StatementEventListener> statementEventListeners = new HashMap<StatementEventListener, StatementEventListener>();

    protected static MysqlPooledConnection getInstance(JdbcConnection connection) throws SQLException {
        return new MysqlPooledConnection(connection);
    }

    public MysqlPooledConnection(JdbcConnection connection) {
        this.physicalConn = connection;
        this.connectionEventListeners = new HashMap<ConnectionEventListener, ConnectionEventListener>();
        this.exceptionInterceptor = this.physicalConn.getExceptionInterceptor();
    }

    @Override
    public synchronized void addConnectionEventListener(ConnectionEventListener connectioneventlistener) {
        if (this.connectionEventListeners != null) {
            this.connectionEventListeners.put(connectioneventlistener, connectioneventlistener);
        }
    }

    @Override
    public synchronized void removeConnectionEventListener(ConnectionEventListener connectioneventlistener) {
        if (this.connectionEventListeners != null) {
            this.connectionEventListeners.remove(connectioneventlistener);
        }
    }

    @Override
    public synchronized Connection getConnection() throws SQLException {
        try {
            return this.getConnection(true, false);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    protected synchronized Connection getConnection(boolean resetServerState, boolean forXa) throws SQLException {
        if (this.physicalConn == null) {
            SQLException sqlException = SQLError.createSQLException(Messages.getString("MysqlPooledConnection.0"), this.exceptionInterceptor);
            this.callConnectionEventListeners(1, sqlException);
            throw sqlException;
        }
        try {
            if (this.logicalHandle != null) {
                ((ConnectionWrapper)this.logicalHandle).close(false);
            }
            if (resetServerState) {
                this.physicalConn.resetServerState();
            }
            this.logicalHandle = ConnectionWrapper.getInstance(this, this.physicalConn, forXa);
        } catch (SQLException sqlException) {
            this.callConnectionEventListeners(1, sqlException);
            throw sqlException;
        }
        return this.logicalHandle;
    }

    @Override
    public synchronized void close() throws SQLException {
        try {
            if (this.physicalConn != null) {
                this.physicalConn.close();
                this.physicalConn = null;
            }
            if (this.connectionEventListeners != null) {
                this.connectionEventListeners.clear();
                this.connectionEventListeners = null;
            }
            this.statementEventListeners.clear();
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    protected synchronized void callConnectionEventListeners(int eventType, SQLException sqlException) {
        if (this.connectionEventListeners == null) {
            return;
        }
        Iterator<Map.Entry<ConnectionEventListener, ConnectionEventListener>> iterator2 = this.connectionEventListeners.entrySet().iterator();
        ConnectionEvent connectionevent = new ConnectionEvent(this, sqlException);
        while (iterator2.hasNext()) {
            ConnectionEventListener connectioneventlistener = iterator2.next().getValue();
            if (eventType == 2) {
                connectioneventlistener.connectionClosed(connectionevent);
                continue;
            }
            if (eventType != 1) continue;
            connectioneventlistener.connectionErrorOccurred(connectionevent);
        }
    }

    protected ExceptionInterceptor getExceptionInterceptor() {
        return this.exceptionInterceptor;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addStatementEventListener(StatementEventListener listener) {
        Map<StatementEventListener, StatementEventListener> map = this.statementEventListeners;
        synchronized (map) {
            this.statementEventListeners.put(listener, listener);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeStatementEventListener(StatementEventListener listener) {
        Map<StatementEventListener, StatementEventListener> map = this.statementEventListeners;
        synchronized (map) {
            this.statementEventListeners.remove(listener);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void fireStatementEvent(StatementEvent event) throws SQLException {
        Map<StatementEventListener, StatementEventListener> map = this.statementEventListeners;
        synchronized (map) {
            for (StatementEventListener listener : this.statementEventListeners.keySet()) {
                listener.statementClosed(event);
            }
        }
    }
}

