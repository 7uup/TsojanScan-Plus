/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.asm;

import com.alibaba.fastjson.asm.ByteVector;
import com.alibaba.fastjson.asm.FieldWriter;
import com.alibaba.fastjson.asm.Item;
import com.alibaba.fastjson.asm.MethodWriter;
import com.alibaba.fastjson.asm.Type;

public class ClassWriter {
    int version;
    int index = 1;
    final ByteVector pool = new ByteVector();
    Item[] items = new Item[256];
    int threshold = (int)(0.75 * (double)this.items.length);
    final Item key = new Item();
    final Item key2 = new Item();
    final Item key3 = new Item();
    Item[] typeTable;
    private int access;
    private int name;
    String thisName;
    private int superName;
    private int interfaceCount;
    private int[] interfaces;
    FieldWriter firstField;
    FieldWriter lastField;
    MethodWriter firstMethod;
    MethodWriter lastMethod;

    public ClassWriter() {
        this(0);
    }

    private ClassWriter(int flags) {
    }

    public void visit(int version, int access, String name, String superName, String[] interfaces) {
        this.version = version;
        this.access = access;
        this.name = this.newClassItem((String)name).index;
        this.thisName = name;
        int n = this.superName = superName == null ? 0 : this.newClassItem((String)superName).index;
        if (interfaces != null && interfaces.length > 0) {
            this.interfaceCount = interfaces.length;
            this.interfaces = new int[this.interfaceCount];
            for (int i = 0; i < this.interfaceCount; ++i) {
                this.interfaces[i] = this.newClassItem((String)interfaces[i]).index;
            }
        }
    }

    public byte[] toByteArray() {
        int size = 24 + 2 * this.interfaceCount;
        int nbFields = 0;
        FieldWriter fb = this.firstField;
        while (fb != null) {
            ++nbFields;
            size += fb.getSize();
            fb = fb.next;
        }
        int nbMethods = 0;
        MethodWriter mb = this.firstMethod;
        while (mb != null) {
            ++nbMethods;
            size += mb.getSize();
            mb = mb.next;
        }
        int attributeCount = 0;
        ByteVector out = new ByteVector(size += this.pool.length);
        out.putInt(-889275714).putInt(this.version);
        out.putShort(this.index).putByteArray(this.pool.data, 0, this.pool.length);
        int mask = 393216;
        out.putShort(this.access & ~mask).putShort(this.name).putShort(this.superName);
        out.putShort(this.interfaceCount);
        for (int i = 0; i < this.interfaceCount; ++i) {
            out.putShort(this.interfaces[i]);
        }
        out.putShort(nbFields);
        fb = this.firstField;
        while (fb != null) {
            fb.put(out);
            fb = fb.next;
        }
        out.putShort(nbMethods);
        mb = this.firstMethod;
        while (mb != null) {
            mb.put(out);
            mb = mb.next;
        }
        out.putShort(attributeCount);
        return out.data;
    }

    Item newConstItem(Object cst) {
        if (cst instanceof Integer) {
            int val = (Integer)cst;
            this.key.set(val);
            Item result = this.get(this.key);
            if (result == null) {
                this.pool.putByte(3).putInt(val);
                result = new Item(this.index++, this.key);
                this.put(result);
            }
            return result;
        }
        if (cst instanceof String) {
            return this.newString((String)cst);
        }
        if (cst instanceof Type) {
            Type t = (Type)cst;
            return this.newClassItem(t.sort == 10 ? t.getInternalName() : t.getDescriptor());
        }
        throw new IllegalArgumentException("value " + cst);
    }

    public int newUTF8(String value) {
        this.key.set(1, value, null, null);
        Item result = this.get(this.key);
        if (result == null) {
            this.pool.putByte(1).putUTF8(value);
            result = new Item(this.index++, this.key);
            this.put(result);
        }
        return result.index;
    }

    public Item newClassItem(String value) {
        this.key2.set(7, value, null, null);
        Item result = this.get(this.key2);
        if (result == null) {
            this.pool.put12(7, this.newUTF8(value));
            result = new Item(this.index++, this.key2);
            this.put(result);
        }
        return result;
    }

    Item newFieldItem(String owner, String name, String desc) {
        this.key3.set(9, owner, name, desc);
        Item result = this.get(this.key3);
        if (result == null) {
            int s1 = this.newClassItem((String)owner).index;
            int s2 = this.newNameTypeItem((String)name, (String)desc).index;
            this.pool.put12(9, s1).putShort(s2);
            result = new Item(this.index++, this.key3);
            this.put(result);
        }
        return result;
    }

    Item newMethodItem(String owner, String name, String desc, boolean itf) {
        int type = itf ? 11 : 10;
        this.key3.set(type, owner, name, desc);
        Item result = this.get(this.key3);
        if (result == null) {
            int s1 = this.newClassItem((String)owner).index;
            int s2 = this.newNameTypeItem((String)name, (String)desc).index;
            this.pool.put12(type, s1).putShort(s2);
            result = new Item(this.index++, this.key3);
            this.put(result);
        }
        return result;
    }

    private Item newString(String value) {
        this.key2.set(8, value, null, null);
        Item result = this.get(this.key2);
        if (result == null) {
            this.pool.put12(8, this.newUTF8(value));
            result = new Item(this.index++, this.key2);
            this.put(result);
        }
        return result;
    }

    public Item newNameTypeItem(String name, String desc) {
        this.key2.set(12, name, desc, null);
        Item result = this.get(this.key2);
        if (result == null) {
            int s1 = this.newUTF8(name);
            int s2 = this.newUTF8(desc);
            this.pool.put12(12, s1).putShort(s2);
            result = new Item(this.index++, this.key2);
            this.put(result);
        }
        return result;
    }

    private Item get(Item key) {
        Item i = this.items[key.hashCode % this.items.length];
        while (!(i == null || i.type == key.type && key.isEqualTo(i))) {
            i = i.next;
        }
        return i;
    }

    private void put(Item i) {
        if (this.index > this.threshold) {
            int ll = this.items.length;
            int nl = ll * 2 + 1;
            Item[] newItems = new Item[nl];
            for (int l = ll - 1; l >= 0; --l) {
                Item j = this.items[l];
                while (j != null) {
                    int index = j.hashCode % newItems.length;
                    Item k = j.next;
                    j.next = newItems[index];
                    newItems[index] = j;
                    j = k;
                }
            }
            this.items = newItems;
            this.threshold = (int)((double)nl * 0.75);
        }
        int index = i.hashCode % this.items.length;
        i.next = this.items[index];
        this.items[index] = i;
    }
}

