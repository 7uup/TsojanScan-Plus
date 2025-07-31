/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.JsonParser;
import com.mysql.cj.xdevapi.JsonValue;
import java.util.HashMap;

public class JsonString
implements JsonValue {
    static HashMap<Character, String> escapeChars = new HashMap();
    private String val = "";

    public String getString() {
        return this.val;
    }

    public JsonString setValue(String value) {
        this.val = value;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("\"");
        for (int i = 0; i < this.val.length(); ++i) {
            if (escapeChars.containsKey(Character.valueOf(this.val.charAt(i)))) {
                sb.append(escapeChars.get(Character.valueOf(this.val.charAt(i))));
                continue;
            }
            sb.append(this.val.charAt(i));
        }
        sb.append("\"");
        return sb.toString();
    }

    static {
        for (JsonParser.EscapeChar ec : JsonParser.EscapeChar.values()) {
            escapeChars.put(Character.valueOf(ec.CHAR), ec.ESCAPED);
        }
    }
}

