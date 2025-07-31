/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.gson.stream;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

public class JsonWriter
implements Closeable,
Flushable {
    private static final String[] REPLACEMENT_CHARS = new String[128];
    private static final String[] HTML_SAFE_REPLACEMENT_CHARS;
    private final Writer out;
    private int[] stack = new int[32];
    private int stackSize = 0;
    private String indent;
    private String separator;
    private boolean lenient;
    private boolean htmlSafe;
    private String deferredName;
    private boolean serializeNulls;

    public JsonWriter(Writer out) {
        this.push(6);
        this.separator = ":";
        this.serializeNulls = true;
        if (out == null) {
            throw new NullPointerException("out == null");
        }
        this.out = out;
    }

    public final void setIndent(String indent) {
        if (indent.length() == 0) {
            this.indent = null;
            this.separator = ":";
        } else {
            this.indent = indent;
            this.separator = ": ";
        }
    }

    public final void setLenient(boolean lenient) {
        this.lenient = lenient;
    }

    public boolean isLenient() {
        return this.lenient;
    }

    public final void setHtmlSafe(boolean htmlSafe) {
        this.htmlSafe = htmlSafe;
    }

    public final boolean isHtmlSafe() {
        return this.htmlSafe;
    }

    public final void setSerializeNulls(boolean serializeNulls) {
        this.serializeNulls = serializeNulls;
    }

    public final boolean getSerializeNulls() {
        return this.serializeNulls;
    }

    public JsonWriter beginArray() throws IOException {
        this.writeDeferredName();
        return this.open(1, '[');
    }

    public JsonWriter endArray() throws IOException {
        return this.close(1, 2, ']');
    }

    public JsonWriter beginObject() throws IOException {
        this.writeDeferredName();
        return this.open(3, '{');
    }

    public JsonWriter endObject() throws IOException {
        return this.close(3, 5, '}');
    }

    private JsonWriter open(int empty, char openBracket) throws IOException {
        this.beforeValue();
        this.push(empty);
        this.out.write(openBracket);
        return this;
    }

    private JsonWriter close(int empty, int nonempty, char closeBracket) throws IOException {
        int context = this.peek();
        if (context != nonempty && context != empty) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.deferredName != null) {
            throw new IllegalStateException("Dangling name: " + this.deferredName);
        }
        --this.stackSize;
        if (context == nonempty) {
            this.newline();
        }
        this.out.write(closeBracket);
        return this;
    }

    private void push(int newTop) {
        if (this.stackSize == this.stack.length) {
            this.stack = Arrays.copyOf(this.stack, this.stackSize * 2);
        }
        this.stack[this.stackSize++] = newTop;
    }

    private int peek() {
        if (this.stackSize == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        return this.stack[this.stackSize - 1];
    }

    private void replaceTop(int topOfStack) {
        this.stack[this.stackSize - 1] = topOfStack;
    }

    public JsonWriter name(String name) throws IOException {
        if (name == null) {
            throw new NullPointerException("name == null");
        }
        if (this.deferredName != null) {
            throw new IllegalStateException();
        }
        if (this.stackSize == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.deferredName = name;
        return this;
    }

    private void writeDeferredName() throws IOException {
        if (this.deferredName != null) {
            this.beforeName();
            this.string(this.deferredName);
            this.deferredName = null;
        }
    }

    public JsonWriter value(String value) throws IOException {
        if (value == null) {
            return this.nullValue();
        }
        this.writeDeferredName();
        this.beforeValue();
        this.string(value);
        return this;
    }

    public JsonWriter jsonValue(String value) throws IOException {
        if (value == null) {
            return this.nullValue();
        }
        this.writeDeferredName();
        this.beforeValue();
        this.out.append(value);
        return this;
    }

    public JsonWriter nullValue() throws IOException {
        if (this.deferredName != null) {
            if (this.serializeNulls) {
                this.writeDeferredName();
            } else {
                this.deferredName = null;
                return this;
            }
        }
        this.beforeValue();
        this.out.write("null");
        return this;
    }

    public JsonWriter value(boolean value) throws IOException {
        this.writeDeferredName();
        this.beforeValue();
        this.out.write(value ? "true" : "false");
        return this;
    }

    public JsonWriter value(Boolean value) throws IOException {
        if (value == null) {
            return this.nullValue();
        }
        this.writeDeferredName();
        this.beforeValue();
        this.out.write(value != false ? "true" : "false");
        return this;
    }

    public JsonWriter value(double value) throws IOException {
        this.writeDeferredName();
        if (!this.lenient && (Double.isNaN(value) || Double.isInfinite(value))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + value);
        }
        this.beforeValue();
        this.out.append(Double.toString(value));
        return this;
    }

    public JsonWriter value(long value) throws IOException {
        this.writeDeferredName();
        this.beforeValue();
        this.out.write(Long.toString(value));
        return this;
    }

    public JsonWriter value(Number value) throws IOException {
        if (value == null) {
            return this.nullValue();
        }
        this.writeDeferredName();
        String string = value.toString();
        if (!this.lenient && (string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + value);
        }
        this.beforeValue();
        this.out.append(string);
        return this;
    }

    @Override
    public void flush() throws IOException {
        if (this.stackSize == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.out.flush();
    }

    @Override
    public void close() throws IOException {
        this.out.close();
        int size = this.stackSize;
        if (size > 1 || size == 1 && this.stack[size - 1] != 7) {
            throw new IOException("Incomplete document");
        }
        this.stackSize = 0;
    }

    private void string(String value) throws IOException {
        String[] replacements = this.htmlSafe ? HTML_SAFE_REPLACEMENT_CHARS : REPLACEMENT_CHARS;
        this.out.write(34);
        int last = 0;
        int length = value.length();
        for (int i = 0; i < length; ++i) {
            String replacement;
            char c = value.charAt(i);
            if (c < '\u0080') {
                replacement = replacements[c];
                if (replacement == null) {
                    continue;
                }
            } else if (c == '\u2028') {
                replacement = "\\u2028";
            } else {
                if (c != '\u2029') continue;
                replacement = "\\u2029";
            }
            if (last < i) {
                this.out.write(value, last, i - last);
            }
            this.out.write(replacement);
            last = i + 1;
        }
        if (last < length) {
            this.out.write(value, last, length - last);
        }
        this.out.write(34);
    }

    private void newline() throws IOException {
        if (this.indent == null) {
            return;
        }
        this.out.write(10);
        int size = this.stackSize;
        for (int i = 1; i < size; ++i) {
            this.out.write(this.indent);
        }
    }

    private void beforeName() throws IOException {
        int context = this.peek();
        if (context == 5) {
            this.out.write(44);
        } else if (context != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        this.newline();
        this.replaceTop(4);
    }

    private void beforeValue() throws IOException {
        switch (this.peek()) {
            case 7: {
                if (!this.lenient) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            case 6: {
                this.replaceTop(7);
                break;
            }
            case 1: {
                this.replaceTop(2);
                this.newline();
                break;
            }
            case 2: {
                this.out.append(',');
                this.newline();
                break;
            }
            case 4: {
                this.out.append(this.separator);
                this.replaceTop(5);
                break;
            }
            default: {
                throw new IllegalStateException("Nesting problem.");
            }
        }
    }

    static {
        for (int i = 0; i <= 31; ++i) {
            JsonWriter.REPLACEMENT_CHARS[i] = String.format("\\u%04x", i);
        }
        JsonWriter.REPLACEMENT_CHARS[34] = "\\\"";
        JsonWriter.REPLACEMENT_CHARS[92] = "\\\\";
        JsonWriter.REPLACEMENT_CHARS[9] = "\\t";
        JsonWriter.REPLACEMENT_CHARS[8] = "\\b";
        JsonWriter.REPLACEMENT_CHARS[10] = "\\n";
        JsonWriter.REPLACEMENT_CHARS[13] = "\\r";
        JsonWriter.REPLACEMENT_CHARS[12] = "\\f";
        HTML_SAFE_REPLACEMENT_CHARS = (String[])REPLACEMENT_CHARS.clone();
        JsonWriter.HTML_SAFE_REPLACEMENT_CHARS[60] = "\\u003c";
        JsonWriter.HTML_SAFE_REPLACEMENT_CHARS[62] = "\\u003e";
        JsonWriter.HTML_SAFE_REPLACEMENT_CHARS[38] = "\\u0026";
        JsonWriter.HTML_SAFE_REPLACEMENT_CHARS[61] = "\\u003d";
        JsonWriter.HTML_SAFE_REPLACEMENT_CHARS[39] = "\\u0027";
    }
}

