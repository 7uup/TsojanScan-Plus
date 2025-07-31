/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
 */
package okio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okio.InputStreamSource;
import okio.Okio;
import okio.OutputStreamSink;
import okio.Sink;
import okio.SocketAsyncTimeout;
import okio.Source;
import okio.Timeout;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, d1={"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\n\u001a\u00020\u000b*\u00020\f\u001a\u0016\u0010\r\u001a\u00020\u000b*\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u0006H\u0007\u001a\n\u0010\r\u001a\u00020\u000b*\u00020\u000f\u001a\n\u0010\r\u001a\u00020\u000b*\u00020\u0010\u001a%\u0010\r\u001a\u00020\u000b*\u00020\u00112\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0007\u00a2\u0006\u0002\u0010\u0015\u001a\n\u0010\u0016\u001a\u00020\u0017*\u00020\f\u001a\n\u0010\u0016\u001a\u00020\u0017*\u00020\u0018\u001a\n\u0010\u0016\u001a\u00020\u0017*\u00020\u0010\u001a%\u0010\u0016\u001a\u00020\u0017*\u00020\u00112\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0007\u00a2\u0006\u0002\u0010\u0019\"\u001c\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001X\u0082\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u001c\u0010\u0005\u001a\u00020\u0006*\u00060\u0007j\u0002`\b8@X\u0080\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\t\u00a8\u0006\u001a"}, d2={"logger", "Ljava/util/logging/Logger;", "kotlin.jvm.PlatformType", "getLogger$Okio__JvmOkioKt", "()Ljava/util/logging/Logger;", "isAndroidGetsocknameError", "", "Ljava/lang/AssertionError;", "Lkotlin/AssertionError;", "(Ljava/lang/AssertionError;)Z", "appendingSink", "Lokio/Sink;", "Ljava/io/File;", "sink", "append", "Ljava/io/OutputStream;", "Ljava/net/Socket;", "Ljava/nio/file/Path;", "options", "", "Ljava/nio/file/OpenOption;", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Lokio/Sink;", "source", "Lokio/Source;", "Ljava/io/InputStream;", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Lokio/Source;", "okio"}, xs="okio/Okio")
final class Okio__JvmOkioKt {
    private static final Logger logger = Logger.getLogger("okio.Okio");

    @NotNull
    public static final Sink sink(@NotNull OutputStream $this$sink) {
        Intrinsics.checkNotNullParameter($this$sink, "$this$sink");
        return new OutputStreamSink($this$sink, new Timeout());
    }

    @NotNull
    public static final Source source(@NotNull InputStream $this$source) {
        Intrinsics.checkNotNullParameter($this$source, "$this$source");
        return new InputStreamSource($this$source, new Timeout());
    }

    @NotNull
    public static final Sink sink(@NotNull Socket $this$sink) throws IOException {
        Intrinsics.checkNotNullParameter($this$sink, "$this$sink");
        SocketAsyncTimeout timeout2 = new SocketAsyncTimeout($this$sink);
        OutputStream outputStream2 = $this$sink.getOutputStream();
        Intrinsics.checkNotNullExpressionValue(outputStream2, "getOutputStream()");
        OutputStreamSink sink2 = new OutputStreamSink(outputStream2, timeout2);
        return timeout2.sink(sink2);
    }

    @NotNull
    public static final Source source(@NotNull Socket $this$source) throws IOException {
        Intrinsics.checkNotNullParameter($this$source, "$this$source");
        SocketAsyncTimeout timeout2 = new SocketAsyncTimeout($this$source);
        InputStream inputStream2 = $this$source.getInputStream();
        Intrinsics.checkNotNullExpressionValue(inputStream2, "getInputStream()");
        InputStreamSource source2 = new InputStreamSource(inputStream2, timeout2);
        return timeout2.source(source2);
    }

    private static final Logger getLogger$Okio__JvmOkioKt() {
        return logger;
    }

    @JvmOverloads
    @NotNull
    public static final Sink sink(@NotNull File $this$sink, boolean append) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter($this$sink, "$this$sink");
        return Okio.sink(new FileOutputStream($this$sink, append));
    }

    public static /* synthetic */ Sink sink$default(File file, boolean bl, int n, Object object) throws FileNotFoundException {
        if ((n & 1) != 0) {
            bl = false;
        }
        return Okio.sink(file, bl);
    }

    @JvmOverloads
    @NotNull
    public static final Sink sink(@NotNull File $this$sink) throws FileNotFoundException {
        return Okio.sink$default($this$sink, false, 1, null);
    }

    @NotNull
    public static final Sink appendingSink(@NotNull File $this$appendingSink) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter($this$appendingSink, "$this$appendingSink");
        return Okio.sink(new FileOutputStream($this$appendingSink, true));
    }

    @NotNull
    public static final Source source(@NotNull File $this$source) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter($this$source, "$this$source");
        File file = $this$source;
        boolean bl = false;
        return Okio.source(new FileInputStream(file));
    }

    @IgnoreJRERequirement
    @NotNull
    public static final Sink sink(@NotNull Path $this$sink, @NotNull OpenOption ... options) throws IOException {
        Intrinsics.checkNotNullParameter($this$sink, "$this$sink");
        Intrinsics.checkNotNullParameter(options, "options");
        OutputStream outputStream2 = Files.newOutputStream($this$sink, Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(outputStream2, "Files.newOutputStream(this, *options)");
        return Okio.sink(outputStream2);
    }

    @IgnoreJRERequirement
    @NotNull
    public static final Source source(@NotNull Path $this$source, @NotNull OpenOption ... options) throws IOException {
        Intrinsics.checkNotNullParameter($this$source, "$this$source");
        Intrinsics.checkNotNullParameter(options, "options");
        InputStream inputStream2 = Files.newInputStream($this$source, Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(inputStream2, "Files.newInputStream(this, *options)");
        return Okio.source(inputStream2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static final boolean isAndroidGetsocknameError(@NotNull AssertionError $this$isAndroidGetsocknameError) {
        Intrinsics.checkNotNullParameter($this$isAndroidGetsocknameError, "$this$isAndroidGetsocknameError");
        if (((Throwable)((Object)$this$isAndroidGetsocknameError)).getCause() == null) return false;
        String string = ((Throwable)((Object)$this$isAndroidGetsocknameError)).getMessage();
        if (string == null) return false;
        boolean bl = StringsKt.contains$default((CharSequence)string, "getsockname failed", false, 2, null);
        if (!bl) return false;
        return true;
    }

    public static final /* synthetic */ Logger access$getLogger$p() {
        return logger;
    }
}

