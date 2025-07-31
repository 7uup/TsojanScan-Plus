/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PrimitiveSpreadBuilder;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\u0002J\f\u0010\f\u001a\u00020\u0004*\u00020\u0002H\u0014R\u000e\u0010\u0006\u001a\u00020\u0002X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2={"Lkotlin/jvm/internal/FloatSpreadBuilder;", "Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "", "size", "", "(I)V", "values", "add", "", "value", "", "toArray", "getSize", "kotlin-stdlib"})
public final class FloatSpreadBuilder
extends PrimitiveSpreadBuilder<float[]> {
    private final float[] values;

    @Override
    protected int getSize(@NotNull float[] $this$getSize) {
        Intrinsics.checkNotNullParameter($this$getSize, "$this$getSize");
        return $this$getSize.length;
    }

    public final void add(float value) {
        FloatSpreadBuilder floatSpreadBuilder = this;
        int n = floatSpreadBuilder.getPosition();
        floatSpreadBuilder.setPosition(n + 1);
        this.values[n] = value;
    }

    @NotNull
    public final float[] toArray() {
        return this.toArray(this.values, new float[this.size()]);
    }

    public FloatSpreadBuilder(int size) {
        super(size);
        this.values = new float[size];
    }
}

