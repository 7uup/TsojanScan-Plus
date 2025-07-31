/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.annotation;

import kotlin.Metadata;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2={"Lkotlin/annotation/AnnotationRetention;", "", "(Ljava/lang/String;I)V", "SOURCE", "BINARY", "RUNTIME", "kotlin-stdlib"})
public final class AnnotationRetention
extends Enum<AnnotationRetention> {
    public static final /* enum */ AnnotationRetention SOURCE;
    public static final /* enum */ AnnotationRetention BINARY;
    public static final /* enum */ AnnotationRetention RUNTIME;
    private static final /* synthetic */ AnnotationRetention[] $VALUES;

    static {
        AnnotationRetention[] annotationRetentionArray = new AnnotationRetention[3];
        AnnotationRetention[] annotationRetentionArray2 = annotationRetentionArray;
        annotationRetentionArray[0] = SOURCE = new AnnotationRetention();
        annotationRetentionArray[1] = BINARY = new AnnotationRetention();
        annotationRetentionArray[2] = RUNTIME = new AnnotationRetention();
        $VALUES = annotationRetentionArray;
    }

    public static AnnotationRetention[] values() {
        return (AnnotationRetention[])$VALUES.clone();
    }

    public static AnnotationRetention valueOf(String string) {
        return Enum.valueOf(AnnotationRetention.class, string);
    }
}

