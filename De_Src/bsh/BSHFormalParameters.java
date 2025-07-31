/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHFormalParameter;
import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.SimpleNode;

class BSHFormalParameters
extends SimpleNode {
    private String[] paramNames;
    Class[] paramTypes;
    int numArgs;
    String[] typeDescriptors;

    BSHFormalParameters(int id) {
        super(id);
    }

    void insureParsed() {
        if (this.paramNames != null) {
            return;
        }
        this.numArgs = this.jjtGetNumChildren();
        String[] paramNames = new String[this.numArgs];
        for (int i = 0; i < this.numArgs; ++i) {
            BSHFormalParameter param = (BSHFormalParameter)this.jjtGetChild(i);
            paramNames[i] = param.name;
        }
        this.paramNames = paramNames;
    }

    public String[] getParamNames() {
        this.insureParsed();
        return this.paramNames;
    }

    public String[] getTypeDescriptors(CallStack callstack, Interpreter interpreter, String defaultPackage) {
        if (this.typeDescriptors != null) {
            return this.typeDescriptors;
        }
        this.insureParsed();
        String[] typeDesc = new String[this.numArgs];
        for (int i = 0; i < this.numArgs; ++i) {
            BSHFormalParameter param = (BSHFormalParameter)this.jjtGetChild(i);
            typeDesc[i] = param.getTypeDescriptor(callstack, interpreter, defaultPackage);
        }
        this.typeDescriptors = typeDesc;
        return typeDesc;
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        if (this.paramTypes != null) {
            return this.paramTypes;
        }
        this.insureParsed();
        Class[] paramTypes = new Class[this.numArgs];
        for (int i = 0; i < this.numArgs; ++i) {
            BSHFormalParameter param = (BSHFormalParameter)this.jjtGetChild(i);
            paramTypes[i] = (Class)param.eval(callstack, interpreter);
        }
        this.paramTypes = paramTypes;
        return paramTypes;
    }
}

