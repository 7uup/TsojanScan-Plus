/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.collections;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0006J\t\u0010\f\u001a\u00020\u0004H\u00c6\u0003J\u000e\u0010\r\u001a\u00028\u0000H\u00c6\u0003\u00a2\u0006\u0002\u0010\nJ(\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00028\u0000H\u00c6\u0001\u00a2\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0004H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0005\u001a\u00028\u0000\u00a2\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0016"}, d2={"Lkotlin/collections/IndexedValue;", "T", "", "index", "", "value", "(ILjava/lang/Object;)V", "getIndex", "()I", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "copy", "(ILjava/lang/Object;)Lkotlin/collections/IndexedValue;", "equals", "", "other", "hashCode", "toString", "", "kotlin-stdlib"})
public final class IndexedValue<T> {
    private final int index;
    private final T value;

    public final int getIndex() {
        return this.index;
    }

    public final T getValue() {
        return this.value;
    }

    public IndexedValue(int index, T value) {
        this.index = index;
        this.value = value;
    }

    public final int component1() {
        return this.index;
    }

    public final T component2() {
        return this.value;
    }

    @NotNull
    public final IndexedValue<T> copy(int index, T value) {
        return new IndexedValue<T>(index, value);
    }

    public static /* synthetic */ IndexedValue copy$default(IndexedValue indexedValue, int n, Object object, int n2, Object object2) {
        if ((n2 & 1) != 0) {
            n = indexedValue.index;
        }
        if ((n2 & 2) != 0) {
            object = indexedValue.value;
        }
        return indexedValue.copy(n, object);
    }

    @NotNull
    public String toString() {
        return "IndexedValue(index=" + this.index + ", value=" + this.value + ")";
    }

    public int hashCode() {
        T t = this.value;
        return this.index * 31 + (t != null ? t.hashCode() : 0);
    }

    public boolean equals(@Nullable Object object) {
        block3: {
            block2: {
                if (this == object) break block2;
                if (!(object instanceof IndexedValue)) break block3;
                IndexedValue indexedValue = (IndexedValue)object;
                if (this.index != indexedValue.index || !Intrinsics.areEqual(this.value, indexedValue.value)) break block3;
            }
            return true;
        }
        return false;
    }
}

