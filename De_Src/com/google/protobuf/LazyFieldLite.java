/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.MessageLite;
import java.io.IOException;

public class LazyFieldLite {
    private ByteString bytes;
    private ExtensionRegistryLite extensionRegistry;
    private volatile boolean isDirty = false;
    protected volatile MessageLite value;

    public LazyFieldLite(ExtensionRegistryLite extensionRegistry, ByteString bytes) {
        this.extensionRegistry = extensionRegistry;
        this.bytes = bytes;
    }

    public LazyFieldLite() {
    }

    public static LazyFieldLite fromValue(MessageLite value) {
        LazyFieldLite lf = new LazyFieldLite();
        lf.setValue(value);
        return lf;
    }

    public boolean containsDefaultInstance() {
        return this.value == null && this.bytes == null;
    }

    public void clear() {
        this.bytes = null;
        this.value = null;
        this.extensionRegistry = null;
        this.isDirty = true;
    }

    public MessageLite getValue(MessageLite defaultInstance) {
        this.ensureInitialized(defaultInstance);
        return this.value;
    }

    public MessageLite setValue(MessageLite value) {
        MessageLite originalValue = this.value;
        this.value = value;
        this.bytes = null;
        this.isDirty = true;
        return originalValue;
    }

    public void merge(LazyFieldLite value) {
        if (value.containsDefaultInstance()) {
            return;
        }
        if (this.bytes == null) {
            this.bytes = value.bytes;
        } else {
            this.bytes.concat(value.toByteString());
        }
        this.isDirty = false;
    }

    public void setByteString(ByteString bytes, ExtensionRegistryLite extensionRegistry) {
        this.bytes = bytes;
        this.extensionRegistry = extensionRegistry;
        this.isDirty = false;
    }

    public ExtensionRegistryLite getExtensionRegistry() {
        return this.extensionRegistry;
    }

    public int getSerializedSize() {
        if (this.isDirty) {
            return this.value.getSerializedSize();
        }
        return this.bytes.size();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ByteString toByteString() {
        if (!this.isDirty) {
            return this.bytes;
        }
        LazyFieldLite lazyFieldLite = this;
        synchronized (lazyFieldLite) {
            if (!this.isDirty) {
                return this.bytes;
            }
            this.bytes = this.value == null ? ByteString.EMPTY : this.value.toByteString();
            this.isDirty = false;
            return this.bytes;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void ensureInitialized(MessageLite defaultInstance) {
        if (this.value != null) {
            return;
        }
        LazyFieldLite lazyFieldLite = this;
        synchronized (lazyFieldLite) {
            if (this.value != null) {
                return;
            }
            try {
                this.value = this.bytes != null ? defaultInstance.getParserForType().parseFrom(this.bytes, this.extensionRegistry) : defaultInstance;
            } catch (IOException iOException) {
                // empty catch block
            }
        }
    }
}

