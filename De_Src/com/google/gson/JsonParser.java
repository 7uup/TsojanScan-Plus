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
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public final class JsonParser {
    @Deprecated
    public JsonParser() {
    }

    public static JsonElement parseString(String json) throws JsonSyntaxException {
        return JsonParser.parseReader(new StringReader(json));
    }

    public static JsonElement parseReader(Reader reader) throws JsonIOException, JsonSyntaxException {
        try {
            JsonReader jsonReader = new JsonReader(reader);
            JsonElement element = JsonParser.parseReader(jsonReader);
            if (!element.isJsonNull() && jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw new JsonSyntaxException("Did not consume the entire document.");
            }
            return element;
        } catch (MalformedJsonException e) {
            throw new JsonSyntaxException(e);
        } catch (IOException e) {
            throw new JsonIOException(e);
        } catch (NumberFormatException e) {
            throw new JsonSyntaxException(e);
        }
    }

    public static JsonElement parseReader(JsonReader reader) throws JsonIOException, JsonSyntaxException {
        boolean lenient = reader.isLenient();
        reader.setLenient(true);
        try {
            JsonElement jsonElement = Streams.parse(reader);
            return jsonElement;
        } catch (StackOverflowError e) {
            throw new JsonParseException("Failed parsing JSON source: " + reader + " to Json", e);
        } catch (OutOfMemoryError e) {
            throw new JsonParseException("Failed parsing JSON source: " + reader + " to Json", e);
        } finally {
            reader.setLenient(lenient);
        }
    }

    @Deprecated
    public JsonElement parse(String json) throws JsonSyntaxException {
        return JsonParser.parseString(json);
    }

    @Deprecated
    public JsonElement parse(Reader json) throws JsonIOException, JsonSyntaxException {
        return JsonParser.parseReader(json);
    }

    @Deprecated
    public JsonElement parse(JsonReader json) throws JsonIOException, JsonSyntaxException {
        return JsonParser.parseReader(json);
    }
}

