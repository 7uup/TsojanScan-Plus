/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.protocol.StandardSocketFactory;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.util.Properties;

public class SocksProxySocketFactory
extends StandardSocketFactory {
    public static int SOCKS_DEFAULT_PORT = 1080;

    @Override
    protected Socket createSocket(Properties props) {
        String socksProxyHost = props.getProperty("socksProxyHost");
        String socksProxyPortString = props.getProperty("socksProxyPort", String.valueOf(SOCKS_DEFAULT_PORT));
        int socksProxyPort = SOCKS_DEFAULT_PORT;
        try {
            socksProxyPort = Integer.valueOf(socksProxyPortString);
        } catch (NumberFormatException numberFormatException) {
            // empty catch block
        }
        return new Socket(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(socksProxyHost, socksProxyPort)));
    }
}

