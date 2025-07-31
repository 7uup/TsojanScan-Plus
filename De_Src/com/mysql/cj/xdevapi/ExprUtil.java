/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.google.protobuf.ByteString;
import com.mysql.cj.exceptions.FeatureNotAvailableException;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.mysql.cj.x.protobuf.MysqlxExpr;
import com.mysql.cj.xdevapi.DbDoc;
import com.mysql.cj.xdevapi.ExprParser;
import com.mysql.cj.xdevapi.Expression;
import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonNumber;
import com.mysql.cj.xdevapi.JsonString;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

public class ExprUtil {
    private static SimpleDateFormat javaSqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat javaSqlTimestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S");
    private static SimpleDateFormat javaSqlTimeFormat = new SimpleDateFormat("HH:mm:ss.S");
    private static SimpleDateFormat javaUtilDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S");

    public static MysqlxExpr.Expr buildLiteralNullScalar() {
        return ExprUtil.buildLiteralExpr(ExprUtil.nullScalar());
    }

    public static MysqlxExpr.Expr buildLiteralScalar(double d) {
        return ExprUtil.buildLiteralExpr(ExprUtil.scalarOf(d));
    }

    public static MysqlxExpr.Expr buildLiteralScalar(long l) {
        return ExprUtil.buildLiteralExpr(ExprUtil.scalarOf(l));
    }

    public static MysqlxExpr.Expr buildLiteralScalar(String str) {
        return ExprUtil.buildLiteralExpr(ExprUtil.scalarOf(str));
    }

    public static MysqlxExpr.Expr buildLiteralScalar(byte[] bytes) {
        return ExprUtil.buildLiteralExpr(ExprUtil.scalarOf(bytes));
    }

    public static MysqlxExpr.Expr buildLiteralScalar(boolean b) {
        return ExprUtil.buildLiteralExpr(ExprUtil.scalarOf(b));
    }

    public static MysqlxExpr.Expr buildLiteralExpr(MysqlxDatatypes.Scalar scalar) {
        return MysqlxExpr.Expr.newBuilder().setType(MysqlxExpr.Expr.Type.LITERAL).setLiteral(scalar).build();
    }

    public static MysqlxDatatypes.Scalar nullScalar() {
        return MysqlxDatatypes.Scalar.newBuilder().setType(MysqlxDatatypes.Scalar.Type.V_NULL).build();
    }

    public static MysqlxDatatypes.Scalar scalarOf(double d) {
        return MysqlxDatatypes.Scalar.newBuilder().setType(MysqlxDatatypes.Scalar.Type.V_DOUBLE).setVDouble(d).build();
    }

    public static MysqlxDatatypes.Scalar scalarOf(long l) {
        return MysqlxDatatypes.Scalar.newBuilder().setType(MysqlxDatatypes.Scalar.Type.V_SINT).setVSignedInt(l).build();
    }

    public static MysqlxDatatypes.Scalar scalarOf(String str) {
        MysqlxDatatypes.Scalar.String sstr = MysqlxDatatypes.Scalar.String.newBuilder().setValue(ByteString.copyFromUtf8(str)).build();
        return MysqlxDatatypes.Scalar.newBuilder().setType(MysqlxDatatypes.Scalar.Type.V_STRING).setVString(sstr).build();
    }

    public static MysqlxDatatypes.Scalar scalarOf(byte[] bytes) {
        MysqlxDatatypes.Scalar.Octets.Builder o = MysqlxDatatypes.Scalar.Octets.newBuilder().setValue(ByteString.copyFrom(bytes));
        return MysqlxDatatypes.Scalar.newBuilder().setType(MysqlxDatatypes.Scalar.Type.V_OCTETS).setVOctets(o).build();
    }

    public static MysqlxDatatypes.Scalar scalarOf(boolean b) {
        return MysqlxDatatypes.Scalar.newBuilder().setType(MysqlxDatatypes.Scalar.Type.V_BOOL).setVBool(b).build();
    }

    public static MysqlxDatatypes.Any buildAny(String str) {
        MysqlxDatatypes.Scalar.String sstr = MysqlxDatatypes.Scalar.String.newBuilder().setValue(ByteString.copyFromUtf8(str)).build();
        MysqlxDatatypes.Scalar s2 = MysqlxDatatypes.Scalar.newBuilder().setType(MysqlxDatatypes.Scalar.Type.V_STRING).setVString(sstr).build();
        MysqlxDatatypes.Any a = MysqlxDatatypes.Any.newBuilder().setType(MysqlxDatatypes.Any.Type.SCALAR).setScalar(s2).build();
        return a;
    }

