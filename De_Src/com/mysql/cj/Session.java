/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj;

import com.mysql.cj.DataStoreMetadata;
import com.mysql.cj.MessageBuilder;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.log.Log;
import com.mysql.cj.log.ProfilerEventHandler;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.ServerSession;
import com.mysql.cj.result.Row;
import java.net.SocketAddress;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;

public interface Session {
    public PropertySet getPropertySet();

    public <M extends Message> MessageBuilder<M> getMessageBuilder();

    public void changeUser(String var1, String var2, String var3);

    public ExceptionInterceptor getExceptionInterceptor();

    public void setExceptionInterceptor(ExceptionInterceptor var1);

    public void quit();

    public void forceClose();

    public boolean versionMeetsMinimum(int var1, int var2, int var3);

    public long getThreadId();

    public boolean isSetNeededForAutoCommitMode(boolean var1);

    public Log getLog();

    public ProfilerEventHandler getProfilerEventHandler();

    public void setProfilerEventHandler(ProfilerEventHandler var1);

    public ServerSession getServerSession();

    public boolean isSSLEstablished();

    public SocketAddress getRemoteSocketAddress();

    public String getProcessHost();

    public void addListener(SessionEventListener var1);

    public void removeListener(SessionEventListener var1);

    public boolean isClosed();

    public String getIdentifierQuoteString();

    public DataStoreMetadata getDataStoreMetadata();

    public <M extends Message, RES_T, R> RES_T query(M var1, Predicate<Row> var2, Function<Row, R> var3, Collector<R, ?, RES_T> var4);

    public static interface SessionEventListener {
        public void handleNormalClose();

        public void handleReconnect();

        public void handleCleanup(Throwable var1);
    }
}

