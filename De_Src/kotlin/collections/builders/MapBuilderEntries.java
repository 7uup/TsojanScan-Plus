/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.collections.builders;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.AbstractMutableSet;
import kotlin.collections.builders.MapBuilder;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableSet;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010'\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010)\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00040\u00032\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00040\u0005B\u001b\b\u0000\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\u000f\u001a\u00020\u00102\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004H\u0016J\"\u0010\u0012\u001a\u00020\u00102\u0018\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00040\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u001d\u0010\u0017\u001a\u00020\u00102\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004H\u0096\u0002J\"\u0010\u0018\u001a\u00020\u00102\u0018\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00040\u0014H\u0016J\b\u0010\u0019\u001a\u00020\u0010H\u0016J\u001b\u0010\u001a\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00040\u001bH\u0096\u0002J\u001c\u0010\u001c\u001a\u00020\u00102\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004H\u0016J\"\u0010\u001d\u001a\u00020\u00102\u0018\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00040\u0014H\u0016J\"\u0010\u001e\u001a\u00020\u00102\u0018\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00040\u0014H\u0016R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001f"}, d2={"Lkotlin/collections/builders/MapBuilderEntries;", "K", "V", "", "", "Lkotlin/collections/AbstractMutableSet;", "backing", "Lkotlin/collections/builders/MapBuilder;", "(Lkotlin/collections/builders/MapBuilder;)V", "getBacking", "()Lkotlin/collections/builders/MapBuilder;", "size", "", "getSize", "()I", "add", "", "element", "addAll", "elements", "", "clear", "", "contains", "containsAll", "isEmpty", "iterator", "", "remove", "removeAll", "retainAll", "kotlin-stdlib"})
public final class MapBuilderEntries<K, V>
extends AbstractMutableSet<Map.Entry<K, V>>
implements Set<Map.Entry<K, V>>,
KMutableSet {
    @NotNull
    private final MapBuilder<K, V> backing;

    @Override
    public int getSize() {
        return this.backing.size();
    }

    @Override
    public boolean isEmpty() {
        return this.backing.isEmpty();
    }

    public boolean contains(@NotNull Map.Entry<K, V> element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return this.backing.containsEntry$kotlin_stdlib(element);
    }

    @Override
    public void clear() {
        this.backing.clear();
    }

    @Override
    public boolean add(@NotNull Map.Entry<K, V> element) {
        Intrinsics.checkNotNullParameter(element, "element");
        throw (Throwable)new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends Map.Entry<K, V>> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        throw (Throwable)new UnsupportedOperationException();
    }

    public boolean remove(@NotNull Map.Entry<K, V> element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return this.backing.removeEntry$kotlin_stdlib(element);
    }

    @Override
    @NotNull
    public Iterator<Map.Entry<K, V>> iterator() {
        return this.backing.entriesIterator$kotlin_stdlib();
    }

    @Override
    public boolean containsAll(@NotNull Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return this.backing.containsAllEntries$kotlin_stdlib(elements);
    }

    @Override
    public boolean removeAll(@NotNull Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        this.backing.checkIsMutable$kotlin_stdlib();
        return super.removeAll(elements);
    }

    @Override
    public boolean retainAll(@NotNull Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        this.backing.checkIsMutable$kotlin_stdlib();
        return super.retainAll(elements);
    }

    @NotNull
    public final MapBuilder<K, V> getBacking() {
        return this.backing;
    }

    public MapBuilderEntries(@NotNull MapBuilder<K, V> backing) {
        Intrinsics.checkNotNullParameter(backing, "backing");
        this.backing = backing;
    }
}

