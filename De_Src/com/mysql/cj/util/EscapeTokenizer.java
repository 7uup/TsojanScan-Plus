/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.util;

public class EscapeTokenizer {
    private static final char CHR_ESCAPE = '\\';
    private static final char CHR_SGL_QUOTE = '\'';
    private static final char CHR_DBL_QUOTE = '\"';
    private static final char CHR_LF = '\n';
    private static final char CHR_CR = '\r';
    private static final char CHR_COMMENT = '-';
    private static final char CHR_BEGIN_TOKEN = '{';
    private static final char CHR_END_TOKEN = '}';
    private static final char CHR_VARIABLE = '@';
    private String source = null;
    private int sourceLength = 0;
    private int pos = 0;
    private boolean emittingEscapeCode = false;
    private boolean sawVariableUse = false;
    private int bracesLevel = 0;
    private boolean inQuotes = false;
    private char quoteChar = '\u0000';

    public EscapeTokenizer(String source2) {
        this.source = source2;
        this.sourceLength = source2.length();
        this.pos = 0;
    }

    public synchronized boolean hasMoreTokens() {
        return this.pos < this.sourceLength;
    }

    /*
     * Unable to fully structure code
     */
    public synchronized String nextToken() {
        tokenBuf = new StringBuilder();
        backslashEscape = false;
        if (this.emittingEscapeCode) {
            tokenBuf.append("{");
            this.emittingEscapeCode = false;
        }
        while (this.pos < this.sourceLength) {
            block19: {
                block21: {
                    block20: {
                        block18: {
                            c = this.source.charAt(this.pos);
                            if (c != '\\') break block18;
                            tokenBuf.append(c);
                            backslashEscape = backslashEscape == false;
                            break block19;
                        }
                        if (c != '\'' && c != '\"' || backslashEscape) break block20;
                        tokenBuf.append(c);
                        if (this.inQuotes) {
                            if (c == this.quoteChar) {
                                if (this.pos + 1 < this.sourceLength && this.source.charAt(this.pos + 1) == this.quoteChar) {
                                    tokenBuf.append(c);
                                    ++this.pos;
                                } else {
                                    this.inQuotes = false;
                                }
                            }
                        } else {
                            this.inQuotes = true;
                            this.quoteChar = c;
                        }
                        break block19;
                    }
                    if (c != '\n' && c != '\r') break block21;
                    tokenBuf.append(c);
                    backslashEscape = false;
                    break block19;
                }
                if (this.inQuotes || backslashEscape) ** GOTO lbl68
                if (c == '-') {
                    tokenBuf.append(c);
                    if (this.pos + 1 < this.sourceLength && this.source.charAt(this.pos + 1) == '-') {
                        while (++this.pos < this.sourceLength && c != '\n' && c != '\r') {
                            c = this.source.charAt(this.pos);
                            tokenBuf.append(c);
                        }
                        --this.pos;
                    }
                } else if (c == '{') {
                    ++this.bracesLevel;
                    if (this.bracesLevel == 1) {
                        this.emittingEscapeCode = true;
                        ++this.pos;
                        return tokenBuf.toString();
                    }
                    tokenBuf.append(c);
                } else if (c == '}') {
                    tokenBuf.append(c);
                    --this.bracesLevel;
                    if (this.bracesLevel == 0) {
                        ++this.pos;
                        return tokenBuf.toString();
                    }
                } else {
                    if (c == '@') {
                        this.sawVariableUse = true;
                    }
lbl68:
                    // 4 sources

                    tokenBuf.append(c);
                    backslashEscape = false;
                }
            }
            ++this.pos;
        }
        return tokenBuf.toString();
    }

    public boolean sawVariableUse() {
        return this.sawVariableUse;
    }
}

