/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.CallStack;
import bsh.Capabilities;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.InterpreterError;
import bsh.SimpleNode;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

public class TargetError
extends EvalError {
    Throwable target;
    boolean inNativeCode;

    public TargetError(String msg, Throwable t, SimpleNode node, CallStack callstack, boolean inNativeCode) {
        super(msg, node, callstack);
        this.target = t;
        this.inNativeCode = inNativeCode;
    }

    public TargetError(Throwable t, SimpleNode node, CallStack callstack) {
        this("TargetError", t, node, callstack, false);
    }

    public Throwable getTarget() {
        if (this.target instanceof InvocationTargetException) {
            return ((InvocationTargetException)this.target).getTargetException();
        }
        return this.target;
    }

    public String toString() {
        return super.toString() + "\nTarget exception: " + this.printTargetError(this.target);
    }

    public void printStackTrace() {
        this.printStackTrace(false, System.err);
    }

    public void printStackTrace(PrintStream out) {
        this.printStackTrace(false, out);
    }

    public void printStackTrace(boolean debug, PrintStream out) {
        if (debug) {
            super.printStackTrace(out);
            out.println("--- Target Stack Trace ---");
        }
        this.target.printStackTrace(out);
    }

    public String printTargetError(Throwable t) {
        String s2 = this.target.toString();
        if (Capabilities.canGenerateInterfaces()) {
            s2 = s2 + "\n" + this.xPrintTargetError(t);
        }
        return s2;
    }

    public String xPrintTargetError(Throwable t) {
        String getTarget = "import java.lang.reflect.UndeclaredThrowableException;String result=\"\";while ( target instanceof UndeclaredThrowableException ) {\ttarget=target.getUndeclaredThrowable(); \tresult+=\"Nested: \"+target.toString();}return result;";
        Interpreter i = new Interpreter();
        try {
            i.set("target", t);
            return (String)i.eval(getTarget);
        } catch (EvalError e) {
            throw new InterpreterError("xprintarget: " + e.toString());
        }
    }

    public boolean inNativeCode() {
        return this.inNativeCode;
    }
}

