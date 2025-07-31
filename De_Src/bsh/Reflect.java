/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BshClassManager;
import bsh.CallStack;
import bsh.Capabilities;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.InterpreterError;
import bsh.LHS;
import bsh.Primitive;
import bsh.ReflectError;
import bsh.ReflectManager;
import bsh.SimpleNode;
import bsh.StringUtil;
import bsh.This;
import bsh.Types;
import bsh.UtilEvalError;
import bsh.UtilTargetError;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Vector;

class Reflect {
    Reflect() {
    }

    public static Object invokeObjectMethod(Object object, String methodName, Object[] args2, Interpreter interpreter, CallStack callstack, SimpleNode callerInfo) throws ReflectError, EvalError, InvocationTargetException {
        if (object instanceof This && !This.isExposedThisMethod(methodName)) {
            return ((This)object).invokeMethod(methodName, args2, interpreter, callstack, callerInfo, false);
        }
        try {
            BshClassManager bcm = interpreter == null ? null : interpreter.getClassManager();
            Class<?> clas = object.getClass();
            Method method = Reflect.resolveExpectedJavaMethod(bcm, clas, object, methodName, args2, false);
            return Reflect.invokeMethod(method, object, args2);
        } catch (UtilEvalError e) {
            throw e.toEvalError(callerInfo, callstack);
        }
    }

    public static Object invokeStaticMethod(BshClassManager bcm, Class clas, String methodName, Object[] args2) throws ReflectError, UtilEvalError, InvocationTargetException {
        Interpreter.debug("invoke static Method");
        Method method = Reflect.resolveExpectedJavaMethod(bcm, clas, null, methodName, args2, true);
        return Reflect.invokeMethod(method, null, args2);
    }

    static Object invokeMethod(Method method, Object object, Object[] args2) throws ReflectError, InvocationTargetException {
        if (args2 == null) {
            args2 = new Object[]{};
        }
        Reflect.logInvokeMethod("Invoking method (entry): ", method, args2);
        Object[] tmpArgs = new Object[args2.length];
        Class<?>[] types = method.getParameterTypes();
        try {
            for (int i = 0; i < args2.length; ++i) {
                tmpArgs[i] = Types.castObject(args2[i], types[i], 1);
            }
        } catch (UtilEvalError e) {
            throw new InterpreterError("illegal argument type in method invocation: " + e);
        }
        tmpArgs = Primitive.unwrap(tmpArgs);
        Reflect.logInvokeMethod("Invoking method (after massaging values): ", method, tmpArgs);
        try {
            Object returnValue = method.invoke(object, tmpArgs);
            if (returnValue == null) {
                returnValue = Primitive.NULL;
            }
            Class<?> returnType = method.getReturnType();
            return Primitive.wrap(returnValue, returnType);
        } catch (IllegalAccessException e) {
            throw new ReflectError("Cannot access method " + StringUtil.methodString(method.getName(), method.getParameterTypes()) + " in '" + method.getDeclaringClass() + "' :" + e);
        }
    }

    public static Object getIndex(Object array, int index) throws ReflectError, UtilTargetError {
        if (Interpreter.DEBUG) {
            Interpreter.debug("getIndex: " + array + ", index=" + index);
        }
        try {
            Object val = Array.get(array, index);
            return Primitive.wrap(val, array.getClass().getComponentType());
        } catch (ArrayIndexOutOfBoundsException e1) {
            throw new UtilTargetError(e1);
        } catch (Exception e) {
            throw new ReflectError("Array access:" + e);
        }
    }

    public static void setIndex(Object array, int index, Object val) throws ReflectError, UtilTargetError {
        try {
            val = Primitive.unwrap(val);
            Array.set(array, index, val);
        } catch (ArrayStoreException e2) {
            throw new UtilTargetError(e2);
        } catch (IllegalArgumentException e1) {
            throw new UtilTargetError(new ArrayStoreException(e1.toString()));
        } catch (Exception e) {
            throw new ReflectError("Array access:" + e);
        }
    }

    public static Object getStaticFieldValue(Class clas, String fieldName) throws UtilEvalError, ReflectError {
        return Reflect.getFieldValue(clas, null, fieldName, true);
    }

