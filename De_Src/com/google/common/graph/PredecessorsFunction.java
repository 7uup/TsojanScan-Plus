/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.DoNotMock;

@DoNotMock(value="Implement with a lambda, or use GraphBuilder to build a Graph with the desired edges")
@Beta
public interface PredecessorsFunction<N> {
    public Iterable<? extends N> predecessors(N var1);
}

