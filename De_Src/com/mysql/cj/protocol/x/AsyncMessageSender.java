/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.x;

import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Message;
import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.exceptions.CJPacketTooBigException;
import com.mysql.cj.protocol.MessageSender;
import com.mysql.cj.protocol.SerializingBufferWriter;
import com.mysql.cj.protocol.x.ErrorToFutureCompletionHandler;
import com.mysql.cj.protocol.x.MessageConstants;
import com.mysql.cj.protocol.x.XMessage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsyncMessageSender
implements MessageSender<XMessage> {
    private static final int HEADER_LEN = 5;
    private int maxAllowedPacket = -1;
    private SerializingBufferWriter bufferWriter;

    public AsyncMessageSender(AsynchronousSocketChannel channel) {
        this.bufferWriter = new SerializingBufferWriter(channel);
    }

    @Override
    public void send(XMessage message) {
        CompletableFuture f = new CompletableFuture();
        this.send(message, (CompletionHandler<Long, Void>)new ErrorToFutureCompletionHandler<Long>(f, () -> f.complete(null)));
        try {
            f.get();
        } catch (ExecutionException ex) {
            throw new CJCommunicationsException("Failed to write message", ex.getCause());
        } catch (InterruptedException ex) {
            throw new CJCommunicationsException("Failed to write message", ex);
        }
    }

    @Override
    public void send(XMessage message, CompletionHandler<Long, Void> callback) {
        Message msg = message.getMessage();
        int type = MessageConstants.getTypeForMessageClass(msg.getClass());
        int size = msg.getSerializedSize();
        int payloadSize = size + 1;
        if (this.maxAllowedPacket > 0 && payloadSize > this.maxAllowedPacket) {
            throw new CJPacketTooBigException(Messages.getString("PacketTooBigException.1", new Object[]{size, this.maxAllowedPacket}));
        }
        ByteBuffer messageBuf = ByteBuffer.allocate(5 + size).order(ByteOrder.LITTLE_ENDIAN).putInt(payloadSize);
        messageBuf.put((byte)type);
        try {
            msg.writeTo(CodedOutputStream.newInstance(messageBuf.array(), 5, size + 5));
            messageBuf.position(messageBuf.limit());
        } catch (IOException ex) {
            throw new CJCommunicationsException("Unable to write message", ex);
        }
        messageBuf.flip();
        this.bufferWriter.queueBuffer(messageBuf, callback);
    }

    @Override
    public void setMaxAllowedPacket(int maxAllowedPacket) {
        this.maxAllowedPacket = maxAllowedPacket;
    }

    public void setChannel(AsynchronousSocketChannel channel) {
        this.bufferWriter.setChannel(channel);
    }
}

