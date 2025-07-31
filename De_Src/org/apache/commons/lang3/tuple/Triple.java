/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.tuple;

import java.io.Serializable;
import java.util.Objects;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.tuple.ImmutableTriple;

public abstract class Triple<L, M, R>
implements Comparable<Triple<L, M, R>>,
Serializable {
    private static final long serialVersionUID = 1L;

    public static <L, M, R> Triple<L, M, R> of(L left, M middle, R right) {
        return new ImmutableTriple<L, M, R>(left, middle, right);
    }

    public abstract L getLeft();

    public abstract M getMiddle();

    public abstract R getRight();

    @Override
    public int compareTo(Triple<L, M, R> other) {
        return new CompareToBuilder().append(this.getLeft(), other.getLeft()).append(this.getMiddle(), other.getMiddle()).append(this.getRight(), other.getRight()).toComparison();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Triple) {
            Triple other = (Triple)obj;
            return Objects.equals(this.getLeft(), other.getLeft()) && Objects.equals(this.getMiddle(), other.getMiddle()) && Objects.equals(this.getRight(), other.getRight());
        }
        return false;
    }

    public int hashCode() {
        return (this.getLeft() == null ? 0 : this.getLeft().hashCode()) ^ (this.getMiddle() == null ? 0 : this.getMiddle().hashCode()) ^ (this.getRight() == null ? 0 : this.getRight().hashCode());
    }

    public String toString() {
        return "(" + this.getLeft() + "," + this.getMiddle() + "," + this.getRight() + ")";
    }

    public String toString(String format) {
        return String.format(format, this.getLeft(), this.getMiddle(), this.getRight());
    }
}

