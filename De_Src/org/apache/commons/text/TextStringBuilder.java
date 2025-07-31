/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.apache.commons.text.Builder;
import org.apache.commons.text.StringTokenizer;
import org.apache.commons.text.matcher.StringMatcher;

public class TextStringBuilder
implements CharSequence,
Appendable,
Serializable,
Builder<String> {
    private static final int FALSE_STRING_SIZE = "false".length();
    private static final int TRUE_STRING_SIZE = "true".length();
    static final int CAPACITY = 32;
    private static final long serialVersionUID = 1L;
    char[] buffer;
    private int size;
    private String newLine;
    private String nullText;

    public TextStringBuilder() {
        this(32);
    }

    public TextStringBuilder(int initialCapacity) {
        if (initialCapacity <= 0) {
            initialCapacity = 32;
        }
        this.buffer = new char[initialCapacity];
    }

    public TextStringBuilder(String str) {
        if (str == null) {
            this.buffer = new char[32];
        } else {
            this.buffer = new char[str.length() + 32];
            this.append(str);
        }
    }

    public String getNewLineText() {
        return this.newLine;
    }

    public TextStringBuilder setNewLineText(String newLine) {
        this.newLine = newLine;
        return this;
    }

    public String getNullText() {
        return this.nullText;
    }

    public TextStringBuilder setNullText(String nullText) {
        if (nullText != null && nullText.isEmpty()) {
            nullText = null;
        }
        this.nullText = nullText;
        return this;
    }

    @Override
    public int length() {
        return this.size;
    }

    public TextStringBuilder setLength(int length) {
        if (length < 0) {
            throw new StringIndexOutOfBoundsException(length);
        }
        if (length < this.size) {
            this.size = length;
        } else if (length > this.size) {
            this.ensureCapacity(length);
            int oldEnd = this.size;
            int newEnd = length;
            this.size = length;
            for (int i = oldEnd; i < newEnd; ++i) {
                this.buffer[i] = '\u0000';
            }
        }
        return this;
    }

    public int capacity() {
        return this.buffer.length;
    }

    public TextStringBuilder ensureCapacity(int capacity) {
        if (capacity > this.buffer.length) {
            char[] old = this.buffer;
            this.buffer = new char[capacity * 2];
            System.arraycopy(old, 0, this.buffer, 0, this.size);
        }
        return this;
    }

    public TextStringBuilder minimizeCapacity() {
        if (this.buffer.length > this.length()) {
            char[] old = this.buffer;
            this.buffer = new char[this.length()];
            System.arraycopy(old, 0, this.buffer, 0, this.size);
        }
        return this;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public TextStringBuilder clear() {
        this.size = 0;
        return this;
    }

    @Override
    public char charAt(int index) {
        if (index < 0 || index >= this.length()) {
            throw new StringIndexOutOfBoundsException(index);
        }
        return this.buffer[index];
    }

    public TextStringBuilder setCharAt(int index, char ch) {
        if (index < 0 || index >= this.length()) {
            throw new StringIndexOutOfBoundsException(index);
        }
        this.buffer[index] = ch;
        return this;
    }

    public TextStringBuilder deleteCharAt(int index) {
        if (index < 0 || index >= this.size) {
            throw new StringIndexOutOfBoundsException(index);
        }
        this.deleteImpl(index, index + 1, 1);
        return this;
    }

    public char[] toCharArray() {
        if (this.size == 0) {
            return new char[0];
        }
        char[] chars = new char[this.size];
        System.arraycopy(this.buffer, 0, chars, 0, this.size);
        return chars;
    }

    public char[] toCharArray(int startIndex, int endIndex) {
        int len = (endIndex = this.validateRange(startIndex, endIndex)) - startIndex;
        if (len == 0) {
            return new char[0];
        }
        char[] chars = new char[len];
        System.arraycopy(this.buffer, startIndex, chars, 0, len);
        return chars;
    }

    public char[] getChars(char[] destination) {
        int len = this.length();
        if (destination == null || destination.length < len) {
            destination = new char[len];
        }
        System.arraycopy(this.buffer, 0, destination, 0, len);
        return destination;
    }

    public void getChars(int startIndex, int endIndex, char[] destination, int destinationIndex) {
        if (startIndex < 0) {
            throw new StringIndexOutOfBoundsException(startIndex);
        }
        if (endIndex < 0 || endIndex > this.length()) {
            throw new StringIndexOutOfBoundsException(endIndex);
        }
        if (startIndex > endIndex) {
            throw new StringIndexOutOfBoundsException("end < start");
        }
        System.arraycopy(this.buffer, startIndex, destination, destinationIndex, endIndex - startIndex);
    }

    public int readFrom(Readable readable) throws IOException {
        int oldSize = this.size;
        if (readable instanceof Reader) {
            int read;
            Reader r = (Reader)readable;
            this.ensureCapacity(this.size + 1);
            while ((read = r.read(this.buffer, this.size, this.buffer.length - this.size)) != -1) {
                this.size += read;
                this.ensureCapacity(this.size + 1);
            }
        } else if (readable instanceof CharBuffer) {
            CharBuffer cb = (CharBuffer)readable;
            int remaining = cb.remaining();
            this.ensureCapacity(this.size + remaining);
            cb.get(this.buffer, this.size, remaining);
            this.size += remaining;
        } else {
            while (true) {
                this.ensureCapacity(this.size + 1);
                CharBuffer buf = CharBuffer.wrap(this.buffer, this.size, this.buffer.length - this.size);
                int read = readable.read(buf);
                if (read == -1) break;
                this.size += read;
            }
        }
        return this.size - oldSize;
    }

    public TextStringBuilder appendNewLine() {
        if (this.newLine == null) {
            this.append(System.lineSeparator());
            return this;
        }
        return this.append(this.newLine);
    }

    public TextStringBuilder appendNull() {
        if (this.nullText == null) {
            return this;
        }
        return this.append(this.nullText);
    }

    public TextStringBuilder append(Object obj) {
        if (obj == null) {
            return this.appendNull();
        }
        if (obj instanceof CharSequence) {
            return this.append((CharSequence)obj);
        }
        return this.append(obj.toString());
    }

    @Override
    public TextStringBuilder append(CharSequence seq) {
        if (seq == null) {
            return this.appendNull();
        }
        if (seq instanceof TextStringBuilder) {
            return this.append((TextStringBuilder)seq);
        }
        if (seq instanceof StringBuilder) {
            return this.append((StringBuilder)seq);
        }
        if (seq instanceof StringBuffer) {
            return this.append((StringBuffer)seq);
        }
        if (seq instanceof CharBuffer) {
            return this.append((CharBuffer)seq);
        }
        return this.append(seq.toString());
    }

    @Override
    public TextStringBuilder append(CharSequence seq, int startIndex, int length) {
        if (seq == null) {
            return this.appendNull();
        }
        return this.append(seq.toString(), startIndex, length);
    }

    public TextStringBuilder append(String str) {
        if (str == null) {
            return this.appendNull();
        }
        int strLen = str.length();
        if (strLen > 0) {
            int len = this.length();
            this.ensureCapacity(len + strLen);
            str.getChars(0, strLen, this.buffer, len);
            this.size += strLen;
        }
        return this;
    }

    public TextStringBuilder append(String str, int startIndex, int length) {
        if (str == null) {
            return this.appendNull();
        }
        if (startIndex < 0 || startIndex > str.length()) {
            throw new StringIndexOutOfBoundsException("startIndex must be valid");
        }
        if (length < 0 || startIndex + length > str.length()) {
            throw new StringIndexOutOfBoundsException("length must be valid");
        }
        if (length > 0) {
            int len = this.length();
            this.ensureCapacity(len + length);
            str.getChars(startIndex, startIndex + length, this.buffer, len);
            this.size += length;
        }
        return this;
    }

    public TextStringBuilder append(String format, Object ... objs) {
        return this.append(String.format(format, objs));
    }

    public TextStringBuilder append(CharBuffer buf) {
        if (buf == null) {
            return this.appendNull();
        }
        if (buf.hasArray()) {
            int length = buf.remaining();
            int len = this.length();
            this.ensureCapacity(len + length);
            System.arraycopy(buf.array(), buf.arrayOffset() + buf.position(), this.buffer, len, length);
            this.size += length;
        } else {
            this.append(buf.toString());
        }
        return this;
    }

    public TextStringBuilder append(CharBuffer buf, int startIndex, int length) {
        if (buf == null) {
            return this.appendNull();
        }
        if (buf.hasArray()) {
            int totalLength = buf.remaining();
            if (startIndex < 0 || startIndex > totalLength) {
                throw new StringIndexOutOfBoundsException("startIndex must be valid");
            }
            if (length < 0 || startIndex + length > totalLength) {
                throw new StringIndexOutOfBoundsException("length must be valid");
            }
            int len = this.length();
            this.ensureCapacity(len + length);
            System.arraycopy(buf.array(), buf.arrayOffset() + buf.position() + startIndex, this.buffer, len, length);
            this.size += length;
        } else {
            this.append(buf.toString(), startIndex, length);
        }
        return this;
    }

    public TextStringBuilder append(StringBuffer str) {
        if (str == null) {
            return this.appendNull();
        }
        int strLen = str.length();
        if (strLen > 0) {
            int len = this.length();
            this.ensureCapacity(len + strLen);
            str.getChars(0, strLen, this.buffer, len);
            this.size += strLen;
        }
        return this;
    }

    public TextStringBuilder append(StringBuffer str, int startIndex, int length) {
        if (str == null) {
            return this.appendNull();
        }
        if (startIndex < 0 || startIndex > str.length()) {
            throw new StringIndexOutOfBoundsException("startIndex must be valid");
        }
        if (length < 0 || startIndex + length > str.length()) {
            throw new StringIndexOutOfBoundsException("length must be valid");
        }
        if (length > 0) {
            int len = this.length();
            this.ensureCapacity(len + length);
            str.getChars(startIndex, startIndex + length, this.buffer, len);
            this.size += length;
        }
        return this;
    }

    public TextStringBuilder append(StringBuilder str) {
        if (str == null) {
            return this.appendNull();
        }
        int strLen = str.length();
        if (strLen > 0) {
            int len = this.length();
            this.ensureCapacity(len + strLen);
            str.getChars(0, strLen, this.buffer, len);
            this.size += strLen;
        }
        return this;
    }

    public TextStringBuilder append(StringBuilder str, int startIndex, int length) {
        if (str == null) {
            return this.appendNull();
        }
        if (startIndex < 0 || startIndex > str.length()) {
            throw new StringIndexOutOfBoundsException("startIndex must be valid");
        }
        if (length < 0 || startIndex + length > str.length()) {
            throw new StringIndexOutOfBoundsException("length must be valid");
        }
        if (length > 0) {
            int len = this.length();
            this.ensureCapacity(len + length);
            str.getChars(startIndex, startIndex + length, this.buffer, len);
            this.size += length;
        }
        return this;
    }

    public TextStringBuilder append(TextStringBuilder str) {
        if (str == null) {
            return this.appendNull();
        }
        int strLen = str.length();
        if (strLen > 0) {
            int len = this.length();
            this.ensureCapacity(len + strLen);
            System.arraycopy(str.buffer, 0, this.buffer, len, strLen);
            this.size += strLen;
        }
        return this;
    }

    public TextStringBuilder append(TextStringBuilder str, int startIndex, int length) {
        if (str == null) {
            return this.appendNull();
        }
        if (startIndex < 0 || startIndex > str.length()) {
            throw new StringIndexOutOfBoundsException("startIndex must be valid");
        }
        if (length < 0 || startIndex + length > str.length()) {
            throw new StringIndexOutOfBoundsException("length must be valid");
        }
        if (length > 0) {
            int len = this.length();
            this.ensureCapacity(len + length);
            str.getChars(startIndex, startIndex + length, this.buffer, len);
            this.size += length;
        }
        return this;
    }

    public TextStringBuilder append(char[] chars) {
        if (chars == null) {
            return this.appendNull();
        }
        int strLen = chars.length;
        if (strLen > 0) {
            int len = this.length();
            this.ensureCapacity(len + strLen);
            System.arraycopy(chars, 0, this.buffer, len, strLen);
            this.size += strLen;
        }
        return this;
    }

    public TextStringBuilder append(char[] chars, int startIndex, int length) {
        if (chars == null) {
            return this.appendNull();
        }
        if (startIndex < 0 || startIndex > chars.length) {
            throw new StringIndexOutOfBoundsException("Invalid startIndex: " + length);
        }
        if (length < 0 || startIndex + length > chars.length) {
            throw new StringIndexOutOfBoundsException("Invalid length: " + length);
        }
        if (length > 0) {
            int len = this.length();
            this.ensureCapacity(len + length);
            System.arraycopy(chars, startIndex, this.buffer, len, length);
            this.size += length;
        }
        return this;
    }

    public TextStringBuilder append(boolean value) {
        if (value) {
            this.ensureCapacity(this.size + TRUE_STRING_SIZE);
            this.buffer[this.size++] = 116;
            this.buffer[this.size++] = 114;
            this.buffer[this.size++] = 117;
            this.buffer[this.size++] = 101;
        } else {
            this.ensureCapacity(this.size + FALSE_STRING_SIZE);
            this.buffer[this.size++] = 102;
            this.buffer[this.size++] = 97;
            this.buffer[this.size++] = 108;
            this.buffer[this.size++] = 115;
            this.buffer[this.size++] = 101;
        }
        return this;
    }

    @Override
    public TextStringBuilder append(char ch) {
        int len = this.length();
        this.ensureCapacity(len + 1);
        this.buffer[this.size++] = ch;
        return this;
    }

    public TextStringBuilder append(int value) {
        return this.append(String.valueOf(value));
    }

    public TextStringBuilder append(long value) {
        return this.append(String.valueOf(value));
    }

    public TextStringBuilder append(float value) {
        return this.append(String.valueOf(value));
    }

    public TextStringBuilder append(double value) {
        return this.append(String.valueOf(value));
    }

    public TextStringBuilder appendln(Object obj) {
        return this.append(obj).appendNewLine();
    }

    public TextStringBuilder appendln(String str) {
        return this.append(str).appendNewLine();
    }

    public TextStringBuilder appendln(String str, int startIndex, int length) {
        return this.append(str, startIndex, length).appendNewLine();
    }

    public TextStringBuilder appendln(String format, Object ... objs) {
        return this.append(format, objs).appendNewLine();
    }

    public TextStringBuilder appendln(StringBuffer str) {
        return this.append(str).appendNewLine();
    }

    public TextStringBuilder appendln(StringBuilder str) {
        return this.append(str).appendNewLine();
    }

    public TextStringBuilder appendln(StringBuilder str, int startIndex, int length) {
        return this.append(str, startIndex, length).appendNewLine();
    }

    public TextStringBuilder appendln(StringBuffer str, int startIndex, int length) {
        return this.append(str, startIndex, length).appendNewLine();
    }

    public TextStringBuilder appendln(TextStringBuilder str) {
        return this.append(str).appendNewLine();
    }

    public TextStringBuilder appendln(TextStringBuilder str, int startIndex, int length) {
        return this.append(str, startIndex, length).appendNewLine();
    }

    public TextStringBuilder appendln(char[] chars) {
        return this.append(chars).appendNewLine();
    }

    public TextStringBuilder appendln(char[] chars, int startIndex, int length) {
        return this.append(chars, startIndex, length).appendNewLine();
    }

    public TextStringBuilder appendln(boolean value) {
        return this.append(value).appendNewLine();
    }

    public TextStringBuilder appendln(char ch) {
        return this.append(ch).appendNewLine();
    }

    public TextStringBuilder appendln(int value) {
        return this.append(value).appendNewLine();
    }

    public TextStringBuilder appendln(long value) {
        return this.append(value).appendNewLine();
    }

    public TextStringBuilder appendln(float value) {
        return this.append(value).appendNewLine();
    }

    public TextStringBuilder appendln(double value) {
        return this.append(value).appendNewLine();
    }

    public <T> TextStringBuilder appendAll(T ... array) {
        if (array != null && array.length > 0) {
            for (T element : array) {
                this.append(element);
            }
        }
        return this;
    }

    public TextStringBuilder appendAll(Iterable<?> iterable) {
        if (iterable != null) {
            for (Object o : iterable) {
                this.append(o);
            }
        }
        return this;
    }

    public TextStringBuilder appendAll(Iterator<?> it) {
        if (it != null) {
            while (it.hasNext()) {
                this.append(it.next());
            }
        }
        return this;
    }

    public TextStringBuilder appendWithSeparators(Object[] array, String separator) {
        if (array != null && array.length > 0) {
            String sep = Objects.toString(separator, "");
            this.append(array[0]);
            for (int i = 1; i < array.length; ++i) {
                this.append(sep);
                this.append(array[i]);
            }
        }
        return this;
    }

    public TextStringBuilder appendWithSeparators(Iterable<?> iterable, String separator) {
        if (iterable != null) {
            String sep = Objects.toString(separator, "");
            Iterator<?> it = iterable.iterator();
            while (it.hasNext()) {
                this.append(it.next());
                if (!it.hasNext()) continue;
                this.append(sep);
            }
        }
        return this;
    }

    public TextStringBuilder appendWithSeparators(Iterator<?> it, String separator) {
        if (it != null) {
            String sep = Objects.toString(separator, "");
            while (it.hasNext()) {
                this.append(it.next());
                if (!it.hasNext()) continue;
                this.append(sep);
            }
        }
        return this;
    }

    public TextStringBuilder appendSeparator(String separator) {
        return this.appendSeparator(separator, null);
    }

    public TextStringBuilder appendSeparator(String standard, String defaultIfEmpty) {
        String str;
        String string = str = this.isEmpty() ? defaultIfEmpty : standard;
        if (str != null) {
            this.append(str);
        }
        return this;
    }

    public TextStringBuilder appendSeparator(char separator) {
        if (this.size() > 0) {
            this.append(separator);
        }
        return this;
    }

    public TextStringBuilder appendSeparator(char standard, char defaultIfEmpty) {
        if (this.size() > 0) {
            this.append(standard);
        } else {
            this.append(defaultIfEmpty);
        }
        return this;
    }

    public TextStringBuilder appendSeparator(String separator, int loopIndex) {
        if (separator != null && loopIndex > 0) {
            this.append(separator);
        }
        return this;
    }

    public TextStringBuilder appendSeparator(char separator, int loopIndex) {
        if (loopIndex > 0) {
            this.append(separator);
        }
        return this;
    }

    public TextStringBuilder appendPadding(int length, char padChar) {
        if (length >= 0) {
            this.ensureCapacity(this.size + length);
            for (int i = 0; i < length; ++i) {
                this.buffer[this.size++] = padChar;
            }
        }
        return this;
    }

    public TextStringBuilder appendFixedWidthPadLeft(Object obj, int width, char padChar) {
        if (width > 0) {
            int strLen;
            String str;
            this.ensureCapacity(this.size + width);
            String string = str = obj == null ? this.getNullText() : obj.toString();
            if (str == null) {
                str = "";
            }
            if ((strLen = str.length()) >= width) {
                str.getChars(strLen - width, strLen, this.buffer, this.size);
            } else {
                int padLen = width - strLen;
                for (int i = 0; i < padLen; ++i) {
                    this.buffer[this.size + i] = padChar;
                }
                str.getChars(0, strLen, this.buffer, this.size + padLen);
            }
            this.size += width;
        }
        return this;
    }

    public TextStringBuilder appendFixedWidthPadLeft(int value, int width, char padChar) {
        return this.appendFixedWidthPadLeft(String.valueOf(value), width, padChar);
    }

    public TextStringBuilder appendFixedWidthPadRight(Object obj, int width, char padChar) {
        if (width > 0) {
            int strLen;
            String str;
            this.ensureCapacity(this.size + width);
            String string = str = obj == null ? this.getNullText() : obj.toString();
            if (str == null) {
                str = "";
            }
            if ((strLen = str.length()) >= width) {
                str.getChars(0, width, this.buffer, this.size);
            } else {
                int padLen = width - strLen;
                str.getChars(0, strLen, this.buffer, this.size);
                for (int i = 0; i < padLen; ++i) {
                    this.buffer[this.size + strLen + i] = padChar;
                }
            }
            this.size += width;
        }
        return this;
    }

    public TextStringBuilder appendFixedWidthPadRight(int value, int width, char padChar) {
        return this.appendFixedWidthPadRight(String.valueOf(value), width, padChar);
    }

    public TextStringBuilder insert(int index, Object obj) {
        if (obj == null) {
            return this.insert(index, this.nullText);
        }
        return this.insert(index, obj.toString());
    }

    public TextStringBuilder insert(int index, String str) {
        int strLen;
        this.validateIndex(index);
        if (str == null) {
            str = this.nullText;
        }
        if (str != null && (strLen = str.length()) > 0) {
            int newSize = this.size + strLen;
            this.ensureCapacity(newSize);
            System.arraycopy(this.buffer, index, this.buffer, index + strLen, this.size - index);
            this.size = newSize;
            str.getChars(0, strLen, this.buffer, index);
        }
        return this;
    }

    public TextStringBuilder insert(int index, char[] chars) {
        this.validateIndex(index);
        if (chars == null) {
            return this.insert(index, this.nullText);
        }
        int len = chars.length;
        if (len > 0) {
            this.ensureCapacity(this.size + len);
            System.arraycopy(this.buffer, index, this.buffer, index + len, this.size - index);
            System.arraycopy(chars, 0, this.buffer, index, len);
            this.size += len;
        }
        return this;
    }

    public TextStringBuilder insert(int index, char[] chars, int offset, int length) {
        this.validateIndex(index);
        if (chars == null) {
            return this.insert(index, this.nullText);
        }
        if (offset < 0 || offset > chars.length) {
            throw new StringIndexOutOfBoundsException("Invalid offset: " + offset);
        }
        if (length < 0 || offset + length > chars.length) {
            throw new StringIndexOutOfBoundsException("Invalid length: " + length);
        }
        if (length > 0) {
            this.ensureCapacity(this.size + length);
            System.arraycopy(this.buffer, index, this.buffer, index + length, this.size - index);
            System.arraycopy(chars, offset, this.buffer, index, length);
            this.size += length;
        }
        return this;
    }

    public TextStringBuilder insert(int index, boolean value) {
        this.validateIndex(index);
        if (value) {
            this.ensureCapacity(this.size + TRUE_STRING_SIZE);
            System.arraycopy(this.buffer, index, this.buffer, index + TRUE_STRING_SIZE, this.size - index);
            this.buffer[index++] = 116;
            this.buffer[index++] = 114;
            this.buffer[index++] = 117;
            this.buffer[index] = 101;
            this.size += TRUE_STRING_SIZE;
        } else {
            this.ensureCapacity(this.size + FALSE_STRING_SIZE);
            System.arraycopy(this.buffer, index, this.buffer, index + FALSE_STRING_SIZE, this.size - index);
            this.buffer[index++] = 102;
            this.buffer[index++] = 97;
            this.buffer[index++] = 108;
            this.buffer[index++] = 115;
            this.buffer[index] = 101;
            this.size += FALSE_STRING_SIZE;
        }
        return this;
    }

    public TextStringBuilder insert(int index, char value) {
        this.validateIndex(index);
        this.ensureCapacity(this.size + 1);
        System.arraycopy(this.buffer, index, this.buffer, index + 1, this.size - index);
        this.buffer[index] = value;
        ++this.size;
        return this;
    }

    public TextStringBuilder insert(int index, int value) {
        return this.insert(index, String.valueOf(value));
    }

    public TextStringBuilder insert(int index, long value) {
        return this.insert(index, String.valueOf(value));
    }

    public TextStringBuilder insert(int index, float value) {
        return this.insert(index, String.valueOf(value));
    }

    public TextStringBuilder insert(int index, double value) {
        return this.insert(index, String.valueOf(value));
    }

    private void deleteImpl(int startIndex, int endIndex, int len) {
        System.arraycopy(this.buffer, endIndex, this.buffer, startIndex, this.size - endIndex);
        this.size -= len;
    }

    public TextStringBuilder delete(int startIndex, int endIndex) {
        int len = (endIndex = this.validateRange(startIndex, endIndex)) - startIndex;
        if (len > 0) {
            this.deleteImpl(startIndex, endIndex, len);
        }
        return this;
    }

    public TextStringBuilder deleteAll(char ch) {
        for (int i = 0; i < this.size; ++i) {
            if (this.buffer[i] != ch) continue;
            int start = i;
            while (++i < this.size && this.buffer[i] == ch) {
            }
            int len = i - start;
            this.deleteImpl(start, i, len);
            i -= len;
        }
        return this;
    }

    public TextStringBuilder deleteFirst(char ch) {
        for (int i = 0; i < this.size; ++i) {
            if (this.buffer[i] != ch) continue;
            this.deleteImpl(i, i + 1, 1);
            break;
        }
        return this;
    }

    public TextStringBuilder deleteAll(String str) {
        int len;
        int n = len = str == null ? 0 : str.length();
        if (len > 0) {
            int index = this.indexOf(str, 0);
            while (index >= 0) {
                this.deleteImpl(index, index + len, len);
                index = this.indexOf(str, index);
            }
        }
        return this;
    }

    public TextStringBuilder deleteFirst(String str) {
        int index;
        int len;
        int n = len = str == null ? 0 : str.length();
        if (len > 0 && (index = this.indexOf(str, 0)) >= 0) {
            this.deleteImpl(index, index + len, len);
        }
        return this;
    }

    public TextStringBuilder deleteAll(StringMatcher matcher) {
        return this.replace(matcher, null, 0, this.size, -1);
    }

    public TextStringBuilder deleteFirst(StringMatcher matcher) {
        return this.replace(matcher, null, 0, this.size, 1);
    }

    private void replaceImpl(int startIndex, int endIndex, int removeLen, String insertStr, int insertLen) {
        int newSize = this.size - removeLen + insertLen;
        if (insertLen != removeLen) {
            this.ensureCapacity(newSize);
            System.arraycopy(this.buffer, endIndex, this.buffer, startIndex + insertLen, this.size - endIndex);
            this.size = newSize;
        }
        if (insertLen > 0) {
            insertStr.getChars(0, insertLen, this.buffer, startIndex);
        }
    }

    public TextStringBuilder replace(int startIndex, int endIndex, String replaceStr) {
        endIndex = this.validateRange(startIndex, endIndex);
        int insertLen = replaceStr == null ? 0 : replaceStr.length();
        this.replaceImpl(startIndex, endIndex, endIndex - startIndex, replaceStr, insertLen);
        return this;
    }

    public TextStringBuilder replaceAll(char search, char replace) {
        if (search != replace) {
            for (int i = 0; i < this.size; ++i) {
                if (this.buffer[i] != search) continue;
                this.buffer[i] = replace;
            }
        }
        return this;
    }

    public TextStringBuilder replaceFirst(char search, char replace) {
        if (search != replace) {
            for (int i = 0; i < this.size; ++i) {
                if (this.buffer[i] != search) continue;
                this.buffer[i] = replace;
                break;
            }
        }
        return this;
    }

    public TextStringBuilder replaceAll(String searchStr, String replaceStr) {
        int searchLen;
        int n = searchLen = searchStr == null ? 0 : searchStr.length();
        if (searchLen > 0) {
            int replaceLen = replaceStr == null ? 0 : replaceStr.length();
            int index = this.indexOf(searchStr, 0);
            while (index >= 0) {
                this.replaceImpl(index, index + searchLen, searchLen, replaceStr, replaceLen);
                index = this.indexOf(searchStr, index + replaceLen);
            }
        }
        return this;
    }

    public TextStringBuilder replaceFirst(String searchStr, String replaceStr) {
        int index;
        int searchLen;
        int n = searchLen = searchStr == null ? 0 : searchStr.length();
        if (searchLen > 0 && (index = this.indexOf(searchStr, 0)) >= 0) {
            int replaceLen = replaceStr == null ? 0 : replaceStr.length();
            this.replaceImpl(index, index + searchLen, searchLen, replaceStr, replaceLen);
        }
        return this;
    }

    public TextStringBuilder replaceAll(StringMatcher matcher, String replaceStr) {
        return this.replace(matcher, replaceStr, 0, this.size, -1);
    }

    public TextStringBuilder replaceFirst(StringMatcher matcher, String replaceStr) {
        return this.replace(matcher, replaceStr, 0, this.size, 1);
    }

    public TextStringBuilder replace(StringMatcher matcher, String replaceStr, int startIndex, int endIndex, int replaceCount) {
        endIndex = this.validateRange(startIndex, endIndex);
        return this.replaceImpl(matcher, replaceStr, startIndex, endIndex, replaceCount);
    }

    private TextStringBuilder replaceImpl(StringMatcher matcher, String replaceStr, int from, int to, int replaceCount) {
        if (matcher == null || this.size == 0) {
            return this;
        }
        int replaceLen = replaceStr == null ? 0 : replaceStr.length();
        for (int i = from; i < to && replaceCount != 0; ++i) {
            char[] buf = this.buffer;
            int removeLen = matcher.isMatch(buf, i, from, to);
            if (removeLen <= 0) continue;
            this.replaceImpl(i, i + removeLen, removeLen, replaceStr, replaceLen);
            to = to - removeLen + replaceLen;
            i = i + replaceLen - 1;
            if (replaceCount <= 0) continue;
            --replaceCount;
        }
        return this;
    }

    public TextStringBuilder reverse() {
        if (this.size == 0) {
            return this;
        }
        int half = this.size / 2;
        char[] buf = this.buffer;
        int leftIdx = 0;
        int rightIdx = this.size - 1;
        while (leftIdx < half) {
            char swap = buf[leftIdx];
            buf[leftIdx] = buf[rightIdx];
            buf[rightIdx] = swap;
            ++leftIdx;
            --rightIdx;
        }
        return this;
    }

    public TextStringBuilder trim() {
        int pos;
        if (this.size == 0) {
            return this;
        }
        int len = this.size;
        char[] buf = this.buffer;
        for (pos = 0; pos < len && buf[pos] <= ' '; ++pos) {
        }
        while (pos < len && buf[len - 1] <= ' ') {
            --len;
        }
        if (len < this.size) {
            this.delete(len, this.size);
        }
        if (pos > 0) {
            this.delete(0, pos);
        }
        return this;
    }

    public boolean startsWith(String str) {
        if (str == null) {
            return false;
        }
        int len = str.length();
        if (len == 0) {
            return true;
        }
        if (len > this.size) {
            return false;
        }
        for (int i = 0; i < len; ++i) {
            if (this.buffer[i] == str.charAt(i)) continue;
            return false;
        }
        return true;
    }

    public boolean endsWith(String str) {
        if (str == null) {
            return false;
        }
        int len = str.length();
        if (len == 0) {
            return true;
        }
        if (len > this.size) {
            return false;
        }
        int pos = this.size - len;
        int i = 0;
        while (i < len) {
            if (this.buffer[pos] != str.charAt(i)) {
                return false;
            }
            ++i;
            ++pos;
        }
        return true;
    }

    @Override
    public CharSequence subSequence(int startIndex, int endIndex) {
        if (startIndex < 0) {
            throw new StringIndexOutOfBoundsException(startIndex);
        }
        if (endIndex > this.size) {
            throw new StringIndexOutOfBoundsException(endIndex);
        }
        if (startIndex > endIndex) {
            throw new StringIndexOutOfBoundsException(endIndex - startIndex);
        }
        return this.substring(startIndex, endIndex);
    }

    public String substring(int start) {
        return this.substring(start, this.size);
    }

    public String substring(int startIndex, int endIndex) {
        endIndex = this.validateRange(startIndex, endIndex);
        return new String(this.buffer, startIndex, endIndex - startIndex);
    }

    public String leftString(int length) {
        if (length <= 0) {
            return "";
        }
        if (length >= this.size) {
            return new String(this.buffer, 0, this.size);
        }
        return new String(this.buffer, 0, length);
    }

    public String rightString(int length) {
        if (length <= 0) {
            return "";
        }
        if (length >= this.size) {
            return new String(this.buffer, 0, this.size);
        }
        return new String(this.buffer, this.size - length, length);
    }

    public String midString(int index, int length) {
        if (index < 0) {
            index = 0;
        }
        if (length <= 0 || index >= this.size) {
            return "";
        }
        if (this.size <= index + length) {
            return new String(this.buffer, index, this.size - index);
        }
        return new String(this.buffer, index, length);
    }

    public boolean contains(char ch) {
        char[] thisBuf = this.buffer;
        for (int i = 0; i < this.size; ++i) {
            if (thisBuf[i] != ch) continue;
            return true;
        }
        return false;
    }

    public boolean contains(String str) {
        return this.indexOf(str, 0) >= 0;
    }

    public boolean contains(StringMatcher matcher) {
        return this.indexOf(matcher, 0) >= 0;
    }

    public int indexOf(char ch) {
        return this.indexOf(ch, 0);
    }

    public int indexOf(char ch, int startIndex) {
        int n = startIndex = startIndex < 0 ? 0 : startIndex;
        if (startIndex >= this.size) {
            return -1;
        }
        char[] thisBuf = this.buffer;
        for (int i = startIndex; i < this.size; ++i) {
            if (thisBuf[i] != ch) continue;
            return i;
        }
        return -1;
    }

    public int indexOf(String str) {
        return this.indexOf(str, 0);
    }

    public int indexOf(String str, int startIndex) {
        int n = startIndex = startIndex < 0 ? 0 : startIndex;
        if (str == null || startIndex >= this.size) {
            return -1;
        }
        int strLen = str.length();
        if (strLen == 1) {
            return this.indexOf(str.charAt(0), startIndex);
        }
        if (strLen == 0) {
            return startIndex;
        }
        if (strLen > this.size) {
            return -1;
        }
        char[] thisBuf = this.buffer;
        int len = this.size - strLen + 1;
        block0: for (int i = startIndex; i < len; ++i) {
            for (int j = 0; j < strLen; ++j) {
                if (str.charAt(j) != thisBuf[i + j]) continue block0;
            }
            return i;
        }
        return -1;
    }

    public int indexOf(StringMatcher matcher) {
        return this.indexOf(matcher, 0);
    }

    public int indexOf(StringMatcher matcher, int startIndex) {
        int n = startIndex = startIndex < 0 ? 0 : startIndex;
        if (matcher == null || startIndex >= this.size) {
            return -1;
        }
        int len = this.size;
        char[] buf = this.buffer;
        for (int i = startIndex; i < len; ++i) {
            if (matcher.isMatch(buf, i, startIndex, len) <= 0) continue;
            return i;
        }
        return -1;
    }

    public int lastIndexOf(char ch) {
        return this.lastIndexOf(ch, this.size - 1);
    }

    public int lastIndexOf(char ch, int startIndex) {
        int n = startIndex = startIndex >= this.size ? this.size - 1 : startIndex;
        if (startIndex < 0) {
            return -1;
        }
        for (int i = startIndex; i >= 0; --i) {
            if (this.buffer[i] != ch) continue;
            return i;
        }
        return -1;
    }

    public int lastIndexOf(String str) {
        return this.lastIndexOf(str, this.size - 1);
    }

    public int lastIndexOf(String str, int startIndex) {
        int n = startIndex = startIndex >= this.size ? this.size - 1 : startIndex;
        if (str == null || startIndex < 0) {
            return -1;
        }
        int strLen = str.length();
        if (strLen > 0 && strLen <= this.size) {
            if (strLen == 1) {
                return this.lastIndexOf(str.charAt(0), startIndex);
            }
            block0: for (int i = startIndex - strLen + 1; i >= 0; --i) {
                for (int j = 0; j < strLen; ++j) {
                    if (str.charAt(j) != this.buffer[i + j]) continue block0;
                }
                return i;
            }
        } else if (strLen == 0) {
            return startIndex;
        }
        return -1;
    }

    public int lastIndexOf(StringMatcher matcher) {
        return this.lastIndexOf(matcher, this.size);
    }

    public int lastIndexOf(StringMatcher matcher, int startIndex) {
        int n = startIndex = startIndex >= this.size ? this.size - 1 : startIndex;
        if (matcher == null || startIndex < 0) {
            return -1;
        }
        char[] buf = this.buffer;
        int endIndex = startIndex + 1;
        for (int i = startIndex; i >= 0; --i) {
            if (matcher.isMatch(buf, i, 0, endIndex) <= 0) continue;
            return i;
        }
        return -1;
    }

    public StringTokenizer asTokenizer() {
        return new TextStringBuilderTokenizer();
    }

    public Reader asReader() {
        return new StrBuilderReader();
    }

    public Writer asWriter() {
        return new StrBuilderWriter();
    }

    public void appendTo(Appendable appendable) throws IOException {
        if (appendable instanceof Writer) {
            ((Writer)appendable).write(this.buffer, 0, this.size);
        } else if (appendable instanceof StringBuilder) {
            ((StringBuilder)appendable).append(this.buffer, 0, this.size);
        } else if (appendable instanceof StringBuffer) {
            ((StringBuffer)appendable).append(this.buffer, 0, this.size);
        } else if (appendable instanceof CharBuffer) {
            ((CharBuffer)appendable).put(this.buffer, 0, this.size);
        } else {
            appendable.append(this);
        }
    }

    public boolean equalsIgnoreCase(TextStringBuilder other) {
        if (this == other) {
            return true;
        }
        if (this.size != other.size) {
            return false;
        }
        char[] thisBuf = this.buffer;
        char[] otherBuf = other.buffer;
        for (int i = this.size - 1; i >= 0; --i) {
            char c1 = thisBuf[i];
            char c2 = otherBuf[i];
            if (c1 == c2 || Character.toUpperCase(c1) == Character.toUpperCase(c2)) continue;
            return false;
        }
        return true;
    }

    public boolean equals(TextStringBuilder other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (this.size != other.size) {
            return false;
        }
        char[] thisBuf = this.buffer;
        char[] otherBuf = other.buffer;
        for (int i = this.size - 1; i >= 0; --i) {
            if (thisBuf[i] == otherBuf[i]) continue;
            return false;
        }
        return true;
    }

    public boolean equals(Object obj) {
        return obj instanceof TextStringBuilder && this.equals((TextStringBuilder)obj);
    }

    public int hashCode() {
        char[] buf = this.buffer;
        int hash = 0;
        for (int i = this.size - 1; i >= 0; --i) {
            hash = 31 * hash + buf[i];
        }
        return hash;
    }

    @Override
    public String toString() {
        return new String(this.buffer, 0, this.size);
    }

    public StringBuffer toStringBuffer() {
        return new StringBuffer(this.size).append(this.buffer, 0, this.size);
    }

    public StringBuilder toStringBuilder() {
        return new StringBuilder(this.size).append(this.buffer, 0, this.size);
    }

    @Override
    public String build() {
        return this.toString();
    }

    protected int validateRange(int startIndex, int endIndex) {
        if (startIndex < 0) {
            throw new StringIndexOutOfBoundsException(startIndex);
        }
        if (endIndex > this.size) {
            endIndex = this.size;
        }
        if (startIndex > endIndex) {
            throw new StringIndexOutOfBoundsException("end < start");
        }
        return endIndex;
    }

    protected void validateIndex(int index) {
        if (index < 0 || index > this.size) {
            throw new StringIndexOutOfBoundsException(index);
        }
    }

    class StrBuilderWriter
    extends Writer {
        StrBuilderWriter() {
        }

        @Override
        public void close() {
        }

        @Override
        public void flush() {
        }

        @Override
        public void write(int c) {
            TextStringBuilder.this.append((char)c);
        }

        @Override
        public void write(char[] cbuf) {
            TextStringBuilder.this.append(cbuf);
        }

        @Override
        public void write(char[] cbuf, int off, int len) {
            TextStringBuilder.this.append(cbuf, off, len);
        }

        @Override
        public void write(String str) {
            TextStringBuilder.this.append(str);
        }

        @Override
        public void write(String str, int off, int len) {
            TextStringBuilder.this.append(str, off, len);
        }
    }

    class StrBuilderReader
    extends Reader {
        private int pos;
        private int mark;

        StrBuilderReader() {
        }

        @Override
        public void close() {
        }

        @Override
        public int read() {
            if (!this.ready()) {
                return -1;
            }
            return TextStringBuilder.this.charAt(this.pos++);
        }

        @Override
        public int read(char[] b, int off, int len) {
            if (off < 0 || len < 0 || off > b.length || off + len > b.length || off + len < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (len == 0) {
                return 0;
            }
            if (this.pos >= TextStringBuilder.this.size()) {
                return -1;
            }
            if (this.pos + len > TextStringBuilder.this.size()) {
                len = TextStringBuilder.this.size() - this.pos;
            }
            TextStringBuilder.this.getChars(this.pos, this.pos + len, b, off);
            this.pos += len;
            return len;
        }

        @Override
        public long skip(long n) {
            if ((long)this.pos + n > (long)TextStringBuilder.this.size()) {
                n = TextStringBuilder.this.size() - this.pos;
            }
            if (n < 0L) {
                return 0L;
            }
            this.pos = (int)((long)this.pos + n);
            return n;
        }

        @Override
        public boolean ready() {
            return this.pos < TextStringBuilder.this.size();
        }

        @Override
        public boolean markSupported() {
            return true;
        }

        @Override
        public void mark(int readAheadLimit) {
            this.mark = this.pos;
        }

        @Override
        public void reset() {
            this.pos = this.mark;
        }
    }

    class TextStringBuilderTokenizer
    extends StringTokenizer {
        TextStringBuilderTokenizer() {
        }

        @Override
        protected List<String> tokenize(char[] chars, int offset, int count) {
            if (chars == null) {
                return super.tokenize(TextStringBuilder.this.buffer, 0, TextStringBuilder.this.size());
            }
            return super.tokenize(chars, offset, count);
        }

        @Override
        public String getContent() {
            String str = super.getContent();
            if (str == null) {
                return TextStringBuilder.this.toString();
            }
            return str;
        }
    }
}

