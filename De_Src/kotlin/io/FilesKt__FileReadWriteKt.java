/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.ExposingBufferByteArrayOutputStream;
import kotlin.io.FilesKt;
import kotlin.io.FilesKt__FilePathComponentsKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000z\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001c\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t\u001a!\u0010\n\u001a\u00020\u000b*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\rH\u0087\b\u001a!\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\rH\u0087\b\u001aB\u0010\u0010\u001a\u00020\u0001*\u00020\u000226\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0004\u00a2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\r\u00a2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00010\u0012\u001aJ\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\r26\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0004\u00a2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\r\u00a2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00010\u0012\u001a7\u0010\u0018\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0007\u00a2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00010\u0019\u001a\r\u0010\u001b\u001a\u00020\u001c*\u00020\u0002H\u0087\b\u001a\r\u0010\u001d\u001a\u00020\u001e*\u00020\u0002H\u0087\b\u001a\u0017\u0010\u001f\u001a\u00020 *\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\u0087\b\u001a\n\u0010!\u001a\u00020\u0004*\u00020\u0002\u001a\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00070#*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0014\u0010$\u001a\u00020\u0007*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0017\u0010%\u001a\u00020&*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\u0087\b\u001aB\u0010'\u001a\u0002H(\"\u0004\b\u0000\u0010(*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\u0018\u0010)\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070*\u0012\u0004\u0012\u0002H(0\u0019H\u0086\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0002\u0010,\u001a\u0012\u0010-\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001c\u0010.\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0017\u0010/\u001a\u000200*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\u0087\b\u0082\u0002\u000f\n\u0006\b\u0011(+0\u0001\n\u0005\b\u009920\u0001\u00a8\u00061"}, d2={"appendBytes", "", "Ljava/io/File;", "array", "", "appendText", "text", "", "charset", "Ljava/nio/charset/Charset;", "bufferedReader", "Ljava/io/BufferedReader;", "bufferSize", "", "bufferedWriter", "Ljava/io/BufferedWriter;", "forEachBlock", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "buffer", "bytesRead", "blockSize", "forEachLine", "Lkotlin/Function1;", "line", "inputStream", "Ljava/io/FileInputStream;", "outputStream", "Ljava/io/FileOutputStream;", "printWriter", "Ljava/io/PrintWriter;", "readBytes", "readLines", "", "readText", "reader", "Ljava/io/InputStreamReader;", "useLines", "T", "block", "Lkotlin/sequences/Sequence;", "Requires newer compiler version to be inlined correctly.", "(Ljava/io/File;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "writeBytes", "writeText", "writer", "Ljava/io/OutputStreamWriter;", "kotlin-stdlib"}, xs="kotlin/io/FilesKt")
class FilesKt__FileReadWriteKt
extends FilesKt__FilePathComponentsKt {
    @InlineOnly
    private static final InputStreamReader reader(File $this$reader, Charset charset) {
        int $i$f$reader = 0;
        Object object = $this$reader;
        boolean bl = false;
        object = new FileInputStream((File)object);
        bl = false;
        return new InputStreamReader((InputStream)object, charset);
    }

    static /* synthetic */ InputStreamReader reader$default(File $this$reader, Charset charset, int n, Object object) {
        if ((n & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        boolean $i$f$reader = false;
        object = $this$reader;
        boolean bl = false;
        object = new FileInputStream((File)object);
        bl = false;
        return new InputStreamReader((InputStream)object, charset);
    }

    @InlineOnly
    private static final BufferedReader bufferedReader(File $this$bufferedReader, Charset charset, int bufferSize) {
        int $i$f$bufferedReader = 0;
        Object object = $this$bufferedReader;
        boolean bl = false;
        Object object2 = object;
        boolean bl2 = false;
        object2 = new FileInputStream((File)object2);
        bl2 = false;
        object = new InputStreamReader((InputStream)object2, charset);
        bl = false;
        return object instanceof BufferedReader ? (BufferedReader)object : new BufferedReader((Reader)object, bufferSize);
    }

    static /* synthetic */ BufferedReader bufferedReader$default(File $this$bufferedReader, Charset charset, int bufferSize, int n, Object object) {
        if ((n & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((n & 2) != 0) {
            bufferSize = 8192;
        }
        boolean $i$f$bufferedReader = false;
        object = $this$bufferedReader;
        boolean bl = false;
        Object object2 = object;
        boolean bl2 = false;
        object2 = new FileInputStream((File)object2);
        bl2 = false;
        object = new InputStreamReader((InputStream)object2, charset);
        bl = false;
        return object instanceof BufferedReader ? (BufferedReader)object : new BufferedReader((Reader)object, bufferSize);
    }

    @InlineOnly
    private static final OutputStreamWriter writer(File $this$writer, Charset charset) {
        int $i$f$writer = 0;
        Object object = $this$writer;
        boolean bl = false;
        object = new FileOutputStream((File)object);
        bl = false;
        return new OutputStreamWriter((OutputStream)object, charset);
    }

    static /* synthetic */ OutputStreamWriter writer$default(File $this$writer, Charset charset, int n, Object object) {
        if ((n & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        boolean $i$f$writer = false;
        object = $this$writer;
        boolean bl = false;
        object = new FileOutputStream((File)object);
        bl = false;
        return new OutputStreamWriter((OutputStream)object, charset);
    }

    @InlineOnly
    private static final BufferedWriter bufferedWriter(File $this$bufferedWriter, Charset charset, int bufferSize) {
        int $i$f$bufferedWriter = 0;
        Object object = $this$bufferedWriter;
        boolean bl = false;
        Object object2 = object;
        boolean bl2 = false;
        object2 = new FileOutputStream((File)object2);
        bl2 = false;
        object = new OutputStreamWriter((OutputStream)object2, charset);
        bl = false;
        return object instanceof BufferedWriter ? (BufferedWriter)object : new BufferedWriter((Writer)object, bufferSize);
    }

    static /* synthetic */ BufferedWriter bufferedWriter$default(File $this$bufferedWriter, Charset charset, int bufferSize, int n, Object object) {
        if ((n & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((n & 2) != 0) {
            bufferSize = 8192;
        }
        boolean $i$f$bufferedWriter = false;
        object = $this$bufferedWriter;
        boolean bl = false;
        Object object2 = object;
        boolean bl2 = false;
        object2 = new FileOutputStream((File)object2);
        bl2 = false;
        object = new OutputStreamWriter((OutputStream)object2, charset);
        bl = false;
        return object instanceof BufferedWriter ? (BufferedWriter)object : new BufferedWriter((Writer)object, bufferSize);
    }

    @InlineOnly
    private static final PrintWriter printWriter(File $this$printWriter, Charset charset) {
        int $i$f$printWriter = 0;
        File file = $this$printWriter;
        int n = 8192;
        boolean bl = false;
        Object object = file;
        boolean bl2 = false;
        Object object2 = object;
        boolean bl3 = false;
        object2 = new FileOutputStream((File)object2);
        bl3 = false;
        object = new OutputStreamWriter((OutputStream)object2, charset);
        bl2 = false;
        return new PrintWriter(object instanceof BufferedWriter ? (BufferedWriter)object : new BufferedWriter((Writer)object, n));
    }

    static /* synthetic */ PrintWriter printWriter$default(File $this$printWriter, Charset charset, int n, Object object) {
        if ((n & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        boolean $i$f$printWriter = false;
        object = $this$printWriter;
        int n2 = 8192;
        boolean bl = false;
        Object object2 = object;
        boolean bl2 = false;
        Object object3 = object2;
        boolean bl3 = false;
        object3 = new FileOutputStream((File)object3);
        bl3 = false;
        object2 = new OutputStreamWriter((OutputStream)object3, charset);
        bl2 = false;
        return new PrintWriter(object2 instanceof BufferedWriter ? (BufferedWriter)object2 : new BufferedWriter((Writer)object2, n2));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @NotNull
    public static final byte[] readBytes(@NotNull File $this$readBytes) {
        byte[] byArray;
        Intrinsics.checkNotNullParameter($this$readBytes, "$this$readBytes");
        Object object = $this$readBytes;
        boolean bl = false;
        object = new FileInputStream((File)object);
        bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            byte[] byArray2;
            int read2;
            FileInputStream input = (FileInputStream)object;
            boolean bl3 = false;
            int offset = 0;
            long l = $this$readBytes.length();
            boolean bl4 = false;
            boolean bl5 = false;
            long length = l;
            boolean bl6 = false;
            if (length > (long)Integer.MAX_VALUE) {
                throw (Throwable)new OutOfMemoryError("File " + $this$readBytes + " is too big (" + length + " bytes) to fit in memory.");
            }
            int remaining = (int)l;
            byte[] result = new byte[remaining];
            while (remaining > 0 && (read2 = input.read(result, offset, remaining)) >= 0) {
                remaining -= read2;
                offset += read2;
            }
            if (remaining > 0) {
                byte[] read2 = result;
                bl5 = false;
                byte[] byArray3 = Arrays.copyOf(read2, offset);
                byArray2 = byArray3;
                Intrinsics.checkNotNullExpressionValue(byArray3, "java.util.Arrays.copyOf(this, newSize)");
            } else {
                int extraByte = input.read();
                if (extraByte == -1) {
                    byArray2 = result;
                } else {
                    ExposingBufferByteArrayOutputStream extra = new ExposingBufferByteArrayOutputStream(8193);
                    extra.write(extraByte);
                    ByteStreamsKt.copyTo$default(input, extra, 0, 2, null);
                    int resultingSize = result.length + extra.size();
                    if (resultingSize < 0) {
                        throw (Throwable)new OutOfMemoryError("File " + $this$readBytes + " is too big to fit in memory.");
                    }
                    byte[] byArray4 = extra.getBuffer();
                    byte[] byArray5 = result;
                    boolean bl7 = false;
                    byte[] byArray6 = Arrays.copyOf(byArray5, resultingSize);
                    Intrinsics.checkNotNullExpressionValue(byArray6, "java.util.Arrays.copyOf(this, newSize)");
                    byArray2 = ArraysKt.copyInto(byArray4, byArray6, result.length, 0, extra.size());
                }
            }
            byArray = byArray2;
        } catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        } finally {
            CloseableKt.closeFinally((Closeable)object, throwable);
        }
        return byArray;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static final void writeBytes(@NotNull File $this$writeBytes, @NotNull byte[] array) {
        Intrinsics.checkNotNullParameter($this$writeBytes, "$this$writeBytes");
        Intrinsics.checkNotNullParameter(array, "array");
        Closeable closeable = new FileOutputStream($this$writeBytes);
        boolean bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            FileOutputStream it = (FileOutputStream)closeable;
            boolean bl3 = false;
            it.write(array);
            Unit unit = Unit.INSTANCE;
        } catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        } finally {
            CloseableKt.closeFinally(closeable, throwable);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static final void appendBytes(@NotNull File $this$appendBytes, @NotNull byte[] array) {
        Intrinsics.checkNotNullParameter($this$appendBytes, "$this$appendBytes");
        Intrinsics.checkNotNullParameter(array, "array");
        Closeable closeable = new FileOutputStream($this$appendBytes, true);
        boolean bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            FileOutputStream it = (FileOutputStream)closeable;
            boolean bl3 = false;
            it.write(array);
            Unit unit = Unit.INSTANCE;
        } catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        } finally {
            CloseableKt.closeFinally(closeable, throwable);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @NotNull
    public static final String readText(@NotNull File $this$readText, @NotNull Charset charset) {
        String string;
        Intrinsics.checkNotNullParameter($this$readText, "$this$readText");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Object object = $this$readText;
        boolean bl = false;
        Object object2 = object;
        boolean bl2 = false;
        object2 = new FileInputStream((File)object2);
        bl2 = false;
        object = new InputStreamReader((InputStream)object2, charset);
        bl = false;
        boolean bl3 = false;
        Throwable throwable = null;
        try {
            InputStreamReader it = (InputStreamReader)object;
            boolean bl4 = false;
            string = TextStreamsKt.readText(it);
        } catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        } finally {
            CloseableKt.closeFinally((Closeable)object, throwable);
        }
        return string;
    }

    public static /* synthetic */ String readText$default(File file, Charset charset, int n, Object object) {
        if ((n & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return FilesKt.readText(file, charset);
    }

    public static final void writeText(@NotNull File $this$writeText, @NotNull String text, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter($this$writeText, "$this$writeText");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(charset, "charset");
        String string = text;
        boolean bl = false;
        byte[] byArray = string.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(byArray, "(this as java.lang.String).getBytes(charset)");
        FilesKt.writeBytes($this$writeText, byArray);
    }

    public static /* synthetic */ void writeText$default(File file, String string, Charset charset, int n, Object object) {
        if ((n & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        FilesKt.writeText(file, string, charset);
    }

    public static final void appendText(@NotNull File $this$appendText, @NotNull String text, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter($this$appendText, "$this$appendText");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(charset, "charset");
        String string = text;
        boolean bl = false;
        byte[] byArray = string.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(byArray, "(this as java.lang.String).getBytes(charset)");
        FilesKt.appendBytes($this$appendText, byArray);
    }

    public static /* synthetic */ void appendText$default(File file, String string, Charset charset, int n, Object object) {
        if ((n & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        FilesKt.appendText(file, string, charset);
    }

    public static final void forEachBlock(@NotNull File $this$forEachBlock, @NotNull Function2<? super byte[], ? super Integer, Unit> action) {
        Intrinsics.checkNotNullParameter($this$forEachBlock, "$this$forEachBlock");
        Intrinsics.checkNotNullParameter(action, "action");
        FilesKt.forEachBlock($this$forEachBlock, 4096, action);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static final void forEachBlock(@NotNull File $this$forEachBlock, int blockSize, @NotNull Function2<? super byte[], ? super Integer, Unit> action) {
        Intrinsics.checkNotNullParameter($this$forEachBlock, "$this$forEachBlock");
        Intrinsics.checkNotNullParameter(action, "action");
        byte[] arr = new byte[RangesKt.coerceAtLeast(blockSize, 512)];
        Object object = $this$forEachBlock;
        boolean bl = false;
        object = new FileInputStream((File)object);
        bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            int size;
            FileInputStream input = (FileInputStream)object;
            boolean bl3 = false;
            while ((size = input.read(arr)) > 0) {
                action.invoke((byte[])arr, (Integer)size);
            }
            Unit unit = Unit.INSTANCE;
        } catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        } finally {
            CloseableKt.closeFinally((Closeable)object, throwable);
        }
    }

    public static final void forEachLine(@NotNull File $this$forEachLine, @NotNull Charset charset, @NotNull Function1<? super String, Unit> action) {
        Intrinsics.checkNotNullParameter($this$forEachLine, "$this$forEachLine");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(action, "action");
        TextStreamsKt.forEachLine(new BufferedReader(new InputStreamReader((InputStream)new FileInputStream($this$forEachLine), charset)), action);
    }

    public static /* synthetic */ void forEachLine$default(File file, Charset charset, Function1 function1, int n, Object object) {
        if ((n & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        FilesKt.forEachLine(file, charset, function1);
    }

    @InlineOnly
    private static final FileInputStream inputStream(File $this$inputStream) {
        int $i$f$inputStream = 0;
        return new FileInputStream($this$inputStream);
    }

    @InlineOnly
    private static final FileOutputStream outputStream(File $this$outputStream) {
        int $i$f$outputStream = 0;
        return new FileOutputStream($this$outputStream);
    }

    @NotNull
    public static final List<String> readLines(@NotNull File $this$readLines, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter($this$readLines, "$this$readLines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        ArrayList result = new ArrayList();
        FilesKt.forEachLine($this$readLines, charset, (Function1<? super String, Unit>)new Function1<String, Unit>(result){
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

    public static /* synthetic */ List readLines$default(File file, Charset charset, int n, Object object) {
        if ((n & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return FilesKt.readLines(file, charset);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static final <T> T useLines(@NotNull File $this$useLines, @NotNull Charset charset, @NotNull Function1<? super Sequence<String>, ? extends T> block) {
        int $i$f$useLines = 0;
        Intrinsics.checkNotNullParameter($this$useLines, "$this$useLines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(block, "block");
        Object object = $this$useLines;
        int n = 8192;
        boolean bl = false;
        Object object2 = object;
        boolean bl2 = false;
        Object object3 = object2;
        boolean bl3 = false;
        object3 = new FileInputStream((File)object3);
        bl3 = false;
        object2 = new InputStreamReader((InputStream)object3, charset);
        bl2 = false;
        object = object2 instanceof BufferedReader ? (BufferedReader)object2 : new BufferedReader((Reader)object2, n);
        n = 0;
        bl = false;
        Throwable throwable = null;
        try {
            BufferedReader it = (BufferedReader)object;
            boolean bl4 = false;
            object2 = block.invoke(TextStreamsKt.lineSequence(it));
        } catch (Throwable throwable2) {
            try {
                throwable = throwable2;
                throw throwable2;
            } catch (Throwable throwable3) {
                InlineMarker.finallyStart(1);
                if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
                    CloseableKt.closeFinally((Closeable)object, throwable);
                } else if (throwable == null) {
                    object.close();
                } else {
                    try {
                        object.close();
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
            CloseableKt.closeFinally((Closeable)object, throwable);
        } else {
            object.close();
        }
        InlineMarker.finallyEnd(1);
        return (T)object2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static /* synthetic */ Object useLines$default(File $this$useLines, Charset charset, Function1 block, int n, Object object) {
        if ((n & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        boolean $i$f$useLines = false;
        Intrinsics.checkNotNullParameter($this$useLines, "$this$useLines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(block, "block");
        object = $this$useLines;
        int n2 = 8192;
        boolean bl = false;
        Object object2 = object;
        boolean bl2 = false;
        Object object3 = object2;
        boolean bl3 = false;
        object3 = new FileInputStream((File)object3);
        bl3 = false;
        object2 = new InputStreamReader((InputStream)object3, charset);
        bl2 = false;
        object = object2 instanceof BufferedReader ? (BufferedReader)object2 : new BufferedReader((Reader)object2, n2);
        n2 = 0;
        bl = false;
        Throwable throwable = null;
        try {
            BufferedReader it = (BufferedReader)object;
            boolean bl4 = false;
            object2 = block.invoke(TextStreamsKt.lineSequence(it));
        } catch (Throwable throwable2) {
            try {
                throwable = throwable2;
                throw throwable2;
            } catch (Throwable throwable3) {
                InlineMarker.finallyStart(1);
                if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
                    CloseableKt.closeFinally((Closeable)object, throwable);
                } else if (throwable == null) {
                    object.close();
                } else {
                    try {
                        object.close();
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
            CloseableKt.closeFinally((Closeable)object, throwable);
        } else {
            object.close();
        }
        InlineMarker.finallyEnd(1);
        return object2;
    }
}

