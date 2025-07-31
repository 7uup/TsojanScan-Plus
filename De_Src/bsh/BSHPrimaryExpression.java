/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHAmbiguousName;
import bsh.BSHPrimarySuffix;
import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.LHS;
import bsh.SimpleNode;
import bsh.UtilEvalError;

class BSHPrimaryExpression
extends SimpleNode {
    BSHPrimaryExpression(int id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        return this.eval(false, callstack, interpreter);
    }

    public LHS toLHS(CallStack callstack, Interpreter interpreter) throws EvalError {
        Object obj = this.eval(true, callstack, interpreter);
        if (!(obj instanceof LHS)) {
            throw new EvalError("Can't assign to:", this, callstack);
        }
        return (LHS)obj;
    }

    private Object eval(boolean toLHS, CallStack callstack, Interpreter interpreter) throws EvalError {
        Object obj = this.jjtGetChild(0);
        int numChildren = this.jjtGetNumChildren();
        for (int i = 1; i < numChildren; ++i) {
            obj = ((BSHPrimarySuffix)this.jjtGetChild(i)).doSuffix(obj, toLHS, callstack, interpreter);
        }
        if (obj instanceof SimpleNode) {
            if (obj instanceof BSHAmbiguousName) {
                obj = toLHS ? ((BSHAmbiguousName)obj).toLHS(callstack, interpreter) : ((BSHAmbiguousName)obj).toObject(callstack, interpreter);
            } else {
                if (toLHS) {
                    throw new EvalError("Can't assign to prefix.", this, callstack);
                }
                obj = ((SimpleNode)obj).eval(callstack, interpreter);
            }
        }
        if (obj instanceof LHS) {
            if (toLHS) {
                return obj;
            }
            try {
                return ((LHS)obj).getValue();
            } catch (UtilEvalError e) {
                throw e.toEvalError(this, callstack);
            }
        }
        return obj;
    }
}

