/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.SequenceScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u00020\u00050\u0004B\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\t\u0010\u0018\u001a\u00020\u0019H\u0096\u0002J\u000e\u0010\u001a\u001a\u00028\u0000H\u0096\u0002\u00a2\u0006\u0002\u0010\u001bJ\r\u0010\u001c\u001a\u00028\u0000H\u0002\u00a2\u0006\u0002\u0010\u001bJ\u001e\u0010\u001d\u001a\u00020\u00052\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u001fH\u0016\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J\u0019\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00028\u0000H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010#J\u001f\u0010$\u001a\u00020\u00052\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010&R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\"\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u0004\u0018\u00018\u0000X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0012R\u0012\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006'"}, d2={"Lkotlin/sequences/SequenceBuilderIterator;", "T", "Lkotlin/sequences/SequenceScope;", "", "Lkotlin/coroutines/Continuation;", "", "()V", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "nextIterator", "nextStep", "getNextStep", "()Lkotlin/coroutines/Continuation;", "setNextStep", "(Lkotlin/coroutines/Continuation;)V", "nextValue", "Ljava/lang/Object;", "state", "", "Lkotlin/sequences/State;", "exceptionalState", "", "hasNext", "", "next", "()Ljava/lang/Object;", "nextNotReady", "resumeWith", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "yield", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "yieldAll", "iterator", "(Ljava/util/Iterator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlin-stdlib"})
final class SequenceBuilderIterator<T>
extends SequenceScope<T>
implements Iterator<T>,
Continuation<Unit>,
KMappedMarker {
    private int state;
    private T nextValue;
    private Iterator<? extends T> nextIterator;
    @Nullable
    private Continuation<? super Unit> nextStep;

    @Nullable
    public final Continuation<Unit> getNextStep() {
        return this.nextStep;
    }

    public final void setNextStep(@Nullable Continuation<? super Unit> continuation) {
        this.nextStep = continuation;
    }

    @Override
    public boolean hasNext() {
        while (true) {
            Continuation<? super Unit> step;
            switch (this.state) {
                case 0: {
                    break;
                }
                case 1: {
                    Iterator<T> iterator2 = this.nextIterator;
                    Intrinsics.checkNotNull(iterator2);
                    if (iterator2.hasNext()) {
                        this.state = 2;
                        return true;
                    }
                    this.nextIterator = null;
                    break;
                }
                case 4: {
                    return false;
                }
                case 2: 
                case 3: {
                    return true;
                }
                default: {
                    throw this.exceptionalState();
                }
            }
            this.state = 5;
            Intrinsics.checkNotNull(this.nextStep);
            this.nextStep = null;
            Continuation<? super Unit> continuation = step;
            Unit unit = Unit.INSTANCE;
            boolean bl = false;
            Result.Companion companion = Result.Companion;
            boolean bl2 = false;
            continuation.resumeWith(Result.constructor-impl(unit));
        }
    }

    @Override
    public T next() {
        switch (this.state) {
            case 0: 
            case 1: {
                return this.nextNotReady();
            }
            case 2: {
                this.state = 1;
                Iterator<T> iterator2 = this.nextIterator;
                Intrinsics.checkNotNull(iterator2);
                return iterator2.next();
            }
            case 3: {
                this.state = 0;
                T result = this.nextValue;
                this.nextValue = null;
                return result;
            }
        }
        throw this.exceptionalState();
    }

    private final T nextNotReady() {
        if (!this.hasNext()) {
            throw (Throwable)new NoSuchElementException();
        }
        return this.next();
    }

    private final Throwable exceptionalState() {
        RuntimeException runtimeException;
        switch (this.state) {
            case 4: {
                runtimeException = new NoSuchElementException();
                break;
            }
            case 5: {
                runtimeException = new IllegalStateException("Iterator has failed.");
                break;
            }
            default: {
                runtimeException = new IllegalStateException("Unexpected state of the iterator: " + this.state);
            }
        }
        return runtimeException;
    }

    @Override
    @Nullable
    public Object yield(T value, @NotNull Continuation<? super Unit> $completion) {
        this.nextValue = value;
        this.state = 3;
        Continuation<? super Unit> c = $completion;
        boolean bl = false;
        this.nextStep = c;
        Object object = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended($completion);
        }
        if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return object;
        }
        return Unit.INSTANCE;
    }

    @Override
    @Nullable
    public Object yieldAll(@NotNull Iterator<? extends T> iterator2, @NotNull Continuation<? super Unit> $completion) {
        if (!iterator2.hasNext()) {
            return Unit.INSTANCE;
        }
        this.nextIterator = iterator2;
        this.state = 2;
        Continuation<? super Unit> c = $completion;
        boolean bl = false;
        this.nextStep = c;
        Object object = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended($completion);
        }
        if (object == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return object;
        }
        return Unit.INSTANCE;
    }

    @Override
    public void resumeWith(@NotNull Object result) {
        Object object = result;
        boolean bl = false;
        ResultKt.throwOnFailure(object);
        this.state = 4;
    }

    @Override
    @NotNull
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}

