/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj;

public interface BatchVisitor {
    public BatchVisitor increment();

    public BatchVisitor decrement();

    public BatchVisitor append(byte[] var1);

    public BatchVisitor merge(byte[] var1, byte[] var2);
}

