/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.CollectionManager;
import bsh.Interpreter;
import bsh.InterpreterError;
import bsh.NameSpace;
import bsh.ParserConstants;
import bsh.Primitive;
import bsh.Reflect;
import bsh.ReflectError;
import bsh.ReflectManager;
import bsh.UtilEvalError;
import bsh.UtilTargetError;
import java.io.Serializable;
import java.lang.reflect.Field;

class LHS
implements ParserConstants,
Serializable {
    NameSpace nameSpace;
    boolean localVar;
    static final int VARIABLE = 0;
    static final int FIELD = 1;
    static final int PROPERTY = 2;
    static final int INDEX = 3;
    static final int METHOD_EVAL = 4;
    int type;
    String varName;
    String propName;
    Field field;
    Object object;
    int index;

    LHS(NameSpace nameSpace, String varName, boolean localVar) {
        this.type = 0;
        this.localVar = localVar;
        this.varName = varName;
        this.nameSpace = nameSpace;
    }

    LHS(Field field) {
        this.type = 1;
        this.object = null;
        this.field = field;
    }

    LHS(Object object, Field field) {
        if (object == null) {
            throw new NullPointerException("constructed empty LHS");
        }
        this.type = 1;
        this.object = object;
        this.field = field;
    }

    LHS(Object object, String propName) {
        if (object == null) {
            throw new NullPointerException("constructed empty LHS");
        }
        this.type = 2;
        this.object = object;
        this.propName = propName;
    }

    LHS(Object array, int index) {
        if (array == null) {
            throw new NullPointerException("constructed empty LHS");
        }
        this.type = 3;
        this.object = array;
        this.index = index;
    }

    public Object getValue() throws UtilEvalError {
        if (this.type == 0) {
            return this.nameSpace.getVariable(this.varName);
        }
        if (this.type == 1) {
            try {
                Object o = this.field.get(this.object);
                return Primitive.wrap(o, this.field.getType());
            } catch (IllegalAccessException e2) {
                throw new UtilEvalError("Can't read field: " + this.field);
            }
        }
        if (this.type == 2) {
            CollectionManager cm = CollectionManager.getCollectionManager();
            if (cm.isMap(this.object)) {
                return cm.getFromMap(this.object, this.propName);
            }
            try {
                return Reflect.getObjectProperty(this.object, this.propName);
            } catch (ReflectError e) {
                Interpreter.debug(e.getMessage());
                throw new UtilEvalError("No such property: " + this.propName);
            }
        }
        if (this.type == 3) {
            try {
                return Reflect.getIndex(this.object, this.index);
            } catch (Exception e) {
                throw new UtilEvalError("Array access: " + e);
            }
        }
        throw new InterpreterError("LHS type");
    }

    public Object assign(Object val, boolean strictJava) throws UtilEvalError {
        if (this.type == 0) {
            if (this.localVar) {
                this.nameSpace.setLocalVariable(this.varName, val, strictJava);
            } else {
                this.nameSpace.setVariable(this.varName, val, strictJava);
            }
        } else {
            if (this.type == 1) {
                try {
                    ReflectManager.RMSetAccessible(this.field);
                    this.field.set(this.object, Primitive.unwrap(val));
                    return val;
                } catch (NullPointerException e) {
                    throw new UtilEvalError("LHS (" + this.field.getName() + ") not a static field.");
                } catch (IllegalAccessException e2) {
                    throw new UtilEvalError("LHS (" + this.field.getName() + ") can't access field: " + e2);
                } catch (IllegalArgumentException e3) {
                    String type = val instanceof Primitive ? ((Primitive)val).getType().getName() : val.getClass().getName();
                    throw new UtilEvalError("Argument type mismatch. " + (val == null ? "null" : type) + " not assignable to field " + this.field.getName());
                }
            }
            if (this.type == 2) {
                CollectionManager cm = CollectionManager.getCollectionManager();
                if (cm.isMap(this.object)) {
                    cm.putInMap(this.object, this.propName, Primitive.unwrap(val));
                } else {
                    try {
                        Reflect.setObjectProperty(this.object, this.propName, val);
                    } catch (ReflectError e) {
                        Interpreter.debug("Assignment: " + e.getMessage());
                        throw new UtilEvalError("No such property: " + this.propName);
                    }
                }
            } else if (this.type == 3) {
                try {
                    Reflect.setIndex(this.object, this.index, val);
                } catch (UtilTargetError e1) {
                    throw e1;
                } catch (Exception e) {
                    throw new UtilEvalError("Assignment: " + e.getMessage());
                }
            } else {
                throw new InterpreterError("unknown lhs");
            }
        }
        return val;
    }

    public String toString() {
        return "LHS: " + (this.field != null ? "field = " + this.field.toString() : "") + (this.varName != null ? " varName = " + this.varName : "") + (this.nameSpace != null ? " nameSpace = " + this.nameSpace.toString() : "");
    }
}

