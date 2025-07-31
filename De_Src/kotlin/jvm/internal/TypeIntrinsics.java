/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Function;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.jvm.internal.FunctionBase;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableCollection;
import kotlin.jvm.internal.markers.KMutableIterable;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.jvm.internal.markers.KMutableList;
import kotlin.jvm.internal.markers.KMutableListIterator;
import kotlin.jvm.internal.markers.KMutableMap;
import kotlin.jvm.internal.markers.KMutableSet;

public class TypeIntrinsics {
    private static <T extends Throwable> T sanitizeStackTrace(T throwable) {
        return Intrinsics.sanitizeStackTrace(throwable, TypeIntrinsics.class.getName());
    }

    public static void throwCce(Object argument, String requestedClassName) {
        String argumentClassName = argument == null ? "null" : argument.getClass().getName();
        TypeIntrinsics.throwCce(argumentClassName + " cannot be cast to " + requestedClassName);
    }

    public static void throwCce(String message) {
        throw TypeIntrinsics.throwCce(new ClassCastException(message));
    }

    public static ClassCastException throwCce(ClassCastException e) {
        throw TypeIntrinsics.sanitizeStackTrace(e);
    }

    public static boolean isMutableIterator(Object obj) {
        return obj instanceof Iterator && (!(obj instanceof KMappedMarker) || obj instanceof KMutableIterator);
    }

    public static Iterator asMutableIterator(Object obj) {
        if (obj instanceof KMappedMarker && !(obj instanceof KMutableIterator)) {
            TypeIntrinsics.throwCce(obj, "kotlin.collections.MutableIterator");
        }
        return TypeIntrinsics.castToIterator(obj);
    }

    public static Iterator asMutableIterator(Object obj, String message) {
        if (obj instanceof KMappedMarker && !(obj instanceof KMutableIterator)) {
            TypeIntrinsics.throwCce(message);
        }
        return TypeIntrinsics.castToIterator(obj);
    }

    public static Iterator castToIterator(Object obj) {
        try {
            return (Iterator)obj;
        } catch (ClassCastException e) {
            throw TypeIntrinsics.throwCce(e);
        }
    }

    public static boolean isMutableListIterator(Object obj) {
        return obj instanceof ListIterator && (!(obj instanceof KMappedMarker) || obj instanceof KMutableListIterator);
    }

    public static ListIterator asMutableListIterator(Object obj) {
        if (obj instanceof KMappedMarker && !(obj instanceof KMutableListIterator)) {
            TypeIntrinsics.throwCce(obj, "kotlin.collections.MutableListIterator");
        }
        return TypeIntrinsics.castToListIterator(obj);
    }

    public static ListIterator asMutableListIterator(Object obj, String message) {
        if (obj instanceof KMappedMarker && !(obj instanceof KMutableListIterator)) {
            TypeIntrinsics.throwCce(message);
        }
        return TypeIntrinsics.castToListIterator(obj);
    }

    public static ListIterator castToListIterator(Object obj) {
        try {
            return (ListIterator)obj;
        } catch (ClassCastException e) {
            throw TypeIntrinsics.throwCce(e);
        }
    }

    public static boolean isMutableIterable(Object obj) {
        return obj instanceof Iterable && (!(obj instanceof KMappedMarker) || obj instanceof KMutableIterable);
    }

    public static Iterable asMutableIterable(Object obj) {
        if (obj instanceof KMappedMarker && !(obj instanceof KMutableIterable)) {
            TypeIntrinsics.throwCce(obj, "kotlin.collections.MutableIterable");
        }
        return TypeIntrinsics.castToIterable(obj);
    }

    public static Iterable asMutableIterable(Object obj, String message) {
        if (obj instanceof KMappedMarker && !(obj instanceof KMutableIterable)) {
            TypeIntrinsics.throwCce(message);
        }
        return TypeIntrinsics.castToIterable(obj);
    }

    public static Iterable castToIterable(Object obj) {
        try {
            return (Iterable)obj;
        } catch (ClassCastException e) {
            throw TypeIntrinsics.throwCce(e);
        }
    }

    public static boolean isMutableCollection(Object obj) {
        return obj instanceof Collection && (!(obj instanceof KMappedMarker) || obj instanceof KMutableCollection);
    }

    public static Collection asMutableCollection(Object obj) {
        if (obj instanceof KMappedMarker && !(obj instanceof KMutableCollection)) {
            TypeIntrinsics.throwCce(obj, "kotlin.collections.MutableCollection");
        }
        return TypeIntrinsics.castToCollection(obj);
    }

    public static Collection asMutableCollection(Object obj, String message) {
        if (obj instanceof KMappedMarker && !(obj instanceof KMutableCollection)) {
            TypeIntrinsics.throwCce(message);
        }
        return TypeIntrinsics.castToCollection(obj);
    }

    public static Collection castToCollection(Object obj) {
        try {
            return (Collection)obj;
        } catch (ClassCastException e) {
            throw TypeIntrinsics.throwCce(e);
        }
    }

    public static boolean isMutableList(Object obj) {
        return obj instanceof List && (!(obj instanceof KMappedMarker) || obj instanceof KMutableList);
    }

    public static List asMutableList(Object obj) {
        if (obj instanceof KMappedMarker && !(obj instanceof KMutableList)) {
            TypeIntrinsics.throwCce(obj, "kotlin.collections.MutableList");
        }
        return TypeIntrinsics.castToList(obj);
    }

