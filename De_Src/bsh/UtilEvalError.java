/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.SimpleNode;

public class UtilEvalError
extends Exception {
    protected UtilEvalError() {
    }

    public UtilEvalError(String s2) {
        super(s2);
    }

    public EvalError toEvalError(String msg, SimpleNode node, CallStack callstack) {
        if (Interpreter.DEBUG) {
            this.printStackTrace();
        }
        msg = msg == null ? "" : msg + ": ";
        return new EvalError(msg + this.getMessage(), node, callstack);
    }

    public EvalError toEvalError(SimpleNode node, CallStack callstack) {
        return this.toEvalError(null, node, callstack);
    }
}

