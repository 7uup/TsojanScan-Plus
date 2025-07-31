/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser;

import java.lang.reflect.Type;

public class ParseContext {
    public Object object;
    public final ParseContext parent;
    public final Object fieldName;
    public final int level;
    public Type type;
    private transient String path;

    public ParseContext(ParseContext parent, Object object, Object fieldName) {
        this.parent = parent;
        this.object = object;
        this.fieldName = fieldName;
        this.level = parent == null ? 0 : parent.level + 1;
    }

    public String toString() {
        if (this.path == null) {
            this.path = this.parent == null ? "$" : (this.fieldName instanceof Integer ? this.parent.toString() + "[" + this.fieldName + "]" : this.parent.toString() + "." + this.fieldName);
        }
        return this.path;
    }
}

