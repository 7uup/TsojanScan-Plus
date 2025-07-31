/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import java.io.Serializable;
import java.util.Hashtable;

public class Modifiers
implements Serializable {
    public static final int CLASS = 0;
    public static final int METHOD = 1;
    public static final int FIELD = 2;
    Hashtable modifiers;

    public void addModifier(int context, String name) {
        Class<Void> existing;
        if (this.modifiers == null) {
            this.modifiers = new Hashtable();
        }
        if ((existing = this.modifiers.put(name, Void.TYPE)) != null) {
            throw new IllegalStateException("Duplicate modifier: " + name);
        }
        int count = 0;
        if (this.hasModifier("private")) {
            ++count;
        }
        if (this.hasModifier("protected")) {
            ++count;
        }
        if (this.hasModifier("public")) {
            ++count;
        }
        if (count > 1) {
            throw new IllegalStateException("public/private/protected cannot be used in combination.");
        }
        switch (context) {
            case 0: {
                this.validateForClass();
                break;
            }
            case 1: {
                this.validateForMethod();
                break;
            }
            case 2: {
                this.validateForField();
            }
        }
    }

    public boolean hasModifier(String name) {
        if (this.modifiers == null) {
            this.modifiers = new Hashtable();
        }
        return this.modifiers.get(name) != null;
    }

    private void validateForMethod() {
        this.insureNo("volatile", "Method");
        this.insureNo("transient", "Method");
    }

    private void validateForField() {
        this.insureNo("synchronized", "Variable");
        this.insureNo("native", "Variable");
        this.insureNo("abstract", "Variable");
    }

    private void validateForClass() {
        this.validateForMethod();
        this.insureNo("native", "Class");
        this.insureNo("synchronized", "Class");
    }

    private void insureNo(String modifier, String context) {
        if (this.hasModifier(modifier)) {
            throw new IllegalStateException(context + " cannot be declared '" + modifier + "'");
        }
    }

    public String toString() {
        return "Modifiers: " + this.modifiers;
    }
}

