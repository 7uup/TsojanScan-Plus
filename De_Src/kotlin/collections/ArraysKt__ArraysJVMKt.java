/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.collections;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u00002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\u001a/\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u00a2\u0006\u0002\u0010\u0006\u001a\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0001\u001a#\u0010\n\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0001H\u0001\u00a2\u0006\u0004\b\u000b\u0010\f\u001a,\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0001H\u0086\b\u00a2\u0006\u0002\u0010\u000e\u001a\u0015\u0010\u000f\u001a\u00020\u0010*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0087\b\u001a&\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u0015H\u0086\b\u00a2\u0006\u0002\u0010\u0016\u00a8\u0006\u0017"}, d2={"arrayOfNulls", "", "T", "reference", "size", "", "([Ljava/lang/Object;I)[Ljava/lang/Object;", "copyOfRangeToIndexCheck", "", "toIndex", "contentDeepHashCodeImpl", "contentDeepHashCode", "([Ljava/lang/Object;)I", "orEmpty", "([Ljava/lang/Object;)[Ljava/lang/Object;", "toString", "", "", "charset", "Ljava/nio/charset/Charset;", "toTypedArray", "", "(Ljava/util/Collection;)[Ljava/lang/Object;", "kotlin-stdlib"}, xs="kotlin/collections/ArraysKt")
class ArraysKt__ArraysJVMKt {
    @NotNull
    public static final /* synthetic */ <T> T[] orEmpty(@Nullable T[] $this$orEmpty) {
        int $i$f$orEmpty = 0;
        Object[] objectArray = $this$orEmpty;
        if ($this$orEmpty == null) {
            Intrinsics.reifiedOperationMarker(0, "T?");
            objectArray = new Object[]{};
        }
        return objectArray;
    }

    @InlineOnly
    private static final String toString(byte[] $this$toString, Charset charset) {
        int $i$f$toString = 0;
        boolean bl = false;
        return new String($this$toString, charset);
    }

    @NotNull
    public static final /* synthetic */ <T> T[] toTypedArray(@NotNull Collection<? extends T> $this$toTypedArray) {
        int $i$f$toTypedArray = 0;
        Intrinsics.checkNotNullParameter($this$toTypedArray, "$this$toTypedArray");
        Collection<Object> thisCollection = $this$toTypedArray;
        Intrinsics.reifiedOperationMarker(0, "T?");
        Object[] objectArray = thisCollection.toArray(new Object[0]);
        if (objectArray == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        return objectArray;
    }

    @NotNull
    public static final <T> T[] arrayOfNulls(@NotNull T[] reference, int size) {
        Intrinsics.checkNotNullParameter(reference, "reference");
        Object object = Array.newInstance(reference.getClass().getComponentType(), size);
        if (object == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        return (Object[])object;
    }

    @SinceKotlin(version="1.3")
    public static final void copyOfRangeToIndexCheck(int toIndex, int size) {
        if (toIndex > size) {
            throw (Throwable)new IndexOutOfBoundsException("toIndex (" + toIndex + ") is greater than size (" + size + ").");
        }
    }

    @SinceKotlin(version="1.3")
    @PublishedApi
    @JvmName(name="contentDeepHashCode")
    public static final <T> int contentDeepHashCode(@Nullable T[] $this$contentDeepHashCodeImpl) {
        return Arrays.deepHashCode($this$contentDeepHashCodeImpl);
    }
}