    public static Object getObjectFieldValue(Object object, String fieldName) throws UtilEvalError, ReflectError {
        if (object instanceof This) {
            return ((This)object).namespace.getVariable(fieldName);
        }
        try {
            return Reflect.getFieldValue(object.getClass(), object, fieldName, false);
        } catch (ReflectError e) {
            if (Reflect.hasObjectPropertyGetter(object.getClass(), fieldName)) {
                return Reflect.getObjectProperty(object, fieldName);
            }
            throw e;
        }
    }

    static LHS getLHSStaticField(Class clas, String fieldName) throws UtilEvalError, ReflectError {
        Field f = Reflect.resolveExpectedJavaField(clas, fieldName, true);
        return new LHS(f);
    }

    static LHS getLHSObjectField(Object object, String fieldName) throws UtilEvalError, ReflectError {
        if (object instanceof This) {
            boolean recurse = false;
            return new LHS(((This)object).namespace, fieldName, recurse);
        }
        try {
            Field f = Reflect.resolveExpectedJavaField(object.getClass(), fieldName, false);
            return new LHS(object, f);
        } catch (ReflectError e) {
            if (Reflect.hasObjectPropertySetter(object.getClass(), fieldName)) {
                return new LHS(object, fieldName);
            }
            throw e;
        }
    }

    private static Object getFieldValue(Class clas, Object object, String fieldName, boolean staticOnly) throws UtilEvalError, ReflectError {
        try {
            Field f = Reflect.resolveExpectedJavaField(clas, fieldName, staticOnly);
            Object value = f.get(object);
            Class<?> returnType = f.getType();
            return Primitive.wrap(value, returnType);
        } catch (NullPointerException e) {
            throw new ReflectError("???" + fieldName + " is not a static field.");
        } catch (IllegalAccessException e) {
            throw new ReflectError("Can't access field: " + fieldName);
        }
    }

    protected static Field resolveJavaField(Class clas, String fieldName, boolean staticOnly) throws UtilEvalError {
        try {
            return Reflect.resolveExpectedJavaField(clas, fieldName, staticOnly);
        } catch (ReflectError e) {
            return null;
        }
    }

    protected static Field resolveExpectedJavaField(Class clas, String fieldName, boolean staticOnly) throws UtilEvalError, ReflectError {
        Field field;
        try {
            field = Capabilities.haveAccessibility() ? Reflect.findAccessibleField(clas, fieldName) : clas.getField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new ReflectError("No such field: " + fieldName);
        } catch (SecurityException e) {
            throw new UtilTargetError("Security Exception while searching fields of: " + clas, e);
        }
        if (staticOnly && !Modifier.isStatic(field.getModifiers())) {
            throw new UtilEvalError("Can't reach instance field: " + fieldName + " from static context: " + clas.getName());
        }
        return field;
    }

    private static Field findAccessibleField(Class clas, String fieldName) throws UtilEvalError, NoSuchFieldException {
        try {
            Field field = clas.getField(fieldName);
            ReflectManager.RMSetAccessible(field);
            return field;
        } catch (NoSuchFieldException e) {
            while (clas != null) {
                try {
                    Field field = clas.getDeclaredField(fieldName);
                    ReflectManager.RMSetAccessible(field);
                    return field;
                } catch (NoSuchFieldException noSuchFieldException) {
                    clas = clas.getSuperclass();
                }
            }
            throw new NoSuchFieldException(fieldName);
        }
    }

    protected static Method resolveExpectedJavaMethod(BshClassManager bcm, Class clas, Object object, String name, Object[] args2, boolean staticOnly) throws ReflectError, UtilEvalError {
        if (object == Primitive.NULL) {
            throw new UtilTargetError(new NullPointerException("Attempt to invoke method " + name + " on null value"));
        }
        Class[] types = Types.getTypes(args2);
        Method method = Reflect.resolveJavaMethod(bcm, clas, name, types, staticOnly);
        if (method == null) {
            throw new ReflectError((staticOnly ? "Static method " : "Method ") + StringUtil.methodString(name, types) + " not found in class'" + clas.getName() + "'");
        }
        return method;
    }

