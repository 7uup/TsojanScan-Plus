/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj;

import com.mysql.cj.Session;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import java.util.Properties;

public interface MysqlConnection {
    public PropertySet getPropertySet();

    public void createNewIO(boolean var1);

    public long getId();

    public Properties getProperties();

    public Object getConnectionMutex();

    public Session getSession();

    public String getURL();

    public String getUser();

    public ExceptionInterceptor getExceptionInterceptor();

    public void checkClosed();

    public void normalClose();

    public void cleanup(Throwable var1);
}

