/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHAmbiguousName;
import bsh.BSHBlock;
import bsh.BSHFormalParameters;
import bsh.BSHReturnType;
import bsh.BshMethod;
import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.Modifiers;
import bsh.NameSpace;
import bsh.Node;
import bsh.Primitive;
import bsh.SimpleNode;
import bsh.UtilEvalError;

class BSHMethodDeclaration
extends SimpleNode {
    public String name;
    BSHReturnType returnTypeNode;
    BSHFormalParameters paramsNode;
    BSHBlock blockNode;
    int firstThrowsClause;
    public Modifiers modifiers;
    Class returnType;
    int numThrows = 0;

    BSHMethodDeclaration(int id) {
        super(id);
    }

    synchronized void insureNodesParsed() {
        if (this.paramsNode != null) {
            return;
        }
        Node firstNode = this.jjtGetChild(0);
        this.firstThrowsClause = 1;
        if (firstNode instanceof BSHReturnType) {
            this.returnTypeNode = (BSHReturnType)firstNode;
            this.paramsNode = (BSHFormalParameters)this.jjtGetChild(1);
            if (this.jjtGetNumChildren() > 2 + this.numThrows) {
                this.blockNode = (BSHBlock)this.jjtGetChild(2 + this.numThrows);
            }
            ++this.firstThrowsClause;
        } else {
            this.paramsNode = (BSHFormalParameters)this.jjtGetChild(0);
            this.blockNode = (BSHBlock)this.jjtGetChild(1 + this.numThrows);
        }
    }

    Class evalReturnType(CallStack callstack, Interpreter interpreter) throws EvalError {
        this.insureNodesParsed();
        if (this.returnTypeNode != null) {
            return this.returnTypeNode.evalReturnType(callstack, interpreter);
        }
        return null;
    }

    String getReturnTypeDescriptor(CallStack callstack, Interpreter interpreter, String defaultPackage) {
        this.insureNodesParsed();
        if (this.returnTypeNode == null) {
            return null;
        }
        return this.returnTypeNode.getTypeDescriptor(callstack, interpreter, defaultPackage);
    }

    BSHReturnType getReturnTypeNode() {
        this.insureNodesParsed();
        return this.returnTypeNode;
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        this.returnType = this.evalReturnType(callstack, interpreter);
        this.evalNodes(callstack, interpreter);
        NameSpace namespace = callstack.top();
        BshMethod bshMethod = new BshMethod(this, namespace, this.modifiers);
        try {
            namespace.setMethod(this.name, bshMethod);
        } catch (UtilEvalError e) {
            throw e.toEvalError(this, callstack);
        }
        return Primitive.VOID;
    }

    private void evalNodes(CallStack callstack, Interpreter interpreter) throws EvalError {
        int i;
        this.insureNodesParsed();
        for (i = this.firstThrowsClause; i < this.numThrows + this.firstThrowsClause; ++i) {
            ((BSHAmbiguousName)this.jjtGetChild(i)).toClass(callstack, interpreter);
        }
        this.paramsNode.eval(callstack, interpreter);
        if (interpreter.getStrictJava()) {
            for (i = 0; i < this.paramsNode.paramTypes.length; ++i) {
                if (this.paramsNode.paramTypes[i] != null) continue;
                throw new EvalError("(Strict Java Mode) Undeclared argument type, parameter: " + this.paramsNode.getParamNames()[i] + " in method: " + this.name, this, null);
            }
            if (this.returnType == null) {
                throw new EvalError("(Strict Java Mode) Undeclared return type for method: " + this.name, this, null);
            }
        }
    }

    public String toString() {
        return "MethodDeclaration: " + this.name;
    }
}

