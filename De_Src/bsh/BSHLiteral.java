/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.InterpreterError;
import bsh.Primitive;
import bsh.SimpleNode;

class BSHLiteral
extends SimpleNode {
    public Object value;

    BSHLiteral(int id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        if (this.value == null) {
            throw new InterpreterError("Null in bsh literal: " + this.value);
        }
        return this.value;
    }

    private char getEscapeChar(char ch) {
        switch (ch) {
            case 'b': {
                ch = (char)8;
                break;
            }
            case 't': {
                ch = (char)9;
                break;
            }
            case 'n': {
                ch = (char)10;
                break;
            }
            case 'f': {
                ch = (char)12;
                break;
            }
            case 'r': {
                ch = (char)13;
                break;
            }
        }
        return ch;
    }

    public void charSetup(String str) {
        char ch = str.charAt(0);
        if (ch == '\\') {
            ch = str.charAt(1);
            ch = Character.isDigit(ch) ? (char)Integer.parseInt(str.substring(1), 8) : this.getEscapeChar(ch);
        }
        this.value = new Primitive(new Character(ch).charValue());
    }

    void stringSetup(String str) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);
            if (ch == '\\') {
                if (Character.isDigit(ch = str.charAt(++i))) {
                    int endPos;
                    for (endPos = i; endPos < i + 2 && Character.isDigit(str.charAt(endPos + 1)); ++endPos) {
                    }
                    ch = (char)Integer.parseInt(str.substring(i, endPos + 1), 8);
                    i = endPos;
                } else {
                    ch = this.getEscapeChar(ch);
                }
            }
            buffer.append(ch);
        }
        this.value = buffer.toString().intern();
    }
}

