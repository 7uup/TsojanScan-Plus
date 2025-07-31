/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.LinesSequence;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0017\u0010\u0000\u001a\u00020\u0005*\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u001c\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u001a\u001e\u0010\n\u001a\u00020\u000b*\u00020\u00022\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000b0\r\u001a\u0010\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010*\u00020\u0001\u001a\n\u0010\u0011\u001a\u00020\u0012*\u00020\u0013\u001a\u0010\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0015*\u00020\u0002\u001a\n\u0010\u0016\u001a\u00020\u000e*\u00020\u0002\u001a\u0017\u0010\u0016\u001a\u00020\u000e*\u00020\u00132\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0087\b\u001a\r\u0010\u0019\u001a\u00020\u001a*\u00020\u000eH\u0087\b\u001a8\u0010\u001b\u001a\u0002H\u001c\"\u0004\b\u0000\u0010\u001c*\u00020\u00022\u0018\u0010\u001d\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u0010\u0012\u0004\u0012\u0002H\u001c0\rH\u0086\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0002\u0010\u001f\u0082\u0002\u000f\n\u0006\b\u0011(\u001e0\u0001\n\u0005\b\u009920\u0001\u00a8\u0006 "}, d2={"buffered", "Ljava/io/BufferedReader;", "Ljava/io/Reader;", "bufferSize", "", "Ljava/io/BufferedWriter;", "Ljava/io/Writer;", "copyTo", "", "out", "forEachLine", "", "action", "Lkotlin/Function1;", "", "lineSequence", "Lkotlin/sequences/Sequence;", "readBytes", "", "Ljava/net/URL;", "readLines", "", "readText", "charset", "Ljava/nio/charset/Charset;", "reader", "Ljava/io/StringReader;", "useLines", "T", "block", "Requires newer compiler version to be inlined correctly.", "(Ljava/io/Reader;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib"})
@JvmName(name="TextStreamsKt")
public final class TextStreamsKt {
    @InlineOnly
    private static final BufferedReader buffered(Reader $this$buffered, int bufferSize) {
        int $i$f$buffered = 0;
        return $this$buffered instanceof BufferedReader ? (BufferedReader)$this$buffered : new BufferedReader($this$buffered, bufferSize);
    }

    static /* synthetic */ BufferedReader buffered$default(Reader $this$buffered, int bufferSize, int n, Object object) {
        if ((n & 1) != 0) {
            bufferSize = 8192;
        }
        boolean $i$f$buffered = false;
        return $this$buffered instanceof BufferedReader ? (BufferedReader)$this$buffered : new BufferedReader($this$buffered, bufferSize);
    }

    @InlineOnly
    private static final BufferedWriter buffered(Writer $this$buffered, int bufferSize) {
        int $i$f$buffered = 0;
        return $this$buffered instanceof BufferedWriter ? (BufferedWriter)$this$buffered : new BufferedWriter($this$buffered, bufferSize);
    }

    static /* synthetic */ BufferedWriter buffered$default(Writer $this$buffered, int bufferSize, int n, Object object) {
        if ((n & 1) != 0) {
            bufferSize = 8192;
        }
        boolean $i$f$buffered = false;
        return $this$buffered instanceof BufferedWriter ? (BufferedWriter)$this$buffered : new BufferedWriter($this$buffered, bufferSize);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * WARNING - void declaration
     */
    public static final void forEachLine(@NotNull Reader $this$forEachLine, @NotNull Function1<? super String, Unit> action) {
        Intrinsics.checkNotNullParameter($this$forEachLine, "$this$forEachLine");
        Intrinsics.checkNotNullParameter(action, "action");
        Reader $this$useLines$iv = $this$forEachLine;
        boolean $i$f$useLines = false;
        Closeable closeable = $this$useLines$iv;
        int n = 8192;
        boolean bl = false;
        closeable = closeable instanceof BufferedReader ? (BufferedReader)closeable : new BufferedReader((Reader)closeable, n);
        n = 0;
        bl = false;
        Throwable throwable = null;
        try {
            void $this$forEach$iv;
            BufferedReader it$iv = (BufferedReader)closeable;
            boolean bl2 = false;
            Sequence<String> it = TextStreamsKt.lineSequence(it$iv);
            boolean bl3 = false;
            Sequence<String> sequence = it;
            Function1<? super String, Unit> action$iv = action;
            boolean $i$f$forEach = false;
            for (Object element$iv : $this$forEach$iv) {
                action$iv.invoke((String)element$iv);
            }
            Unit unit = Unit.INSTANCE;
        } catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        } finally {
            CloseableKt.closeFinally(closeable, throwable);
        }
    }

