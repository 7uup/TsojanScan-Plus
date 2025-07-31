/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.SymbolTable;
import com.alibaba.fastjson.util.IOUtils;
import java.io.Closeable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class JSONLexerBase
implements JSONLexer,
Closeable {
    protected int token;
    protected int pos;
    protected int features;
    protected char ch;
    protected int bp;
    protected int eofPos;
    protected char[] sbuf;
    protected int sp;
    protected int np;
    protected boolean hasSpecial;
    protected Calendar calendar = null;
    protected TimeZone timeZone = JSON.defaultTimeZone;
    protected Locale locale = JSON.defaultLocale;
    public int matchStat = 0;
    private static final ThreadLocal<char[]> SBUF_LOCAL;
    protected String stringDefaultValue = null;
    protected int nanos = 0;
    protected static final char[] typeFieldName;
    protected static final long MULTMIN_RADIX_TEN = -922337203685477580L;
    protected static final int INT_MULTMIN_RADIX_TEN = -214748364;
    protected static final int[] digits;

    protected void lexError(String key, Object ... args2) {
        this.token = 1;
    }

    public JSONLexerBase(int features) {
        this.features = features;
        if ((features & Feature.InitStringFieldAsEmpty.mask) != 0) {
            this.stringDefaultValue = "";
        }
        this.sbuf = SBUF_LOCAL.get();
        if (this.sbuf == null) {
            this.sbuf = new char[512];
        }
    }

    public final int matchStat() {
        return this.matchStat;
    }

    public void setToken(int token) {
        this.token = token;
    }

    @Override
    public final void nextToken() {
        block28: {
            this.sp = 0;
            block19: while (true) {
                this.pos = this.bp;
                if (this.ch == '/') {
                    this.skipComment();
                    continue;
                }
                if (this.ch == '\"') {
                    this.scanString();
                    return;
                }
                if (this.ch == ',') {
                    this.next();
                    this.token = 16;
                    return;
                }
                if (this.ch >= '0' && this.ch <= '9') {
                    this.scanNumber();
                    return;
                }
                if (this.ch == '-') {
                    this.scanNumber();
                    return;
                }
                switch (this.ch) {
                    case '\'': {
                        if (!this.isEnabled(Feature.AllowSingleQuotes)) {
                            throw new JSONException("Feature.AllowSingleQuotes is false");
                        }
                        this.scanStringSingleQuote();
                        return;
                    }
                    case '\b': 
                    case '\t': 
                    case '\n': 
                    case '\f': 
                    case '\r': 
                    case ' ': {
                        this.next();
                        continue block19;
                    }
                    case 't': {
                        this.scanTrue();
                        return;
                    }
                    case 'f': {
                        this.scanFalse();
                        return;
                    }
                    case 'n': {
                        this.scanNullOrNew();
                        return;
                    }
                    case 'N': 
                    case 'S': 
                    case 'T': 
                    case 'u': {
                        this.scanIdent();
                        return;
                    }
                    case '(': {
                        this.next();
                        this.token = 10;
                        return;
                    }
                    case ')': {
                        this.next();
                        this.token = 11;
                        return;
                    }
                    case '[': {
                        this.next();
                        this.token = 14;
                        return;
                    }
                    case ']': {
                        this.next();
                        this.token = 15;
                        return;
                    }
                    case '{': {
                        this.next();
                        this.token = 12;
                        return;
                    }
                    case '}': {
                        this.next();
                        this.token = 13;
                        return;
                    }
                    case ':': {
                        this.next();
                        this.token = 17;
                        return;
                    }
                    case ';': {
                        this.next();
                        this.token = 24;
                        return;
                    }
                    case '.': {
                        this.next();
                        this.token = 25;
                        return;
                    }
                    case '+': {
                        this.next();
                        this.scanNumber();
                        return;
                    }
                    case 'x': {
                        this.scanHex();
                        return;
                    }
                }
                if (this.isEOF()) {
                    if (this.token == 20) {
                        throw new JSONException("EOF error");
                    }
                    this.token = 20;
                    this.eofPos = this.pos = this.bp;
                    break block28;
                }
                if (this.ch > '\u001f' && this.ch != '\u007f') break;
                this.next();
            }
            this.lexError("illegal.char", String.valueOf((int)this.ch));
            this.next();
        }
    }

    @Override
    public final void nextToken(int expect) {
        this.sp = 0;
        while (true) {
            switch (expect) {
                case 12: {
                    if (this.ch == '{') {
                        this.token = 12;
                        this.next();
                        return;
                    }
                    if (this.ch != '[') break;
                    this.token = 14;
                    this.next();
                    return;
                }
                case 16: {
                    if (this.ch == ',') {
                        this.token = 16;
                        this.next();
                        return;
                    }
                    if (this.ch == '}') {
                        this.token = 13;
                        this.next();
                        return;
                    }
                    if (this.ch == ']') {
                        this.token = 15;
                        this.next();
                        return;
                    }
                    if (this.ch == '\u001a') {
                        this.token = 20;
                        return;
                    }
                    if (this.ch != 'n') break;
                    this.scanNullOrNew(false);
                    return;
                }
                case 2: {
                    if (this.ch >= '0' && this.ch <= '9') {
                        this.pos = this.bp;
                        this.scanNumber();
                        return;
                    }
                    if (this.ch == '\"') {
                        this.pos = this.bp;
                        this.scanString();
                        return;
                    }
                    if (this.ch == '[') {
                        this.token = 14;
                        this.next();
                        return;
                    }
                    if (this.ch != '{') break;
                    this.token = 12;
                    this.next();
                    return;
                }
                case 4: {
                    if (this.ch == '\"') {
                        this.pos = this.bp;
                        this.scanString();
                        return;
                    }
                    if (this.ch >= '0' && this.ch <= '9') {
                        this.pos = this.bp;
                        this.scanNumber();
                        return;
                    }
                    if (this.ch == '[') {
                        this.token = 14;
                        this.next();
                        return;
                    }
                    if (this.ch != '{') break;
                    this.token = 12;
                    this.next();
                    return;
                }
                case 14: {
                    if (this.ch == '[') {
                        this.token = 14;
                        this.next();
                        return;
                    }
                    if (this.ch != '{') break;
                    this.token = 12;
                    this.next();
                    return;
                }
                case 15: {
                    if (this.ch == ']') {
                        this.token = 15;
                        this.next();
                        return;
                    }
                }
                case 20: {
                    if (this.ch != '\u001a') break;
                    this.token = 20;
                    return;
                }
                case 18: {
                    this.nextIdent();
                    return;
                }
            }
            if (this.ch != ' ' && this.ch != '\n' && this.ch != '\r' && this.ch != '\t' && this.ch != '\f' && this.ch != '\b') break;
            this.next();
        }
        this.nextToken();
    }

    public final void nextIdent() {
        while (JSONLexerBase.isWhitespace(this.ch)) {
            this.next();
        }
        if (this.ch == '_' || this.ch == '$' || Character.isLetter(this.ch)) {
            this.scanIdent();
        } else {
            this.nextToken();
        }
    }

    @Override
    public final void nextTokenWithColon() {
        this.nextTokenWithChar(':');
    }

    public final void nextTokenWithChar(char expect) {
        this.sp = 0;
        while (true) {
            if (this.ch == expect) {
                this.next();
                this.nextToken();
                return;
            }
            if (this.ch != ' ' && this.ch != '\n' && this.ch != '\r' && this.ch != '\t' && this.ch != '\f' && this.ch != '\b') break;
            this.next();
        }
        throw new JSONException("not match " + expect + " - " + this.ch + ", info : " + this.info());
    }

    @Override
    public final int token() {
        return this.token;
    }

    @Override
    public final String tokenName() {
        return JSONToken.name(this.token);
    }

    @Override
    public final int pos() {
        return this.pos;
    }

    public final String stringDefaultValue() {
        return this.stringDefaultValue;
    }

    @Override
    public final Number integerValue() throws NumberFormatException {
        int digit;
        long limit;
        long result = 0L;
        boolean negative = false;
        if (this.np == -1) {
            this.np = 0;
        }
        int i = this.np;
        int max = this.np + this.sp;
        int type = 32;
        switch (this.charAt(max - 1)) {
            case 'L': {
                --max;
                type = 76;
                break;
            }
            case 'S': {
                --max;
                type = 83;
                break;
            }
            case 'B': {
                --max;
                type = 66;
                break;
            }
        }
        if (this.charAt(this.np) == '-') {
            negative = true;
            limit = Long.MIN_VALUE;
            ++i;
        } else {
            limit = -9223372036854775807L;
        }
        long multmin = -922337203685477580L;
        if (i < max) {
            digit = this.charAt(i++) - 48;
            result = -digit;
        }
        while (i < max) {
            digit = this.charAt(i++) - 48;
            if (result < multmin) {
                return new BigInteger(this.numberString(), 10);
            }
            if ((result *= 10L) < limit + (long)digit) {
                return new BigInteger(this.numberString(), 10);
            }
            result -= (long)digit;
        }
        if (negative) {
            if (i > this.np + 1) {
                if (result >= Integer.MIN_VALUE && type != 76) {
                    if (type == 83) {
                        return (short)result;
                    }
                    if (type == 66) {
                        return (byte)result;
                    }
                    return (int)result;
                }
                return result;
            }
            throw new JSONException("illegal number format : " + this.numberString());
        }
        if ((result = -result) <= Integer.MAX_VALUE && type != 76) {
            if (type == 83) {
                return (short)result;
            }
            if (type == 66) {
                return (byte)result;
            }
            return (int)result;
        }
        return result;
    }

    @Override
    public final void nextTokenWithColon(int expect) {
        this.nextTokenWithChar(':');
    }

    @Override
    public float floatValue() {
        char c0;
        String strVal = this.numberString();
        float floatValue = Float.parseFloat(strVal);
        if ((floatValue == 0.0f || floatValue == Float.POSITIVE_INFINITY) && (c0 = strVal.charAt(0)) > '0' && c0 <= '9') {
            throw new JSONException("float overflow : " + strVal);
        }
        return floatValue;
    }

    public double doubleValue() {
        return Double.parseDouble(this.numberString());
    }

    @Override
    public void config(Feature feature, boolean state) {
        this.features = Feature.config(this.features, feature, state);
        if ((this.features & Feature.InitStringFieldAsEmpty.mask) != 0) {
            this.stringDefaultValue = "";
        }
    }

    @Override
    public final boolean isEnabled(Feature feature) {
        return this.isEnabled(feature.mask);
    }

    @Override
    public final boolean isEnabled(int feature) {
        return (this.features & feature) != 0;
    }

    public final boolean isEnabled(int features, int feature) {
        return (this.features & feature) != 0 || (features & feature) != 0;
    }

    @Override
    public abstract String numberString();

    public abstract boolean isEOF();

    @Override
    public final char getCurrent() {
        return this.ch;
    }

    public abstract char charAt(int var1);

    @Override
    public abstract char next();

    protected void skipComment() {
        this.next();
        if (this.ch == '/') {
            do {
                this.next();
                if (this.ch != '\n') continue;
                this.next();
                return;
            } while (this.ch != '\u001a');
            return;
        }
        if (this.ch == '*') {
            this.next();
            while (this.ch != '\u001a') {
                if (this.ch == '*') {
                    this.next();
                    if (this.ch != '/') continue;
                    this.next();
                    return;
                }
                this.next();
            }
        } else {
            throw new JSONException("invalid comment");
        }
    }

    @Override
    public final String scanSymbol(SymbolTable symbolTable) {
        this.skipWhitespace();
        if (this.ch == '\"') {
            return this.scanSymbol(symbolTable, '\"');
        }
        if (this.ch == '\'') {
            if (!this.isEnabled(Feature.AllowSingleQuotes)) {
                throw new JSONException("syntax error");
            }
            return this.scanSymbol(symbolTable, '\'');
        }
        if (this.ch == '}') {
            this.next();
            this.token = 13;
            return null;
        }
        if (this.ch == ',') {
            this.next();
            this.token = 16;
            return null;
        }
        if (this.ch == '\u001a') {
            this.token = 20;
            return null;
        }
        if (!this.isEnabled(Feature.AllowUnQuotedFieldNames)) {
            throw new JSONException("syntax error");
        }
        return this.scanSymbolUnQuoted(symbolTable);
    }

    protected abstract void arrayCopy(int var1, char[] var2, int var3, int var4);

    @Override
    public final String scanSymbol(SymbolTable symbolTable, char quote) {
        String value;
        char chLocal;
        int hash = 0;
        this.np = this.bp;
        this.sp = 0;
        boolean hasSpecial = false;
        block22: while ((chLocal = this.next()) != quote) {
            if (chLocal == '\u001a') {
                throw new JSONException("unclosed.str");
            }
            if (chLocal == '\\') {
                if (!hasSpecial) {
                    hasSpecial = true;
                    if (this.sp >= this.sbuf.length) {
                        int newCapcity = this.sbuf.length * 2;
                        if (this.sp > newCapcity) {
                            newCapcity = this.sp;
                        }
                        char[] newsbuf = new char[newCapcity];
                        System.arraycopy(this.sbuf, 0, newsbuf, 0, this.sbuf.length);
                        this.sbuf = newsbuf;
                    }
                    this.arrayCopy(this.np + 1, this.sbuf, 0, this.sp);
                }
                chLocal = this.next();
                switch (chLocal) {
                    case '0': {
                        hash = 31 * hash + chLocal;
                        this.putChar('\u0000');
                        continue block22;
                    }
                    case '1': {
                        hash = 31 * hash + chLocal;
                        this.putChar('\u0001');
                        continue block22;
                    }
                    case '2': {
                        hash = 31 * hash + chLocal;
                        this.putChar('\u0002');
                        continue block22;
                    }
                    case '3': {
                        hash = 31 * hash + chLocal;
                        this.putChar('\u0003');
                        continue block22;
                    }
                    case '4': {
                        hash = 31 * hash + chLocal;
                        this.putChar('\u0004');
                        continue block22;
                    }
                    case '5': {
                        hash = 31 * hash + chLocal;
                        this.putChar('\u0005');
                        continue block22;
                    }
                    case '6': {
                        hash = 31 * hash + chLocal;
                        this.putChar('\u0006');
                        continue block22;
                    }
                    case '7': {
                        hash = 31 * hash + chLocal;
                        this.putChar('\u0007');
                        continue block22;
                    }
                    case 'b': {
                        hash = 31 * hash + 8;
                        this.putChar('\b');
                        continue block22;
                    }
                    case 't': {
                        hash = 31 * hash + 9;
                        this.putChar('\t');
                        continue block22;
                    }
                    case 'n': {
                        hash = 31 * hash + 10;
                        this.putChar('\n');
                        continue block22;
                    }
                    case 'v': {
                        hash = 31 * hash + 11;
                        this.putChar('\u000b');
                        continue block22;
                    }
                    case 'F': 
                    case 'f': {
                        hash = 31 * hash + 12;
                        this.putChar('\f');
                        continue block22;
                    }
                    case 'r': {
                        hash = 31 * hash + 13;
                        this.putChar('\r');
                        continue block22;
                    }
                    case '\"': {
                        hash = 31 * hash + 34;
                        this.putChar('\"');
                        continue block22;
                    }
                    case '\'': {
                        hash = 31 * hash + 39;
                        this.putChar('\'');
                        continue block22;
                    }
                    case '/': {
                        hash = 31 * hash + 47;
                        this.putChar('/');
                        continue block22;
                    }
                    case '\\': {
                        hash = 31 * hash + 92;
                        this.putChar('\\');
                        continue block22;
                    }
                    case 'x': {
                        char x1 = this.ch = this.next();
                        char x2 = this.ch = this.next();
                        int x_val = digits[x1] * 16 + digits[x2];
                        char x_char = (char)x_val;
                        hash = 31 * hash + x_char;
                        this.putChar(x_char);
                        continue block22;
                    }
                    case 'u': {
                        char c1 = chLocal = this.next();
                        char c2 = chLocal = this.next();
                        char c3 = chLocal = this.next();
                        char c4 = chLocal = this.next();
                        int val = Integer.parseInt(new String(new char[]{c1, c2, c3, c4}), 16);
                        hash = 31 * hash + val;
                        this.putChar((char)val);
                        continue block22;
                    }
                }
                this.ch = chLocal;
                throw new JSONException("unclosed.str.lit");
            }
            hash = 31 * hash + chLocal;
            if (!hasSpecial) {
                ++this.sp;
                continue;
            }
            if (this.sp == this.sbuf.length) {
                this.putChar(chLocal);
                continue;
            }
            this.sbuf[this.sp++] = chLocal;
        }
        this.token = 4;
        if (!hasSpecial) {
            int offset = this.np == -1 ? 0 : this.np + 1;
            value = this.addSymbol(offset, this.sp, hash, symbolTable);
        } else {
            value = symbolTable.addSymbol(this.sbuf, 0, this.sp, hash);
        }
        this.sp = 0;
        this.next();
        return value;
    }

    @Override
    public final void resetStringPosition() {
        this.sp = 0;
    }

    @Override
    public String info() {
        return "";
    }

    @Override
    public final String scanSymbolUnQuoted(SymbolTable symbolTable) {
        char chLocal;
        boolean firstFlag;
        if (this.token == 1 && this.pos == 0 && this.bp == 1) {
            this.bp = 0;
        }
        boolean[] firstIdentifierFlags = IOUtils.firstIdentifierFlags;
        int first = this.ch;
        boolean bl = firstFlag = this.ch >= firstIdentifierFlags.length || firstIdentifierFlags[first];
        if (!firstFlag) {
            throw new JSONException("illegal identifier : " + this.ch + this.info());
        }
        boolean[] identifierFlags = IOUtils.identifierFlags;
        int hash = first;
        this.np = this.bp;
        this.sp = 1;
        while ((chLocal = this.next()) >= identifierFlags.length || identifierFlags[chLocal]) {
            hash = 31 * hash + chLocal;
            ++this.sp;
        }
        this.ch = this.charAt(this.bp);
        this.token = 18;
        int NULL_HASH = 3392903;
        if (this.sp == 4 && hash == 3392903 && this.charAt(this.np) == 'n' && this.charAt(this.np + 1) == 'u' && this.charAt(this.np + 2) == 'l' && this.charAt(this.np + 3) == 'l') {
            return null;
        }
        if (symbolTable == null) {
            return this.subString(this.np, this.sp);
        }
        return this.addSymbol(this.np, this.sp, hash, symbolTable);
    }

    protected abstract void copyTo(int var1, int var2, char[] var3);

    @Override
    public final void scanString() {
        char ch;
        this.np = this.bp;
        this.hasSpecial = false;
        block22: while ((ch = this.next()) != '\"') {
            if (ch == '\u001a') {
                if (!this.isEOF()) {
                    this.putChar('\u001a');
                    continue;
                }
                throw new JSONException("unclosed string : " + ch);
            }
            if (ch == '\\') {
                if (!this.hasSpecial) {
                    this.hasSpecial = true;
                    if (this.sp >= this.sbuf.length) {
                        int newCapcity = this.sbuf.length * 2;
                        if (this.sp > newCapcity) {
                            newCapcity = this.sp;
                        }
                        char[] newsbuf = new char[newCapcity];
                        System.arraycopy(this.sbuf, 0, newsbuf, 0, this.sbuf.length);
                        this.sbuf = newsbuf;
                    }
                    this.copyTo(this.np + 1, this.sp, this.sbuf);
                }
                ch = this.next();
                switch (ch) {
                    case '0': {
                        this.putChar('\u0000');
                        continue block22;
                    }
                    case '1': {
                        this.putChar('\u0001');
                        continue block22;
                    }
                    case '2': {
                        this.putChar('\u0002');
                        continue block22;
                    }
                    case '3': {
                        this.putChar('\u0003');
                        continue block22;
                    }
                    case '4': {
                        this.putChar('\u0004');
                        continue block22;
                    }
                    case '5': {
                        this.putChar('\u0005');
                        continue block22;
                    }
                    case '6': {
                        this.putChar('\u0006');
                        continue block22;
                    }
                    case '7': {
                        this.putChar('\u0007');
                        continue block22;
                    }
                    case 'b': {
                        this.putChar('\b');
                        continue block22;
                    }
                    case 't': {
                        this.putChar('\t');
                        continue block22;
                    }
                    case 'n': {
                        this.putChar('\n');
                        continue block22;
                    }
                    case 'v': {
                        this.putChar('\u000b');
                        continue block22;
                    }
                    case 'F': 
                    case 'f': {
                        this.putChar('\f');
                        continue block22;
                    }
                    case 'r': {
                        this.putChar('\r');
                        continue block22;
                    }
                    case '\"': {
                        this.putChar('\"');
                        continue block22;
                    }
                    case '\'': {
                        this.putChar('\'');
                        continue block22;
                    }
                    case '/': {
                        this.putChar('/');
                        continue block22;
                    }
                    case '\\': {
                        this.putChar('\\');
                        continue block22;
                    }
                    case 'x': {
                        boolean hex2;
                        char x1 = this.next();
                        char x2 = this.next();
                        boolean hex1 = x1 >= '0' && x1 <= '9' || x1 >= 'a' && x1 <= 'f' || x1 >= 'A' && x1 <= 'F';
                        boolean bl = hex2 = x2 >= '0' && x2 <= '9' || x2 >= 'a' && x2 <= 'f' || x2 >= 'A' && x2 <= 'F';
                        if (!hex1 || !hex2) {
                            throw new JSONException("invalid escape character \\x" + x1 + x2);
                        }
                        char x_char = (char)(digits[x1] * 16 + digits[x2]);
                        this.putChar(x_char);
                        continue block22;
                    }
                    case 'u': {
                        char u1 = this.next();
                        char u2 = this.next();
                        char u3 = this.next();
                        char u4 = this.next();
                        int val = Integer.parseInt(new String(new char[]{u1, u2, u3, u4}), 16);
                        this.putChar((char)val);
                        continue block22;
                    }
                }
                this.ch = ch;
                throw new JSONException("unclosed string : " + ch);
            }
            if (!this.hasSpecial) {
                ++this.sp;
                continue;
            }
            if (this.sp == this.sbuf.length) {
                this.putChar(ch);
                continue;
            }
            this.sbuf[this.sp++] = ch;
        }
        this.token = 4;
        this.ch = this.next();
    }

    public Calendar getCalendar() {
        return this.calendar;
    }

    @Override
    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    @Override
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public Locale getLocale() {
        return this.locale;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public final int intValue() {
        char chLocal;
        int digit;
        int limit;
        if (this.np == -1) {
            this.np = 0;
        }
        int result = 0;
        boolean negative = false;
        int i = this.np;
        int max = this.np + this.sp;
        if (this.charAt(this.np) == '-') {
            negative = true;
            limit = Integer.MIN_VALUE;
            ++i;
        } else {
            limit = -2147483647;
        }
        long multmin = -214748364L;
        if (i < max) {
            digit = this.charAt(i++) - 48;
            result = -digit;
        }
        while (i < max && (chLocal = this.charAt(i++)) != 'L' && chLocal != 'S' && chLocal != 'B') {
            digit = chLocal - 48;
            if ((long)result < multmin) {
                throw new NumberFormatException(this.numberString());
            }
            if ((result *= 10) < limit + digit) {
                throw new NumberFormatException(this.numberString());
            }
            result -= digit;
        }
        if (negative) {
            if (i > this.np + 1) {
                return result;
            }
            throw new NumberFormatException(this.numberString());
        }
        return -result;
    }

    @Override
    public abstract byte[] bytesValue();

    @Override
    public void close() {
        if (this.sbuf.length <= 8192) {
            SBUF_LOCAL.set(this.sbuf);
        }
        this.sbuf = null;
    }

    @Override
    public final boolean isRef() {
        if (this.sp != 4) {
            return false;
        }
        return this.charAt(this.np + 1) == '$' && this.charAt(this.np + 2) == 'r' && this.charAt(this.np + 3) == 'e' && this.charAt(this.np + 4) == 'f';
    }

    @Override
    public String scanTypeName(SymbolTable symbolTable) {
        return null;
    }

    public final int scanType(String type) {
        this.matchStat = 0;
        if (!this.charArrayCompare(typeFieldName)) {
            return -2;
        }
        int bpLocal = this.bp + typeFieldName.length;
        int typeLength = type.length();
        for (int i = 0; i < typeLength; ++i) {
            if (type.charAt(i) == this.charAt(bpLocal + i)) continue;
            return -1;
        }
        if (this.charAt(bpLocal += typeLength) != '\"') {
            return -1;
        }
        this.ch = this.charAt(++bpLocal);
        if (this.ch == ',') {
            this.ch = this.charAt(++bpLocal);
            this.bp = bpLocal;
            this.token = 16;
            return 3;
        }
        if (this.ch == '}') {
            this.ch = this.charAt(++bpLocal);
            if (this.ch == ',') {
                this.token = 16;
                this.ch = this.charAt(++bpLocal);
            } else if (this.ch == ']') {
                this.token = 15;
                this.ch = this.charAt(++bpLocal);
            } else if (this.ch == '}') {
                this.token = 13;
                this.ch = this.charAt(++bpLocal);
            } else if (this.ch == '\u001a') {
                this.token = 20;
            } else {
                return -1;
            }
            this.matchStat = 4;
        }
        this.bp = bpLocal;
        return this.matchStat;
    }

    public final boolean matchField(char[] fieldName) {
        while (!this.charArrayCompare(fieldName)) {
            if (JSONLexerBase.isWhitespace(this.ch)) {
                this.next();
                continue;
            }
            return false;
        }
        this.bp += fieldName.length;
        this.ch = this.charAt(this.bp);
        if (this.ch == '{') {
            this.next();
            this.token = 12;
        } else if (this.ch == '[') {
            this.next();
            this.token = 14;
        } else if (this.ch == 'S' && this.charAt(this.bp + 1) == 'e' && this.charAt(this.bp + 2) == 't' && this.charAt(this.bp + 3) == '[') {
            this.bp += 3;
            this.ch = this.charAt(this.bp);
            this.token = 21;
        } else {
            this.nextToken();
        }
        return true;
    }

    public int matchField(long fieldNameHash) {
        throw new UnsupportedOperationException();
    }

    public boolean seekArrayToItem(int index) {
        throw new UnsupportedOperationException();
    }

    public int seekObjectToField(long fieldNameHash, boolean deepScan) {
        throw new UnsupportedOperationException();
    }

    public int seekObjectToField(long[] fieldNameHash) {
        throw new UnsupportedOperationException();
    }

    public int seekObjectToFieldDeepScan(long fieldNameHash) {
        throw new UnsupportedOperationException();
    }

    public void skipObject() {
        throw new UnsupportedOperationException();
    }

    public void skipObject(boolean valid) {
        throw new UnsupportedOperationException();
    }

    public void skipArray() {
        throw new UnsupportedOperationException();
    }

    public abstract int indexOf(char var1, int var2);

    public abstract String addSymbol(int var1, int var2, int var3, SymbolTable var4);

    /*
     * Enabled aggressive block sorting
     */
    public String scanFieldString(char[] fieldName) {
        char chLocal;
        this.matchStat = 0;
        if (!this.charArrayCompare(fieldName)) {
            this.matchStat = -2;
            return this.stringDefaultValue();
        }
        int offset = fieldName.length;
        if ((chLocal = this.charAt(this.bp + offset++)) != '\"') {
            this.matchStat = -1;
            return this.stringDefaultValue();
        }
        int startIndex = this.bp + fieldName.length + 1;
        int endIndex = this.indexOf('\"', startIndex);
        if (endIndex == -1) {
            throw new JSONException("unclosed str");
        }
        int startIndex2 = this.bp + fieldName.length + 1;
        String stringVal = this.subString(startIndex2, endIndex - startIndex2);
        if (stringVal.indexOf(92) != -1) {
            while (true) {
                int slashCount = 0;
                for (int i = endIndex - 1; i >= 0 && this.charAt(i) == '\\'; ++slashCount, --i) {
                }
                if (slashCount % 2 == 0) {
                    int chars_len = endIndex - (this.bp + fieldName.length + 1);
                    char[] chars = this.sub_chars(this.bp + fieldName.length + 1, chars_len);
                    stringVal = JSONLexerBase.readString(chars, chars_len);
                    break;
                }
                endIndex = this.indexOf('\"', endIndex + 1);
            }
        }
        offset += endIndex - (this.bp + fieldName.length + 1) + 1;
        chLocal = this.charAt(this.bp + offset++);
        String strVal = stringVal;
        if (chLocal == ',') {
            this.bp += offset;
            this.ch = this.charAt(this.bp);
            this.matchStat = 3;
            return strVal;
        }
        if (chLocal != '}') {
            this.matchStat = -1;
            return this.stringDefaultValue();
        }
        if ((chLocal = this.charAt(this.bp + offset++)) == ',') {
            this.token = 16;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else if (chLocal == ']') {
            this.token = 15;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else if (chLocal == '}') {
            this.token = 13;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else {
            if (chLocal != '\u001a') {
                this.matchStat = -1;
                return this.stringDefaultValue();
            }
            this.token = 20;
            this.bp += offset - 1;
            this.ch = (char)26;
        }
        this.matchStat = 4;
        return strVal;
    }

    @Override
    public String scanString(char expectNextChar) {
        String stringVal;
        char chLocal;
        int offset;
        block13: {
            this.matchStat = 0;
            offset = 0;
            if ((chLocal = this.charAt(this.bp + offset++)) == 'n') {
                if (this.charAt(this.bp + offset) == 'u' && this.charAt(this.bp + offset + 1) == 'l' && this.charAt(this.bp + offset + 2) == 'l') {
                    offset += 3;
                } else {
                    this.matchStat = -1;
                    return null;
                }
                chLocal = this.charAt(this.bp + offset++);
                if (chLocal == expectNextChar) {
                    this.bp += offset;
                    this.ch = this.charAt(this.bp);
                    this.matchStat = 3;
                    return null;
                }
                this.matchStat = -1;
                return null;
            }
            while (true) {
                if (chLocal == '\"') {
                    int startIndex = this.bp + offset;
                    int endIndex = this.indexOf('\"', startIndex);
                    if (endIndex == -1) {
                        throw new JSONException("unclosed str");
                    }
                    stringVal = this.subString(this.bp + offset, endIndex - startIndex);
                    if (stringVal.indexOf(92) != -1) {
                        while (true) {
                            int slashCount = 0;
                            for (int i = endIndex - 1; i >= 0 && this.charAt(i) == '\\'; --i) {
                                ++slashCount;
                            }
                            if (slashCount % 2 == 0) break;
                            endIndex = this.indexOf('\"', endIndex + 1);
                        }
                        int chars_len = endIndex - startIndex;
                        char[] chars = this.sub_chars(this.bp + 1, chars_len);
                        stringVal = JSONLexerBase.readString(chars, chars_len);
                    }
                    offset += endIndex - startIndex + 1;
                    break block13;
                }
                if (!JSONLexerBase.isWhitespace(chLocal)) break;
                chLocal = this.charAt(this.bp + offset++);
            }
            this.matchStat = -1;
            return this.stringDefaultValue();
        }
        chLocal = this.charAt(this.bp + offset++);
        String strVal = stringVal;
        while (true) {
            if (chLocal == expectNextChar) {
                this.bp += offset;
                this.ch = this.charAt(this.bp);
                this.matchStat = 3;
                this.token = 16;
                return strVal;
            }
            if (!JSONLexerBase.isWhitespace(chLocal)) break;
            chLocal = this.charAt(this.bp + offset++);
        }
        if (chLocal == ']') {
            this.bp += offset;
            this.ch = this.charAt(this.bp);
            this.matchStat = -1;
        }
        return strVal;
    }

    /*
     * Enabled aggressive block sorting
     */
    public long scanFieldSymbol(char[] fieldName) {
        long hash;
        char chLocal;
        int offset;
        block12: {
            block13: {
                this.matchStat = 0;
                if (!this.charArrayCompare(fieldName)) {
                    this.matchStat = -2;
                    return 0L;
                }
                offset = fieldName.length;
                if ((chLocal = this.charAt(this.bp + offset++)) != '\"') {
                    this.matchStat = -1;
                    return 0L;
                }
                hash = -3750763034362895579L;
                do {
                    if ((chLocal = this.charAt(this.bp + offset++)) == '\"') {
                        if ((chLocal = this.charAt(this.bp + offset++)) != ',') break block12;
                        break block13;
                    }
                    hash ^= (long)chLocal;
                    hash *= 1099511628211L;
                } while (chLocal != '\\');
                this.matchStat = -1;
                return 0L;
            }
            this.bp += offset;
            this.ch = this.charAt(this.bp);
            this.matchStat = 3;
            return hash;
        }
        if (chLocal != '}') {
            this.matchStat = -1;
            return 0L;
        }
        if ((chLocal = this.charAt(this.bp + offset++)) == ',') {
            this.token = 16;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else if (chLocal == ']') {
            this.token = 15;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else if (chLocal == '}') {
            this.token = 13;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else {
            if (chLocal != '\u001a') {
                this.matchStat = -1;
                return 0L;
            }
            this.token = 20;
            this.bp += offset - 1;
            this.ch = (char)26;
        }
        this.matchStat = 4;
        return hash;
    }

    /*
     * Enabled aggressive block sorting
     */
    public long scanEnumSymbol(char[] fieldName) {
        long hash;
        int chLocal;
        int offset;
        block12: {
            block13: {
                this.matchStat = 0;
                if (!this.charArrayCompare(fieldName)) {
                    this.matchStat = -2;
                    return 0L;
                }
                offset = fieldName.length;
                if ((chLocal = this.charAt(this.bp + offset++)) != 34) {
                    this.matchStat = -1;
                    return 0L;
                }
                hash = -3750763034362895579L;
                do {
                    if ((chLocal = this.charAt(this.bp + offset++)) == 34) {
                        if ((chLocal = this.charAt(this.bp + offset++)) != 44) break block12;
                        break block13;
                    }
                    hash ^= (long)(chLocal >= 65 && chLocal <= 90 ? chLocal + 32 : chLocal);
                    hash *= 1099511628211L;
                } while (chLocal != 92);
                this.matchStat = -1;
                return 0L;
            }
            this.bp += offset;
            this.ch = this.charAt(this.bp);
            this.matchStat = 3;
            return hash;
        }
        if (chLocal != 125) {
            this.matchStat = -1;
            return 0L;
        }
        if ((chLocal = (int)this.charAt(this.bp + offset++)) == 44) {
            this.token = 16;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else if (chLocal == 93) {
            this.token = 15;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else if (chLocal == 125) {
            this.token = 13;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else {
            if (chLocal != 26) {
                this.matchStat = -1;
                return 0L;
            }
            this.token = 20;
            this.bp += offset - 1;
            this.ch = (char)26;
        }
        this.matchStat = 4;
        return hash;
    }

    @Override
    public Enum<?> scanEnum(Class<?> enumClass, SymbolTable symbolTable, char serperator) {
        String name = this.scanSymbolWithSeperator(symbolTable, serperator);
        if (name == null) {
            return null;
        }
        return Enum.valueOf(enumClass, name);
    }

    @Override
    public String scanSymbolWithSeperator(SymbolTable symbolTable, char serperator) {
        int hash;
        char chLocal;
        int offset;
        block8: {
            this.matchStat = 0;
            offset = 0;
            if ((chLocal = this.charAt(this.bp + offset++)) == 'n') {
                if (this.charAt(this.bp + offset) == 'u' && this.charAt(this.bp + offset + 1) == 'l' && this.charAt(this.bp + offset + 2) == 'l') {
                    offset += 3;
                } else {
                    this.matchStat = -1;
                    return null;
                }
                chLocal = this.charAt(this.bp + offset++);
                if (chLocal == serperator) {
                    this.bp += offset;
                    this.ch = this.charAt(this.bp);
                    this.matchStat = 3;
                    return null;
                }
                this.matchStat = -1;
                return null;
            }
            if (chLocal != '\"') {
                this.matchStat = -1;
                return null;
            }
            hash = 0;
            do {
                if ((chLocal = this.charAt(this.bp + offset++)) == '\"') break block8;
                hash = 31 * hash + chLocal;
            } while (chLocal != '\\');
            this.matchStat = -1;
            return null;
        }
        int start = this.bp + 0 + 1;
        int len = this.bp + offset - start - 1;
        String strVal = this.addSymbol(start, len, hash, symbolTable);
        chLocal = this.charAt(this.bp + offset++);
        while (true) {
            if (chLocal == serperator) {
                this.bp += offset;
                this.ch = this.charAt(this.bp);
                this.matchStat = 3;
                return strVal;
            }
            if (!JSONLexerBase.isWhitespace(chLocal)) break;
            chLocal = this.charAt(this.bp + offset++);
        }
        this.matchStat = -1;
        return strVal;
    }

    public Collection<String> newCollectionByType(Class<?> type) {
        if (type.isAssignableFrom(HashSet.class)) {
            return new HashSet<String>();
        }
        if (type.isAssignableFrom(ArrayList.class)) {
            return new ArrayList<String>();
        }
        if (type.isAssignableFrom(LinkedList.class)) {
            return new LinkedList<String>();
        }
        try {
            return (Collection)type.newInstance();
        } catch (Exception e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public Collection<String> scanFieldStringArray(char[] fieldName, Class<?> type) {
        char chLocal;
        int offset;
        Collection<String> list;
        block19: {
            this.matchStat = 0;
            if (!this.charArrayCompare(fieldName)) {
                this.matchStat = -2;
                return null;
            }
            list = this.newCollectionByType(type);
            offset = fieldName.length;
            if ((chLocal = this.charAt(this.bp + offset++)) != '[') {
                this.matchStat = -1;
                return null;
            }
            chLocal = this.charAt(this.bp + offset++);
            while (true) {
                block23: {
                    String stringVal;
                    int endIndex;
                    block21: {
                        block22: {
                            block20: {
                                if (chLocal != '\"') break block20;
                                int startIndex = this.bp + offset;
                                endIndex = this.indexOf('\"', startIndex);
                                if (endIndex == -1) {
                                    throw new JSONException("unclosed str");
                                }
                                int startIndex2 = this.bp + offset;
                                stringVal = this.subString(startIndex2, endIndex - startIndex2);
                                if (stringVal.indexOf(92) == -1) break block21;
                                break block22;
                            }
                            if (chLocal == 'n' && this.charAt(this.bp + offset) == 'u' && this.charAt(this.bp + offset + 1) == 'l' && this.charAt(this.bp + offset + 2) == 'l') {
                                offset += 3;
                                chLocal = this.charAt(this.bp + offset++);
                                list.add(null);
                                break block23;
                            } else {
                                if (chLocal != ']') throw new JSONException("illega str");
                                if (list.size() != 0) throw new JSONException("illega str");
                                chLocal = this.charAt(this.bp + offset++);
                                break block19;
                            }
                        }
                        while (true) {
                            int slashCount = 0;
                            for (int i = endIndex - 1; i >= 0 && this.charAt(i) == '\\'; ++slashCount, --i) {
                            }
                            if (slashCount % 2 == 0) {
                                int chars_len = endIndex - (this.bp + offset);
                                char[] chars = this.sub_chars(this.bp + offset, chars_len);
                                stringVal = JSONLexerBase.readString(chars, chars_len);
                                break;
                            }
                            endIndex = this.indexOf('\"', endIndex + 1);
                        }
                    }
                    offset += endIndex - (this.bp + offset) + 1;
                    chLocal = this.charAt(this.bp + offset++);
                    list.add(stringVal);
                }
                if (chLocal != ',') break;
                chLocal = this.charAt(this.bp + offset++);
            }
            if (chLocal != ']') {
                this.matchStat = -1;
                return null;
            }
            chLocal = this.charAt(this.bp + offset++);
        }
        if (chLocal == ',') {
            this.bp += offset;
            this.ch = this.charAt(this.bp);
            this.matchStat = 3;
            return list;
        }
        if (chLocal != '}') {
            this.matchStat = -1;
            return null;
        }
        if ((chLocal = this.charAt(this.bp + offset++)) == ',') {
            this.token = 16;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else if (chLocal == ']') {
            this.token = 15;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else if (chLocal == '}') {
            this.token = 13;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else {
            if (chLocal != '\u001a') {
                this.matchStat = -1;
                return null;
            }
            this.bp += offset - 1;
            this.token = 20;
            this.ch = (char)26;
        }
        this.matchStat = 4;
        return list;
    }

    @Override
    public void scanStringArray(Collection<String> list, char seperator) {
        char chLocal;
        int offset;
        block14: {
            this.matchStat = 0;
            offset = 0;
            if ((chLocal = this.charAt(this.bp + offset++)) == 'n' && this.charAt(this.bp + offset) == 'u' && this.charAt(this.bp + offset + 1) == 'l' && this.charAt(this.bp + offset + 2) == 'l' && this.charAt(this.bp + offset + 3) == seperator) {
                this.bp += 5;
                this.ch = this.charAt(this.bp);
                this.matchStat = 5;
                return;
            }
            if (chLocal != '[') {
                this.matchStat = -1;
                return;
            }
            chLocal = this.charAt(this.bp + offset++);
            while (true) {
                if (chLocal == 'n' && this.charAt(this.bp + offset) == 'u' && this.charAt(this.bp + offset + 1) == 'l' && this.charAt(this.bp + offset + 2) == 'l') {
                    offset += 3;
                    chLocal = this.charAt(this.bp + offset++);
                    list.add(null);
                } else {
                    if (chLocal == ']' && list.size() == 0) {
                        chLocal = this.charAt(this.bp + offset++);
                        break block14;
                    }
                    if (chLocal != '\"') {
                        this.matchStat = -1;
                        return;
                    }
                    int startIndex = this.bp + offset;
                    int endIndex = this.indexOf('\"', startIndex);
                    if (endIndex == -1) {
                        throw new JSONException("unclosed str");
                    }
                    String stringVal = this.subString(this.bp + offset, endIndex - startIndex);
                    if (stringVal.indexOf(92) != -1) {
                        while (true) {
                            int slashCount = 0;
                            for (int i = endIndex - 1; i >= 0 && this.charAt(i) == '\\'; --i) {
                                ++slashCount;
                            }
                            if (slashCount % 2 == 0) break;
                            endIndex = this.indexOf('\"', endIndex + 1);
                        }
                        int chars_len = endIndex - startIndex;
                        char[] chars = this.sub_chars(this.bp + offset, chars_len);
                        stringVal = JSONLexerBase.readString(chars, chars_len);
                    }
                    offset += endIndex - (this.bp + offset) + 1;
                    chLocal = this.charAt(this.bp + offset++);
                    list.add(stringVal);
                }
                if (chLocal != ',') break;
                chLocal = this.charAt(this.bp + offset++);
            }
            if (chLocal == ']') {
                chLocal = this.charAt(this.bp + offset++);
            } else {
                this.matchStat = -1;
                return;
            }
        }
        if (chLocal == seperator) {
            this.bp += offset;
            this.ch = this.charAt(this.bp);
            this.matchStat = 3;
            return;
        }
        this.matchStat = -1;
    }

    /*
     * Enabled aggressive block sorting
     */
    public int scanFieldInt(char[] fieldName) {
        int n;
        int value;
        char chLocal;
        boolean negative;
        this.matchStat = 0;
        if (!this.charArrayCompare(fieldName)) {
            this.matchStat = -2;
            return 0;
        }
        int offset = fieldName.length;
        boolean bl = negative = (chLocal = this.charAt(this.bp + offset++)) == '-';
        if (negative) {
            chLocal = this.charAt(this.bp + offset++);
        }
        if (chLocal >= '0' && chLocal <= '9') {
            value = chLocal - 48;
            while ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                value = value * 10 + (chLocal - 48);
            }
            if (chLocal == '.') {
                this.matchStat = -1;
                return 0;
            }
            if (!(value >= 0 && offset <= 14 + fieldName.length || value == Integer.MIN_VALUE && offset == 17 && negative)) {
                this.matchStat = -1;
                return 0;
            }
        } else {
            this.matchStat = -1;
            return 0;
        }
        if (chLocal == ',') {
            int n2;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            if (negative) {
                n2 = -value;
                return n2;
            }
            n2 = value;
            return n2;
        }
        if (chLocal != '}') {
            this.matchStat = -1;
            return 0;
        }
        if ((chLocal = this.charAt(this.bp + offset++)) == ',') {
            this.token = 16;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else if (chLocal == ']') {
            this.token = 15;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else if (chLocal == '}') {
            this.token = 13;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else {
            if (chLocal != '\u001a') {
                this.matchStat = -1;
                return 0;
            }
            this.token = 20;
            this.bp += offset - 1;
            this.ch = (char)26;
        }
        this.matchStat = 4;
        if (!negative) {
            n = value;
            return n;
        }
        n = -value;
        return n;
    }

    /*
     * Enabled aggressive block sorting
     */
    public final int[] scanFieldIntArray(char[] fieldName) {
        int arrayIndex;
        int[] array;
        char chLocal;
        int offset;
        block20: {
            block19: {
                this.matchStat = 0;
                if (!this.charArrayCompare(fieldName)) {
                    this.matchStat = -2;
                    return null;
                }
                offset = fieldName.length;
                if ((chLocal = this.charAt(this.bp + offset++)) != '[') {
                    this.matchStat = -2;
                    return null;
                }
                chLocal = this.charAt(this.bp + offset++);
                array = new int[16];
                arrayIndex = 0;
                if (chLocal == ']') {
                    chLocal = this.charAt(this.bp + offset++);
                } else {
                    while (true) {
                        boolean nagative = false;
                        if (chLocal == '-') {
                            chLocal = this.charAt(this.bp + offset++);
                            nagative = true;
                        }
                        if (chLocal < '0' || chLocal > '9') break block19;
                        int value = chLocal - 48;
                        while ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                            value = value * 10 + (chLocal - 48);
                        }
                        if (arrayIndex >= array.length) {
                            int[] tmp = new int[array.length * 3 / 2];
                            System.arraycopy(array, 0, tmp, 0, arrayIndex);
                            array = tmp;
                        }
                        int n = array[arrayIndex++] = nagative ? -value : value;
                        if (chLocal == ',') {
                            chLocal = this.charAt(this.bp + offset++);
                            continue;
                        }
                        if (chLocal == ']') break;
                    }
                    chLocal = this.charAt(this.bp + offset++);
                }
                break block20;
            }
            this.matchStat = -1;
            return null;
        }
        if (arrayIndex != array.length) {
            int[] tmp = new int[arrayIndex];
            System.arraycopy(array, 0, tmp, 0, arrayIndex);
            array = tmp;
        }
        if (chLocal == ',') {
            this.bp += offset - 1;
            this.next();
            this.matchStat = 3;
            this.token = 16;
            return array;
        }
        if (chLocal != '}') {
            this.matchStat = -1;
            return null;
        }
        if ((chLocal = this.charAt(this.bp + offset++)) == ',') {
            this.token = 16;
            this.bp += offset - 1;
            this.next();
        } else if (chLocal == ']') {
            this.token = 15;
            this.bp += offset - 1;
            this.next();
        } else if (chLocal == '}') {
            this.token = 13;
            this.bp += offset - 1;
            this.next();
        } else {
            if (chLocal != '\u001a') {
                this.matchStat = -1;
                return null;
            }
            this.bp += offset - 1;
            this.token = 20;
            this.ch = (char)26;
        }
        this.matchStat = 4;
        return array;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public boolean scanBoolean(char expectNext) {
        boolean value;
        char chLocal;
        int offset;
        block12: {
            this.matchStat = 0;
            offset = 0;
            chLocal = this.charAt(this.bp + offset++);
            value = false;
            if (chLocal == 't') {
                if (this.charAt(this.bp + offset) == 'r' && this.charAt(this.bp + offset + 1) == 'u' && this.charAt(this.bp + offset + 2) == 'e') {
                    offset += 3;
                    chLocal = this.charAt(this.bp + offset++);
                    value = true;
                    break block12;
                } else {
                    this.matchStat = -1;
                    return false;
                }
            }
            if (chLocal == 'f') {
                if (this.charAt(this.bp + offset) == 'a' && this.charAt(this.bp + offset + 1) == 'l' && this.charAt(this.bp + offset + 2) == 's' && this.charAt(this.bp + offset + 3) == 'e') {
                    offset += 4;
                    chLocal = this.charAt(this.bp + offset++);
                    value = false;
                    break block12;
                } else {
                    this.matchStat = -1;
                    return false;
                }
            }
            if (chLocal == '1') {
                chLocal = this.charAt(this.bp + offset++);
                value = true;
            } else if (chLocal == '0') {
                chLocal = this.charAt(this.bp + offset++);
                value = false;
            }
        }
        while (true) {
            if (chLocal == expectNext) {
                this.bp += offset;
                this.ch = this.charAt(this.bp);
                this.matchStat = 3;
                return value;
            }
            if (!JSONLexerBase.isWhitespace(chLocal)) {
                this.matchStat = -1;
                return value;
            }
            chLocal = this.charAt(this.bp + offset++);
        }
    }

    @Override
    public int scanInt(char expectNext) {
        int value;
        boolean negative;
        char chLocal;
        boolean quote;
        this.matchStat = 0;
        int offset = 0;
        boolean bl = quote = (chLocal = this.charAt(this.bp + offset++)) == '\"';
        if (quote) {
            chLocal = this.charAt(this.bp + offset++);
        }
        boolean bl2 = negative = chLocal == '-';
        if (negative) {
            chLocal = this.charAt(this.bp + offset++);
        }
        if (chLocal >= '0' && chLocal <= '9') {
            value = chLocal - 48;
            while ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                value = value * 10 + (chLocal - 48);
            }
            if (chLocal == '.') {
                this.matchStat = -1;
                return 0;
            }
            if (value < 0) {
                this.matchStat = -1;
                return 0;
            }
        } else {
            if (chLocal == 'n' && this.charAt(this.bp + offset) == 'u' && this.charAt(this.bp + offset + 1) == 'l' && this.charAt(this.bp + offset + 2) == 'l') {
                this.matchStat = 5;
                int value2 = 0;
                offset += 3;
                chLocal = this.charAt(this.bp + offset++);
                if (quote && chLocal == '\"') {
                    chLocal = this.charAt(this.bp + offset++);
                }
                while (true) {
                    if (chLocal == ',') {
                        this.bp += offset;
                        this.ch = this.charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 16;
                        return value2;
                    }
                    if (chLocal == ']') {
                        this.bp += offset;
                        this.ch = this.charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 15;
                        return value2;
                    }
                    if (!JSONLexerBase.isWhitespace(chLocal)) break;
                    chLocal = this.charAt(this.bp + offset++);
                }
                this.matchStat = -1;
                return 0;
            }
            this.matchStat = -1;
            return 0;
        }
        while (true) {
            if (chLocal == expectNext) {
                this.bp += offset;
                this.ch = this.charAt(this.bp);
                this.matchStat = 3;
                this.token = 16;
                return negative ? -value : value;
            }
            if (!JSONLexerBase.isWhitespace(chLocal)) break;
            chLocal = this.charAt(this.bp + offset++);
        }
        this.matchStat = -1;
        return negative ? -value : value;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean scanFieldBoolean(char[] fieldName) {
        boolean value;
        char chLocal;
        this.matchStat = 0;
        if (!this.charArrayCompare(fieldName)) {
            this.matchStat = -2;
            return false;
        }
        int offset = fieldName.length;
        if ((chLocal = this.charAt(this.bp + offset++)) == 't') {
            if (this.charAt(this.bp + offset++) != 'r') {
                this.matchStat = -1;
                return false;
            }
            if (this.charAt(this.bp + offset++) != 'u') {
                this.matchStat = -1;
                return false;
            }
            if (this.charAt(this.bp + offset++) != 'e') {
                this.matchStat = -1;
                return false;
            }
            value = true;
        } else {
            if (chLocal != 'f') {
                this.matchStat = -1;
                return false;
            }
            if (this.charAt(this.bp + offset++) != 'a') {
                this.matchStat = -1;
                return false;
            }
            if (this.charAt(this.bp + offset++) != 'l') {
                this.matchStat = -1;
                return false;
            }
            if (this.charAt(this.bp + offset++) != 's') {
                this.matchStat = -1;
                return false;
            }
            if (this.charAt(this.bp + offset++) != 'e') {
                this.matchStat = -1;
                return false;
            }
            value = false;
        }
        chLocal = this.charAt(this.bp + offset++);
        if (chLocal == ',') {
            this.bp += offset;
            this.ch = this.charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return value;
        }
        if (chLocal != '}') {
            this.matchStat = -1;
            return false;
        }
        if ((chLocal = this.charAt(this.bp + offset++)) == ',') {
            this.token = 16;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else if (chLocal == ']') {
            this.token = 15;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else if (chLocal == '}') {
            this.token = 13;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else {
            if (chLocal != '\u001a') {
                this.matchStat = -1;
                return false;
            }
            this.token = 20;
            this.bp += offset - 1;
            this.ch = (char)26;
        }
        this.matchStat = 4;
        return value;
    }

    /*
     * Enabled aggressive block sorting
     */
    public long scanFieldLong(char[] fieldName) {
        long l;
        long value;
        this.matchStat = 0;
        if (!this.charArrayCompare(fieldName)) {
            this.matchStat = -2;
            return 0L;
        }
        int offset = fieldName.length;
        char chLocal = this.charAt(this.bp + offset++);
        boolean negative = false;
        if (chLocal == '-') {
            chLocal = this.charAt(this.bp + offset++);
            negative = true;
        }
        if (chLocal >= '0' && chLocal <= '9') {
            boolean valid;
            value = chLocal - 48;
            while ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                value = value * 10L + (long)(chLocal - 48);
            }
            if (chLocal == '.') {
                this.matchStat = -1;
                return 0L;
            }
            boolean bl = valid = offset - fieldName.length < 21 && (value >= 0L || value == Long.MIN_VALUE && negative);
            if (!valid) {
                this.matchStat = -1;
                return 0L;
            }
        } else {
            this.matchStat = -1;
            return 0L;
        }
        if (chLocal == ',') {
            long l2;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            if (negative) {
                l2 = -value;
                return l2;
            }
            l2 = value;
            return l2;
        }
        if (chLocal != '}') {
            this.matchStat = -1;
            return 0L;
        }
        if ((chLocal = this.charAt(this.bp + offset++)) == ',') {
            this.token = 16;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else if (chLocal == ']') {
            this.token = 15;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else if (chLocal == '}') {
            this.token = 13;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else {
            if (chLocal != '\u001a') {
                this.matchStat = -1;
                return 0L;
            }
            this.token = 20;
            this.bp += offset - 1;
            this.ch = (char)26;
        }
        this.matchStat = 4;
        if (!negative) {
            l = value;
            return l;
        }
        l = -value;
        return l;
    }

    @Override
    public long scanLong(char expectNextChar) {
        long value;
        boolean negative;
        char chLocal;
        boolean quote;
        this.matchStat = 0;
        int offset = 0;
        boolean bl = quote = (chLocal = this.charAt(this.bp + offset++)) == '\"';
        if (quote) {
            chLocal = this.charAt(this.bp + offset++);
        }
        boolean bl2 = negative = chLocal == '-';
        if (negative) {
            chLocal = this.charAt(this.bp + offset++);
        }
        if (chLocal >= '0' && chLocal <= '9') {
            boolean valid;
            value = chLocal - 48;
            while ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                value = value * 10L + (long)(chLocal - 48);
            }
            if (chLocal == '.') {
                this.matchStat = -1;
                return 0L;
            }
            boolean bl3 = valid = value >= 0L || value == Long.MIN_VALUE && negative;
            if (!valid) {
                String val = this.subString(this.bp, offset - 1);
                throw new NumberFormatException(val);
            }
        } else {
            if (chLocal == 'n' && this.charAt(this.bp + offset) == 'u' && this.charAt(this.bp + offset + 1) == 'l' && this.charAt(this.bp + offset + 2) == 'l') {
                this.matchStat = 5;
                long value2 = 0L;
                offset += 3;
                chLocal = this.charAt(this.bp + offset++);
                if (quote && chLocal == '\"') {
                    chLocal = this.charAt(this.bp + offset++);
                }
                while (true) {
                    if (chLocal == ',') {
                        this.bp += offset;
                        this.ch = this.charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 16;
                        return value2;
                    }
                    if (chLocal == ']') {
                        this.bp += offset;
                        this.ch = this.charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 15;
                        return value2;
                    }
                    if (!JSONLexerBase.isWhitespace(chLocal)) break;
                    chLocal = this.charAt(this.bp + offset++);
                }
                this.matchStat = -1;
                return 0L;
            }
            this.matchStat = -1;
            return 0L;
        }
        if (quote) {
            if (chLocal != '\"') {
                this.matchStat = -1;
                return 0L;
            }
            chLocal = this.charAt(this.bp + offset++);
        }
        while (true) {
            if (chLocal == expectNextChar) {
                this.bp += offset;
                this.ch = this.charAt(this.bp);
                this.matchStat = 3;
                this.token = 16;
                return negative ? -value : value;
            }
            if (!JSONLexerBase.isWhitespace(chLocal)) break;
            chLocal = this.charAt(this.bp + offset++);
        }
        this.matchStat = -1;
        return value;
    }

    /*
     * Enabled aggressive block sorting
     */
    public final float scanFieldFloat(char[] fieldName) {
        float value;
        char chLocal;
        int offset;
        block35: {
            float value2;
            block34: {
                boolean quote;
                block33: {
                    int count;
                    int start;
                    boolean exp;
                    boolean small;
                    boolean negative;
                    this.matchStat = 0;
                    if (!this.charArrayCompare(fieldName)) {
                        this.matchStat = -2;
                        return 0.0f;
                    }
                    offset = fieldName.length;
                    boolean bl = quote = (chLocal = this.charAt(this.bp + offset++)) == '\"';
                    if (quote) {
                        chLocal = this.charAt(this.bp + offset++);
                    }
                    boolean bl2 = negative = chLocal == '-';
                    if (negative) {
                        chLocal = this.charAt(this.bp + offset++);
                    }
                    if (chLocal < '0' || chLocal > '9') break block33;
                    long intVal = chLocal - 48;
                    while ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                        intVal = intVal * 10L + (long)(chLocal - 48);
                    }
                    long power = 1L;
                    boolean bl3 = small = chLocal == '.';
                    if (small) {
                        if ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                            intVal = intVal * 10L + (long)(chLocal - 48);
                            power = 10L;
                            while ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                                intVal = intVal * 10L + (long)(chLocal - 48);
                                power *= 10L;
                            }
                        } else {
                            this.matchStat = -1;
                            return 0.0f;
                        }
                    }
                    boolean bl4 = exp = chLocal == 'e' || chLocal == 'E';
                    if (exp) {
                        if ((chLocal = this.charAt(this.bp + offset++)) == '+' || chLocal == '-') {
                            chLocal = this.charAt(this.bp + offset++);
                        }
                        while (chLocal >= '0' && chLocal <= '9') {
                            chLocal = this.charAt(this.bp + offset++);
                        }
                    }
                    if (quote) {
                        if (chLocal != '\"') {
                            this.matchStat = -1;
                            return 0.0f;
                        }
                        chLocal = this.charAt(this.bp + offset++);
                        start = this.bp + fieldName.length + 1;
                        count = this.bp + offset - start - 2;
                    } else {
                        start = this.bp + fieldName.length;
                        count = this.bp + offset - start - 1;
                    }
                    if (!exp && count < 17) {
                        value2 = (float)((double)intVal / (double)power);
                        if (negative) {
                            value2 = -value2;
                        }
                        break block34;
                    } else {
                        String text = this.subString(start, count);
                        value2 = Float.parseFloat(text);
                    }
                    break block34;
                }
                if (chLocal == 'n' && this.charAt(this.bp + offset) == 'u' && this.charAt(this.bp + offset + 1) == 'l' && this.charAt(this.bp + offset + 2) == 'l') {
                    this.matchStat = 5;
                    value = 0.0f;
                    offset += 3;
                    chLocal = this.charAt(this.bp + offset++);
                    if (quote && chLocal == '\"') {
                        chLocal = this.charAt(this.bp + offset++);
                    }
                    break block35;
                } else {
                    this.matchStat = -1;
                    return 0.0f;
                }
            }
            if (chLocal == ',') {
                this.bp += offset;
                this.ch = this.charAt(this.bp);
                this.matchStat = 3;
                this.token = 16;
                return value2;
            }
            if (chLocal != '}') {
                this.matchStat = -1;
                return 0.0f;
            }
            if ((chLocal = this.charAt(this.bp + offset++)) == ',') {
                this.token = 16;
                this.bp += offset;
                this.ch = this.charAt(this.bp);
            } else if (chLocal == ']') {
                this.token = 15;
                this.bp += offset;
                this.ch = this.charAt(this.bp);
            } else if (chLocal == '}') {
                this.token = 13;
                this.bp += offset;
                this.ch = this.charAt(this.bp);
            } else {
                if (chLocal != '\u001a') {
                    this.matchStat = -1;
                    return 0.0f;
                }
                this.bp += offset - 1;
                this.token = 20;
                this.ch = (char)26;
            }
            this.matchStat = 4;
            return value2;
        }
        while (true) {
            if (chLocal == ',') {
                this.bp += offset;
                this.ch = this.charAt(this.bp);
                this.matchStat = 5;
                this.token = 16;
                return value;
            }
            if (chLocal == '}') {
                this.bp += offset;
                this.ch = this.charAt(this.bp);
                this.matchStat = 5;
                this.token = 13;
                return value;
            }
            if (!JSONLexerBase.isWhitespace(chLocal)) {
                this.matchStat = -1;
                return 0.0f;
            }
            chLocal = this.charAt(this.bp + offset++);
        }
    }

    @Override
    public final float scanFloat(char seperator) {
        float value;
        boolean negative;
        char chLocal;
        boolean quote;
        this.matchStat = 0;
        int offset = 0;
        boolean bl = quote = (chLocal = this.charAt(this.bp + offset++)) == '\"';
        if (quote) {
            chLocal = this.charAt(this.bp + offset++);
        }
        boolean bl2 = negative = chLocal == '-';
        if (negative) {
            chLocal = this.charAt(this.bp + offset++);
        }
        if (chLocal >= '0' && chLocal <= '9') {
            int count;
            int start;
            boolean exp;
            boolean small;
            long intVal = chLocal - 48;
            while ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                intVal = intVal * 10L + (long)(chLocal - 48);
            }
            long power = 1L;
            boolean bl3 = small = chLocal == '.';
            if (small) {
                if ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                    intVal = intVal * 10L + (long)(chLocal - 48);
                    power = 10L;
                    while ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                        intVal = intVal * 10L + (long)(chLocal - 48);
                        power *= 10L;
                    }
                } else {
                    this.matchStat = -1;
                    return 0.0f;
                }
            }
            boolean bl4 = exp = chLocal == 'e' || chLocal == 'E';
            if (exp) {
                if ((chLocal = this.charAt(this.bp + offset++)) == '+' || chLocal == '-') {
                    chLocal = this.charAt(this.bp + offset++);
                }
                while (chLocal >= '0' && chLocal <= '9') {
                    chLocal = this.charAt(this.bp + offset++);
                }
            }
            if (quote) {
                if (chLocal != '\"') {
                    this.matchStat = -1;
                    return 0.0f;
                }
                chLocal = this.charAt(this.bp + offset++);
                start = this.bp + 1;
                count = this.bp + offset - start - 2;
            } else {
                start = this.bp;
                count = this.bp + offset - start - 1;
            }
            if (!exp && count < 17) {
                value = (float)((double)intVal / (double)power);
                if (negative) {
                    value = -value;
                }
            } else {
                String text = this.subString(start, count);
                value = Float.parseFloat(text);
            }
        } else {
            if (chLocal == 'n' && this.charAt(this.bp + offset) == 'u' && this.charAt(this.bp + offset + 1) == 'l' && this.charAt(this.bp + offset + 2) == 'l') {
                this.matchStat = 5;
                float value2 = 0.0f;
                offset += 3;
                chLocal = this.charAt(this.bp + offset++);
                if (quote && chLocal == '\"') {
                    chLocal = this.charAt(this.bp + offset++);
                }
                while (true) {
                    if (chLocal == ',') {
                        this.bp += offset;
                        this.ch = this.charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 16;
                        return value2;
                    }
                    if (chLocal == ']') {
                        this.bp += offset;
                        this.ch = this.charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 15;
                        return value2;
                    }
                    if (!JSONLexerBase.isWhitespace(chLocal)) break;
                    chLocal = this.charAt(this.bp + offset++);
                }
                this.matchStat = -1;
                return 0.0f;
            }
            this.matchStat = -1;
            return 0.0f;
        }
        if (chLocal == seperator) {
            this.bp += offset;
            this.ch = this.charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return value;
        }
        this.matchStat = -1;
        return value;
    }

    @Override
    public double scanDouble(char seperator) {
        double value;
        boolean negative;
        char chLocal;
        boolean quote;
        this.matchStat = 0;
        int offset = 0;
        boolean bl = quote = (chLocal = this.charAt(this.bp + offset++)) == '\"';
        if (quote) {
            chLocal = this.charAt(this.bp + offset++);
        }
        boolean bl2 = negative = chLocal == '-';
        if (negative) {
            chLocal = this.charAt(this.bp + offset++);
        }
        if (chLocal >= '0' && chLocal <= '9') {
            int count;
            int start;
            boolean exp;
            boolean small;
            long intVal = chLocal - 48;
            while ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                intVal = intVal * 10L + (long)(chLocal - 48);
            }
            long power = 1L;
            boolean bl3 = small = chLocal == '.';
            if (small) {
                if ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                    intVal = intVal * 10L + (long)(chLocal - 48);
                    power = 10L;
                    while ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                        intVal = intVal * 10L + (long)(chLocal - 48);
                        power *= 10L;
                    }
                } else {
                    this.matchStat = -1;
                    return 0.0;
                }
            }
            boolean bl4 = exp = chLocal == 'e' || chLocal == 'E';
            if (exp) {
                if ((chLocal = this.charAt(this.bp + offset++)) == '+' || chLocal == '-') {
                    chLocal = this.charAt(this.bp + offset++);
                }
                while (chLocal >= '0' && chLocal <= '9') {
                    chLocal = this.charAt(this.bp + offset++);
                }
            }
            if (quote) {
                if (chLocal != '\"') {
                    this.matchStat = -1;
                    return 0.0;
                }
                chLocal = this.charAt(this.bp + offset++);
                start = this.bp + 1;
                count = this.bp + offset - start - 2;
            } else {
                start = this.bp;
                count = this.bp + offset - start - 1;
            }
            if (!exp && count < 17) {
                value = (double)intVal / (double)power;
                if (negative) {
                    value = -value;
                }
            } else {
                String text = this.subString(start, count);
                value = Double.parseDouble(text);
            }
        } else {
            if (chLocal == 'n' && this.charAt(this.bp + offset) == 'u' && this.charAt(this.bp + offset + 1) == 'l' && this.charAt(this.bp + offset + 2) == 'l') {
                this.matchStat = 5;
                double value2 = 0.0;
                offset += 3;
                chLocal = this.charAt(this.bp + offset++);
                if (quote && chLocal == '\"') {
                    chLocal = this.charAt(this.bp + offset++);
                }
                while (true) {
                    if (chLocal == ',') {
                        this.bp += offset;
                        this.ch = this.charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 16;
                        return value2;
                    }
                    if (chLocal == ']') {
                        this.bp += offset;
                        this.ch = this.charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 15;
                        return value2;
                    }
                    if (!JSONLexerBase.isWhitespace(chLocal)) break;
                    chLocal = this.charAt(this.bp + offset++);
                }
                this.matchStat = -1;
                return 0.0;
            }
            this.matchStat = -1;
            return 0.0;
        }
        if (chLocal == seperator) {
            this.bp += offset;
            this.ch = this.charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return value;
        }
        this.matchStat = -1;
        return value;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public BigDecimal scanDecimal(char seperator) {
        BigDecimal value;
        char chLocal;
        int offset;
        block32: {
            BigDecimal value2;
            block30: {
                block31: {
                    boolean quote;
                    block29: {
                        int count;
                        int start;
                        boolean exp;
                        boolean small;
                        boolean negative;
                        this.matchStat = 0;
                        offset = 0;
                        boolean bl = quote = (chLocal = this.charAt(this.bp + offset++)) == '\"';
                        if (quote) {
                            chLocal = this.charAt(this.bp + offset++);
                        }
                        boolean bl2 = negative = chLocal == '-';
                        if (negative) {
                            chLocal = this.charAt(this.bp + offset++);
                        }
                        if (chLocal < '0' || chLocal > '9') break block29;
                        while ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                        }
                        boolean bl3 = small = chLocal == '.';
                        if (small) {
                            if ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                                while ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                                }
                            } else {
                                this.matchStat = -1;
                                return null;
                            }
                        }
                        boolean bl4 = exp = chLocal == 'e' || chLocal == 'E';
                        if (exp) {
                            if ((chLocal = this.charAt(this.bp + offset++)) == '+' || chLocal == '-') {
                                chLocal = this.charAt(this.bp + offset++);
                            }
                            while (chLocal >= '0' && chLocal <= '9') {
                                chLocal = this.charAt(this.bp + offset++);
                            }
                        }
                        if (quote) {
                            if (chLocal != '\"') {
                                this.matchStat = -1;
                                return null;
                            }
                            chLocal = this.charAt(this.bp + offset++);
                            start = this.bp + 1;
                            count = this.bp + offset - start - 2;
                        } else {
                            start = this.bp;
                            count = this.bp + offset - start - 1;
                        }
                        if (count > 65535) {
                            throw new JSONException("decimal overflow");
                        }
                        char[] chars = this.sub_chars(start, count);
                        value2 = new BigDecimal(chars, 0, chars.length, MathContext.UNLIMITED);
                        if (chLocal != ',') break block30;
                        break block31;
                    }
                    if (chLocal == 'n' && this.charAt(this.bp + offset) == 'u' && this.charAt(this.bp + offset + 1) == 'l' && this.charAt(this.bp + offset + 2) == 'l') {
                        this.matchStat = 5;
                        value = null;
                        offset += 3;
                        chLocal = this.charAt(this.bp + offset++);
                        if (quote && chLocal == '\"') {
                            chLocal = this.charAt(this.bp + offset++);
                        }
                        break block32;
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                }
                this.bp += offset;
                this.ch = this.charAt(this.bp);
                this.matchStat = 3;
                this.token = 16;
                return value2;
            }
            if (chLocal != ']') {
                this.matchStat = -1;
                return null;
            }
            if ((chLocal = this.charAt(this.bp + offset++)) == ',') {
                this.token = 16;
                this.bp += offset;
                this.ch = this.charAt(this.bp);
            } else if (chLocal == ']') {
                this.token = 15;
                this.bp += offset;
                this.ch = this.charAt(this.bp);
            } else if (chLocal == '}') {
                this.token = 13;
                this.bp += offset;
                this.ch = this.charAt(this.bp);
            } else {
                if (chLocal != '\u001a') {
                    this.matchStat = -1;
                    return null;
                }
                this.token = 20;
                this.bp += offset - 1;
                this.ch = (char)26;
            }
            this.matchStat = 4;
            return value2;
        }
        while (true) {
            if (chLocal == ',') {
                this.bp += offset;
                this.ch = this.charAt(this.bp);
                this.matchStat = 5;
                this.token = 16;
                return value;
            }
            if (chLocal == '}') {
                this.bp += offset;
                this.ch = this.charAt(this.bp);
                this.matchStat = 5;
                this.token = 13;
                return value;
            }
            if (!JSONLexerBase.isWhitespace(chLocal)) {
                this.matchStat = -1;
                return null;
            }
            chLocal = this.charAt(this.bp + offset++);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public final float[] scanFieldFloatArray(char[] fieldName) {
        float[] array;
        char chLocal;
        int offset;
        block27: {
            int arrayIndex;
            block28: {
                block26: {
                    this.matchStat = 0;
                    if (!this.charArrayCompare(fieldName)) {
                        this.matchStat = -2;
                        return null;
                    }
                    offset = fieldName.length;
                    if ((chLocal = this.charAt(this.bp + offset++)) != '[') {
                        this.matchStat = -2;
                        return null;
                    }
                    chLocal = this.charAt(this.bp + offset++);
                    array = new float[16];
                    arrayIndex = 0;
                    while (true) {
                        float value;
                        boolean exp;
                        boolean small;
                        boolean negative;
                        int start = this.bp + offset - 1;
                        boolean bl = negative = chLocal == '-';
                        if (negative) {
                            chLocal = this.charAt(this.bp + offset++);
                        }
                        if (chLocal < '0' || chLocal > '9') break block26;
                        int intVal = chLocal - 48;
                        while ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                            intVal = intVal * 10 + (chLocal - 48);
                        }
                        int power = 1;
                        boolean bl2 = small = chLocal == '.';
                        if (small) {
                            chLocal = this.charAt(this.bp + offset++);
                            power = 10;
                            if (chLocal >= '0' && chLocal <= '9') {
                                intVal = intVal * 10 + (chLocal - 48);
                                while ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                                    intVal = intVal * 10 + (chLocal - 48);
                                    power *= 10;
                                }
                            } else {
                                this.matchStat = -1;
                                return null;
                            }
                        }
                        boolean bl3 = exp = chLocal == 'e' || chLocal == 'E';
                        if (exp) {
                            if ((chLocal = this.charAt(this.bp + offset++)) == '+' || chLocal == '-') {
                                chLocal = this.charAt(this.bp + offset++);
                            }
                            while (chLocal >= '0' && chLocal <= '9') {
                                chLocal = this.charAt(this.bp + offset++);
                            }
                        }
                        int count = this.bp + offset - start - 1;
                        if (!exp && count < 10) {
                            value = (float)intVal / (float)power;
                            if (negative) {
                                value = -value;
                            }
                        } else {
                            String text = this.subString(start, count);
                            value = Float.parseFloat(text);
                        }
                        if (arrayIndex >= array.length) {
                            float[] tmp = new float[array.length * 3 / 2];
                            System.arraycopy(array, 0, tmp, 0, arrayIndex);
                            array = tmp;
                        }
                        array[arrayIndex++] = value;
                        if (chLocal == ',') {
                            chLocal = this.charAt(this.bp + offset++);
                            continue;
                        }
                        if (chLocal == ']') break;
                    }
                    chLocal = this.charAt(this.bp + offset++);
                    if (arrayIndex == array.length) break block27;
                    break block28;
                }
                this.matchStat = -1;
                return null;
            }
            float[] tmp = new float[arrayIndex];
            System.arraycopy(array, 0, tmp, 0, arrayIndex);
            array = tmp;
        }
        if (chLocal == ',') {
            this.bp += offset - 1;
            this.next();
            this.matchStat = 3;
            this.token = 16;
            return array;
        }
        if (chLocal != '}') {
            this.matchStat = -1;
            return null;
        }
        if ((chLocal = this.charAt(this.bp + offset++)) == ',') {
            this.token = 16;
            this.bp += offset - 1;
            this.next();
        } else if (chLocal == ']') {
            this.token = 15;
            this.bp += offset - 1;
            this.next();
        } else if (chLocal == '}') {
            this.token = 13;
            this.bp += offset - 1;
            this.next();
        } else {
            if (chLocal != '\u001a') {
                this.matchStat = -1;
                return null;
            }
            this.bp += offset - 1;
            this.token = 20;
            this.ch = (char)26;
        }
        this.matchStat = 4;
        return array;
    }

    /*
     * Enabled aggressive block sorting
     */
    public final float[][] scanFieldFloatArray2(char[] fieldName) {
        char chLocal;
        this.matchStat = 0;
        if (!this.charArrayCompare(fieldName)) {
            this.matchStat = -2;
            return null;
        }
        int offset = fieldName.length;
        if ((chLocal = this.charAt(this.bp + offset++)) != '[') {
            this.matchStat = -2;
            return null;
        }
        chLocal = this.charAt(this.bp + offset++);
        float[][] arrayarray = new float[16][];
        int arrayarrayIndex = 0;
        while (chLocal == '[') {
            int arrayIndex;
            float[] array;
            block31: {
                block32: {
                    block30: {
                        chLocal = this.charAt(this.bp + offset++);
                        array = new float[16];
                        arrayIndex = 0;
                        while (true) {
                            float value;
                            boolean exp;
                            boolean negative;
                            int start = this.bp + offset - 1;
                            boolean bl = negative = chLocal == '-';
                            if (negative) {
                                chLocal = this.charAt(this.bp + offset++);
                            }
                            if (chLocal < '0' || chLocal > '9') break block30;
                            int intVal = chLocal - 48;
                            while ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                                intVal = intVal * 10 + (chLocal - 48);
                            }
                            int power = 1;
                            if (chLocal == '.') {
                                if ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                                    intVal = intVal * 10 + (chLocal - 48);
                                    power = 10;
                                    while ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                                        intVal = intVal * 10 + (chLocal - 48);
                                        power *= 10;
                                    }
                                } else {
                                    this.matchStat = -1;
                                    return null;
                                }
                            }
                            boolean bl2 = exp = chLocal == 'e' || chLocal == 'E';
                            if (exp) {
                                if ((chLocal = this.charAt(this.bp + offset++)) == '+' || chLocal == '-') {
                                    chLocal = this.charAt(this.bp + offset++);
                                }
                                while (chLocal >= '0' && chLocal <= '9') {
                                    chLocal = this.charAt(this.bp + offset++);
                                }
                            }
                            int count = this.bp + offset - start - 1;
                            if (!exp && count < 10) {
                                value = (float)intVal / (float)power;
                                if (negative) {
                                    value = -value;
                                }
                            } else {
                                String text = this.subString(start, count);
                                value = Float.parseFloat(text);
                            }
                            if (arrayIndex >= array.length) {
                                float[] tmp = new float[array.length * 3 / 2];
                                System.arraycopy(array, 0, tmp, 0, arrayIndex);
                                array = tmp;
                            }
                            array[arrayIndex++] = value;
                            if (chLocal == ',') {
                                chLocal = this.charAt(this.bp + offset++);
                                continue;
                            }
                            if (chLocal == ']') break;
                        }
                        chLocal = this.charAt(this.bp + offset++);
                        if (arrayIndex == array.length) break block31;
                        break block32;
                    }
                    this.matchStat = -1;
                    return null;
                }
                float[] tmp = new float[arrayIndex];
                System.arraycopy(array, 0, tmp, 0, arrayIndex);
                array = tmp;
            }
            if (arrayarrayIndex >= arrayarray.length) {
                float[][] tmp = new float[arrayarray.length * 3 / 2][];
                System.arraycopy(array, 0, tmp, 0, arrayIndex);
                arrayarray = tmp;
            }
            arrayarray[arrayarrayIndex++] = array;
            if (chLocal == ',') {
                chLocal = this.charAt(this.bp + offset++);
                continue;
            }
            if (chLocal != ']') continue;
            chLocal = this.charAt(this.bp + offset++);
            break;
        }
        if (arrayarrayIndex != arrayarray.length) {
            float[][] tmp = new float[arrayarrayIndex][];
            System.arraycopy(arrayarray, 0, tmp, 0, arrayarrayIndex);
            arrayarray = tmp;
        }
        if (chLocal == ',') {
            this.bp += offset - 1;
            this.next();
            this.matchStat = 3;
            this.token = 16;
            return arrayarray;
        }
        if (chLocal != '}') {
            this.matchStat = -1;
            return null;
        }
        if ((chLocal = this.charAt(this.bp + offset++)) == ',') {
            this.token = 16;
            this.bp += offset - 1;
            this.next();
        } else if (chLocal == ']') {
            this.token = 15;
            this.bp += offset - 1;
            this.next();
        } else if (chLocal == '}') {
            this.token = 13;
            this.bp += offset - 1;
            this.next();
        } else {
            if (chLocal != '\u001a') {
                this.matchStat = -1;
                return null;
            }
            this.bp += offset - 1;
            this.token = 20;
            this.ch = (char)26;
        }
        this.matchStat = 4;
        return arrayarray;
    }

    /*
     * Enabled aggressive block sorting
     */
    public final double scanFieldDouble(char[] fieldName) {
        double value;
        char chLocal;
        int offset;
        block35: {
            double value2;
            block34: {
                boolean quote;
                block33: {
                    int count;
                    int start;
                    boolean exp;
                    boolean small;
                    boolean negative;
                    this.matchStat = 0;
                    if (!this.charArrayCompare(fieldName)) {
                        this.matchStat = -2;
                        return 0.0;
                    }
                    offset = fieldName.length;
                    boolean bl = quote = (chLocal = this.charAt(this.bp + offset++)) == '\"';
                    if (quote) {
                        chLocal = this.charAt(this.bp + offset++);
                    }
                    boolean bl2 = negative = chLocal == '-';
                    if (negative) {
                        chLocal = this.charAt(this.bp + offset++);
                    }
                    if (chLocal < '0' || chLocal > '9') break block33;
                    long intVal = chLocal - 48;
                    while ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                        intVal = intVal * 10L + (long)(chLocal - 48);
                    }
                    long power = 1L;
                    boolean bl3 = small = chLocal == '.';
                    if (small) {
                        if ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                            intVal = intVal * 10L + (long)(chLocal - 48);
                            power = 10L;
                            while ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                                intVal = intVal * 10L + (long)(chLocal - 48);
                                power *= 10L;
                            }
                        } else {
                            this.matchStat = -1;
                            return 0.0;
                        }
                    }
                    boolean bl4 = exp = chLocal == 'e' || chLocal == 'E';
                    if (exp) {
                        if ((chLocal = this.charAt(this.bp + offset++)) == '+' || chLocal == '-') {
                            chLocal = this.charAt(this.bp + offset++);
                        }
                        while (chLocal >= '0' && chLocal <= '9') {
                            chLocal = this.charAt(this.bp + offset++);
                        }
                    }
                    if (quote) {
                        if (chLocal != '\"') {
                            this.matchStat = -1;
                            return 0.0;
                        }
                        chLocal = this.charAt(this.bp + offset++);
                        start = this.bp + fieldName.length + 1;
                        count = this.bp + offset - start - 2;
                    } else {
                        start = this.bp + fieldName.length;
                        count = this.bp + offset - start - 1;
                    }
                    if (!exp && count < 17) {
                        value2 = (double)intVal / (double)power;
                        if (negative) {
                            value2 = -value2;
                        }
                        break block34;
                    } else {
                        String text = this.subString(start, count);
                        value2 = Double.parseDouble(text);
                    }
                    break block34;
                }
                if (chLocal == 'n' && this.charAt(this.bp + offset) == 'u' && this.charAt(this.bp + offset + 1) == 'l' && this.charAt(this.bp + offset + 2) == 'l') {
                    this.matchStat = 5;
                    value = 0.0;
                    offset += 3;
                    chLocal = this.charAt(this.bp + offset++);
                    if (quote && chLocal == '\"') {
                        chLocal = this.charAt(this.bp + offset++);
                    }
                    break block35;
                } else {
                    this.matchStat = -1;
                    return 0.0;
                }
            }
            if (chLocal == ',') {
                this.bp += offset;
                this.ch = this.charAt(this.bp);
                this.matchStat = 3;
                this.token = 16;
                return value2;
            }
            if (chLocal != '}') {
                this.matchStat = -1;
                return 0.0;
            }
            if ((chLocal = this.charAt(this.bp + offset++)) == ',') {
                this.token = 16;
                this.bp += offset;
                this.ch = this.charAt(this.bp);
            } else if (chLocal == ']') {
                this.token = 15;
                this.bp += offset;
                this.ch = this.charAt(this.bp);
            } else if (chLocal == '}') {
                this.token = 13;
                this.bp += offset;
                this.ch = this.charAt(this.bp);
            } else {
                if (chLocal != '\u001a') {
                    this.matchStat = -1;
                    return 0.0;
                }
                this.token = 20;
                this.bp += offset - 1;
                this.ch = (char)26;
            }
            this.matchStat = 4;
            return value2;
        }
        while (true) {
            if (chLocal == ',') {
                this.bp += offset;
                this.ch = this.charAt(this.bp);
                this.matchStat = 5;
                this.token = 16;
                return value;
            }
            if (chLocal == '}') {
                this.bp += offset;
                this.ch = this.charAt(this.bp);
                this.matchStat = 5;
                this.token = 13;
                return value;
            }
            if (!JSONLexerBase.isWhitespace(chLocal)) {
                this.matchStat = -1;
                return 0.0;
            }
            chLocal = this.charAt(this.bp + offset++);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public BigDecimal scanFieldDecimal(char[] fieldName) {
        BigDecimal value;
        char chLocal;
        int offset;
        block33: {
            BigDecimal value2;
            block31: {
                block32: {
                    boolean quote;
                    block30: {
                        int count;
                        int start;
                        boolean exp;
                        boolean small;
                        boolean negative;
                        this.matchStat = 0;
                        if (!this.charArrayCompare(fieldName)) {
                            this.matchStat = -2;
                            return null;
                        }
                        offset = fieldName.length;
                        boolean bl = quote = (chLocal = this.charAt(this.bp + offset++)) == '\"';
                        if (quote) {
                            chLocal = this.charAt(this.bp + offset++);
                        }
                        boolean bl2 = negative = chLocal == '-';
                        if (negative) {
                            chLocal = this.charAt(this.bp + offset++);
                        }
                        if (chLocal < '0' || chLocal > '9') break block30;
                        while ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                        }
                        boolean bl3 = small = chLocal == '.';
                        if (small) {
                            if ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                                while ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                                }
                            } else {
                                this.matchStat = -1;
                                return null;
                            }
                        }
                        boolean bl4 = exp = chLocal == 'e' || chLocal == 'E';
                        if (exp) {
                            if ((chLocal = this.charAt(this.bp + offset++)) == '+' || chLocal == '-') {
                                chLocal = this.charAt(this.bp + offset++);
                            }
                            while (chLocal >= '0' && chLocal <= '9') {
                                chLocal = this.charAt(this.bp + offset++);
                            }
                        }
                        if (quote) {
                            if (chLocal != '\"') {
                                this.matchStat = -1;
                                return null;
                            }
                            chLocal = this.charAt(this.bp + offset++);
                            start = this.bp + fieldName.length + 1;
                            count = this.bp + offset - start - 2;
                        } else {
                            start = this.bp + fieldName.length;
                            count = this.bp + offset - start - 1;
                        }
                        if (count > 65535) {
                            throw new JSONException("scan decimal overflow");
                        }
                        char[] chars = this.sub_chars(start, count);
                        value2 = new BigDecimal(chars, 0, chars.length, MathContext.UNLIMITED);
                        if (chLocal != ',') break block31;
                        break block32;
                    }
                    if (chLocal == 'n' && this.charAt(this.bp + offset) == 'u' && this.charAt(this.bp + offset + 1) == 'l' && this.charAt(this.bp + offset + 2) == 'l') {
                        this.matchStat = 5;
                        value = null;
                        offset += 3;
                        chLocal = this.charAt(this.bp + offset++);
                        if (quote && chLocal == '\"') {
                            chLocal = this.charAt(this.bp + offset++);
                        }
                        break block33;
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                }
                this.bp += offset;
                this.ch = this.charAt(this.bp);
                this.matchStat = 3;
                this.token = 16;
                return value2;
            }
            if (chLocal != '}') {
                this.matchStat = -1;
                return null;
            }
            if ((chLocal = this.charAt(this.bp + offset++)) == ',') {
                this.token = 16;
                this.bp += offset;
                this.ch = this.charAt(this.bp);
            } else if (chLocal == ']') {
                this.token = 15;
                this.bp += offset;
                this.ch = this.charAt(this.bp);
            } else if (chLocal == '}') {
                this.token = 13;
                this.bp += offset;
                this.ch = this.charAt(this.bp);
            } else {
                if (chLocal != '\u001a') {
                    this.matchStat = -1;
                    return null;
                }
                this.token = 20;
                this.bp += offset - 1;
                this.ch = (char)26;
            }
            this.matchStat = 4;
            return value2;
        }
        while (true) {
            if (chLocal == ',') {
                this.bp += offset;
                this.ch = this.charAt(this.bp);
                this.matchStat = 5;
                this.token = 16;
                return value;
            }
            if (chLocal == '}') {
                this.bp += offset;
                this.ch = this.charAt(this.bp);
                this.matchStat = 5;
                this.token = 13;
                return value;
            }
            if (!JSONLexerBase.isWhitespace(chLocal)) {
                this.matchStat = -1;
                return null;
            }
            chLocal = this.charAt(this.bp + offset++);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public BigInteger scanFieldBigInteger(char[] fieldName) {
        BigInteger value;
        char chLocal;
        int offset;
        block29: {
            BigInteger value2;
            int count;
            int start;
            boolean overflow;
            long intVal;
            boolean negative;
            boolean quote;
            block28: {
                block27: {
                    this.matchStat = 0;
                    if (!this.charArrayCompare(fieldName)) {
                        this.matchStat = -2;
                        return null;
                    }
                    offset = fieldName.length;
                    boolean bl = quote = (chLocal = this.charAt(this.bp + offset++)) == '\"';
                    if (quote) {
                        chLocal = this.charAt(this.bp + offset++);
                    }
                    boolean bl2 = negative = chLocal == '-';
                    if (negative) {
                        chLocal = this.charAt(this.bp + offset++);
                    }
                    if (chLocal < '0' || chLocal > '9') break block27;
                    intVal = chLocal - 48;
                    overflow = false;
                    break block28;
                }
                if (chLocal == 'n' && this.charAt(this.bp + offset) == 'u' && this.charAt(this.bp + offset + 1) == 'l' && this.charAt(this.bp + offset + 2) == 'l') {
                    this.matchStat = 5;
                    value = null;
                    offset += 3;
                    chLocal = this.charAt(this.bp + offset++);
                    if (quote && chLocal == '\"') {
                        chLocal = this.charAt(this.bp + offset++);
                    }
                    break block29;
                } else {
                    this.matchStat = -1;
                    return null;
                }
            }
            while ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                long temp = intVal * 10L + (long)(chLocal - 48);
                if (temp < intVal) {
                    overflow = true;
                    break;
                }
                intVal = temp;
            }
            if (quote) {
                if (chLocal != '\"') {
                    this.matchStat = -1;
                    return null;
                }
                chLocal = this.charAt(this.bp + offset++);
                start = this.bp + fieldName.length + 1;
                count = this.bp + offset - start - 2;
            } else {
                start = this.bp + fieldName.length;
                count = this.bp + offset - start - 1;
            }
            if (!overflow && (count < 20 || negative && count < 21)) {
                value2 = BigInteger.valueOf(negative ? -intVal : intVal);
            } else {
                if (count > 65535) {
                    throw new JSONException("scanInteger overflow");
                }
                String strVal = this.subString(start, count);
                value2 = new BigInteger(strVal, 10);
            }
            if (chLocal == ',') {
                this.bp += offset;
                this.ch = this.charAt(this.bp);
                this.matchStat = 3;
                this.token = 16;
                return value2;
            }
            if (chLocal != '}') {
                this.matchStat = -1;
                return null;
            }
            if ((chLocal = this.charAt(this.bp + offset++)) == ',') {
                this.token = 16;
                this.bp += offset;
                this.ch = this.charAt(this.bp);
            } else if (chLocal == ']') {
                this.token = 15;
                this.bp += offset;
                this.ch = this.charAt(this.bp);
            } else if (chLocal == '}') {
                this.token = 13;
                this.bp += offset;
                this.ch = this.charAt(this.bp);
            } else {
                if (chLocal != '\u001a') {
                    this.matchStat = -1;
                    return null;
                }
                this.token = 20;
                this.bp += offset - 1;
                this.ch = (char)26;
            }
            this.matchStat = 4;
            return value2;
        }
        while (true) {
            if (chLocal == ',') {
                this.bp += offset;
                this.ch = this.charAt(this.bp);
                this.matchStat = 5;
                this.token = 16;
                return value;
            }
            if (chLocal == '}') {
                this.bp += offset;
                this.ch = this.charAt(this.bp);
                this.matchStat = 5;
                this.token = 13;
                return value;
            }
            if (!JSONLexerBase.isWhitespace(chLocal)) {
                this.matchStat = -1;
                return null;
            }
            chLocal = this.charAt(this.bp + offset++);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public Date scanFieldDate(char[] fieldName) {
        Date dateVal;
        char chLocal;
        int offset;
        block25: {
            String stringVal;
            int endIndex;
            block27: {
                block28: {
                    block26: {
                        this.matchStat = 0;
                        if (!this.charArrayCompare(fieldName)) {
                            this.matchStat = -2;
                            return null;
                        }
                        offset = fieldName.length;
                        if ((chLocal = this.charAt(this.bp + offset++)) != '\"') break block26;
                        int startIndex = this.bp + fieldName.length + 1;
                        endIndex = this.indexOf('\"', startIndex);
                        if (endIndex == -1) {
                            throw new JSONException("unclosed str");
                        }
                        int startIndex2 = this.bp + fieldName.length + 1;
                        stringVal = this.subString(startIndex2, endIndex - startIndex2);
                        if (stringVal.indexOf(92) == -1) break block27;
                        break block28;
                    }
                    if (chLocal == '-' || chLocal >= '0' && chLocal <= '9') {
                        long millis = 0L;
                        boolean negative = false;
                        if (chLocal == '-') {
                            chLocal = this.charAt(this.bp + offset++);
                            negative = true;
                        }
                        if (chLocal >= '0' && chLocal <= '9') {
                            millis = chLocal - 48;
                            while ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                                millis = millis * 10L + (long)(chLocal - 48);
                            }
                        }
                        if (millis < 0L) {
                            this.matchStat = -1;
                            return null;
                        }
                        if (negative) {
                            millis = -millis;
                        }
                        dateVal = new Date(millis);
                        break block25;
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                }
                while (true) {
                    int slashCount = 0;
                    for (int i = endIndex - 1; i >= 0 && this.charAt(i) == '\\'; ++slashCount, --i) {
                    }
                    if (slashCount % 2 == 0) {
                        int chars_len = endIndex - (this.bp + fieldName.length + 1);
                        char[] chars = this.sub_chars(this.bp + fieldName.length + 1, chars_len);
                        stringVal = JSONLexerBase.readString(chars, chars_len);
                        break;
                    }
                    endIndex = this.indexOf('\"', endIndex + 1);
                }
            }
            offset += endIndex - (this.bp + fieldName.length + 1) + 1;
            chLocal = this.charAt(this.bp + offset++);
            JSONScanner dateLexer = new JSONScanner(stringVal);
            try {
                if (!dateLexer.scanISO8601DateIfMatch(false)) {
                    this.matchStat = -1;
                    Date date = null;
                    return date;
                }
                Calendar calendar = dateLexer.getCalendar();
                dateVal = calendar.getTime();
            } finally {
                dateLexer.close();
            }
        }
        if (chLocal == ',') {
            this.bp += offset;
            this.ch = this.charAt(this.bp);
            this.matchStat = 3;
            return dateVal;
        }
        if (chLocal != '}') {
            this.matchStat = -1;
            return null;
        }
        if ((chLocal = this.charAt(this.bp + offset++)) == ',') {
            this.token = 16;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else if (chLocal == ']') {
            this.token = 15;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else if (chLocal == '}') {
            this.token = 13;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else {
            if (chLocal != '\u001a') {
                this.matchStat = -1;
                return null;
            }
            this.token = 20;
            this.bp += offset - 1;
            this.ch = (char)26;
        }
        this.matchStat = 4;
        return dateVal;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public Date scanDate(char seperator) {
        Date dateVal;
        char chLocal;
        int offset;
        block26: {
            String stringVal;
            int endIndex;
            block28: {
                block29: {
                    block27: {
                        this.matchStat = 0;
                        offset = 0;
                        if ((chLocal = this.charAt(this.bp + offset++)) != '\"') break block27;
                        int startIndex = this.bp + 1;
                        endIndex = this.indexOf('\"', startIndex);
                        if (endIndex == -1) {
                            throw new JSONException("unclosed str");
                        }
                        int startIndex2 = this.bp + 1;
                        stringVal = this.subString(startIndex2, endIndex - startIndex2);
                        if (stringVal.indexOf(92) == -1) break block28;
                        break block29;
                    }
                    if (chLocal == '-' || chLocal >= '0' && chLocal <= '9') {
                        long millis = 0L;
                        boolean negative = false;
                        if (chLocal == '-') {
                            chLocal = this.charAt(this.bp + offset++);
                            negative = true;
                        }
                        if (chLocal >= '0' && chLocal <= '9') {
                            millis = chLocal - 48;
                            while ((chLocal = this.charAt(this.bp + offset++)) >= '0' && chLocal <= '9') {
                                millis = millis * 10L + (long)(chLocal - 48);
                            }
                        }
                        if (millis < 0L) {
                            this.matchStat = -1;
                            return null;
                        }
                        if (negative) {
                            millis = -millis;
                        }
                        dateVal = new Date(millis);
                        break block26;
                    } else if (chLocal == 'n' && this.charAt(this.bp + offset) == 'u' && this.charAt(this.bp + offset + 1) == 'l' && this.charAt(this.bp + offset + 2) == 'l') {
                        this.matchStat = 5;
                        dateVal = null;
                        offset += 3;
                        chLocal = this.charAt(this.bp + offset++);
                        break block26;
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                }
                while (true) {
                    int slashCount = 0;
                    for (int i = endIndex - 1; i >= 0 && this.charAt(i) == '\\'; ++slashCount, --i) {
                    }
                    if (slashCount % 2 == 0) {
                        int chars_len = endIndex - (this.bp + 1);
                        char[] chars = this.sub_chars(this.bp + 1, chars_len);
                        stringVal = JSONLexerBase.readString(chars, chars_len);
                        break;
                    }
                    endIndex = this.indexOf('\"', endIndex + 1);
                }
            }
            offset += endIndex - (this.bp + 1) + 1;
            chLocal = this.charAt(this.bp + offset++);
            JSONScanner dateLexer = new JSONScanner(stringVal);
            try {
                if (!dateLexer.scanISO8601DateIfMatch(false)) {
                    this.matchStat = -1;
                    Date date = null;
                    return date;
                }
                Calendar calendar = dateLexer.getCalendar();
                dateVal = calendar.getTime();
            } finally {
                dateLexer.close();
            }
        }
        if (chLocal == ',') {
            this.bp += offset;
            this.ch = this.charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return dateVal;
        }
        if (chLocal != ']') {
            this.matchStat = -1;
            return null;
        }
        if ((chLocal = this.charAt(this.bp + offset++)) == ',') {
            this.token = 16;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else if (chLocal == ']') {
            this.token = 15;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else if (chLocal == '}') {
            this.token = 13;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else {
            if (chLocal != '\u001a') {
                this.matchStat = -1;
                return null;
            }
            this.token = 20;
            this.bp += offset - 1;
            this.ch = (char)26;
        }
        this.matchStat = 4;
        return dateVal;
    }

    /*
     * Enabled aggressive block sorting
     */
    public UUID scanFieldUUID(char[] fieldName) {
        UUID uuid;
        char chLocal;
        int offset;
        block63: {
            char ch;
            int i;
            int num;
            long leastSigBits;
            long mostSigBits;
            int startIndex2;
            int endIndex;
            block67: {
                char ch2;
                int i2;
                int num2;
                long leastSigBits2;
                long mostSigBits2;
                block66: {
                    block64: {
                        int len;
                        block65: {
                            this.matchStat = 0;
                            if (!this.charArrayCompare(fieldName)) {
                                this.matchStat = -2;
                                return null;
                            }
                            offset = fieldName.length;
                            if ((chLocal = this.charAt(this.bp + offset++)) != '\"') break block64;
                            int startIndex = this.bp + fieldName.length + 1;
                            endIndex = this.indexOf('\"', startIndex);
                            if (endIndex == -1) {
                                throw new JSONException("unclosed str");
                            }
                            startIndex2 = this.bp + fieldName.length + 1;
                            len = endIndex - startIndex2;
                            if (len != 36) break block65;
                            mostSigBits2 = 0L;
                            leastSigBits2 = 0L;
                            break block66;
                        }
                        if (len != 32) {
                            this.matchStat = -1;
                            return null;
                        }
                        mostSigBits = 0L;
                        leastSigBits = 0L;
                        break block67;
                    }
                    if (chLocal == 'n' && this.charAt(this.bp + offset++) == 'u' && this.charAt(this.bp + offset++) == 'l' && this.charAt(this.bp + offset++) == 'l') {
                        uuid = null;
                        chLocal = this.charAt(this.bp + offset++);
                        break block63;
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                }
                for (i2 = 0; i2 < 8; mostSigBits2 |= (long)num2, ++i2) {
                    ch2 = this.charAt(startIndex2 + i2);
                    if (ch2 >= '0' && ch2 <= '9') {
                        num2 = ch2 - 48;
                    } else if (ch2 >= 'a' && ch2 <= 'f') {
                        num2 = 10 + (ch2 - 97);
                    } else if (ch2 >= 'A' && ch2 <= 'F') {
                        num2 = 10 + (ch2 - 65);
                    } else {
                        this.matchStat = -2;
                        return null;
                    }
                    mostSigBits2 <<= 4;
                }
                for (i2 = 9; i2 < 13; mostSigBits2 |= (long)num2, ++i2) {
                    ch2 = this.charAt(startIndex2 + i2);
                    if (ch2 >= '0' && ch2 <= '9') {
                        num2 = ch2 - 48;
                    } else if (ch2 >= 'a' && ch2 <= 'f') {
                        num2 = 10 + (ch2 - 97);
                    } else if (ch2 >= 'A' && ch2 <= 'F') {
                        num2 = 10 + (ch2 - 65);
                    } else {
                        this.matchStat = -2;
                        return null;
                    }
                    mostSigBits2 <<= 4;
                }
                for (i2 = 14; i2 < 18; mostSigBits2 |= (long)num2, ++i2) {
                    ch2 = this.charAt(startIndex2 + i2);
                    if (ch2 >= '0' && ch2 <= '9') {
                        num2 = ch2 - 48;
                    } else if (ch2 >= 'a' && ch2 <= 'f') {
                        num2 = 10 + (ch2 - 97);
                    } else if (ch2 >= 'A' && ch2 <= 'F') {
                        num2 = 10 + (ch2 - 65);
                    } else {
                        this.matchStat = -2;
                        return null;
                    }
                    mostSigBits2 <<= 4;
                }
                for (i2 = 19; i2 < 23; leastSigBits2 |= (long)num2, ++i2) {
                    ch2 = this.charAt(startIndex2 + i2);
                    if (ch2 >= '0' && ch2 <= '9') {
                        num2 = ch2 - 48;
                    } else if (ch2 >= 'a' && ch2 <= 'f') {
                        num2 = 10 + (ch2 - 97);
                    } else if (ch2 >= 'A' && ch2 <= 'F') {
                        num2 = 10 + (ch2 - 65);
                    } else {
                        this.matchStat = -2;
                        return null;
                    }
                    leastSigBits2 <<= 4;
                }
                for (i2 = 24; i2 < 36; leastSigBits2 |= (long)num2, ++i2) {
                    ch2 = this.charAt(startIndex2 + i2);
                    if (ch2 >= '0' && ch2 <= '9') {
                        num2 = ch2 - 48;
                    } else if (ch2 >= 'a' && ch2 <= 'f') {
                        num2 = 10 + (ch2 - 97);
                    } else if (ch2 >= 'A' && ch2 <= 'F') {
                        num2 = 10 + (ch2 - 65);
                    } else {
                        this.matchStat = -2;
                        return null;
                    }
                    leastSigBits2 <<= 4;
                }
                uuid = new UUID(mostSigBits2, leastSigBits2);
                offset += endIndex - (this.bp + fieldName.length + 1) + 1;
                chLocal = this.charAt(this.bp + offset++);
                break block63;
            }
            for (i = 0; i < 16; mostSigBits |= (long)num, ++i) {
                ch = this.charAt(startIndex2 + i);
                if (ch >= '0' && ch <= '9') {
                    num = ch - 48;
                } else if (ch >= 'a' && ch <= 'f') {
                    num = 10 + (ch - 97);
                } else if (ch >= 'A' && ch <= 'F') {
                    num = 10 + (ch - 65);
                } else {
                    this.matchStat = -2;
                    return null;
                }
                mostSigBits <<= 4;
            }
            for (i = 16; i < 32; leastSigBits |= (long)num, ++i) {
                ch = this.charAt(startIndex2 + i);
                if (ch >= '0' && ch <= '9') {
                    num = ch - 48;
                } else if (ch >= 'a' && ch <= 'f') {
                    num = 10 + (ch - 97);
                } else if (ch >= 'A' && ch <= 'F') {
                    num = 10 + (ch - 65);
                } else {
                    this.matchStat = -2;
                    return null;
                }
                leastSigBits <<= 4;
            }
            uuid = new UUID(mostSigBits, leastSigBits);
            offset += endIndex - (this.bp + fieldName.length + 1) + 1;
            chLocal = this.charAt(this.bp + offset++);
        }
        if (chLocal == ',') {
            this.bp += offset;
            this.ch = this.charAt(this.bp);
            this.matchStat = 3;
            return uuid;
        }
        if (chLocal != '}') {
            this.matchStat = -1;
            return null;
        }
        if ((chLocal = this.charAt(this.bp + offset++)) == ',') {
            this.token = 16;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else if (chLocal == ']') {
            this.token = 15;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else if (chLocal == '}') {
            this.token = 13;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else {
            if (chLocal != '\u001a') {
                this.matchStat = -1;
                return null;
            }
            this.token = 20;
            this.bp += offset - 1;
            this.ch = (char)26;
        }
        this.matchStat = 4;
        return uuid;
    }

    /*
     * Enabled aggressive block sorting
     */
    public UUID scanUUID(char seperator) {
        UUID uuid;
        char chLocal;
        int offset;
        block62: {
            char ch;
            int i;
            int num;
            long leastSigBits;
            long mostSigBits;
            int startIndex2;
            int endIndex;
            block66: {
                char ch2;
                int i2;
                int num2;
                long leastSigBits2;
                long mostSigBits2;
                block65: {
                    block63: {
                        int len;
                        block64: {
                            this.matchStat = 0;
                            offset = 0;
                            if ((chLocal = this.charAt(this.bp + offset++)) != '\"') break block63;
                            int startIndex = this.bp + 1;
                            endIndex = this.indexOf('\"', startIndex);
                            if (endIndex == -1) {
                                throw new JSONException("unclosed str");
                            }
                            startIndex2 = this.bp + 1;
                            len = endIndex - startIndex2;
                            if (len != 36) break block64;
                            mostSigBits2 = 0L;
                            leastSigBits2 = 0L;
                            break block65;
                        }
                        if (len != 32) {
                            this.matchStat = -1;
                            return null;
                        }
                        mostSigBits = 0L;
                        leastSigBits = 0L;
                        break block66;
                    }
                    if (chLocal == 'n' && this.charAt(this.bp + offset++) == 'u' && this.charAt(this.bp + offset++) == 'l' && this.charAt(this.bp + offset++) == 'l') {
                        uuid = null;
                        chLocal = this.charAt(this.bp + offset++);
                        break block62;
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                }
                for (i2 = 0; i2 < 8; mostSigBits2 |= (long)num2, ++i2) {
                    ch2 = this.charAt(startIndex2 + i2);
                    if (ch2 >= '0' && ch2 <= '9') {
                        num2 = ch2 - 48;
                    } else if (ch2 >= 'a' && ch2 <= 'f') {
                        num2 = 10 + (ch2 - 97);
                    } else if (ch2 >= 'A' && ch2 <= 'F') {
                        num2 = 10 + (ch2 - 65);
                    } else {
                        this.matchStat = -2;
                        return null;
                    }
                    mostSigBits2 <<= 4;
                }
                for (i2 = 9; i2 < 13; mostSigBits2 |= (long)num2, ++i2) {
                    ch2 = this.charAt(startIndex2 + i2);
                    if (ch2 >= '0' && ch2 <= '9') {
                        num2 = ch2 - 48;
                    } else if (ch2 >= 'a' && ch2 <= 'f') {
                        num2 = 10 + (ch2 - 97);
                    } else if (ch2 >= 'A' && ch2 <= 'F') {
                        num2 = 10 + (ch2 - 65);
                    } else {
                        this.matchStat = -2;
                        return null;
                    }
                    mostSigBits2 <<= 4;
                }
                for (i2 = 14; i2 < 18; mostSigBits2 |= (long)num2, ++i2) {
                    ch2 = this.charAt(startIndex2 + i2);
                    if (ch2 >= '0' && ch2 <= '9') {
                        num2 = ch2 - 48;
                    } else if (ch2 >= 'a' && ch2 <= 'f') {
                        num2 = 10 + (ch2 - 97);
                    } else if (ch2 >= 'A' && ch2 <= 'F') {
                        num2 = 10 + (ch2 - 65);
                    } else {
                        this.matchStat = -2;
                        return null;
                    }
                    mostSigBits2 <<= 4;
                }
                for (i2 = 19; i2 < 23; leastSigBits2 |= (long)num2, ++i2) {
                    ch2 = this.charAt(startIndex2 + i2);
                    if (ch2 >= '0' && ch2 <= '9') {
                        num2 = ch2 - 48;
                    } else if (ch2 >= 'a' && ch2 <= 'f') {
                        num2 = 10 + (ch2 - 97);
                    } else if (ch2 >= 'A' && ch2 <= 'F') {
                        num2 = 10 + (ch2 - 65);
                    } else {
                        this.matchStat = -2;
                        return null;
                    }
                    leastSigBits2 <<= 4;
                }
                for (i2 = 24; i2 < 36; leastSigBits2 |= (long)num2, ++i2) {
                    ch2 = this.charAt(startIndex2 + i2);
                    if (ch2 >= '0' && ch2 <= '9') {
                        num2 = ch2 - 48;
                    } else if (ch2 >= 'a' && ch2 <= 'f') {
                        num2 = 10 + (ch2 - 97);
                    } else if (ch2 >= 'A' && ch2 <= 'F') {
                        num2 = 10 + (ch2 - 65);
                    } else {
                        this.matchStat = -2;
                        return null;
                    }
                    leastSigBits2 <<= 4;
                }
                uuid = new UUID(mostSigBits2, leastSigBits2);
                offset += endIndex - (this.bp + 1) + 1;
                chLocal = this.charAt(this.bp + offset++);
                break block62;
            }
            for (i = 0; i < 16; mostSigBits |= (long)num, ++i) {
                ch = this.charAt(startIndex2 + i);
                if (ch >= '0' && ch <= '9') {
                    num = ch - 48;
                } else if (ch >= 'a' && ch <= 'f') {
                    num = 10 + (ch - 97);
                } else if (ch >= 'A' && ch <= 'F') {
                    num = 10 + (ch - 65);
                } else {
                    this.matchStat = -2;
                    return null;
                }
                mostSigBits <<= 4;
            }
            for (i = 16; i < 32; leastSigBits |= (long)num, ++i) {
                ch = this.charAt(startIndex2 + i);
                if (ch >= '0' && ch <= '9') {
                    num = ch - 48;
                } else if (ch >= 'a' && ch <= 'f') {
                    num = 10 + (ch - 97);
                } else if (ch >= 'A' && ch <= 'F') {
                    num = 10 + (ch - 65);
                } else {
                    this.matchStat = -2;
                    return null;
                }
                leastSigBits <<= 4;
            }
            uuid = new UUID(mostSigBits, leastSigBits);
            offset += endIndex - (this.bp + 1) + 1;
            chLocal = this.charAt(this.bp + offset++);
        }
        if (chLocal == ',') {
            this.bp += offset;
            this.ch = this.charAt(this.bp);
            this.matchStat = 3;
            return uuid;
        }
        if (chLocal != ']') {
            this.matchStat = -1;
            return null;
        }
        if ((chLocal = this.charAt(this.bp + offset++)) == ',') {
            this.token = 16;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else if (chLocal == ']') {
            this.token = 15;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else if (chLocal == '}') {
            this.token = 13;
            this.bp += offset;
            this.ch = this.charAt(this.bp);
        } else {
            if (chLocal != '\u001a') {
                this.matchStat = -1;
                return null;
            }
            this.token = 20;
            this.bp += offset - 1;
            this.ch = (char)26;
        }
        this.matchStat = 4;
        return uuid;
    }

    public final void scanTrue() {
        if (this.ch != 't') {
            throw new JSONException("error parse true");
        }
        this.next();
        if (this.ch != 'r') {
            throw new JSONException("error parse true");
        }
        this.next();
        if (this.ch != 'u') {
            throw new JSONException("error parse true");
        }
        this.next();
        if (this.ch != 'e') {
            throw new JSONException("error parse true");
        }
        this.next();
        if (this.ch != ' ' && this.ch != ',' && this.ch != '}' && this.ch != ']' && this.ch != '\n' && this.ch != '\r' && this.ch != '\t' && this.ch != '\u001a' && this.ch != '\f' && this.ch != '\b' && this.ch != ':' && this.ch != '/') {
            throw new JSONException("scan true error");
        }
        this.token = 6;
    }

    public final void scanNullOrNew() {
        this.scanNullOrNew(true);
    }

    public final void scanNullOrNew(boolean acceptColon) {
        if (this.ch != 'n') {
            throw new JSONException("error parse null or new");
        }
        this.next();
        if (this.ch == 'u') {
            this.next();
            if (this.ch != 'l') {
                throw new JSONException("error parse null");
            }
            this.next();
            if (this.ch != 'l') {
                throw new JSONException("error parse null");
            }
            this.next();
            if (!(this.ch == ' ' || this.ch == ',' || this.ch == '}' || this.ch == ']' || this.ch == '\n' || this.ch == '\r' || this.ch == '\t' || this.ch == '\u001a' || this.ch == ':' && acceptColon || this.ch == '\f' || this.ch == '\b')) {
                throw new JSONException("scan null error");
            }
            this.token = 8;
            return;
        }
        if (this.ch != 'e') {
            throw new JSONException("error parse new");
        }
        this.next();
        if (this.ch != 'w') {
            throw new JSONException("error parse new");
        }
        this.next();
        if (this.ch != ' ' && this.ch != ',' && this.ch != '}' && this.ch != ']' && this.ch != '\n' && this.ch != '\r' && this.ch != '\t' && this.ch != '\u001a' && this.ch != '\f' && this.ch != '\b') {
            throw new JSONException("scan new error");
        }
        this.token = 9;
    }

    public final void scanFalse() {
        if (this.ch != 'f') {
            throw new JSONException("error parse false");
        }
        this.next();
        if (this.ch != 'a') {
            throw new JSONException("error parse false");
        }
        this.next();
        if (this.ch != 'l') {
            throw new JSONException("error parse false");
        }
        this.next();
        if (this.ch != 's') {
            throw new JSONException("error parse false");
        }
        this.next();
        if (this.ch != 'e') {
            throw new JSONException("error parse false");
        }
        this.next();
        if (this.ch != ' ' && this.ch != ',' && this.ch != '}' && this.ch != ']' && this.ch != '\n' && this.ch != '\r' && this.ch != '\t' && this.ch != '\u001a' && this.ch != '\f' && this.ch != '\b' && this.ch != ':' && this.ch != '/') {
            throw new JSONException("scan false error");
        }
        this.token = 7;
    }

    public final void scanIdent() {
        this.np = this.bp - 1;
        this.hasSpecial = false;
        do {
            ++this.sp;
            this.next();
        } while (Character.isLetterOrDigit(this.ch));
        String ident = this.stringVal();
        this.token = "null".equalsIgnoreCase(ident) ? 8 : ("new".equals(ident) ? 9 : ("true".equals(ident) ? 6 : ("false".equals(ident) ? 7 : ("undefined".equals(ident) ? 23 : ("Set".equals(ident) ? 21 : ("TreeSet".equals(ident) ? 22 : 18))))));
    }

    @Override
    public abstract String stringVal();

    public abstract String subString(int var1, int var2);

    protected abstract char[] sub_chars(int var1, int var2);

    public static String readString(char[] chars, int chars_len) {
        char[] sbuf = new char[chars_len];
        int len = 0;
        block22: for (int i = 0; i < chars_len; ++i) {
            char ch = chars[i];
            if (ch != '\\') {
                sbuf[len++] = ch;
                continue;
            }
            ch = chars[++i];
            switch (ch) {
                case '0': {
                    sbuf[len++] = '\u0000';
                    continue block22;
                }
                case '1': {
                    sbuf[len++] = '\u0001';
                    continue block22;
                }
                case '2': {
                    sbuf[len++] = 2;
                    continue block22;
                }
                case '3': {
                    sbuf[len++] = 3;
                    continue block22;
                }
                case '4': {
                    sbuf[len++] = 4;
                    continue block22;
                }
                case '5': {
                    sbuf[len++] = 5;
                    continue block22;
                }
                case '6': {
                    sbuf[len++] = 6;
                    continue block22;
                }
                case '7': {
                    sbuf[len++] = 7;
                    continue block22;
                }
                case 'b': {
                    sbuf[len++] = 8;
                    continue block22;
                }
                case 't': {
                    sbuf[len++] = 9;
                    continue block22;
                }
                case 'n': {
                    sbuf[len++] = 10;
                    continue block22;
                }
                case 'v': {
                    sbuf[len++] = 11;
                    continue block22;
                }
                case 'F': 
                case 'f': {
                    sbuf[len++] = 12;
                    continue block22;
                }
                case 'r': {
                    sbuf[len++] = 13;
                    continue block22;
                }
                case '\"': {
                    sbuf[len++] = 34;
                    continue block22;
                }
                case '\'': {
                    sbuf[len++] = 39;
                    continue block22;
                }
                case '/': {
                    sbuf[len++] = 47;
                    continue block22;
                }
                case '\\': {
                    sbuf[len++] = 92;
                    continue block22;
                }
                case 'x': {
                    sbuf[len++] = (char)(digits[chars[++i]] * 16 + digits[chars[++i]]);
                    continue block22;
                }
                case 'u': {
                    sbuf[len++] = (char)Integer.parseInt(new String(new char[]{chars[++i], chars[++i], chars[++i], chars[++i]}), 16);
                    continue block22;
                }
                default: {
                    throw new JSONException("unclosed.str.lit");
                }
            }
        }
        return new String(sbuf, 0, len);
    }

    protected abstract boolean charArrayCompare(char[] var1);

    @Override
    public boolean isBlankInput() {
        int i = 0;
        while (true) {
            char chLocal;
            if ((chLocal = this.charAt(i)) == '\u001a') break;
            if (!JSONLexerBase.isWhitespace(chLocal)) {
                return false;
            }
            ++i;
        }
        this.token = 20;
        return true;
    }

    @Override
    public final void skipWhitespace() {
        while (this.ch <= '/') {
            if (this.ch == ' ' || this.ch == '\r' || this.ch == '\n' || this.ch == '\t' || this.ch == '\f' || this.ch == '\b') {
                this.next();
                continue;
            }
            if (this.ch != '/') break;
            this.skipComment();
        }
    }

    private void scanStringSingleQuote() {
        char chLocal;
        this.np = this.bp;
        this.hasSpecial = false;
        block22: while ((chLocal = this.next()) != '\'') {
            if (chLocal == '\u001a') {
                if (!this.isEOF()) {
                    this.putChar('\u001a');
                    continue;
                }
                throw new JSONException("unclosed single-quote string");
            }
            if (chLocal == '\\') {
                if (!this.hasSpecial) {
                    this.hasSpecial = true;
                    if (this.sp > this.sbuf.length) {
                        char[] newsbuf = new char[this.sp * 2];
                        System.arraycopy(this.sbuf, 0, newsbuf, 0, this.sbuf.length);
                        this.sbuf = newsbuf;
                    }
                    this.copyTo(this.np + 1, this.sp, this.sbuf);
                }
                chLocal = this.next();
                switch (chLocal) {
                    case '0': {
                        this.putChar('\u0000');
                        continue block22;
                    }
                    case '1': {
                        this.putChar('\u0001');
                        continue block22;
                    }
                    case '2': {
                        this.putChar('\u0002');
                        continue block22;
                    }
                    case '3': {
                        this.putChar('\u0003');
                        continue block22;
                    }
                    case '4': {
                        this.putChar('\u0004');
                        continue block22;
                    }
                    case '5': {
                        this.putChar('\u0005');
                        continue block22;
                    }
                    case '6': {
                        this.putChar('\u0006');
                        continue block22;
                    }
                    case '7': {
                        this.putChar('\u0007');
                        continue block22;
                    }
                    case 'b': {
                        this.putChar('\b');
                        continue block22;
                    }
                    case 't': {
                        this.putChar('\t');
                        continue block22;
                    }
                    case 'n': {
                        this.putChar('\n');
                        continue block22;
                    }
                    case 'v': {
                        this.putChar('\u000b');
                        continue block22;
                    }
                    case 'F': 
                    case 'f': {
                        this.putChar('\f');
                        continue block22;
                    }
                    case 'r': {
                        this.putChar('\r');
                        continue block22;
                    }
                    case '\"': {
                        this.putChar('\"');
                        continue block22;
                    }
                    case '\'': {
                        this.putChar('\'');
                        continue block22;
                    }
                    case '/': {
                        this.putChar('/');
                        continue block22;
                    }
                    case '\\': {
                        this.putChar('\\');
                        continue block22;
                    }
                    case 'x': {
                        boolean hex2;
                        char x1 = this.next();
                        char x2 = this.next();
                        boolean hex1 = x1 >= '0' && x1 <= '9' || x1 >= 'a' && x1 <= 'f' || x1 >= 'A' && x1 <= 'F';
                        boolean bl = hex2 = x2 >= '0' && x2 <= '9' || x2 >= 'a' && x2 <= 'f' || x2 >= 'A' && x2 <= 'F';
                        if (!hex1 || !hex2) {
                            throw new JSONException("invalid escape character \\x" + x1 + x2);
                        }
                        this.putChar((char)(digits[x1] * 16 + digits[x2]));
                        continue block22;
                    }
                    case 'u': {
                        this.putChar((char)Integer.parseInt(new String(new char[]{this.next(), this.next(), this.next(), this.next()}), 16));
                        continue block22;
                    }
                }
                this.ch = chLocal;
                throw new JSONException("unclosed single-quote string");
            }
            if (!this.hasSpecial) {
                ++this.sp;
                continue;
            }
            if (this.sp == this.sbuf.length) {
                this.putChar(chLocal);
                continue;
            }
            this.sbuf[this.sp++] = chLocal;
        }
        this.token = 4;
        this.next();
    }

    protected final void putChar(char ch) {
        if (this.sp >= this.sbuf.length) {
            int len = this.sbuf.length * 2;
            if (len < this.sp) {
                len = this.sp + 1;
            }
            char[] newsbuf = new char[len];
            System.arraycopy(this.sbuf, 0, newsbuf, 0, this.sbuf.length);
            this.sbuf = newsbuf;
        }
        this.sbuf[this.sp++] = ch;
    }

    public final void scanHex() {
        if (this.ch != 'x') {
            throw new JSONException("illegal state. " + this.ch);
        }
        this.next();
        if (this.ch != '\'') {
            throw new JSONException("illegal state. " + this.ch);
        }
        this.np = this.bp;
        this.next();
        if (this.ch == '\'') {
            this.next();
            this.token = 26;
            return;
        }
        int i = 0;
        while (true) {
            char ch;
            if ((ch = this.next()) >= '0' && ch <= '9' || ch >= 'A' && ch <= 'F') {
                ++this.sp;
            } else {
                if (ch == '\'') {
                    ++this.sp;
                    break;
                }
                throw new JSONException("illegal state. " + ch);
            }
            ++i;
        }
        this.next();
        this.token = 26;
    }

    @Override
    public final void scanNumber() {
        this.np = this.bp;
        if (this.ch == '-') {
            ++this.sp;
            this.next();
        }
        while (this.ch >= '0' && this.ch <= '9') {
            ++this.sp;
            this.next();
        }
        boolean isDouble = false;
        if (this.ch == '.') {
            ++this.sp;
            this.next();
            isDouble = true;
            while (this.ch >= '0' && this.ch <= '9') {
                ++this.sp;
                this.next();
            }
        }
        if (this.sp > 65535) {
            throw new JSONException("scanNumber overflow");
        }
        if (this.ch == 'L') {
            ++this.sp;
            this.next();
        } else if (this.ch == 'S') {
            ++this.sp;
            this.next();
        } else if (this.ch == 'B') {
            ++this.sp;
            this.next();
        } else if (this.ch == 'F') {
            ++this.sp;
            this.next();
            isDouble = true;
        } else if (this.ch == 'D') {
            ++this.sp;
            this.next();
            isDouble = true;
        } else if (this.ch == 'e' || this.ch == 'E') {
            ++this.sp;
            this.next();
            if (this.ch == '+' || this.ch == '-') {
                ++this.sp;
                this.next();
            }
            while (this.ch >= '0' && this.ch <= '9') {
                ++this.sp;
                this.next();
            }
            if (this.ch == 'D' || this.ch == 'F') {
                ++this.sp;
                this.next();
            }
            isDouble = true;
        }
        this.token = isDouble ? 3 : 2;
    }

    @Override
    public final long longValue() throws NumberFormatException {
        char chLocal;
        int digit;
        long limit;
        long result = 0L;
        boolean negative = false;
        if (this.np == -1) {
            this.np = 0;
        }
        int i = this.np;
        int max = this.np + this.sp;
        if (this.charAt(this.np) == '-') {
            negative = true;
            limit = Long.MIN_VALUE;
            ++i;
        } else {
            limit = -9223372036854775807L;
        }
        long multmin = -922337203685477580L;
        if (i < max) {
            digit = this.charAt(i++) - 48;
            result = -digit;
        }
        while (i < max && (chLocal = this.charAt(i++)) != 'L' && chLocal != 'S' && chLocal != 'B') {
            digit = chLocal - 48;
            if (result < multmin) {
                throw new NumberFormatException(this.numberString());
            }
            if ((result *= 10L) < limit + (long)digit) {
                throw new NumberFormatException(this.numberString());
            }
            result -= (long)digit;
        }
        if (negative) {
            if (i > this.np + 1) {
                return result;
            }
            throw new NumberFormatException(this.numberString());
        }
        return -result;
    }

    @Override
    public final Number decimalValue(boolean decimal) {
        char chLocal = this.charAt(this.np + this.sp - 1);
        try {
            if (chLocal == 'F') {
                return Float.valueOf(Float.parseFloat(this.numberString()));
            }
            if (chLocal == 'D') {
                return Double.parseDouble(this.numberString());
            }
            if (decimal) {
                return this.decimalValue();
            }
            return this.doubleValue();
        } catch (NumberFormatException ex) {
            throw new JSONException(ex.getMessage() + ", " + this.info());
        }
    }

    @Override
    public abstract BigDecimal decimalValue();

    public static boolean isWhitespace(char ch) {
        return ch <= ' ' && (ch == ' ' || ch == '\n' || ch == '\r' || ch == '\t' || ch == '\f' || ch == '\b');
    }

    public String[] scanFieldStringArray(char[] fieldName, int argTypesCount, SymbolTable typeSymbolTable) {
        throw new UnsupportedOperationException();
    }

    public boolean matchField2(char[] fieldName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getFeatures() {
        return this.features;
    }

    @Override
    public void setFeatures(int features) {
        this.features = features;
    }

    static {
        int i;
        SBUF_LOCAL = new ThreadLocal();
        typeFieldName = ("\"" + JSON.DEFAULT_TYPE_KEY + "\":\"").toCharArray();
        digits = new int[103];
        for (i = 48; i <= 57; ++i) {
            JSONLexerBase.digits[i] = i - 48;
        }
        for (i = 97; i <= 102; ++i) {
            JSONLexerBase.digits[i] = i - 97 + 10;
        }
        for (i = 65; i <= 70; ++i) {
            JSONLexerBase.digits[i] = i - 65 + 10;
        }
    }
}

