/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.mysql.cj.x.protobuf.MysqlxExpr;
import com.mysql.cj.xdevapi.ExprParser;
import com.mysql.cj.xdevapi.ExprUtil;
import com.mysql.cj.xdevapi.UpdateType;

public class UpdateSpec {
    private MysqlxCrud.UpdateOperation.UpdateType updateType;
    private MysqlxExpr.ColumnIdentifier source;
    private MysqlxExpr.Expr value;

    public UpdateSpec(UpdateType updateType, String source2) {
        this.updateType = MysqlxCrud.UpdateOperation.UpdateType.valueOf(updateType.name());
        if (source2.length() > 0 && source2.charAt(0) == '$') {
            source2 = source2.substring(1);
        }
        this.source = new ExprParser(source2, false).documentField().getIdentifier();
    }

    public Object getUpdateType() {
        return this.updateType;
    }

    public Object getSource() {
        return this.source;
    }

    public UpdateSpec setValue(Object value) {
        this.value = ExprUtil.argObjectToExpr(value, false);
        return this;
    }

    public Object getValue() {
        return this.value;
    }
}

