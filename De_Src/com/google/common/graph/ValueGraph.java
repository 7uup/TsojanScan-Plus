/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.graph.BaseGraph;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.EndpointPair;
import com.google.common.graph.Graph;
import java.util.Optional;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
public interface ValueGraph<N, V>
extends BaseGraph<N> {
    @Override
    public Set<N> nodes();

    @Override
    public Set<EndpointPair<N>> edges();

    public Graph<N> asGraph();

    @Override
    public boolean isDirected();

    @Override
    public boolean allowsSelfLoops();

    @Override
    public ElementOrder<N> nodeOrder();

    @Override
    public ElementOrder<N> incidentEdgeOrder();

    @Override
    public Set<N> adjacentNodes(N var1);

    @Override
    public Set<N> predecessors(N var1);

    @Override
    public Set<N> successors(N var1);

    @Override
    public Set<EndpointPair<N>> incidentEdges(N var1);

    @Override
    public int degree(N var1);

    @Override
    public int inDegree(N var1);

    @Override
    public int outDegree(N var1);

    @Override
    public boolean hasEdgeConnecting(N var1, N var2);

    @Override
    public boolean hasEdgeConnecting(EndpointPair<N> var1);

    public Optional<V> edgeValue(N var1, N var2);

    public Optional<V> edgeValue(EndpointPair<N> var1);

    public @Nullable V edgeValueOrDefault(N var1, N var2, @Nullable V var3);

    public @Nullable V edgeValueOrDefault(EndpointPair<N> var1, @Nullable V var2);

    public boolean equals(@Nullable Object var1);

    public int hashCode();
}

