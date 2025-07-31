/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.result.Row;
import com.mysql.cj.result.RowList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BufferedRowList
implements RowList {
    private List<Row> rowList;
    private int position = -1;

    public BufferedRowList(List<Row> rowList) {
        this.rowList = rowList;
    }

    public BufferedRowList(Iterator<Row> ris) {
        this.rowList = StreamSupport.stream(Spliterators.spliteratorUnknownSize(ris, 0), false).collect(Collectors.toList());
    }

    @Override
    public Row next() {
        if (this.position + 1 == this.rowList.size()) {
            throw new NoSuchElementException("Can't next() when position=" + this.position + " and size=" + this.rowList.size());
        }
        return this.rowList.get(++this.position);
    }

    @Override
    public Row previous() {
        if (this.position < 1) {
            throw new NoSuchElementException("Can't previous() when position=" + this.position);
        }
        return this.rowList.get(--this.position);
    }

    @Override
    public Row get(int n) {
        if (n < 0 || n >= this.rowList.size()) {
            throw new NoSuchElementException("Can't get(" + n + ") when size=" + this.rowList.size());
        }
        return this.rowList.get(n);
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public int size() {
        return this.rowList.size();
    }

    @Override
    public boolean hasNext() {
        return this.position + 1 < this.rowList.size();
    }
}

