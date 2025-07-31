/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.asm;

import com.alibaba.fastjson.asm.ByteVector;
import com.alibaba.fastjson.asm.ClassWriter;
import com.alibaba.fastjson.asm.Item;
import com.alibaba.fastjson.asm.Label;
import com.alibaba.fastjson.asm.MethodVisitor;
import com.alibaba.fastjson.asm.Type;

public class MethodWriter
implements MethodVisitor {
    MethodWriter next;
    final ClassWriter cw;
    private int access;
    private final int name;
    private final int desc;
    int exceptionCount;
    int[] exceptions;
    private ByteVector code = new ByteVector();
    private int maxStack;
    private int maxLocals;

    public MethodWriter(ClassWriter cw, int access, String name, String desc, String signature, String[] exceptions) {
        if (cw.firstMethod == null) {
            cw.firstMethod = this;
        } else {
            cw.lastMethod.next = this;
        }
        cw.lastMethod = this;
        this.cw = cw;
        this.access = access;
        this.name = cw.newUTF8(name);
        this.desc = cw.newUTF8(desc);
        if (exceptions != null && exceptions.length > 0) {
            this.exceptionCount = exceptions.length;
            this.exceptions = new int[this.exceptionCount];
            for (int i = 0; i < this.exceptionCount; ++i) {
                this.exceptions[i] = cw.newClassItem((String)exceptions[i]).index;
            }
        }
    }

    public void visitInsn(int opcode) {
        this.code.putByte(opcode);
    }

    public void visitIntInsn(int opcode, int operand) {
        this.code.put11(opcode, operand);
    }

    public void visitVarInsn(int opcode, int var) {
        if (var < 4 && opcode != 169) {
            int opt = opcode < 54 ? 26 + (opcode - 21 << 2) + var : 59 + (opcode - 54 << 2) + var;
            this.code.putByte(opt);
        } else if (var >= 256) {
            this.code.putByte(196).put12(opcode, var);
        } else {
            this.code.put11(opcode, var);
        }
    }

    public void visitTypeInsn(int opcode, String type) {
        Item i = this.cw.newClassItem(type);
        this.code.put12(opcode, i.index);
    }

    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        Item i = this.cw.newFieldItem(owner, name, desc);
        this.code.put12(opcode, i.index);
    }

    public void visitMethodInsn(int opcode, String owner, String name, String desc) {
        boolean itf = opcode == 185;
        Item i = this.cw.newMethodItem(owner, name, desc, itf);
        int argSize = i.intVal;
        if (itf) {
            if (argSize == 0) {
                i.intVal = argSize = Type.getArgumentsAndReturnSizes(desc);
            }
            this.code.put12(185, i.index).put11(argSize >> 2, 0);
        } else {
            this.code.put12(opcode, i.index);
        }
    }

    public void visitJumpInsn(int opcode, Label label) {
        if ((label.status & 2) != 0 && label.position - this.code.length < Short.MIN_VALUE) {
            throw new UnsupportedOperationException();
        }
        this.code.putByte(opcode);
        label.put(this, this.code, this.code.length - 1, opcode == 200);
    }

    public void visitLabel(Label label) {
        label.resolve(this, this.code.length, this.code.data);
    }

    public void visitLdcInsn(Object cst) {
        Item i = this.cw.newConstItem(cst);
        int index = i.index;
        if (i.type == 5 || i.type == 6) {
            this.code.put12(20, index);
        } else if (index >= 256) {
            this.code.put12(19, index);
        } else {
            this.code.put11(18, index);
        }
    }

    public void visitIincInsn(int var, int increment) {
        this.code.putByte(132).put11(var, increment);
    }

    public void visitMaxs(int maxStack, int maxLocals) {
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
    }

    public void visitEnd() {
    }

    final int getSize() {
        int size = 8;
        if (this.code.length > 0) {
            this.cw.newUTF8("Code");
            size += 18 + this.code.length + 0;
        }
        if (this.exceptionCount > 0) {
            this.cw.newUTF8("Exceptions");
            size += 8 + 2 * this.exceptionCount;
        }
        return size;
    }

    final void put(ByteVector out) {
        int mask = 393216;
        out.putShort(this.access & 0xFFF9FFFF).putShort(this.name).putShort(this.desc);
        int attributeCount = 0;
        if (this.code.length > 0) {
            ++attributeCount;
        }
        if (this.exceptionCount > 0) {
            ++attributeCount;
        }
        out.putShort(attributeCount);
        if (this.code.length > 0) {
            int size = 12 + this.code.length + 0;
            out.putShort(this.cw.newUTF8("Code")).putInt(size);
            out.putShort(this.maxStack).putShort(this.maxLocals);
            out.putInt(this.code.length).putByteArray(this.code.data, 0, this.code.length);
            out.putShort(0);
            attributeCount = 0;
            out.putShort(attributeCount);
        }
        if (this.exceptionCount > 0) {
            out.putShort(this.cw.newUTF8("Exceptions")).putInt(2 * this.exceptionCount + 2);
            out.putShort(this.exceptionCount);
            for (int i = 0; i < this.exceptionCount; ++i) {
                out.putShort(this.exceptions[i]);
            }
        }
    }
}

