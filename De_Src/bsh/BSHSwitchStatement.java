/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHSwitchLabel;
import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.Node;
import bsh.ParserConstants;
import bsh.Primitive;
import bsh.ReturnControl;
import bsh.SimpleNode;
import bsh.UtilEvalError;

class BSHSwitchStatement
extends SimpleNode
implements ParserConstants {
    public BSHSwitchStatement(int id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        int numchild = this.jjtGetNumChildren();
        int child = 0;
        SimpleNode switchExp = (SimpleNode)this.jjtGetChild(child++);
        Object switchVal = switchExp.eval(callstack, interpreter);
        ReturnControl returnControl = null;
        if (child >= numchild) {
            throw new EvalError("Empty switch statement.", this, callstack);
        }
        BSHSwitchLabel label = (BSHSwitchLabel)this.jjtGetChild(child++);
        block0: while (child < numchild && returnControl == null) {
            Node node;
            if (label.isDefault || this.primitiveEquals(switchVal, label.eval(callstack, interpreter), callstack, switchExp)) {
                while (child < numchild) {
                    Object value;
                    if ((node = this.jjtGetChild(child++)) instanceof BSHSwitchLabel || !((value = ((SimpleNode)node).eval(callstack, interpreter)) instanceof ReturnControl)) continue;
                    returnControl = (ReturnControl)value;
                    continue block0;
                }
                continue;
            }
            while (child < numchild) {
                if (!((node = this.jjtGetChild(child++)) instanceof BSHSwitchLabel)) continue;
                label = (BSHSwitchLabel)node;
                continue block0;
            }
        }
        if (returnControl != null && returnControl.kind == 46) {
            return returnControl;
        }
        return Primitive.VOID;
    }

    private boolean primitiveEquals(Object switchVal, Object targetVal, CallStack callstack, SimpleNode switchExp) throws EvalError {
        if (switchVal instanceof Primitive || targetVal instanceof Primitive) {
            try {
                Object result = Primitive.binaryOperation(switchVal, targetVal, 90);
                result = Primitive.unwrap(result);
                return result.equals(Boolean.TRUE);
            } catch (UtilEvalError e) {
                throw e.toEvalError("Switch value: " + switchExp.getText() + ": ", this, callstack);
            }
        }
        return switchVal.equals(targetVal);
    }
}

