/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a;

import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.CJOperationNotSupportedException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.exceptions.FeatureNotAvailableException;
import com.mysql.cj.exceptions.SSLParamsException;
import com.mysql.cj.log.Log;
import com.mysql.cj.protocol.AbstractSocketConnection;
import com.mysql.cj.protocol.FullReadInputStream;
import com.mysql.cj.protocol.PacketSentTimeHolder;
import com.mysql.cj.protocol.ReadAheadInputStream;
import com.mysql.cj.protocol.ServerSession;
import com.mysql.cj.protocol.SocketConnection;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.channels.AsynchronousSocketChannel;

public class NativeSocketConnection
extends AbstractSocketConnection
implements SocketConnection {
    @Override
    public void connect(String hostName, int portNumber, PropertySet propSet, ExceptionInterceptor excInterceptor, Log log, int loginTimeout) {
        try {
            this.port = portNumber;
            this.host = hostName;
            this.propertySet = propSet;
            this.exceptionInterceptor = excInterceptor;
            this.socketFactory = this.createSocketFactory(propSet.getStringProperty("socketFactory").getStringValue());
            this.mysqlSocket = (Socket)this.socketFactory.connect(this.host, this.port, propSet.exposeAsProperties(), loginTimeout);
            int socketTimeout = propSet.getIntegerProperty("socketTimeout").getValue();
            if (socketTimeout != 0) {
                try {
                    this.mysqlSocket.setSoTimeout(socketTimeout);
                } catch (Exception exception) {
                    // empty catch block
                }
            }
            this.socketFactory.beforeHandshake();
            InputStream rawInputStream = propSet.getBooleanProperty("useReadAheadInput").getValue() != false ? new ReadAheadInputStream(this.mysqlSocket.getInputStream(), 16384, propSet.getBooleanProperty("traceProtocol").getValue(), log) : (propSet.getBooleanProperty("useUnbufferedInput").getValue() != false ? this.mysqlSocket.getInputStream() : new BufferedInputStream(this.mysqlSocket.getInputStream(), 16384));
            this.mysqlInput = new FullReadInputStream(rawInputStream);
            this.mysqlOutput = new BufferedOutputStream(this.mysqlSocket.getOutputStream(), 16384);
        } catch (IOException ioEx) {
            throw ExceptionFactory.createCommunicationsException(propSet, null, new PacketSentTimeHolder(){}, null, ioEx, this.getExceptionInterceptor());
        }
    }

    @Override
    public void performTlsHandshake(ServerSession serverSession) throws SSLParamsException, FeatureNotAvailableException, IOException {
        this.mysqlSocket = (Socket)this.socketFactory.performTlsHandshake(this, serverSession);
        this.mysqlInput = new FullReadInputStream(this.propertySet.getBooleanProperty("useUnbufferedInput").getValue() != false ? this.getMysqlSocket().getInputStream() : new BufferedInputStream(this.getMysqlSocket().getInputStream(), 16384));
        this.mysqlOutput = new BufferedOutputStream(this.getMysqlSocket().getOutputStream(), 16384);
        this.mysqlOutput.flush();
    }

    @Override
    public AsynchronousSocketChannel getAsynchronousSocketChannel() {
        throw ExceptionFactory.createException(CJOperationNotSupportedException.class, "Not supported");
    }
}

