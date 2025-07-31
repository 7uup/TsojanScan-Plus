/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj;

import com.mysql.cj.CacheAdapter;
import com.mysql.cj.CacheAdapterFactory;
import com.mysql.cj.CharsetMapping;
import com.mysql.cj.CoreSession;
import com.mysql.cj.Messages;
import com.mysql.cj.Query;
import com.mysql.cj.Session;
import com.mysql.cj.TransactionEventHandler;
import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.CJOperationNotSupportedException;
import com.mysql.cj.exceptions.ConnectionIsClosedException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.exceptions.ExceptionInterceptorChain;
import com.mysql.cj.exceptions.OperationCancelledException;
import com.mysql.cj.exceptions.PasswordExpiredException;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.interceptors.QueryInterceptor;
import com.mysql.cj.log.Log;
import com.mysql.cj.log.ProfilerEventHandler;
import com.mysql.cj.log.ProfilerEventHandlerFactory;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.NetworkResources;
import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.SocketFactory;
import com.mysql.cj.protocol.a.NativeMessageBuilder;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.protocol.a.NativeProtocol;
import com.mysql.cj.protocol.a.NativeServerSession;
import com.mysql.cj.protocol.a.NativeSocketConnection;
import com.mysql.cj.protocol.a.ResultsetFactory;
import com.mysql.cj.result.Field;
import com.mysql.cj.result.IntegerValueFactory;
import com.mysql.cj.result.LongValueFactory;
import com.mysql.cj.result.Row;
import com.mysql.cj.result.StringValueFactory;
import com.mysql.cj.util.StringUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.UnsupportedCharsetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class NativeSession
extends CoreSession
implements Serializable {
    private static final long serialVersionUID = 5323638898749073419L;
    private CacheAdapter<String, Map<String, String>> serverConfigCache;
    private static final Map<String, Map<Integer, String>> customIndexToCharsetMapByUrl = new HashMap<String, Map<Integer, String>>();
    private static final Map<String, Map<String, Integer>> customCharsetToMblenMapByUrl = new HashMap<String, Map<String, Integer>>();
    private boolean requiresEscapingEncoder;
    private long lastQueryFinishedTime = 0L;
    private boolean needsPing = false;
    private NativeMessageBuilder commandBuilder = new NativeMessageBuilder();
    private boolean isClosed = true;
    private Throwable forceClosedReason;
    private CopyOnWriteArrayList<WeakReference<Session.SessionEventListener>> listeners = new CopyOnWriteArrayList();
    private transient Timer cancelTimer;
    private static final String SERVER_VERSION_STRING_VAR_NAME = "server_version_string";

    public NativeSession(HostInfo hostInfo, PropertySet propSet) {
        super(hostInfo, propSet);
    }

    public void connect(HostInfo hi, String user, String password, String database, int loginTimeout, TransactionEventHandler transactionManager) throws IOException {
        this.hostInfo = hi;
        this.setSessionMaxRows(-1);
        NativeSocketConnection socketConnection = new NativeSocketConnection();
        socketConnection.connect(this.hostInfo.getHost(), this.hostInfo.getPort(), this.propertySet, this.getExceptionInterceptor(), this.log, loginTimeout);
        if (this.protocol == null) {
            this.protocol = NativeProtocol.getInstance(this, socketConnection, this.propertySet, this.log, transactionManager);
        } else {
            this.protocol.init(this, socketConnection, this.propertySet, transactionManager);
        }
        this.protocol.connect(user, password, database);
        this.protocol.getServerSession().setErrorMessageEncoding(this.protocol.getAuthenticationProvider().getEncodingForHandshake());
        this.isClosed = false;
    }

    public NativeProtocol getProtocol() {
        return (NativeProtocol)this.protocol;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void quit() {
        if (this.protocol != null) {
            try {
                ((NativeProtocol)this.protocol).quit();
            } catch (Exception exception) {
                // empty catch block
            }
        }
        NativeSession nativeSession = this;
        synchronized (nativeSession) {
            if (this.cancelTimer != null) {
                this.cancelTimer.cancel();
                this.cancelTimer = null;
            }
        }
        this.isClosed = true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void forceClose() {
        if (this.protocol != null) {
            try {
                this.protocol.getSocketConnection().forceClose();
                ((NativeProtocol)this.protocol).releaseResources();
            } catch (Throwable throwable) {
                // empty catch block
            }
        }
        NativeSession nativeSession = this;
        synchronized (nativeSession) {
            if (this.cancelTimer != null) {
                this.cancelTimer.cancel();
                this.cancelTimer = null;
            }
        }
        this.isClosed = true;
    }

    public void enableMultiQueries() {
        this.sendCommand(this.commandBuilder.buildComSetOption(((NativeProtocol)this.protocol).getSharedSendPacket(), 0), false, 0);
    }

    public void disableMultiQueries() {
        this.sendCommand(this.commandBuilder.buildComSetOption(((NativeProtocol)this.protocol).getSharedSendPacket(), 1), false, 0);
    }

    @Override
    public boolean isSetNeededForAutoCommitMode(boolean autoCommitFlag) {
        return ((NativeServerSession)this.protocol.getServerSession()).isSetNeededForAutoCommitMode(autoCommitFlag, false);
    }

    public int getSessionMaxRows() {
        return this.sessionMaxRows;
    }

    public void setSessionMaxRows(int sessionMaxRows) {
        this.sessionMaxRows = sessionMaxRows;
    }

    public HostInfo getHostInfo() {
        return this.hostInfo;
    }

    public void setQueryInterceptors(List<QueryInterceptor> queryInterceptors) {
        ((NativeProtocol)this.protocol).setQueryInterceptors(queryInterceptors);
    }

    public boolean isServerLocal(Session sess) {
        SocketFactory factory2 = this.protocol.getSocketConnection().getSocketFactory();
        return factory2.isLocallyConnected(sess);
    }

    public void shutdownServer() {
        if (this.versionMeetsMinimum(5, 7, 9)) {
            this.sendCommand(this.commandBuilder.buildComQuery(this.getSharedSendPacket(), "SHUTDOWN"), false, 0);
        } else {
            this.sendCommand(this.commandBuilder.buildComShutdown(this.getSharedSendPacket()), false, 0);
        }
    }

    public void setSocketTimeout(int milliseconds) {
        this.getPropertySet().getProperty("socketTimeout").setValue(milliseconds);
        ((NativeProtocol)this.protocol).setSocketTimeout(milliseconds);
    }

    public int getSocketTimeout() {
        RuntimeProperty sto = this.getPropertySet().getProperty("socketTimeout");
        return (Integer)sto.getValue();
    }

    public void checkForCharsetMismatch() {
        ((NativeProtocol)this.protocol).checkForCharsetMismatch();
    }

    public NativePacketPayload getSharedSendPacket() {
        return ((NativeProtocol)this.protocol).getSharedSendPacket();
    }

    public void dumpPacketRingBuffer() {
        ((NativeProtocol)this.protocol).dumpPacketRingBuffer();
    }

    public <T extends Resultset> T invokeQueryInterceptorsPre(Supplier<String> sql, Query interceptedQuery, boolean forceExecute) {
        return ((NativeProtocol)this.protocol).invokeQueryInterceptorsPre(sql, interceptedQuery, forceExecute);
    }

    public <T extends Resultset> T invokeQueryInterceptorsPost(Supplier<String> sql, Query interceptedQuery, T originalResultSet, boolean forceExecute) {
        return ((NativeProtocol)this.protocol).invokeQueryInterceptorsPost(sql, interceptedQuery, originalResultSet, forceExecute);
    }

    public boolean shouldIntercept() {
        return ((NativeProtocol)this.protocol).getQueryInterceptors() != null;
    }

    public long getCurrentTimeNanosOrMillis() {
        return ((NativeProtocol)this.protocol).getCurrentTimeNanosOrMillis();
    }

    public final NativePacketPayload sendCommand(NativePacketPayload queryPacket, boolean skipCheck, int timeoutMillis) {
        return (NativePacketPayload)this.protocol.sendCommand(queryPacket, skipCheck, timeoutMillis);
    }

    public long getSlowQueryThreshold() {
        return ((NativeProtocol)this.protocol).getSlowQueryThreshold();
    }

    public String getQueryTimingUnits() {
        return ((NativeProtocol)this.protocol).getQueryTimingUnits();
    }

    public boolean hadWarnings() {
        return ((NativeProtocol)this.protocol).hadWarnings();
    }

    public void clearInputStream() {
        ((NativeProtocol)this.protocol).clearInputStream();
    }

    public NetworkResources getNetworkResources() {
        return this.protocol.getSocketConnection().getNetworkResources();
    }

    @Override
    public boolean isSSLEstablished() {
        return this.protocol.getSocketConnection().isSSLEstablished();
    }

    public int getCommandCount() {
        return ((NativeProtocol)this.protocol).getCommandCount();
    }

    @Override
    public SocketAddress getRemoteSocketAddress() {
        return this.protocol.getSocketConnection().getMysqlSocket().getRemoteSocketAddress();
    }

    public ProfilerEventHandler getProfilerEventHandlerInstanceFunction() {
        return ProfilerEventHandlerFactory.getInstance(this);
    }

    public InputStream getLocalInfileInputStream() {
        return this.protocol.getLocalInfileInputStream();
    }

    public void setLocalInfileInputStream(InputStream stream) {
        this.protocol.setLocalInfileInputStream(stream);
    }

    public void registerQueryExecutionTime(long queryTimeMs) {
        ((NativeProtocol)this.protocol).getMetricsHolder().registerQueryExecutionTime(queryTimeMs);
    }

    public void reportNumberOfTablesAccessed(int numTablesAccessed) {
        ((NativeProtocol)this.protocol).getMetricsHolder().reportNumberOfTablesAccessed(numTablesAccessed);
    }

    public void incrementNumberOfPreparedExecutes() {
        if (((Boolean)this.gatherPerfMetrics.getValue()).booleanValue()) {
            ((NativeProtocol)this.protocol).getMetricsHolder().incrementNumberOfPreparedExecutes();
        }
    }

    public void incrementNumberOfPrepares() {
        if (((Boolean)this.gatherPerfMetrics.getValue()).booleanValue()) {
            ((NativeProtocol)this.protocol).getMetricsHolder().incrementNumberOfPrepares();
        }
    }

    public void incrementNumberOfResultSetsCreated() {
        if (((Boolean)this.gatherPerfMetrics.getValue()).booleanValue()) {
            ((NativeProtocol)this.protocol).getMetricsHolder().incrementNumberOfResultSetsCreated();
        }
    }

    public void reportMetrics() {
        if (((Boolean)this.gatherPerfMetrics.getValue()).booleanValue()) {
            // empty if block
        }
    }

    private void configureCharsetProperties() {
        if (this.characterEncoding.getValue() != null) {
            try {
                String testString = "abc";
                StringUtils.getBytes(testString, (String)this.characterEncoding.getValue());
            } catch (WrongArgumentException waEx) {
                String oldEncoding = (String)this.characterEncoding.getValue();
                this.characterEncoding.setValue(CharsetMapping.getJavaEncodingForMysqlCharset(oldEncoding));
                if (this.characterEncoding.getValue() == null) {
                    throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("Connection.5", new Object[]{oldEncoding}), this.getExceptionInterceptor());
                }
                String testString = "abc";
                StringUtils.getBytes(testString, (String)this.characterEncoding.getValue());
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean configureClientCharacterSet(boolean dontCheckServerMatch) {
        boolean characterSetAlreadyConfigured;
        block55: {
            block54: {
                String realJavaEncoding = (String)this.characterEncoding.getValue();
                RuntimeProperty<String> characterSetResults = this.getPropertySet().getProperty("characterSetResults");
                characterSetAlreadyConfigured = false;
                try {
                    String mysqlCharsetName;
                    characterSetAlreadyConfigured = true;
                    this.configureCharsetProperties();
                    realJavaEncoding = (String)this.characterEncoding.getValue();
                    try {
                        String serverEncodingToSet = CharsetMapping.getJavaEncodingForCollationIndex(this.protocol.getServerSession().getServerDefaultCollationIndex());
                        if (serverEncodingToSet == null || serverEncodingToSet.length() == 0) {
                            if (realJavaEncoding != null) {
                                this.characterEncoding.setValue(realJavaEncoding);
                            } else {
                                throw ExceptionFactory.createException(Messages.getString("Connection.6", new Object[]{this.protocol.getServerSession().getServerDefaultCollationIndex()}), this.getExceptionInterceptor());
                            }
                        }
                        if ("ISO8859_1".equalsIgnoreCase(serverEncodingToSet)) {
                            serverEncodingToSet = "Cp1252";
                        }
                        if ("UnicodeBig".equalsIgnoreCase(serverEncodingToSet) || "UTF-16".equalsIgnoreCase(serverEncodingToSet) || "UTF-16LE".equalsIgnoreCase(serverEncodingToSet) || "UTF-32".equalsIgnoreCase(serverEncodingToSet)) {
                            serverEncodingToSet = "UTF-8";
                        }
                        this.characterEncoding.setValue(serverEncodingToSet);
                    } catch (ArrayIndexOutOfBoundsException outOfBoundsEx) {
                        if (realJavaEncoding != null) {
                            this.characterEncoding.setValue(realJavaEncoding);
                        }
                        throw ExceptionFactory.createException(Messages.getString("Connection.6", new Object[]{this.protocol.getServerSession().getServerDefaultCollationIndex()}), this.getExceptionInterceptor());
                    }
                    if (this.characterEncoding.getValue() == null) {
                        this.characterEncoding.setValue("ISO8859_1");
                    }
                    if (realJavaEncoding != null) {
                        if (realJavaEncoding.equalsIgnoreCase("UTF-8") || realJavaEncoding.equalsIgnoreCase("UTF8")) {
                            boolean useutf8mb4 = CharsetMapping.UTF8MB4_INDEXES.contains(this.protocol.getServerSession().getServerDefaultCollationIndex());
                            if (!((Boolean)this.useOldUTF8Behavior.getValue()).booleanValue()) {
                                if (dontCheckServerMatch || !this.protocol.getServerSession().characterSetNamesMatches("utf8") || !this.protocol.getServerSession().characterSetNamesMatches("utf8mb4")) {
                                    this.sendCommand(this.commandBuilder.buildComQuery(null, "SET NAMES " + (useutf8mb4 ? "utf8mb4" : "utf8")), false, 0);
                                    this.protocol.getServerSession().getServerVariables().put("character_set_client", useutf8mb4 ? "utf8mb4" : "utf8");
                                    this.protocol.getServerSession().getServerVariables().put("character_set_connection", useutf8mb4 ? "utf8mb4" : "utf8");
                                }
                            } else {
                                this.sendCommand(this.commandBuilder.buildComQuery(null, "SET NAMES latin1"), false, 0);
                                this.protocol.getServerSession().getServerVariables().put("character_set_client", "latin1");
                                this.protocol.getServerSession().getServerVariables().put("character_set_connection", "latin1");
                            }
                            this.characterEncoding.setValue(realJavaEncoding);
                        } else {
                            mysqlCharsetName = CharsetMapping.getMysqlCharsetForJavaEncoding(realJavaEncoding.toUpperCase(Locale.ENGLISH), this.getServerSession().getServerVersion());
                            if (mysqlCharsetName != null && (dontCheckServerMatch || !this.protocol.getServerSession().characterSetNamesMatches(mysqlCharsetName))) {
                                this.sendCommand(this.commandBuilder.buildComQuery(null, "SET NAMES " + mysqlCharsetName), false, 0);
                                this.protocol.getServerSession().getServerVariables().put("character_set_client", mysqlCharsetName);
                                this.protocol.getServerSession().getServerVariables().put("character_set_connection", mysqlCharsetName);
                            }
                            this.characterEncoding.setValue(realJavaEncoding);
                        }
                    } else if (this.characterEncoding.getValue() != null) {
                        block50: {
                            mysqlCharsetName = this.getServerSession().getServerDefaultCharset();
                            if (((Boolean)this.useOldUTF8Behavior.getValue()).booleanValue()) {
                                mysqlCharsetName = "latin1";
                            }
                            boolean ucs2 = false;
                            if ("ucs2".equalsIgnoreCase(mysqlCharsetName) || "utf16".equalsIgnoreCase(mysqlCharsetName) || "utf16le".equalsIgnoreCase(mysqlCharsetName) || "utf32".equalsIgnoreCase(mysqlCharsetName)) {
                                mysqlCharsetName = "utf8";
                                ucs2 = true;
                                if (characterSetResults.getValue() == null) {
                                    characterSetResults.setValue("UTF-8");
                                }
                            }
                            if (dontCheckServerMatch || !this.protocol.getServerSession().characterSetNamesMatches(mysqlCharsetName) || ucs2) {
                                try {
                                    this.sendCommand(this.commandBuilder.buildComQuery(null, "SET NAMES " + mysqlCharsetName), false, 0);
                                    this.protocol.getServerSession().getServerVariables().put("character_set_client", mysqlCharsetName);
                                    this.protocol.getServerSession().getServerVariables().put("character_set_connection", mysqlCharsetName);
                                } catch (PasswordExpiredException ex) {
                                    if (!((Boolean)this.disconnectOnExpiredPasswords.getValue()).booleanValue()) break block50;
                                    throw ex;
                                }
                            }
                        }
                        realJavaEncoding = (String)this.characterEncoding.getValue();
                    }
                    String onServer = this.protocol.getServerSession().getServerVariable("character_set_results");
                    if (characterSetResults.getValue() == null) {
                        if (onServer != null && onServer.length() > 0 && !"NULL".equalsIgnoreCase(onServer)) {
                            block51: {
                                try {
                                    this.sendCommand(this.commandBuilder.buildComQuery(null, "SET character_set_results = NULL"), false, 0);
                                } catch (PasswordExpiredException ex) {
                                    if (!((Boolean)this.disconnectOnExpiredPasswords.getValue()).booleanValue()) break block51;
                                    throw ex;
                                }
                            }
                            this.protocol.getServerSession().getServerVariables().put("local.character_set_results", null);
                        } else {
                            this.protocol.getServerSession().getServerVariables().put("local.character_set_results", onServer);
                        }
                    } else {
                        block52: {
                            if (((Boolean)this.useOldUTF8Behavior.getValue()).booleanValue()) {
                                try {
                                    this.sendCommand(this.commandBuilder.buildComQuery(null, "SET NAMES latin1"), false, 0);
                                    this.protocol.getServerSession().getServerVariables().put("character_set_client", "latin1");
                                    this.protocol.getServerSession().getServerVariables().put("character_set_connection", "latin1");
                                } catch (PasswordExpiredException ex) {
                                    if (!((Boolean)this.disconnectOnExpiredPasswords.getValue()).booleanValue()) break block52;
                                    throw ex;
                                }
                            }
                        }
                        String charsetResults = (String)characterSetResults.getValue();
                        String mysqlEncodingName = null;
                        mysqlEncodingName = "UTF-8".equalsIgnoreCase(charsetResults) || "UTF8".equalsIgnoreCase(charsetResults) ? "utf8" : ("null".equalsIgnoreCase(charsetResults) ? "NULL" : CharsetMapping.getMysqlCharsetForJavaEncoding(charsetResults.toUpperCase(Locale.ENGLISH), this.getServerSession().getServerVersion()));
                        if (mysqlEncodingName == null) {
                            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("Connection.7", new Object[]{charsetResults}), this.getExceptionInterceptor());
                        }
                        if (!mysqlEncodingName.equalsIgnoreCase(this.protocol.getServerSession().getServerVariable("character_set_results"))) {
                            block53: {
                                StringBuilder setBuf = new StringBuilder("SET character_set_results = ".length() + mysqlEncodingName.length());
                                setBuf.append("SET character_set_results = ").append(mysqlEncodingName);
                                try {
                                    this.sendCommand(this.commandBuilder.buildComQuery(null, setBuf.toString()), false, 0);
                                } catch (PasswordExpiredException ex) {
                                    if (!((Boolean)this.disconnectOnExpiredPasswords.getValue()).booleanValue()) break block53;
                                    throw ex;
                                }
                            }
                            this.protocol.getServerSession().getServerVariables().put("local.character_set_results", mysqlEncodingName);
                            this.protocol.getServerSession().setErrorMessageEncoding(charsetResults);
                        } else {
                            this.protocol.getServerSession().getServerVariables().put("local.character_set_results", onServer);
                        }
                    }
                    String connectionCollation = this.getPropertySet().getStringProperty("connectionCollation").getStringValue();
                    if (connectionCollation == null) break block54;
                    StringBuilder setBuf = new StringBuilder("SET collation_connection = ".length() + connectionCollation.length());
                    setBuf.append("SET collation_connection = ").append(connectionCollation);
                    try {
                        this.sendCommand(this.commandBuilder.buildComQuery(null, setBuf.toString()), false, 0);
                    } catch (PasswordExpiredException ex) {
                        if (((Boolean)this.disconnectOnExpiredPasswords.getValue()).booleanValue()) {
                            throw ex;
                        }
                    }
                } finally {
                    this.characterEncoding.setValue(realJavaEncoding);
                }
            }
            try {
                CharsetEncoder enc = Charset.forName((String)this.characterEncoding.getValue()).newEncoder();
                CharBuffer cbuf = CharBuffer.allocate(1);
                ByteBuffer bbuf = ByteBuffer.allocate(1);
                cbuf.put("\u00a5");
                cbuf.position(0);
                enc.encode(cbuf, bbuf, true);
                if (bbuf.get(0) == 92) {
                    this.requiresEscapingEncoder = true;
                } else {
                    cbuf.clear();
                    bbuf.clear();
                    cbuf.put("\u20a9");
                    cbuf.position(0);
                    enc.encode(cbuf, bbuf, true);
                    if (bbuf.get(0) == 92) {
                        this.requiresEscapingEncoder = true;
                    }
                }
            } catch (UnsupportedCharsetException ucex) {
                byte[] bbuf = StringUtils.getBytes("\u00a5", (String)this.characterEncoding.getValue());
                if (bbuf[0] == 92) {
                    this.requiresEscapingEncoder = true;
                }
                bbuf = StringUtils.getBytes("\u20a9", (String)this.characterEncoding.getValue());
                if (bbuf[0] != 92) break block55;
                this.requiresEscapingEncoder = true;
            }
        }
        return characterSetAlreadyConfigured;
    }

    public boolean getRequiresEscapingEncoder() {
        return this.requiresEscapingEncoder;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void createConfigCacheIfNeeded(Object syncMutex) {
        Object object = syncMutex;
        synchronized (object) {
            if (this.serverConfigCache != null) {
                return;
            }
            try {
                Class<?> factoryClass = Class.forName(this.getPropertySet().getStringProperty("serverConfigCacheFactory").getStringValue());
                CacheAdapterFactory cacheFactory = (CacheAdapterFactory)factoryClass.newInstance();
                this.serverConfigCache = cacheFactory.getInstance(syncMutex, this.hostInfo.getDatabaseUrl(), Integer.MAX_VALUE, Integer.MAX_VALUE);
                ExceptionInterceptor evictOnCommsError = new ExceptionInterceptor(){

                    @Override
                    public ExceptionInterceptor init(Properties config, Log log1) {
                        return this;
                    }

                    @Override
                    public void destroy() {
                    }

                    @Override
                    public Exception interceptException(Exception sqlEx) {
                        if (sqlEx instanceof SQLException && ((SQLException)sqlEx).getSQLState() != null && ((SQLException)sqlEx).getSQLState().startsWith("08")) {
                            NativeSession.this.serverConfigCache.invalidate(NativeSession.this.hostInfo.getDatabaseUrl());
                        }
                        return null;
                    }
                };
                if (this.exceptionInterceptor == null) {
                    this.exceptionInterceptor = evictOnCommsError;
                } else {
                    ((ExceptionInterceptorChain)this.exceptionInterceptor).addRingZero(evictOnCommsError);
                }
            } catch (ClassNotFoundException e) {
                throw ExceptionFactory.createException(Messages.getString("Connection.CantFindCacheFactory", new Object[]{this.getPropertySet().getStringProperty("parseInfoCacheFactory").getValue(), "parseInfoCacheFactory"}), e, this.getExceptionInterceptor());
            } catch (CJException | IllegalAccessException | InstantiationException e) {
                throw ExceptionFactory.createException(Messages.getString("Connection.CantLoadCacheFactory", new Object[]{this.getPropertySet().getStringProperty("parseInfoCacheFactory").getValue(), "parseInfoCacheFactory"}), e, this.getExceptionInterceptor());
            }
        }
    }

    public void loadServerVariables(Object syncMutex, String version) {
        if (((Boolean)this.cacheServerConfiguration.getValue()).booleanValue()) {
            this.createConfigCacheIfNeeded(syncMutex);
            Map<String, String> cachedVariableMap = this.serverConfigCache.get(this.hostInfo.getDatabaseUrl());
            if (cachedVariableMap != null) {
                String cachedServerVersion = cachedVariableMap.get(SERVER_VERSION_STRING_VAR_NAME);
                if (cachedServerVersion != null && this.getServerSession().getServerVersion() != null && cachedServerVersion.equals(this.getServerSession().getServerVersion().toString())) {
                    this.protocol.getServerSession().setServerVariables(cachedVariableMap);
                    return;
                }
                this.serverConfigCache.invalidate(this.hostInfo.getDatabaseUrl());
            }
        }
        try {
            if (version != null && version.indexOf(42) != -1) {
                StringBuilder buf = new StringBuilder(version.length() + 10);
                for (int i = 0; i < version.length(); ++i) {
                    char c = version.charAt(i);
                    buf.append(c == '*' ? "[star]" : Character.valueOf(c));
                }
                version = buf.toString();
            }
            String versionComment = this.propertySet.getBooleanProperty("paranoid").getValue() != false || version == null ? "" : "/* " + version + " */";
            this.protocol.getServerSession().setServerVariables(new HashMap<String, String>());
            if (this.versionMeetsMinimum(5, 1, 0)) {
                StringBuilder queryBuf = new StringBuilder(versionComment).append("SELECT");
                queryBuf.append("  @@session.auto_increment_increment AS auto_increment_increment");
                queryBuf.append(", @@character_set_client AS character_set_client");
                queryBuf.append(", @@character_set_connection AS character_set_connection");
                queryBuf.append(", @@character_set_results AS character_set_results");
                queryBuf.append(", @@character_set_server AS character_set_server");
                queryBuf.append(", @@collation_server AS collation_server");
                queryBuf.append(", @@init_connect AS init_connect");
                queryBuf.append(", @@interactive_timeout AS interactive_timeout");
                if (!this.versionMeetsMinimum(5, 5, 0)) {
                    queryBuf.append(", @@language AS language");
                }
                queryBuf.append(", @@license AS license");
                queryBuf.append(", @@lower_case_table_names AS lower_case_table_names");
                queryBuf.append(", @@max_allowed_packet AS max_allowed_packet");
                queryBuf.append(", @@net_write_timeout AS net_write_timeout");
                if (!this.versionMeetsMinimum(8, 0, 3)) {
                    queryBuf.append(", @@query_cache_size AS query_cache_size");
                    queryBuf.append(", @@query_cache_type AS query_cache_type");
                }
                queryBuf.append(", @@sql_mode AS sql_mode");
                queryBuf.append(", @@system_time_zone AS system_time_zone");
                queryBuf.append(", @@time_zone AS time_zone");
                if (this.versionMeetsMinimum(8, 0, 3) || this.versionMeetsMinimum(5, 7, 20) && !this.versionMeetsMinimum(8, 0, 0)) {
                    queryBuf.append(", @@transaction_isolation AS transaction_isolation");
                } else {
                    queryBuf.append(", @@tx_isolation AS transaction_isolation");
                }
                queryBuf.append(", @@wait_timeout AS wait_timeout");
                NativePacketPayload resultPacket = this.sendCommand(this.commandBuilder.buildComQuery(null, queryBuf.toString()), false, 0);
                Resultset rs = ((NativeProtocol)this.protocol).readAllResults(-1, false, resultPacket, false, null, new ResultsetFactory(Resultset.Type.FORWARD_ONLY, null));
                Field[] f = rs.getColumnDefinition().getFields();
                if (f.length > 0) {
                    StringValueFactory vf = new StringValueFactory(f[0].getEncoding());
                    Row r = (Row)rs.getRows().next();
                    if (r != null) {
                        for (int i = 0; i < f.length; ++i) {
                            this.protocol.getServerSession().getServerVariables().put(f[i].getColumnLabel(), r.getValue(i, vf));
                        }
                    }
                }
            } else {
                Row r;
                NativePacketPayload resultPacket = this.sendCommand(this.commandBuilder.buildComQuery(null, versionComment + "SHOW VARIABLES"), false, 0);
                Resultset rs = ((NativeProtocol)this.protocol).readAllResults(-1, false, resultPacket, false, null, new ResultsetFactory(Resultset.Type.FORWARD_ONLY, null));
                StringValueFactory vf = new StringValueFactory(rs.getColumnDefinition().getFields()[0].getEncoding());
                while ((r = (Row)rs.getRows().next()) != null) {
                    this.protocol.getServerSession().getServerVariables().put(r.getValue(0, vf), r.getValue(1, vf));
                }
            }
        } catch (PasswordExpiredException ex) {
            if (((Boolean)this.disconnectOnExpiredPasswords.getValue()).booleanValue()) {
                throw ex;
            }
        } catch (IOException e) {
            throw ExceptionFactory.createException(e.getMessage(), e);
        }
        if (((Boolean)this.cacheServerConfiguration.getValue()).booleanValue()) {
            this.protocol.getServerSession().getServerVariables().put(SERVER_VERSION_STRING_VAR_NAME, this.getServerSession().getServerVersion().toString());
            this.serverConfigCache.put(this.hostInfo.getDatabaseUrl(), this.protocol.getServerSession().getServerVariables());
        }
    }

    public void setSessionVariables() {
        String sessionVariables = this.getPropertySet().getStringProperty("sessionVariables").getValue();
        if (sessionVariables != null) {
            ArrayList<String> variablesToSet = new ArrayList<String>();
            for (String part : StringUtils.split(sessionVariables, ",", "\"'(", "\"')", "\"'", true)) {
                variablesToSet.addAll(StringUtils.split(part, ";", "\"'(", "\"')", "\"'", true));
            }
            if (!variablesToSet.isEmpty()) {
                StringBuilder query = new StringBuilder("SET ");
                String separator = "";
                for (String variableToSet : variablesToSet) {
                    if (variableToSet.length() <= 0) continue;
                    query.append(separator);
                    if (!variableToSet.startsWith("@")) {
                        query.append("SESSION ");
                    }
                    query.append(variableToSet);
                    separator = ",";
                }
                this.sendCommand(this.commandBuilder.buildComQuery(null, query.toString()), false, 0);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void buildCollationMapping() {
        Map<Integer, String> customCharset = null;
        Map<String, Integer> customMblen = null;
        String databaseURL = this.hostInfo.getDatabaseUrl();
        if (((Boolean)this.cacheServerConfiguration.getValue()).booleanValue()) {
            Map<String, Map<Integer, String>> map = customIndexToCharsetMapByUrl;
            synchronized (map) {
                customCharset = customIndexToCharsetMapByUrl.get(databaseURL);
                customMblen = customCharsetToMblenMapByUrl.get(databaseURL);
            }
        }
        if (customCharset == null && this.getPropertySet().getBooleanProperty("detectCustomCollations").getValue().booleanValue()) {
            Resultset rs;
            NativePacketPayload resultPacket;
            customCharset = new HashMap<Integer, String>();
            customMblen = new HashMap<String, Integer>();
            IntegerValueFactory ivf = new IntegerValueFactory();
            try {
                Row r;
                resultPacket = this.sendCommand(this.commandBuilder.buildComQuery(null, "SHOW COLLATION"), false, 0);
                rs = ((NativeProtocol)this.protocol).readAllResults(-1, false, resultPacket, false, null, new ResultsetFactory(Resultset.Type.FORWARD_ONLY, null));
                StringValueFactory svf = new StringValueFactory(rs.getColumnDefinition().getFields()[1].getEncoding());
                while ((r = (Row)rs.getRows().next()) != null) {
                    int collationIndex = ((Number)r.getValue(2, ivf)).intValue();
                    String charsetName = r.getValue(1, svf);
                    if (collationIndex >= 2048 || !charsetName.equals(CharsetMapping.getMysqlCharsetNameForCollationIndex(collationIndex))) {
                        customCharset.put(collationIndex, charsetName);
                    }
                    if (CharsetMapping.CHARSET_NAME_TO_CHARSET.containsKey(charsetName)) continue;
                    customMblen.put(charsetName, null);
                }
            } catch (PasswordExpiredException ex) {
                if (((Boolean)this.disconnectOnExpiredPasswords.getValue()).booleanValue()) {
                    throw ex;
                }
            } catch (IOException e) {
                throw ExceptionFactory.createException(e.getMessage(), e, this.exceptionInterceptor);
            }
            if (customMblen.size() > 0) {
                try {
                    Row r;
                    resultPacket = this.sendCommand(this.commandBuilder.buildComQuery(null, "SHOW CHARACTER SET"), false, 0);
                    rs = ((NativeProtocol)this.protocol).readAllResults(-1, false, resultPacket, false, null, new ResultsetFactory(Resultset.Type.FORWARD_ONLY, null));
                    int charsetColumn = rs.getColumnDefinition().getColumnNameToIndex().get("Charset");
                    int maxlenColumn = rs.getColumnDefinition().getColumnNameToIndex().get("Maxlen");
                    StringValueFactory svf = new StringValueFactory(rs.getColumnDefinition().getFields()[1].getEncoding());
                    while ((r = (Row)rs.getRows().next()) != null) {
                        String charsetName = r.getValue(charsetColumn, svf);
                        if (!customMblen.containsKey(charsetName)) continue;
                        customMblen.put(charsetName, r.getValue(maxlenColumn, ivf));
                    }
                } catch (PasswordExpiredException ex) {
                    if (((Boolean)this.disconnectOnExpiredPasswords.getValue()).booleanValue()) {
                        throw ex;
                    }
                } catch (IOException e) {
                    throw ExceptionFactory.createException(e.getMessage(), e, this.exceptionInterceptor);
                }
            }
            if (((Boolean)this.cacheServerConfiguration.getValue()).booleanValue()) {
                Map<String, Map<Integer, String>> e = customIndexToCharsetMapByUrl;
                synchronized (e) {
                    customIndexToCharsetMapByUrl.put(databaseURL, customCharset);
                    customCharsetToMblenMapByUrl.put(databaseURL, customMblen);
                }
            }
        }
        if (customCharset != null) {
            ((NativeServerSession)this.protocol.getServerSession()).indexToCustomMysqlCharset = Collections.unmodifiableMap(customCharset);
        }
        if (customMblen != null) {
            ((NativeServerSession)this.protocol.getServerSession()).mysqlCharsetToCustomMblen = Collections.unmodifiableMap(customMblen);
        }
        if (this.protocol.getServerSession().getServerDefaultCollationIndex() == 0) {
            String collationServer = this.protocol.getServerSession().getServerVariable("collation_server");
            if (collationServer != null) {
                for (int i = 1; i < CharsetMapping.COLLATION_INDEX_TO_COLLATION_NAME.length; ++i) {
                    if (!CharsetMapping.COLLATION_INDEX_TO_COLLATION_NAME[i].equals(collationServer)) continue;
                    this.protocol.getServerSession().setServerDefaultCollationIndex(i);
                    break;
                }
            } else {
                this.protocol.getServerSession().setServerDefaultCollationIndex(45);
            }
        }
    }

    @Override
    public String getProcessHost() {
        try {
            long threadId = this.getThreadId();
            String processHost = this.findProcessHost(threadId);
            if (processHost == null) {
                this.log.logWarn(String.format("Connection id %d not found in \"SHOW PROCESSLIST\", assuming 32-bit overflow, using SELECT CONNECTION_ID() instead", threadId));
                NativePacketPayload resultPacket = this.sendCommand(this.commandBuilder.buildComQuery(null, "SELECT CONNECTION_ID()"), false, 0);
                Resultset rs = ((NativeProtocol)this.protocol).readAllResults(-1, false, resultPacket, false, null, new ResultsetFactory(Resultset.Type.FORWARD_ONLY, null));
                LongValueFactory lvf = new LongValueFactory();
                Row r = (Row)rs.getRows().next();
                if (r != null) {
                    threadId = r.getValue(0, lvf);
                    processHost = this.findProcessHost(threadId);
                } else {
                    this.log.logError("No rows returned for statement \"SELECT CONNECTION_ID()\", local connection check will most likely be incorrect");
                }
            }
            if (processHost == null) {
                this.log.logWarn(String.format("Cannot find process listing for connection %d in SHOW PROCESSLIST output, unable to determine if locally connected", threadId));
            }
            return processHost;
        } catch (IOException e) {
            throw ExceptionFactory.createException(e.getMessage(), e);
        }
    }

    private String findProcessHost(long threadId) {
        try {
            Row r;
            String processHost = null;
            NativePacketPayload resultPacket = this.sendCommand(this.commandBuilder.buildComQuery(null, "SHOW PROCESSLIST"), false, 0);
            Resultset rs = ((NativeProtocol)this.protocol).readAllResults(-1, false, resultPacket, false, null, new ResultsetFactory(Resultset.Type.FORWARD_ONLY, null));
            LongValueFactory lvf = new LongValueFactory();
            StringValueFactory svf = new StringValueFactory(rs.getColumnDefinition().getFields()[2].getEncoding());
            while ((r = (Row)rs.getRows().next()) != null) {
                long id = r.getValue(0, lvf);
                if (threadId != id) continue;
                processHost = r.getValue(2, svf);
                break;
            }
            return processHost;
        } catch (IOException e) {
            throw ExceptionFactory.createException(e.getMessage(), e);
        }
    }

    public String queryServerVariable(String varName) {
        try {
            String s2;
            NativePacketPayload resultPacket = this.sendCommand(this.commandBuilder.buildComQuery(null, "SELECT " + varName), false, 0);
            Resultset rs = ((NativeProtocol)this.protocol).readAllResults(-1, false, resultPacket, false, null, new ResultsetFactory(Resultset.Type.FORWARD_ONLY, null));
            StringValueFactory svf = new StringValueFactory(rs.getColumnDefinition().getFields()[0].getEncoding());
            Row r = (Row)rs.getRows().next();
            if (r != null && (s2 = r.getValue(0, svf)) != null) {
                return s2;
            }
            return null;
        } catch (IOException e) {
            throw ExceptionFactory.createException(e.getMessage(), e);
        }
    }

    public <T extends Resultset> T execSQL(Query callingQuery, String query, int maxRows, NativePacketPayload packet, boolean streamResults, ProtocolEntityFactory<T, NativePacketPayload> resultSetFactory, String catalog, ColumnDefinition cachedMetadata, boolean isBatch) {
        long queryStartTime = 0L;
        int endOfQueryPacketPosition = 0;
        if (packet != null) {
            endOfQueryPacketPosition = packet.getPosition();
        }
        if (((Boolean)this.gatherPerfMetrics.getValue()).booleanValue()) {
            queryStartTime = System.currentTimeMillis();
        }
        this.lastQueryFinishedTime = 0L;
        if (((Boolean)this.autoReconnect.getValue()).booleanValue() && (this.getServerSession().isAutoCommit() || ((Boolean)this.autoReconnectForPools.getValue()).booleanValue()) && this.needsPing && !isBatch) {
            try {
                this.ping(false, 0);
                this.needsPing = false;
            } catch (Exception Ex) {
                this.invokeReconnectListeners();
            }
        }
        try {
            if (packet == null) {
                String encoding = (String)this.characterEncoding.getValue();
                T t = ((NativeProtocol)this.protocol).sendQueryString(callingQuery, query, encoding, maxRows, streamResults, catalog, cachedMetadata, this::getProfilerEventHandlerInstanceFunction, resultSetFactory);
                return t;
            }
            T encoding = ((NativeProtocol)this.protocol).sendQueryPacket(callingQuery, packet, maxRows, streamResults, catalog, cachedMetadata, this::getProfilerEventHandlerInstanceFunction, resultSetFactory);
            return encoding;
        } catch (CJException sqlE) {
            if (this.getPropertySet().getBooleanProperty("dumpQueriesOnException").getValue().booleanValue()) {
                String extractedSql = NativePacketPayload.extractSqlFromPacket(query, packet, endOfQueryPacketPosition, this.getPropertySet().getIntegerProperty("maxQuerySizeToLog").getValue());
                StringBuilder messageBuf = new StringBuilder(extractedSql.length() + 32);
                messageBuf.append("\n\nQuery being executed when exception was thrown:\n");
                messageBuf.append(extractedSql);
                messageBuf.append("\n\n");
                sqlE.appendMessage(messageBuf.toString());
            }
            if (((Boolean)this.autoReconnect.getValue()).booleanValue()) {
                if (sqlE instanceof CJCommunicationsException) {
                    this.protocol.getSocketConnection().forceClose();
                }
                this.needsPing = true;
            } else if (sqlE instanceof CJCommunicationsException) {
                this.invokeCleanupListeners(sqlE);
            }
            throw sqlE;
        } catch (Throwable ex) {
            if (((Boolean)this.autoReconnect.getValue()).booleanValue()) {
                if (ex instanceof IOException) {
                    this.protocol.getSocketConnection().forceClose();
                } else if (ex instanceof IOException) {
                    this.invokeCleanupListeners(ex);
                }
                this.needsPing = true;
            }
            throw ExceptionFactory.createException(ex.getMessage(), ex, this.exceptionInterceptor);
        } finally {
            if (((Boolean)this.maintainTimeStats.getValue()).booleanValue()) {
                this.lastQueryFinishedTime = System.currentTimeMillis();
            }
            if (((Boolean)this.gatherPerfMetrics.getValue()).booleanValue()) {
                long queryTime = System.currentTimeMillis() - queryStartTime;
                this.registerQueryExecutionTime(queryTime);
            }
        }
    }

    public long getIdleFor() {
        if (this.lastQueryFinishedTime == 0L) {
            return 0L;
        }
        long now = System.currentTimeMillis();
        long idleTime = now - this.lastQueryFinishedTime;
        return idleTime;
    }

    public boolean isNeedsPing() {
        return this.needsPing;
    }

    public void setNeedsPing(boolean needsPing) {
        this.needsPing = needsPing;
    }

    public void ping(boolean checkForClosedConnection, int timeoutMillis) {
        if (checkForClosedConnection) {
            this.checkClosed();
        }
        long pingMillisLifetime = this.getPropertySet().getIntegerProperty("selfDestructOnPingSecondsLifetime").getValue().intValue();
        int pingMaxOperations = this.getPropertySet().getIntegerProperty("selfDestructOnPingMaxOperations").getValue();
        if (pingMillisLifetime > 0L && System.currentTimeMillis() - this.connectionCreationTimeMillis > pingMillisLifetime || pingMaxOperations > 0 && pingMaxOperations <= this.getCommandCount()) {
            this.invokeNormalCloseListeners();
            throw ExceptionFactory.createException(Messages.getString("Connection.exceededConnectionLifetime"), "08S01", 0, false, null, this.exceptionInterceptor);
        }
        this.sendCommand(this.commandBuilder.buildComPing(null), false, timeoutMillis);
    }

    public long getConnectionCreationTimeMillis() {
        return this.connectionCreationTimeMillis;
    }

    public void setConnectionCreationTimeMillis(long connectionCreationTimeMillis) {
        this.connectionCreationTimeMillis = connectionCreationTimeMillis;
    }

    @Override
    public boolean isClosed() {
        return this.isClosed;
    }

    public void checkClosed() {
        if (this.isClosed) {
            if (this.forceClosedReason != null && this.forceClosedReason.getClass().equals(OperationCancelledException.class)) {
                throw (OperationCancelledException)this.forceClosedReason;
            }
            throw ExceptionFactory.createException(ConnectionIsClosedException.class, Messages.getString("Connection.2"), this.forceClosedReason, this.getExceptionInterceptor());
        }
    }

    public Throwable getForceClosedReason() {
        return this.forceClosedReason;
    }

    public void setForceClosedReason(Throwable forceClosedReason) {
        this.forceClosedReason = forceClosedReason;
    }

    @Override
    public void addListener(Session.SessionEventListener l) {
        this.listeners.addIfAbsent(new WeakReference<Session.SessionEventListener>(l));
    }

    @Override
    public void removeListener(Session.SessionEventListener listener) {
        for (WeakReference<Session.SessionEventListener> wr : this.listeners) {
            Session.SessionEventListener l = (Session.SessionEventListener)wr.get();
            if (l != listener) continue;
            this.listeners.remove(wr);
            break;
        }
    }

    protected void invokeNormalCloseListeners() {
        for (WeakReference<Session.SessionEventListener> wr : this.listeners) {
            Session.SessionEventListener l = (Session.SessionEventListener)wr.get();
            if (l != null) {
                l.handleNormalClose();
                continue;
            }
            this.listeners.remove(wr);
        }
    }

    protected void invokeReconnectListeners() {
        for (WeakReference<Session.SessionEventListener> wr : this.listeners) {
            Session.SessionEventListener l = (Session.SessionEventListener)wr.get();
            if (l != null) {
                l.handleReconnect();
                continue;
            }
            this.listeners.remove(wr);
        }
    }

    public void invokeCleanupListeners(Throwable whyCleanedUp) {
        for (WeakReference<Session.SessionEventListener> wr : this.listeners) {
            Session.SessionEventListener l = (Session.SessionEventListener)wr.get();
            if (l != null) {
                l.handleCleanup(whyCleanedUp);
                continue;
            }
            this.listeners.remove(wr);
        }
    }

    @Override
    public String getIdentifierQuoteString() {
        return this.protocol != null && this.protocol.getServerSession().useAnsiQuotedIdentifiers() ? "\"" : "`";
    }

    public synchronized Timer getCancelTimer() {
        if (this.cancelTimer == null) {
            this.cancelTimer = new Timer("MySQL Statement Cancellation Timer", Boolean.TRUE);
        }
        return this.cancelTimer;
    }

    @Override
    public <M extends Message, RES_T, R> RES_T query(M message, Predicate<Row> filterRow, Function<Row, R> mapRow, Collector<R, ?, RES_T> collector) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }
}

