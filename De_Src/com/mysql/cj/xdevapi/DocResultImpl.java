/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.protocol.x.StatementExecuteOk;
import com.mysql.cj.result.RowList;
import com.mysql.cj.xdevapi.AbstractDataResult;
import com.mysql.cj.xdevapi.DbDoc;
import com.mysql.cj.xdevapi.DbDocFactory;
import com.mysql.cj.xdevapi.DocResult;
import java.util.function.Supplier;

public class DocResultImpl
extends AbstractDataResult<DbDoc>
implements DocResult {
    public DocResultImpl(RowList rows, Supplier<StatementExecuteOk> completer) {
        super(rows, completer, new DbDocFactory());
    }
}