    @NotNull
    public static final List<String> readLines(@NotNull Reader $this$readLines) {
        Intrinsics.checkNotNullParameter($this$readLines, "$this$readLines");
        boolean bl = false;
        ArrayList result = new ArrayList();
        TextStreamsKt.forEachLine($this$readLines, (Function1<? super String, Unit>)new Function1<String, Unit>(result){
            final /* synthetic */ ArrayList $result;

            public final void invoke(@NotNull String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                this.$result.add(it);
            }
            {
                this.$result = arrayList;
                super(1);
            }
        });
        return result;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static final <T> T useLines(@NotNull Reader $this$useLines, @NotNull Function1<? super Sequence<String>, ? extends T> block) {
        T t;
        int $i$f$useLines = 0;
        Intrinsics.checkNotNullParameter($this$useLines, "$this$useLines");
        Intrinsics.checkNotNullParameter(block, "block");
        Closeable closeable = $this$useLines;
        int n = 8192;
        boolean bl = false;
        closeable = closeable instanceof BufferedReader ? (BufferedReader)closeable : new BufferedReader((Reader)closeable, n);
        n = 0;
        bl = false;
        Throwable throwable = null;
        try {
            BufferedReader it = (BufferedReader)closeable;
            boolean bl2 = false;
            t = block.invoke(TextStreamsKt.lineSequence(it));
        } catch (Throwable throwable2) {
            try {
                throwable = throwable2;
                throw throwable2;
            } catch (Throwable throwable3) {
                InlineMarker.finallyStart(1);
                if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
                    CloseableKt.closeFinally(closeable, throwable);
                } else if (throwable == null) {
                    closeable.close();
                } else {
                    try {
                        closeable.close();
                    } catch (Throwable throwable4) {
                        // empty catch block
                    }
                }
                InlineMarker.finallyEnd(1);
                throw throwable3;
            }
        }
        InlineMarker.finallyStart(1);
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
            CloseableKt.closeFinally(closeable, throwable);
        } else {
            closeable.close();
        }
        InlineMarker.finallyEnd(1);
        return t;
    }

    @InlineOnly
    private static final StringReader reader(String $this$reader) {
        int $i$f$reader = 0;
        return new StringReader($this$reader);
    }

    @NotNull
    public static final Sequence<String> lineSequence(@NotNull BufferedReader $this$lineSequence) {
        Intrinsics.checkNotNullParameter($this$lineSequence, "$this$lineSequence");
        return SequencesKt.constrainOnce(new LinesSequence($this$lineSequence));
    }

    @NotNull
    public static final String readText(@NotNull Reader $this$readText) {
        Intrinsics.checkNotNullParameter($this$readText, "$this$readText");
        StringWriter buffer = new StringWriter();
        TextStreamsKt.copyTo$default($this$readText, buffer, 0, 2, null);
        String string = buffer.toString();
        Intrinsics.checkNotNullExpressionValue(string, "buffer.toString()");
        return string;
    }

    public static final long copyTo(@NotNull Reader $this$copyTo, @NotNull Writer out, int bufferSize) {
        Intrinsics.checkNotNullParameter($this$copyTo, "$this$copyTo");
        Intrinsics.checkNotNullParameter(out, "out");
        long charsCopied = 0L;
        char[] buffer = new char[bufferSize];
        int chars = $this$copyTo.read(buffer);
        while (chars >= 0) {
            out.write(buffer, 0, chars);
            charsCopied += (long)chars;
            chars = $this$copyTo.read(buffer);
        }
        return charsCopied;
    }

    public static /* synthetic */ long copyTo$default(Reader reader, Writer writer, int n, int n2, Object object) {
        if ((n2 & 2) != 0) {
            n = 8192;
        }
        return TextStreamsKt.copyTo(reader, writer, n);
    }

    @InlineOnly
    private static final String readText(URL $this$readText, Charset charset) {
        int $i$f$readText = 0;
        byte[] byArray = TextStreamsKt.readBytes($this$readText);
        boolean bl = false;
        boolean bl2 = false;
        return new String(byArray, charset);
    }

    static /* synthetic */ String readText$default(URL $this$readText, Charset charset, int n, Object object) {
        if ((n & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        boolean $i$f$readText = false;
        object = TextStreamsKt.readBytes($this$readText);
        boolean bl = false;
        boolean bl2 = false;
        return new String((byte[])object, charset);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @NotNull
    public static final byte[] readBytes(@NotNull URL $this$readBytes) {
        byte[] byArray;
        Intrinsics.checkNotNullParameter($this$readBytes, "$this$readBytes");
        Closeable closeable = $this$readBytes.openStream();
        boolean bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            InputStream it = (InputStream)closeable;
            boolean bl3 = false;
            InputStream inputStream2 = it;
            Intrinsics.checkNotNullExpressionValue(inputStream2, "it");
            byArray = ByteStreamsKt.readBytes(inputStream2);
        } catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        } finally {
            CloseableKt.closeFinally(closeable, throwable);
        }
        return byArray;
    }
}

