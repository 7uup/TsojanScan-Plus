/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.ResultStreamer;
import com.mysql.cj.protocol.x.StatementExecuteOk;
import com.mysql.cj.protocol.x.XMessage;
import com.mysql.cj.result.BufferedRowList;
import com.mysql.cj.result.Row;
import com.mysql.cj.result.RowList;
import com.mysql.cj.xdevapi.Warning;
import com.mysql.cj.xdevapi.WarningImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class AbstractDataResult<T>
implements ResultStreamer,
Iterator<T> {
    protected int position = -1;
    protected int count = -1;
    protected RowList rows;
    protected Supplier<StatementExecuteOk> completer;
    protected StatementExecuteOk ok;
    protected ProtocolEntityFactory<T, XMessage> rowToData;
    protected List<T> all;

    public AbstractDataResult(RowList rows, Supplier<StatementExecuteOk> completer, ProtocolEntityFactory<T, XMessage> rowToData) {
        this.rows = rows;
        this.completer = completer;
        this.rowToData = rowToData;
    }

    @Override
    public T next() {
        if (this.all != null) {
            throw new WrongArgumentException("Cannot iterate after fetchAll()");
        }
        Row r = (Row)this.rows.next();
        if (r == null) {
            throw new NoSuchElementException();
        }
        ++this.position;
        return this.rowToData.createFromProtocolEntity(r);
    }

    public List<T> fetchAll() {
        if (this.position > -1) {
            throw new WrongArgumentException("Cannot fetchAll() after starting iteration");
        }
        if (this.all == null) {
            this.all = new ArrayList<T>((int)this.count());
            this.rows.forEachRemaining(r -> this.all.add(this.rowToData.createFromProtocolEntity((ProtocolEntity)r)));
            this.all = Collections.unmodifiableList(this.all);
        }
        return this.all;
    }

    public long count() {
        this.finishStreaming();
        return this.count;
    }

    @Override
    public boolean hasNext() {
        return this.rows.hasNext();
    }

    public StatementExecuteOk getStatementExecuteOk() {
        this.finishStreaming();
        return this.ok;
    }

    @Override
    public void finishStreaming() {
        if (this.ok == null) {
            BufferedRowList remainingRows = new BufferedRowList(this.rows);
            this.count = 1 + this.position + remainingRows.size();
            this.rows = remainingRows;
            this.ok = this.completer.get();
        }
    }

    public int getWarningsCount() {
        return this.getStatementExecuteOk().getWarnings().size();
    }

    public Iterator<Warning> getWarnings() {
        return this.getStatementExecuteOk().getWarnings().stream().map(w -> new WarningImpl((com.mysql.cj.protocol.Warning)w)).collect(Collectors.toList()).iterator();
    }
}

