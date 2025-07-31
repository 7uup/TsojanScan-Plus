/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.reflect;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.functions.Function2;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0006\b\u0002\u0010\u0003 \u00012\b\u0012\u0004\u0012\u0002H\u00030\u00042\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005:\u0001\u0010J\u001d\u0010\n\u001a\u00028\u00022\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u0001H&\u00a2\u0006\u0002\u0010\rJ\u001f\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u0001H'\u00a2\u0006\u0002\u0010\rR$\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0011"}, d2={"Lkotlin/reflect/KProperty2;", "D", "E", "V", "Lkotlin/reflect/KProperty;", "Lkotlin/Function2;", "getter", "Lkotlin/reflect/KProperty2$Getter;", "getGetter", "()Lkotlin/reflect/KProperty2$Getter;", "get", "receiver1", "receiver2", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "getDelegate", "", "Getter", "kotlin-stdlib"})
public interface KProperty2<D, E, V>
extends KProperty<V>,
Function2<D, E, V> {
    public V get(D var1, E var2);

    @SinceKotlin(version="1.1")
    @Nullable
    public Object getDelegate(D var1, E var2);

    @NotNull
    public Getter<D, E, V> getGetter();

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0004\b\u0003\u0010\u0001*\u0004\b\u0004\u0010\u0002*\u0006\b\u0005\u0010\u0003 \u00012\b\u0012\u0004\u0012\u0002H\u00030\u00042\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005\u00a8\u0006\u0006"}, d2={"Lkotlin/reflect/KProperty2$Getter;", "D", "E", "V", "Lkotlin/reflect/KProperty$Getter;", "Lkotlin/Function2;", "kotlin-stdlib"})
    public static interface Getter<D, E, V>
    extends KProperty.Getter<V>,
    Function2<D, E, V> {
    }
}

