/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0001\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0003B\u0011\b\u0016\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006B\u001b\b\u0016\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0002\u0010\tB\u0011\b\u0016\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u000b"}, d2={"Lkotlin/KotlinNothingValueException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "()V", "message", "", "(Ljava/lang/String;)V", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "(Ljava/lang/Throwable;)V", "kotlin-stdlib"})
@SinceKotlin(version="1.4")
@PublishedApi
public final class KotlinNothingValueException
extends RuntimeException {
    public KotlinNothingValueException() {
    }

    public KotlinNothingValueException(@Nullable String message) {
        super(message);
    }

    public KotlinNothingValueException(@Nullable String message, @Nullable Throwable cause) {
        super(message, cause);
    }

    public KotlinNothingValueException(@Nullable Throwable cause) {
        super(cause);
    }
}

