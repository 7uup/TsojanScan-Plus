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
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\r\u0010\u0002\u001a\u00020\u0001H\u0007\u00a2\u0006\u0002\b\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0013\u0010\u0002\u001a\u00020\u00018\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0004\u00a8\u0006\u0011"}, d2={"Lokio/ForwardingSource;", "Lokio/Source;", "delegate", "(Lokio/Source;)V", "()Lokio/Source;", "close", "", "-deprecated_delegate", "read", "", "sink", "Lokio/Buffer;", "byteCount", "timeout", "Lokio/Timeout;", "toString", "", "okio"})
public abstract class ForwardingSource
implements Source {
    @NotNull
    private final Source delegate;

    @Override
    public long read(@NotNull Buffer sink2, long byteCount) throws IOException {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        return this.delegate.read(sink2, byteCount);
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
    public final Source -deprecated_delegate() {
        return this.delegate;
    }

    @JvmName(name="delegate")
    @NotNull
    public final Source delegate() {
        return this.delegate;
    }

    public ForwardingSource(@NotNull Source delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
    }
}

