/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm.internal;

import java.io.Serializable;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Function;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;

@java.lang.Deprecated
@Deprecated(message="This class is no longer supported, do not use it.", level=DeprecationLevel.ERROR)
public abstract class FunctionImpl
implements Function,
Serializable,
Function0,
Function1,
Function2,
Function3,
Function4,
Function5,
Function6,
Function7,
Function8,
Function9,
Function10,
Function11,
Function12,
Function13,
Function14,
Function15,
Function16,
Function17,
Function18,
Function19,
Function20,
Function21,
Function22 {
    public abstract int getArity();

    public Object invokeVararg(Object ... p) {
        throw new UnsupportedOperationException();
    }

    private void checkArity(int expected) {
        if (this.getArity() != expected) {
            this.throwWrongArity(expected);
        }
    }

    private void throwWrongArity(int expected) {
        throw new IllegalStateException("Wrong function arity, expected: " + expected + ", actual: " + this.getArity());
    }

    public Object invoke() {
        this.checkArity(0);
        return this.invokeVararg(new Object[0]);
    }

    public Object invoke(Object p1) {
        this.checkArity(1);
        return this.invokeVararg(p1);
    }

    public Object invoke(Object p1, Object p2) {
        this.checkArity(2);
        return this.invokeVararg(p1, p2);
    }

    public Object invoke(Object p1, Object p2, Object p3) {
        this.checkArity(3);
        return this.invokeVararg(p1, p2, p3);
    }

    public Object invoke(Object p1, Object p2, Object p3, Object p4) {
        this.checkArity(4);
        return this.invokeVararg(p1, p2, p3, p4);
    }

    public Object invoke(Object p1, Object p2, Object p3, Object p4, Object p5) {
        this.checkArity(5);
        return this.invokeVararg(p1, p2, p3, p4, p5);
    }

    public Object invoke(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        this.checkArity(6);
        return this.invokeVararg(p1, p2, p3, p4, p5, p6);
    }

    public Object invoke(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        this.checkArity(7);
        return this.invokeVararg(p1, p2, p3, p4, p5, p6, p7);
    }

    public Object invoke(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        this.checkArity(8);
        return this.invokeVararg(p1, p2, p3, p4, p5, p6, p7, p8);
    }

    public Object invoke(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        this.checkArity(9);
        return this.invokeVararg(p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    public Object invoke(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10) {
        this.checkArity(10);
        return this.invokeVararg(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);
    }

    public Object invoke(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11) {
        this.checkArity(11);
        return this.invokeVararg(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11);
    }

    public Object invoke(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12) {
        this.checkArity(12);
        return this.invokeVararg(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12);
    }

    public Object invoke(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13) {
        this.checkArity(13);
        return this.invokeVararg(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13);
    }

    public Object invoke(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14) {
        this.checkArity(14);
        return this.invokeVararg(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14);
    }

    public Object invoke(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15) {
        this.checkArity(15);
        return this.invokeVararg(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15);
    }

    public Object invoke(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16) {
        this.checkArity(16);
        return this.invokeVararg(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16);
    }

    public Object invoke(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17) {
        this.checkArity(17);
        return this.invokeVararg(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17);
    }

    public Object invoke(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18) {
        this.checkArity(18);
        return this.invokeVararg(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18);
    }

    public Object invoke(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19) {
        this.checkArity(19);
        return this.invokeVararg(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19);
    }

    public Object invoke(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20) {
        this.checkArity(20);
        return this.invokeVararg(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20);
    }

    public Object invoke(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21) {
        this.checkArity(21);
        return this.invokeVararg(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21);
    }

    public Object invoke(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22) {
        this.checkArity(22);
        return this.invokeVararg(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22);
    }
}

