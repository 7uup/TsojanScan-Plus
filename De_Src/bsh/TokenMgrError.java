/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

public class TokenMgrError
extends Error {
    static final int LEXICAL_ERROR = 0;
    static final int STATIC_LEXER_ERROR = 1;
    static final int INVALID_LEXICAL_STATE = 2;
    static final int LOOP_DETECTED = 3;
    int errorCode;

    protected static final String addEscapes(String str) {
        StringBuffer retval = new StringBuffer();
        block11: for (int i = 0; i < str.length(); ++i) {
            switch (str.charAt(i)) {
                case '\u0000': {
                    continue block11;
                }
                case '\b': {
                    retval.append("\\b");
                    continue block11;
                }
                case '\t': {
                    retval.append("\\t");
                    continue block11;
                }
                case '\n': {
                    retval.append("\\n");
                    continue block11;
                }
                case '\f': {
                    retval.append("\\f");
                    continue block11;
                }
                case '\r': {
                    retval.append("\\r");
                    continue block11;
                }
                case '\"': {
                    retval.append("\\\"");
                    continue block11;
                }
                case '\'': {
                    retval.append("\\'");
                    continue block11;
                }
                case '\\': {
                    retval.append("\\\\");
                    continue block11;
                }
                default: {
                    char ch = str.charAt(i);
                    if (ch < ' ' || ch > '~') {
                        String s2 = "0000" + Integer.toString(ch, 16);
                        retval.append("\\u" + s2.substring(s2.length() - 4, s2.length()));
                        continue block11;
                    }
                    retval.append(ch);
                }
            }
        }
        return retval.toString();
    }

    protected static String LexicalError(boolean EOFSeen, int lexState, int errorLine, int errorColumn, String errorAfter, char curChar) {
        return "Lexical error at line " + errorLine + ", column " + errorColumn + ".  Encountered: " + (EOFSeen ? "<EOF> " : "\"" + TokenMgrError.addEscapes(String.valueOf(curChar)) + "\"" + " (" + curChar + "), ") + "after : \"" + TokenMgrError.addEscapes(errorAfter) + "\"";
    }

    public String getMessage() {
        return super.getMessage();
    }

    public TokenMgrError() {
    }

    public TokenMgrError(String message, int reason) {
        super(message);
        this.errorCode = reason;
    }

    public TokenMgrError(boolean EOFSeen, int lexState, int errorLine, int errorColumn, String errorAfter, char curChar, int reason) {
        this(TokenMgrError.LexicalError(EOFSeen, lexState, errorLine, errorColumn, errorAfter, curChar), reason);
    }
}

