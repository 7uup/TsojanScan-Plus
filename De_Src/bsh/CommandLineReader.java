/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import java.io.FilterReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

class CommandLineReader
extends FilterReader {
    static final int normal = 0;
    static final int lastCharNL = 1;
    static final int sentSemi = 2;
    int state = 1;

    public CommandLineReader(Reader in) {
        super(in);
    }

    public int read() throws IOException {
        int b;
        if (this.state == 2) {
            this.state = 1;
            return 10;
        }
        while ((b = this.in.read()) == 13) {
        }
        if (b == 10) {
            if (this.state == 1) {
                b = 59;
                this.state = 2;
            } else {
                this.state = 1;
            }
        } else {
            this.state = 0;
        }
        return b;
    }

    public int read(char[] buff, int off, int len) throws IOException {
        int b = this.read();
        if (b == -1) {
            return -1;
        }
        buff[off] = (char)b;
        return 1;
    }

    public static void main(String[] args2) throws Exception {
        CommandLineReader in = new CommandLineReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println(((Reader)in).read());
        }
    }
}

