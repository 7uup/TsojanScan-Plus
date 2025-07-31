/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\nH\u0087\b\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0087\b\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\rH\u0087\b\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u000b\u001a\u00020\fH\u0087\b\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\u000eH\u0087\b\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0087\b\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\u000fH\u0087\b\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0087\b\u001a\r\u0010\u0010\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u00a8\u0006\u0011"}, d2={"dec", "Ljava/math/BigDecimal;", "div", "other", "inc", "minus", "plus", "rem", "times", "toBigDecimal", "", "mathContext", "Ljava/math/MathContext;", "", "", "", "unaryMinus", "kotlin-stdlib"}, xs="kotlin/NumbersKt")
class NumbersKt__BigDecimalsKt {
    @InlineOnly
    private static final BigDecimal plus(BigDecimal $this$plus, BigDecimal other) {
        int $i$f$plus = 0;
        Intrinsics.checkNotNullParameter($this$plus, "$this$plus");
        BigDecimal bigDecimal = $this$plus.add(other);
        Intrinsics.checkNotNullExpressionValue(bigDecimal, "this.add(other)");
        return bigDecimal;
    }

    @InlineOnly
    private static final BigDecimal minus(BigDecimal $this$minus, BigDecimal other) {
        int $i$f$minus = 0;
        Intrinsics.checkNotNullParameter($this$minus, "$this$minus");
        BigDecimal bigDecimal = $this$minus.subtract(other);
        Intrinsics.checkNotNullExpressionValue(bigDecimal, "this.subtract(other)");
        return bigDecimal;
    }

    @InlineOnly
    private static final BigDecimal times(BigDecimal $this$times, BigDecimal other) {
        int $i$f$times = 0;
        Intrinsics.checkNotNullParameter($this$times, "$this$times");
        BigDecimal bigDecimal = $this$times.multiply(other);
        Intrinsics.checkNotNullExpressionValue(bigDecimal, "this.multiply(other)");
        return bigDecimal;
    }

    @InlineOnly
    private static final BigDecimal div(BigDecimal $this$div, BigDecimal other) {
        int $i$f$div = 0;
        Intrinsics.checkNotNullParameter($this$div, "$this$div");
        BigDecimal bigDecimal = $this$div.divide(other, RoundingMode.HALF_EVEN);
        Intrinsics.checkNotNullExpressionValue(bigDecimal, "this.divide(other, RoundingMode.HALF_EVEN)");
        return bigDecimal;
    }

    @InlineOnly
    private static final BigDecimal rem(BigDecimal $this$rem, BigDecimal other) {
        int $i$f$rem = 0;
        Intrinsics.checkNotNullParameter($this$rem, "$this$rem");
        BigDecimal bigDecimal = $this$rem.remainder(other);
        Intrinsics.checkNotNullExpressionValue(bigDecimal, "this.remainder(other)");
        return bigDecimal;
    }

    @InlineOnly
    private static final BigDecimal unaryMinus(BigDecimal $this$unaryMinus) {
        int $i$f$unaryMinus = 0;
        Intrinsics.checkNotNullParameter($this$unaryMinus, "$this$unaryMinus");
        BigDecimal bigDecimal = $this$unaryMinus.negate();
        Intrinsics.checkNotNullExpressionValue(bigDecimal, "this.negate()");
        return bigDecimal;
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigDecimal inc(BigDecimal $this$inc) {
        int $i$f$inc = 0;
        Intrinsics.checkNotNullParameter($this$inc, "$this$inc");
        BigDecimal bigDecimal = $this$inc.add(BigDecimal.ONE);
        Intrinsics.checkNotNullExpressionValue(bigDecimal, "this.add(BigDecimal.ONE)");
        return bigDecimal;
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigDecimal dec(BigDecimal $this$dec) {
        int $i$f$dec = 0;
        Intrinsics.checkNotNullParameter($this$dec, "$this$dec");
        BigDecimal bigDecimal = $this$dec.subtract(BigDecimal.ONE);
        Intrinsics.checkNotNullExpressionValue(bigDecimal, "this.subtract(BigDecimal.ONE)");
        return bigDecimal;
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(int $this$toBigDecimal) {
        int $i$f$toBigDecimal = 0;
        BigDecimal bigDecimal = BigDecimal.valueOf($this$toBigDecimal);
        Intrinsics.checkNotNullExpressionValue(bigDecimal, "BigDecimal.valueOf(this.toLong())");
        return bigDecimal;
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(int $this$toBigDecimal, MathContext mathContext) {
        int $i$f$toBigDecimal = 0;
        return new BigDecimal($this$toBigDecimal, mathContext);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(long $this$toBigDecimal) {
        int $i$f$toBigDecimal = 0;
        BigDecimal bigDecimal = BigDecimal.valueOf($this$toBigDecimal);
        Intrinsics.checkNotNullExpressionValue(bigDecimal, "BigDecimal.valueOf(this)");
        return bigDecimal;
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(long $this$toBigDecimal, MathContext mathContext) {
        int $i$f$toBigDecimal = 0;
        return new BigDecimal($this$toBigDecimal, mathContext);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(float $this$toBigDecimal) {
        int $i$f$toBigDecimal = 0;
        return new BigDecimal(String.valueOf($this$toBigDecimal));
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(float $this$toBigDecimal, MathContext mathContext) {
        int $i$f$toBigDecimal = 0;
        return new BigDecimal(String.valueOf($this$toBigDecimal), mathContext);
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(double $this$toBigDecimal) {
        int $i$f$toBigDecimal = 0;
        return new BigDecimal(String.valueOf($this$toBigDecimal));
    }

    @SinceKotlin(version="1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(double $this$toBigDecimal, MathContext mathContext) {
        int $i$f$toBigDecimal = 0;
        return new BigDecimal(String.valueOf($this$toBigDecimal), mathContext);
    }
}

