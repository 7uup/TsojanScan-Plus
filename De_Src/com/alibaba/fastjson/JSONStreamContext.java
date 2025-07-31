/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson;

class JSONStreamContext {
    static final int StartObject = 1001;
    static final int PropertyKey = 1002;
    static final int PropertyValue = 1003;
    static final int StartArray = 1004;
    static final int ArrayValue = 1005;
    protected final JSONStreamContext parent;
    protected int state;

    public JSONStreamContext(JSONStreamContext parent, int state) {
        this.parent = parent;
        this.state = state;
    }
}

