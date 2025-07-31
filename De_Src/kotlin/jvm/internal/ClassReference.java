/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm.internal;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Function;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVisibility;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000 M2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001MB\u0011\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005\u00a2\u0006\u0002\u0010\u0006J\u0013\u0010D\u001a\u00020\u00122\b\u0010E\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\b\u0010F\u001a\u00020GH\u0002J\b\u0010H\u001a\u00020IH\u0016J\u0012\u0010J\u001a\u00020\u00122\b\u0010K\u001a\u0004\u0018\u00010\u0002H\u0017J\b\u0010L\u001a\u00020/H\u0016R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR \u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u000e0\r8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u00128VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u00128VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0016\u0010\u0015R\u001a\u0010\u0018\u001a\u00020\u00128VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u0018\u0010\u0015R\u001a\u0010\u001a\u001a\u00020\u00128VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\b\u001b\u0010\u0014\u001a\u0004\b\u001a\u0010\u0015R\u001a\u0010\u001c\u001a\u00020\u00128VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\b\u001d\u0010\u0014\u001a\u0004\b\u001c\u0010\u0015R\u001a\u0010\u001e\u001a\u00020\u00128VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\b\u001f\u0010\u0014\u001a\u0004\b\u001e\u0010\u0015R\u001a\u0010 \u001a\u00020\u00128VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\b!\u0010\u0014\u001a\u0004\b \u0010\u0015R\u001a\u0010\"\u001a\u00020\u00128VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\b#\u0010\u0014\u001a\u0004\b\"\u0010\u0015R\u0018\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001e\u0010&\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030'0\r8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b(\u0010\u0010R\u001e\u0010)\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\r8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b*\u0010\u0010R\u0016\u0010+\u001a\u0004\u0018\u00010\u00028VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b,\u0010-R\u0016\u0010.\u001a\u0004\u0018\u00010/8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b0\u00101R(\u00102\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u00010\b8VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\b3\u0010\u0014\u001a\u0004\b4\u0010\u000bR\u0016\u00105\u001a\u0004\u0018\u00010/8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b6\u00101R \u00107\u001a\b\u0012\u0004\u0012\u0002080\b8VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\b9\u0010\u0014\u001a\u0004\b:\u0010\u000bR \u0010;\u001a\b\u0012\u0004\u0012\u00020<0\b8VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\b=\u0010\u0014\u001a\u0004\b>\u0010\u000bR\u001c\u0010?\u001a\u0004\u0018\u00010@8VX\u0097\u0004\u00a2\u0006\f\u0012\u0004\bA\u0010\u0014\u001a\u0004\bB\u0010C\u00a8\u0006N"}, d2={"Lkotlin/jvm/internal/ClassReference;", "Lkotlin/reflect/KClass;", "", "Lkotlin/jvm/internal/ClassBasedDeclarationContainer;", "jClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)V", "annotations", "", "", "getAnnotations", "()Ljava/util/List;", "constructors", "", "Lkotlin/reflect/KFunction;", "getConstructors", "()Ljava/util/Collection;", "isAbstract", "", "isAbstract$annotations", "()V", "()Z", "isCompanion", "isCompanion$annotations", "isData", "isData$annotations", "isFinal", "isFinal$annotations", "isFun", "isFun$annotations", "isInner", "isInner$annotations", "isOpen", "isOpen$annotations", "isSealed", "isSealed$annotations", "getJClass", "()Ljava/lang/Class;", "members", "Lkotlin/reflect/KCallable;", "getMembers", "nestedClasses", "getNestedClasses", "objectInstance", "getObjectInstance", "()Ljava/lang/Object;", "qualifiedName", "", "getQualifiedName", "()Ljava/lang/String;", "sealedSubclasses", "getSealedSubclasses$annotations", "getSealedSubclasses", "simpleName", "getSimpleName", "supertypes", "Lkotlin/reflect/KType;", "getSupertypes$annotations", "getSupertypes", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters$annotations", "getTypeParameters", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility$annotations", "getVisibility", "()Lkotlin/reflect/KVisibility;", "equals", "other", "error", "", "hashCode", "", "isInstance", "value", "toString", "Companion", "kotlin-stdlib"})
public final class ClassReference
implements KClass<Object>,
ClassBasedDeclarationContainer {
    @NotNull
    private final Class<?> jClass;
    private static final Map<Class<? extends Function<?>>, Integer> FUNCTION_CLASSES;
    private static final HashMap<String, String> primitiveFqNames;
    private static final HashMap<String, String> primitiveWrapperFqNames;
    private static final HashMap<String, String> classFqNames;
    private static final Map<String, String> simpleNames;
    public static final Companion Companion;

    @Override
    @Nullable
    public String getSimpleName() {
        return Companion.getClassSimpleName(this.getJClass());
    }

    @Override
    @Nullable
    public String getQualifiedName() {
        return Companion.getClassQualifiedName(this.getJClass());
    }

    @Override
    @NotNull
    public Collection<KCallable<?>> getMembers() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @Override
    @NotNull
    public Collection<KFunction<Object>> getConstructors() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @Override
    @NotNull
    public Collection<KClass<?>> getNestedClasses() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @Override
    @NotNull
    public List<Annotation> getAnnotations() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @Override
    @Nullable
    public Object getObjectInstance() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @Override
    @SinceKotlin(version="1.1")
    public boolean isInstance(@Nullable Object value) {
        return Companion.isInstance(value, this.getJClass());
    }

    @SinceKotlin(version="1.1")
    public static /* synthetic */ void getTypeParameters$annotations() {
    }

    @Override
    @NotNull
    public List<KTypeParameter> getTypeParameters() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version="1.1")
    public static /* synthetic */ void getSupertypes$annotations() {
    }

    @Override
    @NotNull
    public List<KType> getSupertypes() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version="1.3")
    public static /* synthetic */ void getSealedSubclasses$annotations() {
    }

    @Override
    @NotNull
    public List<KClass<? extends Object>> getSealedSubclasses() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version="1.1")
    public static /* synthetic */ void getVisibility$annotations() {
    }

    @Override
    @Nullable
    public KVisibility getVisibility() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version="1.1")
    public static /* synthetic */ void isFinal$annotations() {
    }

    @Override
    public boolean isFinal() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version="1.1")
    public static /* synthetic */ void isOpen$annotations() {
    }

    @Override
    public boolean isOpen() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version="1.1")
    public static /* synthetic */ void isAbstract$annotations() {
    }

    @Override
    public boolean isAbstract() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version="1.1")
    public static /* synthetic */ void isSealed$annotations() {
    }

    @Override
    public boolean isSealed() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version="1.1")
    public static /* synthetic */ void isData$annotations() {
    }

    @Override
    public boolean isData() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version="1.1")
    public static /* synthetic */ void isInner$annotations() {
    }

    @Override
    public boolean isInner() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version="1.1")
    public static /* synthetic */ void isCompanion$annotations() {
    }

    @Override
    public boolean isCompanion() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version="1.4")
    public static /* synthetic */ void isFun$annotations() {
    }

    @Override
    public boolean isFun() {
        Void void_ = this.error();
        throw new KotlinNothingValueException();
    }

    private final Void error() {
        throw (Throwable)new KotlinReflectionNotSupportedError();
    }

    @Override
    public boolean equals(@Nullable Object other) {
        return other instanceof ClassReference && Intrinsics.areEqual(JvmClassMappingKt.getJavaObjectType(this), JvmClassMappingKt.getJavaObjectType((KClass)other));
    }

    @Override
    public int hashCode() {
        return JvmClassMappingKt.getJavaObjectType(this).hashCode();
    }

    @NotNull
    public String toString() {
        return this.getJClass().toString() + " (Kotlin reflection is not available)";
    }

    @Override
    @NotNull
    public Class<?> getJClass() {
        return this.jClass;
    }

    public ClassReference(@NotNull Class<?> jClass) {
        Intrinsics.checkNotNullParameter(jClass, "jClass");
        this.jClass = jClass;
    }

    /*
     * WARNING - void declaration
     */
    static {
        void $this$mapValuesTo$iv$iv;
        Pair<Object, Integer> pair;
        Object object;
        Serializable serializable;
        Object item$iv$iv2;
        void $this$mapIndexedTo$iv$iv222;
        Companion = new Companion(null);
        HashMap<String, String> $this$mapIndexed$iv = CollectionsKt.listOf(Function0.class, Function1.class, Function2.class, Function3.class, Function4.class, Function5.class, Function6.class, Function7.class, Function8.class, Function9.class, Function10.class, Function11.class, Function12.class, Function13.class, Function14.class, Function15.class, Function16.class, Function17.class, Function18.class, Function19.class, Function20.class, Function21.class, Function22.class);
        boolean $i$f$mapIndexed = false;
        Iterable iterable = $this$mapIndexed$iv;
        Object destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$mapIndexed$iv, 10));
        boolean $i$f$mapIndexedTo = false;
        int index$iv$iv = 0;
        for (Object item$iv$iv2 : $this$mapIndexedTo$iv$iv222) {
            void i;
            void clazz;
            int n = index$iv$iv++;
            boolean bl = false;
            if (n < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            serializable = (Class)item$iv$iv2;
            int n2 = n;
            object = destination$iv$iv;
            boolean bl2 = false;
            pair = TuplesKt.to(clazz, (int)i);
            object.add(pair);
        }
        FUNCTION_CLASSES = MapsKt.toMap((List)destination$iv$iv);
        $this$mapIndexed$iv = new HashMap();
        $i$f$mapIndexed = false;
        boolean $this$mapIndexedTo$iv$iv222 = false;
        HashMap<String, String> $this$apply = $this$mapIndexed$iv;
        boolean bl = false;
        $this$apply.put("boolean", "kotlin.Boolean");
        $this$apply.put("char", "kotlin.Char");
        $this$apply.put("byte", "kotlin.Byte");
        $this$apply.put("short", "kotlin.Short");
        $this$apply.put("int", "kotlin.Int");
        $this$apply.put("float", "kotlin.Float");
        $this$apply.put("long", "kotlin.Long");
        $this$apply.put("double", "kotlin.Double");
        primitiveFqNames = $this$mapIndexed$iv;
        $this$mapIndexed$iv = new HashMap<String, String>();
        $i$f$mapIndexed = false;
        $this$mapIndexedTo$iv$iv222 = false;
        $this$apply = $this$mapIndexed$iv;
        boolean bl3 = false;
        $this$apply.put("java.lang.Boolean", "kotlin.Boolean");
        $this$apply.put("java.lang.Character", "kotlin.Char");
        $this$apply.put("java.lang.Byte", "kotlin.Byte");
        $this$apply.put("java.lang.Short", "kotlin.Short");
        $this$apply.put("java.lang.Integer", "kotlin.Int");
        $this$apply.put("java.lang.Float", "kotlin.Float");
        $this$apply.put("java.lang.Long", "kotlin.Long");
        $this$apply.put("java.lang.Double", "kotlin.Double");
        primitiveWrapperFqNames = $this$mapIndexed$iv;
        $this$mapIndexed$iv = new HashMap();
        $i$f$mapIndexed = false;
        $this$mapIndexedTo$iv$iv222 = false;
        $this$apply = $this$mapIndexed$iv;
        boolean bl4 = false;
        $this$apply.put("java.lang.Object", "kotlin.Any");
        $this$apply.put("java.lang.String", "kotlin.String");
        $this$apply.put("java.lang.CharSequence", "kotlin.CharSequence");
        $this$apply.put("java.lang.Throwable", "kotlin.Throwable");
        $this$apply.put("java.lang.Cloneable", "kotlin.Cloneable");
        $this$apply.put("java.lang.Number", "kotlin.Number");
        $this$apply.put("java.lang.Comparable", "kotlin.Comparable");
        $this$apply.put("java.lang.Enum", "kotlin.Enum");
        $this$apply.put("java.lang.annotation.Annotation", "kotlin.Annotation");
        $this$apply.put("java.lang.Iterable", "kotlin.collections.Iterable");
        $this$apply.put("java.util.Iterator", "kotlin.collections.Iterator");
        $this$apply.put("java.util.Collection", "kotlin.collections.Collection");
        $this$apply.put("java.util.List", "kotlin.collections.List");
        $this$apply.put("java.util.Set", "kotlin.collections.Set");
        $this$apply.put("java.util.ListIterator", "kotlin.collections.ListIterator");
        $this$apply.put("java.util.Map", "kotlin.collections.Map");
        $this$apply.put("java.util.Map$Entry", "kotlin.collections.Map.Entry");
        $this$apply.put("kotlin.jvm.internal.StringCompanionObject", "kotlin.String.Companion");
        $this$apply.put("kotlin.jvm.internal.EnumCompanionObject", "kotlin.Enum.Companion");
        $this$apply.putAll((Map)primitiveFqNames);
        $this$apply.putAll((Map)primitiveWrapperFqNames);
        Collection<String> collection = primitiveFqNames.values();
        Intrinsics.checkNotNullExpressionValue(collection, "primitiveFqNames.values");
        Object $this$associateTo$iv = collection;
        boolean $i$f$associateTo22 = false;
        item$iv$iv2 = $this$associateTo$iv.iterator();
        while (item$iv$iv2.hasNext()) {
            Object element$iv = item$iv$iv2.next();
            Map map = $this$apply;
            String kotlinName = (String)element$iv;
            boolean bl5 = false;
            StringBuilder stringBuilder = new StringBuilder().append("kotlin.jvm.internal.");
            String string = kotlinName;
            Intrinsics.checkNotNullExpressionValue(string, "kotlinName");
            serializable = TuplesKt.to(stringBuilder.append(StringsKt.substringAfterLast$default(string, '.', null, 2, null)).append("CompanionObject").toString(), kotlinName + ".Companion");
            bl5 = false;
            map.put(((Pair)serializable).getFirst(), ((Pair)serializable).getSecond());
        }
        item$iv$iv2 = FUNCTION_CLASSES;
        boolean element$iv = false;
        Iterator $i$f$associateTo22 = item$iv$iv2.entrySet().iterator();
        while ($i$f$associateTo22.hasNext()) {
            void klass;
            Object object2 = $this$associateTo$iv = $i$f$associateTo22.next();
            boolean bl6 = false;
            item$iv$iv2 = (Class)object2.getKey();
            object2 = $this$associateTo$iv;
            bl6 = false;
            int arity = ((Number)object2.getValue()).intValue();
            $this$apply.put(klass.getName(), "kotlin.Function" + arity);
        }
        classFqNames = $this$mapIndexed$iv;
        Map $this$mapValues$iv = classFqNames;
        boolean $i$f$mapValues = false;
        Map $this$mapIndexedTo$iv$iv222 = $this$mapValues$iv;
        destination$iv$iv = new LinkedHashMap(MapsKt.mapCapacity($this$mapValues$iv.size()));
        boolean $i$f$mapValuesTo = false;
        Iterable $this$associateByTo$iv$iv$iv = $this$mapValuesTo$iv$iv.entrySet();
        boolean $i$f$associateByTo = false;
        for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
            void $dstr$_u24__u24$fqName;
            void it$iv$iv;
            Map.Entry entry = (Map.Entry)element$iv$iv$iv;
            Object object3 = destination$iv$iv;
            boolean bl7 = false;
            Object k = it$iv$iv.getKey();
            Map.Entry entry2 = (Map.Entry)element$iv$iv$iv;
            pair = k;
            object = object3;
            boolean bl8 = false;
            void var15_32 = $dstr$_u24__u24$fqName;
            boolean bl9 = false;
            String fqName = (String)var15_32.getValue();
            String string = StringsKt.substringAfterLast$default(fqName, '.', null, 2, null);
            object.put(pair, string);
        }
        simpleNames = destination$iv$iv;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u000f\u001a\u0004\u0018\u00010\n2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0005J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\n2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0005J\u001c\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00012\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0005R&\u0010\u0003\u001a\u001a\u0012\u0010\u0012\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R*\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R*\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R*\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2={"Lkotlin/jvm/internal/ClassReference$Companion;", "", "()V", "FUNCTION_CLASSES", "", "Ljava/lang/Class;", "Lkotlin/Function;", "", "classFqNames", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "primitiveFqNames", "primitiveWrapperFqNames", "simpleNames", "getClassQualifiedName", "jClass", "getClassSimpleName", "isInstance", "", "value", "kotlin-stdlib"})
    public static final class Companion {
        @Nullable
        public final String getClassSimpleName(@NotNull Class<?> jClass) {
            Object object;
            block14: {
                block15: {
                    Object object2;
                    String name;
                    block17: {
                        boolean bl;
                        boolean bl2;
                        Executable executable;
                        block16: {
                            block13: {
                                Intrinsics.checkNotNullParameter(jClass, "jClass");
                                if (!jClass.isAnonymousClass()) break block13;
                                object = null;
                                break block14;
                            }
                            if (!jClass.isLocalClass()) break block15;
                            name = jClass.getSimpleName();
                            object2 = jClass.getEnclosingMethod();
                            if (object2 == null) break block16;
                            executable = object2;
                            bl2 = false;
                            bl = false;
                            Method method = executable;
                            boolean bl3 = false;
                            String string = name;
                            Intrinsics.checkNotNullExpressionValue(string, "name");
                            object2 = StringsKt.substringAfter$default(string, method.getName() + "$", null, 2, null);
                            if (object2 != null) break block17;
                        }
                        Constructor<?> constructor = jClass.getEnclosingConstructor();
                        if (constructor != null) {
                            executable = constructor;
                            bl2 = false;
                            bl = false;
                            Executable constructor2 = executable;
                            boolean bl4 = false;
                            String string = name;
                            Intrinsics.checkNotNullExpressionValue(string, "name");
                            object2 = StringsKt.substringAfter$default(string, ((Constructor)constructor2).getName() + "$", null, 2, null);
                        } else {
                            object2 = object = null;
                        }
                    }
                    if (object2 == null) {
                        String string = name;
                        Intrinsics.checkNotNullExpressionValue(string, "name");
                        object = StringsKt.substringAfter$default(string, '$', null, 2, null);
                    }
                    break block14;
                }
                if (jClass.isArray()) {
                    Object object3;
                    Class<?> componentType = jClass.getComponentType();
                    if (componentType.isPrimitive()) {
                        String string = (String)simpleNames.get(componentType.getName());
                        object3 = string != null ? string + "Array" : null;
                    } else {
                        object3 = object = null;
                    }
                    if (object3 == null) {
                        object = "Array";
                    }
                } else {
                    object = (String)simpleNames.get(jClass.getName());
                    if (object == null) {
                        object = jClass.getSimpleName();
                    }
                }
            }
            return object;
        }

        @Nullable
        public final String getClassQualifiedName(@NotNull Class<?> jClass) {
            String string;
            Intrinsics.checkNotNullParameter(jClass, "jClass");
            if (jClass.isAnonymousClass()) {
                string = null;
            } else if (jClass.isLocalClass()) {
                string = null;
            } else if (jClass.isArray()) {
                String string2;
                Class<?> componentType = jClass.getComponentType();
                if (componentType.isPrimitive()) {
                    String string3 = (String)classFqNames.get(componentType.getName());
                    string2 = string3 != null ? string3 + "Array" : null;
                } else {
                    string2 = string = null;
                }
                if (string2 == null) {
                    string = "kotlin.Array";
                }
            } else {
                string = (String)classFqNames.get(jClass.getName());
                if (string == null) {
                    string = jClass.getCanonicalName();
                }
            }
            return string;
        }

        public final boolean isInstance(@Nullable Object value, @NotNull Class<?> jClass) {
            Intrinsics.checkNotNullParameter(jClass, "jClass");
            Object object = FUNCTION_CLASSES;
            boolean bl = false;
            Map map = object;
            if (map == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<K, V>");
            }
            Integer n = (Integer)map.get(jClass);
            if (n != null) {
                object = n;
                bl = false;
                boolean bl2 = false;
                int arity = ((Number)object).intValue();
                boolean bl3 = false;
                return TypeIntrinsics.isFunctionOfArity(value, arity);
            }
            Class<?> objectType = jClass.isPrimitive() ? JvmClassMappingKt.getJavaObjectType(JvmClassMappingKt.getKotlinClass(jClass)) : jClass;
            return objectType.isInstance(value);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

