/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHIfStatement;
import bsh.BlockNameSpace;
import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.NameSpace;
import bsh.ParserConstants;
import bsh.Primitive;
import bsh.ReturnControl;
import bsh.SimpleNode;

class BSHForStatement
extends SimpleNode
implements ParserConstants {
    public boolean hasForInit;
    public boolean hasExpression;
    public boolean hasForUpdate;
    private SimpleNode forInit;
    private SimpleNode expression;
    private SimpleNode forUpdate;
    private SimpleNode statement;
    private boolean parsed;

    BSHForStatement(int id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        boolean cond;
        int i = 0;
        if (this.hasForInit) {
            this.forInit = (SimpleNode)this.jjtGetChild(i++);
        }
        if (this.hasExpression) {
            this.expression = (SimpleNode)this.jjtGetChild(i++);
        }
        if (this.hasForUpdate) {
            this.forUpdate = (SimpleNode)this.jjtGetChild(i++);
        }
        if (i < this.jjtGetNumChildren()) {
            this.statement = (SimpleNode)this.jjtGetChild(i);
        }
        NameSpace enclosingNameSpace = callstack.top();
        BlockNameSpace forNameSpace = new BlockNameSpace(enclosingNameSpace);
        callstack.swap(forNameSpace);
        if (this.hasForInit) {
            this.forInit.eval(callstack, interpreter);
        }
        Object returnControl = Primitive.VOID;
        while (!this.hasExpression || (cond = BSHIfStatement.evaluateCondition(this.expression, callstack, interpreter))) {
            Object ret;
            boolean breakout = false;
            if (this.statement != null && (ret = this.statement.eval(callstack, interpreter)) instanceof ReturnControl) {
                switch (((ReturnControl)ret).kind) {
                    case 46: {
                        returnControl = ret;
                        breakout = true;
                        break;
                    }
                    case 19: {
                        break;
                    }
                    case 12: {
                        breakout = true;
                    }
                }
            }
            if (breakout) break;
            if (!this.hasForUpdate) continue;
            this.forUpdate.eval(callstack, interpreter);
        }
        callstack.swap(enclosingNameSpace);
        return returnControl;
    }
}

