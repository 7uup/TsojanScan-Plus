/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHType;
import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.NameSpace;
import bsh.SimpleNode;
import bsh.Types;
import bsh.UtilEvalError;

class BSHCastExpression
extends SimpleNode {
    public BSHCastExpression(int id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        NameSpace namespace = callstack.top();
        Class toType = ((BSHType)this.jjtGetChild(0)).getType(callstack, interpreter);
        SimpleNode expression = (SimpleNode)this.jjtGetChild(1);
        Object fromValue = expression.eval(callstack, interpreter);
        Class<?> fromType = fromValue.getClass();
        try {
            return Types.castObject(fromValue, toType, 0);
        } catch (UtilEvalError e) {
            throw e.toEvalError(this, callstack);
        }
    }
}

