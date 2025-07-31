/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.RyuDouble;
import com.alibaba.fastjson.util.RyuFloat;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.List;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class SerializeWriter
extends Writer {
    private static final ThreadLocal<char[]> bufLocal = new ThreadLocal();
    private static final ThreadLocal<byte[]> bytesBufLocal = new ThreadLocal();
    private static final char[] VALUE_TRUE = ":true".toCharArray();
    private static final char[] VALUE_FALSE = ":false".toCharArray();
    private static int BUFFER_THRESHOLD = 131072;
    protected char[] buf;
    protected int count;
    protected int features;
    private final Writer writer;
    protected boolean useSingleQuotes;
    protected boolean quoteFieldNames;
    protected boolean sortField;
    protected boolean disableCircularReferenceDetect;
    protected boolean beanToArray;
    protected boolean writeNonStringValueAsString;
    protected boolean notWriteDefaultValue;
    protected boolean writeEnumUsingName;
    protected boolean writeEnumUsingToString;
    protected boolean writeDirect;
    protected char keySeperator;
    protected int maxBufSize = -1;
    protected boolean browserSecure;
    protected long sepcialBits;
    static final int nonDirectFeatures;

    public SerializeWriter() {
        this((Writer)null);
    }

    public SerializeWriter(Writer writer) {
        this(writer, JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.EMPTY);
    }

    public SerializeWriter(SerializerFeature ... features) {
        this((Writer)null, features);
    }

    public SerializeWriter(Writer writer, SerializerFeature ... features) {
        this(writer, 0, features);
    }

    public SerializeWriter(Writer writer, int defaultFeatures, SerializerFeature ... features) {
        this.writer = writer;
        this.buf = bufLocal.get();
        if (this.buf != null) {
            bufLocal.set(null);
        } else {
            this.buf = new char[2048];
        }
        int featuresValue = defaultFeatures;
        for (SerializerFeature feature : features) {
            featuresValue |= feature.getMask();
        }
        this.features = featuresValue;
        this.computeFeatures();
    }

    public int getMaxBufSize() {
        return this.maxBufSize;
    }

    public void setMaxBufSize(int maxBufSize) {
        if (maxBufSize < this.buf.length) {
            throw new JSONException("must > " + this.buf.length);
        }
        this.maxBufSize = maxBufSize;
    }

    public int getBufferLength() {
        return this.buf.length;
    }

    public SerializeWriter(int initialSize) {
        this(null, initialSize);
    }

    public SerializeWriter(Writer writer, int initialSize) {
        this.writer = writer;
        if (initialSize <= 0) {
            throw new IllegalArgumentException("Negative initial size: " + initialSize);
        }
        this.buf = new char[initialSize];
        this.computeFeatures();
    }

    public void config(SerializerFeature feature, boolean state) {
        if (state) {
            this.features |= feature.getMask();
            if (feature == SerializerFeature.WriteEnumUsingToString) {
                this.features &= ~SerializerFeature.WriteEnumUsingName.getMask();
            } else if (feature == SerializerFeature.WriteEnumUsingName) {
                this.features &= ~SerializerFeature.WriteEnumUsingToString.getMask();
            }
        } else {
            this.features &= ~feature.getMask();
        }
        this.computeFeatures();
    }

    protected void computeFeatures() {
        this.quoteFieldNames = (this.features & SerializerFeature.QuoteFieldNames.mask) != 0;
        this.useSingleQuotes = (this.features & SerializerFeature.UseSingleQuotes.mask) != 0;
        this.sortField = (this.features & SerializerFeature.SortField.mask) != 0;
        this.disableCircularReferenceDetect = (this.features & SerializerFeature.DisableCircularReferenceDetect.mask) != 0;
        this.beanToArray = (this.features & SerializerFeature.BeanToArray.mask) != 0;
        this.writeNonStringValueAsString = (this.features & SerializerFeature.WriteNonStringValueAsString.mask) != 0;
        this.notWriteDefaultValue = (this.features & SerializerFeature.NotWriteDefaultValue.mask) != 0;
        this.writeEnumUsingName = (this.features & SerializerFeature.WriteEnumUsingName.mask) != 0;
        this.writeEnumUsingToString = (this.features & SerializerFeature.WriteEnumUsingToString.mask) != 0;
        this.writeDirect = this.quoteFieldNames && (this.features & nonDirectFeatures) == 0 && (this.beanToArray || this.writeEnumUsingName);
        this.keySeperator = (char)(this.useSingleQuotes ? 39 : 34);
        this.browserSecure = (this.features & SerializerFeature.BrowserSecure.mask) != 0;
        long S0 = 0x4FFFFFFFFL;
        long S1 = 140758963191807L;
        long S2 = 5764610843043954687L;
        this.sepcialBits = this.browserSecure ? 5764610843043954687L : ((this.features & SerializerFeature.WriteSlashAsSpecial.mask) != 0 ? 140758963191807L : 0x4FFFFFFFFL);
    }

    public boolean isSortField() {
        return this.sortField;
    }

    public boolean isNotWriteDefaultValue() {
        return this.notWriteDefaultValue;
    }

    public boolean isEnabled(SerializerFeature feature) {
        return (this.features & feature.mask) != 0;
    }

    public boolean isEnabled(int feature) {
        return (this.features & feature) != 0;
    }

    @Override
    public void write(int c) {
        int newcount = this.count + 1;
        if (newcount > this.buf.length) {
            if (this.writer == null) {
                this.expandCapacity(newcount);
            } else {
                this.flush();
                newcount = 1;
            }
        }
        this.buf[this.count] = (char)c;
        this.count = newcount;
    }

    @Override
    public void write(char[] c, int off, int len) {
        if (off < 0 || off > c.length || len < 0 || off + len > c.length || off + len < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (len == 0) {
            return;
        }
        int newcount = this.count + len;
        if (newcount > this.buf.length) {
            if (this.writer == null) {
                this.expandCapacity(newcount);
            } else {
                int rest;
                do {
                    rest = this.buf.length - this.count;
                    System.arraycopy(c, off, this.buf, this.count, rest);
                    this.count = this.buf.length;
                    this.flush();
                    off += rest;
                } while ((len -= rest) > this.buf.length);
                newcount = len;
            }
        }
        System.arraycopy(c, off, this.buf, this.count, len);
        this.count = newcount;
    }

    public void expandCapacity(int minimumCapacity) {
        char[] charsLocal;
        if (this.maxBufSize != -1 && minimumCapacity >= this.maxBufSize) {
            throw new JSONException("serialize exceeded MAX_OUTPUT_LENGTH=" + this.maxBufSize + ", minimumCapacity=" + minimumCapacity);
        }
        int newCapacity = this.buf.length + (this.buf.length >> 1) + 1;
        if (newCapacity < minimumCapacity) {
            newCapacity = minimumCapacity;
        }
        char[] newValue = new char[newCapacity];
        System.arraycopy(this.buf, 0, newValue, 0, this.count);
        if (this.buf.length < BUFFER_THRESHOLD && ((charsLocal = bufLocal.get()) == null || charsLocal.length < this.buf.length)) {
            bufLocal.set(this.buf);
        }
        this.buf = newValue;
    }

    @Override
    public SerializeWriter append(CharSequence csq) {
        String s2 = csq == null ? "null" : csq.toString();
        this.write(s2, 0, s2.length());
        return this;
    }

    @Override
    public SerializeWriter append(CharSequence csq, int start, int end) {
        String s2 = (csq == null ? "null" : csq).subSequence(start, end).toString();
        this.write(s2, 0, s2.length());
        return this;
    }

    @Override
    public SerializeWriter append(char c) {
        this.write(c);
        return this;
    }

    @Override
    public void write(String str, int off, int len) {
        int newcount = this.count + len;
        if (newcount > this.buf.length) {
            if (this.writer == null) {
                this.expandCapacity(newcount);
            } else {
                int rest;
                do {
                    rest = this.buf.length - this.count;
                    str.getChars(off, off + rest, this.buf, this.count);
                    this.count = this.buf.length;
                    this.flush();
                    off += rest;
                } while ((len -= rest) > this.buf.length);
                newcount = len;
            }
        }
        str.getChars(off, off + len, this.buf, this.count);
        this.count = newcount;
    }

    public void writeTo(Writer out) throws IOException {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        out.write(this.buf, 0, this.count);
    }

    public void writeTo(OutputStream out, String charsetName) throws IOException {
        this.writeTo(out, Charset.forName(charsetName));
    }

    public void writeTo(OutputStream out, Charset charset) throws IOException {
        this.writeToEx(out, charset);
    }

    public int writeToEx(OutputStream out, Charset charset) throws IOException {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        if (charset == IOUtils.UTF8) {
            return this.encodeToUTF8(out);
        }
        byte[] bytes = new String(this.buf, 0, this.count).getBytes(charset);
        out.write(bytes);
        return bytes.length;
    }

    public char[] toCharArray() {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        char[] newValue = new char[this.count];
        System.arraycopy(this.buf, 0, newValue, 0, this.count);
        return newValue;
    }

    public char[] toCharArrayForSpringWebSocket() {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        char[] newValue = new char[this.count - 2];
        System.arraycopy(this.buf, 1, newValue, 0, this.count - 2);
        return newValue;
    }

    public byte[] toBytes(String charsetName) {
        return this.toBytes(charsetName == null || "UTF-8".equals(charsetName) ? IOUtils.UTF8 : Charset.forName(charsetName));
    }

    public byte[] toBytes(Charset charset) {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        if (charset == IOUtils.UTF8) {
            return this.encodeToUTF8Bytes();
        }
        return new String(this.buf, 0, this.count).getBytes(charset);
    }

    private int encodeToUTF8(OutputStream out) throws IOException {
        int bytesLength = (int)((double)this.count * 3.0);
        byte[] bytes = bytesBufLocal.get();
        if (bytes == null) {
            bytes = new byte[8192];
            bytesBufLocal.set(bytes);
        }
        byte[] bytesLocal = bytes;
        if (bytes.length < bytesLength) {
            bytes = new byte[bytesLength];
        }
        int position = IOUtils.encodeUTF8(this.buf, 0, this.count, bytes);
        out.write(bytes, 0, position);
        if (bytes != bytesLocal && bytes.length <= BUFFER_THRESHOLD) {
            bytesBufLocal.set(bytes);
        }
        return position;
    }

    private byte[] encodeToUTF8Bytes() {
        int bytesLength = (int)((double)this.count * 3.0);
        byte[] bytes = bytesBufLocal.get();
        if (bytes == null) {
            bytes = new byte[8192];
            bytesBufLocal.set(bytes);
        }
        byte[] bytesLocal = bytes;
        if (bytes.length < bytesLength) {
            bytes = new byte[bytesLength];
        }
        int position = IOUtils.encodeUTF8(this.buf, 0, this.count, bytes);
        byte[] copy = new byte[position];
        System.arraycopy(bytes, 0, copy, 0, position);
        if (bytes != bytesLocal && bytes.length <= BUFFER_THRESHOLD) {
            bytesBufLocal.set(bytes);
        }
        return copy;
    }

    public int size() {
        return this.count;
    }

    public String toString() {
        return new String(this.buf, 0, this.count);
    }

    @Override
    public void close() {
        if (this.writer != null && this.count > 0) {
            this.flush();
        }
        if (this.buf.length <= BUFFER_THRESHOLD) {
            bufLocal.set(this.buf);
        }
        this.buf = null;
    }

    @Override
    public void write(String text) {
        if (text == null) {
            this.writeNull();
            return;
        }
        this.write(text, 0, text.length());
    }

    public void writeInt(int i) {
        if (i == Integer.MIN_VALUE) {
            this.write("-2147483648");
            return;
        }
        int size = i < 0 ? IOUtils.stringSize(-i) + 1 : IOUtils.stringSize(i);
        int newcount = this.count + size;
        if (newcount > this.buf.length) {
            if (this.writer == null) {
                this.expandCapacity(newcount);
            } else {
                char[] chars = new char[size];
                IOUtils.getChars(i, size, chars);
                this.write(chars, 0, chars.length);
                return;
            }
        }
        IOUtils.getChars(i, newcount, this.buf);
        this.count = newcount;
    }

    public void writeByteArray(byte[] bytes) {
        int quote;
        if (this.isEnabled(SerializerFeature.WriteClassName.mask)) {
            this.writeHex(bytes);
            return;
        }
        int bytesLen = bytes.length;
        int n = quote = this.useSingleQuotes ? 39 : 34;
        if (bytesLen == 0) {
            String emptyString = this.useSingleQuotes ? "''" : "\"\"";
            this.write(emptyString);
            return;
        }
        char[] CA = IOUtils.CA;
        int eLen = bytesLen / 3 * 3;
        int charsLen = (bytesLen - 1) / 3 + 1 << 2;
        int offset = this.count;
        int newcount = this.count + charsLen + 2;
        if (newcount > this.buf.length) {
            if (this.writer != null) {
                int i;
                this.write(quote);
                int s2 = 0;
                while (s2 < eLen) {
                    i = (bytes[s2++] & 0xFF) << 16 | (bytes[s2++] & 0xFF) << 8 | bytes[s2++] & 0xFF;
                    this.write(CA[i >>> 18 & 0x3F]);
                    this.write(CA[i >>> 12 & 0x3F]);
                    this.write(CA[i >>> 6 & 0x3F]);
                    this.write(CA[i & 0x3F]);
                }
                int left = bytesLen - eLen;
                if (left > 0) {
                    i = (bytes[eLen] & 0xFF) << 10 | (left == 2 ? (bytes[bytesLen - 1] & 0xFF) << 2 : 0);
                    this.write(CA[i >> 12]);
                    this.write(CA[i >>> 6 & 0x3F]);
                    this.write(left == 2 ? CA[i & 0x3F] : 61);
                    this.write(61);
                }
                this.write(quote);
                return;
            }
            this.expandCapacity(newcount);
        }
        this.count = newcount;
        this.buf[offset++] = quote;
        int s3 = 0;
        int d = offset;
        while (s3 < eLen) {
            int i = (bytes[s3++] & 0xFF) << 16 | (bytes[s3++] & 0xFF) << 8 | bytes[s3++] & 0xFF;
            this.buf[d++] = CA[i >>> 18 & 0x3F];
            this.buf[d++] = CA[i >>> 12 & 0x3F];
            this.buf[d++] = CA[i >>> 6 & 0x3F];
            this.buf[d++] = CA[i & 0x3F];
        }
        int left = bytesLen - eLen;
        if (left > 0) {
            int i = (bytes[eLen] & 0xFF) << 10 | (left == 2 ? (bytes[bytesLen - 1] & 0xFF) << 2 : 0);
            this.buf[newcount - 5] = CA[i >> 12];
            this.buf[newcount - 4] = CA[i >>> 6 & 0x3F];
            this.buf[newcount - 3] = left == 2 ? CA[i & 0x3F] : 61;
            this.buf[newcount - 2] = 61;
        }
        this.buf[newcount - 1] = quote;
    }

    public void writeHex(byte[] bytes) {
        int newcount = this.count + bytes.length * 2 + 3;
        if (newcount > this.buf.length) {
            this.expandCapacity(newcount);
        }
        this.buf[this.count++] = 120;
        this.buf[this.count++] = 39;
        for (int i = 0; i < bytes.length; ++i) {
            byte b = bytes[i];
            int a = b & 0xFF;
            int b0 = a >> 4;
            int b1 = a & 0xF;
            this.buf[this.count++] = (char)(b0 + (b0 < 10 ? 48 : 55));
            this.buf[this.count++] = (char)(b1 + (b1 < 10 ? 48 : 55));
        }
        this.buf[this.count++] = 39;
    }

    public void writeFloat(float value, boolean checkWriteClassName) {
        if (value != value || value == Float.POSITIVE_INFINITY || value == Float.NEGATIVE_INFINITY) {
            this.writeNull();
        } else {
            int newcount = this.count + 15;
            if (newcount > this.buf.length) {
                if (this.writer == null) {
                    this.expandCapacity(newcount);
                } else {
                    String str = RyuFloat.toString(value);
                    this.write(str, 0, str.length());
                    if (checkWriteClassName && this.isEnabled(SerializerFeature.WriteClassName)) {
                        this.write(70);
                    }
                    return;
                }
            }
            int len = RyuFloat.toString(value, this.buf, this.count);
            this.count += len;
            if (checkWriteClassName && this.isEnabled(SerializerFeature.WriteClassName)) {
                this.write(70);
            }
        }
    }

    public void writeDouble(double value, boolean checkWriteClassName) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            this.writeNull();
            return;
        }
        int newcount = this.count + 24;
        if (newcount > this.buf.length) {
            if (this.writer == null) {
                this.expandCapacity(newcount);
            } else {
                String str = RyuDouble.toString(value);
                this.write(str, 0, str.length());
                if (checkWriteClassName && this.isEnabled(SerializerFeature.WriteClassName)) {
                    this.write(68);
                }
                return;
            }
        }
        int len = RyuDouble.toString(value, this.buf, this.count);
        this.count += len;
        if (checkWriteClassName && this.isEnabled(SerializerFeature.WriteClassName)) {
            this.write(68);
        }
    }

    public void writeEnum(Enum<?> value) {
        if (value == null) {
            this.writeNull();
            return;
        }
        String strVal = null;
        if (this.writeEnumUsingName && !this.writeEnumUsingToString) {
            strVal = value.name();
        } else if (this.writeEnumUsingToString) {
            strVal = value.toString();
        }
        if (strVal != null) {
            int quote = this.isEnabled(SerializerFeature.UseSingleQuotes) ? 39 : 34;
            this.write(quote);
            this.write(strVal);
            this.write(quote);
        } else {
            this.writeInt(value.ordinal());
        }
    }

    public void writeLongAndChar(long i, char c) throws IOException {
        this.writeLong(i);
        this.write(c);
    }

    public void writeLong(long i) {
        boolean needQuotationMark;
        boolean bl = needQuotationMark = this.isEnabled(SerializerFeature.BrowserCompatible) && !this.isEnabled(SerializerFeature.WriteClassName) && (i > 0x1FFFFFFFFFFFFFL || i < -9007199254740991L);
        if (i == Long.MIN_VALUE) {
            if (needQuotationMark) {
                this.write("\"-9223372036854775808\"");
            } else {
                this.write("-9223372036854775808");
            }
            return;
        }
        int size = i < 0L ? IOUtils.stringSize(-i) + 1 : IOUtils.stringSize(i);
        int newcount = this.count + size;
        if (needQuotationMark) {
            newcount += 2;
        }
        if (newcount > this.buf.length) {
            if (this.writer == null) {
                this.expandCapacity(newcount);
            } else {
                char[] chars = new char[size];
                IOUtils.getChars(i, size, chars);
                if (needQuotationMark) {
                    this.write(34);
                    this.write(chars, 0, chars.length);
                    this.write(34);
                } else {
                    this.write(chars, 0, chars.length);
                }
                return;
            }
        }
        if (needQuotationMark) {
            this.buf[this.count] = 34;
            IOUtils.getChars(i, newcount - 1, this.buf);
            this.buf[newcount - 1] = 34;
        } else {
            IOUtils.getChars(i, newcount, this.buf);
        }
        this.count = newcount;
    }

    public void writeNull() {
        this.write("null");
    }

    public void writeNull(SerializerFeature feature) {
        this.writeNull(0, feature.mask);
    }

    public void writeNull(int beanFeatures, int feature) {
        if ((beanFeatures & feature) == 0 && (this.features & feature) == 0) {
            this.writeNull();
            return;
        }
        if ((beanFeatures & SerializerFeature.WriteMapNullValue.mask) != 0 && (beanFeatures & ~SerializerFeature.WriteMapNullValue.mask & SerializerFeature.WRITE_MAP_NULL_FEATURES) == 0) {
            this.writeNull();
            return;
        }
        if (feature == SerializerFeature.WriteNullListAsEmpty.mask) {
            this.write("[]");
        } else if (feature == SerializerFeature.WriteNullStringAsEmpty.mask) {
            this.writeString("");
        } else if (feature == SerializerFeature.WriteNullBooleanAsFalse.mask) {
            this.write("false");
        } else if (feature == SerializerFeature.WriteNullNumberAsZero.mask) {
            this.write(48);
        } else {
            this.writeNull();
        }
    }

    public void writeStringWithDoubleQuote(String text, char seperator) {
        if (text == null) {
            this.writeNull();
            if (seperator != '\u0000') {
                this.write(seperator);
            }
            return;
        }
        int len = text.length();
        int newcount = this.count + len + 2;
        if (seperator != '\u0000') {
            ++newcount;
        }
        if (newcount > this.buf.length) {
            if (this.writer != null) {
                this.write(34);
                for (int i = 0; i < text.length(); ++i) {
                    char ch = text.charAt(i);
                    if (this.isEnabled(SerializerFeature.BrowserSecure) && (ch == '(' || ch == ')' || ch == '<' || ch == '>')) {
                        this.write(92);
                        this.write(117);
                        this.write(IOUtils.DIGITS[ch >>> 12 & 0xF]);
                        this.write(IOUtils.DIGITS[ch >>> 8 & 0xF]);
                        this.write(IOUtils.DIGITS[ch >>> 4 & 0xF]);
                        this.write(IOUtils.DIGITS[ch & 0xF]);
                        continue;
                    }
                    if (this.isEnabled(SerializerFeature.BrowserCompatible)) {
                        if (ch == '\b' || ch == '\f' || ch == '\n' || ch == '\r' || ch == '\t' || ch == '\"' || ch == '/' || ch == '\\') {
                            this.write(92);
                            this.write(IOUtils.replaceChars[ch]);
                            continue;
                        }
                        if (ch < ' ') {
                            this.write(92);
                            this.write(117);
                            this.write(48);
                            this.write(48);
                            this.write(IOUtils.ASCII_CHARS[ch * 2]);
                            this.write(IOUtils.ASCII_CHARS[ch * 2 + 1]);
                            continue;
                        }
                        if (ch >= '\u007f') {
                            this.write(92);
                            this.write(117);
                            this.write(IOUtils.DIGITS[ch >>> 12 & 0xF]);
                            this.write(IOUtils.DIGITS[ch >>> 8 & 0xF]);
                            this.write(IOUtils.DIGITS[ch >>> 4 & 0xF]);
                            this.write(IOUtils.DIGITS[ch & 0xF]);
                            continue;
                        }
                    } else if (ch < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[ch] != 0 || ch == '/' && this.isEnabled(SerializerFeature.WriteSlashAsSpecial)) {
                        this.write(92);
                        if (IOUtils.specicalFlags_doubleQuotes[ch] == 4) {
                            this.write(117);
                            this.write(IOUtils.DIGITS[ch >>> 12 & 0xF]);
                            this.write(IOUtils.DIGITS[ch >>> 8 & 0xF]);
                            this.write(IOUtils.DIGITS[ch >>> 4 & 0xF]);
                            this.write(IOUtils.DIGITS[ch & 0xF]);
                            continue;
                        }
                        this.write(IOUtils.replaceChars[ch]);
                        continue;
                    }
                    this.write(ch);
                }
                this.write(34);
                if (seperator != '\u0000') {
                    this.write(seperator);
                }
                return;
            }
            this.expandCapacity(newcount);
        }
        int start = this.count + 1;
        int end = start + len;
        this.buf[this.count] = 34;
        text.getChars(0, len, this.buf, start);
        this.count = newcount;
        if (this.isEnabled(SerializerFeature.BrowserCompatible)) {
            char ch;
            int i;
            int lastSpecialIndex = -1;
            for (i = start; i < end; ++i) {
                ch = this.buf[i];
                if (ch == '\"' || ch == '/' || ch == '\\') {
                    lastSpecialIndex = i;
                    ++newcount;
                    continue;
                }
                if (ch == '\b' || ch == '\f' || ch == '\n' || ch == '\r' || ch == '\t') {
                    lastSpecialIndex = i;
                    ++newcount;
                    continue;
                }
                if (ch < ' ') {
                    lastSpecialIndex = i;
                    newcount += 5;
                    continue;
                }
                if (ch < '\u007f') continue;
                lastSpecialIndex = i;
                newcount += 5;
            }
            if (newcount > this.buf.length) {
                this.expandCapacity(newcount);
            }
            this.count = newcount;
            for (i = lastSpecialIndex; i >= start; --i) {
                ch = this.buf[i];
                if (ch == '\b' || ch == '\f' || ch == '\n' || ch == '\r' || ch == '\t') {
                    System.arraycopy(this.buf, i + 1, this.buf, i + 2, end - i - 1);
                    this.buf[i] = 92;
                    this.buf[i + 1] = IOUtils.replaceChars[ch];
                    ++end;
                    continue;
                }
                if (ch == '\"' || ch == '/' || ch == '\\') {
                    System.arraycopy(this.buf, i + 1, this.buf, i + 2, end - i - 1);
                    this.buf[i] = 92;
                    this.buf[i + 1] = ch;
                    ++end;
                    continue;
                }
                if (ch < ' ') {
                    System.arraycopy(this.buf, i + 1, this.buf, i + 6, end - i - 1);
                    this.buf[i] = 92;
                    this.buf[i + 1] = 117;
                    this.buf[i + 2] = 48;
                    this.buf[i + 3] = 48;
                    this.buf[i + 4] = IOUtils.ASCII_CHARS[ch * 2];
                    this.buf[i + 5] = IOUtils.ASCII_CHARS[ch * 2 + 1];
                    end += 5;
                    continue;
                }
                if (ch < '\u007f') continue;
                System.arraycopy(this.buf, i + 1, this.buf, i + 6, end - i - 1);
                this.buf[i] = 92;
                this.buf[i + 1] = 117;
                this.buf[i + 2] = IOUtils.DIGITS[ch >>> 12 & 0xF];
                this.buf[i + 3] = IOUtils.DIGITS[ch >>> 8 & 0xF];
                this.buf[i + 4] = IOUtils.DIGITS[ch >>> 4 & 0xF];
                this.buf[i + 5] = IOUtils.DIGITS[ch & 0xF];
                end += 5;
            }
            if (seperator != '\u0000') {
                this.buf[this.count - 2] = 34;
                this.buf[this.count - 1] = seperator;
            } else {
                this.buf[this.count - 1] = 34;
            }
            return;
        }
        int specialCount = 0;
        int lastSpecialIndex = -1;
        int firstSpecialIndex = -1;
        int lastSpecial = 0;
        for (int i = start; i < end; ++i) {
            boolean special;
            int ch = this.buf[i];
            if (ch >= 93) {
                if (ch < 127 || ch != 8232 && ch != 8233 && ch >= 160) continue;
                if (firstSpecialIndex == -1) {
                    firstSpecialIndex = i;
                }
                ++specialCount;
                lastSpecialIndex = i;
                lastSpecial = ch;
                newcount += 4;
                continue;
            }
            boolean bl = special = ch < 64 && (this.sepcialBits & 1L << ch) != 0L || ch == 92;
            if (!special) continue;
            ++specialCount;
            lastSpecialIndex = i;
            lastSpecial = ch;
            if (ch == 40 || ch == 41 || ch == 60 || ch == 62 || ch < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[ch] == 4) {
                newcount += 4;
            }
            if (firstSpecialIndex != -1) continue;
            firstSpecialIndex = i;
        }
        if (specialCount > 0) {
            if ((newcount += specialCount) > this.buf.length) {
                this.expandCapacity(newcount);
            }
            this.count = newcount;
            if (specialCount == 1) {
                int LengthOfCopy;
                int destPos;
                int srcPos;
                if (lastSpecial == 8232) {
                    srcPos = lastSpecialIndex + 1;
                    destPos = lastSpecialIndex + 6;
                    LengthOfCopy = end - lastSpecialIndex - 1;
                    System.arraycopy(this.buf, srcPos, this.buf, destPos, LengthOfCopy);
                    this.buf[lastSpecialIndex] = 92;
                    this.buf[++lastSpecialIndex] = 117;
                    this.buf[++lastSpecialIndex] = 50;
                    this.buf[++lastSpecialIndex] = 48;
                    this.buf[++lastSpecialIndex] = 50;
                    this.buf[++lastSpecialIndex] = 56;
                } else if (lastSpecial == 8233) {
                    srcPos = lastSpecialIndex + 1;
                    destPos = lastSpecialIndex + 6;
                    LengthOfCopy = end - lastSpecialIndex - 1;
                    System.arraycopy(this.buf, srcPos, this.buf, destPos, LengthOfCopy);
                    this.buf[lastSpecialIndex] = 92;
                    this.buf[++lastSpecialIndex] = 117;
                    this.buf[++lastSpecialIndex] = 50;
                    this.buf[++lastSpecialIndex] = 48;
                    this.buf[++lastSpecialIndex] = 50;
                    this.buf[++lastSpecialIndex] = 57;
                } else if (lastSpecial == 40 || lastSpecial == 41 || lastSpecial == 60 || lastSpecial == 62) {
                    srcPos = lastSpecialIndex + 1;
                    destPos = lastSpecialIndex + 6;
                    LengthOfCopy = end - lastSpecialIndex - 1;
                    System.arraycopy(this.buf, srcPos, this.buf, destPos, LengthOfCopy);
                    this.buf[lastSpecialIndex] = 92;
                    this.buf[++lastSpecialIndex] = 117;
                    int ch = lastSpecial;
                    this.buf[++lastSpecialIndex] = IOUtils.DIGITS[ch >>> 12 & 0xF];
                    this.buf[++lastSpecialIndex] = IOUtils.DIGITS[ch >>> 8 & 0xF];
                    this.buf[++lastSpecialIndex] = IOUtils.DIGITS[ch >>> 4 & 0xF];
                    this.buf[++lastSpecialIndex] = IOUtils.DIGITS[ch & 0xF];
                } else {
                    int destPos2;
                    int srcPos2;
                    int ch = lastSpecial;
                    if (ch < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[ch] == 4) {
                        srcPos2 = lastSpecialIndex + 1;
                        destPos2 = lastSpecialIndex + 6;
                        int LengthOfCopy2 = end - lastSpecialIndex - 1;
                        System.arraycopy(this.buf, srcPos2, this.buf, destPos2, LengthOfCopy2);
                        int bufIndex = lastSpecialIndex;
                        this.buf[bufIndex++] = 92;
                        this.buf[bufIndex++] = 117;
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 12 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 8 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 4 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch & 0xF];
                    } else {
                        srcPos2 = lastSpecialIndex + 1;
                        destPos2 = lastSpecialIndex + 2;
                        int LengthOfCopy3 = end - lastSpecialIndex - 1;
                        System.arraycopy(this.buf, srcPos2, this.buf, destPos2, LengthOfCopy3);
                        this.buf[lastSpecialIndex] = 92;
                        this.buf[++lastSpecialIndex] = IOUtils.replaceChars[ch];
                    }
                }
            } else if (specialCount > 1) {
                int textIndex = firstSpecialIndex - start;
                int bufIndex = firstSpecialIndex;
                for (int i = textIndex; i < text.length(); ++i) {
                    char ch = text.charAt(i);
                    if (this.browserSecure && (ch == '(' || ch == ')' || ch == '<' || ch == '>')) {
                        this.buf[bufIndex++] = 92;
                        this.buf[bufIndex++] = 117;
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 12 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 8 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 4 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch & 0xF];
                        end += 5;
                        continue;
                    }
                    if (ch < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[ch] != 0 || ch == '/' && this.isEnabled(SerializerFeature.WriteSlashAsSpecial)) {
                        this.buf[bufIndex++] = 92;
                        if (IOUtils.specicalFlags_doubleQuotes[ch] == 4) {
                            this.buf[bufIndex++] = 117;
                            this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 12 & 0xF];
                            this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 8 & 0xF];
                            this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 4 & 0xF];
                            this.buf[bufIndex++] = IOUtils.DIGITS[ch & 0xF];
                            end += 5;
                            continue;
                        }
                        this.buf[bufIndex++] = IOUtils.replaceChars[ch];
                        ++end;
                        continue;
                    }
                    if (ch == '\u2028' || ch == '\u2029') {
                        this.buf[bufIndex++] = 92;
                        this.buf[bufIndex++] = 117;
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 12 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 8 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 4 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch & 0xF];
                        end += 5;
                        continue;
                    }
                    this.buf[bufIndex++] = ch;
                }
            }
        }
        if (seperator != '\u0000') {
            this.buf[this.count - 2] = 34;
            this.buf[this.count - 1] = seperator;
        } else {
            this.buf[this.count - 1] = 34;
        }
    }

    public void writeStringWithDoubleQuote(char[] text, char seperator) {
        if (text == null) {
            this.writeNull();
            if (seperator != '\u0000') {
                this.write(seperator);
            }
            return;
        }
        int len = text.length;
        int newcount = this.count + len + 2;
        if (seperator != '\u0000') {
            ++newcount;
        }
        if (newcount > this.buf.length) {
            if (this.writer != null) {
                this.write(34);
                for (int i = 0; i < text.length; ++i) {
                    char ch = text[i];
                    if (this.isEnabled(SerializerFeature.BrowserSecure) && (ch == '(' || ch == ')' || ch == '<' || ch == '>')) {
                        this.write(92);
                        this.write(117);
                        this.write(IOUtils.DIGITS[ch >>> 12 & 0xF]);
                        this.write(IOUtils.DIGITS[ch >>> 8 & 0xF]);
                        this.write(IOUtils.DIGITS[ch >>> 4 & 0xF]);
                        this.write(IOUtils.DIGITS[ch & 0xF]);
                        continue;
                    }
                    if (this.isEnabled(SerializerFeature.BrowserCompatible)) {
                        if (ch == '\b' || ch == '\f' || ch == '\n' || ch == '\r' || ch == '\t' || ch == '\"' || ch == '/' || ch == '\\') {
                            this.write(92);
                            this.write(IOUtils.replaceChars[ch]);
                            continue;
                        }
                        if (ch < ' ') {
                            this.write(92);
                            this.write(117);
                            this.write(48);
                            this.write(48);
                            this.write(IOUtils.ASCII_CHARS[ch * 2]);
                            this.write(IOUtils.ASCII_CHARS[ch * 2 + 1]);
                            continue;
                        }
                        if (ch >= '\u007f') {
                            this.write(92);
                            this.write(117);
                            this.write(IOUtils.DIGITS[ch >>> 12 & 0xF]);
                            this.write(IOUtils.DIGITS[ch >>> 8 & 0xF]);
                            this.write(IOUtils.DIGITS[ch >>> 4 & 0xF]);
                            this.write(IOUtils.DIGITS[ch & 0xF]);
                            continue;
                        }
                    } else if (ch < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[ch] != 0 || ch == '/' && this.isEnabled(SerializerFeature.WriteSlashAsSpecial)) {
                        this.write(92);
                        if (IOUtils.specicalFlags_doubleQuotes[ch] == 4) {
                            this.write(117);
                            this.write(IOUtils.DIGITS[ch >>> 12 & 0xF]);
                            this.write(IOUtils.DIGITS[ch >>> 8 & 0xF]);
                            this.write(IOUtils.DIGITS[ch >>> 4 & 0xF]);
                            this.write(IOUtils.DIGITS[ch & 0xF]);
                            continue;
                        }
                        this.write(IOUtils.replaceChars[ch]);
                        continue;
                    }
                    this.write(ch);
                }
                this.write(34);
                if (seperator != '\u0000') {
                    this.write(seperator);
                }
                return;
            }
            this.expandCapacity(newcount);
        }
        int start = this.count + 1;
        int end = start + len;
        this.buf[this.count] = 34;
        System.arraycopy(text, 0, this.buf, start, text.length);
        this.count = newcount;
        if (this.isEnabled(SerializerFeature.BrowserCompatible)) {
            char ch;
            int i;
            int lastSpecialIndex = -1;
            for (i = start; i < end; ++i) {
                ch = this.buf[i];
                if (ch == '\"' || ch == '/' || ch == '\\') {
                    lastSpecialIndex = i;
                    ++newcount;
                    continue;
                }
                if (ch == '\b' || ch == '\f' || ch == '\n' || ch == '\r' || ch == '\t') {
                    lastSpecialIndex = i;
                    ++newcount;
                    continue;
                }
                if (ch < ' ') {
                    lastSpecialIndex = i;
                    newcount += 5;
                    continue;
                }
                if (ch < '\u007f') continue;
                lastSpecialIndex = i;
                newcount += 5;
            }
            if (newcount > this.buf.length) {
                this.expandCapacity(newcount);
            }
            this.count = newcount;
            for (i = lastSpecialIndex; i >= start; --i) {
                ch = this.buf[i];
                if (ch == '\b' || ch == '\f' || ch == '\n' || ch == '\r' || ch == '\t') {
                    System.arraycopy(this.buf, i + 1, this.buf, i + 2, end - i - 1);
                    this.buf[i] = 92;
                    this.buf[i + 1] = IOUtils.replaceChars[ch];
                    ++end;
                    continue;
                }
                if (ch == '\"' || ch == '/' || ch == '\\') {
                    System.arraycopy(this.buf, i + 1, this.buf, i + 2, end - i - 1);
                    this.buf[i] = 92;
                    this.buf[i + 1] = ch;
                    ++end;
                    continue;
                }
                if (ch < ' ') {
                    System.arraycopy(this.buf, i + 1, this.buf, i + 6, end - i - 1);
                    this.buf[i] = 92;
                    this.buf[i + 1] = 117;
                    this.buf[i + 2] = 48;
                    this.buf[i + 3] = 48;
                    this.buf[i + 4] = IOUtils.ASCII_CHARS[ch * 2];
                    this.buf[i + 5] = IOUtils.ASCII_CHARS[ch * 2 + 1];
                    end += 5;
                    continue;
                }
                if (ch < '\u007f') continue;
                System.arraycopy(this.buf, i + 1, this.buf, i + 6, end - i - 1);
                this.buf[i] = 92;
                this.buf[i + 1] = 117;
                this.buf[i + 2] = IOUtils.DIGITS[ch >>> 12 & 0xF];
                this.buf[i + 3] = IOUtils.DIGITS[ch >>> 8 & 0xF];
                this.buf[i + 4] = IOUtils.DIGITS[ch >>> 4 & 0xF];
                this.buf[i + 5] = IOUtils.DIGITS[ch & 0xF];
                end += 5;
            }
            if (seperator != '\u0000') {
                this.buf[this.count - 2] = 34;
                this.buf[this.count - 1] = seperator;
            } else {
                this.buf[this.count - 1] = 34;
            }
            return;
        }
        int specialCount = 0;
        int lastSpecialIndex = -1;
        int firstSpecialIndex = -1;
        int lastSpecial = 0;
        for (int i = start; i < end; ++i) {
            boolean special;
            int ch = this.buf[i];
            if (ch >= 93) {
                if (ch < 127 || ch != 8232 && ch != 8233 && ch >= 160) continue;
                if (firstSpecialIndex == -1) {
                    firstSpecialIndex = i;
                }
                ++specialCount;
                lastSpecialIndex = i;
                lastSpecial = ch;
                newcount += 4;
                continue;
            }
            boolean bl = special = ch < 64 && (this.sepcialBits & 1L << ch) != 0L || ch == 92;
            if (!special) continue;
            ++specialCount;
            lastSpecialIndex = i;
            lastSpecial = ch;
            if (ch == 40 || ch == 41 || ch == 60 || ch == 62 || ch < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[ch] == 4) {
                newcount += 4;
            }
            if (firstSpecialIndex != -1) continue;
            firstSpecialIndex = i;
        }
        if (specialCount > 0) {
            if ((newcount += specialCount) > this.buf.length) {
                this.expandCapacity(newcount);
            }
            this.count = newcount;
            if (specialCount == 1) {
                int LengthOfCopy;
                int destPos;
                int srcPos;
                if (lastSpecial == 8232) {
                    srcPos = lastSpecialIndex + 1;
                    destPos = lastSpecialIndex + 6;
                    LengthOfCopy = end - lastSpecialIndex - 1;
                    System.arraycopy(this.buf, srcPos, this.buf, destPos, LengthOfCopy);
                    this.buf[lastSpecialIndex] = 92;
                    this.buf[++lastSpecialIndex] = 117;
                    this.buf[++lastSpecialIndex] = 50;
                    this.buf[++lastSpecialIndex] = 48;
                    this.buf[++lastSpecialIndex] = 50;
                    this.buf[++lastSpecialIndex] = 56;
                } else if (lastSpecial == 8233) {
                    srcPos = lastSpecialIndex + 1;
                    destPos = lastSpecialIndex + 6;
                    LengthOfCopy = end - lastSpecialIndex - 1;
                    System.arraycopy(this.buf, srcPos, this.buf, destPos, LengthOfCopy);
                    this.buf[lastSpecialIndex] = 92;
                    this.buf[++lastSpecialIndex] = 117;
                    this.buf[++lastSpecialIndex] = 50;
                    this.buf[++lastSpecialIndex] = 48;
                    this.buf[++lastSpecialIndex] = 50;
                    this.buf[++lastSpecialIndex] = 57;
                } else if (lastSpecial == 40 || lastSpecial == 41 || lastSpecial == 60 || lastSpecial == 62) {
                    srcPos = lastSpecialIndex + 1;
                    destPos = lastSpecialIndex + 6;
                    LengthOfCopy = end - lastSpecialIndex - 1;
                    System.arraycopy(this.buf, srcPos, this.buf, destPos, LengthOfCopy);
                    this.buf[lastSpecialIndex] = 92;
                    this.buf[++lastSpecialIndex] = 117;
                    int ch = lastSpecial;
                    this.buf[++lastSpecialIndex] = IOUtils.DIGITS[ch >>> 12 & 0xF];
                    this.buf[++lastSpecialIndex] = IOUtils.DIGITS[ch >>> 8 & 0xF];
                    this.buf[++lastSpecialIndex] = IOUtils.DIGITS[ch >>> 4 & 0xF];
                    this.buf[++lastSpecialIndex] = IOUtils.DIGITS[ch & 0xF];
                } else {
                    int destPos2;
                    int srcPos2;
                    int ch = lastSpecial;
                    if (ch < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[ch] == 4) {
                        srcPos2 = lastSpecialIndex + 1;
                        destPos2 = lastSpecialIndex + 6;
                        int LengthOfCopy2 = end - lastSpecialIndex - 1;
                        System.arraycopy(this.buf, srcPos2, this.buf, destPos2, LengthOfCopy2);
                        int bufIndex = lastSpecialIndex;
                        this.buf[bufIndex++] = 92;
                        this.buf[bufIndex++] = 117;
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 12 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 8 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 4 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch & 0xF];
                    } else {
                        srcPos2 = lastSpecialIndex + 1;
                        destPos2 = lastSpecialIndex + 2;
                        int LengthOfCopy3 = end - lastSpecialIndex - 1;
                        System.arraycopy(this.buf, srcPos2, this.buf, destPos2, LengthOfCopy3);
                        this.buf[lastSpecialIndex] = 92;
                        this.buf[++lastSpecialIndex] = IOUtils.replaceChars[ch];
                    }
                }
            } else if (specialCount > 1) {
                int textIndex = firstSpecialIndex - start;
                int bufIndex = firstSpecialIndex;
                for (int i = textIndex; i < text.length; ++i) {
                    char ch = text[i];
                    if (this.browserSecure && (ch == '(' || ch == ')' || ch == '<' || ch == '>')) {
                        this.buf[bufIndex++] = 92;
                        this.buf[bufIndex++] = 117;
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 12 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 8 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 4 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch & 0xF];
                        end += 5;
                        continue;
                    }
                    if (ch < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[ch] != 0 || ch == '/' && this.isEnabled(SerializerFeature.WriteSlashAsSpecial)) {
                        this.buf[bufIndex++] = 92;
                        if (IOUtils.specicalFlags_doubleQuotes[ch] == 4) {
                            this.buf[bufIndex++] = 117;
                            this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 12 & 0xF];
                            this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 8 & 0xF];
                            this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 4 & 0xF];
                            this.buf[bufIndex++] = IOUtils.DIGITS[ch & 0xF];
                            end += 5;
                            continue;
                        }
                        this.buf[bufIndex++] = IOUtils.replaceChars[ch];
                        ++end;
                        continue;
                    }
                    if (ch == '\u2028' || ch == '\u2029') {
                        this.buf[bufIndex++] = 92;
                        this.buf[bufIndex++] = 117;
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 12 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 8 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 4 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch & 0xF];
                        end += 5;
                        continue;
                    }
                    this.buf[bufIndex++] = ch;
                }
            }
        }
        if (seperator != '\u0000') {
            this.buf[this.count - 2] = 34;
            this.buf[this.count - 1] = seperator;
        } else {
            this.buf[this.count - 1] = 34;
        }
    }

    public void writeFieldNameDirect(String text) {
        int len = text.length();
        int newcount = this.count + len + 3;
        if (newcount > this.buf.length) {
            this.expandCapacity(newcount);
        }
        int start = this.count + 1;
        this.buf[this.count] = 34;
        text.getChars(0, len, this.buf, start);
        this.count = newcount;
        this.buf[this.count - 2] = 34;
        this.buf[this.count - 1] = 58;
    }

    public void write(List<String> list) {
        int offset;
        if (list.isEmpty()) {
            this.write("[]");
            return;
        }
        int initOffset = offset = this.count;
        int list_size = list.size();
        for (int i = 0; i < list_size; ++i) {
            int j;
            String text = list.get(i);
            boolean hasSpecial = false;
            if (text == null) {
                hasSpecial = true;
            } else {
                char ch;
                int len = text.length();
                for (j = 0; j < len && !(hasSpecial = (ch = text.charAt(j)) < ' ' || ch > '~' || ch == '\"' || ch == '\\'); ++j) {
                }
            }
            if (hasSpecial) {
                this.count = initOffset;
                this.write(91);
                for (j = 0; j < list.size(); ++j) {
                    text = list.get(j);
                    if (j != 0) {
                        this.write(44);
                    }
                    if (text == null) {
                        this.write("null");
                        continue;
                    }
                    this.writeStringWithDoubleQuote(text, '\u0000');
                }
                this.write(93);
                return;
            }
            int newcount = offset + text.length() + 3;
            if (i == list.size() - 1) {
                ++newcount;
            }
            if (newcount > this.buf.length) {
                this.count = offset;
                this.expandCapacity(newcount);
            }
            this.buf[offset++] = i == 0 ? 91 : 44;
            this.buf[offset++] = 34;
            text.getChars(0, text.length(), this.buf, offset);
            offset += text.length();
            this.buf[offset++] = 34;
        }
        this.buf[offset++] = 93;
        this.count = offset;
    }

    public void writeFieldValue(char seperator, String name, char value) {
        this.write(seperator);
        this.writeFieldName(name);
        if (value == '\u0000') {
            this.writeString("\u0000");
        } else {
            this.writeString(Character.toString(value));
        }
    }

    public void writeFieldValue(char seperator, String name, boolean value) {
        if (!this.quoteFieldNames) {
            this.write(seperator);
            this.writeFieldName(name);
            this.write(value);
            return;
        }
        int intSize = value ? 4 : 5;
        int nameLen = name.length();
        int newcount = this.count + nameLen + 4 + intSize;
        if (newcount > this.buf.length) {
            if (this.writer != null) {
                this.write(seperator);
                this.writeString(name);
                this.write(58);
                this.write(value);
                return;
            }
            this.expandCapacity(newcount);
        }
        int start = this.count;
        this.count = newcount;
        this.buf[start] = seperator;
        int nameEnd = start + nameLen + 1;
        this.buf[start + 1] = this.keySeperator;
        name.getChars(0, nameLen, this.buf, start + 2);
        this.buf[nameEnd + 1] = this.keySeperator;
        if (value) {
            System.arraycopy(VALUE_TRUE, 0, this.buf, nameEnd + 2, 5);
        } else {
            System.arraycopy(VALUE_FALSE, 0, this.buf, nameEnd + 2, 6);
        }
    }

    public void write(boolean value) {
        if (value) {
            this.write("true");
        } else {
            this.write("false");
        }
    }

    public void writeFieldValue(char seperator, String name, int value) {
        if (value == Integer.MIN_VALUE || !this.quoteFieldNames) {
            this.write(seperator);
            this.writeFieldName(name);
            this.writeInt(value);
            return;
        }
        int intSize = value < 0 ? IOUtils.stringSize(-value) + 1 : IOUtils.stringSize(value);
        int nameLen = name.length();
        int newcount = this.count + nameLen + 4 + intSize;
        if (newcount > this.buf.length) {
            if (this.writer != null) {
                this.write(seperator);
                this.writeFieldName(name);
                this.writeInt(value);
                return;
            }
            this.expandCapacity(newcount);
        }
        int start = this.count;
        this.count = newcount;
        this.buf[start] = seperator;
        int nameEnd = start + nameLen + 1;
        this.buf[start + 1] = this.keySeperator;
        name.getChars(0, nameLen, this.buf, start + 2);
        this.buf[nameEnd + 1] = this.keySeperator;
        this.buf[nameEnd + 2] = 58;
        IOUtils.getChars(value, this.count, this.buf);
    }

    public void writeFieldValue(char seperator, String name, long value) {
        if (value == Long.MIN_VALUE || !this.quoteFieldNames || this.isEnabled(SerializerFeature.BrowserCompatible.mask)) {
            this.write(seperator);
            this.writeFieldName(name);
            this.writeLong(value);
            return;
        }
        int intSize = value < 0L ? IOUtils.stringSize(-value) + 1 : IOUtils.stringSize(value);
        int nameLen = name.length();
        int newcount = this.count + nameLen + 4 + intSize;
        if (newcount > this.buf.length) {
            if (this.writer != null) {
                this.write(seperator);
                this.writeFieldName(name);
                this.writeLong(value);
                return;
            }
            this.expandCapacity(newcount);
        }
        int start = this.count;
        this.count = newcount;
        this.buf[start] = seperator;
        int nameEnd = start + nameLen + 1;
        this.buf[start + 1] = this.keySeperator;
        name.getChars(0, nameLen, this.buf, start + 2);
        this.buf[nameEnd + 1] = this.keySeperator;
        this.buf[nameEnd + 2] = 58;
        IOUtils.getChars(value, this.count, this.buf);
    }

    public void writeFieldValue(char seperator, String name, float value) {
        this.write(seperator);
        this.writeFieldName(name);
        this.writeFloat(value, false);
    }

    public void writeFieldValue(char seperator, String name, double value) {
        this.write(seperator);
        this.writeFieldName(name);
        this.writeDouble(value, false);
    }

    public void writeFieldValue(char seperator, String name, String value) {
        if (this.quoteFieldNames) {
            if (this.useSingleQuotes) {
                this.write(seperator);
                this.writeFieldName(name);
                if (value == null) {
                    this.writeNull();
                } else {
                    this.writeString(value);
                }
            } else if (this.isEnabled(SerializerFeature.BrowserCompatible)) {
                this.write(seperator);
                this.writeStringWithDoubleQuote(name, ':');
                this.writeStringWithDoubleQuote(value, '\u0000');
            } else {
                this.writeFieldValueStringWithDoubleQuoteCheck(seperator, name, value);
            }
        } else {
            this.write(seperator);
            this.writeFieldName(name);
            if (value == null) {
                this.writeNull();
            } else {
                this.writeString(value);
            }
        }
    }

    public void writeFieldValueStringWithDoubleQuoteCheck(char seperator, String name, String value) {
        int valueLen;
        int nameLen = name.length();
        int newcount = this.count;
        if (value == null) {
            valueLen = 4;
            newcount += nameLen + 8;
        } else {
            valueLen = value.length();
            newcount += nameLen + valueLen + 6;
        }
        if (newcount > this.buf.length) {
            if (this.writer != null) {
                this.write(seperator);
                this.writeStringWithDoubleQuote(name, ':');
                this.writeStringWithDoubleQuote(value, '\u0000');
                return;
            }
            this.expandCapacity(newcount);
        }
        this.buf[this.count] = seperator;
        int nameStart = this.count + 2;
        int nameEnd = nameStart + nameLen;
        this.buf[this.count + 1] = 34;
        name.getChars(0, nameLen, this.buf, nameStart);
        this.count = newcount;
        this.buf[nameEnd] = 34;
        int index = nameEnd + 1;
        this.buf[index++] = 58;
        if (value == null) {
            this.buf[index++] = 110;
            this.buf[index++] = 117;
            this.buf[index++] = 108;
            this.buf[index++] = 108;
            return;
        }
        this.buf[index++] = 34;
        int valueStart = index;
        int valueEnd = valueStart + valueLen;
        value.getChars(0, valueLen, this.buf, valueStart);
        int specialCount = 0;
        int lastSpecialIndex = -1;
        int firstSpecialIndex = -1;
        int lastSpecial = 0;
        for (int i = valueStart; i < valueEnd; ++i) {
            boolean special;
            int ch = this.buf[i];
            if (ch >= 93) {
                if (ch < 127 || ch != 8232 && ch != 8233 && ch >= 160) continue;
                if (firstSpecialIndex == -1) {
                    firstSpecialIndex = i;
                }
                ++specialCount;
                lastSpecialIndex = i;
                lastSpecial = ch;
                newcount += 4;
                continue;
            }
            boolean bl = special = ch < 64 && (this.sepcialBits & 1L << ch) != 0L || ch == 92;
            if (!special) continue;
            ++specialCount;
            lastSpecialIndex = i;
            lastSpecial = ch;
            if (ch == 40 || ch == 41 || ch == 60 || ch == 62 || ch < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[ch] == 4) {
                newcount += 4;
            }
            if (firstSpecialIndex != -1) continue;
            firstSpecialIndex = i;
        }
        if (specialCount > 0) {
            if ((newcount += specialCount) > this.buf.length) {
                this.expandCapacity(newcount);
            }
            this.count = newcount;
            if (specialCount == 1) {
                int destPos;
                int srcPos;
                int ch;
                int LengthOfCopy;
                int destPos2;
                int srcPos2;
                if (lastSpecial == 8232) {
                    srcPos2 = lastSpecialIndex + 1;
                    destPos2 = lastSpecialIndex + 6;
                    LengthOfCopy = valueEnd - lastSpecialIndex - 1;
                    System.arraycopy(this.buf, srcPos2, this.buf, destPos2, LengthOfCopy);
                    this.buf[lastSpecialIndex] = 92;
                    this.buf[++lastSpecialIndex] = 117;
                    this.buf[++lastSpecialIndex] = 50;
                    this.buf[++lastSpecialIndex] = 48;
                    this.buf[++lastSpecialIndex] = 50;
                    this.buf[++lastSpecialIndex] = 56;
                } else if (lastSpecial == 8233) {
                    srcPos2 = lastSpecialIndex + 1;
                    destPos2 = lastSpecialIndex + 6;
                    LengthOfCopy = valueEnd - lastSpecialIndex - 1;
                    System.arraycopy(this.buf, srcPos2, this.buf, destPos2, LengthOfCopy);
                    this.buf[lastSpecialIndex] = 92;
                    this.buf[++lastSpecialIndex] = 117;
                    this.buf[++lastSpecialIndex] = 50;
                    this.buf[++lastSpecialIndex] = 48;
                    this.buf[++lastSpecialIndex] = 50;
                    this.buf[++lastSpecialIndex] = 57;
                } else if (lastSpecial == 40 || lastSpecial == 41 || lastSpecial == 60 || lastSpecial == 62) {
                    ch = lastSpecial;
                    srcPos = lastSpecialIndex + 1;
                    destPos = lastSpecialIndex + 6;
                    int LengthOfCopy2 = valueEnd - lastSpecialIndex - 1;
                    System.arraycopy(this.buf, srcPos, this.buf, destPos, LengthOfCopy2);
                    int bufIndex = lastSpecialIndex;
                    this.buf[bufIndex++] = 92;
                    this.buf[bufIndex++] = 117;
                    this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 12 & 0xF];
                    this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 8 & 0xF];
                    this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 4 & 0xF];
                    this.buf[bufIndex++] = IOUtils.DIGITS[ch & 0xF];
                } else {
                    ch = lastSpecial;
                    if (ch < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[ch] == 4) {
                        srcPos = lastSpecialIndex + 1;
                        destPos = lastSpecialIndex + 6;
                        int LengthOfCopy3 = valueEnd - lastSpecialIndex - 1;
                        System.arraycopy(this.buf, srcPos, this.buf, destPos, LengthOfCopy3);
                        int bufIndex = lastSpecialIndex;
                        this.buf[bufIndex++] = 92;
                        this.buf[bufIndex++] = 117;
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 12 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 8 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 4 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch & 0xF];
                    } else {
                        srcPos = lastSpecialIndex + 1;
                        destPos = lastSpecialIndex + 2;
                        int LengthOfCopy4 = valueEnd - lastSpecialIndex - 1;
                        System.arraycopy(this.buf, srcPos, this.buf, destPos, LengthOfCopy4);
                        this.buf[lastSpecialIndex] = 92;
                        this.buf[++lastSpecialIndex] = IOUtils.replaceChars[ch];
                    }
                }
            } else if (specialCount > 1) {
                int textIndex = firstSpecialIndex - valueStart;
                int bufIndex = firstSpecialIndex;
                for (int i = textIndex; i < value.length(); ++i) {
                    char ch = value.charAt(i);
                    if (this.browserSecure && (ch == '(' || ch == ')' || ch == '<' || ch == '>')) {
                        this.buf[bufIndex++] = 92;
                        this.buf[bufIndex++] = 117;
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 12 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 8 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 4 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch & 0xF];
                        valueEnd += 5;
                        continue;
                    }
                    if (ch < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[ch] != 0 || ch == '/' && this.isEnabled(SerializerFeature.WriteSlashAsSpecial)) {
                        this.buf[bufIndex++] = 92;
                        if (IOUtils.specicalFlags_doubleQuotes[ch] == 4) {
                            this.buf[bufIndex++] = 117;
                            this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 12 & 0xF];
                            this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 8 & 0xF];
                            this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 4 & 0xF];
                            this.buf[bufIndex++] = IOUtils.DIGITS[ch & 0xF];
                            valueEnd += 5;
                            continue;
                        }
                        this.buf[bufIndex++] = IOUtils.replaceChars[ch];
                        ++valueEnd;
                        continue;
                    }
                    if (ch == '\u2028' || ch == '\u2029') {
                        this.buf[bufIndex++] = 92;
                        this.buf[bufIndex++] = 117;
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 12 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 8 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch >>> 4 & 0xF];
                        this.buf[bufIndex++] = IOUtils.DIGITS[ch & 0xF];
                        valueEnd += 5;
                        continue;
                    }
                    this.buf[bufIndex++] = ch;
                }
            }
        }
        this.buf[this.count - 1] = 34;
    }

    public void writeFieldValueStringWithDoubleQuote(char seperator, String name, String value) {
        int nameLen = name.length();
        int newcount = this.count;
        int valueLen = value.length();
        if ((newcount += nameLen + valueLen + 6) > this.buf.length) {
            if (this.writer != null) {
                this.write(seperator);
                this.writeStringWithDoubleQuote(name, ':');
                this.writeStringWithDoubleQuote(value, '\u0000');
                return;
            }
            this.expandCapacity(newcount);
        }
        this.buf[this.count] = seperator;
        int nameStart = this.count + 2;
        int nameEnd = nameStart + nameLen;
        this.buf[this.count + 1] = 34;
        name.getChars(0, nameLen, this.buf, nameStart);
        this.count = newcount;
        this.buf[nameEnd] = 34;
        int index = nameEnd + 1;
        this.buf[index++] = 58;
        this.buf[index++] = 34;
        int valueStart = index;
        value.getChars(0, valueLen, this.buf, valueStart);
        this.buf[this.count - 1] = 34;
    }

    public void writeFieldValue(char seperator, String name, Enum<?> value) {
        if (value == null) {
            this.write(seperator);
            this.writeFieldName(name);
            this.writeNull();
            return;
        }
        if (this.writeEnumUsingName && !this.writeEnumUsingToString) {
            this.writeEnumFieldValue(seperator, name, value.name());
        } else if (this.writeEnumUsingToString) {
            this.writeEnumFieldValue(seperator, name, value.toString());
        } else {
            this.writeFieldValue(seperator, name, value.ordinal());
        }
    }

    private void writeEnumFieldValue(char seperator, String name, String value) {
        if (this.useSingleQuotes) {
            this.writeFieldValue(seperator, name, value);
        } else {
            this.writeFieldValueStringWithDoubleQuote(seperator, name, value);
        }
    }

    public void writeFieldValue(char seperator, String name, BigDecimal value) {
        this.write(seperator);
        this.writeFieldName(name);
        if (value == null) {
            this.writeNull();
        } else {
            int scale = value.scale();
            this.write(this.isEnabled(SerializerFeature.WriteBigDecimalAsPlain) && scale >= -100 && scale < 100 ? value.toPlainString() : value.toString());
        }
    }

    public void writeString(String text, char seperator) {
        if (this.useSingleQuotes) {
            this.writeStringWithSingleQuote(text);
            this.write(seperator);
        } else {
            this.writeStringWithDoubleQuote(text, seperator);
        }
    }

    public void writeString(String text) {
        if (this.useSingleQuotes) {
            this.writeStringWithSingleQuote(text);
        } else {
            this.writeStringWithDoubleQuote(text, '\u0000');
        }
    }

    public void writeString(char[] chars) {
        if (this.useSingleQuotes) {
            this.writeStringWithSingleQuote(chars);
        } else {
            String text = new String(chars);
            this.writeStringWithDoubleQuote(text, '\u0000');
        }
    }

    protected void writeStringWithSingleQuote(String text) {
        char ch;
        int i;
        if (text == null) {
            int newcount = this.count + 4;
            if (newcount > this.buf.length) {
                this.expandCapacity(newcount);
            }
            "null".getChars(0, 4, this.buf, this.count);
            this.count = newcount;
            return;
        }
        int len = text.length();
        int newcount = this.count + len + 2;
        if (newcount > this.buf.length) {
            if (this.writer != null) {
                this.write(39);
                for (int i2 = 0; i2 < text.length(); ++i2) {
                    char ch2 = text.charAt(i2);
                    if (ch2 <= '\r' || ch2 == '\\' || ch2 == '\'' || ch2 == '/' && this.isEnabled(SerializerFeature.WriteSlashAsSpecial)) {
                        this.write(92);
                        this.write(IOUtils.replaceChars[ch2]);
                        continue;
                    }
                    this.write(ch2);
                }
                this.write(39);
                return;
            }
            this.expandCapacity(newcount);
        }
        int start = this.count + 1;
        int end = start + len;
        this.buf[this.count] = 39;
        text.getChars(0, len, this.buf, start);
        this.count = newcount;
        int specialCount = 0;
        int lastSpecialIndex = -1;
        char lastSpecial = '\u0000';
        for (i = start; i < end; ++i) {
            ch = this.buf[i];
            if (ch > '\r' && ch != '\\' && ch != '\'' && (ch != '/' || !this.isEnabled(SerializerFeature.WriteSlashAsSpecial))) continue;
            ++specialCount;
            lastSpecialIndex = i;
            lastSpecial = ch;
        }
        if ((newcount += specialCount) > this.buf.length) {
            this.expandCapacity(newcount);
        }
        this.count = newcount;
        if (specialCount == 1) {
            System.arraycopy(this.buf, lastSpecialIndex + 1, this.buf, lastSpecialIndex + 2, end - lastSpecialIndex - 1);
            this.buf[lastSpecialIndex] = 92;
            this.buf[++lastSpecialIndex] = IOUtils.replaceChars[lastSpecial];
        } else if (specialCount > 1) {
            System.arraycopy(this.buf, lastSpecialIndex + 1, this.buf, lastSpecialIndex + 2, end - lastSpecialIndex - 1);
            this.buf[lastSpecialIndex] = 92;
            this.buf[++lastSpecialIndex] = IOUtils.replaceChars[lastSpecial];
            ++end;
            for (i = lastSpecialIndex - 2; i >= start; --i) {
                ch = this.buf[i];
                if (ch > '\r' && ch != '\\' && ch != '\'' && (ch != '/' || !this.isEnabled(SerializerFeature.WriteSlashAsSpecial))) continue;
                System.arraycopy(this.buf, i + 1, this.buf, i + 2, end - i - 1);
                this.buf[i] = 92;
                this.buf[i + 1] = IOUtils.replaceChars[ch];
                ++end;
            }
        }
        this.buf[this.count - 1] = 39;
    }

    protected void writeStringWithSingleQuote(char[] chars) {
        char ch;
        int i;
        if (chars == null) {
            int newcount = this.count + 4;
            if (newcount > this.buf.length) {
                this.expandCapacity(newcount);
            }
            "null".getChars(0, 4, this.buf, this.count);
            this.count = newcount;
            return;
        }
        int len = chars.length;
        int newcount = this.count + len + 2;
        if (newcount > this.buf.length) {
            if (this.writer != null) {
                this.write(39);
                for (int i2 = 0; i2 < chars.length; ++i2) {
                    char ch2 = chars[i2];
                    if (ch2 <= '\r' || ch2 == '\\' || ch2 == '\'' || ch2 == '/' && this.isEnabled(SerializerFeature.WriteSlashAsSpecial)) {
                        this.write(92);
                        this.write(IOUtils.replaceChars[ch2]);
                        continue;
                    }
                    this.write(ch2);
                }
                this.write(39);
                return;
            }
            this.expandCapacity(newcount);
        }
        int start = this.count + 1;
        int end = start + len;
        this.buf[this.count] = 39;
        System.arraycopy(chars, 0, this.buf, start, chars.length);
        this.count = newcount;
        int specialCount = 0;
        int lastSpecialIndex = -1;
        char lastSpecial = '\u0000';
        for (i = start; i < end; ++i) {
            ch = this.buf[i];
            if (ch > '\r' && ch != '\\' && ch != '\'' && (ch != '/' || !this.isEnabled(SerializerFeature.WriteSlashAsSpecial))) continue;
            ++specialCount;
            lastSpecialIndex = i;
            lastSpecial = ch;
        }
        if ((newcount += specialCount) > this.buf.length) {
            this.expandCapacity(newcount);
        }
        this.count = newcount;
        if (specialCount == 1) {
            System.arraycopy(this.buf, lastSpecialIndex + 1, this.buf, lastSpecialIndex + 2, end - lastSpecialIndex - 1);
            this.buf[lastSpecialIndex] = 92;
            this.buf[++lastSpecialIndex] = IOUtils.replaceChars[lastSpecial];
        } else if (specialCount > 1) {
            System.arraycopy(this.buf, lastSpecialIndex + 1, this.buf, lastSpecialIndex + 2, end - lastSpecialIndex - 1);
            this.buf[lastSpecialIndex] = 92;
            this.buf[++lastSpecialIndex] = IOUtils.replaceChars[lastSpecial];
            ++end;
            for (i = lastSpecialIndex - 2; i >= start; --i) {
                ch = this.buf[i];
                if (ch > '\r' && ch != '\\' && ch != '\'' && (ch != '/' || !this.isEnabled(SerializerFeature.WriteSlashAsSpecial))) continue;
                System.arraycopy(this.buf, i + 1, this.buf, i + 2, end - i - 1);
                this.buf[i] = 92;
                this.buf[i + 1] = IOUtils.replaceChars[ch];
                ++end;
            }
        }
        this.buf[this.count - 1] = 39;
    }

    public void writeFieldName(String key) {
        this.writeFieldName(key, false);
    }

    public void writeFieldName(String key, boolean checkSpecial) {
        if (key == null) {
            this.write("null:");
            return;
        }
        if (this.useSingleQuotes) {
            if (this.quoteFieldNames) {
                this.writeStringWithSingleQuote(key);
                this.write(58);
            } else {
                this.writeKeyWithSingleQuoteIfHasSpecial(key);
            }
        } else if (this.quoteFieldNames) {
            this.writeStringWithDoubleQuote(key, ':');
        } else {
            boolean hashSpecial = key.length() == 0;
            for (int i = 0; i < key.length(); ++i) {
                boolean special;
                char ch = key.charAt(i);
                boolean bl = special = ch < '@' && (this.sepcialBits & 1L << ch) != 0L || ch == '\\';
                if (!special) continue;
                hashSpecial = true;
                break;
            }
            if (hashSpecial) {
                this.writeStringWithDoubleQuote(key, ':');
            } else {
                this.write(key);
                this.write(58);
            }
        }
    }

    private void writeKeyWithSingleQuoteIfHasSpecial(String text) {
        byte[] specicalFlags_singleQuotes = IOUtils.specicalFlags_singleQuotes;
        int len = text.length();
        int newcount = this.count + len + 1;
        if (newcount > this.buf.length) {
            if (this.writer != null) {
                char ch;
                int i;
                if (len == 0) {
                    this.write(39);
                    this.write(39);
                    this.write(58);
                    return;
                }
                boolean hasSpecial = false;
                for (i = 0; i < len; ++i) {
                    ch = text.charAt(i);
                    if (ch >= specicalFlags_singleQuotes.length || specicalFlags_singleQuotes[ch] == 0) continue;
                    hasSpecial = true;
                    break;
                }
                if (hasSpecial) {
                    this.write(39);
                }
                for (i = 0; i < len; ++i) {
                    ch = text.charAt(i);
                    if (ch < specicalFlags_singleQuotes.length && specicalFlags_singleQuotes[ch] != 0) {
                        this.write(92);
                        this.write(IOUtils.replaceChars[ch]);
                        continue;
                    }
                    this.write(ch);
                }
                if (hasSpecial) {
                    this.write(39);
                }
                this.write(58);
                return;
            }
            this.expandCapacity(newcount);
        }
        if (len == 0) {
            int newCount = this.count + 3;
            if (newCount > this.buf.length) {
                this.expandCapacity(this.count + 3);
            }
            this.buf[this.count++] = 39;
            this.buf[this.count++] = 39;
            this.buf[this.count++] = 58;
            return;
        }
        int start = this.count;
        int end = start + len;
        text.getChars(0, len, this.buf, start);
        this.count = newcount;
        boolean hasSpecial = false;
        for (int i = start; i < end; ++i) {
            char ch = this.buf[i];
            if (ch >= specicalFlags_singleQuotes.length || specicalFlags_singleQuotes[ch] == 0) continue;
            if (!hasSpecial) {
                if ((newcount += 3) > this.buf.length) {
                    this.expandCapacity(newcount);
                }
                this.count = newcount;
                System.arraycopy(this.buf, i + 1, this.buf, i + 3, end - i - 1);
                System.arraycopy(this.buf, 0, this.buf, 1, i);
                this.buf[start] = 39;
                this.buf[++i] = 92;
                this.buf[++i] = IOUtils.replaceChars[ch];
                end += 2;
                this.buf[this.count - 2] = 39;
                hasSpecial = true;
                continue;
            }
            if (++newcount > this.buf.length) {
                this.expandCapacity(newcount);
            }
            this.count = newcount;
            System.arraycopy(this.buf, i + 1, this.buf, i + 2, end - i);
            this.buf[i] = 92;
            this.buf[++i] = IOUtils.replaceChars[ch];
            ++end;
        }
        this.buf[newcount - 1] = 58;
    }

    @Override
    public void flush() {
        if (this.writer == null) {
            return;
        }
        try {
            this.writer.write(this.buf, 0, this.count);
            this.writer.flush();
        } catch (IOException e) {
            throw new JSONException(e.getMessage(), e);
        }
        this.count = 0;
    }

    public void reset() {
        this.count = 0;
    }

    static {
        try {
            int serializer_buffer_threshold;
            String prop = IOUtils.getStringProperty("fastjson.serializer_buffer_threshold");
            if (prop != null && prop.length() > 0 && (serializer_buffer_threshold = Integer.parseInt(prop)) >= 64 && serializer_buffer_threshold <= 65536) {
                BUFFER_THRESHOLD = serializer_buffer_threshold * 1024;
            }
        } catch (Throwable throwable) {
            // empty catch block
        }
        nonDirectFeatures = 0 | SerializerFeature.UseSingleQuotes.mask | SerializerFeature.BrowserCompatible.mask | SerializerFeature.PrettyFormat.mask | SerializerFeature.WriteEnumUsingToString.mask | SerializerFeature.WriteNonStringValueAsString.mask | SerializerFeature.WriteSlashAsSpecial.mask | SerializerFeature.IgnoreErrorGetter.mask | SerializerFeature.WriteClassName.mask | SerializerFeature.NotWriteDefaultValue.mask;
    }
}

