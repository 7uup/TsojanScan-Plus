/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ForwardingDeque;
import java.util.Collection;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

@Deprecated
@GwtIncompatible
public abstract class ForwardingBlockingDeque<E>
extends ForwardingDeque<E>
implements BlockingDeque<E> {
    protected ForwardingBlockingDeque() {
    }

    @Override
    protected abstract BlockingDeque<E> delegate();

    @Override
    public int remainingCapacity() {
        return this.delegate().remainingCapacity();
    }

    @Override
    public void putFirst(E e) throws InterruptedException {
        this.delegate().putFirst(e);
    }

    @Override
    public void putLast(E e) throws InterruptedException {
        this.delegate().putLast(e);
    }

    @Override
    public boolean offerFirst(E e, long timeout2, TimeUnit unit) throws InterruptedException {
        return this.delegate().offerFirst(e, timeout2, unit);
    }

    @Override
    public boolean offerLast(E e, long timeout2, TimeUnit unit) throws InterruptedException {
        return this.delegate().offerLast(e, timeout2, unit);
    }

    @Override
    public E takeFirst() throws InterruptedException {
        return this.delegate().takeFirst();
    }

    @Override
    public E takeLast() throws InterruptedException {
        return this.delegate().takeLast();
    }

    @Override
    public E pollFirst(long timeout2, TimeUnit unit) throws InterruptedException {
        return this.delegate().pollFirst(timeout2, unit);
    }

    @Override
    public E pollLast(long timeout2, TimeUnit unit) throws InterruptedException {
        return this.delegate().pollLast(timeout2, unit);
    }

    @Override
    public void put(E e) throws InterruptedException {
        this.delegate().put(e);
    }

    @Override
    public boolean offer(E e, long timeout2, TimeUnit unit) throws InterruptedException {
        return this.delegate().offer(e, timeout2, unit);
    }

    @Override
    public E take() throws InterruptedException {
        return this.delegate().take();
    }

    @Override
    public E poll(long timeout2, TimeUnit unit) throws InterruptedException {
        return this.delegate().poll(timeout2, unit);
    }

    @Override
    public int drainTo(Collection<? super E> c) {
        return this.delegate().drainTo(c);
    }

    @Override
    public int drainTo(Collection<? super E> c, int maxElements) {
        return this.delegate().drainTo(c, maxElements);
    }
}

