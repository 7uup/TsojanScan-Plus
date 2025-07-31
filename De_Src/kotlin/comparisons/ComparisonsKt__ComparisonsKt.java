/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.comparisons.NaturalOrderComparator;
import kotlin.comparisons.ReverseOrderComparator;
import kotlin.comparisons.ReversedComparator;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a>\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u00022\u001a\b\u0004\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005H\u0087\b\u00f8\u0001\u0000\u001aY\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u000226\u0010\u0007\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u00050\b\"\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005\u00a2\u0006\u0002\u0010\t\u001aZ\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n2\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\n0\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\n`\u00032\u0014\b\u0004\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\n0\u0005H\u0087\b\u00f8\u0001\u0000\u001a>\u0010\f\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u00022\u001a\b\u0004\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005H\u0087\b\u00f8\u0001\u0000\u001aZ\u0010\f\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n2\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\n0\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\n`\u00032\u0014\b\u0004\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\n0\u0005H\u0087\b\u00f8\u0001\u0000\u001a-\u0010\r\u001a\u00020\u000e\"\f\b\u0000\u0010\u0002*\u0006\u0012\u0002\b\u00030\u00062\b\u0010\u000f\u001a\u0004\u0018\u0001H\u00022\b\u0010\u0010\u001a\u0004\u0018\u0001H\u0002\u00a2\u0006\u0002\u0010\u0011\u001aA\u0010\u0012\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u000f\u001a\u0002H\u00022\u0006\u0010\u0010\u001a\u0002H\u00022\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013\u001aY\u0010\u0012\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u000f\u001a\u0002H\u00022\u0006\u0010\u0010\u001a\u0002H\u000226\u0010\u0007\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u00050\b\"\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005\u00a2\u0006\u0002\u0010\u0014\u001a]\u0010\u0012\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n2\u0006\u0010\u000f\u001a\u0002H\u00022\u0006\u0010\u0010\u001a\u0002H\u00022\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\n0\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\n`\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\n0\u0005H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015\u001aG\u0010\u0016\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u000f\u001a\u0002H\u00022\u0006\u0010\u0010\u001a\u0002H\u00022 \u0010\u0007\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u00050\bH\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0014\u001a&\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006\u001a-\u0010\u0019\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001j\n\u0012\u0006\u0012\u0004\u0018\u0001H\u0002`\u0003\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0087\b\u001a@\u0010\u0019\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001j\n\u0012\u0006\u0012\u0004\u0018\u0001H\u0002`\u0003\"\b\b\u0000\u0010\u0002*\u00020\u001a2\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0003\u001a-\u0010\u001b\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001j\n\u0012\u0006\u0012\u0004\u0018\u0001H\u0002`\u0003\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0087\b\u001a@\u0010\u001b\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001j\n\u0012\u0006\u0012\u0004\u0018\u0001H\u0002`\u0003\"\b\b\u0000\u0010\u0002*\u00020\u001a2\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0003\u001a&\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006\u001a0\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\u001aO\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0003H\u0086\u0004\u001aR\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u001a\b\u0004\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005H\u0087\b\u00f8\u0001\u0000\u001an\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\n0\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\n`\u00032\u0014\b\u0004\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\n0\u0005H\u0087\b\u00f8\u0001\u0000\u001aR\u0010 \u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u001a\b\u0004\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005H\u0087\b\u00f8\u0001\u0000\u001an\u0010 \u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\n0\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\n`\u00032\u0014\b\u0004\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\n0\u0005H\u0087\b\u00f8\u0001\u0000\u001ap\u0010!\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u000328\b\u0004\u0010\"\u001a2\u0012\u0013\u0012\u0011H\u0002\u00a2\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u0011H\u0002\u00a2\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u000e0#H\u0087\b\u00f8\u0001\u0000\u001aO\u0010&\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u001a\u0010\u000b\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0003H\u0086\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006'"}, d2={"compareBy", "Ljava/util/Comparator;", "T", "Lkotlin/Comparator;", "selector", "Lkotlin/Function1;", "", "selectors", "", "([Lkotlin/jvm/functions/Function1;)Ljava/util/Comparator;", "K", "comparator", "compareByDescending", "compareValues", "", "a", "b", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)I", "compareValuesBy", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)I", "(Ljava/lang/Object;Ljava/lang/Object;[Lkotlin/jvm/functions/Function1;)I", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;Lkotlin/jvm/functions/Function1;)I", "compareValuesByImpl", "compareValuesByImpl$ComparisonsKt__ComparisonsKt", "naturalOrder", "nullsFirst", "", "nullsLast", "reverseOrder", "reversed", "then", "thenBy", "thenByDescending", "thenComparator", "comparison", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "thenDescending", "kotlin-stdlib"}, xs="kotlin/comparisons/ComparisonsKt")
class ComparisonsKt__ComparisonsKt {
    public static final <T> int compareValuesBy(T a, T b, @NotNull Function1<? super T, ? extends Comparable<?>> ... selectors) {
        Intrinsics.checkNotNullParameter(selectors, "selectors");
        boolean bl = selectors.length > 0;
        boolean bl2 = false;
        boolean bl3 = false;
        bl3 = false;
        boolean bl4 = false;
        if (!bl) {
            boolean bl5 = false;
            String string = "Failed requirement.";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        return ComparisonsKt__ComparisonsKt.compareValuesByImpl$ComparisonsKt__ComparisonsKt(a, b, selectors);
    }

    private static final <T> int compareValuesByImpl$ComparisonsKt__ComparisonsKt(T a, T b, Function1<? super T, ? extends Comparable<?>>[] selectors) {
        for (Function1<T, Comparable<?>> function1 : selectors) {
            Comparable<?> v2;
            Comparable<?> v1 = function1.invoke(a);
            int diff = ComparisonsKt.compareValues(v1, v2 = function1.invoke(b));
            if (diff == 0) continue;
            return diff;
        }
        return 0;
    }

    @InlineOnly
    private static final <T> int compareValuesBy(T a, T b, Function1<? super T, ? extends Comparable<?>> selector) {
        int $i$f$compareValuesBy = 0;
        return ComparisonsKt.compareValues(selector.invoke(a), selector.invoke(b));
    }

    @InlineOnly
    private static final <T, K> int compareValuesBy(T a, T b, Comparator<? super K> comparator, Function1<? super T, ? extends K> selector) {
        int $i$f$compareValuesBy = 0;
        return comparator.compare(selector.invoke(a), selector.invoke(b));
    }

    public static final <T extends Comparable<?>> int compareValues(@Nullable T a, @Nullable T b) {
        if (a == b) {
            return 0;
        }
        if (a == null) {
            return -1;
        }
        if (b == null) {
            return 1;
        }
        return a.compareTo(b);
    }

    @NotNull
    public static final <T> Comparator<T> compareBy(@NotNull Function1<? super T, ? extends Comparable<?>> ... selectors) {
        Intrinsics.checkNotNullParameter(selectors, "selectors");
        boolean bl = selectors.length > 0;
        boolean bl2 = false;
        boolean bl3 = false;
        bl3 = false;
        boolean bl4 = false;
        if (!bl) {
            boolean bl5 = false;
            String string = "Failed requirement.";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        return new Comparator<T>(selectors){
            final /* synthetic */ Function1[] $selectors;

            public final int compare(T a, T b) {
                return ComparisonsKt__ComparisonsKt.access$compareValuesByImpl(a, b, this.$selectors);
            }
            {
                this.$selectors = function1Array;
            }
        };
    }

    @InlineOnly
    private static final <T> Comparator<T> compareBy(Function1<? super T, ? extends Comparable<?>> selector) {
        int $i$f$compareBy = 0;
        return new Comparator<T>(selector){
            final /* synthetic */ Function1 $selector;

            public final int compare(T a, T b) {
                boolean bl = false;
                return ComparisonsKt.compareValues((Comparable)this.$selector.invoke(a), (Comparable)this.$selector.invoke(b));
            }
            {
                this.$selector = function1;
            }
        };
    }

    @InlineOnly
    private static final <T, K> Comparator<T> compareBy(Comparator<? super K> comparator, Function1<? super T, ? extends K> selector) {
        int $i$f$compareBy = 0;
        return new Comparator<T>(comparator, selector){
            final /* synthetic */ Comparator $comparator;
            final /* synthetic */ Function1 $selector;

            public final int compare(T a, T b) {
                Comparator comparator = this.$comparator;
                boolean bl = false;
                return comparator.compare(this.$selector.invoke(a), this.$selector.invoke(b));
            }
            {
                this.$comparator = comparator;
                this.$selector = function1;
            }
        };
    }

    @InlineOnly
    private static final <T> Comparator<T> compareByDescending(Function1<? super T, ? extends Comparable<?>> selector) {
        int $i$f$compareByDescending = 0;
        return new Comparator<T>(selector){
            final /* synthetic */ Function1 $selector;

            public final int compare(T a, T b) {
                boolean bl = false;
                return ComparisonsKt.compareValues((Comparable)this.$selector.invoke(b), (Comparable)this.$selector.invoke(a));
            }
            {
                this.$selector = function1;
            }
        };
    }

    @InlineOnly
    private static final <T, K> Comparator<T> compareByDescending(Comparator<? super K> comparator, Function1<? super T, ? extends K> selector) {
        int $i$f$compareByDescending = 0;
        return new Comparator<T>(comparator, selector){
            final /* synthetic */ Comparator $comparator;
            final /* synthetic */ Function1 $selector;

            public final int compare(T a, T b) {
                Comparator comparator = this.$comparator;
                boolean bl = false;
                return comparator.compare(this.$selector.invoke(b), this.$selector.invoke(a));
            }
            {
                this.$comparator = comparator;
                this.$selector = function1;
            }
        };
    }

    @InlineOnly
    private static final <T> Comparator<T> thenBy(Comparator<T> $this$thenBy, Function1<? super T, ? extends Comparable<?>> selector) {
        int $i$f$thenBy = 0;
        return new Comparator<T>($this$thenBy, selector){
            final /* synthetic */ Comparator $this_thenBy;
            final /* synthetic */ Function1 $selector;

            public final int compare(T a, T b) {
                int n;
                int previousCompare = this.$this_thenBy.compare(a, b);
                if (previousCompare != 0) {
                    n = previousCompare;
                } else {
                    boolean bl = false;
                    n = ComparisonsKt.compareValues((Comparable)this.$selector.invoke(a), (Comparable)this.$selector.invoke(b));
                }
                return n;
            }
            {
                this.$this_thenBy = comparator;
                this.$selector = function1;
            }
        };
    }

    @InlineOnly
    private static final <T, K> Comparator<T> thenBy(Comparator<T> $this$thenBy, Comparator<? super K> comparator, Function1<? super T, ? extends K> selector) {
        int $i$f$thenBy = 0;
        return new Comparator<T>($this$thenBy, comparator, selector){
            final /* synthetic */ Comparator $this_thenBy;
            final /* synthetic */ Comparator $comparator;
            final /* synthetic */ Function1 $selector;

            public final int compare(T a, T b) {
                int n;
                int previousCompare = this.$this_thenBy.compare(a, b);
                if (previousCompare != 0) {
                    n = previousCompare;
                } else {
                    Comparator comparator = this.$comparator;
                    boolean bl = false;
                    n = comparator.compare(this.$selector.invoke(a), this.$selector.invoke(b));
                }
                return n;
            }
            {
                this.$this_thenBy = comparator;
                this.$comparator = comparator2;
                this.$selector = function1;
            }
        };
    }

    @InlineOnly
    private static final <T> Comparator<T> thenByDescending(Comparator<T> $this$thenByDescending, Function1<? super T, ? extends Comparable<?>> selector) {
        int $i$f$thenByDescending = 0;
        return new Comparator<T>($this$thenByDescending, selector){
            final /* synthetic */ Comparator $this_thenByDescending;
            final /* synthetic */ Function1 $selector;

            public final int compare(T a, T b) {
                int n;
                int previousCompare = this.$this_thenByDescending.compare(a, b);
                if (previousCompare != 0) {
                    n = previousCompare;
                } else {
                    boolean bl = false;
                    n = ComparisonsKt.compareValues((Comparable)this.$selector.invoke(b), (Comparable)this.$selector.invoke(a));
                }
                return n;
            }
            {
                this.$this_thenByDescending = comparator;
                this.$selector = function1;
            }
        };
    }

    @InlineOnly
    private static final <T, K> Comparator<T> thenByDescending(Comparator<T> $this$thenByDescending, Comparator<? super K> comparator, Function1<? super T, ? extends K> selector) {
        int $i$f$thenByDescending = 0;
        return new Comparator<T>($this$thenByDescending, comparator, selector){
            final /* synthetic */ Comparator $this_thenByDescending;
            final /* synthetic */ Comparator $comparator;
            final /* synthetic */ Function1 $selector;

            public final int compare(T a, T b) {
                int n;
                int previousCompare = this.$this_thenByDescending.compare(a, b);
                if (previousCompare != 0) {
                    n = previousCompare;
                } else {
                    Comparator comparator = this.$comparator;
                    boolean bl = false;
                    n = comparator.compare(this.$selector.invoke(b), this.$selector.invoke(a));
                }
                return n;
            }
            {
                this.$this_thenByDescending = comparator;
                this.$comparator = comparator2;
                this.$selector = function1;
            }
        };
    }

    @InlineOnly
    private static final <T> Comparator<T> thenComparator(Comparator<T> $this$thenComparator, Function2<? super T, ? super T, Integer> comparison) {
        int $i$f$thenComparator = 0;
        return new Comparator<T>($this$thenComparator, comparison){
            final /* synthetic */ Comparator $this_thenComparator;
            final /* synthetic */ Function2 $comparison;

            public final int compare(T a, T b) {
                int previousCompare = this.$this_thenComparator.compare(a, b);
                return previousCompare != 0 ? previousCompare : ((Number)this.$comparison.invoke(a, b)).intValue();
            }
            {
                this.$this_thenComparator = comparator;
                this.$comparison = function2;
            }
        };
    }

    @NotNull
    public static final <T> Comparator<T> then(@NotNull Comparator<T> $this$then, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter($this$then, "$this$then");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return new Comparator<T>($this$then, comparator){
            final /* synthetic */ Comparator $this_then;
            final /* synthetic */ Comparator $comparator;

            public final int compare(T a, T b) {
                int previousCompare = this.$this_then.compare(a, b);
                return previousCompare != 0 ? previousCompare : this.$comparator.compare(a, b);
            }
            {
                this.$this_then = comparator;
                this.$comparator = comparator2;
            }
        };
    }

    @NotNull
    public static final <T> Comparator<T> thenDescending(@NotNull Comparator<T> $this$thenDescending, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter($this$thenDescending, "$this$thenDescending");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return new Comparator<T>($this$thenDescending, comparator){
            final /* synthetic */ Comparator $this_thenDescending;
            final /* synthetic */ Comparator $comparator;

            public final int compare(T a, T b) {
                int previousCompare = this.$this_thenDescending.compare(a, b);
                return previousCompare != 0 ? previousCompare : this.$comparator.compare(b, a);
            }
            {
                this.$this_thenDescending = comparator;
                this.$comparator = comparator2;
            }
        };
    }

    @NotNull
    public static final <T> Comparator<T> nullsFirst(@NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return new Comparator<T>(comparator){
            final /* synthetic */ Comparator $comparator;

            public final int compare(@Nullable T a, @Nullable T b) {
                return a == b ? 0 : (a == null ? -1 : (b == null ? 1 : this.$comparator.compare(a, b)));
            }
            {
                this.$comparator = comparator;
            }
        };
    }

    @InlineOnly
    private static final <T extends Comparable<? super T>> Comparator<T> nullsFirst() {
        int $i$f$nullsFirst = 0;
        return ComparisonsKt.nullsFirst(ComparisonsKt.<T>naturalOrder());
    }

    @NotNull
    public static final <T> Comparator<T> nullsLast(@NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return new Comparator<T>(comparator){
            final /* synthetic */ Comparator $comparator;

            public final int compare(@Nullable T a, @Nullable T b) {
                return a == b ? 0 : (a == null ? 1 : (b == null ? -1 : this.$comparator.compare(a, b)));
            }
            {
                this.$comparator = comparator;
            }
        };
    }

    @InlineOnly
    private static final <T extends Comparable<? super T>> Comparator<T> nullsLast() {
        int $i$f$nullsLast = 0;
        return ComparisonsKt.nullsLast(ComparisonsKt.<T>naturalOrder());
    }

    @NotNull
    public static final <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
        NaturalOrderComparator naturalOrderComparator = NaturalOrderComparator.INSTANCE;
        if (naturalOrderComparator == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Comparator<T> /* = java.util.Comparator<T> */");
        }
        return naturalOrderComparator;
    }

    @NotNull
    public static final <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
        ReverseOrderComparator reverseOrderComparator = ReverseOrderComparator.INSTANCE;
        if (reverseOrderComparator == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Comparator<T> /* = java.util.Comparator<T> */");
        }
        return reverseOrderComparator;
    }

