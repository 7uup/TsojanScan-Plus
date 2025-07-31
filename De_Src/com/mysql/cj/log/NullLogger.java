/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.log;

import com.mysql.cj.log.Log;

public class NullLogger
implements Log {
    public NullLogger(String instanceName) {
    }

    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public boolean isErrorEnabled() {
        return false;
    }

    @Override
    public boolean isFatalEnabled() {
        return false;
    }

    @Override
    public boolean isInfoEnabled() {
        return false;
    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public boolean isWarnEnabled() {
        return false;
    }

    @Override
    public void logDebug(Object msg) {
    }

    @Override
    public void logDebug(Object msg, Throwable thrown) {
    }

    @Override
    public void logError(Object msg) {
    }

    @Override
    public void logError(Object msg, Throwable thrown) {
    }

    @Override
    public void logFatal(Object msg) {
    }

    @Override
    public void logFatal(Object msg, Throwable thrown) {
    }

    @Override
    public void logInfo(Object msg) {
    }

    @Override
    public void logInfo(Object msg, Throwable thrown) {
    }

    @Override
    public void logTrace(Object msg) {
    }

    @Override
    public void logTrace(Object msg, Throwable thrown) {
    }

    @Override
    public void logWarn(Object msg) {
    }

    @Override
    public void logWarn(Object msg, Throwable thrown) {
    }
}

