/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B+\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0007\u00a2\u0006\u0002\u0010\bJ\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0096\u0002R\u0016\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2={"Lkotlin/sequences/GeneratorSequence;", "T", "", "Lkotlin/sequences/Sequence;", "getInitialValue", "Lkotlin/Function0;", "getNextValue", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "iterator", "", "kotlin-stdlib"})
final class GeneratorSequence<T>
implements Sequence<T> {
    private final Function0<T> getInitialValue;
    private final Function1<T, T> getNextValue;

    @Override
    @NotNull
    public Iterator<T> iterator() {
        return new Iterator<T>(this){
            @Nullable
            private T nextItem;
            private int nextState;
            final /* synthetic */ GeneratorSequence this$0;

            @Nullable
            public final T getNextItem() {
                return this.nextItem;
            }

            public final void setNextItem(@Nullable T t) {
                this.nextItem = t;
            }

            public final int getNextState() {
                return this.nextState;
            }

            public final void setNextState(int n) {
                this.nextState = n;
            }

            private final void calcNext() {
                R r;
                if (this.nextState == -2) {
                    r = GeneratorSequence.access$getGetInitialValue$p(this.this$0).invoke();
                } else {
                    Function1 function1 = GeneratorSequence.access$getGetNextValue$p(this.this$0);
                    T t = this.nextItem;
                    Intrinsics.checkNotNull(t);
                    r = function1.invoke(t);
                }
                this.nextItem = r;
                this.nextState = this.nextItem == null ? 0 : 1;
            }

            @NotNull
            public T next() {
                if (this.nextState < 0) {
                    this.calcNext();
                }
                if (this.nextState == 0) {
                    throw (Throwable)new NoSuchElementException();
                }
                T t = this.nextItem;
                if (t == null) {
                    throw new NullPointerException("null cannot be cast to non-null type T");
                }
                T result = t;
                this.nextState = -1;
                return result;
            }

            public boolean hasNext() {
                if (this.nextState < 0) {
                    this.calcNext();
                }
                return this.nextState == 1;
            }
            {
                this.this$0 = this$0;
                this.nextState = -2;
            }

            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }
        };
    }

    public GeneratorSequence(@NotNull Function0<? extends T> getInitialValue, @NotNull Function1<? super T, ? extends T> getNextValue) {
        Intrinsics.checkNotNullParameter(getInitialValue, "getInitialValue");
        Intrinsics.checkNotNullParameter(getNextValue, "getNextValue");
        this.getInitialValue = getInitialValue;
        this.getNextValue = getNextValue;
    }

    public static final /* synthetic */ Function0 access$getGetInitialValue$p(GeneratorSequence $this) {
        return $this.getInitialValue;
    }

    public static final /* synthetic */ Function1 access$getGetNextValue$p(GeneratorSequence $this) {
        return $this.getNextValue;
    }
}

