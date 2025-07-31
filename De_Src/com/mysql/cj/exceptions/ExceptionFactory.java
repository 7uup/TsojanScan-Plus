/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.exceptions;

import com.mysql.cj.Messages;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.protocol.PacketReceivedTimeHolder;
import com.mysql.cj.protocol.PacketSentTimeHolder;
import com.mysql.cj.protocol.ServerSession;
import com.mysql.cj.util.Util;
import java.net.BindException;

public class ExceptionFactory {
    private static final long DEFAULT_WAIT_TIMEOUT_SECONDS = 28800L;
    private static final int DUE_TO_TIMEOUT_FALSE = 0;
    private static final int DUE_TO_TIMEOUT_MAYBE = 2;
    private static final int DUE_TO_TIMEOUT_TRUE = 1;

    public static CJException createException(String message) {
        return ExceptionFactory.createException(CJException.class, message);
    }

    public static <T extends CJException> T createException(Class<T> clazz, String message) {
        CJException sqlEx;
        try {
            sqlEx = (CJException)clazz.getConstructor(String.class).newInstance(message);
        } catch (Throwable e) {
            sqlEx = new CJException(message);
        }
        return (T)sqlEx;
    }

    public static CJException createException(String message, ExceptionInterceptor interceptor) {
        return ExceptionFactory.createException(CJException.class, message, interceptor);
    }

    public static <T extends CJException> T createException(Class<T> clazz, String message, ExceptionInterceptor interceptor) {
        T sqlEx = ExceptionFactory.createException(clazz, message);
        return sqlEx;
    }

    public static CJException createException(String message, Throwable cause) {
        return ExceptionFactory.createException(CJException.class, message, cause);
    }

    public static <T extends CJException> T createException(Class<T> clazz, String message, Throwable cause) {
        T sqlEx = ExceptionFactory.createException(clazz, message);
        if (cause != null) {
            try {
                ((Throwable)sqlEx).initCause(cause);
            } catch (Throwable throwable) {
                // empty catch block
            }
            if (cause instanceof CJException) {
                ((CJException)sqlEx).setSQLState(((CJException)cause).getSQLState());
                ((CJException)sqlEx).setVendorCode(((CJException)cause).getVendorCode());
                ((CJException)sqlEx).setTransient(((CJException)cause).isTransient());
            }
        }
        return sqlEx;
    }

    public static CJException createException(String message, Throwable cause, ExceptionInterceptor interceptor) {
        return ExceptionFactory.createException(CJException.class, message, cause, interceptor);
    }

    public static CJException createException(String message, String sqlState, int vendorErrorCode, boolean isTransient, Throwable cause, ExceptionInterceptor interceptor) {
        CJException ex = ExceptionFactory.createException(CJException.class, message, cause, interceptor);
        ex.setSQLState(sqlState);
        ex.setVendorCode(vendorErrorCode);
        ex.setTransient(isTransient);
        return ex;
    }

    public static <T extends CJException> T createException(Class<T> clazz, String message, Throwable cause, ExceptionInterceptor interceptor) {
        T sqlEx = ExceptionFactory.createException(clazz, message, cause);
        return sqlEx;
    }

    public static CJCommunicationsException createCommunicationsException(PropertySet propertySet, ServerSession serverSession, PacketSentTimeHolder packetSentTimeHolder, PacketReceivedTimeHolder packetReceivedTimeHolder, Throwable cause, ExceptionInterceptor interceptor) {
        CJCommunicationsException sqlEx = ExceptionFactory.createException(CJCommunicationsException.class, null, cause, interceptor);
        sqlEx.init(propertySet, serverSession, packetSentTimeHolder, packetReceivedTimeHolder);
        return sqlEx;
    }