    protected static Method resolveJavaMethod(BshClassManager bcm, Class clas, String name, Class[] types, boolean staticOnly) throws UtilEvalError {
        if (clas == null) {
            throw new InterpreterError("null class");
        }
        Method method = null;
        if (bcm == null) {
            Interpreter.debug("resolveJavaMethod UNOPTIMIZED lookup");
        } else {
            method = bcm.getResolvedMethod(clas, name, types, staticOnly);
        }
        if (method == null) {
            boolean publicOnly = !Capabilities.haveAccessibility();
            try {
                method = Reflect.findOverloadedMethod(clas, name, types, publicOnly);
            } catch (SecurityException e) {
                throw new UtilTargetError("Security Exception while searching methods of: " + clas, e);
            }
            Reflect.checkFoundStaticMethod(method, staticOnly, clas);
            if (method != null && !publicOnly) {
                try {
                    ReflectManager.RMSetAccessible(method);
                } catch (UtilEvalError e) {
                    // empty catch block
                }
            }
            if (method != null && bcm != null) {
                bcm.cacheResolvedMethod(clas, types, method);
            }
        }
        return method;
    }

    private static Method findOverloadedMethod(Class baseClass, String methodName, Class[] types, boolean publicOnly) {
        if (Interpreter.DEBUG) {
            Interpreter.debug("Searching for method: " + StringUtil.methodString(methodName, types) + " in '" + baseClass.getName() + "'");
        }
        Method[] methods = Reflect.getCandidateMethods(baseClass, methodName, types.length, publicOnly);
        if (Interpreter.DEBUG) {
            Interpreter.debug("Looking for most specific method: " + methodName);
        }
        Method method = Reflect.findMostSpecificMethod(types, methods);
        return method;
    }

    static Method[] getCandidateMethods(Class baseClass, String methodName, int numArgs, boolean publicOnly) {
        Vector candidates = Reflect.gatherMethodsRecursive(baseClass, methodName, numArgs, publicOnly, null);
        Object[] ma = new Method[candidates.size()];
        candidates.copyInto(ma);
        return ma;
    }

    private static Vector gatherMethodsRecursive(Class baseClass, String methodName, int numArgs, boolean publicOnly, Vector candidates) {
        if (candidates == null) {
            candidates = new Vector();
        }
        if (publicOnly) {
            if (Reflect.isPublic(baseClass)) {
                Reflect.addCandidates(baseClass.getMethods(), methodName, numArgs, publicOnly, candidates);
            }
        } else {
            Reflect.addCandidates(baseClass.getDeclaredMethods(), methodName, numArgs, publicOnly, candidates);
        }
        Class<?>[] intfs = baseClass.getInterfaces();
        for (int i = 0; i < intfs.length; ++i) {
            Reflect.gatherMethodsRecursive(intfs[i], methodName, numArgs, publicOnly, candidates);
        }
        Class superclass = baseClass.getSuperclass();
        if (superclass != null) {
            Reflect.gatherMethodsRecursive(superclass, methodName, numArgs, publicOnly, candidates);
        }
        return candidates;
    }

    private static Vector addCandidates(Method[] methods, String methodName, int numArgs, boolean publicOnly, Vector candidates) {
        for (int i = 0; i < methods.length; ++i) {
            Method m3 = methods[i];
            if (!m3.getName().equals(methodName) || m3.getParameterTypes().length != numArgs || publicOnly && !Reflect.isPublic(m3)) continue;
            candidates.add(m3);
        }
        return candidates;
    }

