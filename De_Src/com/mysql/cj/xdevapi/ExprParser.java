/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.mysql.cj.x.protobuf.MysqlxExpr;
import com.mysql.cj.xdevapi.ExprUnparser;
import com.mysql.cj.xdevapi.ExprUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ExprParser {
    String string;
    List<Token> tokens = new ArrayList<Token>();
    int tokenPos = 0;
    Map<String, Integer> placeholderNameToPosition = new HashMap<String, Integer>();
    int positionalPlaceholderCount = 0;
    private boolean allowRelationalColumns;
    static Map<String, TokenType> reservedWords = new HashMap<String, TokenType>();

    public ExprParser(String s2) {
        this(s2, true);
    }

    public ExprParser(String s2, boolean allowRelationalColumns) {
        this.string = s2;
        this.lex();
        this.allowRelationalColumns = allowRelationalColumns;
    }

    boolean nextCharEquals(int i, char c) {
        return i + 1 < this.string.length() && this.string.charAt(i + 1) == c;
    }

    private int lexNumber(int i) {
        boolean isInt = true;
        int start = i;
        while (i < this.string.length()) {
            char c = this.string.charAt(i);
            if (c == '.') {
                isInt = false;
            } else if (c == 'e' || c == 'E') {
                isInt = false;
                if (this.nextCharEquals(i, '-') || this.nextCharEquals(i, '+')) {
                    ++i;
                }
            } else if (!Character.isDigit(c)) break;
            ++i;
        }
        if (isInt) {
            this.tokens.add(new Token(TokenType.LNUM_INT, this.string.substring(start, i)));
        } else {
            this.tokens.add(new Token(TokenType.LNUM_DOUBLE, this.string.substring(start, i)));
        }
        return --i;
    }

    void lex() {
        block29: for (int i = 0; i < this.string.length(); ++i) {
            int start = i;
            char c = this.string.charAt(i);
            if (Character.isWhitespace(c)) continue;
            if (Character.isDigit(c)) {
                i = this.lexNumber(i);
                continue;
            }
            if (c != '_' && !Character.isUnicodeIdentifierStart(c)) {
                switch (c) {
                    case ':': {
                        this.tokens.add(new Token(TokenType.COLON, c));
                        continue block29;
                    }
                    case '+': {
                        this.tokens.add(new Token(TokenType.PLUS, c));
                        continue block29;
                    }
                    case '-': {
                        if (this.nextCharEquals(i, '>')) {
                            ++i;
                            this.tokens.add(new Token(TokenType.COLDOCPATH, "->"));
                            continue block29;
                        }
                        this.tokens.add(new Token(TokenType.MINUS, c));
                        continue block29;
                    }
                    case '*': {
                        if (this.nextCharEquals(i, '*')) {
                            ++i;
                            this.tokens.add(new Token(TokenType.DOUBLESTAR, "**"));
                            continue block29;
                        }
                        this.tokens.add(new Token(TokenType.STAR, c));
                        continue block29;
                    }
                    case '/': {
                        this.tokens.add(new Token(TokenType.SLASH, c));
                        continue block29;
                    }
                    case '$': {
                        this.tokens.add(new Token(TokenType.DOLLAR, c));
                        continue block29;
                    }
                    case '%': {
                        this.tokens.add(new Token(TokenType.MOD, c));
                        continue block29;
                    }
                    case '=': {
                        if (this.nextCharEquals(i, '=')) {
                            ++i;
                        }
                        this.tokens.add(new Token(TokenType.EQ, "=="));
                        continue block29;
                    }
                    case '&': {
                        if (this.nextCharEquals(i, '&')) {
                            ++i;
                            this.tokens.add(new Token(TokenType.ANDAND, "&&"));
                            continue block29;
                        }
                        this.tokens.add(new Token(TokenType.BITAND, c));
                        continue block29;
                    }
                    case '|': {
                        if (this.nextCharEquals(i, '|')) {
                            ++i;
                            this.tokens.add(new Token(TokenType.OROR, "||"));
                            continue block29;
                        }
                        this.tokens.add(new Token(TokenType.BITOR, c));
                        continue block29;
                    }
                    case '^': {
                        this.tokens.add(new Token(TokenType.BITXOR, c));
                        continue block29;
                    }
                    case '(': {
                        this.tokens.add(new Token(TokenType.LPAREN, c));
                        continue block29;
                    }
                    case ')': {
                        this.tokens.add(new Token(TokenType.RPAREN, c));
                        continue block29;
                    }
                    case '[': {
                        this.tokens.add(new Token(TokenType.LSQBRACKET, c));
                        continue block29;
                    }
                    case ']': {
                        this.tokens.add(new Token(TokenType.RSQBRACKET, c));
                        continue block29;
                    }
                    case '{': {
                        this.tokens.add(new Token(TokenType.LCURLY, c));
                        continue block29;
                    }
                    case '}': {
                        this.tokens.add(new Token(TokenType.RCURLY, c));
                        continue block29;
                    }
                    case '~': {
                        this.tokens.add(new Token(TokenType.NEG, c));
                        continue block29;
                    }
                    case ',': {
                        this.tokens.add(new Token(TokenType.COMMA, c));
                        continue block29;
                    }
                    case '!': {
                        if (this.nextCharEquals(i, '=')) {
                            ++i;
                            this.tokens.add(new Token(TokenType.NE, "!="));
                            continue block29;
                        }
                        this.tokens.add(new Token(TokenType.BANG, c));
                        continue block29;
                    }
                    case '?': {
                        this.tokens.add(new Token(TokenType.EROTEME, c));
                        continue block29;
                    }
                    case '<': {
                        if (this.nextCharEquals(i, '<')) {
                            ++i;
                            this.tokens.add(new Token(TokenType.LSHIFT, "<<"));
                            continue block29;
                        }
                        if (this.nextCharEquals(i, '=')) {
                            ++i;
                            this.tokens.add(new Token(TokenType.LE, "<="));
                            continue block29;
                        }
                        this.tokens.add(new Token(TokenType.LT, c));
                        continue block29;
                    }
                    case '>': {
                        if (this.nextCharEquals(i, '>')) {
                            ++i;
                            this.tokens.add(new Token(TokenType.RSHIFT, ">>"));
                            continue block29;
                        }
                        if (this.nextCharEquals(i, '=')) {
                            ++i;
                            this.tokens.add(new Token(TokenType.GE, ">="));
                            continue block29;
                        }
                        this.tokens.add(new Token(TokenType.GT, c));
                        continue block29;
                    }
                    case '.': {
                        if (this.nextCharEquals(i, '*')) {
                            ++i;
                            this.tokens.add(new Token(TokenType.DOTSTAR, ".*"));
                            continue block29;
                        }
                        if (i + 1 < this.string.length() && Character.isDigit(this.string.charAt(i + 1))) {
                            i = this.lexNumber(i);
                            continue block29;
                        }
                        this.tokens.add(new Token(TokenType.DOT, c));
                        continue block29;
                    }
                    case '\"': 
                    case '\'': 
                    case '`': {
                        char quoteChar = c;
                        StringBuilder val = new StringBuilder();
                        try {
                            c = this.string.charAt(++i);
                            while (c != quoteChar || i + 1 < this.string.length() && this.string.charAt(i + 1) == quoteChar) {
                                if (c == '\\' || c == quoteChar) {
                                    ++i;
                                }
                                val.append(this.string.charAt(i));
                                c = this.string.charAt(++i);
                            }
                        } catch (StringIndexOutOfBoundsException ex) {
                            throw new WrongArgumentException("Unterminated string starting at " + start);
                        }
                        this.tokens.add(new Token(quoteChar == '`' ? TokenType.IDENT : TokenType.LSTRING, val.toString()));
                        continue block29;
                    }
                    default: {
                        throw new WrongArgumentException("Can't parse at pos: " + i);
                    }
                }
            }
            while (i < this.string.length() && Character.isUnicodeIdentifierPart(this.string.charAt(i))) {
                ++i;
            }
            String val = this.string.substring(start, i);
            String valLower = val.toLowerCase();
            if (i < this.string.length()) {
                --i;
            }
            if (reservedWords.containsKey(valLower)) {
                if ("and".equals(valLower)) {
                    this.tokens.add(new Token(reservedWords.get(valLower), "&&"));
                    continue;
                }
                if ("or".equals(valLower)) {
                    this.tokens.add(new Token(reservedWords.get(valLower), "||"));
                    continue;
                }
                this.tokens.add(new Token(reservedWords.get(valLower), valLower));
                continue;
            }
            this.tokens.add(new Token(TokenType.IDENT, val));
        }
    }

    void assertTokenAt(int pos, TokenType type) {
        if (this.tokens.size() <= pos) {
            throw new WrongArgumentException("No more tokens when expecting " + (Object)((Object)type) + " at token pos " + pos);
        }
        if (this.tokens.get((int)pos).type != type) {
            throw new WrongArgumentException("Expected token type " + (Object)((Object)type) + " at token pos " + pos);
        }
    }

    boolean currentTokenTypeEquals(TokenType t) {
        return this.posTokenTypeEquals(this.tokenPos, t);
    }

    boolean nextTokenTypeEquals(TokenType t) {
        return this.posTokenTypeEquals(this.tokenPos + 1, t);
    }

    boolean posTokenTypeEquals(int pos, TokenType t) {
        return this.tokens.size() > pos && this.tokens.get((int)pos).type == t;
    }

    String consumeToken(TokenType t) {
        this.assertTokenAt(this.tokenPos, t);
        String value = this.tokens.get((int)this.tokenPos).value;
        ++this.tokenPos;
        return value;
    }

    List<MysqlxExpr.Expr> parenExprList() {
        ArrayList<MysqlxExpr.Expr> exprs = new ArrayList<MysqlxExpr.Expr>();
        this.consumeToken(TokenType.LPAREN);
        if (!this.currentTokenTypeEquals(TokenType.RPAREN)) {
            exprs.add(this.expr());
            while (this.currentTokenTypeEquals(TokenType.COMMA)) {
                this.consumeToken(TokenType.COMMA);
                exprs.add(this.expr());
            }
        }
        this.consumeToken(TokenType.RPAREN);
        return exprs;
    }

    MysqlxExpr.Expr functionCall() {
        MysqlxExpr.Identifier id = this.identifier();
        MysqlxExpr.FunctionCall.Builder b = MysqlxExpr.FunctionCall.newBuilder();
        b.setName(id);
        b.addAllParam(this.parenExprList());
        return MysqlxExpr.Expr.newBuilder().setType(MysqlxExpr.Expr.Type.FUNC_CALL).setFunctionCall(b.build()).build();
    }

    MysqlxExpr.Expr starOperator() {
        MysqlxExpr.Operator op = MysqlxExpr.Operator.newBuilder().setName("*").build();
        return MysqlxExpr.Expr.newBuilder().setType(MysqlxExpr.Expr.Type.OPERATOR).setOperator(op).build();
    }

    MysqlxExpr.Identifier identifier() {
        MysqlxExpr.Identifier.Builder builder = MysqlxExpr.Identifier.newBuilder();
        this.assertTokenAt(this.tokenPos, TokenType.IDENT);
        if (this.nextTokenTypeEquals(TokenType.DOT)) {
            builder.setSchemaName(this.tokens.get((int)this.tokenPos).value);
            this.consumeToken(TokenType.IDENT);
            this.consumeToken(TokenType.DOT);
            this.assertTokenAt(this.tokenPos, TokenType.IDENT);
        }
        builder.setName(this.tokens.get((int)this.tokenPos).value);
        this.consumeToken(TokenType.IDENT);
        return builder.build();
    }

    MysqlxExpr.DocumentPathItem docPathMember() {
        String memberName;
        this.consumeToken(TokenType.DOT);
        Token t = this.tokens.get(this.tokenPos);
        if (this.currentTokenTypeEquals(TokenType.IDENT)) {
            if (!t.value.equals(ExprUnparser.quoteIdentifier(t.value))) {
                throw new WrongArgumentException("'" + t.value + "' is not a valid JSON/ECMAScript identifier");
            }
            this.consumeToken(TokenType.IDENT);
            memberName = t.value;
        } else if (this.currentTokenTypeEquals(TokenType.LSTRING)) {
            this.consumeToken(TokenType.LSTRING);
            memberName = t.value;
        } else {
            throw new WrongArgumentException("Expected token type IDENT or LSTRING in JSON path at token pos " + this.tokenPos);
        }
        MysqlxExpr.DocumentPathItem.Builder item = MysqlxExpr.DocumentPathItem.newBuilder();
        item.setType(MysqlxExpr.DocumentPathItem.Type.MEMBER);
        item.setValue(memberName);
        return item.build();
    }

    MysqlxExpr.DocumentPathItem docPathArrayLoc() {
        MysqlxExpr.DocumentPathItem.Builder builder = MysqlxExpr.DocumentPathItem.newBuilder();
        this.consumeToken(TokenType.LSQBRACKET);
        if (this.currentTokenTypeEquals(TokenType.STAR)) {
            this.consumeToken(TokenType.STAR);
            this.consumeToken(TokenType.RSQBRACKET);
            return builder.setType(MysqlxExpr.DocumentPathItem.Type.ARRAY_INDEX_ASTERISK).build();
        }
        if (this.currentTokenTypeEquals(TokenType.LNUM_INT)) {
            Integer v = Integer.valueOf(this.tokens.get((int)this.tokenPos).value);
            if (v < 0) {
                throw new WrongArgumentException("Array index cannot be negative at " + this.tokenPos);
            }
            this.consumeToken(TokenType.LNUM_INT);
            this.consumeToken(TokenType.RSQBRACKET);
            return builder.setType(MysqlxExpr.DocumentPathItem.Type.ARRAY_INDEX).setIndex(v).build();
        }
        throw new WrongArgumentException("Expected token type STAR or LNUM_INT in JSON path array index at token pos " + this.tokenPos);
    }

    public List<MysqlxExpr.DocumentPathItem> documentPath() {
        ArrayList<MysqlxExpr.DocumentPathItem> items = new ArrayList<MysqlxExpr.DocumentPathItem>();
        while (true) {
            if (this.currentTokenTypeEquals(TokenType.DOT)) {
                items.add(this.docPathMember());
                continue;
            }
            if (this.currentTokenTypeEquals(TokenType.DOTSTAR)) {
                this.consumeToken(TokenType.DOTSTAR);
                items.add(MysqlxExpr.DocumentPathItem.newBuilder().setType(MysqlxExpr.DocumentPathItem.Type.MEMBER_ASTERISK).build());
                continue;
            }
            if (this.currentTokenTypeEquals(TokenType.LSQBRACKET)) {
                items.add(this.docPathArrayLoc());
                continue;
            }
            if (!this.currentTokenTypeEquals(TokenType.DOUBLESTAR)) break;
            this.consumeToken(TokenType.DOUBLESTAR);
            items.add(MysqlxExpr.DocumentPathItem.newBuilder().setType(MysqlxExpr.DocumentPathItem.Type.DOUBLE_ASTERISK).build());
        }
        if (items.size() > 0 && ((MysqlxExpr.DocumentPathItem)items.get(items.size() - 1)).getType() == MysqlxExpr.DocumentPathItem.Type.DOUBLE_ASTERISK) {
            throw new WrongArgumentException("JSON path may not end in '**' at " + this.tokenPos);
        }
        return items;
    }

    public MysqlxExpr.Expr documentField() {
        MysqlxExpr.ColumnIdentifier.Builder builder = MysqlxExpr.ColumnIdentifier.newBuilder();
        if (this.currentTokenTypeEquals(TokenType.IDENT)) {
            builder.addDocumentPath(MysqlxExpr.DocumentPathItem.newBuilder().setType(MysqlxExpr.DocumentPathItem.Type.MEMBER).setValue(this.consumeToken(TokenType.IDENT)).build());
        }
        builder.addAllDocumentPath(this.documentPath());
        return MysqlxExpr.Expr.newBuilder().setType(MysqlxExpr.Expr.Type.IDENT).setIdentifier(builder.build()).build();
    }

    MysqlxExpr.Expr columnIdentifier() {
        LinkedList<String> parts = new LinkedList<String>();
        parts.add(this.consumeToken(TokenType.IDENT));
        while (this.currentTokenTypeEquals(TokenType.DOT)) {
            this.consumeToken(TokenType.DOT);
            parts.add(this.consumeToken(TokenType.IDENT));
            if (parts.size() != 3) continue;
        }
        Collections.reverse(parts);
        MysqlxExpr.ColumnIdentifier.Builder id = MysqlxExpr.ColumnIdentifier.newBuilder();
        block6: for (int i = 0; i < parts.size(); ++i) {
            switch (i) {
                case 0: {
                    id.setName((String)parts.get(0));
                    continue block6;
                }
                case 1: {
                    id.setTableName((String)parts.get(1));
                    continue block6;
                }
                case 2: {
                    id.setSchemaName((String)parts.get(2));
                }
            }
        }
        if (this.currentTokenTypeEquals(TokenType.COLDOCPATH)) {
            this.consumeToken(TokenType.COLDOCPATH);
            if (this.currentTokenTypeEquals(TokenType.DOLLAR)) {
                this.consumeToken(TokenType.DOLLAR);
                id.addAllDocumentPath(this.documentPath());
            } else if (this.currentTokenTypeEquals(TokenType.LSTRING)) {
                String path = this.consumeToken(TokenType.LSTRING);
                if (path.charAt(0) != '$') {
                    throw new WrongArgumentException("Invalid document path at " + this.tokenPos);
                }
                id.addAllDocumentPath(new ExprParser(path.substring(1, path.length())).documentPath());
            }
            if (id.getDocumentPathCount() == 0) {
                throw new WrongArgumentException("Invalid document path at " + this.tokenPos);
            }
        }
        return MysqlxExpr.Expr.newBuilder().setType(MysqlxExpr.Expr.Type.IDENT).setIdentifier(id.build()).build();
    }

    MysqlxExpr.Expr buildUnaryOp(String name, MysqlxExpr.Expr param) {
        MysqlxExpr.Operator op = MysqlxExpr.Operator.newBuilder().setName(name).addParam(param).build();
        return MysqlxExpr.Expr.newBuilder().setType(MysqlxExpr.Expr.Type.OPERATOR).setOperator(op).build();
    }

    MysqlxExpr.Expr atomicExpr() {
        if (this.tokenPos >= this.tokens.size()) {
            throw new WrongArgumentException("No more tokens when expecting one at token pos " + this.tokenPos);
        }
        Token t = this.tokens.get(this.tokenPos);
        ++this.tokenPos;
        switch (t.type) {
            case EROTEME: 
            case COLON: {
                String placeholderName;
                if (this.currentTokenTypeEquals(TokenType.LNUM_INT)) {
                    placeholderName = this.consumeToken(TokenType.LNUM_INT);
                } else if (this.currentTokenTypeEquals(TokenType.IDENT)) {
                    placeholderName = this.consumeToken(TokenType.IDENT);
                } else if (t.type == TokenType.EROTEME) {
                    placeholderName = String.valueOf(this.positionalPlaceholderCount);
                } else {
                    throw new WrongArgumentException("Invalid placeholder name at token pos " + this.tokenPos);
                }
                MysqlxExpr.Expr.Builder placeholder = MysqlxExpr.Expr.newBuilder().setType(MysqlxExpr.Expr.Type.PLACEHOLDER);
                if (this.placeholderNameToPosition.containsKey(placeholderName)) {
                    placeholder.setPosition(this.placeholderNameToPosition.get(placeholderName));
                } else {
                    placeholder.setPosition(this.positionalPlaceholderCount);
                    this.placeholderNameToPosition.put(placeholderName, this.positionalPlaceholderCount);
                    ++this.positionalPlaceholderCount;
                }
                return placeholder.build();
            }
            case LPAREN: {
                MysqlxExpr.Expr e2 = this.expr();
                this.consumeToken(TokenType.RPAREN);
                return e2;
            }
            case LCURLY: {
                MysqlxExpr.Object.Builder builder = MysqlxExpr.Object.newBuilder();
                if (this.currentTokenTypeEquals(TokenType.LSTRING)) {
                    this.parseCommaSeparatedList(() -> {
                        String key = this.consumeToken(TokenType.LSTRING);
                        this.consumeToken(TokenType.COLON);
                        MysqlxExpr.Expr value = this.expr();
                        return Collections.singletonMap(key, value);
                    }).stream().map(pair -> pair.entrySet().iterator().next()).map(e -> MysqlxExpr.Object.ObjectField.newBuilder().setKey((String)e.getKey()).setValue((MysqlxExpr.Expr)e.getValue())).forEach(builder::addFld);
                }
                this.consumeToken(TokenType.RCURLY);
                return MysqlxExpr.Expr.newBuilder().setType(MysqlxExpr.Expr.Type.OBJECT).setObject(builder.build()).build();
            }
            case LSQBRACKET: {
                MysqlxExpr.Array.Builder builder = MysqlxExpr.Expr.newBuilder().setType(MysqlxExpr.Expr.Type.ARRAY).getArrayBuilder();
                this.parseCommaSeparatedList(() -> this.expr()).stream().forEach(builder::addValue);
                this.consumeToken(TokenType.RSQBRACKET);
                return MysqlxExpr.Expr.newBuilder().setType(MysqlxExpr.Expr.Type.ARRAY).setArray(builder).build();
            }
            case CAST: {
                this.consumeToken(TokenType.LPAREN);
                MysqlxExpr.Operator.Builder builder = MysqlxExpr.Operator.newBuilder().setName(TokenType.CAST.toString().toLowerCase());
                builder.addParam(this.expr());
                this.consumeToken(TokenType.AS);
                StringBuilder typeStr = new StringBuilder(this.tokens.get((int)this.tokenPos).value.toUpperCase());
                if (this.currentTokenTypeEquals(TokenType.DECIMAL)) {
                    ++this.tokenPos;
                    if (this.currentTokenTypeEquals(TokenType.LPAREN)) {
                        typeStr.append(this.consumeToken(TokenType.LPAREN));
                        typeStr.append(this.consumeToken(TokenType.LNUM_INT));
                        if (this.currentTokenTypeEquals(TokenType.COMMA)) {
                            typeStr.append(this.consumeToken(TokenType.COMMA));
                            typeStr.append(this.consumeToken(TokenType.LNUM_INT));
                        }
                        typeStr.append(this.consumeToken(TokenType.RPAREN));
                    }
                } else if (this.currentTokenTypeEquals(TokenType.CHAR) || this.currentTokenTypeEquals(TokenType.BINARY)) {
                    ++this.tokenPos;
                    if (this.currentTokenTypeEquals(TokenType.LPAREN)) {
                        typeStr.append(this.consumeToken(TokenType.LPAREN));
                        typeStr.append(this.consumeToken(TokenType.LNUM_INT));
                        typeStr.append(this.consumeToken(TokenType.RPAREN));
                    }
                } else if (this.currentTokenTypeEquals(TokenType.UNSIGNED) || this.currentTokenTypeEquals(TokenType.SIGNED)) {
                    ++this.tokenPos;
                    if (this.currentTokenTypeEquals(TokenType.INTEGER)) {
                        this.consumeToken(TokenType.INTEGER);
                    }
                } else if (this.currentTokenTypeEquals(TokenType.JSON) || this.currentTokenTypeEquals(TokenType.DATE) || this.currentTokenTypeEquals(TokenType.DATETIME) || this.currentTokenTypeEquals(TokenType.TIME)) {
                    ++this.tokenPos;
                } else {
                    throw new WrongArgumentException("Expected valid CAST type argument at " + this.tokenPos);
                }
                this.consumeToken(TokenType.RPAREN);
                builder.addParam(ExprUtil.buildLiteralScalar(typeStr.toString().getBytes()));
                return MysqlxExpr.Expr.newBuilder().setType(MysqlxExpr.Expr.Type.OPERATOR).setOperator(builder.build()).build();
            }
            case PLUS: 
            case MINUS: {
                if (this.currentTokenTypeEquals(TokenType.LNUM_INT) || this.currentTokenTypeEquals(TokenType.LNUM_DOUBLE)) {
                    this.tokens.get((int)this.tokenPos).value = t.value + this.tokens.get((int)this.tokenPos).value;
                    return this.atomicExpr();
                }
                return this.buildUnaryOp(t.value, this.atomicExpr());
            }
            case NOT: 
            case NEG: 
            case BANG: {
                return this.buildUnaryOp(t.value, this.atomicExpr());
            }
            case LSTRING: {
                return ExprUtil.buildLiteralScalar(t.value);
            }
            case NULL: {
                return ExprUtil.buildLiteralNullScalar();
            }
            case LNUM_INT: {
                return ExprUtil.buildLiteralScalar(Long.valueOf(t.value));
            }
            case LNUM_DOUBLE: {
                return ExprUtil.buildLiteralScalar(Double.valueOf(t.value));
            }
            case TRUE: 
            case FALSE: {
                return ExprUtil.buildLiteralScalar(t.type == TokenType.TRUE);
            }
            case DOLLAR: {
                return this.documentField();
            }
            case STAR: {
                return this.starOperator();
            }
            case IDENT: {
                --this.tokenPos;
                if (this.nextTokenTypeEquals(TokenType.LPAREN) || this.posTokenTypeEquals(this.tokenPos + 1, TokenType.DOT) && this.posTokenTypeEquals(this.tokenPos + 2, TokenType.IDENT) && this.posTokenTypeEquals(this.tokenPos + 3, TokenType.LPAREN)) {
                    return this.functionCall();
                }
                if (this.allowRelationalColumns) {
                    return this.columnIdentifier();
                }
                return this.documentField();
            }
        }
        throw new WrongArgumentException("Cannot find atomic expression at token pos: " + (this.tokenPos - 1));
    }

    MysqlxExpr.Expr parseLeftAssocBinaryOpExpr(TokenType[] types, ParseExpr innerParser) {
        MysqlxExpr.Expr lhs = innerParser.parseExpr();
        while (this.tokenPos < this.tokens.size() && Arrays.asList(types).contains((Object)this.tokens.get((int)this.tokenPos).type)) {
            MysqlxExpr.Operator.Builder builder = MysqlxExpr.Operator.newBuilder().setName(this.tokens.get((int)this.tokenPos).value).addParam(lhs);
            ++this.tokenPos;
            builder.addParam(innerParser.parseExpr());
            lhs = MysqlxExpr.Expr.newBuilder().setType(MysqlxExpr.Expr.Type.OPERATOR).setOperator(builder.build()).build();
        }
        return lhs;
    }

    MysqlxExpr.Expr addSubIntervalExpr() {
        MysqlxExpr.Expr lhs = this.atomicExpr();
        while ((this.currentTokenTypeEquals(TokenType.PLUS) || this.currentTokenTypeEquals(TokenType.MINUS)) && this.nextTokenTypeEquals(TokenType.INTERVAL)) {
            Token op = this.tokens.get(this.tokenPos);
            ++this.tokenPos;
            MysqlxExpr.Operator.Builder builder = MysqlxExpr.Operator.newBuilder().addParam(this.unquoteWorkaround(lhs));
            this.consumeToken(TokenType.INTERVAL);
            if (op.type == TokenType.PLUS) {
                builder.setName("date_add");
            } else {
                builder.setName("date_sub");
            }
            builder.addParam(this.unquoteWorkaround(this.bitExpr()));
            if (!(this.currentTokenTypeEquals(TokenType.MICROSECOND) || this.currentTokenTypeEquals(TokenType.SECOND) || this.currentTokenTypeEquals(TokenType.MINUTE) || this.currentTokenTypeEquals(TokenType.HOUR) || this.currentTokenTypeEquals(TokenType.DAY) || this.currentTokenTypeEquals(TokenType.WEEK) || this.currentTokenTypeEquals(TokenType.MONTH) || this.currentTokenTypeEquals(TokenType.QUARTER) || this.currentTokenTypeEquals(TokenType.YEAR) || this.currentTokenTypeEquals(TokenType.SECOND_MICROSECOND) || this.currentTokenTypeEquals(TokenType.MINUTE_MICROSECOND) || this.currentTokenTypeEquals(TokenType.MINUTE_SECOND) || this.currentTokenTypeEquals(TokenType.HOUR_MICROSECOND) || this.currentTokenTypeEquals(TokenType.HOUR_SECOND) || this.currentTokenTypeEquals(TokenType.HOUR_MINUTE) || this.currentTokenTypeEquals(TokenType.DAY_MICROSECOND) || this.currentTokenTypeEquals(TokenType.DAY_SECOND) || this.currentTokenTypeEquals(TokenType.DAY_MINUTE) || this.currentTokenTypeEquals(TokenType.DAY_HOUR) || this.currentTokenTypeEquals(TokenType.YEAR_MONTH))) {
                throw new WrongArgumentException("Expected interval units at " + this.tokenPos);
            }
            builder.addParam(ExprUtil.buildLiteralScalar(this.tokens.get((int)this.tokenPos).value.toUpperCase().getBytes()));
            ++this.tokenPos;
            lhs = MysqlxExpr.Expr.newBuilder().setType(MysqlxExpr.Expr.Type.OPERATOR).setOperator(builder.build()).build();
        }
        return lhs;
    }

    MysqlxExpr.Expr mulDivExpr() {
        return this.parseLeftAssocBinaryOpExpr(new TokenType[]{TokenType.STAR, TokenType.SLASH, TokenType.MOD}, this::addSubIntervalExpr);
    }

    MysqlxExpr.Expr addSubExpr() {
        return this.parseLeftAssocBinaryOpExpr(new TokenType[]{TokenType.PLUS, TokenType.MINUS}, this::mulDivExpr);
    }

    MysqlxExpr.Expr shiftExpr() {
        return this.parseLeftAssocBinaryOpExpr(new TokenType[]{TokenType.LSHIFT, TokenType.RSHIFT}, this::addSubExpr);
    }

    MysqlxExpr.Expr bitExpr() {
        return this.parseLeftAssocBinaryOpExpr(new TokenType[]{TokenType.BITAND, TokenType.BITOR, TokenType.BITXOR}, this::shiftExpr);
    }

    MysqlxExpr.Expr compExpr() {
        return this.parseLeftAssocBinaryOpExpr(new TokenType[]{TokenType.GE, TokenType.GT, TokenType.LE, TokenType.LT, TokenType.EQ, TokenType.NE}, this::bitExpr);
    }

    private MysqlxExpr.Expr unquoteWorkaround(MysqlxExpr.Expr e) {
        if (e.getType() == MysqlxExpr.Expr.Type.IDENT && e.getIdentifier().getDocumentPathList().size() > 0) {
            MysqlxExpr.Identifier id = MysqlxExpr.Identifier.newBuilder().setName("JSON_UNQUOTE").build();
            MysqlxExpr.FunctionCall.Builder b = MysqlxExpr.FunctionCall.newBuilder().setName(id).addParam(e);
            return MysqlxExpr.Expr.newBuilder().setType(MysqlxExpr.Expr.Type.FUNC_CALL).setFunctionCall(b.build()).build();
        }
        return e;
    }

    MysqlxExpr.Expr ilriExpr() {
        MysqlxExpr.Expr lhs = this.compExpr();
        List<TokenType> expected = Arrays.asList(TokenType.IS, TokenType.IN, TokenType.LIKE, TokenType.BETWEEN, TokenType.REGEXP, TokenType.NOT);
        while (this.tokenPos < this.tokens.size() && expected.contains((Object)this.tokens.get((int)this.tokenPos).type)) {
            boolean isNot = false;
            if (this.currentTokenTypeEquals(TokenType.NOT)) {
                this.consumeToken(TokenType.NOT);
                isNot = true;
            }
            if (this.tokenPos >= this.tokens.size()) continue;
            ArrayList<MysqlxExpr.Expr> params = new ArrayList<MysqlxExpr.Expr>();
            params.add(this.unquoteWorkaround(lhs));
            String opName = this.tokens.get((int)this.tokenPos).value.toLowerCase();
            switch (this.tokens.get((int)this.tokenPos).type) {
                case IS: {
                    this.consumeToken(TokenType.IS);
                    if (this.currentTokenTypeEquals(TokenType.NOT)) {
                        this.consumeToken(TokenType.NOT);
                        opName = "is_not";
                    }
                    params.add(this.compExpr());
                    break;
                }
                case IN: {
                    this.consumeToken(TokenType.IN);
                    if (this.currentTokenTypeEquals(TokenType.LPAREN)) {
                        params.addAll(this.parenExprList());
                        break;
                    }
                    if (!this.currentTokenTypeEquals(TokenType.LSQBRACKET)) {
                        opName = "cont_in";
                    }
                    params.add(this.compExpr());
                    break;
                }
                case LIKE: {
                    this.consumeToken(TokenType.LIKE);
                    params.add(this.unquoteWorkaround(this.compExpr()));
                    if (!this.currentTokenTypeEquals(TokenType.ESCAPE)) break;
                    this.consumeToken(TokenType.ESCAPE);
                    params.add(this.unquoteWorkaround(this.compExpr()));
                    break;
                }
                case BETWEEN: {
                    this.consumeToken(TokenType.BETWEEN);
                    params.add(this.compExpr());
                    this.assertTokenAt(this.tokenPos, TokenType.AND);
                    this.consumeToken(TokenType.AND);
                    params.add(this.compExpr());
                    break;
                }
                case REGEXP: {
                    this.consumeToken(TokenType.REGEXP);
                    params.add(this.compExpr());
                    break;
                }
                default: {
                    throw new WrongArgumentException("Unknown token after NOT at pos: " + this.tokenPos);
                }
            }
            if (isNot) {
                opName = "not_" + opName;
            }
            MysqlxExpr.Operator.Builder builder = MysqlxExpr.Operator.newBuilder().setName(opName).addAllParam(params);
            lhs = MysqlxExpr.Expr.newBuilder().setType(MysqlxExpr.Expr.Type.OPERATOR).setOperator(builder.build()).build();
        }
        return lhs;
    }

    MysqlxExpr.Expr andExpr() {
        return this.parseLeftAssocBinaryOpExpr(new TokenType[]{TokenType.AND, TokenType.ANDAND}, this::ilriExpr);
    }

    MysqlxExpr.Expr orExpr() {
        return this.parseLeftAssocBinaryOpExpr(new TokenType[]{TokenType.OR, TokenType.OROR}, this::andExpr);
    }

    MysqlxExpr.Expr expr() {
        MysqlxExpr.Expr e = this.orExpr();
        return e;
    }

    public MysqlxExpr.Expr parse() {
        try {
            MysqlxExpr.Expr e = this.expr();
            if (this.tokenPos != this.tokens.size()) {
                throw new WrongArgumentException("Only " + this.tokenPos + " tokens consumed, out of " + this.tokens.size());
            }
            return e;
        } catch (IllegalArgumentException ex) {
            throw new WrongArgumentException("Unable to parse query '" + this.string + "'", ex);
        }
    }

    private <T> List<T> parseCommaSeparatedList(Supplier<T> elementParser) {
        ArrayList<T> elements = new ArrayList<T>();
        boolean first = true;
        while (first || this.currentTokenTypeEquals(TokenType.COMMA)) {
            if (!first) {
                this.consumeToken(TokenType.COMMA);
            } else {
                first = false;
            }
            elements.add(elementParser.get());
        }
        return elements;
    }

    public List<MysqlxCrud.Order> parseOrderSpec() {
        return this.parseCommaSeparatedList(() -> {
            MysqlxCrud.Order.Builder builder = MysqlxCrud.Order.newBuilder();
            builder.setExpr(this.expr());
            if (this.currentTokenTypeEquals(TokenType.ORDERBY_ASC)) {
                this.consumeToken(TokenType.ORDERBY_ASC);
                builder.setDirection(MysqlxCrud.Order.Direction.ASC);
            } else if (this.currentTokenTypeEquals(TokenType.ORDERBY_DESC)) {
                this.consumeToken(TokenType.ORDERBY_DESC);
                builder.setDirection(MysqlxCrud.Order.Direction.DESC);
            }
            return builder.build();
        });
    }

    public List<MysqlxCrud.Projection> parseTableSelectProjection() {
        return this.parseCommaSeparatedList(() -> {
            MysqlxCrud.Projection.Builder builder = MysqlxCrud.Projection.newBuilder();
            builder.setSource(this.expr());
            if (this.currentTokenTypeEquals(TokenType.AS)) {
                this.consumeToken(TokenType.AS);
                builder.setAlias(this.consumeToken(TokenType.IDENT));
            }
            return builder.build();
        });
    }

    public MysqlxCrud.Column parseTableInsertField() {
        return MysqlxCrud.Column.newBuilder().setName(this.consumeToken(TokenType.IDENT)).build();
    }

    public MysqlxExpr.ColumnIdentifier parseTableUpdateField() {
        return this.columnIdentifier().getIdentifier();
    }

    public List<MysqlxCrud.Projection> parseDocumentProjection() {
        this.allowRelationalColumns = false;
        return this.parseCommaSeparatedList(() -> {
            MysqlxCrud.Projection.Builder builder = MysqlxCrud.Projection.newBuilder();
            builder.setSource(this.expr());
            this.consumeToken(TokenType.AS);
            builder.setAlias(this.consumeToken(TokenType.IDENT));
            return builder.build();
        });
    }

    public List<MysqlxExpr.Expr> parseExprList() {
        return this.parseCommaSeparatedList(this::expr);
    }

    public int getPositionalPlaceholderCount() {
        return this.positionalPlaceholderCount;
    }

    public Map<String, Integer> getPlaceholderNameToPositionMap() {
        return Collections.unmodifiableMap(this.placeholderNameToPosition);
    }

    static {
        reservedWords.put("and", TokenType.AND);
        reservedWords.put("or", TokenType.OR);
        reservedWords.put("xor", TokenType.XOR);
        reservedWords.put("is", TokenType.IS);
        reservedWords.put("not", TokenType.NOT);
        reservedWords.put("like", TokenType.LIKE);
        reservedWords.put("in", TokenType.IN);
        reservedWords.put("regexp", TokenType.REGEXP);
        reservedWords.put("between", TokenType.BETWEEN);
        reservedWords.put("interval", TokenType.INTERVAL);
        reservedWords.put("escape", TokenType.ESCAPE);
        reservedWords.put("div", TokenType.SLASH);
        reservedWords.put("hex", TokenType.HEX);
        reservedWords.put("bin", TokenType.BIN);
        reservedWords.put("true", TokenType.TRUE);
        reservedWords.put("false", TokenType.FALSE);
        reservedWords.put("null", TokenType.NULL);
        reservedWords.put("microsecond", TokenType.MICROSECOND);
        reservedWords.put("second", TokenType.SECOND);
        reservedWords.put("minute", TokenType.MINUTE);
        reservedWords.put("hour", TokenType.HOUR);
        reservedWords.put("day", TokenType.DAY);
        reservedWords.put("week", TokenType.WEEK);
        reservedWords.put("month", TokenType.MONTH);
        reservedWords.put("quarter", TokenType.QUARTER);
        reservedWords.put("year", TokenType.YEAR);
        reservedWords.put("second_microsecond", TokenType.SECOND_MICROSECOND);
        reservedWords.put("minute_microsecond", TokenType.MINUTE_MICROSECOND);
        reservedWords.put("minute_second", TokenType.MINUTE_SECOND);
        reservedWords.put("hour_microsecond", TokenType.HOUR_MICROSECOND);
        reservedWords.put("hour_second", TokenType.HOUR_SECOND);
        reservedWords.put("hour_minute", TokenType.HOUR_MINUTE);
        reservedWords.put("day_microsecond", TokenType.DAY_MICROSECOND);
        reservedWords.put("day_second", TokenType.DAY_SECOND);
        reservedWords.put("day_minute", TokenType.DAY_MINUTE);
        reservedWords.put("day_hour", TokenType.DAY_HOUR);
        reservedWords.put("year_month", TokenType.YEAR_MONTH);
        reservedWords.put("asc", TokenType.ORDERBY_ASC);
        reservedWords.put("desc", TokenType.ORDERBY_DESC);
        reservedWords.put("as", TokenType.AS);
        reservedWords.put("cast", TokenType.CAST);
        reservedWords.put("decimal", TokenType.DECIMAL);
        reservedWords.put("unsigned", TokenType.UNSIGNED);
        reservedWords.put("signed", TokenType.SIGNED);
        reservedWords.put("integer", TokenType.INTEGER);
        reservedWords.put("date", TokenType.DATE);
        reservedWords.put("time", TokenType.TIME);
        reservedWords.put("datetime", TokenType.DATETIME);
        reservedWords.put("char", TokenType.CHAR);
        reservedWords.put("binary", TokenType.BINARY);
        reservedWords.put("json", TokenType.BINARY);
    }

    @FunctionalInterface
    static interface ParseExpr {
        public MysqlxExpr.Expr parseExpr();
    }

    static class Token {
        TokenType type;
        String value;

        public Token(TokenType x, char c) {
            this.type = x;
            this.value = new String(new char[]{c});
        }

        public Token(TokenType t, String v) {
            this.type = t;
            this.value = v;
        }

        public String toString() {
            if (this.type == TokenType.IDENT || this.type == TokenType.LNUM_INT || this.type == TokenType.LNUM_DOUBLE || this.type == TokenType.LSTRING) {
                return this.type.toString() + "(" + this.value + ")";
            }
            return this.type.toString();
        }
    }

    private static enum TokenType {
        NOT,
        AND,
        ANDAND,
        OR,
        OROR,
        XOR,
        IS,
        LPAREN,
        RPAREN,
        LSQBRACKET,
        RSQBRACKET,
        BETWEEN,
        TRUE,
        NULL,
        FALSE,
        IN,
        LIKE,
        INTERVAL,
        REGEXP,
        ESCAPE,
        IDENT,
        LSTRING,
        LNUM_INT,
        LNUM_DOUBLE,
        DOT,
        DOLLAR,
        COMMA,
        EQ,
        NE,
        GT,
        GE,
        LT,
        LE,
        BITAND,
        BITOR,
        BITXOR,
        LSHIFT,
        RSHIFT,
        PLUS,
        MINUS,
        STAR,
        SLASH,
        HEX,
        BIN,
        NEG,
        BANG,
        EROTEME,
        MICROSECOND,
        SECOND,
        MINUTE,
        HOUR,
        DAY,
        WEEK,
        MONTH,
        QUARTER,
        YEAR,
        SECOND_MICROSECOND,
        MINUTE_MICROSECOND,
        MINUTE_SECOND,
        HOUR_MICROSECOND,
        HOUR_SECOND,
        HOUR_MINUTE,
        DAY_MICROSECOND,
        DAY_SECOND,
        DAY_MINUTE,
        DAY_HOUR,
        YEAR_MONTH,
        DOUBLESTAR,
        MOD,
        COLON,
        ORDERBY_ASC,
        ORDERBY_DESC,
        AS,
        LCURLY,
        RCURLY,
        DOTSTAR,
        CAST,
        DECIMAL,
        UNSIGNED,
        SIGNED,
        INTEGER,
        DATE,
        TIME,
        DATETIME,
        CHAR,
        BINARY,
        JSON,
        COLDOCPATH;

    }
}

