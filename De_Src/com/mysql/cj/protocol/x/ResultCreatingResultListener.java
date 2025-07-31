/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.x;

import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.ResultListener;
import com.mysql.cj.protocol.x.StatementExecuteOk;
import com.mysql.cj.result.BufferedRowList;
import com.mysql.cj.result.Row;
import com.mysql.cj.result.RowList;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class ResultCreatingResultListener<RES_T>
implements ResultListener<StatementExecuteOk> {
    private ColumnDefinition metadata;
    private List<Row> rows = new ArrayList<Row>();
    private Function<ColumnDefinition, BiFunction<RowList, Supplier<StatementExecuteOk>, RES_T>> resultCtor;
    private CompletableFuture<RES_T> future;

    public ResultCreatingResultListener(Function<ColumnDefinition, BiFunction<RowList, Supplier<StatementExecuteOk>, RES_T>> resultCtor, CompletableFuture<RES_T> future) {
        this.resultCtor = resultCtor;
        this.future = future;
    }

    @Override
    public void onMetadata(ColumnDefinition metadataFields) {
        this.metadata = metadataFields;
    }

    @Override
    public void onRow(Row r) {
        this.rows.add(r);
    }

    @Override
    public void onComplete(StatementExecuteOk ok) {
        BufferedRowList rowList = new BufferedRowList(this.rows);
        RES_T result = this.resultCtor.apply(this.metadata).apply(rowList, () -> ok);
        this.future.complete(result);
    }

    @Override
    public void onException(Throwable t) {
        this.future.completeExceptionally(t);
    }
}

