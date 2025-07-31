/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class JsonElement {
    public abstract JsonElement deepCopy();

    public boolean isJsonArray() {
        return this instanceof JsonArray;
    }

    public boolean isJsonObject() {
        return this instanceof JsonObject;
    }

    public boolean isJsonPrimitive() {
        return this instanceof JsonPrimitive;
    }

    public boolean isJsonNull() {
        return this instanceof JsonNull;
    }

    public JsonObject getAsJsonObject() {
        if (this.isJsonObject()) {
            return (JsonObject)this;
        }
        throw new IllegalStateException("Not a JSON Object: " + this);
    }

    public JsonArray getAsJsonArray() {
        if (this.isJsonArray()) {
            return (JsonArray)this;
        }
        throw new IllegalStateException("Not a JSON Array: " + this);
    }

    public JsonPrimitive getAsJsonPrimitive() {
        if (this.isJsonPrimitive()) {
            return (JsonPrimitive)this;
        }
        throw new IllegalStateException("Not a JSON Primitive: " + this);
    }

    public JsonNull getAsJsonNull() {
        if (this.isJsonNull()) {
            return (JsonNull)this;
        }
        throw new IllegalStateException("Not a JSON Null: " + this);
    }

    public boolean getAsBoolean() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public Number getAsNumber() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public String getAsString() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public double getAsDouble() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public float getAsFloat() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public long getAsLong() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public int getAsInt() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public byte getAsByte() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    @Deprecated
    public char getAsCharacter() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public BigDecimal getAsBigDecimal() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public BigInteger getAsBigInteger() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public short getAsShort() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            JsonWriter jsonWriter = new JsonWriter(stringWriter);
            jsonWriter.setLenient(true);
            Streams.write(this, jsonWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError((Object)e);
        }
    }
}

