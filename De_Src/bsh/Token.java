/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import java.io.Serializable;

public class Token
implements Serializable {
    public int kind;
    public int beginLine;
    public int beginColumn;
    public int endLine;
    public int endColumn;
    public String image;
    public Token next;
    public Token specialToken;

    public String toString() {
        return this.image;
    }

    public static final Token newToken(int ofKind) {
        switch (ofKind) {
            default: 
        }
        return new Token();
    }
}

