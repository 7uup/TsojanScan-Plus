/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.x.protobuf.MysqlxExpr;
import com.mysql.cj.xdevapi.ExprParser;
import com.mysql.cj.xdevapi.ExprUtil;
import java.util.HashMap;
import java.util.Map;

public class UpdateParams {
    private Map<MysqlxExpr.ColumnIdentifier, MysqlxExpr.Expr> updateOps = new HashMap<MysqlxExpr.ColumnIdentifier, MysqlxExpr.Expr>();

    public void setUpdates(Map<String, Object> updates) {
        updates.entrySet().forEach(e -> this.addUpdate((String)e.getKey(), e.getValue()));
    }

    public void addUpdate(String path, Object value) {
        this.updateOps.put(new ExprParser(path, true).parseTableUpdateField(), ExprUtil.argObjectToExpr(value, true));
    }

    public Object getUpdates() {
        return this.updateOps;
    }
}

