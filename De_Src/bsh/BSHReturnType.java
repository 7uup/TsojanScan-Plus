/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHType;
import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.SimpleNode;

class BSHReturnType
extends SimpleNode {
    public boolean isVoid;

    BSHReturnType(int id) {
        super(id);
    }

    BSHType getTypeNode() {
        return (BSHType)this.jjtGetChild(0);
    }

    public String getTypeDescriptor(CallStack callstack, Interpreter interpreter, String defaultPackage) {
        if (this.isVoid) {
            return "V";
        }
        return this.getTypeNode().getTypeDescriptor(callstack, interpreter, defaultPackage);
    }

    public Class evalReturnType(CallStack callstack, Interpreter interpreter) throws EvalError {
        if (this.isVoid) {
            return Void.TYPE;
        }
        return this.getTypeNode().getType(callstack, interpreter);
    }
}

