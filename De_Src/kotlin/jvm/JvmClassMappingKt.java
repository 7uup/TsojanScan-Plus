/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm;

import java.lang.annotation.Annotation;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0011\n\u0002\b\u0002\u001a\u001f\u0010\u0018\u001a\u00020\u0019\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\r*\u0006\u0012\u0002\b\u00030\u001a\u00a2\u0006\u0002\u0010\u001b\"'\u0010\u0000\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u00028F\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"-\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00018G\u00a2\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"&\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\b\b\u0000\u0010\u0002*\u00020\r*\u0002H\u00028\u00c6\u0002\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000e\";\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0007\"\b\b\u0000\u0010\u0002*\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00018\u00c7\u0002X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u000f\u0010\t\u001a\u0004\b\u0010\u0010\u000b\"+\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\b\b\u0000\u0010\u0002*\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u000b\"-\u0010\u0013\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0007\"\b\b\u0000\u0010\u0002*\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\u000b\"+\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00078G\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006\u001c"}, d2={"annotationClass", "Lkotlin/reflect/KClass;", "T", "", "getAnnotationClass", "(Ljava/lang/annotation/Annotation;)Lkotlin/reflect/KClass;", "java", "Ljava/lang/Class;", "getJavaClass$annotations", "(Lkotlin/reflect/KClass;)V", "getJavaClass", "(Lkotlin/reflect/KClass;)Ljava/lang/Class;", "javaClass", "", "(Ljava/lang/Object;)Ljava/lang/Class;", "getRuntimeClassOfKClassInstance$annotations", "getRuntimeClassOfKClassInstance", "javaObjectType", "getJavaObjectType", "javaPrimitiveType", "getJavaPrimitiveType", "kotlin", "getKotlinClass", "(Ljava/lang/Class;)Lkotlin/reflect/KClass;", "isArrayOf", "", "", "([Ljava/lang/Object;)Z", "kotlin-stdlib"})
@JvmName(name="JvmClassMappingKt")
public final class JvmClassMappingKt {
    public static /* synthetic */ void getJavaClass$annotations(KClass kClass) {
    }

