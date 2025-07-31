/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.Primitive;
import bsh.ReturnControl;
import bsh.SimpleNode;

class BSHIfStatement
extends SimpleNode {
    BSHIfStatement(int id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        Object ret = null;
        if (BSHIfStatement.evaluateCondition((SimpleNode)this.jjtGetChild(0), callstack, interpreter)) {
            ret = ((SimpleNode)this.jjtGetChild(1)).eval(callstack, interpreter);
        } else if (this.jjtGetNumChildren() > 2) {
            ret = ((SimpleNode)this.jjtGetChild(2)).eval(callstack, interpreter);
        }
        if (ret instanceof ReturnControl) {
            return ret;
        }
        return Primitive.VOID;
    }

    public static boolean evaluateCondition(SimpleNode condExp, CallStack callstack, Interpreter interpreter) throws EvalError {
        Object obj = condExp.eval(callstack, interpreter);
        if (obj instanceof Primitive) {
            if (obj == Primitive.VOID) {
                throw new EvalError("Condition evaluates to void type", condExp, callstack);
            }
            obj = ((Primitive)obj).getValue();
        }
        if (obj instanceof Boolean) {
            return (Boolean)obj;
        }
        throw new EvalError("Condition must evaluate to a Boolean or boolean.", condExp, callstack);
    }
}

