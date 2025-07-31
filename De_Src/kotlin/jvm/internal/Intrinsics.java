/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm.internal;

import java.util.Arrays;
import kotlin.KotlinNullPointerException;
import kotlin.SinceKotlin;
import kotlin.UninitializedPropertyAccessException;

public class Intrinsics {
    private Intrinsics() {
    }

    public static String stringPlus(String self, Object other) {
        return self + other;
    }

    public static void checkNotNull(Object object) {
        if (object == null) {
            Intrinsics.throwJavaNpe();
        }
    }

    public static void checkNotNull(Object object, String message) {
        if (object == null) {
            Intrinsics.throwJavaNpe(message);
        }
    }

    public static void throwNpe() {
        throw Intrinsics.sanitizeStackTrace(new KotlinNullPointerException());
    }

    public static void throwNpe(String message) {
        throw Intrinsics.sanitizeStackTrace(new KotlinNullPointerException(message));
    }

    @SinceKotlin(version="1.4")
    public static void throwJavaNpe() {
        throw Intrinsics.sanitizeStackTrace(new NullPointerException());
    }

    @SinceKotlin(version="1.4")
    public static void throwJavaNpe(String message) {
        throw Intrinsics.sanitizeStackTrace(new NullPointerException(message));
    }

    public static void throwUninitializedProperty(String message) {
        throw Intrinsics.sanitizeStackTrace(new UninitializedPropertyAccessException(message));
    }

    public static void throwUninitializedPropertyAccessException(String propertyName) {
        Intrinsics.throwUninitializedProperty("lateinit property " + propertyName + " has not been initialized");
    }

    public static void throwAssert() {
        throw Intrinsics.sanitizeStackTrace(new AssertionError());
    }

    public static void throwAssert(String message) {
        throw Intrinsics.sanitizeStackTrace(new AssertionError((Object)message));
    }

    public static void throwIllegalArgument() {
        throw Intrinsics.sanitizeStackTrace(new IllegalArgumentException());
    }

    public static void throwIllegalArgument(String message) {
        throw Intrinsics.sanitizeStackTrace(new IllegalArgumentException(message));
    }

    public static void throwIllegalState() {
        throw Intrinsics.sanitizeStackTrace(new IllegalStateException());
    }

    public static void throwIllegalState(String message) {
        throw Intrinsics.sanitizeStackTrace(new IllegalStateException(message));
    }

    public static void checkExpressionValueIsNotNull(Object value, String expression) {
        if (value == null) {
            throw Intrinsics.sanitizeStackTrace(new IllegalStateException(expression + " must not be null"));
        }
    }

    public static void checkNotNullExpressionValue(Object value, String expression) {
        if (value == null) {
            throw Intrinsics.sanitizeStackTrace(new NullPointerException(expression + " must not be null"));
        }
    }

    public static void checkReturnedValueIsNotNull(Object value, String className, String methodName) {
        if (value == null) {
            throw Intrinsics.sanitizeStackTrace(new IllegalStateException("Method specified as non-null returned null: " + className + "." + methodName));
        }
    }

    public static void checkReturnedValueIsNotNull(Object value, String message) {
        if (value == null) {
            throw Intrinsics.sanitizeStackTrace(new IllegalStateException(message));
        }
    }

    public static void checkFieldIsNotNull(Object value, String className, String fieldName) {
        if (value == null) {
            throw Intrinsics.sanitizeStackTrace(new IllegalStateException("Field specified as non-null is null: " + className + "." + fieldName));
        }
    }

    public static void checkFieldIsNotNull(Object value, String message) {
        if (value == null) {
            throw Intrinsics.sanitizeStackTrace(new IllegalStateException(message));
        }
    }

    public static void checkParameterIsNotNull(Object value, String paramName) {
        if (value == null) {
            Intrinsics.throwParameterIsNullIAE(paramName);
        }
    }

    public static void checkNotNullParameter(Object value, String paramName) {
        if (value == null) {
            Intrinsics.throwParameterIsNullNPE(paramName);
        }
    }

    private static void throwParameterIsNullIAE(String paramName) {
        throw Intrinsics.sanitizeStackTrace(new IllegalArgumentException(Intrinsics.createParameterIsNullExceptionMessage(paramName)));
    }

