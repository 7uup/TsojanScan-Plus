/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.CallStack;
import bsh.EvalError;
import bsh.SimpleNode;
import bsh.TargetError;
import bsh.UtilEvalError;

public class UtilTargetError
extends UtilEvalError {
    public Throwable t;

    public UtilTargetError(String message, Throwable t) {
        super(message);
        this.t = t;
    }

    public UtilTargetError(Throwable t) {
        this(null, t);
    }

    public EvalError toEvalError(String msg, SimpleNode node, CallStack callstack) {
        msg = msg == null ? this.getMessage() : msg + ": " + this.getMessage();
        return new TargetError(msg, this.t, node, callstack, false);
    }
}

