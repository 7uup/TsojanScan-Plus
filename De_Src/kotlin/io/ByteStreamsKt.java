/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.NoSuchElementException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.collections.ByteIterator;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000Z\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0017\u0010\u0000\u001a\u00020\u0005*\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0017\u0010\u0007\u001a\u00020\b*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a\u0017\u0010\u000b\u001a\u00020\f*\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a\u0017\u0010\r\u001a\u00020\u000e*\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a\u001c\u0010\u0010\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u001a\r\u0010\u0013\u001a\u00020\u000e*\u00020\u0014H\u0087\b\u001a\u001d\u0010\u0013\u001a\u00020\u000e*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\u0087\b\u001a\r\u0010\u0017\u001a\u00020\u0018*\u00020\u0001H\u0086\u0002\u001a\f\u0010\u0019\u001a\u00020\u0014*\u00020\u0002H\u0007\u001a\u0016\u0010\u0019\u001a\u00020\u0014*\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u0004H\u0007\u001a\u0017\u0010\u001b\u001a\u00020\u001c*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a\u0017\u0010\u001d\u001a\u00020\u001e*\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u00a8\u0006\u001f"}, d2={"buffered", "Ljava/io/BufferedInputStream;", "Ljava/io/InputStream;", "bufferSize", "", "Ljava/io/BufferedOutputStream;", "Ljava/io/OutputStream;", "bufferedReader", "Ljava/io/BufferedReader;", "charset", "Ljava/nio/charset/Charset;", "bufferedWriter", "Ljava/io/BufferedWriter;", "byteInputStream", "Ljava/io/ByteArrayInputStream;", "", "copyTo", "", "out", "inputStream", "", "offset", "length", "iterator", "Lkotlin/collections/ByteIterator;", "readBytes", "estimatedSize", "reader", "Ljava/io/InputStreamReader;", "writer", "Ljava/io/OutputStreamWriter;", "kotlin-stdlib"})
@JvmName(name="ByteStreamsKt")
public final class ByteStreamsKt {
    @NotNull
    public static final ByteIterator iterator(@NotNull BufferedInputStream $this$iterator) {
        Intrinsics.checkNotNullParameter($this$iterator, "$this$iterator");
        return new ByteIterator($this$iterator){
            private int nextByte;
            private boolean nextPrepared;
            private boolean finished;
            final /* synthetic */ BufferedInputStream $this_iterator;

            public final int getNextByte() {
                return this.nextByte;
            }

            public final void setNextByte(int n) {
                this.nextByte = n;
            }

            public final boolean getNextPrepared() {
                return this.nextPrepared;
            }

            public final void setNextPrepared(boolean bl) {
                this.nextPrepared = bl;
            }

            public final boolean getFinished() {
                return this.finished;
            }

            public final void setFinished(boolean bl) {
                this.finished = bl;
            }

            private final void prepareNext() {
                if (!this.nextPrepared && !this.finished) {
                    this.nextByte = this.$this_iterator.read();
                    this.nextPrepared = true;
                    this.finished = this.nextByte == -1;
                }
            }

            public boolean hasNext() {
                this.prepareNext();
                return !this.finished;
            }

            public byte nextByte() {
                this.prepareNext();
                if (this.finished) {
                    throw (Throwable)new NoSuchElementException("Input stream is over.");
                }
                byte res = (byte)this.nextByte;
                this.nextPrepared = false;
                return res;
            }
            {
                this.$this_iterator = $receiver;
                this.nextByte = -1;
            }
        };
    }

