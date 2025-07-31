/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.InterpreterError;
import bsh.LHS;
import bsh.Name;
import bsh.NameSpace;
import bsh.SimpleNode;
import bsh.UtilEvalError;

class BSHAmbiguousName
extends SimpleNode {
    public String text;

    BSHAmbiguousName(int id) {
        super(id);
    }

    public Name getName(NameSpace namespace) {
        return namespace.getNameResolver(this.text);
    }

    public Object toObject(CallStack callstack, Interpreter interpreter) throws EvalError {
        return this.toObject(callstack, interpreter, false);
    }

    Object toObject(CallStack callstack, Interpreter interpreter, boolean forceClass) throws EvalError {
        try {
            return this.getName(callstack.top()).toObject(callstack, interpreter, forceClass);
        } catch (UtilEvalError e) {
            throw e.toEvalError(this, callstack);
        }
    }

    public Class toClass(CallStack callstack, Interpreter interpreter) throws EvalError {
        try {
            return this.getName(callstack.top()).toClass();
        } catch (ClassNotFoundException e) {
            throw new EvalError(e.getMessage(), this, callstack);
        } catch (UtilEvalError e2) {
            throw e2.toEvalError(this, callstack);
        }
    }

    public LHS toLHS(CallStack callstack, Interpreter interpreter) throws EvalError {
        try {
            return this.getName(callstack.top()).toLHS(callstack, interpreter);
        } catch (UtilEvalError e) {
            throw e.toEvalError(this, callstack);
        }
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        throw new InterpreterError("Don't know how to eval an ambiguous name!  Use toObject() if you want an object.");
    }

    public String toString() {
        return "AmbigousName: " + this.text;
    }
}

