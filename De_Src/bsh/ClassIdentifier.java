/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

public class ClassIdentifier {
    Class clas;

    public ClassIdentifier(Class clas) {
        this.clas = clas;
    }

    public Class getTargetClass() {
        return this.clas;
    }

    public String toString() {
        return "Class Identifier: " + this.clas.getName();
    }
}

