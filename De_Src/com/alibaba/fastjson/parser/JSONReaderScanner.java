/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.SymbolTable;
import com.alibaba.fastjson.util.IOUtils;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.MathContext;

public final class JSONReaderScanner
extends JSONLexerBase {
    private static final ThreadLocal<char[]> BUF_LOCAL = new ThreadLocal();
    private Reader reader;
    private char[] buf;
    private int bufLength;

    public JSONReaderScanner(String input) {
        this(input, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONReaderScanner(String input, int features) {
        this(new StringReader(input), features);
    }

    public JSONReaderScanner(char[] input, int inputLength) {
        this(input, inputLength, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONReaderScanner(Reader reader) {
        this(reader, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONReaderScanner(Reader reader, int features) {
        super(features);
        this.reader = reader;
        this.buf = BUF_LOCAL.get();
        if (this.buf != null) {
            BUF_LOCAL.set(null);
        }
        if (this.buf == null) {
            this.buf = new char[16384];
        }
        try {
            this.bufLength = reader.read(this.buf);
        } catch (IOException e) {
            throw new JSONException(e.getMessage(), e);
        }
        this.bp = -1;
        this.next();
        if (this.ch == '\ufeff') {
            this.next();
        }
    }

    public JSONReaderScanner(char[] input, int inputLength, int features) {
        this(new CharArrayReader(input, 0, inputLength), features);
    }

    public final char charAt(int index) {
        if (index >= this.bufLength) {
            if (this.bufLength == -1) {
                if (index < this.sp) {
                    return this.buf[index];
                }
                return '\u001a';
            }
            if (this.bp == 0) {
                char[] buf = new char[this.buf.length * 3 / 2];
                System.arraycopy(this.buf, this.bp, buf, 0, this.bufLength);
                int rest = buf.length - this.bufLength;
                try {
                    int len = this.reader.read(buf, this.bufLength, rest);
                    this.bufLength += len;
                    this.buf = buf;
                } catch (IOException e) {
                    throw new JSONException(e.getMessage(), e);
                }
            }
            int rest = this.bufLength - this.bp;
            if (rest > 0) {
                System.arraycopy(this.buf, this.bp, this.buf, 0, rest);
            }
            try {
                this.bufLength = this.reader.read(this.buf, rest, this.buf.length - rest);
            } catch (IOException e) {
                throw new JSONException(e.getMessage(), e);
            }
            if (this.bufLength == 0) {
                throw new JSONException("illegal state, textLength is zero");
            }
            if (this.bufLength == -1) {
                return '\u001a';
            }
            this.bufLength += rest;
            index -= this.bp;
            this.np -= this.bp;
            this.bp = 0;
        }
        return this.buf[index];
    }

    public final int indexOf(char ch, int startIndex) {
        int offset = startIndex - this.bp;
        int index;
        char chLoal;
        while (ch != (chLoal = this.charAt(index = this.bp + offset))) {
            if (chLoal == '\u001a') {
                return -1;
            }
            ++offset;
        }
        return offset + this.bp;
    }

    public final String addSymbol(int offset, int len, int hash, SymbolTable symbolTable) {
        return symbolTable.addSymbol(this.buf, offset, len, hash);
    }

    public final char next() {
        int index;
        if ((index = ++this.bp) >= this.bufLength) {
            if (this.bufLength == -1) {
                return '\u001a';
            }
            if (this.sp > 0) {
                int offset = this.bufLength - this.sp;
                if (this.ch == '\"' && offset > 0) {
                    --offset;
                }
                System.arraycopy(this.buf, offset, this.buf, 0, this.sp);
            }
            this.np = -1;
            index = this.bp = this.sp;
            try {
                int startPos = this.bp;
                int readLength = this.buf.length - startPos;
                if (readLength == 0) {
                    char[] newBuf = new char[this.buf.length * 2];
                    System.arraycopy(this.buf, 0, newBuf, 0, this.buf.length);
                    this.buf = newBuf;
                    readLength = this.buf.length - startPos;
                }
                this.bufLength = this.reader.read(this.buf, this.bp, readLength);
            } catch (IOException e) {
                throw new JSONException(e.getMessage(), e);
            }
            if (this.bufLength == 0) {
                throw new JSONException("illegal stat, textLength is zero");
            }
            if (this.bufLength == -1) {
                this.ch = '\u001a';
                return '\u001a';
            }
            this.bufLength += this.bp;
        }
        this.ch = this.buf[index];
        return this.ch;
    }

    protected final void copyTo(int offset, int count, char[] dest) {
        System.arraycopy(this.buf, offset, dest, 0, count);
    }

    public final boolean charArrayCompare(char[] chars) {
        for (int i = 0; i < chars.length; ++i) {
            if (this.charAt(this.bp + i) == chars[i]) continue;
            return false;
        }
        return true;
    }

    public byte[] bytesValue() {
        if (this.token == 26) {
            throw new JSONException("TODO");
        }
        return IOUtils.decodeBase64(this.buf, this.np + 1, this.sp);
    }

    protected final void arrayCopy(int srcPos, char[] dest, int destPos, int length) {
        System.arraycopy(this.buf, srcPos, dest, destPos, length);
    }

    public final String stringVal() {
        if (!this.hasSpecial) {
            int offset = this.np + 1;
            if (offset < 0) {
                throw new IllegalStateException();
            }
            if (offset > this.buf.length - this.sp) {
                throw new IllegalStateException();
            }
            return new String(this.buf, offset, this.sp);
        }
        return new String(this.sbuf, 0, this.sp);
    }

    public final String subString(int offset, int count) {
        if (count < 0) {
            throw new StringIndexOutOfBoundsException(count);
        }
        return new String(this.buf, offset, count);
    }

    public final char[] sub_chars(int offset, int count) {
        if (count < 0) {
            throw new StringIndexOutOfBoundsException(count);
        }
        if (offset == 0) {
            return this.buf;
        }
        char[] chars = new char[count];
        System.arraycopy(this.buf, offset, chars, 0, count);
        return chars;
    }

    public final String numberString() {
        int offset = this.np;
        if (offset == -1) {
            offset = 0;
        }
        char chLocal = this.charAt(offset + this.sp - 1);
        int sp = this.sp;
        if (chLocal == 'L' || chLocal == 'S' || chLocal == 'B' || chLocal == 'F' || chLocal == 'D') {
            --sp;
        }
        String value = new String(this.buf, offset, sp);
        return value;
    }

    public final BigDecimal decimalValue() {
        int offset = this.np;
        if (offset == -1) {
            offset = 0;
        }
        char chLocal = this.charAt(offset + this.sp - 1);
        int sp = this.sp;
        if (chLocal == 'L' || chLocal == 'S' || chLocal == 'B' || chLocal == 'F' || chLocal == 'D') {
            --sp;
        }
        if (sp > 65535) {
            throw new JSONException("decimal overflow");
        }
        return new BigDecimal(this.buf, offset, sp, MathContext.UNLIMITED);
    }

    public void close() {
        super.close();
        if (this.buf.length <= 65536) {
            BUF_LOCAL.set(this.buf);
        }
        this.buf = null;
        IOUtils.close(this.reader);
    }

    public boolean isEOF() {
        return this.bufLength == -1 || this.bp == this.buf.length || this.ch == '\u001a' && this.bp + 1 >= this.buf.length;
    }

    public final boolean isBlankInput() {
        int i = 0;
        while (true) {
            char chLocal;
            if ((chLocal = this.buf[i]) == '\u001a') break;
            if (!JSONReaderScanner.isWhitespace(chLocal)) {
                return false;
            }
            ++i;
        }
        this.token = 20;
        return true;
    }
}

