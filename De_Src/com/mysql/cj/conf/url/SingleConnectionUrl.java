/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.conf.url;

import com.mysql.cj.conf.ConnectionUrl;
import com.mysql.cj.conf.ConnectionUrlParser;
import java.util.Properties;

public class SingleConnectionUrl
extends ConnectionUrl {
    public SingleConnectionUrl(ConnectionUrlParser connStrParser, Properties info) {
        super(connStrParser, info);
        this.type = ConnectionUrl.Type.SINGLE_CONNECTION;
    }
}

