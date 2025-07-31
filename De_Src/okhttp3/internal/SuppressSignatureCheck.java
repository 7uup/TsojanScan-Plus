/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;

@Retention(value=AnnotationRetention.BINARY)
@Documented
@Target(allowedTargets={AnnotationTarget.CONSTRUCTOR, AnnotationTarget.CLASS, AnnotationTarget.FUNCTION})
@java.lang.annotation.Retention(value=RetentionPolicy.CLASS)
@java.lang.annotation.Target(value={ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000\u00a8\u0006\u0002"}, d2={"Lokhttp3/internal/SuppressSignatureCheck;", "", "okhttp"})
public @interface SuppressSignatureCheck {
}

