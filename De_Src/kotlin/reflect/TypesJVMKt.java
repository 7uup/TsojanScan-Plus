/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.reflect;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.SinceKotlin;
import kotlin.collections.CollectionsKt;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.KTypeBase;
import kotlin.reflect.GenericArrayTypeImpl;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;
import kotlin.reflect.ParameterizedTypeImpl;
import kotlin.reflect.TypeVariableImpl;
import kotlin.reflect.TypesJVMKt;
import kotlin.reflect.TypesJVMKt$WhenMappings;
import kotlin.reflect.WildcardTypeImpl;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u001a\"\u0010\n\u001a\u00020\u00012\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000eH\u0003\u001a\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0001H\u0002\u001a\u0016\u0010\u0012\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0003\"\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00078BX\u0083\u0004\u00a2\u0006\f\u0012\u0004\b\u0003\u0010\b\u001a\u0004\b\u0005\u0010\t\u00a8\u0006\u0015"}, d2={"javaType", "Ljava/lang/reflect/Type;", "Lkotlin/reflect/KType;", "getJavaType$annotations", "(Lkotlin/reflect/KType;)V", "getJavaType", "(Lkotlin/reflect/KType;)Ljava/lang/reflect/Type;", "Lkotlin/reflect/KTypeProjection;", "(Lkotlin/reflect/KTypeProjection;)V", "(Lkotlin/reflect/KTypeProjection;)Ljava/lang/reflect/Type;", "createPossiblyInnerType", "jClass", "Ljava/lang/Class;", "arguments", "", "typeToString", "", "type", "computeJavaType", "forceWrapper", "", "kotlin-stdlib"})
public final class TypesJVMKt {
    @SinceKotlin(version="1.4")
    @ExperimentalStdlibApi
    @LowPriorityInOverloadResolution
    public static /* synthetic */ void getJavaType$annotations(KType kType) {
    }

    @NotNull
    public static final Type getJavaType(@NotNull KType $this$javaType) {
        Intrinsics.checkNotNullParameter($this$javaType, "$this$javaType");
        if ($this$javaType instanceof KTypeBase) {
            Type type = ((KTypeBase)$this$javaType).getJavaType();
            if (type != null) {
                Type type2 = type;
                boolean bl = false;
                boolean bl2 = false;
                Type it = type2;
                boolean bl3 = false;
                return it;
            }
        }
        return TypesJVMKt.computeJavaType$default($this$javaType, false, 1, null);
    }

    /*
     * Unable to fully structure code
     */
    @ExperimentalStdlibApi
    private static final Type computeJavaType(KType $this$computeJavaType, boolean forceWrapper) {
        block9: {
            block10: {
                classifier = $this$computeJavaType.getClassifier();
                if (classifier instanceof KTypeParameter) {
                    return new TypeVariableImpl((KTypeParameter)classifier);
                }
                if (!(classifier instanceof KClass)) break block9;
                jClass = forceWrapper != false ? JvmClassMappingKt.getJavaObjectType((KClass)classifier) : JvmClassMappingKt.getJavaClass((KClass)classifier);
                arguments = $this$computeJavaType.getArguments();
                if (arguments.isEmpty()) {
                    return jClass;
                }
                if (!jClass.isArray()) break block10;
                if (jClass.getComponentType().isPrimitive()) {
                    return jClass;
                }
                v0 = CollectionsKt.singleOrNull(arguments);
                if (v0 == null) {
                    throw (Throwable)new IllegalArgumentException("kotlin.Array must have exactly one type argument: " + $this$computeJavaType);
                }
                var7_5 = v0;
                var5_6 = var7_5.component1();
                elementType = var7_5.component2();
                v1 = variance;
                if (v1 == null) ** GOTO lbl-1000
                switch (TypesJVMKt$WhenMappings.$EnumSwitchMapping$0[v1.ordinal()]) {
                    case 1: lbl-1000:
                    // 2 sources

                    {
                        v2 = jClass;
                        break;
                    }
                    case 2: 
                    case 3: {
                        v3 = elementType;
                        Intrinsics.checkNotNull(v3);
                        javaElementType = TypesJVMKt.computeJavaType$default(v3, false, 1, null);
                        if (javaElementType instanceof Class) {
                            v2 = jClass;
                            break;
                        }
                        v2 = new GenericArrayTypeImpl(javaElementType);
                        break;
                    }
                    default: {
                        throw new NoWhenBranchMatchedException();
                    }
                }
                return v2;
            }
            return TypesJVMKt.createPossiblyInnerType(jClass, arguments);
        }
        throw (Throwable)new UnsupportedOperationException("Unsupported type classifier: " + $this$computeJavaType);
    }

    static /* synthetic */ Type computeJavaType$default(KType kType, boolean bl, int n, Object object) {
        if ((n & 1) != 0) {
            bl = false;
        }
        return TypesJVMKt.computeJavaType(kType, bl);
    }

