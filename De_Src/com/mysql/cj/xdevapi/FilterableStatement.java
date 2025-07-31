/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.FilterParams;
import com.mysql.cj.xdevapi.Statement;

public abstract class FilterableStatement<STMT_T, RES_T>
implements Statement<STMT_T, RES_T> {
    protected FilterParams filterParams;

    public FilterableStatement(FilterParams filterParams) {
        this.filterParams = filterParams;
    }

    public STMT_T where(String searchCondition) {
        this.filterParams.setCriteria(searchCondition);
        return (STMT_T)this;
    }

    public STMT_T sort(String ... sortFields) {
        return this.orderBy(sortFields);
    }

    public STMT_T orderBy(String ... sortFields) {
        this.filterParams.setOrder(sortFields);
        return (STMT_T)this;
    }

    public STMT_T limit(long numberOfRows) {
        this.filterParams.setLimit(numberOfRows);
        return (STMT_T)this;
    }

    public STMT_T offset(long limitOffset) {
        this.filterParams.setOffset(limitOffset);
        return (STMT_T)this;
    }

    public boolean isRelational() {
        return this.filterParams.isRelational();
    }

    @Override
    public STMT_T clearBindings() {
        this.filterParams.clearArgs();
        return (STMT_T)this;
    }

    @Override
    public STMT_T bind(String argName, Object value) {
        this.filterParams.addArg(argName, value);
        return (STMT_T)this;
    }
}

