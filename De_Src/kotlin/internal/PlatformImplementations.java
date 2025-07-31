/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.internal;

import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.MatchResult;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.FallbackThreadLocalRandom;
import kotlin.random.Random;
import kotlin.text.MatchGroup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001:\u0001\u0012B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\u001a\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u00112\u0006\u0010\u0007\u001a\u00020\u0006H\u0016\u00a8\u0006\u0013"}, d2={"Lkotlin/internal/PlatformImplementations;", "", "()V", "addSuppressed", "", "cause", "", "exception", "defaultPlatformRandom", "Lkotlin/random/Random;", "getMatchResultNamedGroup", "Lkotlin/text/MatchGroup;", "matchResult", "Ljava/util/regex/MatchResult;", "name", "", "getSuppressed", "", "ReflectThrowable", "kotlin-stdlib"})
public class PlatformImplementations {
    public void addSuppressed(@NotNull Throwable cause, @NotNull Throwable exception) {
        block0: {
            Intrinsics.checkNotNullParameter(cause, "cause");
            Intrinsics.checkNotNullParameter(exception, "exception");
            Method method = ReflectThrowable.addSuppressed;
            if (method == null) break block0;
            method.invoke(cause, exception);
        }
    }

    @NotNull
    public List<Throwable> getSuppressed(@NotNull Throwable exception) {
        List<Throwable> list;
        block5: {
            block4: {
                Intrinsics.checkNotNullParameter(exception, "exception");
                list = ReflectThrowable.getSuppressed;
                if (list == null || (list = ((Method)((Object)list)).invoke(exception, new Object[0])) == null) break block4;
                List<Throwable> list2 = list;
                boolean bl = false;
                boolean bl2 = false;
                List<Throwable> it = list2;
                boolean bl3 = false;
                List<Throwable> list3 = it;
                if (list3 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.Throwable>");
                }
                list = ArraysKt.asList((Throwable[])list3);
                if (list != null) break block5;
            }
            list = CollectionsKt.emptyList();
        }
        return list;
    }

    @Nullable
    public MatchGroup getMatchResultNamedGroup(@NotNull MatchResult matchResult, @NotNull String name) {
        Intrinsics.checkNotNullParameter(matchResult, "matchResult");
        Intrinsics.checkNotNullParameter(name, "name");
        throw (Throwable)new UnsupportedOperationException("Retrieving groups by name is not supported on this platform.");
    }

    @NotNull
    public Random defaultPlatformRandom() {
        return new FallbackThreadLocalRandom();
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c2\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2={"Lkotlin/internal/PlatformImplementations$ReflectThrowable;", "", "()V", "addSuppressed", "Ljava/lang/reflect/Method;", "getSuppressed", "kotlin-stdlib"})
    private static final class ReflectThrowable {
        @JvmField
        @Nullable
        public static final Method addSuppressed;
        @JvmField
        @Nullable
        public static final Method getSuppressed;
        public static final ReflectThrowable INSTANCE;

        private ReflectThrowable() {
        }

        /*
         * Unable to fully structure code
         */
        static {
            block5: {
                block4: {
                    ReflectThrowable.INSTANCE = var0 = new ReflectThrowable();
                    throwableClass = Throwable.class;
                    throwableMethods = throwableClass.getMethods();
                    Intrinsics.checkNotNullExpressionValue(throwableMethods, "throwableMethods");
                    var3_3 = throwableMethods;
                    var4_4 = false;
                    var5_5 = var3_3;
                    var6_6 = false;
                    var7_7 = var5_5;
                    var8_8 = var7_7.length;
                    for (var9_9 = 0; var9_9 < var8_8; ++var9_9) {
                        it = var10_10 = var7_7[var9_9];
                        $i$a$-find-PlatformImplementations$ReflectThrowable$1 = false;
                        v0 = it;
                        Intrinsics.checkNotNullExpressionValue(v0, "it");
                        if (!Intrinsics.areEqual(v0.getName(), "addSuppressed")) ** GOTO lbl-1000
                        v1 = it.getParameterTypes();
                        Intrinsics.checkNotNullExpressionValue(v1, "it.parameterTypes");
                        if (Intrinsics.areEqual(ArraysKt.singleOrNull(v1), throwableClass)) {
                            v2 = true;
                        } else lbl-1000:
                        // 2 sources

                        {
                            v2 = false;
                        }
                        if (!v2) continue;
                        v3 = var10_10;
                        break block4;
                    }
                    v3 = null;
                }
                ReflectThrowable.addSuppressed = v3;
                var3_3 = throwableMethods;
                var4_4 = false;
                var5_5 = var3_3;
                var6_6 = false;
                var7_7 = var5_5;
                var8_8 = var7_7.length;
                for (var9_9 = 0; var9_9 < var8_8; ++var9_9) {
                    it = var10_10 = var7_7[var9_9];
                    $i$a$-find-PlatformImplementations$ReflectThrowable$2 = false;
                    v4 = it;
                    Intrinsics.checkNotNullExpressionValue(v4, "it");
                    if (!Intrinsics.areEqual(v4.getName(), "getSuppressed")) continue;
                    v5 = var10_10;
                    break block5;
                }
                v5 = null;
            }
            ReflectThrowable.getSuppressed = v5;
        }
    }
}

