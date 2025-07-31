/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.reflect;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.reflect.KAnnotatedElement;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KFunction;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVisibility;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000d\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005J\u0013\u0010>\u001a\u00020\f2\b\u0010?\u001a\u0004\u0018\u00010\u0002H\u00a6\u0002J\b\u0010@\u001a\u00020AH&J\u0012\u0010B\u001a\u00020\f2\b\u0010C\u001a\u0004\u0018\u00010\u0002H'R\u001e\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b0\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\f8&X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\f8&X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0010\u0010\u000fR\u001a\u0010\u0012\u001a\u00020\f8&X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0012\u0010\u000fR\u001a\u0010\u0014\u001a\u00020\f8&X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b\u0015\u0010\u000e\u001a\u0004\b\u0014\u0010\u000fR\u001a\u0010\u0016\u001a\u00020\f8&X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b\u0017\u0010\u000e\u001a\u0004\b\u0016\u0010\u000fR\u001a\u0010\u0018\u001a\u00020\f8&X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b\u0019\u0010\u000e\u001a\u0004\b\u0018\u0010\u000fR\u001a\u0010\u001a\u001a\u00020\f8&X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b\u001b\u0010\u000e\u001a\u0004\b\u001a\u0010\u000fR\u001a\u0010\u001c\u001a\u00020\f8&X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b\u001d\u0010\u000e\u001a\u0004\b\u001c\u0010\u000fR\u001c\u0010\u001e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001f0\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b \u0010\nR\u001c\u0010!\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00000\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\"\u0010\nR\u0014\u0010#\u001a\u0004\u0018\u00018\u0000X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b$\u0010%R\u0014\u0010&\u001a\u0004\u0018\u00010'X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b(\u0010)R(\u0010*\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00000+8&X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b,\u0010\u000e\u001a\u0004\b-\u0010.R\u0014\u0010/\u001a\u0004\u0018\u00010'X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b0\u0010)R \u00101\u001a\b\u0012\u0004\u0012\u0002020+8&X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b3\u0010\u000e\u001a\u0004\b4\u0010.R \u00105\u001a\b\u0012\u0004\u0012\u0002060+8&X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b7\u0010\u000e\u001a\u0004\b8\u0010.R\u001c\u00109\u001a\u0004\u0018\u00010:8&X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b;\u0010\u000e\u001a\u0004\b<\u0010=\u00a8\u0006D"}, d2={"Lkotlin/reflect/KClass;", "T", "", "Lkotlin/reflect/KDeclarationContainer;", "Lkotlin/reflect/KAnnotatedElement;", "Lkotlin/reflect/KClassifier;", "constructors", "", "Lkotlin/reflect/KFunction;", "getConstructors", "()Ljava/util/Collection;", "isAbstract", "", "isAbstract$annotations", "()V", "()Z", "isCompanion", "isCompanion$annotations", "isData", "isData$annotations", "isFinal", "isFinal$annotations", "isFun", "isFun$annotations", "isInner", "isInner$annotations", "isOpen", "isOpen$annotations", "isSealed", "isSealed$annotations", "members", "Lkotlin/reflect/KCallable;", "getMembers", "nestedClasses", "getNestedClasses", "objectInstance", "getObjectInstance", "()Ljava/lang/Object;", "qualifiedName", "", "getQualifiedName", "()Ljava/lang/String;", "sealedSubclasses", "", "getSealedSubclasses$annotations", "getSealedSubclasses", "()Ljava/util/List;", "simpleName", "getSimpleName", "supertypes", "Lkotlin/reflect/KType;", "getSupertypes$annotations", "getSupertypes", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters$annotations", "getTypeParameters", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility$annotations", "getVisibility", "()Lkotlin/reflect/KVisibility;", "equals", "other", "hashCode", "", "isInstance", "value", "kotlin-stdlib"})
public interface KClass<T>
extends KDeclarationContainer,
KAnnotatedElement,
KClassifier {
    @Nullable
    public String getSimpleName();

    @Nullable
    public String getQualifiedName();

    @Override
    @NotNull
    public Collection<KCallable<?>> getMembers();

    @NotNull
    public Collection<KFunction<T>> getConstructors();

    @NotNull
    public Collection<KClass<?>> getNestedClasses();

    @Nullable
    public T getObjectInstance();

    @SinceKotlin(version="1.1")
    public boolean isInstance(@Nullable Object var1);

    @NotNull
    public List<KTypeParameter> getTypeParameters();

    @NotNull
    public List<KType> getSupertypes();

    @NotNull
    public List<KClass<? extends T>> getSealedSubclasses();

    @Nullable
    public KVisibility getVisibility();

    public boolean isFinal();

    public boolean isOpen();

    public boolean isAbstract();

    public boolean isSealed();

    public boolean isData();

    public boolean isInner();

    public boolean isCompanion();

    public boolean isFun();

    public boolean equals(@Nullable Object var1);

    public int hashCode();

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=3)
    public static final class DefaultImpls {
        @SinceKotlin(version="1.1")
        public static /* synthetic */ void getTypeParameters$annotations() {
        }

        @SinceKotlin(version="1.1")
        public static /* synthetic */ void getSupertypes$annotations() {
        }

        @SinceKotlin(version="1.3")
        public static /* synthetic */ void getSealedSubclasses$annotations() {
        }

        @SinceKotlin(version="1.1")
        public static /* synthetic */ void getVisibility$annotations() {
        }

        @SinceKotlin(version="1.1")
        public static /* synthetic */ void isFinal$annotations() {
        }

        @SinceKotlin(version="1.1")
        public static /* synthetic */ void isOpen$annotations() {
        }

        @SinceKotlin(version="1.1")
        public static /* synthetic */ void isAbstract$annotations() {
        }

        @SinceKotlin(version="1.1")
        public static /* synthetic */ void isSealed$annotations() {
        }

        @SinceKotlin(version="1.1")
        public static /* synthetic */ void isData$annotations() {
        }

        @SinceKotlin(version="1.1")
        public static /* synthetic */ void isInner$annotations() {
        }

        @SinceKotlin(version="1.1")
        public static /* synthetic */ void isCompanion$annotations() {
        }

        @SinceKotlin(version="1.4")
        public static /* synthetic */ void isFun$annotations() {
        }
    }
}

