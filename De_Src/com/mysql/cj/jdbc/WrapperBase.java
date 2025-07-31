/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.jdbc.MysqlPooledConnection;
import com.mysql.cj.util.Util;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.Map;

abstract class WrapperBase {
    protected MysqlPooledConnection pooledConnection;
    protected Map<Class<?>, Object> unwrappedInterfaces = null;
    protected ExceptionInterceptor exceptionInterceptor;

    protected void checkAndFireConnectionError(SQLException sqlEx) throws SQLException {
        if (this.pooledConnection != null && "08S01".equals(sqlEx.getSQLState())) {
            this.pooledConnection.callConnectionEventListeners(1, sqlEx);
        }
        throw sqlEx;
    }

    protected WrapperBase(MysqlPooledConnection pooledConnection) {
        this.pooledConnection = pooledConnection;
        this.exceptionInterceptor = this.pooledConnection.getExceptionInterceptor();
    }

    protected class ConnectionErrorFiringInvocationHandler
    implements InvocationHandler {
        Object invokeOn = null;

        public ConnectionErrorFiringInvocationHandler(Object toInvokeOn) {
            this.invokeOn = toInvokeOn;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args2) throws Throwable {
            if ("equals".equals(method.getName())) {
                return args2[0].equals(this);
            }
            Object result = null;
            try {
                result = method.invoke(this.invokeOn, args2);
                if (result != null) {
                    result = this.proxyIfInterfaceIsJdbc(result, result.getClass());
                }
            } catch (InvocationTargetException e) {
                if (e.getTargetException() instanceof SQLException) {
                    WrapperBase.this.checkAndFireConnectionError((SQLException)e.getTargetException());
                }
                throw e;
            }
            return result;
        }

        private Object proxyIfInterfaceIsJdbc(Object toProxy, Class<?> clazz) {
            int n = 0;
            Class<?>[] interfaces = clazz.getInterfaces();
            Class<?>[] classArray = interfaces;
            int n2 = classArray.length;
            if (n < n2) {
                Class<?> iclass = classArray[n];
                String packageName = Util.getPackageName(iclass);
                if ("java.sql".equals(packageName) || "javax.sql".equals(packageName)) {
                    return Proxy.newProxyInstance(toProxy.getClass().getClassLoader(), interfaces, new ConnectionErrorFiringInvocationHandler(toProxy));
                }
                return this.proxyIfInterfaceIsJdbc(toProxy, iclass);
            }
            return toProxy;
        }
    }
}

