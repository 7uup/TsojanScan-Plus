/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.util.zip.Deflater;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.ws.MessageDeflaterKt;
import okio.Buffer;
import okio.ByteString;
import okio.DeflaterSink;
import okio.Sink;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016J\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0006J\u0014\u0010\u000f\u001a\u00020\u0003*\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2={"Lokhttp3/internal/ws/MessageDeflater;", "Ljava/io/Closeable;", "noContextTakeover", "", "(Z)V", "deflatedBytes", "Lokio/Buffer;", "deflater", "Ljava/util/zip/Deflater;", "deflaterSink", "Lokio/DeflaterSink;", "close", "", "deflate", "buffer", "endsWith", "suffix", "Lokio/ByteString;", "okhttp"})
public final class MessageDeflater
implements Closeable {
    private final Buffer deflatedBytes;
    private final Deflater deflater;
    private final DeflaterSink deflaterSink;
    private final boolean noContextTakeover;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void deflate(@NotNull Buffer buffer) throws IOException {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        boolean bl = this.deflatedBytes.size() == 0L;
        boolean bl2 = false;
        boolean bl3 = false;
        bl3 = false;
        boolean bl4 = false;
        if (!bl) {
            boolean bl5 = false;
            String string = "Failed requirement.";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        if (this.noContextTakeover) {
            this.deflater.reset();
        }
        this.deflaterSink.write(buffer, buffer.size());
        this.deflaterSink.flush();
        if (this.endsWith(this.deflatedBytes, MessageDeflaterKt.access$getEMPTY_DEFLATE_BLOCK$p())) {
            long newSize = this.deflatedBytes.size() - (long)4;
            Closeable closeable = Buffer.readAndWriteUnsafe$default(this.deflatedBytes, null, 1, null);
            bl4 = false;
            boolean bl6 = false;
            Throwable throwable = null;
            try {
                Buffer.UnsafeCursor cursor = (Buffer.UnsafeCursor)closeable;
                boolean bl7 = false;
                long l = cursor.resizeBuffer(newSize);
            } catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            } finally {
                CloseableKt.closeFinally(closeable, throwable);
            }
        } else {
            this.deflatedBytes.writeByte(0);
        }
        buffer.write(this.deflatedBytes, this.deflatedBytes.size());
    }

    @Override
    public void close() throws IOException {
        this.deflaterSink.close();
    }

    private final boolean endsWith(Buffer $this$endsWith, ByteString suffix) {
        return $this$endsWith.rangeEquals($this$endsWith.size() - (long)suffix.size(), suffix);
    }

    public MessageDeflater(boolean noContextTakeover) {
        this.noContextTakeover = noContextTakeover;
        this.deflatedBytes = new Buffer();
        this.deflater = new Deflater(-1, true);
        this.deflaterSink = new DeflaterSink((Sink)this.deflatedBytes, this.deflater);
    }
}

