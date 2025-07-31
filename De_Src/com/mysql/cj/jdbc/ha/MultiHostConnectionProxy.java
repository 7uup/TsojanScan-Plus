/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.ha;

import com.mysql.cj.conf.ConnectionUrl;
import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.ha.MultiHostMySQLConnection;
import com.mysql.cj.util.Util;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

public abstract class MultiHostConnectionProxy
implements InvocationHandler {
    private static final String METHOD_GET_MULTI_HOST_SAFE_PROXY = "getMultiHostSafeProxy";
    private static final String METHOD_EQUALS = "equals";
    private static final String METHOD_HASH_CODE = "hashCode";
    private static final String METHOD_CLOSE = "close";
    private static final String METHOD_ABORT_INTERNAL = "abortInternal";
    private static final String METHOD_ABORT = "abort";
    private static final String METHOD_IS_CLOSED = "isClosed";
    private static final String METHOD_GET_AUTO_COMMIT = "getAutoCommit";
    private static final String METHOD_GET_CATALOG = "getCatalog";
    private static final String METHOD_GET_TRANSACTION_ISOLATION = "getTransactionIsolation";
    private static final String METHOD_GET_SESSION_MAX_ROWS = "getSessionMaxRows";
    List<HostInfo> hostsList;
    protected ConnectionUrl connectionUrl;
    boolean autoReconnect = false;
    JdbcConnection thisAsConnection = this.getNewWrapperForThisAsConnection();
    JdbcConnection proxyConnection = null;
    JdbcConnection currentConnection = null;
    boolean isClosed = false;
    boolean closedExplicitly = false;
    String closedReason = null;
    protected Throwable lastExceptionDealtWith = null;

    MultiHostConnectionProxy() throws SQLException {
    }

    MultiHostConnectionProxy(ConnectionUrl connectionUrl) throws SQLException {
        this();
        this.initializeHostsSpecs(connectionUrl, connectionUrl.getHostsList());
    }

    int initializeHostsSpecs(ConnectionUrl connUrl, List<HostInfo> hosts) {
        this.connectionUrl = connUrl;
        Properties props = connUrl.getConnectionArgumentsAsProperties();
        this.autoReconnect = "true".equalsIgnoreCase(props.getProperty("autoReconnect")) || "true".equalsIgnoreCase(props.getProperty("autoReconnectForPools"));
        this.hostsList = new ArrayList<HostInfo>(hosts);
        int numHosts = this.hostsList.size();
        return numHosts;
    }

    protected JdbcConnection getProxy() {
        return this.proxyConnection != null ? this.proxyConnection : this.thisAsConnection;
    }

    protected final void setProxy(JdbcConnection proxyConn) {
        this.proxyConnection = proxyConn;
        this.propagateProxyDown(proxyConn);
    }

    protected void propagateProxyDown(JdbcConnection proxyConn) {
        this.currentConnection.setProxy(proxyConn);
    }

    JdbcConnection getNewWrapperForThisAsConnection() throws SQLException {
        return new MultiHostMySQLConnection(this);
    }

    Object proxyIfReturnTypeIsJdbcInterface(Class<?> returnType, Object toProxy) {
        if (toProxy != null && Util.isJdbcInterface(returnType)) {
            Class<?> toProxyClass = toProxy.getClass();
            return Proxy.newProxyInstance(toProxyClass.getClassLoader(), Util.getImplementedInterfaces(toProxyClass), this.getNewJdbcInterfaceProxy(toProxy));
        }
        return toProxy;
    }

    InvocationHandler getNewJdbcInterfaceProxy(Object toProxy) {
        return new JdbcInterfaceProxy(toProxy);
    }

    void dealWithInvocationException(InvocationTargetException e) throws SQLException, Throwable, InvocationTargetException {
        Throwable t = e.getTargetException();
        if (t != null) {
            if (this.lastExceptionDealtWith != t && this.shouldExceptionTriggerConnectionSwitch(t)) {
                this.invalidateCurrentConnection();
                this.pickNewConnection();
                this.lastExceptionDealtWith = t;
            }
            throw t;
        }
        throw e;
    }

    abstract boolean shouldExceptionTriggerConnectionSwitch(Throwable var1);

    abstract boolean isMasterConnection();

    synchronized void invalidateCurrentConnection() throws SQLException {
        this.invalidateConnection(this.currentConnection);
    }

    synchronized void invalidateConnection(JdbcConnection conn) throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.realClose(true, !conn.getAutoCommit(), true, null);
            }
        } catch (SQLException sQLException) {
            // empty catch block
        }
    }

    abstract void pickNewConnection() throws SQLException;

    synchronized ConnectionImpl createConnectionForHost(HostInfo hostInfo) throws SQLException {
        ConnectionImpl conn = (ConnectionImpl)ConnectionImpl.getInstance(hostInfo);
        conn.setProxy(this.getProxy());
        return conn;
    }

    void syncSessionState(JdbcConnection source2, JdbcConnection target) throws SQLException {
        if (source2 == null || target == null) {
            return;
        }
        RuntimeProperty<Boolean> sourceUseLocalSessionState = source2.getPropertySet().getBooleanProperty("useLocalSessionState");
        boolean prevUseLocalSessionState = sourceUseLocalSessionState.getValue();
        sourceUseLocalSessionState.setValue(true);
        boolean readOnly = source2.isReadOnly();
        sourceUseLocalSessionState.setValue(prevUseLocalSessionState);
        this.syncSessionState(source2, target, readOnly);
    }

    void syncSessionState(JdbcConnection source2, JdbcConnection target, boolean readOnly) throws SQLException {
        if (target != null) {
            target.setReadOnly(readOnly);
        }
        if (source2 == null || target == null) {
            return;
        }
        RuntimeProperty<Boolean> sourceUseLocalSessionState = source2.getPropertySet().getBooleanProperty("useLocalSessionState");
        boolean prevUseLocalSessionState = sourceUseLocalSessionState.getValue();
        sourceUseLocalSessionState.setValue(true);
        target.setAutoCommit(source2.getAutoCommit());
        target.setCatalog(source2.getCatalog());
        target.setTransactionIsolation(source2.getTransactionIsolation());
        target.setSessionMaxRows(source2.getSessionMaxRows());
        sourceUseLocalSessionState.setValue(prevUseLocalSessionState);
    }

    abstract void doClose() throws SQLException;

    abstract void doAbortInternal() throws SQLException;

    abstract void doAbort(Executor var1) throws SQLException;

    @Override
    public synchronized Object invoke(Object proxy, Method method, Object[] args2) throws Throwable {
        String methodName = method.getName();
        if (METHOD_GET_MULTI_HOST_SAFE_PROXY.equals(methodName)) {
            return this.thisAsConnection;
        }
        if (METHOD_EQUALS.equals(methodName)) {
            return args2[0].equals(this);
        }
        if (METHOD_HASH_CODE.equals(methodName)) {
            return this.hashCode();
        }
        if (METHOD_CLOSE.equals(methodName)) {
            this.doClose();
            this.isClosed = true;
            this.closedReason = "Connection explicitly closed.";
            this.closedExplicitly = true;
            return null;
        }
        if (METHOD_ABORT_INTERNAL.equals(methodName)) {
            this.doAbortInternal();
            this.currentConnection.abortInternal();
            this.isClosed = true;
            this.closedReason = "Connection explicitly closed.";
            return null;
        }
        if (METHOD_ABORT.equals(methodName) && args2.length == 1) {
            this.doAbort((Executor)args2[0]);
            this.isClosed = true;
            this.closedReason = "Connection explicitly closed.";
            return null;
        }
        if (METHOD_IS_CLOSED.equals(methodName)) {
            return this.isClosed;
        }
        try {
            return this.invokeMore(proxy, method, args2);
        } catch (InvocationTargetException e) {
            throw e.getCause() != null ? e.getCause() : e;
        } catch (Exception e) {
            Class<?>[] declaredException;
            for (Class<?> declEx : declaredException = method.getExceptionTypes()) {
                if (!declEx.isAssignableFrom(e.getClass())) continue;
                throw e;
            }
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    abstract Object invokeMore(Object var1, Method var2, Object[] var3) throws Throwable;

    protected boolean allowedOnClosedConnection(Method method) {
        String methodName = method.getName();
        return methodName.equals(METHOD_GET_AUTO_COMMIT) || methodName.equals(METHOD_GET_CATALOG) || methodName.equals(METHOD_GET_TRANSACTION_ISOLATION) || methodName.equals(METHOD_GET_SESSION_MAX_ROWS);
    }

    class JdbcInterfaceProxy
    implements InvocationHandler {
        Object invokeOn = null;

        JdbcInterfaceProxy(Object toInvokeOn) {
            this.invokeOn = toInvokeOn;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args2) throws Throwable {
            if (MultiHostConnectionProxy.METHOD_EQUALS.equals(method.getName())) {
                return args2[0].equals(this);
            }
            MultiHostConnectionProxy multiHostConnectionProxy = MultiHostConnectionProxy.this;
            synchronized (multiHostConnectionProxy) {
                Object result = null;
                try {
                    result = method.invoke(this.invokeOn, args2);
                    result = MultiHostConnectionProxy.this.proxyIfReturnTypeIsJdbcInterface(method.getReturnType(), result);
                } catch (InvocationTargetException e) {
                    MultiHostConnectionProxy.this.dealWithInvocationException(e);
                }
                return result;
            }
        }
    }
}

