/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHType;
import bsh.BSHVariableDeclarator;
import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.Modifiers;
import bsh.NameSpace;
import bsh.Primitive;
import bsh.SimpleNode;
import bsh.UtilEvalError;

class BSHTypedVariableDeclaration
extends SimpleNode {
    public Modifiers modifiers;

    BSHTypedVariableDeclaration(int id) {
        super(id);
    }

    private BSHType getTypeNode() {
        return (BSHType)this.jjtGetChild(0);
    }

    Class evalType(CallStack callstack, Interpreter interpreter) throws EvalError {
        BSHType typeNode = this.getTypeNode();
        return typeNode.getType(callstack, interpreter);
    }

    BSHVariableDeclarator[] getDeclarators() {
        int n = this.jjtGetNumChildren();
        int start = 1;
        BSHVariableDeclarator[] bvda = new BSHVariableDeclarator[n - start];
        for (int i = start; i < n; ++i) {
            bvda[i - start] = (BSHVariableDeclarator)this.jjtGetChild(i);
        }
        return bvda;
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        try {
            NameSpace namespace = callstack.top();
            BSHType typeNode = this.getTypeNode();
            Class type = typeNode.getType(callstack, interpreter);
            BSHVariableDeclarator[] bvda = this.getDeclarators();
            for (int i = 0; i < bvda.length; ++i) {
                BSHVariableDeclarator dec = bvda[i];
                Object value = dec.eval(typeNode, callstack, interpreter);
                try {
                    namespace.setTypedVariable(dec.name, type, value, this.modifiers);
                    continue;
                } catch (UtilEvalError e) {
                    throw e.toEvalError(this, callstack);
                }
            }
        } catch (EvalError e) {
            e.reThrow("Typed variable declaration");
        }
        return Primitive.VOID;
    }

    public String getTypeDescriptor(CallStack callstack, Interpreter interpreter, String defaultPackage) {
        return this.getTypeNode().getTypeDescriptor(callstack, interpreter, defaultPackage);
    }
}

