/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CharIterator;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\t\u001a\u00020\nH\u0096\u0002J\b\u0010\u000e\u001a\u00020\u0003H\u0016R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u000f"}, d2={"Lkotlin/ranges/CharProgressionIterator;", "Lkotlin/collections/CharIterator;", "first", "", "last", "step", "", "(CCI)V", "finalElement", "hasNext", "", "next", "getStep", "()I", "nextChar", "kotlin-stdlib"})
public final class CharProgressionIterator
extends CharIterator {
    private final int finalElement;
    private boolean hasNext;
    private int next;
    private final int step;

    @Override
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override
    public char nextChar() {
        int value = this.next;
        if (value == this.finalElement) {
            if (!this.hasNext) {
                throw (Throwable)new NoSuchElementException();
            }
            this.hasNext = false;
        } else {
            this.next += this.step;
        }
        return (char)value;
    }

    public final int getStep() {
        return this.step;
    }

    public CharProgressionIterator(char first, char last, int step) {
        this.step = step;
        this.finalElement = last;
        this.hasNext = this.step > 0 ? Intrinsics.compare(first, last) <= 0 : Intrinsics.compare(first, last) >= 0;
        this.next = this.hasNext ? first : this.finalElement;
    }
}

