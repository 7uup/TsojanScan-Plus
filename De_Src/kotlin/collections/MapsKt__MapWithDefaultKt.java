/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.collections;

import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.collections.MapWithDefault;
import kotlin.collections.MapWithDefaultImpl;
import kotlin.collections.MapsKt;
import kotlin.collections.MutableMapWithDefault;
import kotlin.collections.MutableMapWithDefaultImpl;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000\u001e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\u001a3\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u0001\u00a2\u0006\u0004\b\u0005\u0010\u0006\u001aQ\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u00032!\u0010\b\u001a\u001d\u0012\u0013\u0012\u0011H\u0002\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u0002H\u00010\t\u001aX\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\f2!\u0010\b\u001a\u001d\u0012\u0013\u0012\u0011H\u0002\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u0002H\u00010\tH\u0007\u00a2\u0006\u0002\b\r\u00a8\u0006\u000e"}, d2={"getOrImplicitDefault", "V", "K", "", "key", "getOrImplicitDefaultNullable", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/lang/Object;", "withDefault", "defaultValue", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "", "withDefaultMutable", "kotlin-stdlib"}, xs="kotlin/collections/MapsKt")
class MapsKt__MapWithDefaultKt {
    @JvmName(name="getOrImplicitDefaultNullable")
    @PublishedApi
    public static final <K, V> V getOrImplicitDefaultNullable(@NotNull Map<K, ? extends V> $this$getOrImplicitDefault, K key) {
        Intrinsics.checkNotNullParameter($this$getOrImplicitDefault, "$this$getOrImplicitDefault");
        if ($this$getOrImplicitDefault instanceof MapWithDefault) {
            return ((MapWithDefault)$this$getOrImplicitDefault).getOrImplicitDefault(key);
        }
        Map<K, V> $this$getOrElseNullable$iv = $this$getOrImplicitDefault;
        boolean $i$f$getOrElseNullable = false;
        V value$iv = $this$getOrElseNullable$iv.get(key);
        if (value$iv == null && !$this$getOrElseNullable$iv.containsKey(key)) {
            boolean bl = false;
            throw (Throwable)new NoSuchElementException("Key " + key + " is missing in the map.");
        }
        return value$iv;
    }

    @NotNull
    public static final <K, V> Map<K, V> withDefault(@NotNull Map<K, ? extends V> $this$withDefault, @NotNull Function1<? super K, ? extends V> defaultValue) {
        Intrinsics.checkNotNullParameter($this$withDefault, "$this$withDefault");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        Map<K, ? extends V> map = $this$withDefault;
        return map instanceof MapWithDefault ? MapsKt.withDefault(((MapWithDefault)$this$withDefault).getMap(), defaultValue) : (Map)new MapWithDefaultImpl<K, V>($this$withDefault, defaultValue);
    }

    @JvmName(name="withDefaultMutable")
    @NotNull
    public static final <K, V> Map<K, V> withDefaultMutable(@NotNull Map<K, V> $this$withDefault, @NotNull Function1<? super K, ? extends V> defaultValue) {
        Intrinsics.checkNotNullParameter($this$withDefault, "$this$withDefault");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        Map<K, V> map = $this$withDefault;
        return map instanceof MutableMapWithDefault ? MapsKt.withDefaultMutable(((MutableMapWithDefault)$this$withDefault).getMap(), defaultValue) : (Map)new MutableMapWithDefaultImpl<K, V>($this$withDefault, defaultValue);
    }
}

