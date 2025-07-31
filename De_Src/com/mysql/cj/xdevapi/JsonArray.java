/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.JsonValue;
import java.util.ArrayList;

public class JsonArray
extends ArrayList<JsonValue>
implements JsonValue {
    private static final long serialVersionUID = 6557406141541247905L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (JsonValue val : this) {
            if (sb.length() > 1) {
                sb.append(",");
            }
            sb.append(val.toString());
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String toFormattedString() {
        StringBuilder sb = new StringBuilder("[");
        for (JsonValue val : this) {
            if (sb.length() > 1) {
                sb.append(", ");
            }
            sb.append(val.toFormattedString());
        }
        sb.append("]");
        return sb.toString();
    }

    public JsonArray addValue(JsonValue val) {
        this.add(val);
        return this;
    }
}

