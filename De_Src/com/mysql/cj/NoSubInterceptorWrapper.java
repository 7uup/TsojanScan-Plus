/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj;

import com.mysql.cj.Messages;
import com.mysql.cj.MysqlConnection;
import com.mysql.cj.Query;
import com.mysql.cj.interceptors.QueryInterceptor;
import com.mysql.cj.log.Log;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.ServerSession;
import java.util.Properties;
import java.util.function.Supplier;

public class NoSubInterceptorWrapper
implements QueryInterceptor {
    private final QueryInterceptor underlyingInterceptor;

    public NoSubInterceptorWrapper(QueryInterceptor underlyingInterceptor) {
        if (underlyingInterceptor == null) {
            throw new RuntimeException(Messages.getString("NoSubInterceptorWrapper.0"));
        }
        this.underlyingInterceptor = underlyingInterceptor;
    }

    @Override
    public void destroy() {
        this.underlyingInterceptor.destroy();
    }

    @Override
    public boolean executeTopLevelOnly() {
        return this.underlyingInterceptor.executeTopLevelOnly();
    }

    @Override
    public QueryInterceptor init(MysqlConnection conn, Properties props, Log log) {
        this.underlyingInterceptor.init(conn, props, log);
        return this;
    }

    @Override
    public <T extends Resultset> T postProcess(Supplier<String> sql, Query interceptedQuery, T originalResultSet, ServerSession serverSession) {
        this.underlyingInterceptor.postProcess(sql, interceptedQuery, originalResultSet, serverSession);
        return null;
    }

    @Override
    public <T extends Resultset> T preProcess(Supplier<String> sql, Query interceptedQuery) {
        this.underlyingInterceptor.preProcess(sql, interceptedQuery);
        return null;
    }

    @Override
    public <M extends Message> M preProcess(M queryPacket) {
        this.underlyingInterceptor.preProcess(queryPacket);
        return null;
    }

    @Override
    public <M extends Message> M postProcess(M queryPacket, M originalResponsePacket) {
        this.underlyingInterceptor.postProcess(queryPacket, originalResponsePacket);
        return null;
    }

    public QueryInterceptor getUnderlyingInterceptor() {
        return this.underlyingInterceptor;
    }
}