    static Object constructObject(Class clas, Object[] args2) throws ReflectError, InvocationTargetException {
        Constructor[] constructors;
        if (clas.isInterface()) {
            throw new ReflectError("Can't create instance of an interface: " + clas);
        }
        Object obj = null;
        Class[] types = Types.getTypes(args2);
        Constructor con = null;
        Constructor[] constructorArray = constructors = Capabilities.haveAccessibility() ? clas.getDeclaredConstructors() : clas.getConstructors();
        if (Interpreter.DEBUG) {
            Interpreter.debug("Looking for most specific constructor: " + clas);
        }
        if ((con = Reflect.findMostSpecificConstructor(types, constructors)) == null) {
            throw Reflect.cantFindConstructor(clas, types);
        }
        if (!Reflect.isPublic(con)) {
            try {
                ReflectManager.RMSetAccessible(con);
            } catch (UtilEvalError e) {
                // empty catch block
            }
        }
        args2 = Primitive.unwrap(args2);
        try {
            obj = con.newInstance(args2);
        } catch (InstantiationException e) {
            throw new ReflectError("The class " + clas + " is abstract ");
        } catch (IllegalAccessException e) {
            throw new ReflectError("We don't have permission to create an instance.Use setAccessibility(true) to enable access.");
        } catch (IllegalArgumentException e) {
            throw new ReflectError("The number of arguments was wrong");
        }
        if (obj == null) {
            throw new ReflectError("Couldn't construct the object");
        }
        return obj;
    }

    static Constructor findMostSpecificConstructor(Class[] idealMatch, Constructor[] constructors) {
        int match = Reflect.findMostSpecificConstructorIndex(idealMatch, constructors);
        return match == -1 ? null : constructors[match];
    }

    static int findMostSpecificConstructorIndex(Class[] idealMatch, Constructor[] constructors) {
        Class[][] candidates = new Class[constructors.length][];
        for (int i = 0; i < candidates.length; ++i) {
            candidates[i] = constructors[i].getParameterTypes();
        }
        return Reflect.findMostSpecificSignature(idealMatch, candidates);
    }

    static Method findMostSpecificMethod(Class[] idealMatch, Method[] methods) {
        Class[][] candidateSigs = new Class[methods.length][];
        for (int i = 0; i < methods.length; ++i) {
            candidateSigs[i] = methods[i].getParameterTypes();
        }
        int match = Reflect.findMostSpecificSignature(idealMatch, candidateSigs);
        return match == -1 ? null : methods[match];
    }

    static int findMostSpecificSignature(Class[] idealMatch, Class[][] candidates) {
        for (int round = 1; round <= 4; ++round) {
            Class[] bestMatch = null;
            int bestMatchIndex = -1;
            for (int i = 0; i < candidates.length; ++i) {
                Class[] targetMatch = candidates[i];
                if (!Types.isSignatureAssignable(idealMatch, targetMatch, round) || bestMatch != null && !Types.isSignatureAssignable(targetMatch, bestMatch, 1)) continue;
                bestMatch = targetMatch;
                bestMatchIndex = i;
            }
            if (bestMatch == null) continue;
            return bestMatchIndex;
        }
        return -1;
    }

    private static String accessorName(String getorset, String propName) {
        return getorset + String.valueOf(Character.toUpperCase(propName.charAt(0))) + propName.substring(1);
    }

    public static boolean hasObjectPropertyGetter(Class clas, String propName) {
        String getterName = Reflect.accessorName("get", propName);
        try {
            clas.getMethod(getterName, new Class[0]);
            return true;
        } catch (NoSuchMethodException e) {
            getterName = Reflect.accessorName("is", propName);
            try {
                Method m3 = clas.getMethod(getterName, new Class[0]);
                return m3.getReturnType() == Boolean.TYPE;
            } catch (NoSuchMethodException e2) {
                return false;
            }
        }
    }

    public static boolean hasObjectPropertySetter(Class clas, String propName) {
        String setterName = Reflect.accessorName("set", propName);
        Method[] methods = clas.getMethods();
        for (int i = 0; i < methods.length; ++i) {
            if (!methods[i].getName().equals(setterName)) continue;
            return true;
        }
        return false;
    }

    public static Object getObjectProperty(Object obj, String propName) throws UtilEvalError, ReflectError {
        String accessorName;
        Object[] args2 = new Object[]{};
        Interpreter.debug("property access: ");
        Method method = null;
        Exception e1 = null;
        Exception e2 = null;
        try {
            accessorName = Reflect.accessorName("get", propName);
            method = Reflect.resolveExpectedJavaMethod(null, obj.getClass(), obj, accessorName, args2, false);
        } catch (Exception e) {
            e1 = e;
        }
        if (method == null) {
            try {
                accessorName = Reflect.accessorName("is", propName);
                method = Reflect.resolveExpectedJavaMethod(null, obj.getClass(), obj, accessorName, args2, false);
                if (method.getReturnType() != Boolean.TYPE) {
                    method = null;
                }
            } catch (Exception e) {
                e2 = e;
            }
        }
        if (method == null) {
            throw new ReflectError("Error in property getter: " + e1 + (e2 != null ? " : " + e2 : ""));
        }
        try {
            return Reflect.invokeMethod(method, obj, args2);
        } catch (InvocationTargetException e) {
            throw new UtilEvalError("Property accessor threw exception: " + e.getTargetException());
        }
    }

