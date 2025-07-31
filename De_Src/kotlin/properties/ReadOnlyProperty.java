/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.properties;

import kotlin.Metadata;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00e6\u0080\u0001\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0000*\u0006\b\u0001\u0010\u0002 \u00012\u00020\u0003J\"\u0010\u0004\u001a\u00028\u00012\u0006\u0010\u0005\u001a\u00028\u00002\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007H\u00a6\u0002\u00a2\u0006\u0002\u0010\b\u00a8\u0006\t"}, d2={"Lkotlin/properties/ReadOnlyProperty;", "T", "V", "", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "kotlin-stdlib"})
public interface ReadOnlyProperty<T, V> {
    public V getValue(T var1, @NotNull KProperty<?> var2);
}

