/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.EmptyIterator;
import kotlin.collections.RingBuffer;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0000\u001aH\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00070\u0006\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\u00062\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0000\u001aD\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00070\u000e\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0000\u00a8\u0006\u000f"}, d2={"checkWindowSizeStep", "", "size", "", "step", "windowedIterator", "", "", "T", "iterator", "partialWindows", "", "reuseBuffer", "windowedSequence", "Lkotlin/sequences/Sequence;", "kotlin-stdlib"})
public final class SlidingWindowKt {
    public static final void checkWindowSizeStep(int size, int step) {
        boolean bl = size > 0 && step > 0;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = size != step ? "Both size " + size + " and step " + step + " must be greater than zero." : "size " + size + " must be greater than zero.";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
    }

    @NotNull
    public static final <T> Sequence<List<T>> windowedSequence(@NotNull Sequence<? extends T> $this$windowedSequence, int size, int step, boolean partialWindows, boolean reuseBuffer) {
        Intrinsics.checkNotNullParameter($this$windowedSequence, "$this$windowedSequence");
        SlidingWindowKt.checkWindowSizeStep(size, step);
        boolean bl = false;
        return new Sequence<List<? extends T>>($this$windowedSequence, size, step, partialWindows, reuseBuffer){
            final /* synthetic */ Sequence $this_windowedSequence$inlined;
            final /* synthetic */ int $size$inlined;
            final /* synthetic */ int $step$inlined;
            final /* synthetic */ boolean $partialWindows$inlined;
            final /* synthetic */ boolean $reuseBuffer$inlined;
            {
                this.$this_windowedSequence$inlined = sequence;
                this.$size$inlined = n;
                this.$step$inlined = n2;
                this.$partialWindows$inlined = bl;
                this.$reuseBuffer$inlined = bl2;
            }

            @NotNull
            public Iterator<List<? extends T>> iterator() {
                boolean bl = false;
                return SlidingWindowKt.windowedIterator(this.$this_windowedSequence$inlined.iterator(), this.$size$inlined, this.$step$inlined, this.$partialWindows$inlined, this.$reuseBuffer$inlined);
            }
        };
    }

