/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.InterpreterError;
import bsh.NameSpace;
import java.util.Vector;

public class CallStack {
    private Vector stack = new Vector(2);

    public CallStack() {
    }

    public CallStack(NameSpace namespace) {
        this.push(namespace);
    }

    public void clear() {
        this.stack.removeAllElements();
    }

    public void push(NameSpace ns) {
        this.stack.insertElementAt(ns, 0);
    }

    public NameSpace top() {
        return this.get(0);
    }

    public NameSpace get(int depth) {
        if (depth >= this.depth()) {
            return NameSpace.JAVACODE;
        }
        return (NameSpace)this.stack.elementAt(depth);
    }

    public void set(int depth, NameSpace ns) {
        this.stack.setElementAt(ns, depth);
    }

    public NameSpace pop() {
        if (this.depth() < 1) {
            throw new InterpreterError("pop on empty CallStack");
        }
        NameSpace top = this.top();
        this.stack.removeElementAt(0);
        return top;
    }

    public NameSpace swap(NameSpace newTop) {
        NameSpace oldTop = (NameSpace)this.stack.elementAt(0);
        this.stack.setElementAt(newTop, 0);
        return oldTop;
    }

    public int depth() {
        return this.stack.size();
    }

    public NameSpace[] toArray() {
        Object[] nsa = new NameSpace[this.depth()];
        this.stack.copyInto(nsa);
        return nsa;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("CallStack:\n");
        NameSpace[] nsa = this.toArray();
        for (int i = 0; i < nsa.length; ++i) {
            sb.append("\t" + nsa[i] + "\n");
        }
        return sb.toString();
    }

    public CallStack copy() {
        CallStack cs = new CallStack();
        cs.stack = (Vector)this.stack.clone();
        return cs;
    }
}

