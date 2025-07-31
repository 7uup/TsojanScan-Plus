/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHClassDeclaration;
import bsh.BlockNameSpace;
import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.NameSpace;
import bsh.Primitive;
import bsh.ReturnControl;
import bsh.SimpleNode;

class BSHBlock
extends SimpleNode {
    public boolean isSynchronized = false;

    BSHBlock(int id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        return this.eval(callstack, interpreter, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Object eval(CallStack callstack, Interpreter interpreter, boolean overrideNamespace) throws EvalError {
        Object ret;
        Object syncValue = null;
        if (this.isSynchronized) {
            SimpleNode exp = (SimpleNode)this.jjtGetChild(0);
            syncValue = exp.eval(callstack, interpreter);
        }
        if (this.isSynchronized) {
            Object object = syncValue;
            synchronized (object) {
                ret = this.evalBlock(callstack, interpreter, overrideNamespace, null);
            }
        } else {
            ret = this.evalBlock(callstack, interpreter, overrideNamespace, null);
        }
        return ret;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    Object evalBlock(CallStack callstack, Interpreter interpreter, boolean overrideNamespace, NodeFilter nodeFilter) throws EvalError {
        Object ret = Primitive.VOID;
        NameSpace enclosingNameSpace = null;
        if (!overrideNamespace) {
            enclosingNameSpace = callstack.top();
            BlockNameSpace bodyNameSpace = new BlockNameSpace(enclosingNameSpace);
            callstack.swap(bodyNameSpace);
        }
        int startChild = this.isSynchronized ? 1 : 0;
        int numChildren = this.jjtGetNumChildren();
        try {
            SimpleNode node;
            int i;
            for (i = startChild; i < numChildren; ++i) {
                node = (SimpleNode)this.jjtGetChild(i);
                if (nodeFilter != null && !nodeFilter.isVisible(node) || !(node instanceof BSHClassDeclaration)) continue;
                node.eval(callstack, interpreter);
            }
            for (i = startChild; i < numChildren; ++i) {
                node = (SimpleNode)this.jjtGetChild(i);
                if (node instanceof BSHClassDeclaration || nodeFilter != null && !nodeFilter.isVisible(node) || !((ret = node.eval(callstack, interpreter)) instanceof ReturnControl)) continue;
                break;
            }
        } finally {
            if (!overrideNamespace) {
                callstack.swap(enclosingNameSpace);
            }
        }
        return ret;
    }

    public static interface NodeFilter {
        public boolean isVisible(SimpleNode var1);
    }
}

