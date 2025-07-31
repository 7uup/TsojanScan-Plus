/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.SimpleNode;
import bsh.TargetError;

class BSHThrowStatement
extends SimpleNode {
    BSHThrowStatement(int id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        Object obj = ((SimpleNode)this.jjtGetChild(0)).eval(callstack, interpreter);
        if (!(obj instanceof Exception)) {
            throw new EvalError("Expression in 'throw' must be Exception type", this, callstack);
        }
        throw new TargetError((Exception)obj, (SimpleNode)this, callstack);
    }
}

