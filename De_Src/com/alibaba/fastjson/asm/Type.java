/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.asm;

public class Type {
    public static final Type VOID_TYPE = new Type(0, null, 0x56050000, 1);
    public static final Type BOOLEAN_TYPE = new Type(1, null, 1509950721, 1);
    public static final Type CHAR_TYPE = new Type(2, null, 1124075009, 1);
    public static final Type BYTE_TYPE = new Type(3, null, 1107297537, 1);
    public static final Type SHORT_TYPE = new Type(4, null, 1392510721, 1);
    public static final Type INT_TYPE = new Type(5, null, 1224736769, 1);
    public static final Type FLOAT_TYPE = new Type(6, null, 1174536705, 1);
    public static final Type LONG_TYPE = new Type(7, null, 1241579778, 1);
    public static final Type DOUBLE_TYPE = new Type(8, null, 1141048066, 1);
    protected final int sort;
    private final char[] buf;
    private final int off;
    private final int len;

    private Type(int sort, char[] buf, int off, int len) {
        this.sort = sort;
        this.buf = buf;
        this.off = off;
        this.len = len;
    }

    public static Type getType(String typeDescriptor) {
        return Type.getType(typeDescriptor.toCharArray(), 0);
    }

    public static int getArgumentsAndReturnSizes(String desc) {
        int n = 1;
        int c = 1;
        while (true) {
            char car;
            if ((car = desc.charAt(c++)) == ')') {
                car = desc.charAt(c);
                return n << 2 | (car == 'V' ? 0 : (car == 'D' || car == 'J' ? 2 : 1));
            }
            if (car == 'L') {
                while (desc.charAt(c++) != ';') {
                }
                ++n;
                continue;
            }
            if (car == 'D' || car == 'J') {
                n += 2;
                continue;
            }
            ++n;
        }
    }

    private static Type getType(char[] buf, int off) {
        switch (buf[off]) {
            case 'V': {
                return VOID_TYPE;
            }
            case 'Z': {
                return BOOLEAN_TYPE;
            }
            case 'C': {
                return CHAR_TYPE;
            }
            case 'B': {
                return BYTE_TYPE;
            }
            case 'S': {
                return SHORT_TYPE;
            }
            case 'I': {
                return INT_TYPE;
            }
            case 'F': {
                return FLOAT_TYPE;
            }
            case 'J': {
                return LONG_TYPE;
            }
            case 'D': {
                return DOUBLE_TYPE;
            }
            case '[': {
                int len = 1;
                while (buf[off + len] == '[') {
                    ++len;
                }
                if (buf[off + len] == 'L') {
                    ++len;
                    while (buf[off + len] != ';') {
                        ++len;
                    }
                }
                return new Type(9, buf, off, len + 1);
            }
        }
        int len = 1;
        while (buf[off + len] != ';') {
            ++len;
        }
        return new Type(10, buf, off + 1, len - 1);
    }

    public String getInternalName() {
        return new String(this.buf, this.off, this.len);
    }

    String getDescriptor() {
        return new String(this.buf, this.off, this.len);
    }

    private int getDimensions() {
        int i = 1;
        while (this.buf[this.off + i] == '[') {
            ++i;
        }
        return i;
    }

    static Type[] getArgumentTypes(String methodDescriptor) {
        char car;
        char[] buf = methodDescriptor.toCharArray();
        int off = 1;
        int size = 0;
        while ((car = buf[off++]) != ')') {
            if (car == 'L') {
                while (buf[off++] != ';') {
                }
                ++size;
                continue;
            }
            if (car == '[') continue;
            ++size;
        }
        Type[] args2 = new Type[size];
        off = 1;
        size = 0;
        while (buf[off] != ')') {
            args2[size] = Type.getType(buf, off);
            off += args2[size].len + (args2[size].sort == 10 ? 2 : 0);
            ++size;
        }
        return args2;
    }

    protected String getClassName() {
        switch (this.sort) {
            case 0: {
                return "void";
            }
            case 1: {
                return "boolean";
            }
            case 2: {
                return "char";
            }
            case 3: {
                return "byte";
            }
            case 4: {
                return "short";
            }
            case 5: {
                return "int";
            }
            case 6: {
                return "float";
            }
            case 7: {
                return "long";
            }
            case 8: {
                return "double";
            }
            case 9: {
                Type elementType = Type.getType(this.buf, this.off + this.getDimensions());
                StringBuilder b = new StringBuilder(elementType.getClassName());
                for (int i = this.getDimensions(); i > 0; --i) {
                    b.append("[]");
                }
                return b.toString();
            }
        }
        return new String(this.buf, this.off, this.len).replace('/', '.');
    }
}

