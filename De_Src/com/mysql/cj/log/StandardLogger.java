/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.log;

import com.mysql.cj.log.Log;
import com.mysql.cj.log.ProfilerEvent;
import com.mysql.cj.util.LogUtils;
import com.mysql.cj.util.Util;
import java.util.Date;

public class StandardLogger
implements Log {
    private static final int FATAL = 0;
    private static final int ERROR = 1;
    private static final int WARN = 2;
    private static final int INFO = 3;
    private static final int DEBUG = 4;
    private static final int TRACE = 5;
    private static StringBuffer bufferedLog = null;
    private boolean logLocationInfo = true;

    public StandardLogger(String name) {
        this(name, false);
    }

    public StandardLogger(String name, boolean logLocationInfo) {
        this.logLocationInfo = logLocationInfo;
    }

    public static void startLoggingToBuffer() {
        bufferedLog = new StringBuffer();
    }

    public static void dropBuffer() {
        bufferedLog = null;
    }

    public static Appendable getBuffer() {
        return bufferedLog;
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public boolean isErrorEnabled() {
        return true;
    }

    @Override
    public boolean isFatalEnabled() {
        return true;
    }

    @Override
    public boolean isInfoEnabled() {
        return true;
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public boolean isWarnEnabled() {
        return true;
    }

    @Override
    public void logDebug(Object message) {
        this.logInternal(4, message, null);
    }

    @Override
    public void logDebug(Object message, Throwable exception) {
        this.logInternal(4, message, exception);
    }

    @Override
    public void logError(Object message) {
        this.logInternal(1, message, null);
    }

    @Override
    public void logError(Object message, Throwable exception) {
        this.logInternal(1, message, exception);
    }

    @Override
    public void logFatal(Object message) {
        this.logInternal(0, message, null);
    }

    @Override
    public void logFatal(Object message, Throwable exception) {
        this.logInternal(0, message, exception);
    }

    @Override
    public void logInfo(Object message) {
        this.logInternal(3, message, null);
    }

    @Override
    public void logInfo(Object message, Throwable exception) {
        this.logInternal(3, message, exception);
    }

    @Override
    public void logTrace(Object message) {
        this.logInternal(5, message, null);
    }

    @Override
    public void logTrace(Object message, Throwable exception) {
        this.logInternal(5, message, exception);
    }

    @Override
    public void logWarn(Object message) {
        this.logInternal(2, message, null);
    }

    @Override
    public void logWarn(Object message, Throwable exception) {
        this.logInternal(2, message, exception);
    }

    protected void logInternal(int level, Object msg, Throwable exception) {
        StringBuilder msgBuf = new StringBuilder();
        msgBuf.append(new Date().toString());
        msgBuf.append(" ");
        switch (level) {
            case 0: {
                msgBuf.append("FATAL: ");
                break;
            }
            case 1: {
                msgBuf.append("ERROR: ");
                break;
            }
            case 2: {
                msgBuf.append("WARN: ");
                break;
            }
            case 3: {
                msgBuf.append("INFO: ");
                break;
            }
            case 4: {
                msgBuf.append("DEBUG: ");
                break;
            }
            case 5: {
                msgBuf.append("TRACE: ");
            }
        }
        if (msg instanceof ProfilerEvent) {
            msgBuf.append(LogUtils.expandProfilerEventIfNecessary(msg));
        } else {
            if (this.logLocationInfo && level != 5) {
                Throwable locationException = new Throwable();
                msgBuf.append(LogUtils.findCallingClassAndMethod(locationException));
                msgBuf.append(" ");
            }
            if (msg != null) {
                msgBuf.append(String.valueOf(msg));
            }
        }
        if (exception != null) {
            msgBuf.append("\n");
            msgBuf.append("\n");
            msgBuf.append("EXCEPTION STACK TRACE:");
            msgBuf.append("\n");
            msgBuf.append("\n");
            msgBuf.append(Util.stackTraceToString(exception));
        }
        String messageAsString = msgBuf.toString();
        System.err.println(messageAsString);
        if (bufferedLog != null) {
            bufferedLog.append(messageAsString);
        }
    }
}

