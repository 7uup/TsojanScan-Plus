/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.mysql.cj.xdevapi.AbstractFilterParams;
import com.mysql.cj.xdevapi.ExprParser;
import com.mysql.cj.xdevapi.Expression;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class DocFilterParams
extends AbstractFilterParams {
    public DocFilterParams(String schemaName, String collectionName) {
        super(schemaName, collectionName, false);
    }

    public void setFields(Expression docProjection) {
        this.fields = Collections.singletonList(MysqlxCrud.Projection.newBuilder().setSource(new ExprParser(docProjection.getExpressionString(), false).parse()).build());
    }

    @Override
    public void setFields(String ... projection) {
        this.fields = new ExprParser(Arrays.stream(projection).collect(Collectors.joining(", ")), false).parseDocumentProjection();
    }
}

