/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.JsonValue;
import java.math.BigDecimal;

public class JsonNumber
implements JsonValue {
    private String val = "null";

    public Integer getInteger() {
        return new BigDecimal(this.val).intValue();
    }

    public BigDecimal getBigDecimal() {
        return new BigDecimal(this.val);
    }

    public JsonNumber setValue(String value) {
        this.val = new BigDecimal(value).toString();
        return this;
    }

    public String toString() {
        return this.val;
    }
}

