/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class JavaCharStream {
    public static final boolean staticFlag = false;
    public int bufpos = -1;
    int bufsize;
    int available;
    int tokenBegin;
    protected int[] bufline;
    protected int[] bufcolumn;
    protected int column = 0;
    protected int line = 1;
    protected boolean prevCharIsCR = false;
    protected boolean prevCharIsLF = false;
    protected Reader inputStream;
    protected char[] nextCharBuf;
    protected char[] buffer;
    protected int maxNextCharInd = 0;
    protected int nextCharInd = -1;
    protected int inBuf = 0;

    static final int hexval(char c) throws IOException {
        switch (c) {
            case '0': {
                return 0;
            }
            case '1': {
                return 1;
            }
            case '2': {
                return 2;
            }
            case '3': {
                return 3;
            }
            case '4': {
                return 4;
            }
            case '5': {
                return 5;
            }
            case '6': {
                return 6;
            }
            case '7': {
                return 7;
            }
            case '8': {
                return 8;
            }
            case '9': {
                return 9;
            }
            case 'A': 
            case 'a': {
                return 10;
            }
            case 'B': 
            case 'b': {
                return 11;
            }
            case 'C': 
            case 'c': {
                return 12;
            }
            case 'D': 
            case 'd': {
                return 13;
            }
            case 'E': 
            case 'e': {
                return 14;
            }
            case 'F': 
            case 'f': {
                return 15;
            }
        }
        throw new IOException();
    }

    protected void ExpandBuff(boolean wrapAround) {
        char[] newbuffer = new char[this.bufsize + 2048];
        int[] newbufline = new int[this.bufsize + 2048];
        int[] newbufcolumn = new int[this.bufsize + 2048];
        try {
            if (wrapAround) {
                System.arraycopy(this.buffer, this.tokenBegin, newbuffer, 0, this.bufsize - this.tokenBegin);
                System.arraycopy(this.buffer, 0, newbuffer, this.bufsize - this.tokenBegin, this.bufpos);
                this.buffer = newbuffer;
                System.arraycopy(this.bufline, this.tokenBegin, newbufline, 0, this.bufsize - this.tokenBegin);
                System.arraycopy(this.bufline, 0, newbufline, this.bufsize - this.tokenBegin, this.bufpos);
                this.bufline = newbufline;
                System.arraycopy(this.bufcolumn, this.tokenBegin, newbufcolumn, 0, this.bufsize - this.tokenBegin);
                System.arraycopy(this.bufcolumn, 0, newbufcolumn, this.bufsize - this.tokenBegin, this.bufpos);
                this.bufcolumn = newbufcolumn;
                this.bufpos += this.bufsize - this.tokenBegin;
            } else {
                System.arraycopy(this.buffer, this.tokenBegin, newbuffer, 0, this.bufsize - this.tokenBegin);
                this.buffer = newbuffer;
                System.arraycopy(this.bufline, this.tokenBegin, newbufline, 0, this.bufsize - this.tokenBegin);
                this.bufline = newbufline;
                System.arraycopy(this.bufcolumn, this.tokenBegin, newbufcolumn, 0, this.bufsize - this.tokenBegin);
                this.bufcolumn = newbufcolumn;
                this.bufpos -= this.tokenBegin;
            }
        } catch (Throwable t) {
            throw new Error(t.getMessage());
        }
        this.available = this.bufsize += 2048;
        this.tokenBegin = 0;
    }

    protected void FillBuff() throws IOException {
        if (this.maxNextCharInd == 4096) {
            this.nextCharInd = 0;
            this.maxNextCharInd = 0;
        }
        try {
            int i = this.inputStream.read(this.nextCharBuf, this.maxNextCharInd, 4096 - this.maxNextCharInd);
            if (i == -1) {
                this.inputStream.close();
                throw new IOException();
            }
            this.maxNextCharInd += i;
            return;
        } catch (IOException e) {
            if (this.bufpos != 0) {
                --this.bufpos;
                this.backup(0);
            } else {
                this.bufline[this.bufpos] = this.line;
                this.bufcolumn[this.bufpos] = this.column;
            }
            throw e;
        }
    }

    protected char ReadByte() throws IOException {
        if (++this.nextCharInd >= this.maxNextCharInd) {
            this.FillBuff();
        }
        return this.nextCharBuf[this.nextCharInd];
    }

    public char BeginToken() throws IOException {
        if (this.inBuf > 0) {
            --this.inBuf;
            if (++this.bufpos == this.bufsize) {
                this.bufpos = 0;
            }
            this.tokenBegin = this.bufpos;
            return this.buffer[this.bufpos];
        }
        this.tokenBegin = 0;
        this.bufpos = -1;
        return this.readChar();
    }

    protected void AdjustBuffSize() {
        if (this.available == this.bufsize) {
            if (this.tokenBegin > 2048) {
                this.bufpos = 0;
                this.available = this.tokenBegin;
            } else {
                this.ExpandBuff(false);
            }
        } else if (this.available > this.tokenBegin) {
            this.available = this.bufsize;
        } else if (this.tokenBegin - this.available < 2048) {
            this.ExpandBuff(true);
        } else {
            this.available = this.tokenBegin;
        }
    }

    protected void UpdateLineColumn(char c) {
        ++this.column;
        if (this.prevCharIsLF) {
            this.prevCharIsLF = false;
            this.column = 1;
            ++this.line;
        } else if (this.prevCharIsCR) {
            this.prevCharIsCR = false;
            if (c == '\n') {
                this.prevCharIsLF = true;
            } else {
                this.column = 1;
                ++this.line;
            }
        }
        switch (c) {
            case '\r': {
                this.prevCharIsCR = true;
                break;
            }
            case '\n': {
                this.prevCharIsLF = true;
                break;
            }
            case '\t': {
                --this.column;
                this.column += 8 - (this.column & 7);
                break;
            }
        }
        this.bufline[this.bufpos] = this.line;
        this.bufcolumn[this.bufpos] = this.column;
    }

    public char readChar() throws IOException {
        char c;
        if (this.inBuf > 0) {
            --this.inBuf;
            if (++this.bufpos == this.bufsize) {
                this.bufpos = 0;
            }
            return this.buffer[this.bufpos];
        }
        if (++this.bufpos == this.available) {
            this.AdjustBuffSize();
        }
        this.buffer[this.bufpos] = c = this.ReadByte();
        if (c == '\\') {
            this.UpdateLineColumn(c);
            int backSlashCnt = 1;
            while (true) {
                if (++this.bufpos == this.available) {
                    this.AdjustBuffSize();
                }
                try {
                    this.buffer[this.bufpos] = c = this.ReadByte();
                    if (c != '\\') {
                        this.UpdateLineColumn(c);
                        if (c == 'u' && (backSlashCnt & 1) == 1) {
                            if (--this.bufpos >= 0) break;
                            this.bufpos = this.bufsize - 1;
                            break;
                        }
                        this.backup(backSlashCnt);
                        return '\\';
                    }
                } catch (IOException e) {
                    if (backSlashCnt > 1) {
                        this.backup(backSlashCnt);
                    }
                    return '\\';
                }
                this.UpdateLineColumn(c);
                ++backSlashCnt;
            }
            try {
                while ((c = this.ReadByte()) == 'u') {
                    ++this.column;
                }
                this.buffer[this.bufpos] = c = (char)(JavaCharStream.hexval(c) << 12 | JavaCharStream.hexval(this.ReadByte()) << 8 | JavaCharStream.hexval(this.ReadByte()) << 4 | JavaCharStream.hexval(this.ReadByte()));
                this.column += 4;
            } catch (IOException e) {
                throw new Error("Invalid escape character at line " + this.line + " column " + this.column + ".");
            }
            if (backSlashCnt == 1) {
                return c;
            }
            this.backup(backSlashCnt - 1);
            return '\\';
        }
        this.UpdateLineColumn(c);
        return c;
    }

    public int getColumn() {
        return this.bufcolumn[this.bufpos];
    }

    public int getLine() {
        return this.bufline[this.bufpos];
    }

    public int getEndColumn() {
        return this.bufcolumn[this.bufpos];
    }

    public int getEndLine() {
        return this.bufline[this.bufpos];
    }

    public int getBeginColumn() {
        return this.bufcolumn[this.tokenBegin];
    }

    public int getBeginLine() {
        return this.bufline[this.tokenBegin];
    }

    public void backup(int amount) {
        this.inBuf += amount;
        if ((this.bufpos -= amount) < 0) {
            this.bufpos += this.bufsize;
        }
    }

    public JavaCharStream(Reader dstream, int startline, int startcolumn, int buffersize) {
        this.inputStream = dstream;
        this.line = startline;
        this.column = startcolumn - 1;
        this.available = this.bufsize = buffersize;
        this.buffer = new char[buffersize];
        this.bufline = new int[buffersize];
        this.bufcolumn = new int[buffersize];
        this.nextCharBuf = new char[4096];
    }

    public JavaCharStream(Reader dstream, int startline, int startcolumn) {
        this(dstream, startline, startcolumn, 4096);
    }

    public JavaCharStream(Reader dstream) {
        this(dstream, 1, 1, 4096);
    }

    public void ReInit(Reader dstream, int startline, int startcolumn, int buffersize) {
        this.inputStream = dstream;
        this.line = startline;
        this.column = startcolumn - 1;
        if (this.buffer == null || buffersize != this.buffer.length) {
            this.available = this.bufsize = buffersize;
            this.buffer = new char[buffersize];
            this.bufline = new int[buffersize];
            this.bufcolumn = new int[buffersize];
            this.nextCharBuf = new char[4096];
        }
        this.prevCharIsCR = false;
        this.prevCharIsLF = false;
        this.maxNextCharInd = 0;
        this.inBuf = 0;
        this.tokenBegin = 0;
        this.bufpos = -1;
        this.nextCharInd = -1;
    }

    public void ReInit(Reader dstream, int startline, int startcolumn) {
        this.ReInit(dstream, startline, startcolumn, 4096);
    }

    public void ReInit(Reader dstream) {
        this.ReInit(dstream, 1, 1, 4096);
    }

    public JavaCharStream(InputStream dstream, int startline, int startcolumn, int buffersize) {
        this(new InputStreamReader(dstream), startline, startcolumn, 4096);
    }

    public JavaCharStream(InputStream dstream, int startline, int startcolumn) {
        this(dstream, startline, startcolumn, 4096);
    }

    public JavaCharStream(InputStream dstream) {
        this(dstream, 1, 1, 4096);
    }

    public void ReInit(InputStream dstream, int startline, int startcolumn, int buffersize) {
        this.ReInit(new InputStreamReader(dstream), startline, startcolumn, 4096);
    }

    public void ReInit(InputStream dstream, int startline, int startcolumn) {
        this.ReInit(dstream, startline, startcolumn, 4096);
    }

    public void ReInit(InputStream dstream) {
        this.ReInit(dstream, 1, 1, 4096);
    }

    public String GetImage() {
        if (this.bufpos >= this.tokenBegin) {
            return new String(this.buffer, this.tokenBegin, this.bufpos - this.tokenBegin + 1);
        }
        return new String(this.buffer, this.tokenBegin, this.bufsize - this.tokenBegin) + new String(this.buffer, 0, this.bufpos + 1);
    }

    public char[] GetSuffix(int len) {
        char[] ret = new char[len];
        if (this.bufpos + 1 >= len) {
            System.arraycopy(this.buffer, this.bufpos - len + 1, ret, 0, len);
        } else {
            System.arraycopy(this.buffer, this.bufsize - (len - this.bufpos - 1), ret, 0, len - this.bufpos - 1);
            System.arraycopy(this.buffer, 0, ret, len - this.bufpos - 1, this.bufpos + 1);
        }
        return ret;
    }

    public void Done() {
        this.nextCharBuf = null;
        this.buffer = null;
        this.bufline = null;
        this.bufcolumn = null;
    }

    public void adjustBeginLineColumn(int newLine, int newCol) {
        int i;
        int start = this.tokenBegin;
        int len = this.bufpos >= this.tokenBegin ? this.bufpos - this.tokenBegin + this.inBuf + 1 : this.bufsize - this.tokenBegin + this.bufpos + 1 + this.inBuf;
        int j = 0;
        int k = 0;
        int nextColDiff = 0;
        int columnDiff = 0;
        for (i = 0; i < len && this.bufline[j = start % this.bufsize] == this.bufline[k = ++start % this.bufsize]; ++i) {
            this.bufline[j] = newLine;
            nextColDiff = columnDiff + this.bufcolumn[k] - this.bufcolumn[j];
            this.bufcolumn[j] = newCol + columnDiff;
            columnDiff = nextColDiff;
        }
        if (i < len) {
            this.bufline[j] = newLine++;
            this.bufcolumn[j] = newCol + columnDiff;
            while (i++ < len) {
                j = start % this.bufsize;
                if (this.bufline[j] != this.bufline[++start % this.bufsize]) {
                    this.bufline[j] = newLine++;
                    continue;
                }
                this.bufline[j] = newLine;
            }
        }
        this.line = this.bufline[j];
        this.column = this.bufcolumn[j];
    }
}

