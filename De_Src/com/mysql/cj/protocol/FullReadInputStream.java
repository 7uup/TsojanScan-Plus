/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.Messages;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FullReadInputStream
extends FilterInputStream {
    public FullReadInputStream(InputStream underlyingStream) {
        super(underlyingStream);
    }

    public InputStream getUnderlyingStream() {
        return this.in;
    }

    public int readFully(byte[] b) throws IOException {
        return this.readFully(b, 0, b.length);
    }

    public int readFully(byte[] b, int off, int len) throws IOException {
        int n;
        int count;
        if (len < 0) {
            throw new IndexOutOfBoundsException();
        }
        for (n = 0; n < len; n += count) {
            count = this.read(b, off + n, len - n);
            if (count >= 0) continue;
            throw new EOFException(Messages.getString("MysqlIO.EOF", new Object[]{len, n}));
        }
        return n;
    }

    public long skipFully(long len) throws IOException {
        long n;
        long count;
        if (len < 0L) {
            throw new IOException(Messages.getString("MysqlIO.105"));
        }
        for (n = 0L; n < len; n += count) {
            count = this.skip(len - n);
            if (count >= 0L) continue;
            throw new EOFException(Messages.getString("MysqlIO.EOF", new Object[]{len, n}));
        }
        return n;
    }

    public int skipLengthEncodedInteger() throws IOException {
        int sw = this.read() & 0xFF;
        switch (sw) {
            case 252: {
                return (int)this.skipFully(2L) + 1;
            }
            case 253: {
                return (int)this.skipFully(3L) + 1;
            }
            case 254: {
                return (int)this.skipFully(8L) + 1;
            }
        }
        return 1;
    }
}

