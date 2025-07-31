/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class JsonStreamParser
implements Iterator<JsonElement> {
    private final JsonReader parser;
    private final Object lock;

    public JsonStreamParser(String json) {
        this(new StringReader(json));
    }

    public JsonStreamParser(Reader reader) {
        this.parser = new JsonReader(reader);
        this.parser.setLenient(true);
        this.lock = new Object();
    }

    @Override
    public JsonElement next() throws JsonParseException {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        try {
            return Streams.parse(this.parser);
        } catch (StackOverflowError e) {
            throw new JsonParseException("Failed parsing JSON source to Json", e);
        } catch (OutOfMemoryError e) {
            throw new JsonParseException("Failed parsing JSON source to Json", e);
        } catch (JsonParseException e) {
            throw e.getCause() instanceof EOFException ? new NoSuchElementException() : e;
        }
    }

    @Override
    public boolean hasNext() {
        Object object = this.lock;
        synchronized (object) {
            try {
                return this.parser.peek() != JsonToken.END_DOCUMENT;
            } catch (MalformedJsonException e) {
                throw new JsonSyntaxException(e);
            } catch (IOException e) {
                throw new JsonIOException(e);
            }
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}

