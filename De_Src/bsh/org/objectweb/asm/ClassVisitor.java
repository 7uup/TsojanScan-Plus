/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.org.objectweb.asm;

import bsh.org.objectweb.asm.CodeVisitor;

public interface ClassVisitor {
    public void visit(int var1, String var2, String var3, String[] var4, String var5);

    public void visitInnerClass(String var1, String var2, String var3, int var4);

    public void visitField(int var1, String var2, String var3, Object var4);

    public CodeVisitor visitMethod(int var1, String var2, String var3, String[] var4);

    public void visitEnd();
}

