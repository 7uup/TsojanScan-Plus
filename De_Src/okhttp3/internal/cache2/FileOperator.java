/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.cache2;

import java.io.IOException;
import java.nio.channels.FileChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bJ\u001e\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2={"Lokhttp3/internal/cache2/FileOperator;", "", "fileChannel", "Ljava/nio/channels/FileChannel;", "(Ljava/nio/channels/FileChannel;)V", "read", "", "pos", "", "sink", "Lokio/Buffer;", "byteCount", "write", "source", "okhttp"})
public final class FileOperator {
    private final FileChannel fileChannel;

    public final void write(long pos, @NotNull Buffer source2, long byteCount) throws IOException {
        long bytesWritten;
        Intrinsics.checkNotNullParameter(source2, "source");
        if (byteCount < 0L || byteCount > source2.size()) {
            throw (Throwable)new IndexOutOfBoundsException();
        }
        long mutablePos = pos;
        for (long mutableByteCount = byteCount; mutableByteCount > 0L; mutableByteCount -= bytesWritten) {
            bytesWritten = this.fileChannel.transferFrom(source2, mutablePos, mutableByteCount);
            mutablePos += bytesWritten;
        }
    }

    public final void read(long pos, @NotNull Buffer sink2, long byteCount) {
        long bytesRead;
        Intrinsics.checkNotNullParameter(sink2, "sink");
        if (byteCount < 0L) {
            throw (Throwable)new IndexOutOfBoundsException();
        }
        long mutablePos = pos;
        for (long mutableByteCount = byteCount; mutableByteCount > 0L; mutableByteCount -= bytesRead) {
            bytesRead = this.fileChannel.transferTo(mutablePos, mutableByteCount, sink2);
            mutablePos += bytesRead;
        }
    }

    public FileOperator(@NotNull FileChannel fileChannel) {
        Intrinsics.checkNotNullParameter(fileChannel, "fileChannel");
        this.fileChannel = fileChannel;
    }
}