    @InlineOnly
    private static final ByteArrayInputStream byteInputStream(String $this$byteInputStream, Charset charset) {
        int $i$f$byteInputStream = 0;
        String string = $this$byteInputStream;
        boolean bl = false;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] byArray = string2.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(byArray, "(this as java.lang.String).getBytes(charset)");
        return new ByteArrayInputStream(byArray);
    }

    static /* synthetic */ ByteArrayInputStream byteInputStream$default(String $this$byteInputStream, Charset charset, int n, Object object) {
        if ((n & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        boolean $i$f$byteInputStream = false;
        object = $this$byteInputStream;
        boolean bl = false;
        Object object2 = object;
        if (object2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] byArray = ((String)object2).getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(byArray, "(this as java.lang.String).getBytes(charset)");
        return new ByteArrayInputStream(byArray);
    }

    @InlineOnly
    private static final ByteArrayInputStream inputStream(byte[] $this$inputStream) {
        int $i$f$inputStream = 0;
        return new ByteArrayInputStream($this$inputStream);
    }

    @InlineOnly
    private static final ByteArrayInputStream inputStream(byte[] $this$inputStream, int offset, int length) {
        int $i$f$inputStream = 0;
        return new ByteArrayInputStream($this$inputStream, offset, length);
    }

    @InlineOnly
    private static final BufferedInputStream buffered(InputStream $this$buffered, int bufferSize) {
        int $i$f$buffered = 0;
        return $this$buffered instanceof BufferedInputStream ? (BufferedInputStream)$this$buffered : new BufferedInputStream($this$buffered, bufferSize);
    }

    static /* synthetic */ BufferedInputStream buffered$default(InputStream $this$buffered, int bufferSize, int n, Object object) {
        if ((n & 1) != 0) {
            bufferSize = 8192;
        }
        boolean $i$f$buffered = false;
        return $this$buffered instanceof BufferedInputStream ? (BufferedInputStream)$this$buffered : new BufferedInputStream($this$buffered, bufferSize);
    }

    @InlineOnly
    private static final InputStreamReader reader(InputStream $this$reader, Charset charset) {
        int $i$f$reader = 0;
        return new InputStreamReader($this$reader, charset);
    }

    static /* synthetic */ InputStreamReader reader$default(InputStream $this$reader, Charset charset, int n, Object object) {
        if ((n & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        boolean $i$f$reader = false;
        return new InputStreamReader($this$reader, charset);
    }

    @InlineOnly
    private static final BufferedReader bufferedReader(InputStream $this$bufferedReader, Charset charset) {
        int $i$f$bufferedReader = 0;
        Closeable closeable = $this$bufferedReader;
        int n = 0;
        closeable = new InputStreamReader((InputStream)closeable, charset);
        n = 8192;
        boolean bl = false;
        return closeable instanceof BufferedReader ? (BufferedReader)closeable : new BufferedReader((Reader)closeable, n);
    }

    static /* synthetic */ BufferedReader bufferedReader$default(InputStream $this$bufferedReader, Charset charset, int n, Object object) {
        if ((n & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        boolean $i$f$bufferedReader = false;
        object = $this$bufferedReader;
        int n2 = 0;
        object = new InputStreamReader((InputStream)object, charset);
        n2 = 8192;
        boolean bl = false;
        return object instanceof BufferedReader ? (BufferedReader)object : new BufferedReader((Reader)object, n2);
    }

    @InlineOnly
    private static final BufferedOutputStream buffered(OutputStream $this$buffered, int bufferSize) {
        int $i$f$buffered = 0;
        return $this$buffered instanceof BufferedOutputStream ? (BufferedOutputStream)$this$buffered : new BufferedOutputStream($this$buffered, bufferSize);
    }

    static /* synthetic */ BufferedOutputStream buffered$default(OutputStream $this$buffered, int bufferSize, int n, Object object) {
        if ((n & 1) != 0) {
            bufferSize = 8192;
        }
        boolean $i$f$buffered = false;
        return $this$buffered instanceof BufferedOutputStream ? (BufferedOutputStream)$this$buffered : new BufferedOutputStream($this$buffered, bufferSize);
    }

    @InlineOnly
    private static final OutputStreamWriter writer(OutputStream $this$writer, Charset charset) {
        int $i$f$writer = 0;
        return new OutputStreamWriter($this$writer, charset);
    }

    static /* synthetic */ OutputStreamWriter writer$default(OutputStream $this$writer, Charset charset, int n, Object object) {
        if ((n & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        boolean $i$f$writer = false;
        return new OutputStreamWriter($this$writer, charset);
    }

    @InlineOnly
    private static final BufferedWriter bufferedWriter(OutputStream $this$bufferedWriter, Charset charset) {
        int $i$f$bufferedWriter = 0;
        Closeable closeable = $this$bufferedWriter;
        int n = 0;
        closeable = new OutputStreamWriter((OutputStream)closeable, charset);
        n = 8192;
        boolean bl = false;
        return closeable instanceof BufferedWriter ? (BufferedWriter)closeable : new BufferedWriter((Writer)closeable, n);
    }

    static /* synthetic */ BufferedWriter bufferedWriter$default(OutputStream $this$bufferedWriter, Charset charset, int n, Object object) {
        if ((n & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        boolean $i$f$bufferedWriter = false;
        object = $this$bufferedWriter;
        int n2 = 0;
        object = new OutputStreamWriter((OutputStream)object, charset);
        n2 = 8192;
        boolean bl = false;
        return object instanceof BufferedWriter ? (BufferedWriter)object : new BufferedWriter((Writer)object, n2);
    }

    public static final long copyTo(@NotNull InputStream $this$copyTo, @NotNull OutputStream out, int bufferSize) {
        Intrinsics.checkNotNullParameter($this$copyTo, "$this$copyTo");
        Intrinsics.checkNotNullParameter(out, "out");
        long bytesCopied = 0L;
        byte[] buffer = new byte[bufferSize];
        int bytes = $this$copyTo.read(buffer);
        while (bytes >= 0) {
            out.write(buffer, 0, bytes);
            bytesCopied += (long)bytes;
            bytes = $this$copyTo.read(buffer);
        }
        return bytesCopied;
    }

    public static /* synthetic */ long copyTo$default(InputStream inputStream2, OutputStream outputStream2, int n, int n2, Object object) {
        if ((n2 & 2) != 0) {
            n = 8192;
        }
        return ByteStreamsKt.copyTo(inputStream2, outputStream2, n);
    }

    @Deprecated(message="Use readBytes() overload without estimatedSize parameter", replaceWith=@ReplaceWith(imports={}, expression="readBytes()"))
    @NotNull
    public static final byte[] readBytes(@NotNull InputStream $this$readBytes, int estimatedSize) {
        Intrinsics.checkNotNullParameter($this$readBytes, "$this$readBytes");
        int n = $this$readBytes.available();
        boolean bl = false;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream(Math.max(estimatedSize, n));
        ByteStreamsKt.copyTo$default($this$readBytes, buffer, 0, 2, null);
        byte[] byArray = buffer.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byArray, "buffer.toByteArray()");
        return byArray;
    }

    public static /* synthetic */ byte[] readBytes$default(InputStream inputStream2, int n, int n2, Object object) {
        if ((n2 & 1) != 0) {
            n = 8192;
        }
        return ByteStreamsKt.readBytes(inputStream2, n);
    }

    @SinceKotlin(version="1.3")
    @NotNull
    public static final byte[] readBytes(@NotNull InputStream $this$readBytes) {
        Intrinsics.checkNotNullParameter($this$readBytes, "$this$readBytes");
        int n = 8192;
        int n2 = $this$readBytes.available();
        boolean bl = false;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream(Math.max(n, n2));
        ByteStreamsKt.copyTo$default($this$readBytes, buffer, 0, 2, null);
        byte[] byArray = buffer.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byArray, "buffer.toByteArray()");
        return byArray;
    }
}

