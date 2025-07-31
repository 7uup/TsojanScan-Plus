/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHAmbiguousName;
import bsh.BSHPrimitiveType;
import bsh.BshClassManager;
import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.Name;
import bsh.SimpleNode;
import java.lang.reflect.Array;

class BSHType
extends SimpleNode
implements BshClassManager.Listener {
    private Class baseType;
    private int arrayDims;
    private Class type;
    String descriptor;

    BSHType(int id) {
        super(id);
    }

    public void addArrayDimension() {
        ++this.arrayDims;
    }

    SimpleNode getTypeNode() {
        return (SimpleNode)this.jjtGetChild(0);
    }

    public String getTypeDescriptor(CallStack callstack, Interpreter interpreter, String defaultPackage) {
        String descriptor;
        if (this.descriptor != null) {
            return this.descriptor;
        }
        SimpleNode node = this.getTypeNode();
        if (node instanceof BSHPrimitiveType) {
            descriptor = BSHType.getTypeDescriptor(((BSHPrimitiveType)node).type);
        } else {
            String clasName = ((BSHAmbiguousName)node).text;
            BshClassManager bcm = interpreter.getClassManager();
            String definingClass = bcm.getClassBeingDefined(clasName);
            Class clas = null;
            if (definingClass == null) {
                try {
                    clas = ((BSHAmbiguousName)node).toClass(callstack, interpreter);
                } catch (EvalError e) {}
            } else {
                clasName = definingClass;
            }
            descriptor = clas != null ? BSHType.getTypeDescriptor(clas) : (defaultPackage == null || Name.isCompound(clasName) ? "L" + clasName.replace('.', '/') + ";" : "L" + defaultPackage.replace('.', '/') + "/" + clasName + ";");
        }
        for (int i = 0; i < this.arrayDims; ++i) {
            descriptor = "[" + descriptor;
        }
        this.descriptor = descriptor;
        return descriptor;
    }

    public Class getType(CallStack callstack, Interpreter interpreter) throws EvalError {
        if (this.type != null) {
            return this.type;
        }
        SimpleNode node = this.getTypeNode();
        this.baseType = node instanceof BSHPrimitiveType ? ((BSHPrimitiveType)node).getType() : ((BSHAmbiguousName)node).toClass(callstack, interpreter);
        if (this.arrayDims > 0) {
            try {
                int[] dims = new int[this.arrayDims];
                Object obj = Array.newInstance(this.baseType, dims);
                this.type = obj.getClass();
            } catch (Exception e) {
                throw new EvalError("Couldn't construct array type", this, callstack);
            }
        } else {
            this.type = this.baseType;
        }
        interpreter.getClassManager().addListener(this);
        return this.type;
    }

    public Class getBaseType() {
        return this.baseType;
    }

    public int getArrayDims() {
        return this.arrayDims;
    }

    public void classLoaderChanged() {
        this.type = null;
        this.baseType = null;
    }

    public static String getTypeDescriptor(Class clas) {
        if (clas == Boolean.TYPE) {
            return "Z";
        }
        if (clas == Character.TYPE) {
            return "C";
        }
        if (clas == Byte.TYPE) {
            return "B";
        }
        if (clas == Short.TYPE) {
            return "S";
        }
        if (clas == Integer.TYPE) {
            return "I";
        }
        if (clas == Long.TYPE) {
            return "J";
        }
        if (clas == Float.TYPE) {
            return "F";
        }
        if (clas == Double.TYPE) {
            return "D";
        }
        if (clas == Void.TYPE) {
            return "V";
        }
        String name = clas.getName().replace('.', '/');
        if (name.startsWith("[") || name.endsWith(";")) {
            return name;
        }
        return "L" + name.replace('.', '/') + ";";
    }
}

