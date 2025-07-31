/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javax.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.meta.TypeQualifier;
import javax.annotation.meta.TypeQualifierValidator;
import javax.annotation.meta.When;

@Documented
@TypeQualifier(applicableTo=Number.class)
@Retention(value=RetentionPolicy.RUNTIME)
public @interface Nonnegative {
    public When when() default When.ALWAYS;

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class Checker
    implements TypeQualifierValidator<Nonnegative> {
        @Override
        public When forConstantValue(Nonnegative annotation, Object v) {
            boolean isNegative;
            if (!(v instanceof Number)) {
                return When.NEVER;
            }
            Number value = (Number)v;
            if (value instanceof Long) {
                isNegative = value.longValue() < 0L;
            } else if (value instanceof Double) {
                isNegative = value.doubleValue() < 0.0;
            } else if (value instanceof Float) {
                isNegative = value.floatValue() < 0.0f;
            } else {
                boolean bl = isNegative = value.intValue() < 0;
            }
            if (isNegative) {
                return When.NEVER;
            }
            return When.ALWAYS;
        }
    }
}

