/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.CacheAdapter;
import com.mysql.cj.CacheAdapterFactory;
import com.mysql.cj.Constants;
import com.mysql.cj.LicenseConfiguration;
import com.mysql.cj.Messages;
import com.mysql.cj.NativeSession;
import com.mysql.cj.NoSubInterceptorWrapper;
import com.mysql.cj.ParseInfo;
import com.mysql.cj.PreparedQuery;
import com.mysql.cj.ServerVersion;
import com.mysql.cj.Session;
import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.exceptions.ExceptionInterceptorChain;
import com.mysql.cj.exceptions.PasswordExpiredException;
import com.mysql.cj.exceptions.UnableToConnectException;
import com.mysql.cj.interceptors.QueryInterceptor;
import com.mysql.cj.jdbc.Blob;
import com.mysql.cj.jdbc.CallableStatement;
import com.mysql.cj.jdbc.ClientInfoProvider;
import com.mysql.cj.jdbc.ClientPreparedStatement;
import com.mysql.cj.jdbc.Clob;
import com.mysql.cj.jdbc.DatabaseMetaData;
import com.mysql.cj.jdbc.EscapeProcessor;
import com.mysql.cj.jdbc.EscapeProcessorResult;
import com.mysql.cj.jdbc.IterateBlock;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.JdbcPreparedStatement;
import com.mysql.cj.jdbc.JdbcPropertySet;
import com.mysql.cj.jdbc.JdbcPropertySetImpl;
import com.mysql.cj.jdbc.JdbcStatement;
import com.mysql.cj.jdbc.MysqlSQLXML;
import com.mysql.cj.jdbc.MysqlSavepoint;
import com.mysql.cj.jdbc.NClob;
import com.mysql.cj.jdbc.NonRegisteringDriver;
import com.mysql.cj.jdbc.ServerPreparedStatement;
import com.mysql.cj.jdbc.StatementImpl;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import com.mysql.cj.jdbc.ha.MultiHostMySQLConnection;
import com.mysql.cj.jdbc.interceptors.ConnectionLifecycleInterceptor;
import com.mysql.cj.jdbc.result.CachedResultSetMetaData;
import com.mysql.cj.jdbc.result.CachedResultSetMetaDataImpl;
import com.mysql.cj.jdbc.result.ResultSetFactory;
import com.mysql.cj.jdbc.result.ResultSetInternalMethods;
import com.mysql.cj.jdbc.result.UpdatableResultSet;
import com.mysql.cj.log.ProfilerEventHandlerFactory;
import com.mysql.cj.log.ProfilerEventImpl;
import com.mysql.cj.log.StandardLogger;
import com.mysql.cj.protocol.SocksProxySocketFactory;
import com.mysql.cj.util.LRUCache;
import com.mysql.cj.util.LogUtils;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.util.Util;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.sql.Array;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLPermission;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

