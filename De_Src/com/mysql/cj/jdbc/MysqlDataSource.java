/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.Messages;
import com.mysql.cj.conf.AbstractRuntimeProperty;
import com.mysql.cj.conf.ConnectionUrl;
import com.mysql.cj.conf.PropertyDefinitions;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.jdbc.JdbcPropertySet;
import com.mysql.cj.jdbc.JdbcPropertySetImpl;
import com.mysql.cj.jdbc.MysqlDataSourceFactory;
import com.mysql.cj.jdbc.NonRegisteringDriver;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;
import javax.sql.DataSource;

public class MysqlDataSource
extends JdbcPropertySetImpl
implements DataSource,
Referenceable,
Serializable,
JdbcPropertySet {
    static final long serialVersionUID = -5515846944416881264L;
    protected static final NonRegisteringDriver mysqlDriver;
    protected transient PrintWriter logWriter = null;
    protected String databaseName = null;
    protected String encoding = null;
    protected String hostName = null;
    protected String password = null;
    protected String profileSQLString = "false";
    protected String url = null;
    protected String user = null;
    protected boolean explicitUrl = false;
    protected int port = 3306;
    protected String description = "MySQL Connector/J Data Source";

    @Override
    public Connection getConnection() throws SQLException {
        try {
            return this.getConnection(this.user, this.password);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    @Override
    public Connection getConnection(String userID, String pass) throws SQLException {
        try {
            Properties props = this.exposeAsProperties();
            if (userID != null) {
                props.setProperty(PropertyDefinitions.PropertyKey.USER.getKeyName(), userID);
            }
            if (pass != null) {
                props.setProperty(PropertyDefinitions.PropertyKey.PASSWORD.getKeyName(), pass);
            }
            return this.getConnection(props);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public void setDatabaseName(String dbName) {
        this.databaseName = dbName;
    }

    public String getDatabaseName() {
        return this.databaseName != null ? this.databaseName : "";
    }

    @Override
    public void setLogWriter(PrintWriter output) throws SQLException {
        try {
            this.logWriter = output;
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    @Override
    public PrintWriter getLogWriter() {
        try {
            return this.logWriter;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        try {
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    @Override
    public int getLoginTimeout() {
        try {
            return 0;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public void setPort(int p) {
        this.port = p;
    }

    public int getPort() {
        return this.port;
    }

    public void setPortNumber(int p) {
        this.setPort(p);
    }

    public int getPortNumber() {
        return this.getPort();
    }

    public void setPropertiesViaRef(Reference ref) throws SQLException {
        for (String propName : PropertyDefinitions.PROPERTY_NAME_TO_PROPERTY_DEFINITION.keySet()) {
            RuntimeProperty propToSet = this.getProperty(propName);
            if (ref == null) continue;
            propToSet.initializeFrom(ref, null);
        }
        this.postInitialization();
    }

    @Override
    public Reference getReference() throws NamingException {
        String factoryName = MysqlDataSourceFactory.class.getName();
        Reference ref = new Reference(this.getClass().getName(), factoryName, null);
        ref.add(new StringRefAddr(PropertyDefinitions.PropertyKey.USER.getKeyName(), this.getUser()));
        ref.add(new StringRefAddr(PropertyDefinitions.PropertyKey.PASSWORD.getKeyName(), this.password));
        ref.add(new StringRefAddr("serverName", this.getServerName()));
        ref.add(new StringRefAddr("port", "" + this.getPort()));
        ref.add(new StringRefAddr("databaseName", this.getDatabaseName()));
        ref.add(new StringRefAddr("url", this.getUrl()));
        ref.add(new StringRefAddr("explicitUrl", String.valueOf(this.explicitUrl)));
        for (String propName : PropertyDefinitions.PROPERTY_NAME_TO_PROPERTY_DEFINITION.keySet()) {
            RuntimeProperty propToStore = this.getProperty(propName);
            String val = propToStore.getStringValue();
            if (val == null) continue;
            ref.add(new StringRefAddr(propToStore.getPropertyDefinition().getName(), val));
        }
        return ref;
    }

    public void setServerName(String serverName) {
        this.hostName = serverName;
    }

    public String getServerName() {
        return this.hostName != null ? this.hostName : "";
    }

    public void setURL(String url) {
        this.setUrl(url);
    }

    public String getURL() {
        return this.getUrl();
    }

    public void setUrl(String url) {
        this.url = url;
        this.explicitUrl = true;
    }

    public String getUrl() {
        if (!this.explicitUrl) {
            StringBuilder sbUrl = new StringBuilder(ConnectionUrl.Type.SINGLE_CONNECTION.getScheme());
            sbUrl.append("//").append(this.getServerName()).append(":").append(this.getPort()).append("/").append(this.getDatabaseName());
            return sbUrl.toString();
        }
        return this.url;
    }

    public void setUser(String userID) {
        this.user = userID;
    }

    public String getUser() {
        return this.user;
    }

    protected Connection getConnection(Properties props) throws SQLException {
        String jdbcUrlToUse = this.explicitUrl ? this.url : this.getUrl();
        ConnectionUrl connUrl = ConnectionUrl.getConnectionUrlInstance(jdbcUrlToUse, null);
        Properties urlProps = connUrl.getConnectionArgumentsAsProperties();
        urlProps.remove(PropertyDefinitions.PropertyKey.HOST.getKeyName());
        urlProps.remove(PropertyDefinitions.PropertyKey.PORT.getKeyName());
        urlProps.remove(PropertyDefinitions.PropertyKey.DBNAME.getKeyName());
        urlProps.stringPropertyNames().stream().forEach(k -> props.setProperty((String)k, urlProps.getProperty((String)k)));
        return mysqlDriver.connect(jdbcUrlToUse, props);
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        try {
            return null;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        try {
            return false;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    protected String getStringRuntimeProperty(String name) throws SQLException {
        try {
            return this.getStringProperty(name).getValue();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    protected void setStringRuntimeProperty(String name, String value) throws SQLException {
        try {
            ((AbstractRuntimeProperty)this.getStringProperty(name)).setValueInternal(value, null, null);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    protected boolean getBooleanRuntimeProperty(String name) throws SQLException {
        try {
            return this.getBooleanProperty(name).getValue();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    protected void setBooleanRuntimeProperty(String name, boolean value) throws SQLException {
        try {
            ((AbstractRuntimeProperty)this.getBooleanProperty(name)).setValueInternal(value, null, null);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    protected int getIntegerRuntimeProperty(String name) throws SQLException {
        try {
            return this.getIntegerProperty(name).getValue();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    protected void setIntegerRuntimeProperty(String name, int value) throws SQLException {
        try {
            ((AbstractRuntimeProperty)this.getIntegerProperty(name)).setValueInternal(value, null, null);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    protected long getLongRuntimeProperty(String name) throws SQLException {
        try {
            return this.getLongProperty(name).getValue();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    protected void setLongRuntimeProperty(String name, long value) throws SQLException {
        try {
            ((AbstractRuntimeProperty)this.getLongProperty(name)).setValueInternal(value, null, null);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    protected int getMemorySizeRuntimeProperty(String name) throws SQLException {
        try {
            return this.getMemorySizeProperty(name).getValue();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    protected void setMemorySizeRuntimeProperty(String name, int value) throws SQLException {
        try {
            ((AbstractRuntimeProperty)this.getMemorySizeProperty(name)).setValueInternal(value, null, null);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    protected String getEnumRuntimeProperty(String name) throws SQLException {
        try {
            return this.getEnumProperty(name).getStringValue();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    protected void setEnumRuntimeProperty(String name, String value) throws SQLException {
        try {
            ((AbstractRuntimeProperty)this.getEnumProperty(name)).setValueInternal(value, null);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    @Override
    public Properties exposeAsProperties() {
        Properties props = new Properties();
        for (String propName : PropertyDefinitions.PROPERTY_NAME_TO_PROPERTY_DEFINITION.keySet()) {
            RuntimeProperty propToGet = this.getProperty(propName);
            String propValue = propToGet.getStringValue();
            if (propValue == null || !propToGet.isExplicitlySet()) continue;
            props.setProperty(propToGet.getPropertyDefinition().getName(), propValue);
        }
        return props;
    }

    static {
        try {
            mysqlDriver = new NonRegisteringDriver();
        } catch (Exception E) {
            throw new RuntimeException(Messages.getString("MysqlDataSource.0"));
        }
    }

    public boolean getDumpQueriesOnException() throws SQLException {
        return this.getBooleanRuntimeProperty("dumpQueriesOnException");
    }

    public void setDumpQueriesOnException(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("dumpQueriesOnException", bl);
    }

    public String getSocksProxyHost() throws SQLException {
        return this.getStringRuntimeProperty("socksProxyHost");
    }

    public void setSocksProxyHost(String string) throws SQLException {
        this.setStringRuntimeProperty("socksProxyHost", string);
    }

    public String getResourceId() throws SQLException {
        return this.getStringRuntimeProperty("resourceId");
    }

    public void setResourceId(String string) throws SQLException {
        this.setStringRuntimeProperty("resourceId", string);
    }

    public boolean getHaEnableJMX() throws SQLException {
        return this.getBooleanRuntimeProperty("haEnableJMX");
    }

    public void setHaEnableJMX(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("haEnableJMX", bl);
    }

    public int getLoadBalanceAutoCommitStatementThreshold() throws SQLException {
        return this.getIntegerRuntimeProperty("loadBalanceAutoCommitStatementThreshold");
    }

    public void setLoadBalanceAutoCommitStatementThreshold(int n) throws SQLException {
        this.setIntegerRuntimeProperty("loadBalanceAutoCommitStatementThreshold", n);
    }

    public String getLogger() throws SQLException {
        return this.getStringRuntimeProperty("logger");
    }

    public void setLogger(String string) throws SQLException {
        this.setStringRuntimeProperty("logger", string);
    }

    public boolean getQueryTimeoutKillsConnection() throws SQLException {
        return this.getBooleanRuntimeProperty("queryTimeoutKillsConnection");
    }

    public void setQueryTimeoutKillsConnection(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("queryTimeoutKillsConnection", bl);
    }

    public String getClobCharacterEncoding() throws SQLException {
        return this.getStringRuntimeProperty("clobCharacterEncoding");
    }

    public void setClobCharacterEncoding(String string) throws SQLException {
        this.setStringRuntimeProperty("clobCharacterEncoding", string);
    }

    public boolean getUseLocalTransactionState() throws SQLException {
        return this.getBooleanRuntimeProperty("useLocalTransactionState");
    }

    public void setUseLocalTransactionState(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("useLocalTransactionState", bl);
    }

    public int getLargeRowSizeThreshold() throws SQLException {
        return this.getIntegerRuntimeProperty("largeRowSizeThreshold");
    }

    public void setLargeRowSizeThreshold(int n) throws SQLException {
        this.setIntegerRuntimeProperty("largeRowSizeThreshold", n);
    }

    public int getLoadBalanceBlacklistTimeout() throws SQLException {
        return this.getIntegerRuntimeProperty("loadBalanceBlacklistTimeout");
    }

    public void setLoadBalanceBlacklistTimeout(int n) throws SQLException {
        this.setIntegerRuntimeProperty("loadBalanceBlacklistTimeout", n);
    }

    public String getLoadBalanceAutoCommitStatementRegex() throws SQLException {
        return this.getStringRuntimeProperty("loadBalanceAutoCommitStatementRegex");
    }

    public void setLoadBalanceAutoCommitStatementRegex(String string) throws SQLException {
        this.setStringRuntimeProperty("loadBalanceAutoCommitStatementRegex", string);
    }

    public int getMaxAllowedPacket() throws SQLException {
        return this.getIntegerRuntimeProperty("maxAllowedPacket");
    }

    public void setMaxAllowedPacket(int n) throws SQLException {
        this.setIntegerRuntimeProperty("maxAllowedPacket", n);
    }

    public boolean getIgnoreNonTxTables() throws SQLException {
        return this.getBooleanRuntimeProperty("ignoreNonTxTables");
    }

    public void setIgnoreNonTxTables(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("ignoreNonTxTables", bl);
    }

    public boolean getLoadBalanceValidateConnectionOnSwapServer() throws SQLException {
        return this.getBooleanRuntimeProperty("loadBalanceValidateConnectionOnSwapServer");
    }

    public void setLoadBalanceValidateConnectionOnSwapServer(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("loadBalanceValidateConnectionOnSwapServer", bl);
    }

    public int getMaxRows() throws SQLException {
        return this.getIntegerRuntimeProperty("maxRows");
    }

    public void setMaxRows(int n) throws SQLException {
        this.setIntegerRuntimeProperty("maxRows", n);
    }

    public boolean getPadCharsWithSpace() throws SQLException {
        return this.getBooleanRuntimeProperty("padCharsWithSpace");
    }

    public void setPadCharsWithSpace(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("padCharsWithSpace", bl);
    }

    public boolean getUseAffectedRows() throws SQLException {
        return this.getBooleanRuntimeProperty("useAffectedRows");
    }

    public void setUseAffectedRows(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("useAffectedRows", bl);
    }

    public String getEnabledTLSProtocols() throws SQLException {
        return this.getStringRuntimeProperty("enabledTLSProtocols");
    }

    public void setEnabledTLSProtocols(String string) throws SQLException {
        this.setStringRuntimeProperty("enabledTLSProtocols", string);
    }

    public boolean getUseColumnNamesInFindColumn() throws SQLException {
        return this.getBooleanRuntimeProperty("useColumnNamesInFindColumn");
    }

    public void setUseColumnNamesInFindColumn(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("useColumnNamesInFindColumn", bl);
    }

    public String getCharacterSetResults() throws SQLException {
        return this.getStringRuntimeProperty("characterSetResults");
    }

    public void setCharacterSetResults(String string) throws SQLException {
        this.setStringRuntimeProperty("characterSetResults", string);
    }

    public boolean getElideSetAutoCommits() throws SQLException {
        return this.getBooleanRuntimeProperty("elideSetAutoCommits");
    }

    public void setElideSetAutoCommits(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("elideSetAutoCommits", bl);
    }

    public String getServerRSAPublicKeyFile() throws SQLException {
        return this.getStringRuntimeProperty("serverRSAPublicKeyFile");
    }

    public void setServerRSAPublicKeyFile(String string) throws SQLException {
        this.setStringRuntimeProperty("serverRSAPublicKeyFile", string);
    }

    public boolean getPedantic() throws SQLException {
        return this.getBooleanRuntimeProperty("pedantic");
    }

    public void setPedantic(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("pedantic", bl);
    }

    public String getDefaultAuthenticationPlugin() throws SQLException {
        return this.getStringRuntimeProperty("defaultAuthenticationPlugin");
    }

    public void setDefaultAuthenticationPlugin(String string) throws SQLException {
        this.setStringRuntimeProperty("defaultAuthenticationPlugin", string);
    }

    public boolean getOverrideSupportsIntegrityEnhancementFacility() throws SQLException {
        return this.getBooleanRuntimeProperty("overrideSupportsIntegrityEnhancementFacility");
    }

    public void setOverrideSupportsIntegrityEnhancementFacility(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("overrideSupportsIntegrityEnhancementFacility", bl);
    }

    public boolean getAllowLoadLocalInfile() throws SQLException {
        return this.getBooleanRuntimeProperty("allowLoadLocalInfile");
    }

    public void setAllowLoadLocalInfile(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("allowLoadLocalInfile", bl);
    }

    public String getExceptionInterceptors() throws SQLException {
        return this.getStringRuntimeProperty("exceptionInterceptors");
    }

    public void setExceptionInterceptors(String string) throws SQLException {
        this.setStringRuntimeProperty("exceptionInterceptors", string);
    }

    public boolean getAutoDeserialize() throws SQLException {
        return this.getBooleanRuntimeProperty("autoDeserialize");
    }

    public void setAutoDeserialize(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("autoDeserialize", bl);
    }

    public boolean getProfileSQL() throws SQLException {
        return this.getBooleanRuntimeProperty("profileSQL");
    }

    public void setProfileSQL(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("profileSQL", bl);
    }

    public int getTcpSndBuf() throws SQLException {
        return this.getIntegerRuntimeProperty("tcpSndBuf");
    }

    public void setTcpSndBuf(int n) throws SQLException {
        this.setIntegerRuntimeProperty("tcpSndBuf", n);
    }

    public boolean getEmulateUnsupportedPstmts() throws SQLException {
        return this.getBooleanRuntimeProperty("emulateUnsupportedPstmts");
    }

    public void setEmulateUnsupportedPstmts(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("emulateUnsupportedPstmts", bl);
    }

    public int getSelfDestructOnPingMaxOperations() throws SQLException {
        return this.getIntegerRuntimeProperty("selfDestructOnPingMaxOperations");
    }

    public void setSelfDestructOnPingMaxOperations(int n) throws SQLException {
        this.setIntegerRuntimeProperty("selfDestructOnPingMaxOperations", n);
    }

    public String getSocketFactory() throws SQLException {
        return this.getStringRuntimeProperty("socketFactory");
    }

    public void setSocketFactory(String string) throws SQLException {
        this.setStringRuntimeProperty("socketFactory", string);
    }

    public boolean getCacheResultSetMetadata() throws SQLException {
        return this.getBooleanRuntimeProperty("cacheResultSetMetadata");
    }

    public void setCacheResultSetMetadata(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("cacheResultSetMetadata", bl);
    }

    public String getHaLoadBalanceStrategy() throws SQLException {
        return this.getStringRuntimeProperty("haLoadBalanceStrategy");
    }

    public void setHaLoadBalanceStrategy(String string) throws SQLException {
        this.setStringRuntimeProperty("haLoadBalanceStrategy", string);
    }

    public boolean getAllowMasterDownConnections() throws SQLException {
        return this.getBooleanRuntimeProperty("allowMasterDownConnections");
    }

    public void setAllowMasterDownConnections(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("allowMasterDownConnections", bl);
    }

    public int getMaxReconnects() throws SQLException {
        return this.getIntegerRuntimeProperty("maxReconnects");
    }

    public void setMaxReconnects(int n) throws SQLException {
        this.setIntegerRuntimeProperty("maxReconnects", n);
    }

    public boolean getUseSSL() throws SQLException {
        return this.getBooleanRuntimeProperty("useSSL");
    }

    public void setUseSSL(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("useSSL", bl);
    }

    public String getSessionVariables() throws SQLException {
        return this.getStringRuntimeProperty("sessionVariables");
    }

    public void setSessionVariables(String string) throws SQLException {
        this.setStringRuntimeProperty("sessionVariables", string);
    }

    public boolean getAllowSlaveDownConnections() throws SQLException {
        return this.getBooleanRuntimeProperty("allowSlaveDownConnections");
    }

    public void setAllowSlaveDownConnections(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("allowSlaveDownConnections", bl);
    }

    public boolean getDontTrackOpenResources() throws SQLException {
        return this.getBooleanRuntimeProperty("dontTrackOpenResources");
    }

    public void setDontTrackOpenResources(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("dontTrackOpenResources", bl);
    }

    public boolean getAllowPublicKeyRetrieval() throws SQLException {
        return this.getBooleanRuntimeProperty("allowPublicKeyRetrieval");
    }

    public void setAllowPublicKeyRetrieval(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("allowPublicKeyRetrieval", bl);
    }

    public String getTrustCertificateKeyStoreUrl() throws SQLException {
        return this.getStringRuntimeProperty("trustCertificateKeyStoreUrl");
    }

    public void setTrustCertificateKeyStoreUrl(String string) throws SQLException {
        this.setStringRuntimeProperty("trustCertificateKeyStoreUrl", string);
    }

    public boolean getPopulateInsertRowWithDefaultValues() throws SQLException {
        return this.getBooleanRuntimeProperty("populateInsertRowWithDefaultValues");
    }

    public void setPopulateInsertRowWithDefaultValues(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("populateInsertRowWithDefaultValues", bl);
    }

    public boolean getCacheCallableStmts() throws SQLException {
        return this.getBooleanRuntimeProperty("cacheCallableStmts");
    }

    public void setCacheCallableStmts(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("cacheCallableStmts", bl);
    }

    public String getServerTimezone() throws SQLException {
        return this.getStringRuntimeProperty("serverTimezone");
    }

    public void setServerTimezone(String string) throws SQLException {
        this.setStringRuntimeProperty("serverTimezone", string);
    }

    public boolean getXdevapiUseAsyncProtocol() throws SQLException {
        return this.getBooleanRuntimeProperty("xdevapiUseAsyncProtocol");
    }

    public void setXdevapiUseAsyncProtocol(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("xdevapiUseAsyncProtocol", bl);
    }

    public boolean getJdbcCompliantTruncation() throws SQLException {
        return this.getBooleanRuntimeProperty("jdbcCompliantTruncation");
    }

    public void setJdbcCompliantTruncation(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("jdbcCompliantTruncation", bl);
    }

    public boolean getTransformedBitIsBoolean() throws SQLException {
        return this.getBooleanRuntimeProperty("transformedBitIsBoolean");
    }

    public void setTransformedBitIsBoolean(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("transformedBitIsBoolean", bl);
    }

    public String getServerAffinityOrder() throws SQLException {
        return this.getStringRuntimeProperty("serverAffinityOrder");
    }

    public void setServerAffinityOrder(String string) throws SQLException {
        this.setStringRuntimeProperty("serverAffinityOrder", string);
    }

    public boolean getAutoReconnectForPools() throws SQLException {
        return this.getBooleanRuntimeProperty("autoReconnectForPools");
    }

    public void setAutoReconnectForPools(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("autoReconnectForPools", bl);
    }

    public boolean getUseCursorFetch() throws SQLException {
        return this.getBooleanRuntimeProperty("useCursorFetch");
    }

    public void setUseCursorFetch(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("useCursorFetch", bl);
    }

    public boolean getEmptyStringsConvertToZero() throws SQLException {
        return this.getBooleanRuntimeProperty("emptyStringsConvertToZero");
    }

    public void setEmptyStringsConvertToZero(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("emptyStringsConvertToZero", bl);
    }

    public long getSlowQueryThresholdNanos() throws SQLException {
        return this.getLongRuntimeProperty("slowQueryThresholdNanos");
    }

    public void setSlowQueryThresholdNanos(long l) throws SQLException {
        this.setLongRuntimeProperty("slowQueryThresholdNanos", l);
    }

    public boolean getUseNanosForElapsedTime() throws SQLException {
        return this.getBooleanRuntimeProperty("useNanosForElapsedTime");
    }

    public void setUseNanosForElapsedTime(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("useNanosForElapsedTime", bl);
    }

    public int getLoadBalanceHostRemovalGracePeriod() throws SQLException {
        return this.getIntegerRuntimeProperty("loadBalanceHostRemovalGracePeriod");
    }

    public void setLoadBalanceHostRemovalGracePeriod(int n) throws SQLException {
        this.setIntegerRuntimeProperty("loadBalanceHostRemovalGracePeriod", n);
    }

    public int getSlowQueryThresholdMillis() throws SQLException {
        return this.getIntegerRuntimeProperty("slowQueryThresholdMillis");
    }

    public void setSlowQueryThresholdMillis(int n) throws SQLException {
        this.setIntegerRuntimeProperty("slowQueryThresholdMillis", n);
    }

    public int getTcpTrafficClass() throws SQLException {
        return this.getIntegerRuntimeProperty("tcpTrafficClass");
    }

    public void setTcpTrafficClass(int n) throws SQLException {
        this.setIntegerRuntimeProperty("tcpTrafficClass", n);
    }

    public int getLoadBalancePingTimeout() throws SQLException {
        return this.getIntegerRuntimeProperty("loadBalancePingTimeout");
    }

    public void setLoadBalancePingTimeout(int n) throws SQLException {
        this.setIntegerRuntimeProperty("loadBalancePingTimeout", n);
    }

    public boolean getIncludeInnodbStatusInDeadlockExceptions() throws SQLException {
        return this.getBooleanRuntimeProperty("includeInnodbStatusInDeadlockExceptions");
    }

    public void setIncludeInnodbStatusInDeadlockExceptions(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("includeInnodbStatusInDeadlockExceptions", bl);
    }

    public boolean getUseCompression() throws SQLException {
        return this.getBooleanRuntimeProperty("useCompression");
    }

    public void setUseCompression(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("useCompression", bl);
    }

    public boolean getUseOnlyServerErrorMessages() throws SQLException {
        return this.getBooleanRuntimeProperty("useOnlyServerErrorMessages");
    }

    public void setUseOnlyServerErrorMessages(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("useOnlyServerErrorMessages", bl);
    }

    public String getEnabledSSLCipherSuites() throws SQLException {
        return this.getStringRuntimeProperty("enabledSSLCipherSuites");
    }

    public void setEnabledSSLCipherSuites(String string) throws SQLException {
        this.setStringRuntimeProperty("enabledSSLCipherSuites", string);
    }

    public boolean getUseServerPrepStmts() throws SQLException {
        return this.getBooleanRuntimeProperty("useServerPrepStmts");
    }

    public void setUseServerPrepStmts(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("useServerPrepStmts", bl);
    }

    public String getLoadBalanceSQLStateFailover() throws SQLException {
        return this.getStringRuntimeProperty("loadBalanceSQLStateFailover");
    }

    public void setLoadBalanceSQLStateFailover(String string) throws SQLException {
        this.setStringRuntimeProperty("loadBalanceSQLStateFailover", string);
    }

    public int getNetTimeoutForStreamingResults() throws SQLException {
        return this.getIntegerRuntimeProperty("netTimeoutForStreamingResults");
    }

    public void setNetTimeoutForStreamingResults(int n) throws SQLException {
        this.setIntegerRuntimeProperty("netTimeoutForStreamingResults", n);
    }

    public boolean getUseUsageAdvisor() throws SQLException {
        return this.getBooleanRuntimeProperty("useUsageAdvisor");
    }

    public void setUseUsageAdvisor(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("useUsageAdvisor", bl);
    }

    public String getPasswordCharacterEncoding() throws SQLException {
        return this.getStringRuntimeProperty("passwordCharacterEncoding");
    }

    public void setPasswordCharacterEncoding(String string) throws SQLException {
        this.setStringRuntimeProperty("passwordCharacterEncoding", string);
    }

    public int getRetriesAllDown() throws SQLException {
        return this.getIntegerRuntimeProperty("retriesAllDown");
    }

    public void setRetriesAllDown(int n) throws SQLException {
        this.setIntegerRuntimeProperty("retriesAllDown", n);
    }

    public boolean getUseReadAheadInput() throws SQLException {
        return this.getBooleanRuntimeProperty("useReadAheadInput");
    }

    public void setUseReadAheadInput(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("useReadAheadInput", bl);
    }

    public boolean getPinGlobalTxToPhysicalConnection() throws SQLException {
        return this.getBooleanRuntimeProperty("pinGlobalTxToPhysicalConnection");
    }

    public void setPinGlobalTxToPhysicalConnection(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("pinGlobalTxToPhysicalConnection", bl);
    }

    public boolean getAutoClosePStmtStreams() throws SQLException {
        return this.getBooleanRuntimeProperty("autoClosePStmtStreams");
    }

    public void setAutoClosePStmtStreams(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("autoClosePStmtStreams", bl);
    }

    public boolean getGatherPerfMetrics() throws SQLException {
        return this.getBooleanRuntimeProperty("gatherPerfMetrics");
    }

    public void setGatherPerfMetrics(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("gatherPerfMetrics", bl);
    }

    public boolean getEmulateLocators() throws SQLException {
        return this.getBooleanRuntimeProperty("emulateLocators");
    }

    public void setEmulateLocators(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("emulateLocators", bl);
    }

    public String getXdevapiSSLTruststorePassword() throws SQLException {
        return this.getStringRuntimeProperty("xdevapiSSLTruststorePassword");
    }

    public void setXdevapiSSLTruststorePassword(String string) throws SQLException {
        this.setStringRuntimeProperty("xdevapiSSLTruststorePassword", string);
    }

    public boolean getGetProceduresReturnsFunctions() throws SQLException {
        return this.getBooleanRuntimeProperty("getProceduresReturnsFunctions");
    }

    public void setGetProceduresReturnsFunctions(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("getProceduresReturnsFunctions", bl);
    }

    public boolean getMaintainTimeStats() throws SQLException {
        return this.getBooleanRuntimeProperty("maintainTimeStats");
    }

    public void setMaintainTimeStats(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("maintainTimeStats", bl);
    }

    public String getClientCertificateKeyStoreType() throws SQLException {
        return this.getStringRuntimeProperty("clientCertificateKeyStoreType");
    }

    public void setClientCertificateKeyStoreType(String string) throws SQLException {
        this.setStringRuntimeProperty("clientCertificateKeyStoreType", string);
    }

    public boolean getRewriteBatchedStatements() throws SQLException {
        return this.getBooleanRuntimeProperty("rewriteBatchedStatements");
    }

    public void setRewriteBatchedStatements(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("rewriteBatchedStatements", bl);
    }

    public boolean getUseOldAliasMetadataBehavior() throws SQLException {
        return this.getBooleanRuntimeProperty("useOldAliasMetadataBehavior");
    }

    public void setUseOldAliasMetadataBehavior(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("useOldAliasMetadataBehavior", bl);
    }

    public String getXdevapiSSLMode() throws SQLException {
        return this.getEnumRuntimeProperty("xdevapiSSLMode");
    }

    public void setXdevapiSSLMode(String string) throws SQLException {
        this.setEnumRuntimeProperty("xdevapiSSLMode", string);
    }

    public boolean getCreateDatabaseIfNotExist() throws SQLException {
        return this.getBooleanRuntimeProperty("createDatabaseIfNotExist");
    }

    public void setCreateDatabaseIfNotExist(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("createDatabaseIfNotExist", bl);
    }

    public int getQueriesBeforeRetryMaster() throws SQLException {
        return this.getIntegerRuntimeProperty("queriesBeforeRetryMaster");
    }

    public void setQueriesBeforeRetryMaster(int n) throws SQLException {
        this.setIntegerRuntimeProperty("queriesBeforeRetryMaster", n);
    }

    public String getLoadBalanceSQLExceptionSubclassFailover() throws SQLException {
        return this.getStringRuntimeProperty("loadBalanceSQLExceptionSubclassFailover");
    }

    public void setLoadBalanceSQLExceptionSubclassFailover(String string) throws SQLException {
        this.setStringRuntimeProperty("loadBalanceSQLExceptionSubclassFailover", string);
    }

    public int getTcpRcvBuf() throws SQLException {
        return this.getIntegerRuntimeProperty("tcpRcvBuf");
    }

    public void setTcpRcvBuf(int n) throws SQLException {
        this.setIntegerRuntimeProperty("tcpRcvBuf", n);
    }

    public boolean getDontCheckOnDuplicateKeyUpdateInSQL() throws SQLException {
        return this.getBooleanRuntimeProperty("dontCheckOnDuplicateKeyUpdateInSQL");
    }

    public void setDontCheckOnDuplicateKeyUpdateInSQL(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("dontCheckOnDuplicateKeyUpdateInSQL", bl);
    }

    public int getSocksProxyPort() throws SQLException {
        return this.getIntegerRuntimeProperty("socksProxyPort");
    }

    public void setSocksProxyPort(int n) throws SQLException {
        this.setIntegerRuntimeProperty("socksProxyPort", n);
    }

    public boolean getUltraDevHack() throws SQLException {
        return this.getBooleanRuntimeProperty("ultraDevHack");
    }

    public void setUltraDevHack(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("ultraDevHack", bl);
    }

    public boolean getAlwaysSendSetIsolation() throws SQLException {
        return this.getBooleanRuntimeProperty("alwaysSendSetIsolation");
    }

    public void setAlwaysSendSetIsolation(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("alwaysSendSetIsolation", bl);
    }

    public int getReportMetricsIntervalMillis() throws SQLException {
        return this.getIntegerRuntimeProperty("reportMetricsIntervalMillis");
    }

    public void setReportMetricsIntervalMillis(int n) throws SQLException {
        this.setIntegerRuntimeProperty("reportMetricsIntervalMillis", n);
    }

    public boolean getAllowUrlInLocalInfile() throws SQLException {
        return this.getBooleanRuntimeProperty("allowUrlInLocalInfile");
    }

    public void setAllowUrlInLocalInfile(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("allowUrlInLocalInfile", bl);
    }

    public int getMaxQuerySizeToLog() throws SQLException {
        return this.getIntegerRuntimeProperty("maxQuerySizeToLog");
    }

    public void setMaxQuerySizeToLog(int n) throws SQLException {
        this.setIntegerRuntimeProperty("maxQuerySizeToLog", n);
    }

    public boolean getCachePrepStmts() throws SQLException {
        return this.getBooleanRuntimeProperty("cachePrepStmts");
    }

    public void setCachePrepStmts(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("cachePrepStmts", bl);
    }

    public boolean getFunctionsNeverReturnBlobs() throws SQLException {
        return this.getBooleanRuntimeProperty("functionsNeverReturnBlobs");
    }

    public void setFunctionsNeverReturnBlobs(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("functionsNeverReturnBlobs", bl);
    }

    public boolean getEnableQueryTimeouts() throws SQLException {
        return this.getBooleanRuntimeProperty("enableQueryTimeouts");
    }

    public void setEnableQueryTimeouts(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("enableQueryTimeouts", bl);
    }

    public int getSelfDestructOnPingSecondsLifetime() throws SQLException {
        return this.getIntegerRuntimeProperty("selfDestructOnPingSecondsLifetime");
    }

    public void setSelfDestructOnPingSecondsLifetime(int n) throws SQLException {
        this.setIntegerRuntimeProperty("selfDestructOnPingSecondsLifetime", n);
    }

    public String getXdevapiSSLTruststore() throws SQLException {
        return this.getStringRuntimeProperty("xdevapiSSLTruststore");
    }

    public void setXdevapiSSLTruststore(String string) throws SQLException {
        this.setStringRuntimeProperty("xdevapiSSLTruststore", string);
    }

    public int getPrepStmtCacheSize() throws SQLException {
        return this.getIntegerRuntimeProperty("prepStmtCacheSize");
    }

    public void setPrepStmtCacheSize(int n) throws SQLException {
        this.setIntegerRuntimeProperty("prepStmtCacheSize", n);
    }

    public boolean getYearIsDateType() throws SQLException {
        return this.getBooleanRuntimeProperty("yearIsDateType");
    }

    public void setYearIsDateType(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("yearIsDateType", bl);
    }

    public boolean getCacheServerConfiguration() throws SQLException {
        return this.getBooleanRuntimeProperty("cacheServerConfiguration");
    }

    public void setCacheServerConfiguration(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("cacheServerConfiguration", bl);
    }

    public boolean getBlobsAreStrings() throws SQLException {
        return this.getBooleanRuntimeProperty("blobsAreStrings");
    }

    public void setBlobsAreStrings(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("blobsAreStrings", bl);
    }

    public boolean getEnablePacketDebug() throws SQLException {
        return this.getBooleanRuntimeProperty("enablePacketDebug");
    }

    public void setEnablePacketDebug(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("enablePacketDebug", bl);
    }

    public boolean getReconnectAtTxEnd() throws SQLException {
        return this.getBooleanRuntimeProperty("reconnectAtTxEnd");
    }

    public void setReconnectAtTxEnd(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("reconnectAtTxEnd", bl);
    }

    public String getTrustCertificateKeyStorePassword() throws SQLException {
        return this.getStringRuntimeProperty("trustCertificateKeyStorePassword");
    }

    public void setTrustCertificateKeyStorePassword(String string) throws SQLException {
        this.setStringRuntimeProperty("trustCertificateKeyStorePassword", string);
    }

    public String getAuthenticationPlugins() throws SQLException {
        return this.getStringRuntimeProperty("authenticationPlugins");
    }

    public void setAuthenticationPlugins(String string) throws SQLException {
        this.setStringRuntimeProperty("authenticationPlugins", string);
    }

    public String getPropertiesTransform() throws SQLException {
        return this.getStringRuntimeProperty("propertiesTransform");
    }

    public void setPropertiesTransform(String string) throws SQLException {
        this.setStringRuntimeProperty("propertiesTransform", string);
    }

    public boolean getClobberStreamingResults() throws SQLException {
        return this.getBooleanRuntimeProperty("clobberStreamingResults");
    }

    public void setClobberStreamingResults(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("clobberStreamingResults", bl);
    }

    public int getMetadataCacheSize() throws SQLException {
        return this.getIntegerRuntimeProperty("metadataCacheSize");
    }

    public void setMetadataCacheSize(int n) throws SQLException {
        this.setIntegerRuntimeProperty("metadataCacheSize", n);
    }

    public boolean getTraceProtocol() throws SQLException {
        return this.getBooleanRuntimeProperty("traceProtocol");
    }

    public void setTraceProtocol(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("traceProtocol", bl);
    }

    public boolean getNoDatetimeStringSync() throws SQLException {
        return this.getBooleanRuntimeProperty("noDatetimeStringSync");
    }

    public void setNoDatetimeStringSync(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("noDatetimeStringSync", bl);
    }

    public boolean getRequireSSL() throws SQLException {
        return this.getBooleanRuntimeProperty("requireSSL");
    }

    public void setRequireSSL(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("requireSSL", bl);
    }

    public String getXdevapiAuth() throws SQLException {
        return this.getEnumRuntimeProperty("xdevapiAuth");
    }

    public void setXdevapiAuth(String string) throws SQLException {
        this.setEnumRuntimeProperty("xdevapiAuth", string);
    }

    public int getConnectTimeout() throws SQLException {
        return this.getIntegerRuntimeProperty("connectTimeout");
    }

    public void setConnectTimeout(int n) throws SQLException {
        this.setIntegerRuntimeProperty("connectTimeout", n);
    }

    public int getSocketTimeout() throws SQLException {
        return this.getIntegerRuntimeProperty("socketTimeout");
    }

    public void setSocketTimeout(int n) throws SQLException {
        this.setIntegerRuntimeProperty("socketTimeout", n);
    }

    public String getZeroDateTimeBehavior() throws SQLException {
        return this.getEnumRuntimeProperty("zeroDateTimeBehavior");
    }

    public void setZeroDateTimeBehavior(String string) throws SQLException {
        this.setEnumRuntimeProperty("zeroDateTimeBehavior", string);
    }

    public String getLocalSocketAddress() throws SQLException {
        return this.getStringRuntimeProperty("localSocketAddress");
    }

    public void setLocalSocketAddress(String string) throws SQLException {
        this.setStringRuntimeProperty("localSocketAddress", string);
    }

    public boolean getLogXaCommands() throws SQLException {
        return this.getBooleanRuntimeProperty("logXaCommands");
    }

    public void setLogXaCommands(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("logXaCommands", bl);
    }

    public int getResultSetSizeThreshold() throws SQLException {
        return this.getIntegerRuntimeProperty("resultSetSizeThreshold");
    }

    public void setResultSetSizeThreshold(int n) throws SQLException {
        this.setIntegerRuntimeProperty("resultSetSizeThreshold", n);
    }

    public boolean getInteractiveClient() throws SQLException {
        return this.getBooleanRuntimeProperty("interactiveClient");
    }

    public void setInteractiveClient(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("interactiveClient", bl);
    }

    public boolean getTcpNoDelay() throws SQLException {
        return this.getBooleanRuntimeProperty("tcpNoDelay");
    }

    public void setTcpNoDelay(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("tcpNoDelay", bl);
    }

    public boolean getHoldResultsOpenOverStatementClose() throws SQLException {
        return this.getBooleanRuntimeProperty("holdResultsOpenOverStatementClose");
    }

    public void setHoldResultsOpenOverStatementClose(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("holdResultsOpenOverStatementClose", bl);
    }

    public boolean getDetectCustomCollations() throws SQLException {
        return this.getBooleanRuntimeProperty("detectCustomCollations");
    }

    public void setDetectCustomCollations(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("detectCustomCollations", bl);
    }

    public int getPacketDebugBufferSize() throws SQLException {
        return this.getIntegerRuntimeProperty("packetDebugBufferSize");
    }

    public void setPacketDebugBufferSize(int n) throws SQLException {
        this.setIntegerRuntimeProperty("packetDebugBufferSize", n);
    }

    public boolean getCompensateOnDuplicateKeyUpdateCounts() throws SQLException {
        return this.getBooleanRuntimeProperty("compensateOnDuplicateKeyUpdateCounts");
    }

    public void setCompensateOnDuplicateKeyUpdateCounts(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("compensateOnDuplicateKeyUpdateCounts", bl);
    }

    public boolean getIncludeThreadNamesAsStatementComment() throws SQLException {
        return this.getBooleanRuntimeProperty("includeThreadNamesAsStatementComment");
    }

    public void setIncludeThreadNamesAsStatementComment(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("includeThreadNamesAsStatementComment", bl);
    }

    public boolean getNoAccessToProcedureBodies() throws SQLException {
        return this.getBooleanRuntimeProperty("noAccessToProcedureBodies");
    }

    public void setNoAccessToProcedureBodies(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("noAccessToProcedureBodies", bl);
    }

    public boolean getUseHostsInPrivileges() throws SQLException {
        return this.getBooleanRuntimeProperty("useHostsInPrivileges");
    }

    public void setUseHostsInPrivileges(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("useHostsInPrivileges", bl);
    }

    public int getDefaultFetchSize() throws SQLException {
        return this.getIntegerRuntimeProperty("defaultFetchSize");
    }

    public void setDefaultFetchSize(int n) throws SQLException {
        this.setIntegerRuntimeProperty("defaultFetchSize", n);
    }

    public boolean getAutoReconnect() throws SQLException {
        return this.getBooleanRuntimeProperty("autoReconnect");
    }

    public void setAutoReconnect(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("autoReconnect", bl);
    }

    public boolean getDisconnectOnExpiredPasswords() throws SQLException {
        return this.getBooleanRuntimeProperty("disconnectOnExpiredPasswords");
    }

    public void setDisconnectOnExpiredPasswords(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("disconnectOnExpiredPasswords", bl);
    }

    public boolean getContinueBatchOnError() throws SQLException {
        return this.getBooleanRuntimeProperty("continueBatchOnError");
    }

    public void setContinueBatchOnError(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("continueBatchOnError", bl);
    }

    public boolean getParanoid() throws SQLException {
        return this.getBooleanRuntimeProperty("paranoid");
    }

    public void setParanoid(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("paranoid", bl);
    }

    public boolean getUseInformationSchema() throws SQLException {
        return this.getBooleanRuntimeProperty("useInformationSchema");
    }

    public void setUseInformationSchema(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("useInformationSchema", bl);
    }

    public boolean getRollbackOnPooledClose() throws SQLException {
        return this.getBooleanRuntimeProperty("rollbackOnPooledClose");
    }

    public void setRollbackOnPooledClose(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("rollbackOnPooledClose", bl);
    }

    public boolean getReadOnlyPropagatesToServer() throws SQLException {
        return this.getBooleanRuntimeProperty("readOnlyPropagatesToServer");
    }

    public void setReadOnlyPropagatesToServer(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("readOnlyPropagatesToServer", bl);
    }

    public int getBlobSendChunkSize() throws SQLException {
        return this.getIntegerRuntimeProperty("blobSendChunkSize");
    }

    public void setBlobSendChunkSize(int n) throws SQLException {
        this.setIntegerRuntimeProperty("blobSendChunkSize", n);
    }

    public String getTrustCertificateKeyStoreType() throws SQLException {
        return this.getStringRuntimeProperty("trustCertificateKeyStoreType");
    }

    public void setTrustCertificateKeyStoreType(String string) throws SQLException {
        this.setStringRuntimeProperty("trustCertificateKeyStoreType", string);
    }

    public int getXdevapiAsyncResponseTimeout() throws SQLException {
        return this.getIntegerRuntimeProperty("xdevapiAsyncResponseTimeout");
    }

    public void setXdevapiAsyncResponseTimeout(int n) throws SQLException {
        this.setIntegerRuntimeProperty("xdevapiAsyncResponseTimeout", n);
    }

    public String getCharacterEncoding() throws SQLException {
        return this.getStringRuntimeProperty("characterEncoding");
    }

    public void setCharacterEncoding(String string) throws SQLException {
        this.setStringRuntimeProperty("characterEncoding", string);
    }

    public boolean getVerifyServerCertificate() throws SQLException {
        return this.getBooleanRuntimeProperty("verifyServerCertificate");
    }

    public void setVerifyServerCertificate(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("verifyServerCertificate", bl);
    }

    public boolean getAutoGenerateTestcaseScript() throws SQLException {
        return this.getBooleanRuntimeProperty("autoGenerateTestcaseScript");
    }

    public void setAutoGenerateTestcaseScript(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("autoGenerateTestcaseScript", bl);
    }

    public String getConnectionAttributes() throws SQLException {
        return this.getStringRuntimeProperty("connectionAttributes");
    }

    public void setConnectionAttributes(String string) throws SQLException {
        this.setStringRuntimeProperty("connectionAttributes", string);
    }

    public boolean getExplainSlowQueries() throws SQLException {
        return this.getBooleanRuntimeProperty("explainSlowQueries");
    }

    public void setExplainSlowQueries(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("explainSlowQueries", bl);
    }

    public String getParseInfoCacheFactory() throws SQLException {
        return this.getStringRuntimeProperty("parseInfoCacheFactory");
    }

    public void setParseInfoCacheFactory(String string) throws SQLException {
        this.setStringRuntimeProperty("parseInfoCacheFactory", string);
    }

    public boolean getEnableEscapeProcessing() throws SQLException {
        return this.getBooleanRuntimeProperty("enableEscapeProcessing");
    }

    public void setEnableEscapeProcessing(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("enableEscapeProcessing", bl);
    }

    public boolean getFailOverReadOnly() throws SQLException {
        return this.getBooleanRuntimeProperty("failOverReadOnly");
    }

    public void setFailOverReadOnly(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("failOverReadOnly", bl);
    }

    public int getLocatorFetchBufferSize() throws SQLException {
        return this.getIntegerRuntimeProperty("locatorFetchBufferSize");
    }

    public void setLocatorFetchBufferSize(int n) throws SQLException {
        this.setIntegerRuntimeProperty("locatorFetchBufferSize", n);
    }

    public boolean getNullCatalogMeansCurrent() throws SQLException {
        return this.getBooleanRuntimeProperty("nullCatalogMeansCurrent");
    }

    public void setNullCatalogMeansCurrent(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("nullCatalogMeansCurrent", bl);
    }

    public boolean getTcpKeepAlive() throws SQLException {
        return this.getBooleanRuntimeProperty("tcpKeepAlive");
    }

    public void setTcpKeepAlive(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("tcpKeepAlive", bl);
    }

    public boolean getUseUnbufferedInput() throws SQLException {
        return this.getBooleanRuntimeProperty("useUnbufferedInput");
    }

    public void setUseUnbufferedInput(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("useUnbufferedInput", bl);
    }

    public boolean getAllowMultiQueries() throws SQLException {
        return this.getBooleanRuntimeProperty("allowMultiQueries");
    }

    public void setAllowMultiQueries(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("allowMultiQueries", bl);
    }

    public int getInitialTimeout() throws SQLException {
        return this.getIntegerRuntimeProperty("initialTimeout");
    }

    public void setInitialTimeout(int n) throws SQLException {
        this.setIntegerRuntimeProperty("initialTimeout", n);
    }

    public String getProfilerEventHandler() throws SQLException {
        return this.getStringRuntimeProperty("profilerEventHandler");
    }

    public void setProfilerEventHandler(String string) throws SQLException {
        this.setStringRuntimeProperty("profilerEventHandler", string);
    }

    public boolean getStrictUpdates() throws SQLException {
        return this.getBooleanRuntimeProperty("strictUpdates");
    }

    public void setStrictUpdates(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("strictUpdates", bl);
    }

    public boolean getTreatUtilDateAsTimestamp() throws SQLException {
        return this.getBooleanRuntimeProperty("treatUtilDateAsTimestamp");
    }

    public void setTreatUtilDateAsTimestamp(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("treatUtilDateAsTimestamp", bl);
    }

    public String getServerConfigCacheFactory() throws SQLException {
        return this.getStringRuntimeProperty("serverConfigCacheFactory");
    }

    public void setServerConfigCacheFactory(String string) throws SQLException {
        this.setStringRuntimeProperty("serverConfigCacheFactory", string);
    }

    public String getClientCertificateKeyStoreUrl() throws SQLException {
        return this.getStringRuntimeProperty("clientCertificateKeyStoreUrl");
    }

    public void setClientCertificateKeyStoreUrl(String string) throws SQLException {
        this.setStringRuntimeProperty("clientCertificateKeyStoreUrl", string);
    }

    public int getPrepStmtCacheSqlLimit() throws SQLException {
        return this.getIntegerRuntimeProperty("prepStmtCacheSqlLimit");
    }

    public void setPrepStmtCacheSqlLimit(int n) throws SQLException {
        this.setIntegerRuntimeProperty("prepStmtCacheSqlLimit", n);
    }

    public String getClientInfoProvider() throws SQLException {
        return this.getStringRuntimeProperty("clientInfoProvider");
    }

    public void setClientInfoProvider(String string) throws SQLException {
        this.setStringRuntimeProperty("clientInfoProvider", string);
    }

    public String getConnectionCollation() throws SQLException {
        return this.getStringRuntimeProperty("connectionCollation");
    }

    public void setConnectionCollation(String string) throws SQLException {
        this.setStringRuntimeProperty("connectionCollation", string);
    }

    public String getLoadBalanceExceptionChecker() throws SQLException {
        return this.getStringRuntimeProperty("loadBalanceExceptionChecker");
    }

    public void setLoadBalanceExceptionChecker(String string) throws SQLException {
        this.setStringRuntimeProperty("loadBalanceExceptionChecker", string);
    }

    public String getReplicationConnectionGroup() throws SQLException {
        return this.getStringRuntimeProperty("replicationConnectionGroup");
    }

    public void setReplicationConnectionGroup(String string) throws SQLException {
        this.setStringRuntimeProperty("replicationConnectionGroup", string);
    }

    public boolean getGenerateSimpleParameterMetadata() throws SQLException {
        return this.getBooleanRuntimeProperty("generateSimpleParameterMetadata");
    }

    public void setGenerateSimpleParameterMetadata(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("generateSimpleParameterMetadata", bl);
    }

    public boolean getProcessEscapeCodesForPrepStmts() throws SQLException {
        return this.getBooleanRuntimeProperty("processEscapeCodesForPrepStmts");
    }

    public void setProcessEscapeCodesForPrepStmts(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("processEscapeCodesForPrepStmts", bl);
    }

    public boolean getLogSlowQueries() throws SQLException {
        return this.getBooleanRuntimeProperty("logSlowQueries");
    }

    public void setLogSlowQueries(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("logSlowQueries", bl);
    }

    public boolean getUseOldUTF8Behavior() throws SQLException {
        return this.getBooleanRuntimeProperty("useOldUTF8Behavior");
    }

    public void setUseOldUTF8Behavior(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("useOldUTF8Behavior", bl);
    }

    public int getCallableStmtCacheSize() throws SQLException {
        return this.getIntegerRuntimeProperty("callableStmtCacheSize");
    }

    public void setCallableStmtCacheSize(int n) throws SQLException {
        this.setIntegerRuntimeProperty("callableStmtCacheSize", n);
    }

    public String getUseConfigs() throws SQLException {
        return this.getStringRuntimeProperty("useConfigs");
    }

    public void setUseConfigs(String string) throws SQLException {
        this.setStringRuntimeProperty("useConfigs", string);
    }

    public boolean getIncludeThreadDumpInDeadlockExceptions() throws SQLException {
        return this.getBooleanRuntimeProperty("includeThreadDumpInDeadlockExceptions");
    }

    public void setIncludeThreadDumpInDeadlockExceptions(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("includeThreadDumpInDeadlockExceptions", bl);
    }

    public boolean getUseStreamLengthsInPrepStmts() throws SQLException {
        return this.getBooleanRuntimeProperty("useStreamLengthsInPrepStmts");
    }

    public void setUseStreamLengthsInPrepStmts(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("useStreamLengthsInPrepStmts", bl);
    }

    public String getDisabledAuthenticationPlugins() throws SQLException {
        return this.getStringRuntimeProperty("disabledAuthenticationPlugins");
    }

    public void setDisabledAuthenticationPlugins(String string) throws SQLException {
        this.setStringRuntimeProperty("disabledAuthenticationPlugins", string);
    }

    public boolean getTinyInt1isBit() throws SQLException {
        return this.getBooleanRuntimeProperty("tinyInt1isBit");
    }

    public void setTinyInt1isBit(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("tinyInt1isBit", bl);
    }

    public String getLoadBalanceConnectionGroup() throws SQLException {
        return this.getStringRuntimeProperty("loadBalanceConnectionGroup");
    }

    public void setLoadBalanceConnectionGroup(String string) throws SQLException {
        this.setStringRuntimeProperty("loadBalanceConnectionGroup", string);
    }

    public boolean getReadFromMasterWhenNoSlaves() throws SQLException {
        return this.getBooleanRuntimeProperty("readFromMasterWhenNoSlaves");
    }

    public void setReadFromMasterWhenNoSlaves(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("readFromMasterWhenNoSlaves", bl);
    }

    public String getClientCertificateKeyStorePassword() throws SQLException {
        return this.getStringRuntimeProperty("clientCertificateKeyStorePassword");
    }

    public void setClientCertificateKeyStorePassword(String string) throws SQLException {
        this.setStringRuntimeProperty("clientCertificateKeyStorePassword", string);
    }

    public boolean getSendFractionalSeconds() throws SQLException {
        return this.getBooleanRuntimeProperty("sendFractionalSeconds");
    }

    public void setSendFractionalSeconds(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("sendFractionalSeconds", bl);
    }

    public boolean getAllowNanAndInf() throws SQLException {
        return this.getBooleanRuntimeProperty("allowNanAndInf");
    }

    public void setAllowNanAndInf(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("allowNanAndInf", bl);
    }

    public String getQueryInterceptors() throws SQLException {
        return this.getStringRuntimeProperty("queryInterceptors");
    }

    public void setQueryInterceptors(String string) throws SQLException {
        this.setStringRuntimeProperty("queryInterceptors", string);
    }

    public boolean getAutoSlowLog() throws SQLException {
        return this.getBooleanRuntimeProperty("autoSlowLog");
    }

    public void setAutoSlowLog(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("autoSlowLog", bl);
    }

    public String getXdevapiSSLTruststoreType() throws SQLException {
        return this.getStringRuntimeProperty("xdevapiSSLTruststoreType");
    }

    public void setXdevapiSSLTruststoreType(String string) throws SQLException {
        this.setStringRuntimeProperty("xdevapiSSLTruststoreType", string);
    }

    public int getSecondsBeforeRetryMaster() throws SQLException {
        return this.getIntegerRuntimeProperty("secondsBeforeRetryMaster");
    }

    public void setSecondsBeforeRetryMaster(int n) throws SQLException {
        this.setIntegerRuntimeProperty("secondsBeforeRetryMaster", n);
    }

    public boolean getUseLocalSessionState() throws SQLException {
        return this.getBooleanRuntimeProperty("useLocalSessionState");
    }

    public void setUseLocalSessionState(boolean bl) throws SQLException {
        this.setBooleanRuntimeProperty("useLocalSessionState", bl);
    }

    public String getConnectionLifecycleInterceptors() throws SQLException {
        return this.getStringRuntimeProperty("connectionLifecycleInterceptors");
    }

    public void setConnectionLifecycleInterceptors(String string) throws SQLException {
        this.setStringRuntimeProperty("connectionLifecycleInterceptors", string);
    }
}

