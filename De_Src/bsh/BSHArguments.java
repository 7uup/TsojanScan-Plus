/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.Primitive;
import bsh.SimpleNode;

class BSHArguments
extends SimpleNode {
    BSHArguments(int id) {
        super(id);
    }

    public Object[] getArguments(CallStack callstack, Interpreter interpreter) throws EvalError {
        Object[] args2 = new Object[this.jjtGetNumChildren()];
        for (int i = 0; i < args2.length; ++i) {
            args2[i] = ((SimpleNode)this.jjtGetChild(i)).eval(callstack, interpreter);
            if (args2[i] != Primitive.VOID) continue;
            throw new EvalError("Undefined argument: " + ((SimpleNode)this.jjtGetChild(i)).getText(), this, callstack);
        }
        return args2;
    }
}

