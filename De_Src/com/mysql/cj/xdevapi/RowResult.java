/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.Column;
import com.mysql.cj.xdevapi.FetchResult;
import com.mysql.cj.xdevapi.Row;
import java.util.List;

public interface RowResult
extends FetchResult<Row> {
    public int getColumnCount();

    public List<Column> getColumns();

    public List<String> getColumnNames();
}