    private static void throwParameterIsNullNPE(String paramName) {
        throw Intrinsics.sanitizeStackTrace(new NullPointerException(Intrinsics.createParameterIsNullExceptionMessage(paramName)));
    }

    private static String createParameterIsNullExceptionMessage(String paramName) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StackTraceElement caller = stackTraceElements[4];
        String className = caller.getClassName();
        String methodName = caller.getMethodName();
        return "Parameter specified as non-null is null: method " + className + "." + methodName + ", parameter " + paramName;
    }

    public static int compare(long thisVal, long anotherVal) {
        return thisVal < anotherVal ? -1 : (thisVal == anotherVal ? 0 : 1);
    }

    public static int compare(int thisVal, int anotherVal) {
        return thisVal < anotherVal ? -1 : (thisVal == anotherVal ? 0 : 1);
    }

    public static boolean areEqual(Object first, Object second) {
        return first == null ? second == null : first.equals(second);
    }

    @SinceKotlin(version="1.1")
    public static boolean areEqual(Double first, Double second) {
        return first == null ? second == null : second != null && first.doubleValue() == second.doubleValue();
    }

    @SinceKotlin(version="1.1")
    public static boolean areEqual(Double first, double second) {
        return first != null && first == second;
    }

    @SinceKotlin(version="1.1")
    public static boolean areEqual(double first, Double second) {
        return second != null && first == second;
    }

    @SinceKotlin(version="1.1")
    public static boolean areEqual(Float first, Float second) {
        return first == null ? second == null : second != null && first.floatValue() == second.floatValue();
    }

    @SinceKotlin(version="1.1")
    public static boolean areEqual(Float first, float second) {
        return first != null && first.floatValue() == second;
    }

    @SinceKotlin(version="1.1")
    public static boolean areEqual(float first, Float second) {
        return second != null && first == second.floatValue();
    }

    public static void throwUndefinedForReified() {
        Intrinsics.throwUndefinedForReified("This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.");
    }

    public static void throwUndefinedForReified(String message) {
        throw new UnsupportedOperationException(message);
    }

    public static void reifiedOperationMarker(int id, String typeParameterIdentifier) {
        Intrinsics.throwUndefinedForReified();
    }

    public static void reifiedOperationMarker(int id, String typeParameterIdentifier, String message) {
        Intrinsics.throwUndefinedForReified(message);
    }

    public static void needClassReification() {
        Intrinsics.throwUndefinedForReified();
    }

    public static void needClassReification(String message) {
        Intrinsics.throwUndefinedForReified(message);
    }

    public static void checkHasClass(String internalName) throws ClassNotFoundException {
        String fqName = internalName.replace('/', '.');
        try {
            Class.forName(fqName);
        } catch (ClassNotFoundException e) {
            throw Intrinsics.sanitizeStackTrace(new ClassNotFoundException("Class " + fqName + " is not found. Please update the Kotlin runtime to the latest version", e));
        }
    }

    public static void checkHasClass(String internalName, String requiredVersion) throws ClassNotFoundException {
        String fqName = internalName.replace('/', '.');
        try {
            Class.forName(fqName);
        } catch (ClassNotFoundException e) {
            throw Intrinsics.sanitizeStackTrace(new ClassNotFoundException("Class " + fqName + " is not found: this code requires the Kotlin runtime of version at least " + requiredVersion, e));
        }
    }

    private static <T extends Throwable> T sanitizeStackTrace(T throwable) {
        return Intrinsics.sanitizeStackTrace(throwable, Intrinsics.class.getName());
    }

    static <T extends Throwable> T sanitizeStackTrace(T throwable, String classNameToDrop) {
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        int size = stackTrace.length;
        int lastIntrinsic = -1;
        for (int i = 0; i < size; ++i) {
            if (!classNameToDrop.equals(stackTrace[i].getClassName())) continue;
            lastIntrinsic = i;
        }
        StackTraceElement[] newStackTrace = Arrays.copyOfRange(stackTrace, lastIntrinsic + 1, size);
        throwable.setStackTrace(newStackTrace);
        return throwable;
    }

    @SinceKotlin(version="1.4")
    public static class Kotlin {
        private Kotlin() {
        }
    }
}

