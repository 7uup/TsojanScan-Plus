/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.collections;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0007\bb\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u00012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003J\u0015\u0010\u0007\u001a\u00028\u00012\u0006\u0010\b\u001a\u00028\u0000H&\u00a2\u0006\u0002\u0010\tR\u001e\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\n"}, d2={"Lkotlin/collections/MapWithDefault;", "K", "V", "", "map", "getMap", "()Ljava/util/Map;", "getOrImplicitDefault", "key", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-stdlib"})
interface MapWithDefault<K, V>
extends Map<K, V>,
KMappedMarker {
    @NotNull
    public Map<K, V> getMap();

    public V getOrImplicitDefault(K var1);
}

