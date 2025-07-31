/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.asm;

import com.alibaba.fastjson.asm.ByteVector;
import com.alibaba.fastjson.asm.MethodWriter;

public class Label {
    int status;
    int position;
    private int referenceCount;
    private int[] srcAndRefPositions;
    static final int FORWARD_REFERENCE_TYPE_MASK = -268435456;
    static final int FORWARD_REFERENCE_HANDLE_MASK = 0xFFFFFFF;
    static final int FORWARD_REFERENCE_TYPE_SHORT = 0x10000000;
    static final int FORWARD_REFERENCE_TYPE_WIDE = 0x20000000;
    int inputStackTop;
    int outputStackMax;
    Label successor;
    Label next;

    void put(MethodWriter owner, ByteVector out, int source2, boolean wideOffset) {
        if ((this.status & 2) == 0) {
            if (wideOffset) {
                this.addReference(source2, out.length, 0x20000000);
                out.putInt(-1);
            } else {
                this.addReference(source2, out.length, 0x10000000);
                out.putShort(-1);
            }
        } else if (wideOffset) {
            out.putInt(this.position - source2);
        } else {
            out.putShort(this.position - source2);
        }
    }

    private void addReference(int sourcePosition, int referencePosition, int referenceType) {
        if (this.srcAndRefPositions == null) {
            this.srcAndRefPositions = new int[6];
        }
        if (this.referenceCount >= this.srcAndRefPositions.length) {
            int[] a = new int[this.srcAndRefPositions.length + 6];
            System.arraycopy(this.srcAndRefPositions, 0, a, 0, this.srcAndRefPositions.length);
            this.srcAndRefPositions = a;
        }
        this.srcAndRefPositions[this.referenceCount++] = sourcePosition;
        this.srcAndRefPositions[this.referenceCount++] = referencePosition | referenceType;
    }

    void resolve(MethodWriter owner, int position, byte[] data) {
        this.status |= 2;
        this.position = position;
        int i = 0;
        while (i < this.referenceCount) {
            int source2 = this.srcAndRefPositions[i++];
            int reference = this.srcAndRefPositions[i++];
            int handle = reference & 0xFFFFFFF;
            int offset = position - source2;
            if ((reference & 0xF0000000) == 0x10000000) {
                data[handle++] = (byte)(offset >>> 8);
                data[handle] = (byte)offset;
                continue;
            }
            data[handle++] = (byte)(offset >>> 24);
            data[handle++] = (byte)(offset >>> 16);
            data[handle++] = (byte)(offset >>> 8);
            data[handle] = (byte)offset;
        }
    }
}

