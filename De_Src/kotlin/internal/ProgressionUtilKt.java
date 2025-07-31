/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.internal;

import kotlin.Metadata;
import kotlin.PublishedApi;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002\u001a \u0010\u0000\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a \u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0001H\u0001\u001a \u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0001\u001a\u0018\u0010\n\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005H\u0002\u00a8\u0006\u000b"}, d2={"differenceModulo", "", "a", "b", "c", "", "getProgressionLastElement", "start", "end", "step", "mod", "kotlin-stdlib"})
public final class ProgressionUtilKt {
    private static final int mod(int a, int b) {
        int mod = a % b;
        return mod >= 0 ? mod : mod + b;
    }

    private static final long mod(long a, long b) {
        long mod = a % b;
        return mod >= 0L ? mod : mod + b;
    }

    private static final int differenceModulo(int a, int b, int c) {
        return ProgressionUtilKt.mod(ProgressionUtilKt.mod(a, c) - ProgressionUtilKt.mod(b, c), c);
    }

    private static final long differenceModulo(long a, long b, long c) {
        return ProgressionUtilKt.mod(ProgressionUtilKt.mod(a, c) - ProgressionUtilKt.mod(b, c), c);
    }

    @PublishedApi
    public static final int getProgressionLastElement(int start, int end, int step) {
        int n;
        if (step > 0) {
            n = start >= end ? end : end - ProgressionUtilKt.differenceModulo(end, start, step);
        } else if (step < 0) {
            n = start <= end ? end : end + ProgressionUtilKt.differenceModulo(start, end, -step);
        } else {
            throw (Throwable)new IllegalArgumentException("Step is zero.");
        }
        return n;
    }

    @PublishedApi
    public static final long getProgressionLastElement(long start, long end, long step) {
        long l;
        if (step > 0L) {
            l = start >= end ? end : end - ProgressionUtilKt.differenceModulo(end, start, step);
        } else if (step < 0L) {
            l = start <= end ? end : end + ProgressionUtilKt.differenceModulo(start, end, -step);
        } else {
            throw (Throwable)new IllegalArgumentException("Step is zero.");
        }
        return l;
    }
}

