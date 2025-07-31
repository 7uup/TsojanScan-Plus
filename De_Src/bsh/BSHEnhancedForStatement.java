/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHType;
import bsh.BlockNameSpace;
import bsh.BshIterator;
import bsh.CallStack;
import bsh.CollectionManager;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.Modifiers;
import bsh.NameSpace;
import bsh.ParserConstants;
import bsh.Primitive;
import bsh.ReturnControl;
import bsh.SimpleNode;
import bsh.UtilEvalError;

class BSHEnhancedForStatement
extends SimpleNode
implements ParserConstants {
    String varName;

    BSHEnhancedForStatement(int id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        SimpleNode expression;
        Class elementType = null;
        SimpleNode statement = null;
        NameSpace enclosingNameSpace = callstack.top();
        SimpleNode firstNode = (SimpleNode)this.jjtGetChild(0);
        int nodeCount = this.jjtGetNumChildren();
        if (firstNode instanceof BSHType) {
            elementType = ((BSHType)firstNode).getType(callstack, interpreter);
            expression = (SimpleNode)this.jjtGetChild(1);
            if (nodeCount > 2) {
                statement = (SimpleNode)this.jjtGetChild(2);
            }
        } else {
            expression = firstNode;
            if (nodeCount > 1) {
                statement = (SimpleNode)this.jjtGetChild(1);
            }
        }
        BlockNameSpace eachNameSpace = new BlockNameSpace(enclosingNameSpace);
        callstack.swap(eachNameSpace);
        Object iteratee = expression.eval(callstack, interpreter);
        if (iteratee == Primitive.NULL) {
            throw new EvalError("The collection, array, map, iterator, or enumeration portion of a for statement cannot be null.", this, callstack);
        }
        CollectionManager cm = CollectionManager.getCollectionManager();
        if (!cm.isBshIterable(iteratee)) {
            throw new EvalError("Can't iterate over type: " + iteratee.getClass(), this, callstack);
        }
        BshIterator iterator2 = cm.getBshIterator(iteratee);
        Object returnControl = Primitive.VOID;
        while (iterator2.hasNext()) {
            Object ret;
            try {
                if (elementType != null) {
                    eachNameSpace.setTypedVariable(this.varName, elementType, iterator2.next(), new Modifiers());
                } else {
                    eachNameSpace.setVariable(this.varName, iterator2.next(), false);
                }
            } catch (UtilEvalError e) {
                throw e.toEvalError("for loop iterator variable:" + this.varName, this, callstack);
            }
            boolean breakout = false;
            if (statement != null && (ret = statement.eval(callstack, interpreter)) instanceof ReturnControl) {
                switch (((ReturnControl)ret).kind) {
                    case 46: {
                        returnControl = ret;
                        breakout = true;
                        break;
                    }
                    case 19: {
                        break;
                    }
                    case 12: {
                        breakout = true;
                    }
                }
            }
            if (!breakout) continue;
            break;
        }
        callstack.swap(enclosingNameSpace);
        return returnControl;
    }
}

