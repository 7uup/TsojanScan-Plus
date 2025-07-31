/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import kotlin.Metadata;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002J\b\u0010\u0006\u001a\u00020\u0007H&R\u0012\u0010\u0003\u001a\u00028\u0000X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u00a8\u0006\b"}, d2={"Lkotlin/Lazy;", "T", "", "value", "getValue", "()Ljava/lang/Object;", "isInitialized", "", "kotlin-stdlib"})
public interface Lazy<T> {
    public T getValue();

    public boolean isInitialized();
}

