/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Target;

@Target(allowedTargets={AnnotationTarget.ANNOTATION_CLASS})
@Retention(value=RetentionPolicy.RUNTIME)
@java.lang.annotation.Target(value={ElementType.ANNOTATION_TYPE})
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000\u00a8\u0006\u0002"}, d2={"Lkotlin/annotation/MustBeDocumented;", "", "kotlin-stdlib"})
public @interface MustBeDocumented {
}

