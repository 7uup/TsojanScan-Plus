/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj;

public interface TransactionEventHandler {
    public void transactionBegun();

    public void transactionCompleted();
}

