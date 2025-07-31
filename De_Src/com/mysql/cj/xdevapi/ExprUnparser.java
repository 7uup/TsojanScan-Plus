/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.mysql.cj.x.protobuf.MysqlxExpr;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ExprUnparser {
    static Set<String> infixOperators = new HashSet<String>();

    static String scalarToString(MysqlxDatatypes.Scalar e) {
        switch (e.getType()) {
            case V_SINT: {
                return "" + e.getVSignedInt();
            }
            case V_OCTETS: {
                return "\"" + ExprUnparser.escapeLiteral(e.getVOctets().getValue().toStringUtf8()) + "\"";
            }
            case V_STRING: {
                return "\"" + ExprUnparser.escapeLiteral(e.getVString().getValue().toStringUtf8()) + "\"";
            }
            case V_DOUBLE: {
                return "" + e.getVDouble();
            }
            case V_BOOL: {
                return e.getVBool() ? "TRUE" : "FALSE";
            }
            case V_NULL: {
                return "NULL";
            }
        }
        throw new IllegalArgumentException("Unknown type tag: " + e.getType());
    }

    static String documentPathToString(List<MysqlxExpr.DocumentPathItem> items) {
        StringBuilder docPathString = new StringBuilder();
        for (MysqlxExpr.DocumentPathItem item : items) {
            switch (item.getType()) {
                case MEMBER: {
                    docPathString.append(".").append(ExprUnparser.quoteDocumentPathMember(item.getValue()));
                    break;
                }
                case MEMBER_ASTERISK: {
                    docPathString.append(".*");
                    break;
                }
                case ARRAY_INDEX: {
                    docPathString.append("[").append("" + Integer.toUnsignedLong(item.getIndex())).append("]");
                    break;
                }
                case ARRAY_INDEX_ASTERISK: {
                    docPathString.append("[*]");
                    break;
                }
                case DOUBLE_ASTERISK: {
                    docPathString.append("**");
                }
            }
        }
        return docPathString.toString();
    }

    static String columnIdentifierToString(MysqlxExpr.ColumnIdentifier e) {
        if (e.hasName()) {
            String s2 = ExprUnparser.quoteIdentifier(e.getName());
            if (e.hasTableName()) {
                s2 = ExprUnparser.quoteIdentifier(e.getTableName()) + "." + s2;
            }
            if (e.hasSchemaName()) {
                s2 = ExprUnparser.quoteIdentifier(e.getSchemaName()) + "." + s2;
            }
            if (e.getDocumentPathCount() > 0) {
                s2 = s2 + "->$" + ExprUnparser.documentPathToString(e.getDocumentPathList());
            }
            return s2;
        }
        return "$" + ExprUnparser.documentPathToString(e.getDocumentPathList());
    }

    static String functionCallToString(MysqlxExpr.FunctionCall e) {
        MysqlxExpr.Identifier i = e.getName();
        String s2 = ExprUnparser.quoteIdentifier(i.getName());
        if (i.hasSchemaName()) {
            s2 = ExprUnparser.quoteIdentifier(i.getSchemaName()) + "." + s2;
        }
        s2 = s2 + "(";
        for (MysqlxExpr.Expr p : e.getParamList()) {
            s2 = s2 + ExprUnparser.exprToString(p) + ", ";
        }
        s2 = s2.replaceAll(", $", "");
        s2 = s2 + ")";
        return s2;
    }

    static String paramListToString(List<String> params) {
        String s2 = "(";
        boolean first = true;
        for (String param : params) {
            if (!first) {
                s2 = s2 + ", ";
            }
            first = false;
            s2 = s2 + param;
        }
        return s2 + ")";
    }

    static String operatorToString(MysqlxExpr.Operator e) {
        String name = e.getName();
        ArrayList<String> params = new ArrayList<String>();
        for (MysqlxExpr.Expr p : e.getParamList()) {
            params.add(ExprUnparser.exprToString(p));
        }
        if ("between".equals(name) || "not_between".equals(name)) {
            name = name.replaceAll("not_between", "not between");
            return String.format("(%s %s %s AND %s)", params.get(0), name, params.get(1), params.get(2));
        }
        if ("in".equals(name) || "not_in".equals(name)) {
            name = name.replaceAll("not_in", "not in");
            return String.format("%s %s%s", params.get(0), name, ExprUnparser.paramListToString(params.subList(1, params.size())));
        }
        if ("like".equals(name) || "not_like".equals(name)) {
            name = name.replaceAll("not_like", "not like");
            String s2 = String.format("%s %s %s", params.get(0), name, params.get(1));
            if (params.size() == 3) {
                s2 = s2 + " ESCAPE " + (String)params.get(2);
            }
            return s2;
        }
        if ("regexp".equals(name) || "not_regexp".equals("name")) {
            name = name.replaceAll("not_regexp", "not regexp");
            return String.format("(%s %s %s)", params.get(0), name, params.get(1));
        }
        if ("cast".equals(name)) {
            return String.format("cast(%s AS %s)", params.get(0), ((String)params.get(1)).replaceAll("\"", ""));
        }
        if ((name.length() < 3 || infixOperators.contains(name)) && params.size() == 2) {
            return String.format("(%s %s %s)", params.get(0), name, params.get(1));
        }
        if (params.size() == 1) {
            return String.format("%s%s", name, params.get(0));
        }
        if (params.size() == 0) {
            return name;
        }
        return name + ExprUnparser.paramListToString(params);
    }

    static String objectToString(MysqlxExpr.Object o) {
        String fields = o.getFldList().stream().map(f -> "'" + ExprUnparser.quoteJsonKey(f.getKey()) + "'" + ":" + ExprUnparser.exprToString(f.getValue())).collect(Collectors.joining(", "));
        return "{" + fields + "}";
    }

    public static String escapeLiteral(String s2) {
        return s2.replaceAll("\"", "\"\"");
    }

    public static String quoteIdentifier(String ident) {
        if (ident.contains("`") || ident.contains("\"") || ident.contains("'") || ident.contains("$") || ident.contains(".") || ident.contains("-")) {
            return "`" + ident.replaceAll("`", "``") + "`";
        }
        return ident;
    }

    public static String quoteJsonKey(String key) {
        return key.replaceAll("'", "\\\\'");
    }

    public static String quoteDocumentPathMember(String member) {
        if (!member.matches("[a-zA-Z0-9_]*")) {
            return "\"" + member.replaceAll("\"", "\\\\\"") + "\"";
        }
        return member;
    }

    public static String exprToString(MysqlxExpr.Expr e) {
        switch (e.getType()) {
            case LITERAL: {
                return ExprUnparser.scalarToString(e.getLiteral());
            }
            case IDENT: {
                return ExprUnparser.columnIdentifierToString(e.getIdentifier());
            }
            case FUNC_CALL: {
                return ExprUnparser.functionCallToString(e.getFunctionCall());
            }
            case OPERATOR: {
                return ExprUnparser.operatorToString(e.getOperator());
            }
            case PLACEHOLDER: {
                return ":" + Integer.toUnsignedLong(e.getPosition());
            }
            case OBJECT: {
                return ExprUnparser.objectToString(e.getObject());
            }
        }
        throw new IllegalArgumentException("Unknown type tag: " + e.getType());
    }

    static {
        infixOperators.add("and");
        infixOperators.add("or");
    }
}

