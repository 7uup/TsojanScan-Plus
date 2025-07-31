/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.Capabilities;
import bsh.InterpreterError;
import bsh.Primitive;
import bsh.Reflect;
import bsh.This;
import bsh.UtilEvalError;
import bsh.UtilTargetError;

class Types {
    static final int CAST = 0;
    static final int ASSIGNMENT = 1;
    static final int JAVA_BASE_ASSIGNABLE = 1;
    static final int JAVA_BOX_TYPES_ASSIGABLE = 2;
    static final int JAVA_VARARGS_ASSIGNABLE = 3;
    static final int BSH_ASSIGNABLE = 4;
    static final int FIRST_ROUND_ASSIGNABLE = 1;
    static final int LAST_ROUND_ASSIGNABLE = 4;
    static Primitive VALID_CAST = new Primitive(1);
    static Primitive INVALID_CAST = new Primitive(-1);

    Types() {
    }

    public static Class[] getTypes(Object[] args2) {
        if (args2 == null) {
            return new Class[0];
        }
        Class[] types = new Class[args2.length];
        for (int i = 0; i < args2.length; ++i) {
            types[i] = args2[i] == null ? null : (args2[i] instanceof Primitive ? ((Primitive)args2[i]).getType() : args2[i].getClass());
        }
        return types;
    }

    static boolean isSignatureAssignable(Class[] from, Class[] to, int round) {
        if (round != 3 && from.length != to.length) {
            return false;
        }
        switch (round) {
            case 1: {
                for (int i = 0; i < from.length; ++i) {
                    if (Types.isJavaBaseAssignable(to[i], from[i])) continue;
                    return false;
                }
                return true;
            }
            case 2: {
                for (int i = 0; i < from.length; ++i) {
                    if (Types.isJavaBoxTypesAssignable(to[i], from[i])) continue;
                    return false;
                }
                return true;
            }
            case 3: {
                return Types.isSignatureVarargsAssignable(from, to);
            }
            case 4: {
                for (int i = 0; i < from.length; ++i) {
                    if (Types.isBshAssignable(to[i], from[i])) continue;
                    return false;
                }
                return true;
            }
        }
        throw new InterpreterError("bad case");
    }

    private static boolean isSignatureVarargsAssignable(Class[] from, Class[] to) {
        return false;
    }

    static boolean isJavaAssignable(Class lhsType, Class rhsType) {
        return Types.isJavaBaseAssignable(lhsType, rhsType) || Types.isJavaBoxTypesAssignable(lhsType, rhsType);
    }

    static boolean isJavaBaseAssignable(Class lhsType, Class rhsType) {
        if (lhsType == null) {
            return false;
        }
        if (rhsType == null) {
            return !lhsType.isPrimitive();
        }
        if (lhsType.isPrimitive() && rhsType.isPrimitive()) {
            if (lhsType == rhsType) {
                return true;
            }
            if (rhsType == Byte.TYPE && (lhsType == Short.TYPE || lhsType == Integer.TYPE || lhsType == Long.TYPE || lhsType == Float.TYPE || lhsType == Double.TYPE)) {
                return true;
            }
            if (rhsType == Short.TYPE && (lhsType == Integer.TYPE || lhsType == Long.TYPE || lhsType == Float.TYPE || lhsType == Double.TYPE)) {
                return true;
            }
            if (rhsType == Character.TYPE && (lhsType == Integer.TYPE || lhsType == Long.TYPE || lhsType == Float.TYPE || lhsType == Double.TYPE)) {
                return true;
            }
            if (rhsType == Integer.TYPE && (lhsType == Long.TYPE || lhsType == Float.TYPE || lhsType == Double.TYPE)) {
                return true;
            }
            if (rhsType == Long.TYPE && (lhsType == Float.TYPE || lhsType == Double.TYPE)) {
                return true;
            }
            if (rhsType == Float.TYPE && lhsType == Double.TYPE) {
                return true;
            }
        } else if (lhsType.isAssignableFrom(rhsType)) {
            return true;
        }
        return false;
    }

    static boolean isJavaBoxTypesAssignable(Class lhsType, Class rhsType) {
        if (lhsType == null) {
            return false;
        }
        if (lhsType == Object.class) {
            return true;
        }
        if (lhsType == Number.class && rhsType != Character.TYPE && rhsType != Boolean.TYPE) {
            return true;
        }
        return Primitive.wrapperMap.get(lhsType) == rhsType;
    }

