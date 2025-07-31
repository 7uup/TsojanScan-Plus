/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.jdbc;

import com.mysql.cj.protocol.ServerSession;
import com.mysql.cj.protocol.SocketConnection;
import com.mysql.cj.protocol.StandardSocketFactory;
import com.mysql.jdbc.SocketFactory;
import java.io.Closeable;
import java.io.IOException;
import java.util.Properties;

public class SocketFactoryWrapper
extends StandardSocketFactory
implements com.mysql.cj.protocol.SocketFactory {
    SocketFactory socketFactory;

    public SocketFactoryWrapper(Object legacyFactory) {
        this.socketFactory = (SocketFactory)legacyFactory;
    }

    @Override
    public <T extends Closeable> T connect(String hostname, int portNumber, Properties props, int loginTimeout) throws IOException {
        this.rawSocket = this.socketFactory.connect(hostname, portNumber, props);
        return (T)this.rawSocket;
    }

    @Override
    public <T extends Closeable> T performTlsHandshake(SocketConnection socketConnection, ServerSession serverSession) throws IOException {
        return super.performTlsHandshake(socketConnection, serverSession);
    }

    @Override
    public void beforeHandshake() throws IOException {
        this.socketFactory.beforeHandshake();
    }

    @Override
    public void afterHandshake() throws IOException {
        this.socketFactory.afterHandshake();
    }
}

