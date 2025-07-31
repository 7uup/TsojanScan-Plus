/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj;

import com.mysql.cj.ParseInfo;
import com.mysql.cj.Query;
import com.mysql.cj.QueryBindings;
import com.mysql.cj.protocol.Message;

public interface PreparedQuery<T extends QueryBindings<?>>
extends Query {
    public ParseInfo getParseInfo();

    public void setParseInfo(ParseInfo var1);

    public void checkNullOrEmptyQuery(String var1);

    public String getOriginalSql();

    public void setOriginalSql(String var1);

    public int getParameterCount();

    public void setParameterCount(int var1);

    public T getQueryBindings();

    public void setQueryBindings(T var1);

    public int computeBatchSize(int var1);

    public int getBatchCommandIndex();

    public void setBatchCommandIndex(int var1);

    public String asSql();

    public String asSql(boolean var1);

    public <M extends Message> M fillSendPacket();

    public <M extends Message> M fillSendPacket(QueryBindings<?> var1);
}

