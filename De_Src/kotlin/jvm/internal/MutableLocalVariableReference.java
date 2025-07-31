/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm.internal;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.LocalVariableReferencesKt;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0016\u00a8\u0006\n"}, d2={"Lkotlin/jvm/internal/MutableLocalVariableReference;", "Lkotlin/jvm/internal/MutablePropertyReference0;", "()V", "get", "", "getOwner", "Lkotlin/reflect/KDeclarationContainer;", "set", "", "value", "kotlin-stdlib"})
@SinceKotlin(version="1.1")
public class MutableLocalVariableReference
extends MutablePropertyReference0 {
    @Override
    @NotNull
    public KDeclarationContainer getOwner() {
        Void void_ = LocalVariableReferencesKt.access$notSupportedError();
        throw new KotlinNothingValueException();
    }

    @Override
    @Nullable
    public Object get() {
        Void void_ = LocalVariableReferencesKt.access$notSupportedError();
        throw new KotlinNothingValueException();
    }

    public void set(@Nullable Object value) {
        Void void_ = LocalVariableReferencesKt.access$notSupportedError();
        throw new KotlinNothingValueException();
    }
}

