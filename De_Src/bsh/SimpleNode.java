/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.InterpreterError;
import bsh.Node;
import bsh.ParserTreeConstants;
import bsh.Token;

class SimpleNode
implements Node {
    public static SimpleNode JAVACODE = new SimpleNode(-1){

        public String getSourceFile() {
            return "<Called from Java Code>";
        }

        public int getLineNumber() {
            return -1;
        }

        public String getText() {
            return "<Compiled Java Code>";
        }
    };
    protected Node parent;
    protected Node[] children;
    protected int id;
    Token firstToken;
    Token lastToken;
    String sourceFile;

    public SimpleNode(int i) {
        this.id = i;
    }

    public void jjtOpen() {
    }

    public void jjtClose() {
    }

    public void jjtSetParent(Node n) {
        this.parent = n;
    }

    public Node jjtGetParent() {
        return this.parent;
    }

    public void jjtAddChild(Node n, int i) {
        if (this.children == null) {
            this.children = new Node[i + 1];
        } else if (i >= this.children.length) {
            Node[] c = new Node[i + 1];
            System.arraycopy(this.children, 0, c, 0, this.children.length);
            this.children = c;
        }
        this.children[i] = n;
    }

    public Node jjtGetChild(int i) {
        return this.children[i];
    }

    public SimpleNode getChild(int i) {
        return (SimpleNode)this.jjtGetChild(i);
    }

    public int jjtGetNumChildren() {
        return this.children == null ? 0 : this.children.length;
    }

    public String toString() {
        return ParserTreeConstants.jjtNodeName[this.id];
    }

    public String toString(String prefix) {
        return prefix + this.toString();
    }

    public void dump(String prefix) {
        System.out.println(this.toString(prefix));
        if (this.children != null) {
            for (int i = 0; i < this.children.length; ++i) {
                SimpleNode n = (SimpleNode)this.children[i];
                if (n == null) continue;
                n.dump(prefix + " ");
            }
        }
    }

    public void prune() {
        this.jjtSetParent(null);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        throw new InterpreterError("Unimplemented or inappropriate for " + this.getClass().getName());
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public String getSourceFile() {
        if (this.sourceFile == null) {
            if (this.parent != null) {
                return ((SimpleNode)this.parent).getSourceFile();
            }
            return "<unknown file>";
        }
        return this.sourceFile;
    }

    public int getLineNumber() {
        return this.firstToken.beginLine;
    }

    public String getText() {
        StringBuffer text = new StringBuffer();
        Token t = this.firstToken;
        while (t != null) {
            text.append(t.image);
            if (!t.image.equals(".")) {
                text.append(" ");
            }
            if (t == this.lastToken || t.image.equals("{") || t.image.equals(";")) break;
            t = t.next;
        }
        return text.toString();
    }
}

