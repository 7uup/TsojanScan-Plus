/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHBlock;
import bsh.BSHFormalParameter;
import bsh.BlockNameSpace;
import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.InterpreterError;
import bsh.Modifiers;
import bsh.NameSpace;
import bsh.Node;
import bsh.Primitive;
import bsh.ReturnControl;
import bsh.SimpleNode;
import bsh.TargetError;
import bsh.Types;
import bsh.UtilEvalError;
import java.util.Vector;

class BSHTryStatement
extends SimpleNode {
    BSHTryStatement(int id) {
        super(id);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        BSHBlock tryBlock = (BSHBlock)this.jjtGetChild(0);
        Vector<Node> catchParams = new Vector<Node>();
        Vector<Node> catchBlocks = new Vector<Node>();
        int nchild = this.jjtGetNumChildren();
        Node node = null;
        int i = 1;
        while (i < nchild && (node = this.jjtGetChild(i++)) instanceof BSHFormalParameter) {
            catchParams.addElement(node);
            catchBlocks.addElement(this.jjtGetChild(i++));
            node = null;
        }
        BSHBlock finallyBlock = null;
        if (node != null) {
            finallyBlock = (BSHBlock)node;
        }
        TargetError target = null;
        Throwable thrown = null;
        Object ret = null;
        int callstackDepth = callstack.depth();
        try {
            ret = tryBlock.eval(callstack, interpreter);
        } catch (TargetError e) {
            target = e;
            String stackInfo = "Bsh Stack: ";
            while (callstack.depth() > callstackDepth) {
                stackInfo = stackInfo + "\t" + callstack.pop() + "\n";
            }
        }
        if (target != null) {
            thrown = target.getTarget();
        }
        if (thrown != null) {
            int n = catchParams.size();
            for (i = 0; i < n; ++i) {
                BSHFormalParameter fp = (BSHFormalParameter)catchParams.elementAt(i);
                fp.eval(callstack, interpreter);
                if (fp.type == null && interpreter.getStrictJava()) {
                    throw new EvalError("(Strict Java) Untyped catch block", this, callstack);
                }
                if (fp.type != null) {
                    try {
                        thrown = (Throwable)Types.castObject(thrown, fp.type, 1);
                    } catch (UtilEvalError e) {
                        continue;
                    }
                }
                BSHBlock cb = (BSHBlock)catchBlocks.elementAt(i);
                NameSpace enclosingNameSpace = callstack.top();
                BlockNameSpace cbNameSpace = new BlockNameSpace(enclosingNameSpace);
                try {
                    if (fp.type == BSHFormalParameter.UNTYPED) {
                        cbNameSpace.setBlockVariable(fp.name, thrown);
                    } else {
                        Modifiers modifiers = new Modifiers();
                        cbNameSpace.setTypedVariable(fp.name, fp.type, (Object)thrown, new Modifiers());
                    }
                } catch (UtilEvalError e) {
                    throw new InterpreterError("Unable to set var in catch block namespace.");
                }
                callstack.swap(cbNameSpace);
                try {
                    ret = cb.eval(callstack, interpreter);
                } finally {
                    callstack.swap(enclosingNameSpace);
                }
                target = null;
                break;
            }
        }
        if (finallyBlock != null) {
            ret = finallyBlock.eval(callstack, interpreter);
        }
        if (target != null) {
            throw target;
        }
        if (ret instanceof ReturnControl) {
            return ret;
        }
        return Primitive.VOID;
    }
}

