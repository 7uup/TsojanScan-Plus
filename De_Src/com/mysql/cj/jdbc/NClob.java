/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.jdbc.Clob;

public class NClob
extends Clob
implements java.sql.NClob {
    NClob(ExceptionInterceptor exceptionInterceptor) {
        super(exceptionInterceptor);
    }

    public NClob(String charDataInit, ExceptionInterceptor exceptionInterceptor) {
        super(charDataInit, exceptionInterceptor);
    }
}

