/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHIfStatement;
import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.ParserConstants;
import bsh.Primitive;
import bsh.ReturnControl;
import bsh.SimpleNode;

class BSHWhileStatement
extends SimpleNode
implements ParserConstants {
    public boolean isDoStatement;

    BSHWhileStatement(int id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        SimpleNode condExp;
        int numChild = this.jjtGetNumChildren();
        SimpleNode body = null;
        if (this.isDoStatement) {
            condExp = (SimpleNode)this.jjtGetChild(1);
            body = (SimpleNode)this.jjtGetChild(0);
        } else {
            condExp = (SimpleNode)this.jjtGetChild(0);
            if (numChild > 1) {
                body = (SimpleNode)this.jjtGetChild(1);
            }
        }
        boolean doOnceFlag = this.isDoStatement;
        block5: while (doOnceFlag || BSHIfStatement.evaluateCondition(condExp, callstack, interpreter)) {
            if (body == null) continue;
            Object ret = body.eval(callstack, interpreter);
            boolean breakout = false;
            if (ret instanceof ReturnControl) {
                switch (((ReturnControl)ret).kind) {
                    case 46: {
                        return ret;
                    }
                    case 19: {
                        continue block5;
                    }
                    case 12: {
                        breakout = true;
                    }
                }
            }
            if (breakout) break;
            doOnceFlag = false;
        }
        return Primitive.VOID;
    }
}

