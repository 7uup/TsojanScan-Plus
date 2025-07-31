/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.DurationUnitKt$WhenMappings;
import kotlin.time.DurationUnitKt__DurationUnitJvmKt;
import kotlin.time.ExperimentalTime;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u0001*\u00060\u0002j\u0002`\u0003H\u0001\u00a8\u0006\u0004"}, d2={"shortName", "", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "kotlin-stdlib"}, xs="kotlin/time/DurationUnitKt")
class DurationUnitKt__DurationUnitKt
extends DurationUnitKt__DurationUnitJvmKt {
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    @NotNull
    public static final String shortName(@NotNull TimeUnit $this$shortName) {
        String string;
        Intrinsics.checkNotNullParameter((Object)$this$shortName, "$this$shortName");
        switch (DurationUnitKt$WhenMappings.$EnumSwitchMapping$0[$this$shortName.ordinal()]) {
            case 1: {
                string = "ns";
                break;
            }
            case 2: {
                string = "us";
                break;
            }
            case 3: {
                string = "ms";
                break;
            }
            case 4: {
                string = "s";
                break;
            }
            case 5: {
                string = "m";
                break;
            }
            case 6: {
                string = "h";
                break;
            }
            case 7: {
                string = "d";
                break;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
        return string;
    }
}

