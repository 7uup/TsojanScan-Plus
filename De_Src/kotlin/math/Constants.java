/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.math;

import kotlin.Metadata;
import kotlin.jvm.JvmField;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\b\u00c2\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0000X\u0081\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0000X\u0081\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0000X\u0081\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00048\u0000X\u0081\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00048\u0000X\u0081\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00048\u0000X\u0081\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2={"Lkotlin/math/Constants;", "", "()V", "LN2", "", "epsilon", "taylor_2_bound", "taylor_n_bound", "upper_taylor_2_bound", "upper_taylor_n_bound", "kotlin-stdlib"})
final class Constants {
    @JvmField
    public static final double LN2;
    @JvmField
    public static final double epsilon;
    @JvmField
    public static final double taylor_2_bound;
    @JvmField
    public static final double taylor_n_bound;
    @JvmField
    public static final double upper_taylor_2_bound;
    @JvmField
    public static final double upper_taylor_n_bound;
    public static final Constants INSTANCE;

    private Constants() {
    }

    static {
        Constants constants;
        INSTANCE = constants = new Constants();
        double d = 2.0;
        boolean bl = false;
        LN2 = Math.log(d);
        epsilon = Math.ulp(1.0);
        taylor_2_bound = Math.sqrt(epsilon);
        taylor_n_bound = Math.sqrt(taylor_2_bound);
        upper_taylor_2_bound = 1.0 / taylor_2_bound;
        upper_taylor_n_bound = 1.0 / taylor_n_bound;
    }
}

