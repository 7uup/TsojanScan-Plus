/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONStreamContext;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

public class JSONWriter
implements Closeable,
Flushable {
    private SerializeWriter writer;
    private JSONSerializer serializer;
    private JSONStreamContext context;

    public JSONWriter(Writer out) {
        this.writer = new SerializeWriter(out);
        this.serializer = new JSONSerializer(this.writer);
    }

    public void config(SerializerFeature feature, boolean state) {
        this.writer.config(feature, state);
    }

    public void startObject() {
        if (this.context != null) {
            this.beginStructure();
        }
        this.context = new JSONStreamContext(this.context, 1001);
        this.writer.write(123);
    }

    public void endObject() {
        this.writer.write(125);
        this.endStructure();
    }

    public void writeKey(String key) {
        this.writeObject(key);
    }

    public void writeValue(Object object) {
        this.writeObject(object);
    }

    public void writeObject(String object) {
        this.beforeWrite();
        this.serializer.write(object);
        this.afterWrite();
    }

    public void writeObject(Object object) {
        this.beforeWrite();
        this.serializer.write(object);
        this.afterWrite();
    }

    public void startArray() {
        if (this.context != null) {
            this.beginStructure();
        }
        this.context = new JSONStreamContext(this.context, 1004);
        this.writer.write(91);
    }

    private void beginStructure() {
        int state = this.context.state;
        switch (this.context.state) {
            case 1002: {
                this.writer.write(58);
                break;
            }
            case 1005: {
                this.writer.write(44);
                break;
            }
            case 1001: {
                break;
            }
            case 1004: {
                break;
            }
            default: {
                throw new JSONException("illegal state : " + state);
            }
        }
    }

    public void endArray() {
        this.writer.write(93);
        this.endStructure();
    }

    private void endStructure() {
        this.context = this.context.parent;
        if (this.context == null) {
            return;
        }
        int newState = -1;
        switch (this.context.state) {
            case 1002: {
                newState = 1003;
                break;
            }
            case 1004: {
                newState = 1005;
                break;
            }
            case 1005: {
                break;
            }
            case 1001: {
                newState = 1002;
                break;
            }
        }
        if (newState != -1) {
            this.context.state = newState;
        }
    }

    private void beforeWrite() {
        if (this.context == null) {
            return;
        }
        switch (this.context.state) {
            case 1001: 
            case 1004: {
                break;
            }
            case 1002: {
                this.writer.write(58);
                break;
            }
            case 1003: {
                this.writer.write(44);
                break;
            }
            case 1005: {
                this.writer.write(44);
                break;
            }
        }
    }

    private void afterWrite() {
        if (this.context == null) {
            return;
        }
        int state = this.context.state;
        int newState = -1;
        switch (state) {
            case 1002: {
                newState = 1003;
                break;
            }
            case 1001: 
            case 1003: {
                newState = 1002;
                break;
            }
            case 1004: {
                newState = 1005;
                break;
            }
            case 1005: {
                break;
            }
        }
        if (newState != -1) {
            this.context.state = newState;
        }
    }

    public void flush() throws IOException {
        this.writer.flush();
    }

    public void close() throws IOException {
        this.writer.close();
    }

    @Deprecated
    public void writeStartObject() {
        this.startObject();
    }

    @Deprecated
    public void writeEndObject() {
        this.endObject();
    }

    @Deprecated
    public void writeStartArray() {
        this.startArray();
    }

    @Deprecated
    public void writeEndArray() {
        this.endArray();
    }
}

