/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.ha;

import com.mysql.cj.jdbc.ha.StandardLoadBalanceExceptionChecker;

public class NdbLoadBalanceExceptionChecker
extends StandardLoadBalanceExceptionChecker {
    @Override
    public boolean shouldExceptionTriggerFailover(Throwable ex) {
        return super.shouldExceptionTriggerFailover(ex) || this.checkNdbException(ex);
    }

    private boolean checkNdbException(Throwable ex) {
        return ex.getMessage().startsWith("Lock wait timeout exceeded") || ex.getMessage().startsWith("Got temporary error") && ex.getMessage().endsWith("from NDB");
    }
}

