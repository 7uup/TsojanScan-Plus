/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.sequences;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.internal.InlineOnly;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.sequences.ConstrainedOnceSequence;
import kotlin.sequences.EmptySequence;
import kotlin.sequences.FlatteningSequence;
import kotlin.sequences.GeneratorSequence;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import kotlin.sequences.SequencesKt__SequencesJVMKt;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.sequences.TransformingSequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u001c\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\u001a.\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0014\b\u0004\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004H\u0087\b\u00f8\u0001\u0000\u001a\u0012\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002\u001ab\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\b0\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\t\"\u0004\b\u0002\u0010\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\f2\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u0002H\t\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00050\u000eH\u0000\u001a&\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00102\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0004\u001a<\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00102\u000e\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u00042\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u000e\u001a=\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u0001H\u00022\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u000eH\u0007\u00a2\u0006\u0002\u0010\u0014\u001a+\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0017\"\u0002H\u0002\u00a2\u0006\u0002\u0010\u0018\u001a\u001c\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0005\u001a\u001c\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001\u001aC\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\b0\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\b*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00050\u000eH\u0002\u00a2\u0006\u0002\b\u001c\u001a)\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u001d0\u0001H\u0007\u00a2\u0006\u0002\b\u001e\u001a\"\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0001\u001a2\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0004H\u0007\u001a!\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001H\u0087\b\u001a\u001e\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0007\u001a&\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010#\u001a\u00020$H\u0007\u001a@\u0010%\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020'\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0'0&\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\b*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\b0&0\u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006("}, d2={"Sequence", "Lkotlin/sequences/Sequence;", "T", "iterator", "Lkotlin/Function0;", "", "emptySequence", "flatMapIndexed", "R", "C", "source", "transform", "Lkotlin/Function2;", "", "Lkotlin/Function1;", "generateSequence", "", "nextFunction", "seedFunction", "seed", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Lkotlin/sequences/Sequence;", "sequenceOf", "elements", "", "([Ljava/lang/Object;)Lkotlin/sequences/Sequence;", "asSequence", "constrainOnce", "flatten", "flatten$SequencesKt__SequencesKt", "", "flattenSequenceOfIterable", "ifEmpty", "defaultValue", "orEmpty", "shuffled", "random", "Lkotlin/random/Random;", "unzip", "Lkotlin/Pair;", "", "kotlin-stdlib"}, xs="kotlin/sequences/SequencesKt")
class SequencesKt__SequencesKt
extends SequencesKt__SequencesJVMKt {
    @InlineOnly
    private static final <T> Sequence<T> Sequence(Function0<? extends Iterator<? extends T>> iterator2) {
        int $i$f$Sequence = 0;
        return new Sequence<T>(iterator2){
            final /* synthetic */ Function0 $iterator;

            @NotNull
            public Iterator<T> iterator() {
                return (Iterator)this.$iterator.invoke();
            }
            {
                this.$iterator = $captured_local_variable$0;
            }
        };
    }

    @NotNull
    public static final <T> Sequence<T> asSequence(@NotNull Iterator<? extends T> $this$asSequence) {
        Intrinsics.checkNotNullParameter($this$asSequence, "$this$asSequence");
        boolean bl = false;
        return SequencesKt.constrainOnce(new Sequence<T>($this$asSequence){
            final /* synthetic */ Iterator $this_asSequence$inlined;
            {
                this.$this_asSequence$inlined = iterator2;
            }

            @NotNull
            public Iterator<T> iterator() {
                boolean bl = false;
                return this.$this_asSequence$inlined;
            }
        });
    }

    @NotNull
    public static final <T> Sequence<T> sequenceOf(@NotNull T ... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        T[] TArray = elements;
        boolean bl = false;
        return TArray.length == 0 ? SequencesKt.emptySequence() : ArraysKt.asSequence(elements);
    }

    @NotNull
    public static final <T> Sequence<T> emptySequence() {
        return EmptySequence.INSTANCE;
    }

    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final <T> Sequence<T> orEmpty(Sequence<? extends T> $this$orEmpty) {
        int $i$f$orEmpty = 0;
        Sequence<Object> sequence = $this$orEmpty;
        if (sequence == null) {
            sequence = SequencesKt.emptySequence();
        }
        return sequence;
    }

    @SinceKotlin(version="1.3")
    @NotNull
    public static final <T> Sequence<T> ifEmpty(@NotNull Sequence<? extends T> $this$ifEmpty, @NotNull Function0<? extends Sequence<? extends T>> defaultValue) {
        Intrinsics.checkNotNullParameter($this$ifEmpty, "$this$ifEmpty");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return SequencesKt.sequence(new Function2<SequenceScope<? super T>, Continuation<? super Unit>, Object>($this$ifEmpty, defaultValue, null){
            private SequenceScope p$;
            Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ Sequence $this_ifEmpty;
            final /* synthetic */ Function0 $defaultValue;

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
                Iterator<T> iterator22;
                SequenceScope $this$sequence2;
                Object object = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0: {
                        ResultKt.throwOnFailure($result);
                        $this$sequence2 = this.p$;
                        iterator22 = this.$this_ifEmpty.iterator();
                        if (!iterator22.hasNext()) break;
                        this.L$0 = $this$sequence2;
                        this.L$1 = iterator22;
                        this.label = 1;
                        Object object2 = $this$sequence2.yieldAll(iterator22, (Continuation<Unit>)this);
                        if (object2 != object) return Unit.INSTANCE;
                        return object;
                    }
                    case 1: {
                        Iterator iterator22 = (Iterator)this.L$1;
                        SequenceScope $this$sequence2 = (SequenceScope)this.L$0;
                        ResultKt.throwOnFailure($result);
                        Object object2 = $result;
                        return Unit.INSTANCE;
                    }
                }
                this.L$0 = $this$sequence2;
                this.L$1 = iterator22;
                this.label = 2;
                Object object3 = $this$sequence2.yieldAll((Sequence)this.$defaultValue.invoke(), (Continuation<Unit>)this);
                if (object3 != object) return Unit.INSTANCE;
                return object;
                {
                    case 2: {
                        Iterator iterator22 = (Iterator)this.L$1;
                        $this$sequence2 = (SequenceScope)this.L$0;
                        ResultKt.throwOnFailure($result);
                        object3 = $result;
                        return Unit.INSTANCE;
                    }
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            {
                this.$this_ifEmpty = sequence;
                this.$defaultValue = function0;
                super(2, continuation);
            }

            @NotNull
            public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> completion) {
                Intrinsics.checkNotNullParameter(completion, "completion");
                Function2<SequenceScope<? super T>, Continuation<? super Unit>, Object> function2 = new /* invalid duplicate definition of identical inner class */;
                SequenceScope sequenceScope = function2.p$ = (SequenceScope)value;
                return function2;
            }

            public final Object invoke(Object object, Object object2) {
                return (this.create(object, (Continuation)object2)).invokeSuspend(Unit.INSTANCE);
            }
        });
    }

    @NotNull
    public static final <T> Sequence<T> flatten(@NotNull Sequence<? extends Sequence<? extends T>> $this$flatten) {
        Intrinsics.checkNotNullParameter($this$flatten, "$this$flatten");
        return SequencesKt__SequencesKt.flatten$SequencesKt__SequencesKt($this$flatten, flatten.1.INSTANCE);
    }

    @JvmName(name="flattenSequenceOfIterable")
    @NotNull
    public static final <T> Sequence<T> flattenSequenceOfIterable(@NotNull Sequence<? extends Iterable<? extends T>> $this$flatten) {
        Intrinsics.checkNotNullParameter($this$flatten, "$this$flatten");
        return SequencesKt__SequencesKt.flatten$SequencesKt__SequencesKt($this$flatten, flatten.2.INSTANCE);
    }

    private static final <T, R> Sequence<R> flatten$SequencesKt__SequencesKt(Sequence<? extends T> $this$flatten, Function1<? super T, ? extends Iterator<? extends R>> iterator2) {
        if ($this$flatten instanceof TransformingSequence) {
            return ((TransformingSequence)$this$flatten).flatten$kotlin_stdlib(iterator2);
        }
        return new FlatteningSequence($this$flatten, flatten.3.INSTANCE, iterator2);
    }

    @NotNull
    public static final <T, R> Pair<List<T>, List<R>> unzip(@NotNull Sequence<? extends Pair<? extends T, ? extends R>> $this$unzip) {
        Intrinsics.checkNotNullParameter($this$unzip, "$this$unzip");
        ArrayList<T> listT = new ArrayList<T>();
        ArrayList<R> listR = new ArrayList<R>();
        Iterator<Pair<T, R>> iterator2 = $this$unzip.iterator();
        while (iterator2.hasNext()) {
            Pair<T, R> pair = iterator2.next();
            listT.add(pair.getFirst());
            listR.add(pair.getSecond());
        }
        return TuplesKt.to(listT, listR);
    }

    @SinceKotlin(version="1.4")
    @NotNull
    public static final <T> Sequence<T> shuffled(@NotNull Sequence<? extends T> $this$shuffled) {
        Intrinsics.checkNotNullParameter($this$shuffled, "$this$shuffled");
        return SequencesKt.shuffled($this$shuffled, Random.Default);
    }

    @SinceKotlin(version="1.4")
    @NotNull
    public static final <T> Sequence<T> shuffled(@NotNull Sequence<? extends T> $this$shuffled, @NotNull Random random) {
        Intrinsics.checkNotNullParameter($this$shuffled, "$this$shuffled");
        Intrinsics.checkNotNullParameter(random, "random");
        return SequencesKt.sequence(new Function2<SequenceScope<? super T>, Continuation<? super Unit>, Object>($this$shuffled, random, null){
            private SequenceScope p$;
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            int I$0;
            int label;
            final /* synthetic */ Sequence $this_shuffled;
            final /* synthetic */ Random $random;

            /*
             * Unable to fully structure code
             */
            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
                var7_2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                block0 : switch (this.label) {
                    case 0: {
                        ResultKt.throwOnFailure($result);
                        $this$sequence = this.p$;
                        buffer = SequencesKt.toMutableList(this.$this_shuffled);
lbl7:
                        // 2 sources

                        while (true) {
                            var4_6 = buffer;
                            var5_8 = false;
                            if (!(var4_6.isEmpty() == false)) break block0;
                            j = this.$random.nextInt(buffer.size());
                            last = CollectionsKt.removeLast(buffer);
                            value = j < buffer.size() ? buffer.set(j, last) : last;
                            this.L$0 = $this$sequence;
                            this.L$1 = buffer;
                            this.I$0 = j;
                            this.L$2 = last;
                            this.L$3 = value;
                            this.label = 1;
                            v0 = $this$sequence.yield(value, this);
                            if (v0 != var7_2) continue;
                            return var7_2;
                        }
                    }
                    case 1: {
                        value = this.L$3;
                        last = this.L$2;
                        j = this.I$0;
                        buffer = (List)this.L$1;
                        $this$sequence = (SequenceScope)this.L$0;
                        ResultKt.throwOnFailure($result);
                        v0 = $result;
                        ** continue;
                    }
                }
                return Unit.INSTANCE;
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            {
                this.$this_shuffled = sequence;
                this.$random = random;
                super(2, continuation);
            }

            @NotNull
            public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> completion) {
                Intrinsics.checkNotNullParameter(completion, "completion");
                Function2<SequenceScope<? super T>, Continuation<? super Unit>, Object> function2 = new /* invalid duplicate definition of identical inner class */;
                SequenceScope sequenceScope = function2.p$ = (SequenceScope)value;
                return function2;
            }

            public final Object invoke(Object object, Object object2) {
                return (this.create(object, (Continuation)object2)).invokeSuspend(Unit.INSTANCE);
            }
        });
    }

    @NotNull
    public static final <T, C, R> Sequence<R> flatMapIndexed(@NotNull Sequence<? extends T> source2, @NotNull Function2<? super Integer, ? super T, ? extends C> transform, @NotNull Function1<? super C, ? extends Iterator<? extends R>> iterator2) {
        Intrinsics.checkNotNullParameter(source2, "source");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Intrinsics.checkNotNullParameter(iterator2, "iterator");
        return SequencesKt.sequence(new Function2<SequenceScope<? super R>, Continuation<? super Unit>, Object>(source2, transform, iterator2, null){
            private SequenceScope p$;
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            int I$0;
            int label;
            final /* synthetic */ Sequence $source;
            final /* synthetic */ Function2 $transform;
            final /* synthetic */ Function1 $iterator;

            /*
             * Unable to fully structure code
             */
            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
                var9_2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0: {
                        ResultKt.throwOnFailure($result);
                        $this$sequence = this.p$;
                        index = 0;
                        var5_5 = this.$source.iterator();
lbl8:
                        // 3 sources

                        while (var5_5.hasNext()) {
                            element = var5_5.next();
                            var7_8 = index++;
                            var8_9 = false;
                            if (var7_8 < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            result = this.$transform.invoke(Boxing.boxInt(var7_8), element);
                            this.L$0 = $this$sequence;
                            this.I$0 = index;
                            this.L$1 = element;
                            this.L$2 = var5_5;
                            this.L$3 = result;
                            this.label = 1;
                            v0 = $this$sequence.yieldAll((Iterator)this.$iterator.invoke(result), (Continuation<Unit>)this);
                            if (v0 != var9_2) continue;
                            return var9_2;
                        }
                        break;
                    }
                    case 1: {
                        result = this.L$3;
                        var5_5 = (Iterator<T>)this.L$2;
                        element = this.L$1;
                        index = this.I$0;
                        $this$sequence = (SequenceScope)this.L$0;
                        ResultKt.throwOnFailure($result);
                        v0 = $result;
                        ** GOTO lbl8
                    }
                }
                return Unit.INSTANCE;
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            {
                this.$source = sequence;
                this.$transform = function2;
                this.$iterator = function1;
                super(2, continuation);
            }

            @NotNull
            public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> completion) {
                Intrinsics.checkNotNullParameter(completion, "completion");
                Function2<SequenceScope<? super R>, Continuation<? super Unit>, Object> function2 = new /* invalid duplicate definition of identical inner class */;
                SequenceScope sequenceScope = function2.p$ = (SequenceScope)value;
                return function2;
            }

            public final Object invoke(Object object, Object object2) {
                return (this.create(object, (Continuation)object2)).invokeSuspend(Unit.INSTANCE);
            }
        });
    }

    @NotNull
    public static final <T> Sequence<T> constrainOnce(@NotNull Sequence<? extends T> $this$constrainOnce) {
        Intrinsics.checkNotNullParameter($this$constrainOnce, "$this$constrainOnce");
        return $this$constrainOnce instanceof ConstrainedOnceSequence ? (ConstrainedOnceSequence<Object>)$this$constrainOnce : new ConstrainedOnceSequence<T>($this$constrainOnce);
    }

    @NotNull
    public static final <T> Sequence<T> generateSequence(@NotNull Function0<? extends T> nextFunction) {
        Intrinsics.checkNotNullParameter(nextFunction, "nextFunction");
        return SequencesKt.constrainOnce((Sequence)new GeneratorSequence<T>(nextFunction, new Function1<T, T>(nextFunction){
            final /* synthetic */ Function0 $nextFunction;

            @Nullable
            public final T invoke(@NotNull T it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return (T)this.$nextFunction.invoke();
            }
            {
                this.$nextFunction = function0;
                super(1);
            }
        }));
    }

    @LowPriorityInOverloadResolution
    @NotNull
    public static final <T> Sequence<T> generateSequence(@Nullable T seed, @NotNull Function1<? super T, ? extends T> nextFunction) {
        Intrinsics.checkNotNullParameter(nextFunction, "nextFunction");
        return seed == null ? (Sequence)EmptySequence.INSTANCE : (Sequence)new GeneratorSequence<T>(new Function0<T>(seed){
            final /* synthetic */ Object $seed;

            @Nullable
            public final T invoke() {
                return (T)this.$seed;
            }
            {
                this.$seed = object;
                super(0);
            }
        }, nextFunction);
    }

    @NotNull
    public static final <T> Sequence<T> generateSequence(@NotNull Function0<? extends T> seedFunction, @NotNull Function1<? super T, ? extends T> nextFunction) {
        Intrinsics.checkNotNullParameter(seedFunction, "seedFunction");
        Intrinsics.checkNotNullParameter(nextFunction, "nextFunction");
        return new GeneratorSequence<T>(seedFunction, nextFunction);
    }
}

