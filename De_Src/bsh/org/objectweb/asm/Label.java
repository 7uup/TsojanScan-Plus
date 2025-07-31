/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.org.objectweb.asm;

import bsh.org.objectweb.asm.ByteVector;
import bsh.org.objectweb.asm.CodeWriter;
import bsh.org.objectweb.asm.Edge;

public class Label {
    CodeWriter owner;
    boolean resolved;
    int position;
    private int referenceCount;
    private int[] srcAndRefPositions;
    int beginStackSize;
    int maxStackSize;
    Edge successors;
    Label next;
    boolean pushed;

    void put(CodeWriter owner, ByteVector out, int source2, boolean wideOffset) {
        if (this.resolved) {
            if (wideOffset) {
                out.put4(this.position - source2);
            } else {
                out.put2(this.position - source2);
            }
        } else if (wideOffset) {
            this.addReference(-1 - source2, out.length);
            out.put4(-1);
        } else {
            this.addReference(source2, out.length);
            out.put2(-1);
        }
    }

    private void addReference(int sourcePosition, int referencePosition) {
        if (this.srcAndRefPositions == null) {
            this.srcAndRefPositions = new int[6];
        }
        if (this.referenceCount >= this.srcAndRefPositions.length) {
            int[] a = new int[this.srcAndRefPositions.length + 6];
            System.arraycopy(this.srcAndRefPositions, 0, a, 0, this.srcAndRefPositions.length);
            this.srcAndRefPositions = a;
        }
        this.srcAndRefPositions[this.referenceCount++] = sourcePosition;
        this.srcAndRefPositions[this.referenceCount++] = referencePosition;
    }

    boolean resolve(CodeWriter owner, int position, byte[] data) {
        boolean needUpdate = false;
        this.resolved = true;
        this.position = position;
        int i = 0;
        while (i < this.referenceCount) {
            int offset;
            int source2 = this.srcAndRefPositions[i++];
            int reference = this.srcAndRefPositions[i++];
            if (source2 >= 0) {
                offset = position - source2;
                if (offset < Short.MIN_VALUE || offset > Short.MAX_VALUE) {
                    int opcode = data[reference - 1] & 0xFF;
                    data[reference - 1] = opcode <= 168 ? (byte)(opcode + 49) : (byte)(opcode + 20);
                    needUpdate = true;
                }
                data[reference++] = (byte)(offset >>> 8);
                data[reference] = (byte)offset;
                continue;
            }
            offset = position + source2 + 1;
            data[reference++] = (byte)(offset >>> 24);
            data[reference++] = (byte)(offset >>> 16);
            data[reference++] = (byte)(offset >>> 8);
            data[reference] = (byte)offset;
        }
        return needUpdate;
    }
}

