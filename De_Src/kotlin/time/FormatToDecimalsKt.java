/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.time;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000.\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\u001a\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0002\u001a\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000\u001a\u0018\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bH\u0000\u001a\u0018\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bH\u0000\"\u001c\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0004\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2={"precisionFormats", "", "Ljava/lang/ThreadLocal;", "Ljava/text/DecimalFormat;", "[Ljava/lang/ThreadLocal;", "rootNegativeExpFormatSymbols", "Ljava/text/DecimalFormatSymbols;", "rootPositiveExpFormatSymbols", "scientificFormat", "createFormatForDecimals", "decimals", "", "formatScientific", "", "value", "", "formatToExactDecimals", "formatUpToDecimals", "kotlin-stdlib"})
public final class FormatToDecimalsKt {
    private static final DecimalFormatSymbols rootNegativeExpFormatSymbols;
    private static final DecimalFormatSymbols rootPositiveExpFormatSymbols;
    private static final ThreadLocal<DecimalFormat>[] precisionFormats;
    private static final ThreadLocal<DecimalFormat> scientificFormat;

    private static final DecimalFormat createFormatForDecimals(int decimals) {
        DecimalFormat decimalFormat = new DecimalFormat("0", rootNegativeExpFormatSymbols);
        boolean bl = false;
        boolean bl2 = false;
        DecimalFormat $this$apply = decimalFormat;
        boolean bl3 = false;
        if (decimals > 0) {
            $this$apply.setMinimumFractionDigits(decimals);
        }
        $this$apply.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat;
    }

    @NotNull
    public static final String formatToExactDecimals(double value, int decimals) {
        DecimalFormat decimalFormat;
        if (decimals < precisionFormats.length) {
            ThreadLocal<DecimalFormat> threadLocal = precisionFormats[decimals];
            boolean bl = false;
            DecimalFormat decimalFormat2 = threadLocal.get();
            if (decimalFormat2 == null) {
                boolean bl2 = false;
                DecimalFormat decimalFormat3 = FormatToDecimalsKt.createFormatForDecimals(decimals);
                boolean bl3 = false;
                boolean bl4 = false;
                DecimalFormat decimalFormat4 = decimalFormat3;
                boolean bl5 = false;
                threadLocal.set(decimalFormat4);
                decimalFormat2 = decimalFormat3;
            }
            decimalFormat = decimalFormat2;
        } else {
            decimalFormat = FormatToDecimalsKt.createFormatForDecimals(decimals);
        }
        DecimalFormat format = decimalFormat;
        String string = format.format(value);
        Intrinsics.checkNotNullExpressionValue(string, "format.format(value)");
        return string;
    }

    @NotNull
    public static final String formatUpToDecimals(double value, int decimals) {
        DecimalFormat decimalFormat = FormatToDecimalsKt.createFormatForDecimals(0);
        boolean bl = false;
        boolean bl2 = false;
        DecimalFormat $this$apply = decimalFormat;
        boolean bl3 = false;
        $this$apply.setMaximumFractionDigits(decimals);
        String string = decimalFormat.format(value);
        Intrinsics.checkNotNullExpressionValue(string, "createFormatForDecimals(\u2026 }\n        .format(value)");
        return string;
    }

    @NotNull
    public static final String formatScientific(double value) {
        Object object = scientificFormat;
        boolean bl = false;
        DecimalFormat decimalFormat = ((ThreadLocal)object).get();
        if (decimalFormat == null) {
            boolean bl2 = false;
            DecimalFormat decimalFormat2 = new DecimalFormat("0E0", rootNegativeExpFormatSymbols);
            boolean bl3 = false;
            boolean bl4 = false;
            DecimalFormat $this$apply = decimalFormat2;
            boolean bl5 = false;
            $this$apply.setMinimumFractionDigits(2);
            DecimalFormat decimalFormat3 = decimalFormat2;
            boolean bl6 = false;
            bl3 = false;
            DecimalFormat decimalFormat4 = decimalFormat3;
            boolean bl7 = false;
            ((ThreadLocal)object).set(decimalFormat4);
            decimalFormat = decimalFormat3;
        }
        object = decimalFormat;
        bl = false;
        boolean bl8 = false;
        DecimalFormat $this$apply = (DecimalFormat)object;
        boolean bl9 = false;
        $this$apply.setDecimalFormatSymbols(value >= 1.0 || value <= (double)-1 ? rootPositiveExpFormatSymbols : rootNegativeExpFormatSymbols);
        String string = ((DecimalFormat)object).format(value);
        Intrinsics.checkNotNullExpressionValue(string, "scientificFormat.getOrSe\u2026 }\n        .format(value)");
        return string;
    }

    static {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.ROOT);
        boolean bl = false;
        int n = 0;
        DecimalFormatSymbols $this$apply = decimalFormatSymbols;
        boolean bl2 = false;
        $this$apply.setExponentSeparator("e");
        rootNegativeExpFormatSymbols = decimalFormatSymbols;
        decimalFormatSymbols = new DecimalFormatSymbols(Locale.ROOT);
        bl = false;
        n = 0;
        $this$apply = decimalFormatSymbols;
        boolean bl3 = false;
        $this$apply.setExponentSeparator("e+");
        rootPositiveExpFormatSymbols = decimalFormatSymbols;
        int n2 = 4;
        ThreadLocal[] threadLocalArray = new ThreadLocal[n2];
        n = 0;
        while (n < n2) {
            ThreadLocal threadLocal;
            int $this$apply2 = n;
            int n3 = n++;
            ThreadLocal[] threadLocalArray2 = threadLocalArray;
            boolean bl4 = false;
            threadLocalArray2[n3] = threadLocal = new ThreadLocal();
        }
        precisionFormats = threadLocalArray;
        scientificFormat = new ThreadLocal();
    }
}

