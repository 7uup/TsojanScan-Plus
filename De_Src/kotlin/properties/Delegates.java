/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.properties;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.NotNullVar;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u0002H\u00050\u0004\"\b\b\u0000\u0010\u0005*\u00020\u0001J\u0080\u0001\u0010\u0006\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001a\u0002H\u00052Q\b\u0004\u0010\b\u001aK\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\n\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u0011H\u0005\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u0011H\u0005\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\tH\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0080\u0001\u0010\u0012\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001a\u0002H\u00052Q\b\u0004\u0010\b\u001aK\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\n\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u0011H\u0005\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u0011H\u0005\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00130\tH\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\u0014"}, d2={"Lkotlin/properties/Delegates;", "", "()V", "notNull", "Lkotlin/properties/ReadWriteProperty;", "T", "observable", "initialValue", "onChange", "Lkotlin/Function3;", "Lkotlin/reflect/KProperty;", "Lkotlin/ParameterName;", "name", "property", "oldValue", "newValue", "", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Lkotlin/properties/ReadWriteProperty;", "vetoable", "", "kotlin-stdlib"})
public final class Delegates {
    public static final Delegates INSTANCE;

    @NotNull
    public final <T> ReadWriteProperty<Object, T> notNull() {
        return new NotNullVar();
    }

    @NotNull
    public final <T> ReadWriteProperty<Object, T> observable(T initialValue, @NotNull Function3<? super KProperty<?>, ? super T, ? super T, Unit> onChange) {
        int $i$f$observable = 0;
        Intrinsics.checkNotNullParameter(onChange, "onChange");
        return new ObservableProperty<T>(onChange, initialValue, initialValue){
            final /* synthetic */ Function3 $onChange;
            final /* synthetic */ Object $initialValue;

            protected void afterChange(@NotNull KProperty<?> property, T oldValue, T newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                this.$onChange.invoke(property, oldValue, newValue);
            }
            {
                this.$onChange = $captured_local_variable$0;
                this.$initialValue = $captured_local_variable$1;
                super($super_call_param$2);
            }
        };
    }

    @NotNull
    public final <T> ReadWriteProperty<Object, T> vetoable(T initialValue, @NotNull Function3<? super KProperty<?>, ? super T, ? super T, Boolean> onChange) {
        int $i$f$vetoable = 0;
        Intrinsics.checkNotNullParameter(onChange, "onChange");
        return new ObservableProperty<T>(onChange, initialValue, initialValue){
            final /* synthetic */ Function3 $onChange;
            final /* synthetic */ Object $initialValue;

            protected boolean beforeChange(@NotNull KProperty<?> property, T oldValue, T newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                return (Boolean)this.$onChange.invoke(property, oldValue, newValue);
            }
            {
                this.$onChange = $captured_local_variable$0;
                this.$initialValue = $captured_local_variable$1;
                super($super_call_param$2);
            }
        };
    }

    private Delegates() {
    }

    static {
        Delegates delegates;
        INSTANCE = delegates = new Delegates();
    }
}