    @NotNull
    public static final <T> Comparator<T> reversed(@NotNull Comparator<T> $this$reversed) {
        Comparator comparator;
        Intrinsics.checkNotNullParameter($this$reversed, "$this$reversed");
        Comparator<T> comparator2 = $this$reversed;
        if (comparator2 instanceof ReversedComparator) {
            comparator = ((ReversedComparator)$this$reversed).getComparator();
        } else if (Intrinsics.areEqual(comparator2, NaturalOrderComparator.INSTANCE)) {
            ReverseOrderComparator reverseOrderComparator = ReverseOrderComparator.INSTANCE;
            if (reverseOrderComparator == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Comparator<T> /* = java.util.Comparator<T> */");
            }
            comparator = reverseOrderComparator;
        } else if (Intrinsics.areEqual(comparator2, ReverseOrderComparator.INSTANCE)) {
            NaturalOrderComparator naturalOrderComparator = NaturalOrderComparator.INSTANCE;
            if (naturalOrderComparator == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Comparator<T> /* = java.util.Comparator<T> */");
            }
            comparator = naturalOrderComparator;
        } else {
            comparator = new ReversedComparator<T>($this$reversed);
        }
        return comparator;
    }

    public static final /* synthetic */ int access$compareValuesByImpl(Object a, Object b, Function1[] selectors) {
        return ComparisonsKt__ComparisonsKt.compareValuesByImpl$ComparisonsKt__ComparisonsKt(a, b, selectors);
    }
}

