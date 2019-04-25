/*
Copyright 2003 by Steven S. Skiena; all rights reserved.

Permission is granted for use in non-commercial applications
provided this copyright notice remains intact and unchanged.

This program appears in my book:

"Programming Challenges: The Programming Contest Training Manual"
by Steven Skiena and Miguel Revilla, Springer-Verlag, New York 2003.

See our website www.programming-challenges.com for additional information.

This book can be ordered from Amazon.com at

http://www.amazon.com/exec/obidos/ASIN/0387001638/thealgorithmrepo/

*/
package com.algorist.wgraph;

import com.algorist.graph.BFS;
import com.algorist.graph.GraphSearchCallback;

import static java.lang.Math.min;

/**
 * Network flow implementation -- Ford-Fulkerson augmenting path algorithm.
 *
 * @author csong2022
 */
public class Netflow {
    private final FlowGraph g;
    private final BFS<FlowEdgeNode> bfs;

    public Netflow(FlowGraph g, int source, int sink) {
        this.g = g;
        g.addResidualEdges();

        this.bfs = new BFS<>(g);
        NetflowCallback callback = new NetflowCallback();
        bfs.search(source, callback);

        int volume = pathVolume(source, sink); /* weight of the augmenting path */

        while (volume > 0) {
            augmentPath(source, sink, volume);

            bfs.initialize();
            bfs.search(source, callback);

            volume = pathVolume(source, sink);
        }
    }

    private int pathVolume(int start, int end) {
        if (parent(end) == -1) return 0;

        FlowEdgeNode e = g.findEdge(parent(end), end); /* edge in question */

        if (start == parent(end)) {
            return e.residual;
        } else {
            return min(pathVolume(start, parent(end)), e.residual);
        }
    }

    private void augmentPath(int start, int end, int volume) {
        if (start == end) return;

        FlowEdgeNode e = g.findEdge(parent(end), end); /* edge in question */
        e.increaseFlow(volume);

        e = g.findEdge(end, parent(end));
        e.increaseResidual(volume);

        augmentPath(start, parent(end), volume);
    }

    private int parent(int x) {
        return bfs.parent(x);
    }

    private static class NetflowCallback implements GraphSearchCallback<FlowEdgeNode> {
        @Override
        public boolean validateEdge(FlowEdgeNode e) {
            return e.residual() > 0;
        }

        @Override
        public void processVertexEarly(int v) {
        }

        @Override
        public void processVertexLate(int v) {
        }

        @Override
        public void processEdge(int x, int y) {
        }
    }
}
