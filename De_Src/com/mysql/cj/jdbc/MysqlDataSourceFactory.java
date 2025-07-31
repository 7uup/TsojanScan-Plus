/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.Messages;
import com.mysql.cj.conf.PropertyDefinitions;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.jdbc.MysqlXADataSource;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;

public class MysqlDataSourceFactory
implements ObjectFactory {
    protected static final String DATA_SOURCE_CLASS_NAME = MysqlDataSource.class.getName();
    protected static final String POOL_DATA_SOURCE_CLASS_NAME = MysqlConnectionPoolDataSource.class.getName();
    protected static final String XA_DATA_SOURCE_CLASS_NAME = MysqlXADataSource.class.getName();

    @Override
    public Object getObjectInstance(Object refObj, Name nm, Context ctx, Hashtable<?, ?> env) throws Exception {
        Reference ref = (Reference)refObj;
        String className = ref.getClassName();
        if (className != null && (className.equals(DATA_SOURCE_CLASS_NAME) || className.equals(POOL_DATA_SOURCE_CLASS_NAME) || className.equals(XA_DATA_SOURCE_CLASS_NAME))) {
            String explicitUrlAsString;
            String databaseName;
            String serverName;
            String password;
            MysqlDataSource dataSource = null;
            try {
                dataSource = (MysqlDataSource)Class.forName(className).newInstance();
            } catch (Exception ex) {
                throw new RuntimeException(Messages.getString("MysqlDataSourceFactory.0", new Object[]{className, ex.toString()}));
            }
            int portNumber = 3306;
            String portNumberAsString = this.nullSafeRefAddrStringGet("port", ref);
            if (portNumberAsString != null) {
                portNumber = Integer.parseInt(portNumberAsString);
            }
            dataSource.setPort(portNumber);
            String user = this.nullSafeRefAddrStringGet(PropertyDefinitions.PropertyKey.USER.getKeyName(), ref);
            if (user != null) {
                dataSource.setUser(user);
            }
            if ((password = this.nullSafeRefAddrStringGet(PropertyDefinitions.PropertyKey.PASSWORD.getKeyName(), ref)) != null) {
                dataSource.setPassword(password);
            }
            if ((serverName = this.nullSafeRefAddrStringGet("serverName", ref)) != null) {
                dataSource.setServerName(serverName);
            }
            if ((databaseName = this.nullSafeRefAddrStringGet("databaseName", ref)) != null) {
                dataSource.setDatabaseName(databaseName);
            }
            if ((explicitUrlAsString = this.nullSafeRefAddrStringGet("explicitUrl", ref)) != null && Boolean.valueOf(explicitUrlAsString).booleanValue()) {
                dataSource.setUrl(this.nullSafeRefAddrStringGet("url", ref));
            }
            dataSource.setPropertiesViaRef(ref);
            return dataSource;
        }
        return null;
    }

    private String nullSafeRefAddrStringGet(String referenceName, Reference ref) {
        RefAddr refAddr = ref.get(referenceName);
        String asString2 = refAddr != null ? (String)refAddr.getContent() : null;
        return asString2;
    }
}

