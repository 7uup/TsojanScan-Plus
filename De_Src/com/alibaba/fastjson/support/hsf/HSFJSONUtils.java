/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.support.hsf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.SymbolTable;
import com.alibaba.fastjson.support.hsf.MethodLocator;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class HSFJSONUtils {
    static final SymbolTable typeSymbolTable = new SymbolTable(1024);
    static final char[] fieldName_argsTypes = "\"argsTypes\"".toCharArray();
    static final char[] fieldName_argsObjs = "\"argsObjs\"".toCharArray();
    static final char[] fieldName_type = "\"@type\":".toCharArray();

    public static Object[] parseInvocationArguments(String json, MethodLocator methodLocator) {
        Object[] values2;
        DefaultJSONParser parser = new DefaultJSONParser(json);
        JSONLexerBase lexer = (JSONLexerBase)parser.getLexer();
        ParseContext rootContext = parser.setContext(null, null);
        int token = lexer.token();
        if (token == 12) {
            Method method;
            String type;
            String[] typeNames = lexer.scanFieldStringArray(fieldName_argsTypes, -1, typeSymbolTable);
            if (typeNames == null && lexer.matchStat == -2 && "com.alibaba.fastjson.JSONObject".equals(type = lexer.scanFieldString(fieldName_type))) {
                typeNames = lexer.scanFieldStringArray(fieldName_argsTypes, -1, typeSymbolTable);
            }
            if ((method = methodLocator.findMethod(typeNames)) == null) {
                lexer.close();
                JSONObject jsonObject = JSON.parseObject(json);
                typeNames = jsonObject.getObject("argsTypes", String[].class);
                method = methodLocator.findMethod(typeNames);
                JSONArray argsObjs = jsonObject.getJSONArray("argsObjs");
                if (argsObjs == null) {
                    values2 = null;
                } else {
                    Type[] argTypes = method.getGenericParameterTypes();
                    values2 = new Object[argTypes.length];
                    for (int i = 0; i < argTypes.length; ++i) {
                        Type type2 = argTypes[i];
                        values2[i] = argsObjs.getObject(i, type2);
                    }
                }
            } else {
                Type[] argTypes = method.getGenericParameterTypes();
                lexer.skipWhitespace();
                if (lexer.getCurrent() == ',') {
                    lexer.next();
                }
                if (lexer.matchField2(fieldName_argsObjs)) {
                    lexer.nextToken();
                    ParseContext context = parser.setContext(rootContext, null, "argsObjs");
                    context.object = values2 = parser.parseArray(argTypes);
                    parser.accept(13);
                    parser.handleResovleTask(null);
                } else {
                    values2 = null;
                }
                parser.close();
            }
        } else if (token == 14) {
            String[] typeNames = lexer.scanFieldStringArray(null, -1, typeSymbolTable);
            lexer.skipWhitespace();
            char ch = lexer.getCurrent();
            if (ch == ']') {
                Method method = methodLocator.findMethod(null);
                Type[] argTypes = method.getGenericParameterTypes();
                Object[] values3 = new Object[typeNames.length];
                for (int i = 0; i < typeNames.length; ++i) {
                    Type argType = argTypes[i];
                    String typeName = typeNames[i];
                    values3[i] = argType != String.class ? TypeUtils.cast((Object)typeName, argType, parser.getConfig()) : typeName;
                }
                return values3;
            }
            if (ch == ',') {
                lexer.next();
                lexer.skipWhitespace();
            }
            lexer.nextToken(14);
            Method method = methodLocator.findMethod(typeNames);
            Type[] argTypes = method.getGenericParameterTypes();
            values2 = parser.parseArray(argTypes);
            lexer.close();
        } else {
            values2 = null;
        }
        return values2;
    }
}

