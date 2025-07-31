/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.log;

import com.mysql.cj.log.Log;
import com.mysql.cj.log.ProfilerEvent;
import com.mysql.cj.util.LogUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Jdk14Logger
implements Log {
    private static final Level DEBUG = Level.FINE;
    private static final Level ERROR = Level.SEVERE;
    private static final Level FATAL = Level.SEVERE;
    private static final Level INFO = Level.INFO;
    private static final Level TRACE = Level.FINEST;
    private static final Level WARN = Level.WARNING;
    protected Logger jdkLogger = null;

    public Jdk14Logger(String name) {
        this.jdkLogger = Logger.getLogger(name);
    }

    @Override
    public boolean isDebugEnabled() {
        return this.jdkLogger.isLoggable(Level.FINE);
    }

    @Override
    public boolean isErrorEnabled() {
        return this.jdkLogger.isLoggable(Level.SEVERE);
    }

    @Override
    public boolean isFatalEnabled() {
        return this.jdkLogger.isLoggable(Level.SEVERE);
    }

    @Override
    public boolean isInfoEnabled() {
        return this.jdkLogger.isLoggable(Level.INFO);
    }

    @Override
    public boolean isTraceEnabled() {
        return this.jdkLogger.isLoggable(Level.FINEST);
    }

    @Override
    public boolean isWarnEnabled() {
        return this.jdkLogger.isLoggable(Level.WARNING);
    }

    @Override
    public void logDebug(Object message) {
        this.logInternal(DEBUG, message, null);
    }

    @Override
    public void logDebug(Object message, Throwable exception) {
        this.logInternal(DEBUG, message, exception);
    }

    @Override
    public void logError(Object message) {
        this.logInternal(ERROR, message, null);
    }

    @Override
    public void logError(Object message, Throwable exception) {
        this.logInternal(ERROR, message, exception);
    }

    @Override
    public void logFatal(Object message) {
        this.logInternal(FATAL, message, null);
    }

    @Override
    public void logFatal(Object message, Throwable exception) {
        this.logInternal(FATAL, message, exception);
    }

    @Override
    public void logInfo(Object message) {
        this.logInternal(INFO, message, null);
    }

    @Override
    public void logInfo(Object message, Throwable exception) {
        this.logInternal(INFO, message, exception);
    }

    @Override
    public void logTrace(Object message) {
        this.logInternal(TRACE, message, null);
    }

    @Override
    public void logTrace(Object message, Throwable exception) {
        this.logInternal(TRACE, message, exception);
    }

    @Override
    public void logWarn(Object message) {
        this.logInternal(WARN, message, null);
    }

    @Override
    public void logWarn(Object message, Throwable exception) {
        this.logInternal(WARN, message, exception);
    }

    private static final int findCallerStackDepth(StackTraceElement[] stackTrace) {
        int numFrames = stackTrace.length;
        for (int i = 0; i < numFrames; ++i) {
            String callerClassName = stackTrace[i].getClassName();
            if (callerClassName.startsWith("com.mysql.cj") || callerClassName.startsWith("com.mysql.cj.core") || callerClassName.startsWith("com.mysql.cj.jdbc")) continue;
            return i;
        }
        return 0;
    }

    private void logInternal(Level level, Object msg, Throwable exception) {
        if (this.jdkLogger.isLoggable(level)) {
            String messageAsString = null;
            String callerMethodName = "N/A";
            String callerClassName = "N/A";
            if (msg instanceof ProfilerEvent) {
                messageAsString = LogUtils.expandProfilerEventIfNecessary(msg).toString();
            } else {
                Throwable locationException = new Throwable();
                StackTraceElement[] locations = locationException.getStackTrace();
                int frameIdx = Jdk14Logger.findCallerStackDepth(locations);
                if (frameIdx != 0) {
                    callerClassName = locations[frameIdx].getClassName();
                    callerMethodName = locations[frameIdx].getMethodName();
                }
                messageAsString = String.valueOf(msg);
            }
            if (exception == null) {
                this.jdkLogger.logp(level, callerClassName, callerMethodName, messageAsString);
            } else {
                this.jdkLogger.logp(level, callerClassName, callerMethodName, messageAsString, exception);
            }
        }
    }
}

