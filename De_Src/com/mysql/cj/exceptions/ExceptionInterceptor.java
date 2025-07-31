/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.exceptions;

import com.mysql.cj.log.Log;
import java.util.Properties;

public interface ExceptionInterceptor {
    public ExceptionInterceptor init(Properties var1, Log var2);

    public void destroy();

    public Exception interceptException(Exception var1);
}

