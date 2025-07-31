/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BshMethod;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.NameSpace;
import bsh.This;
import bsh.UtilEvalError;

class BlockNameSpace
extends NameSpace {
    public BlockNameSpace(NameSpace parent) throws EvalError {
        super(parent, parent.getName() + "/BlockNameSpace");
    }

    public void setVariable(String name, Object value, boolean strictJava, boolean recurse) throws UtilEvalError {
        if (this.weHaveVar(name)) {
            super.setVariable(name, value, strictJava, false);
        } else {
            this.getParent().setVariable(name, value, strictJava, recurse);
        }
    }

    public void setBlockVariable(String name, Object value) throws UtilEvalError {
        super.setVariable(name, value, false, false);
    }

    private boolean weHaveVar(String name) {
        try {
            return super.getVariableImpl(name, false) != null;
        } catch (UtilEvalError e) {
            return false;
        }
    }

    private NameSpace getNonBlockParent() {
        NameSpace parent = super.getParent();
        if (parent instanceof BlockNameSpace) {
            return ((BlockNameSpace)parent).getNonBlockParent();
        }
        return parent;
    }

    public This getThis(Interpreter declaringInterpreter) {
        return this.getNonBlockParent().getThis(declaringInterpreter);
    }

    public This getSuper(Interpreter declaringInterpreter) {
        return this.getNonBlockParent().getSuper(declaringInterpreter);
    }

    public void importClass(String name) {
        this.getParent().importClass(name);
    }

    public void importPackage(String name) {
        this.getParent().importPackage(name);
    }

    public void setMethod(String name, BshMethod method) throws UtilEvalError {
        this.getParent().setMethod(name, method);
    }
}

