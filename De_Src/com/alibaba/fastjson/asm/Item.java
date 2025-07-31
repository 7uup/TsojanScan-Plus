/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.asm;

final class Item {
    int index;
    int type;
    int intVal;
    long longVal;
    String strVal1;
    String strVal2;
    String strVal3;
    int hashCode;
    Item next;

    Item() {
    }

    Item(int index, Item i) {
        this.index = index;
        this.type = i.type;
        this.intVal = i.intVal;
        this.longVal = i.longVal;
        this.strVal1 = i.strVal1;
        this.strVal2 = i.strVal2;
        this.strVal3 = i.strVal3;
        this.hashCode = i.hashCode;
    }

    void set(int type, String strVal1, String strVal2, String strVal3) {
        this.type = type;
        this.strVal1 = strVal1;
        this.strVal2 = strVal2;
        this.strVal3 = strVal3;
        switch (type) {
            case 1: 
            case 7: 
            case 8: 
            case 13: {
                this.hashCode = Integer.MAX_VALUE & type + strVal1.hashCode();
                return;
            }
            case 12: {
                this.hashCode = Integer.MAX_VALUE & type + strVal1.hashCode() * strVal2.hashCode();
                return;
            }
        }
        this.hashCode = Integer.MAX_VALUE & type + strVal1.hashCode() * strVal2.hashCode() * strVal3.hashCode();
    }

    void set(int intVal) {
        this.type = 3;
        this.intVal = intVal;
        this.hashCode = Integer.MAX_VALUE & this.type + intVal;
    }

    boolean isEqualTo(Item i) {
        switch (this.type) {
            case 1: 
            case 7: 
            case 8: 
            case 13: {
                return i.strVal1.equals(this.strVal1);
            }
            case 5: 
            case 6: 
            case 15: {
                return i.longVal == this.longVal;
            }
            case 3: 
            case 4: {
                return i.intVal == this.intVal;
            }
            case 12: {
                return i.strVal1.equals(this.strVal1) && i.strVal2.equals(this.strVal2);
            }
        }
        return i.strVal1.equals(this.strVal1) && i.strVal2.equals(this.strVal2) && i.strVal3.equals(this.strVal3);
    }
}

