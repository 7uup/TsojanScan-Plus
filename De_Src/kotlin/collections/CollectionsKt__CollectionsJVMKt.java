/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.builders.ListBuilder;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000T\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0001\u001a?\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u001d\u0010\b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\u0002\b\u000bH\u0081\b\u00f8\u0001\u0000\u001a7\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u001d\u0010\b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\u0002\b\u000bH\u0081\b\u00f8\u0001\u0000\u001a\u0011\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u0081\b\u001a\u0011\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0007H\u0081\b\u001a\"\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00112\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0081\b\u00a2\u0006\u0002\u0010\u0015\u001a4\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00160\u0011\"\u0004\b\u0000\u0010\u00162\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u00142\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00160\u0011H\u0081\b\u00a2\u0006\u0002\u0010\u0018\u001a\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\"\u0004\b\u0000\u0010\u0002H\u0001\u001a\u001c\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0001\u001a\u001f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00160\u0001\"\u0004\b\u0000\u0010\u00162\u0006\u0010\u001b\u001a\u0002H\u0016\u00a2\u0006\u0002\u0010\u001c\u001a1\u0010\u001d\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00120\u0011\"\u0004\b\u0000\u0010\u0016*\n\u0012\u0006\b\u0001\u0012\u0002H\u00160\u00112\u0006\u0010\u001e\u001a\u00020\u001fH\u0000\u00a2\u0006\u0002\u0010 \u001a\u001e\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u00160\u0001\"\u0004\b\u0000\u0010\u0016*\b\u0012\u0004\u0012\u0002H\u00160\"H\u0007\u001a&\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u00160\u0001\"\u0004\b\u0000\u0010\u0016*\b\u0012\u0004\u0012\u0002H\u00160\"2\u0006\u0010#\u001a\u00020$H\u0007\u001a\u001f\u0010%\u001a\b\u0012\u0004\u0012\u0002H\u00160\u0001\"\u0004\b\u0000\u0010\u0016*\b\u0012\u0004\u0012\u0002H\u00160&H\u0087\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006'"}, d2={"build", "", "E", "builder", "", "buildListInternal", "capacity", "", "builderAction", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "checkCountOverflow", "count", "checkIndexOverflow", "index", "copyToArrayImpl", "", "", "collection", "", "(Ljava/util/Collection;)[Ljava/lang/Object;", "T", "array", "(Ljava/util/Collection;[Ljava/lang/Object;)[Ljava/lang/Object;", "createListBuilder", "listOf", "element", "(Ljava/lang/Object;)Ljava/util/List;", "copyToArrayOfAny", "isVarargs", "", "([Ljava/lang/Object;Z)[Ljava/lang/Object;", "shuffled", "", "random", "Ljava/util/Random;", "toList", "Ljava/util/Enumeration;", "kotlin-stdlib"}, xs="kotlin/collections/CollectionsKt")
class CollectionsKt__CollectionsJVMKt {
    @NotNull
    public static final <T> List<T> listOf(T element) {
        List<T> list = Collections.singletonList(element);
        Intrinsics.checkNotNullExpressionValue(list, "java.util.Collections.singletonList(element)");
        return list;
    }

    @PublishedApi
    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final <E> List<E> buildListInternal(Function1<? super List<E>, Unit> builderAction) {
        int $i$f$buildListInternal = 0;
        List<E> list = CollectionsKt.createListBuilder();
        boolean bl = false;
        boolean bl2 = false;
        builderAction.invoke(list);
        return CollectionsKt.build(list);
    }

    @PublishedApi
    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final <E> List<E> buildListInternal(int capacity, Function1<? super List<E>, Unit> builderAction) {
        int $i$f$buildListInternal = 0;
        List<E> list = CollectionsKt.createListBuilder(capacity);
        boolean bl = false;
        boolean bl2 = false;
        builderAction.invoke(list);
        return CollectionsKt.build(list);
    }