    static boolean isBshAssignable(Class toType, Class fromType) {
        try {
            return Types.castObject(toType, fromType, null, 1, true) == VALID_CAST;
        } catch (UtilEvalError e) {
            throw new InterpreterError("err in cast check: " + e);
        }
    }

    public static Object castObject(Object fromValue, Class toType, int operation) throws UtilEvalError {
        if (fromValue == null) {
            throw new InterpreterError("null fromValue");
        }
        Class fromType = fromValue instanceof Primitive ? ((Primitive)fromValue).getType() : fromValue.getClass();
        return Types.castObject(toType, fromType, fromValue, operation, false);
    }

    private static Object castObject(Class toType, Class fromType, Object fromValue, int operation, boolean checkOnly) throws UtilEvalError {
        if (checkOnly && fromValue != null) {
            throw new InterpreterError("bad cast params 1");
        }
        if (!checkOnly && fromValue == null) {
            throw new InterpreterError("bad cast params 2");
        }
        if (fromType == Primitive.class) {
            throw new InterpreterError("bad from Type, need to unwrap");
        }
        if (fromValue == Primitive.NULL && fromType != null) {
            throw new InterpreterError("inconsistent args 1");
        }
        if (fromValue == Primitive.VOID && fromType != Void.TYPE) {
            throw new InterpreterError("inconsistent args 2");
        }
        if (toType == Void.TYPE) {
            throw new InterpreterError("loose toType should be null");
        }
        if (toType == null || toType == fromType) {
            return checkOnly ? VALID_CAST : fromValue;
        }
        if (toType.isPrimitive()) {
            if (fromType == Void.TYPE || fromType == null || fromType.isPrimitive()) {
                return Primitive.castPrimitive(toType, fromType, (Primitive)fromValue, checkOnly, operation);
            }
            if (Primitive.isWrapperType(fromType)) {
                Class unboxedFromType = Primitive.unboxType(fromType);
                Primitive primFromValue = checkOnly ? null : (Primitive)Primitive.wrap(fromValue, unboxedFromType);
                return Primitive.castPrimitive(toType, unboxedFromType, primFromValue, checkOnly, operation);
            }
            if (checkOnly) {
                return INVALID_CAST;
            }
            throw Types.castError(toType, fromType, operation);
        }
        if (fromType == Void.TYPE || fromType == null || fromType.isPrimitive()) {
            if (Primitive.isWrapperType(toType) && fromType != Void.TYPE && fromType != null) {
                return checkOnly ? VALID_CAST : Primitive.castWrapper(Primitive.unboxType(toType), ((Primitive)fromValue).getValue());
            }
            if (toType == Object.class && fromType != Void.TYPE && fromType != null) {
                return checkOnly ? VALID_CAST : ((Primitive)fromValue).getValue();
            }
            return Primitive.castPrimitive(toType, fromType, (Primitive)fromValue, checkOnly, operation);
        }
        if (toType.isAssignableFrom(fromType)) {
            return checkOnly ? VALID_CAST : fromValue;
        }
        if (toType.isInterface() && This.class.isAssignableFrom(fromType) && Capabilities.canGenerateInterfaces()) {
            return checkOnly ? VALID_CAST : ((This)fromValue).getInterface(toType);
        }
        if (Primitive.isWrapperType(toType) && Primitive.isWrapperType(fromType)) {
            return checkOnly ? VALID_CAST : Primitive.castWrapper(toType, fromValue);
        }
        if (checkOnly) {
            return INVALID_CAST;
        }
        throw Types.castError(toType, fromType, operation);
    }

    static UtilEvalError castError(Class lhsType, Class rhsType, int operation) {
        return Types.castError(Reflect.normalizeClassName(lhsType), Reflect.normalizeClassName(rhsType), operation);
    }

    static UtilEvalError castError(String lhs, String rhs, int operation) {
        if (operation == 1) {
            return new UtilEvalError("Can't assign " + rhs + " to " + lhs);
        }
        ClassCastException cce = new ClassCastException("Cannot cast " + rhs + " to " + lhs);
        return new UtilTargetError(cce);
    }
}

