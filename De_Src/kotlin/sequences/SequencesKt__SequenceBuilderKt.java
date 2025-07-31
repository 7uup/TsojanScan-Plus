/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.sequences;

import java.util.Iterator;
import kotlin.BuilderInference;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceBuilderIterator;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000R\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aN\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\u0004\b\u0000\u0010\n2/\b\t\u0010\u000b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00100\f\u00a2\u0006\u0002\b\u0011H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012\u001aN\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\n0\u0014\"\u0004\b\u0000\u0010\n2/\b\t\u0010\u000b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00100\f\u00a2\u0006\u0002\b\u0011H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015\u001aM\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\u0004\b\u0000\u0010\n2/\b\u0001\u0010\u0017\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00100\f\u00a2\u0006\u0002\b\u0011H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012\u001aM\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\n0\u0014\"\u0004\b\u0000\u0010\n2/\b\u0001\u0010\u0017\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00100\f\u00a2\u0006\u0002\b\u0011H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015\"\u0012\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002X\u0082T\u00a2\u0006\u0002\n\u0000\"\u0012\u0010\u0003\u001a\u00060\u0001j\u0002`\u0002X\u0082T\u00a2\u0006\u0002\n\u0000\"\u0012\u0010\u0004\u001a\u00060\u0001j\u0002`\u0002X\u0082T\u00a2\u0006\u0002\n\u0000\"\u0012\u0010\u0005\u001a\u00060\u0001j\u0002`\u0002X\u0082T\u00a2\u0006\u0002\n\u0000\"\u0012\u0010\u0006\u001a\u00060\u0001j\u0002`\u0002X\u0082T\u00a2\u0006\u0002\n\u0000\"\u0012\u0010\u0007\u001a\u00060\u0001j\u0002`\u0002X\u0082T\u00a2\u0006\u0002\n\u0000*V\b\u0007\u0010\u0019\u001a\u0004\b\u0000\u0010\n\"\b\u0012\u0004\u0012\u0002H\n0\r2\b\u0012\u0004\u0012\u0002H\n0\rB6\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u001c\b\u001d\u0012\u0018\b\u000bB\u0014\b\u001e\u0012\u0006\b\u001f\u0012\u0002\b\f\u0012\b\b \u0012\u0004\b\b(!\u0012\n\b\"\u0012\u0006\b\n0#8$*\f\b\u0002\u0010%\"\u00020\u00012\u00020\u0001\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006&"}, d2={"State_Done", "", "Lkotlin/sequences/State;", "State_Failed", "State_ManyNotReady", "State_ManyReady", "State_NotReady", "State_Ready", "buildIterator", "", "T", "builderAction", "Lkotlin/Function2;", "Lkotlin/sequences/SequenceScope;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;)Ljava/util/Iterator;", "buildSequence", "Lkotlin/sequences/Sequence;", "(Lkotlin/jvm/functions/Function2;)Lkotlin/sequences/Sequence;", "iterator", "block", "sequence", "SequenceBuilder", "Lkotlin/Deprecated;", "message", "Use SequenceScope class instead.", "replaceWith", "Lkotlin/ReplaceWith;", "imports", "expression", "SequenceScope<T>", "level", "Lkotlin/DeprecationLevel;", "ERROR", "State", "kotlin-stdlib"}, xs="kotlin/sequences/SequencesKt")
class SequencesKt__SequenceBuilderKt {
    private static final int State_NotReady = 0;
    private static final int State_ManyNotReady = 1;
    private static final int State_ManyReady = 2;
    private static final int State_Ready = 3;
    private static final int State_Done = 4;
    private static final int State_Failed = 5;

    @SinceKotlin(version="1.3")
    @NotNull
    public static final <T> Sequence<T> sequence(@BuilderInference @NotNull Function2<? super SequenceScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        boolean bl = false;
        return new Sequence<T>(block){
            final /* synthetic */ Function2 $block$inlined;
            {
                this.$block$inlined = function2;
            }

            @NotNull
            public Iterator<T> iterator() {
                boolean bl = false;
                return SequencesKt.iterator(this.$block$inlined);
            }
        };
    }

    @Deprecated(message="Use 'sequence { }' function instead.", replaceWith=@ReplaceWith(imports={}, expression="sequence(builderAction)"), level=DeprecationLevel.ERROR)
    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final <T> Sequence<T> buildSequence(@BuilderInference Function2<? super SequenceScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> builderAction) {
        int $i$f$buildSequence = 0;
        boolean bl = false;
        return new Sequence<T>(builderAction){
            final /* synthetic */ Function2 $builderAction$inlined;
            {
                this.$builderAction$inlined = function2;
            }

            @NotNull
            public Iterator<T> iterator() {
                boolean bl = false;
                return SequencesKt.iterator(this.$builderAction$inlined);
            }
        };
    }

    @SinceKotlin(version="1.3")
    @NotNull
    public static final <T> Iterator<T> iterator(@BuilderInference @NotNull Function2<? super SequenceScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        SequenceBuilderIterator iterator2 = new SequenceBuilderIterator();
        iterator2.setNextStep(IntrinsicsKt.createCoroutineUnintercepted(block, iterator2, iterator2));
        return iterator2;
    }

    @Deprecated(message="Use 'iterator { }' function instead.", replaceWith=@ReplaceWith(imports={}, expression="iterator(builderAction)"), level=DeprecationLevel.ERROR)
    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final <T> Iterator<T> buildIterator(@BuilderInference Function2<? super SequenceScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> builderAction) {
        int $i$f$buildIterator = 0;
        return SequencesKt.iterator(builderAction);
    }

    @Deprecated(message="Use SequenceScope class instead.", replaceWith=@ReplaceWith(imports={}, expression="SequenceScope<T>"), level=DeprecationLevel.ERROR)
    public static /* synthetic */ void SequenceBuilder$annotations() {
    }
}

