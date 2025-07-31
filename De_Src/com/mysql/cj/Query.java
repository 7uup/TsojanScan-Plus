/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj;

import com.mysql.cj.CancelQueryTask;
import com.mysql.cj.Session;
import com.mysql.cj.log.ProfilerEventHandler;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.Resultset;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public interface Query {
    public int getId();

    public void setCancelStatus(CancelStatus var1);

    public void checkCancelTimeout();

    public <T extends Resultset, M extends Message> ProtocolEntityFactory<T, M> getResultSetFactory();

    public Session getSession();

    public Object getCancelTimeoutMutex();

    public void resetCancelledState();

    public void closeQuery();

    public void addBatch(Object var1);

    public List<Object> getBatchedArgs();

    public void clearBatchedArgs();

    public int getResultFetchSize();

    public void setResultFetchSize(int var1);

    public Resultset.Type getResultType();

    public void setResultType(Resultset.Type var1);

    public int getTimeoutInMillis();

    public void setTimeoutInMillis(int var1);

    public CancelQueryTask startQueryTimer(Query var1, int var2);

    public ProfilerEventHandler getEventSink();

    public void setEventSink(ProfilerEventHandler var1);

    public AtomicBoolean getStatementExecuting();

    public String getCurrentCatalog();

    public void setCurrentCatalog(String var1);

    public boolean isClearWarningsCalled();

    public void setClearWarningsCalled(boolean var1);

    public void statementBegins();

    public void stopQueryTimer(CancelQueryTask var1, boolean var2, boolean var3);

    public static enum CancelStatus {
        NOT_CANCELED,
        CANCELED_BY_USER,
        CANCELED_BY_TIMEOUT;

    }
}