    @JvmName(name="getJavaClass")
    @NotNull
    public static final <T> Class<T> getJavaClass(@NotNull KClass<T> $this$java) {
        Intrinsics.checkNotNullParameter($this$java, "$this$java");
        Class<?> clazz = ((ClassBasedDeclarationContainer)((Object)$this$java)).getJClass();
        if (clazz == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<T>");
        }
        return clazz;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Nullable
    public static final <T> Class<T> getJavaPrimitiveType(@NotNull KClass<T> $this$javaPrimitiveType) {
        Intrinsics.checkNotNullParameter($this$javaPrimitiveType, "$this$javaPrimitiveType");
        Class<?> thisJClass = ((ClassBasedDeclarationContainer)((Object)$this$javaPrimitiveType)).getJClass();
        if (thisJClass.isPrimitive()) {
            Class<?> clazz = thisJClass;
            if (clazz != null) return clazz;
            throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<T>");
        }
        String string = thisJClass.getName();
        if (string == null) return null;
        switch (string) {
            case "java.lang.Boolean": {
                Class<Object> clazz = Boolean.TYPE;
                return clazz;
            }
            case "java.lang.Character": {
                Class<Object> clazz = Character.TYPE;
                return clazz;
            }
            case "java.lang.Byte": {
                Class<Object> clazz = Byte.TYPE;
                return clazz;
            }
            case "java.lang.Short": {
                Class<Object> clazz = Short.TYPE;
                return clazz;
            }
            case "java.lang.Integer": {
                Class<Object> clazz = Integer.TYPE;
                return clazz;
            }
            case "java.lang.Float": {
                Class<Object> clazz = Float.TYPE;
                return clazz;
            }
            case "java.lang.Long": {
                Class<Object> clazz = Long.TYPE;
                return clazz;
            }
            case "java.lang.Double": {
                Class<Object> clazz = Double.TYPE;
                return clazz;
            }
            case "java.lang.Void": {
                Class<Object> clazz = Void.TYPE;
                return clazz;
            }
            default: {
                return null;
            }
        }
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    @NotNull
    public static final <T> Class<T> getJavaObjectType(@NotNull KClass<T> $this$javaObjectType) {
        Intrinsics.checkNotNullParameter($this$javaObjectType, "$this$javaObjectType");
        thisJClass = ((ClassBasedDeclarationContainer)$this$javaObjectType).getJClass();
        if (!thisJClass.isPrimitive()) {
            v0 = thisJClass;
            if (v0 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<T>");
            }
            return v0;
        }
        v1 = thisJClass.getName();
        if (v1 == null) ** GOTO lbl-1000
        var2_2 = v1;
        tmp = -1;
        switch (var2_2.hashCode()) {
            case 64711720: {
                if (!var2_2.equals("boolean")) break;
                tmp = 1;
                break;
            }
            case 3625364: {
                if (!var2_2.equals("void")) break;
                tmp = 2;
                break;
            }
            case 3039496: {
                if (!var2_2.equals("byte")) break;
                tmp = 3;
                break;
            }
            case -1325958191: {
                if (!var2_2.equals("double")) break;
                tmp = 4;
                break;
            }
            case 3052374: {
                if (!var2_2.equals("char")) break;
                tmp = 5;
                break;
            }
            case 109413500: {
                if (!var2_2.equals("short")) break;
                tmp = 6;
                break;
            }
            case 97526364: {
                if (!var2_2.equals("float")) break;
                tmp = 7;
                break;
            }
            case 104431: {
                if (!var2_2.equals("int")) break;
                tmp = 8;
                break;
            }
            case 3327612: {
                if (!var2_2.equals("long")) break;
                tmp = 9;
                break;
            }
        }
        switch (tmp) {
            case 1: {
                v2 /* !! */  = Boolean.class;
                break;
            }
            case 5: {
                v2 /* !! */  = Character.class;
                break;
            }
            case 3: {
                v2 /* !! */  = Byte.class;
                break;
            }
            case 6: {
                v2 /* !! */  = Short.class;
                break;
            }
            case 8: {
                v2 /* !! */  = Integer.class;
                break;
            }
            case 7: {
                v2 /* !! */  = Float.class;
                break;
            }
            case 9: {
                v2 /* !! */  = Long.class;
                break;
            }
            case 4: {
                v2 /* !! */  = Double.class;
                break;
            }
            case 2: {
                v2 /* !! */  = Void.class;
                break;
            }
            default: lbl-1000:
            // 2 sources

            {
                v2 /* !! */  = thisJClass;
            }
        }
        if (v2 /* !! */  == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<T>");
        }
        return v2 /* !! */ ;
    }

    @JvmName(name="getKotlinClass")
    @NotNull
    public static final <T> KClass<T> getKotlinClass(@NotNull Class<T> $this$kotlin) {
        Intrinsics.checkNotNullParameter($this$kotlin, "$this$kotlin");
        return Reflection.getOrCreateKotlinClass($this$kotlin);
    }

    @NotNull
    public static final <T> Class<T> getJavaClass(@NotNull T $this$javaClass) {
        int $i$f$getJavaClass = 0;
        Intrinsics.checkNotNullParameter($this$javaClass, "$this$javaClass");
        Class<?> clazz = $this$javaClass.getClass();
        if (clazz == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<T>");
        }
        return clazz;
    }

    @Deprecated(message="Use 'java' property to get Java class corresponding to this Kotlin class or cast this instance to Any if you really want to get the runtime Java class of this implementation of KClass.", replaceWith=@ReplaceWith(imports={}, expression="(this as Any).javaClass"), level=DeprecationLevel.ERROR)
    public static /* synthetic */ void getRuntimeClassOfKClassInstance$annotations(KClass kClass) {
    }

    @JvmName(name="getRuntimeClassOfKClassInstance")
    @NotNull
    public static final <T> Class<KClass<T>> getRuntimeClassOfKClassInstance(@NotNull KClass<T> $this$javaClass) {
        int $i$f$getRuntimeClassOfKClassInstance = 0;
        Intrinsics.checkNotNullParameter($this$javaClass, "$this$javaClass");
        Class<KClass<T>> clazz = ((Object)$this$javaClass).getClass();
        if (clazz == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<kotlin.reflect.KClass<T>>");
        }
        return clazz;
    }

    public static final /* synthetic */ <T> boolean isArrayOf(@NotNull Object[] $this$isArrayOf) {
        Intrinsics.checkNotNullParameter($this$isArrayOf, "$this$isArrayOf");
        Intrinsics.reifiedOperationMarker(4, "T");
        return Object.class.isAssignableFrom($this$isArrayOf.getClass().getComponentType());
    }

    @NotNull
    public static final <T extends Annotation> KClass<? extends T> getAnnotationClass(@NotNull T $this$annotationClass) {
        Intrinsics.checkNotNullParameter($this$annotationClass, "$this$annotationClass");
        KClass<? extends Annotation> kClass = JvmClassMappingKt.getKotlinClass($this$annotationClass.annotationType());
        if (kClass == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.reflect.KClass<out T>");
        }
        return kClass;
    }
}

