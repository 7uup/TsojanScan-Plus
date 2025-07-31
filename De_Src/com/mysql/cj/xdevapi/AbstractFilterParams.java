/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.mysql.cj.x.protobuf.MysqlxExpr;
import com.mysql.cj.xdevapi.ExprParser;
import com.mysql.cj.xdevapi.ExprUtil;
import com.mysql.cj.xdevapi.FilterParams;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractFilterParams
implements FilterParams {
    protected MysqlxCrud.Collection collection;
    protected Long limit;
    protected Long offset;
    protected String[] orderExpr;
    private List<MysqlxCrud.Order> order;
    protected String criteriaStr;
    private MysqlxExpr.Expr criteria;
    protected MysqlxDatatypes.Scalar[] args;
    private Map<String, Integer> placeholderNameToPosition;
    protected boolean isRelational;
    protected String[] groupBy;
    private List<MysqlxExpr.Expr> grouping;
    String having;
    private MysqlxExpr.Expr groupingCriteria;
    protected String[] projection;
    protected List<MysqlxCrud.Projection> fields;
    protected FilterParams.RowLock lock;
    protected FilterParams.RowLockOptions lockOption;

    public AbstractFilterParams(String schemaName, String collectionName, boolean isRelational) {
        this.collection = ExprUtil.buildCollection(schemaName, collectionName);
        this.isRelational = isRelational;
    }

    @Override
    public Object getCollection() {
        return this.collection;
    }

    @Override
    public Object getOrder() {
        return this.order;
    }

    @Override
    public void setOrder(String ... orderExpression) {
        this.orderExpr = orderExpression;
        this.order = new ExprParser(Arrays.stream(orderExpression).collect(Collectors.joining(", ")), this.isRelational).parseOrderSpec();
    }

    @Override
    public Long getLimit() {
        return this.limit;
    }

    @Override
    public void setLimit(Long limit) {
        this.limit = limit;
    }

    @Override
    public Long getOffset() {
        return this.offset;
    }

    @Override
    public void setOffset(Long offset) {
        this.offset = offset;
    }

    @Override
    public Object getCriteria() {
        return this.criteria;
    }

    @Override
    public void setCriteria(String criteriaString) {
        this.criteriaStr = criteriaString;
        ExprParser parser = new ExprParser(criteriaString, this.isRelational);
        this.criteria = parser.parse();
        if (parser.getPositionalPlaceholderCount() > 0) {
            this.placeholderNameToPosition = parser.getPlaceholderNameToPositionMap();
            this.args = new MysqlxDatatypes.Scalar[parser.getPositionalPlaceholderCount()];
        }
    }

    @Override
    public Object getArgs() {
        if (this.args == null) {
            return null;
        }
        return Arrays.asList(this.args);
    }

    @Override
    public void addArg(String name, Object value) {
        if (this.args == null) {
            throw new WrongArgumentException("No placeholders");
        }
        if (this.placeholderNameToPosition.get(name) == null) {
            throw new WrongArgumentException("Unknown placeholder: " + name);
        }
        this.args[this.placeholderNameToPosition.get((Object)name).intValue()] = ExprUtil.argObjectToScalar(value);
    }

    @Override
    public void verifyAllArgsBound() {
        if (this.args != null) {
            IntStream.range(0, this.args.length).filter(i -> this.args[i] == null).mapToObj(i -> this.placeholderNameToPosition.entrySet().stream().filter(e -> (Integer)e.getValue() == i).map(Map.Entry::getKey).findFirst().get()).forEach(name -> {
                throw new WrongArgumentException("Placeholder '" + name + "' is not bound");
            });
        }
    }

    @Override
    public void clearArgs() {
        if (this.args != null) {
            IntStream.range(0, this.args.length).forEach(i -> {
                this.args[i] = null;
            });
        }
    }

    @Override
    public boolean isRelational() {
        return this.isRelational;
    }

    @Override
    public abstract void setFields(String ... var1);

    @Override
    public Object getFields() {
        return this.fields;
    }

    @Override
    public void setGrouping(String ... groupBy) {
        this.groupBy = groupBy;
        this.grouping = new ExprParser(Arrays.stream(groupBy).collect(Collectors.joining(", ")), this.isRelational()).parseExprList();
    }

    @Override
    public Object getGrouping() {
        return this.grouping;
    }

    @Override
    public void setGroupingCriteria(String having) {
        this.having = having;
        this.groupingCriteria = new ExprParser(having, this.isRelational()).parse();
    }

    @Override
    public Object getGroupingCriteria() {
        return this.groupingCriteria;
    }

    @Override
    public FilterParams.RowLock getLock() {
        return this.lock;
    }

    @Override
    public void setLock(FilterParams.RowLock rowLock) {
        this.lock = rowLock;
    }

    @Override
    public FilterParams.RowLockOptions getLockOption() {
        return this.lockOption;
    }

    @Override
    public void setLockOption(FilterParams.RowLockOptions lockOption) {
        this.lockOption = lockOption;
    }
}

