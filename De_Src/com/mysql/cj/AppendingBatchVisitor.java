/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj;

import com.mysql.cj.BatchVisitor;
import com.mysql.cj.util.StringUtils;
import java.util.Iterator;
import java.util.LinkedList;

public class AppendingBatchVisitor
implements BatchVisitor {
    LinkedList<byte[]> statementComponents = new LinkedList();

    @Override
    public BatchVisitor append(byte[] values2) {
        this.statementComponents.addLast(values2);
        return this;
    }

    @Override
    public BatchVisitor increment() {
        return this;
    }

    @Override
    public BatchVisitor decrement() {
        this.statementComponents.removeLast();
        return this;
    }

    @Override
    public BatchVisitor merge(byte[] front, byte[] back) {
        int mergedLength = front.length + back.length;
        byte[] merged = new byte[mergedLength];
        System.arraycopy(front, 0, merged, 0, front.length);
        System.arraycopy(back, 0, merged, front.length, back.length);
        this.statementComponents.addLast(merged);
        return this;
    }

    public byte[][] getStaticSqlStrings() {
        byte[][] asBytes = new byte[this.statementComponents.size()][];
        this.statementComponents.toArray((T[])asBytes);
        return asBytes;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        Iterator iter = this.statementComponents.iterator();
        while (iter.hasNext()) {
            buf.append(StringUtils.toString((byte[])iter.next()));
        }
        return buf.toString();
    }
}

