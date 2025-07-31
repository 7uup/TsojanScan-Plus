/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHType;
import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.SimpleNode;

class BSHFormalParameter
extends SimpleNode {
    public static final Class UNTYPED = null;
    public String name;
    public Class type;

    BSHFormalParameter(int id) {
        super(id);
    }

    public String getTypeDescriptor(CallStack callstack, Interpreter interpreter, String defaultPackage) {
        if (this.jjtGetNumChildren() > 0) {
            return ((BSHType)this.jjtGetChild(0)).getTypeDescriptor(callstack, interpreter, defaultPackage);
        }
        return "Ljava/lang/Object;";
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        this.type = this.jjtGetNumChildren() > 0 ? ((BSHType)this.jjtGetChild(0)).getType(callstack, interpreter) : UNTYPED;
        return this.type;
    }
}

