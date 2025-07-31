/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.ParserConstants;
import bsh.SimpleNode;

class ReturnControl
implements ParserConstants {
    public int kind;
    public Object value;
    public SimpleNode returnPoint;

    public ReturnControl(int kind, Object value, SimpleNode returnPoint) {
        this.kind = kind;
        this.value = value;
        this.returnPoint = returnPoint;
    }
}

