/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.Primitive;
import bsh.SimpleNode;

class BSHStatementExpressionList
extends SimpleNode {
    BSHStatementExpressionList(int id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        int n = this.jjtGetNumChildren();
        for (int i = 0; i < n; ++i) {
            SimpleNode node = (SimpleNode)this.jjtGetChild(i);
            node.eval(callstack, interpreter);
        }
        return Primitive.VOID;
    }
}

