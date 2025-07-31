/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.CallStack;
import bsh.NameSpace;
import bsh.SimpleNode;

public class EvalError
extends Exception {
    SimpleNode node;
    String message;
    CallStack callstack;

    public EvalError(String s2, SimpleNode node, CallStack callstack) {
        this.setMessage(s2);
        this.node = node;
        if (callstack != null) {
            this.callstack = callstack.copy();
        }
    }

    public String toString() {
        String trace = this.node != null ? " : at Line: " + this.node.getLineNumber() + " : in file: " + this.node.getSourceFile() + " : " + this.node.getText() : ": <at unknown location>";
        if (this.callstack != null) {
            trace = trace + "\n" + this.getScriptStackTrace();
        }
        return this.getMessage() + trace;
    }

    public void reThrow(String msg) throws EvalError {
        this.prependMessage(msg);
        throw this;
    }

    SimpleNode getNode() {
        return this.node;
    }

    void setNode(SimpleNode node) {
        this.node = node;
    }

    public String getErrorText() {
        if (this.node != null) {
            return this.node.getText();
        }
        return "<unknown error>";
    }

    public int getErrorLineNumber() {
        if (this.node != null) {
            return this.node.getLineNumber();
        }
        return -1;
    }

    public String getErrorSourceFile() {
        if (this.node != null) {
            return this.node.getSourceFile();
        }
        return "<unknown file>";
    }

    public String getScriptStackTrace() {
        if (this.callstack == null) {
            return "<Unknown>";
        }
        String trace = "";
        CallStack stack = this.callstack.copy();
        while (stack.depth() > 0) {
            NameSpace ns = stack.pop();
            SimpleNode node = ns.getNode();
            if (!ns.isMethod) continue;
            trace = trace + "\nCalled from method: " + ns.getName();
            if (node == null) continue;
            trace = trace + " : at Line: " + node.getLineNumber() + " : in file: " + node.getSourceFile() + " : " + node.getText();
        }
        return trace;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String s2) {
        this.message = s2;
    }

    protected void prependMessage(String s2) {
        if (s2 == null) {
            return;
        }
        this.message = this.message == null ? s2 : s2 + " : " + this.message;
    }
}

