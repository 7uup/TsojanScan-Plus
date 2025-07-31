/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.ha;

import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import com.mysql.cj.jdbc.ha.LoadBalanceExceptionChecker;
import com.mysql.cj.util.StringUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class StandardLoadBalanceExceptionChecker
implements LoadBalanceExceptionChecker {
    private List<String> sqlStateList;
    private List<Class<?>> sqlExClassList;

    @Override
    public boolean shouldExceptionTriggerFailover(Throwable ex) {
        Iterator<Object> i;
        String sqlState;
        String string = sqlState = ex instanceof SQLException ? ((SQLException)ex).getSQLState() : null;
        if (sqlState != null) {
            if (sqlState.startsWith("08")) {
                return true;
            }
            if (this.sqlStateList != null) {
                i = this.sqlStateList.iterator();
                while (i.hasNext()) {
                    if (!sqlState.startsWith(((String)i.next()).toString())) continue;
                    return true;
                }
            }
        }
        if (ex instanceof CommunicationsException || ex instanceof CJCommunicationsException) {
            return true;
        }
        if (this.sqlExClassList != null) {
            i = this.sqlExClassList.iterator();
            while (i.hasNext()) {
                if (!((Class)i.next()).isInstance(ex)) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(Properties props) {
        this.configureSQLStateList(props.getProperty("loadBalanceSQLStateFailover", null));
        this.configureSQLExceptionSubclassList(props.getProperty("loadBalanceSQLExceptionSubclassFailover", null));
    }

    private void configureSQLStateList(String sqlStates) {
        if (sqlStates == null || "".equals(sqlStates)) {
            return;
        }
        List<String> states = StringUtils.split(sqlStates, ",", true);
        ArrayList<String> newStates = new ArrayList<String>();
        for (String state : states) {
            if (state.length() <= 0) continue;
            newStates.add(state);
        }
        if (newStates.size() > 0) {
            this.sqlStateList = newStates;
        }
    }

    private void configureSQLExceptionSubclassList(String sqlExClasses) {
        if (sqlExClasses == null || "".equals(sqlExClasses)) {
            return;
        }
        List<String> classes = StringUtils.split(sqlExClasses, ",", true);
        ArrayList newClasses = new ArrayList();
        for (String exClass : classes) {
            try {
                Class<?> c = Class.forName(exClass);
                newClasses.add(c);
            } catch (Exception exception) {}
        }
        if (newClasses.size() > 0) {
            this.sqlExClassList = newClasses;
        }
    }
}

