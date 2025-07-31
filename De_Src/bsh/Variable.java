/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.LHS;
import bsh.Modifiers;
import bsh.Primitive;
import bsh.Types;
import bsh.UtilEvalError;
import java.io.Serializable;

public class Variable
implements Serializable {
    static final int DECLARATION = 0;
    static final int ASSIGNMENT = 1;
    String name;
    Class type = null;
    String typeDescriptor;
    Object value;
    Modifiers modifiers;
    LHS lhs;

    Variable(String name, Class type, LHS lhs) {
        this.name = name;
        this.lhs = lhs;
        this.type = type;
    }

    Variable(String name, Object value, Modifiers modifiers) throws UtilEvalError {
        this(name, (Class)null, value, modifiers);
    }

    Variable(String name, String typeDescriptor, Object value, Modifiers modifiers) throws UtilEvalError {
        this(name, (Class)null, value, modifiers);
        this.typeDescriptor = typeDescriptor;
    }

    Variable(String name, Class type, Object value, Modifiers modifiers) throws UtilEvalError {
        this.name = name;
        this.type = type;
        this.modifiers = modifiers;
        this.setValue(value, 0);
    }

    public void setValue(Object value, int context) throws UtilEvalError {
        if (this.hasModifier("final") && this.value != null) {
            throw new UtilEvalError("Final variable, can't re-assign.");
        }
        if (value == null) {
            value = Primitive.getDefaultValue(this.type);
        }
        if (this.lhs != null) {
            this.lhs.assign(Primitive.unwrap(value), false);
            return;
        }
        if (this.type != null) {
            value = Types.castObject(value, this.type, context == 0 ? 0 : 1);
        }
        this.value = value;
    }

    Object getValue() throws UtilEvalError {
        if (this.lhs != null) {
            return this.type == null ? this.lhs.getValue() : Primitive.wrap(this.lhs.getValue(), this.type);
        }
        return this.value;
    }

    public Class getType() {
        return this.type;
    }

    public String getTypeDescriptor() {
        return this.typeDescriptor;
    }

    public Modifiers getModifiers() {
        return this.modifiers;
    }

    public String getName() {
        return this.name;
    }

    public boolean hasModifier(String name) {
        return this.modifiers != null && this.modifiers.hasModifier(name);
    }

    public String toString() {
        return "Variable: " + super.toString() + " " + this.name + ", type:" + this.type + ", value:" + this.value + ", lhs = " + this.lhs;
    }
}

