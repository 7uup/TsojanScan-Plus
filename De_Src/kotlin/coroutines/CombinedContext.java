/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.coroutines;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.coroutines.CombinedContext;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003:\u0001!B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0000H\u0002J\u0013\u0010\f\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002J5\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u00102\u0006\u0010\u0011\u001a\u0002H\u00102\u0018\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u00100\u0013H\u0016\u00a2\u0006\u0002\u0010\u0014J(\u0010\u0015\u001a\u0004\u0018\u0001H\u0016\"\b\b\u0000\u0010\u0016*\u00020\u00062\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00160\u0018H\u0096\u0002\u00a2\u0006\u0002\u0010\u0019J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0014\u0010\u001c\u001a\u00020\u00012\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0018H\u0016J\b\u0010\u001d\u001a\u00020\u001bH\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u000eH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2={"Lkotlin/coroutines/CombinedContext;", "Lkotlin/coroutines/CoroutineContext;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "left", "element", "Lkotlin/coroutines/CoroutineContext$Element;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext$Element;)V", "contains", "", "containsAll", "context", "equals", "other", "", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "hashCode", "", "minusKey", "size", "toString", "", "writeReplace", "Serialized", "kotlin-stdlib"})
@SinceKotlin(version="1.3")
public final class CombinedContext
implements CoroutineContext,
Serializable {
    private final CoroutineContext left;
    private final CoroutineContext.Element element;

    @Override
    @Nullable
    public <E extends CoroutineContext.Element> E get(@NotNull CoroutineContext.Key<E> key) {
        CoroutineContext next;
        Intrinsics.checkNotNullParameter(key, "key");
        CombinedContext cur = this;
        while (true) {
            E e = cur.element.get(key);
            if (e != null) {
                E e2 = e;
                boolean bl = false;
                boolean bl2 = false;
                E it = e2;
                boolean bl3 = false;
                return it;
            }
            next = cur.left;
            if (!(next instanceof CombinedContext)) break;
            cur = (CombinedContext)next;
        }
        return next.get(key);
    }

    @Override
    public <R> R fold(R initial, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(operation, "operation");
        return operation.invoke(this.left.fold(initial, operation), this.element);
    }

    @Override
    @NotNull
    public CoroutineContext minusKey(@NotNull CoroutineContext.Key<?> key) {
        Intrinsics.checkNotNullParameter(key, "key");
        Object obj = this.element.get(key);
        if (obj != null) {
            Object obj2 = obj;
            boolean bl = false;
            boolean bl2 = false;
            Object it = obj2;
            boolean bl3 = false;
            return this.left;
        }
        CoroutineContext newLeft = this.left.minusKey(key);
        return newLeft == this.left ? (CoroutineContext)this : (newLeft == EmptyCoroutineContext.INSTANCE ? (CoroutineContext)this.element : (CoroutineContext)new CombinedContext(newLeft, this.element));
    }

    private final int size() {
        CombinedContext cur = this;
        int size = 2;
        while (true) {
            CoroutineContext coroutineContext;
            if (!((coroutineContext = cur.left) instanceof CombinedContext)) {
                coroutineContext = null;
            }
            if ((CombinedContext)coroutineContext == null) {
                return size;
            }
            ++size;
        }
    }

    private final boolean contains(CoroutineContext.Element element) {
        return Intrinsics.areEqual(this.get(element.getKey()), element);
    }

    private final boolean containsAll(CombinedContext context) {
        CoroutineContext next;
        CombinedContext cur = context;
        while (true) {
            if (!this.contains(cur.element)) {
                return false;
            }
            next = cur.left;
            if (!(next instanceof CombinedContext)) break;
            cur = (CombinedContext)next;
        }
        CoroutineContext coroutineContext = next;
        if (coroutineContext == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.coroutines.CoroutineContext.Element");
        }
        return this.contains((CoroutineContext.Element)coroutineContext);
    }

    public boolean equals(@Nullable Object other) {
        return this == other || other instanceof CombinedContext && ((CombinedContext)other).size() == this.size() && ((CombinedContext)other).containsAll(this);
    }

    public int hashCode() {
        return this.left.hashCode() + this.element.hashCode();
    }

    @NotNull
    public String toString() {
        return "[" + this.fold("", toString.1.INSTANCE) + "]";
    }

    /*
     * WARNING - void declaration
     */
    private final Object writeReplace() {
        void index;
        int n = this.size();
        CoroutineContext[] elements = new CoroutineContext[n];
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 0;
        this.fold(Unit.INSTANCE, (Function2)new Function2<Unit, CoroutineContext.Element, Unit>(elements, (Ref.IntRef)index){
            final /* synthetic */ CoroutineContext[] $elements;
            final /* synthetic */ Ref.IntRef $index;

            public final void invoke(@NotNull Unit $noName_0, @NotNull CoroutineContext.Element element) {
                Intrinsics.checkNotNullParameter($noName_0, "<anonymous parameter 0>");
                Intrinsics.checkNotNullParameter(element, "element");
                int n = this.$index.element;
                this.$index.element = n + 1;
                this.$elements[n] = element;
            }
            {
                this.$elements = coroutineContextArray;
                this.$index = intRef;
                super(2);
            }
        });
        boolean bl = index.element == n;
        boolean bl2 = false;
        boolean bl3 = false;
        bl3 = false;
        boolean bl4 = false;
        if (!bl) {
            boolean bl5 = false;
            String string = "Check failed.";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        return new Serialized(elements);
    }

    public CombinedContext(@NotNull CoroutineContext left, @NotNull CoroutineContext.Element element) {
        Intrinsics.checkNotNullParameter(left, "left");
        Intrinsics.checkNotNullParameter(element, "element");
        this.left = left;
        this.element = element;
    }

    @Override
    @NotNull
    public CoroutineContext plus(@NotNull CoroutineContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return CoroutineContext.DefaultImpls.plus(this, context);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u0000 \f2\u00060\u0001j\u0002`\u0002:\u0001\fB\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\u0002\u0010\u0006J\b\u0010\n\u001a\u00020\u000bH\u0002R\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\r"}, d2={"Lkotlin/coroutines/CombinedContext$Serialized;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "elements", "", "Lkotlin/coroutines/CoroutineContext;", "([Lkotlin/coroutines/CoroutineContext;)V", "getElements", "()[Lkotlin/coroutines/CoroutineContext;", "[Lkotlin/coroutines/CoroutineContext;", "readResolve", "", "Companion", "kotlin-stdlib"})
    private static final class Serialized
    implements Serializable {
        @NotNull
        private final CoroutineContext[] elements;
        private static final long serialVersionUID = 0L;
        public static final Companion Companion = new Companion(null);

        /*
         * WARNING - void declaration
         */
        private final Object readResolve() {
            void $this$fold$iv;
            CoroutineContext[] coroutineContextArray = this.elements;
            EmptyCoroutineContext initial$iv = EmptyCoroutineContext.INSTANCE;
            boolean $i$f$fold = false;
            CoroutineContext accumulator$iv = initial$iv;
            void var5_5 = $this$fold$iv;
            int n = ((void)var5_5).length;
            for (int i = 0; i < n; ++i) {
                void p2;
                void element$iv;
                void var9_9 = element$iv = var5_5[i];
                CoroutineContext p1 = accumulator$iv;
                boolean bl = false;
                accumulator$iv = p1.plus((CoroutineContext)p2);
            }
            return accumulator$iv;
        }

        @NotNull
        public final CoroutineContext[] getElements() {
            return this.elements;
        }

        public Serialized(@NotNull CoroutineContext[] elements) {
            Intrinsics.checkNotNullParameter(elements, "elements");
            this.elements = elements;
        }

        @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2={"Lkotlin/coroutines/CombinedContext$Serialized$Companion;", "", "()V", "serialVersionUID", "", "kotlin-stdlib"})
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }
}