    public static void setObjectProperty(Object obj, String propName, Object value) throws ReflectError, UtilEvalError {
        String accessorName = Reflect.accessorName("set", propName);
        Object[] args2 = new Object[]{value};
        Interpreter.debug("property access: ");
        try {
            Method method = Reflect.resolveExpectedJavaMethod(null, obj.getClass(), obj, accessorName, args2, false);
            Reflect.invokeMethod(method, obj, args2);
        } catch (InvocationTargetException e) {
            throw new UtilEvalError("Property accessor threw exception: " + e.getTargetException());
        }
    }

    public static String normalizeClassName(Class type) {
        if (!type.isArray()) {
            return type.getName();
        }
        StringBuffer className = new StringBuffer();
        try {
            className.append(Reflect.getArrayBaseType(type).getName() + " ");
            for (int i = 0; i < Reflect.getArrayDimensions(type); ++i) {
                className.append("[]");
            }
        } catch (ReflectError reflectError) {
            // empty catch block
        }
        return className.toString();
    }

    public static int getArrayDimensions(Class arrayClass) {
        if (!arrayClass.isArray()) {
            return 0;
        }
        return arrayClass.getName().lastIndexOf(91) + 1;
    }

    public static Class getArrayBaseType(Class arrayClass) throws ReflectError {
        if (!arrayClass.isArray()) {
            throw new ReflectError("The class is not an array.");
        }
        return arrayClass.getComponentType();
    }

    public static Object invokeCompiledCommand(Class commandClass, Object[] args2, Interpreter interpreter, CallStack callstack) throws UtilEvalError {
        Object[] invokeArgs = new Object[args2.length + 2];
        invokeArgs[0] = interpreter;
        invokeArgs[1] = callstack;
        System.arraycopy(args2, 0, invokeArgs, 2, args2.length);
        BshClassManager bcm = interpreter.getClassManager();
        try {
            return Reflect.invokeStaticMethod(bcm, commandClass, "invoke", invokeArgs);
        } catch (InvocationTargetException e) {
            throw new UtilEvalError("Error in compiled command: " + e.getTargetException());
        } catch (ReflectError e) {
            throw new UtilEvalError("Error invoking compiled command: " + e);
        }
    }

    private static void logInvokeMethod(String msg, Method method, Object[] args2) {
        if (Interpreter.DEBUG) {
            Interpreter.debug(msg + method + " with args:");
            for (int i = 0; i < args2.length; ++i) {
                Interpreter.debug("args[" + i + "] = " + args2[i] + " type = " + args2[i].getClass());
            }
        }
    }

    private static void checkFoundStaticMethod(Method method, boolean staticOnly, Class clas) throws UtilEvalError {
        if (method != null && staticOnly && !Reflect.isStatic(method)) {
            throw new UtilEvalError("Cannot reach instance method: " + StringUtil.methodString(method.getName(), method.getParameterTypes()) + " from static context: " + clas.getName());
        }
    }

    private static ReflectError cantFindConstructor(Class clas, Class[] types) {
        if (types.length == 0) {
            return new ReflectError("Can't find default constructor for: " + clas);
        }
        return new ReflectError("Can't find constructor: " + StringUtil.methodString(clas.getName(), types) + " in class: " + clas.getName());
    }

    private static boolean isPublic(Class c) {
        return Modifier.isPublic(c.getModifiers());
    }

    private static boolean isPublic(Method m3) {
        return Modifier.isPublic(m3.getModifiers());
    }

    private static boolean isPublic(Constructor c) {
        return Modifier.isPublic(c.getModifiers());
    }

    private static boolean isStatic(Method m3) {
        return Modifier.isStatic(m3.getModifiers());
    }
}

