/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.protocol.ExportControlled;
import com.mysql.cj.protocol.ServerSession;
import com.mysql.cj.protocol.SocketConnection;
import com.mysql.cj.protocol.SocketFactory;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketOption;
import java.net.StandardSocketOptions;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncSocketFactory
implements SocketFactory {
    AsynchronousSocketChannel channel;

    @Override
    public <T extends Closeable> T connect(String host, int port, Properties props, int loginTimeout) throws IOException {
        try {
            this.channel = AsynchronousSocketChannel.open();
            this.channel.setOption((SocketOption)StandardSocketOptions.SO_SNDBUF, (Object)131072);
            this.channel.setOption((SocketOption)StandardSocketOptions.SO_RCVBUF, (Object)131072);
            Future<Void> connectPromise = this.channel.connect(new InetSocketAddress(host, port));
            connectPromise.get();
        } catch (CJCommunicationsException e) {
            throw e;
        } catch (IOException | InterruptedException | RuntimeException | ExecutionException ex) {
            throw new CJCommunicationsException(ex);
        }
        return (T)this.channel;
    }

    @Override
    public <T extends Closeable> T performTlsHandshake(SocketConnection socketConnection, ServerSession serverSession) throws IOException {
        this.channel = ExportControlled.startTlsOnAsynchronousChannel(this.channel, socketConnection);
        return (T)this.channel;
    }
}

