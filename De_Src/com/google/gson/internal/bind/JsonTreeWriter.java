/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.gson.internal.bind;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public final class JsonTreeWriter
extends JsonWriter {
    private static final Writer UNWRITABLE_WRITER = new Writer(){

        @Override
        public void write(char[] buffer, int offset, int counter) {
            throw new AssertionError();
        }

        @Override
        public void flush() throws IOException {
            throw new AssertionError();
        }

        @Override
        public void close() throws IOException {
            throw new AssertionError();
        }
    };
    private static final JsonPrimitive SENTINEL_CLOSED = new JsonPrimitive("closed");
    private final List<JsonElement> stack = new ArrayList<JsonElement>();
    private String pendingName;
    private JsonElement product = JsonNull.INSTANCE;

    public JsonTreeWriter() {
        super(UNWRITABLE_WRITER);
    }

    public JsonElement get() {
        if (!this.stack.isEmpty()) {
            throw new IllegalStateException("Expected one JSON element but was " + this.stack);
        }
        return this.product;
    }

    private JsonElement peek() {
        return this.stack.get(this.stack.size() - 1);
    }

    private void put(JsonElement value) {
        if (this.pendingName != null) {
            if (!value.isJsonNull() || this.getSerializeNulls()) {
                JsonObject object = (JsonObject)this.peek();
                object.add(this.pendingName, value);
            }
            this.pendingName = null;
        } else if (this.stack.isEmpty()) {
            this.product = value;
        } else {
            JsonElement element = this.peek();
            if (element instanceof JsonArray) {
                ((JsonArray)element).add(value);
            } else {
                throw new IllegalStateException();
            }
        }
    }

    @Override
    public JsonWriter beginArray() throws IOException {
        JsonArray array = new JsonArray();
        this.put(array);
        this.stack.add(array);
        return this;
    }

    @Override
    public JsonWriter endArray() throws IOException {
        if (this.stack.isEmpty() || this.pendingName != null) {
            throw new IllegalStateException();
        }
        JsonElement element = this.peek();
        if (element instanceof JsonArray) {
            this.stack.remove(this.stack.size() - 1);
            return this;
        }
        throw new IllegalStateException();
    }

    @Override
    public JsonWriter beginObject() throws IOException {
        JsonObject object = new JsonObject();
        this.put(object);
        this.stack.add(object);
        return this;
    }

    @Override
    public JsonWriter endObject() throws IOException {
        if (this.stack.isEmpty() || this.pendingName != null) {
            throw new IllegalStateException();
        }
        JsonElement element = this.peek();
        if (element instanceof JsonObject) {
            this.stack.remove(this.stack.size() - 1);
            return this;
        }
        throw new IllegalStateException();
    }

    @Override
    public JsonWriter name(String name) throws IOException {
        if (this.stack.isEmpty() || this.pendingName != null) {
            throw new IllegalStateException();
        }
        JsonElement element = this.peek();
        if (element instanceof JsonObject) {
            this.pendingName = name;
            return this;
        }
        throw new IllegalStateException();
    }

    @Override
    public JsonWriter value(String value) throws IOException {
        if (value == null) {
            return this.nullValue();
        }
        this.put(new JsonPrimitive(value));
        return this;
    }

    @Override
    public JsonWriter nullValue() throws IOException {
        this.put(JsonNull.INSTANCE);
        return this;
    }

    @Override
    public JsonWriter value(boolean value) throws IOException {
        this.put(new JsonPrimitive(value));
        return this;
    }

    @Override
    public JsonWriter value(Boolean value) throws IOException {
        if (value == null) {
            return this.nullValue();
        }
        this.put(new JsonPrimitive(value));
        return this;
    }

    @Override
    public JsonWriter value(double value) throws IOException {
        if (!this.isLenient() && (Double.isNaN(value) || Double.isInfinite(value))) {
            throw new IllegalArgumentException("JSON forbids NaN and infinities: " + value);
        }
        this.put(new JsonPrimitive(value));
        return this;
    }

    @Override
    public JsonWriter value(long value) throws IOException {
        this.put(new JsonPrimitive(value));
        return this;
    }

    @Override
    public JsonWriter value(Number value) throws IOException {
        double d;
        if (value == null) {
            return this.nullValue();
        }
        if (!this.isLenient() && (Double.isNaN(d = value.doubleValue()) || Double.isInfinite(d))) {
            throw new IllegalArgumentException("JSON forbids NaN and infinities: " + value);
        }
        this.put(new JsonPrimitive(value));
        return this;
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public void close() throws IOException {
        if (!this.stack.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        this.stack.add(SENTINEL_CLOSED);
    }
}