    @PublishedApi
    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @NotNull
    public static final <E> List<E> createListBuilder() {
        return new ListBuilder();
    }

    @PublishedApi
    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @NotNull
    public static final <E> List<E> createListBuilder(int capacity) {
        return new ListBuilder(capacity);
    }

    @PublishedApi
    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @NotNull
    public static final <E> List<E> build(@NotNull List<E> builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        return ((ListBuilder)builder).build();
    }

    @InlineOnly
    private static final <T> List<T> toList(Enumeration<T> $this$toList) {
        int $i$f$toList = 0;
        ArrayList<T> arrayList = Collections.list($this$toList);
        Intrinsics.checkNotNullExpressionValue(arrayList, "java.util.Collections.list(this)");
        return arrayList;
    }

    @SinceKotlin(version="1.2")
    @NotNull
    public static final <T> List<T> shuffled(@NotNull Iterable<? extends T> $this$shuffled) {
        Intrinsics.checkNotNullParameter($this$shuffled, "$this$shuffled");
        List<? extends T> list = CollectionsKt.toMutableList($this$shuffled);
        boolean bl = false;
        boolean bl2 = false;
        List<? extends T> $this$apply = list;
        boolean bl3 = false;
        List<? extends T> list2 = $this$apply;
        boolean bl4 = false;
        Collections.shuffle(list2);
        return list;
    }

    @SinceKotlin(version="1.2")
    @NotNull
    public static final <T> List<T> shuffled(@NotNull Iterable<? extends T> $this$shuffled, @NotNull Random random) {
        Intrinsics.checkNotNullParameter($this$shuffled, "$this$shuffled");
        Intrinsics.checkNotNullParameter(random, "random");
        List<? extends T> list = CollectionsKt.toMutableList($this$shuffled);
        boolean bl = false;
        boolean bl2 = false;
        List<? extends T> $this$apply = list;
        boolean bl3 = false;
        List<? extends T> list2 = $this$apply;
        Random random2 = random;
        boolean bl4 = false;
        Collections.shuffle(list2, random2);
        return list;
    }

    @InlineOnly
    private static final Object[] copyToArrayImpl(Collection<?> collection) {
        int $i$f$copyToArrayImpl = 0;
        return CollectionToArray.toArray(collection);
    }

    @InlineOnly
    private static final <T> T[] copyToArrayImpl(Collection<?> collection, T[] array) {
        int $i$f$copyToArrayImpl = 0;
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        }
        Object[] objectArray = CollectionToArray.toArray(collection, array);
        if (objectArray == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        return objectArray;
    }

    @NotNull
    public static final <T> Object[] copyToArrayOfAny(@NotNull T[] $this$copyToArrayOfAny, boolean isVarargs) {
        Intrinsics.checkNotNullParameter($this$copyToArrayOfAny, "$this$copyToArrayOfAny");
        Object[] objectArray = isVarargs && Intrinsics.areEqual($this$copyToArrayOfAny.getClass(), Object[].class) ? $this$copyToArrayOfAny : Arrays.copyOf($this$copyToArrayOfAny, $this$copyToArrayOfAny.length, Object[].class);
        Intrinsics.checkNotNullExpressionValue(objectArray, "if (isVarargs && this.ja\u2026 Array<Any?>::class.java)");
        return objectArray;
    }

    @PublishedApi
    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final int checkIndexOverflow(int index) {
        int $i$f$checkIndexOverflow = 0;
        if (index < 0) {
            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
                CollectionsKt.throwIndexOverflow();
            } else {
                throw (Throwable)new ArithmeticException("Index overflow has happened.");
            }
        }
        return index;
    }

    @PublishedApi
    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final int checkCountOverflow(int count) {
        int $i$f$checkCountOverflow = 0;
        if (count < 0) {
            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
                CollectionsKt.throwCountOverflow();
            } else {
                throw (Throwable)new ArithmeticException("Count overflow has happened.");
            }
        }
        return count;
    }
}

