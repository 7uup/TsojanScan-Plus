/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm.internal;

import java.util.Arrays;
import java.util.Collections;
import kotlin.SinceKotlin;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.FunctionBase;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.MutablePropertyReference1;
import kotlin.jvm.internal.MutablePropertyReference2;
import kotlin.jvm.internal.PropertyReference0;
import kotlin.jvm.internal.PropertyReference1;
import kotlin.jvm.internal.PropertyReference2;
import kotlin.jvm.internal.ReflectionFactory;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty0;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KMutableProperty2;
import kotlin.reflect.KProperty0;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KProperty2;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;

public class Reflection {
    private static final ReflectionFactory factory;
    static final String REFLECTION_NOT_AVAILABLE = " (Kotlin reflection is not available)";
    private static final KClass[] EMPTY_K_CLASS_ARRAY;

    public static KClass createKotlinClass(Class javaClass) {
        return factory.createKotlinClass(javaClass);
    }

    public static KClass createKotlinClass(Class javaClass, String internalName) {
        return factory.createKotlinClass(javaClass, internalName);
    }

    @SinceKotlin(version="1.4")
    public static KDeclarationContainer getOrCreateKotlinPackage(Class javaClass) {
        return factory.getOrCreateKotlinPackage(javaClass, "");
    }

    public static KDeclarationContainer getOrCreateKotlinPackage(Class javaClass, String moduleName) {
        return factory.getOrCreateKotlinPackage(javaClass, moduleName);
    }

    public static KClass getOrCreateKotlinClass(Class javaClass) {
        return factory.getOrCreateKotlinClass(javaClass);
    }

    public static KClass getOrCreateKotlinClass(Class javaClass, String internalName) {
        return factory.getOrCreateKotlinClass(javaClass, internalName);
    }

    public static KClass[] getOrCreateKotlinClasses(Class[] javaClasses) {
        int size = javaClasses.length;
        if (size == 0) {
            return EMPTY_K_CLASS_ARRAY;
        }
        KClass[] kClasses = new KClass[size];
        for (int i = 0; i < size; ++i) {
            kClasses[i] = Reflection.getOrCreateKotlinClass(javaClasses[i]);
        }
        return kClasses;
    }

    @SinceKotlin(version="1.1")
    public static String renderLambdaToString(Lambda lambda2) {
        return factory.renderLambdaToString(lambda2);
    }

    @SinceKotlin(version="1.3")
    public static String renderLambdaToString(FunctionBase lambda2) {
        return factory.renderLambdaToString(lambda2);
    }

    public static KFunction function(FunctionReference f) {
        return factory.function(f);
    }

    public static KProperty0 property0(PropertyReference0 p) {
        return factory.property0(p);
    }

    public static KMutableProperty0 mutableProperty0(MutablePropertyReference0 p) {
        return factory.mutableProperty0(p);
    }

    public static KProperty1 property1(PropertyReference1 p) {
        return factory.property1(p);
    }

    public static KMutableProperty1 mutableProperty1(MutablePropertyReference1 p) {
        return factory.mutableProperty1(p);
    }

    public static KProperty2 property2(PropertyReference2 p) {
        return factory.property2(p);
    }

    public static KMutableProperty2 mutableProperty2(MutablePropertyReference2 p) {
        return factory.mutableProperty2(p);
    }

    @SinceKotlin(version="1.4")
    public static KType typeOf(KClassifier classifier) {
        return factory.typeOf(classifier, Collections.<KTypeProjection>emptyList(), false);
    }

    @SinceKotlin(version="1.4")
    public static KType typeOf(Class klass) {
        return factory.typeOf(Reflection.getOrCreateKotlinClass(klass), Collections.<KTypeProjection>emptyList(), false);
    }

    @SinceKotlin(version="1.4")
    public static KType typeOf(Class klass, KTypeProjection arg1) {
        return factory.typeOf(Reflection.getOrCreateKotlinClass(klass), Collections.singletonList(arg1), false);
    }

    @SinceKotlin(version="1.4")
    public static KType typeOf(Class klass, KTypeProjection arg1, KTypeProjection arg2) {
        return factory.typeOf(Reflection.getOrCreateKotlinClass(klass), Arrays.asList(arg1, arg2), false);
    }

    @SinceKotlin(version="1.4")
    public static KType typeOf(Class klass, KTypeProjection ... arguments) {
        return factory.typeOf(Reflection.getOrCreateKotlinClass(klass), ArraysKt.toList(arguments), false);
    }

    @SinceKotlin(version="1.4")
    public static KType nullableTypeOf(KClassifier classifier) {
        return factory.typeOf(classifier, Collections.<KTypeProjection>emptyList(), true);
    }

    @SinceKotlin(version="1.4")
    public static KType nullableTypeOf(Class klass) {
        return factory.typeOf(Reflection.getOrCreateKotlinClass(klass), Collections.<KTypeProjection>emptyList(), true);
    }

    @SinceKotlin(version="1.4")
    public static KType nullableTypeOf(Class klass, KTypeProjection arg1) {
        return factory.typeOf(Reflection.getOrCreateKotlinClass(klass), Collections.singletonList(arg1), true);
    }

    @SinceKotlin(version="1.4")
    public static KType nullableTypeOf(Class klass, KTypeProjection arg1, KTypeProjection arg2) {
        return factory.typeOf(Reflection.getOrCreateKotlinClass(klass), Arrays.asList(arg1, arg2), true);
    }

    @SinceKotlin(version="1.4")
    public static KType nullableTypeOf(Class klass, KTypeProjection ... arguments) {
        return factory.typeOf(Reflection.getOrCreateKotlinClass(klass), ArraysKt.toList(arguments), true);
    }

    @SinceKotlin(version="1.4")
    public static KTypeParameter typeParameter(Object container, String name, KVariance variance, boolean isReified) {
        return factory.typeParameter(container, name, variance, isReified);
    }

    @SinceKotlin(version="1.4")
    public static void setUpperBounds(KTypeParameter typeParameter, KType bound) {
        factory.setUpperBounds(typeParameter, Collections.singletonList(bound));
    }

    @SinceKotlin(version="1.4")
    public static void setUpperBounds(KTypeParameter typeParameter, KType ... bounds) {
        factory.setUpperBounds(typeParameter, ArraysKt.toList(bounds));
    }

    static {
        ReflectionFactory impl;
        try {
            Class<?> implClass = Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl");
            impl = (ReflectionFactory)implClass.newInstance();
        } catch (ClassCastException e) {
            impl = null;
        } catch (ClassNotFoundException e) {
            impl = null;
        } catch (InstantiationException e) {
            impl = null;
        } catch (IllegalAccessException e) {
            impl = null;
        }
        factory = impl != null ? impl : new ReflectionFactory();
        EMPTY_K_CLASS_ARRAY = new KClass[0];
    }
}

