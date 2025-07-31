/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.time.ExperimentalTime;
import kotlin.time.MonotonicTimeSource;
import kotlin.time.TimeMark;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u0000 \u00042\u00020\u0001:\u0002\u0004\u0005J\b\u0010\u0002\u001a\u00020\u0003H&\u00a8\u0006\u0006"}, d2={"Lkotlin/time/TimeSource;", "", "markNow", "Lkotlin/time/TimeMark;", "Companion", "Monotonic", "kotlin-stdlib"})
@SinceKotlin(version="1.3")
@ExperimentalTime
public interface TimeSource {
    public static final Companion Companion = kotlin.time.TimeSource$Companion.$$INSTANCE;

    @NotNull
    public TimeMark markNow();

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004H\u0096\u0001J\b\u0010\u0005\u001a\u00020\u0006H\u0016\u00a8\u0006\u0007"}, d2={"Lkotlin/time/TimeSource$Monotonic;", "Lkotlin/time/TimeSource;", "()V", "markNow", "Lkotlin/time/TimeMark;", "toString", "", "kotlin-stdlib"})
    public static final class Monotonic
    implements TimeSource {
        public static final Monotonic INSTANCE;
        private final /* synthetic */ MonotonicTimeSource $$delegate_0;

        @NotNull
        public String toString() {
            return MonotonicTimeSource.INSTANCE.toString();
        }

        private Monotonic() {
            this.$$delegate_0 = MonotonicTimeSource.INSTANCE;
        }

        static {
            Monotonic monotonic;
            INSTANCE = monotonic = new Monotonic();
        }

        @Override
        @NotNull
        public TimeMark markNow() {
            return this.$$delegate_0.markNow();
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2={"Lkotlin/time/TimeSource$Companion;", "", "()V", "kotlin-stdlib"})
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE;

        private Companion() {
        }

        static {
            Companion companion;
            $$INSTANCE = companion = new Companion();
        }
    }
}

