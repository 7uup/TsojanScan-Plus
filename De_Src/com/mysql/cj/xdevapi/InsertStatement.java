/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.InsertResult;
import com.mysql.cj.xdevapi.Statement;
import java.util.Arrays;
import java.util.List;

public interface InsertStatement
extends Statement<InsertStatement, InsertResult> {
    public InsertStatement values(List<Object> var1);

    default public InsertStatement values(Object ... values2) {
        return this.values(Arrays.asList(values2));
    }
}

