/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHType;
import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.ParserConstants;
import bsh.Primitive;
import bsh.SimpleNode;
import bsh.Types;
import bsh.UtilEvalError;

class BSHBinaryExpression
extends SimpleNode
implements ParserConstants {
    public int kind;

    BSHBinaryExpression(int id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        Object obj;
        Object lhs = ((SimpleNode)this.jjtGetChild(0)).eval(callstack, interpreter);
        if (this.kind == 35) {
            if (lhs == Primitive.NULL) {
                return new Primitive(false);
            }
            Class rhs = ((BSHType)this.jjtGetChild(1)).getType(callstack, interpreter);
            if (lhs instanceof Primitive) {
                if (rhs == Primitive.class) {
                    return new Primitive(true);
                }
                return new Primitive(false);
            }
            boolean ret = Types.isJavaBaseAssignable(rhs, lhs.getClass());
            return new Primitive(ret);
        }
        if (this.kind == 98 || this.kind == 99) {
            obj = lhs;
            if (this.isPrimitiveValue(lhs)) {
                obj = ((Primitive)lhs).getValue();
            }
            if (obj instanceof Boolean && !((Boolean)obj).booleanValue()) {
                return new Primitive(false);
            }
        }
        if (this.kind == 96 || this.kind == 97) {
            obj = lhs;
            if (this.isPrimitiveValue(lhs)) {
                obj = ((Primitive)lhs).getValue();
            }
            if (obj instanceof Boolean && ((Boolean)obj).booleanValue()) {
                return new Primitive(true);
            }
        }
        boolean isLhsWrapper = this.isWrapper(lhs);
        Object rhs = ((SimpleNode)this.jjtGetChild(1)).eval(callstack, interpreter);
        boolean isRhsWrapper = this.isWrapper(rhs);
        if (!(!isLhsWrapper && !this.isPrimitiveValue(lhs) || !isRhsWrapper && !this.isPrimitiveValue(rhs) || isLhsWrapper && isRhsWrapper && this.kind == 90)) {
            try {
                return Primitive.binaryOperation(lhs, rhs, this.kind);
            } catch (UtilEvalError e) {
                throw e.toEvalError(this, callstack);
            }
        }
        switch (this.kind) {
            case 90: {
                return new Primitive(lhs == rhs);
            }
            case 95: {
                return new Primitive(lhs != rhs);
            }
            case 102: {
                if (!(lhs instanceof String) && !(rhs instanceof String)) break;
                return lhs.toString() + rhs.toString();
            }
        }
        if (lhs instanceof Primitive || rhs instanceof Primitive) {
            if (lhs == Primitive.VOID || rhs == Primitive.VOID) {
                throw new EvalError("illegal use of undefined variable, class, or 'void' literal", this, callstack);
            }
            if (lhs == Primitive.NULL || rhs == Primitive.NULL) {
                throw new EvalError("illegal use of null value or 'null' literal", this, callstack);
            }
        }
        throw new EvalError("Operator: '" + tokenImage[this.kind] + "' inappropriate for objects", this, callstack);
    }

    private boolean isPrimitiveValue(Object obj) {
        return obj instanceof Primitive && obj != Primitive.VOID && obj != Primitive.NULL;
    }

    private boolean isWrapper(Object obj) {
        return obj instanceof Boolean || obj instanceof Character || obj instanceof Number;
    }
}

