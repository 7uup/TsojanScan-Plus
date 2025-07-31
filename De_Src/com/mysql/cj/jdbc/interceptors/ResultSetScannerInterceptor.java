/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.interceptors;

import com.mysql.cj.Messages;
import com.mysql.cj.MysqlConnection;
import com.mysql.cj.Query;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.interceptors.QueryInterceptor;
import com.mysql.cj.jdbc.result.ResultSetInternalMethods;
import com.mysql.cj.log.Log;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.ServerSession;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.Properties;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultSetScannerInterceptor
implements QueryInterceptor {
    protected Pattern regexP;

    @Override
    public QueryInterceptor init(MysqlConnection conn, Properties props, Log log) {
        String regexFromUser = props.getProperty("resultSetScannerRegex");
        if (regexFromUser == null || regexFromUser.length() == 0) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ResultSetScannerInterceptor.0"));
        }
        try {
            this.regexP = Pattern.compile(regexFromUser);
        } catch (Throwable t) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ResultSetScannerInterceptor.1"), t);
        }
        return this;
    }

    @Override
    public <T extends Resultset> T postProcess(Supplier<String> sql, Query interceptedQuery, T originalResultSet, ServerSession serverSession) {
        final T finalResultSet = originalResultSet;
        return (T)((Resultset)Proxy.newProxyInstance(originalResultSet.getClass().getClassLoader(), new Class[]{Resultset.class, ResultSetInternalMethods.class}, new InvocationHandler(){

            @Override
            public Object invoke(Object proxy, Method method, Object[] args2) throws Throwable {
                Matcher matcher;
                if ("equals".equals(method.getName())) {
                    return args2[0].equals(this);
                }
                Object invocationResult = method.invoke(finalResultSet, args2);
                String methodName = method.getName();
                if ((invocationResult != null && invocationResult instanceof String || "getString".equals(methodName) || "getObject".equals(methodName) || "getObjectStoredProc".equals(methodName)) && (matcher = ResultSetScannerInterceptor.this.regexP.matcher(invocationResult.toString())).matches()) {
                    throw new SQLException(Messages.getString("ResultSetScannerInterceptor.2"));
                }
                return invocationResult;
            }
        }));
    }

    @Override
    public <T extends Resultset> T preProcess(Supplier<String> sql, Query interceptedQuery) {
        return null;
    }

    @Override
    public boolean executeTopLevelOnly() {
        return false;
    }

    @Override
    public void destroy() {
    }
}

