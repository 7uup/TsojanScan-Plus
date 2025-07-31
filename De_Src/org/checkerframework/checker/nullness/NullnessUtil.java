/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.nullness;

import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class NullnessUtil {
    private NullnessUtil() {
        throw new AssertionError((Object)"shouldn't be instantiated");
    }

    @EnsuresNonNull(value={"#1"})
    public static <T> @NonNull T castNonNull(@Nullable T ref) {
        assert (ref != null) : "Misuse of castNonNull: called with a null argument";
        return ref;
    }

    @EnsuresNonNull(value={"#1"})
    public static <T> @NonNull T @NonNull [] castNonNullDeep(T @Nullable [] arr) {
        return NullnessUtil.castNonNullArray(arr);
    }

    @EnsuresNonNull(value={"#1"})
    public static <T> @NonNull T @NonNull [][] castNonNullDeep(T @Nullable [] @Nullable [] arr) {
        return (Object[][])NullnessUtil.castNonNullArray(arr);
    }

    @EnsuresNonNull(value={"#1"})
    public static <T> @NonNull T @NonNull [][][] castNonNullDeep(T @Nullable [] @Nullable [] @Nullable [] arr) {
        return (Object[][][])NullnessUtil.castNonNullArray(arr);
    }

    @EnsuresNonNull(value={"#1"})
    public static <T> @NonNull T @NonNull [][][][] castNonNullDeep(T @Nullable [] @Nullable [] @Nullable [] @Nullable [] arr) {
        return (Object[][][][])NullnessUtil.castNonNullArray(arr);
    }

    @EnsuresNonNull(value={"#1"})
    public static <T> @NonNull T @NonNull [][][][][] castNonNullDeep(T @Nullable [] @Nullable [] @Nullable [] @Nullable [] @Nullable [] arr) {
        return (Object[][][][][])NullnessUtil.castNonNullArray(arr);
    }

    private static <T> @NonNull T @NonNull [] castNonNullArray(T @Nullable [] arr) {
        assert (arr != null) : "Misuse of castNonNullArray: called with a null array argument";
        for (int i = 0; i < arr.length; ++i) {
            assert (arr[i] != null) : "Misuse of castNonNull: called with a null array element";
            NullnessUtil.checkIfArray(arr[i]);
        }
        return arr;
    }

    private static void checkIfArray(@NonNull Object ref) {
        assert (ref != null) : "Misuse of checkIfArray: called with a null argument";
        Class<?> comp = ref.getClass().getComponentType();
        if (comp != null && !comp.isPrimitive()) {
            NullnessUtil.castNonNullArray((Object[])ref);
        }
    }
}

