/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.platform.android;

import java.util.logging.Handler;
import java.util.logging.LogRecord;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.platform.android.AndroidLog;
import okhttp3.internal.platform.android.AndroidLogKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0016\u00a8\u0006\t"}, d2={"Lokhttp3/internal/platform/android/AndroidLogHandler;", "Ljava/util/logging/Handler;", "()V", "close", "", "flush", "publish", "record", "Ljava/util/logging/LogRecord;", "okhttp"})
public final class AndroidLogHandler
extends Handler {
    public static final AndroidLogHandler INSTANCE;

    @Override
    public void publish(@NotNull LogRecord record) {
        Intrinsics.checkNotNullParameter(record, "record");
        String string = record.getLoggerName();
        Intrinsics.checkNotNullExpressionValue(string, "record.loggerName");
        int n = AndroidLogKt.access$getAndroidLevel$p(record);
        String string2 = record.getMessage();
        Intrinsics.checkNotNullExpressionValue(string2, "record.message");
        AndroidLog.INSTANCE.androidLog$okhttp(string, n, string2, record.getThrown());
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() {
    }

    private AndroidLogHandler() {
    }

    static {
        AndroidLogHandler androidLogHandler;
        INSTANCE = androidLogHandler = new AndroidLogHandler();
    }
}