    public static List asMutableList(Object obj, String message) {
        if (obj instanceof KMappedMarker && !(obj instanceof KMutableList)) {
            TypeIntrinsics.throwCce(message);
        }
        return TypeIntrinsics.castToList(obj);
    }

    public static List castToList(Object obj) {
        try {
            return (List)obj;
        } catch (ClassCastException e) {
            throw TypeIntrinsics.throwCce(e);
        }
    }

    public static boolean isMutableSet(Object obj) {
        return obj instanceof Set && (!(obj instanceof KMappedMarker) || obj instanceof KMutableSet);
    }

    public static Set asMutableSet(Object obj) {
        if (obj instanceof KMappedMarker && !(obj instanceof KMutableSet)) {
            TypeIntrinsics.throwCce(obj, "kotlin.collections.MutableSet");
        }
        return TypeIntrinsics.castToSet(obj);
    }

    public static Set asMutableSet(Object obj, String message) {
        if (obj instanceof KMappedMarker && !(obj instanceof KMutableSet)) {
            TypeIntrinsics.throwCce(message);
        }
        return TypeIntrinsics.castToSet(obj);
    }

    public static Set castToSet(Object obj) {
        try {
            return (Set)obj;
        } catch (ClassCastException e) {
            throw TypeIntrinsics.throwCce(e);
        }
    }

    public static boolean isMutableMap(Object obj) {
        return obj instanceof Map && (!(obj instanceof KMappedMarker) || obj instanceof KMutableMap);
    }

    public static Map asMutableMap(Object obj) {
        if (obj instanceof KMappedMarker && !(obj instanceof KMutableMap)) {
            TypeIntrinsics.throwCce(obj, "kotlin.collections.MutableMap");
        }
        return TypeIntrinsics.castToMap(obj);
    }

    public static Map asMutableMap(Object obj, String message) {
        if (obj instanceof KMappedMarker && !(obj instanceof KMutableMap)) {
            TypeIntrinsics.throwCce(message);
        }
        return TypeIntrinsics.castToMap(obj);
    }

    public static Map castToMap(Object obj) {
        try {
            return (Map)obj;
        } catch (ClassCastException e) {
            throw TypeIntrinsics.throwCce(e);
        }
    }

    public static boolean isMutableMapEntry(Object obj) {
        return obj instanceof Map.Entry && (!(obj instanceof KMappedMarker) || obj instanceof KMutableMap.Entry);
    }

    public static Map.Entry asMutableMapEntry(Object obj) {
        if (obj instanceof KMappedMarker && !(obj instanceof KMutableMap.Entry)) {
            TypeIntrinsics.throwCce(obj, "kotlin.collections.MutableMap.MutableEntry");
        }
        return TypeIntrinsics.castToMapEntry(obj);
    }

    public static Map.Entry asMutableMapEntry(Object obj, String message) {
        if (obj instanceof KMappedMarker && !(obj instanceof KMutableMap.Entry)) {
            TypeIntrinsics.throwCce(message);
        }
        return TypeIntrinsics.castToMapEntry(obj);
    }

    public static Map.Entry castToMapEntry(Object obj) {
        try {
            return (Map.Entry)obj;
        } catch (ClassCastException e) {
            throw TypeIntrinsics.throwCce(e);
        }
    }

    public static int getFunctionArity(Object obj) {
        if (obj instanceof FunctionBase) {
            return ((FunctionBase)obj).getArity();
        }
        if (obj instanceof Function0) {
            return 0;
        }
        if (obj instanceof Function1) {
            return 1;
        }
        if (obj instanceof Function2) {
            return 2;
        }
        if (obj instanceof Function3) {
            return 3;
        }
        if (obj instanceof Function4) {
            return 4;
        }
        if (obj instanceof Function5) {
            return 5;
        }
        if (obj instanceof Function6) {
            return 6;
        }
        if (obj instanceof Function7) {
            return 7;
        }
        if (obj instanceof Function8) {
            return 8;
        }
        if (obj instanceof Function9) {
            return 9;
        }
        if (obj instanceof Function10) {
            return 10;
        }
        if (obj instanceof Function11) {
            return 11;
        }
        if (obj instanceof Function12) {
            return 12;
        }
        if (obj instanceof Function13) {
            return 13;
        }
        if (obj instanceof Function14) {
            return 14;
        }
        if (obj instanceof Function15) {
            return 15;
        }
        if (obj instanceof Function16) {
            return 16;
        }
        if (obj instanceof Function17) {
            return 17;
        }
        if (obj instanceof Function18) {
            return 18;
        }
        if (obj instanceof Function19) {
            return 19;
        }
        if (obj instanceof Function20) {
            return 20;
        }
        if (obj instanceof Function21) {
            return 21;
        }
        if (obj instanceof Function22) {
            return 22;
        }
        return -1;
    }

    public static boolean isFunctionOfArity(Object obj, int arity) {
        return obj instanceof Function && TypeIntrinsics.getFunctionArity(obj) == arity;
    }

    public static Object beforeCheckcastToFunctionOfArity(Object obj, int arity) {
        if (obj != null && !TypeIntrinsics.isFunctionOfArity(obj, arity)) {
            TypeIntrinsics.throwCce(obj, "kotlin.jvm.functions.Function" + arity);
        }
        return obj;
    }

    public static Object beforeCheckcastToFunctionOfArity(Object obj, int arity, String message) {
        if (obj != null && !TypeIntrinsics.isFunctionOfArity(obj, arity)) {
            TypeIntrinsics.throwCce(message);
        }
        return obj;
    }
}