    public static String createLinkFailureMessageBasedOnHeuristics(PropertySet propertySet, ServerSession serverSession, PacketSentTimeHolder packetSentTimeHolder, PacketReceivedTimeHolder packetReceivedTimeHolder, Throwable underlyingException) {
        Object[] timingInfo;
        long serverTimeoutSeconds = 0L;
        boolean isInteractiveClient = false;
        long lastPacketReceivedTimeMs = packetReceivedTimeHolder == null ? 0L : packetReceivedTimeHolder.getLastPacketReceivedTime();
        long lastPacketSentTimeMs = packetSentTimeHolder.getLastPacketSentTime();
        if (lastPacketSentTimeMs > lastPacketReceivedTimeMs) {
            lastPacketSentTimeMs = packetSentTimeHolder.getPreviousPacketSentTime();
        }
        if (propertySet != null) {
            isInteractiveClient = propertySet.getBooleanProperty("interactiveClient").getValue();
            String serverTimeoutSecondsStr = null;
            if (serverSession != null) {
                String string = serverTimeoutSecondsStr = isInteractiveClient ? serverSession.getServerVariable("interactive_timeout") : serverSession.getServerVariable("wait_timeout");
            }
            if (serverTimeoutSecondsStr != null) {
                try {
                    serverTimeoutSeconds = Long.parseLong(serverTimeoutSecondsStr);
                } catch (NumberFormatException nfe) {
                    serverTimeoutSeconds = 0L;
                }
            }
        }
        StringBuilder exceptionMessageBuf = new StringBuilder();
        long nowMs = System.currentTimeMillis();
        if (lastPacketSentTimeMs == 0L) {
            lastPacketSentTimeMs = nowMs;
        }
        long timeSinceLastPacketSentMs = nowMs - lastPacketSentTimeMs;
        long timeSinceLastPacketSeconds = timeSinceLastPacketSentMs / 1000L;
        long timeSinceLastPacketReceivedMs = nowMs - lastPacketReceivedTimeMs;
        int dueToTimeout = 0;
        StringBuilder timeoutMessageBuf = null;
        if (serverTimeoutSeconds != 0L) {
            if (timeSinceLastPacketSeconds > serverTimeoutSeconds) {
                dueToTimeout = 1;
                timeoutMessageBuf = new StringBuilder();
                timeoutMessageBuf.append(Messages.getString("CommunicationsException.2"));
                timeoutMessageBuf.append(Messages.getString(isInteractiveClient ? "CommunicationsException.4" : "CommunicationsException.3"));
            }
        } else if (timeSinceLastPacketSeconds > 28800L) {
            dueToTimeout = 2;
            timeoutMessageBuf = new StringBuilder();
            timeoutMessageBuf.append(Messages.getString("CommunicationsException.5"));
            timeoutMessageBuf.append(Messages.getString("CommunicationsException.6"));
            timeoutMessageBuf.append(Messages.getString("CommunicationsException.7"));
            timeoutMessageBuf.append(Messages.getString("CommunicationsException.8"));
        }
        if (dueToTimeout == 1 || dueToTimeout == 2) {
            if (lastPacketReceivedTimeMs != 0L) {
                timingInfo = new Object[]{timeSinceLastPacketReceivedMs, timeSinceLastPacketSentMs};
                exceptionMessageBuf.append(Messages.getString("CommunicationsException.ServerPacketTimingInfo", timingInfo));
            } else {
                exceptionMessageBuf.append(Messages.getString("CommunicationsException.ServerPacketTimingInfoNoRecv", new Object[]{timeSinceLastPacketSentMs}));
            }
            if (timeoutMessageBuf != null) {
                exceptionMessageBuf.append((CharSequence)timeoutMessageBuf);
            }
            exceptionMessageBuf.append(Messages.getString("CommunicationsException.11"));
            exceptionMessageBuf.append(Messages.getString("CommunicationsException.12"));
            exceptionMessageBuf.append(Messages.getString("CommunicationsException.13"));
        } else if (underlyingException instanceof BindException) {
            String localSocketAddress = propertySet.getStringProperty("localSocketAddress").getValue();
            if (localSocketAddress != null && !Util.interfaceExists(localSocketAddress)) {
                exceptionMessageBuf.append(Messages.getString("CommunicationsException.LocalSocketAddressNotAvailable"));
            } else {
                exceptionMessageBuf.append(Messages.getString("CommunicationsException.TooManyClientConnections"));
            }
        }
        if (exceptionMessageBuf.length() == 0) {
            exceptionMessageBuf.append(Messages.getString("CommunicationsException.20"));
            if (propertySet.getBooleanProperty("maintainTimeStats").getValue().booleanValue() && !propertySet.getBooleanProperty("paranoid").getValue().booleanValue()) {
                exceptionMessageBuf.append("\n\n");
                if (lastPacketReceivedTimeMs != 0L) {
                    timingInfo = new Object[]{timeSinceLastPacketReceivedMs, timeSinceLastPacketSentMs};
                    exceptionMessageBuf.append(Messages.getString("CommunicationsException.ServerPacketTimingInfo", timingInfo));
                } else {
                    exceptionMessageBuf.append(Messages.getString("CommunicationsException.ServerPacketTimingInfoNoRecv", new Object[]{timeSinceLastPacketSentMs}));
                }
            }
        }
        return exceptionMessageBuf.toString();
    }
}

