/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javax.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.regex.Pattern;
import javax.annotation.RegEx;
import javax.annotation.meta.TypeQualifier;
import javax.annotation.meta.TypeQualifierValidator;
import javax.annotation.meta.When;

@Documented
@TypeQualifier(applicableTo=String.class)
@Retention(value=RetentionPolicy.RUNTIME)
public @interface MatchesPattern {
    @RegEx
    public String value();

    public int flags() default 0;

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class Checker
    implements TypeQualifierValidator<MatchesPattern> {
        @Override
        public When forConstantValue(MatchesPattern annotation, Object value) {
            Pattern p = Pattern.compile(annotation.value(), annotation.flags());
            if (p.matcher((String)value).matches()) {
                return When.ALWAYS;
            }
            return When.NEVER;
        }
    }
}

