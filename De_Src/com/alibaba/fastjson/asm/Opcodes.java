/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.asm;

public interface Opcodes {
    public static final int T_INT = 10;
    public static final int V1_5 = 49;
    public static final int ACC_PUBLIC = 1;
    public static final int ACC_SUPER = 32;
    public static final int ACONST_NULL = 1;
    public static final int ICONST_0 = 3;
    public static final int ICONST_1 = 4;
    public static final int LCONST_0 = 9;
    public static final int LCONST_1 = 10;
    public static final int FCONST_0 = 11;
    public static final int DCONST_0 = 14;
    public static final int BIPUSH = 16;
    public static final int ILOAD = 21;
    public static final int LLOAD = 22;
    public static final int FLOAD = 23;
    public static final int DLOAD = 24;
    public static final int ALOAD = 25;
    public static final int ISTORE = 54;
    public static final int LSTORE = 55;
    public static final int FSTORE = 56;
    public static final int DSTORE = 57;
    public static final int ASTORE = 58;
    public static final int IASTORE = 79;
    public static final int POP = 87;
    public static final int DUP = 89;
    public static final int IADD = 96;
    public static final int IAND = 126;
    public static final int IOR = 128;
    public static final int LCMP = 148;
    public static final int FCMPL = 149;
    public static final int DCMPL = 151;
    public static final int IFEQ = 153;
    public static final int IFNE = 154;
    public static final int IFLE = 158;
    public static final int IF_ICMPEQ = 159;
    public static final int IF_ICMPNE = 160;
    public static final int IF_ICMPLT = 161;
    public static final int IF_ICMPGE = 162;
    public static final int IF_ICMPGT = 163;
    public static final int IF_ACMPEQ = 165;
    public static final int IF_ACMPNE = 166;
    public static final int GOTO = 167;
    public static final int RET = 169;
    public static final int ARETURN = 176;
    public static final int RETURN = 177;
    public static final int GETSTATIC = 178;
    public static final int GETFIELD = 180;
    public static final int PUTFIELD = 181;
    public static final int INVOKEVIRTUAL = 182;
    public static final int INVOKESPECIAL = 183;
    public static final int INVOKESTATIC = 184;
    public static final int INVOKEINTERFACE = 185;
    public static final int NEW = 187;
    public static final int NEWARRAY = 188;
    public static final int CHECKCAST = 192;
    public static final int INSTANCEOF = 193;
    public static final int IFNULL = 198;
    public static final int IFNONNULL = 199;
    public static final int GOTO_W = 200;
}