    @NotNull
    public static final <T> Iterator<List<T>> windowedIterator(@NotNull Iterator<? extends T> iterator2, int size, int step, boolean partialWindows, boolean reuseBuffer) {
        Intrinsics.checkNotNullParameter(iterator2, "iterator");
        if (!iterator2.hasNext()) {
            return EmptyIterator.INSTANCE;
        }
        return SequencesKt.iterator(new Function2<SequenceScope<? super List<? extends T>>, Continuation<? super Unit>, Object>(size, step, iterator2, reuseBuffer, partialWindows, null){
            private SequenceScope p$;
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            int I$0;
            int I$1;
            int I$2;
            int label;
            final /* synthetic */ int $size;
            final /* synthetic */ int $step;
            final /* synthetic */ Iterator $iterator;
            final /* synthetic */ boolean $reuseBuffer;
            final /* synthetic */ boolean $partialWindows;

            /*
             * Unable to fully structure code
             */
            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
                var11_2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0: {
                        ResultKt.throwOnFailure($result);
                        $this$iterator = this.p$;
                        bufferInitialCapacity = RangesKt.coerceAtMost(this.$size, 1024);
                        gap = this.$step - this.$size;
                        if (gap < 0) break;
                        buffer = new ArrayList<Object>(bufferInitialCapacity);
                        skip = 0;
                        var9_19 = this.$iterator;
                        var10_21 = false;
                        var8_22 = var9_19;
lbl14:
                        // 4 sources

                        while (var8_22.hasNext()) {
                            e = var8_22.next();
                            if (skip > 0) {
                                --skip;
                                continue;
                            }
                            buffer.add(e);
                            if (buffer.size() != this.$size) continue;
                            this.L$0 = $this$iterator;
                            this.I$0 = bufferInitialCapacity;
                            this.I$1 = gap;
                            this.L$1 = buffer;
                            this.I$2 = skip;
                            this.L$2 = e;
                            this.L$3 = var8_22;
                            this.label = 1;
                            v0 = $this$iterator.yield(buffer, this);
                            if (v0 == var11_2) {
                                return var11_2;
                            }
                            ** GOTO lbl45
                        }
                        break;
                    }
                    case 1: {
                        var8_22 = (Iterator)this.L$3;
                        e = this.L$2;
                        skip = this.I$2;
                        buffer = (ArrayList<Object>)this.L$1;
                        gap = this.I$1;
                        bufferInitialCapacity = this.I$0;
                        $this$iterator = (SequenceScope)this.L$0;
                        ResultKt.throwOnFailure($result);
                        v0 = $result;
lbl45:
                        // 2 sources

                        if (this.$reuseBuffer) {
                            buffer.clear();
                        } else {
                            buffer = new ArrayList<E>(this.$size);
                        }
                        skip = gap;
                        ** GOTO lbl14
                    }
                }
                var7_25 = buffer;
                var8_23 = false;
                if (var7_25.isEmpty() == false && (this.$partialWindows || buffer.size() == this.$size)) {
                    this.L$0 = $this$iterator;
                    this.I$0 = bufferInitialCapacity;
                    this.I$1 = gap;
                    this.L$1 = buffer;
                    this.I$2 = skip;
                    this.label = 2;
                    v1 = $this$iterator.yield(buffer, this);
                    if (v1 == var11_2) {
                        return var11_2;
                    }
                }
                ** GOTO lbl147
                {
                    case 2: {
                        skip = this.I$2;
                        buffer = (ArrayList)this.L$1;
                        gap = this.I$1;
                        bufferInitialCapacity = this.I$0;
                        $this$iterator = (SequenceScope)this.L$0;
                        ResultKt.throwOnFailure($result);
                        v1 = $result;
                        ** GOTO lbl147
                    }
                }
                buffer = new RingBuffer<Object>(bufferInitialCapacity);
                var8_24 = this.$iterator;
                var9_20 = false;
                var7_26 = var8_24;
lbl77:
                // 4 sources

                while (var7_26.hasNext()) {
                    e = var7_26.next();
                    buffer.add(e);
                    if (!buffer.isFull()) continue;
                    if (buffer.size() < this.$size) {
                        buffer = buffer.expanded(this.$size);
                        continue;
                    }
                    this.L$0 = $this$iterator;
                    this.I$0 = bufferInitialCapacity;
                    this.I$1 = gap;
                    this.L$1 = buffer;
                    this.L$2 = e;
                    this.L$3 = var7_26;
                    this.label = 3;
                    v2 = $this$iterator.yield(this.$reuseBuffer != false ? (List)buffer : (List)new ArrayList<E>((Collection)buffer), this);
                    if (v2 == var11_2) {
                        return var11_2;
                    }
                    ** GOTO lbl105
                }
                {
                    break;
                    case 3: {
                        var7_26 = (Iterator)this.L$3;
                        e = this.L$2;
                        buffer = (RingBuffer)this.L$1;
                        gap = this.I$1;
                        bufferInitialCapacity = this.I$0;
                        $this$iterator = (SequenceScope)this.L$0;
                        ResultKt.throwOnFailure($result);
                        v2 = $result;
lbl105:
                        // 2 sources

                        buffer.removeFirst(this.$step);
                        ** GOTO lbl77
                    }
                }
                if (!this.$partialWindows) ** GOTO lbl147
lbl108:
                // 2 sources

                while (buffer.size() > this.$step) {
                    this.L$0 = $this$iterator;
                    this.I$0 = bufferInitialCapacity;
                    this.I$1 = gap;
                    this.L$1 = buffer;
                    this.label = 4;
                    v3 = $this$iterator.yield(this.$reuseBuffer != false ? (List)buffer : (List)new ArrayList<E>((Collection)buffer), this);
                    if (v3 == var11_2) {
                        return var11_2;
                    }
                    ** GOTO lbl126
                }
                {
                    break;
                    case 4: {
                        buffer = (RingBuffer<Object>)this.L$1;
                        gap = this.I$1;
                        bufferInitialCapacity = this.I$0;
                        $this$iterator = (SequenceScope)this.L$0;
                        ResultKt.throwOnFailure($result);
                        v3 = $result;
lbl126:
                        // 2 sources

                        buffer.removeFirst(this.$step);
                        ** GOTO lbl108
                    }
                }
                var6_18 = buffer;
                var7_27 = false;
                if (var6_18.isEmpty() == false) {
                    this.L$0 = $this$iterator;
                    this.I$0 = bufferInitialCapacity;
                    this.I$1 = gap;
                    this.L$1 = buffer;
                    this.label = 5;
                    v4 = $this$iterator.yield(buffer, this);
                    if (v4 == var11_2) {
                        return var11_2;
                    }
                }
                ** GOTO lbl147
                {
                    case 5: {
                        buffer = (RingBuffer)this.L$1;
                        gap = this.I$1;
                        bufferInitialCapacity = this.I$0;
                        $this$iterator = (SequenceScope)this.L$0;
                        ResultKt.throwOnFailure($result);
                        v4 = $result;
lbl147:
                        // 5 sources

                        return Unit.INSTANCE;
                    }
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            {
                this.$size = n;
                this.$step = n2;
                this.$iterator = iterator2;
                this.$reuseBuffer = bl;
                this.$partialWindows = bl2;
                super(2, continuation);
            }

            @NotNull
            public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> completion) {
                Intrinsics.checkNotNullParameter(completion, "completion");
                Function2<SequenceScope<? super List<? extends T>>, Continuation<? super Unit>, Object> function2 = new /* invalid duplicate definition of identical inner class */;
                SequenceScope sequenceScope = function2.p$ = (SequenceScope)value;
                return function2;
            }

            public final Object invoke(Object object, Object object2) {
                return (this.create(object, (Continuation)object2)).invokeSuspend(Unit.INSTANCE);
            }
        });
    }
}

