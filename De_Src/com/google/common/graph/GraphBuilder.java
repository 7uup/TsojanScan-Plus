/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.graph.AbstractGraphBuilder;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.Graph;
import com.google.common.graph.Graphs;
import com.google.common.graph.ImmutableGraph;
import com.google.common.graph.MutableGraph;
import com.google.common.graph.StandardMutableGraph;
import com.google.errorprone.annotations.DoNotMock;

@DoNotMock
@Beta
public final class GraphBuilder<N>
extends AbstractGraphBuilder<N> {
    private GraphBuilder(boolean directed) {
        super(directed);
    }

    public static GraphBuilder<Object> directed() {
        return new GraphBuilder<Object>(true);
    }

    public static GraphBuilder<Object> undirected() {
        return new GraphBuilder<Object>(false);
    }

    public static <N> GraphBuilder<N> from(Graph<N> graph) {
        return new GraphBuilder<N>(graph.isDirected()).allowsSelfLoops(graph.allowsSelfLoops()).nodeOrder(graph.nodeOrder()).incidentEdgeOrder(graph.incidentEdgeOrder());
    }

    public <N1 extends N> ImmutableGraph.Builder<N1> immutable() {
        GraphBuilder<N1> castBuilder = this.cast();
        return new ImmutableGraph.Builder<N1>(castBuilder);
    }

    public GraphBuilder<N> allowsSelfLoops(boolean allowsSelfLoops) {
        this.allowsSelfLoops = allowsSelfLoops;
        return this;
    }

    public GraphBuilder<N> expectedNodeCount(int expectedNodeCount) {
        this.expectedNodeCount = Optional.of(Graphs.checkNonNegative(expectedNodeCount));
        return this;
    }

    public <N1 extends N> GraphBuilder<N1> nodeOrder(ElementOrder<N1> nodeOrder) {
        GraphBuilder<N1> newBuilder = this.cast();
        newBuilder.nodeOrder = Preconditions.checkNotNull(nodeOrder);
        return newBuilder;
    }

    public <N1 extends N> GraphBuilder<N1> incidentEdgeOrder(ElementOrder<N1> incidentEdgeOrder) {
        Preconditions.checkArgument(incidentEdgeOrder.type() == ElementOrder.Type.UNORDERED || incidentEdgeOrder.type() == ElementOrder.Type.STABLE, "The given elementOrder (%s) is unsupported. incidentEdgeOrder() only supports ElementOrder.unordered() and ElementOrder.stable().", incidentEdgeOrder);
        GraphBuilder<N1> newBuilder = this.cast();
        newBuilder.incidentEdgeOrder = Preconditions.checkNotNull(incidentEdgeOrder);
        return newBuilder;
    }

    public <N1 extends N> MutableGraph<N1> build() {
        return new StandardMutableGraph(this);
    }

    GraphBuilder<N> copy() {
        GraphBuilder<N> newBuilder = new GraphBuilder<N>(this.directed);
        newBuilder.allowsSelfLoops = this.allowsSelfLoops;
        newBuilder.nodeOrder = this.nodeOrder;
        newBuilder.expectedNodeCount = this.expectedNodeCount;
        newBuilder.incidentEdgeOrder = this.incidentEdgeOrder;
        return newBuilder;
    }

    private <N1 extends N> GraphBuilder<N1> cast() {
        return this;
    }
}

