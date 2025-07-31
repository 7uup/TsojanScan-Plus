/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public abstract class TypeAdapter<T> {
    public abstract void write(JsonWriter var1, T var2) throws IOException;

    public final void toJson(Writer out, T value) throws IOException {
        JsonWriter writer = new JsonWriter(out);
        this.write(writer, value);
    }

    public final TypeAdapter<T> nullSafe() {
        return new TypeAdapter<T>(){

            @Override
            public void write(JsonWriter out, T value) throws IOException {
                if (value == null) {
                    out.nullValue();
                } else {
                    TypeAdapter.this.write(out, value);
                }
            }

            @Override
            public T read(JsonReader reader) throws IOException {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return null;
                }
                return TypeAdapter.this.read(reader);
            }
        };
    }

    public final String toJson(T value) {
        StringWriter stringWriter = new StringWriter();
        try {
            this.toJson(stringWriter, value);
        } catch (IOException e) {
            throw new AssertionError((Object)e);
        }
        return stringWriter.toString();
    }

    public final JsonElement toJsonTree(T value) {
        try {
            JsonTreeWriter jsonWriter = new JsonTreeWriter();
            this.write(jsonWriter, value);
            return jsonWriter.get();
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }

    public abstract T read(JsonReader var1) throws IOException;

    public final T fromJson(Reader in) throws IOException {
        JsonReader reader = new JsonReader(in);
        return this.read(reader);
    }

    public final T fromJson(String json) throws IOException {
        return this.fromJson(new StringReader(json));
    }

    public final T fromJsonTree(JsonElement jsonTree) {
        try {
            JsonTreeReader jsonReader = new JsonTreeReader(jsonTree);
            return this.read(jsonReader);
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }
}

