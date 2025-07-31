/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import kotlin.SinceKotlin;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;
import kotlin.jvm.JvmName;

@Retention(value=AnnotationRetention.RUNTIME)
@Target(allowedTargets={AnnotationTarget.CLASS})
@java.lang.annotation.Retention(value=RetentionPolicy.RUNTIME)
@java.lang.annotation.Target(value={ElementType.TYPE})
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0011\b\u0087\u0002\u0018\u00002\u00020\u0001B\\\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\t\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003R\u0011\u0010\u0006\u001a\u00020\u00058\u0007\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0007\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0007\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0011R\u0012\u0010\r\u001a\u00020\u0003X\u0087\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\t8\u0007\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u00038\u0007\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u00058\u0007\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\u000fR\u0012\u0010\f\u001a\u00020\tX\u0087\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u0016\u00a8\u0006\u001a"}, d2={"Lkotlin/Metadata;", "", "kind", "", "metadataVersion", "", "bytecodeVersion", "data1", "", "", "data2", "extraString", "packageName", "extraInt", "bv", "()[I", "d1", "()[Ljava/lang/String;", "d2", "xi", "()I", "xs", "()Ljava/lang/String;", "k", "mv", "pn", "kotlin-stdlib"})
@SinceKotlin(version="1.3")
public @interface Metadata {
    @JvmName(name="k")
    public int k() default 1;

    @JvmName(name="mv")
    public int[] mv() default {};

    @JvmName(name="bv")
    public int[] bv() default {};

    @JvmName(name="d1")
    public String[] d1() default {};

    @JvmName(name="d2")
    public String[] d2() default {};

    @JvmName(name="xs")
    public String xs() default "";

    @JvmName(name="pn")
    public String pn() default "";

    @JvmName(name="xi")
    public int xi() default 0;
}

