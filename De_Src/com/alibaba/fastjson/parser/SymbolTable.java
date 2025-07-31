/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;

public class SymbolTable {
    private final String[] symbols;
    private final int indexMask;

    public SymbolTable(int tableSize) {
        this.indexMask = tableSize - 1;
        this.symbols = new String[tableSize];
        this.addSymbol("$ref", 0, 4, "$ref".hashCode());
        this.addSymbol(JSON.DEFAULT_TYPE_KEY, 0, JSON.DEFAULT_TYPE_KEY.length(), JSON.DEFAULT_TYPE_KEY.hashCode());
    }

    public String addSymbol(char[] buffer, int offset, int len) {
        int hash = SymbolTable.hash(buffer, offset, len);
        return this.addSymbol(buffer, offset, len, hash);
    }

    public String addSymbol(char[] buffer, int offset, int len, int hash) {
        int bucket = hash & this.indexMask;
        String symbol = this.symbols[bucket];
        if (symbol != null) {
            boolean eq = true;
            if (hash == symbol.hashCode() && len == symbol.length()) {
                for (int i = 0; i < len; ++i) {
                    if (buffer[offset + i] == symbol.charAt(i)) continue;
                    eq = false;
                    break;
                }
            } else {
                eq = false;
            }
            if (eq) {
                return symbol;
            }
            return new String(buffer, offset, len);
        }
        this.symbols[bucket] = symbol = new String(buffer, offset, len).intern();
        return symbol;
    }

    public String addSymbol(String buffer, int offset, int len, int hash) {
        return this.addSymbol(buffer, offset, len, hash, false);
    }

    public String addSymbol(String buffer, int offset, int len, int hash, boolean replace) {
        int bucket = hash & this.indexMask;
        String symbol = this.symbols[bucket];
        if (symbol != null) {
            if (hash == symbol.hashCode() && len == symbol.length() && buffer.startsWith(symbol, offset)) {
                return symbol;
            }
            String str = SymbolTable.subString(buffer, offset, len);
            if (replace) {
                this.symbols[bucket] = str;
            }
            return str;
        }
        symbol = len == buffer.length() ? buffer : SymbolTable.subString(buffer, offset, len);
        this.symbols[bucket] = symbol = symbol.intern();
        return symbol;
    }

    private static String subString(String src, int offset, int len) {
        char[] chars = new char[len];
        src.getChars(offset, offset + len, chars, 0);
        return new String(chars);
    }

    public static int hash(char[] buffer, int offset, int len) {
        int h2 = 0;
        int off = offset;
        for (int i = 0; i < len; ++i) {
            h2 = 31 * h2 + buffer[off++];
        }
        return h2;
    }
}

