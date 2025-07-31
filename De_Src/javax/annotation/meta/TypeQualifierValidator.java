/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javax.annotation.meta;

import java.lang.annotation.Annotation;
import javax.annotation.Nonnull;
import javax.annotation.meta.When;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface TypeQualifierValidator<A extends Annotation> {
    @Nonnull
    public When forConstantValue(@Nonnull A var1, Object var2);
}

