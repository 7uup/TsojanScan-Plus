/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm.internal;

import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.SinceKotlin;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeReference$WhenMappings;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\b\u0010\u0017\u001a\u00020\u0013H\u0002J\u0013\u0010\u0018\u001a\u00020\b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0096\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0013H\u0016J\f\u0010\u0017\u001a\u00020\u0013*\u00020\u0006H\u0002R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00058VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0011R\u001c\u0010\u0012\u001a\u00020\u0013*\u0006\u0012\u0002\b\u00030\u00148BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006\u001e"}, d2={"Lkotlin/jvm/internal/TypeReference;", "Lkotlin/reflect/KType;", "classifier", "Lkotlin/reflect/KClassifier;", "arguments", "", "Lkotlin/reflect/KTypeProjection;", "isMarkedNullable", "", "(Lkotlin/reflect/KClassifier;Ljava/util/List;Z)V", "annotations", "", "getAnnotations", "()Ljava/util/List;", "getArguments", "getClassifier", "()Lkotlin/reflect/KClassifier;", "()Z", "arrayClassName", "", "Ljava/lang/Class;", "getArrayClassName", "(Ljava/lang/Class;)Ljava/lang/String;", "asString", "equals", "other", "", "hashCode", "", "toString", "kotlin-stdlib"})
@SinceKotlin(version="1.4")
public final class TypeReference
implements KType {
    @NotNull
    private final KClassifier classifier;
    @NotNull
    private final List<KTypeProjection> arguments;
    private final boolean isMarkedNullable;

    @Override
    @NotNull
    public List<Annotation> getAnnotations() {
        return CollectionsKt.emptyList();
    }

    public boolean equals(@Nullable Object other) {
        return other instanceof TypeReference && Intrinsics.areEqual(this.getClassifier(), ((TypeReference)other).getClassifier()) && Intrinsics.areEqual(this.getArguments(), ((TypeReference)other).getArguments()) && this.isMarkedNullable() == ((TypeReference)other).isMarkedNullable();
    }

    public int hashCode() {
        return (this.getClassifier().hashCode() * 31 + ((Object)this.getArguments()).hashCode()) * 31 + ((Object)this.isMarkedNullable()).hashCode();
    }

    @NotNull
    public String toString() {
        return this.asString() + " (Kotlin reflection is not available)";
    }

    private final String asString() {
        KClassifier kClassifier = this.getClassifier();
        if (!(kClassifier instanceof KClass)) {
            kClassifier = null;
        }
        KClass kClass = (KClass)kClassifier;
        Class<KClass> javaClass = kClass != null ? JvmClassMappingKt.getJavaClass(kClass) : null;
        String klass = javaClass == null ? this.getClassifier().toString() : (javaClass.isArray() ? this.getArrayClassName(javaClass) : javaClass.getName());
        String args2 = this.getArguments().isEmpty() ? "" : CollectionsKt.joinToString$default(this.getArguments(), ", ", "<", ">", 0, null, new Function1<KTypeProjection, CharSequence>(this){
            final /* synthetic */ TypeReference this$0;

            @NotNull
            public final CharSequence invoke(@NotNull KTypeProjection it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return TypeReference.access$asString(this.this$0, it);
            }
            {
                this.this$0 = typeReference;
                super(1);
            }
        }, 24, null);
        String nullable = this.isMarkedNullable() ? "?" : "";
        return klass + args2 + nullable;
    }

    private final String getArrayClassName(Class<?> $this$arrayClassName) {
        Class<?> clazz = $this$arrayClassName;
        return Intrinsics.areEqual(clazz, boolean[].class) ? "kotlin.BooleanArray" : (Intrinsics.areEqual(clazz, char[].class) ? "kotlin.CharArray" : (Intrinsics.areEqual(clazz, byte[].class) ? "kotlin.ByteArray" : (Intrinsics.areEqual(clazz, short[].class) ? "kotlin.ShortArray" : (Intrinsics.areEqual(clazz, int[].class) ? "kotlin.IntArray" : (Intrinsics.areEqual(clazz, float[].class) ? "kotlin.FloatArray" : (Intrinsics.areEqual(clazz, long[].class) ? "kotlin.LongArray" : (Intrinsics.areEqual(clazz, double[].class) ? "kotlin.DoubleArray" : "kotlin.Array")))))));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final String asString(KTypeProjection $this$asString) {
        Object object;
        if ($this$asString.getVariance() == null) {
            return "*";
        }
        KType kType = $this$asString.getType();
        if (!(kType instanceof TypeReference)) {
            kType = null;
        }
        if ((object = (TypeReference)kType) == null || (object = ((TypeReference)object).asString()) == null) {
            object = String.valueOf($this$asString.getType());
        }
        Object typeString = object;
        KVariance kVariance = $this$asString.getVariance();
        if (kVariance == null) throw new NoWhenBranchMatchedException();
        switch (TypeReference$WhenMappings.$EnumSwitchMapping$0[kVariance.ordinal()]) {
            case 1: {
                Object object2 = typeString;
                return object2;
            }
            case 2: {
                Object object2 = "in " + (String)typeString;
                return object2;
            }
            case 3: {
                Object object2 = "out " + (String)typeString;
                return object2;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    @Override
    @NotNull
    public KClassifier getClassifier() {
        return this.classifier;
    }

    @Override
    @NotNull
    public List<KTypeProjection> getArguments() {
        return this.arguments;
    }

    @Override
    public boolean isMarkedNullable() {
        return this.isMarkedNullable;
    }

    public TypeReference(@NotNull KClassifier classifier, @NotNull List<KTypeProjection> arguments, boolean isMarkedNullable) {
        Intrinsics.checkNotNullParameter(classifier, "classifier");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        this.classifier = classifier;
        this.arguments = arguments;
        this.isMarkedNullable = isMarkedNullable;
    }

    public static final /* synthetic */ String access$asString(TypeReference $this, KTypeProjection $this$access_u24asString) {
        return $this.asString($this$access_u24asString);
    }
}

