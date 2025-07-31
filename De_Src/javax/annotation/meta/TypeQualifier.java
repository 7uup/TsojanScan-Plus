/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javax.annotation.meta;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Documented
@Target(value={ElementType.ANNOTATION_TYPE})
@Retention(value=RetentionPolicy.RUNTIME)
public @interface TypeQualifier {
    public Class<?> applicableTo() default Object.class;
}

