/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHPrimaryExpression;
import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.InterpreterError;
import bsh.LHS;
import bsh.ParserConstants;
import bsh.Primitive;
import bsh.SimpleNode;
import bsh.UtilEvalError;

class BSHAssignment
extends SimpleNode
implements ParserConstants {
    public int operator;

    BSHAssignment(int id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        SimpleNode rhsNode;
        Object rhs;
        BSHPrimaryExpression lhsNode = (BSHPrimaryExpression)this.jjtGetChild(0);
        if (lhsNode == null) {
            throw new InterpreterError("Error, null LHSnode");
        }
        boolean strictJava = interpreter.getStrictJava();
        LHS lhs = lhsNode.toLHS(callstack, interpreter);
        if (lhs == null) {
            throw new InterpreterError("Error, null LHS");
        }
        Object lhsValue = null;
        if (this.operator != 81) {
            try {
                lhsValue = lhs.getValue();
            } catch (UtilEvalError e) {
                throw e.toEvalError(this, callstack);
            }
        }
        if ((rhs = (rhsNode = (SimpleNode)this.jjtGetChild(1)).eval(callstack, interpreter)) == Primitive.VOID) {
            throw new EvalError("Void assignment.", this, callstack);
        }
        try {
            switch (this.operator) {
                case 81: {
                    return lhs.assign(rhs, strictJava);
                }
                case 118: {
                    return lhs.assign(this.operation(lhsValue, rhs, 102), strictJava);
                }
                case 119: {
                    return lhs.assign(this.operation(lhsValue, rhs, 103), strictJava);
                }
                case 120: {
                    return lhs.assign(this.operation(lhsValue, rhs, 104), strictJava);
                }
                case 121: {
                    return lhs.assign(this.operation(lhsValue, rhs, 105), strictJava);
                }
                case 122: 
                case 123: {
                    return lhs.assign(this.operation(lhsValue, rhs, 106), strictJava);
                }
                case 124: 
                case 125: {
                    return lhs.assign(this.operation(lhsValue, rhs, 108), strictJava);
                }
                case 126: {
                    return lhs.assign(this.operation(lhsValue, rhs, 110), strictJava);
                }
                case 127: {
                    return lhs.assign(this.operation(lhsValue, rhs, 111), strictJava);
                }
                case 128: 
                case 129: {
                    return lhs.assign(this.operation(lhsValue, rhs, 112), strictJava);
                }
                case 130: 
                case 131: {
                    return lhs.assign(this.operation(lhsValue, rhs, 114), strictJava);
                }
                case 132: 
                case 133: {
                    return lhs.assign(this.operation(lhsValue, rhs, 116), strictJava);
                }
            }
            throw new InterpreterError("unimplemented operator in assignment BSH");
        } catch (UtilEvalError e) {
            throw e.toEvalError(this, callstack);
        }
    }

    private Object operation(Object lhs, Object rhs, int kind) throws UtilEvalError {
        if (lhs instanceof String && rhs != Primitive.VOID) {
            if (kind != 102) {
                throw new UtilEvalError("Use of non + operator with String LHS");
            }
            return (String)lhs + rhs;
        }
        if (lhs instanceof Primitive || rhs instanceof Primitive) {
            if (lhs == Primitive.VOID || rhs == Primitive.VOID) {
                throw new UtilEvalError("Illegal use of undefined object or 'void' literal");
            }
            if (lhs == Primitive.NULL || rhs == Primitive.NULL) {
                throw new UtilEvalError("Illegal use of null object or 'null' literal");
            }
        }
        if ((lhs instanceof Boolean || lhs instanceof Character || lhs instanceof Number || lhs instanceof Primitive) && (rhs instanceof Boolean || rhs instanceof Character || rhs instanceof Number || rhs instanceof Primitive)) {
            return Primitive.binaryOperation(lhs, rhs, kind);
        }
        throw new UtilEvalError("Non primitive value in operator: " + lhs.getClass() + " " + tokenImage[kind] + " " + rhs.getClass());
    }
}

