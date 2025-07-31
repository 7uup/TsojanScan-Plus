/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.MessageBuilder;
import com.mysql.cj.QueryResult;
import com.mysql.cj.Session;
import com.mysql.cj.TransactionEventHandler;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.log.ProfilerEventHandler;
import com.mysql.cj.protocol.AuthenticationProvider;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.PacketReceivedTimeHolder;
import com.mysql.cj.protocol.PacketSentTimeHolder;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.ResultStreamer;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.ServerCapabilities;
import com.mysql.cj.protocol.ServerSession;
import com.mysql.cj.protocol.SocketConnection;
import com.mysql.cj.result.RowList;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;

public interface Protocol<M extends Message> {
    public void init(Session var1, SocketConnection var2, PropertySet var3, TransactionEventHandler var4);

    public PropertySet getPropertySet();

    public void setPropertySet(PropertySet var1);

    public MessageBuilder<M> getMessageBuilder();

    public ServerCapabilities readServerCapabilities();

    public ServerSession getServerSession();

    public SocketConnection getSocketConnection();

    public AuthenticationProvider<M> getAuthenticationProvider();

    public ExceptionInterceptor getExceptionInterceptor();

    public PacketSentTimeHolder getPacketSentTimeHolder();

    public void setPacketSentTimeHolder(PacketSentTimeHolder var1);

    public PacketReceivedTimeHolder getPacketReceivedTimeHolder();

    public void setPacketReceivedTimeHolder(PacketReceivedTimeHolder var1);

    public void connect(String var1, String var2, String var3);

    public void negotiateSSLConnection(int var1);

    public void beforeHandshake();

    public void afterHandshake();

    public void changeDatabase(String var1);

    public void changeUser(String var1, String var2, String var3);

    public String getPasswordCharacterEncoding();

    public boolean versionMeetsMinimum(int var1, int var2, int var3);

    public M readMessage(M var1);

    public M checkErrorMessage();

    public void send(Message var1, int var2);

    public <RES extends QueryResult> CompletableFuture<RES> sendAsync(Message var1);

    public ColumnDefinition readMetadata();

    public RowList getRowInputStream(ColumnDefinition var1);

    public M sendCommand(Message var1, boolean var2, int var3);

    public <T extends ProtocolEntity> T read(Class<T> var1, ProtocolEntityFactory<T, M> var2) throws IOException;

    public <T extends ProtocolEntity> T read(Class<Resultset> var1, int var2, boolean var3, M var4, boolean var5, ColumnDefinition var6, ProtocolEntityFactory<T, M> var7) throws IOException;

    public void setLocalInfileInputStream(InputStream var1);

    public InputStream getLocalInfileInputStream();

    public String getQueryComment();

    public void setQueryComment(String var1);

    public <QR extends QueryResult> QR readQueryResult();

    public void close() throws IOException;

    public void setCurrentResultStreamer(ResultStreamer var1);

    public void configureTimezone();

    public void initServerSession();

    @FunctionalInterface
    public static interface GetProfilerEventHandlerInstanceFunction {
        public ProfilerEventHandler apply();
    }
}

