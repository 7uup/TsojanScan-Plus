/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.x;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.protocol.FullReadInputStream;
import com.mysql.cj.protocol.MessageListener;
import com.mysql.cj.protocol.MessageReader;
import com.mysql.cj.protocol.x.MessageConstants;
import com.mysql.cj.protocol.x.XMessage;
import com.mysql.cj.protocol.x.XMessageHeader;
import com.mysql.cj.protocol.x.XProtocolError;
import com.mysql.cj.x.protobuf.Mysqlx;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class SyncMessageReader
implements MessageReader<XMessageHeader, XMessage> {
    private FullReadInputStream inputStream;
    private XMessageHeader header;
    BlockingQueue<MessageListener<XMessage>> messageListenerQueue = new LinkedBlockingQueue<MessageListener<XMessage>>();
    Object dispatchingThreadMonitor = new Object();
    Object waitingSyncOperationMonitor = new Object();
    Thread dispatchingThread = null;

    public SyncMessageReader(FullReadInputStream inputStream2) {
        this.inputStream = inputStream2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XMessageHeader readHeader() throws IOException {
        Object object = this.waitingSyncOperationMonitor;
        synchronized (object) {
            if (this.header == null) {
                this.header = this.readHeaderLocal();
            }
            if (this.header.getMessageType() == 1) {
                throw new XProtocolError(this.readMessageLocal(Mysqlx.Error.class));
            }
            return this.header;
        }
    }

    private XMessageHeader readHeaderLocal() throws IOException {
        try {
            byte[] len = new byte[5];
            this.inputStream.readFully(len);
            this.header = new XMessageHeader(len);
        } catch (IOException ex) {
            throw new CJCommunicationsException("Cannot read packet header", ex);
        }
        return this.header;
    }

    private <T extends GeneratedMessage> T readMessageLocal(Class<T> messageClass) {
        Parser<? extends GeneratedMessage> parser = MessageConstants.MESSAGE_CLASS_TO_PARSER.get(messageClass);
        byte[] packet = new byte[this.header.getMessageSize()];
        try {
            this.inputStream.readFully(packet);
        } catch (IOException ex) {
            throw new CJCommunicationsException("Cannot read packet payload", ex);
        }
        try {
            GeneratedMessage ex = parser.parseFrom(packet);
            return (T)ex;
        } catch (InvalidProtocolBufferException ex) {
            throw new WrongArgumentException(ex);
        } finally {
            this.header = null;
        }
    }

    @Override
    public XMessage readMessage(Optional<XMessage> reuse, XMessageHeader hdr) throws IOException {
        return this.readMessage((Optional)reuse, hdr.getMessageType());
    }

    @Override
    public XMessage readMessage(Optional<XMessage> reuse, int expectedType) throws IOException {
        Object object = this.waitingSyncOperationMonitor;
        synchronized (object) {
            try {
                Class<? extends GeneratedMessage> messageClass = MessageConstants.getMessageClassForType(this.readHeader().getMessageType());
                Class<? extends GeneratedMessage> expectedClass = MessageConstants.getMessageClassForType(expectedType);
                if (expectedClass != messageClass) {
                    throw new WrongArgumentException("Unexpected message class. Expected '" + expectedClass.getSimpleName() + "' but actually received '" + messageClass.getSimpleName() + "'");
                }
                return new XMessage(this.readMessageLocal(messageClass));
            } catch (IOException e) {
                throw new XProtocolError(e.getMessage(), e);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void pushMessageListener(MessageListener<XMessage> listener) {
        try {
            this.messageListenerQueue.put(listener);
        } catch (InterruptedException e) {
            throw new CJCommunicationsException("Cannot queue message listener.", e);
        }
        Object object = this.dispatchingThreadMonitor;
        synchronized (object) {
            if (this.dispatchingThread == null) {
                ListenersDispatcher ld = new ListenersDispatcher();
                this.dispatchingThread = new Thread((Runnable)ld, "Message listeners dispatching thread");
                this.dispatchingThread.start();
                int millis = 5000;
                while (!ld.started) {
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        throw new XProtocolError(e.getMessage(), e);
                    }
                    if ((millis -= 10) > 0) continue;
                    throw new XProtocolError("Timeout for starting ListenersDispatcher exceeded.");
                }
            }
        }
    }

    private class ListenersDispatcher
    implements Runnable {
        private static final long POLL_TIMEOUT = 100L;
        boolean started = false;

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void run() {
            Object object = SyncMessageReader.this.waitingSyncOperationMonitor;
            synchronized (object) {
                this.started = true;
                block10: while (true) {
                    try {
                        while (true) {
                            MessageListener<XMessage> l;
                            if ((l = SyncMessageReader.this.messageListenerQueue.poll(100L, TimeUnit.MILLISECONDS)) == null) {
                                Object object2 = SyncMessageReader.this.dispatchingThreadMonitor;
                                synchronized (object2) {
                                    if (SyncMessageReader.this.messageListenerQueue.peek() == null) {
                                        SyncMessageReader.this.dispatchingThread = null;
                                        break block10;
                                    }
                                    continue block10;
                                }
                            }
                            try {
                                XMessageHeader hdr;
                                XMessage msg = null;
                                while (!((Boolean)l.createFromMessage(msg = SyncMessageReader.this.readMessage((Optional<XMessage>)null, hdr = SyncMessageReader.this.readHeader()))).booleanValue()) {
                                }
                                continue block10;
                            } catch (Throwable t) {
                                l.error(t);
                                continue;
                            }
                            break;
                        }
                    } catch (InterruptedException e) {
                        throw new CJCommunicationsException("Read operation interrupted.", e);
                    }
                }
            }
        }
    }
}

