/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.reflect;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.reflect.KMutableProperty;
import kotlin.reflect.KProperty1;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00020\u0004:\u0001\u000eJ\u001d\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u0001H&\u00a2\u0006\u0002\u0010\rR\u001e\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u000f"}, d2={"Lkotlin/reflect/KMutableProperty1;", "T", "V", "Lkotlin/reflect/KProperty1;", "Lkotlin/reflect/KMutableProperty;", "setter", "Lkotlin/reflect/KMutableProperty1$Setter;", "getSetter", "()Lkotlin/reflect/KMutableProperty1$Setter;", "set", "", "receiver", "value", "(Ljava/lang/Object;Ljava/lang/Object;)V", "Setter", "kotlin-stdlib"})
public interface KMutableProperty1<T, V>
extends KProperty1<T, V>,
KMutableProperty<V> {
    public void set(T var1, V var2);

    @NotNull
    public Setter<T, V> getSetter();

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\bf\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00050\u0004\u00a8\u0006\u0006"}, d2={"Lkotlin/reflect/KMutableProperty1$Setter;", "T", "V", "Lkotlin/reflect/KMutableProperty$Setter;", "Lkotlin/Function2;", "", "kotlin-stdlib"})
    public static interface Setter<T, V>
    extends KMutableProperty.Setter<V>,
    Function2<T, V, Unit> {
    }
}

