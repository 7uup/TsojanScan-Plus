/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SingleFieldBuilder<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder>
implements GeneratedMessage.BuilderParent {
    private GeneratedMessage.BuilderParent parent;
    private BType builder;
    private MType message;
    private boolean isClean;

    public SingleFieldBuilder(MType message, GeneratedMessage.BuilderParent parent, boolean isClean) {
        if (message == null) {
            throw new NullPointerException();
        }
        this.message = message;
        this.parent = parent;
        this.isClean = isClean;
    }

    public void dispose() {
        this.parent = null;
    }

    public MType getMessage() {
        if (this.message == null) {
            this.message = (GeneratedMessage)this.builder.buildPartial();
        }
        return this.message;
    }

    public MType build() {
        this.isClean = true;
        return this.getMessage();
    }

    public BType getBuilder() {
        if (this.builder == null) {
            this.builder = (GeneratedMessage.Builder)((GeneratedMessage)this.message).newBuilderForType(this);
            ((AbstractMessage.Builder)this.builder).mergeFrom((Message)this.message);
            ((GeneratedMessage.Builder)this.builder).markClean();
        }
        return this.builder;
    }

    public IType getMessageOrBuilder() {
        if (this.builder != null) {
            return (IType)this.builder;
        }
        return (IType)this.message;
    }

    public SingleFieldBuilder<MType, BType, IType> setMessage(MType message) {
        if (message == null) {
            throw new NullPointerException();
        }
        this.message = message;
        if (this.builder != null) {
            ((GeneratedMessage.Builder)this.builder).dispose();
            this.builder = null;
        }
        this.onChanged();
        return this;
    }

    public SingleFieldBuilder<MType, BType, IType> mergeFrom(MType value) {
        if (this.builder == null && this.message == this.message.getDefaultInstanceForType()) {
            this.message = value;
        } else {
            ((AbstractMessage.Builder)this.getBuilder()).mergeFrom((Message)value);
        }
        this.onChanged();
        return this;
    }

    public SingleFieldBuilder<MType, BType, IType> clear() {
        this.message = (GeneratedMessage)(this.message != null ? this.message.getDefaultInstanceForType() : this.builder.getDefaultInstanceForType());
        if (this.builder != null) {
            ((GeneratedMessage.Builder)this.builder).dispose();
            this.builder = null;
        }
        this.onChanged();
        return this;
    }

    private void onChanged() {
        if (this.builder != null) {
            this.message = null;
        }
        if (this.isClean && this.parent != null) {
            this.parent.markDirty();
            this.isClean = false;
        }
    }

    @Override
    public void markDirty() {
        this.onChanged();
    }
}

