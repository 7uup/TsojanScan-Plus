/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;

@Target(allowedTargets={AnnotationTarget.CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.LOCAL_VARIABLE, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.CONSTRUCTOR, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.EXPRESSION, AnnotationTarget.FILE, AnnotationTarget.TYPEALIAS})
@Retention(value=AnnotationRetention.SOURCE)
@Deprecated(message="Please use OptIn instead.", replaceWith=@ReplaceWith(imports={"kotlin.OptIn"}, expression="OptIn(*markerClass)"))
@java.lang.annotation.Retention(value=RetentionPolicy.SOURCE)
@java.lang.annotation.Target(value={ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.LOCAL_VARIABLE})
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B$\u0012\"\u0010\u0002\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u00040\u0003\"\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0004R\u001f\u0010\u0002\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u00040\u0003\u00a2\u0006\u0006\u001a\u0004\b\u0002\u0010\u0005\u00f8\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009120\u0001\u00a8\u0006\u0006"}, d2={"Lkotlin/UseExperimental;", "", "markerClass", "", "Lkotlin/reflect/KClass;", "()[Ljava/lang/Class;", "kotlin-stdlib"})
@SinceKotlin(version="1.2")
public @interface UseExperimental {
    public Class<? extends Annotation>[] markerClass();
}

