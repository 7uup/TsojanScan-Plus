/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.conf;

import com.mysql.cj.conf.DatabaseUrlContainer;
import com.mysql.cj.conf.PropertyDefinitions;
import com.mysql.cj.util.StringUtils;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class HostInfo
implements DatabaseUrlContainer {
    private static final String HOST_PORT_SEPARATOR = ":";
    private final DatabaseUrlContainer originalUrl;
    private final String host;
    private final int port;
    private final String user;
    private final String password;
    private final boolean isPasswordless;
    private final Map<String, String> hostProperties = new HashMap<String, String>();

    public HostInfo() {
        this(null, null, -1, null, null, true, null);
    }

    public HostInfo(DatabaseUrlContainer url, String host, int port, String user, String password) {
        this(url, host, port, user, password, password == null, null);
    }

    public HostInfo(DatabaseUrlContainer url, String host, int port, String user, String password, Map<String, String> properties) {
        this(url, host, port, user, password, password == null, properties);
    }

    public HostInfo(DatabaseUrlContainer url, String host, int port, String user, String password, boolean isPasswordless, Map<String, String> properties) {
        this.originalUrl = url;
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.isPasswordless = isPasswordless;
        if (properties != null) {
            this.hostProperties.putAll(properties);
        }
    }

    public HostInfo(Properties props) {
        this.originalUrl = null;
        this.host = props.getProperty(PropertyDefinitions.PropertyKey.HOST.getKeyName());
        this.port = Integer.parseInt(props.getProperty(PropertyDefinitions.PropertyKey.PORT.getKeyName()));
        this.user = props.getProperty(PropertyDefinitions.PropertyKey.USER.getKeyName());
        this.password = props.getProperty(PropertyDefinitions.PropertyKey.PASSWORD.getKeyName());
        this.isPasswordless = this.password == null;
        Enumeration keyEnum = props.keys();
        while (keyEnum.hasMoreElements()) {
            String key = (String)keyEnum.nextElement();
            this.hostProperties.put(key, props.getProperty(key));
        }
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public String getHostPortPair() {
        return this.host + HOST_PORT_SEPARATOR + this.port;
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean isPasswordless() {
        return this.isPasswordless;
    }

    public Map<String, String> getHostProperties() {
        return Collections.unmodifiableMap(this.hostProperties);
    }

    public String getProperty(String key) {
        return this.hostProperties.get(key);
    }

    public String getDatabase() {
        String database = this.hostProperties.get(PropertyDefinitions.PropertyKey.DBNAME.getKeyName());
        return StringUtils.isNullOrEmpty(database) ? "" : database;
    }

    public Properties exposeAsProperties() {
        Properties props = new Properties();
        this.hostProperties.entrySet().stream().forEach(e -> props.setProperty((String)e.getKey(), e.getValue() == null ? "" : (String)e.getValue()));
        props.setProperty(PropertyDefinitions.PropertyKey.HOST.getKeyName(), this.getHost());
        props.setProperty(PropertyDefinitions.PropertyKey.PORT.getKeyName(), String.valueOf(this.getPort()));
        props.setProperty(PropertyDefinitions.PropertyKey.USER.getKeyName(), this.getUser());
        props.setProperty(PropertyDefinitions.PropertyKey.PASSWORD.getKeyName(), this.getPassword());
        return props;
    }

    @Override
    public String getDatabaseUrl() {
        return this.originalUrl != null ? this.originalUrl.getDatabaseUrl() : "";
    }

    public String toString() {
        StringBuilder asStr = new StringBuilder(super.toString());
        asStr.append(String.format(" :: {host: \"%s\", port: %d, user: %s, password: %s, hostProperties: %s}", this.host, this.port, this.user, this.password, this.hostProperties));
        return asStr.toString();
    }
}