public class ConnectionImpl
implements JdbcConnection,
Session.SessionEventListener,
Serializable {
    private static final long serialVersionUID = 4009476458425101761L;
    private static final SQLPermission SET_NETWORK_TIMEOUT_PERM = new SQLPermission("setNetworkTimeout");
    private static final SQLPermission ABORT_PERM = new SQLPermission("abort");
    private JdbcConnection proxy = null;
    private InvocationHandler realProxy = null;
    public static Map<?, ?> charsetMap;
    protected static final String DEFAULT_LOGGER_CLASS;
    private static Map<String, Integer> mapTransIsolationNameToValue;
    protected static Map<?, ?> roundRobinStatsMap;
    private List<ConnectionLifecycleInterceptor> connectionLifecycleInterceptors;
    private static final int DEFAULT_RESULT_SET_TYPE = 1003;
    private static final int DEFAULT_RESULT_SET_CONCURRENCY = 1007;
    private static final Random random;
    private CacheAdapter<String, ParseInfo> cachedPreparedStatementParams;
    private String database = null;
    private java.sql.DatabaseMetaData dbmd = null;
    private NativeSession session = null;
    private boolean isInGlobalTx = false;
    private int isolationLevel = 2;
    private final CopyOnWriteArrayList<JdbcStatement> openStatements = new CopyOnWriteArrayList();
    private LRUCache<CompoundCacheKey, CallableStatement.CallableStatementParamInfo> parsedCallableStatementCache;
    private String password = null;
    private String pointOfOrigin;
    protected Properties props = null;
    private boolean readOnly = false;
    protected LRUCache<String, CachedResultSetMetaData> resultSetMetadataCache;
    private Map<String, Class<?>> typeMap;
    private String user = null;
    private LRUCache<String, Boolean> serverSideStatementCheckCache;
    private LRUCache<CompoundCacheKey, ServerPreparedStatement> serverSideStatementCache;
    private HostInfo origHostInfo;
    private String origHostToConnectTo;
    private int origPortToConnectTo;
    private boolean hasTriedMasterFlag = false;
    private List<QueryInterceptor> queryInterceptors;
    protected JdbcPropertySet propertySet;
    private RuntimeProperty<Boolean> autoReconnectForPools;
    private RuntimeProperty<Boolean> cachePrepStmts;
    private RuntimeProperty<Boolean> autoReconnect;
    private RuntimeProperty<Boolean> useUsageAdvisor;
    private RuntimeProperty<Boolean> reconnectAtTxEnd;
    private RuntimeProperty<Boolean> emulateUnsupportedPstmts;
    private RuntimeProperty<Boolean> ignoreNonTxTables;
    private RuntimeProperty<Boolean> pedantic;
    private RuntimeProperty<Integer> prepStmtCacheSqlLimit;
    private RuntimeProperty<Boolean> useLocalSessionState;
    private RuntimeProperty<Boolean> useServerPrepStmts;
    private RuntimeProperty<Boolean> processEscapeCodesForPrepStmts;
    private RuntimeProperty<Boolean> useLocalTransactionState;
    private RuntimeProperty<Boolean> disconnectOnExpiredPasswords;
    private RuntimeProperty<Boolean> readOnlyPropagatesToServer;
    protected ResultSetFactory nullStatementResultSetFactory;
    private int autoIncrementIncrement = 0;
    private ExceptionInterceptor exceptionInterceptor;
    private ClientInfoProvider infoProvider;

    @Override
    public String getHost() {
        return this.session.getHostInfo().getHost();
    }

    @Override
    public boolean isProxySet() {
        return this.proxy != null;
    }

    @Override
    public void setProxy(JdbcConnection proxy) {
        this.proxy = proxy;
        this.realProxy = this.proxy instanceof MultiHostMySQLConnection ? ((MultiHostMySQLConnection)proxy).getThisAsProxy() : null;
    }

    private JdbcConnection getProxy() {
        return this.proxy != null ? this.proxy : this;
    }

    @Override
    public JdbcConnection getMultiHostSafeProxy() {
        return this.getProxy();
    }

    @Override
    public JdbcConnection getActiveMySQLConnection() {
        return this;
    }

    @Override
    public Object getConnectionMutex() {
        return this.realProxy != null ? this.realProxy : this.getProxy();
    }

    public static JdbcConnection getInstance(HostInfo hostInfo) throws SQLException {
        return new ConnectionImpl(hostInfo);
    }

    protected static synchronized int getNextRoundRobinHostIndex(String url, List<?> hostList) {
        int indexRange = hostList.size();
        int index = random.nextInt(indexRange);
        return index;
    }

    private static boolean nullSafeCompare(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1 == null && s2 != null) {
            return false;
        }
        return s1 != null && s1.equals(s2);
    }

    protected ConnectionImpl() {
    }

    public ConnectionImpl(HostInfo hostInfo) throws SQLException {
        try {
            this.origHostInfo = hostInfo;
            this.origHostToConnectTo = hostInfo.getHost();
            this.origPortToConnectTo = hostInfo.getPort();
            this.database = hostInfo.getDatabase();
            this.user = StringUtils.isNullOrEmpty(hostInfo.getUser()) ? "" : hostInfo.getUser();
            this.password = StringUtils.isNullOrEmpty(hostInfo.getPassword()) ? "" : hostInfo.getPassword();
            this.props = hostInfo.exposeAsProperties();
            this.propertySet = new JdbcPropertySetImpl();
            this.propertySet.initializeProperties(this.props);
            this.nullStatementResultSetFactory = new ResultSetFactory(this, null);
            this.session = new NativeSession(hostInfo, this.propertySet);
            this.session.addListener(this);
            this.autoReconnectForPools = this.propertySet.getBooleanProperty("autoReconnectForPools");
            this.cachePrepStmts = this.propertySet.getBooleanProperty("cachePrepStmts");
            this.autoReconnect = this.propertySet.getBooleanProperty("autoReconnect");
            this.useUsageAdvisor = this.propertySet.getBooleanProperty("useUsageAdvisor");
            this.reconnectAtTxEnd = this.propertySet.getBooleanProperty("reconnectAtTxEnd");
            this.emulateUnsupportedPstmts = this.propertySet.getBooleanProperty("emulateUnsupportedPstmts");
            this.ignoreNonTxTables = this.propertySet.getBooleanProperty("ignoreNonTxTables");
            this.pedantic = this.propertySet.getBooleanProperty("pedantic");
            this.prepStmtCacheSqlLimit = this.propertySet.getIntegerProperty("prepStmtCacheSqlLimit");
            this.useLocalSessionState = this.propertySet.getBooleanProperty("useLocalSessionState");
            this.useServerPrepStmts = this.propertySet.getBooleanProperty("useServerPrepStmts");
            this.processEscapeCodesForPrepStmts = this.propertySet.getBooleanProperty("processEscapeCodesForPrepStmts");
            this.useLocalTransactionState = this.propertySet.getBooleanProperty("useLocalTransactionState");
            this.disconnectOnExpiredPasswords = this.propertySet.getBooleanProperty("disconnectOnExpiredPasswords");
            this.readOnlyPropagatesToServer = this.propertySet.getBooleanProperty("readOnlyPropagatesToServer");
            String exceptionInterceptorClasses = this.propertySet.getStringProperty("exceptionInterceptors").getStringValue();
            if (exceptionInterceptorClasses != null && !"".equals(exceptionInterceptorClasses)) {
                this.exceptionInterceptor = new ExceptionInterceptorChain(exceptionInterceptorClasses, this.props, this.session.getLog());
            }
            if (this.cachePrepStmts.getValue().booleanValue()) {
                this.createPreparedStatementCaches();
            }
            if (this.propertySet.getBooleanProperty("cacheCallableStmts").getValue().booleanValue()) {
                this.parsedCallableStatementCache = new LRUCache(this.propertySet.getIntegerProperty("callableStmtCacheSize").getValue());
            }
            if (this.propertySet.getBooleanProperty("allowMultiQueries").getValue().booleanValue()) {
                this.propertySet.getProperty("cacheResultSetMetadata").setValue(false);
            }
            if (this.propertySet.getBooleanProperty("cacheResultSetMetadata").getValue().booleanValue()) {
                this.resultSetMetadataCache = new LRUCache(this.propertySet.getIntegerProperty("metadataCacheSize").getValue());
            }
            if (this.propertySet.getStringProperty("socksProxyHost").getStringValue() != null) {
                this.propertySet.getProperty("socketFactory").setValue(SocksProxySocketFactory.class.getName());
            }
            this.pointOfOrigin = this.useUsageAdvisor.getValue() != false ? LogUtils.findCallingClassAndMethod(new Throwable()) : "";
            this.dbmd = this.getMetaData(false, false);
            this.initializeSafeQueryInterceptors();
        } catch (CJException e1) {
            throw SQLExceptionsMapping.translateException(e1, this.getExceptionInterceptor());
        }
        try {
            this.createNewIO(false);
            this.unSafeQueryInterceptors();
            NonRegisteringDriver.trackConnection(this);
        } catch (SQLException ex) {
            this.cleanup(ex);
            throw ex;
        } catch (Exception ex) {
            this.cleanup(ex);
            throw SQLError.createSQLException(this.propertySet.getBooleanProperty("paranoid").getValue() != false ? Messages.getString("Connection.0") : Messages.getString("Connection.1", new Object[]{this.session.getHostInfo().getHost(), this.session.getHostInfo().getPort()}), "08S01", ex, this.getExceptionInterceptor());
        }
    }

    @Override
    public JdbcPropertySet getPropertySet() {
        return this.propertySet;
    }

    @Override
    public void unSafeQueryInterceptors() throws SQLException {
        try {
            this.queryInterceptors = this.queryInterceptors.stream().map(u -> ((NoSubInterceptorWrapper)u).getUnderlyingInterceptor()).collect(Collectors.toList());
            if (this.session != null) {
                this.session.setQueryInterceptors(this.queryInterceptors);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void initializeSafeQueryInterceptors() throws SQLException {
        try {
            this.queryInterceptors = Util.loadClasses(this.propertySet.getStringProperty("queryInterceptors").getStringValue(), "MysqlIo.BadQueryInterceptor", this.getExceptionInterceptor()).stream().map(o -> new NoSubInterceptorWrapper(o.init(this, this.props, this.session.getLog()))).collect(Collectors.toList());
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public List<QueryInterceptor> getQueryInterceptorsInstances() {
        return this.queryInterceptors;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean canHandleAsServerPreparedStatement(String sql) throws SQLException {
        if (sql == null || sql.length() == 0) {
            return true;
        }
        if (!this.useServerPrepStmts.getValue().booleanValue()) {
            return false;
        }
        if (this.cachePrepStmts.getValue().booleanValue()) {
            LRUCache<String, Boolean> lRUCache = this.serverSideStatementCheckCache;
            synchronized (lRUCache) {
                Boolean flag = (Boolean)this.serverSideStatementCheckCache.get(sql);
                if (flag != null) {
                    return flag;
                }
                boolean canHandle = StringUtils.canHandleAsServerPreparedStatementNoCache(sql, this.getServerVersion());
                if (sql.length() < this.prepStmtCacheSqlLimit.getValue()) {
                    this.serverSideStatementCheckCache.put(sql, canHandle ? Boolean.TRUE : Boolean.FALSE);
                }
                return canHandle;
            }
        }
        return StringUtils.canHandleAsServerPreparedStatementNoCache(sql, this.getServerVersion());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void changeUser(String userName, String newPassword) throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                this.checkClosed();
                if (userName == null || userName.equals("")) {
                    userName = "";
                }
                if (newPassword == null) {
                    newPassword = "";
                }
                try {
                    this.session.changeUser(userName, newPassword, this.database);
                } catch (CJException ex) {
                    if ("28000".equals(ex.getSQLState())) {
                        this.cleanup(ex);
                    }
                    throw ex;
                }
                this.user = userName;
                this.password = newPassword;
                this.session.configureClientCharacterSet(true);
                this.session.setSessionVariables();
                this.setupServerForTruncationChecks();
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void checkClosed() {
        this.session.checkClosed();
    }

    @Override
    public void throwConnectionClosedException() throws SQLException {
        try {
            SQLException ex = SQLError.createSQLException(Messages.getString("Connection.2"), "08003", this.getExceptionInterceptor());
            if (this.session.getForceClosedReason() != null) {
                ex.initCause(this.session.getForceClosedReason());
            }
            throw ex;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    private void checkTransactionIsolationLevel() {
        Integer intTI;
        String s2 = this.session.getServerSession().getServerVariable("transaction_isolation");
        if (s2 == null) {
            s2 = this.session.getServerSession().getServerVariable("tx_isolation");
        }
        if (s2 != null && (intTI = mapTransIsolationNameToValue.get(s2)) != null) {
            this.isolationLevel = intTI;
        }
    }

    @Override
    public void abortInternal() throws SQLException {
        try {
            this.session.forceClose();
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void cleanup(Throwable whyCleanedUp) {
        try {
            if (this.session != null) {
                if (this.isClosed()) {
                    this.session.forceClose();
                } else {
                    this.realClose(false, false, false, whyCleanedUp);
                }
            }
        } catch (CJException | SQLException exception) {
            // empty catch block
        }
    }

    @Override
    @Deprecated
    public void clearHasTriedMaster() {
        this.hasTriedMasterFlag = false;
    }

    @Override
    public void clearWarnings() throws SQLException {
        try {
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement clientPrepareStatement(String sql) throws SQLException {
        try {
            return this.clientPrepareStatement(sql, 1003, 1007);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement clientPrepareStatement(String sql, int autoGenKeyIndex) throws SQLException {
        try {
            PreparedStatement pStmt = this.clientPrepareStatement(sql);
            ((ClientPreparedStatement)pStmt).setRetrieveGeneratedKeys(autoGenKeyIndex == 1);
            return pStmt;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement clientPrepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        try {
            return this.clientPrepareStatement(sql, resultSetType, resultSetConcurrency, true);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    public PreparedStatement clientPrepareStatement(String sql, int resultSetType, int resultSetConcurrency, boolean processEscapeCodesIfNeeded) throws SQLException {
        try {
            this.checkClosed();
            String nativeSql = processEscapeCodesIfNeeded && this.processEscapeCodesForPrepStmts.getValue() != false ? this.nativeSQL(sql) : sql;
            ClientPreparedStatement pStmt = null;
            if (this.cachePrepStmts.getValue().booleanValue()) {
                ParseInfo pStmtInfo = this.cachedPreparedStatementParams.get(nativeSql);
                if (pStmtInfo == null) {
                    pStmt = ClientPreparedStatement.getInstance(this.getMultiHostSafeProxy(), nativeSql, this.database);
                    this.cachedPreparedStatementParams.put(nativeSql, pStmt.getParseInfo());
                } else {
                    pStmt = ClientPreparedStatement.getInstance(this.getMultiHostSafeProxy(), nativeSql, this.database, pStmtInfo);
                }
            } else {
                pStmt = ClientPreparedStatement.getInstance(this.getMultiHostSafeProxy(), nativeSql, this.database);
            }
            pStmt.setResultSetType(resultSetType);
            pStmt.setResultSetConcurrency(resultSetConcurrency);
            return pStmt;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement clientPrepareStatement(String sql, int[] autoGenKeyIndexes) throws SQLException {
        try {
            ClientPreparedStatement pStmt = (ClientPreparedStatement)this.clientPrepareStatement(sql);
            pStmt.setRetrieveGeneratedKeys(autoGenKeyIndexes != null && autoGenKeyIndexes.length > 0);
            return pStmt;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement clientPrepareStatement(String sql, String[] autoGenKeyColNames) throws SQLException {
        try {
            ClientPreparedStatement pStmt = (ClientPreparedStatement)this.clientPrepareStatement(sql);
            pStmt.setRetrieveGeneratedKeys(autoGenKeyColNames != null && autoGenKeyColNames.length > 0);
            return pStmt;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement clientPrepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        try {
            return this.clientPrepareStatement(sql, resultSetType, resultSetConcurrency, true);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void close() throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                if (this.connectionLifecycleInterceptors != null) {
                    for (ConnectionLifecycleInterceptor cli : this.connectionLifecycleInterceptors) {
                        cli.close();
                    }
                }
                this.realClose(true, true, false, null);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void normalClose() {
        try {
            this.close();
        } catch (SQLException e) {
            ExceptionFactory.createException(e.getMessage(), e);
        }
    }

    private void closeAllOpenStatements() throws SQLException {
        SQLException postponedException = null;
        for (JdbcStatement stmt : this.openStatements) {
            try {
                ((StatementImpl)stmt).realClose(false, true);
            } catch (SQLException sqlEx) {
                postponedException = sqlEx;
            }
        }
        if (postponedException != null) {
            throw postponedException;
        }
    }

    private void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException sQLException) {
                // empty catch block
            }
            stmt = null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void commit() throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                this.checkClosed();
                try {
                    if (this.connectionLifecycleInterceptors != null) {
                        IterateBlock<ConnectionLifecycleInterceptor> iter = new IterateBlock<ConnectionLifecycleInterceptor>(this.connectionLifecycleInterceptors.iterator()){

                            @Override
                            void forEach(ConnectionLifecycleInterceptor each) throws SQLException {
                                if (!each.commit()) {
                                    this.stopIterating = true;
                                }
                            }
                        };
                        iter.doForAll();
                        if (!iter.fullIteration()) {
                            return;
                        }
                    }
                    if (this.session.getServerSession().isAutoCommit()) {
                        throw SQLError.createSQLException(Messages.getString("Connection.3"), this.getExceptionInterceptor());
                    }
                    if (this.useLocalTransactionState.getValue().booleanValue() && !this.session.getServerSession().inTransactionOnServer()) {
                        return;
                    }
                    this.session.execSQL(null, "commit", -1, null, false, this.nullStatementResultSetFactory, this.database, null, false);
                } catch (SQLException sqlException) {
                    if (!"08S01".equals(sqlException.getSQLState())) throw sqlException;
                    throw SQLError.createSQLException(Messages.getString("Connection.4"), "08007", this.getExceptionInterceptor());
                } finally {
                    this.session.setNeedsPing(this.reconnectAtTxEnd.getValue());
                }
                return;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void createNewIO(boolean isForReconnect) {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                try {
                    if (!this.autoReconnect.getValue().booleanValue()) {
                        this.connectOneTryOnly(isForReconnect);
                        return;
                    }
                    this.connectWithRetries(isForReconnect);
                } catch (SQLException ex) {
                    throw ExceptionFactory.createException(UnableToConnectException.class, ex.getMessage(), ex);
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void connectWithRetries(boolean isForReconnect) throws SQLException {
        double timeout2 = this.propertySet.getIntegerProperty("initialTimeout").getValue().intValue();
        boolean connectionGood = false;
        Exception connectionException = null;
        for (int attemptCount = 0; attemptCount < this.propertySet.getIntegerProperty("maxReconnects").getValue() && !connectionGood; ++attemptCount) {
            try {
                String oldCatalog;
                boolean oldReadOnly;
                int oldIsolationLevel;
                boolean oldAutoCommit;
                this.session.forceClose();
                JdbcConnection c = this.getProxy();
                this.session.connect(this.origHostInfo, this.user, this.password, this.database, DriverManager.getLoginTimeout() * 1000, c);
                this.pingInternal(false, 0);
                Object object = this.getConnectionMutex();
                synchronized (object) {
                    oldAutoCommit = this.getAutoCommit();
                    oldIsolationLevel = this.isolationLevel;
                    oldReadOnly = this.isReadOnly(false);
                    oldCatalog = this.getCatalog();
                    this.session.setQueryInterceptors(this.queryInterceptors);
                }
                this.initializePropsFromServer();
                if (isForReconnect) {
                    this.setAutoCommit(oldAutoCommit);
                    this.setTransactionIsolation(oldIsolationLevel);
                    this.setCatalog(oldCatalog);
                    this.setReadOnly(oldReadOnly);
                }
                connectionGood = true;
                break;
            } catch (UnableToConnectException rejEx) {
                this.close();
                this.session.getProtocol().getSocketConnection().forceClose();
            } catch (Exception EEE) {
                connectionException = EEE;
                connectionGood = false;
            }
            if (connectionGood) break;
            if (attemptCount <= 0) continue;
            try {
                Thread.sleep((long)timeout2 * 1000L);
                continue;
            } catch (InterruptedException EEE) {
                // empty catch block
            }
        }
        if (!connectionGood) {
            SQLException chainedEx = SQLError.createSQLException(Messages.getString("Connection.UnableToConnectWithRetries", new Object[]{this.propertySet.getIntegerProperty("maxReconnects").getValue()}), "08001", connectionException, this.getExceptionInterceptor());
            throw chainedEx;
        }
        if (this.propertySet.getBooleanProperty("paranoid").getValue().booleanValue() && !this.autoReconnect.getValue().booleanValue()) {
            this.password = null;
            this.user = null;
        }
        if (isForReconnect) {
            Iterator<JdbcStatement> statementIter = this.openStatements.iterator();
            Stack<JdbcStatement> serverPreparedStatements = null;
            while (statementIter.hasNext()) {
                JdbcStatement statementObj = statementIter.next();
                if (!(statementObj instanceof ServerPreparedStatement)) continue;
                if (serverPreparedStatements == null) {
                    serverPreparedStatements = new Stack<JdbcStatement>();
                }
                serverPreparedStatements.add(statementObj);
            }
            if (serverPreparedStatements != null) {
                while (!serverPreparedStatements.isEmpty()) {
                    ((ServerPreparedStatement)serverPreparedStatements.pop()).rePrepare();
                }
            }
        }
    }

    private void connectOneTryOnly(boolean isForReconnect) throws SQLException {
        Exception connectionNotEstablishedBecause = null;
        try {
            JdbcConnection c = this.getProxy();
            this.session.connect(this.origHostInfo, this.user, this.password, this.database, DriverManager.getLoginTimeout() * 1000, c);
            boolean oldAutoCommit = this.getAutoCommit();
            int oldIsolationLevel = this.isolationLevel;
            boolean oldReadOnly = this.isReadOnly(false);
            String oldCatalog = this.getCatalog();
            this.session.setQueryInterceptors(this.queryInterceptors);
            this.initializePropsFromServer();
            if (isForReconnect) {
                this.setAutoCommit(oldAutoCommit);
                this.setTransactionIsolation(oldIsolationLevel);
                this.setCatalog(oldCatalog);
                this.setReadOnly(oldReadOnly);
            }
            return;
        } catch (UnableToConnectException rejEx) {
            this.close();
            this.session.getProtocol().getSocketConnection().forceClose();
            throw rejEx;
        } catch (Exception EEE) {
            if ((EEE instanceof PasswordExpiredException || EEE instanceof SQLException && ((SQLException)EEE).getErrorCode() == 1820) && !this.disconnectOnExpiredPasswords.getValue().booleanValue()) {
                return;
            }
            if (this.session != null) {
                this.session.forceClose();
            }
            connectionNotEstablishedBecause = EEE;
            if (EEE instanceof SQLException) {
                throw (SQLException)EEE;
            }
            if (EEE.getCause() != null && EEE.getCause() instanceof SQLException) {
                throw (SQLException)EEE.getCause();
            }
            if (EEE instanceof CJException) {
                throw (CJException)EEE;
            }
            SQLException chainedEx = SQLError.createSQLException(Messages.getString("Connection.UnableToConnect"), "08001", this.getExceptionInterceptor());
            chainedEx.initCause(connectionNotEstablishedBecause);
            throw chainedEx;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void createPreparedStatementCaches() throws SQLException {
        Object object = this.getConnectionMutex();
        synchronized (object) {
            int cacheSize = this.propertySet.getIntegerProperty("prepStmtCacheSize").getValue();
            String parseInfoCacheFactory = this.propertySet.getStringProperty("parseInfoCacheFactory").getValue();
            try {
                Class<?> factoryClass = Class.forName(parseInfoCacheFactory);
                CacheAdapterFactory cacheFactory = (CacheAdapterFactory)factoryClass.newInstance();
                this.cachedPreparedStatementParams = cacheFactory.getInstance(this, this.origHostInfo.getDatabaseUrl(), cacheSize, this.prepStmtCacheSqlLimit.getValue());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                SQLException sqlEx = SQLError.createSQLException(Messages.getString("Connection.CantFindCacheFactory", new Object[]{parseInfoCacheFactory, "parseInfoCacheFactory"}), this.getExceptionInterceptor());
                sqlEx.initCause(e);
                throw sqlEx;
            } catch (Exception e) {
                SQLException sqlEx = SQLError.createSQLException(Messages.getString("Connection.CantLoadCacheFactory", new Object[]{parseInfoCacheFactory, "parseInfoCacheFactory"}), this.getExceptionInterceptor());
                sqlEx.initCause(e);
                throw sqlEx;
            }
            if (this.useServerPrepStmts.getValue().booleanValue()) {
                this.serverSideStatementCheckCache = new LRUCache(cacheSize);
                this.serverSideStatementCache = new LRUCache<CompoundCacheKey, ServerPreparedStatement>(cacheSize){
                    private static final long serialVersionUID = 7692318650375988114L;

                    @Override
                    protected boolean removeEldestEntry(Map.Entry<CompoundCacheKey, ServerPreparedStatement> eldest) {
                        if (this.maxElements <= 1) {
                            return false;
                        }
                        boolean removeIt = super.removeEldestEntry(eldest);
                        if (removeIt) {
                            ServerPreparedStatement ps = eldest.getValue();
                            ps.isCached = false;
                            ps.setClosed(false);
                            try {
                                ps.close();
                            } catch (SQLException sQLException) {
                                // empty catch block
                            }
                        }
                        return removeIt;
                    }
                };
            }
        }
    }

    @Override
    public Statement createStatement() throws SQLException {
        try {
            return this.createStatement(1003, 1007);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        try {
            this.checkClosed();
            StatementImpl stmt = new StatementImpl(this.getMultiHostSafeProxy(), this.database);
            stmt.setResultSetType(resultSetType);
            stmt.setResultSetConcurrency(resultSetConcurrency);
            return stmt;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        try {
            if (this.pedantic.getValue().booleanValue() && resultSetHoldability != 1) {
                throw SQLError.createSQLException("HOLD_CUSRORS_OVER_COMMIT is only supported holdability level", "S1009", this.getExceptionInterceptor());
            }
            return this.createStatement(resultSetType, resultSetConcurrency);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public int getActiveStatementCount() {
        return this.openStatements.size();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getAutoCommit() throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                return this.session.getServerSession().isAutoCommit();
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getCatalog() throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                return this.database;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getCharacterSetMetadata() {
        Object object = this.getConnectionMutex();
        synchronized (object) {
            return this.session.getServerSession().getCharacterSetMetadata();
        }
    }

    @Override
    public int getHoldability() throws SQLException {
        try {
            return 2;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public long getId() {
        return this.session.getThreadId();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getIdleFor() {
        Object object = this.getConnectionMutex();
        synchronized (object) {
            return this.session.getIdleFor();
        }
    }

    @Override
    public java.sql.DatabaseMetaData getMetaData() throws SQLException {
        try {
            return this.getMetaData(true, true);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    private java.sql.DatabaseMetaData getMetaData(boolean checkClosed, boolean checkForInfoSchema) throws SQLException {
        try {
            if (checkClosed) {
                this.checkClosed();
            }
            DatabaseMetaData dbmeta = DatabaseMetaData.getInstance(this.getMultiHostSafeProxy(), this.database, checkForInfoSchema, this.nullStatementResultSetFactory);
            if (this.getSession() != null && this.getSession().getProtocol() != null) {
                dbmeta.setMetadataEncoding(this.getSession().getServerSession().getCharacterSetMetadata());
                dbmeta.setMetadataCollationIndex(this.getSession().getServerSession().getMetadataCollationIndex());
            }
            return dbmeta;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Statement getMetadataSafeStatement() throws SQLException {
        try {
            return this.getMetadataSafeStatement(0);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    public Statement getMetadataSafeStatement(int maxRows) throws SQLException {
        Statement stmt = this.createStatement();
        stmt.setMaxRows(maxRows == -1 ? 0 : maxRows);
        stmt.setEscapeProcessing(false);
        if (stmt.getFetchSize() != 0) {
            stmt.setFetchSize(0);
        }
        return stmt;
    }

    @Override
    public ServerVersion getServerVersion() {
        return this.session.getServerSession().getServerVersion();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getTransactionIsolation() throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                if (!this.useLocalSessionState.getValue().booleanValue()) {
                    String s2 = this.session.queryServerVariable(this.versionMeetsMinimum(8, 0, 3) || this.versionMeetsMinimum(5, 7, 20) && !this.versionMeetsMinimum(8, 0, 0) ? "@@session.transaction_isolation" : "@@session.tx_isolation");
                    if (s2 != null) {
                        Integer intTI = mapTransIsolationNameToValue.get(s2);
                        if (intTI != null) {
                            this.isolationLevel = intTI;
                            return this.isolationLevel;
                        }
                        throw SQLError.createSQLException(Messages.getString("Connection.12", new Object[]{s2}), "S1000", this.getExceptionInterceptor());
                    }
                    throw SQLError.createSQLException(Messages.getString("Connection.13"), "S1000", this.getExceptionInterceptor());
                }
                return this.isolationLevel;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                if (this.typeMap == null) {
                    this.typeMap = new HashMap();
                }
                return this.typeMap;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public String getURL() {
        return this.origHostInfo.getDatabaseUrl();
    }

    @Override
    public String getUser() {
        return this.user;
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        try {
            return null;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean hasSameProperties(JdbcConnection c) {
        return this.props.equals(c.getProperties());
    }

    @Override
    public Properties getProperties() {
        return this.props;
    }

    @Override
    @Deprecated
    public boolean hasTriedMaster() {
        return this.hasTriedMasterFlag;
    }

    private void initializePropsFromServer() throws SQLException {
        String connectionInterceptorClasses = this.propertySet.getStringProperty("connectionLifecycleInterceptors").getStringValue();
        this.connectionLifecycleInterceptors = null;
        if (connectionInterceptorClasses != null) {
            try {
                this.connectionLifecycleInterceptors = Util.loadClasses(this.propertySet.getStringProperty("connectionLifecycleInterceptors").getStringValue(), "Connection.badLifecycleInterceptor", this.getExceptionInterceptor()).stream().map(o -> o.init(this, this.props, this.session.getLog())).collect(Collectors.toList());
            } catch (CJException e) {
                throw SQLExceptionsMapping.translateException(e, this.getExceptionInterceptor());
            }
        }
        this.session.setSessionVariables();
        this.session.loadServerVariables(this.getConnectionMutex(), this.dbmd.getDriverVersion());
        this.autoIncrementIncrement = this.session.getServerSession().getServerVariable("auto_increment_increment", 1);
        this.session.buildCollationMapping();
        try {
            LicenseConfiguration.checkLicenseType(this.session.getServerSession().getServerVariables());
        } catch (CJException e) {
            throw SQLError.createSQLException(e.getMessage(), "08001", this.getExceptionInterceptor());
        }
        this.session.getProtocol().initServerSession();
        this.checkTransactionIsolationLevel();
        this.session.checkForCharsetMismatch();
        this.session.configureClientCharacterSet(false);
        this.handleAutoCommitDefaults();
        this.session.getServerSession().configureCharacterSets();
        ((DatabaseMetaData)this.dbmd).setMetadataEncoding(this.getSession().getServerSession().getCharacterSetMetadata());
        ((DatabaseMetaData)this.dbmd).setMetadataCollationIndex(this.getSession().getServerSession().getMetadataCollationIndex());
        this.setupServerForTruncationChecks();
    }

    private void handleAutoCommitDefaults() throws SQLException {
        try {
            block9: {
                boolean resetAutoCommitDefault = false;
                String initConnectValue = this.session.getServerSession().getServerVariable("init_connect");
                if (initConnectValue != null && initConnectValue.length() > 0) {
                    String s2 = this.session.queryServerVariable("@@session.autocommit");
                    if (s2 != null) {
                        this.session.getServerSession().setAutoCommit(Boolean.parseBoolean(s2));
                        if (!this.session.getServerSession().isAutoCommit()) {
                            resetAutoCommitDefault = true;
                        }
                    }
                } else {
                    resetAutoCommitDefault = true;
                }
                if (resetAutoCommitDefault) {
                    try {
                        this.setAutoCommit(true);
                    } catch (SQLException ex) {
                        if (ex.getErrorCode() == 1820 && !this.disconnectOnExpiredPasswords.getValue().booleanValue()) break block9;
                        throw ex;
                    }
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isClosed() {
        try {
            return this.session.isClosed();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isInGlobalTx() {
        return this.isInGlobalTx;
    }

    @Override
    public boolean isMasterConnection() {
        return false;
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        try {
            return this.isReadOnly(true);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isReadOnly(boolean useSessionStatus) throws SQLException {
        try {
            block6: {
                if (useSessionStatus && !this.session.isClosed() && this.versionMeetsMinimum(5, 6, 5) && !this.useLocalSessionState.getValue().booleanValue() && this.readOnlyPropagatesToServer.getValue().booleanValue()) {
                    try {
                        String s2 = this.session.queryServerVariable(this.versionMeetsMinimum(8, 0, 3) || this.versionMeetsMinimum(5, 7, 20) && !this.versionMeetsMinimum(8, 0, 0) ? "@@session.transaction_read_only" : "@@session.tx_read_only");
                        if (s2 != null) {
                            return Integer.parseInt(s2) != 0;
                        }
                    } catch (PasswordExpiredException ex) {
                        if (!this.disconnectOnExpiredPasswords.getValue().booleanValue()) break block6;
                        throw SQLError.createSQLException(Messages.getString("Connection.16"), "S1000", ex, this.getExceptionInterceptor());
                    }
                }
            }
            return this.readOnly;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSameResource(JdbcConnection otherConnection) {
        Object object = this.getConnectionMutex();
        synchronized (object) {
            if (otherConnection == null) {
                return false;
            }
            boolean directCompare = true;
            String otherHost = ((ConnectionImpl)otherConnection).origHostToConnectTo;
            String otherOrigDatabase = ((ConnectionImpl)otherConnection).origHostInfo.getDatabase();
            String otherCurrentCatalog = ((ConnectionImpl)otherConnection).database;
            if (!ConnectionImpl.nullSafeCompare(otherHost, this.origHostToConnectTo)) {
                directCompare = false;
            } else if (otherHost != null && otherHost.indexOf(44) == -1 && otherHost.indexOf(58) == -1) {
                boolean bl = directCompare = ((ConnectionImpl)otherConnection).origPortToConnectTo == this.origPortToConnectTo;
            }
            if (!(!directCompare || ConnectionImpl.nullSafeCompare(otherOrigDatabase, this.origHostInfo.getDatabase()) && ConnectionImpl.nullSafeCompare(otherCurrentCatalog, this.database))) {
                directCompare = false;
            }
            if (directCompare) {
                return true;
            }
            String otherResourceId = ((ConnectionImpl)otherConnection).getPropertySet().getStringProperty("resourceId").getValue();
            String myResourceId = this.propertySet.getStringProperty("resourceId").getValue();
            return (otherResourceId != null || myResourceId != null) && (directCompare = ConnectionImpl.nullSafeCompare(otherResourceId, myResourceId));
            {
            }
        }
    }

    @Override
    public int getAutoIncrementIncrement() {
        return this.autoIncrementIncrement;
    }

    @Override
    public boolean lowerCaseTableNames() {
        return this.session.getServerSession().isLowerCaseTableNames();
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        try {
            if (sql == null) {
                return null;
            }
            Object escapedSqlResult = EscapeProcessor.escapeSQL(sql, this.getMultiHostSafeProxy().getSession().getServerSession().getDefaultTimeZone(), this.getMultiHostSafeProxy().getSession().getServerSession().getCapabilities().serverSupportsFracSecs(), this.getExceptionInterceptor());
            if (escapedSqlResult instanceof String) {
                return (String)escapedSqlResult;
            }
            return ((EscapeProcessorResult)escapedSqlResult).escapedSql;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    private CallableStatement parseCallableStatement(String sql) throws SQLException {
        Object escapedSqlResult = EscapeProcessor.escapeSQL(sql, this.getMultiHostSafeProxy().getSession().getServerSession().getDefaultTimeZone(), this.getMultiHostSafeProxy().getSession().getServerSession().getCapabilities().serverSupportsFracSecs(), this.getExceptionInterceptor());
        boolean isFunctionCall = false;
        String parsedSql = null;
        if (escapedSqlResult instanceof EscapeProcessorResult) {
            parsedSql = ((EscapeProcessorResult)escapedSqlResult).escapedSql;
            isFunctionCall = ((EscapeProcessorResult)escapedSqlResult).callingStoredFunction;
        } else {
            parsedSql = (String)escapedSqlResult;
            isFunctionCall = false;
        }
        return CallableStatement.getInstance(this.getMultiHostSafeProxy(), parsedSql, this.database, isFunctionCall);
    }

    @Override
    public void ping() throws SQLException {
        try {
            this.pingInternal(true, 0);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void pingInternal(boolean checkForClosedConnection, int timeoutMillis) throws SQLException {
        try {
            this.session.ping(checkForClosedConnection, timeoutMillis);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public java.sql.CallableStatement prepareCall(String sql) throws SQLException {
        try {
            return this.prepareCall(sql, 1003, 1007);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public java.sql.CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        try {
            CallableStatement cStmt = null;
            if (!this.propertySet.getBooleanProperty("cacheCallableStmts").getValue().booleanValue()) {
                cStmt = this.parseCallableStatement(sql);
            } else {
                LRUCache<CompoundCacheKey, CallableStatement.CallableStatementParamInfo> lRUCache = this.parsedCallableStatementCache;
                synchronized (lRUCache) {
                    CompoundCacheKey key = new CompoundCacheKey(this.getCatalog(), sql);
                    CallableStatement.CallableStatementParamInfo cachedParamInfo = (CallableStatement.CallableStatementParamInfo)this.parsedCallableStatementCache.get(key);
                    if (cachedParamInfo != null) {
                        cStmt = CallableStatement.getInstance(this.getMultiHostSafeProxy(), cachedParamInfo);
                    } else {
                        CallableStatement callableStatement = cStmt = this.parseCallableStatement(sql);
                        synchronized (callableStatement) {
                            cachedParamInfo = cStmt.paramInfo;
                        }
                        this.parsedCallableStatementCache.put(key, cachedParamInfo);
                    }
                }
            }
            cStmt.setResultSetType(resultSetType);
            cStmt.setResultSetConcurrency(resultSetConcurrency);
            return cStmt;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public java.sql.CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        try {
            if (this.pedantic.getValue().booleanValue() && resultSetHoldability != 1) {
                throw SQLError.createSQLException(Messages.getString("Connection.17"), "S1009", this.getExceptionInterceptor());
            }
            CallableStatement cStmt = (CallableStatement)this.prepareCall(sql, resultSetType, resultSetConcurrency);
            return cStmt;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        try {
            return this.prepareStatement(sql, 1003, 1007);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGenKeyIndex) throws SQLException {
        try {
            PreparedStatement pStmt = this.prepareStatement(sql);
            ((ClientPreparedStatement)pStmt).setRetrieveGeneratedKeys(autoGenKeyIndex == 1);
            return pStmt;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                String nativeSql;
                this.checkClosed();
                ClientPreparedStatement pStmt = null;
                boolean canServerPrepare = true;
                String string = nativeSql = this.processEscapeCodesForPrepStmts.getValue() != false ? this.nativeSQL(sql) : sql;
                if (this.useServerPrepStmts.getValue().booleanValue() && this.emulateUnsupportedPstmts.getValue().booleanValue()) {
                    canServerPrepare = this.canHandleAsServerPreparedStatement(nativeSql);
                }
                if (this.useServerPrepStmts.getValue().booleanValue() && canServerPrepare) {
                    if (this.cachePrepStmts.getValue().booleanValue()) {
                        LRUCache<CompoundCacheKey, ServerPreparedStatement> lRUCache = this.serverSideStatementCache;
                        synchronized (lRUCache) {
                            pStmt = (ClientPreparedStatement)this.serverSideStatementCache.remove(new CompoundCacheKey(this.database, sql));
                            if (pStmt != null) {
                                ((ServerPreparedStatement)pStmt).setClosed(false);
                                pStmt.clearParameters();
                            }
                            if (pStmt == null) {
                                try {
                                    pStmt = ServerPreparedStatement.getInstance(this.getMultiHostSafeProxy(), nativeSql, this.database, resultSetType, resultSetConcurrency);
                                    if (sql.length() < this.prepStmtCacheSqlLimit.getValue()) {
                                        ((ServerPreparedStatement)pStmt).isCached = true;
                                    }
                                    pStmt.setResultSetType(resultSetType);
                                    pStmt.setResultSetConcurrency(resultSetConcurrency);
                                } catch (SQLException sqlEx) {
                                    if (this.emulateUnsupportedPstmts.getValue().booleanValue()) {
                                        pStmt = (ClientPreparedStatement)this.clientPrepareStatement(nativeSql, resultSetType, resultSetConcurrency, false);
                                        if (sql.length() < this.prepStmtCacheSqlLimit.getValue()) {
                                            this.serverSideStatementCheckCache.put(sql, Boolean.FALSE);
                                        }
                                    }
                                    throw sqlEx;
                                }
                            }
                        }
                    }
                    try {
                        pStmt = ServerPreparedStatement.getInstance(this.getMultiHostSafeProxy(), nativeSql, this.database, resultSetType, resultSetConcurrency);
                        pStmt.setResultSetType(resultSetType);
                        pStmt.setResultSetConcurrency(resultSetConcurrency);
                    } catch (SQLException sqlEx) {
                        if (this.emulateUnsupportedPstmts.getValue().booleanValue()) {
                            pStmt = (ClientPreparedStatement)this.clientPrepareStatement(nativeSql, resultSetType, resultSetConcurrency, false);
                        }
                        throw sqlEx;
                    }
                } else {
                    pStmt = (ClientPreparedStatement)this.clientPrepareStatement(nativeSql, resultSetType, resultSetConcurrency, false);
                }
                return pStmt;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        try {
            if (this.pedantic.getValue().booleanValue() && resultSetHoldability != 1) {
                throw SQLError.createSQLException(Messages.getString("Connection.17"), "S1009", this.getExceptionInterceptor());
            }
            return this.prepareStatement(sql, resultSetType, resultSetConcurrency);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] autoGenKeyIndexes) throws SQLException {
        try {
            PreparedStatement pStmt = this.prepareStatement(sql);
            ((ClientPreparedStatement)pStmt).setRetrieveGeneratedKeys(autoGenKeyIndexes != null && autoGenKeyIndexes.length > 0);
            return pStmt;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] autoGenKeyColNames) throws SQLException {
        try {
            PreparedStatement pStmt = this.prepareStatement(sql);
            ((ClientPreparedStatement)pStmt).setRetrieveGeneratedKeys(autoGenKeyColNames != null && autoGenKeyColNames.length > 0);
            return pStmt;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void realClose(boolean calledExplicitly, boolean issueRollback, boolean skipLocalTeardown, Throwable reason) throws SQLException {
        try {
            SQLException sqlEx = null;
            if (this.isClosed()) {
                return;
            }
            this.session.setForceClosedReason(reason);
            try {
                if (!skipLocalTeardown) {
                    if (!this.getAutoCommit() && issueRollback) {
                        try {
                            this.rollback();
                        } catch (SQLException ex) {
                            sqlEx = ex;
                        }
                    }
                    this.session.reportMetrics();
                    if (this.useUsageAdvisor.getValue().booleanValue()) {
                        long connectionLifeTime;
                        if (!calledExplicitly) {
                            this.session.getProfilerEventHandler().consumeEvent(new ProfilerEventImpl(0, "", this.getCatalog(), this.session.getThreadId(), -1, -1, System.currentTimeMillis(), 0L, Constants.MILLIS_I18N, null, this.pointOfOrigin, Messages.getString("Connection.18")));
                        }
                        if ((connectionLifeTime = System.currentTimeMillis() - this.session.getConnectionCreationTimeMillis()) < 500L) {
                            this.session.getProfilerEventHandler().consumeEvent(new ProfilerEventImpl(0, "", this.getCatalog(), this.session.getThreadId(), -1, -1, System.currentTimeMillis(), 0L, Constants.MILLIS_I18N, null, this.pointOfOrigin, Messages.getString("Connection.19")));
                        }
                    }
                    try {
                        this.closeAllOpenStatements();
                    } catch (SQLException ex) {
                        sqlEx = ex;
                    }
                    this.session.quit();
                } else {
                    this.session.forceClose();
                }
                if (this.queryInterceptors != null) {
                    for (int i = 0; i < this.queryInterceptors.size(); ++i) {
                        this.queryInterceptors.get(i).destroy();
                    }
                }
                if (this.exceptionInterceptor != null) {
                    this.exceptionInterceptor.destroy();
                }
            } finally {
                ProfilerEventHandlerFactory.removeInstance(this.session);
                this.openStatements.clear();
                this.queryInterceptors = null;
                this.exceptionInterceptor = null;
                this.nullStatementResultSetFactory = null;
            }
            if (sqlEx != null) {
                throw sqlEx;
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void recachePreparedStatement(JdbcPreparedStatement pstmt) throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                if (this.cachePrepStmts.getValue().booleanValue() && pstmt.isPoolable()) {
                    LRUCache<CompoundCacheKey, ServerPreparedStatement> lRUCache = this.serverSideStatementCache;
                    synchronized (lRUCache) {
                        ServerPreparedStatement oldServerPrepStmt = this.serverSideStatementCache.put(new CompoundCacheKey(pstmt.getCurrentCatalog(), ((PreparedQuery)pstmt.getQuery()).getOriginalSql()), (ServerPreparedStatement)pstmt);
                        if (oldServerPrepStmt != null && oldServerPrepStmt != pstmt) {
                            oldServerPrepStmt.isCached = false;
                            oldServerPrepStmt.setClosed(false);
                            oldServerPrepStmt.realClose(true, true);
                        }
                    }
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void decachePreparedStatement(JdbcPreparedStatement pstmt) throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                if (this.cachePrepStmts.getValue().booleanValue() && pstmt.isPoolable()) {
                    LRUCache<CompoundCacheKey, ServerPreparedStatement> lRUCache = this.serverSideStatementCache;
                    synchronized (lRUCache) {
                        this.serverSideStatementCache.remove(new CompoundCacheKey(pstmt.getCurrentCatalog(), ((PreparedQuery)pstmt.getQuery()).getOriginalSql()));
                    }
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void registerStatement(JdbcStatement stmt) {
        this.openStatements.addIfAbsent(stmt);
    }

    @Override
    public void releaseSavepoint(Savepoint arg0) throws SQLException {
        try {
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void resetServerState() throws SQLException {
        try {
            if (!this.propertySet.getBooleanProperty("paranoid").getValue().booleanValue() && this.session != null) {
                this.changeUser(this.user, this.password);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void rollback() throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                this.checkClosed();
                try {
                    if (this.connectionLifecycleInterceptors != null) {
                        IterateBlock<ConnectionLifecycleInterceptor> iter = new IterateBlock<ConnectionLifecycleInterceptor>(this.connectionLifecycleInterceptors.iterator()){

                            @Override
                            void forEach(ConnectionLifecycleInterceptor each) throws SQLException {
                                if (!each.rollback()) {
                                    this.stopIterating = true;
                                }
                            }
                        };
                        iter.doForAll();
                        if (!iter.fullIteration()) {
                            return;
                        }
                    }
                    if (this.session.getServerSession().isAutoCommit()) {
                        throw SQLError.createSQLException(Messages.getString("Connection.20"), "08003", this.getExceptionInterceptor());
                    }
                    try {
                        this.rollbackNoChecks();
                    } catch (SQLException sqlEx) {
                        if (this.ignoreNonTxTables.getInitialValue().booleanValue() && sqlEx.getErrorCode() == 1196) {
                            this.session.setNeedsPing(this.reconnectAtTxEnd.getValue());
                            return;
                        }
                        try {
                            throw sqlEx;
                        } catch (SQLException sqlException) {
                            if ("08S01".equals(sqlException.getSQLState())) {
                                throw SQLError.createSQLException(Messages.getString("Connection.21"), "08007", this.getExceptionInterceptor());
                            }
                            throw sqlException;
                        } catch (Throwable throwable) {
                            throw throwable;
                        }
                    }
                } finally {
                    this.session.setNeedsPing(this.reconnectAtTxEnd.getValue());
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void rollback(final Savepoint savepoint) throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                this.checkClosed();
                try {
                    if (this.connectionLifecycleInterceptors != null) {
                        IterateBlock<ConnectionLifecycleInterceptor> iter = new IterateBlock<ConnectionLifecycleInterceptor>(this.connectionLifecycleInterceptors.iterator()){

                            @Override
                            void forEach(ConnectionLifecycleInterceptor each) throws SQLException {
                                if (!each.rollback(savepoint)) {
                                    this.stopIterating = true;
                                }
                            }
                        };
                        iter.doForAll();
                        if (!iter.fullIteration()) {
                            return;
                        }
                    }
                    StringBuilder rollbackQuery = new StringBuilder("ROLLBACK TO SAVEPOINT ");
                    rollbackQuery.append('`');
                    rollbackQuery.append(savepoint.getSavepointName());
                    rollbackQuery.append('`');
                    Statement stmt = null;
                    try {
                        stmt = this.getMetadataSafeStatement();
                        stmt.executeUpdate(rollbackQuery.toString());
                    } catch (SQLException sqlEx) {
                        int indexOfError153;
                        String msg;
                        int errno = sqlEx.getErrorCode();
                        if (errno == 1181 && (msg = sqlEx.getMessage()) != null && (indexOfError153 = msg.indexOf("153")) != -1) {
                            throw SQLError.createSQLException(Messages.getString("Connection.22", new Object[]{savepoint.getSavepointName()}), "S1009", errno, this.getExceptionInterceptor());
                        }
                        if (this.ignoreNonTxTables.getValue().booleanValue() && sqlEx.getErrorCode() != 1196) {
                            throw sqlEx;
                        }
                        if ("08S01".equals(sqlEx.getSQLState())) {
                            throw SQLError.createSQLException(Messages.getString("Connection.23"), "08007", this.getExceptionInterceptor());
                        }
                        throw sqlEx;
                    } finally {
                        this.closeStatement(stmt);
                    }
                } finally {
                    this.session.setNeedsPing(this.reconnectAtTxEnd.getValue());
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void rollbackNoChecks() throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                if (this.useLocalTransactionState.getValue().booleanValue() && !this.session.getServerSession().inTransactionOnServer()) {
                    return;
                }
                this.session.execSQL(null, "rollback", -1, null, false, this.nullStatementResultSetFactory, this.database, null, false);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement serverPrepareStatement(String sql) throws SQLException {
        try {
            String nativeSql = this.processEscapeCodesForPrepStmts.getValue() != false ? this.nativeSQL(sql) : sql;
            return ServerPreparedStatement.getInstance(this.getMultiHostSafeProxy(), nativeSql, this.getCatalog(), 1003, 1007);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement serverPrepareStatement(String sql, int autoGenKeyIndex) throws SQLException {
        try {
            String nativeSql = this.processEscapeCodesForPrepStmts.getValue() != false ? this.nativeSQL(sql) : sql;
            ServerPreparedStatement pStmt = ServerPreparedStatement.getInstance(this.getMultiHostSafeProxy(), nativeSql, this.getCatalog(), 1003, 1007);
            pStmt.setRetrieveGeneratedKeys(autoGenKeyIndex == 1);
            return pStmt;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement serverPrepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        try {
            String nativeSql = this.processEscapeCodesForPrepStmts.getValue() != false ? this.nativeSQL(sql) : sql;
            return ServerPreparedStatement.getInstance(this.getMultiHostSafeProxy(), nativeSql, this.getCatalog(), resultSetType, resultSetConcurrency);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement serverPrepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        try {
            if (this.pedantic.getValue().booleanValue() && resultSetHoldability != 1) {
                throw SQLError.createSQLException(Messages.getString("Connection.17"), "S1009", this.getExceptionInterceptor());
            }
            return this.serverPrepareStatement(sql, resultSetType, resultSetConcurrency);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement serverPrepareStatement(String sql, int[] autoGenKeyIndexes) throws SQLException {
        try {
            ClientPreparedStatement pStmt = (ClientPreparedStatement)this.serverPrepareStatement(sql);
            pStmt.setRetrieveGeneratedKeys(autoGenKeyIndexes != null && autoGenKeyIndexes.length > 0);
            return pStmt;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public PreparedStatement serverPrepareStatement(String sql, String[] autoGenKeyColNames) throws SQLException {
        try {
            ClientPreparedStatement pStmt = (ClientPreparedStatement)this.serverPrepareStatement(sql);
            pStmt.setRetrieveGeneratedKeys(autoGenKeyColNames != null && autoGenKeyColNames.length > 0);
            return pStmt;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAutoCommit(final boolean autoCommitFlag) throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                this.checkClosed();
                if (this.connectionLifecycleInterceptors != null) {
                    IterateBlock<ConnectionLifecycleInterceptor> iter = new IterateBlock<ConnectionLifecycleInterceptor>(this.connectionLifecycleInterceptors.iterator()){

                        @Override
                        void forEach(ConnectionLifecycleInterceptor each) throws SQLException {
                            if (!each.setAutoCommit(autoCommitFlag)) {
                                this.stopIterating = true;
                            }
                        }
                    };
                    iter.doForAll();
                    if (!iter.fullIteration()) {
                        return;
                    }
                }
                if (this.autoReconnectForPools.getValue().booleanValue()) {
                    this.autoReconnect.setValue(true);
                }
                try {
                    boolean needsSetOnServer = true;
                    if (this.useLocalSessionState.getValue().booleanValue() && this.session.getServerSession().isAutoCommit() == autoCommitFlag) {
                        needsSetOnServer = false;
                    } else if (!this.autoReconnect.getValue().booleanValue()) {
                        needsSetOnServer = this.getSession().isSetNeededForAutoCommitMode(autoCommitFlag);
                    }
                    this.session.getServerSession().setAutoCommit(autoCommitFlag);
                    if (needsSetOnServer) {
                        this.session.execSQL(null, autoCommitFlag ? "SET autocommit=1" : "SET autocommit=0", -1, null, false, this.nullStatementResultSetFactory, this.database, null, false);
                    }
                } finally {
                    if (this.autoReconnectForPools.getValue().booleanValue()) {
                        this.autoReconnect.setValue(false);
                    }
                }
                return;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCatalog(final String catalog) throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                String quotedId;
                this.checkClosed();
                if (catalog == null) {
                    throw SQLError.createSQLException("Catalog can not be null", "S1009", this.getExceptionInterceptor());
                }
                if (this.connectionLifecycleInterceptors != null) {
                    IterateBlock<ConnectionLifecycleInterceptor> iter = new IterateBlock<ConnectionLifecycleInterceptor>(this.connectionLifecycleInterceptors.iterator()){

                        @Override
                        void forEach(ConnectionLifecycleInterceptor each) throws SQLException {
                            if (!each.setCatalog(catalog)) {
                                this.stopIterating = true;
                            }
                        }
                    };
                    iter.doForAll();
                    if (!iter.fullIteration()) {
                        return;
                    }
                }
                if (this.useLocalSessionState.getValue().booleanValue()) {
                    if (this.session.getServerSession().isLowerCaseTableNames()) {
                        if (this.database.equalsIgnoreCase(catalog)) {
                            return;
                        }
                    } else if (this.database.equals(catalog)) {
                        return;
                    }
                }
                if ((quotedId = this.session.getIdentifierQuoteString()) == null || quotedId.equals(" ")) {
                    quotedId = "";
                }
                StringBuilder query = new StringBuilder("USE ");
                query.append(StringUtils.quoteIdentifier(catalog, quotedId, this.pedantic.getValue()));
                this.session.execSQL(null, query.toString(), -1, null, false, this.nullStatementResultSetFactory, this.database, null, false);
                this.database = catalog;
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setFailedOver(boolean flag) {
    }

    @Override
    public void setHoldability(int arg0) throws SQLException {
        try {
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setInGlobalTx(boolean flag) {
        this.isInGlobalTx = flag;
    }

    @Override
    public void setReadOnly(boolean readOnlyFlag) throws SQLException {
        try {
            this.checkClosed();
            this.setReadOnlyInternal(readOnlyFlag);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setReadOnlyInternal(boolean readOnlyFlag) throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                if (this.readOnlyPropagatesToServer.getValue().booleanValue() && this.versionMeetsMinimum(5, 6, 5) && (!this.useLocalSessionState.getValue().booleanValue() || readOnlyFlag != this.readOnly)) {
                    this.session.execSQL(null, "set session transaction " + (readOnlyFlag ? "read only" : "read write"), -1, null, false, this.nullStatementResultSetFactory, this.database, null, false);
                }
                this.readOnly = readOnlyFlag;
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        try {
            MysqlSavepoint savepoint = new MysqlSavepoint(this.getExceptionInterceptor());
            this.setSavepoint(savepoint);
            return savepoint;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void setSavepoint(MysqlSavepoint savepoint) throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                this.checkClosed();
                StringBuilder savePointQuery = new StringBuilder("SAVEPOINT ");
                savePointQuery.append('`');
                savePointQuery.append(savepoint.getSavepointName());
                savePointQuery.append('`');
                Statement stmt = null;
                try {
                    stmt = this.getMetadataSafeStatement();
                    stmt.executeUpdate(savePointQuery.toString());
                } finally {
                    this.closeStatement(stmt);
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                MysqlSavepoint savepoint = new MysqlSavepoint(name, this.getExceptionInterceptor());
                this.setSavepoint(savepoint);
                return savepoint;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                this.checkClosed();
                String sql = null;
                boolean shouldSendSet = false;
                if (this.propertySet.getBooleanProperty("alwaysSendSetIsolation").getValue().booleanValue()) {
                    shouldSendSet = true;
                } else if (level != this.isolationLevel) {
                    shouldSendSet = true;
                }
                if (this.useLocalSessionState.getValue().booleanValue()) {
                    boolean bl = shouldSendSet = this.isolationLevel != level;
                }
                if (shouldSendSet) {
                    switch (level) {
                        case 0: {
                            throw SQLError.createSQLException(Messages.getString("Connection.24"), this.getExceptionInterceptor());
                        }
                        case 2: {
                            sql = "SET SESSION TRANSACTION ISOLATION LEVEL READ COMMITTED";
                            break;
                        }
                        case 1: {
                            sql = "SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED";
                            break;
                        }
                        case 4: {
                            sql = "SET SESSION TRANSACTION ISOLATION LEVEL REPEATABLE READ";
                            break;
                        }
                        case 8: {
                            sql = "SET SESSION TRANSACTION ISOLATION LEVEL SERIALIZABLE";
                            break;
                        }
                        default: {
                            throw SQLError.createSQLException(Messages.getString("Connection.25", new Object[]{level}), "S1C00", this.getExceptionInterceptor());
                        }
                    }
                    this.session.execSQL(null, sql, -1, null, false, this.nullStatementResultSetFactory, this.database, null, false);
                    this.isolationLevel = level;
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                this.typeMap = map;
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void setupServerForTruncationChecks() throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                RuntimeProperty<Boolean> jdbcCompliantTruncation = this.propertySet.getProperty("jdbcCompliantTruncation");
                if (((Boolean)jdbcCompliantTruncation.getValue()).booleanValue()) {
                    boolean strictTransTablesIsSet;
                    String currentSqlMode = this.session.getServerSession().getServerVariable("sql_mode");
                    boolean bl = strictTransTablesIsSet = StringUtils.indexOfIgnoreCase(currentSqlMode, "STRICT_TRANS_TABLES") != -1;
                    if (currentSqlMode == null || currentSqlMode.length() == 0 || !strictTransTablesIsSet) {
                        StringBuilder commandBuf = new StringBuilder("SET sql_mode='");
                        if (currentSqlMode != null && currentSqlMode.length() > 0) {
                            commandBuf.append(currentSqlMode);
                            commandBuf.append(",");
                        }
                        commandBuf.append("STRICT_TRANS_TABLES'");
                        this.session.execSQL(null, commandBuf.toString(), -1, null, false, this.nullStatementResultSetFactory, this.database, null, false);
                        jdbcCompliantTruncation.setValue(false);
                    } else if (strictTransTablesIsSet) {
                        jdbcCompliantTruncation.setValue(false);
                    }
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void shutdownServer() throws SQLException {
        try {
            try {
                this.session.shutdownServer();
            } catch (CJException ex) {
                SQLException sqlEx = SQLError.createSQLException(Messages.getString("Connection.UnhandledExceptionDuringShutdown"), "S1000", this.getExceptionInterceptor());
                sqlEx.initCause(ex);
                throw sqlEx;
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void unregisterStatement(JdbcStatement stmt) {
        this.openStatements.remove(stmt);
    }

    public boolean versionMeetsMinimum(int major, int minor, int subminor) {
        try {
            this.checkClosed();
            return this.session.versionMeetsMinimum(major, minor, subminor);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CachedResultSetMetaData getCachedMetaData(String sql) {
        if (this.resultSetMetadataCache != null) {
            LRUCache<String, CachedResultSetMetaData> lRUCache = this.resultSetMetadataCache;
            synchronized (lRUCache) {
                return (CachedResultSetMetaData)this.resultSetMetadataCache.get(sql);
            }
        }
        return null;
    }

    @Override
    public void initializeResultsMetadataFromCache(String sql, CachedResultSetMetaData cachedMetaData, ResultSetInternalMethods resultSet) throws SQLException {
        try {
            if (cachedMetaData == null) {
                cachedMetaData = new CachedResultSetMetaDataImpl();
                resultSet.getColumnDefinition().buildIndexMapping();
                resultSet.initializeWithMetadata();
                if (resultSet instanceof UpdatableResultSet) {
                    ((UpdatableResultSet)resultSet).checkUpdatability();
                }
                resultSet.populateCachedMetaData(cachedMetaData);
                this.resultSetMetadataCache.put(sql, cachedMetaData);
            } else {
                resultSet.getColumnDefinition().initializeFrom(cachedMetaData);
                resultSet.initializeWithMetadata();
                if (resultSet instanceof UpdatableResultSet) {
                    ((UpdatableResultSet)resultSet).checkUpdatability();
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public String getStatementComment() {
        return this.session.getProtocol().getQueryComment();
    }

    @Override
    public void setStatementComment(String comment) {
        this.session.getProtocol().setQueryComment(comment);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void transactionBegun() {
        Object object = this.getConnectionMutex();
        synchronized (object) {
            if (this.connectionLifecycleInterceptors != null) {
                this.connectionLifecycleInterceptors.stream().forEach(ConnectionLifecycleInterceptor::transactionBegun);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void transactionCompleted() {
        Object object = this.getConnectionMutex();
        synchronized (object) {
            if (this.connectionLifecycleInterceptors != null) {
                this.connectionLifecycleInterceptors.stream().forEach(ConnectionLifecycleInterceptor::transactionCompleted);
            }
        }
    }

    @Override
    public boolean storesLowerCaseTableName() {
        return this.session.getServerSession().storesLowerCaseTableNames();
    }

    @Override
    public ExceptionInterceptor getExceptionInterceptor() {
        return this.exceptionInterceptor;
    }

    @Override
    public boolean isServerLocal() throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                try {
                    return this.session.isServerLocal(this.getSession());
                } catch (CJException ex) {
                    SQLException sqlEx = SQLExceptionsMapping.translateException(ex, this.getExceptionInterceptor());
                    throw sqlEx;
                }
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getSessionMaxRows() {
        Object object = this.getConnectionMutex();
        synchronized (object) {
            return this.session.getSessionMaxRows();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSessionMaxRows(int max) throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                if (this.session.getSessionMaxRows() != max) {
                    this.session.setSessionMaxRows(max);
                    this.session.execSQL(null, "SET SQL_SELECT_LIMIT=" + (this.session.getSessionMaxRows() == -1 ? "DEFAULT" : Integer.valueOf(this.session.getSessionMaxRows())), -1, null, false, this.nullStatementResultSetFactory, this.database, null, false);
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSchema(String schema) throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                this.checkClosed();
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getSchema() throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                this.checkClosed();
                return null;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        try {
            SecurityManager sec = System.getSecurityManager();
            if (sec != null) {
                sec.checkPermission(ABORT_PERM);
            }
            if (executor == null) {
                throw SQLError.createSQLException(Messages.getString("Connection.26"), "S1009", this.getExceptionInterceptor());
            }
            executor.execute(new Runnable(){

                @Override
                public void run() {
                    try {
                        ConnectionImpl.this.abortInternal();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                SecurityManager sec = System.getSecurityManager();
                if (sec != null) {
                    sec.checkPermission(SET_NETWORK_TIMEOUT_PERM);
                }
                if (executor == null) {
                    throw SQLError.createSQLException(Messages.getString("Connection.26"), "S1009", this.getExceptionInterceptor());
                }
                this.checkClosed();
                executor.execute(new NetworkTimeoutSetter(this, milliseconds));
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getNetworkTimeout() throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                this.checkClosed();
                return this.session.getSocketTimeout();
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public java.sql.Clob createClob() {
        try {
            return new Clob(this.getExceptionInterceptor());
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public java.sql.Blob createBlob() {
        try {
            return new Blob(this.getExceptionInterceptor());
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public java.sql.NClob createNClob() {
        try {
            return new NClob(this.getExceptionInterceptor());
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        try {
            return new MysqlSQLXML(this.getExceptionInterceptor());
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isValid(int timeout2) throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                if (this.isClosed()) {
                    return false;
                }
                try {
                    try {
                        this.pingInternal(false, timeout2 * 1000);
                    } catch (Throwable t) {
                        try {
                            this.abortInternal();
                        } catch (Throwable throwable) {
                            // empty catch block
                        }
                        return false;
                    }
                } catch (Throwable t) {
                    return false;
                }
                return true;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ClientInfoProvider getClientInfoProviderImpl() throws SQLException {
        try {
            Object object = this.getConnectionMutex();
            synchronized (object) {
                if (this.infoProvider == null) {
                    block12: {
                        String clientInfoProvider = this.propertySet.getStringProperty("clientInfoProvider").getStringValue();
                        try {
                            try {
                                this.infoProvider = (ClientInfoProvider)Util.getInstance(clientInfoProvider, new Class[0], new Object[0], this.getExceptionInterceptor());
                            } catch (CJException ex) {
                                if (!(ex.getCause() instanceof ClassCastException)) break block12;
                                try {
                                    this.infoProvider = (ClientInfoProvider)Util.getInstance("com.mysql.cj.jdbc." + clientInfoProvider, new Class[0], new Object[0], this.getExceptionInterceptor());
                                } catch (CJException e) {
                                    throw SQLExceptionsMapping.translateException(e, this.getExceptionInterceptor());
                                }
                            }
                        } catch (ClassCastException cce) {
                            throw SQLError.createSQLException(Messages.getString("Connection.ClientInfoNotImplemented", new Object[]{clientInfoProvider}), "S1009", this.getExceptionInterceptor());
                        }
                    }
                    this.infoProvider.initialize(this, this.props);
                }
                return this.infoProvider;
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        try {
            this.getClientInfoProviderImpl().setClientInfo(this, name, value);
        } catch (SQLClientInfoException ciEx) {
            throw ciEx;
        } catch (CJException | SQLException sqlEx) {
            SQLClientInfoException clientInfoEx = new SQLClientInfoException();
            clientInfoEx.initCause(sqlEx);
            throw clientInfoEx;
        }
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        try {
            this.getClientInfoProviderImpl().setClientInfo(this, properties);
        } catch (SQLClientInfoException ciEx) {
            throw ciEx;
        } catch (CJException | SQLException sqlEx) {
            SQLClientInfoException clientInfoEx = new SQLClientInfoException();
            clientInfoEx.initCause(sqlEx);
            throw clientInfoEx;
        }
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        try {
            return this.getClientInfoProviderImpl().getClientInfo(this, name);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        try {
            return this.getClientInfoProviderImpl().getClientInfo(this);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        try {
            throw SQLError.createSQLFeatureNotSupportedException();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        try {
            throw SQLError.createSQLFeatureNotSupportedException();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        try {
            try {
                return iface.cast(this);
            } catch (ClassCastException cce) {
                throw SQLError.createSQLException("Unable to unwrap to " + iface.toString(), "S1009", this.getExceptionInterceptor());
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        try {
            this.checkClosed();
            return iface.isInstance(this);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public NativeSession getSession() {
        return this.session;
    }

    @Override
    public String getHostPortPair() {
        return this.origHostInfo.getHostPortPair();
    }

    @Override
    public void handleNormalClose() {
        try {
            this.close();
        } catch (SQLException e) {
            ExceptionFactory.createException(e.getMessage(), e);
        }
    }

    @Override
    public void handleReconnect() {
        this.createNewIO(true);
    }

    @Override
    public void handleCleanup(Throwable whyCleanedUp) {
        this.cleanup(whyCleanedUp);
    }

    static {
        DEFAULT_LOGGER_CLASS = StandardLogger.class.getName();
        mapTransIsolationNameToValue = null;
        mapTransIsolationNameToValue = new HashMap<String, Integer>(8);
        mapTransIsolationNameToValue.put("READ-UNCOMMITED", 1);
        mapTransIsolationNameToValue.put("READ-UNCOMMITTED", 1);
        mapTransIsolationNameToValue.put("READ-COMMITTED", 2);
        mapTransIsolationNameToValue.put("REPEATABLE-READ", 4);
        mapTransIsolationNameToValue.put("SERIALIZABLE", 8);
        random = new Random();
    }

    private static class NetworkTimeoutSetter
    implements Runnable {
        private final WeakReference<JdbcConnection> connRef;
        private final int milliseconds;

        public NetworkTimeoutSetter(JdbcConnection conn, int milliseconds) {
            this.connRef = new WeakReference<JdbcConnection>(conn);
            this.milliseconds = milliseconds;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void run() {
            JdbcConnection conn = (JdbcConnection)this.connRef.get();
            if (conn != null) {
                Object object = conn.getConnectionMutex();
                synchronized (object) {
                    ((NativeSession)conn.getSession()).setSocketTimeout(this.milliseconds);
                }
            }
        }
    }

    static class CompoundCacheKey {
        final String componentOne;
        final String componentTwo;
        final int hashCode;

        CompoundCacheKey(String partOne, String partTwo) {
            this.componentOne = partOne;
            this.componentTwo = partTwo;
            int hc = 17;
            hc = 31 * hc + (this.componentOne != null ? this.componentOne.hashCode() : 0);
            this.hashCode = hc = 31 * hc + (this.componentTwo != null ? this.componentTwo.hashCode() : 0);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && CompoundCacheKey.class.isAssignableFrom(obj.getClass())) {
                CompoundCacheKey another = (CompoundCacheKey)obj;
                if (this.componentOne == null ? another.componentOne == null : this.componentOne.equals(another.componentOne)) {
                    return this.componentTwo == null ? another.componentTwo == null : this.componentTwo.equals(another.componentTwo);
                }
            }
            return false;
        }

        public int hashCode() {
            return this.hashCode;
        }
    }
}

