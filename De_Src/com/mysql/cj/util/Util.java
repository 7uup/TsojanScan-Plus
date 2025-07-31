/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.util;

import com.mysql.cj.Constants;
import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.util.StringUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Util {
    private static int jvmVersion;
    private static int jvmUpdateNumber;
    private static boolean isColdFusion;
    private static final ConcurrentMap<Class<?>, Boolean> isJdbcInterfaceCache;
    private static final ConcurrentMap<Class<?>, Class<?>[]> implementedInterfacesCache;

    public static int getJVMVersion() {
        return jvmVersion;
    }

    public static boolean jvmMeetsMinimum(int version, int updateNumber) {
        return Util.getJVMVersion() > version || Util.getJVMVersion() == version && Util.getJVMUpdateNumber() >= updateNumber;
    }

    public static int getJVMUpdateNumber() {
        return jvmUpdateNumber;
    }

    public static boolean isColdFusion() {
        return isColdFusion;
    }

    public static boolean isCommunityEdition(String serverVersion) {
        return !Util.isEnterpriseEdition(serverVersion);
    }

    public static boolean isEnterpriseEdition(String serverVersion) {
        return serverVersion.contains("enterprise") || serverVersion.contains("commercial") || serverVersion.contains("advanced");
    }

    public static String stackTraceToString(Throwable ex) {
        StringBuilder traceBuf = new StringBuilder();
        traceBuf.append(Messages.getString("Util.1"));
        if (ex != null) {
            traceBuf.append(ex.getClass().getName());
            String message = ex.getMessage();
            if (message != null) {
                traceBuf.append(Messages.getString("Util.2"));
                traceBuf.append(message);
            }
            StringWriter out = new StringWriter();
            PrintWriter printOut = new PrintWriter(out);
            ex.printStackTrace(printOut);
            traceBuf.append(Messages.getString("Util.3"));
            traceBuf.append(out.toString());
        }
        traceBuf.append(Messages.getString("Util.4"));
        return traceBuf.toString();
    }

    public static Object getInstance(String className, Class<?>[] argTypes, Object[] args2, ExceptionInterceptor exceptionInterceptor, String errorMessage) {
        try {
            return Util.handleNewInstance(Class.forName(className).getConstructor(argTypes), args2, exceptionInterceptor);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException e) {
            throw ExceptionFactory.createException(WrongArgumentException.class, errorMessage, e, exceptionInterceptor);
        }
    }

    public static Object getInstance(String className, Class<?>[] argTypes, Object[] args2, ExceptionInterceptor exceptionInterceptor) {
        return Util.getInstance(className, argTypes, args2, exceptionInterceptor, "Can't instantiate required class");
    }

    public static Object handleNewInstance(Constructor<?> ctor, Object[] args2, ExceptionInterceptor exceptionInterceptor) {
        try {
            return ctor.newInstance(args2);
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException e) {
            throw ExceptionFactory.createException(WrongArgumentException.class, "Can't instantiate required class", e, exceptionInterceptor);
        } catch (InvocationTargetException e) {
            Throwable target = e.getTargetException();
            if (target instanceof ExceptionInInitializerError) {
                target = ((ExceptionInInitializerError)target).getException();
            } else if (target instanceof CJException) {
                throw (CJException)target;
            }
            throw ExceptionFactory.createException(WrongArgumentException.class, target.getMessage(), target, exceptionInterceptor);
        }
    }

    public static boolean interfaceExists(String hostname) {
        try {
            Class<?> networkInterfaceClass = Class.forName("java.net.NetworkInterface");
            return networkInterfaceClass.getMethod("getByName", null).invoke(networkInterfaceClass, hostname) != null;
        } catch (Throwable t) {
            return false;
        }
    }

    public static Map<Object, Object> calculateDifferences(Map<?, ?> map1, Map<?, ?> map2) {
        HashMap<Object, Object> diffMap = new HashMap<Object, Object>();
        for (Map.Entry<?, ?> entry : map1.entrySet()) {
            Object key = entry.getKey();
            Number value1 = null;
            Number value2 = null;
            if (entry.getValue() instanceof Number) {
                value1 = (Number)entry.getValue();
                value2 = (Number)map2.get(key);
            } else {
                try {
                    value1 = new Double(entry.getValue().toString());
                    value2 = new Double(map2.get(key).toString());
                } catch (NumberFormatException nfe) {
                    continue;
                }
            }
            if (value1.equals(value2)) continue;
            if (value1 instanceof Byte) {
                diffMap.put(key, (byte)((Byte)value2 - (Byte)value1));
                continue;
            }
            if (value1 instanceof Short) {
                diffMap.put(key, (short)((Short)value2 - (Short)value1));
                continue;
            }
            if (value1 instanceof Integer) {
                diffMap.put(key, (Integer)value2 - (Integer)value1);
                continue;
            }
            if (value1 instanceof Long) {
                diffMap.put(key, (Long)value2 - (Long)value1);
                continue;
            }
            if (value1 instanceof Float) {
                diffMap.put(key, Float.valueOf(((Float)value2).floatValue() - ((Float)value1).floatValue()));
                continue;
            }
            if (value1 instanceof Double) {
                diffMap.put(key, ((Double)value2).shortValue() - ((Double)value1).shortValue());
                continue;
            }
            if (value1 instanceof BigDecimal) {
                diffMap.put(key, ((BigDecimal)value2).subtract((BigDecimal)value1));
                continue;
            }
            if (!(value1 instanceof BigInteger)) continue;
            diffMap.put(key, ((BigInteger)value2).subtract((BigInteger)value1));
        }
        return diffMap;
    }

    public static <T> List<T> loadClasses(String extensionClassNames, String errorMessageKey, ExceptionInterceptor exceptionInterceptor) {
        LinkedList instances = new LinkedList();
        List<String> interceptorsToCreate = StringUtils.split(extensionClassNames, ",", true);
        String className = null;
        try {
            int s2 = interceptorsToCreate.size();
            for (int i = 0; i < s2; ++i) {
                className = interceptorsToCreate.get(i);
                Object instance = Class.forName(className).newInstance();
                instances.add(instance);
            }
        } catch (Throwable t) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString(errorMessageKey, new Object[]{className}), t, exceptionInterceptor);
        }
        return instances;
    }

    public static boolean isJdbcInterface(Class<?> clazz) {
        if (isJdbcInterfaceCache.containsKey(clazz)) {
            return (Boolean)isJdbcInterfaceCache.get(clazz);
        }
        if (clazz.isInterface()) {
            try {
                if (Util.isJdbcPackage(clazz.getPackage().getName())) {
                    isJdbcInterfaceCache.putIfAbsent(clazz, true);
                    return true;
                }
            } catch (Exception exception) {
                // empty catch block
            }
        }
        for (Class<?> iface : clazz.getInterfaces()) {
            if (!Util.isJdbcInterface(iface)) continue;
            isJdbcInterfaceCache.putIfAbsent(clazz, true);
            return true;
        }
        if (clazz.getSuperclass() != null && Util.isJdbcInterface(clazz.getSuperclass())) {
            isJdbcInterfaceCache.putIfAbsent(clazz, true);
            return true;
        }
        isJdbcInterfaceCache.putIfAbsent(clazz, false);
        return false;
    }

    public static boolean isJdbcPackage(String packageName) {
        return packageName != null && (packageName.startsWith("java.sql") || packageName.startsWith("javax.sql") || packageName.startsWith("com.mysql.cj.jdbc"));
    }

    public static Class<?>[] getImplementedInterfaces(Class<?> clazz) {
        Class[] implementedInterfaces = (Class[])implementedInterfacesCache.get(clazz);
        if (implementedInterfaces != null) {
            return implementedInterfaces;
        }
        LinkedHashSet interfaces = new LinkedHashSet();
        Class<?> superClass = clazz;
        do {
            Collections.addAll(interfaces, superClass.getInterfaces());
        } while ((superClass = superClass.getSuperclass()) != null);
        implementedInterfaces = interfaces.toArray(new Class[interfaces.size()]);
        Class[] oldValue = implementedInterfacesCache.putIfAbsent(clazz, implementedInterfaces);
        if (oldValue != null) {
            implementedInterfaces = oldValue;
        }
        return implementedInterfaces;
    }

    public static long secondsSinceMillis(long timeInMillis) {
        return (System.currentTimeMillis() - timeInMillis) / 1000L;
    }

    public static int truncateAndConvertToInt(long longValue) {
        return longValue > Integer.MAX_VALUE ? Integer.MAX_VALUE : (longValue < Integer.MIN_VALUE ? Integer.MIN_VALUE : (int)longValue);
    }

    public static int[] truncateAndConvertToInt(long[] longArray) {
        int[] intArray = new int[longArray.length];
        for (int i = 0; i < longArray.length; ++i) {
            intArray[i] = longArray[i] > Integer.MAX_VALUE ? Integer.MAX_VALUE : (longArray[i] < Integer.MIN_VALUE ? Integer.MIN_VALUE : (int)longArray[i]);
        }
        return intArray;
    }

    public static String getPackageName(Class<?> clazz) {
        String fqcn = clazz.getName();
        int classNameStartsAt = fqcn.lastIndexOf(46);
        if (classNameStartsAt > 0) {
            return fqcn.substring(0, classNameStartsAt);
        }
        return "";
    }

    public static boolean isRunningOnWindows() {
        return StringUtils.indexOfIgnoreCase(Constants.OS_NAME, "WINDOWS") != -1;
    }

    public static int readFully(Reader reader, char[] buf, int length) throws IOException {
        int numCharsRead;
        int count;
        for (numCharsRead = 0; numCharsRead < length && (count = reader.read(buf, numCharsRead, length - numCharsRead)) >= 0; numCharsRead += count) {
        }
        return numCharsRead;
    }

    static {
        String loadedFrom;
        jvmVersion = 8;
        jvmUpdateNumber = -1;
        isColdFusion = false;
        int startPos = Constants.JVM_VERSION.indexOf(46);
        int endPos = startPos + 1;
        if (startPos != -1) {
            while (Character.isDigit(Constants.JVM_VERSION.charAt(endPos)) && ++endPos < Constants.JVM_VERSION.length()) {
            }
        }
        if (endPos > ++startPos) {
            jvmVersion = Integer.parseInt(Constants.JVM_VERSION.substring(startPos, endPos));
        }
        startPos = Constants.JVM_VERSION.indexOf("_");
        endPos = startPos + 1;
        if (startPos != -1) {
            while (Character.isDigit(Constants.JVM_VERSION.charAt(endPos)) && ++endPos < Constants.JVM_VERSION.length()) {
            }
        }
        if (endPos > ++startPos) {
            jvmUpdateNumber = Integer.parseInt(Constants.JVM_VERSION.substring(startPos, endPos));
        }
        isColdFusion = (loadedFrom = Util.stackTraceToString(new Throwable())) != null ? loadedFrom.indexOf("coldfusion") != -1 : false;
        isJdbcInterfaceCache = new ConcurrentHashMap();
        implementedInterfacesCache = new ConcurrentHashMap();
    }
}

