/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.SymbolTable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Locale;
import java.util.TimeZone;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface JSONLexer {
    public static final char EOI = '\u001a';
    public static final int NOT_MATCH = -1;
    public static final int NOT_MATCH_NAME = -2;
    public static final int UNKNOWN = 0;
    public static final int OBJECT = 1;
    public static final int ARRAY = 2;
    public static final int VALUE = 3;
    public static final int END = 4;
    public static final int VALUE_NULL = 5;

    public int token();

    public String tokenName();

    public void skipWhitespace();

    public void nextToken();

    public void nextToken(int var1);

    public char getCurrent();

    public char next();

    public String scanSymbol(SymbolTable var1);

    public String scanSymbol(SymbolTable var1, char var2);

    public void resetStringPosition();

    public void scanNumber();

    public int pos();

    public Number integerValue();

    public BigDecimal decimalValue();

    public Number decimalValue(boolean var1);

    public String scanSymbolUnQuoted(SymbolTable var1);

    public String stringVal();

    public boolean isEnabled(int var1);

    public boolean isEnabled(Feature var1);

    public void config(Feature var1, boolean var2);

    public void scanString();

    public int intValue();

    public void nextTokenWithColon();

    public void nextTokenWithColon(int var1);

    public boolean isBlankInput();

    public void close();

    public long longValue();

    public boolean isRef();

    public String scanTypeName(SymbolTable var1);

    public String numberString();

    public byte[] bytesValue();

    public float floatValue();

    public int scanInt(char var1);

    public long scanLong(char var1);

    public float scanFloat(char var1);

    public double scanDouble(char var1);

    public boolean scanBoolean(char var1);

    public BigDecimal scanDecimal(char var1);

    public String scanString(char var1);

    public Enum<?> scanEnum(Class<?> var1, SymbolTable var2, char var3);

    public String scanSymbolWithSeperator(SymbolTable var1, char var2);

    public void scanStringArray(Collection<String> var1, char var2);

    public TimeZone getTimeZone();

    public void setTimeZone(TimeZone var1);

    public Locale getLocale();

    public void setLocale(Locale var1);

    public String info();

    public int getFeatures();

    public void setFeatures(int var1);
}

