/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class Validate {
    private static final String DEFAULT_NOT_NAN_EX_MESSAGE = "The validated value is not a number";
    private static final String DEFAULT_FINITE_EX_MESSAGE = "The value is invalid: %f";
    private static final String DEFAULT_EXCLUSIVE_BETWEEN_EX_MESSAGE = "The value %s is not in the specified exclusive range of %s to %s";
    private static final String DEFAULT_INCLUSIVE_BETWEEN_EX_MESSAGE = "The value %s is not in the specified inclusive range of %s to %s";
    private static final String DEFAULT_MATCHES_PATTERN_EX = "The string %s does not match the pattern %s";
    private static final String DEFAULT_IS_NULL_EX_MESSAGE = "The validated object is null";
    private static final String DEFAULT_IS_TRUE_EX_MESSAGE = "The validated expression is false";
    private static final String DEFAULT_NO_NULL_ELEMENTS_ARRAY_EX_MESSAGE = "The validated array contains null element at index: %d";
    private static final String DEFAULT_NO_NULL_ELEMENTS_COLLECTION_EX_MESSAGE = "The validated collection contains null element at index: %d";
    private static final String DEFAULT_NOT_BLANK_EX_MESSAGE = "The validated character sequence is blank";
    private static final String DEFAULT_NOT_EMPTY_ARRAY_EX_MESSAGE = "The validated array is empty";
    private static final String DEFAULT_NOT_EMPTY_CHAR_SEQUENCE_EX_MESSAGE = "The validated character sequence is empty";
    private static final String DEFAULT_NOT_EMPTY_COLLECTION_EX_MESSAGE = "The validated collection is empty";
    private static final String DEFAULT_NOT_EMPTY_MAP_EX_MESSAGE = "The validated map is empty";
    private static final String DEFAULT_VALID_INDEX_ARRAY_EX_MESSAGE = "The validated array index is invalid: %d";
    private static final String DEFAULT_VALID_INDEX_CHAR_SEQUENCE_EX_MESSAGE = "The validated character sequence index is invalid: %d";
    private static final String DEFAULT_VALID_INDEX_COLLECTION_EX_MESSAGE = "The validated collection index is invalid: %d";
    private static final String DEFAULT_VALID_STATE_EX_MESSAGE = "The validated state is false";
    private static final String DEFAULT_IS_ASSIGNABLE_EX_MESSAGE = "Cannot assign a %s to a %s";
    private static final String DEFAULT_IS_INSTANCE_OF_EX_MESSAGE = "Expected type: %s, actual: %s";

    public static void isTrue(boolean expression, String message, long value) {
        if (!expression) {
            throw new IllegalArgumentException(String.format(message, value));
        }
    }

    public static void isTrue(boolean expression, String message, double value) {
        if (!expression) {
            throw new IllegalArgumentException(String.format(message, value));
        }
    }

    public static void isTrue(boolean expression, String message, Object ... values2) {
        if (!expression) {
            throw new IllegalArgumentException(String.format(message, values2));
        }
    }

    public static void isTrue(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException(DEFAULT_IS_TRUE_EX_MESSAGE);
        }
    }

    public static <T> T notNull(T object) {
        return Validate.notNull(object, DEFAULT_IS_NULL_EX_MESSAGE, new Object[0]);
    }

    public static <T> T notNull(T object, String message, Object ... values2) {
        if (object == null) {
            throw new NullPointerException(String.format(message, values2));
        }
        return object;
    }

    public static <T> T[] notEmpty(T[] array, String message, Object ... values2) {
        if (array == null) {
            throw new NullPointerException(String.format(message, values2));
        }
        if (array.length == 0) {
            throw new IllegalArgumentException(String.format(message, values2));
        }
        return array;
    }

    public static <T> T[] notEmpty(T[] array) {
        return Validate.notEmpty(array, DEFAULT_NOT_EMPTY_ARRAY_EX_MESSAGE, new Object[0]);
    }

    public static <T extends Collection<?>> T notEmpty(T collection, String message, Object ... values2) {
        if (collection == null) {
            throw new NullPointerException(String.format(message, values2));
        }
        if (collection.isEmpty()) {
            throw new IllegalArgumentException(String.format(message, values2));
        }
        return collection;
    }

    public static <T extends Collection<?>> T notEmpty(T collection) {
        return Validate.notEmpty(collection, DEFAULT_NOT_EMPTY_COLLECTION_EX_MESSAGE, new Object[0]);
    }

    public static <T extends Map<?, ?>> T notEmpty(T map, String message, Object ... values2) {
        if (map == null) {
            throw new NullPointerException(String.format(message, values2));
        }
        if (map.isEmpty()) {
            throw new IllegalArgumentException(String.format(message, values2));
        }
        return map;
    }

    public static <T extends Map<?, ?>> T notEmpty(T map) {
        return Validate.notEmpty(map, DEFAULT_NOT_EMPTY_MAP_EX_MESSAGE, new Object[0]);
    }

    public static <T extends CharSequence> T notEmpty(T chars, String message, Object ... values2) {
        if (chars == null) {
            throw new NullPointerException(String.format(message, values2));
        }
        if (chars.length() == 0) {
            throw new IllegalArgumentException(String.format(message, values2));
        }
        return chars;
    }

    public static <T extends CharSequence> T notEmpty(T chars) {
        return Validate.notEmpty(chars, DEFAULT_NOT_EMPTY_CHAR_SEQUENCE_EX_MESSAGE, new Object[0]);
    }

    public static <T extends CharSequence> T notBlank(T chars, String message, Object ... values2) {
        if (chars == null) {
            throw new NullPointerException(String.format(message, values2));
        }
        if (StringUtils.isBlank(chars)) {
            throw new IllegalArgumentException(String.format(message, values2));
        }
        return chars;
    }

    public static <T extends CharSequence> T notBlank(T chars) {
        return Validate.notBlank(chars, DEFAULT_NOT_BLANK_EX_MESSAGE, new Object[0]);
    }

    public static <T> T[] noNullElements(T[] array, String message, Object ... values2) {
        Validate.notNull(array);
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != null) continue;
            Object[] values22 = ArrayUtils.add(values2, Integer.valueOf(i));
            throw new IllegalArgumentException(String.format(message, values22));
        }
        return array;
    }

    public static <T> T[] noNullElements(T[] array) {
        return Validate.noNullElements(array, DEFAULT_NO_NULL_ELEMENTS_ARRAY_EX_MESSAGE, new Object[0]);
    }

    public static <T extends Iterable<?>> T noNullElements(T iterable, String message, Object ... values2) {
        Validate.notNull(iterable);
        int i = 0;
        Iterator<?> it = iterable.iterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                Object[] values22 = ArrayUtils.addAll(values2, i);
                throw new IllegalArgumentException(String.format(message, values22));
            }
            ++i;
        }
        return iterable;
    }

    public static <T extends Iterable<?>> T noNullElements(T iterable) {
        return Validate.noNullElements(iterable, DEFAULT_NO_NULL_ELEMENTS_COLLECTION_EX_MESSAGE, new Object[0]);
    }

    public static <T> T[] validIndex(T[] array, int index, String message, Object ... values2) {
        Validate.notNull(array);
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException(String.format(message, values2));
        }
        return array;
    }

    public static <T> T[] validIndex(T[] array, int index) {
        return Validate.validIndex(array, index, DEFAULT_VALID_INDEX_ARRAY_EX_MESSAGE, new Object[]{index});
    }

    public static <T extends Collection<?>> T validIndex(T collection, int index, String message, Object ... values2) {
        Validate.notNull(collection);
        if (index < 0 || index >= collection.size()) {
            throw new IndexOutOfBoundsException(String.format(message, values2));
        }
        return collection;
    }

    public static <T extends Collection<?>> T validIndex(T collection, int index) {
        return Validate.validIndex(collection, index, DEFAULT_VALID_INDEX_COLLECTION_EX_MESSAGE, new Object[]{index});
    }

    public static <T extends CharSequence> T validIndex(T chars, int index, String message, Object ... values2) {
        Validate.notNull(chars);
        if (index < 0 || index >= chars.length()) {
            throw new IndexOutOfBoundsException(String.format(message, values2));
        }
        return chars;
    }

    public static <T extends CharSequence> T validIndex(T chars, int index) {
        return Validate.validIndex(chars, index, DEFAULT_VALID_INDEX_CHAR_SEQUENCE_EX_MESSAGE, index);
    }

    public static void validState(boolean expression) {
        if (!expression) {
            throw new IllegalStateException(DEFAULT_VALID_STATE_EX_MESSAGE);
        }
    }

    public static void validState(boolean expression, String message, Object ... values2) {
        if (!expression) {
            throw new IllegalStateException(String.format(message, values2));
        }
    }

    public static void matchesPattern(CharSequence input, String pattern) {
        if (!Pattern.matches(pattern, input)) {
            throw new IllegalArgumentException(String.format(DEFAULT_MATCHES_PATTERN_EX, input, pattern));
        }
    }

    public static void matchesPattern(CharSequence input, String pattern, String message, Object ... values2) {
        if (!Pattern.matches(pattern, input)) {
            throw new IllegalArgumentException(String.format(message, values2));
        }
    }

    public static void notNaN(double value) {
        Validate.notNaN(value, DEFAULT_NOT_NAN_EX_MESSAGE, new Object[0]);
    }

    public static void notNaN(double value, String message, Object ... values2) {
        if (Double.isNaN(value)) {
            throw new IllegalArgumentException(String.format(message, values2));
        }
    }

    public static void finite(double value) {
        Validate.finite(value, DEFAULT_FINITE_EX_MESSAGE, value);
    }

    public static void finite(double value, String message, Object ... values2) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException(String.format(message, values2));
        }
    }

    public static <T> void inclusiveBetween(T start, T end, Comparable<T> value) {
        if (value.compareTo(start) < 0 || value.compareTo(end) > 0) {
            throw new IllegalArgumentException(String.format(DEFAULT_INCLUSIVE_BETWEEN_EX_MESSAGE, value, start, end));
        }
    }

    public static <T> void inclusiveBetween(T start, T end, Comparable<T> value, String message, Object ... values2) {
        if (value.compareTo(start) < 0 || value.compareTo(end) > 0) {
            throw new IllegalArgumentException(String.format(message, values2));
        }
    }

    public static void inclusiveBetween(long start, long end, long value) {
        if (value < start || value > end) {
            throw new IllegalArgumentException(String.format(DEFAULT_INCLUSIVE_BETWEEN_EX_MESSAGE, value, start, end));
        }
    }

    public static void inclusiveBetween(long start, long end, long value, String message) {
        if (value < start || value > end) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void inclusiveBetween(double start, double end, double value) {
        if (value < start || value > end) {
            throw new IllegalArgumentException(String.format(DEFAULT_INCLUSIVE_BETWEEN_EX_MESSAGE, value, start, end));
        }
    }

    public static void inclusiveBetween(double start, double end, double value, String message) {
        if (value < start || value > end) {
            throw new IllegalArgumentException(message);
        }
    }

    public static <T> void exclusiveBetween(T start, T end, Comparable<T> value) {
        if (value.compareTo(start) <= 0 || value.compareTo(end) >= 0) {
            throw new IllegalArgumentException(String.format(DEFAULT_EXCLUSIVE_BETWEEN_EX_MESSAGE, value, start, end));
        }
    }

    public static <T> void exclusiveBetween(T start, T end, Comparable<T> value, String message, Object ... values2) {
        if (value.compareTo(start) <= 0 || value.compareTo(end) >= 0) {
            throw new IllegalArgumentException(String.format(message, values2));
        }
    }

    public static void exclusiveBetween(long start, long end, long value) {
        if (value <= start || value >= end) {
            throw new IllegalArgumentException(String.format(DEFAULT_EXCLUSIVE_BETWEEN_EX_MESSAGE, value, start, end));
        }
    }

    public static void exclusiveBetween(long start, long end, long value, String message) {
        if (value <= start || value >= end) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void exclusiveBetween(double start, double end, double value) {
        if (value <= start || value >= end) {
            throw new IllegalArgumentException(String.format(DEFAULT_EXCLUSIVE_BETWEEN_EX_MESSAGE, value, start, end));
        }
    }

    public static void exclusiveBetween(double start, double end, double value, String message) {
        if (value <= start || value >= end) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isInstanceOf(Class<?> type, Object obj) {
        if (!type.isInstance(obj)) {
            throw new IllegalArgumentException(String.format(DEFAULT_IS_INSTANCE_OF_EX_MESSAGE, type.getName(), obj == null ? "null" : obj.getClass().getName()));
        }
    }

    public static void isInstanceOf(Class<?> type, Object obj, String message, Object ... values2) {
        if (!type.isInstance(obj)) {
            throw new IllegalArgumentException(String.format(message, values2));
        }
    }

    public static void isAssignableFrom(Class<?> superType, Class<?> type) {
        if (!superType.isAssignableFrom(type)) {
            throw new IllegalArgumentException(String.format(DEFAULT_IS_ASSIGNABLE_EX_MESSAGE, type == null ? "null" : type.getName(), superType.getName()));
        }
    }

    public static void isAssignableFrom(Class<?> superType, Class<?> type, String message, Object ... values2) {
        if (!superType.isAssignableFrom(type)) {
            throw new IllegalArgumentException(String.format(message, values2));
        }
    }
}

