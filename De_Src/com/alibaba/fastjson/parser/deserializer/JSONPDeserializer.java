/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.SymbolTable;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.lang.reflect.Type;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class JSONPDeserializer
implements ObjectDeserializer {
    public static final JSONPDeserializer instance = new JSONPDeserializer();

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        JSONLexerBase lexer = (JSONLexerBase)parser.getLexer();
        SymbolTable symbolTable = parser.getSymbolTable();
        String funcName = lexer.scanSymbolUnQuoted(symbolTable);
        lexer.nextToken();
        int tok = lexer.token();
        if (tok == 25) {
            String name = lexer.scanSymbolUnQuoted(parser.getSymbolTable());
            funcName = funcName + ".";
            funcName = funcName + name;
            lexer.nextToken();
            tok = lexer.token();
        }
        JSONPObject jsonp = new JSONPObject(funcName);
        if (tok != 10) {
            throw new JSONException("illegal jsonp : " + lexer.info());
        }
        lexer.nextToken();
        while (true) {
            Object arg = parser.parse();
            jsonp.addParameter(arg);
            tok = lexer.token();
            if (tok != 16) break;
            lexer.nextToken();
        }
        if (tok != 11) {
            throw new JSONException("illegal jsonp : " + lexer.info());
        }
        lexer.nextToken();
        tok = lexer.token();
        if (tok == 24) {
            lexer.nextToken();
        }
        return (T)jsonp;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}

