/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.protocol.ServerSession;
import com.mysql.cj.protocol.SocketConnection;
import com.mysql.cj.protocol.SocketMetadata;
import java.io.Closeable;
import java.io.IOException;
import java.util.Properties;

public interface SocketFactory
extends SocketMetadata {
    public static final String TCP_KEEP_ALIVE_DEFAULT_VALUE = "true";
    public static final String TCP_RCV_BUF_DEFAULT_VALUE = "0";
    public static final String TCP_SND_BUF_DEFAULT_VALUE = "0";
    public static final String TCP_TRAFFIC_CLASS_DEFAULT_VALUE = "0";
    public static final String TCP_NO_DELAY_DEFAULT_VALUE = "true";

    public <T extends Closeable> T connect(String var1, int var2, Properties var3, int var4) throws IOException;

    default public void beforeHandshake() throws IOException {
    }

    public <T extends Closeable> T performTlsHandshake(SocketConnection var1, ServerSession var2) throws IOException;

    default public void afterHandshake() throws IOException {
    }
}

