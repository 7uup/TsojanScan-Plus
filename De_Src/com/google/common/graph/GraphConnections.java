/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.graph.EndpointPair;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

interface GraphConnections<N, V> {
    public Set<N> adjacentNodes();

    public Set<N> predecessors();

    public Set<N> successors();

    public Iterator<EndpointPair<N>> incidentEdgeIterator(N var1);

    public @Nullable V value(N var1);

    public void removePredecessor(N var1);

    @CanIgnoreReturnValue
    public V removeSuccessor(N var1);

    public void addPredecessor(N var1, V var2);

    @CanIgnoreReturnValue
    public V addSuccessor(N var1, V var2);
}

