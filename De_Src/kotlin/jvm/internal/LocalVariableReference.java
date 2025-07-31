/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm.internal;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.LocalVariableReferencesKt;
import kotlin.jvm.internal.PropertyReference0;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016\u00a8\u0006\u0007"}, d2={"Lkotlin/jvm/internal/LocalVariableReference;", "Lkotlin/jvm/internal/PropertyReference0;", "()V", "get", "", "getOwner", "Lkotlin/reflect/KDeclarationContainer;", "kotlin-stdlib"})
@SinceKotlin(version="1.1")
public class LocalVariableReference
extends PropertyReference0 {
    @Override
    @NotNull
    public KDeclarationContainer getOwner() {
        Void void_ = LocalVariableReferencesKt.access$notSupportedError();
        throw new KotlinNothingValueException();
    }

    @Nullable
    public Object get() {
        Void void_ = LocalVariableReferencesKt.access$notSupportedError();
        throw new KotlinNothingValueException();
    }
}

