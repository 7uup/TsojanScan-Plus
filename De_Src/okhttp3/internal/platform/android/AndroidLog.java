/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  android.util.Log
 */
package okhttp3.internal.platform.android;

import android.util.Log;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.OkHttpClient;
import okhttp3.internal.SuppressSignatureCheck;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http2.Http2;
import okhttp3.internal.platform.android.AndroidLogHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0007\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J/\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0000\u00a2\u0006\u0002\b\u0012J\u0006\u0010\u0013\u001a\u00020\fJ\u0018\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\nH\u0002J\u0010\u0010\u0017\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2={"Lokhttp3/internal/platform/android/AndroidLog;", "", "()V", "MAX_LOG_LENGTH", "", "configuredLoggers", "Ljava/util/concurrent/CopyOnWriteArraySet;", "Ljava/util/logging/Logger;", "knownLoggers", "", "", "androidLog", "", "loggerName", "logLevel", "message", "t", "", "androidLog$okhttp", "enable", "enableLogging", "logger", "tag", "loggerTag", "okhttp"})
@SuppressSignatureCheck
public final class AndroidLog {
    private static final int MAX_LOG_LENGTH = 4000;
    private static final CopyOnWriteArraySet<Logger> configuredLoggers;
    private static final Map<String, String> knownLoggers;
    public static final AndroidLog INSTANCE;

    public final void androidLog$okhttp(@NotNull String loggerName, int logLevel, @NotNull String message, @Nullable Throwable t) {
        Intrinsics.checkNotNullParameter(loggerName, "loggerName");
        Intrinsics.checkNotNullParameter(message, "message");
        String tag = this.loggerTag(loggerName);
        if (Log.isLoggable((String)tag, (int)logLevel)) {
            String logMessage = message;
            if (t != null) {
                logMessage = logMessage + "\n" + Log.getStackTraceString((Throwable)t);
            }
            int length = logMessage.length();
            for (int i = 0; i < length; ++i) {
                int end;
                int newline = StringsKt.indexOf$default((CharSequence)logMessage, '\n', i, false, 4, null);
                newline = newline != -1 ? newline : length;
                do {
                    int n = i + 4000;
                    boolean bl = false;
                    end = Math.min(newline, n);
                    String string = logMessage;
                    bl = false;
                    String string2 = string;
                    if (string2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    String string3 = string2.substring(i, end);
                    Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                    Log.println((int)logLevel, (String)tag, (String)string3);
                } while ((i = end) < newline);
            }
        }
    }

    private final String loggerTag(String loggerName) {
        String string = knownLoggers.get(loggerName);
        if (string == null) {
            string = StringsKt.take(loggerName, 23);
        }
        return string;
    }

    /*
     * WARNING - void declaration
     */
    public final void enable() {
        Object object = knownLoggers;
        boolean bl = false;
        Iterator<Map.Entry<String, String>> iterator2 = object.entrySet().iterator();
        while (iterator2.hasNext()) {
            void logger;
            Map.Entry<String, String> entry;
            Map.Entry<String, String> entry2 = entry = iterator2.next();
            boolean bl2 = false;
            object = entry2.getKey();
            entry2 = entry;
            bl2 = false;
            String tag = entry2.getValue();
            this.enableLogging((String)logger, tag);
        }
    }

    private final void enableLogging(String logger, String tag) {
        Logger logger2 = Logger.getLogger(logger);
        if (configuredLoggers.add(logger2)) {
            Logger logger3 = logger2;
            Intrinsics.checkNotNullExpressionValue(logger3, "logger");
            logger3.setUseParentHandlers(false);
            logger2.setLevel(Log.isLoggable((String)tag, (int)3) ? Level.FINE : (Log.isLoggable((String)tag, (int)4) ? Level.INFO : Level.WARNING));
            logger2.addHandler(AndroidLogHandler.INSTANCE);
        }
    }

    private AndroidLog() {
    }

    static {
        String packageName;
        AndroidLog androidLog;
        INSTANCE = androidLog = new AndroidLog();
        configuredLoggers = new CopyOnWriteArraySet();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        boolean bl = false;
        boolean bl2 = false;
        LinkedHashMap $this$apply = linkedHashMap;
        boolean bl3 = false;
        Package package_ = OkHttpClient.class.getPackage();
        String string = packageName = package_ != null ? package_.getName() : null;
        if (packageName != null) {
            ((Map)$this$apply).put(packageName, "OkHttp");
        }
        Map map = $this$apply;
        String string2 = OkHttpClient.class.getName();
        Intrinsics.checkNotNullExpressionValue(string2, "OkHttpClient::class.java.name");
        map.put(string2, "okhttp.OkHttpClient");
        Map map2 = $this$apply;
        String string3 = Http2.class.getName();
        Intrinsics.checkNotNullExpressionValue(string3, "Http2::class.java.name");
        map2.put(string3, "okhttp.Http2");
        Map map3 = $this$apply;
        String string4 = TaskRunner.class.getName();
        Intrinsics.checkNotNullExpressionValue(string4, "TaskRunner::class.java.name");
        map3.put(string4, "okhttp.TaskRunner");
        ((Map)$this$apply).put("okhttp3.mockwebserver.MockWebServer", "okhttp.MockWebServer");
        knownLoggers = MapsKt.toMap(linkedHashMap);
    }
}

