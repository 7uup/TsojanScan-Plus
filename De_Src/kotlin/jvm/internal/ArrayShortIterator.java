/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.ShortIterator;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0017\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\n\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0096\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2={"Lkotlin/jvm/internal/ArrayShortIterator;", "Lkotlin/collections/ShortIterator;", "array", "", "([S)V", "index", "", "hasNext", "", "nextShort", "", "kotlin-stdlib"})
final class ArrayShortIterator
extends ShortIterator {
    private int index;
    private final short[] array;

    @Override
    public boolean hasNext() {
        return this.index < this.array.length;
    }

    @Override
    public short nextShort() {
        short s2;
        try {
            int s22 = this.index;
            this.index = s22 + 1;
            s2 = this.array[s22];
        } catch (ArrayIndexOutOfBoundsException e) {
            --this.index;
            throw (Throwable)new NoSuchElementException(e.getMessage());
        }
        return s2;
    }

    public ArrayShortIterator(@NotNull short[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        this.array = array;
    }
}

