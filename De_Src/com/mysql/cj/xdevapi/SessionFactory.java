/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.conf.ConnectionUrl;
import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.InvalidConnectionAttributeException;
import com.mysql.cj.xdevapi.Session;
import com.mysql.cj.xdevapi.SessionImpl;
import java.util.Properties;

public class SessionFactory {
    private ConnectionUrl parseUrl(String url) {
        ConnectionUrl connUrl = ConnectionUrl.getConnectionUrlInstance(url, null);
        if (connUrl == null || connUrl.getType() != ConnectionUrl.Type.XDEVAPI_SESSION) {
            throw ExceptionFactory.createException(InvalidConnectionAttributeException.class, "Initialization via URL failed for \"" + url + "\"");
        }
        return connUrl;
    }

    public Session getSession(String url) {
        CJCommunicationsException latestException = null;
        ConnectionUrl connUrl = this.parseUrl(url);
        for (HostInfo hi : connUrl.getHostsList()) {
            try {
                return new SessionImpl(hi);
            } catch (CJCommunicationsException e) {
                latestException = e;
            }
        }
        if (latestException != null) {
            throw latestException;
        }
        return null;
    }

    public Session getSession(Properties properties) {
        ConnectionUrl connUrl = ConnectionUrl.getConnectionUrlInstance(ConnectionUrl.Type.XDEVAPI_SESSION.getScheme(), properties);
        return new SessionImpl(connUrl.getMainHost());
    }
}

