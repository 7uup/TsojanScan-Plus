/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson;

import com.alibaba.fastjson.JSONException;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public abstract class JSONValidator
implements Cloneable,
Closeable {
    protected boolean eof;
    protected int pos = -1;
    protected char ch;
    protected Type type;
    private Boolean validateResult;
    protected int count = 0;
    protected boolean supportMultiValue = false;

    public static JSONValidator fromUtf8(byte[] jsonBytes) {
        return new UTF8Validator(jsonBytes);
    }

    public static JSONValidator fromUtf8(InputStream is) {
        return new UTF8InputStreamValidator(is);
    }

    public static JSONValidator from(String jsonStr) {
        return new UTF16Validator(jsonStr);
    }

    public static JSONValidator from(Reader r) {
        return new ReaderValidator(r);
    }

    public boolean isSupportMultiValue() {
        return this.supportMultiValue;
    }

    public JSONValidator setSupportMultiValue(boolean supportMultiValue) {
        this.supportMultiValue = supportMultiValue;
        return this;
    }

    public Type getType() {
        if (this.type == null) {
            this.validate();
        }
        return this.type;
    }

    abstract void next();

    public boolean validate() {
        block5: {
            block4: {
                if (this.validateResult != null) {
                    return this.validateResult;
                }
                do {
                    if (!this.any()) {
                        this.validateResult = false;
                        return false;
                    }
                    this.skipWhiteSpace();
                    ++this.count;
                    if (this.eof) {
                        this.validateResult = true;
                        return true;
                    }
                    if (!this.supportMultiValue) break block4;
                    this.skipWhiteSpace();
                } while (!this.eof);
                break block5;
            }
            this.validateResult = false;
            return false;
        }
        this.validateResult = true;
        return true;
    }

    public void close() throws IOException {
    }

    private boolean any() {
        switch (this.ch) {
            case '{': {
                this.next();
                while (JSONValidator.isWhiteSpace(this.ch)) {
                    this.next();
                }
                if (this.ch == '}') {
                    this.next();
                    this.type = Type.Object;
                    return true;
                }
                while (true) {
                    if (this.ch != '\"') {
                        return false;
                    }
                    this.fieldName();
                    this.skipWhiteSpace();
                    if (this.ch != ':') {
                        return false;
                    }
                    this.next();
                    this.skipWhiteSpace();
                    if (!this.any()) {
                        return false;
                    }
                    this.skipWhiteSpace();
                    if (this.ch != ',') break;
                    this.next();
                    this.skipWhiteSpace();
                }
                if (this.ch == '}') {
                    this.next();
                    this.type = Type.Object;
                    return true;
                }
                return false;
            }
            case '[': {
                this.next();
                this.skipWhiteSpace();
                if (this.ch == ']') {
                    this.next();
                    this.type = Type.Array;
                    return true;
                }
                while (true) {
                    if (!this.any()) {
                        return false;
                    }
                    this.skipWhiteSpace();
                    if (this.ch != ',') break;
                    this.next();
                    this.skipWhiteSpace();
                }
                if (this.ch == ']') {
                    this.next();
                    this.type = Type.Array;
                    return true;
                }
                return false;
            }
            case '+': 
            case '-': 
            case '0': 
            case '1': 
            case '2': 
            case '3': 
            case '4': 
            case '5': 
            case '6': 
            case '7': 
            case '8': 
            case '9': {
                if (this.ch == '-' || this.ch == '+') {
                    this.next();
                    this.skipWhiteSpace();
                    if (this.ch < '0' || this.ch > '9') {
                        return false;
                    }
                }
                do {
                    this.next();
                } while (this.ch >= '0' && this.ch <= '9');
                if (this.ch == '.') {
                    this.next();
                    if (this.ch < '0' || this.ch > '9') {
                        return false;
                    }
                    while (this.ch >= '0' && this.ch <= '9') {
                        this.next();
                    }
                }
                if (this.ch == 'e' || this.ch == 'E') {
                    this.next();
                    if (this.ch == '-' || this.ch == '+') {
                        this.next();
                    }
                    if (this.ch >= '0' && this.ch <= '9') {
                        this.next();
                    } else {
                        return false;
                    }
                    while (this.ch >= '0' && this.ch <= '9') {
                        this.next();
                    }
                }
                this.type = Type.Value;
                break;
            }
            case '\"': {
                this.next();
                while (true) {
                    if (this.eof) {
                        return false;
                    }
                    if (this.ch == '\\') {
                        this.next();
                        if (this.ch == 'u') {
                            this.next();
                            this.next();
                            this.next();
                            this.next();
                            this.next();
                            continue;
                        }
                        this.next();
                        continue;
                    }
                    if (this.ch == '\"') {
                        this.next();
                        this.type = Type.Value;
                        return true;
                    }
                    this.next();
                }
            }
            case 't': {
                this.next();
                if (this.ch != 'r') {
                    return false;
                }
                this.next();
                if (this.ch != 'u') {
                    return false;
                }
                this.next();
                if (this.ch != 'e') {
                    return false;
                }
                this.next();
                if (JSONValidator.isWhiteSpace(this.ch) || this.ch == ',' || this.ch == ']' || this.ch == '}' || this.ch == '\u0000') {
                    this.type = Type.Value;
                    return true;
                }
                return false;
            }
            case 'f': {
                this.next();
                if (this.ch != 'a') {
                    return false;
                }
                this.next();
                if (this.ch != 'l') {
                    return false;
                }
                this.next();
                if (this.ch != 's') {
                    return false;
                }
                this.next();
                if (this.ch != 'e') {
                    return false;
                }
                this.next();
                if (JSONValidator.isWhiteSpace(this.ch) || this.ch == ',' || this.ch == ']' || this.ch == '}' || this.ch == '\u0000') {
                    this.type = Type.Value;
                    return true;
                }
                return false;
            }
            case 'n': {
                this.next();
                if (this.ch != 'u') {
                    return false;
                }
                this.next();
                if (this.ch != 'l') {
                    return false;
                }
                this.next();
                if (this.ch != 'l') {
                    return false;
                }
                this.next();
                if (JSONValidator.isWhiteSpace(this.ch) || this.ch == ',' || this.ch == ']' || this.ch == '}' || this.ch == '\u0000') {
                    this.type = Type.Value;
                    return true;
                }
                return false;
            }
            default: {
                return false;
            }
        }
        return true;
    }

    protected void fieldName() {
        this.next();
        while (true) {
            if (this.ch == '\\') {
                this.next();
                if (this.ch == 'u') {
                    this.next();
                    this.next();
                    this.next();
                    this.next();
                    this.next();
                    continue;
                }
                this.next();
                continue;
            }
            if (this.ch == '\"') break;
            this.next();
        }
        this.next();
    }

    protected boolean string() {
        this.next();
        while (!this.eof) {
            if (this.ch == '\\') {
                this.next();
                if (this.ch == 'u') {
                    this.next();
                    this.next();
                    this.next();
                    this.next();
                    this.next();
                    continue;
                }
                this.next();
                continue;
            }
            if (this.ch == '\"') {
                this.next();
                return true;
            }
            this.next();
        }
        return false;
    }

    void skipWhiteSpace() {
        while (JSONValidator.isWhiteSpace(this.ch)) {
            this.next();
        }
    }

    static final boolean isWhiteSpace(char ch) {
        return ch == ' ' || ch == '\t' || ch == '\r' || ch == '\n' || ch == '\f' || ch == '\b';
    }

    static class ReaderValidator
    extends JSONValidator {
        private static final ThreadLocal<char[]> bufLocal = new ThreadLocal();
        final Reader r;
        private char[] buf;
        private int end = -1;
        private int readCount = 0;

        ReaderValidator(Reader r) {
            this.r = r;
            this.buf = bufLocal.get();
            if (this.buf != null) {
                bufLocal.set(null);
            } else {
                this.buf = new char[8192];
            }
            this.next();
            this.skipWhiteSpace();
        }

        void next() {
            if (this.pos < this.end) {
                this.ch = this.buf[++this.pos];
            } else if (!this.eof) {
                int len;
                try {
                    len = this.r.read(this.buf, 0, this.buf.length);
                    ++this.readCount;
                } catch (IOException ex) {
                    throw new JSONException("read error");
                }
                if (len > 0) {
                    this.ch = this.buf[0];
                    this.pos = 0;
                    this.end = len - 1;
                } else if (len == -1) {
                    this.pos = 0;
                    this.end = 0;
                    this.buf = null;
                    this.ch = '\u0000';
                    this.eof = true;
                } else {
                    this.pos = 0;
                    this.end = 0;
                    this.buf = null;
                    this.ch = '\u0000';
                    this.eof = true;
                    throw new JSONException("read error");
                }
            }
        }

        public void close() throws IOException {
            bufLocal.set(this.buf);
            this.r.close();
        }
    }

    static class UTF16Validator
    extends JSONValidator {
        private final String str;

        public UTF16Validator(String str) {
            this.str = str;
            this.next();
            this.skipWhiteSpace();
        }

        void next() {
            ++this.pos;
            if (this.pos >= this.str.length()) {
                this.ch = '\u0000';
                this.eof = true;
            } else {
                this.ch = this.str.charAt(this.pos);
            }
        }

        protected final void fieldName() {
            char ch;
            for (int i = this.pos + 1; i < this.str.length() && (ch = this.str.charAt(i)) != '\\'; ++i) {
                if (ch != '\"') continue;
                this.ch = this.str.charAt(i + 1);
                this.pos = i + 1;
                return;
            }
            this.next();
            while (true) {
                if (this.ch == '\\') {
                    this.next();
                    if (this.ch == 'u') {
                        this.next();
                        this.next();
                        this.next();
                        this.next();
                        this.next();
                        continue;
                    }
                    this.next();
                    continue;
                }
                if (this.ch == '\"') {
                    this.next();
                    break;
                }
                if (this.eof) break;
                this.next();
            }
        }
    }

    static class UTF8InputStreamValidator
    extends JSONValidator {
        private static final ThreadLocal<byte[]> bufLocal = new ThreadLocal();
        private final InputStream is;
        private byte[] buf;
        private int end = -1;
        private int readCount = 0;

        public UTF8InputStreamValidator(InputStream is) {
            this.is = is;
            this.buf = bufLocal.get();
            if (this.buf != null) {
                bufLocal.set(null);
            } else {
                this.buf = new byte[8192];
            }
            this.next();
            this.skipWhiteSpace();
        }

        void next() {
            if (this.pos < this.end) {
                this.ch = (char)this.buf[++this.pos];
            } else if (!this.eof) {
                int len;
                try {
                    len = this.is.read(this.buf, 0, this.buf.length);
                    ++this.readCount;
                } catch (IOException ex) {
                    throw new JSONException("read error");
                }
                if (len > 0) {
                    this.ch = (char)this.buf[0];
                    this.pos = 0;
                    this.end = len - 1;
                } else if (len == -1) {
                    this.pos = 0;
                    this.end = 0;
                    this.buf = null;
                    this.ch = '\u0000';
                    this.eof = true;
                } else {
                    this.pos = 0;
                    this.end = 0;
                    this.buf = null;
                    this.ch = '\u0000';
                    this.eof = true;
                    throw new JSONException("read error");
                }
            }
        }

        public void close() throws IOException {
            bufLocal.set(this.buf);
            this.is.close();
        }
    }

    static class UTF8Validator
    extends JSONValidator {
        private final byte[] bytes;

        public UTF8Validator(byte[] bytes) {
            this.bytes = bytes;
            this.next();
            this.skipWhiteSpace();
        }

        void next() {
            ++this.pos;
            if (this.pos >= this.bytes.length) {
                this.ch = '\u0000';
                this.eof = true;
            } else {
                this.ch = (char)this.bytes[this.pos];
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum Type {
        Object,
        Array,
        Value;

    }
}

