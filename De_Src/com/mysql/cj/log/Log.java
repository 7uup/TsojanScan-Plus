/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.log;

public interface Log {
    public static final String LOGGER_INSTANCE_NAME = "MySQL";

    public boolean isDebugEnabled();

    public boolean isErrorEnabled();

    public boolean isFatalEnabled();

    public boolean isInfoEnabled();

    public boolean isTraceEnabled();

    public boolean isWarnEnabled();

    public void logDebug(Object var1);

    public void logDebug(Object var1, Throwable var2);

    public void logError(Object var1);

    public void logError(Object var1, Throwable var2);

    public void logFatal(Object var1);

    public void logFatal(Object var1, Throwable var2);

    public void logInfo(Object var1);

    public void logInfo(Object var1, Throwable var2);

    public void logTrace(Object var1);

    public void logTrace(Object var1, Throwable var2);

    public void logWarn(Object var1);

    public void logWarn(Object var1, Throwable var2);
}

