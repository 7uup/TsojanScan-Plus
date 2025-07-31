/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHIfStatement;
import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.SimpleNode;

class BSHTernaryExpression
extends SimpleNode {
    BSHTernaryExpression(int id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        SimpleNode cond = (SimpleNode)this.jjtGetChild(0);
        SimpleNode evalTrue = (SimpleNode)this.jjtGetChild(1);
        SimpleNode evalFalse = (SimpleNode)this.jjtGetChild(2);
        if (BSHIfStatement.evaluateCondition(cond, callstack, interpreter)) {
            return evalTrue.eval(callstack, interpreter);
        }
        return evalFalse.eval(callstack, interpreter);
    }
}

