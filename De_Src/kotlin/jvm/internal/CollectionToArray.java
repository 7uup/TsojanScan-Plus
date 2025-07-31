/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm.internal;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u00002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a#\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0007\u00a2\u0006\u0004\b\t\u0010\n\u001a5\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0010\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0001H\u0007\u00a2\u0006\u0004\b\t\u0010\f\u001a~\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0014\u0010\u000e\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u000f2\u001a\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u00112(\u0010\u0012\u001a$\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u0013H\u0082\b\u00a2\u0006\u0002\u0010\u0014\"\u0018\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2={"EMPTY", "", "", "[Ljava/lang/Object;", "MAX_SIZE", "", "collectionToArray", "collection", "", "toArray", "(Ljava/util/Collection;)[Ljava/lang/Object;", "a", "(Ljava/util/Collection;[Ljava/lang/Object;)[Ljava/lang/Object;", "toArrayImpl", "empty", "Lkotlin/Function0;", "alloc", "Lkotlin/Function1;", "trim", "Lkotlin/Function2;", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)[Ljava/lang/Object;", "kotlin-stdlib"})
@JvmName(name="CollectionToArray")
public final class CollectionToArray {
    private static final Object[] EMPTY = new Object[0];
    private static final int MAX_SIZE = 0x7FFFFFFD;

    /*
     * WARNING - void declaration
     */
    @JvmName(name="toArray")
    @NotNull
    public static final Object[] toArray(@NotNull Collection<?> collection) {
        Object[] objectArray;
        block9: {
            Intrinsics.checkNotNullParameter(collection, "collection");
            boolean $i$f$toArrayImpl = false;
            int size$iv = collection.size();
            if (size$iv == 0) {
                boolean bl = false;
                objectArray = EMPTY;
            } else {
                Iterator<?> iter$iv = collection.iterator();
                if (!iter$iv.hasNext()) {
                    boolean bl = false;
                    objectArray = EMPTY;
                } else {
                    void size;
                    int size2 = size$iv;
                    boolean bl = false;
                    Object[] result$iv = new Object[size2];
                    int i$iv = 0;
                    while (true) {
                        result$iv[i$iv++] = iter$iv.next();
                        if (i$iv >= result$iv.length) {
                            if (!iter$iv.hasNext()) {
                                objectArray = result$iv;
                                break block9;
                            }
                            int newSize$iv = i$iv * 3 + 1 >>> 1;
                            if (newSize$iv <= i$iv) {
                                if (i$iv >= 0x7FFFFFFD) {
                                    throw (Throwable)new OutOfMemoryError();
                                }
                                newSize$iv = 0x7FFFFFFD;
                            }
                            Intrinsics.checkNotNullExpressionValue(Arrays.copyOf(result$iv, newSize$iv), "Arrays.copyOf(result, newSize)");
                            continue;
                        }
                        if (!iter$iv.hasNext()) break;
                    }
                    int n = i$iv;
                    Object[] result = result$iv;
                    boolean bl2 = false;
                    Object[] objectArray2 = Arrays.copyOf(result, (int)size);
                    objectArray = objectArray2;
                    Intrinsics.checkNotNullExpressionValue(objectArray2, "Arrays.copyOf(result, size)");
                }
            }
        }
        return objectArray;
    }

    /*
     * WARNING - void declaration
     */
    @JvmName(name="toArray")
    @NotNull
    public static final Object[] toArray(@NotNull Collection<?> collection, @Nullable Object[] a) {
        Object[] objectArray;
        block17: {
            Intrinsics.checkNotNullParameter(collection, "collection");
            if (a == null) {
                throw (Throwable)new NullPointerException();
            }
            boolean $i$f$toArrayImpl = false;
            int size$iv = collection.size();
            if (size$iv == 0) {
                boolean bl = false;
                if (a.length > 0) {
                    a[0] = null;
                }
                objectArray = a;
            } else {
                Iterator<?> iter$iv = collection.iterator();
                if (!iter$iv.hasNext()) {
                    boolean bl = false;
                    if (a.length > 0) {
                        a[0] = null;
                    }
                    objectArray = a;
                } else {
                    Object[] objectArray2;
                    int size = size$iv;
                    boolean bl = false;
                    if (size <= a.length) {
                        objectArray2 = a;
                    } else {
                        Object object = Array.newInstance(a.getClass().getComponentType(), size);
                        if (object == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                        }
                        objectArray2 = (Object[])object;
                    }
                    Object[] result$iv = objectArray2;
                    int i$iv = 0;
                    while (true) {
                        result$iv[i$iv++] = iter$iv.next();
                        if (i$iv >= result$iv.length) {
                            if (!iter$iv.hasNext()) {
                                objectArray = result$iv;
                                break block17;
                            }
                            int newSize$iv = i$iv * 3 + 1 >>> 1;
                            if (newSize$iv <= i$iv) {
                                if (i$iv >= 0x7FFFFFFD) {
                                    throw (Throwable)new OutOfMemoryError();
                                }
                                newSize$iv = 0x7FFFFFFD;
                            }
                            Intrinsics.checkNotNullExpressionValue(Arrays.copyOf(result$iv, newSize$iv), "Arrays.copyOf(result, newSize)");
                            continue;
                        }
                        if (!iter$iv.hasNext()) break;
                    }
                    int n = i$iv;
                    Object[] result = result$iv;
                    boolean bl2 = false;
                    if (result == a) {
                        a[size2] = null;
                        objectArray = a;
                    } else {
                        void size2;
                        Object[] objectArray3 = Arrays.copyOf(result, (int)size2);
                        objectArray = objectArray3;
                        Intrinsics.checkNotNullExpressionValue(objectArray3, "Arrays.copyOf(result, size)");
                    }
                }
            }
        }
        return objectArray;
    }

    private static final Object[] toArrayImpl(Collection<?> collection, Function0<Object[]> empty, Function1<? super Integer, Object[]> alloc, Function2<? super Object[], ? super Integer, Object[]> trim) {
        int $i$f$toArrayImpl = 0;
        int size = collection.size();
        if (size == 0) {
            return empty.invoke();
        }
        Iterator<?> iter = collection.iterator();
        if (!iter.hasNext()) {
            return empty.invoke();
        }
        Object[] result = alloc.invoke((Integer)size);
        int i = 0;
        while (true) {
            result[i++] = iter.next();
            if (i >= result.length) {
                if (!iter.hasNext()) {
                    return result;
                }
                int newSize = i * 3 + 1 >>> 1;
                if (newSize <= i) {
                    if (i >= 0x7FFFFFFD) {
                        throw (Throwable)new OutOfMemoryError();
                    }
                    newSize = 0x7FFFFFFD;
                }
                Intrinsics.checkNotNullExpressionValue(Arrays.copyOf(result, newSize), "Arrays.copyOf(result, newSize)");
                continue;
            }
            if (!iter.hasNext()) break;
        }
        return trim.invoke((Object[])result, (Integer)i);
    }
}

