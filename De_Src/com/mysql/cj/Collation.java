/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj;

import com.mysql.cj.CharsetMapping;
import com.mysql.cj.MysqlCharset;

class Collation {
    public final int index;
    public final String collationName;
    public final int priority;
    public final MysqlCharset mysqlCharset;

    public Collation(int index, String collationName, int priority, String charsetName) {
        this.index = index;
        this.collationName = collationName;
        this.priority = priority;
        this.mysqlCharset = CharsetMapping.CHARSET_NAME_TO_CHARSET.get(charsetName);
    }

    public String toString() {
        StringBuilder asString2 = new StringBuilder();
        asString2.append("[");
        asString2.append("index=");
        asString2.append(this.index);
        asString2.append(",collationName=");
        asString2.append(this.collationName);
        asString2.append(",charsetName=");
        asString2.append(this.mysqlCharset.charsetName);
        asString2.append(",javaCharsetName=");
        asString2.append(this.mysqlCharset.getMatchingJavaEncoding(null));
        asString2.append("]");
        return asString2.toString();
    }
}

