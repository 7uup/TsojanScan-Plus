/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.conf.url;

import com.mysql.cj.Messages;
import com.mysql.cj.conf.ConnectionUrl;
import com.mysql.cj.conf.ConnectionUrlParser;
import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.conf.PropertyDefinitions;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.util.StringUtils;
import java.util.Comparator;
import java.util.Map;
import java.util.Properties;

public class XDevAPIConnectionUrl
extends ConnectionUrl {
    private static final int DEFAULT_PORT = 33060;

    public XDevAPIConnectionUrl(ConnectionUrlParser connStrParser, Properties info) {
        super(connStrParser, info);
        this.type = ConnectionUrl.Type.XDEVAPI_SESSION;
        boolean first = true;
        String user = null;
        String password = null;
        boolean hasPriority = false;
        for (HostInfo hi2 : this.hosts) {
            if (first) {
                first = false;
                user = hi2.getUser();
                password = hi2.getPassword();
                hasPriority = hi2.getHostProperties().containsKey(PropertyDefinitions.PropertyKey.PRIORITY.getKeyName());
            } else {
                if (!user.equals(hi2.getUser()) || !password.equals(hi2.getPassword())) {
                    throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ConnectionString.14", new Object[]{ConnectionUrl.Type.XDEVAPI_SESSION.getScheme()}));
                }
                if (hasPriority ^ hi2.getHostProperties().containsKey(PropertyDefinitions.PropertyKey.PRIORITY.getKeyName())) {
                    throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ConnectionString.15", new Object[]{ConnectionUrl.Type.XDEVAPI_SESSION.getScheme()}));
                }
            }
            if (!hasPriority) continue;
            try {
                int priority = Integer.parseInt(hi2.getProperty(PropertyDefinitions.PropertyKey.PRIORITY.getKeyName()));
                if (priority >= 0 && priority <= 100) continue;
                throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ConnectionString.16", new Object[]{ConnectionUrl.Type.XDEVAPI_SESSION.getScheme()}));
            } catch (NumberFormatException e) {
                throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ConnectionString.16", new Object[]{ConnectionUrl.Type.XDEVAPI_SESSION.getScheme()}));
            }
        }
        if (hasPriority) {
            this.hosts.sort(Comparator.comparing(hi -> Integer.parseInt(hi.getHostProperties().get(PropertyDefinitions.PropertyKey.PRIORITY.getKeyName()))).reversed());
        }
    }

    @Override
    protected void processColdFusionAutoConfiguration() {
    }

    @Override
    protected void preprocessPerTypeHostProperties(Map<String, String> hostProps) {
        if (hostProps.containsKey(PropertyDefinitions.PropertyKey.ADDRESS.getKeyName())) {
            String address = hostProps.get(PropertyDefinitions.PropertyKey.ADDRESS.getKeyName());
            ConnectionUrlParser.Pair<String, Integer> hostPortPair = ConnectionUrlParser.parseHostPortPair(address);
            String host = StringUtils.safeTrim((String)hostPortPair.left);
            Integer port = (Integer)hostPortPair.right;
            if (!StringUtils.isNullOrEmpty(host) && !hostProps.containsKey(PropertyDefinitions.PropertyKey.HOST.getKeyName())) {
                hostProps.put(PropertyDefinitions.PropertyKey.HOST.getKeyName(), host);
            }
            if (port != -1 && !hostProps.containsKey(PropertyDefinitions.PropertyKey.PORT.getKeyName())) {
                hostProps.put(PropertyDefinitions.PropertyKey.PORT.getKeyName(), port.toString());
            }
        }
    }

    @Override
    public int getDefaultPort() {
        return 33060;
    }

    @Override
    protected void fixProtocolDependencies(Map<String, String> hostProps) {
    }
}

