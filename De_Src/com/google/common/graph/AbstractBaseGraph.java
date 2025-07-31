/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.graph.BaseGraph;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.EndpointPair;
import com.google.common.graph.EndpointPairIterator;
import com.google.common.graph.IncidentEdgeSet;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import java.util.AbstractSet;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

abstract class AbstractBaseGraph<N>
implements BaseGraph<N> {
    AbstractBaseGraph() {
    }

    protected long edgeCount() {
        long degreeSum = 0L;
        for (Object node : this.nodes()) {
            degreeSum += (long)this.degree(node);
        }
        Preconditions.checkState((degreeSum & 1L) == 0L);
        return degreeSum >>> 1;
    }

    @Override
    public Set<EndpointPair<N>> edges() {
        return new AbstractSet<EndpointPair<N>>(){

            @Override
            public UnmodifiableIterator<EndpointPair<N>> iterator() {
                return EndpointPairIterator.of(AbstractBaseGraph.this);
            }

            @Override
            public int size() {
                return Ints.saturatedCast(AbstractBaseGraph.this.edgeCount());
            }

            @Override
            public boolean remove(Object o) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean contains(@Nullable Object obj) {
                if (!(obj instanceof EndpointPair)) {
                    return false;
                }
                EndpointPair endpointPair = (EndpointPair)obj;
                return AbstractBaseGraph.this.isOrderingCompatible(endpointPair) && AbstractBaseGraph.this.nodes().contains(endpointPair.nodeU()) && AbstractBaseGraph.this.successors(endpointPair.nodeU()).contains(endpointPair.nodeV());
            }
        };
    }

    @Override
    public ElementOrder<N> incidentEdgeOrder() {
        return ElementOrder.unordered();
    }

    @Override
    public Set<EndpointPair<N>> incidentEdges(N node) {
        Preconditions.checkNotNull(node);
        Preconditions.checkArgument(this.nodes().contains(node), "Node %s is not an element of this graph.", node);
        return new IncidentEdgeSet<N>(this, node){

            @Override
            public UnmodifiableIterator<EndpointPair<N>> iterator() {
                if (this.graph.isDirected()) {
                    return Iterators.unmodifiableIterator(Iterators.concat(Iterators.transform(this.graph.predecessors(this.node).iterator(), new Function<N, EndpointPair<N>>(){

                        @Override
                        public EndpointPair<N> apply(N predecessor) {
                            return EndpointPair.ordered(predecessor, node);
                        }
                    }), Iterators.transform(Sets.difference(this.graph.successors(this.node), ImmutableSet.of(this.node)).iterator(), new Function<N, EndpointPair<N>>(){

                        @Override
                        public EndpointPair<N> apply(N successor) {
                            return EndpointPair.ordered(node, successor);
                        }
                    })));
                }
                return Iterators.unmodifiableIterator(Iterators.transform(this.graph.adjacentNodes(this.node).iterator(), new Function<N, EndpointPair<N>>(){

                    @Override
                    public EndpointPair<N> apply(N adjacentNode) {
                        return EndpointPair.unordered(node, adjacentNode);
                    }
                }));
            }
        };
    }

    @Override
    public int degree(N node) {
        if (this.isDirected()) {
            return IntMath.saturatedAdd(this.predecessors((Object)node).size(), this.successors((Object)node).size());
        }
        Set<N> neighbors = this.adjacentNodes(node);
        int selfLoopCount = this.allowsSelfLoops() && neighbors.contains(node) ? 1 : 0;
        return IntMath.saturatedAdd(neighbors.size(), selfLoopCount);
    }

    @Override
    public int inDegree(N node) {
        return this.isDirected() ? this.predecessors((Object)node).size() : this.degree(node);
    }

    @Override
    public int outDegree(N node) {
        return this.isDirected() ? this.successors((Object)node).size() : this.degree(node);
    }

    @Override
    public boolean hasEdgeConnecting(N nodeU, N nodeV) {
        Preconditions.checkNotNull(nodeU);
        Preconditions.checkNotNull(nodeV);
        return this.nodes().contains(nodeU) && this.successors((Object)nodeU).contains(nodeV);
    }

    @Override
    public boolean hasEdgeConnecting(EndpointPair<N> endpoints) {
        Preconditions.checkNotNull(endpoints);
        if (!this.isOrderingCompatible(endpoints)) {
            return false;
        }
        N nodeU = endpoints.nodeU();
        N nodeV = endpoints.nodeV();
        return this.nodes().contains(nodeU) && this.successors((Object)nodeU).contains(nodeV);
    }

    protected final void validateEndpoints(EndpointPair<?> endpoints) {
        Preconditions.checkNotNull(endpoints);
        Preconditions.checkArgument(this.isOrderingCompatible(endpoints), "Mismatch: unordered endpoints cannot be used with directed graphs");
    }

    protected final boolean isOrderingCompatible(EndpointPair<?> endpoints) {
        return endpoints.isOrdered() || !this.isDirected();
    }
}

