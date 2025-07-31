/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.EndpointPair;
import com.google.common.graph.GraphConnections;
import com.google.common.graph.Graphs;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.checkerframework.checker.nullness.qual.Nullable;

final class DirectedGraphConnections<N, V>
implements GraphConnections<N, V> {
    private static final Object PRED = new Object();
    private final Map<N, Object> adjacentNodeValues;
    private final @Nullable List<NodeConnection<N>> orderedNodeConnections;
    private int predecessorCount;
    private int successorCount;

    private DirectedGraphConnections(Map<N, Object> adjacentNodeValues, @Nullable List<NodeConnection<N>> orderedNodeConnections, int predecessorCount, int successorCount) {
        this.adjacentNodeValues = Preconditions.checkNotNull(adjacentNodeValues);
        this.orderedNodeConnections = orderedNodeConnections;
        this.predecessorCount = Graphs.checkNonNegative(predecessorCount);
        this.successorCount = Graphs.checkNonNegative(successorCount);
        Preconditions.checkState(predecessorCount <= adjacentNodeValues.size() && successorCount <= adjacentNodeValues.size());
    }

    static <N, V> DirectedGraphConnections<N, V> of(ElementOrder<N> incidentEdgeOrder) {
        ArrayList<NodeConnection<N>> orderedNodeConnections;
        int initialCapacity = 4;
        switch (incidentEdgeOrder.type()) {
            case UNORDERED: {
                orderedNodeConnections = null;
                break;
            }
            case STABLE: {
                orderedNodeConnections = new ArrayList<NodeConnection<N>>();
                break;
            }
            default: {
                throw new AssertionError((Object)incidentEdgeOrder.type());
            }
        }
        return new DirectedGraphConnections(new HashMap(initialCapacity, 1.0f), orderedNodeConnections, 0, 0);
    }

    static <N, V> DirectedGraphConnections<N, V> ofImmutable(N thisNode, Iterable<EndpointPair<N>> incidentEdges, Function<N, V> successorNodeToValueFn) {
        Preconditions.checkNotNull(thisNode);
        Preconditions.checkNotNull(successorNodeToValueFn);
        HashMap<N, Object> adjacentNodeValues = new HashMap<N, Object>();
        ImmutableList.Builder orderedNodeConnectionsBuilder = ImmutableList.builder();
        int predecessorCount = 0;
        int successorCount = 0;
        for (EndpointPair<N> incidentEdge : incidentEdges) {
            if (incidentEdge.nodeU().equals(thisNode) && incidentEdge.nodeV().equals(thisNode)) {
                adjacentNodeValues.put(thisNode, new PredAndSucc(successorNodeToValueFn.apply(thisNode)));
                orderedNodeConnectionsBuilder.add(new NodeConnection.Pred<N>(thisNode));
                orderedNodeConnectionsBuilder.add(new NodeConnection.Succ<N>(thisNode));
                ++predecessorCount;
                ++successorCount;
                continue;
            }
            if (incidentEdge.nodeV().equals(thisNode)) {
                N predecessor = incidentEdge.nodeU();
                Object existingValue = adjacentNodeValues.put(predecessor, PRED);
                if (existingValue != null) {
                    adjacentNodeValues.put(predecessor, new PredAndSucc(existingValue));
                }
                orderedNodeConnectionsBuilder.add(new NodeConnection.Pred<N>(predecessor));
                ++predecessorCount;
                continue;
            }
            Preconditions.checkArgument(incidentEdge.nodeU().equals(thisNode));
            N successor = incidentEdge.nodeV();
            V value = successorNodeToValueFn.apply(successor);
            V existingValue = adjacentNodeValues.put(successor, value);
            if (existingValue != null) {
                Preconditions.checkArgument(existingValue == PRED);
                adjacentNodeValues.put(successor, new PredAndSucc(value));
            }
            orderedNodeConnectionsBuilder.add(new NodeConnection.Succ<N>(successor));
            ++successorCount;
        }
        return new DirectedGraphConnections(adjacentNodeValues, orderedNodeConnectionsBuilder.build(), predecessorCount, successorCount);
    }

    @Override
    public Set<N> adjacentNodes() {
        if (this.orderedNodeConnections == null) {
            return Collections.unmodifiableSet(this.adjacentNodeValues.keySet());
        }
        return new AbstractSet<N>(){

            @Override
            public UnmodifiableIterator<N> iterator() {
                final Iterator nodeConnections = DirectedGraphConnections.this.orderedNodeConnections.iterator();
                final HashSet seenNodes = new HashSet();
                return new AbstractIterator<N>(){

                    @Override
                    protected N computeNext() {
                        while (nodeConnections.hasNext()) {
                            NodeConnection nodeConnection = (NodeConnection)nodeConnections.next();
                            boolean added = seenNodes.add(nodeConnection.node);
                            if (!added) continue;
                            return nodeConnection.node;
                        }
                        return this.endOfData();
                    }
                };
            }

            @Override
            public int size() {
                return DirectedGraphConnections.this.adjacentNodeValues.size();
            }

            @Override
            public boolean contains(@Nullable Object obj) {
                return DirectedGraphConnections.this.adjacentNodeValues.containsKey(obj);
            }
        };
    }

    @Override
    public Set<N> predecessors() {
        return new AbstractSet<N>(){

            @Override
            public UnmodifiableIterator<N> iterator() {
                if (DirectedGraphConnections.this.orderedNodeConnections == null) {
                    final Iterator entries = DirectedGraphConnections.this.adjacentNodeValues.entrySet().iterator();
                    return new AbstractIterator<N>(){

                        @Override
                        protected N computeNext() {
                            while (entries.hasNext()) {
                                Map.Entry entry = (Map.Entry)entries.next();
                                if (!DirectedGraphConnections.isPredecessor(entry.getValue())) continue;
                                return entry.getKey();
                            }
                            return this.endOfData();
                        }
                    };
                }
                final Iterator nodeConnections = DirectedGraphConnections.this.orderedNodeConnections.iterator();
                return new AbstractIterator<N>(){

                    @Override
                    protected N computeNext() {
                        while (nodeConnections.hasNext()) {
                            NodeConnection nodeConnection = (NodeConnection)nodeConnections.next();
                            if (!(nodeConnection instanceof NodeConnection.Pred)) continue;
                            return nodeConnection.node;
                        }
                        return this.endOfData();
                    }
                };
            }

            @Override
            public int size() {
                return DirectedGraphConnections.this.predecessorCount;
            }

            @Override
            public boolean contains(@Nullable Object obj) {
                return DirectedGraphConnections.isPredecessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
            }
        };
    }

    @Override
    public Set<N> successors() {
        return new AbstractSet<N>(){

            @Override
            public UnmodifiableIterator<N> iterator() {
                if (DirectedGraphConnections.this.orderedNodeConnections == null) {
                    final Iterator entries = DirectedGraphConnections.this.adjacentNodeValues.entrySet().iterator();
                    return new AbstractIterator<N>(){

                        @Override
                        protected N computeNext() {
                            while (entries.hasNext()) {
                                Map.Entry entry = (Map.Entry)entries.next();
                                if (!DirectedGraphConnections.isSuccessor(entry.getValue())) continue;
                                return entry.getKey();
                            }
                            return this.endOfData();
                        }
                    };
                }
                final Iterator nodeConnections = DirectedGraphConnections.this.orderedNodeConnections.iterator();
                return new AbstractIterator<N>(){

                    @Override
                    protected N computeNext() {
                        while (nodeConnections.hasNext()) {
                            NodeConnection nodeConnection = (NodeConnection)nodeConnections.next();
                            if (!(nodeConnection instanceof NodeConnection.Succ)) continue;
                            return nodeConnection.node;
                        }
                        return this.endOfData();
                    }
                };
            }

            @Override
            public int size() {
                return DirectedGraphConnections.this.successorCount;
            }

            @Override
            public boolean contains(@Nullable Object obj) {
                return DirectedGraphConnections.isSuccessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
            }
        };
    }

    @Override
    public Iterator<EndpointPair<N>> incidentEdgeIterator(final N thisNode) {
        Preconditions.checkNotNull(thisNode);
        final Iterator resultWithDoubleSelfLoop = this.orderedNodeConnections == null ? Iterators.concat(Iterators.transform(this.predecessors().iterator(), new Function<N, EndpointPair<N>>(){

            @Override
            public EndpointPair<N> apply(N predecessor) {
                return EndpointPair.ordered(predecessor, thisNode);
            }
        }), Iterators.transform(this.successors().iterator(), new Function<N, EndpointPair<N>>(){

            @Override
            public EndpointPair<N> apply(N successor) {
                return EndpointPair.ordered(thisNode, successor);
            }
        })) : Iterators.transform(this.orderedNodeConnections.iterator(), new Function<NodeConnection<N>, EndpointPair<N>>(){

            @Override
            public EndpointPair<N> apply(NodeConnection<N> connection) {
                if (connection instanceof NodeConnection.Succ) {
                    return EndpointPair.ordered(thisNode, connection.node);
                }
                return EndpointPair.ordered(connection.node, thisNode);
            }
        });
        final AtomicBoolean alreadySeenSelfLoop = new AtomicBoolean(false);
        return new AbstractIterator<EndpointPair<N>>(){

            @Override
            protected EndpointPair<N> computeNext() {
                while (resultWithDoubleSelfLoop.hasNext()) {
                    EndpointPair edge = (EndpointPair)resultWithDoubleSelfLoop.next();
                    if (edge.nodeU().equals(edge.nodeV())) {
                        if (alreadySeenSelfLoop.getAndSet(true)) continue;
                        return edge;
                    }
                    return edge;
                }
                return (EndpointPair)this.endOfData();
            }
        };
    }

    @Override
    public V value(N node) {
        Preconditions.checkNotNull(node);
        Object value = this.adjacentNodeValues.get(node);
        if (value == PRED) {
            return null;
        }
        if (value instanceof PredAndSucc) {
            return (V)((PredAndSucc)value).successorValue;
        }
        return (V)value;
    }

    @Override
    public void removePredecessor(N node) {
        boolean removedPredecessor;
        Preconditions.checkNotNull(node);
        Object previousValue = this.adjacentNodeValues.get(node);
        if (previousValue == PRED) {
            this.adjacentNodeValues.remove(node);
            removedPredecessor = true;
        } else if (previousValue instanceof PredAndSucc) {
            this.adjacentNodeValues.put(node, ((PredAndSucc)previousValue).successorValue);
            removedPredecessor = true;
        } else {
            removedPredecessor = false;
        }
        if (removedPredecessor) {
            Graphs.checkNonNegative(--this.predecessorCount);
            if (this.orderedNodeConnections != null) {
                this.orderedNodeConnections.remove(new NodeConnection.Pred<N>(node));
            }
        }
    }

    @Override
    public V removeSuccessor(Object node) {
        Object removedValue;
        Preconditions.checkNotNull(node);
        Object previousValue = this.adjacentNodeValues.get(node);
        if (previousValue == null || previousValue == PRED) {
            removedValue = null;
        } else if (previousValue instanceof PredAndSucc) {
            this.adjacentNodeValues.put(node, PRED);
            removedValue = ((PredAndSucc)previousValue).successorValue;
        } else {
            this.adjacentNodeValues.remove(node);
            removedValue = previousValue;
        }
        if (removedValue != null) {
            Graphs.checkNonNegative(--this.successorCount);
            if (this.orderedNodeConnections != null) {
                this.orderedNodeConnections.remove(new NodeConnection.Succ<Object>(node));
            }
        }
        return (V)removedValue;
    }

    @Override
    public void addPredecessor(N node, V unused) {
        boolean addedPredecessor;
        Object previousValue = this.adjacentNodeValues.put(node, PRED);
        if (previousValue == null) {
            addedPredecessor = true;
        } else if (previousValue instanceof PredAndSucc) {
            this.adjacentNodeValues.put(node, previousValue);
            addedPredecessor = false;
        } else if (previousValue != PRED) {
            this.adjacentNodeValues.put(node, new PredAndSucc(previousValue));
            addedPredecessor = true;
        } else {
            addedPredecessor = false;
        }
        if (addedPredecessor) {
            Graphs.checkPositive(++this.predecessorCount);
            if (this.orderedNodeConnections != null) {
                this.orderedNodeConnections.add(new NodeConnection.Pred<N>(node));
            }
        }
    }

    @Override
    public V addSuccessor(N node, V value) {
        Object previousSuccessor;
        Object previousValue = this.adjacentNodeValues.put(node, value);
        if (previousValue == null) {
            previousSuccessor = null;
        } else if (previousValue instanceof PredAndSucc) {
            this.adjacentNodeValues.put(node, new PredAndSucc(value));
            previousSuccessor = ((PredAndSucc)previousValue).successorValue;
        } else if (previousValue == PRED) {
            this.adjacentNodeValues.put(node, new PredAndSucc(value));
            previousSuccessor = null;
        } else {
            previousSuccessor = previousValue;
        }
        if (previousSuccessor == null) {
            Graphs.checkPositive(++this.successorCount);
            if (this.orderedNodeConnections != null) {
                this.orderedNodeConnections.add(new NodeConnection.Succ<N>(node));
            }
        }
        return (V)previousSuccessor;
    }

    private static boolean isPredecessor(@Nullable Object value) {
        return value == PRED || value instanceof PredAndSucc;
    }

    private static boolean isSuccessor(@Nullable Object value) {
        return value != PRED && value != null;
    }

    private static abstract class NodeConnection<N> {
        final N node;

        NodeConnection(N node) {
            this.node = Preconditions.checkNotNull(node);
        }

        static final class Succ<N>
        extends NodeConnection<N> {
            Succ(N node) {
                super(node);
            }

            public boolean equals(Object that) {
                if (that instanceof Succ) {
                    return this.node.equals(((Succ)that).node);
                }
                return false;
            }

            public int hashCode() {
                return Succ.class.hashCode() + this.node.hashCode();
            }
        }

        static final class Pred<N>
        extends NodeConnection<N> {
            Pred(N node) {
                super(node);
            }

            public boolean equals(Object that) {
                if (that instanceof Pred) {
                    return this.node.equals(((Pred)that).node);
                }
                return false;
            }

            public int hashCode() {
                return Pred.class.hashCode() + this.node.hashCode();
            }
        }
    }

    private static final class PredAndSucc {
        private final Object successorValue;

        PredAndSucc(Object successorValue) {
            this.successorValue = successorValue;
        }
    }
}

