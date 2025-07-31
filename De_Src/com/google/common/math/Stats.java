/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.math.DoubleUtils;
import com.google.common.math.StatsAccumulator;
import com.google.common.primitives.Doubles;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
@GwtIncompatible
public final class Stats
implements Serializable {
    private final long count;
    private final double mean;
    private final double sumOfSquaresOfDeltas;
    private final double min;
    private final double max;
    static final int BYTES = 40;
    private static final long serialVersionUID = 0L;

    Stats(long count, double mean, double sumOfSquaresOfDeltas, double min2, double max) {
        this.count = count;
        this.mean = mean;
        this.sumOfSquaresOfDeltas = sumOfSquaresOfDeltas;
        this.min = min2;
        this.max = max;
    }

    public static Stats of(Iterable<? extends Number> values2) {
        StatsAccumulator accumulator = new StatsAccumulator();
        accumulator.addAll(values2);
        return accumulator.snapshot();
    }

    public static Stats of(Iterator<? extends Number> values2) {
        StatsAccumulator accumulator = new StatsAccumulator();
        accumulator.addAll(values2);
        return accumulator.snapshot();
    }

    public static Stats of(double ... values2) {
        StatsAccumulator acummulator = new StatsAccumulator();
        acummulator.addAll(values2);
        return acummulator.snapshot();
    }

    public static Stats of(int ... values2) {
        StatsAccumulator acummulator = new StatsAccumulator();
        acummulator.addAll(values2);
        return acummulator.snapshot();
    }

    public static Stats of(long ... values2) {
        StatsAccumulator acummulator = new StatsAccumulator();
        acummulator.addAll(values2);
        return acummulator.snapshot();
    }

    public static Stats of(DoubleStream values2) {
        return values2.collect(StatsAccumulator::new, StatsAccumulator::add, StatsAccumulator::addAll).snapshot();
    }

    public static Stats of(IntStream values2) {
        return values2.collect(StatsAccumulator::new, StatsAccumulator::add, StatsAccumulator::addAll).snapshot();
    }

    public static Stats of(LongStream values2) {
        return values2.collect(StatsAccumulator::new, StatsAccumulator::add, StatsAccumulator::addAll).snapshot();
    }

    public static Collector<Number, StatsAccumulator, Stats> toStats() {
        return Collector.of(StatsAccumulator::new, (a, x) -> a.add(x.doubleValue()), (l, r) -> {
            l.addAll((StatsAccumulator)r);
            return l;
        }, StatsAccumulator::snapshot, Collector.Characteristics.UNORDERED);
    }

    public long count() {
        return this.count;
    }

    public double mean() {
        Preconditions.checkState(this.count != 0L);
        return this.mean;
    }

    public double sum() {
        return this.mean * (double)this.count;
    }

    public double populationVariance() {
        Preconditions.checkState(this.count > 0L);
        if (Double.isNaN(this.sumOfSquaresOfDeltas)) {
            return Double.NaN;
        }
        if (this.count == 1L) {
            return 0.0;
        }
        return DoubleUtils.ensureNonNegative(this.sumOfSquaresOfDeltas) / (double)this.count();
    }

    public double populationStandardDeviation() {
        return Math.sqrt(this.populationVariance());
    }

    public double sampleVariance() {
        Preconditions.checkState(this.count > 1L);
        if (Double.isNaN(this.sumOfSquaresOfDeltas)) {
            return Double.NaN;
        }
        return DoubleUtils.ensureNonNegative(this.sumOfSquaresOfDeltas) / (double)(this.count - 1L);
    }

    public double sampleStandardDeviation() {
        return Math.sqrt(this.sampleVariance());
    }

    public double min() {
        Preconditions.checkState(this.count != 0L);
        return this.min;
    }

    public double max() {
        Preconditions.checkState(this.count != 0L);
        return this.max;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Stats other = (Stats)obj;
        return this.count == other.count && Double.doubleToLongBits(this.mean) == Double.doubleToLongBits(other.mean) && Double.doubleToLongBits(this.sumOfSquaresOfDeltas) == Double.doubleToLongBits(other.sumOfSquaresOfDeltas) && Double.doubleToLongBits(this.min) == Double.doubleToLongBits(other.min) && Double.doubleToLongBits(this.max) == Double.doubleToLongBits(other.max);
    }

    public int hashCode() {
        return Objects.hashCode(this.count, this.mean, this.sumOfSquaresOfDeltas, this.min, this.max);
    }

    public String toString() {
        if (this.count() > 0L) {
            return MoreObjects.toStringHelper(this).add("count", this.count).add("mean", this.mean).add("populationStandardDeviation", this.populationStandardDeviation()).add("min", this.min).add("max", this.max).toString();
        }
        return MoreObjects.toStringHelper(this).add("count", this.count).toString();
    }

    double sumOfSquaresOfDeltas() {
        return this.sumOfSquaresOfDeltas;
    }

    public static double meanOf(Iterable<? extends Number> values2) {
        return Stats.meanOf(values2.iterator());
    }

    public static double meanOf(Iterator<? extends Number> values2) {
        Preconditions.checkArgument(values2.hasNext());
        long count = 1L;
        double mean = values2.next().doubleValue();
        while (values2.hasNext()) {
            double value = values2.next().doubleValue();
            ++count;
            if (Doubles.isFinite(value) && Doubles.isFinite(mean)) {
                mean += (value - mean) / (double)count;
                continue;
            }
            mean = StatsAccumulator.calculateNewMeanNonFinite(mean, value);
        }
        return mean;
    }

    public static double meanOf(double ... values2) {
        Preconditions.checkArgument(values2.length > 0);
        double mean = values2[0];
        for (int index = 1; index < values2.length; ++index) {
            double value = values2[index];
            if (Doubles.isFinite(value) && Doubles.isFinite(mean)) {
                mean += (value - mean) / (double)(index + 1);
                continue;
            }
            mean = StatsAccumulator.calculateNewMeanNonFinite(mean, value);
        }
        return mean;
    }

    public static double meanOf(int ... values2) {
        Preconditions.checkArgument(values2.length > 0);
        double mean = values2[0];
        for (int index = 1; index < values2.length; ++index) {
            double value = values2[index];
            if (Doubles.isFinite(value) && Doubles.isFinite(mean)) {
                mean += (value - mean) / (double)(index + 1);
                continue;
            }
            mean = StatsAccumulator.calculateNewMeanNonFinite(mean, value);
        }
        return mean;
    }

    public static double meanOf(long ... values2) {
        Preconditions.checkArgument(values2.length > 0);
        double mean = values2[0];
        for (int index = 1; index < values2.length; ++index) {
            double value = values2[index];
            if (Doubles.isFinite(value) && Doubles.isFinite(mean)) {
                mean += (value - mean) / (double)(index + 1);
                continue;
            }
            mean = StatsAccumulator.calculateNewMeanNonFinite(mean, value);
        }
        return mean;
    }

    public byte[] toByteArray() {
        ByteBuffer buff = ByteBuffer.allocate(40).order(ByteOrder.LITTLE_ENDIAN);
        this.writeTo(buff);
        return buff.array();
    }

    void writeTo(ByteBuffer buffer) {
        Preconditions.checkNotNull(buffer);
        Preconditions.checkArgument(buffer.remaining() >= 40, "Expected at least Stats.BYTES = %s remaining , got %s", 40, buffer.remaining());
        buffer.putLong(this.count).putDouble(this.mean).putDouble(this.sumOfSquaresOfDeltas).putDouble(this.min).putDouble(this.max);
    }

    public static Stats fromByteArray(byte[] byteArray) {
        Preconditions.checkNotNull(byteArray);
        Preconditions.checkArgument(byteArray.length == 40, "Expected Stats.BYTES = %s remaining , got %s", 40, byteArray.length);
        return Stats.readFrom(ByteBuffer.wrap(byteArray).order(ByteOrder.LITTLE_ENDIAN));
    }

    static Stats readFrom(ByteBuffer buffer) {
        Preconditions.checkNotNull(buffer);
        Preconditions.checkArgument(buffer.remaining() >= 40, "Expected at least Stats.BYTES = %s remaining , got %s", 40, buffer.remaining());
        return new Stats(buffer.getLong(), buffer.getDouble(), buffer.getDouble(), buffer.getDouble(), buffer.getDouble());
    }
}

