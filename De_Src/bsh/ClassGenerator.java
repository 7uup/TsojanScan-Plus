/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHBlock;
import bsh.BshClassManager;
import bsh.CallStack;
import bsh.Capabilities;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.Modifiers;
import bsh.NameSpace;
import bsh.ReflectError;
import bsh.UtilEvalError;
import java.lang.reflect.InvocationTargetException;

public abstract class ClassGenerator {
    private static ClassGenerator cg;

    public static ClassGenerator getClassGenerator() throws UtilEvalError {
        if (cg == null) {
            try {
                Class<?> clas = Class.forName("bsh.ClassGeneratorImpl");
                cg = (ClassGenerator)clas.newInstance();
            } catch (Exception e) {
                throw new Capabilities.Unavailable("ClassGenerator unavailable: " + e);
            }
        }
        return cg;
    }

    public abstract Class generateClass(String var1, Modifiers var2, Class[] var3, Class var4, BSHBlock var5, boolean var6, CallStack var7, Interpreter var8) throws EvalError;

    public abstract Object invokeSuperclassMethod(BshClassManager var1, Object var2, String var3, Object[] var4) throws UtilEvalError, ReflectError, InvocationTargetException;

    public abstract void setInstanceNameSpaceParent(Object var1, String var2, NameSpace var3);
}

