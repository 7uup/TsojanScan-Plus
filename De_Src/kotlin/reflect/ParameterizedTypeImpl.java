/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.ParameterizedTypeImpl;
import kotlin.reflect.TypeImpl;
import kotlin.reflect.TypesJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B)\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b\u00a2\u0006\u0002\u0010\tJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\u0013\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\nH\u0016\u00a2\u0006\u0002\u0010\u0011J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\u0013\u001a\u00020\u0006H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0015H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000b\u00a8\u0006\u0019"}, d2={"Lkotlin/reflect/ParameterizedTypeImpl;", "Ljava/lang/reflect/ParameterizedType;", "Lkotlin/reflect/TypeImpl;", "rawType", "Ljava/lang/Class;", "ownerType", "Ljava/lang/reflect/Type;", "typeArguments", "", "(Ljava/lang/Class;Ljava/lang/reflect/Type;Ljava/util/List;)V", "", "[Ljava/lang/reflect/Type;", "equals", "", "other", "", "getActualTypeArguments", "()[Ljava/lang/reflect/Type;", "getOwnerType", "getRawType", "getTypeName", "", "hashCode", "", "toString", "kotlin-stdlib"})
@ExperimentalStdlibApi
final class ParameterizedTypeImpl
implements ParameterizedType,
TypeImpl {
    private final Type[] typeArguments;
    private final Class<?> rawType;
    private final Type ownerType;

    @Override
    @NotNull
    public Type getRawType() {
        return this.rawType;
    }

    @Override
    @Nullable
    public Type getOwnerType() {
        return this.ownerType;
    }

    @Override
    @NotNull
    public Type[] getActualTypeArguments() {
        return this.typeArguments;
    }

    @Override
    @NotNull
    public String getTypeName() {
        boolean bl = false;
        boolean bl2 = false;
        StringBuilder stringBuilder = new StringBuilder();
        boolean bl3 = false;
        boolean bl4 = false;
        StringBuilder $this$buildString = stringBuilder;
        boolean bl5 = false;
        if (this.ownerType != null) {
            $this$buildString.append(TypesJVMKt.access$typeToString(this.ownerType));
            $this$buildString.append("$");
            $this$buildString.append(this.rawType.getSimpleName());
        } else {
            $this$buildString.append(TypesJVMKt.access$typeToString(this.rawType));
        }
        Type[] typeArray = this.typeArguments;
        boolean bl6 = false;
        Type[] typeArray2 = typeArray;
        boolean bl7 = false;
        if (!(typeArray2.length == 0)) {
            ArraysKt.joinTo$default(this.typeArguments, (Appendable)$this$buildString, null, (CharSequence)"<", (CharSequence)">", 0, null, (Function1)getTypeName.1.1.INSTANCE, 50, null);
        }
        String string = stringBuilder.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean equals(@Nullable Object other) {
        if (!(other instanceof ParameterizedType)) return false;
        if (!Intrinsics.areEqual(this.rawType, ((ParameterizedType)other).getRawType())) return false;
        if (!Intrinsics.areEqual(this.ownerType, ((ParameterizedType)other).getOwnerType())) return false;
        Object[] objectArray = this.getActualTypeArguments();
        Object[] objectArray2 = ((ParameterizedType)other).getActualTypeArguments();
        boolean bl = false;
        if (!Arrays.equals(objectArray, objectArray2)) return false;
        return true;
    }

    public int hashCode() {
        Object[] objectArray = this.ownerType;
        boolean bl = false;
        int n = objectArray != null ? objectArray.hashCode() : 0;
        objectArray = this.getActualTypeArguments();
        bl = false;
        return this.rawType.hashCode() ^ n ^ Arrays.hashCode(objectArray);
    }

    @NotNull
    public String toString() {
        return this.getTypeName();
    }

    public ParameterizedTypeImpl(@NotNull Class<?> rawType, @Nullable Type ownerType, @NotNull List<? extends Type> typeArguments) {
        Intrinsics.checkNotNullParameter(rawType, "rawType");
        Intrinsics.checkNotNullParameter(typeArguments, "typeArguments");
        this.rawType = rawType;
        this.ownerType = ownerType;
        Collection $this$toTypedArray$iv = typeArguments;
        boolean $i$f$toTypedArray = false;
        Collection thisCollection$iv = $this$toTypedArray$iv;
        Type[] typeArray = thisCollection$iv.toArray(new Type[0]);
        if (typeArray == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        this.typeArguments = typeArray;
    }
}

