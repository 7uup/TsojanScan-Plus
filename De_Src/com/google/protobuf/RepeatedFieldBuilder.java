/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.SingleFieldBuilder;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class RepeatedFieldBuilder<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder>
implements GeneratedMessage.BuilderParent {
    private GeneratedMessage.BuilderParent parent;
    private List<MType> messages;
    private boolean isMessagesListMutable;
    private List<SingleFieldBuilder<MType, BType, IType>> builders;
    private boolean isClean;
    private MessageExternalList<MType, BType, IType> externalMessageList;
    private BuilderExternalList<MType, BType, IType> externalBuilderList;
    private MessageOrBuilderExternalList<MType, BType, IType> externalMessageOrBuilderList;

    public RepeatedFieldBuilder(List<MType> messages, boolean isMessagesListMutable, GeneratedMessage.BuilderParent parent, boolean isClean) {
        this.messages = messages;
        this.isMessagesListMutable = isMessagesListMutable;
        this.parent = parent;
        this.isClean = isClean;
    }

    public void dispose() {
        this.parent = null;
    }

    private void ensureMutableMessageList() {
        if (!this.isMessagesListMutable) {
            this.messages = new ArrayList<MType>(this.messages);
            this.isMessagesListMutable = true;
        }
    }

    private void ensureBuilders() {
        if (this.builders == null) {
            this.builders = new ArrayList<SingleFieldBuilder<MType, BType, IType>>(this.messages.size());
            for (int i = 0; i < this.messages.size(); ++i) {
                this.builders.add(null);
            }
        }
    }

    public int getCount() {
        return this.messages.size();
    }

    public boolean isEmpty() {
        return this.messages.isEmpty();
    }

    public MType getMessage(int index) {
        return this.getMessage(index, false);
    }

    private MType getMessage(int index, boolean forBuild) {
        if (this.builders == null) {
            return (MType)((GeneratedMessage)this.messages.get(index));
        }
        SingleFieldBuilder<MType, BType, IType> builder = this.builders.get(index);
        if (builder == null) {
            return (MType)((GeneratedMessage)this.messages.get(index));
        }
        return forBuild ? builder.build() : builder.getMessage();
    }

    public BType getBuilder(int index) {
        this.ensureBuilders();
        SingleFieldBuilder<Object, BType, IType> builder = this.builders.get(index);
        if (builder == null) {
            GeneratedMessage message = (GeneratedMessage)this.messages.get(index);
            builder = new SingleFieldBuilder(message, this, this.isClean);
            this.builders.set(index, builder);
        }
        return builder.getBuilder();
    }

    public IType getMessageOrBuilder(int index) {
        if (this.builders == null) {
            return (IType)((MessageOrBuilder)this.messages.get(index));
        }
        SingleFieldBuilder<MType, BType, IType> builder = this.builders.get(index);
        if (builder == null) {
            return (IType)((MessageOrBuilder)this.messages.get(index));
        }
        return builder.getMessageOrBuilder();
    }

    public RepeatedFieldBuilder<MType, BType, IType> setMessage(int index, MType message) {
        SingleFieldBuilder entry;
        if (message == null) {
            throw new NullPointerException();
        }
        this.ensureMutableMessageList();
        this.messages.set(index, message);
        if (this.builders != null && (entry = (SingleFieldBuilder)this.builders.set(index, null)) != null) {
            entry.dispose();
        }
        this.onChanged();
        this.incrementModCounts();
        return this;
    }

    public RepeatedFieldBuilder<MType, BType, IType> addMessage(MType message) {
        if (message == null) {
            throw new NullPointerException();
        }
        this.ensureMutableMessageList();
        this.messages.add(message);
        if (this.builders != null) {
            this.builders.add(null);
        }
        this.onChanged();
        this.incrementModCounts();
        return this;
    }

    public RepeatedFieldBuilder<MType, BType, IType> addMessage(int index, MType message) {
        if (message == null) {
            throw new NullPointerException();
        }
        this.ensureMutableMessageList();
        this.messages.add(index, message);
        if (this.builders != null) {
            this.builders.add(index, null);
        }
        this.onChanged();
        this.incrementModCounts();
        return this;
    }

    public RepeatedFieldBuilder<MType, BType, IType> addAllMessages(Iterable<? extends MType> values2) {
        for (GeneratedMessage value : values2) {
            if (value != null) continue;
            throw new NullPointerException();
        }
        if (values2 instanceof Collection) {
            Collection collection = (Collection)values2;
            if (collection.size() == 0) {
                return this;
            }
            this.ensureMutableMessageList();
            for (GeneratedMessage value : values2) {
                this.addMessage(value);
            }
        } else {
            this.ensureMutableMessageList();
            for (GeneratedMessage value : values2) {
                this.addMessage(value);
            }
        }
        this.onChanged();
        this.incrementModCounts();
        return this;
    }

    public BType addBuilder(MType message) {
        this.ensureMutableMessageList();
        this.ensureBuilders();
        SingleFieldBuilder builder = new SingleFieldBuilder(message, this, this.isClean);
        this.messages.add(null);
        this.builders.add(builder);
        this.onChanged();
        this.incrementModCounts();
        return builder.getBuilder();
    }

    public BType addBuilder(int index, MType message) {
        this.ensureMutableMessageList();
        this.ensureBuilders();
        SingleFieldBuilder builder = new SingleFieldBuilder(message, this, this.isClean);
        this.messages.add(index, null);
        this.builders.add(index, builder);
        this.onChanged();
        this.incrementModCounts();
        return builder.getBuilder();
    }

    public void remove(int index) {
        SingleFieldBuilder<MType, BType, IType> entry;
        this.ensureMutableMessageList();
        this.messages.remove(index);
        if (this.builders != null && (entry = this.builders.remove(index)) != null) {
            entry.dispose();
        }
        this.onChanged();
        this.incrementModCounts();
    }

    public void clear() {
        this.messages = Collections.emptyList();
        this.isMessagesListMutable = false;
        if (this.builders != null) {
            for (SingleFieldBuilder<MType, BType, IType> entry : this.builders) {
                if (entry == null) continue;
                entry.dispose();
            }
            this.builders = null;
        }
        this.onChanged();
        this.incrementModCounts();
    }

    public List<MType> build() {
        int i;
        this.isClean = true;
        if (!this.isMessagesListMutable && this.builders == null) {
            return this.messages;
        }
        boolean allMessagesInSync = true;
        if (!this.isMessagesListMutable) {
            for (i = 0; i < this.messages.size(); ++i) {
                Message message = (Message)this.messages.get(i);
                SingleFieldBuilder<MType, BType, IType> builder = this.builders.get(i);
                if (builder == null || builder.build() == message) continue;
                allMessagesInSync = false;
                break;
            }
            if (allMessagesInSync) {
                return this.messages;
            }
        }
        this.ensureMutableMessageList();
        for (i = 0; i < this.messages.size(); ++i) {
            this.messages.set(i, this.getMessage(i, true));
        }
        this.messages = Collections.unmodifiableList(this.messages);
        this.isMessagesListMutable = false;
        return this.messages;
    }

    public List<MType> getMessageList() {
        if (this.externalMessageList == null) {
            this.externalMessageList = new MessageExternalList(this);
        }
        return this.externalMessageList;
    }

    public List<BType> getBuilderList() {
        if (this.externalBuilderList == null) {
            this.externalBuilderList = new BuilderExternalList(this);
        }
        return this.externalBuilderList;
    }

    public List<IType> getMessageOrBuilderList() {
        if (this.externalMessageOrBuilderList == null) {
            this.externalMessageOrBuilderList = new MessageOrBuilderExternalList(this);
        }
        return this.externalMessageOrBuilderList;
    }

    private void onChanged() {
        if (this.isClean && this.parent != null) {
            this.parent.markDirty();
            this.isClean = false;
        }
    }

    @Override
    public void markDirty() {
        this.onChanged();
    }

    private void incrementModCounts() {
        if (this.externalMessageList != null) {
            this.externalMessageList.incrementModCount();
        }
        if (this.externalBuilderList != null) {
            this.externalBuilderList.incrementModCount();
        }
        if (this.externalMessageOrBuilderList != null) {
            this.externalMessageOrBuilderList.incrementModCount();
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class MessageOrBuilderExternalList<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder>
    extends AbstractList<IType>
    implements List<IType> {
        RepeatedFieldBuilder<MType, BType, IType> builder;

        MessageOrBuilderExternalList(RepeatedFieldBuilder<MType, BType, IType> builder) {
            this.builder = builder;
        }

        @Override
        public int size() {
            return this.builder.getCount();
        }

        @Override
        public IType get(int index) {
            return this.builder.getMessageOrBuilder(index);
        }

        void incrementModCount() {
            ++this.modCount;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class BuilderExternalList<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder>
    extends AbstractList<BType>
    implements List<BType> {
        RepeatedFieldBuilder<MType, BType, IType> builder;

        BuilderExternalList(RepeatedFieldBuilder<MType, BType, IType> builder) {
            this.builder = builder;
        }

        @Override
        public int size() {
            return this.builder.getCount();
        }

        @Override
        public BType get(int index) {
            return this.builder.getBuilder(index);
        }

        void incrementModCount() {
            ++this.modCount;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class MessageExternalList<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder>
    extends AbstractList<MType>
    implements List<MType> {
        RepeatedFieldBuilder<MType, BType, IType> builder;

        MessageExternalList(RepeatedFieldBuilder<MType, BType, IType> builder) {
            this.builder = builder;
        }

        @Override
        public int size() {
            return this.builder.getCount();
        }

        @Override
        public MType get(int index) {
            return this.builder.getMessage(index);
        }

        void incrementModCount() {
            ++this.modCount;
        }
    }
}

