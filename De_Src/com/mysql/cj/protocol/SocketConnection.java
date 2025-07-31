/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.exceptions.FeatureNotAvailableException;
import com.mysql.cj.exceptions.SSLParamsException;
import com.mysql.cj.log.Log;
import com.mysql.cj.protocol.FullReadInputStream;
import com.mysql.cj.protocol.NetworkResources;
import com.mysql.cj.protocol.ServerSession;
import com.mysql.cj.protocol.SocketFactory;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.channels.AsynchronousSocketChannel;

public interface SocketConnection {
    public void connect(String var1, int var2, PropertySet var3, ExceptionInterceptor var4, Log var5, int var6);

    public void performTlsHandshake(ServerSession var1) throws SSLParamsException, FeatureNotAvailableException, IOException;

    public void forceClose();

    public NetworkResources getNetworkResources();

    public String getHost();

    public int getPort();

    public Socket getMysqlSocket();

    public FullReadInputStream getMysqlInput();

    public void setMysqlInput(InputStream var1);

    public BufferedOutputStream getMysqlOutput();

    public boolean isSSLEstablished();

    public SocketFactory getSocketFactory();

    public void setSocketFactory(SocketFactory var1);

    public ExceptionInterceptor getExceptionInterceptor();

    public PropertySet getPropertySet();

    default public boolean isSynchronous() {
        return true;
    }

    public AsynchronousSocketChannel getAsynchronousSocketChannel();
}

