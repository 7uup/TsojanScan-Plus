/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a.result;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.ResultsetRow;
import com.mysql.cj.protocol.ResultsetRows;
import com.mysql.cj.protocol.a.BinaryRowFactory;
import com.mysql.cj.protocol.a.NativeMessageBuilder;
import com.mysql.cj.protocol.a.NativeProtocol;
import com.mysql.cj.protocol.a.result.AbstractResultsetRows;
import com.mysql.cj.result.Row;
import java.util.ArrayList;
import java.util.List;

public class ResultsetRowsCursor
extends AbstractResultsetRows
implements ResultsetRows {
    private List<Row> fetchedRows;
    private int currentPositionInEntireResult = -1;
    private boolean lastRowFetched = false;
    private NativeProtocol protocol;
    private boolean firstFetchCompleted = false;
    protected NativeMessageBuilder commandBuilder = new NativeMessageBuilder();

    public ResultsetRowsCursor(NativeProtocol ioChannel, ColumnDefinition columnDefinition) {
        this.metadata = columnDefinition;
        this.protocol = ioChannel;
        this.rowFactory = new BinaryRowFactory(this.protocol, this.metadata, Resultset.Concurrency.READ_ONLY, false);
    }

    @Override
    public boolean isAfterLast() {
        return this.lastRowFetched && this.currentPositionInFetchedRows > this.fetchedRows.size();
    }

    @Override
    public boolean isBeforeFirst() {
        return this.currentPositionInEntireResult < 0;
    }

    @Override
    public int getPosition() {
        return this.currentPositionInEntireResult + 1;
    }

    @Override
    public boolean isEmpty() {
        return this.isBeforeFirst() && this.isAfterLast();
    }

    @Override
    public boolean isFirst() {
        return this.currentPositionInEntireResult == 0;
    }

    @Override
    public boolean isLast() {
        return this.lastRowFetched && this.currentPositionInFetchedRows == this.fetchedRows.size() - 1;
    }

    @Override
    public void close() {
        this.metadata = null;
        this.owner = null;
    }

    @Override
    public boolean hasNext() {
        int maxRows;
        if (this.fetchedRows != null && this.fetchedRows.size() == 0) {
            return false;
        }
        if (this.owner != null && (maxRows = this.owner.getOwningStatementMaxRows()) != -1 && this.currentPositionInEntireResult + 1 > maxRows) {
            return false;
        }
        if (this.currentPositionInEntireResult != -1) {
            if (this.currentPositionInFetchedRows < this.fetchedRows.size() - 1) {
                return true;
            }
            if (this.currentPositionInFetchedRows == this.fetchedRows.size() && this.lastRowFetched) {
                return false;
            }
            this.fetchMoreRows();
            return this.fetchedRows.size() > 0;
        }
        this.fetchMoreRows();
        return this.fetchedRows.size() > 0;
    }

    @Override
    public Row next() {
        if (this.fetchedRows == null && this.currentPositionInEntireResult != -1) {
            throw ExceptionFactory.createException(Messages.getString("ResultSet.Operation_not_allowed_after_ResultSet_closed_144"), this.protocol.getExceptionInterceptor());
        }
        if (!this.hasNext()) {
            return null;
        }
        ++this.currentPositionInEntireResult;
        ++this.currentPositionInFetchedRows;
        if (this.fetchedRows != null && this.fetchedRows.size() == 0) {
            return null;
        }
        if (this.fetchedRows == null || this.currentPositionInFetchedRows > this.fetchedRows.size() - 1) {
            this.fetchMoreRows();
            this.currentPositionInFetchedRows = 0;
        }
        Row row = this.fetchedRows.get(this.currentPositionInFetchedRows);
        row.setMetadata(this.metadata);
        return row;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void fetchMoreRows() {
        if (this.lastRowFetched) {
            this.fetchedRows = new ArrayList<Row>(0);
            return;
        }
        Object object = this.owner.getSyncMutex();
        synchronized (object) {
            try {
                int numRowsToFetch;
                boolean oldFirstFetchCompleted = this.firstFetchCompleted;
                if (!this.firstFetchCompleted) {
                    this.firstFetchCompleted = true;
                }
                if ((numRowsToFetch = this.owner.getOwnerFetchSize()) == 0) {
                    numRowsToFetch = this.owner.getOwningStatementFetchSize();
                }
                if (numRowsToFetch == Integer.MIN_VALUE) {
                    numRowsToFetch = 1;
                }
                if (this.fetchedRows == null) {
                    this.fetchedRows = new ArrayList<Row>(numRowsToFetch);
                } else {
                    this.fetchedRows.clear();
                }
                this.protocol.sendCommand(this.commandBuilder.buildComStmtFetch(this.protocol.getSharedSendPacket(), this.owner.getOwningStatementServerId(), numRowsToFetch), true, 0);
                Row row = null;
                while ((row = (Row)this.protocol.read(ResultsetRow.class, this.rowFactory)) != null) {
                    this.fetchedRows.add(row);
                }
                this.currentPositionInFetchedRows = -1;
                if (this.protocol.getServerSession().isLastRowSent()) {
                    this.lastRowFetched = true;
                    if (!oldFirstFetchCompleted && this.fetchedRows.size() == 0) {
                        this.wasEmpty = true;
                    }
                }
            } catch (Exception ex) {
                throw ExceptionFactory.createException(ex.getMessage(), ex);
            }
        }
    }

    @Override
    public void addRow(Row row) {
    }

    @Override
    public void afterLast() {
        throw ExceptionFactory.createException(Messages.getString("ResultSet.ForwardOnly"));
    }

    @Override
    public void beforeFirst() {
        throw ExceptionFactory.createException(Messages.getString("ResultSet.ForwardOnly"));
    }

    @Override
    public void beforeLast() {
        throw ExceptionFactory.createException(Messages.getString("ResultSet.ForwardOnly"));
    }

    @Override
    public void moveRowRelative(int rows) {
        throw ExceptionFactory.createException(Messages.getString("ResultSet.ForwardOnly"));
    }

    @Override
    public void setCurrentRow(int rowNumber) {
        throw ExceptionFactory.createException(Messages.getString("ResultSet.ForwardOnly"));
    }
}

