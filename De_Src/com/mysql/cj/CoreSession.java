/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj;

import com.mysql.cj.DataStoreMetadata;
import com.mysql.cj.DataStoreMetadataImpl;
import com.mysql.cj.MessageBuilder;
import com.mysql.cj.QueryResult;
import com.mysql.cj.Session;
import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.CJOperationNotSupportedException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.log.Log;
import com.mysql.cj.log.LogFactory;
import com.mysql.cj.log.NullLogger;
import com.mysql.cj.log.ProfilerEventHandler;
import com.mysql.cj.log.ProfilerEventHandlerFactory;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.Protocol;
import com.mysql.cj.protocol.ServerSession;
import com.mysql.cj.result.Row;
import com.mysql.cj.result.RowList;
import java.net.SocketAddress;
import java.util.Spliterators;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class CoreSession
implements Session {
    protected PropertySet propertySet;
    protected ExceptionInterceptor exceptionInterceptor;
    protected transient Log log;
    protected static final Log NULL_LOGGER = new NullLogger("MySQL");
    protected transient Protocol<? extends Message> protocol;
    protected MessageBuilder<? extends Message> messageBuilder;
    protected long connectionCreationTimeMillis = System.currentTimeMillis();
    protected HostInfo hostInfo = null;
    protected RuntimeProperty<Boolean> gatherPerfMetrics;
    protected RuntimeProperty<String> characterEncoding;
    protected RuntimeProperty<Boolean> useOldUTF8Behavior;
    protected RuntimeProperty<Boolean> disconnectOnExpiredPasswords;
    protected RuntimeProperty<Boolean> cacheServerConfiguration;
    protected RuntimeProperty<Boolean> autoReconnect;
    protected RuntimeProperty<Boolean> autoReconnectForPools;
    protected RuntimeProperty<Boolean> maintainTimeStats;
    protected int sessionMaxRows = -1;
    private ProfilerEventHandler eventSink;

    public CoreSession(HostInfo hostInfo, PropertySet propSet) {
        this.hostInfo = hostInfo;
        this.propertySet = propSet;
        this.gatherPerfMetrics = this.getPropertySet().getBooleanProperty("gatherPerfMetrics");
        this.characterEncoding = this.getPropertySet().getStringProperty("characterEncoding");
        this.useOldUTF8Behavior = this.getPropertySet().getBooleanProperty("useOldUTF8Behavior");
        this.disconnectOnExpiredPasswords = this.getPropertySet().getBooleanProperty("disconnectOnExpiredPasswords");
        this.cacheServerConfiguration = this.getPropertySet().getBooleanProperty("cacheServerConfiguration");
        this.autoReconnect = this.getPropertySet().getBooleanProperty("autoReconnect");
        this.autoReconnectForPools = this.getPropertySet().getBooleanProperty("autoReconnectForPools");
        this.maintainTimeStats = this.getPropertySet().getBooleanProperty("maintainTimeStats");
        this.log = LogFactory.getLogger(this.getPropertySet().getStringProperty("logger").getStringValue(), "MySQL");
        if (this.getPropertySet().getBooleanProperty("profileSQL").getValue().booleanValue() || this.getPropertySet().getBooleanProperty("useUsageAdvisor").getValue().booleanValue()) {
            ProfilerEventHandlerFactory.getInstance(this);
        }
    }

    @Override
    public void changeUser(String user, String password, String database) {
        this.sessionMaxRows = -1;
        this.protocol.changeUser(user, password, database);
    }

    @Override
    public PropertySet getPropertySet() {
        return this.propertySet;
    }

    @Override
    public ExceptionInterceptor getExceptionInterceptor() {
        return this.exceptionInterceptor;
    }

    @Override
    public void setExceptionInterceptor(ExceptionInterceptor exceptionInterceptor) {
        this.exceptionInterceptor = exceptionInterceptor;
    }

    @Override
    public Log getLog() {
        return this.log;
    }

    @Override
    public <M extends Message> MessageBuilder<M> getMessageBuilder() {
        return this.messageBuilder;
    }

    public <QR extends QueryResult> QR sendMessage(Message message) {
        this.protocol.send(message, 0);
        return this.protocol.readQueryResult();
    }

    public <QR extends QueryResult> CompletableFuture<QR> asyncSendMessage(Message message) {
        return this.protocol.sendAsync(message);
    }

    @Override
    public <M extends Message, RES_T, R> RES_T query(M message, Predicate<Row> filterRow, Function<Row, R> mapRow, Collector<R, ?, RES_T> collector) {
        this.protocol.send(message, 0);
        ColumnDefinition metadata = this.protocol.readMetadata();
        RowList ris = this.protocol.getRowInputStream(metadata);
        Stream<Row> stream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(ris, 0), false);
        if (filterRow != null) {
            stream = stream.filter(filterRow);
        }
        RES_T result = stream.map(mapRow).collect(collector);
        this.protocol.readQueryResult();
        return result;
    }

    @Override
    public ServerSession getServerSession() {
        return this.protocol.getServerSession();
    }

    @Override
    public boolean versionMeetsMinimum(int major, int minor, int subminor) {
        return this.protocol.versionMeetsMinimum(major, minor, subminor);
    }

    @Override
    public long getThreadId() {
        return this.protocol.getServerSession().getThreadId();
    }

    @Override
    public void forceClose() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public boolean isSetNeededForAutoCommitMode(boolean autoCommitFlag) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public ProfilerEventHandler getProfilerEventHandler() {
        return this.eventSink;
    }

    @Override
    public void setProfilerEventHandler(ProfilerEventHandler h2) {
        this.eventSink = h2;
    }

    @Override
    public boolean isSSLEstablished() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public SocketAddress getRemoteSocketAddress() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public void addListener(Session.SessionEventListener l) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public void removeListener(Session.SessionEventListener l) {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public String getIdentifierQuoteString() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }

    @Override
    public DataStoreMetadata getDataStoreMetadata() {
        return new DataStoreMetadataImpl(this);
    }
}

