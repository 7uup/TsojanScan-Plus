/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.http;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okhttp3.internal.http.DatesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000+\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\n\u001a\u000e\u0010\f\u001a\u0004\u0018\u00010\r*\u00020\u0005H\u0000\u001a\f\u0010\u000e\u001a\u00020\u0005*\u00020\rH\u0000\"\u0018\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0006\"\u000e\u0010\u0007\u001a\u00020\bX\u0080T\u00a2\u0006\u0002\n\u0000\"\u0010\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000b\u00a8\u0006\u000f"}, d2={"BROWSER_COMPATIBLE_DATE_FORMATS", "", "Ljava/text/DateFormat;", "[Ljava/text/DateFormat;", "BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS", "", "[Ljava/lang/String;", "MAX_DATE", "", "STANDARD_DATE_FORMAT", "okhttp3/internal/http/DatesKt$STANDARD_DATE_FORMAT$1", "Lokhttp3/internal/http/DatesKt$STANDARD_DATE_FORMAT$1;", "toHttpDateOrNull", "Ljava/util/Date;", "toHttpDateString", "okhttp"})
public final class DatesKt {
    public static final long MAX_DATE = 253402300799999L;
    private static final STANDARD_DATE_FORMAT.1 STANDARD_DATE_FORMAT = new ThreadLocal<DateFormat>(){

        @NotNull
        protected DateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
            boolean bl = false;
            boolean bl2 = false;
            SimpleDateFormat $this$apply = simpleDateFormat;
            boolean bl3 = false;
            $this$apply.setLenient(false);
            $this$apply.setTimeZone(Util.UTC);
            return simpleDateFormat;
        }
    };
    private static final String[] BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS = new String[]{"EEE, dd MMM yyyy HH:mm:ss zzz", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z", "EEE MMM d yyyy HH:mm:ss z"};
    private static final DateFormat[] BROWSER_COMPATIBLE_DATE_FORMATS = new DateFormat[BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS.length];

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * WARNING - void declaration
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Nullable
    public static final Date toHttpDateOrNull(@NotNull String $this$toHttpDateOrNull) {
        Intrinsics.checkNotNullParameter($this$toHttpDateOrNull, "$this$toHttpDateOrNull");
        CharSequence charSequence = $this$toHttpDateOrNull;
        boolean bl = false;
        if (charSequence.length() == 0) {
            return null;
        }
        boolean bl2 = false;
        if (bl2) {
            return null;
        }
        ParsePosition position = new ParsePosition(0);
        Date result = ((DateFormat)STANDARD_DATE_FORMAT.get()).parse($this$toHttpDateOrNull, position);
        if (position.getIndex() == $this$toHttpDateOrNull.length()) {
            return result;
        }
        String[] stringArray = BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS;
        boolean bl3 = false;
        boolean bl4 = false;
        synchronized (stringArray) {
            boolean bl5 = false;
            int n = 0;
            int n2 = BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS.length;
            while (true) {
                void i;
                if (n >= n2) {
                    Unit unit = Unit.INSTANCE;
                    return null;
                }
                DateFormat format = BROWSER_COMPATIBLE_DATE_FORMATS[i];
                if (format == null) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS[i], Locale.US);
                    boolean bl6 = false;
                    boolean bl7 = false;
                    SimpleDateFormat $this$apply = simpleDateFormat;
                    boolean bl8 = false;
                    $this$apply.setTimeZone(Util.UTC);
                    DatesKt.BROWSER_COMPATIBLE_DATE_FORMATS[i] = format = (DateFormat)simpleDateFormat;
                }
                position.setIndex(0);
                result = format.parse($this$toHttpDateOrNull, position);
                if (position.getIndex() != 0) {
                    return result;
                }
                ++i;
            }
        }
    }

    @NotNull
    public static final String toHttpDateString(@NotNull Date $this$toHttpDateString) {
        Intrinsics.checkNotNullParameter($this$toHttpDateString, "$this$toHttpDateString");
        String string = ((DateFormat)STANDARD_DATE_FORMAT.get()).format($this$toHttpDateString);
        Intrinsics.checkNotNullExpressionValue(string, "STANDARD_DATE_FORMAT.get().format(this)");
        return string;
    }
}

