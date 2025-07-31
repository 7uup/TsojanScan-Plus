/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.graph.BaseGraph;
import com.google.common.graph.EndpointPair;
import java.util.AbstractSet;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

abstract class IncidentEdgeSet<N>
extends AbstractSet<EndpointPair<N>> {
    protected final N node;
    protected final BaseGraph<N> graph;

    IncidentEdgeSet(BaseGraph<N> graph, N node) {
        this.graph = graph;
        this.node = node;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        if (this.graph.isDirected()) {
            return this.graph.inDegree(this.node) + this.graph.outDegree(this.node) - (this.graph.successors((Object)this.node).contains(this.node) ? 1 : 0);
        }
        return this.graph.adjacentNodes(this.node).size();
    }

    @Override
    public boolean contains(@Nullable Object obj) {
        if (!(obj instanceof EndpointPair)) {
            return false;
        }
        EndpointPair endpointPair = (EndpointPair)obj;
        if (this.graph.isDirected()) {
            if (!endpointPair.isOrdered()) {
                return false;
            }
            Object source2 = endpointPair.source();
            Object target = endpointPair.target();
            return this.node.equals(source2) && this.graph.successors((Object)this.node).contains(target) || this.node.equals(target) && this.graph.predecessors((Object)this.node).contains(source2);
        }
        if (endpointPair.isOrdered()) {
            return false;
        }
        Set<N> adjacent = this.graph.adjacentNodes(this.node);
        Object nodeU = endpointPair.nodeU();
        Object nodeV = endpointPair.nodeV();
        return this.node.equals(nodeV) && adjacent.contains(nodeU) || this.node.equals(nodeU) && adjacent.contains(nodeV);
    }
}

