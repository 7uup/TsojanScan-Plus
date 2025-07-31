/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import java.io.IOException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.Sink;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\r\u0010\u0002\u001a\u00020\u0001H\u0007\u00a2\u0006\u0002\b\u0007J\b\u0010\b\u001a\u00020\u0006H\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0013\u0010\u0002\u001a\u00020\u00018\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0004\u00a8\u0006\u0012"}, d2={"Lokio/ForwardingSink;", "Lokio/Sink;", "delegate", "(Lokio/Sink;)V", "()Lokio/Sink;", "close", "", "-deprecated_delegate", "flush", "timeout", "Lokio/Timeout;", "toString", "", "write", "source", "Lokio/Buffer;", "byteCount", "", "okio"})
public abstract class ForwardingSink
implements Sink {
    @NotNull
    private final Sink delegate;

    @Override
    public void write(@NotNull Buffer source2, long byteCount) throws IOException {
        Intrinsics.checkNotNullParameter(source2, "source");
        this.delegate.write(source2, byteCount);
    }

    @Override
    public void flush() throws IOException {
        this.delegate.flush();
    }

    @Override
    @NotNull
    public Timeout timeout() {
        return this.delegate.timeout();
    }

    @Override
    public void close() throws IOException {
        this.delegate.close();
    }

    @NotNull
    public String toString() {
        return this.getClass().getSimpleName() + '(' + this.delegate + ')';
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="delegate"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_delegate")
    @NotNull
    public final Sink -deprecated_delegate() {
        return this.delegate;
    }

    @JvmName(name="delegate")
    @NotNull
    public final Sink delegate() {
        return this.delegate;
    }

    public ForwardingSink(@NotNull Sink delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
    }
}