    /*
     * WARNING - void declaration
     */
    @ExperimentalStdlibApi
    private static final Type createPossiblyInnerType(Class<?> jClass, List<KTypeProjection> arguments) {
        Collection<Type> collection;
        void $this$mapTo$iv$iv;
        void $this$map$iv;
        Class<?> clazz = jClass.getDeclaringClass();
        if (clazz == null) {
            Collection<Type> collection2;
            void $this$mapTo$iv$iv2;
            void $this$map$iv2;
            Iterable iterable = arguments;
            Type type = null;
            Class<?> clazz2 = jClass;
            boolean $i$f$map = false;
            void var5_14 = $this$map$iv2;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
            boolean $i$f$mapTo = false;
            Iterator iterator2 = $this$mapTo$iv$iv2.iterator();
            while (iterator2.hasNext()) {
                void receiver;
                Object item$iv$iv;
                Object t = item$iv$iv = iterator2.next();
                collection2 = destination$iv$iv;
                boolean bl = false;
                Type type2 = TypesJVMKt.getJavaType((KTypeProjection)receiver);
                collection2.add(type2);
            }
            collection2 = (List)destination$iv$iv;
            List list = collection2;
            Type type3 = type;
            Class<?> clazz3 = clazz2;
            return new ParameterizedTypeImpl(clazz3, type3, list);
        }
        Class<?> ownerClass = clazz;
        if (Modifier.isStatic(jClass.getModifiers())) {
            Collection<Type> collection3;
            Iterable $this$map$iv2 = arguments;
            Type type = ownerClass;
            Class<?> clazz4 = jClass;
            boolean $i$f$map = false;
            Iterable $this$mapTo$iv$iv2 = $this$map$iv2;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
            boolean $i$f$mapTo = false;
            Iterator iterator3 = $this$mapTo$iv$iv2.iterator();
            while (iterator3.hasNext()) {
                Object item$iv$iv;
                Object receiver = item$iv$iv = iterator3.next();
                collection3 = destination$iv$iv;
                boolean bl = false;
                Type type4 = TypesJVMKt.getJavaType((KTypeProjection)receiver);
                collection3.add(type4);
            }
            collection3 = (List)destination$iv$iv;
            List list = collection3;
            Type type5 = type;
            Class<?> clazz5 = clazz4;
            return new ParameterizedTypeImpl(clazz5, type5, list);
        }
        int n = jClass.getTypeParameters().length;
        Iterable $i$f$map = arguments.subList(0, n);
        Type type = TypesJVMKt.createPossiblyInnerType(ownerClass, arguments.subList(n, arguments.size()));
        Class<?> clazz6 = jClass;
        boolean $i$f$map2 = false;
        void destination$iv$iv = $this$map$iv;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        boolean $i$f$mapTo = false;
        Iterator iterator4 = $this$mapTo$iv$iv.iterator();
        while (iterator4.hasNext()) {
            void receiver;
            Object item$iv$iv;
            Object bl = item$iv$iv = iterator4.next();
            collection = destination$iv$iv2;
            boolean $i$a$-unknown-Object2 = false;
            Type type6 = TypesJVMKt.getJavaType((KTypeProjection)receiver);
            collection.add(type6);
        }
        collection = (List)destination$iv$iv2;
        List list = collection;
        Type type7 = type;
        Class<?> clazz7 = clazz6;
        return new ParameterizedTypeImpl(clazz7, type7, list);
    }

    @ExperimentalStdlibApi
    private static /* synthetic */ void getJavaType$annotations(KTypeProjection kTypeProjection) {
    }

    private static final Type getJavaType(KTypeProjection $this$javaType) {
        Type type;
        KVariance kVariance = $this$javaType.getVariance();
        if (kVariance == null) {
            return WildcardTypeImpl.Companion.getSTAR();
        }
        KVariance variance = kVariance;
        KType kType = $this$javaType.getType();
        Intrinsics.checkNotNull(kType);
        KType type2 = kType;
        switch (TypesJVMKt$WhenMappings.$EnumSwitchMapping$1[variance.ordinal()]) {
            case 1: {
                type = TypesJVMKt.computeJavaType(type2, true);
                break;
            }
            case 2: {
                type = new WildcardTypeImpl(null, TypesJVMKt.computeJavaType(type2, true));
                break;
            }
            case 3: {
                type = new WildcardTypeImpl(TypesJVMKt.computeJavaType(type2, true), null);
                break;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
        return type;
    }

    private static final String typeToString(Type type) {
        String string;
        if (type instanceof Class) {
            String string2;
            if (((Class)type).isArray()) {
                Sequence<Type> unwrap2 = SequencesKt.generateSequence(type, (Function1)typeToString.unwrap.1.INSTANCE);
                string2 = ((Class)SequencesKt.last(unwrap2)).getName() + StringsKt.repeat("[]", SequencesKt.count(unwrap2));
            } else {
                string2 = ((Class)type).getName();
            }
            string = string2;
            Intrinsics.checkNotNullExpressionValue(string2, "if (type.isArray) {\n    \u2026\n        } else type.name");
        } else {
            string = type.toString();
        }
        return string;
    }

    public static final /* synthetic */ Type access$computeJavaType(KType $this$access_u24computeJavaType, boolean forceWrapper) {
        return TypesJVMKt.computeJavaType($this$access_u24computeJavaType, forceWrapper);
    }

    public static final /* synthetic */ String access$typeToString(Type type) {
        return TypesJVMKt.typeToString(type);
    }
}

