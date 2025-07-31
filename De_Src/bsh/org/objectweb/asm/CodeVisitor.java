/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.org.objectweb.asm;

import bsh.org.objectweb.asm.Label;

public interface CodeVisitor {
    public void visitInsn(int var1);

    public void visitIntInsn(int var1, int var2);

    public void visitVarInsn(int var1, int var2);

    public void visitTypeInsn(int var1, String var2);

    public void visitFieldInsn(int var1, String var2, String var3, String var4);

    public void visitMethodInsn(int var1, String var2, String var3, String var4);

    public void visitJumpInsn(int var1, Label var2);

    public void visitLabel(Label var1);

    public void visitLdcInsn(Object var1);

    public void visitIincInsn(int var1, int var2);

    public void visitTableSwitchInsn(int var1, int var2, Label var3, Label[] var4);

    public void visitLookupSwitchInsn(Label var1, int[] var2, Label[] var3);

    public void visitMultiANewArrayInsn(String var1, int var2);

    public void visitTryCatchBlock(Label var1, Label var2, Label var3, String var4);

    public void visitMaxs(int var1, int var2);

    public void visitLocalVariable(String var1, String var2, Label var3, Label var4, int var5);

    public void visitLineNumber(int var1, Label var2);
}

