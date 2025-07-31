/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.tuple;

import org.apache.commons.lang3.tuple.Triple;

public class MutableTriple<L, M, R>
extends Triple<L, M, R> {
    private static final long serialVersionUID = 1L;
    public L left;
    public M middle;
    public R right;

    public static <L, M, R> MutableTriple<L, M, R> of(L left, M middle, R right) {
        return new MutableTriple<L, M, R>(left, middle, right);
    }

    public MutableTriple() {
    }

    public MutableTriple(L left, M middle, R right) {
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    @Override
    public L getLeft() {
        return this.left;
    }

    public void setLeft(L left) {
        this.left = left;
    }

    @Override
    public M getMiddle() {
        return this.middle;
    }

    public void setMiddle(M middle) {
        this.middle = middle;
    }

    @Override
    public R getRight() {
        return this.right;
    }

    public void setRight(R right) {
        this.right = right;
    }
}

