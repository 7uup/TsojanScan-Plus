/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.org.objectweb.asm;

import bsh.org.objectweb.asm.Label;

class Edge {
    int stackSize;
    Label successor;
    Edge next;
    Edge poolNext;

    Edge() {
    }
}