    public static MysqlxDatatypes.Any buildAny(boolean b) {
        return MysqlxDatatypes.Any.newBuilder().setType(MysqlxDatatypes.Any.Type.SCALAR).setScalar(ExprUtil.scalarOf(b)).build();
    }

    public static MysqlxCrud.Collection buildCollection(String schemaName, String collectionName) {
        return MysqlxCrud.Collection.newBuilder().setSchema(schemaName).setName(collectionName).build();
    }

    public static MysqlxDatatypes.Scalar argObjectToScalar(Object value) {
        MysqlxExpr.Expr e = ExprUtil.argObjectToExpr(value, false);
        if (!e.hasLiteral()) {
            throw new WrongArgumentException("No literal interpretation of argument: " + value);
        }
        return e.getLiteral();
    }

    public static MysqlxDatatypes.Any argObjectToScalarAny(Object value) {
        MysqlxDatatypes.Scalar s2 = ExprUtil.argObjectToScalar(value);
        return MysqlxDatatypes.Any.newBuilder().setType(MysqlxDatatypes.Any.Type.SCALAR).setScalar(s2).build();
    }

    public static MysqlxExpr.Expr argObjectToExpr(Object value, boolean allowRelationalColumns) {
        if (value == null) {
            return ExprUtil.buildLiteralNullScalar();
        }
        if (value.getClass() == Boolean.class) {
            return ExprUtil.buildLiteralScalar((Boolean)value);
        }
        if (value.getClass() == Byte.class) {
            return ExprUtil.buildLiteralScalar(((Byte)value).longValue());
        }
        if (value.getClass() == Short.class) {
            return ExprUtil.buildLiteralScalar(((Short)value).longValue());
        }
        if (value.getClass() == Integer.class) {
            return ExprUtil.buildLiteralScalar(((Integer)value).longValue());
        }
        if (value.getClass() == Long.class) {
            return ExprUtil.buildLiteralScalar((Long)value);
        }
        if (value.getClass() == Float.class) {
            return ExprUtil.buildLiteralScalar(((Float)value).doubleValue());
        }
        if (value.getClass() == Double.class) {
            return ExprUtil.buildLiteralScalar((Double)value);
        }
        if (value.getClass() == String.class) {
            return ExprUtil.buildLiteralScalar((String)value);
        }
        if (value.getClass() == Expression.class) {
            return new ExprParser(((Expression)value).getExpressionString(), allowRelationalColumns).parse();
        }
        if (value.getClass() == Date.class) {
            return ExprUtil.buildLiteralScalar(javaSqlDateFormat.format((java.util.Date)value));
        }
        if (value.getClass() == Time.class) {
            return ExprUtil.buildLiteralScalar(javaSqlTimeFormat.format((java.util.Date)value));
        }
        if (value.getClass() == Timestamp.class) {
            return ExprUtil.buildLiteralScalar(javaSqlTimestampFormat.format((java.util.Date)value));
        }
        if (value.getClass() == java.util.Date.class) {
            return ExprUtil.buildLiteralScalar(javaUtilDateFormat.format((java.util.Date)value));
        }
        if (DbDoc.class.isAssignableFrom(value.getClass())) {
            return new ExprParser(((DbDoc)value).toString()).parse();
        }
        if (value.getClass() == JsonArray.class) {
            return MysqlxExpr.Expr.newBuilder().setType(MysqlxExpr.Expr.Type.ARRAY).setArray(MysqlxExpr.Expr.newBuilder().setType(MysqlxExpr.Expr.Type.ARRAY).getArrayBuilder().addAllValue(((JsonArray)value).stream().map(f -> ExprUtil.argObjectToExpr(f, true)).collect(Collectors.toList()))).build();
        }
        if (value.getClass() == JsonString.class) {
            return ExprUtil.buildLiteralScalar(((JsonString)value).getString());
        }
        if (value.getClass() == JsonNumber.class) {
            return ExprUtil.buildLiteralScalar(((JsonNumber)value).getInteger().intValue());
        }
        throw new FeatureNotAvailableException("TODO: other types: BigDecimal");
    }
}

