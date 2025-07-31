/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.EndpointPair;
import com.google.common.graph.GraphConnections;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

final class UndirectedGraphConnections<N, V>
implements GraphConnections<N, V> {
    private final Map<N, V> adjacentNodeValues;

    private UndirectedGraphConnections(Map<N, V> adjacentNodeValues) {
        this.adjacentNodeValues = Preconditions.checkNotNull(adjacentNodeValues);
    }

    static <N, V> UndirectedGraphConnections<N, V> of(ElementOrder<N> incidentEdgeOrder) {
        switch (incidentEdgeOrder.type()) {
            case UNORDERED: {
                return new UndirectedGraphConnections(new HashMap(2, 1.0f));
            }
            case STABLE: {
                return new UndirectedGraphConnections(new LinkedHashMap(2, 1.0f));
            }
        }
        throw new AssertionError((Object)incidentEdgeOrder.type());
    }

    static <N, V> UndirectedGraphConnections<N, V> ofImmutable(Map<N, V> adjacentNodeValues) {
        return new UndirectedGraphConnections<N, V>(ImmutableMap.copyOf(adjacentNodeValues));
    }

    @Override
    public Set<N> adjacentNodes() {
        return Collections.unmodifiableSet(this.adjacentNodeValues.keySet());
    }

    @Override
    public Set<N> predecessors() {
        return this.adjacentNodes();
    }

    @Override
    public Set<N> successors() {
        return this.adjacentNodes();
    }

    @Override
    public Iterator<EndpointPair<N>> incidentEdgeIterator(final N thisNode) {
        return Iterators.transform(this.adjacentNodeValues.keySet().iterator(), new Function<N, EndpointPair<N>>(){

            @Override
            public EndpointPair<N> apply(N incidentNode) {
                return EndpointPair.unordered(thisNode, incidentNode);
            }
        });
    }

    @Override
    public V value(N node) {
        return this.adjacentNodeValues.get(node);
    }

    @Override
    public void removePredecessor(N node) {
        V unused = this.removeSuccessor(node);
    }

    @Override
    public V removeSuccessor(N node) {
        return this.adjacentNodeValues.remove(node);
    }

    @Override
    public void addPredecessor(N node, V value) {
        V unused = this.addSuccessor(node, value);
    }

    @Override
    public V addSuccessor(N node, V value) {
        return this.adjacentNodeValues.put(node, value);
    }
}

