/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.asm;

import com.alibaba.fastjson.asm.MethodCollector;
import com.alibaba.fastjson.asm.TypeCollector;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ClassReader {
    public final byte[] b;
    private final int[] items;
    private final String[] strings;
    private final int maxStringLength;
    public final int header;
    private boolean readAnnotations;

    public ClassReader(InputStream is, boolean readAnnotations) throws IOException {
        int len;
        this.readAnnotations = readAnnotations;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        while ((len = is.read(buf)) != -1) {
            if (len <= 0) continue;
            out.write(buf, 0, len);
        }
        is.close();
        this.b = out.toByteArray();
        this.items = new int[this.readUnsignedShort(8)];
        int n = this.items.length;
        this.strings = new String[n];
        int max = 0;
        int index = 10;
        for (int i = 1; i < n; ++i) {
            int size;
            this.items[i] = index + 1;
            switch (this.b[index]) {
                case 3: 
                case 4: 
                case 9: 
                case 10: 
                case 11: 
                case 12: 
                case 18: {
                    size = 5;
                    break;
                }
                case 5: 
                case 6: {
                    size = 9;
                    ++i;
                    break;
                }
                case 15: {
                    size = 4;
                    break;
                }
                case 1: {
                    size = 3 + this.readUnsignedShort(index + 1);
                    if (size <= max) break;
                    max = size;
                    break;
                }
                default: {
                    size = 3;
                }
            }
            index += size;
        }
        this.maxStringLength = max;
        this.header = index;
    }

    public void accept(TypeCollector classVisitor) {
        int j;
        int i;
        int u;
        char[] c = new char[this.maxStringLength];
        int anns = 0;
        if (this.readAnnotations) {
            u = this.getAttributes();
            for (i = this.readUnsignedShort(u); i > 0; --i) {
                String attrName = this.readUTF8(u + 2, c);
                if ("RuntimeVisibleAnnotations".equals(attrName)) {
                    anns = u + 8;
                    break;
                }
                u += 6 + this.readInt(u + 4);
            }
        }
        u = this.header;
        int len = this.readUnsignedShort(u + 6);
        u += 8;
        for (i = 0; i < len; ++i) {
            u += 2;
        }
        int v = u;
        i = this.readUnsignedShort(v);
        v += 2;
        while (i > 0) {
            j = this.readUnsignedShort(v + 6);
            v += 8;
            while (j > 0) {
                v += 6 + this.readInt(v + 2);
                --j;
            }
            --i;
        }
        i = this.readUnsignedShort(v);
        v += 2;
        while (i > 0) {
            j = this.readUnsignedShort(v + 6);
            v += 8;
            while (j > 0) {
                v += 6 + this.readInt(v + 2);
                --j;
            }
            --i;
        }
        i = this.readUnsignedShort(v);
        v += 2;
        while (i > 0) {
            v += 6 + this.readInt(v + 2);
            --i;
        }
        if (anns != 0) {
            v = anns + 2;
            for (i = this.readUnsignedShort(anns); i > 0; --i) {
                String name = this.readUTF8(v, c);
                classVisitor.visitAnnotation(name);
            }
        }
        i = this.readUnsignedShort(u);
        u += 2;
        while (i > 0) {
            j = this.readUnsignedShort(u + 6);
            u += 8;
            while (j > 0) {
                u += 6 + this.readInt(u + 2);
                --j;
            }
            --i;
        }
        i = this.readUnsignedShort(u);
        u += 2;
        while (i > 0) {
            u = this.readMethod(classVisitor, c, u);
            --i;
        }
    }

    private int getAttributes() {
        int j;
        int i;
        int u = this.header + 8 + this.readUnsignedShort(this.header + 6) * 2;
        for (i = this.readUnsignedShort(u); i > 0; --i) {
            for (j = this.readUnsignedShort(u + 8); j > 0; --j) {
                u += 6 + this.readInt(u + 12);
            }
            u += 8;
        }
        for (i = this.readUnsignedShort(u += 2); i > 0; --i) {
            for (j = this.readUnsignedShort(u + 8); j > 0; --j) {
                u += 6 + this.readInt(u + 12);
            }
            u += 8;
        }
        return u + 2;
    }

    private int readMethod(TypeCollector classVisitor, char[] c, int u) {
        String attrName;
        int access = this.readUnsignedShort(u);
        String name = this.readUTF8(u + 2, c);
        String desc = this.readUTF8(u + 4, c);
        int v = 0;
        int w = 0;
        int j = this.readUnsignedShort(u + 6);
        u += 8;
        while (j > 0) {
            attrName = this.readUTF8(u, c);
            int attrSize = this.readInt(u + 2);
            u += 6;
            if (attrName.equals("Code")) {
                v = u;
            }
            u += attrSize;
            --j;
        }
        if (w != 0) {
            w += 2;
            for (j = 0; j < this.readUnsignedShort(w); ++j) {
                w += 2;
            }
        }
        MethodCollector mv = classVisitor.visitMethod(access, name, desc);
        if (mv != null && v != 0) {
            int codeEnd;
            int codeLength = this.readInt(v + 4);
            int codeStart = v += 8;
            v = codeEnd = v + codeLength;
            j = this.readUnsignedShort(v);
            v += 2;
            while (j > 0) {
                v += 8;
                --j;
            }
            int varTable = 0;
            int varTypeTable = 0;
            j = this.readUnsignedShort(v);
            v += 2;
            while (j > 0) {
                attrName = this.readUTF8(v, c);
                if (attrName.equals("LocalVariableTable")) {
                    varTable = v + 6;
                } else if (attrName.equals("LocalVariableTypeTable")) {
                    varTypeTable = v + 6;
                }
                v += 6 + this.readInt(v + 2);
                --j;
            }
            v = codeStart;
            if (varTable != 0) {
                int k;
                if (varTypeTable != 0) {
                    k = this.readUnsignedShort(varTypeTable) * 3;
                    w = varTypeTable + 2;
                    int[] typeTable = new int[k];
                    while (k > 0) {
                        typeTable[--k] = w + 6;
                        typeTable[--k] = this.readUnsignedShort(w + 8);
                        typeTable[--k] = this.readUnsignedShort(w);
                        w += 10;
                    }
                }
                w = varTable + 2;
                for (k = this.readUnsignedShort(varTable); k > 0; --k) {
                    int index = this.readUnsignedShort(w + 8);
                    mv.visitLocalVariable(this.readUTF8(w + 4, c), index);
                    w += 10;
                }
            }
        }
        return u;
    }

    private int readUnsignedShort(int index) {
        byte[] b = this.b;
        return (b[index] & 0xFF) << 8 | b[index + 1] & 0xFF;
    }

    private int readInt(int index) {
        byte[] b = this.b;
        return (b[index] & 0xFF) << 24 | (b[index + 1] & 0xFF) << 16 | (b[index + 2] & 0xFF) << 8 | b[index + 3] & 0xFF;
    }

    private String readUTF8(int index, char[] buf) {
        int item = this.readUnsignedShort(index);
        String s2 = this.strings[item];
        if (s2 != null) {
            return s2;
        }
        index = this.items[item];
        this.strings[item] = this.readUTF(index + 2, this.readUnsignedShort(index), buf);
        return this.strings[item];
    }

    private String readUTF(int index, int utfLen, char[] buf) {
        int endIndex = index + utfLen;
        byte[] b = this.b;
        int strLen = 0;
        int st = 0;
        int cc = 0;
        while (index < endIndex) {
            int c = b[index++];
            switch (st) {
                case 0: {
                    if ((c &= 0xFF) < 128) {
                        buf[strLen++] = (char)c;
                        break;
                    }
                    if (c < 224 && c > 191) {
                        cc = (char)(c & 0x1F);
                        st = 1;
                        break;
                    }
                    cc = (char)(c & 0xF);
                    st = 2;
                    break;
                }
                case 1: {
                    buf[strLen++] = (char)(cc << 6 | c & 0x3F);
                    st = 0;
                    break;
                }
                case 2: {
                    cc = (char)(cc << 6 | c & 0x3F);
                    st = 1;
                }
            }
        }
        return new String(buf, 0, strLen);
    }
}

