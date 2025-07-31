/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.nullness;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class Opt {
    private Opt() {
        throw new AssertionError((Object)"shouldn't be instantiated");
    }

    public static <T> T get(T primary) {
        if (primary == null) {
            throw new NoSuchElementException("No value present");
        }
        return primary;
    }

    @EnsuresNonNullIf(expression={"#1"}, result=true)
    public static boolean isPresent(@Nullable Object primary) {
        return primary != null;
    }

    public static <T> void ifPresent(T primary, Consumer<@NonNull ? super @NonNull T> consumer) {
        if (primary != null) {
            consumer.accept(primary);
        }
    }

    public static <T> @Nullable T filter(T primary, Predicate<@NonNull ? super @NonNull T> predicate) {
        if (primary == null) {
            return null;
        }
        return (T)(predicate.test(primary) ? primary : null);
    }

    public static <T, U> @Nullable U map(T primary, Function<@NonNull ? super @NonNull T, ? extends U> mapper) {
        if (primary == null) {
            return null;
        }
        return mapper.apply(primary);
    }

    public static <T> @NonNull T orElse(T primary, @NonNull T other) {
        return primary != null ? primary : other;
    }

    public static <T> @NonNull T orElseGet(T primary, Supplier<? extends @NonNull T> other) {
        return primary != null ? primary : other.get();
    }

    public static <T, X extends Throwable> T orElseThrow(T primary, Supplier<? extends X> exceptionSupplier) throws X {
        if (primary != null) {
            return primary;
        }
        throw (Throwable)exceptionSupplier.get();
    }
}

