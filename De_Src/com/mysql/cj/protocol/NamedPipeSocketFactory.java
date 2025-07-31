/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.Messages;
import com.mysql.cj.Session;
import com.mysql.cj.protocol.ServerSession;
import com.mysql.cj.protocol.SocketConnection;
import com.mysql.cj.protocol.SocketFactory;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.net.SocketException;
import java.util.Properties;

public class NamedPipeSocketFactory
implements SocketFactory {
    private Socket namedPipeSocket;

    @Override
    public <T extends Closeable> T performTlsHandshake(SocketConnection socketConnection, ServerSession serverSession) throws IOException {
        return (T)this.namedPipeSocket;
    }

    @Override
    public <T extends Closeable> T connect(String host, int portNumber, Properties props, int loginTimeout) throws IOException {
        String namedPipePath = props.getProperty("namedPipePath");
        if (namedPipePath == null) {
            namedPipePath = "\\\\.\\pipe\\MySQL";
        } else if (namedPipePath.length() == 0) {
            throw new SocketException(Messages.getString("NamedPipeSocketFactory.2") + "namedPipePath" + Messages.getString("NamedPipeSocketFactory.3"));
        }
        this.namedPipeSocket = new NamedPipeSocket(namedPipePath);
        return (T)this.namedPipeSocket;
    }

    @Override
    public boolean isLocallyConnected(Session sess) {
        return true;
    }

    class RandomAccessFileOutputStream
    extends OutputStream {
        RandomAccessFile raFile;

        RandomAccessFileOutputStream(RandomAccessFile file) {
            this.raFile = file;
        }

        @Override
        public void close() throws IOException {
            this.raFile.close();
        }

        @Override
        public void write(byte[] b) throws IOException {
            this.raFile.write(b);
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            this.raFile.write(b, off, len);
        }

        @Override
        public void write(int b) throws IOException {
        }
    }

    class RandomAccessFileInputStream
    extends InputStream {
        RandomAccessFile raFile;

        RandomAccessFileInputStream(RandomAccessFile file) {
            this.raFile = file;
        }

        @Override
        public int available() throws IOException {
            return -1;
        }

        @Override
        public void close() throws IOException {
            this.raFile.close();
        }

        @Override
        public int read() throws IOException {
            return this.raFile.read();
        }

        @Override
        public int read(byte[] b) throws IOException {
            return this.raFile.read(b);
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            return this.raFile.read(b, off, len);
        }
    }

    class NamedPipeSocket
    extends Socket {
        private boolean isClosed = false;
        private RandomAccessFile namedPipeFile;

        NamedPipeSocket(String filePath) throws IOException {
            if (filePath == null || filePath.length() == 0) {
                throw new IOException(Messages.getString("NamedPipeSocketFactory.4"));
            }
            this.namedPipeFile = new RandomAccessFile(filePath, "rw");
        }

        @Override
        public synchronized void close() throws IOException {
            this.namedPipeFile.close();
            this.isClosed = true;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new RandomAccessFileInputStream(this.namedPipeFile);
        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            return new RandomAccessFileOutputStream(this.namedPipeFile);
        }

        @Override
        public boolean isClosed() {
            return this.isClosed;
        }

        @Override
        public void shutdownInput() throws IOException {
        }
    }
}

