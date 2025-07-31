/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.reflect;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.functions.Function0;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\fJ\r\u0010\b\u001a\u00028\u0000H&\u00a2\u0006\u0002\u0010\tJ\n\u0010\n\u001a\u0004\u0018\u00010\u000bH'R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\r"}, d2={"Lkotlin/reflect/KProperty0;", "V", "Lkotlin/reflect/KProperty;", "Lkotlin/Function0;", "getter", "Lkotlin/reflect/KProperty0$Getter;", "getGetter", "()Lkotlin/reflect/KProperty0$Getter;", "get", "()Ljava/lang/Object;", "getDelegate", "", "Getter", "kotlin-stdlib"})
public interface KProperty0<V>
extends KProperty<V>,
Function0<V> {
    public V get();

    @SinceKotlin(version="1.1")
    @Nullable
    public Object getDelegate();

    @Override
    @NotNull
    public Getter<V> getGetter();

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003\u00a8\u0006\u0004"}, d2={"Lkotlin/reflect/KProperty0$Getter;", "V", "Lkotlin/reflect/KProperty$Getter;", "Lkotlin/Function0;", "kotlin-stdlib"})
    public static interface Getter<V>
    extends KProperty.Getter<V>,
    Function0<V> {
    }
}

