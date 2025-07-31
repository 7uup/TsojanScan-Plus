/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.log;

import com.mysql.cj.Session;
import com.mysql.cj.log.ProfilerEventHandler;
import com.mysql.cj.util.Util;

public class ProfilerEventHandlerFactory {
    public static synchronized ProfilerEventHandler getInstance(Session sess) {
        ProfilerEventHandler handler = sess.getProfilerEventHandler();
        if (handler == null) {
            handler = (ProfilerEventHandler)Util.getInstance(sess.getPropertySet().getStringProperty("profilerEventHandler").getStringValue(), new Class[0], new Object[0], sess.getExceptionInterceptor());
            handler.init(sess.getLog());
            sess.setProfilerEventHandler(handler);
        }
        return handler;
    }

    public static synchronized void removeInstance(Session sess) {
        ProfilerEventHandler handler = sess.getProfilerEventHandler();
        if (handler != null) {
            handler.destroy();
        }
    }
}

