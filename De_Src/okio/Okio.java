/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
 */
package okio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio__JvmOkioKt;
import okio.Okio__OkioKt;
import okio.Sink;
import okio.Source;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=4, d1={"okio/Okio__JvmOkioKt", "okio/Okio__OkioKt"})
public final class Okio {
    public static final boolean isAndroidGetsocknameError(@NotNull AssertionError $this$isAndroidGetsocknameError) {
        return Okio__JvmOkioKt.isAndroidGetsocknameError($this$isAndroidGetsocknameError);
    }

    @JvmName(name="blackhole")
    @NotNull
    public static final Sink blackhole() {
        return Okio__OkioKt.blackhole();
    }

    @NotNull
    public static final Sink appendingSink(@NotNull File $this$appendingSink) throws FileNotFoundException {
        return Okio__JvmOkioKt.appendingSink($this$appendingSink);
    }

    @NotNull
    public static final BufferedSink buffer(@NotNull Sink $this$buffer) {
        return Okio__OkioKt.buffer($this$buffer);
    }

    @NotNull
    public static final BufferedSource buffer(@NotNull Source $this$buffer) {
        return Okio__OkioKt.buffer($this$buffer);
    }

    @JvmOverloads
    @NotNull
    public static final Sink sink(@NotNull File $this$sink, boolean append) throws FileNotFoundException {
        return Okio__JvmOkioKt.sink($this$sink, append);
    }

    public static /* synthetic */ Sink sink$default(File file, boolean bl, int n, Object object) throws FileNotFoundException {
        return Okio__JvmOkioKt.sink$default(file, bl, n, object);
    }

    @JvmOverloads
    @NotNull
    public static final Sink sink(@NotNull File $this$sink) throws FileNotFoundException {
        return Okio.sink$default($this$sink, false, 1, null);
    }

    @NotNull
    public static final Sink sink(@NotNull OutputStream $this$sink) {
        return Okio__JvmOkioKt.sink($this$sink);
    }

    @NotNull
    public static final Sink sink(@NotNull Socket $this$sink) throws IOException {
        return Okio__JvmOkioKt.sink($this$sink);
    }

    @IgnoreJRERequirement
    @NotNull
    public static final Sink sink(@NotNull Path $this$sink, @NotNull OpenOption ... options) throws IOException {
        return Okio__JvmOkioKt.sink($this$sink, options);
    }

    @NotNull
    public static final Source source(@NotNull File $this$source) throws FileNotFoundException {
        return Okio__JvmOkioKt.source($this$source);
    }

    @NotNull
    public static final Source source(@NotNull InputStream $this$source) {
        return Okio__JvmOkioKt.source($this$source);
    }

    @NotNull
    public static final Source source(@NotNull Socket $this$source) throws IOException {
        return Okio__JvmOkioKt.source($this$source);
    }

    @IgnoreJRERequirement
    @NotNull
    public static final Source source(@NotNull Path $this$source, @NotNull OpenOption ... options) throws IOException {
        return Okio__JvmOkioKt.source($this$source, options);
    }
}

