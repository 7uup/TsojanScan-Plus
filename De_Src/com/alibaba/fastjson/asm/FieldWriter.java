/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.asm;

import com.alibaba.fastjson.asm.ByteVector;
import com.alibaba.fastjson.asm.ClassWriter;

public final class FieldWriter {
    FieldWriter next;
    private final int access;
    private final int name;
    private final int desc;

    public FieldWriter(ClassWriter cw, int access, String name, String desc) {
        if (cw.firstField == null) {
            cw.firstField = this;
        } else {
            cw.lastField.next = this;
        }
        cw.lastField = this;
        this.access = access;
        this.name = cw.newUTF8(name);
        this.desc = cw.newUTF8(desc);
    }

    public void visitEnd() {
    }

    int getSize() {
        return 8;
    }

    void put(ByteVector out) {
        int mask = 393216;
        out.putShort(this.access & 0xFFF9FFFF).putShort(this.name).putShort(this.desc);
        int attributeCount = 0;
        out.putShort(attributeCount);
    }
}

