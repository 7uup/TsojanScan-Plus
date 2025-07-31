/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.tuple;

import org.apache.commons.lang3.tuple.Pair;

public final class ImmutablePair<L, R>
extends Pair<L, R> {
    private static final ImmutablePair NULL = ImmutablePair.of(null, null);
    private static final long serialVersionUID = 4954918890077093841L;
    public final L left;
    public final R right;

    public static <L, R> ImmutablePair<L, R> nullPair() {
        return NULL;
    }

    public static <L, R> ImmutablePair<L, R> of(L left, R right) {
        return new ImmutablePair<L, R>(left, right);
    }

    public ImmutablePair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public L getLeft() {
        return this.left;
    }

    @Override
    public R getRight() {
        return this.right;
    }

    @Override
    public R setValue(R value) {
        throw new UnsupportedOperationException();
    }
}

